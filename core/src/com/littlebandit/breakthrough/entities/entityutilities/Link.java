package com.littlebandit.breakthrough.entities.entityutilities;

public interface Link {
	
	public void start();
	
	public boolean isReadyToStart();
	
	public void startNext(Link link);

}
