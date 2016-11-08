package edu.unca.cl373.jatyas.flowChart;

import java.util.ArrayList;
import java.util.Arrays;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;

import javax.swing.*;

import csci348.drawings.*;

public class Outline {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Drawing d = new SimpleDrawing();
		
		Point p0 = new Point(25, 25, d);
		Point p1 = new Point(75, 100, d);
		Point p2 = new Point(200, 225, d);
		Point p3 = new Point(300, 300, d);
		
		p0.draw();
		
		Line l1 = new Line(p1, p2, d);	// sloped line
		Line l2 = new Line(new Point(50, 50, d), new Point(50, 300, d), d);	// vertical line
		Line l3 = new Line(new Point(300, 400, d), new Point(100, 400, d), d);	// horizontal line
		
		l1.draw();
		l2.draw();
		l3.draw();
		
		Line lR = new Line(new Point(200,200,d), new Point(350, 200,d), d);
		Line lT = new Line(new Point(350,200,d), new Point(350, 350,d), d);
		Line lL = new Line(new Point(350,350,d), new Point(200, 350,d), d);
		Line lB = new Line(new Point(200,350,d), new Point(200, 200,d), d);
		
		ArrayList<Line> arr = new ArrayList<Line>(Arrays.asList(lR,lT,lL,lB));
		
		Polygon poly0 = new Polygon(arr, d);
		poly0.draw();
		
		Rectangle r = new Rectangle(new Point(100, 100, d), 200, 400, d);
		r.draw();
		r.fill();
		r.unFill();
		
		Circle c = new Circle(p3, 150, d);
		c.draw();
		c.fill();
		c.unFill();
	}
	
	public Boolean drawBox(int lng, int hght, int startX, int startY) {
		return true;
	}

}
