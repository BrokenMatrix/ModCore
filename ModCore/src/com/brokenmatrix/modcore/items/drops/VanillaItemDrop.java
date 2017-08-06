package com.brokenmatrix.modcore.items.drops;

import org.bukkit.inventory.ItemStack;

public class VanillaItemDrop implements CustomDrop
{
	private ItemStack item;
	
	public VanillaItemDrop(ItemStack item)
	{
		this.item = item;
	}
	
	@Override
	public ItemStack getItemStack()
	{	
		return new ItemStack(item);
	}
}

