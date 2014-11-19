package com.littlebandit.breakthrough.entities;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.littlebandit.breakthrough.entities.entityutilities.Link;

public class LinkEntity extends SimpleEntity implements Link {
	private boolean start = false;
	private Link nextLink;

	public LinkEntity(String id, Sprite sprite, float x, float y) {
		super(id, sprite, x, y);
	}

	@Override
	public void start() {
		start = true;

	}

	@Override
	public void startNextLink() {
		start = false;
		if (nextLink != null) {
			nextLink.start();
		}
	}

	@Override
	public boolean isReadyToStart() {
		return start;
	}

	@Override
	public void addLink(Link link) {
		nextLink = link;
		
	}

	@Override
	public Link getLink() {
		return nextLink;
	}
	
	@Override
	public void disposeAll() {
		// TODO Auto-generated method stub

	}

}
