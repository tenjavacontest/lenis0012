package com.lenis0012.tenjava.commands;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import com.lenis0012.tenjava.Core;
import com.lenis0012.tenjava.entities.CustomDragon;

public class RideDragonCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(!(sender instanceof Player)) {
			sender.sendMessage("You trying to break my plugin????? SO MEAN!!!");
			return true;
		} 
		
		Core.debug("Command executed, spawning a dragon.");
		Player player = (Player) sender;
		if(!player.isInsideVehicle()) {
			Location loc = player.getLocation();
			CustomDragon dragon = new CustomDragon(player.getWorld());
			dragon.spawn(loc);
			dragon.getBukkitEntity().setPassenger(player);
			player.sendMessage("\247aEntering a dragon!");
		} else {
			Entity entity = player.getVehicle();
			player.leaveVehicle();
			player.sendMessage("\247aLeft your vehicle!");
			entity.remove();
		}
		
		return true;
	}
}