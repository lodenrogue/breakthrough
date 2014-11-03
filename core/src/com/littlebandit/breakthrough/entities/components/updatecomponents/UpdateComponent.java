package com.littlebandit.breakthrough.entities.components.updatecomponents;

import com.littlebandit.breakthrough.entities.Entity;

/**
 * Interface representing the update component for objects with need for logic.
 * 
 * @author Miguel Hernandez
 *
 */
public interface UpdateComponent {

	public void update(Entity entity);
}