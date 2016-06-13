package com.cwjcsu.learning.笔试;

import java.util.HashSet;
import java.util.Set;

/**
 * 
 * @author atlas
 * @date 2013-4-22
 */
public class Name {
	private final String first, last;

	public Name(String first, String last) {
		this.first = first;
		this.last = last;
	}
 
	public boolean equals(Object o) {
		if (!(o instanceof Name))
			return false;
		Name n = (Name) o;
		return n.first.equals(first) && n.last.equals(last);
	}

	public static void main(String[] args) {
		Set<Name> s = new HashSet<Name>();
		for(int i=0;i<3;i++){
			s.add(new Name("Mickey", "Mouse"));
		}
		StringBuilder out = new StringBuilder(s.size());
		out.append(s.contains(new Name("Mickey", "Mouse")));
		System.out.println(out);
	}
}