/*$Id: $
 * author   			date   comment
 * cwjcsu@gmail.com  2013-1-10  Created
 */
package com.cwjcsu.common.exec;


import com.cwjcsu.common.StringUtils;

public class ExecuteResult {
	@Override
	public String toString() {
		return "ExecuteResult{" + "exitCode=" + exitCode + ",failType=" + failType + ", executeOut='" + executeOut
				+ '\'' + ", errorOut='" + errorOut + '\'' + '}';
	}

	private int exitCode;
	private String executeOut;

	private String errorOut;

	private FailType failType;

	public ExecuteResult(int exitCode, String executeOut, String errorOut) {
		this(exitCode, exitCode != 0 ? FailType.ErrorExit : null, executeOut, errorOut);
	}

	public ExecuteResult(int exitCode, FailType failType, String executeOut, String errorOut) {
		super();
		this.exitCode = exitCode;
		this.executeOut = executeOut;
		this.errorOut = errorOut;
		this.failType = failType;
	}

	public int getExitCode() {
		return exitCode;
	}

	public void setExitCode(int exitCode) {
		this.exitCode = exitCode;
	}

	public String getExecuteOut() {
		return executeOut;
	}

	public void setExecuteOut(String executeOut) {
		this.executeOut = executeOut;
	}

	public String getErrorOut() {
		return errorOut;
	}

	public void setErrorOut(String errorOut) {
		this.errorOut = errorOut;
	}

	public FailType getFailType() {
		return failType;
	}

	public enum FailType {
		ErrorExit, // 退出码非0
		IOError, // IO异常，命令不存在或者不可执行；
		TimedOut, // 执行命令超时；
		Interrupted, // 执行子进程被打断或者等待结果过程被打断；
		Error,// 其他未知异常
	}

	public String getErrorDescription() {
		if (failType != null) {
			switch (failType) {
			case IOError:
				return "命令不存在或者不可执行";
			case TimedOut:
				return "执行命令超时";
			case Interrupted:
				return "执行命令被中断";
			case Error:
				return "未知异常";
			case ErrorExit:
				if (StringUtils.notEmpty(errorOut)) {
					return errorOut;
				}
				return "命令异常退出";
			}
		}
		return null;
	}
}
