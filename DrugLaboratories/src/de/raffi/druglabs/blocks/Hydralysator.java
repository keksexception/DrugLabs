package de.raffi.druglabs.blocks;

import java.util.UUID;

import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import de.raffi.druglabs.utils.Items;
import de.raffi.druglabs.utils.SerializableLocation;
import de.raffi.druglabs.utils.Translations;

public class Hydralysator extends FunctionBlock{

	/**
	 * 
	 */
	private static final long serialVersionUID = -231705733673235841L;

	public Hydralysator(UUID id, SerializableLocation loc, Inventory blockInventory) {
		super(id, loc, blockInventory);
	}

	@Override
	public void spawn(Player p) {
		p.sendMessage(Translations.MESSAGE_BLOCK_PLACED.replace("%", Translations.BLOCK_NAME_HYDRALYSATOR));	

	}

	@Override
	public void destroy(Player p) {
		p.sendMessage(Translations.MESSAGE_BLOCK_REMOVED.replace("%", Translations.BLOCK_NAME_HYDRALYSATOR));
		getLocation().getWorld().dropItemNaturally(getLocation().toNormal(), Items.HYDRALYSATOR);
	}

	@Override
	public void onInteract(Player p) {
		
	}



}
