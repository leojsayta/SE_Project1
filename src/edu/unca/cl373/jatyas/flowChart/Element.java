package edu.unca.cl373.jatyas.flowChart;

import csci348.drawings.Drawing;

public abstract class Element {
	
	private Drawing canvas = null;
	
	public Element(Drawing canvas) {
		this.canvas = canvas;
	}
	
	public void draw(){};
	
	public void erase(){};
	
	public Drawing getCanvas() {
		return canvas;
	}

	private void setCanvas(Drawing canvas) {
		this.canvas = canvas;
	}
}
