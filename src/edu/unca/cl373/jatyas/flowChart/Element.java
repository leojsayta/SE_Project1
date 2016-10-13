package edu.unca.cl373.jatyas.flowChart;

import csci348.drawings.Drawing;

public abstract class Element {
	
	private Drawing canvas = null;
	
	protected Element() {}
	
	protected Element(Drawing canvas) {
		this.canvas = canvas;
	}
	
	public abstract void draw();
	
	public abstract void erase();
	
	public abstract boolean isCorrect();
	
	public Drawing getCanvas() {
		return canvas;
	}

	private void setCanvas(Drawing canvas) {
		this.canvas = canvas;
	}

	@Override
	public abstract int hashCode();

	@Override
	public abstract boolean equals(Object obj);
	
}


