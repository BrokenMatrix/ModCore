package com.brokenmatrix.modcore.blocks;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;

import com.brokenmatrix.modcore.items.CustomBlockItem;
import com.brokenmatrix.modcore.items.drops.CustomDrop;

public class CustomBlock
{
	private String name;
	private Material material;
	private int id = -1;
	private CustomDrop[] drops;
	private CustomBlockItem item;
	
	public CustomBlock(String name, Material material, CustomDrop... drops)
	{
		if (!material.isBlock())
		{
			System.err.println("The block " + name + " was trying to initilize with the non block material " + material.name() + ".");
			
			return;
		}
		
		this.name = name;
		this.material = material;
		this.drops = drops;
	}
	
	public void onPlace(BlockPlaceEvent e) {}
	public void onBreak(BlockBreakEvent e) {}
	
	public void setID(int id)
	{
		this.id = id;
	}
	
	public void setPlacer(CustomBlockItem item)
	{
		this.item = item;
	}
	
	public String getName()
	{
		return name;
	}
	
	public int getID()
	{
		return id;
	}
	
	public Material getMaterial()
	{
		return material;
	}
	
	public List<ItemStack> getDrops()
	{
		if (drops == null)
		{
			return null;
		}
		
		List<ItemStack> customDrops = new ArrayList<ItemStack>();
		
		for (CustomDrop drop : drops)
		{
			ItemStack item = drop.getItemStack();
			
			if (item == null)
				continue;
			if (item.getAmount() == 0)
				continue;
			
			customDrops.add(item);
		}
		
		if (item != null)
		{
			customDrops.add(item.getItemStack(1));
		}
		
		return customDrops;
	}
}
