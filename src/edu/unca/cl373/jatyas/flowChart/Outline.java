package edu.unca.cl373.jatyas.flowChart;

import csci348.drawings.Drawing;

public class Outline {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Drawing d = new Drawing();
		Line l1 = Line.drawLine(75, 100, 75, 100, d);	// point
		Line l2 = Line.drawLine(50, 50, 50, 300, d);	// vertical line
		Line l3 = Line.drawLine(100, 400, 300, 400, d);	// horizontal line
		

	}
	
	public Boolean drawBox(int lng, int hght, int startX, int startY) {
		return true;
	}

}
