package de.raffi.druglabs.drug;

import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import de.raffi.druglabs.main.DrugLabs;
import de.raffi.druglabs.utils.Translations;

public class TripMDMA extends Trip{

	public TripMDMA(Player player) {
		super(player, Translations.MDMA_TRIP_UPDATETICKS, Translations.MDMA_TRIP_DURATION_SECONDS);
	}
	private int particleID;
	@Override
	public void onStart() {
		getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.SLOW_DIGGING, 1000000, 255, false, false));
		getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, 1000000, 255, false, false));
		getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 1000000, 1, false, false));
		getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 1000000, 2, false, false));
		getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, 1000000, 100, false, false));
		particleID=Bukkit.getScheduler().scheduleSyncRepeatingTask(DrugLabs.getPlugin(), new Runnable() {
			public void run() {
				double d = Translations.MDMA_TRIP_ENTITYSEARCHRADIUS;
				for(Entity e : getPlayer().getNearbyEntities(d, d, d))
					if(e instanceof LivingEntity)
						DrugLabs.VERSIONHANDLER.sendHeartParticles(getPlayer(), e.getLocation(), 0.6f, 0.6f, 0.6f, 0.5f, 2);

			}
		},2, 2);
		
	}

	@Override
	public void onStop() {
		Bukkit.getScheduler().cancelTask(particleID);
		getPlayer().getActivePotionEffects().forEach(p->getPlayer().removePotionEffect(p.getType()));

	}
	@Override
	public void runTrip() {
		super.runTrip();
	}
	public double b() {
		return new Random().nextDouble()*a();
	}
	public int a() {
		return new Random().nextBoolean()?1:-1;
	}
}
