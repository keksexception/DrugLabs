package de.raffi.druglabs.compability.v1_8_R3;

import org.bukkit.craftbukkit.v1_8_R3.inventory.CraftItemStack;
import org.bukkit.inventory.ItemStack;

import net.minecraft.server.v1_8_R3.NBTTagCompound;

public class NBTTagManager_v1_8_R3 {
	
	private ItemStack stack;
	public NBTTagManager_v1_8_R3(ItemStack stack) {
		this.stack = stack;
	}
	
	public ItemStack setNBT(String key, String write) {
		NBTTagCompound tag = new NBTTagCompound();
		tag.setString(key, write);
		net.minecraft.server.v1_8_R3.ItemStack st = CraftItemStack.asNMSCopy(stack);
		st.setTag(tag);
		return CraftItemStack.asBukkitCopy(st);
	}
	public ItemStack addNBT(String key, String write) {
		net.minecraft.server.v1_8_R3.ItemStack st = CraftItemStack.asNMSCopy(stack);
		NBTTagCompound tag = st.getTag();
		tag.setString(key, write);
		st.setTag(tag);
		return CraftItemStack.asBukkitCopy(st);
	}
	public String getNBTString(String key) {
		NBTTagCompound tag = CraftItemStack.asNMSCopy(stack).getTag();
		if(tag != null && tag.hasKey(key)) {
			return tag.getString(key);
		} 
		return null;
	}
	
	public ItemStack setNBT(String key, int write) {
		NBTTagCompound tag = new NBTTagCompound();
		tag.setInt(key, write);
		net.minecraft.server.v1_8_R3.ItemStack st = CraftItemStack.asNMSCopy(stack);
		st.setTag(tag);
		return CraftItemStack.asBukkitCopy(st);
	}
	public ItemStack addNBT(String key, int write) {
		net.minecraft.server.v1_8_R3.ItemStack st = CraftItemStack.asNMSCopy(stack);
		NBTTagCompound tag = st.getTag();
		tag.setInt(key, write);
		st.setTag(tag);
		return CraftItemStack.asBukkitCopy(st);
	}
	public int getNBTInt(String key) {
		NBTTagCompound tag = CraftItemStack.asNMSCopy(stack).getTag();
		if(tag != null && tag.hasKey(key)) {
			return tag.getInt(key);
		} 
		return -1;
	}
	
	public ItemStack setNBT(String key, double write) {
		NBTTagCompound tag = new NBTTagCompound();
		tag.setDouble(key, write);
		net.minecraft.server.v1_8_R3.ItemStack st = CraftItemStack.asNMSCopy(stack);
		st.setTag(tag);
		return CraftItemStack.asBukkitCopy(st);
	}
	public ItemStack addNBT(String key, double write) {
		net.minecraft.server.v1_8_R3.ItemStack st = CraftItemStack.asNMSCopy(stack);
		NBTTagCompound tag = st.getTag();
		tag.setDouble(key, write);
		st.setTag(tag);
		return CraftItemStack.asBukkitCopy(st);
	}
	public double getNBTDouble(String key) {
		NBTTagCompound tag = CraftItemStack.asNMSCopy(stack).getTag();
		if(tag != null && tag.hasKey(key)) {
			return tag.getDouble(key);
		} 
		return -1;
	}
	public ItemStack setNBT(String key, float write) {
		NBTTagCompound tag = new NBTTagCompound();
		tag.setFloat(key, write);
		net.minecraft.server.v1_8_R3.ItemStack st = CraftItemStack.asNMSCopy(stack);
		st.setTag(tag);
		return CraftItemStack.asBukkitCopy(st);
	}
	public ItemStack addNBT(String key, float write) {
		net.minecraft.server.v1_8_R3.ItemStack st = CraftItemStack.asNMSCopy(stack);
		NBTTagCompound tag = st.getTag();
		tag.setFloat(key, write);
		st.setTag(tag);
		return CraftItemStack.asBukkitCopy(st);
	}
	public float getNBTFloat(String key) {
		NBTTagCompound tag = CraftItemStack.asNMSCopy(stack).getTag();
		if(tag != null && tag.hasKey(key)) {
			return tag.getFloat(key);
		} 
		return -1;
	}
	public ItemStack setNBT(String key, long write) {
		NBTTagCompound tag = new NBTTagCompound();
		tag.setLong(key, write);
		net.minecraft.server.v1_8_R3.ItemStack st = CraftItemStack.asNMSCopy(stack);
		st.setTag(tag);
		return CraftItemStack.asBukkitCopy(st);
	}
	public ItemStack addNBT(String key, long write) {
		net.minecraft.server.v1_8_R3.ItemStack st = CraftItemStack.asNMSCopy(stack);
		NBTTagCompound tag = st.getTag();
		tag.setLong(key, write);
		st.setTag(tag);
		return CraftItemStack.asBukkitCopy(st);
	}
	public float getNBTLong(String key) {
		NBTTagCompound tag = CraftItemStack.asNMSCopy(stack).getTag();
		if(tag != null && tag.hasKey(key)) {
			return tag.getLong(key);
		} 
		return -1;
	}
	public ItemStack setNBT(String key, boolean write) {
		NBTTagCompound tag = new NBTTagCompound();
		tag.setBoolean(key, write);
		net.minecraft.server.v1_8_R3.ItemStack st = CraftItemStack.asNMSCopy(stack);
		st.setTag(tag);
		return CraftItemStack.asBukkitCopy(st);
	}
	public ItemStack addNBT(String key, boolean write) {
		net.minecraft.server.v1_8_R3.ItemStack st = CraftItemStack.asNMSCopy(stack);
		NBTTagCompound tag = st.getTag();
		tag.setBoolean(key, write);
		st.setTag(tag);
		return CraftItemStack.asBukkitCopy(st);
	}
	public boolean getNBTBoolean(String key) {
		NBTTagCompound tag = CraftItemStack.asNMSCopy(stack).getTag();
		if(tag != null && tag.hasKey(key)) {
			return tag.getBoolean(key);
		} 
		return false;
	}
	public boolean hasTag(String key) {
		try {
			return CraftItemStack.asNMSCopy(stack).getTag().hasKey(key);
		} catch (Exception e) {
			return false;
		}
	}


}
