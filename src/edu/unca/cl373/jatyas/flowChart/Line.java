package edu.unca.cl373.jatyas.flowChart;


import java.util.*;

import csci348.drawings.Drawing;

public class Line {
	
	private int start_X;
	private int start_Y;
	private int end_X;
	private int end_Y;
	
	private ArrayList<Point> points;
	
	private Drawing canvas;
	
	private Line(int start_X, int start_Y, int end_X, int end_Y, Drawing canvas) {
		this.start_X = start_X;
		this.start_Y = start_Y;
		this.end_X = end_X;
		this.end_Y = end_Y;
		
		this.canvas = canvas;
		
		this.points = new ArrayList<Point>();
		
		writeLine();
	}
	
	public static Line drawLine(int start_X, int start_Y, int end_X, int end_Y, Drawing canvas) {
		Line l = new Line(start_X, start_Y, end_X, end_Y, canvas);	
		return l;
	}
	
	private boolean writeLine() {
		try {
			
			double deltaX = this.end_X - this.start_X;
			double deltaY = this.end_Y - this.start_Y;
			
			boolean zeroSlope = (0.0 <= Math.abs(deltaY)) && (Math.abs(deltaY) < 0.001);
			boolean infiniteSlope = (0.0 <= Math.abs(deltaX)) && (Math.abs(deltaX) < 0.001);
			
			if (zeroSlope && infiniteSlope) {	// point
				this.canvas.showPoint(this.start_X, this.start_Y);
				this.points.add(new Point(this.start_X, this.start_Y));
			}
			else if (zeroSlope) {	// horizontal line					
				for (int x = this.start_X; x <= this.end_X; x++) {
					this.canvas.showPoint(x, this.start_Y);
				}
			}
			else if (infiniteSlope) {	// vertical line
				for (int y = this.start_Y; y <= this.end_Y; y++) {
					this.canvas.showPoint(this.start_X, y);
				}
			}
			else {	// other lines
				int y = this.start_Y;
				double slope = this.getSlope(deltaX, deltaY);
				
				for (int x = this.start_X; x <= this.end_X; x++) {
					y = (int) (x*slope + this.start_Y);
					this.canvas.showPoint(x, y);
				}
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

	protected int getStart_X() {
		return start_X;
	}

	protected void setStart_X(int start_X) {
		this.start_X = start_X;
	}

	protected int getStart_Y() {
		return start_Y;
	}

	protected void setStart_Y(int start_Y) {
		this.start_Y = start_Y;
	}

	protected int getEnd_X() {
		return end_X;
	}

	protected void setEnd_X(int end_X) {
		this.end_X = end_X;
	}

	protected int getEnd_Y() {
		return end_Y;
	}

	protected void setEnd_Y(int end_Y) {
		this.end_Y = end_Y;
	}
	
	

}
