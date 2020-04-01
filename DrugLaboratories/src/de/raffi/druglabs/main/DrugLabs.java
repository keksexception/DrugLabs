package de.raffi.druglabs.main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.FurnaceRecipe;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import de.raffi.druglabs.blocks.FunctionBlock;
import de.raffi.druglabs.blocks.WeedPlant;
import de.raffi.druglabs.commands.DrugLabsCommand;
import de.raffi.druglabs.compability.Unfinished;
import de.raffi.druglabs.compability.Versionhandler;
import de.raffi.druglabs.compability.v1_12_R1.Handler_v1_12_R1;
import de.raffi.druglabs.economy.PriceList;
import de.raffi.druglabs.economy.Shop;
import de.raffi.druglabs.economy.VaultBridge;
import de.raffi.druglabs.event.RegisterAddonEvent;
import de.raffi.druglabs.listener.DrugListener;
import de.raffi.druglabs.utils.DrugLogger;
import de.raffi.druglabs.utils.Items;
import de.raffi.druglabs.utils.Manager;
import de.raffi.druglabs.utils.Translations;

public class DrugLabs extends JavaPlugin{
	
	private static DrugLabs plugin;
	public static String VERSION;
	public static Versionhandler VERSIONHANDLER;
	
	@Override
	public void onEnable() {
		plugin = this;
		if(!setupVersionHandler()) {
				DrugLogger.error("******************************************************");
				DrugLogger.error("Your server ('"+VERSION+"') is not compatible with this plugin.");
				DrugLogger.error("To avoid errors and damage on your server, the plugin will be disabled.");
				DrugLogger.error("******************************************************");
				DrugLogger.error("******************************************************");
				DrugLogger.error("Dein Server ('"+VERSION+"') ist mit diesem Plugin nicht kompatibel.");
				DrugLogger.error("Um Probleme zu vermeiden, wird das Plugin abgeschaltet.");
				DrugLogger.error("******************************************************");
				Bukkit.getPluginManager().disablePlugin(getPlugin());
				return;
			
		} else {
			DrugLogger.info("Version check successfull.");
		}
		if(VERSIONHANDLER instanceof Unfinished) DrugLogger.warn("Your serverversion is not to 100% compatible with this plugin. This may cause errors.");
		Translations.load(Translations.class);
		Items.registerItems(VERSIONHANDLER);
		getCommand("druglabs").setExecutor(new DrugLabsCommand());
		
		PluginManager pm = Bukkit.getPluginManager();
		pm.registerEvents(new DrugListener(), this);
		
		loadRecipes();
		if(Translations.ECONOMY) {
			DrugLogger.info("Economy enabled.");
			Translations.load(PriceList.class);
			if(VaultBridge.setupEconomy(pm,true))
				Shop.register();
			else DrugLogger.warn("Error whilst setting up Economy: Unknown. Skipped registering Shop. Retrying in 40 ticks");
		}
		Manager.initBlocks();
		runWeedLoop();
		Bukkit.getPluginManager().callEvent(new RegisterAddonEvent());
	}
	/**
	 * sets up the {@link Versionhandler}
	 * @return true if successfull (true means: the serverversion is supported)
	 */
	public boolean setupVersionHandler() {
		VERSION = getPlugin().getServerVersion();
		try {
			DrugLogger.info("Setting up version handler");
			VERSIONHANDLER = (Versionhandler) Class.forName("de.raffi.druglabs.compability." + VERSION + ".Handler_" + VERSION).newInstance();
			return true;
		} catch (Exception e) {
			DrugLogger.error("Error whilst setting up version handler. Your current server version is not supported by this plugin. " + e);
			return false;
		}
	}
	/**
	 * calls {@link Manager#saveBlocks()}
	 */
	@Override
	public void onDisable() {
		try {
			Manager.saveBlocks();
		} catch (Exception e) {
			DrugLogger.error("Invalid");
		}
	}
	/**
	 * loads all recipes
	 */
	protected void loadRecipes() {
		DrugLogger.info("Loading custom recipes ...");
		try {
			Bukkit.getServer().addRecipe(new FurnaceRecipe(Items.SYNTHETIC_HYDRASTININ, Items.HYDRASTININ.getType()));
			FurnaceRecipe f = new FurnaceRecipe(Items.CLAVICEPS, Items.ERGOT.getType());
			Bukkit.getServer().addRecipe(f);
		} catch (Exception e) {
			DrugLogger.error("§4Error whilst registering Furnance Recipe: Invalid Serverversion");
		}		
		ShapedRecipe separator = new ShapedRecipe(Items.SEPARATOR);
		separator.shape("AAA", "ABA", "AAA");
		separator.setIngredient('A', Material.STONE);
		separator.setIngredient('B', Material.SHEARS);
		Bukkit.getServer().addRecipe(separator);
		
		ShapedRecipe synth = new ShapedRecipe(Items.SYNTHESIZER);
		synth.shape("AAA", "BCB", "DED");
		synth.setIngredient('A', Material.OBSIDIAN);
		synth.setIngredient('B', Material.IRON_BLOCK);
		synth.setIngredient('C', Material.BEACON);
		synth.setIngredient('D', Material.EMERALD_BLOCK);
		synth.setIngredient('E', Material.FLINT_AND_STEEL);
		Bukkit.getServer().addRecipe(synth);
		
		ShapedRecipe hydra = new ShapedRecipe(Items.HYDRALYSATOR);
		hydra.shape("AAA", "ABA", "ACA");
		hydra.setIngredient('A', Material.STONE);
		hydra.setIngredient('B', Material.WATER_BUCKET);
		hydra.setIngredient('C', Material.DIAMOND_BLOCK);
		Bukkit.getServer().addRecipe(hydra);
		if(Translations.ECONOMY) {
			ShapedRecipe computer = new ShapedRecipe(Items.COMPUTER);
			computer.shape("ADA", "EBE", "CCC");
			computer.setIngredient('A', Material.STONE);
			computer.setIngredient('B', Material.REDSTONE);
			computer.setIngredient('C', Material.REDSTONE_TORCH_ON);
			computer.setIngredient('D', Material.THIN_GLASS);
			computer.setIngredient('E', Material.REDSTONE_COMPARATOR);
			Bukkit.getServer().addRecipe(computer);
		}
		if(VERSIONHANDLER instanceof Handler_v1_12_R1) ((Handler_v1_12_R1) VERSIONHANDLER).createRecipes();
	}
	/**
	 * 
	 * @return the Serverversion Example: <b>v1_8_R3</b>
	 */
	public String getServerVersion() {
		return Bukkit.getServer().getClass().getPackage().getName().split("\\.")[3];
	}
	public static DrugLabs getPlugin() {
		return plugin;
	}
	/**
	 * used to check if the {@link WeedPlant weedplants} have the required lightlevel of 14.
	 * If not, the Block will be removed
	 */
	protected void runWeedLoop() {
		if(!Translations.RUN_WEEDLOOP) return;
		DrugLogger.info("Run weed loop");
		Bukkit.getScheduler().scheduleSyncRepeatingTask(getPlugin(), new Runnable() {
			public void run() {
				List<FunctionBlock> copy = new ArrayList<>();
				copy.addAll(Manager.blocks);
				Collections.copy(copy, Manager.blocks);
				copy.forEach(block->{
					if(block instanceof WeedPlant) {
						if(block.getBukkitBlock().getLightLevel()<14) {
							Manager.removeBlock(null, block.getLocation().toNormal());
							block.getBukkitBlock().setType(Material.AIR);
						}
					}
				});
			}
		}, Translations.WEEDLOOP_SECONDS*20, Translations.WEEDLOOP_SECONDS*20);
	}
}
