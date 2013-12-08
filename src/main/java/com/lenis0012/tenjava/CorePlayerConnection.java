package com.lenis0012.tenjava;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import com.lenis0012.tenjava.entities.CustomEntity;

import net.minecraft.server.v1_6_R3.EntityPlayer;
import net.minecraft.server.v1_6_R3.INetworkManager;
import net.minecraft.server.v1_6_R3.MinecraftServer;
import net.minecraft.server.v1_6_R3.Packet27PlayerInput;
import net.minecraft.server.v1_6_R3.PlayerConnection;

public class CorePlayerConnection extends PlayerConnection {
	private Player bPlayer;
	
	public CorePlayerConnection(MinecraftServer minecraftserver, INetworkManager inetworkmanager, EntityPlayer entityplayer) {
		super(minecraftserver, inetworkmanager, entityplayer);
		this.bPlayer = player.getBukkitEntity();
	}

	public static void hook(Player player) {
		MinecraftServer server = Utils.getMCServer();
		EntityPlayer eplayer = Utils.getNMSPlayer(player);
		PlayerConnection oldConnection = eplayer.playerConnection;
		INetworkManager networkManager = oldConnection.networkManager;
		PlayerConnection newConnection = new CorePlayerConnection(server, networkManager, eplayer);
		eplayer.playerConnection = newConnection;
		Utils.replacePlayerConnection(oldConnection, newConnection);
		Core.debug("Player connection for player " + player.getName() + " was hooked.");
	}
	
	public static void unhook(Player player) {
		MinecraftServer server = Utils.getMCServer();
		EntityPlayer eplayer = Utils.getNMSPlayer(player);
		PlayerConnection oldConnection = eplayer.playerConnection;
		INetworkManager networkManager = oldConnection.networkManager;
		PlayerConnection newConnection = new PlayerConnection(server, networkManager, eplayer);
		eplayer.playerConnection = newConnection;
		Utils.replacePlayerConnection(oldConnection, newConnection);
		Core.debug("Player connection for player " + player.getName() + " was unhooked.");
	}

	@Override
	public void a(Packet27PlayerInput packet) {
		Entity vehicle = bPlayer.getVehicle();
		if(vehicle != null) {
			Object nmsEntity = Utils.getNMSEntity(vehicle);
			if(nmsEntity instanceof CustomEntity) {
				Core.debug("Received an input packet from " + bPlayer.getName());
				CustomEntity cEntity  = (CustomEntity) nmsEntity;
				float forward = Utils.InputPacket.getForward(packet);
				float sideways = Utils.InputPacket.getSideways(packet);
				boolean jump = Utils.InputPacket.getJump(packet);
				boolean unmount = Utils.InputPacket.getUnmount(packet);
				if(cEntity.onPlayerInput(bPlayer, forward, sideways, jump, unmount)) {
					return;
				}
			}
		}
		
		super.a(packet);
	}
}