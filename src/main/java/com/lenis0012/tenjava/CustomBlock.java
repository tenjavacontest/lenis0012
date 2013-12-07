package com.lenis0012.tenjava;

import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_6_R3.CraftWorld;
import org.bukkit.entity.FallingBlock;
import org.bukkit.event.entity.CreatureSpawnEvent.SpawnReason;

import net.minecraft.server.v1_6_R3.EntityFallingBlock;

public class CustomBlock extends EntityFallingBlock {
	private static final double GRAVITY = 7.999999821186065E-4D;
	private Location loc;
	private long lastTick;
	
	public CustomBlock(Location loc) {
		super(((CraftWorld) loc.getWorld()).getHandle(), loc.getX(), loc.getY(), loc.getZ(), 46, 0);
		this.loc = loc;
		this.lastTick = System.currentTimeMillis();
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
		super.l_();
		
		long time = System.currentTimeMillis();
		double gravity = (time - lastTick) * GRAVITY;
		this.motY += gravity;
	}
}