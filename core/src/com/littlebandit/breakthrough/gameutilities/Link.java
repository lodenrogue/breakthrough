package com.littlebandit.breakthrough.gameutilities;

public interface Link {
	
	public void start();
	
	public boolean isReadyToStart();
	
	public void startNext(Link link);

}
