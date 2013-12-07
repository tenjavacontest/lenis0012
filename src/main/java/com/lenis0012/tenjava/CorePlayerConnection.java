package com.lenis0012.tenjava;

import org.bukkit.entity.Player;

import net.minecraft.server.v1_6_R3.EntityPlayer;
import net.minecraft.server.v1_6_R3.INetworkManager;
import net.minecraft.server.v1_6_R3.MinecraftServer;
import net.minecraft.server.v1_6_R3.Packet27PlayerInput;
import net.minecraft.server.v1_6_R3.PlayerConnection;

public class CorePlayerConnection extends PlayerConnection {
	
	public CorePlayerConnection(MinecraftServer minecraftserver, INetworkManager inetworkmanager, EntityPlayer entityplayer) {
		super(minecraftserver, inetworkmanager, entityplayer);
	}

	public static void hook(Player player) {
		MinecraftServer server = Utils.getMCServer();
		EntityPlayer eplayer = Utils.getNMSPlayer(player);
		PlayerConnection oldConnection = eplayer.playerConnection;
		INetworkManager networkManager = oldConnection.networkManager;
		PlayerConnection newConnection = new CorePlayerConnection(server, networkManager, eplayer);
		Utils.replacePlayerConnection(oldConnection, newConnection);
	}

	public void a(Packet27PlayerInput packet) {
		
	}
}