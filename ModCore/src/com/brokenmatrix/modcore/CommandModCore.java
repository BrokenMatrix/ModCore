package com.brokenmatrix.modcore;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.plugin.java.JavaPlugin;

import com.brokenmatrix.modcore.items.CustomItem;
import com.brokenmatrix.modcore.items.CustomItems;
import com.brokenmatrix.modcore.tools.Helper;

import net.md_5.bungee.api.ChatColor;

public class CommandModCore implements CommandExecutor
{
	private ModCore plugin;
	
	public CommandModCore(ModCore plugin)
	{
		this.plugin = plugin;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String Label, String[] args)
	{
		String message = null;
		
		if (args.length > 0)
		{
			if (args[0].equals("help"))
			{
				message = getHelpMessage();
			}
			else if (args[0].equals("info"))
			{
				message = getInformationMessage();
			}
			else if (args[0].equals("give"))
			{
				message = giveCommand(sender, args);
			}
			else if (args[0].equals("dupe"))
			{
				message = dupeCommand(sender);
			}
		}
		if (message == null)
		{
			message = ChatColor.RED + "Provided arguments are not valid\n" + getHelpMessage();
		}
		
		Helper.sendMessage(sender, message);
		
		return true;
	}
	
	private String dupeCommand(CommandSender sender)
	{
		if (sender.hasPermission("modcore.give"))
		{
			PlayerInventory inventory = Bukkit.getPlayer(sender.getName()).getInventory();
			
			for (ItemStack s : inventory.getStorageContents())
			{
				if (s != null)
				{
					CustomItem i = Helper.GetItem(s);
					
					if (i != null)
					{
						inventory.addItem(i.getItemStack(s.getAmount()));
					}
				}
			}
			
			return ChatColor.GREEN + "Duplicated all custom items in your inventory!";
		}
		
		return ChatColor.RED + "That would be cheating!";
	}
	
	private String giveCommand(CommandSender sender, String[] args)
	{
		if (sender.hasPermission("modcore.give"))
		{
			if (args.length == 4)
			{
				Player player = Bukkit.getPlayer(args[1]);
				
				if (player != null)
				{
					CustomItem item = CustomItems.GetItemUnsafe(args[2]);
					
					if (item != null)
					{
						int amount = Integer.parseInt(args[3]);
						
						if (amount > 0)
						{
							player.getInventory().addItem(item.getItemStack(amount));
							
							return ChatColor.GREEN + "Gave " + player.getName() + " " + amount + " of " + item.getName();
						}
						else
						{
							return ChatColor.RED + "The item amount must be greater than zero.";
						}
					}
					else
					{
						return ChatColor.RED + "'" + args[2] + "' is not a valid item! Do not use spaces or color codes.";
					}
				}
				else
				{
					return ChatColor.RED + args[1] + " is not a valid player!";
				}
			}
			else
			{
				return ChatColor.RED + "Give needs 3 arguments, <player>, <item> and <amount>!";
			}
		}
		else
		{
			return ChatColor.RED + "You aren't allow to cheat!";
		}
	}
	
	private String getHelpMessage()
	{
		StringBuilder message = new StringBuilder();
		
		message.append(ChatColor.YELLOW + "ModCore Help\n");
		message.append("   /mc help - Displays the help page\n");
		message.append("   /mc info - Displays information about the information.\n");
		message.append("   /mc give <player> <item> <amount> - Gives a player\n   the specified custom item\n");
		message.append("   /mc dupe - Duplicates all custom items in your inventory");
		
		return message.toString();
	}
	
	private String getInformationMessage()
	{
		StringBuilder message = new StringBuilder();
		
		message.append(ChatColor.YELLOW + "ModCore Information\n");
		message.append("   Version:" + plugin.getDescription().getVersion() + "\n");
		message.append("   Authors:\n");
		for (String author : plugin.getDescription().getAuthors())
		{
			message.append("      " + author + "\n");
		}
		message.append("   Mods:\n");
		for (JavaPlugin mod : plugin.getMods())
		{
			message.append("      " + mod.getName() + "\n");
		}
		
		return message.toString();
	}
}
