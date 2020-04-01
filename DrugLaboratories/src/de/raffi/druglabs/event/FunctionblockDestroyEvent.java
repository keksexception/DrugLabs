package de.raffi.druglabs.event;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import de.raffi.druglabs.blocks.FunctionBlock;

public class FunctionblockDestroyEvent extends Event implements Cancellable{
	
	private Player player;
	private FunctionBlock block;
	private Location location;
	private boolean cancelled=false;
	
	
	public FunctionblockDestroyEvent(Player player, FunctionBlock block, Location location) {
		this.player = player;
		this.block = block;
		this.location = location;
	}
	public FunctionBlock getBlock() {
		return block;
	}
	public Location getLocation() {
		return location;
	}
	public boolean isDestroyedByPlayer() {
		return player != null;
	}
	public Player getPlayer() {
		return player;
	}
	private static final HandlerList handlers = new HandlerList();
	public static HandlerList getHandlerList() {
		return handlers;
	}
	@Override
	public HandlerList getHandlers() {
		return handlers;
	}

	@Override
	public boolean isCancelled() {
		return cancelled;
	}

	@Override
	public void setCancelled(boolean cancelled) {
		this.cancelled = cancelled;
	}

}
