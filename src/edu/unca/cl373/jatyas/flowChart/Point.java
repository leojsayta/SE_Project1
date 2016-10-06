package edu.unca.cl373.jatyas.flowChart;

class Point {
	private int x_coord;
	private int y_coord;
	
	protected Point(int x_Coordinate, int y_Coordinate) {
		this.x_coord = x_Coordinate;
		this.y_coord = y_Coordinate;
	}

	protected int getX_coord() {
		return x_coord;
	}

	protected void setX_coord(int x_coord) {
		this.x_coord = x_coord;
	}

	protected int getY_coord() {
		return y_coord;
	}

	protected void setY_coord(int y_coord) {
		this.y_coord = y_coord;
	}
}
