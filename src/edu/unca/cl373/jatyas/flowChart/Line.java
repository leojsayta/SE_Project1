package edu.unca.cl373.jatyas.flowChart;


import java.util.*;

import csci348.drawings.Drawing;

public class Line extends Element {
	
	private int start_X;
	private int start_Y;
	private int end_X;
	private int end_Y;
	
	private ArrayList<Point> points;
	
	public Line(int start_X, int start_Y, int end_X, int end_Y, Drawing canvas) {
		super(canvas);
		
		this.start_X = start_X;
		this.start_Y = start_Y;
		this.end_X = end_X;
		this.end_Y = end_Y;
		
		this.points = new ArrayList<Point>();
	}
	
	public void draw(){
		writeLine();
	}
	
	public void erase(){
		eraseLine();
	}
	
	private boolean writeLine() {
		try {
			
			double deltaX = getEnd_X() - getStart_X();
			double deltaY = getEnd_Y() - getStart_Y();
			
			boolean zeroSlope = (0.0 <= Math.abs(deltaY)) && (Math.abs(deltaY) < 0.001);
			boolean infiniteSlope = (0.0 <= Math.abs(deltaX)) && (Math.abs(deltaX) < 0.001);
			
			if (zeroSlope && infiniteSlope) {	// point
				Point p = new Point(getStart_X(), getStart_Y(), getCanvas());
				p.draw();
				this.points.add(p);
			}
			else if (zeroSlope) {	// horizontal line					
				for (int x = getStart_X(); x <= getEnd_X(); x++) {
					Point p = new Point(x, getStart_Y(), getCanvas());
					p.draw();
					this.points.add(p);
				}
			}
			else if (infiniteSlope) {	// vertical line
				for (int y = getStart_Y(); y <= getEnd_Y(); y++) {
					Point p = new Point(getStart_X(), y, getCanvas());
					p.draw();
					this.points.add(p);
				}
			}
			else {	// other lines
				int y = getStart_Y();
				double slope = getSlope(deltaX, deltaY);
				
				for (int x = getStart_X(); x <= getEnd_X(); x++) {
					y = (int) (x*slope + getStart_Y());
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

	public int getStart_X() {
		return start_X;
	}

	private void setStart_X(int start_X) {
		this.start_X = start_X;
	}

	public int getStart_Y() {
		return start_Y;
	}

	private void setStart_Y(int start_Y) {
		this.start_Y = start_Y;
	}

	public int getEnd_X() {
		return end_X;
	}

	private void setEnd_X(int end_X) {
		this.end_X = end_X;
	}

	public int getEnd_Y() {
		return end_Y;
	}

	private void setEnd_Y(int end_Y) {
		this.end_Y = end_Y;
	}

	
	
	

}
