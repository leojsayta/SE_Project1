package edu.unca.cl373.jatyas.flowChart;

import java.util.Objects;
import java.util.concurrent.CompletionException;

import csci348.drawings.Drawing;
import sun.net.www.ApplicationLaunchException;

public abstract class Element implements IDrawable {
	
	public static final String ERROR_DRAWING_CANVAS_IS_NULL = "The drawing canvas does not exist.";
	
	private Drawing canvas = null;
	
	protected Element() {}
	
	protected Element(Drawing canvas) {
		if (!Objects.isNull(getCanvas())){
			throw new NullPointerException(Element.ERROR_DRAWING_CANVAS_IS_NULL);
		}
		
		this.canvas = canvas;
	}
	
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


