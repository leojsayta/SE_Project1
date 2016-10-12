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
	
	public void draw() {
		if (!Objects.isNull(getCanvas())){
			getCanvas().showPoint(getX_coord(), getY_coord());
		}
	}
	
	public void erase() {
		if (!Objects.isNull(getCanvas())){
			getCanvas().hidePoint(getX_coord(), getY_coord());
		}
	}

	public int getX_coord() {
		return x_coord;
	}

	private void setX_coord(int x_coord) {
		this.x_coord = x_coord;
	}

	public int getY_coord() {
		return y_coord;
	}

	private void setY_coord(int y_coord) {
		this.y_coord = y_coord;
	}
}
