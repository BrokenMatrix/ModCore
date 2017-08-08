package com.brokenmatrix.modcore.items;

import org.bukkit.Material;

import com.brokenmatrix.modcore.blocks.CustomBlock;

public class CustomBlockItem extends CustomItem
{
	protected CustomBlock block;
	
	public CustomBlockItem(String name, Material base, CustomBlock block, boolean dropsSelf, int maxStackSize, String... lore)
	{
		super(name, base, maxStackSize, lore);
		
		this.block = block;
		this.block.setPlacer(this);
	}
	
	public CustomBlockItem(String name, Material base, CustomBlock block, boolean dropsSelf, String... lore)
	{
		this(name, base, block, dropsSelf, 1, lore);
	}
	
	public CustomBlock getBlock()
	{
		return block;
	}
}
