package edu.unca.cl373.jatyas.flowChart;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletionException;

import csci348.drawings.Drawing;

public class Rectangle extends Polygon implements IFillable {

	private Point start;
	private int height;
	private int width;
	
	private List<Line> fillLines;
	private Boolean isFilled;
	
	public Rectangle(Point start, int height, int width, Drawing canvas) {
		super(canvas);

		if (height < Math.abs(MIN_SIDE_LENGTH)) {
			throw new IllegalArgumentException(ERROR_SIDE_LENGTH);
		}
		if (width <= Math.abs(MIN_SIDE_LENGTH)) {
			throw new IllegalArgumentException(ERROR_SIDE_LENGTH);
		}

		this.start = start;
		this.height = height;
		this.width = width;
		this.fillLines = new ArrayList<Line>();
		this.isFilled = false;
	}

	@Override
	public void draw() {
		if (getBorderLines().isEmpty())
			createBorderLines();
		
		if (!isCorrect())
			throw new CompletionException(new Throwable(ERROR_BAD_BORDERLINES_STATE));
		
		for (Line borderLine : getBorderLines()) {
			borderLine.draw();
		}
		
		super.isDrawn = true;
	}

	@Override
	public void fill() {
		if (!isFilled()) {
			if (this.fillLines.isEmpty())
				createFillLines();
			
			for (Line fillLine : this.fillLines) {
				fillLine.draw();
			}
			this.isFilled = true;
		}
	}

	@Override
	public void unFill() {
		if (isFilled()) {
			for (Line fillLine : this.fillLines) {
				fillLine.erase();
			}
			this.isFilled = false;
		}
		
		if (super.isDrawn())
			draw();
	}

	@Override
	public boolean isFilled() {
		return this.isFilled;
	}

	private boolean createBorderLines() {
		int startY = this.start.getY_coord();
		int startX = this.start.getX_coord();
		int endX = startX + this.width - 1;
		int endY = startY + this.height - 1;

		Point topLeft = this.start;
		Point topRight = new Point(endX, startY, getCanvas());
		Point bottomRight = new Point(endX, endY, getCanvas());
		Point bottomLeft = new Point(startX, endY, getCanvas());

		getBorderLines().add(new Line(topLeft, topRight, getCanvas()));
		getBorderLines().add(new Line(topRight, bottomRight, getCanvas()));
		getBorderLines().add(new Line(bottomRight, bottomLeft, getCanvas()));
		getBorderLines().add(new Line(bottomLeft, topLeft, getCanvas()));

		return true;
	}

	private boolean createFillLines() {
		int startY = this.start.getY_coord();
		int startX = this.start.getX_coord();
		int endX = startX + this.width - 1;
		Point lineStartPoint = null;
		Point lineEndPoint = null;

		for (int y = startY; y < (startY + this.height); y++) {
			lineStartPoint = new Point(startX, y, this.getCanvas());
			lineEndPoint = new Point(endX, y, this.getCanvas());
			this.fillLines.add(new Line(lineStartPoint, lineEndPoint, this.getCanvas()));
		}

		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((fillLines == null) ? 0 : fillLines.hashCode());
		result = prime * result + height;
		result = prime * result + ((isFilled == null) ? 0 : isFilled.hashCode());
		result = prime * result + ((start == null) ? 0 : start.hashCode());
		result = prime * result + width;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!super.equals(obj)) {
			return false;
		}
		if (!(obj instanceof Rectangle)) {
			return false;
		}
		Rectangle other = (Rectangle) obj;
		if (fillLines == null) {
			if (other.fillLines != null) {
				return false;
			}
		} else if (!fillLines.equals(other.fillLines)) {
			return false;
		}
		if (height != other.height) {
			return false;
		}
		if (isFilled == null) {
			if (other.isFilled != null) {
				return false;
			}
		} else if (!isFilled.equals(other.isFilled)) {
			return false;
		}
		if (start == null) {
			if (other.start != null) {
				return false;
			}
		} else if (!start.equals(other.start)) {
			return false;
		}
		if (width != other.width) {
			return false;
		}
		return true;
	}

	public Point getStart() {
		return start;
	}

	public int getHeight() {
		return height;
	}

	public int getWidth() {
		return width;
	}

	protected List<Line> getFillLines() {
		return fillLines;
	}

	public Boolean getIsFilled() {
		return isFilled;
	}

}
