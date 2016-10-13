package edu.unca.cl373.jatyas.flowChart;

import csci348.drawings.Drawing;

public class Outline {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Drawing d = new Drawing();
		
		Point p0 = new Point(25, 25, d);
		Point p1 = new Point(75, 100, d);
		Point p2 = new Point(200, 225, d);
		
		Line l1 = new Line(p1, p2, d);	// sloped line
		Line l2 = new Line(new Point(50, 50, d), new Point(50, 300, d), d);	// vertical line
		Line l3 = new Line(new Point(100, 400, d), new Point(300, 400, d), d);	// horizontal line
		
		p0.draw();
		l1.draw();
		l2.draw();
		l3.draw();
		
	}
	
	public Boolean drawBox(int lng, int hght, int startX, int startY) {
		return true;
	}

}
