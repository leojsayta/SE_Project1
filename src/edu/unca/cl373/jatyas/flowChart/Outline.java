package edu.unca.cl373.jatyas.flowChart;

import java.util.ArrayList;
import java.util.Arrays;

import csci348.drawings.Drawing;

public class Outline {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Drawing d = new Drawing();
		
//		Point p0 = new Point(25, 25, d);
//		Point p1 = new Point(75, 100, d);
//		Point p2 = new Point(200, 225, d);
//		
//		p0.draw();
//		
//		Line l1 = new Line(p1, p2, d);	// sloped line
//		Line l2 = new Line(new Point(50, 50, d), new Point(50, 300, d), d);	// vertical line
		Line l3 = new Line(new Point(300, 400, d), new Point(100, 400, d), d);	// horizontal line
//		
//		l1.draw();
//		l2.draw();
		l3.draw();
		
//		Line lR = new Line(new Point(200,200,d), new Point(350, 200,d), d);
//		Line lT = new Line(new Point(350,200,d), new Point(350, 350,d), d);
//		Line lL = new Line(new Point(350,350,d), new Point(200, 350,d), d);
//		Line lB = new Line(new Point(200,350,d), new Point(200, 200,d), d);
//		
//		ArrayList<Line> arr = new ArrayList<Line>(Arrays.asList(lR,lT,lL,lB));
//		
//		Polygon poly0 = new Polygon(arr, d);
//		poly0.draw();
	}
	
	public Boolean drawBox(int lng, int hght, int startX, int startY) {
		return true;
	}

}
