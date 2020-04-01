package de.raffi.druglabs.utils;

import org.bukkit.Bukkit;

import de.raffi.druglabs.main.DrugLabs;

public class DrugLogger {
	
	
	public static void info(String s) {
		if(Translations.LOGGER_INFO)
			Bukkit.getServer().getConsoleSender().sendMessage("§a[" + DrugLabs.getPlugin().getName() + "/INFO]:"+ s);
	}
	public static void warn(String s) {
		if(Translations.LOGGER_WARN)
			Bukkit.getServer().getConsoleSender().sendMessage("§e[" + DrugLabs.getPlugin().getName() + "/WARN]:"+ s);
	}
	public static void error(String s) {
		if(Translations.LOGGER_ERROR)
			Bukkit.getServer().getConsoleSender().sendMessage("§c[" + DrugLabs.getPlugin().getName() + "/ERROR]:"+ s);
	}
	public static void log(String s) {
		Bukkit.getServer().getConsoleSender().sendMessage("[" + DrugLabs.getPlugin().getName() + "/INFO]:"+ s);
	}
}
