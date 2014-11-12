package com.littlebandit.breakthrough.entities.components.updatecomponents.blockcomponents;

import com.littlebandit.breakthrough.entities.Entity;
import com.littlebandit.breakthrough.entities.components.updatecomponents.UpdateComponent;
import com.littlebandit.breakthrough.gameutilities.managers.TextureManager;

public class BlockCollision implements UpdateComponent {
	private UpdateComponent destruction = new BlockDestruction();
	private boolean doDestruction = false;
	private int hitCount = 0;

	@Override
	public void update(Entity entity) {
		if(entity.isColliding()){
			hitCount++;
			
			if(hitCount == 1){
				entity.getSprite().setTexture(TextureManager.getTexture("block01"));
			}
			else if (hitCount == 2){
				entity.getSprite().setTexture(TextureManager.getTexture("block02"));
			}
			else if (hitCount == 3){
				doDestruction = true;
			}
			entity.setIsColliding(false);
		}
		
		if(doDestruction){
			destruction.update(entity);
		}

	}

}
