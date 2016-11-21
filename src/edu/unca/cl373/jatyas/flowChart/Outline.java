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
		
		Point p0 = new Point(100, 100, d);
		Point p1 = new Point(200, 200, d);
		Point p2 = new Point(300, 300, d);
		Point p3 = new Point(400, 400, d);
		Point p4 = new Point(450, 450, d);
		
//		p0.draw();
//		
//		Line l1 = new Line(p1, p2, d);	// sloped line
//		Line l2 = new Line(new Point(50, 50, d), new Point(50, 300, d), d);	// vertical line
//		Line l3 = new Line(new Point(300, 400, d), new Point(100, 400, d), d);	// horizontal line
//		
//		l1.draw();
//		l2.draw();
//		l3.draw();
//		
//		Line lR = new Line(new Point(200,200,d), new Point(350, 200,d), d);
//		Line lT = new Line(new Point(350,200,d), new Point(350, 350,d), d);
//		Line lL = new Line(new Point(350,350,d), new Point(200, 350,d), d);
//		Line lB = new Line(new Point(200,350,d), new Point(200, 200,d), d);
//		
//		ArrayList<Line> arr = new ArrayList<Line>(Arrays.asList(lR,lT,lL,lB));
//		
//		Polygon poly0 = new Polygon(arr, d);
//		poly0.draw();
//		
//		Rectangle r = new Rectangle(new Point(100, 100, d), 200, 400, d);
//		r.draw();
//		r.fill();
//		r.unFill();
//		
//		Circle c = new Circle(p3, 150, d);
//		c.draw();
//		c.fill();
//		c.unFill();
		
//		Triangle t1 = new Triangle(p0, 80, Direction.EAST, d);
//		t1.draw();
//		t1.erase();
//		t1.fill();
//		t1.unFill();
//		
//		Triangle t2 = new Triangle(p1, 80, Direction.WEST, d);
//		t2.draw();
//		t2.erase();
//		t2.fill();
//		t2.unFill();
//		
//		Triangle t3 = new Triangle(p2, 80, Direction.NORTH, d);
//		t3.draw();
//		t3.erase();
//		t3.fill();
//		t3.unFill();
//		
//		Triangle t4 = new Triangle(p3, 80, Direction.SOUTH, d);
//		t4.draw();
//		t4.erase();
//		t4.fill();
//		t4.unFill();
		
//		Diamond dia = new Diamond(p3, 100, d);
//		dia.draw();
//		dia.erase();
//		dia.fill();
//		dia.unFill();
		
		Arrow a = new Arrow(p2, 50, 150, Direction.NORTH, d);
//		a.draw();
//		a.erase();
//		a.fill();
//		a.unFill();
	}
	
}
