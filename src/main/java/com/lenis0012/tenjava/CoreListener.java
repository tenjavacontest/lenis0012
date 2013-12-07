package com.lenis0012.tenjava;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.FallingBlock;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

public class CoreListener implements Listener {
	
	/**
	 * Block velocity testing.
	 * Will be improved.
	 * 
	 * @param event
	 */
	@EventHandler
	public void onPlaterInteract(PlayerInteractEvent event) {
		Player player = event.getPlayer();
		Block block = event.getClickedBlock();
		ItemStack item = player.getItemInHand();
		if(item != null && block != null) {
			if(item.getType() == Material.BLAZE_ROD && block.getType() == Material.PISTON_BASE) {
				Location loc = block.getLocation().add(0, 2, 0);
//				FallingBlock fblock = world.spawnFallingBlock(loc, 46, (byte) 0);
				CustomBlock cblock = new CustomBlock(loc);
				FallingBlock fblock = cblock.spawn();
				Vector velocity = new Vector(0.2, 2, 0);
				fblock.setVelocity(velocity);
				player.sendMessage("The shizzle was launched!");
				Core.debug("A falling entity was launched!");
			}
		}
	}
}