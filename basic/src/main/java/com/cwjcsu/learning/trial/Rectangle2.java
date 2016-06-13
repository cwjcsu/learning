package com.cwjcsu.learning.trial;
/**
 * 
 * @author atlas
 * @date 2013-3-20
 */
public class Rectangle2 {
	// instance variables
	private int length;
	private int width;

	/**
	 * Constructor for objects of class rectangle
	 */
	public Rectangle2(int l, int w) {
		// initialise instance variables
		length = l;
		width = w;
	}

	// return the height
	public int getLength() {
		return length;
	}

	public int getWidth() {
		return width;
	}

	public String toString() {
		return "Rectangle - " + length + " X " + width;
	}

	@Override
	public boolean equals(Object obj) {
		if (this.getClass() == obj.getClass()) {
			Rectangle2 other = (Rectangle2) obj;
			return length == other.length && width == other.width;
		}
		return false;
	}
}

class Box2 extends Rectangle2 {
	// instance variables
	private int height;

	/**
	 * Constructor for objects of class box
	 */
	public Box2(int l, int w, int h) {
		// call superclass
		super(l, w);
		// initialise instance variables
		height = h;
	}

	// return the height
	public int getHeight() {
		return height;
	}

	public String toString() {
		return "Box - " + getLength() + " X " + getWidth() + " X " + height;
	}
	
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		return super.equals(obj);
	}
}

class Cube extends Box2 {
	public Cube(int length) {
		super(length, length, length);
	}

	public String toString() {
		return "Cube - " + getLength() + " X " + getWidth() + " X "
				+ getHeight();
	}
}