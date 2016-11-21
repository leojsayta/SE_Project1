package edu.unca.cl373.jatyas.flowChart;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletionException;

import csci348.drawings.Drawing;

public class Diamond extends Element implements IFillable {
	
	private int sideLength;
	private Point centerPoint;
	private boolean isDrawn;
	private boolean filled;
	private Triangle top, bottom;

	public Diamond(Point centerPoint, int sideLength, Drawing canvas) {
		super(canvas);
		this.centerPoint = centerPoint;
		this.sideLength = sideLength;
			
		this.top = new Triangle(centerPoint, sideLength, Direction.NORTH, canvas);
		this.bottom = new Triangle(centerPoint, sideLength, Direction.SOUTH, canvas);
		
		this.isDrawn = false;
		this.filled = false;
	}

	public void draw() {
		this.top.draw();
		this.bottom.draw();
		
		this.top.getBaseSide().erase();
		this.bottom.getBaseSide().erase();

		this.isDrawn = true;
	}

	public void erase() {
		if (isDrawn()) {
			this.top.erase();
			this.bottom.erase();
		}
		
		this.isDrawn = false;
	}
	
	public boolean isDrawn() {
		return this.isDrawn;
	}

	@Override
	public boolean isFilled() {
		return filled;
	}

	@Override
	public void fill() {
		if (!isFilled()) {
			this.top.fill();
			this.bottom.fill();
			this.filled = true;
		}
	}

	@Override
	public void unFill() {
		if (isFilled()) {
			this.top.unFill();
			this.bottom.unFill();
			this.filled = false;
		}

		if (isDrawn())
			draw();
	}

	public int getSideLength() {
		return sideLength;
	}

	public Point centerPoint() {
		return centerPoint;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bottom == null) ? 0 : bottom.hashCode());
		result = prime * result + ((centerPoint == null) ? 0 : centerPoint.hashCode());
		result = prime * result + sideLength;
		result = prime * result + ((top == null) ? 0 : top.hashCode());
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
		if (!(obj instanceof Diamond)) {
			return false;
		}
		Diamond other = (Diamond) obj;
		if (bottom == null) {
			if (other.bottom != null) {
				return false;
			}
		} else if (!bottom.equals(other.bottom)) {
			return false;
		}
		if (centerPoint == null) {
			if (other.centerPoint != null) {
				return false;
			}
		} else if (!centerPoint.equals(other.centerPoint)) {
			return false;
		}
		if (sideLength != other.sideLength) {
			return false;
		}
		if (top == null) {
			if (other.top != null) {
				return false;
			}
		} else if (!top.equals(other.top)) {
			return false;
		}
		return true;
	}

}
