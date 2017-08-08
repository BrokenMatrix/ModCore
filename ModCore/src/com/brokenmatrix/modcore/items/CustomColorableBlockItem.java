package com.brokenmatrix.modcore.items;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.brokenmatrix.modcore.blocks.CustomBlock;

public class CustomColorableBlockItem extends CustomBlockItem
{
	private byte color;
	
	public CustomColorableBlockItem(String name, Material base, byte color, CustomBlock block, boolean dropsSelf, int maxStackSize, String... lore)
	{
		super(name, base, block, dropsSelf, maxStackSize, lore);
		
		this.color = color;
	}
	
	public CustomColorableBlockItem(String name, Material base, byte color, CustomBlock block, boolean dropsSelf, String... lore)
	{
		this(name, base, color, block, dropsSelf, 64, lore);
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
		System.out.println(stack.getData().getData() + " " + color);
		
		ItemMeta meta = stack.getItemMeta();
		meta.setLore(lore);
		meta.setDisplayName(name);
		stack.setItemMeta(meta);
		
		return stack;
	}
}
