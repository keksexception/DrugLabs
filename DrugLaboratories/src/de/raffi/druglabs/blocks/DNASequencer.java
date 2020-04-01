package de.raffi.druglabs.blocks;

import java.util.Random;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import de.raffi.druglabs.main.DrugLabs;
import de.raffi.druglabs.utils.SerializableLocation;

public class DNASequencer extends FunctionBlock{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DNASequencer(UUID id, SerializableLocation loc, Inventory blockInventory) {
		super(id, loc, blockInventory);
	}

	@Override
	public void spawn(Player p) {
		
	}

	@Override
	public void destroy(Player p) {
		
	}

	@Override
	public void onInteract(Player p) {
	}
	private int sequencingID=-1,count;
	public void startSequencing(LivingEntity e) {
		if(sequencingID!=-1) return;
		sequencingID = Bukkit.getScheduler().scheduleSyncRepeatingTask(DrugLabs.getPlugin(), new Runnable() {
			public void run() {
			//shake
				if(count%2==0) {
					e.teleport(getLocation().toNormal());
					e.damage(0);
				} else {
					e.teleport(getLocation().toNormal().add(0, 0.2, 0));
				}
				Location l = e.getLocation();
				l.setYaw(new Random().nextFloat()*360f);
				l.setPitch(new Random().nextFloat()*-360f);
				e.teleport(l);
				
				count++;
				if(count >= 500) {
					Bukkit.getScheduler().cancelTask(sequencingID);
					sequencingID=-1;
					count = 0;
				}
			}
		}, 1, 1);
	}

}
