package com.brokenmatrix.modcore.blocks;

import org.bukkit.Material;
import org.bukkit.event.block.BlockPlaceEvent;

import com.brokenmatrix.modcore.items.drops.CustomDrop;

public class CustomColorableBlock extends CustomBlock
{
	private byte color;
	
	public CustomColorableBlock(String name, Material material, byte color, CustomDrop... drops)
	{
		super(name, material, drops);
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public void onPlace(BlockPlaceEvent e)
	{
		e.getPlayer().getWorld().getBlockAt(e.getBlock().getLocation()).getState().getData().setData(color);
	}
}
