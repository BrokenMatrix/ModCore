package com.brokenmatrix.modcore.items;

import java.util.Arrays;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class CustomItem
{
	protected List<String> lore;
	protected String name;
	protected Material base;
	protected int maxStackSize;
	
	public CustomItem(String name, Material base, int maxStackSize, String... lore)
	{
		this.name = name;
		this.base = base;
		this.lore = Arrays.asList(lore);
		this.maxStackSize = maxStackSize;
	}
	
	public CustomItem(String name, Material base, String... lore)
	{
		this(name, base, 1, lore);
	}
	
	public void onLeftClick(boolean isAir, Player player, ItemStack self) {}
	public void onRightClick(boolean isAir, Player player, ItemStack self) {}
	public void onKilledEntity(EntityDeathEvent e) {}
	public void onBlockBroken(BlockBreakEvent e) {}
	public void onEntityInteract(Entity entity, ItemStack self) {}
	public void onBreak(ItemStack self) {}
	
	public String getName()
	{
		return name;
	}
	
	public ItemStack getItemStack(int amount)
	{
		int cutAmount = amount;
		if (cutAmount > maxStackSize)
		{
			cutAmount = maxStackSize;
		}
		
		ItemStack stack = new ItemStack(base, cutAmount);
		
		ItemMeta meta = stack.getItemMeta();
		meta.setLore(lore);
		meta.setDisplayName(name);
		stack.setItemMeta(meta);
		
		return stack;
	}
}
