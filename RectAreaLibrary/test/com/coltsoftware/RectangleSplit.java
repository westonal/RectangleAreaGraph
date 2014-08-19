package com.coltsoftware;

import java.util.ArrayList;
import java.util.List;

public class RectangleSplit {

	private final ArrayList<SplitResult> arrayList = new ArrayList<SplitResult>();

	public List<SplitResult> split(Rectangle rectangle) {

		for (int i = 0; i < arrayList.size(); i++) {
			SplitResult result = arrayList.get(i);
			result.rectangle = new Rectangle(rectangle.getLeft() + i
					* rectangle.getWidth() / arrayList.size(),
					rectangle.getTop(),
					rectangle.getWidth() / arrayList.size(),
					rectangle.getHeight());
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
