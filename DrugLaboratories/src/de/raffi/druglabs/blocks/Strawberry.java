package de.raffi.druglabs.blocks;

import java.util.Random;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.SkullType;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.Skull;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import de.raffi.druglabs.main.DrugLabs;
import de.raffi.druglabs.utils.Items;
import de.raffi.druglabs.utils.SerializableLocation;

public class Strawberry extends FunctionBlock{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2739101112656964934L;

	public Strawberry(UUID id, SerializableLocation loc, Inventory blockInventory) {
		super(id, loc, blockInventory);
		if(canGrow())
		growLoop();
	}
	private int stage;
	@Override
	public void spawn(Player p) {
		switch (stage) {
		case 1:
			setSkull(GROWN_0);
			break;
		case 2:
			setSkull(GROWN_1);
			break;			
		case 3:
			setSkull(GROWN_FULL);
			break;
		default:
			break;
		}
	}
	private static final String GROWN_FULL = "RuthlessTomato",GROWN_1="Pandasaurus_R",GROWN_0="MHF_CoconutG";
	@Override
	public void destroy(Player p) {
		if(stage >= 3) {
			getLocation().getWorld().dropItemNaturally(getLocation().toNormal(), Items.STRAWBERRY);
			if(new Random().nextBoolean()) 	getLocation().getWorld().dropItemNaturally(getLocation().toNormal(), Items.STRAWBERRY_SEEDS);
		}
	}
	/**
	 * calls {@link Strawberry#grow()} every 2 minutes (20*60*2 ticks)
	 */
	public void growLoop() {
		Bukkit.getScheduler().scheduleSyncDelayedTask(DrugLabs.getPlugin(), new Runnable() {
			public void run() {
				grow();
			}
		},20*60*2);
	}
	/**
	 * places a skull with the skin of the given owner at the location of {@link Strawberry#getBukkitBlock() the bukkit block}
	 * @param owner Owner of the skull. Example: <b>KeksException</b> places 
	 * the skull of KeksException at the block location
	 */
	public void setSkull(String owner) {
		Block b = getLocation().getWorld().getBlockAt(getLocation().toNormal());
		b.setType(Material.SKULL);
		Skull skull = (Skull) b.getState();
		skull.setSkullType(SkullType.PLAYER);
		skull.setOwner(owner);
		try {
			BlockFace set = BlockFace.values()[new Random().nextInt(BlockFace.values().length-1)];
			skull.setRotation(set);
		} catch (Exception e) {}
		
		skull.update(true);
	}
	@Override
	public void onInteract(Player p) {} //not used
	
	/**
	 * 
	 * @return true if the current {@link Strawberry#stage stage} < 3
	 */
	public boolean canGrow() {
		return stage < 3;
	}
	/**
	 * increases the stage by 1 and calls {@link Strawberry#growLoop()} and {@link Strawberry#spawn(Player)}.
	 * the spawn method also updates the skull
	 */
	public void grow() {
		if(!canGrow()) return;
		stage++;
		spawn(null);
		growLoop();
		DrugLabs.VERSIONHANDLER.sendVillagerParticles(getLocation().toNormal().add(0.5, 0, 0.5), 0.5f, 0.2f, 0.5f, 0f, 20);
		DrugLabs.VERSIONHANDLER.playDigStoneSound(getLocation().toNormal(), 1.0f, 1.0f);
	}

}
