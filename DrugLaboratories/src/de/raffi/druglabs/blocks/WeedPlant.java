package de.raffi.druglabs.blocks;

import java.util.Random;
import java.util.UUID;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import de.raffi.druglabs.utils.Items;
import de.raffi.druglabs.utils.SerializableLocation;
import de.raffi.druglabs.utils.Translations;

public class WeedPlant extends FunctionBlock{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7868053312653472119L;

	public WeedPlant(UUID id, SerializableLocation loc, Inventory blockInventory) {
		super(id, loc, blockInventory);
	}

	@SuppressWarnings("deprecation")
	@Override
	public void spawn(Player p) {
		Block block = getLocation().getWorld().getBlockAt(getLocation().toNormal());
		block.setType(Material.LONG_GRASS);
		block.setData((byte) 2);
		
	}

	@Override
	public void destroy(Player p) {
		if(p!=null) getLocation().getWorld().dropItemNaturally(getLocation().toNormal(), Items.GROWN_WEED);
		if(new Random().nextInt(100)<=Translations.DROPCHANCE_SEEDS_WEED) getLocation().getWorld().dropItemNaturally(getLocation().toNormal(), Items.SEED_WEED);
		
	}

	@Override
	public void onInteract(Player p) {
		// TODO Auto-generated method stub
		
	}


}
