package de.raffi.druglabs.compability.v1_14_R1;

import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.craftbukkit.v1_14_R1.CraftParticle;
import org.bukkit.craftbukkit.v1_14_R1.CraftWorld;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.FurnaceBurnEvent;
import org.bukkit.event.inventory.FurnaceSmeltEvent;
import org.bukkit.inventory.ItemStack;

import de.raffi.druglabs.compability.HighVersionHandler;
import de.raffi.druglabs.compability.SoundHandler;
import de.raffi.druglabs.compability.Versionhandler;
import net.minecraft.server.v1_14_R1.EntityZombie;
import net.minecraft.server.v1_14_R1.PacketPlayOutSpawnEntityLiving;
import net.minecraft.server.v1_14_R1.PacketPlayOutWorldParticles;
import net.minecraft.server.v1_14_R1.ParticleParam;
import net.minecraft.server.v1_14_R1.WorldServer;

public class Handler_v1_14_R1 implements Versionhandler,HighVersionHandler{

	@Override
	public int sendZombiePacket(Player p, Location loc) {
		WorldServer s = ((CraftWorld)p.getWorld()).getHandle();
		EntityZombie zombie = new EntityZombie(s);
		loc.setPitch(p.getLocation().getPitch());
		loc.setYaw(p.getLocation().getYaw());
		zombie.setPosition(loc.getX(), loc.getY(), loc.getZ());
		PacketAPI_v1_14_R1.sendPacket(p, new PacketPlayOutSpawnEntityLiving(zombie));
		p.playSound(zombie.getBukkitEntity().getLocation(), SoundHandler.getSound(SoundHandler.ENTITY_ZOMBIE_AMBIENT), 1.0f, 1.0f);
		return zombie.getId();
		
	}

	@Override
	public void sendDestroyPacket(Player p, int id) {
		PacketAPI_v1_14_R1.destroyEntity(p, id);
	}

	@Override
	public void sendHeartParticles(Player p, Location loc, float a, float b, float c, float d, int count) {
		ParticleSystem_v1_14_R1.sendParticleTo(Particle.HEART, p, loc, a, b, c, d, count);	
	}

	@Override
	public void sendRedstoneParticles(Player p, Location loc, float a, float b, float c, float d, int count) {
		ParticleSystem_v1_14_R1.sendParticleTo(Particle.REDSTONE, p, loc, a, b, c, d, count);	
	}

	@Override
	public void sendGuardianParticles(Player p, Location loc) {
		ParticleParam pa = CraftParticle.toNMS(Particle.MOB_APPEARANCE, null);
		PacketPlayOutWorldParticles packet = new PacketPlayOutWorldParticles(pa, false, loc.getBlockX(), loc.getBlockY(), loc.getBlockZ(), 0, 0, 0, 0, 1);
		PacketAPI_v1_14_R1.sendPacket(p, packet);
	}

	@Override
	public boolean hasTag(ItemStack i, String tag) {
		return new NBTTagManager_v1_14_R1(i).hasTag(tag);
	}

	@Override
	public void sendVillagerParticles(Location loc, float a, float b, float c, float d, int count) {
		ParticleSystem_v1_14_R1.sendParticle(Particle.VILLAGER_HAPPY, loc, a, b, c, d, count);	
		
	}
	@Override
	public void playCatSound(Location l, float a, float b) {
		l.getWorld().playSound(l, SoundHandler.getSound(SoundHandler.ENTITY_CAT_PURREOW), a, b);
		
	}

	@Override
	public void playExperienceSound(Location l, float a, float b) {
		l.getWorld().playSound(l, SoundHandler.getSound(SoundHandler.ENTITY_EXPERIENCE_ORB_PICKUP), a, b);
		
	}

	@Override
	public void playDigStoneSound(Location l, float a, float b) {
		l.getWorld().playSound(l, SoundHandler.getSound(SoundHandler.BLOCK_STONE_BREAK), a, b);
		
	}

	@Override
	public void playBurpSound(Player p, float a, float b) {
		p.playSound(p.getLocation(), SoundHandler.getSound(SoundHandler.ENTITY_PLAYER_BURP), a, b);
	}
	@Override
	public void playCaveSound(Player p, float a, float b) {
		p.playSound(p.getLocation(), SoundHandler.getSound(SoundHandler.AMBIENT_CAVE), a, b);
	}

	@Override
	public void handleFurnaceBurn(FurnaceBurnEvent e) {
		e.setCancelled(false);
	}

	@Override
	public void handleFurnaceSmelt(FurnaceSmeltEvent e) {
		e.setCancelled(false);
	}
	@Override
	public ItemStack addTag(ItemStack i, String tag, boolean b) {
		return new NBTTagManager_v1_14_R1(i).addNBT(tag, b);
	}
	@Override
	public void playClickSound(Player p, float a, float b) {
		p.playSound(p.getLocation(), SoundHandler.getSound("BLOCK_WOODEN_PRESSURE_PLATE_CLICK_ON"), a, b);
	}
	@Override
	public void playExperienceSound(Player p, float a, float b) {
		p.playSound(p.getLocation(), SoundHandler.getSound(SoundHandler.ENTITY_EXPERIENCE_ORB_PICKUP), a, b);
	}
}
