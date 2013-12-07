package com.lenis0012.tenjava;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;
import java.util.ListIterator;

import net.minecraft.server.v1_6_R3.Entity;
import net.minecraft.server.v1_6_R3.EntityPlayer;
import net.minecraft.server.v1_6_R3.MinecraftServer;
import net.minecraft.server.v1_6_R3.Packet27PlayerInput;
import net.minecraft.server.v1_6_R3.ServerConnection;

import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_6_R3.CraftServer;
import org.bukkit.craftbukkit.v1_6_R3.entity.CraftEntity;
import org.bukkit.craftbukkit.v1_6_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class Utils {
	private static final Method getServerConnection = getMethod(MinecraftServer.class, "ag");
	private static final Field getPlayerConnections = getField(ServerConnection.class, "c");
	private static List<Object> playerConnections;
	
	@SuppressWarnings("unchecked")
	public static void load() {
		Object serverConnection = invoke(getServerConnection, getMCServer());
		playerConnections = (List<Object>) getFieldValue(getPlayerConnections, serverConnection);
	}
	
	/**
	 * Let the server know we registered a new player connection.
	 * This is needed to fix some important bugs.
	 * 
	 * @param oldPlayerConnection Old player connection.
	 * @param newPlayerConnection New custom player connection
	 */
	public static void replacePlayerConnection(Object oldPlayerConnection, Object newPlayerConnection) {
		ListIterator<Object> it = playerConnections.listIterator();
		while(it.hasNext()) {
			Object next = it.next();
			if(next.equals(oldPlayerConnection)) {
				it.set(newPlayerConnection);
			}
		}
	}
	
	/**
	 * Access MinecraftServer.class
	 * 
	 * @return MinercraftServer instance
	 */
	public static MinecraftServer getMCServer() {
		return ((CraftServer) Bukkit.getServer()).getHandle().getServer();
	}
	
	/**
	 * Get NMS instance of a player.
	 * 
	 * @param player Player bukkit instance
	 * @return Player nms instance
	 */
	public static EntityPlayer getNMSPlayer(Player player) {
		return ((CraftPlayer) player).getHandle();
	}
	
	/**
	 * Get the nms instance of an entity.
	 * 
	 * @param entity Entity bukkit instance.
	 * @return Entity nms instance.
	 */
	public static Entity getNMSEntity(org.bukkit.entity.Entity entity) {
		return ((CraftEntity) entity).getHandle();
	}
	
	/**
	 * Use reflection to get data from a private field.
	 * 
	 * @param field Field to get data from.
	 * @param instance Class instance.
	 * @return Field data from class instance.
	 */
	@SuppressWarnings("unchecked")
	private static <T> T getFieldValue(Field field, Object instance) {
		try {
			return (T) field.get(instance);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * Invoke a method from a class.
	 * 
	 * @param method Method to invoke.
	 * @param instance Class instance.
	 * @param values Class type values to insert.
	 * @return Result from method.
	 */
	@SuppressWarnings("unchecked")
	private static <T> T invoke(Method method, Object instance, Object... values) {
		try {
			return (T) method.invoke(instance, values);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * Obtain an accessible field from a class by name.
	 * 
	 * @param gClass Class to obtain field from.
	 * @param name Field name.
	 * @return Obtained field.
	 */
	private static Field getField(Class<?> gClass, String name) {
		try {
			Field field = gClass.getDeclaredField(name);
			field.setAccessible(true);
			return field;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * Obtain an accessible method from a class.
	 * 
	 * @param gClass Class to obtain method from.
	 * @param name Name of method to obtain.
	 * @param types Class types of method.
	 * @return Obtained method.
	 */
	private static Method getMethod(Class<?> gClass, String name, Class<?>... types) {
		try {
			Method method = gClass.getDeclaredMethod(name, types);
			method.setAccessible(true);
			return method;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static class InputPacket {
		private static final Class<?> CLASS = Packet27PlayerInput.class;
		private static final Field forward = getField(CLASS, "a");
		private static final Field sideays = getField(CLASS, "b");
		private static final Field jump = getField(CLASS, "c");
		private static final Field unmount = getField(CLASS, "d");
		
		public static float getForward(Object instance) {
			return getFieldValue(forward, instance);
		}
		
		public static float getSideways(Object instance) {
			return getFieldValue(sideays, instance);
		}
		
		public static boolean getJump(Object instance) {
			return getFieldValue(jump, instance);
		}
		
		public static boolean getUnmount(Object instance) {
			return getFieldValue(unmount, instance);
		}
	}
}