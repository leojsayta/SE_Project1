package edu.unca.cl373.jatyas.flowChart;

import java.util.Objects;

import csci348.drawings.Drawing;

public class Point extends Element {
	
	private int x_coord;
	private int y_coord;
	
	public Point(int x_Coordinate, int y_Coordinate, Drawing canvas) {
		super(canvas);
		this.x_coord = x_Coordinate;
		this.y_coord = y_Coordinate;
	}
	
	public Point(Point p, Drawing canvas) {
		super(canvas);
		this.x_coord = p.x_coord;
		this.y_coord = p.y_coord;
	}
	
	@Override
	public void draw() {
		getCanvas().showPoint(getX_coord(), getY_coord());
	}
	
	@Override
	public void erase() {
		getCanvas().hidePoint(getX_coord(), getY_coord());
	}

	public int getX_coord() {
		return x_coord;
	}

	public int getY_coord() {
		return y_coord;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + x_coord;
		result = prime * result + y_coord;
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
		if (!(obj instanceof Point)) {
			return false;
		}
		Point other = (Point) obj;
		if (x_coord != other.x_coord) {
			return false;
		}
		if (y_coord != other.y_coord) {
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
