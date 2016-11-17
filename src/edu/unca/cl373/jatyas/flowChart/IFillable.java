package edu.unca.cl373.jatyas.flowChart;

import java.util.concurrent.CompletionException;

public interface IFillable {
	
	public boolean isFilled();
	public void fill() throws CompletionException;
	public void unFill();
}
