package edu.unca.cl373.jatyas.flowChart;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletionException;

import csci348.drawings.Drawing;

public class Triangle extends Polygon implements IFillable {

	public static final double SQ_ROOT_OF_THREE = Math.sqrt(3);

	private int sideLength;
	private Point baseCenterPoint;
	private boolean filled;
	private List<Line> fillLines;
	private Direction direction;

	private Line leftSide;
	private Line rightSide;
	private Line bottomSide;

	public Triangle(Point baseCenterPoint, int sideLength, Direction direction, Drawing canvas) {
		super(canvas);
		this.baseCenterPoint = baseCenterPoint;
		this.sideLength = sideLength;
		this.direction = direction;

		this.fillLines = new ArrayList<Line>();
		this.filled = false;
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

		this.isDrawn = true;
	}

	@Override
	public boolean isFilled() {
		// TODO Auto-generated method stub
		return filled;
	}

	@Override
	public void fill() {
		if (!isFilled()) {
			if (this.fillLines.isEmpty())
				createFillLines();

			for (Line fillLine : this.fillLines) {
				fillLine.draw();
			}
			this.filled = true;
		}
	}

	@Override
	public void unFill() {
		if (isFilled()) {
			for (Line fillLine : this.fillLines) {
				fillLine.erase();
			}
			this.filled = false;
		}

		if (super.isDrawn())
			draw();
	}

	public int getSideLength() {
		return sideLength;
	}

	public Point getBaseCenterPoint() {
		return baseCenterPoint;
	}

	protected List<Line> getFillLines() {
		return fillLines;
	}

	public Direction getDirection() {
		return direction;
	}

	private boolean createBorderLines() {
		Point bottomLeftP;
		Point apexP;
		Point bottomRightP;

		double height = ((double) getSideLength() / 2.0) * SQ_ROOT_OF_THREE;

		int bottomL_x = getBaseCenterPoint().getX_coord() - getSideLength() / 2;
		int bottomR_x = bottomL_x + getSideLength();
		int top_y;
		if (getDirection() == Direction.SOUTH) {
			top_y = getBaseCenterPoint().getY_coord() + (int) Math.round(height);
		} else {
			top_y = getBaseCenterPoint().getY_coord() - (int) Math.round(height);
		}

		bottomLeftP = new Point(bottomL_x, getBaseCenterPoint().getY_coord(), getCanvas());
		bottomRightP = new Point(bottomR_x, getBaseCenterPoint().getY_coord(), getCanvas());
		apexP = new Point(getBaseCenterPoint().getX_coord(), top_y, getCanvas());

		leftSide = new Line(bottomLeftP, apexP, getCanvas());
		rightSide = new Line(apexP, bottomRightP, getCanvas());
		bottomSide = new Line(bottomRightP, bottomLeftP, getCanvas());

		getBorderLines().add(leftSide);
		getBorderLines().add(rightSide);
		getBorderLines().add(bottomSide);

		return true;
	}

	private boolean createFillLines() {
		Point leftEndPoint;
		Point rightEndPoint;
		Line fillLine;
		
		int leftX, rightX, y;
		int centerX = getBaseCenterPoint().getX_coord();
		for (Point leftPoint : this.leftSide.getPoints()) {
			leftX = leftPoint.getX_coord();
			y = leftPoint.getY_coord();
			rightX = (centerX - leftX) * 2 + leftX;

			leftEndPoint = new Point(leftPoint, getCanvas());
			rightEndPoint = new Point(rightX, y, getCanvas());

			if (leftX != rightX) {
				fillLine = new Line(leftEndPoint, rightEndPoint, getCanvas());
				getFillLines().add(fillLine);
			}
		}

		return true;
	}
}
