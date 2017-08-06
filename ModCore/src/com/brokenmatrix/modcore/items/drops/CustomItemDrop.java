package com.brokenmatrix.modcore.items.drops;

import org.bukkit.inventory.ItemStack;

import com.brokenmatrix.modcore.items.CustomItem;

public class CustomItemDrop implements CustomDrop
{
	private CustomItem item;
	private int amount;
	
	public CustomItemDrop(CustomItem item, int amount)
	{
		this.item = item;
		this.amount = amount;
	}
	
	@Override
	public ItemStack getItemStack()
	{	
		return item.getItemStack(amount);
	}
}
