package com.cwjcsu.projecteuler.p1_50;

public class Problem17 {
	public static String numberTransfer(int number) {
		String s = null;

		if (number <= 19 && number >= 1) {
			switch (number) {
			case 1:
				s = "one";
				break;
			case 2:
				s = "two";
				break;
			case 3:
				s = "three";
				break;
			case 4:
				s = "four";
				break;
			case 5:
				s = "five";
				break;
			case 6:
				s = "six";
				break;
			case 7:
				s = "seven";
				break;
			case 8:
				s = "eight";
				break;
			case 9:
				s = "nine";
				break;
			case 10:
				s = "ten";
				break;
			case 11:
				s = "eleven";
				break;
			case 12:
				s = "twelve";
				break;
			case 13:
				s = "thirteen";
				break;
			case 14:
				s = "fourteen";
				break;
			case 15:
				s = "fifteen";
				break;
			case 16:
				s = "sixteen";
				break;
			case 17:
				s = "seventeen";
				break;
			case 18:
				s = "eighteen";
				break;
			case 19:
				s = "nineteen";
				break;
			}
		} else if (number == 20) {
			s = "twenty";
		} else if (number >= 20 && number <= 29) {
			number -= number / 10 * 10;
			s = "twenty-" + numberTransfer(number);
		} else if (number == 30) {
			s = "thirty";
		} else if (number >= 31 && number <= 39) {
			number -= number / 10 * 10;
			s = "thirty-" + numberTransfer(number);
		} else if (number == 40) {
			s = "forty";
		} else if (number >= 41 && number <= 49) {
			number -= number / 10 * 10;
			s = "forty-" + numberTransfer(number);
		} else if (number == 50) {
			s = "fifty";
		} else if (number >= 51 && number <= 59) {
			number -= number / 10 * 10;
			s = "fifty-" + numberTransfer(number);
		} else if (number == 60) {
			s = "sixty";
		} else if (number >= 61 && number <= 69) {
			number -= number / 10 * 10;
			s = "sixty-" + numberTransfer(number);
		} else if (number == 70) {
			s = "seventy";
		} else if (number >= 71 && number <= 79) {
			number -= number / 10 * 10;
			s = "seventy-" + numberTransfer(number);
		} else if (number == 80) {
			s = "eighty";
		} else if (number >= 81 && number <= 89) {
			number -= number / 10 * 10;
			s = "eighty-" + numberTransfer(number);
		} else if (number == 90) {
			s = "ninety";
		} else if (number >= 91 && number <= 99) {
			number -= number / 10 * 10;
			s = "ninety-" + numberTransfer(number);
		} else if (number == 100 || number == 200 || number == 300
				|| number == 400 || number == 500 || number == 600
				|| number == 700 || number == 800 || number == 900) {
			number /= 100;
			s = numberTransfer(number) + " hundred";
		} else if (number >= 101 && number <= 999) {
			int h = number / 100;
			number = number - h * 100;
			s = numberTransfer(h) + " hundred and " + numberTransfer(number);
		} else if (number == 1000) {
			s = "one thousand";
		} else if (number >= 1000 && number <= 9999) {
			int t = number / 1000;
			number = number - t * 1000;
			s = numberTransfer(t) + " thousand " + numberTransfer(number);
		} else {
			s = "ERROR";
		}

		return s;
	}

	public static void main(String[] args) {
		int result = 0;
		String s;

		for (int i = 1; i <= 1000; i++) {
			s = numberTransfer(i);

			for (int j = 0; j < s.length(); j++) {
				if (s.charAt(j) != ' ' && s.charAt(j) != '-') {
					result++;
				}
			}
		}

		System.out.println(result);
	}
}
