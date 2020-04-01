package de.raffi.druglabs.blocks;

import java.util.UUID;

import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import de.raffi.druglabs.utils.Items;
import de.raffi.druglabs.utils.SerializableLocation;
import de.raffi.druglabs.utils.Translations;

public class WeedSeed extends FunctionBlock{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7885141588089100103L;

	public WeedSeed(UUID id, SerializableLocation loc, Inventory blockInventory) {
		super(id, loc, blockInventory);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void spawn(Player p) {
		if(p!=null)
			p.sendMessage(Translations.MESSAGE_BLOCK_PLACED.replace("%", Translations.BLOCK_NAME_SEEDS_WEED));	
		
	}

	@Override
	public void destroy(Player p) {
		if(p != null)
			getLocation().getWorld().dropItemNaturally(getLocation().toNormal(), Items.SEED_WEED);
		
	}

	@Override
	public void onInteract(Player p) {
		// TODO Auto-generated method stub
		
	}


}
