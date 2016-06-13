/*$Id: $
 * author                     date    comment
 * cwjcsu@gmail.com  2015年11月30日  Created
*/ 
package com.cwjcsu.learning.jfx;

import javax.swing.SwingUtilities;

/**
 * 
 * @author atlas
 *
 */
public class SwingHtmlDemo {

	public static void main(String [] args){

	    SwingUtilities.invokeLater(new Runnable() {
	      public void run() {
	        ApplicationFrame mainFrame = new ApplicationFrame();
	        mainFrame.setVisible(true);
	      }
	    });

	  }
}
