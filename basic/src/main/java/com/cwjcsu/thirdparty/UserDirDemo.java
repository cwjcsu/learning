package com.cwjcsu.thirdparty;

import java.io.File;

/*$Id: $
 * author   date   comment
 * cwjcsu@gmail.com  2016年5月6日  Created
*/

public class UserDirDemo {
	/*
	 * user.dir是脚本所在的路径，new File(".")
	 *
[root@ha1 opt]# ydw/test.sh
/opt/ydw
user.dir:/opt
.:/opt/.
AgentdHome:null

	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("user.dir:" + System.getProperty("user.dir"));
		System.out.println(".:" + new File(".").getAbsolutePath());
		System.out.println("AgentdHome:" +System.getProperty("agentdHome"));
	}

}
