package com.cwjcsu.common;

import java.lang.reflect.Field;

/**
 * 
 * @author atlas
 * @date 2014-3-13
 */
public class TypeUtil {

	/**
	 * 返回类clazz（或者父类）里面名称为field的Field对象，如果不存在或者没有权限访问，则返回null
	 * 
	 * @param clazz
	 * @param field
	 * @return
	 */
	public static Field getField(Class clazz, String field) {
		while (true) {
			if (clazz == null || clazz.equals(Object.class)) {
				break;
			}
			try {
				Field f = clazz.getDeclaredField(field);
				if (f != null) {
					return f;
				}
			} catch (SecurityException e) {
			} catch (NoSuchFieldException e) {
			}
			clazz = clazz.getSuperclass();
		}
		return null;
	}

	/**
	 * fieldChain的格式式:"prop1.prop2.prop3"
	 * 反射方式获取对象bean通过fieldChain指向的属性,如果不存在这样的属性或者无法访问属性则抛出NoSuchFieldException。
	 * 
	 * @param bean
	 * @param fieldChain
	 * @return
	 */
	public static Object getObject(Object bean, String fieldChain)
			throws NoSuchFieldException {
		if (bean == null || StringUtils.isEmpty(fieldChain)) {
			throw new IllegalArgumentException("bean:" + bean + ",fieldChain:"
					+ fieldChain);
		}
		String[] fields = fieldChain.split("\\.");
		Object obj = bean;
		for (String field : fields) {
			if (StringUtils.isEmpty(field)) {
				throw new IllegalArgumentException("bean:" + bean
						+ ",fieldChain:" + fieldChain);
			}
			if (obj == null) {
				break;
			}
			Field f = getField(bean.getClass(), field);
			if (f == null) {
				throw new NoSuchFieldException(field + " in chain "
						+ fieldChain + " of object " + bean);
			}
			try {
				f.setAccessible(true);
				obj = f.get(obj);
			} catch (Exception e) {
				return null;
			}
		}
		return obj;
	}

}
