package com.lenis0012.tenjava.entities;

import org.bukkit.entity.Player;

public interface CustomEntity {
	
	/**
	 * Called whenever a player presses an input button in this entity.
	 * 
	 * @param player Player pressing keys.
	 * @param forward Forward movement
	 * @param sideways Sideways movement
	 * @param jumping Holding spacebar?
	 * @param sneaking Holding shift?
	 * @return Precent from sending to server publicly?
	 */
	public boolean onPlayerInput(Player player, float forward, float sideways, boolean jumping, boolean sneaking);
}