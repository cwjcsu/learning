package com.cwjcsu.common;

import static com.cwjcsu.common._.$;

import java.io.IOException;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import com.cwjcsu.common.ping.Ping;
import org.slf4j.Logger;


/**
 * 
 * @author atlas
 * @date 2012-11-12
 */
public class NetUtil {
	private static final Logger logger = _.COMMON;

	private static final Pattern IP_PATTERN = Pattern
			.compile("^(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$");

	/**
	 * [239.0.0.0,239.255.255.255
	 */
	private static final Pattern ADMINISTRATIVE_MULTICAST_IP_PATTERN = Pattern
			.compile("^(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$");

	/**
	 * 根据ip地址或者host name获取本地的网络接口，如果不是本地网络接口，则返回null
	 * 
	 * @param ip
	 * @return
	 */
	public static NetworkInterface getLocalInterface(String ip) {
		try {
			return getLocalInterface(InetAddress.getByName(ip));
		} catch (UnknownHostException e) {
			return null;
		}
	}

	public static InetAddress LOOPBACK_ADDRESS;
	public static InetAddress ANY_ADDRESS;

	public static InetAddress getLoopbackAddress() {
		if (LOOPBACK_ADDRESS != null)
			return LOOPBACK_ADDRESS;
		InetAddress address = getLoopbackAddress0();
		LOOPBACK_ADDRESS = address;
		return address;
	}

	public static boolean isLoopback(InetAddress address) {
		String name = address.getHostAddress();
		return address.isLoopbackAddress()
				&& IP_PATTERN.matcher(name).matches();
	}

	public static boolean isIpV4Address(String ipV4) {
		return IP_PATTERN.matcher(ipV4).matches();
	}

	public static InetAddress getLocalAnyAddress() {
		try {
			if (ANY_ADDRESS != null)
				return ANY_ADDRESS;
			return (ANY_ADDRESS = InetAddress.getByName("0.0.0.0"));
		} catch (UnknownHostException e) {
			logger.warn("" + e);
			throw new RuntimeException(e);
		}
	}

	public static InetAddress getLoopbackAddress0() {
		InetAddress address = null;
		try {
			address = InetAddress.getByName("127.0.0.1");
		} catch (UnknownHostException e) {
			logger.warn("127.0.0.1 is not loopback address", e);
		}
		if (address != null && isLoopback(address))
			return address;

		try {
			address = InetAddress.getByName("localhost");
		} catch (UnknownHostException e) {
			logger.warn("localhost is not loopback address", e);
		}
		if (address != null && isLoopback(address))
			return address;
		try {
			Enumeration<NetworkInterface> interfaces = NetworkInterface
					.getNetworkInterfaces();
			if (interfaces != null) {
				while (interfaces.hasMoreElements()) {
					try {
						NetworkInterface network = interfaces.nextElement();
						Enumeration<InetAddress> addresses = network
								.getInetAddresses();
						if (addresses != null) {
							while (addresses.hasMoreElements()) {
								try {
									address = addresses.nextElement();
									if (isLoopback(address))
										return address;

								} catch (Throwable e) {
									logger.warn(
											"Failed to retriving ip address, "
													+ e.getMessage(), e);
								}
							}
						}
					} catch (Throwable e) {
						logger.warn(
								"Failed to retriving ip address, "
										+ e.getMessage(), e);
					}
				}
			}
		} catch (Throwable e) {
			logger.warn("Failed to retriving ip address, " + e.getMessage(), e);
		}
		return address;
	}

	public static NetworkInterface getLocalInterface(InetAddress ip) {
		try {
			NetworkInterface inter = NetworkInterface.getByInetAddress(ip);
			return inter;
		} catch (SocketException e) {
			return null;
		}
	}

	public static boolean isAnyHost(String host) {
		return "0.0.0.0".equals(host);
	}

	public static boolean ping(InetAddress host) {
		return ping(host, 3000);
	}

	/**
	 * 先检查InetAddress.isReachable,如果false，再使用ping检查
	 * 
	 * @see #isReachable(InetAddress, int)
	 * @param host
	 * @return
	 */
	public static boolean isReachable(InetAddress host) {
		return isReachable(host, 3000);
	}

	/**
	 * 先检查InetAddress.isReachable,如果false，再使用ping检查
	 * 
	 * @param host
	 * @param timeout
	 * @return
	 */
	public static boolean isReachable(InetAddress host, int timeout) {
		boolean reachable = false;
		try {
			reachable = host.isReachable(timeout);
			if (reachable)
				// isReachable return true 在网卡断掉的瞬间不靠普，这种情况下调用者需要多次尝试以获取正确结果
				return true;
		} catch (IOException e) {

		}
		// isReachable return false 不靠谱
		return ping(host, timeout);
	}

	/**
	 * 
	 * @param host
	 * @param timout
	 *            in ms
	 * @return
	 */
	public static boolean ping(InetAddress host, int timeout, boolean debug) {
		// boolean reachable = false;
		// try {
		// reachable = host.isReachable(timeout);
		// if (reachable)
		// return true;
		// } catch (IOException e) {
		//
		// }
		// isReachable return true 在网卡断掉的瞬间也不靠普，这种情况下调用者需要多次尝试以获取正确结果
		// isReachable return false 不靠谱
		try {
			String hostAddr = host.getHostAddress();
			int time = Ping.ping(hostAddr, timeout, debug);
			if (time <= timeout) {
				return true;
			}
		} catch (Exception e0) {
			return false;
		}
		return false;
	}

	public static List<Boolean> ping(List<String> hosts, int timeout,
			boolean debug) {
		Map<String, Integer> results = Ping.ping(hosts, timeout, debug);
		List<Boolean> ret = new ArrayList<Boolean>();
		for (String host : hosts) {
			Integer time = results.get(host);
			if (time != null && time <= timeout) {
				ret.add(Boolean.TRUE);
			} else {
				ret.add(Boolean.FALSE);
			}
		}
		return ret;
	}

	public static boolean ping(InetAddress host, int timeout) {
		return ping(host, timeout, true);
	}

	/**
	 * parse a ip address in format ip[:port],if port not specified,the returned
	 * IpAddress's port will be set to 0
	 * 
	 * @param address
	 * @return
	 * @throws UnknownHostException
	 */
	public static IpAddress parseIpAddress(String address)
			throws UnknownHostException {
		if (StringUtils.isEmpty(address)) {
			return null;
		}
		int port = 0;
		String[] addresses = address.split(":");
		if (addresses.length == 2) {
			try {
				port = Integer.parseInt(addresses[1]);
				if ((port < 0) || (port > 65535)) {
					throw new UnknownHostException($("Illegal port : {}", port));
				}
			} catch (NumberFormatException e) {
				throw new UnknownHostException($("Illegal port : {}",
						addresses[1]));
			}
		}
		String addrStr = addresses[0];
		try {
			InetAddress ip = InetAddress.getByName(addrStr);
			return new IpAddress(ip, port);
		} catch (UnknownHostException e) {
			throw e;
		}
	}

	/**
	 * The range from 239.0.0.0 through 239.255.255.255 is reserved for
	 * administratively scoped addresses.
	 * 
	 * @param addr
	 * @return
	 */
	public static boolean isAdministrativeMulticastAddress(InetAddress addr) {
		long ipLong = ipToLong(addr);
		if (ipLong >= MULTICAST_ADDR_ADMINISTRATIVE_START
				&& ipLong <= MULTICAST_ADDR_ADMINISTRATIVE_END) {
			return true;
		}
		return false;
	}

	public static long MULTICAST_ADDR_ADMINISTRATIVE_START;
	public static long MULTICAST_ADDR_ADMINISTRATIVE_END;
	static {
		try {
			MULTICAST_ADDR_ADMINISTRATIVE_START = ipToLong("239.0.0.0");
			MULTICAST_ADDR_ADMINISTRATIVE_END = ipToLong("239.255.255.255");
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}

	public static long ipToLong(InetAddress ipAddress) {
		try {
			return ipToLong(ipAddress.getHostAddress());
		} catch (UnknownHostException e) {
			// never happend
			return -1;
		}
	}

	public static long ipToLong(String ipAddress) throws UnknownHostException {
		long result = 0;
		String[] ipAddressInArray = ipAddress.split("\\.");
		if (ipAddressInArray.length != 4) {
			throw new UnknownHostException(ipAddress);
		}
		try {
			for (int i = 3; i >= 0; i--) {
				long ip = Long.parseLong(ipAddressInArray[3 - i]);
				// left shifting 24,16,8,0 and bitwise OR

				// 1. 192 << 24
				// 1. 168 << 16
				// 1. 1 << 8
				// 1. 2 << 0
				result |= ip << (i * 8);
			}
		} catch (NumberFormatException e) {
			throw new UnknownHostException(ipAddress);
		}
		return result;
	}

	public static void main(String[] args) {
		System.out.println(getLocalInterface("127.0.0.1"));
		System.out.println(getLocalInterface("localhost"));
		System.out.println(getLocalInterface("192.168.9.44"));
		System.out.println(getLocalInterface("192.168.3.37"));

		System.out.println(getLocalInterface("192.168.3.11"));
		System.out.println(getLocalInterface("192.168.9.11"));
	}

}
