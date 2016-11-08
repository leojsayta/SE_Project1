package edu.unca.cl373.jatyas.flowChart;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletionException;

import csci348.drawings.Drawing;

public class Circle extends Element implements IFillable {
	
	private Point centerPoint;
	private int radius;
	private List<Point> borderPoints;
	private List<Circle> circles;
	private Boolean isFilled;
	protected boolean isDrawn;
	
	protected Circle() {
		// TODO Auto-generated constructor stub
	}

	public Circle(Drawing canvas) {
		super(canvas);
		// TODO Auto-generated constructor stub
	}
	
	public Circle(Point center, int radius, Drawing canvas) {
		super(canvas);
		this.centerPoint = center;
		this.radius = radius;
		this.borderPoints = new ArrayList<>();
		this.circles = new ArrayList<>();
		this.isFilled = false;
	}

	public Point getCenterPoint() {
		return centerPoint;
	}

	private void setCenterPoint(Point centerPoint) {
		this.centerPoint = centerPoint;
	}

	public int getRadius() {
		return radius;
	}

	private void setRadius(int radius) {
		this.radius = radius;
	}

	public List<Point> getBorderPoints() {
		return borderPoints;
	}

	private void setBorderPoints(List<Point> borderPoints) {
		this.borderPoints = borderPoints;
	}

	public List<Circle> getCircles() {
		return circles;
	}

	private void setCircles(List<Circle> circles) {
		this.circles = circles;
	}

	@Override
	public boolean isFilled() {
		return this.isFilled;
	}

	@Override
	public void fill() {
		if (!isFilled()) {
			if (getCircles().isEmpty())
				createFillCircles();
			
			for (Circle c : getCircles()) {
				c.draw();
			}
			this.isFilled = true;
		}
	}

	@Override
	public void unFill() {
		if (isFilled()) {
			for (Circle c : getCircles()) {
				c.erase();
			}
			this.isFilled = false;
		}
		
		if (isDrawn())
			draw();
	}

	@Override
	public void draw() throws CompletionException {
		drawCircle(getCenterPoint().getX_coord(), getCenterPoint().getY_coord(), getRadius(), getBorderPoints());
		for (Point p : getBorderPoints()) {
			p.draw();
		}
		
		this.isDrawn = true;
	}

	@Override
	public void erase() {
		for (Point p : getBorderPoints()) {
			p.erase();
		}

		this.isDrawn = false;
	}

	@Override
	public boolean isDrawn() {
		return this.isDrawn;
	}

	@Override
	public boolean isCorrect() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		return false;
	}

	// https://en.wikipedia.org/wiki/Midpoint_circle_algorithm#Algorithm
	private void drawCircle(int center_X, int center_Y, int radius, List<Point> circlePoints)
	{
	    int x = radius;
	    int y = 0;
	    int err = 0;

	    while (x >= y)
	    {
	    	circlePoints.add(new Point(center_X + x, center_Y + y, getCanvas()));
	    	circlePoints.add(new Point(center_X + y, center_Y + x, getCanvas()));
	    	circlePoints.add(new Point(center_X - y, center_Y + x, getCanvas()));
	    	circlePoints.add(new Point(center_X - x, center_Y + y, getCanvas()));
	    	circlePoints.add(new Point(center_X - x, center_Y - y, getCanvas()));
	    	circlePoints.add(new Point(center_X - y, center_Y - x, getCanvas()));
	    	circlePoints.add(new Point(center_X + y, center_Y - x, getCanvas()));
	    	circlePoints.add(new Point(center_X + x, center_Y - y, getCanvas()));

	        y += 1;
	        err += 1 + 2*y;
	        if (2*(err-x) + 1 > 0)
	        {
	            x -= 1;
	            err += 1 - 2*x;
	        }
	    }
	}
	
	private void createFillCircles() {
		Circle c;
		for (int r = 0; r <= getRadius(); r++) {
			c = new Circle(getCenterPoint(), r, getCanvas());
			getCircles().add(c);
			drawCircle(getCenterPoint().getX_coord(), getCenterPoint().getY_coord(), r, c.getBorderPoints());
		}
	}
}
