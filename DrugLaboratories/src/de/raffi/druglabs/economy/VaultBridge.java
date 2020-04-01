package de.raffi.druglabs.economy;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.RegisteredServiceProvider;

import de.raffi.druglabs.main.DrugLabs;
import de.raffi.druglabs.utils.DrugLogger;
import net.milkbowl.vault.economy.Economy;


public class VaultBridge {

	private static Economy econ;

	/**
	 * set's up the econonmy, called by {@link DrugLabs#onEnable()}
	 * @param pm
	 * @param retry
	 * @return true if success
	 */
	public static boolean setupEconomy(PluginManager pm,boolean retry) {
		if (pm.getPlugin("Vault") == null) {
			return false;
		} else DrugLogger.info("Vault is installed.");
		RegisteredServiceProvider<Economy> rsp = Bukkit.getServer().getServicesManager().getRegistration(Economy.class);
		if (rsp == null) {
			if(retry)
			Bukkit.getScheduler().scheduleSyncDelayedTask(DrugLabs.getPlugin(), new Runnable() {
				public void run() {
					DrugLogger.warn("Retry...");
					if(setupEconomy(pm,false))Shop.register();
				}
			},40);
			DrugLogger.warn("Error whilst setting up economy handler: Could not load Economy.class");
			return false;
		}
		econ = rsp.getProvider();
		return econ != null;
	}
	public static Economy getEconomy() {
        return econ;
    }

}
