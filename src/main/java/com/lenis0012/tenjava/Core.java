package com.lenis0012.tenjava;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Ten Java Contest.
 * Starting at: 4PM
 * 
 * @author lenis0012
 */
public class Core extends JavaPlugin {
	private static final boolean DEBUG_ENABLED = true;
	
	@Override
	public void onEnable() {
		PluginManager pm = this.getServer().getPluginManager();
		pm.registerEvents(new CoreListener(), this);
	}
	
	/**
	 * Send a debug message to the log if enabled.
	 * 
	 * @param msg Message to debug.
	 */
	public static void debug(String msg) {
		if(DEBUG_ENABLED) {
			Logger log = Logger.getLogger("Minecraft");
			log.log(Level.INFO, "[DEBUG] " + msg);
		}
	}
}