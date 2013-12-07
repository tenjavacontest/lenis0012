package com.lenis0012.tenjava;

import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_6_R3.CraftWorld;
import org.bukkit.entity.FallingBlock;
import org.bukkit.event.entity.CreatureSpawnEvent.SpawnReason;

import net.minecraft.server.v1_6_R3.EntityFallingBlock;

public class CustomBlock extends EntityFallingBlock {
	private Location loc;
	
	public CustomBlock(Location loc) {
		super(((CraftWorld) loc.getWorld()).getHandle(), loc.getX(), loc.getY(), loc.getZ(), 46, 0);
		this.loc = loc;
		Core.debug("Custom block created at " + loc.toString());
	}
	
	public FallingBlock spawn() {
		this.setPosition(loc.getX(), loc.getY(), loc.getZ());
		world.addEntity(this, SpawnReason.CUSTOM);
		return (FallingBlock) this.getBukkitEntity();
	}
	
	/**
	 * Called when the blokc updates.
	 */
	@Override
	public void l_() {
		this.motY = makePositive(this.motY);
	}
	
	/**
	 * Called when the block moves.
	 * Disable gravity.
	 */
	@Override
	public void move(double dx, double dy, double dz) {
		super.move(dx, makePositive(dy), dz);
	}
	
	/**
	 * Make a number positive
	 * 
	 * @param i Number
	 * @return Positive number
	 */
	private double makePositive(double i) {
		return Math.max(i, 0);
	}
}