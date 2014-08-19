package com.coltsoftware.example;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;

import com.coltsoftware.rectangleareagraph.Rectangle;
import com.coltsoftware.rectangleareagraph.RectangleSplit.SplitResult;
import com.coltsoftware.rectangleareagraph.RectangleStringSplit;

public class ValuesToPng {

	public static void main(String[] args) {
		RectangleStringSplit rectangleSplit = new RectangleStringSplit();
		rectangleSplit.addValue(7, "A");
		rectangleSplit.addValue(8, "B");
		rectangleSplit.addValue(9, "C");
		rectangleSplit.addValue(10, "D");
		rectangleSplit.addValue(30, "E");
		rectangleSplit.addValue(40, "F");
		rectangleSplit.addValue(60, "G");
		rectangleSplit.addValue(80, "H");
		rectangleSplit.addValue(130, "I");
		int width = 800;
		int height = 640;
		List<SplitResult<String>> split = rectangleSplit.split(new Rectangle(0,
				0, width - 1, height - 1));
		RenderedImage image = render(split, width, height);
		saveImage(image);
	}

	private static RenderedImage render(List<SplitResult<String>> split,
			int width, int height) {
		BufferedImage bufferedImage = new BufferedImage(width, height,
				BufferedImage.TYPE_INT_ARGB);
		Graphics2D graphics = bufferedImage.createGraphics();
		Paint p = Color.BLACK;
		graphics.setPaint(p);
		for (SplitResult<String> result : split) {
			Rectangle rectangle = result.getRectangle();

			graphics.drawRect(rectangle.getLeft(), rectangle.getTop(),
					rectangle.getWidth(), rectangle.getHeight());
			graphics.drawChars(result.getTag().toCharArray(), 0, result
					.getTag().length(),
					rectangle.getLeft() + rectangle.getWidth() / 2,
					rectangle.getTop() + rectangle.getHeight() / 2);
		}
		return bufferedImage;
	}

	private static void saveImage(RenderedImage bufferedImage) {
		try {
			File outputfile = new File("saved.png");
			ImageIO.write(bufferedImage, "png", outputfile);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

}
