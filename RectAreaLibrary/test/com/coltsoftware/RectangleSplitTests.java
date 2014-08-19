package com.coltsoftware;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.coltsoftware.RectangleSplit.SplitResult;

public class RectangleSplitTests {

	private RectangleSplit splitter;

	@Before
	public void setup() {
		splitter = new RectangleSplit();
	}

	@Test
	public void can_split_nothing() {
		List<SplitResult> results = splitter
				.split(new Rectangle(0, 0, 100, 100));
		assertEquals(0, results.size());
	}

	@Test
	public void can_split_one_item() {
		splitter.addValue(100, "Red");
		List<SplitResult> results = splitter
				.split(new Rectangle(0, 0, 100, 100));
		assertEquals(1, results.size());
		SplitResult result = results.get(0);
		assertEquals(100, result.getValue());
		assertEquals("Red", result.getDescription());
		assertEquals(new Rectangle(0, 0, 100, 100), result.getRectangle());
	}

	@Test
	public void can_split_two_items_and_read_values() {
		splitter.addValue(100, "Red");
		splitter.addValue(235, "Green");
		List<SplitResult> results = splitter
				.split(new Rectangle(0, 0, 100, 100));
		assertEquals(2, results.size());
		{
			SplitResult result = results.get(0);
			assertEquals(100, result.getValue());
			assertEquals("Red", result.getDescription());
		}
		{
			SplitResult result = results.get(1);
			assertEquals(235, result.getValue());
			assertEquals("Green", result.getDescription());
		}
	}

	@Test
	public void can_split_two_even_items() {
		splitter.addValue(100, "Red");
		splitter.addValue(100, "Green");
		List<SplitResult> results = splitter
				.split(new Rectangle(0, 0, 100, 100));
		assertEquals(2, results.size());
		assertEquals(new Rectangle(0, 0, 50, 100), results.get(0)
				.getRectangle());
		assertEquals(new Rectangle(50, 0, 50, 100), results.get(1)
				.getRectangle());
	}
	
	@Test
	public void can_split_three_even_items() {
		splitter.addValue(7, "Red");
		splitter.addValue(7, "Green");
		splitter.addValue(7, "Blue");
		List<SplitResult> results = splitter
				.split(new Rectangle(0, 0, 100, 100));
		assertEquals(3, results.size());
		assertEquals(new Rectangle(0, 0, 33, 100), results.get(0)
				.getRectangle());
		assertEquals(new Rectangle(33, 0, 33, 100), results.get(1)
				.getRectangle());		
		assertEquals(new Rectangle(66, 0, 34, 100), results.get(2)
				.getRectangle());
	}

}
