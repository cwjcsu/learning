package com.cwjcsu.projecteuler.p1_50;

import com.cwjcsu.projecteuler.util.Util;

/*
 * Analysis
 There are only 9 x 9! (3,265,920) possible 0-9 ten-digit pandigital numbers. Simply generate the possibilities and check each three-digit section to be divisible by the appropriate prime number. But even on a fast computer this method broke the one minute limitation.

 Clearly something had to be done to reduce the number of permutations and upon closer inspection some rules were discovered. Keep in mind we are dealing with 3 digit numbers that must have unique digits in order to qualify as part of a 0-9 pandigital ten-digit number.

 Rule 0: Can��t start with {0}.

 Rule 1: Since d4 has to be divisible by 2 then d4 has to be from the set {0, 2, 4, 6, 8}.

 Rule 2: Since d4d5d6 has to be divisible by 5 then d6 has to be from the set {0, 5}.

 Rule 3: d6d7d8 has to be divisible by 11 and, according to Rule 2, has to start with a {0, 5}. Well, it can��t start with 0 as that would yield invalid numbers such as {011, 022, �� 099}, so it has to start with 5 and have only unique digits. The set is
 {506, 517, 528, 539, 561, 572, 583, 594}.

 New Rule 2: Amend Rule 2 to d6 = {5}.

 Rule 5: Since d7d8d9 must be divisible by 13, begin with {06, 17, 28, 39, 61, 72, 83, 94}, contain no 5s and no repeated digits our set becomes {286, 390, 728, 832}.

 Rule 6: Since d8d9d10 must be divisible by 17, begin with {28, 32, 86, 90}, contain no 5s and no repeated digits our set becomes {289, 867, 901}.

 Let��s review what we have and what combinations are possible for d6 through d10:
 d6 = {5}
 d7d8d9 = {286, 390, 728}
 d8d9d10 = {289, 867, 901}

 d6d7d8d9d10 = {52867, 53901, 57289}

 Rule 7: d5d6d7 must be divisible by 7 and end with {52, 53, 57} yields {357, 952}

 Rule 1: Amend Rule 1 to d4 = {0, 4, 6}.

 Our solution set ends with {��.357289 or ��.952867}

 You could finish the problem furthering this technique, but we reduced it enough to have the computer solve the rest.
 */
public class Problem43 {
	//16695334890
	public static void main(String[] args) {
		long sum = 0;
		String[] cases = perm("4310");
		long a = 0;
		for (String s : cases) {
			if (s.charAt(0) != '0' && check((a = Long.valueOf(s + "952867")))) {
				sum += a;
			}
		}
		cases = perm("6410");
		for (String s : cases) {
			if (s.charAt(0) != '0'
					&& check((a = Long.valueOf(s + "357289")))) {
				sum += a;
			}
		}
		System.out.println(sum);
	}

	static boolean check(long n) {
		String d = String.valueOf(n);
		return Util.isEven(Integer.parseInt(d.substring(1, 4)))
				&& Integer.parseInt(d.substring(2, 5)) % 3 == 0;
	}

	static String[] perm(String src) {
		out = new String[24];
		c = 0;
		printAllArray(src, "");
		return out;
	}

	static int c = 0;
	static String[] out;

	private static void printAllArray(String s, String n) {
		if (s.length() == 0) {
			out[c++] = n;
		} else {
			for (int i = 0; i < s.length(); ++i) {
				printAllArray(s.substring(1), n + s.charAt(0));
				s = s.substring(1) + s.charAt(0);
			}
		}
	}
}
