package com.brokenmatrix.modcore.items;

import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.material.Colorable;
import org.bukkit.material.MaterialData;

import com.brokenmatrix.modcore.blocks.CustomBlock;

public class CustomColorableBlockItem extends CustomBlockItem
{
	private DyeColor color;
	
	public CustomColorableBlockItem(String name, Material base, DyeColor color, CustomBlock block, boolean dropsSelf, int maxStackSize, String... lore)
	{
		super(name, base, block, dropsSelf, maxStackSize, lore);
	}
	
	public CustomColorableBlockItem(String name, Material base, DyeColor color, CustomBlock block, boolean dropsSelf, String... lore)
	{
		this(name, base, color, block, dropsSelf, 1, lore);
	}
	
	public ItemStack getItemStack(int amount)
	{
		ItemStack stack = super.getItemStack(amount);
		
		MaterialData data = stack.getData();
		if (data instanceof Colorable)
		{
			Colorable c = (Colorable) data;
			c.setColor(color);
		}
		
		return stack;
	}
}
