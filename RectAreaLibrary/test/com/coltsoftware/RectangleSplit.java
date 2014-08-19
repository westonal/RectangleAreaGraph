package com.coltsoftware;

import java.util.ArrayList;
import java.util.List;

public class RectangleSplit {

	private final SplitResultList arrayList = new SplitResultList();

	public List<SplitResult> split(Rectangle rectangle) {
		processSubList(arrayList.subList(0, arrayList.size()), rectangle, false);
		return arrayList.asList();
	}

	private static void processSubList(SplitResultList arrayList,
			Rectangle rectangle, boolean dirToggle) {
		int total = arrayList.getTotal();

		int oneThird = total / 3;

		int t2 = 0;
		int size = arrayList.size();
		int firstThirdCount = 0;
		for (int i3 = 0; i3 < size; i3++) {
			t2 += arrayList.get(i3).value;
			firstThirdCount++;
			if (t2 >= oneThird) {
				break;
			}
		}

		if (firstThirdCount < size && firstThirdCount > 1) {
			SplitResultList clone = arrayList.subList(0, arrayList.size());
			SplitResultList firstThird = clone.subList(0, firstThirdCount);
			clone.fold(firstThirdCount);
			processSubList(clone, rectangle, dirToggle);
			processSubList(firstThird, clone.get(0).rectangle, !dirToggle);
			return;
		}

		if (dirToggle) {
			int height = rectangle.getHeight();

			for (int i = 0; i < size; i++) {
				SplitResult result = arrayList.get(i);
				int height2 = i == (size - 1) ? height : result.value
						* rectangle.getHeight() / total;
				int left = rectangle.getLeft();
				int top = rectangle.getTop() + rectangle.getHeight() - height;
				int width = rectangle.getWidth();
				result.rectangle = new Rectangle(left, top, width, height2);
				height -= height2;
			}
		} else {
			int width = rectangle.getWidth();

			for (int i = 0; i < size; i++) {
				SplitResult result = arrayList.get(i);
				int width2 = i == (size - 1) ? width : result.value
						* rectangle.getWidth() / total;
				int left = rectangle.getLeft() + rectangle.getWidth() - width;
				int top = rectangle.getTop();
				int height = rectangle.getHeight();
				result.rectangle = new Rectangle(left, top, width2, height);
				width -= width2;
			}
		}
	}

	public void addValue(int value, String description) {
		arrayList.add(new SplitResult(value, description));
	}

	public static class SplitResultList {
		private final ArrayList<SplitResult> arrayList = new ArrayList<SplitResult>();

		public int size() {
			return arrayList.size();
		}

		public void fold(int count) {
			int subTotal = 0;
			for (int i = 0; i < count; i++)
				subTotal += arrayList.remove(0).value;
			SplitResult combined = new SplitResult(subTotal, "Combined");
			arrayList.add(0, combined);
		}

		public SplitResult get(int index) {
			return arrayList.get(index);
		}

		public void add(SplitResult splitResult) {
			arrayList.add(splitResult);
		}

		public List<SplitResult> asList() {
			return arrayList;
		}

		public int getTotal() {
			int total = 0;
			int size = size();
			for (int i = 0; i < size; i++)
				total += arrayList.get(i).value;
			return total;
		}

		public SplitResultList subList(int startAt, int count) {
			SplitResultList splitResultList = new SplitResultList();
			for (int i = startAt; i < startAt + count; i++)
				splitResultList.add(get(i));
			return splitResultList;
		}

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
