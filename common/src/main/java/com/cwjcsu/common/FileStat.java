package com.cwjcsu.common;

import java.io.File;
import java.io.Serializable;

/**
 * 
 * @author atlas
 * @date 2013-2-18
 */
public class FileStat implements Serializable {
	private static final long serialVersionUID = 1537983495431535184L;
	public final File file;
	/**
	 * mode in decimal format
	 */
	public final int mode;
	public final int uid;
	public final String user;
	public final int gid;
	public final String group;

	public FileStat(File file, int mode, int uid, String user, int gid,
			String group) {
		super();
		this.file = file;
		this.mode = mode;
		this.uid = uid;
		this.user = user;
		this.gid = gid;
		this.group = group;
	}

	@Override
	public String toString() {
		return "FileStat [file=" + file + ", mode="
				+ Integer.toOctalString(mode) + ", uid=" + uid + ", user="
				+ user + ", gid=" + gid + ", group=" + group + "]";
	}

}
