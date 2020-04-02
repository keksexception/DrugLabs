package de.raffi.druglabs.listener;

import java.util.HashMap;
import java.util.Random;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.CropState;
import org.bukkit.Effect;
import org.bukkit.Material;
import org.bukkit.block.Furnace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockGrowEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.inventory.FurnaceBurnEvent;
import org.bukkit.event.inventory.FurnaceSmeltEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.material.Crops;
import org.bukkit.material.MaterialData;

import de.raffi.druglabs.blocks.Computer;
import de.raffi.druglabs.blocks.FunctionBlock;
import de.raffi.druglabs.blocks.Hydralysator;
import de.raffi.druglabs.blocks.Interactable;
import de.raffi.druglabs.blocks.Separator;
import de.raffi.druglabs.blocks.Strawberry;
import de.raffi.druglabs.blocks.Synthesizer;
import de.raffi.druglabs.blocks.WeedPlant;
import de.raffi.druglabs.blocks.WeedSeed;
import de.raffi.druglabs.compability.HighVersionHandler;
import de.raffi.druglabs.compability.Versionhandler;
import de.raffi.druglabs.drug.TripBadLSD;
import de.raffi.druglabs.drug.TripMDMA;
import de.raffi.druglabs.drug.TripMushroom;
import de.raffi.druglabs.drug.TripNiceLSD;
import de.raffi.druglabs.drug.TripNiceWeed;
import de.raffi.druglabs.economy.PriceList;
import de.raffi.druglabs.economy.Shop;
import de.raffi.druglabs.economy.ShopItem;
import de.raffi.druglabs.event.FunctionblockInteractEvent;
import de.raffi.druglabs.main.DrugLabs;
import de.raffi.druglabs.utils.InventoryManager;
import de.raffi.druglabs.utils.Items;
import de.raffi.druglabs.utils.Manager;
import de.raffi.druglabs.utils.SerializableLocation;
import de.raffi.druglabs.utils.Translations;

public class DrugListener implements Listener{
	
	private HashMap<Player, FunctionBlock> lastInteract = new HashMap<>();
	private Versionhandler v = DrugLabs.VERSIONHANDLER;
	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
	}
	@EventHandler
	public void onPlace(BlockPlaceEvent e) {
		Player p = e.getPlayer();
		if(v.hasTag(p.getItemInHand(),"separator")) {
			Manager.addBlock(p,new Separator(UUID.randomUUID(), SerializableLocation.toSerializable(e.getBlockPlaced().getLocation()), null));
		} else if(v.hasTag(p.getItemInHand(),"synthesizer")) {
			Manager.addBlock(p,new Synthesizer(UUID.randomUUID(), SerializableLocation.toSerializable(e.getBlockPlaced().getLocation()), InventoryManager.createSynthesizer()));
		} else if(v.hasTag(p.getItemInHand(),"weedseed")) {
			Manager.addBlock(p, new WeedSeed(UUID.randomUUID(), SerializableLocation.toSerializable(e.getBlockPlaced().getLocation()), null));
		} else if(v.hasTag(p.getItemInHand(), "strawberryseeds")) {
			Manager.addBlock(p, new Strawberry(UUID.randomUUID(), SerializableLocation.toSerializable(e.getBlockPlaced().getLocation()), null));
		} else if(v.hasTag(p.getItemInHand(), "computer")) {
			Manager.addBlock(p, new Computer(UUID.randomUUID(), SerializableLocation.toSerializable(e.getBlockPlaced().getLocation()), null));
		} else if(v.hasTag(p.getItemInHand(), "hydralysator")) {
			Manager.addBlock(p, new Hydralysator(UUID.randomUUID(), SerializableLocation.toSerializable(e.getBlockPlaced().getLocation()), null));
		}
	}
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onBreak(BlockBreakEvent e) {
		Player p = e.getPlayer();
		if(Manager.isFunctionBlock(e.getBlock().getLocation())) { //check if the block is a functionblock
			if(Manager.removeBlock(p,e.getBlock().getLocation())) { //check if the block can be removed
				e.setCancelled(true); 
				e.getBlock().setType(Material.AIR); //remove the block
			}
		} else {
			if(e.getBlock().getType()==Material.LONG_GRASS && e.getBlock().getData()==2) { //check if the block is Fern
				if(new Random().nextInt(100)<=Translations.DROPCHANCE_SEEDS_WEED) { //drop the item by probability
					e.setCancelled(true);
					e.getBlock().setType(Material.AIR);
					e.getBlock().getWorld().dropItemNaturally(e.getBlock().getLocation(), Items.SEED_WEED);
				}
			} else if(e.getBlock().getType()==Material.CROPS) { //check if the block is wheat
				Crops crop = (Crops) e.getBlock().getState().getData();
				if(crop.getState()==CropState.RIPE) { //check if the wheat is ripe
					if(new Random().nextInt(100)<=Translations.DROPCHANCE_ERGOT) { //drop ergot by probability
						e.setCancelled(true);
						e.getBlock().setType(Material.AIR); //remove the block
						if(DrugLabs.VERSIONHANDLER instanceof HighVersionHandler)
							e.getBlock().getWorld().dropItemNaturally(e.getBlock().getLocation(), Items.CLAVICEPS); //drop the claviceps directly, because custom furnance recipes are not supported
						else
							e.getBlock().getWorld().dropItemNaturally(e.getBlock().getLocation(), Items.ERGOT); //drop the ergot (custom furnace recipe supported)
					}
				}
			} else if(e.getBlock().getType()==Material.LONG_GRASS) { //check if the block is grass
				if(new Random().nextInt(100)<=Translations.DROPCHANCE_SEEDS_STRAWBERRY) {
					e.getBlock().getWorld().dropItemNaturally(e.getBlock().getLocation(), Items.STRAWBERRY_SEEDS); //drop the item by probability
				}
			} else if(e.getBlock().getType()==Material.DEAD_BUSH) {
				if(new Random().nextInt(100)<=Translations.DROPCHANCE_HYDRASTIS_CANADENSIS) {
					e.getBlock().getWorld().dropItemNaturally(e.getBlock().getLocation(), Items.HYDRASTININ); //(hydrastinin = hydrastis canadensis) drop the item by probability
				}
			}
		}		
	}
	@EventHandler
	public void onClick(InventoryClickEvent e) {
		if(e.getInventory()!=null&&e.getCurrentItem()!=null) {
			if(v.hasTag(e.getCurrentItem(),"placeholder")) e.setCancelled(true); //cancel the event if the item should not be taken from the inventory
			Player p = (Player) e.getWhoClicked();
			if(lastInteract.get(p)!=null&&lastInteract.get(p)instanceof Synthesizer) { 
				if(e.getCurrentItem().getType()==Material.REDSTONE)	{
					if(((Synthesizer)lastInteract.get(p)).canStartSynth()) {
						((Synthesizer)lastInteract.get(p)).startSynth();
					} else if(((Synthesizer)lastInteract.get(p)).canStartHydraSynth()){
						((Synthesizer)lastInteract.get(p)).startHydraSynth();
					} else DrugLabs.VERSIONHANDLER.playBurpSound(p, 1.0f, 2.0f);
				}
			} else if(lastInteract.get(p)!=null&&lastInteract.get(p) instanceof Computer) {
				if(v.hasTag(e.getCurrentItem(), "browser")) {
					p.openInventory(InventoryManager.getDarknetInventory());
					e.setCancelled(true);
					DrugLabs.VERSIONHANDLER.playClickSound(p, 1.0f, 2.0f);
					
				}else if(v.hasTag(e.getCurrentItem(), "drugstore")) {
					p.openInventory(InventoryManager.getDrugStoreInventory());
					e.setCancelled(true);
					DrugLabs.VERSIONHANDLER.playClickSound(p, 1.0f, 2.0f);
				}
				if(e.getView().getTitle().equals(PriceList.INVENTORY_TITLE_COMPUTER)||e.getView().getTitle().equals(PriceList.INVENTORY_TITLE_DARKNET)||e.getView().getTitle().equals(PriceList.INVENTORY_TITLE_DARKNET_DRUGSTORE)) e.setCancelled(true);
				if(e.getView().getTitle().equals(PriceList.INVENTORY_TITLE_DARKNET_DRUGSTORE)) {
					ShopItem i = Shop.get(e.getCurrentItem());
					if(i!=null)
						if(e.isRightClick()&&e.isShiftClick()) {
							i.buy(p); //buy the item
						}
				}	
			}
				
			
		}
	}
	@EventHandler
	public void onInteract(PlayerInteractEvent e) {		
		if(e.getAction()==Action.RIGHT_CLICK_BLOCK) {
			FunctionBlock f = Manager.getFunctionBlock(e.getClickedBlock().getLocation());
			if(f!=null) {
				if(f instanceof Interactable) {
					FunctionblockInteractEvent fie = new FunctionblockInteractEvent(e.getPlayer(), f); //create the event
					Bukkit.getPluginManager().callEvent(fie); //call the event
					if(!fie.isCancelled()) { //check if the event is cancelled
						f.onInteract(e.getPlayer()); //interact with the clicked block					
						lastInteract.put(e.getPlayer(), f); //save the interacted block to the hashmap
						e.setCancelled(true);  //cancel the event
					}					
				}else if(f instanceof WeedPlant&&e.getMaterial()==Material.INK_SACK&&e.getItem().getDurability()==(short)15) { //bonemeal
					e.setCancelled(true);
					int i = e.getPlayer().getItemInHand().getAmount()-1; //get new item amount
					if(i == 0) e.getPlayer().setItemInHand(new ItemStack(Material.AIR)); //remove the item if amount=0
					else e.getPlayer().getItemInHand().setAmount(i); //set the new amount
				} else	if(f instanceof Strawberry&&e.getMaterial()==Material.INK_SACK&&e.getItem().getDurability()==(short)15) { //bonemeal
					((Strawberry) f).grow();
					int i = e.getPlayer().getItemInHand().getAmount()-1; //get new item amount
					if(i == 0) e.getPlayer().setItemInHand(new ItemStack(Material.AIR)); //remove the item if amount=0
					else e.getPlayer().getItemInHand().setAmount(i); //set the new amount
				}
			}
			
		}
		if(e.getAction()==Action.RIGHT_CLICK_BLOCK||e.getAction()==Action.RIGHT_CLICK_AIR) {
			if(e.getMaterial()==Material.BROWN_MUSHROOM||e.getMaterial()==Material.RED_MUSHROOM) {
				DrugLabs.VERSIONHANDLER.playBurpSound(e.getPlayer(), 1.0f, 1.0f);
				int i = e.getPlayer().getItemInHand().getAmount()-1; //get new item amount
				if(i == 0) e.getPlayer().setItemInHand(new ItemStack(Material.AIR)); //remove the item if amount=0
				else e.getPlayer().getItemInHand().setAmount(i); //set the new amount
				new TripMushroom(e.getPlayer()).startTrip(); //start the trip
				return;
			} else if(v.hasTag(e.getItem(), "strawberry")) {
				if(e.getPlayer().getFoodLevel()<20) {
					DrugLabs.VERSIONHANDLER.playBurpSound(e.getPlayer(), 1.0f, 1.0f);
					int i = e.getPlayer().getItemInHand().getAmount()-1; //get new item amount
					if(i == 0) e.getPlayer().setItemInHand(new ItemStack(Material.AIR)); //remove the item if amount=0
					else e.getPlayer().getItemInHand().setAmount(i); //set the new amount
					e.getPlayer().setFoodLevel(e.getPlayer().getFoodLevel()+3); //increase food level 
				}
				e.setCancelled(true);
		
				return;
			}
			if(v.hasTag(e.getItem(),"lsd")) {
				if(new Random().nextBoolean())
					new TripBadLSD(e.getPlayer()).startTrip();
				else new TripNiceLSD(e.getPlayer()).startTrip();
				int i = e.getPlayer().getItemInHand().getAmount()-1; //get new item amount
				if(i == 0) e.getPlayer().setItemInHand(new ItemStack(Material.AIR)); //remove the item if amount=0
				else e.getPlayer().getItemInHand().setAmount(i);//set the new amount
			} else if(v.hasTag(e.getItem(),"weedgrown")) {
					new TripNiceWeed(e.getPlayer()).startTrip();
				int i = e.getPlayer().getItemInHand().getAmount()-1; //get new item amount
				if(i == 0) e.getPlayer().setItemInHand(new ItemStack(Material.AIR)); //remove the item if amount=0
				else e.getPlayer().getItemInHand().setAmount(i); //set the new amount
			} else if(v.hasTag(e.getItem(),"mdma")) {
				new TripMDMA(e.getPlayer()).startTrip();
			int i = e.getPlayer().getItemInHand().getAmount()-1; //get new item amount
			if(i == 0) e.getPlayer().setItemInHand(new ItemStack(Material.AIR)); //remove the item if amount=0
			else e.getPlayer().getItemInHand().setAmount(i); //set the new amount
		} 
		}
		
	}
	@EventHandler
	public void onEntity(PlayerInteractAtEntityEvent e) {
		
	}
	@EventHandler
	public void onBurn(FurnaceBurnEvent e) {
		FunctionBlock f = Manager.getFunctionBlock(e.getBlock().getLocation());
		Inventory inv = ((Furnace)e.getBlock().getState()).getInventory();
		if(f==null) {
			if(inv.getItem(0).getType()==Items.ERGOT.getType() || inv.getItem(0).getType()==Items.SYNTHETIC_HYDRASTININ.getType()) {
				e.setBurnTime(0);
				e.setCancelled(true);
			}
		} else {
			if(f instanceof Separator) {
				if(!v.hasTag(inv.getItem(0),"ergot")) {
					e.setBurnTime(0);
					e.setCancelled(true);
				}
			} else if(f instanceof Hydralysator) {
				if(!v.hasTag(inv.getItem(0),"hydrastinin")) {
					e.setBurnTime(0);
					e.setCancelled(true);
				} else {
					e.setBurnTime(500);
				}
			}
		}
		
		if(DrugLabs.VERSIONHANDLER instanceof HighVersionHandler) ((HighVersionHandler) DrugLabs.VERSIONHANDLER).handleFurnaceBurn(e);
		
	}
	@EventHandler
	public void onGrow(BlockGrowEvent e) {
		FunctionBlock b = Manager.getFunctionBlock(e.getBlock().getLocation());
		if(b!=null) {
			if(b instanceof WeedSeed) {
				if(e.getBlock().getLightLevel()<14) { //check the lightlevel ( 15 is max )
					e.setCancelled(true);
				} else {
					e.setCancelled(true);
					Manager.removeBlock(null, b.getLocation().toNormal());
					Manager.addBlock(null, new WeedPlant(UUID.randomUUID(), b.getLocation(), null));
					DrugLabs.VERSIONHANDLER.playDigStoneSound(b.getLocation().toNormal(), 0.4f, 1.0f);
					for (int i = 0; i < 50; i++) {
						b.getLocation().getWorld().playEffect(b.getLocation().toNormal().add(0.5, 0, 0.5), Effect.TILE_BREAK, new MaterialData(Material.LONG_GRASS), 100);
					}
				}
			} else if(b instanceof Strawberry) {
				((Strawberry) b).grow();
			}
			
		}
	}
	@EventHandler
	public void onSmelt(FurnaceSmeltEvent e) {
		FunctionBlock f = Manager.getFunctionBlock(e.getBlock().getLocation());
		if (v.hasTag(e.getResult(),"claviceps")) {
			if (f == null || !v.hasTag(e.getSource(),"ergot") || !(f instanceof Separator)) {
				e.setResult(null);
				e.setCancelled(true);
			}
		} else if(v.hasTag(e.getResult(), "synthetichydrastinin")) {
			if (f == null || !v.hasTag(e.getSource(),"hydrastinin") || !(f instanceof Hydralysator)) {
				e.setResult(null);
				e.setCancelled(true);
			} 
		}
		if(DrugLabs.VERSIONHANDLER instanceof HighVersionHandler) ((HighVersionHandler) DrugLabs.VERSIONHANDLER).handleFurnaceSmelt(e);

	}
}
