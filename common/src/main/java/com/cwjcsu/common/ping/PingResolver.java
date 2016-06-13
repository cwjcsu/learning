package com.cwjcsu.common.ping;

import java.util.logging.Logger;

public interface PingResolver {
	/**
	 * 返回解析后的ping时间，用毫秒做单位，如果解析出错，返回Integer.MAX_VALUE
	 * 
	 * @param result
	 * @return
	 */
	int getPingTime(String output);

	String getPingCommand(int count, String host);

	/**
	 * 
	 * @param count
	 *            Stop after sending count ECHO_REQUEST packets. With deadline
	 *            option, ping waits for count ECHO_REPLY packets, until the
	 *            timeout expires.
	 * @param w
	 *            deadline : Specify a timeout, in seconds, before ping exits
	 *            regardless of how many packets have been sent or received. In
	 *            this case ping does not stop after count packet are sent, it
	 *            waits either for deadline expire or until count probes are
	 *            answered or for some error notification from network.
	 * 
	 * @param host
	 * @return
	 */
	String getPingCommand(int count, int w, String host);

	/* Logger LOG = _.COMMON; */

	Logger LOG = Logger.getLogger("PING");
}
