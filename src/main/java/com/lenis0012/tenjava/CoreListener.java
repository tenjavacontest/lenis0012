package com.lenis0012.tenjava;

import org.bukkit.Material;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Vehicle;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.vehicle.VehicleExitEvent;
import org.bukkit.inventory.ItemStack;

import com.lenis0012.tenjava.entities.CustomTank;

public class CoreListener implements Listener {
	
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event) {
		Player player = event.getPlayer();
		CorePlayerConnection.hook(player);
	}
	
	@EventHandler
	public void onVehicleExit(VehicleExitEvent event) {
		Vehicle vehicle = event.getVehicle();
		vehicle.remove();
		
		//Remove blaze rod (grenade launcher)
		//This is only called for the tanks
		if(Utils.getNMSEntity(vehicle) instanceof CustomTank) {
			LivingEntity passenger = event.getExited();
			if(passenger instanceof Player) {
				((Player) passenger).getInventory().remove(Material.BLAZE_ROD);
			}
		}
	}
	
	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent event) {
		Player player = event.getPlayer();
		ItemStack it = player.getItemInHand();
		if(it != null && (event.getAction() == Action.LEFT_CLICK_AIR ||
				event.getAction() == Action.LEFT_CLICK_BLOCK)) {
			Fireball fireball = player.launchProjectile(Fireball.class);
			fireball.setVelocity(fireball.getVelocity().multiply(2));
		}
	}
}