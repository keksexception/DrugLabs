package de.raffi.druglabs.compability.v1_8_R3;


import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

import net.minecraft.server.v1_8_R3.Packet;
import net.minecraft.server.v1_8_R3.PacketPlayOutEntityDestroy;

public class PacketAPI_v1_8_R3 {
	
	public PacketAPI_v1_8_R3() {
	}
	
	public static void sendPacket(CraftPlayer p, @SuppressWarnings("rawtypes") net.minecraft.server.v1_8_R3.Packet packet) {
		p.getHandle().playerConnection.sendPacket(packet);
	}
	public static void sendPacket(Player p, Packet<?> packet) {
		sendPacket((CraftPlayer)p, packet);
	}
	public static void sendPacketToAll(@SuppressWarnings("rawtypes") Packet packet) {
		for(Player all : Bukkit.getOnlinePlayers()) {
			sendPacket((CraftPlayer) all, packet);
		}
	}

	/**
	 * 
	 * @param p
	 * @param id entityID
	 */
	public static void destroyEntity(Player p, int id) {
		sendPacket((CraftPlayer) p, new PacketPlayOutEntityDestroy(id));
	}
	
}
