package com.brokenmatrix.modcore.items.drops;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;

public final class CustomDrops
{
	private static HashMap<EntityType, List<ICustomDrop>> EntityDrops;
	private static HashMap<Material, List<ICustomDrop>> BlockDrops;
	
	static
	{
		EntityDrops = new HashMap<EntityType, List<ICustomDrop>>();
		BlockDrops = new HashMap<Material, List<ICustomDrop>>();
	}
	
	public static void Register(EntityType entity, ICustomDrop drop)
	{
		if (!EntityDrops.containsKey(entity))
		{
			EntityDrops.put(entity, new ArrayList<ICustomDrop>());
		}
		
		EntityDrops.get(entity).add(drop);
	}
	
	public static void Register(Material block, ICustomDrop drop)
	{
		if (block.isBlock())
		{
			if (!BlockDrops.containsKey(block))
			{
				BlockDrops.put(block, new ArrayList<ICustomDrop>());
			}
			
			BlockDrops.get(block).add(drop);
		}
	}
	
	public static List<ItemStack> GetDrops(EntityType entity)
	{
		List<ICustomDrop> customDrops = EntityDrops.get(entity);
		if (customDrops == null)
		{
			return null;
		}
		
		List<ItemStack> drops = new ArrayList<ItemStack>();
		for (ICustomDrop drop : customDrops)
		{
			ItemStack item = drop.getItemStack();
			if (item == null)
				continue;
			if (item.getAmount() == 0)
				continue;
			
			drops.add(item);
		}
		
		return drops;
	}
	
	public static List<ItemStack> GetDrops(Material block)
	{
		List<ICustomDrop> customDrops = BlockDrops.get(block);
		if (customDrops == null)
		{
			return null;
		}
		
		List<ItemStack> drops = new ArrayList<ItemStack>();
		for (ICustomDrop drop : customDrops)
		{
			ItemStack item = drop.getItemStack();
			if (item == null)
				continue;
			if (item.getAmount() == 0)
				continue;
			
			drops.add(item);
		}
		
		return drops;
	}
}
