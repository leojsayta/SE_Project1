package edu.unca.cl373.jatyas.flowChart;

import java.util.concurrent.CompletionException;

public interface IDrawable {

	public void draw() throws CompletionException;
	public void erase();
	public boolean isDrawn();
}
