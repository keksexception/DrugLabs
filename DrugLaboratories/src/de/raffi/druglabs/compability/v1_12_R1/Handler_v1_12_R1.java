package de.raffi.druglabs.compability.v1_12_R1;

import java.lang.reflect.Constructor;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.craftbukkit.v1_12_R1.CraftWorld;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;

import de.raffi.druglabs.compability.SoundHandler;
import de.raffi.druglabs.compability.Unfinished;
import de.raffi.druglabs.compability.Versionhandler;
import de.raffi.druglabs.main.DrugLabs;
import de.raffi.druglabs.utils.DrugLogger;
import de.raffi.druglabs.utils.Items;
import de.raffi.druglabs.utils.Translations;
import net.minecraft.server.v1_12_R1.EntityZombie;
import net.minecraft.server.v1_12_R1.EnumParticle;
import net.minecraft.server.v1_12_R1.PacketPlayOutSpawnEntityLiving;
import net.minecraft.server.v1_12_R1.PacketPlayOutWorldParticles;
import net.minecraft.server.v1_12_R1.WorldServer;

public class Handler_v1_12_R1 implements Versionhandler, Unfinished{


	@Override
	public int sendZombiePacket(Player p, Location loc) {
		WorldServer s = ((CraftWorld)p.getWorld()).getHandle();
		EntityZombie zombie = new EntityZombie(s);
		loc.setPitch(p.getLocation().getPitch());
		loc.setYaw(p.getLocation().getYaw());
		zombie.setPosition(loc.getX(), loc.getY(), loc.getZ());
		PacketAPI_v1_12_R1.sendPacket(p, new PacketPlayOutSpawnEntityLiving(zombie));
		p.playSound(zombie.getBukkitEntity().getLocation(), SoundHandler.getSound(SoundHandler.ENTITY_ZOMBIE_AMBIENT), 1.0f, 1.0f);
		return zombie.getId();
		
	}

	@Override
	public void sendDestroyPacket(Player p, int id) {
		PacketAPI_v1_12_R1.destroyEntity(p, id);
	}

	@Override
	public void sendHeartParticles(Player p, Location loc, float a, float b, float c, float d, int count) {
		ParticleSystem_v1_12_R1.sendParticleTo(EnumParticle.HEART, p, loc, a, b, c, d, count);	
	}

	@Override
	public void sendRedstoneParticles(Player p, Location loc, float a, float b, float c, float d, int count) {
		ParticleSystem_v1_12_R1.sendParticleTo(EnumParticle.REDSTONE, p, loc, a, b, c, d, count);	
	}

	@Override
	public void sendGuardianParticles(Player p, Location loc) {
		PacketPlayOutWorldParticles packet = new PacketPlayOutWorldParticles(EnumParticle.MOB_APPEARANCE, false, loc.getBlockX(), loc.getBlockY(), loc.getBlockZ(), 0, 0, 0, 0, 1);
		PacketAPI_v1_12_R1.sendPacket(p, packet);
	}

	@Override
	public boolean hasTag(ItemStack i, String tag) {
		return new NBTTagManager_v1_12_R1(i).hasTag(tag);
	}

	@Override
	public void sendVillagerParticles(Location loc, float a, float b, float c, float d, int count) {
		ParticleSystem_v1_12_R1.sendParticle(EnumParticle.VILLAGER_HAPPY, loc, a, b, c, d, count);	
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
	public ItemStack addTag(ItemStack i, String tag, boolean b) {
		return new NBTTagManager_v1_12_R1(i).addNBT(tag, b);
	}
	@Override
	public void playClickSound(Player p, float a, float b) {
		p.playSound(p.getLocation(), SoundHandler.getSound("BLOCK_WOODEN_PRESSURE_PLATE_CLICK_ON"), a, b);
	}
	@Override
	public void playExperienceSound(Player p, float a, float b) {
		p.playSound(p.getLocation(), SoundHandler.getSound(SoundHandler.ENTITY_EXPERIENCE_ORB_PICKUP), a, b);
	}
	public void createRecipes() {
		try {
			DrugLogger.warn("Forcing crafting recipes ...");
			 NamespacedKey key = new NamespacedKey(DrugLabs.getPlugin(), "separator");
			  Class<?> cl = Class.forName("org.bukkit.inventory.ShapedRecipe");
			  Constructor<?> cons = cl.getConstructor(NamespacedKey.class,ItemStack.class);
			  ShapedRecipe separator = (ShapedRecipe) cons.newInstance(key,Items.SEPARATOR);
			  separator.shape("AAA", "ABA", "AAA");
			  separator.setIngredient('A', Material.STONE);
			  separator.setIngredient('B', Material.SHEARS);
			  Bukkit.addRecipe(separator);
			  key = new NamespacedKey(DrugLabs.getPlugin(), "synthesizer");
				ShapedRecipe synth = (ShapedRecipe) cons.newInstance(key,Items.SYNTHESIZER);
				synth.shape("AAA", "BCB", "DED");
				synth.setIngredient('A', Material.OBSIDIAN);
				synth.setIngredient('B', Material.IRON_BLOCK);
				synth.setIngredient('C', Material.BEACON);
				synth.setIngredient('D', Material.EMERALD_BLOCK);
				synth.setIngredient('E', Material.FLINT_AND_STEEL);
				Bukkit.addRecipe(synth);
				if(Translations.ECONOMY) {
					key = new NamespacedKey(DrugLabs.getPlugin(), "computer");
					ShapedRecipe computer = (ShapedRecipe) cons.newInstance(key,Items.COMPUTER);
					computer.shape("ADA", "EBE", "CCC");
					computer.setIngredient('A', Material.STONE);
					computer.setIngredient('B', Material.REDSTONE);
					computer.setIngredient('C', Material.REDSTONE_TORCH_ON);
					computer.setIngredient('D', Material.THIN_GLASS);
					computer.setIngredient('E', Material.REDSTONE_COMPARATOR);
					Bukkit.addRecipe(computer);
				}
		} catch (Exception e) {
			e.printStackTrace();
		}
		 
	}
}
