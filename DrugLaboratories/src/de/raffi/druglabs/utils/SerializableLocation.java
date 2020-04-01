package de.raffi.druglabs.utils;

import java.io.Serializable;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;

public class SerializableLocation implements Serializable{

	/**
	 * 
	 */
	
	private static final long serialVersionUID = 5923332077346942418L;
	private String world;
	private double x,y,z;
	private float yaw,pitch;
	
	public SerializableLocation(World world, double x, double y, double z, float yaw, float pitch) {
		this.world = world.getName();
		this.x = x;
		this.y = y;
		this.z = z;
		this.yaw = yaw;
		this.pitch = pitch;
	}
	public static SerializableLocation toSerializable(Location loc) {
		return new SerializableLocation(loc.getWorld(), loc.getX(), loc.getY(), loc.getZ(),loc.getYaw(),loc.getPitch());
	}
	public Location toNormal() {
		return new Location(getWorld(), getX(), getY(), getZ(), getYaw(), getPitch());
	}
	public World getWorld() {
		return Bukkit.getWorld(world);
	}
	public double getX() {
		return x;
	}
	public double getY() {
		return y;
	}
	public double getZ() {
		return z;
	}
	public float getYaw() {
		return yaw;
	}
	public float getPitch() {
		return pitch;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}
	public static final SerializableLocation DEFAULT = new SerializableLocation(Bukkit.getWorld("world"), 0, 0, 0, 0, 0);
	/**
	 * 
	 * @param s
	 * @return null
	 * @deprecated always returning null
	 */
	public static SerializableLocation fromString(String s) {
		return null;
	}
	
}
