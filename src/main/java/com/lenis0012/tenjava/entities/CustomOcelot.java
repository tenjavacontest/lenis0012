package com.lenis0012.tenjava.entities;

import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.craftbukkit.v1_6_R3.CraftWorld;
import org.bukkit.entity.Ocelot;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.CreatureSpawnEvent.SpawnReason;
import org.bukkit.util.Vector;

import com.lenis0012.tenjava.Core;
import com.lenis0012.tenjava.Utils;

import net.minecraft.server.v1_6_R3.EntityOcelot;

public class CustomOcelot extends EntityOcelot implements CustomEntity {

	public CustomOcelot(World world) {
		super(((CraftWorld) world).getHandle());
		Utils.clearPathfinding(goalSelector);
	}
	
	public Ocelot spawn(Location loc) {
		this.setPositionRotation(loc.getX(), loc.getY(), loc.getZ(), loc.getYaw(), 0F);
		world.addEntity(this, SpawnReason.CUSTOM);
		return (Ocelot) this.getBukkitEntity();
	}
	
	public void l_() {
		super.l_();
		
		if(this.passenger != null) {
			float pyaw = this.passenger.yaw;
			this.yaw = pyaw;
		} else
			this.die();
	}
	
	@Override
	public void move(double dx, double dy, double dz) {
		super.move(dx, dy, dz);
		
		if((dx > 0 || dz > 0) && Math.random() < 0.3) {
			world.getWorld().playEffect(this.getBukkitEntity().getLocation(), 
					Effect.MOBSPAWNER_FLAMES, 1);
			if(Math.random() > 0.95) {
				world.broadcastEntityEffect(this, (byte) 7);
			}
		}
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
		if(this.onGround) {
			Core.debug("Performing jump.");
			this.motY  = 1.0;
			this.velocityChanged = true;
		}
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
		} if(sneaking && this.onGround) {
			this.motY = 4;
			this.velocityChanged = true;
			world.getWorld().createExplosion(this.getBukkitEntity().getLocation(), 1);
		}
		
		return true;
	}
}