package edu.unca.cl373.jatyas.flowChart;


import java.util.*;

import csci348.drawings.Drawing;

public class Line extends Element {
	
	private Point startPoint;
	private Point endPoint;
	
	private ArrayList<Point> points;
	
	public Line(Point startPoint, Point endPoint, Drawing canvas) {
		super(canvas);
		
		this.startPoint = startPoint;
		this.endPoint = endPoint;
		
		this.points = new ArrayList<Point>();
	}
	
	@Override
	public void draw(){
		if (isCorrect()) writeLine();
	}
	
	@Override
	public void erase(){
		eraseLine();
	}
	
	private boolean writeLine() {
		try {
			
			double deltaX = getEndPoint().getX_coord() - getStartPoint().getX_coord();
			double deltaY = getEndPoint().getY_coord() - getStartPoint().getY_coord();
			
			boolean zeroSlope = (0.0 <= Math.abs(deltaY)) && (Math.abs(deltaY) < 0.001);
			boolean infiniteSlope = (0.0 <= Math.abs(deltaX)) && (Math.abs(deltaX) < 0.001);
			
			if (zeroSlope) {	// horizontal line					
				for (int x = getStartPoint().getX_coord(); x <= getEndPoint().getX_coord(); x++) {
					Point p = new Point(x, getStartPoint().getY_coord(), getCanvas());
					p.draw();
					this.points.add(p);
				}
			}
			else if (infiniteSlope) {	// vertical line
				for (int y = getStartPoint().getY_coord(); y <= getEndPoint().getY_coord(); y++) {
					Point p = new Point(getStartPoint().getX_coord(), y, getCanvas());
					p.draw();
					this.points.add(p);
				}
			}
			else {	// other lines
				int y = getStartPoint().getY_coord();
				double slope = getSlope(deltaX, deltaY);
				
				for (int x = getStartPoint().getX_coord(); x <= getEndPoint().getX_coord(); x++) {
					y = (int) (x*slope + getStartPoint().getY_coord());
					Point p = new Point(x, y, getCanvas());
					p.draw();
					this.points.add(p);
				}
			}
			
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	private boolean eraseLine() {
		try {
			for (Point p : this.points) {
				p.erase();
			}

			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	private double getSlope(double deltaX, double deltaY) {

		if (0.0 < Math.abs(deltaY) && Math.abs(deltaY) < 0.001) {
			return 0.0;
		}

		if (0.0 < Math.abs(deltaX) && Math.abs(deltaX) < 0.001) {
			throw new NumberFormatException("Infinite slope.");
		}

		return deltaY / deltaX;
	}

	public Point getStartPoint() {
		return startPoint;
	}

	private void setStartPoint(Point start) {
		this.startPoint = start;
	}

	public Point getEndPoint() {
		return endPoint;
	}

	private void setEndPoint(Point end) {
		this.endPoint = end;
	}

	public ArrayList<Point> getPoints() {
		return points;
	}

	private void setPoints(ArrayList<Point> points) {
		this.points = points;
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
	public boolean isCorrect() {
		if (getStartPoint().equals(null) || getEndPoint().equals(null))
			return false;
		
		if (getStartPoint().equals(getEndPoint()))
		{
			return false;
		}
		
		return true;
	}

}
