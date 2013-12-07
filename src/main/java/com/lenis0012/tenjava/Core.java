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
	private static Core instance;
	private static Logger logger;
	
	private static void setInstance(Core core) {
		instance = core;
	}
	
	private static void setLogger(Logger log) {
		logger = log;
	}
	
	public static final Core getInstance() {
		return instance;
	}
	
	@Override
	public void onEnable() {
		try {
			long beginTime = System.currentTimeMillis();
			setInstance(this);
			setLogger(this.getLogger());
			
			info("Loading utils class...");
			Utils.load();
			
			info("Loading listeners...");
			PluginManager pm = this.getServer().getPluginManager();
			pm.registerEvents(new CoreListener(), this);
			
			long duration = System.currentTimeMillis() - beginTime;
			info("Plugin was successfully enabled (took " + duration + " ms)");
		} catch(Throwable t) {
			severe("Plugin faled to enable", t);
			setEnabled(false);
		}
	}
	
	@Override
	public void onDisable() {
		setInstance(null);
	}
	
	/**
	 * Send a debug message to the log if enabled.
	 * 
	 * @param msg Message to debug.
	 */
	public static void debug(String msg) {
		if(DEBUG_ENABLED) {
			info("[DEBUG] " + msg);
		}
	}
	
	/**
	 * Send a severe message to the log.
	 * 
	 * @param msg Message to send.
	 */
	public static void severe(String msg) {
		logger.log(Level.SEVERE, msg);
	}
	
	/**
	 * Send a severe message to the log.
	 * 
	 * @param msg Message to send.
	 */
	public static void severe(String msg, Throwable t) {
		logger.log(Level.SEVERE, msg, t);
	}
	
	/**
	 * Send an info message to the log.
	 * 
	 * @param msg Message to send.
	 */
	public static void info(String msg) {
		logger.log(Level.INFO, msg);
	}
}