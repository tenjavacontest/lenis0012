package com.lenis0012.tenjava.entities;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.craftbukkit.v1_6_R3.CraftWorld;
import org.bukkit.entity.Minecart;
import org.bukkit.event.entity.CreatureSpawnEvent.SpawnReason;

import net.minecraft.server.v1_6_R3.EntityMinecartRideable;

public class CustomTank extends EntityMinecartRideable implements CustomEntity {

	public CustomTank(World world) {
		super(((CraftWorld) world).getHandle());
	}
	
	public Minecart spawn(Location loc) {
		this.setPositionRotation(loc.getX(), loc.getY(), loc.getZ(), loc.getYaw(), loc.getPitch());
		world.addEntity(this, SpawnReason.CUSTOM);
		return (Minecart) this.getBukkitEntity();
	}
	
	public void move(double x, double z) {
		super.move(x, 0, z);
	}
	
	public void jump() {
		if(this.onGround) {
			this.motY  = 1.0;
		}
	}
	
	
	@Override
	public boolean isInvulnerable() {
		return true;
	}

	@Override
	public boolean onPlayerInput(float forward, float sideways,boolean jumping, boolean sneaking) {
		this.move((double) forward, (double) sideways);
		if(jumping) {
			this.jump();
		}
		
		return true;
	}
}