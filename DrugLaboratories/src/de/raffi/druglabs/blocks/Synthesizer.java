package de.raffi.druglabs.blocks;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import de.raffi.druglabs.main.DrugLabs;
import de.raffi.druglabs.utils.Items;
import de.raffi.druglabs.utils.SerializableLocation;
import de.raffi.druglabs.utils.Translations;

public class Synthesizer extends FunctionBlock implements Interactable{

	public Synthesizer(UUID id, SerializableLocation loc, Inventory blockInventory) {
		super(id, loc, blockInventory);
		started = false;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 4551365231182994602L;

	

	@Override
	public void spawn(Player p) {
		p.sendMessage(Translations.MESSAGE_BLOCK_PLACED.replace("%", Translations.BLOCK_NAME_SYNTHESIZER));	
	}

	@Override
	public void destroy(Player p) {
		p.sendMessage(Translations.MESSAGE_BLOCK_REMOVED.replace("%", Translations.BLOCK_NAME_SYNTHESIZER));
		//p.getWorld().dropItemNaturally(getLocation().toNormal(), getBlockInventory().getItem(10));
		getLocation().getWorld().dropItemNaturally(getLocation().toNormal(), Items.SYNTHESIZER);
	}

	@Override
	public void setBlockInventory(Inventory blockInventory) {
		super.setBlockInventory(blockInventory);
		if(blockInventory!=null)
		getBlockInventory().setItem(25, Items.SYNTH_START);
	}
	@Override
	public void onInteract(Player p) {		
		p.openInventory(getBlockInventory());
	}
	public boolean canStartSynth() {
		return DrugLabs.VERSIONHANDLER.hasTag(getBlockInventory().getItem(10), "claviceps")&&getBlockInventory().getItem(12)!=null&&getBlockInventory().getItem(12).getType()==Material.EMERALD&&getBlockInventory().getItem(10).getAmount()>=2;
	}
	boolean started;
	public void startSynth() {
		if(started) return;
		getBlockInventory().setItem(25, Items.SYNTH_PROGRESS);
		started = true;
		ItemStack clavi = getBlockInventory().getItem(10);
		int ca =clavi.getAmount()-2;
		if(ca <= 0) getBlockInventory().setItem(10, new ItemStack(Material.AIR));
		else
			clavi.setAmount(ca);
		
		ItemStack emerald = getBlockInventory().getItem(12);
		int ea =emerald.getAmount()-1;	
		if(ea <= 0) getBlockInventory().setItem(12, new ItemStack(Material.AIR));
		else
			emerald.setAmount(ea);
		DrugLabs.VERSIONHANDLER.playCatSound(getLocation().toNormal(), 0.1f, 1.5f);
		Bukkit.getScheduler().scheduleSyncDelayedTask(DrugLabs.getPlugin(), new Runnable() {
			public void run() {
				started = false;
				ItemStack result = getBlockInventory().getItem(16);
				if(result == null) getBlockInventory().setItem(16, Items.LSD);
				else getBlockInventory().addItem(Items.LSD);
				DrugLabs.VERSIONHANDLER.playExperienceSound(getLocation().toNormal(), 0.1f, 1.0f);
				if(canStartSynth()) startSynth();
				else getBlockInventory().setItem(25, Items.SYNTH_START);
			}
		},20*15);
	}
	public boolean canStartHydraSynth() {
		return DrugLabs.VERSIONHANDLER.hasTag(getBlockInventory().getItem(10), "synthetichydrastinin")&&getBlockInventory().getItem(12)!=null&&getBlockInventory().getItem(12).getType()==Material.DIAMOND&&getBlockInventory().getItem(12).getAmount()>=2&&getBlockInventory().getItem(10).getAmount()>=1;
	}
	public void startHydraSynth() {
		if(started) return;
		getBlockInventory().setItem(25, Items.SYNTH_PROGRESS);
		started = true;
		ItemStack isochiniolinol = getBlockInventory().getItem(10);
		int ca =isochiniolinol.getAmount()-1;
		if(ca <= 0) getBlockInventory().setItem(10, new ItemStack(Material.AIR));
		else
			isochiniolinol.setAmount(ca);
		
		ItemStack diamond = getBlockInventory().getItem(12);
		int da =diamond.getAmount()-2;	
		if(da <= 0) getBlockInventory().setItem(12, new ItemStack(Material.AIR));
		else
			diamond.setAmount(da);
		DrugLabs.VERSIONHANDLER.playCatSound(getLocation().toNormal(), 0.1f, 1.5f);
		Bukkit.getScheduler().scheduleSyncDelayedTask(DrugLabs.getPlugin(), new Runnable() {
			public void run() {
				started = false;
				ItemStack result = getBlockInventory().getItem(16);
				if(result == null) getBlockInventory().setItem(16, Items.MDMA);
				else getBlockInventory().addItem(Items.MDMA);
				DrugLabs.VERSIONHANDLER.playExperienceSound(getLocation().toNormal(), 0.1f, 1.0f);
				if(canStartHydraSynth()) startHydraSynth();
				else getBlockInventory().setItem(25, Items.SYNTH_START);
			}
		},20*25);
	}

	@Override
	public String getInventoryTitle() {
		return Translations.BLOCK_NAME_SYNTHESIZER;
	}

}
