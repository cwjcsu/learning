package com.cwjcsu.common;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;

/**
 * 
 * @author atlas
 * @date 2012-11-21
 */
public class Util {
	/**
	 * 
	 * @param num
	 * @return 是否是偶数
	 */
	public static boolean isEven(int num) {
		return (num & 1) == 0;
	}

	public static void sleep(long timeMs) {
		try {
			Thread.sleep(timeMs);
		} catch (InterruptedException e) {
			// ignore
		}
	}

	public static void sleepUntil(long timestampWakeUp) {
		long sleep = timestampWakeUp - System.currentTimeMillis();
		if (sleep > 0) {
			sleep(sleep);
		}
	}

	public static int sum(int[] d) {
		int sum = 0;
		for (int d0 : d) {
			sum += d0;
		}
		return sum;
	}

	/**
	 * 先把ids排序，从小到大； 再把比currentId大的按从小到达放入一个list； 最后比currentId小的从小到大追加到list；
	 * 如果ids不包含currentId，那么返回empty<br/>
	 * 返回的List里面不能包含currentId
	 * 
	 * @param ids
	 * @param currentId
	 * @return
	 */
	public static List<String> sortNextId(List<String> ids, String currentId) {
		Collections.sort(ids);
		List<String> nextIds = new ArrayList<String>();
		// currentId's index in ids
		int got = -1;
		for (int i = 0; i < ids.size(); i++) {
			if (currentId.equals(ids.get(i))) {
				got = i;
				break;
			}
		}
		if (got == -1) {
			return nextIds;
		}
		for (int i = got + 1; i < ids.size(); i++) {
			nextIds.add(ids.get(i));
		}
		for (int i = 0; i < got; i++) {
			nextIds.add(ids.get(i));
		}
		return nextIds;
	}

	public static int countTrue(boolean... trues) {
		int i = 0;
		for (boolean t : trues) {
			if (t)
				i++;
		}
		return i;
	}

	public static boolean in(Object o, Object... objs) {
		for (Object obj : objs) {
			if (o == null) {
				if (obj == null)
					return true;
				else
					continue;
			}
			if (o == obj) {
				return true;
			}
			if (o.equals(obj))
				return true;
		}
		return false;
	}

	private static Random random = new Random();

	public static void setRandomSeed(long seed) {
		random = new Random(seed);
	}

	public static void fillRandom(byte[] req) {
		random.nextBytes(req);
	}

	public static boolean isEmpty(Collection<?> set) {
		return set == null || set.size() == 0;
	}

	// private static Set<Class<?>> primitiveTypes = new HashSet<Class<?>>();
	private static DuplexMap<Class<?>, Class<?>> primitiveTypes = new DuplexMap<Class<?>, Class<?>>();
	static {
		primitiveTypes.put(Boolean.class, boolean.class);
		primitiveTypes.put(Byte.class, byte.class);
		primitiveTypes.put(Character.class, char.class);
		primitiveTypes.put(Short.class, short.class);
		primitiveTypes.put(Integer.class, int.class);
		primitiveTypes.put(Long.class, long.class);
		primitiveTypes.put(Float.class, float.class);
	}

	public static boolean isPrimitiveOrWrapper(Object obj) {
		if (obj == null) {
			return false;
		}
		return primitiveTypes.contains(obj.getClass())
				|| primitiveTypes.containsValue(obj.getClass());
	}

	public static boolean isPrimitiveClass(Class<?> clazz) {
		if (clazz == null) {
			return false;
		}
		return primitiveTypes.containsValue(clazz);
	}

	public static boolean isPrimitiveOrWrapperClass(Class<?> clazz) {
		if (clazz == null) {
			return false;
		}
		return primitiveTypes.contains(clazz)
				|| primitiveTypes.containsValue(clazz);
	}

	public static boolean isWrapperClass(Class<?> clazz) {
		if (clazz == null) {
			return false;
		}
		return primitiveTypes.contains(clazz);
	}

	public static boolean isAssignableFrom(Class c1, Class c2) {
		if (c1.isAssignableFrom(c2))// no auto boxing
			return true;
		Class<?> c11 = primitiveTypes.getByKey(c1);
		if (c11 == null) {
			c11 = primitiveTypes.getByValue(c1);
		}
		if (c11 != null && c11 == c2) {
			return true;
		}
		return false;
	}

	/**
	 * equals，==，同为null时都返回true
	 * 
	 * @param o1
	 * @param o2
	 * @return
	 */
	public static boolean eq(Object o1, Object o2) {
		if (o1 == null && o2 == null) {
			return true;
		}
		if (o1 != null && o2 != null && (o1 == o2 || o1.equals(o2))) {
			return true;
		}
		return false;
	}

	public static void validate(Object[] args, Class<?>[] expected) {
		if (args == null) {
			args = new Object[0];
		}
		if (expected == null) {
			expected = new Class<?>[0];
		}

		if (args.length > expected.length) {
			throw new IllegalArgumentException(_.$("TooManyParameters({}>{})",
					args.length, expected.length));
		}
		for (int i = 0; i < expected.length; ++i) {
			Class<?> argType = expected[i];
			if (args.length <= i)
				throw new IllegalArgumentException(_.$(
						"Argument of type {} required in position {}", argType,
						i));
			Object arg = args[i];
			if (arg != null && !isAssignableFrom(argType, arg.getClass())) {
				throw new IllegalArgumentException(_.$(
						"Wrong argument type {} in position {},should be {} ",
						arg.getClass(), i, argType));
			}
		}
	}

	/** Returns a random value in the range [0 , range) */
	public static int random(int range) {
		return random.nextInt(range);
	}

	/** Returns a random value in the range [from ,from + range) */
	public static int random(int from, int range) {
		return from + random.nextInt(range);
	}

	/**
	 * 求交集运算
	 * 
	 * @param s1
	 * @param s2
	 * @return
	 */
	public static Set<String> cap(Set<String> s1, Set<String> s2) {
		Set<String> capSet = new HashSet<String>();
		for (String v : s1) {
			if (s2.contains(v)) {
				capSet.add(v);
			}
		}
		return capSet;
	}

	/**
	 * 
	 * @param items
	 * @param condition
	 * @return
	 */
	public static <T> T removeOnCondition(Collection<T> items,
			Condition<T> condition) {
		Iterator<T> iter = items.iterator();
		while (iter.hasNext()) {
			T t = iter.next();
			if (condition.when(t)) {
				iter.remove();
				return t;
			}
		}
		return null;
	}

	/**
	 * 
	 * @param items
	 * @param condition
	 * @return the first item which meet the condition or null if none of them
	 *         meet the condition
	 */
	public static <T> T findOnCondition(Collection<T> items,
			Condition<T> condition) {
		for (T t : items) {
			if (condition.when(t)) {
				return t;
			}
		}
		return null;
	}

	public static void logOut(final String messagePattern,
			final Object... argArray) {
		System.out
				.println(_.$("{} [{}] {}", DateUtil.formatDate(new Date(),
						"yyyy-MM-dd HH:mm:ss.SSS"), Thread.currentThread()
						.getName(), _.$(messagePattern, argArray)));
	}

	public static final SecureRandom RANDOM = new SecureRandom();
}