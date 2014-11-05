package com.littlebandit.breakthrough.entities.playstate;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.littlebandit.breakthrough.entities.SimpleEntity;
import com.littlebandit.breakthrough.entities.playstate.components.updatecomponents.blockcomponents.BlockUpdateComponent;

/**
 * Block entity.
 * 
 * @author Miguel Hernandez
 *
 */
public class Block extends SimpleEntity {

	public Block(String id, Sprite sprite, float x, float y) {
		super(id, sprite, x, y);
		setUpdateComponent(new BlockUpdateComponent());
	}

	@Override
	public void disposeAll() {

	}

}
