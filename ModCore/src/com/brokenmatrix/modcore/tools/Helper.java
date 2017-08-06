package com.brokenmatrix.modcore.tools;

import java.util.Random;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.brokenmatrix.modcore.items.CustomItem;
import com.brokenmatrix.modcore.items.CustomItems;

public final class Helper
{
	private static Random Random;
	
	static
	{
		Random = new Random();
	}
	
	public static void sendMessage(CommandSender sender, String message)
	{
		if (sender != null && message != null)
		{
			String[] s = message.split("\n");
			
			for (String m : s)
			{
				sender.sendMessage(m);
		    }
		}
	}
	
	public static void sendMessage(Player player, String message)
	{
		if (player != null && message != null && player.isOnline())
		{
			String[] s = message.split("\n");
			
			for (String m : s)
			{
				player.sendMessage(m);
		    }
		}
	}
	
	public static String WithoutColorsAndSpaces(String s)
	{
		StringBuilder noColors = new StringBuilder();
		
		char[] chars = s.toCharArray();
		boolean skip = false;
		for (char c : chars)
		{
			if (skip)
			{
				skip = false;
				continue;
			}
			
			if (c == '&')
			{
				skip = true;
				continue;
			}
			
			if (c == ' ')
			{
				continue;
			}
			
			noColors.append(c);
		}
		
		return noColors.toString();
	}
	
	public static int NextInt(int cap)
	{
		return Random.nextInt(cap + 1);
	}
	
	public static CustomItem GetItem(ItemStack from)
	{
		if (from.hasItemMeta())
		{
			if (from.getItemMeta().hasDisplayName())
			{
				return CustomItems.GetItem(from.getItemMeta().getDisplayName());
			}
		}
		
		return null;
	}
}
