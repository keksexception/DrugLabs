package de.raffi.druglabs.drug;

import java.util.Random;

import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

import de.raffi.druglabs.utils.Translations;

public class TripMushroom extends Trip{

	public TripMushroom(Player player) {
		super(player, Translations.MUSHROOM_TRIP_UPDATETICKS, Translations.MUSHROOM_TRIP_DURATION_SECONDS);
	}

	@Override
	public void onStart() {
		getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 1000000, 255, false, false));
		getPlayer().setFoodLevel(20);
	}

	@Override
	public void onStop() {
		getPlayer().getActivePotionEffects().forEach(p->getPlayer().removePotionEffect(p.getType()));
	}
	@Override
	public void runTrip() {	
		if(new Random().nextInt(100)<=60) {
			getPlayer().setVelocity(new Vector(new Random().nextDouble()*r(), new Random().nextDouble()*r(), new Random().nextDouble()*r()));
		}
		if(new Random().nextBoolean())
		getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, 60, 255, false, false));

		super.runTrip();
	}
	public int r() {
		return new Random().nextBoolean() ? 1:-1;
	}
}
