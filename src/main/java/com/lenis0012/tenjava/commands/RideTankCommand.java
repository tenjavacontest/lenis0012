package com.lenis0012.tenjava.commands;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.lenis0012.tenjava.Core;
import com.lenis0012.tenjava.entities.CustomTank;

public class RideTankCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(!(sender instanceof Player)) {
			sender.sendMessage("You trying to break my plugin????? SO MEAN!!!");
			return true;
		} 
		
		Core.debug("Command executed, spawning a tank.");
		Player player = (Player) sender;
		Location loc = player.getLocation();
		CustomTank tank = new CustomTank(player.getWorld());
		tank.spawn(loc);
		tank.getBukkitEntity().setPassenger(player);
		player.sendMessage("\247aEntering a tank!");
		
		return true;
	}
}