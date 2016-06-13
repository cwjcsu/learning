/*$Id: $
 * author   			date   comment
 * cwjcsu@gmail.com  2013-7-19  Created
*/ 
package com.cwjcsu.common.exec;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.concurrent.Callable;

public class StreamGobbler implements Callable<String> {
	private static Logger logger = LoggerFactory.getLogger(StreamGobbler.class);
	private InputStream inputStream;
	private String streamType;
	private StringBuilder buf;

	/**
	 * Constructor.
	 * 
	 * @param inputStream
	 *            the InputStream to be consumed
	 * @param streamType
	 *            the stream type (should be OUTPUT or ERROR)
	 */
	public StreamGobbler(final InputStream inputStream, final String streamType) {
		this.inputStream = inputStream;
		this.streamType = streamType;
		this.buf = new StringBuilder();
	}

	/**
	 * Consumes the output from the input stream and displays the lines
	 * consumed if configured to do so.
	 */
	public String call() throws Exception {
		try {
			InputStreamReader inputStreamReader = new InputStreamReader(
					inputStream);
			BufferedReader bufferedReader = new BufferedReader(
					inputStreamReader);
			String line = null;
			while ((line = bufferedReader.readLine()) != null) {
				this.buf.append(line + "\n");
			}
		} catch (IOException ex) {
			logger.trace("Failed to successfully consume and display the input stream of type "
					+ streamType + ".", ex);
		}
		return this.buf.toString();
	}
}