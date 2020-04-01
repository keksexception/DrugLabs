package de.raffi.druglabs.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.raffi.druglabs.compability.HighVersionHandler;
import de.raffi.druglabs.economy.PriceList;
import de.raffi.druglabs.main.DrugLabs;
import de.raffi.druglabs.utils.InventoryManager;
import de.raffi.druglabs.utils.Translations;

public class DrugLabsCommand implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command arg1, String arg2, String[] args) {
		if(args.length==0) {
			sender.sendMessage("§7DrugLabs by KeksException");
			return false;
		}
		if(sender.hasPermission(Translations.COMMAND_DRUGLABS_PERMISSION)) {
			if(args.length==1) {
				if(args[0].equalsIgnoreCase("reload")) {
					sender.sendMessage(Translations.MESSAGE_CONFIG_RELOAD);
					Translations.load(Translations.class);
					if(Translations.ECONOMY) Translations.load(PriceList.class);
					//TODO: reload event
					sender.sendMessage(Translations.MESSAGE_CONFIG_RELOAD_COMPLETE);
				} else if(args[0].equalsIgnoreCase("test")) { //on
					sender.sendMessage("Server version: " + DrugLabs.VERSION);
					sender.sendMessage("Current version handler: "+DrugLabs.VERSIONHANDLER.getClass().getSimpleName());			
					if(!DrugLabs.VERSION.equals("v1_8_R3")) sender.sendMessage("§cPlease note: The native pluginversion is not equal to the serverversion. This CAN cause errors.");
					
				} else if(args[0].equalsIgnoreCase("items")) {
					if(sender instanceof Player) ((Player) sender).openInventory(InventoryManager.getItemInventory());
				} else if(args[0].equalsIgnoreCase("info")) {
					if(DrugLabs.VERSIONHANDLER instanceof HighVersionHandler) {
						sender.sendMessage("§cPlease Note: The serverversion is not to 100% compatible with the plugin version.");
						sender.sendMessage("§cSome features may have been deactivated and/or changed");
					} else {
						sender.sendMessage("§aThe plugin works fine with the serverversion");
					}
				}
			}else if(args.length==2) {
				 sender.sendMessage("§cSyntax: /druglabs <reload>");
			}else sender.sendMessage("§cSyntax: /druglabs <reload>");
		} else sender.sendMessage(Translations.MESSAGE_NOPERMISSION);
		
		return false;
	}

}
