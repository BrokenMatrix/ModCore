package com.brokenmatrix.modcore.items;

import java.util.HashMap;

import org.bukkit.inventory.meta.ItemMeta.Spigot;

import com.brokenmatrix.modcore.tools.Helper;

public final class CustomItems extends Spigot
{
	private static HashMap<String, CustomItem> Items;
	private static HashMap<String, String> NoneToColor;
	
	static
	{
		Items = new HashMap<String, CustomItem>();
		NoneToColor = new HashMap<String, String>();
	}
	
	public static void Register(CustomItem item)
	{
		if (!Items.containsKey(item.getName()))
		{
			Items.put(item.getName(), item);
			NoneToColor.put(Helper.WithoutColorsAndSpaces(item.getName()), item.getName());
		}
		else
		{
			System.err.println("CUSTOM ITEM ERROR: An item was already registered with the name '"  + item.getName() + "'!");
		}
	}
	
	public static CustomItem GetItem(String item)
	{
		return Items.get(item);
	}
	
	public static CustomItem GetItemUnsafe(String item)
	{
		return Items.get(NoneToColor.get(item));
	}
}
