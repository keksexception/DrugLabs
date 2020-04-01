package de.raffi.druglabs.blocks;

import java.util.UUID;

import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import de.raffi.druglabs.utils.InventoryManager;
import de.raffi.druglabs.utils.Items;
import de.raffi.druglabs.utils.SerializableLocation;
import de.raffi.druglabs.utils.Translations;

public class Computer extends FunctionBlock implements Interactable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Computer(UUID id, SerializableLocation loc, Inventory blockInventory) {
		super(id, loc, blockInventory);
	}

	@Override
	public void spawn(Player p) {
		p.sendMessage(Translations.MESSAGE_BLOCK_PLACED.replace("%", Translations.BLOCK_NAME_COMPUTER));	
		
	}

	@Override
	public void destroy(Player p) {
		p.sendMessage(Translations.MESSAGE_BLOCK_REMOVED.replace("%", Translations.BLOCK_NAME_COMPUTER));	

		getLocation().getWorld().dropItemNaturally(getLocation().toNormal(), Items.COMPUTER);
		
	}

	@Override
	public void onInteract(Player p) {
		p.openInventory(InventoryManager.getComputerInventory()); //open the computer inventory
		
	}
}
