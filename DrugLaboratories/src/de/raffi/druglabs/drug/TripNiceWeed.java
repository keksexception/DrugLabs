package de.raffi.druglabs.drug;

import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.WeatherType;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import de.raffi.druglabs.main.DrugLabs;
import de.raffi.druglabs.utils.Translations;

public class TripNiceWeed extends Trip{

	public TripNiceWeed(Player player) {
		super(player, Translations.WEED_TRIP_UPDATETICKS_NICE, Translations.WEED_TRIP_DURATION_SECONDS_NICE);
	}

	@Override
	public void onStart() {
		getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 1000000, 1, false, false));
		getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, 1000000, 255, false, false));
		getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 1000000, 1, false, false));
		getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 1000000, 1, false, false));
		getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.HUNGER, 1000000, 2, false, false));
		particleID=Bukkit.getScheduler().scheduleSyncRepeatingTask(DrugLabs.getPlugin(), new Runnable() {
			public void run() {
				DrugLabs.VERSIONHANDLER.sendHeartParticles(getPlayer(), getPlayer().getLocation(), 0.4f, 0.4f, 0.4f, 1.0f, 10);
				DrugLabs.VERSIONHANDLER.sendRedstoneParticles(getPlayer(), getPlayer().getLocation(), 2.6f, 2.6f, 2.6f, 0.5f, 40);

			}
		},2, 2);
		
	}

	@Override
	public void onStop() {
		getPlayer().getActivePotionEffects().forEach(p->getPlayer().removePotionEffect(p.getType()));
		getPlayer().resetPlayerTime();
		getPlayer().setPlayerWeather(WeatherType.CLEAR);
		getPlayer().resetPlayerWeather();
		Bukkit.getScheduler().cancelTask(particleID);
	
	}
	private int particleID;
	@Override
	public void runTrip() {
		if(new Random().nextBoolean())
			getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 120, 255, false, false));
		getPlayer().setPlayerTime(new Random().nextInt(), false);
		if(new Random().nextInt(100)<=20) {
			for(int i = 0; i <10;i++) {
				Location loc = getPlayer().getLocation();
				DrugLabs.VERSIONHANDLER.sendGuardianParticles(getPlayer(), loc);
				DrugLabs.VERSIONHANDLER.playCaveSound(getPlayer(), 1.0f, 1.0f);
			}
			

		}
		super.runTrip();

	}

}
