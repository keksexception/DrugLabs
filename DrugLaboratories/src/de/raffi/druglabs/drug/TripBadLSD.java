package de.raffi.druglabs.drug;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

import de.raffi.druglabs.main.DrugLabs;
import de.raffi.druglabs.utils.Translations;

public class TripBadLSD extends Trip{
	
	
	public TripBadLSD(Player player) {
		super(player, Translations.LSD_TRIP_UPDATETICKS_BAD, Translations.LSD_TRIP_DURATION_SECONDS_BAD);
	}
	private List<Integer> remove = new ArrayList<>();
	@Override
	public void runTrip() {
		if(new Random().nextInt(100)<=60) {
			getPlayer().teleport(random(getPlayer().getLocation(), 10));
		}
		if(new Random().nextInt(100)<=40) {
			getPlayer().damage(0);
		}
		if(new Random().nextInt(100)<=40) {
			getPlayer().setVelocity(new Vector(new Random().nextDouble(), new Random().nextDouble(), new Random().nextDouble()));
		}
		if(new Random().nextInt(100)<=10) {
			Location rand = random(getPlayer().getLocation(), 5);
			rand.setPitch(getPlayer().getLocation().getPitch());
			rand.setYaw(getPlayer().getLocation().getYaw());
			remove.add(DrugLabs.VERSIONHANDLER.sendZombiePacket(getPlayer(), rand));
		}
		if(new Random().nextInt(100)<=30) {
			randomSound();
		}
		if(new Random().nextInt(100)<=5) {
			sounds();
		}
		super.runTrip();

	}
	public void randomSound() {
		Sound random = Sound.values()[new Random().nextInt(Sound.values().length-1)];
		getPlayer().playSound(getPlayer().getLocation(), random, 2.0f, 1.0f);
	}
	public void sounds() {
		Bukkit.getScheduler().scheduleSyncDelayedTask(DrugLabs.getPlugin(), new Runnable() {
			public void run() {
				randomSound();
				Bukkit.getScheduler().scheduleSyncDelayedTask(DrugLabs.getPlugin(), new Runnable() {
					public void run() {
						randomSound();
						Bukkit.getScheduler().scheduleSyncDelayedTask(DrugLabs.getPlugin(), new Runnable() {
							public void run() {randomSound();
								Bukkit.getScheduler().scheduleSyncDelayedTask(DrugLabs.getPlugin(), new Runnable() {
									public void run() {
										randomSound();
									}
								},15);
							}
						},15);
					}
				},15);
				
			}
		},15);
	}
	@Override
	public void onStart() {
		getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 1000000, 255, false, false));
		getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, 1000000, 255, false, false));
		getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 1000000, 0, false, false));
		getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 1000000, 1, false, false));
	}
	@Override
	public void onStop() {
		getPlayer().getActivePotionEffects().forEach(p->getPlayer().removePotionEffect(p.getType()));
		
		remove.forEach(i -> DrugLabs.VERSIONHANDLER.sendDestroyPacket(getPlayer(), i));
		remove.clear();
		
	}
	public Location random(Location start, int i) {
		double x = start.getX() + new Random().nextInt(i) * r();
		double y = start.getY();
		double z = start.getZ() + new Random().nextInt(i) * r();
		
		Location loc =  new Location(start.getWorld(), x, y, z);
		if(s(loc)) {
			return loc;
		} else {
			for(int y1 = 256; y1 > 0; y1--) {
				Location lx = new Location(start.getWorld(), x, y1, z);
				if(lx.getBlock().getType() != Material.AIR && s(lx)) return lx;
			}
		}
		return random(start, i);
	}
	private boolean s(Location loc) {
		return loc.clone().add(0, 1, 0).getBlock().getType() == Material.AIR && loc.clone().add(0, 2, 0).getBlock().getType() == Material.AIR;
	}
	private int r() {
		return new Random().nextBoolean() ? 1 : -1;
	}
}
