package com.cwjcsu.learning.trial;
/**
 * 
 * @author atlas
 * @date 2013-3-20
 */
public class ProcessDemo {
	// public static void main(String[] args) {
	// Process process = null;
	// try{
	// //xxxx
	// }catch(xxx){
	//
	// }finally{
	// if(process!=null){
	// process.destroy();
	// }
	// }
	// }

	public static void main(String[] args) {
		System.out.println(getDuration(1000 * 60 * 62 * 3 + 2000));
	}

	public static String getDuration(long milliseconds) {
		int secs = (int) (milliseconds / 1000);
		int hours = secs / 3600;
		secs = secs - 3600 * hours;
		int minutes = secs / 60;
		secs = secs - minutes * 60;
		int seconds = secs;
		if (hours < 1)
			return minutes + ":" + seconds;
		return hours + ":" + minutes + ":" + seconds;
	}
}
