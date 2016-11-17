package edu.unca.cl373.jatyas.flowChart;

import java.util.*;
import java.util.concurrent.CompletionException;

import csci348.drawings.Drawing;

public class Polygon extends Element implements IDrawable {
	
	private List<Line> borderLines;
	protected boolean isDrawn;
	
	public static final int MIN_SIDE_LENGTH = 1;
	public static final int MIN_NUMBER_SIDES = 3;
	public static final String ERROR_MIN_SIDE_NUM = "The number of sides must be greater than or equal to "
			+ String.valueOf(MIN_NUMBER_SIDES) + ".";
	public static final String ERROR_SIDE_LENGTH = "The length of each side must must be greater than or equal to "
			+ String.valueOf(MIN_SIDE_LENGTH) + ".";
	public static final String ERROR_BAD_BORDERLINES_STATE = "The border lines of the shape are incorrectly formed.";

	protected Polygon(Drawing canvas){
		super(canvas);
		this.borderLines = new ArrayList<Line>();
		this.isDrawn = false;
	}
	
	public Polygon(List<Line> lines, Drawing canvas) {
		super(canvas);
		
		this.borderLines = lines;
		this.isDrawn = false;
	}
	
	protected List<Line> getBorderLines() {
		return borderLines;
	}

	@Override
	public void draw() {
		if (!isCorrect())
			throw new CompletionException(new Throwable(ERROR_BAD_BORDERLINES_STATE));
		
		for (Line borderLine : getBorderLines()) {
			borderLine.draw();
		}
		
		this.isDrawn = true;
	}

	@Override
	public void erase() {
		for (Line borderLine : getBorderLines()) {
			borderLine.erase();
		}
		
		this.isDrawn = false;
	}

	@Override
	public boolean isDrawn() {
		return this.isDrawn;
	}
	
	public boolean isCorrect() {
		
		List<Line> borderLine = getBorderLines();
		int numLines = borderLine.size();
		
		if (borderLine.isEmpty() || (numLines < Polygon.MIN_NUMBER_SIDES))
			return false;
		
		for (int i = 0; i < numLines - 1; i++) {
			if (!(borderLine.get(i).isCorrect() && borderLine.get(i + 1).isCorrect()))
				return false;
			if (!(borderLine.get(i).getEndPoint().equals(borderLine.get(i + 1).getStartPoint())))
				return false;
		}
		
		if (!borderLine.get(0).getStartPoint().equals(borderLine.get(numLines - 1).getEndPoint()))
			return false;
		
		return true;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((borderLines == null) ? 0 : borderLines.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Polygon)) {
			return false;
		}
		Polygon other = (Polygon) obj;
		if (borderLines == null) {
			if (other.borderLines != null) {
				return false;
			}
		} else if (!borderLines.equals(other.borderLines)) {
			return false;
		}
		return true;
	}

	
}
