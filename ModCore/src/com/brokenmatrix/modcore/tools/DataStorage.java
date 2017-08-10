package com.brokenmatrix.modcore.tools;

import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;

public final class DataStorage
{
	private static HashMap<Location, Integer> CustomBlocks;
	private static HashMap<Location, String> ExtraBlockData;
	
	public static void Store(Location loc, int val)
	{
		CustomBlocks.put(loc, val);
	}
	
	public static void Store(Location loc, String val)
	{
		ExtraBlockData.put(loc, val);
	}
	
	public static void RemoveBlock(Location loc)
	{
		CustomBlocks.remove(loc);
	}
	
	public static void RemoveData(Location loc)
	{
		ExtraBlockData.remove(loc);
	}
	
	public static int GetBlock(Location loc)
	{
		if (!CustomBlocks.containsKey(loc))
		{
			return -1;
		}
		
		return CustomBlocks.get(loc);
	}
	
	public static String GetValue(Location loc)
	{
		return ExtraBlockData.get(loc);
	}
	
	public static void Load(List<String> customBlocks, List<String> extraBlockData)
	{
		CustomBlocks = new HashMap<Location, Integer>();
		ExtraBlockData = new HashMap<Location, String>();
		
		for (String s : customBlocks)
		{
			String[] parts = s.split(",");
			int x = Integer.parseInt(parts[1]);
			int y = Integer.parseInt(parts[2]);
			int z = Integer.parseInt(parts[3]);
			int id = Integer.parseInt(parts[4]);
			
			World world = Bukkit.getWorld(parts[0]);
			CustomBlocks.put(new Location(world, x, y, z), id);
		}
		
		for (String s : extraBlockData)
		{
			String[] parts = s.split(",");
			int x = Integer.parseInt(parts[1]);
			int y = Integer.parseInt(parts[2]);
			int z = Integer.parseInt(parts[3]);
			
			World world = Bukkit.getWorld(parts[0]);
			ExtraBlockData.put(new Location(world, x, y, z), parts[4]);
		}
	}
	
	public static void Save(List<String> customBlocks, List<String> extraBlockData)
	{
		for (Entry<Location, Integer> entry : CustomBlocks.entrySet())
		{
			Location loc = entry.getKey();
			World world = loc.getWorld();
			int val = entry.getValue();
			
			customBlocks.add(world.getName() + "," + loc.getBlockX() + "," + loc.getBlockY() + "," + loc.getBlockZ() + "," + val);
		}
		
		for (Entry<Location, String> entry : ExtraBlockData.entrySet())
		{
			Location loc = entry.getKey();
			World world = loc.getWorld();
			String val = entry.getValue();
			
			extraBlockData.add(world.getName() + "," + loc.getBlockX() + "," + loc.getBlockY() + "," + loc.getBlockZ() + "," + val);
		}
	}
}
