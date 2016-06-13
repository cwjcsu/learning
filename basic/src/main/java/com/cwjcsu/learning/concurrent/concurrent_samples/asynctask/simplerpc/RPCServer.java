package com.cwjcsu.learning.concurrent.concurrent_samples.asynctask.simplerpc;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.EOFException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Method;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.cwjcsu.learning.concurrent.concurrent_samples.asynctask.ResultType;

public class RPCServer {

    static final int PORT = 4567;
    
    public static void main() throws Exception 
    {
        ServerSocket server = new ServerSocket(PORT);
        for(;;)
        {
            Socket sock = server.accept();
            new ServerConn(sock).start();
        }
    }
    
    static final ExecutorService threadPool = Executors.newCachedThreadPool();
    static final ConcurrentHashMap services = new ConcurrentHashMap();
    public static void registerService(String name,Object obj)
    {
        services.put(name,obj);
    }
    
    static class ServerConn extends Thread
    {
        Socket sock;

        public ServerConn(Socket sock) {
            super();
            this.sock = sock;
        }
        public void run()
        {
            try
            {
                final ObjectOutputStream out = new ObjectOutputStream(new BufferedOutputStream(sock.getOutputStream()));
                out.flush();
                ObjectInputStream in = new ObjectInputStream(new BufferedInputStream(sock.getInputStream()));
                
                for(;;)
                {
                    final long invoke_id = in.readLong();
                    final String serviceName = (String)in.readObject();
                    final Class serviceModel = (Class)in.readObject();
                    final String methodName = (String)in.readObject();
                    final Class[] argTypes = (Class[])in.readObject();
                    final Object[] args = (Object[])in.readObject();
                    threadPool.execute(new Runnable()
                    {
                       public void run()
                       {
                           Object result = null;
                           ResultType type = ResultType.RESULT_OK;
                           try
                           {
                               Object obj = services.get(serviceName);
                               if(obj == null)
                                   throw new Exception("service not found  " + serviceName);
                               Method md = serviceModel.getMethod(methodName,argTypes);
                               result = md.invoke(obj,args);
                           }
                           catch(Throwable ex)
                           {
                               type = ResultType.RESULT_ERR;
                               result = ex;
                           }
                           try
                           {
                               synchronized(out)
                               {
                                   out.reset();
                                   out.writeLong(invoke_id);
                                   out.writeObject(type);
                                   out.writeObject(result);
                                   out.flush();
                               }
                           }
                           catch(Exception ex)
                           {
                               ex.printStackTrace();
                               Util.close(sock);
                           }
                       }
                    });
                }
            }
            catch(Exception ex)
            {
                if(!(ex instanceof EOFException))
                    ex.printStackTrace();
            }
            Util.close(sock);
        }
    }
}
