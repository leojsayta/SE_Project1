package edu.unca.cl373.jatyas.flowChart;

import csci348.drawings.Drawing;

public class Box {
	
	private int start_X;
	private int start_Y;
	private int end_X;
	private int end_Y;
	
	private Line x1;
	private Line x2;
	private Line y1;
	private Line y2;
	
	public Box(int start_X, int start_Y, int end_X, int end_Y) {
		this.start_X = start_X;
		this.start_Y = start_Y;
		this.end_X = end_X;
		this.end_Y = end_Y;
	}

	public int getStart_X() {
		return start_X;
	}

	public void setStart_X(int start_X) {
		this.start_X = start_X;
	}

	public int getStart_Y() {
		return start_Y;
	}

	public void setStart_Y(int start_Y) {
		this.start_Y = start_Y;
	}

	public int getEnd_X() {
		return end_X;
	}

	public void setEnd_X(int end_X) {
		this.end_X = end_X;
	}

	public int getEnd_Y() {
		return end_Y;
	}

	public void setEnd_Y(int end_Y) {
		this.end_Y = end_Y;
	}
	
	
}
