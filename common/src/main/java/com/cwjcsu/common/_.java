package com.cwjcsu.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author atlas
 * @date 2013-1-7
 */
public class _ {
	public static String $(final String messagePattern,
			final Object... argArray) {
		if (argArray == null || argArray.length == 0)
			return messagePattern;
		return MF.$(messagePattern, argArray);
	}

	public static final Logger COMMON = __("COMMON");
	public static final Logger RESTFUL = __("RESTFUL");
	public static final Logger CMM = __("CMM");
	public static final Logger CCM = __("CCM");
	public static final Logger TRAP = __("TRAP");
	public static final Logger STATUS = __("STATUS");
	public static final Logger NODE_FAULT = __("NODE_FAULT");
	public static final Logger NODE_STATE_CACHE = __("NODE_STATE_CACHE");
	public static final Logger ADMIN = __("ADMIN");
	public static final Logger WEB = __("WEB");
	public static final Logger LIC = __("LIC");

	public static final Logger NET_FD = __("NET_FD");

	/**
	 * coordinator
	 */
	public static final Logger ELECT = __("ELECT");

	public static final Logger CLUSTER = __("CLUSTER");
	
	public static final Logger EXE = __("EXE");

	public static final Logger SPLIT = __("SPLIT");

	public static final Logger CONFIG = __("CONFIG");

	public static final Logger JGROUPS = __("JGROUPS");

	/**
	 * JGroupMember
	 */
	public static final Logger JGMEM = __("JGMEM");

	public static final Logger STARTUP = __("STARTUP");

	/**
	 * HaCoreWiringService
	 */
	public static final Logger WIRE = __("WIRE");

	public static final Logger EVENT = __("EVENT");

	public static final Logger PROTO = __("PROTO");

	public static final Logger JG_CMD = __("JG_CMD");

	public static final Logger CACHE = __("CACHE");

	public static final Logger RETRY = __("RETRY");

	public static final Logger INTERNAL = __("INTERNAL");

	public static final Logger LOCAL = __("LOCAL");

	public static final Logger HEARTBEAT = __("HEARTBEAT");

	public static final Logger FSM = __("FSM");

	public static final Logger WatchDog = __("WatchDog");

	public static final Logger WatchDogFeed = LoggerFactory
			.getLogger("WatchDogFeed");

	public static final Logger SPY = __("SPY");

	public static final String PREFFIX = "h.";

	public static final Logger PING = __("PING");

	public static final Logger SYNC_CACHE = __("SYNC_CACHE");

	public static final Logger JNI = __("JNI");

	public static final Logger EXEC = __("EXEC");

	public static final Logger SNMP_AGENT = __("SNMP-AGENT");

	public static final Logger META = __("META");

	public static final Logger TASK = __("TASK");

	public static final Logger DRBD = __("DRBD");
	
	public static final Logger SCSI = __("SCSI");

	private static final Logger __(String log) {
		return LoggerFactory.getLogger(PREFFIX + log);
	}
}
