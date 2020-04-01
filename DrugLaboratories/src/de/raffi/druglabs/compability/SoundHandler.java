package de.raffi.druglabs.compability;

import java.util.HashMap;

import org.bukkit.Sound;

public class SoundHandler {
	/**
	 * the sound id of the sound higher after MC 1.8
	 */
	public static final String ENTITY_ZOMBIE_AMBIENT = "ENTITY_ZOMBIE_AMBIENT", ENTITY_CAT_PURREOW="ENTITY_CAT_PURREOW",ENTITY_EXPERIENCE_ORB_PICKUP="ENTITY_EXPERIENCE_ORB_PICKUP",
			BLOCK_STONE_BREAK="BLOCK_STONE_BREAK",ENTITY_PLAYER_BURP="ENTITY_PLAYER_BURP",AMBIENT_CAVE="AMBIENT_CAVE";
	private static HashMap<String, Sound> cache = new HashMap<>();
	/**
	 * i created this method to get the correct sound. Example: Zombie Sound in 1.8: Sound#ZOMBIE_IDLE but in higher versions: Sound#ENTITY_ZOMBIE_AMBIENT </p>
	 * 
	 * other examples: </p>
	 * MC 1.8: <b>player.playSound(player.getLocation(), SoundHandler.getSound("BURP"), 1.0f, 1.0f);</b> </p>
	 * > MC 1.8: <b>player.playSound(player.getLocation(), SoundHandler.getSound("ENTITY_PLAYER_BURP"), 1.0f, 1.0f);</b>
	 * 
	 * @param s the name of the sound. Example: <b>ENTITY_ZOMBIE_AMBIENT</b> or <b>ZOMBIE_IDLE<b>
	 * @return the sound
	 */
	public static Sound getSound(String s) {
		if(cache.get(s)==null) {
			try {
				for(Object o : Class.forName("org.bukkit.Sound").getEnumConstants()) {
					Sound so = (Sound) o;
					if(so.name().equals(s)) {
						cache.put(s, so);
						return so;
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
				return Sound.values()[0];
			}
		} else return cache.get(s);
		
		
		return Sound.values()[0];
		
	}

}
