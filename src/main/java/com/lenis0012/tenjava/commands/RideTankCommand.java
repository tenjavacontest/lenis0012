package com.lenis0012.tenjava.commands;

import java.util.Arrays;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

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
		if(!player.isInsideVehicle()) {
			Location loc = player.getLocation();
			CustomTank tank = new CustomTank(player.getWorld());
			tank.spawn(loc);
			tank.getBukkitEntity().setPassenger(player);
			player.sendMessage("\247aEntering a tank!");
			ItemStack it = new ItemStack(Material.BLAZE_ROD, 1);
			ItemMeta meta = it.getItemMeta();
			meta.setDisplayName("\247eGrenade launcher");
			meta.setLore(Arrays.asList("\2477Left click to shoot a grenade with your tank."));
			it.setItemMeta(meta);
			player.setItemInHand(it);
		} else {
			player.leaveVehicle();
			player.sendMessage("\247aLeft your vehicle!");
		}
		
		return true;
	}
}