package com.cwjcsu.learning.trial;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/*$Id: $
 * author                     date    comment
 * cwjcsu@gmail.com  2015年10月8日  Created
 */
/**
 * 
 * @author atlas
 *
 */
public class ByteArraySet {

	public static void main(String[] args) {
		Set<MyArray> ARRAY_SET = new HashSet<MyArray>();
		ARRAY_SET.add(new MyArray(new byte[] { (byte) 0xaa, (byte) 0xbb,
				(byte) 0xcc, (byte) 0xdd }));
		ARRAY_SET.add(new MyArray(new byte[] { (byte) 0x11, (byte) 0x22,
				(byte) 0x33, (byte) 0x44 }));
		byte[] check = { (byte) 0x11, (byte) 0x22, (byte) 0x33, (byte) 0x44 };
		byte[] check2 = { (byte) 0x12, (byte) 0x23, (byte) 0x34, (byte) 0x45 };
		System.out.println(ARRAY_SET.contains(new MyArray(check)));
		System.out.println(ARRAY_SET.contains(new MyArray(check2)));
	}

	static class MyArray {
		private byte[] array;

		public MyArray(byte[] array) {
			super();
			this.array = array;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + Arrays.hashCode(array);
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			MyArray other = (MyArray) obj;
			if (!Arrays.equals(array, other.array))
				return false;
			return true;
		}
	}
}
