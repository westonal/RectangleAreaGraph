package com.coltsoftware;

import java.util.ArrayList;
import java.util.List;

public class RectangleSplit {

	private final ArrayList<SplitResult> arrayList = new ArrayList<SplitResult>();

	public List<SplitResult> split(Rectangle rectangle) {
		final int size = arrayList.size();

		int total = 0;
		for (int i2 = 0; i2 < size; i2++)
			total += arrayList.get(i2).value;

		int width = rectangle.getWidth();

		for (int i = 0; i < size; i++) {
			SplitResult result = arrayList.get(i);
			int width2 = i == (size - 1) ? width : result.value
					* rectangle.getWidth() / total;
			result.rectangle = new Rectangle(rectangle.getLeft()
					+ rectangle.getWidth() - width, rectangle.getTop(), width2,
					rectangle.getHeight());
			width -= width2;
		}

		return arrayList;
	}

	public void addValue(int value, String description) {
		arrayList.add(new SplitResult(value, description));
	}

	public static class SplitResult {

		private final int value;
		private final String description;
		private Rectangle rectangle;

		public SplitResult(int value, String description) {
			this.value = value;
			this.description = description;
		}

		public int getValue() {
			return value;
		}

		public String getDescription() {
			return description;
		}

		public Rectangle getRectangle() {
			return rectangle;
		}

	}

}
