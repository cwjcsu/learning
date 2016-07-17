/*$Id: $
 * author   date   comment
 * cwjcsu@gmail.com  2015年10月26日  Created
 */
package com.cwjcsu.thirdparty.leetcode;

import java.math.BigInteger;
import java.util.*;

public class Batch {

    public static void main(String[] args) {
        // System.out.println(hammingWeight(2147483648L));
        // System.out.println(isAnagram("anagram", "nagaram"));
        // System.out.println(majorityElement(new int[] { 6, 5, 5 }));
        // System.out.println(isHappy(7));
        System.out.println(isPowerOfTwo(0));
    }

    public static boolean isAnagram(String s, String t) {
        Map<Character, Integer> set = new HashMap<Character, Integer>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            Integer count = set.get(c);
            if (count == null) {
                count = 1;
            } else {
                count++;
            }
            set.put(c, count);
        }
        for (int i = 0; i < t.length(); i++) {
            char c = t.charAt(i);
            Integer count = set.get(c);
            if (count == null) {
                return false;
            }
            count--;
            if (count == 0) {
                set.remove(c);
            } else {
                set.put(c, count);
            }
        }
        return s.length() == t.length();
    }

    /**
     * 169
     *
     * @param nums
     * @return
     */
    public static int majorityElement(int[] nums) {
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int i = 0; i < nums.length; i++) {
            Integer count = map.get(nums[i]);
            if (count == null) {
                count = 1;
            } else {
                count++;
            }
            if (count >= (nums.length + 1) / 2) {
                return nums[i];
            }
            map.put(nums[i], count);
        }
        return 0;
    }

    /**
     * 206
     *
     * @param head
     * @return
     */
    public static ListNode reverseList(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode next = head;
        ListNode prev = head.next;
        head.next = null;
        while (prev != null) {
            ListNode tmp = prev.next;
            prev.next = next;
            next = prev;
            prev = tmp;
        }
        return next;
    }

    /**
     * 83
     *
     * @param head
     * @return
     */
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode head0 = head;
        ListNode prev = head;
        ListNode next = head.next;
        while (next != null) {
            if (prev.val == next.val) {
                ListNode tmp = next.next;
                next.next = null;
                prev.next = tmp;
                next = tmp;
            } else {
                prev = next;
                next = next.next;
            }
        }
        return head0;
    }


    static Map<Integer, Integer> stairCache = new HashMap<Integer, Integer>();

    static {
        stairCache.put(1, 1);
        stairCache.put(2, 2);
    }

    /**
     * 70
     * OK
     *
     * @param n
     * @return
     */
    public int climbStairs(int n) {
        if (n == 1) {
            return 1;
        }
        if (n == 2) {
            return 2;
        }
        Integer step = stairCache.get(n);
        if (step != null) {
            return step;
        }
        int s = climbStairs(n - 1) + climbStairs(n - 2);
        stairCache.put(n, s);
        return s;
    }

    /**
     * 202
     *
     * @param n
     * @return
     */
    public static boolean isHappy(int n) {
        java.util.Set<Integer> squares = new java.util.HashSet<Integer>();
        return isHappy(n, squares);
    }

    public static boolean isHappy(int n, java.util.Set<Integer> squares) {
        if (n == 1) {
            return true;
        }
        int s = 0;
        while (n > 0) {
            int d = n % 10;
            s += d * d;
            n /= 10;
        }
        if (squares.contains(s)) {
            return false;
        }
        squares.add(s);
        return isHappy(s, squares);
    }

    public static boolean isPowerOfTwo(int n) {
        if (n < 0) {
            return false;
        }
        while (n >= 0) {
            if (n == 1) {
                return true;
            } else if (n == 0) {
                return false;
            }
            if (n % 2 != 0) {
                return false;
            }
            n /= 2;
        }
        return true;
    }

    public int removeElement(int[] nums, int val) {
        int j = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != val) {
                nums[j++] = nums[i];
            }
        }
        return j;
    }

    /**
     * 107
     *
     * @param root
     * @return
     */
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> nodes = new ArrayList<List<Integer>>();
        levelOrderBottom(root, 0, nodes);
        Collections.reverse(nodes);
        return nodes;
    }

    private void levelOrderBottom(TreeNode node, int level,
                                  List<List<Integer>> nodes) {
        if (node == null) {
            return;
        }
        List<Integer> list = (level < nodes.size()) ? nodes
                .get(level) : null;
        if (list == null) {
            list = new ArrayList<Integer>();
            nodes.add(list);
        }
        list.add(node.val);
        if (node.left != null) {
            levelOrderBottom(node.left, level + 1, nodes);
        }
        if (node.right != null) {
            levelOrderBottom(node.right, level + 1, nodes);
        }
    }

    /**
     * 26,Remove Duplicates from Sorted Array
     * <p>
     * ,too much time,O(n)
     *
     * @param nums
     * @return
     */
    public int removeDuplicates(int[] nums) {
        Integer curr = null;
        int dups = 0;
        for (int i = 0; i < nums.length - dups - 1; i++) {
            if (curr == null) {
                curr = nums[i];
                continue;
            }
            if (curr == nums[i]) {
                dups++;
                for (int j = i; j < nums.length - dups; j++) {
                    nums[j] = nums[j + 1];
                }
                i--;
            }
            curr = nums[i + 1];
        }
        return nums.length - dups;
    }

    /**
     * time:O(n) space:O(n)
     *
     * @param nums
     * @return
     */
    public int removeDuplicates2(int[] nums) {
        Integer pre = null;
        int[] left = new int[nums.length];
        int j = 0;
        for (int i = 0; i < nums.length; i++) {
            if (pre == null) {
                pre = nums[i];
                left[j++] = pre;
                continue;
            }
            if (pre != nums[i]) {
                pre = nums[i];
                left[j++] = pre;
            }
        }
        for (int i = 0; i < j; i++) {
            nums[i] = left[i];
        }
        return j;
    }

    /**
     * best: TODO time:O(n), mem:O(1)
     *
     * @param nums
     * @return
     */
    public int removeDuplicates3(int[] nums) {
        Integer pre = null;
        int j = 0;
        for (int i = 0; i < nums.length; i++) {
            if (pre == null) {
                pre = nums[i];
                nums[j++] = pre;
                continue;
            }
            if (pre != nums[i]) {
                pre = nums[i];
                nums[j++] = pre;
            }
        }
        return j;
    }

    /**
     * 66
     *
     * @param digits
     * @return
     */
    public int[] plusOne(int[] digits) {
        int add = 1;
        for (int i = digits.length - 1; i >= 0; i--) {
            int d = digits[i] + add;
            if (d >= 10) {
                add = 1;
            } else {
                add = 0;
            }
            digits[i] = d % 10;
        }
        if (add == 1) {
            int[] newDigits = new int[digits.length + 1];
            newDigits[0] = 1;
            for (int i = 0; i < digits.length; i++) {
                newDigits[i + 1] = digits[i];
            }
            return newDigits;
        }
        return digits;
    }

    /**
     * 118
     *
     * @param numRows
     * @return
     */
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> datas = new ArrayList<List<Integer>>();
        List<Integer> prev = null;
        for (int i = 1; i <= numRows; i++) {
            List<Integer> data = new ArrayList<Integer>(i);
            datas.add(data);
            if (prev == null) {
                data.add(1);
                prev = data;
                continue;
            }
            for (int j = 1; j <= i; j++) {
                int i1 = j - 1 - 1;
                int i2 = j - 1;
                int d1 = 0, d2 = 0;
                if (i1 >= 0 && i1 < prev.size()) {
                    d1 = prev.get(i1);
                }
                if (i2 >= 0 && i2 < prev.size()) {
                    d2 = prev.get(i2);
                }
                data.add((d1 + d2));
            }
            prev = data;
        }
        return datas;
    }

    /**
     * 112
     *
     * @param root
     * @param sum
     * @return
     */
    public boolean hasPathSum(TreeNode root, int sum) {
        if (root == null) {
            return false;
        }
        boolean ok = false;
        if (root.right == null && root.left == null) {
            return sum == root.val;
        }
        if (root.left != null) {
            ok = hasPathSum(root.left, sum - root.val);
        }
        if (ok) {
            return ok;
        }
        if (root.right != null && !ok) {
            return hasPathSum(root.right, sum - root.val);
        }
        return false;
    }

    public boolean hasPathSum0(TreeNode root, int sum) {
        if (root == null) {
            if (sum == 0)
                return true;
            else
                return false;
        }
        if (root.right == null && root.left == null) {
            return sum == root.val;
        }
        boolean ok = false;
        if (root.left != null) {
            ok = hasPathSum(root.left, sum - root.val);
        }
        if (ok) {
            return ok;
        }
        if (root.right != null && !ok) {
            return hasPathSum(root.right, sum - root.val);
        }
        return false;
    }

    /**
     * 172
     *
     * @param n
     * @return
     */
    public int trailingZeroes(int n) {
        int a = 1808548329;
        // Initialize result
        int count = 0;
        // Keep dividing n by powers of 5 and update count
        for (int i = 5; n / i >= 1; i *= 5)
            count += n / i;
        return count;
    }

    /**
     * 119
     *
     * @param rowIndex
     * @return
     */
    public List<Integer> getRow(int rowIndex) {
        List<Integer> row = new ArrayList<Integer>(rowIndex + 1);
        for (int i = 0; i <= rowIndex; i++) {
            row.add(pascal(rowIndex, i));
        }
        return row;
    }

    Map<String, Integer> pascalCache = new HashMap<String, Integer>();

    /**
     * 求组合数kCn，缓存+递归法 （杨辉三角的第n行第k个数，n，k 从0开始）
     *
     * @return
     */
    public int pascal(int n, int k) {
        if (k == 0 || k == n) {
            return 1;
        }
        String key = n + "-" + k;
        Integer v = pascalCache.get(key);
        if (v != null) {
            return v;
        }
        v = pascal(n - 1, k - 1) + pascal(n - 1, k);
        pascalCache.put(key, v);
        return v;
    }

    /**
     * 求组合数kCn，采用公式： nCr=!n/(!r * !(n-r))
     * <p>
     * TODO
     * <p>
     * （杨辉三角的第n行第k个数，n，k 从0开始）
     *
     * @param n
     * @param k
     * @return
     */
    public int pascal2(int n, int k) {
        k = Math.min(k, n - k);

        return -1;
    }

    /**
     * 102
     *
     * @param root
     * @return
     */
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> nodes = new ArrayList<List<Integer>>();
        levelOrder(root, 0, nodes);
        return nodes;
    }

    private void levelOrder(TreeNode node, int level,
                            List<List<Integer>> nodes) {
        if (node == null) {
            return;
        }
        List<Integer> list = (level < nodes.size()) ? nodes
                .get(level) : null;
        if (list == null) {
            list = new ArrayList<Integer>();
            nodes.add(list);
        }
        list.add(node.val);
        if (node.left != null) {
            levelOrderBottom(node.left, level + 1, nodes);
        }
        if (node.right != null) {
            levelOrderBottom(node.right, level + 1, nodes);
        }
    }

    /**
     * 160
     *
     * @param headA
     * @param headB
     * @return
     */
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode nextA = headA;
        ListNode nextB = headB;
        while (true) {
            if (nextA.val == nextB.val) {
                return nextA;
            }
        }
    }

    /**
     * 88
     *
     * @param nums1
     * @param m
     * @param nums2
     * @param n
     */
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int[] num = new int[m + n];
        int k = 0;
        int i = 0, j = 0;
        while (true) {
            if (i >= m && j >= n) {
                break;
            }
            int d1 = i < m ? nums1[i] : Integer.MAX_VALUE;
            int d2 = j < n ? nums2[j] : Integer.MAX_VALUE;
            if (d1 < d2) {
                num[k++] = d1;
                i++;
            } else if (d1 > d2) {
                num[k++] = d2;
                j++;
            } else {
                if (i < m) {
                    num[k++] = d1;
                    i++;
                }
                if (j < n) {
                    num[k++] = d2;
                    j++;
                }
            }
        }
        for (i = 0; i < m + n; i++) {
            nums1[i] = num[i];
        }
    }

    /**
     * 190， 按二进制reverse
     *
     * @param n
     * @return
     */
    public int reverseBits0(int n) {
        long r = 0;
        while (n > 0) {
            r = r * 2 + n % 2;
            n /= 2;
        }
        return (int) r;
    }

    /**
     * 00000000000000000000000000000001
     *
     * @param n
     * @return
     */
    public int reverseBits2(long n) {
        String s = Long.toBinaryString(n);
        int pad = 32 - s.length();
        if (pad > 0) {
            for (int i = 0; i < pad; i++) {
                s = "0" + s;
            }
        }
        char[] chars = s.toCharArray();
        for (int i = 0; i <= chars.length - i - 1; i++) {
            char a = chars[i];
            chars[i] = chars[chars.length - i - 1];
            chars[chars.length - i] = a;
        }
        long t = Long.valueOf(new String(chars), 2);
        return (int) t;
    }

    /**
     * 19
     *
     * @param head
     * @param n
     * @return
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {

        // ListNode prev = new ListNode(0);
        // prev.next = head;
        ListNode target = head;
        ListNode next = head;
        for (int i = 0; i <= n; i++) {
            if (next != null) {
                next = next.next;
            } else {
                return head.next;
            }
        }
        while (next != null) {
            target = target.next;
            // prev = prev.next;
            next = next.next;
        }
        target.next = target.next != null ? target.next.next : null;
        return head;
    }

    /**
     * 58
     *
     * @param s
     * @return
     */
    public int lengthOfLastWord(String s) {
        int len = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            if (len == 0 && s.charAt(i) == ' ') {
                continue;
            }
            if (s.charAt(i) == ' ') {
                break;
            }
            len++;
        }
        return len;
    }

    public long reverse(long n) {
        if (n < 0) {
            return -reverse(-n);
        }
        long r = 0;
        while (n > 0) {
            r = r * 10 + n % 10;
            n /= 10;
        }
        return r;
    }

    /**
     * 205
     *
     * @param s
     * @param t
     * @return
     */
    public boolean isIsomorphic(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        Map<Character, Character> map1 = new HashMap<Character, Character>();
        Map<Character, Character> map2 = new HashMap<Character, Character>();
        for (int i = 0; i < s.length(); i++) {
            char c1 = s.charAt(i);
            char c2 = t.charAt(i);

            Character c11 = map1.get(c1);
            if (c11 != null) {
                if (c11 != c2)
                    return false;
            } else {
                map1.put(c1, c2);
            }

            Character c22 = map2.get(c2);
            if (c22 != null) {
                if (c22 != c1) {
                    return false;
                }
            } else {
                map2.put(c2, c1);
            }
        }
        return true;
    }

    /**
     * 14
     *
     * @param strs
     * @return
     */
    public String longestCommonPrefix(String[] strs) {
        StringBuilder sb = new StringBuilder();
        if (strs.length == 0) {
            return "";
        }
        for (int i = 0; ; i++) {
            Character c = null;
            for (int j = 0; j < strs.length; j++) {
                if (i >= strs[j].length()) {
                    return sb.toString();
                }
                if (c == null) {
                    c = strs[j].charAt(i);
                } else if (c != strs[j].charAt(i)) {
                    return sb.toString();
                }
            }
            sb.append(c);
        }
    }

    /**
     * 67
     *
     * @param a
     * @param b
     * @return
     */
    public String addBinary(String a, String b) {
        StringBuilder sb = new StringBuilder();
        if (a.length() < b.length()) {
            String t = a;
            a = b;
            b = t;
        }
        int j = b.length() - 1;
        int add = 0;
        for (int i = a.length() - 1; i >= 0; i--, j--) {
            char ca = a.charAt(i);
            char cb = j >= 0 ? b.charAt(j) : '0';
            int s = (ca - '0') + (cb - '0') + add;
            if (s >= 2) {
                add = 1;
            } else {
                add = 0;
            }
            char r = (char) ('0' + s % 2);
            sb.append(r);
        }
        if (add > 0) {
            sb.append('1');
        }
        return sb.reverse().toString();
    }

    /**
     * 234
     * <p>
     * <p>
     * 1,using stack: time O(n),space O(n)
     * <p>
     * 2, （1）用快慢指针找中点：快指针移动两步，慢指针移动一步，快指针到尾的时候，慢指针恰好是中点。
     * （2）然后将后半部分链表反转一下，就可以比较了，使用的space是O(1)，但是破坏了链表的结构。
     *
     * @param head
     * @return
     */
    public boolean isPalindrome(ListNode head) {
        Stack<Integer> stack = new Stack<Integer>();
        ListNode next = head;
        while (next != null) {
            stack.push(next.val);
            next = next.next;
        }
        next = head;
        while (next != null) {
            Integer i = stack.pop();
            if (i != next.val) {
                return false;
            }
            next = next.next;
        }
        return true;
    }

    /**
     * 257
     *
     * @param root
     * @return
     */
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> paths = new ArrayList<String>();
        binaryTreePaths(root, null, paths);
        return paths;
    }

    public void binaryTreePaths(TreeNode root, String path, List<String> paths) {
        if (root == null) {
            return;
        }
        if (path == null || path.length() == 0) {
            path = "" + root.val;
        } else {
            path += "->" + root.val;
        }
        if (root.left == null && root.right == null) {
            paths.add(path);
            return;
        }
        if (root.left != null) {
            binaryTreePaths(root.left, path, paths);
        }
        if (root.right != null) {
            binaryTreePaths(root.right, path, paths);
        }
    }

    /**
     * 125
     *
     * @param s
     * @return
     */
    public boolean isPalindrome(String s) {
        int i = 0;
        int j = s.length() - 1;
        while (i <= j) {
            char a = s.charAt(i);
            char b = s.charAt(j);
            if (!isAlphanumeric(a)) {
                i++;
                continue;
            }
            if (!isAlphanumeric(b)) {
                j--;
                continue;
            }
            if (a == b || Math.abs(a - b) == 32) {
                i++;
                j--;
                continue;
            }
            return false;
        }
        return true;
    }

    public boolean isAlphanumeric(char c) {
        if (c >= '0' && c <= '9') {
            return true;
        }
        if (c >= 'a' && c <= 'z') {
            return true;
        }
        if (c >= 'A' && c <= 'Z') {
            return true;
        }
        return false;
    }

    /**
     * 6
     *
     * @param s
     * @param numRows
     * @return
     */
    public String convert(String s, int numRows) {
        StringBuilder sb = new StringBuilder();
        int D = 2 * numRows - 2;
        if (D == 0) {
            return s;
        }
        for (int r = 0; r < numRows; r++) {
            boolean lastRow = (r == numRows - 1);
            for (int k = 0; ; k++) {
                int d = D * k;
                int i = d - r;
                int j = d + r;
                if (i >= s.length()) {
                    break;
                }
                if (i >= 0) {
                    sb.append(s.charAt(i));
                }
                if (!lastRow && j != i && j < s.length()) {
                    sb.append(s.charAt(j));
                }
            }
        }
        return sb.toString();
    }

    /**
     * 204
     *
     * @param n
     * @return
     * @ Util.countPrimesBelow
     */
    public int countPrimes(int n) {
        if (n < 2) {
            return 0;
        }
        //
        return -1;
    }

    /**
     * 278
     *
     * @return
     */
    public int firstBadVersion(int n) {
        long left = 1;
        long right = n;
        long m = 1;
        int t = 0;
        while (left <= right) {
            m = (left + right) / 2;
            if (isBadVersion((int) m)) {
                t = (int) m;
                right = m - 1;
            } else {
                left = m + 1;
            }
        }
        return t;
    }

    private boolean isBadVersion(int m) {
        return m >= 2;
    }

    /**
     * 168-fail
     *
     * @param n
     * @return
     */
    public String convertToTitle(int n) {
        String s = "";
        while (n - 1 >= 0) {
            char c = (char) ((n - 1) % 26 + 'A');
            s = c + s;
            if (n <= 26) {
                break;
            }
            n = n % 26 == 0 ? (n / 26) : (n / 26 + 1);
        }
        return s;
    }

    /**
     * 189
     *
     * @param nums
     * @param k
     */
    public void rotate(int[] nums, int k) {
        int[] d = new int[k];
        int j = 0;
        for (int i = nums.length - k - 1; i < nums.length - 1; i++) {
            d[j++] = nums[i];
        }
    }

    /**
     * 165
     *
     * @param version1
     * @param version2
     * @return
     */
    public int compareVersion(String version1, String version2) {
        return -1;
    }

    /**
     * 是否只包含2,3,5作为因子 Hamming numbers
     *
     * @param num
     * @return
     */
    public boolean isUgly(int num) {
        while (num > 0) {
            if (num == 1) {
                return true;
            }
            boolean ok = false;
            if (num % 2 == 0) {
                num /= 2;
                ok = true;
            }
            if (num % 3 == 0) {
                num /= 3;
                ok = true;
            }
            if (num % 5 == 0) {
                num /= 5;
                ok = true;
            }
            if (!ok) {
                return false;
            }
        }
        return false;
    }

    /**
     * 235
     * <p>
     * 如果当前节点为空或者与目标节点之一相同，则返回当前节点
     * <p>
     * 递归寻找p和q在左右子树中的位置
     * <p>
     * 如果p和q分别位于root的左右两侧，则root为它们的LCA，否则为左子树或者右子树
     *
     * @param root
     * @param p
     * @param q
     * @return
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        List<TreeNode> lca = new ArrayList<TreeNode>(1);
        hasChild(root, p, q, lca);
        if (lca.size() > 0) {
            return lca.get(0);
        }
        return null;
    }

    /**
     * 递归法：发现根之后返回
     *
     * @param root
     * @param p
     * @param q
     * @return 0:root不是p和q的根，1，root是p的根，2：root是q的根，3：root是p和q的根
     */
    public int hasChild(TreeNode root, TreeNode p, TreeNode q,
                        List<TreeNode> lca) {
        if (root == null) {
            return 0;
        }
        int ret1 = 0;
        int ret2 = 0;
        if (root == p) {
            ret1 = 1;
        }
        if (root == q) {
            ret2 = 2;
        }
        int ret11 = hasChild(root.left, p, q, lca);
        if (ret1 + ret2 + ret11 == 3) {// p,q在以左子树和root构成的数上，直接返回
            if (lca.size() == 0) {
                lca.add(ret1 + ret2 > 0 ? root : root.left);
            }
            return 3;
        }
        int ret22 = hasChild(root.right, p, q, lca);
        if (ret1 + ret2 + ret11 + ret22 == 3) {// p,q在以root为根的树上，直接返回
            if (lca.size() == 0) {
                if (lca.size() == 0) {
                    if (ret1 + ret2 > 0 || (ret11 > 0 && ret22 > 0)) {
                        lca.add(root);
                    } else if (ret11 == 3) {
                        lca.add(root.left);
                    } else {
                        lca.add(root.right);
                    }
                }
            }
            return 3;
        }
        return ret1 + ret2 + ret11 + ret22;
    }

    /**
     * 235 递归法2--Best
     *
     * @param root
     * @param p
     * @param q
     * @return
     */
    public TreeNode lowestCommonAncestor2(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q) {
            return root;
        }
        TreeNode left = lowestCommonAncestor2(root.left, p, q);
        TreeNode right = lowestCommonAncestor2(root.right, p, q);
        if (left != null && right != null) {
            return root;
        }
        if (left != null) {
            return left;
        }
        return right;
    }

    /**
     * 240
     *
     * @param matrix
     * @param target
     * @return
     */
    public boolean searchMatrix(int[][] matrix, int target) {
        for (int i = 0; i < matrix.length; i++) {
            int j = bSearch(matrix[i], 0, matrix[i].length, target);
            if (j >= 0) {
                return true;
            }
        }
        return false;
    }

    public int bSearch(int[] X, int s, int e, int t) {
        while (s <= e) {
            int m = (s + e) / 2;
            if (m >= X.length || m < 0) {
                return -1;
            }
            if (X[m] == t) {
                return m;
            } else if (X[m] > t) {
                e = m - 1;
            } else {
                s = m + 1;
            }
        }
        return -1;
    }

    /**
     * 268
     * <p>
     * 方法1：n*(n+1)/2 - sum(nums)
     * <p>
     * 方法2：using Java BitSet
     * <p>
     * 方法3：using XOR
     *
     * @param nums
     * @return
     */
    public int missingNumber(int[] nums) {
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
        }
        int n = nums.length;
        return n * (n + 1) / 2 - sum;
    }

    /**
     * 306，通过分析累加数的特性，可以知道累加数的结构取决于前面两个数的值。假设前面两个数是a和b，
     * 则累加数可以是：abc1c2c3...
     * 其中，c1=a+b,c2=b+c1,c3=c1+c2,...
     * <p>
     * 根据上面特性，确定算法如下：
     * <p>
     * 首先判断是否存在：开头两个数之和等于接下来的第三个数，如果存在则递归进行判断后续是都满足。
     * <p>
     * 比如"958103111224335"，通过枚举发现95+8=103,即找到了a=95，b=8，c1=a+b=103，然后递归判断：
     * 111224335是否满足，而前面两个数是8和103，
     *
     * @param num
     * @return
     */
    public boolean isAdditiveNumber(String num) {
        for (int i = 0; i < num.length() / 2 + 1; i++) {
            BigInteger a = asInteger(num, 0, i);
            int la = a.toString().length();
            for (int j = i + 1; j < num.length() - la; j++) {
                BigInteger b = asInteger(num, i + 1, j);
                BigInteger x = a.add(b);
                String xs = x.toString();
                if (j + xs.length() < num.length()) {
                    if (xs.equals(num.substring(j + 1, j + xs.length() + 1))) {
                        if (isAdditiveNumber(num.substring(i + 1), b, x)) {
                            return true;
                        }
                    }
                }
                if (num.charAt(i + 1) == '0') {
                    break;
                }
            }
        }
        return false;
    }

    public static char[] reverse(char[] array) {
        for (int i = 0; i < array.length - i; i++) {
            char a = array[i];
            array[i] = array[array.length - i - 1];
            array[array.length - i - 1] = a;
        }
        return array;
    }

    /**
     * 344
     *
     * @param s
     * @return
     */
    public String reverseString(String s) {
        return new String(reverse(s.toCharArray()));
    }


    /**
     * 371
     * Calculate the sum of two integers a and b, but you are not allowed to use the operator + and -.
     *
     * @param a
     * @param b
     * @return
     */
    public int getSum(int a, int b) {
        if (b == 0) {
            return a;
        }
        if (a == 0) {
            return b;
        }
        int a1 = a ^ b;
        int b1 = (a & b) << 1;
        return getSum(a1, b1);
    }


    public BigInteger asInteger(String num, int from, int to) {
        return new BigInteger(num.substring(from, to + 1));
    }

    public boolean isAdditiveNumber(String num, BigInteger n1, BigInteger n2) {
        BigInteger n = n1.add(n2);
        String ns = n.toString();
        int l1 = n1.toString().length();
        int start = l1 + n2.toString().length();
        if (num.length() == start) {
            return true;
        }
        if (eq(num, start, ns)) {
            if (start + ns.length() == num.length()) {
                return true;
            }
            return isAdditiveNumber(num.substring(l1), n2, n);
        }
        return false;
    }

    private static boolean eq(String num, int start, String str) {
        if (start + str.length() <= num.length()) {
            return num.substring(start, start + str.length()).equals(str);
        }
        return false;
    }

    /**
     * 349 Intersection of Two Arrays
     *
     * @param nums1
     * @param nums2
     * @return
     */
    public int[] intersection(int[] nums1, int[] nums2) {
        Set<Integer> set = new HashSet<Integer>();
        Set<Integer> num2Set = new HashSet<Integer>();
        for (int num : nums2) {
            num2Set.add(num);
        }
        for (int i = 0; i < nums1.length; i++) {
            if (num2Set.contains(nums1[i])) {
                set.add(nums1[i]);
            }
        }
        int[] ret = new int[set.size()];
        int i = 0;
        for (Integer num : set) {
            ret[i++] = num;
        }
        return ret;
    }

    /**
     * 237
     *
     * @param node
     */
    public void deleteNode(ListNode node) {
        ListNode next = node;
        ListNode prev = node;
        while (next.next != null) {
            next.val = next.next.val;
            prev = next;
            next = next.next;
        }
        prev.next = null;
    }

    /**
     * 350
     *
     * @param nums1
     * @param nums2
     * @return
     */
    public int[] intersect(int[] nums1, int[] nums2) {
        int[] tmp = nums1;
        List<Integer> set = new ArrayList<Integer>();
        Map<Integer, Integer> num2Set = new HashMap<Integer, Integer>();
        for (int num : nums2) {
            Integer c = num2Set.get(num);
            if (c == null) {
                c = 0;
            }
            c = c + 1;
            num2Set.put(num, c);
        }
        for (int i = 0; i < nums1.length; i++) {
            int num = nums1[i];
            Integer c = num2Set.get(num);
            if (c != null) {
                set.add(nums1[i]);
                c = c - 1;
                if (c == 0) {
                    num2Set.remove(num);
                } else {
                    num2Set.put(num, c);
                }
            }
        }
        int[] ret = new int[set.size()];
        int i = 0;
        for (Integer num : set) {
            ret[i++] = num;
        }
        return ret;
    }


    private static Map<Character, Integer> roman = new HashMap<Character, Integer>();

    static {
        roman.put('I', 1);
        roman.put('V', 5);
        roman.put('X', 10);
        roman.put('L', 50);
        roman.put('C', 100);
        roman.put('D', 500);
        roman.put('M', 1000);
    }

    static boolean isJoin(char a, char b) {
        switch (a) {
            case 'I':
                return b == 'V';
            case 'V':
                return b == 'X' || b == 'I';
            case 'X':
                return b == 'V' || b == 'L';
            case 'L':
                return b == 'X' || b == 'C';
            case 'C':
                return b == 'L' || b == 'D';
            case 'D':
                return b == 'C' || b == 'M';
            case 'M':
                return b == 'D';
        }
        return false;
    }

    /**
     * 13 Roman to int
     * <p>
     * FAIL
     *
     * @param s
     * @return
     */
    public int romanToInt(String s) {
        int S = 0;
        char[] data = s.toCharArray();
        char pre = 0;
        int C = 0;
        for (int i = 0; i < data.length; i++) {
            char c = data[i];
            int v = roman.get(c);
            if (pre == 0) {
                C = v;
            } else if (pre == c) {
                C += v;
            } else if (pre < v) {
                S = v - C;
                pre = 0;
            } else if (pre > v) {
                C += v;
            }
            pre = c;
        }
        return S;
    }

    /**
     * 326
     *
     * @param n
     * @return
     */
    public boolean isPowerOfThree(int n) {
        if (n <= 0) {
            return false;
        }
        int k = (int) (Math.log10(n) / Math.log10(3));
        int N = (int) Math.pow(3, k);
        return n == N;
    }

    /**
     * 342
     *
     * @param n
     * @return
     */
    public boolean isPowerOfFour(int n) {
        if (n <= 0) {
            return false;
        }
        int k = (int) (Math.log10(n) / Math.log10(4));
        int N = (int) Math.pow(4, k);
        return n == N;
    }

    /**
     * 191
     * C++用解题
     *
     * @param n
     * @return
     */
    public int hammingWeight(long n) {
        int c = 0;
        long a = 1;
        while (a > 0) {
            if ((n & a) > 0) {
                c++;
            }
            a <<= 1;
        }
        return c;
    }

    /**
     * 190 OK
     * use C++
     *
     * @param n
     * @return
     */
    public int reverseBits(int n) {
        int a = 0;
        int c = 32;
        while (n > 0) {
            int u = (n & 1);
            if (u == 0) {
                if (a != 0) {
                    a <<= 1;
                }
            } else {
                if (a == 0) {
                    a = 1;
                } else {
                    a <<= 1;
                    a += 1;
                }
            }
            n >>= 1;
            c--;
        }
        a <<= c;
        return a;
    }

    public int[] countBits(int num) {
        if (num == 0) {
            return new int[]{0};
        }
        int[] ret = new int[num + 1];
        ret[0] = 0;
        ret[1] = 1;
        OUT:
        for (int a = 2; ; a *= 2) {
            for (int i = a; i < 2 * a; i++) {
                if (i > num) {
                    break OUT;
                }
                if (i == a) {
                    ret[a] = 1;
                } else {
                    ret[i] = 1 + ret[i - a];
                }
                if (i == num) {
                    break OUT;
                }
            }
        }
        return ret;
    }

    /**
     * 121
     * dp
     * 动态规划
     *
     * @param prices
     * @return
     */
    public int maxProfit(int[] prices) {
        if (prices.length <= 1) {
            return 0;
        }
        int[] profits = new int[prices.length];
        for (int i = 1; i < prices.length; i++) {
            int maxProfit = profits[i - 1];
            if (prices[i] > prices[i - 1]) {
                for (int j = 0; j < i; j++) {
                    if (prices[i] - prices[j] > maxProfit) {
                        maxProfit = prices[i] - prices[j];
                    }
                }
                profits[i] = maxProfit;
            } else {
                profits[i] = profits[i - 1];
            }
        }
        return profits[prices.length - 1];
    }

    /**
     * 121
     * WRONG
     *
     * @param prices
     * @return
     */
    public int maxProfit0(int[] prices) {
        if (prices.length <= 1) {
            return 0;
        }
        int[] profits = new int[prices.length];
        int index1 = -1;
        int index2 = -1;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] > prices[i - 1]) {
                if (index1 == -1) {
                    index1 = i - 1;
                }
                if (index2 == -1) {
                    index2 = i;
                }
                int p0 = profits[i - 1];
                int p1 = prices[i] - prices[i - 1];
                int p2 = prices[i] - prices[index1];
                if (p1 > p2) {
                    if (p1 > p0) {
                        index1 = i - 1;
                        index2 = i;
                        profits[i] = p1;
                    } else if (p1 < p0) {
                        profits[i] = p0;
                    } else {//p1==p0
                        if (prices[i - 1] < prices[index1]) {
                            index1 = i - 1;
                            index2 = i;
                        }
                        profits[i] = p0;
                    }
                } else if (p2 > p1) {
                    if (p2 > p0) {
                        index2 = i;
                        profits[i] = p2;
                    } else if (p2 <= p0) {
                        profits[i] = p0;
                    }
                } else if (p1 == p2) {
                    if (p1 < p0) {
                        profits[i] = p0;
                    } else if (prices[i - 1] < prices[index1]) {
                        index1 = i - 1;
                        index2 = i;
                    }
                    profits[i] = p1;
                }
            } else {
                profits[i] = profits[i - 1];
            }
        }
        return profits[prices.length - 1];
    }

    /**
     * 141
     *
     * @param head
     * @return
     */
    public boolean hasCycle(ListNode head) {
        Set<ListNode> vals = new HashSet<ListNode>();
        ListNode next = head;
        while (next != null) {
            if (vals.contains(next)) {
                return true;
            }
            vals.add(next);
            next = next.next;
        }
        return false;
    }

    /**
     * 141
     *
     * @param head
     * @return
     */
    public boolean hasCycle2(ListNode head) {
        if (head == null || head.next == null) {
            return false;
        }
        ListNode slow = head;
        ListNode fast = head.next;
        while (slow != fast) {
            if (fast == null || fast.next == null) {
                return false;
            }
            slow = slow.next;
            fast = fast.next.next;
        }
        return true;
    }

    /**
     * 142
     * @param head
     * @return
     */
    public ListNode detectCycle(ListNode head) {
        Set<ListNode> vals = new HashSet<ListNode>();
        ListNode next = head;
        while (next != null) {
            if (vals.contains(next)) {
                return next;
            }
            vals.add(next);
            next = next.next;
        }
        return null;
    }


    /**
     * 287
     * @param nums
     * @return
     */
    public int findDuplicate(int[] nums) {
        for(int i=0;i<nums.length;i++) {
            for(int j=i+1;j<nums.length;j++) {
                if (nums[i] == nums[j]) {
                    return nums[i];
                }
            }
        }
        return 0;
    }
}
