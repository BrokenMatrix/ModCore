package com.brokenmatrix.modcore.blocks;

import org.bukkit.Material;
import org.bukkit.event.block.BlockPlaceEvent;

import com.brokenmatrix.modcore.items.drops.ICustomDrop;

public class CustomColorableBlock extends CustomBlock
{
	private byte color;
	
	public CustomColorableBlock(String name, Material material, byte color, ICustomDrop... drops)
	{
		super(name, material, drops);
		
		this.color = color;
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public void onPlace(BlockPlaceEvent e)
	{
		e.getPlayer().getWorld().getBlockAt(e.getBlock().getLocation()).setData(color);
	}
}
