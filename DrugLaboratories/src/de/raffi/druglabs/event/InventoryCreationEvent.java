package de.raffi.druglabs.event;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.inventory.Inventory;

public class InventoryCreationEvent extends Event{
	
	
	private Inventory inventory;
	private Type type;
	
	
	public InventoryCreationEvent(Inventory inventory, Type type) {
		super();
		this.inventory = inventory;
		this.type = type;
	}
	public Inventory getInventory() {
		return inventory;
	}
	public Type getType() {
		return type;
	}
	private static final HandlerList handlers = new HandlerList();
	public static HandlerList getHandlerList() {
		return handlers;
	}
	@Override
	public HandlerList getHandlers() {
		return handlers;
	}

	public enum Type {
		ADMIN_INVENTORY,DARKNET,COMPUTER;
	}
}
