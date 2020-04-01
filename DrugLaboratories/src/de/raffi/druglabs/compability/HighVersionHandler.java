package de.raffi.druglabs.compability;

import org.bukkit.event.inventory.FurnaceBurnEvent;
import org.bukkit.event.inventory.FurnaceSmeltEvent;

/**
 * 
 * don't use it
 *
 */
public interface HighVersionHandler {
	
	public void handleFurnaceBurn(FurnaceBurnEvent e);
	public void handleFurnaceSmelt(FurnaceSmeltEvent e);
}
