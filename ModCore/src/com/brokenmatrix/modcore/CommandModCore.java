package com.brokenmatrix.modcore;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
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
								
								if (amount <= 0)
								{
									player.getInventory().addItem(item.getItemStack(amount));
									
									message = ChatColor.GREEN + "Gave " + player.getName() + " " + amount + " of " + item.getName() + "!";
								}
								else
								{
									message = ChatColor.RED + "The item amount must be greater than zero.";
								}
							}
							else
							{
								message = ChatColor.RED + "'" + args[2] + "' is not a valid item! Do not use spaces or color codes.";
							}
						}
						else
						{
							message = ChatColor.RED + args[1] + " is not a valid player!";
						}
					}
					else
					{
						message = ChatColor.RED + "Give needs 3 arguments, <player>, <item> and <amount>!";
					}
				}
				else
				{
					message = ChatColor.RED + "You aren't allow to cheat!";
				}
			}
		}
		if (message == null)
		{
			message = ChatColor.RED + "Provided arguments are not valid\n" + getHelpMessage();
		}
		
		Helper.sendMessage(sender, message);
		
		return true;
	}
	
	private String getHelpMessage()
	{
		StringBuilder message = new StringBuilder();
		
		message.append(ChatColor.YELLOW + "ModCore Help\n");
		message.append("   /mc help - Displays the help page\n");
		message.append("   /mc info - Displays information about the information.\n");
		message.append("   /mc give <player> <item> <amount> - Gives a player\n   the specified custom item\n");
		
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
