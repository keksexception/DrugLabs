package de.raffi.druglabs.compability.v1_15_R1;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Particle.DustOptions;
import org.bukkit.craftbukkit.v1_15_R1.CraftParticle;
import org.bukkit.craftbukkit.v1_15_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;

import net.minecraft.server.v1_15_R1.PacketPlayOutWorldParticles;
import net.minecraft.server.v1_15_R1.ParticleParam;


public class ParticleSystem_v1_15_R1 {
	
	public static void sendParticle(Particle type, Location loc, float xOffset, float yOffset, float zOffset,
			float speed, int count) {
		DustOptions o = new DustOptions(Color.AQUA, 1);
		ParticleParam pa = CraftParticle.toNMS(type, o);
		float x = (float) loc.getX();
		float y = (float) loc.getY();
		float z = (float) loc.getZ();
		PacketPlayOutWorldParticles packet = new PacketPlayOutWorldParticles(pa, true, x, y, z, xOffset, zOffset, zOffset, speed, count);
		for (Player p : Bukkit.getOnlinePlayers()) {
				((CraftPlayer) p).getHandle().playerConnection.sendPacket(packet);			
			
		}
	}

	public static void sendParticle(Particle type, double x, double y, double z, float xOffset, float yOffset,
			float zOffset, float speed, int count) {
		float xf = (float) x;
		float yf = (float) y;
		float zf = (float) y;
		DustOptions o = new DustOptions(Color.AQUA, 1);
		ParticleParam pa = CraftParticle.toNMS(type, o);
		PacketPlayOutWorldParticles packet = new PacketPlayOutWorldParticles(pa, true, xf, yf, zf, xOffset, yOffset,
				zOffset, speed, count);
		for (Player p : Bukkit.getOnlinePlayers()) {
				((CraftPlayer) p).getHandle().playerConnection.sendPacket(packet);
		}
	}

	public static void sendParticleTo(Particle type, Player p, Location loc, float xOffset, float yOffset,
			float zOffset, float speed, int count) {
		float x = (float) loc.getX();
		float y = (float) loc.getY();
		float z = (float) loc.getZ();
		DustOptions o = new DustOptions(Color.AQUA, 1);
		ParticleParam pa = CraftParticle.toNMS(type, o);
		PacketPlayOutWorldParticles packet = new PacketPlayOutWorldParticles(pa, true, x, y, z, xOffset, yOffset,
				zOffset, speed, count);
		((CraftPlayer) p).getHandle().playerConnection.sendPacket(packet);
	}

	public static void sendParticleTo(Particle type, Player p, double x, double y, double z, float xOffset,
			float yOffset, float zOffset, float speed, int count) {
		float xf = (float) x;
		float yf = (float) y;
		float zf = (float) y;
		DustOptions o = new DustOptions(Color.AQUA, 1);
		ParticleParam pa = CraftParticle.toNMS(type, o);
		PacketPlayOutWorldParticles packet = new PacketPlayOutWorldParticles(pa, true, xf, yf, zf, xOffset, yOffset,
				zOffset, speed, count);
		((CraftPlayer) p).getHandle().playerConnection.sendPacket(packet);

	}

}
