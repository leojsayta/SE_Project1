package edu.unca.cl373.jatyas.flowChart;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletionException;

import csci348.drawings.Drawing;

public class Circle extends Element implements IDrawable, IFillable {
	
	private Point centerPoint;
	private int radius;
	private List<Point> borderPoints;
	private List<Circle> circles;
	private Boolean isFilled;
	protected boolean isDrawn;

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

	public int getRadius() {
		return radius;
	}

	protected List<Point> getBorderPoints() {
		return borderPoints;
	}

	protected List<Circle> getCircles() {
		return circles;
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

	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((borderPoints == null) ? 0 : borderPoints.hashCode());
		result = prime * result + ((centerPoint == null) ? 0 : centerPoint.hashCode());
		result = prime * result + ((circles == null) ? 0 : circles.hashCode());
		result = prime * result + radius;
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
		if (!(obj instanceof Circle)) {
			return false;
		}
		Circle other = (Circle) obj;
		if (centerPoint == null) {
			if (other.centerPoint != null) {
				return false;
			}
		} else if (!centerPoint.equals(other.centerPoint)) {
			return false;
		}
		if (radius != other.radius) {
			return false;
		}
		return true;
	}
}
