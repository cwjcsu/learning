package com.cwjcsu.thirdparty.javaeye;

import com.cwjcsu.common.Base64;

import java.io.UnsupportedEncodingException;


/**
 * 
 * @author atlas
 * @date 2012-9-27
 */
public class Main {

	/**
	 * @param args
	 * @throws UnsupportedEncodingException 
	 */
	public static void main(String[] args) throws UnsupportedEncodingException {
		//_javaeye3_session_
		byte[] data= Base64.decode("BAh7CDoPc2Vzc2lvbl9pZCIlNzM0MTM5MjQ3NmFmZDc1N2U1NWY2MTBhNzVlMDQ3ODQ6EF9jc3JmX3Rva2VuIjFkcGRiMmJXNEpRRFNrNW9ZdW1JS3FjVTFGNnR0OU04NmgwRjByWU5ORGc0PToMdXNlcl9pZGkDqKcB");
		// session_id=xxxx,csrf_token=xxx,user_idixxx
		System.out.println(new String(data));
		
		//
		data  = Base64.decode("MTA4NDU2X2I0MWZkOWE2NGU5MWY5M2QzMmVmNThlNzBlYzY4M2Vm");
		System.out.println(new String(data));
		
		//login_token
		data=Base64.decode("dpdb2bW4JQDSk5oYumIKqcU1F6tt9M86h0F0rYNNDg4=");
		System.out.println(new String(data,"GBK"));
		
	}
 
}
