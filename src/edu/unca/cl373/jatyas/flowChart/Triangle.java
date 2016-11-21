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
	private int height;

	private Line lesserSide;
	private Line greaterSide;
	private Line baseSide;

	public Triangle(Point baseCenterPoint, int sideLength, Direction direction, Drawing canvas) {
		super(canvas);
		this.baseCenterPoint = baseCenterPoint;
		this.sideLength = sideLength;
		this.direction = direction;
		this.height = -1;

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
			if (getBorderLines().isEmpty())
				createBorderLines();
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

	public Line getLesserSide() {
		return lesserSide;
	}

	public Line getGreaterSide() {
		return greaterSide;
	}

	public Line getBaseSide() {
		return baseSide;
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

	public int getHeight() {
		if (this.height == -1)
			this.height = (int) Math.round(((double) getSideLength() / 2.0) * SQ_ROOT_OF_THREE);
		return this.height;
	}

	public Direction getDirection() {
		return direction;
	}

	private boolean createBorderLines() {
		Point apexA;
		Point apexB;
		Point apexC;

//		double height = ((double) getSideLength() / 2.0) * SQ_ROOT_OF_THREE;

		int baseLeast_X;
		int baseLeast_Y;
		int baseGreatest_X;
		int baseGreatest_Y;
		int top_X;
		int top_Y;
		
		if (getDirection() == direction.NORTH || getDirection() == Direction.SOUTH) {
			baseLeast_X = getBaseCenterPoint().getX_coord() - getSideLength() / 2;
			baseGreatest_X = baseLeast_X + getSideLength();
			
			if (getDirection() == direction.NORTH) {
				top_Y = getBaseCenterPoint().getY_coord() - getHeight();
			}
			else {	// SOUTH
				top_Y = getBaseCenterPoint().getY_coord() + getHeight();
			}
			
			apexA = new Point(baseLeast_X, getBaseCenterPoint().getY_coord(), getCanvas());
			apexB = new Point(getBaseCenterPoint().getX_coord(), top_Y, getCanvas());
			apexC = new Point(baseGreatest_X, getBaseCenterPoint().getY_coord(), getCanvas());
			
			this.lesserSide = new Line(apexA, apexB, getCanvas());
			this.greaterSide = new Line(apexB, apexC, getCanvas());
			this.baseSide = new Line(apexC, apexA, getCanvas());
		}
		else {	// EAST or WEST
			baseLeast_Y = getBaseCenterPoint().getY_coord() - getSideLength() / 2;
			baseGreatest_Y = baseLeast_Y + getSideLength();
			
			if (getDirection() == direction.EAST) {
				top_X = getBaseCenterPoint().getX_coord() + getHeight();
			}
			else {	// WEST
				top_X = getBaseCenterPoint().getX_coord() - getHeight();
			}
			
			apexA = new Point(getBaseCenterPoint().getX_coord(), baseLeast_Y, getCanvas());
			apexB = new Point(top_X, getBaseCenterPoint().getY_coord(), getCanvas());
			apexC = new Point(getBaseCenterPoint().getX_coord(), baseGreatest_Y, getCanvas());
			
			this.lesserSide = new Line(apexA, apexB, getCanvas());
			this.greaterSide = new Line(apexB, apexC, getCanvas());
			this.baseSide = new Line(apexC, apexA, getCanvas());
		}
		
		getBorderLines().add(lesserSide);
		getBorderLines().add(greaterSide);
		getBorderLines().add(baseSide);

		return true;
	}

	private boolean createFillLines() {
		Point lesserEndPoint;
		Point greaterEndPoint;
		Line fillLine;
		
		int lesser_X_Val, lesser_Y_Val, centerVal, greater_X_Val, greater_Y_Val;
		
		if (getDirection() == Direction.NORTH || getDirection() == Direction.SOUTH)
			centerVal = getBaseCenterPoint().getX_coord();
		else	//EAST or WEST pointing
			centerVal = getBaseCenterPoint().getY_coord();
		
		for (Point pnt : this.lesserSide.getPoints()) {
			
			lesserEndPoint = new Point(pnt, getCanvas());
			
			if (getDirection() == Direction.NORTH || getDirection() == Direction.SOUTH) {	// triangles point extends along Y-axis
				lesser_X_Val = pnt.getX_coord();
				greater_X_Val = (centerVal - lesser_X_Val) * 2 + lesser_X_Val;
				greaterEndPoint = new Point(greater_X_Val, pnt.getY_coord(), getCanvas());
			}
			else {	// EAST or WEST:  triangle's point extends along X-axis
				lesser_Y_Val = pnt.getY_coord();
				greater_Y_Val = (centerVal - lesser_Y_Val) * 2 + lesser_Y_Val;
				greaterEndPoint = new Point(pnt.getX_coord(), greater_Y_Val, getCanvas());
			}
				
			if (!lesserEndPoint.equals(greaterEndPoint)) {
				fillLine = new Line(lesserEndPoint, greaterEndPoint, getCanvas());
				getFillLines().add(fillLine);
			}
		}

		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((baseCenterPoint == null) ? 0 : baseCenterPoint.hashCode());
		result = prime * result + ((baseSide == null) ? 0 : baseSide.hashCode());
		result = prime * result + ((direction == null) ? 0 : direction.hashCode());
		result = prime * result + ((lesserSide == null) ? 0 : lesserSide.hashCode());
		result = prime * result + ((greaterSide == null) ? 0 : greaterSide.hashCode());
		result = prime * result + sideLength;
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
		if (!(obj instanceof Triangle)) {
			return false;
		}
		Triangle other = (Triangle) obj;
		if (baseCenterPoint == null) {
			if (other.baseCenterPoint != null) {
				return false;
			}
		} else if (!baseCenterPoint.equals(other.baseCenterPoint)) {
			return false;
		}
		if (direction != other.direction) {
			return false;
		}
		if (sideLength != other.sideLength) {
			return false;
		}
		return true;
	}
}
