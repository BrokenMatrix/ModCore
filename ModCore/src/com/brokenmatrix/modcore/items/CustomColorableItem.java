package com.brokenmatrix.modcore.items;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class CustomColorableItem extends CustomItem
{
	private byte color;
	
	public CustomColorableItem(String name, Material base, byte color, int maxStackSize, String... lore)
	{
		super(name, base, maxStackSize, lore);
		
		this.color = color;
	}
	
	public CustomColorableItem(String name, Material base, byte color, String... lore)
	{
		this(name, base, color, 1, lore);
	}
	
	@SuppressWarnings("deprecation")
	public ItemStack getItemStack(int amount)
	{
		int cutAmount = amount;
		if (cutAmount > maxStackSize)
		{
			cutAmount = maxStackSize;
		}
		
		ItemStack stack = new ItemStack(base, cutAmount, (short) 0, color);
		
		ItemMeta meta = stack.getItemMeta();
		meta.setLore(lore);
		meta.setDisplayName(name);
		stack.setItemMeta(meta);
		
		return stack;
	}
}
