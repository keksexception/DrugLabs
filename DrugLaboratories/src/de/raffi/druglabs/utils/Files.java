package de.raffi.druglabs.utils;

import java.io.File;

public class Files {
	
	/**
	 * folder of the plugin {@value Files#FOLDER}
	 */
	public static final String FOLDER = "plugins/DrugLabs";
	/**
	 * the file where all messages are in
	 * {@value Files#TRANSLATION}
	 */
	public static final String TRANSLATION = "translation.yml";
	/**
	 * plugin configuration file
	 */
	public static final String CONFIG = "config.yml";
	/**
	 * used to store the inventory data
	 */
	public static final String BLOCKS = "blocks.yml";
	/**
	 * shop configuration file
	 */
	public static final String SHOP = "shop.yml";
	/**
	 * the {@link Manager#blocks blocklist} is stored here
	 */
	public static final File DATA = new File(FOLDER, "drugs.data");
}
