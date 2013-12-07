package com.lenis0012.tenjava;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.vehicle.VehicleExitEvent;

public class CoreListener implements Listener {
	
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event) {
		Player player = event.getPlayer();
		CorePlayerConnection.hook(player);
	}
	
	@EventHandler
	public void onVehicleExit(VehicleExitEvent event) {
		event.getVehicle().remove();
	}
}