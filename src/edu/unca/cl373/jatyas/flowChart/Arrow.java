package edu.unca.cl373.jatyas.flowChart;

import java.util.concurrent.CompletionException;

import csci348.drawings.Drawing;

public class Arrow extends Element implements IDrawable, IFillable {
	
	private boolean isDrawn;
	private boolean filled;
	private Triangle head;
	private Line shaft;
	private Point headPoint;
	private int shaftLength;
	private Direction dir;

	public Arrow(Point headEndPoint, int headSideLength, int shaftLength, Direction direction, Drawing canvas) {
		super(canvas);
		
		this.headPoint = new Point(headEndPoint, canvas);
		this.shaftLength = shaftLength;
		this.dir = direction;
		
		int heightHead = (int) Math.round( ((double)headSideLength / 2.0) * Triangle.SQ_ROOT_OF_THREE) ;
		int headBaseCenter_XCoord, headBaseCenter_YCoord, shaftEndPnt_XCoord, shaftEndPnt_YCoord;
		
		switch (direction) {
		case NORTH:
			headBaseCenter_YCoord = headEndPoint.getY_coord() + heightHead;
			headBaseCenter_XCoord = headEndPoint.getX_coord();
			shaftEndPnt_YCoord = headBaseCenter_YCoord + this.shaftLength;
			shaftEndPnt_XCoord = headEndPoint.getX_coord();;
			break;
		case SOUTH:
			headBaseCenter_YCoord = headEndPoint.getY_coord() - heightHead;
			headBaseCenter_XCoord = headEndPoint.getX_coord();
			shaftEndPnt_YCoord = headBaseCenter_YCoord - this.shaftLength;
			shaftEndPnt_XCoord = headEndPoint.getX_coord();
			break;
		case EAST:
			headBaseCenter_XCoord = headEndPoint.getX_coord() - heightHead;
			headBaseCenter_YCoord = headEndPoint.getY_coord();
			shaftEndPnt_XCoord = headBaseCenter_XCoord - this.shaftLength;
			shaftEndPnt_YCoord = headEndPoint.getY_coord();
			break;
		case WEST:
			headBaseCenter_XCoord = headEndPoint.getX_coord() + heightHead;
			headBaseCenter_YCoord = headEndPoint.getY_coord();
			shaftEndPnt_XCoord = headBaseCenter_XCoord + this.shaftLength;
			shaftEndPnt_YCoord = headEndPoint.getY_coord();
			break;
		default:
			headBaseCenter_XCoord = -1;	// necessary to keep IDE from having a fit
			headBaseCenter_YCoord = -1;
			shaftEndPnt_XCoord = -1;
			shaftEndPnt_YCoord = -1;
			break;
		}
				
		Point head_BaseCenterPt = new Point(headBaseCenter_XCoord, headBaseCenter_YCoord, canvas);
		Point shaft_EndPoint = new Point(shaftEndPnt_XCoord, shaftEndPnt_YCoord, canvas);
		this.head = new Triangle(head_BaseCenterPt, headSideLength, direction, canvas);
		this.shaft = new Line(head_BaseCenterPt, shaft_EndPoint, canvas);
	}

	@Override
	public boolean isFilled() {
		return this.filled;
	}

	@Override
	public void fill() throws CompletionException {
		if (!isFilled()) {
			this.head.fill();
			this.filled = true;
		}
	}

	@Override
	public void unFill() {
		if (isFilled()) {
			this.head.unFill();
			this.filled = false;
			if (isDrawn())
				draw();
		}
	}

	@Override
	public void draw() throws CompletionException {
		if (!isDrawn()) {
			this.head.draw();
			this.shaft.draw();
			this.isDrawn = true;
		}
	}

	@Override
	public void erase() {
		if(isDrawn()){
			this.head.erase();
			this.shaft.erase();
			this.isDrawn = false;
		}
	}

	@Override
	public boolean isDrawn() {
		return this.isDrawn;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dir == null) ? 0 : dir.hashCode());
		result = prime * result + ((headPoint == null) ? 0 : headPoint.hashCode());
		result = prime * result + shaftLength;
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
		if (!(obj instanceof Arrow)) {
			return false;
		}
		Arrow other = (Arrow) obj;
		if (dir != other.dir) {
			return false;
		}
		if (headPoint == null) {
			if (other.headPoint != null) {
				return false;
			}
		} else if (!headPoint.equals(other.headPoint)) {
			return false;
		}
		if (shaftLength != other.shaftLength) {
			return false;
		}
		return true;
	}


	

}
