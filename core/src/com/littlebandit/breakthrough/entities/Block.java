package com.littlebandit.breakthrough.entities;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.littlebandit.breakthrough.entities.components.updatecomponents.blockcomponents.BlockUpdateComponent;

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
