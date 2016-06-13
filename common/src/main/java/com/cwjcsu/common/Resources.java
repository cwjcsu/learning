package com.cwjcsu.common;

import java.io.Closeable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Resources {
	private static final Logger logger = Logger.getLogger("COMMON");

	public static void close(Closeable resource) {
		try {
			if (resource != null)
				resource.close();
		} catch (IOException e) {
			logger.log(Level.WARNING, e.getMessage(), e);
		}
	}

	public static void close(ObjectOutput output) {
		try {
			if (output != null)
				output.close();
		} catch (IOException e) {
			logger.log(Level.WARNING, e.getMessage(), e);
		}
	}

	public static void close(ObjectInput input) {
		try {
			if (input != null)
				input.close();
		} catch (IOException e) {
			logger.log(Level.WARNING, e.getMessage(), e);
		}
	}

	public static void close(Connection connection) {
		try {
			if (connection != null)
				connection.close();
		} catch (SQLException e) {
			logger.log(Level.WARNING, e.getMessage(), e);
		}
	}

	public static void close(Statement statement) {
		try {
			if (statement != null)
				statement.close();
		} catch (SQLException e) {
			logger.log(Level.WARNING, e.getMessage(), e);
		}
	}

	public static void close(ResultSet resultSet) {
		try {
			if (resultSet != null)
				resultSet.close();
		} catch (SQLException e) {
			logger.log(Level.WARNING, e.getMessage(), e);
		}
	}

	public static void close(Process process) {
		if (process != null) {
			close(process.getErrorStream());
			close(process.getInputStream());
			close(process.getOutputStream());
			process.destroy();
		}
	}

	private Resources() {
		// Hide
	}
}
