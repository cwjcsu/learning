package com.cwjcsu.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

import org.slf4j.Logger;

/**
 * 
 * @author atlas
 * @date 2012-11-14
 */
public class HaEnv {
	private static final Logger log = _.COMMON;
	public static final String haenv_conf = "/etc/haenv";

	public static final int DEFAULT_HA_CLUSTER_PORT = 3388;

	private static Dict instance = null;

	public synchronized static Dict getEnv() {
		if (instance == null) {
			instance = load();
		}
		return instance;
	}

	private static Dict load() {
		Properties props = new Properties();
		try {
			InputStream input = new FileInputStream(haenv_conf);
			if (input != null) {
				props.load(input);
			}
		} catch (Exception e) {
			log.error("error loading haenv conf " + haenv_conf, e);
		}
		return new DictImpl(props, true);
	}

	public static String getPrefix() {
		return getEnv().get("PREFIX", "/opt/ha");
	}

	public static String getClusterConfig() {
		// $PREFIX/conf/cluster.xml
		return StringUtils.joinPath(getPrefix(), "/conf/cluster.xml");
	}

	public static String getTempClusterConfig() {
		String tempFile;
		while (true) {
			tempFile = StringUtils.joinPath(getPrefix(),
					_.$("/conf/cluster.xml.{}.tmp", Util.random(1000, 9999)));
			if (new File(tempFile).exists()) {
				Thread.yield();
				continue;
			} else {
				break;
			}
		}
		return tempFile;
	}

	/**
	 * 返回节点状态缓存文件的路径。
	 * 
	 * @return
	 */
	public static String getNodeStateCacheFile() {
		return StringUtils.joinPath(getPrefix(),
				"/recover/state-recover.properties");
	}

	public static int getClusterPort() {
		return getEnv().requireInteger("HA_CLUSTER_PORT",
				DEFAULT_HA_CLUSTER_PORT);
	}

	/**
	 * 
	 * @return
	 */
	public static String getPrincipalFile() {
		return StringUtils.joinPath(getPrefix(), "/conf/passwd");
	}

	public static String getHADCCommand() {
		return StringUtils.joinPath(getPrefix(), "/bin/hadc.py");
	}

	public static String getLRMDCommand() {
		return StringUtils.joinPath(getPrefix(), "/bin/halrmd.py");
	}

	public static String getHaDaemonsCommand() {
		return StringUtils.joinPath(getPrefix(), "/bin/hadaemons");
	}

	public static String getHaLogFilterCommand() {
		return StringUtils.joinPath(getPrefix(), "/bin/halogfilter");
	}

	public static String getResourcesPath() {
		return StringUtils.joinPath(getPrefix(), "/resource/resources");
	}

	public static String getMetadataFile(String type) {
		String path = StringUtils.joinPath(getResourcesPath(), type);
		return StringUtils.joinPath(path, "meta-data.xml");
	}

	public static String getLoogbackConfigFile() {
		return StringUtils.joinPath(getPrefix(), "/conf/hacmm-logback.xml");
	}

	public static String getLicensePath() {
		return StringUtils.joinPath(getPrefix(), "/lic/");
	}

	public static String getHaLogPath() {
		return "/var/log/skybility/ha.log";
	}

	public static String getHaDcLogPath() {
		return "/var/log/skybility/hadc.log";
	}

	public static String getHaCmmLogPath() {
		return "/var/log/skybility/hacmm.log";
	}

	public static String getHaLogDirectory() {
		return "/var/log/skybility";
	}

	/**
	 * 获取记录当前系统重启日志的文件路径
	 * 
	 * @return
	 */
	public static String getHaRebootLogFile() {
		return "/var/log/skybility/reboot.log";
	}

	public static String getRequestUrl(String host, String uri) {
		int port = HaEnv.getEnv().requireInteger("HAWEB_PORT");
		return StringUtils.joinPath("http://" + host + ":" + port + "/", uri);
	}

	public static String getFstatCommand() {
		return StringUtils.joinPath(getPrefix(), "/bin/hafstat");
	}

	public static String getChmodCommand() {
		return StringUtils.joinPath(getPrefix(), "/bin/hachmod");
	}

	public static String getResourceTemplatePath() {
		return StringUtils.joinPath(getPrefix(), "/resource/template");
	}

	public static String getRebootCommand() {
		return StringUtils.joinPath(getPrefix(), "/bin/hareboot");
	}

	public static String getJNILibDir() {
		return StringUtils.joinPath(getPrefix(), "/lib");
	}

	public static String getSolutionToErrorsPath() {
		return StringUtils.joinPath(getPrefix(), "/sltn");
	}

	public static String getDrbdCheckCommand() {
		return StringUtils.joinPath(getPrefix(), "/bin/hadmctl --show all");
	}
}
