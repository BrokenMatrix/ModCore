package com.brokenmatrix.modcore.items;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.material.MaterialData;

import com.brokenmatrix.modcore.blocks.CustomBlock;

public class CustomColorableBlockItem extends CustomBlockItem
{
	private byte color;
	
	public CustomColorableBlockItem(String name, Material base, byte color, CustomBlock block, boolean dropsSelf, int maxStackSize, String... lore)
	{
		super(name, base, block, dropsSelf, maxStackSize, lore);
	}
	
	public CustomColorableBlockItem(String name, Material base, byte color, CustomBlock block, boolean dropsSelf, String... lore)
	{
		this(name, base, color, block, dropsSelf, 1, lore);
	}
	
	@SuppressWarnings("deprecation")
	public ItemStack getItemStack(int amount)
	{
		ItemStack stack = super.getItemStack(amount);
		
		MaterialData data = stack.getData();
		data.setData(color);
		stack.setData(data);
		
		return stack;
	}
}
