package de.raffi.druglabs.blocks;

import java.io.Serializable;
import java.util.UUID;

import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import de.raffi.druglabs.utils.Manager;
import de.raffi.druglabs.utils.SerializableLocation;

public abstract class FunctionBlock implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -161907998953355351L;
	private SerializableLocation loc;
	private UUID id;
	private Inventory blockInventory;
	
	
	public FunctionBlock(UUID id,SerializableLocation loc,Inventory blockInventory) {
		this.id = id;
		this.loc = loc;
		this.blockInventory = blockInventory;
	}
	/**
	 * returns the location of the block. 
	 * Use {@link SerializableLocation#toNormal()} to convert is to {@link Location a bukkit location}
	 * @return location of the block
	 */
	public SerializableLocation getLocation() {
		return loc;
	}
	/**
	 * the uuid is used, to identify the block
	 * @return
	 */
	public UUID getId() {
		return id;
	}
	/**
	 * this method is used to create an inventory title 
	 * @return 
	 */
	public String getInventoryTitle() {return "";}
	/**
	 * 
	 * @return the bukkit block at the location
	 */
	public Block getBukkitBlock() {
		return getLocation().getWorld().getBlockAt(getLocation().toNormal());
	}
	/**
	 * 
	 * @return the inventory of the block
	 */
	public Inventory getBlockInventory() {
		return blockInventory;
	}
	public void setBlockInventory(Inventory blockInventory) {
		this.blockInventory = blockInventory;
	}
	/**
	 * called when the block is placed
	 * @see {@link Manager#addBlock(Player, FunctionBlock)}
	 * @param p the player who spawned the block
	 */
	public abstract void spawn(Player p);
	/**
	 * called when the block is destroyed
	 * @see {@link Manager#removeBlock(Player, org.bukkit.Location)}
	 * @param p the player who spawned the block
	 */
	public abstract void destroy(Player p);
	/**
	 * only called if the interface {@link Interactable} is implemented
	 * @param p the player who rightclicked the block
	 */
	public abstract void onInteract(Player p);
}
