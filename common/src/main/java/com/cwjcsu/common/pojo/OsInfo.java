package com.cwjcsu.common.pojo;

/**
 * 
 * @author atlas
 * @date 2012-8-24
 */
public class OsInfo {
	private String name;
	private String version;
	private String arch;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getArch() {
		return arch;
	}

	public void setArch(String arch) {
		this.arch = arch;
	}

	@Override
	public String toString() {
		return name + "-" + version + "-" + arch;
	}
}
