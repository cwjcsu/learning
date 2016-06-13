package com.cwjcsu.thirdparty;

import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Toolkit;

/*$Id: $
 * author   date   comment
 * cwjcsu@gmail.com  2016年4月18日  Created
*/

public class SwingInfo {
	private static void log(Object msg) {
		System.out.println(msg);
	}

	public static void main(String[] args) {
		GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
		int width = gd.getDisplayMode().getWidth();
		int height = gd.getDisplayMode().getHeight();
		log("DisplayMode:" + width + "," + height);
		Toolkit tk = Toolkit.getDefaultToolkit();
		log("screnn Size:"+tk.getScreenSize());
		log("dpi:"+tk.getScreenResolution());
	}

}
