package com.coltsoftware;

public final class Rectangle {

	private final int left;
	private final int top;
	private final int width;
	private final int height;

	public Rectangle(int left, int top, int width, int height) {
		this.left = left;
		this.top = top;
		this.width = width;
		this.height = height;
	}

	public int getLeft() {
		return left;
	}

	public int getTop() {
		return top;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		Rectangle other = null;
		if (obj.getClass() == getClass())
			other = (Rectangle) obj;

		return equals(other);
	}

	public boolean equals(Rectangle other) {
		if (other == null)
			return false;
		return other.left == left && other.top == top && other.width == width
				&& other.height == height;
	}

	@Override
	public int hashCode() {
		int hash = left;
		hash *= 31;
		hash += top;
		hash *= 31;
		hash += width;
		hash *= 31;
		hash += height;
		return hash;
	}

	@Override
	public String toString() {
		return String.format("[(%d, %d) %d x %d]", left, top, width, height);
	}

}
