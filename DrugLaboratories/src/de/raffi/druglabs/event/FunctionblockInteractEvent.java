package de.raffi.druglabs.event;

import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import de.raffi.druglabs.blocks.FunctionBlock;

public class FunctionblockInteractEvent extends Event implements Cancellable{
	
	private Player player;
	private boolean cancelled=false;
	private FunctionBlock block;
	
	
	public FunctionblockInteractEvent(Player player, FunctionBlock block) {
		this.player = player;
		this.block = block;
	}
	public FunctionBlock getBlock() {
		return block;
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
