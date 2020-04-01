package de.raffi.druglabs.drug;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import de.raffi.druglabs.main.DrugLabs;

public abstract class Trip {
	
	
	private Player player;
	private int taskID,updateTicks,tripDurationSeconds;
	protected long end;
	/**
	 * 
	 * @param player
	 * @param updateTicks scheduler update ticks
	 * @param tripDurationSeconds
	 */
	public Trip(Player player,int updateTicks, int tripDurationSeconds) {
		this.player = player;
		this.updateTicks = updateTicks;
		this.end = System.currentTimeMillis() + tripDurationSeconds*1000;
	}
	/**
	 * adds the tripDuraction in seconds again
	 */
	public void addTime() {
		end+=tripDurationSeconds*1000;
	}
	/**
	 * 
	 * @param end The milli seconds the trip should end
	 */
	public void setEnd(long end) {
		this.end = end;
	}
	/**
	 * 
	 * @return true, if {@link System#currentTimeMillis()} > {@link Trip#end}
	 */
	public boolean isExpired() {
		return end < System.currentTimeMillis();
	}
	/**
	 * 
	 * @return end of the trip
	 */
	public long getEnd() {
		return end;
	}
	public Player getPlayer() {
		return player;
	}
	public int getTripDurationSeconds() {
		return tripDurationSeconds;
	}
	/**
	 * starts the trip scheduler
	 * 
	 */
	public void startTrip() {
		onStart();
		taskID = Bukkit.getScheduler().scheduleSyncRepeatingTask(DrugLabs.getPlugin(), new Runnable() {
			public void run() {
				runTrip();
			}
		}, updateTicks, updateTicks);
	}
	public int getTaskID() {
		return taskID;
	}
	/**
	 * called every {@link Trip#updateTicks}
	 */
	public void runTrip() {
		if(System.currentTimeMillis()>=end) {
			Bukkit.getScheduler().cancelTask(taskID);
			onStop();
		}
	}
	/**
	 * called on the start
	 */
	public abstract void onStart();
	/**
	 * called when the trip stops.
	 */
	public abstract void onStop();
}
