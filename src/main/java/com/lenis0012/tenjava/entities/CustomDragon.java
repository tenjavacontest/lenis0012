package com.lenis0012.tenjava.entities;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.craftbukkit.v1_6_R3.CraftWorld;
import org.bukkit.entity.Minecart;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.CreatureSpawnEvent.SpawnReason;
import org.bukkit.util.Vector;

import com.lenis0012.tenjava.Core;

import net.minecraft.server.v1_6_R3.EntityEnderDragon;

public class CustomDragon extends EntityEnderDragon implements CustomEntity {

	public CustomDragon(World world) {
		super(((CraftWorld) world).getHandle());
	}
	
	public Minecart spawn(Location loc) {
		this.setPositionRotation(loc.getX(), loc.getY(), loc.getZ(), loc.getYaw(), 0F);
		world.addEntity(this, SpawnReason.CUSTOM);
		return (Minecart) this.getBukkitEntity();
	}
	
	@Override
	public void c() {
	}
	
	public void move(Player player, double x, double z) {
//		super.move(x, 0, z);
		if(x > 0 || z > 0) {
			Vector velocity = player.getLocation().getDirection().multiply(1.5);
			this.motX = velocity.getX();
			this.motZ = velocity.getZ();
			this.velocityChanged = true;
		}
	}
	
	public void jump() {
		Core.debug("Performing jump.");
		this.motY  = 0.7;
		this.velocityChanged = true;
	}
	
	public void sneak() {
		Core.debug("Performing sneak.");
		this.motY = -0.7;
		this.velocityChanged = true;
	}
	
	
	@Override
	public boolean isInvulnerable() {
		return true;
	}

	@Override
	public boolean onPlayerInput(Player player, float forward, float sideways,boolean jumping, boolean sneaking) {
		this.move(player, (double) forward, (double) sideways);
		if(jumping) {
			this.jump();
		} else if(sneaking) {
			this.sneak();
		}
		
		return true;
	}
}