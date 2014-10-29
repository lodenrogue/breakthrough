package com.littlebandit.breakthrough.entities.entityutilities;

import java.util.HashMap;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.littlebandit.breakthrough.entities.Entity;

/**
 * ArrayList extension. Contains capabilities to call entity update methods and
 * search/return entities by id.
 * 
 * @author Miguel Hernandez
 *
 */

public class EntityArrayList extends Array<Entity> {
	private HashMap<String, Entity> entityHashMap;

	public EntityArrayList() {
		super();
		entityHashMap = new HashMap<String, Entity>();
	}

	/**
	 * Appends the specified entity to the end of this list. Also places the
	 * entity in a hash map.
	 */

	@Override
	public void add(Entity e) {
		super.add(e);
		entityHashMap.put(e.getId(), e);
	}

	/**
	 * Calls the update method for each entity in the list.
	 */
	public void updateAll() {
		for (Entity e : this) {
			e.update();
		}
	}
	
	public void renderAll(SpriteBatch batch){
		for(Entity e : this){
			e.render(batch);
		}
	}

	/**
	 * Removes every entity from this list.
	 */
	public void removeAll() {
		removeAll();
		entityHashMap.clear();
	}

	/**
	 * Removes a specified entity from the list.
	 * 
	 * @param e Entity to be removed.
	 */
	public void removeEntity(Entity e) {
		removeValue(e, false);
		entityHashMap.remove(e.getId());
	}

	/**
	 * Returns an entity identified by it's id.
	 * 
	 * @param id Id of the target entity.
	 * @return Returns the entity associated with the id.
	 */
	public Entity getEntityById(String id) {
		return entityHashMap.get(id);
	}
}