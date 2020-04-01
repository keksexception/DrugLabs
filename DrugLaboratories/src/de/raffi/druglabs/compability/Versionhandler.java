package de.raffi.druglabs.compability;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

/**
 * 
 * handles all NMS actions and actions which are different in
 * different versions of Spigot, Bukkit or NMS
 *
 */
public interface Versionhandler {
	

	/**
	 * 
	 * @param p
	 * @param loc
	 * @return entity id
	 */
	public int sendZombiePacket(Player p,Location loc);
	/**
	 * sends a <b>PacketPlayOutEntityDestroy</b> packet to the player
	 * @param p player send the packet to
	 * @param id the entity id of the entity to destroy
	 */
	public void sendDestroyPacket(Player p,int id);
	/**
	 * sends particle to a player
	 * @param p the player send the particle to
	 * @param loc the Location of the particles
	 * @param a x-offset
	 * @param b y-offset
	 * @param c z-offset
	 * @param d speed
	 * @param count particle count
	 */
	public void sendHeartParticles(Player p, Location loc, float a, float b, float c, float d, int count);
	/**
	 * sends particle to a player
	 * @param p the player send the particle to
	 * @param loc the Location of the particles
	 * @param a x-offset
	 * @param b y-offset
	 * @param c z-offset
	 * @param d speed
	 * @param count particle count
	 */
	public void sendRedstoneParticles(Player p, Location loc, float a, float b, float c, float d, int count);
	/**
	 * sends the crazy guarding effect
	 * @param p the player send the particle to
	 * @param loc 
	 */
	public void sendGuardianParticles(Player p, Location loc);
	/**
	 * the particles can be seen from every one
	 * @param loc the location of the particles
	 * @param a x-offset
	 * @param b y-offset
	 * @param c z-offset
	 * @param d speed
	 * @param count particle count
	 */
	public void sendVillagerParticles(Location loc, float a, float b, float c, float d, int count);
	/**
	 * 
	 * @param i the ItemStack the NBT-Tag should be checked
	 * @param tag the NBT-Tag which should be checked
	 * @return true, if the ItemStack has the tag; false if it does not have the tag or if an error oncurred
	 */
	public boolean hasTag(ItemStack i, String tag);
	/**
	 * everyone can hear the sound
	 * @param l the Location
	 * @param a volume
	 * @param b pitch
	 */
	public void playCatSound(Location l,float a, float b);
	/**
	 * everyone can hear the sound
	 * @param l the Location
	 * @param a volume
	 * @param b pitch
	 */
	public void playExperienceSound(Location l,float a, float b);
	/**
	 * only the Player can here the sound
	 * @param p the player you want to play the sound on
	 * @param a volume
	 * @param b pitch
	 */
	public void playExperienceSound(Player p,float a, float b);
	/**
	 * everyone can hear the sound
	 * @param l the Location
	 * @param a volume
	 * @param b pitch
	 */
	public void playDigStoneSound(Location l,float a, float b);
	/**
	 * only the Player can here the sound
	 * @param p the player you want to play the sound on
	 * @param a volume
	 * @param b pitch
	 */
	public void playBurpSound(Player p,float a, float b);
	/**
	 * only the Player can here the sound
	 * @param p the player you want to play the sound on
	 * @param a volume
	 * @param b pitch
	 */
	public void playCaveSound(Player p,float a, float b);
	/**
	 * only the Player can here the sound
	 * @param p the player you want to play the sound on
	 * @param a volume
	 * @param b pitch
	 */
	public void playClickSound(Player p,float a, float b);
	/**
	 * 
	 * @param i The ItemStack you want add the tag
	 * @param tag the NBT-tag you want to add
	 * @param b the value, it does not matter in the plugin if you are using <b>true</b> or <b>false</b>, because only the existens of the NBT-Tag is checked, not the value
	 * @return the new ItemStack
	 */
	public ItemStack addTag(ItemStack i, String tag, boolean b);
}
