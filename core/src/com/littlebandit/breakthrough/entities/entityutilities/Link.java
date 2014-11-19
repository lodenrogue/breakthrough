package com.littlebandit.breakthrough.entities.entityutilities;

public interface Link {
	
	public void start();
	
	public boolean isReadyToStart();
	
	public void startNextLink();
	
	public void addLink(Link link);
	
	public Link getLink();

}
