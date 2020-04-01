package de.raffi.druglabs.drug;

import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

import de.raffi.druglabs.main.DrugLabs;
import de.raffi.druglabs.utils.Translations;

public class TripNiceLSD extends Trip{

	public TripNiceLSD(Player player) {
		super(player, Translations.LSD_TRIP_UPDATETICKS_NICE, Translations.LSD_TRIP_DURATION_SECONDS_NICE);
	}
	@SuppressWarnings("deprecation")
	@Override
	public void runTrip() {
		if(new Random().nextInt(100)<=30) {
			randomSound();
		}
		if(new Random().nextInt(100)<=60) {
			getPlayer().setVelocity(new Vector(new Random().nextDouble()*r(), new Random().nextDouble()*r(), new Random().nextDouble()*r()));
		}
		if(new Random().nextInt(100)<=10) {
			getPlayer().sendBlockChange(getPlayer().getLocation(), Material.DIAMOND_ORE, (byte) 0);
		}
		super.runTrip();
	}
	private int particleID;
	public int r() {
		return new Random().nextBoolean() ? 1:-1;
	}
	public void randomSound() {
		Sound random = Sound.values()[new Random().nextInt(Sound.values().length-1)];
		getPlayer().playSound(getPlayer().getLocation(), random, 2.0f, 1.0f);
	}
	@Override
	public void onStart() {
		getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 1000000, 255, false, false));
		getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, 1000000, 255, false, false));
		getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 1000000, 0, false, false));
		getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 1000000, 2, false, false));
		particleID=Bukkit.getScheduler().scheduleSyncRepeatingTask(DrugLabs.getPlugin(), new Runnable() {
			public void run() {
				DrugLabs.VERSIONHANDLER.sendRedstoneParticles(getPlayer(), getPlayer().getLocation(), 5.6f, 5.6f, 5.6f, 0.5f, 42);
			}
		},2, 2);
	}
	@Override
	public void onStop() {
		getPlayer().getActivePotionEffects().forEach(p->getPlayer().removePotionEffect(p.getType()));
		Bukkit.getScheduler().cancelTask(particleID);

		
	}
}
