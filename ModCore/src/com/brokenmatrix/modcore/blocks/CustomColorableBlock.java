package com.brokenmatrix.modcore.blocks;

import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.block.BlockState;
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
		BlockState state = e.getPlayer().getWorld().getBlockAt(e.getBlock().getLocation()).getState();
		MaterialData data = state.getData();
		if (data instanceof Colorable)
		{
			Colorable c = (Colorable) data;
			c.setColor(color);
		}
		state.setData(data);
		state.update();
	}
}
