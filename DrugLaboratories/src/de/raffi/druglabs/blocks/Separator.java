package de.raffi.druglabs.blocks;

import java.util.UUID;

import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import de.raffi.druglabs.utils.Items;
import de.raffi.druglabs.utils.SerializableLocation;
import de.raffi.druglabs.utils.Translations;

public class Separator extends FunctionBlock{

	public Separator(UUID id, SerializableLocation loc, Inventory blockInventory) {
		super(id, loc, blockInventory);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	@Override
	public void spawn(Player p) {
		p.sendMessage(Translations.MESSAGE_BLOCK_PLACED.replace("%", Translations.BLOCK_NAME_SEPARATOR));
	}

	@Override
	public void destroy(Player p) {
		p.sendMessage(Translations.MESSAGE_BLOCK_REMOVED.replace("%", Translations.BLOCK_NAME_SEPARATOR));
		getLocation().getWorld().dropItemNaturally(getLocation().toNormal(), Items.SEPARATOR);
	}

	@Override
	public void onInteract(Player p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getInventoryTitle() {
		return Translations.BLOCK_NAME_SEPARATOR;
	}

}
