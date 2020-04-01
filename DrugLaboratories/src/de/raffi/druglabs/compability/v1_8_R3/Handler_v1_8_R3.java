package de.raffi.druglabs.compability.v1_8_R3;

import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_8_R3.CraftWorld;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import de.raffi.druglabs.compability.SoundHandler;
import de.raffi.druglabs.compability.Versionhandler;
import net.minecraft.server.v1_8_R3.EntityZombie;
import net.minecraft.server.v1_8_R3.EnumParticle;
import net.minecraft.server.v1_8_R3.PacketPlayOutSpawnEntityLiving;
import net.minecraft.server.v1_8_R3.PacketPlayOutWorldParticles;
import net.minecraft.server.v1_8_R3.WorldServer;

public class Handler_v1_8_R3 implements Versionhandler{


	@Override
	public int sendZombiePacket(Player p, Location loc) {
		WorldServer s = ((CraftWorld)p.getWorld()).getHandle();
		EntityZombie zombie = new EntityZombie(s);
		loc.setPitch(p.getLocation().getPitch());
		loc.setYaw(p.getLocation().getYaw());
		zombie.setPosition(loc.getX(), loc.getY(), loc.getZ());
		PacketAPI_v1_8_R3.sendPacket(p, new PacketPlayOutSpawnEntityLiving(zombie));
		p.playSound(zombie.getBukkitEntity().getLocation(), SoundHandler.getSound("ZOMBIE_IDLE"), 1.0f, 1.0f);
		return zombie.getId();
		
	}

	@Override
	public void sendDestroyPacket(Player p, int id) {
		PacketAPI_v1_8_R3.destroyEntity(p, id);
	}

	@Override
	public void sendHeartParticles(Player p, Location loc, float a, float b, float c, float d, int count) {
		ParticleSystem_v1_8_R3.sendParticleTo(EnumParticle.HEART, p, loc, a, b, c, d, count);	
	}

	@Override
	public void sendRedstoneParticles(Player p, Location loc, float a, float b, float c, float d, int count) {
		ParticleSystem_v1_8_R3.sendParticleTo(EnumParticle.REDSTONE, p, loc, a, b, c, d, count);	
	}

	@Override
	public void sendGuardianParticles(Player p, Location loc) {
		PacketPlayOutWorldParticles packet = new PacketPlayOutWorldParticles(EnumParticle.MOB_APPEARANCE, false, loc.getBlockX(), loc.getBlockY(), loc.getBlockZ(), 0, 0, 0, 0, 1);
		PacketAPI_v1_8_R3.sendPacket(p, packet);
	}

	@Override
	public boolean hasTag(ItemStack i, String tag) {
		return new NBTTagManager_v1_8_R3(i).hasTag(tag);
	}

	@Override
	public void sendVillagerParticles(Location loc, float a, float b, float c, float d, int count) {
		ParticleSystem_v1_8_R3.sendParticle(EnumParticle.VILLAGER_HAPPY, loc, a, b, c, d, count);	
		
	}

	@Override
	public void playCatSound(Location l, float a, float b) {
		l.getWorld().playSound(l, SoundHandler.getSound("CAT_PURREOW"), a, b);
		
	}

	@Override
	public void playExperienceSound(Location l, float a, float b) {
		l.getWorld().playSound(l, SoundHandler.getSound("ORB_PICKUP"), a, b);
		
	}

	@Override
	public void playDigStoneSound(Location l, float a, float b) {
		l.getWorld().playSound(l, SoundHandler.getSound("DIG_STONE"), a, b);
		
	}

	@Override
	public void playBurpSound(Player p, float a, float b) {
		p.playSound(p.getLocation(), SoundHandler.getSound("BURP"), a, b);
		
	}

	@Override
	public void playCaveSound(Player p, float a, float b) {
		p.playSound(p.getLocation(), SoundHandler.getSound("AMBIENCE_CAVE"), a, b);
		
	}

	@Override
	public ItemStack addTag(ItemStack i, String tag, boolean b) {
		return new NBTTagManager_v1_8_R3(i).addNBT(tag, b);
	}

	@Override
	public void playClickSound(Player p, float a, float b) {
		p.playSound(p.getLocation(), SoundHandler.getSound("CLICK"), a, b);
		
	}

	@Override
	public void playExperienceSound(Player p, float a, float b) {
		p.playSound(p.getLocation(), SoundHandler.getSound("ORB_PICKUP"), a, b);
	}



}
