package edu.unca.cl373.jatyas.flowChart;

import java.util.*;

import csci348.drawings.Drawing;

public class Polygon extends Element {
	
	private List<Line> polyLines;
	
	public static final int MinSideNumber = 3; 

	public Polygon(List<Line> lines, Drawing canvas) {
		super(canvas);
		this.polyLines = lines;
	}
	
	@Override
	public boolean draw() {
		if (!isCorrect())
			return false;
		
		for (Line pLine : getPolyLines()) {
			pLine.draw();
		}
		
		return true;
	}

	@Override
	public boolean erase() {
		for (Line pLine : getPolyLines()) {
			pLine.erase();
		}
		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((polyLines == null) ? 0 : polyLines.hashCode());
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
		if (!(obj instanceof Polygon)) {
			return false;
		}
		Polygon other = (Polygon) obj;
		if (polyLines == null) {
			if (other.polyLines != null) {
				return false;
			}
		} else if (!polyLines.equals(other.polyLines)) {
			return false;
		}
		return true;
	}

	public List<Line> getPolyLines() {
		return polyLines;
	}

	private void setPolyLines(List<Line> polyLines) {
		this.polyLines = polyLines;
	}

	@Override
	public boolean isCorrect() {
		
		List<Line> pLines = getPolyLines();
		int numLines = pLines.size();
		
		if (pLines.isEmpty() || (numLines < Polygon.MinSideNumber))
			return false;
		
		for (int i = 0; i < numLines - 1; i++) {
			if (!(pLines.get(i).isCorrect() && pLines.get(i + 1).isCorrect()))
				return false;
			if (!(pLines.get(i).getEndPoint().equals(pLines.get(i + 1).getStartPoint())))
				return false;
		}
		
		if (!pLines.get(0).getStartPoint().equals(pLines.get(numLines - 1).getEndPoint()))
			return false;
		
		return true;
	}

}
