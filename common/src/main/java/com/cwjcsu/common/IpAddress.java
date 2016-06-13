package com.cwjcsu.common;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.*;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;

/**
 * Created by Weijun on 2016/6/12.
 */
public class IpAddress {
    private static final long serialVersionUID = 5877146630213185651L;
    private InetAddress ip_addr;
    private int port;
    protected static final Log log = LogFactory.getLog(IpAddress.class);
    static boolean resolve_dns;
    protected int size = -1;

    public IpAddress(String i, int p) throws UnknownHostException {
        this.port = p;
        this.ip_addr = InetAddress.getByName(i);
    }

    public IpAddress(InetAddress i, int p) {
        this.ip_addr = i;
        this.port = p;
        if (this.ip_addr == null) {
            setAddressToLocalHost();
        }
    }

    private void setAddressToLocalHost() {
        try {
            this.ip_addr = InetAddress.getLocalHost();
        } catch (Exception e) {
            this.ip_addr = null;
        }
        if (this.ip_addr == null) {
            try {
                this.ip_addr = InetAddress.getByName(null);
            } catch (UnknownHostException e) {
                log.error("exception: " + e);
            }
        }
    }

    public IpAddress(int port) {
        this(port, true);
    }

    public IpAddress(int port, boolean set_default_host) {
        this.port = port;
        if (set_default_host) {
            setAddressToLocalHost();
        }
    }

    public IpAddress(InetSocketAddress sock_addr) {
        this.port = sock_addr.getPort();
        this.ip_addr = sock_addr.getAddress();
    }

    public final InetAddress getIpAddress() {
        return this.ip_addr;
    }

    public final int getPort() {
        return this.port;
    }

    public final int compareTo(IpAddress o) {
        if (this == o) {
            return 0;
        }
        if (!(o instanceof IpAddress)) {
            throw new ClassCastException(
                    "comparison between different classes: the other object is " + (o != null ? o.getClass() : o));
        }
        IpAddress other = (IpAddress) o;
        if (this.ip_addr == null) {
            if (other.ip_addr == null) {
                return this.port > other.port ? 1 : this.port < other.port ? -1 : 0;
            }
            return -1;
        }
        int h1 = this.ip_addr.hashCode();
        int h2 = other.ip_addr.hashCode();
        int rc = h1 > h2 ? 1 : h1 < h2 ? -1 : 0;
        return this.port > other.port ? 1 : this.port < other.port ? -1 : rc != 0 ? rc : 0;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof IpAddress)) {
            return false;
        }
        IpAddress other = (IpAddress) obj;
        boolean sameIP;
        if (this.ip_addr != null) {
            sameIP = this.ip_addr.equals(other.ip_addr);
        } else {
            sameIP = other.ip_addr == null;
        }
        return (sameIP) && (this.port == other.port);
    }

    public final int hashCode() {
        return this.ip_addr != null ? this.ip_addr.hashCode() + this.port : this.port;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (this.ip_addr == null) {
            sb.append("<null>");
        } else if (this.ip_addr.isMulticastAddress()) {
            sb.append(this.ip_addr.getHostAddress());
        } else {
            String host_name;
            if (resolve_dns) {
                host_name = this.ip_addr.getHostName();
            } else {
                host_name = this.ip_addr.getHostAddress();
            }
            sb.append(host_name);
        }
        sb.append(":").append(this.port);
        return sb.toString();
    }

    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        try {
            readFrom(in);
        } catch (Exception e) {
            throw new IOException(e);
        }
    }

    public void writeExternal(ObjectOutput out) throws IOException {
        try {
            writeTo(out);
        } catch (Exception e) {
            throw new IOException(e);
        }
    }

    public void writeTo(DataOutput out) throws Exception {
        if (this.ip_addr != null) {
            byte[] address = this.ip_addr.getAddress();
            out.writeByte(address.length);
            out.write(address, 0, address.length);
            if ((this.ip_addr instanceof Inet6Address)) {
                out.writeInt(((Inet6Address) this.ip_addr).getScopeId());
            }
        } else {
            out.writeByte(0);
        }
        out.writeShort(this.port);
    }

    public void readFrom(DataInput in) throws Exception {
        int len = in.readByte();
        if ((len > 0) && (len != 4) && (len != 16)) {
            throw new IOException("length has to be 4 or 16 bytes (was " + len + " bytes)");
        }
        byte[] a = new byte[len];
        in.readFully(a);
        if (len == 16) {
            int scope_id = in.readInt();
            this.ip_addr = Inet6Address.getByAddress(null, a, scope_id);
        } else {
            this.ip_addr = InetAddress.getByAddress(a);
        }
        this.port = in.readUnsignedShort();
    }

    public int size() {
        if (this.size >= 0) {
            return this.size;
        }
        int tmp_size = 3;
        if (this.ip_addr != null) {
            tmp_size += this.ip_addr.getAddress().length;
            if ((this.ip_addr instanceof Inet6Address)) {
                tmp_size += 4;
            }
        }
        this.size = tmp_size;
        return tmp_size;
    }

    public IpAddress copy() {
        return new IpAddress(this.ip_addr, this.port);
    }

    public IpAddress() {
    }
}
