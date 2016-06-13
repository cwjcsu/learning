package com.cwjcsu.learning.concurrent.concurrent_samples.asynctask.simplerpc;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.Socket;
import java.net.SocketException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

import com.cwjcsu.learning.concurrent.concurrent_samples.asynctask.Result;
import com.cwjcsu.learning.concurrent.concurrent_samples.asynctask.ResultFuture;
import com.cwjcsu.learning.concurrent.concurrent_samples.asynctask.ResultType;

public class RPCClient {
    
    final ConcurrentHashMap map = new ConcurrentHashMap();
    final AtomicLong idSeq = new AtomicLong(0);
    public class Request extends ResultFuture 
    {
        final long id;
        Request()
        {
            this.id = idSeq.incrementAndGet();
            map.put(id,this);
        }
        @Override
        public boolean setResult(Result r) {
            boolean bok = super.setResult(r);
            if(bok)
                map.remove(this.id);
            return bok;
        }
        public Object getResultValue() throws Throwable
        {
            Result r = super.get();
            if(r.type == ResultType.RESULT_OK)
                return r.value;
            if(r.value != null && (r.value instanceof Throwable))
                throw (Throwable)r.value;
            throw new RuntimeException(r.type.name());
        }
    }
    final ObjectOutputStream out;
    final Socket sock;
    final ObjectInputStream in;
    public RPCClient(String server,int port) throws Exception
    {
        if(server == null) server = "127.0.0.1";
        if(port == 0) port = RPCServer.PORT;
        sock = new Socket(server,port);
        out = new ObjectOutputStream(new BufferedOutputStream(sock.getOutputStream()));
        out.flush();
        in = new ObjectInputStream(new BufferedInputStream(sock.getInputStream()));
        new ConnThread().start();
    }
    volatile boolean closed = false;
    public void close()
    {
        closed = true;
        Util.close(sock);
        for(Object req:map.values().toArray())
        {
            ((Request)req).cancel();
        }
        
    }
    class ConnThread extends Thread
    {
        public void run()
        {
            try
            {
                for(;;)
                {
                    long id = in.readLong();
                    ResultType type = (ResultType)in.readObject();
                    Object value = in.readObject();
                    Request req = (Request)map.get(id);
                    if(req != null)
                    {
                        req.setResult(new Result(type,value));
                    }
                }
            }
            catch(Exception ex)
            {
                if(!closed || !(ex instanceof SocketException))
                    ex.printStackTrace();
            }
            close();
        }
    }
    public RPCClient(String server) throws Exception
    {
        this(server,0);
    }
    public RPCClient() throws Exception
    {
        this(null,0);
    }
    public <T> T createService(final String name,final Class<T> model) throws Exception
    {
        name.getClass();
        return (T)Proxy.newProxyInstance(model.getClassLoader(),new Class[]{model},new InvocationHandler()
        {
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                Request req = new Request();
                map.put(req.id,req);
                try
                {
                    try
                    {
                        synchronized(out)
                        {
                            out.reset();
                            out.writeLong(req.id);
                            out.writeObject(name);
                            out.writeObject(model);
                            out.writeObject(method.getName());
                            out.writeObject(method.getParameterTypes());
                            out.writeObject(args);
                            out.flush();
                        }
                    }
                    catch(Throwable ex)
                    {
                        close();
                        throw ex;
                    }
                    
                    return req.getResultValue();
                }
                finally
                {
                    req.cancel();
                }
            }
            
        });
    }
     
}
