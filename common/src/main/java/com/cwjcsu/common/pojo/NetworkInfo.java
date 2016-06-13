package com.cwjcsu.common.pojo;
/**
 * 
 * @author atlas
 * @date   2012-8-23
 */
public class NetworkInfo {
	private String mac;
	private String ip;
	private String netIfName;
	private String hostname;
	private String broadcastIp;
	private short networkPrefixLength;

	public String getMac() {
		return mac;
	}

	public void setMac(String mac) {
		this.mac = mac;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getHostname() {
		return hostname;
	}

	public void setHostname(String hostname) {
		this.hostname = hostname;
	}

	public String getNetIfName() {
		return netIfName;
	}

	public void setNetIfName(String netIfName) {
		this.netIfName = netIfName;
	}
	
	@Override
    public String toString() {
	    StringBuilder builder = new StringBuilder();
	    builder.append("NetworkInfo [mac=");
	    builder.append(mac);
	    builder.append(", ip=");
	    builder.append(ip);
	    builder.append(", netIfName=");
	    builder.append(netIfName);
	    builder.append(", hostname=");
	    builder.append(hostname);
	    builder.append(", broadcastIp=");
	    builder.append(broadcastIp);
	    builder.append(", networkPrefixLength=");
	    builder.append(networkPrefixLength);
	    builder.append("]");
	    return builder.toString();
    }

	public void setBroadcastIp(String broadcastIp) {
	    this.broadcastIp = broadcastIp;
    }

	public void setNetworkPrefixLength(short networkPrefixLength) {
	    this.networkPrefixLength = networkPrefixLength;
    }

	public String getBroadcastIp() {
    	return broadcastIp;
    }

	public short getNetworkPrefixLength() {
    	return networkPrefixLength;
    }
	
}