package com.cwjcsu.common;

import static java.lang.System.getProperty;

import java.lang.reflect.Array;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtils {

	public static final Charset DEFAULT_CHARSET = Charset.forName("UTF-8");

	public static final String LINE_SEPARATOR = getProperty("line.separator");

	public static char[] HEX_CHAR = { '0', '1', '2', '3', '4', '5', '6', '7',
			'8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };

	public static  char[] LOWERCASE_CHARS = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k',
			'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w',
			'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };

	public static char[] PASSWORD_CHARS = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',//
			'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',
			'0', '1', '2', '3', '4', '5', '6', '7', '8', '9',//
			'~', '!', '@', '$', '%', '&', '*', '(', ')', '_', '+', '-', ',','.','[', ']', '{', '}', '<', '>', '?'};
	/**
	 * 生成指定长度由小写字母和数字组成的随机字符串
	 *
	 * @param length
	 * @return
	 */
	public static String randomStr(int length) {
		char[] arr = new char[length];
		for (int i = 0; i < length; i++) {
			// 生成下标的随机数
			Random random = new Random();
			int index = random.nextInt(LOWERCASE_CHARS.length);
			char tempChar = LOWERCASE_CHARS[index];
			arr[i] = tempChar;
		}
		return new String(arr);
	}

	public static String randomPasswordStr(int length) {
		char[] arr = new char[length];
		for (int i = 0; i < length; i++) {
			// 生成下标的随机数
			Random random = new Random();
			int index = random.nextInt(PASSWORD_CHARS.length);
			char tempChar = PASSWORD_CHARS[index];
			arr[i] = tempChar;
		}
		return new String(arr);
	}

	public static String bytesToHex(byte[] b) {
		StringBuilder sb = new StringBuilder(b.length * 2);
		for (int i = 0; i < b.length; i++) {
			sb.append(HEX_CHAR[(b[i] & 0xf0) >>> 4]);
			sb.append(HEX_CHAR[b[i] & 0x0f]);
		}
		return sb.toString();
	}

	public static String toArrayString(Object array) {
		if (array == null) {
			return String.valueOf(array);
		}
		Class<?> clazz = array.getClass();
		if (clazz.isArray()) {
			int len = Array.getLength(array);
			if (len == 0) {
				return "[]";
			}
			if (clazz.getComponentType() == char.class) {
				return new String((char[]) array);
			}
			StringBuilder sb = new StringBuilder();
			sb.append('[');
			for (int i = 0; i < len; i++) {
				sb.append(Array.get(array, i));
				if (i < len - 1) {
					sb.append(",");
				}
			}
			sb.append(']');
			return sb.toString();
		}
		return String.valueOf(array);
	}

	public static byte[] hexToBytes(String hex) {
		byte[] bytes = new byte[hex.length() / 2];
		int k = 0;
		for (int i = 0; i < bytes.length; i++) {
			byte high = (byte) (Character.digit(hex.charAt(k), 16) & 0xff);
			byte low = (byte) (Character.digit(hex.charAt(k + 1), 16) & 0xff);
			bytes[i] = (byte) (high << 4 | low);
			k += 2;
		}
		return bytes;
	}

	public static boolean isEmpty(Object str) {
		if (str == null || "".equals(str.toString().trim()))
			return true;
		return false;
	}

	public static boolean isEmpty(String str) {
		if (str == null || "".equals(str.trim()))
			return true;
		return false;
	}

	public static boolean notEmpty(String str) {
		return !isEmpty(str);
	}

	/**
	 * 参数列表里有任何一个String满足 str==null || "".equals(str.trim())即返回true
	 *
	 * @param strs
	 * @return
	 */
	public static boolean isAnyEmpty(String... strs) {
		if (strs.length == 0) {
			return true;
		}
		for (int i = 0; i < strs.length; i++) {
			if (isEmpty(strs[i])) {
				return true;
			}
		}
		return false;
	}

	public static String joinPath(String path, String file) {
		boolean a = path.charAt(path.length() - 1) == '/';
		boolean b = file.charAt(0) == '/';
		if (a && b) {
			return path + file.substring(1);
		} else if (!a && !b) {
			return path + "/" + file;
		} else {
			return path + file;
		}
	}

	public static <T> String join(Collection<T> data, String connector) {
		if (connector == null) {
			throw new NullPointerException("null connector");
		}
		StringBuilder sb = new StringBuilder();
		Iterator<T> iter = data.iterator();
		int i = 0;
		while (iter.hasNext()) {
			T obj = iter.next();
			sb.append(String.valueOf(obj));
			if (i < data.size() - 1) {
				sb.append(connector);
			}
			i++;
		}
		return sb.toString();
	}

	public static <T> String join(T[] data, String connector) {
		if (connector == null) {
			throw new NullPointerException("null connector");
		}
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < data.length; i++) {
			sb.append(String.valueOf(data[i]));
			if (i < data.length - 1) {
				sb.append(connector);
			}
		}
		return sb.toString();
	}

	public static boolean equals(String str1, String str2) {
		return (str1 == str2) || (str1 != null && str1.equals(str2));
	}

	/**
	 * null is support
	 *
	 * @param thiz
	 * @param params
	 * @return
	 */
	public static boolean in(Object thiz, Object... params) {
		for (Object p : params) {
			if (thiz == null && p == null)
				return true;
			if (thiz == p) {
				return true;
			}
			if (thiz != null && thiz.equals(p))
				return true;
		}
		return false;
	}

	public static boolean in(int thiz, int... params) {
		for (int p : params) {
			if (thiz == p)
				return true;
		}
		return false;
	}

	public static boolean inIgnoreCase(String thiz, String... params) {
		for (String p : params) {
			if (thiz == null && p == null)
				return true;
			if (thiz != null && thiz.equalsIgnoreCase(p))
				return true;
		}
		return false;
	}

	public static String repeat(char ch, int count) {
		StringBuilder buffer = new StringBuilder();
		for (int i = 0; i < count; ++i)
			buffer.append(ch);
		return buffer.toString();
	}

	public static String toMessageString(Throwable e) {
		// fix a dead loop bug when cuase is myself.
		StringBuilder sb = new StringBuilder();
		sb.append(e.getMessage());
		Throwable prev = e;
		Throwable cause = e.getCause();
		while (prev != cause && cause != null) {
			sb.append(" :" + cause.getMessage());
			prev = cause;
			cause = cause.getCause();
		}
		return sb.toString();
	}

	public static String toString(Throwable e) {
		// fix a dead loop bug when cuase is myself.
		StringBuilder sb = new StringBuilder();
		sb.append(e);
		Throwable prev = e;
		Throwable cause = e.getCause();
		while (prev != cause && cause != null) {
			sb.append(" caused by " + cause);
			prev = cause;
			cause = cause.getCause();
		}
		return sb.toString();
	}

	public static String toCauseString(Throwable e) {
		Throwable prev = e;
		Throwable cause = e.getCause();
		while (prev != cause && cause != null) {
			prev = cause;
			cause = cause.getCause();
		}
		return prev.toString();
	}

	public static String toString(int[] datas, String separator) {
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		for (int i = 0; i < datas.length; i++) {
			sb.append(datas[i]);
			if (i < datas.length - 1) {
				sb.append(separator);
			}
		}
		sb.append("]");
		return sb.toString();
	}

	public static String toString(int[] datas) {
		return toString(datas, ",");
	}

	public static String toString(Collection<?> datas, String separator) {
		StringBuilder sb = new StringBuilder();
		int i = 0;
		for (Object o : datas) {
			sb.append(o);
			if (i++ < datas.size() - 1) {
				sb.append(separator);
			}
		}
		return sb.toString();
	}

	public static String trim(String src, String redundant) {
		while (src.startsWith(redundant)) {
			src = src.substring(redundant.length(), src.length());
		}
		while (src.endsWith(redundant)) {
			src = src.substring(0, src.length() - redundant.length());
		}
		return src;
	}

	public static String lineup(List<String> lines) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < lines.size(); i++) {
			String line = lines.get(i);
			sb.append(line);
			if (i < lines.size()) {
				sb.append("\n");
			}
		}
		return sb.toString();
	}

	public static String filterEmpty(String id) {
		if (isEmpty(id))
			return null;
		return id;
	}

	public static Object combine(String[] strings, String pot) {
		if (strings == null) {
			return null;
		}
		if (strings.length == 1) {
			return strings[0];
		}
		StringBuilder sb = new StringBuilder();
		for (String str : strings) {
			sb.append(str);
			sb.append(pot);
		}
		return sb.subSequence(0, sb.length() - pot.length());
	}

	public static List<String> filterEmpty(List<String> list) {
		Util.removeOnCondition(list, new Condition<String>() {
			public boolean when(String t) {
				return isEmpty(t);
			}
		});
		return list;
	}

	/**
	 * 取交集
	 *
	 * @param list1
	 * @param list2
	 * @return
	 */
	public static List<String> intersect(Collection<String> list1,
										 Collection<String> list2) {
		List<String> list0 = new ArrayList<String>(Math.min(list1.size(),
				list2.size()));
		for (String item : list1) {
			if (list2.contains(item)) {
				list0.add(item);
			}
		}
		return list0;
	}

	/**
	 * 获取交集的size
	 *
	 * @param list1
	 * @param list2
	 * @return
	 */
	public static int intersectSize(Collection<String> list1,
									Collection<String> list2) {
		int count = 0;
		for (String item : list1) {
			if (list2.contains(item)) {
				count++;
			}
		}
		return count;
	}

	/**
	 * 返回在set1中，但不在set2中的字符串集合
	 *
	 * @param set1
	 * @param set2
	 * @return
	 */
	public static Set<String> subtract(Collection<String> set1,
									   Collection<String> set2) {
		Set<String> remain = new HashSet<String>();
		for (String str : set1) {
			if (!set2.contains(str)) {
				remain.add(str);
			}
		}
		return remain;
	}

	public static List<String> sort(Collection<String> set) {
		List<String> sorted = new ArrayList<String>(set);
		Collections.sort(sorted);
		return sorted;
	}

//	public static String htmlifyXml(String convertIn) {
//		if (convertIn == null) {
//			return null;
//		}
//		return org.apache.commons.lang3.StringEscapeUtils
//				.escapeHtml4(convertIn);
//	}
//
//	public static String htmlifyHtml(String convertIn) {
//		if (convertIn == null) {
//			return null;
//		}
//		return org.apache.commons.lang3.StringEscapeUtils
//				.escapeHtml4(convertIn);
//	}

	public static List<String> siftByPattern(String str, String pattern) {
		Pattern p = Pattern.compile(pattern);
		Matcher matcher = p.matcher(str);
		List<String> sifts = new ArrayList<String>();
		while (matcher.find()) {
			sifts.add(matcher.group());
		}
		return sifts;
	}

	public static void main(String[] args){
		System.out.println(randomPasswordStr(8));
	}
}
