package de.raffi.druglabs.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import de.raffi.druglabs.blocks.FunctionBlock;
import de.raffi.druglabs.event.FunctionblockAddEvent;
import de.raffi.druglabs.event.FunctionblockDestroyEvent;
import de.raffi.druglabs.main.DrugLabs;

public class Manager {
	
	/**
	 * this list includes all {@link FunctionBlock FunctionBlocks} in the
	 * world which are placed with the Method {@link Manager#addBlock(Player, FunctionBlock)}
	 */
	public static List<FunctionBlock> blocks;
	protected static File root = new File(Files.FOLDER, Files.BLOCKS);
	protected static FileConfiguration cfg = YamlConfiguration.loadConfiguration(root);
	
	/**
	 * reads and loads the blocks into {@link Manager#blocks the list}
	 */
	@SuppressWarnings("unchecked")
	public static void initBlocks() {
		DrugLogger.log("Reading blocks ...");
		try {
			File f = new File(Files.FOLDER);
			if(!f.exists()) f.mkdir();
		} catch (Exception e) {}
		if(!Files.DATA.exists())
			try {
				Files.DATA.createNewFile();
			} catch (Exception e) {
				e.printStackTrace();
				DrugLogger.error("§cError: " + e.getMessage());
			}
		
		try {
			ObjectHelper reader = new ObjectHelper(Files.DATA).reader();
			blocks = (List<FunctionBlock>) reader.readObject();
			reader.closeReader();
		} catch (Exception e) {
			DrugLogger.warn("Could not read block list. This is caused by an error, or you are using this plugin for the thirst time.");
		}
		if(blocks == null)blocks = new ArrayList<>();
		DrugLogger.log("Found " + blocks.size() + " custom blocks");
		blocks.forEach(block->{
			block.setBlockInventory(InventoryManager.getInventory(block.getId().toString(), block.getInventoryTitle()));
		});
	}
	/**
	 * saves all blocks into {@link Files#DATA}.
	 * called in {@link DrugLabs#onDisable()}
	 */
	public static void saveBlocks() {
		DrugLogger.log("Saving blocks ...");
		if(!Files.DATA.exists())
			try {
				Files.DATA.createNewFile();
			} catch (Exception e) {
				e.printStackTrace();
				DrugLogger.error("§cError: " + e.getMessage());
			}
		for(FunctionBlock b : blocks) {
			if(b.getBlockInventory()!=null) {
				InventoryManager.saveInventory(b.getId().toString(), b.getBlockInventory());
				b.setBlockInventory(null);
			}
		}
		ObjectHelper writer = new ObjectHelper(Files.DATA).writer();
		writer.writeObject(blocks);
		writer.closeWriter();
	}
	/**
	 * 
	 * @param loc the location which should be checked
	 * @return true, if there is a {@link FunctionBlock} at the given location
	 */
	public static boolean isFunctionBlock(Location loc) {
		return getFunctionBlock(loc)!=null;
	}
	/**
	 * 
	 * @param loc the location which should be checked
	 * @return the {@link FunctionBlock} at the given location; null if there is no {@link FunctionBlock}
	 */
	public static FunctionBlock getFunctionBlock(Location loc) {
		for(FunctionBlock block : blocks) {
			if(isSameLocation(block.getLocation().toNormal(), loc)) return block;
		}
		return null;
	}
	/**
	 * checks if the location is at the same block as the other
	 * @param l1 Location 1
	 * @param l2 Location 2
	 * @return
	 */
	public static boolean isSameLocation(Location l1, Location l2) {
		return l1.getWorld().getName()==l2.getWorld().getName()&&l1.getBlockX()==l2.getBlockX()&&l1.getBlockY()==l2.getBlockY()&&l1.getBlockZ()==l2.getBlockZ();
	}
	/**
	 * The method calls the {@link FunctionblockDestroyEvent}. If the event
	 * is not cancelled, {@link FunctionBlock#destroy(Player)} is called
	 * and the block will be removed from {@link Manager#blocks}
	 * @param p The player who removed the block; <b>null</b> if it is removed by something other
	 * @param loc The Block which should be removed
	 * @return false when the event is cancelled
	 */
	public static boolean removeBlock(Player p,Location loc) {
		FunctionBlock b = getFunctionBlock(loc);
		FunctionblockDestroyEvent ev = new FunctionblockDestroyEvent(p, b, loc);
		Bukkit.getPluginManager().callEvent(ev);
		if(!ev.isCancelled()) {
			b.destroy(p);
			blocks.remove(b);
			return true;
		} else return false;
		
	}
	/**
	 * The method calls  the {@link FunctionblockAddEvent}. If the event
	 * is not cancelled, {@link FunctionBlock#spawn(Player)} is called
	 * and the block will be added to {@link Manager#blocks}
	 * @param p The player who places/adds the block
	 * @param block the block which should be placed
	 */
	public static void addBlock(Player p,FunctionBlock block) {
		FunctionblockAddEvent ev = new FunctionblockAddEvent(p, block, block.getLocation().toNormal());
		Bukkit.getPluginManager().callEvent(ev);
		if(!ev.isCancelled()) {
			blocks.add(block);
			block.spawn(p);
		}		
	}
}
