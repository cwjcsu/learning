package com.cwjcsu.common;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class DictImpl implements Dict {

	private Map map;

	private boolean ignoreCase;

	private static Map<String, SimpleDateFormat> SDFS = new ConcurrentHashMap<String, SimpleDateFormat>();
	
	public DictImpl(){
		this.map = new HashMap<String,Object>();
	}

	public DictImpl(Map map) {
		this(map, false);
	}

	public DictImpl(Map map, boolean ignoreCase) {
		super();
		this.ignoreCase = ignoreCase;
		if (ignoreCase) {
			this.map = new HashMap();
			for (Object key : map.keySet()) {
				this.map.put(key.toString().toLowerCase(), map.get(key));
			}
		} else {
			this.map = new HashMap(map);
		}

	}

	public <T> T get(String key, T defaultValue) {
		T t = (T) get(_(key));
		if (t == null)
			return defaultValue;
		return t;
	}

	private String _(String key) {
		if (key == null) {
			return null;
		}
		return ignoreCase ? key.toLowerCase() : key;
	}

	@Override
	public <T> T get(String key) {
		Object t = map.get(_(key));
		try {
			if (t instanceof String) {
				if (StringUtils.isEmpty((String) t))
					return null;
			}
			return (T) t;
		} catch (ClassCastException e) {
			throw new ClassCastException("Type not match of property " + key
					+ ":" + e.getMessage());
		}
	}

	@Override
	public <T> T require(String key) {
		T t = (T) get(_(key));
		if (t == null) {
			throwException(key);
		}
		return t;
	}

	@Override
	public Map getMap() {
		return map;
	}

	@Override
	public String getAsString(String key) {
		Object t = map.get(_(key));
		if (t instanceof String)
			return (String) t;
		if (t == null)
			return null;
		return String.valueOf(t);
	}

	@Override
	public Boolean getAsBoolean(String key) {
		Object t = map.get(_(key));
		if (t instanceof Boolean)
			return (Boolean) t;
		if (t instanceof String) {
			if (StringUtils.isEmpty((String) t))
				return null;
		}
		if (t == null)
			return null;
		return Boolean.valueOf(t.toString());
	}

	@Override
	public Integer getAsInteger(String key) {
		Object t = map.get(_(key));
		if (t instanceof Number)
			return ((Number) t).intValue();
		if (t instanceof String) {
			if (StringUtils.isEmpty((String) t))
				return null;
		}
		try {
			if (t != null)
				return Integer.valueOf(t.toString());
		} catch (NumberFormatException e) {
			throw throwException(key, t, "Integer");
		}
		return null;
	}

	private RuntimeException throwException(String key, Object value,
			String type) {
		throw new IllegalArgumentException(
				String.format(
						"The value \"%s\" of corresponding property \"%s\" is not an %s.",
						value, key, type));
	}

	private void throwException(String key) {
		throw new IllegalArgumentException(
				"got null value of required property " + key);
	}

	@Override
	public Long getAsLong(String key) {
		Object t = map.get(_(key));

		if (t instanceof Number)
			return ((Number) t).longValue();
		if (t instanceof String) {
			if (StringUtils.isEmpty((String) t))
				return null;
		}
		try {
			if (t != null)
				return Long.valueOf(t.toString());
		} catch (NullPointerException e) {
			throw throwException(key, t, "Long");
		}
		return null;
	}

	@Override
	public Double getAsDouble(String key) {
		Object t = map.get(_(key));
		if (t instanceof Number)
			return ((Number) t).doubleValue();
		if (t instanceof String) {
			if (StringUtils.isEmpty((String) t))
				return null;
		}
		try {
			if (t != null)
				return Double.valueOf(t.toString());
		} catch (NumberFormatException e) {
			throw throwException(key, t, "Double");
		}
		return null;
	}

	@Override
	public Date getAsDate(String key, String pattern) {
		String dateStr = getAsString(key);
		if (dateStr == null) {
			return null;
		}
		SimpleDateFormat smp = SDFS.get(pattern);
		if (smp == null) {
			smp = new SimpleDateFormat(pattern);
			SDFS.put(pattern, smp);
		}
		try {
			return smp.parse(dateStr);
		} catch (ParseException e) {
			throwException(key, dateStr, "date");
		}
		return null;
	}

	@Override
	public String requireString(String key) {
		String value = getAsString(key);
		if (value == null) {
			throwException(key);
		}
		return value;
	}

	@Override
	public int requireInteger(String key) {
		Integer value = getAsInteger(key);
		if (value == null) {
			throwException(key);
		}
		return value.intValue();
	}

	@Override
	public int requireInteger(String key, int defaultValue) {
		Integer value = getAsInteger(key);
		if (value == null) {
			return defaultValue;
		}
		return value.intValue();
	}

	@Override
	public long requireLong(String key) {
		Long value = getAsLong(key);
		if (value == null) {
			throwException(key);
		}
		return value.longValue();

	}

	@Override
	public double requireDouble(String key) {
		Double value = getAsDouble(key);
		if (value == null) {
			throwException(key);
		}
		return value.doubleValue();
	}

	@Override
	public Boolean requireBoolean(String key) {
		Boolean value = getAsBoolean(key);
		if (value == null)
			throwException(key);
		return value.booleanValue();
	}

	@Override
	public Date requireDate(String key, String pattern) {
		String dateStr = getAsString(key);
		if (dateStr == null) {
			throwException(key);
		}
		SimpleDateFormat smp = SDFS.get(pattern);
		if (smp == null) {
			smp = new SimpleDateFormat(pattern);
			SDFS.put(pattern, smp);
		}
		try {
			return smp.parse(dateStr);
		} catch (ParseException e) {
			throwException(key, dateStr, "date");
			return null;
		}
	}

	@Override
	public String toString() {
		return "Dict " + this.map;
	}

}
