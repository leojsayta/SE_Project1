package edu.unca.cl373.jatyas.flowChart;

import java.util.*;
import java.util.concurrent.CompletionException;

import csci348.drawings.Drawing;

public class Line extends Element {
	
	public static final String ERROR_BAD_LINE_POINTS = "The points of the line are incorrectly formed.";

	private Point startPoint;
	private Point endPoint;
	private List<Point> points;
	
	protected Line(Drawing canvas){
		super(canvas);
		
		this.points = new ArrayList<Point>();
	}
	
	public Line(Point startPoint, Point endPoint, Drawing canvas) {
		super(canvas);
		
		this.startPoint = startPoint;
		this.endPoint = endPoint;
		
		this.points = new ArrayList<Point>();
		
		if (isCorrect())
			createPoints();
		else 
			throw new CompletionException(new Throwable(Line.ERROR_BAD_LINE_POINTS));
	}
	
	@Override
	public void draw(){
		if (isCorrect()) 
			if (getPoints().isEmpty())
				if (createPoints())
					drawLine();
				else
					throw new CompletionException(new Throwable(Line.ERROR_BAD_LINE_POINTS));
			else
				drawLine();
		else 
			throw new CompletionException(new Throwable(Line.ERROR_BAD_LINE_POINTS));
	}
	
	@Override
	public void erase(){
		eraseLine();
	}
	
	public boolean isCorrect() {
		if (getStartPoint().equals(null) || getEndPoint().equals(null))
			return false;
		
		if (getStartPoint().equals(getEndPoint()))
		{
			return false;
		}
		
		return true;
	}

	private boolean createPoints() {
		try {

			int deltaX = getEndPoint().getX_coord() - getStartPoint().getX_coord();
			int deltaY = getEndPoint().getY_coord() - getStartPoint().getY_coord();

			boolean zeroSlope = (deltaY == 0);			// okay b/c using integers which can be exactly expressed
			boolean infiniteSlope = (deltaX == 0);		// okay b/c using integers which can be exactly expressed

			if (infiniteSlope) { 	// vertical line
				if (deltaY > 0) {			// points North
					for (int y = getStartPoint().getY_coord(); y <= getEndPoint().getY_coord(); y++) {
						this.points.add(new Point(getStartPoint().getX_coord(), y, getCanvas()));
					}
				}
				else {						// points South
					for (int y = getStartPoint().getY_coord(); y >= getEndPoint().getY_coord(); y--) {
						this.points.add(new Point(getStartPoint().getX_coord(), y, getCanvas()));
					}
				}
			} 
			else {					// non-vertical lines
				int y = getStartPoint().getY_coord();
				double slope = getSlope(deltaX, deltaY);
				
				if (deltaX > 0) {				// direction East
					for (int x = getStartPoint().getX_coord(); x <= getEndPoint().getX_coord(); x++) {
						if (!zeroSlope) {				// NOT horizontal line
							y = (int) ((x -  getStartPoint().getX_coord())* slope + getStartPoint().getY_coord());	
						}
						this.points.add(new Point(x, y, getCanvas()));
					}
				}
				else {							// direction West
					for (int x = getStartPoint().getX_coord(); x >= getEndPoint().getX_coord(); x--) {
						if (!zeroSlope) {				// NOT horizontal line
							y = (int) ((x -  getStartPoint().getX_coord())* slope + getStartPoint().getY_coord());
						}
						this.points.add(new Point(x, y, getCanvas()));
					}
				}
			}
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	private void drawLine() {
		try {
			for (Point p : this.points) {
				p.draw();
			}
		} catch (Exception e) {
			throw new CompletionException(new Throwable(Line.ERROR_BAD_LINE_POINTS));
		}
	}
	
	private void eraseLine() {
		try {
			for (Point p : this.points) {
				p.erase();
			}
		} catch (Exception e) {
			throw new CompletionException(new Throwable(Line.ERROR_BAD_LINE_POINTS));
		}
	}
	
	private double getSlope(int deltaX, int deltaY) {

		if (deltaY == 0) {
			return 0.0;
		}

		if (deltaX == 0) {
			throw new NumberFormatException("Infinite slope.");
		}

		return (double)deltaY / (double)deltaX;
	}

	public Point getStartPoint() {
		return startPoint;
	}

	public Point getEndPoint() {
		return endPoint;
	}

	protected List<Point> getPoints() {
		return points;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((endPoint == null) ? 0 : endPoint.hashCode());
		result = prime * result + ((points == null) ? 0 : points.hashCode());
		result = prime * result + ((startPoint == null) ? 0 : startPoint.hashCode());
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
		if (!(obj instanceof Line)) {
			return false;
		}
		Line other = (Line) obj;
		if (endPoint == null) {
			if (other.endPoint != null) {
				return false;
			}
		} else if (!endPoint.equals(other.endPoint)) {
			return false;
		}
		if (points == null) {
			if (other.points != null) {
				return false;
			}
		} else if (!points.equals(other.points)) {
			return false;
		}
		if (startPoint == null) {
			if (other.startPoint != null) {
				return false;
			}
		} else if (!startPoint.equals(other.startPoint)) {
			return false;
		}
		return true;
	}

	@Override
	public boolean isDrawn() {
		// TODO Auto-generated method stub
		return false;
	}

	
}
