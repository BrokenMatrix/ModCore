package com.brokenmatrix.modcore.blocks;

import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.material.Colorable;
import org.bukkit.material.MaterialData;

import com.brokenmatrix.modcore.items.drops.CustomDrop;

public class CustomColorableBlock extends CustomBlock
{
	private DyeColor color;
	
	public CustomColorableBlock(String name, Material material, DyeColor color, CustomDrop... drops)
	{
		super(name, material, drops);
	}
	
	@Override
	public void onPlace(BlockPlaceEvent e)
	{
		MaterialData state = e.getPlayer().getWorld().getBlockAt(e.getBlock().getLocation()).getState().getData();
		if (state instanceof Colorable)
		{
			Colorable c = (Colorable) state;
			c.setColor(color);
		}
	}
}
