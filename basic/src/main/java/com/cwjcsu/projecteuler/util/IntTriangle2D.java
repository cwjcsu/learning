/*$Id: $
 --------------------------------------
  Skybility
 ---------------------------------------
  Copyright By Skybility ,All right Reserved
 * author                     date    comment
 * chenweijun@skybility.com  2015年9月22日  Created
 */
package com.cwjcsu.projecteuler.util;

import java.util.HashSet;
import java.util.Set;

/**
 * 
 * @author atlas
 *
 */
public class IntTriangle2D {
	public IntVector2D A;
	public IntVector2D B;
	public IntVector2D C;

	private Set<IntVector2D> points = new HashSet<IntVector2D>(3);

	public IntTriangle2D(IntVector2D a, IntVector2D b, IntVector2D c) {
		super();
		A = a;
		B = b;
		C = c;
		this.points.add(a);
		this.points.add(b);
		this.points.add(c);
	}

	@Override
	public int hashCode() {
		return A.hashCode() + B.hashCode() + C.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		IntTriangle2D other = (IntTriangle2D) obj;
		return other.points.contains(this.A) && other.points.contains(this.B)
				&& other.points.contains(this.C);
	}

}
