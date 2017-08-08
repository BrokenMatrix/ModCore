package com.brokenmatrix.modcore;

import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemBreakEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;

import com.brokenmatrix.modcore.blocks.CustomBlocks;
import com.brokenmatrix.modcore.blocks.CustomBlock;
import com.brokenmatrix.modcore.items.CustomBlockItem;
import com.brokenmatrix.modcore.items.CustomItem;
import com.brokenmatrix.modcore.items.drops.CustomDrops;
import com.brokenmatrix.modcore.tools.DataStorage;
import com.brokenmatrix.modcore.tools.Helper;

import java.util.List;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDeathEvent;

public class EventListener implements Listener
{
	@EventHandler
	public void onBlockPlace(BlockPlaceEvent e)
	{
		if (!e.isCancelled() && e.getItemInHand() != null)
		{
			CustomItem item = Helper.GetItem(e.getItemInHand());
			
			if (item != null)
			{
				if (item instanceof CustomBlockItem)
				{
					CustomBlockItem blockItem = (CustomBlockItem) item;
					CustomBlock block = blockItem.getBlock();
					
					block.onPlace(e);
					if (!e.isCancelled())
					{
						DataStorage.Store(e.getBlockPlaced().getLocation(), block.getID());
					}
				}
			}
		}
	}
	
	@EventHandler
	public void onItemBreak(PlayerItemBreakEvent e)
	{
		CustomItem item = Helper.GetItem(e.getBrokenItem());
		
		if (item != null)
		{
			item.onBreak(e.getBrokenItem());
		}
	}
	
	@EventHandler
	public void onEntityInteract(PlayerInteractEntityEvent e)
	{
		if (!e.isCancelled() && e.getHand() == EquipmentSlot.HAND)
		{
			ItemStack holding = e.getPlayer().getItemOnCursor();
			
			if (holding != null)
			{
				CustomItem item = Helper.GetItem(holding);
				
				if (item != null)
				{
					item.onEntityInteract(e.getRightClicked(), holding);
				}
			}
		}
	}
	
	@EventHandler
	public void onBlockBreak(BlockBreakEvent e)
	{	
		if (!e.isCancelled() && e.getPlayer() != null)
		{
			ItemStack holding = e.getPlayer().getItemOnCursor();
			
			if (holding != null)
			{
				CustomItem item = Helper.GetItem(holding);
				
				if (item != null)
				{
					item.onBlockBroken(e);
				}
			}
			
			if (!e.isCancelled())
			{
				Location loc = e.getBlock().getLocation();
				
				int customBlock = DataStorage.GetBlock(loc);
				
				if (customBlock > -1)
				{
					CustomBlock block = CustomBlocks.GetBlock(customBlock);
					
					if (block != null)
					{
						block.onBreak(e);
						
						if (!e.isCancelled())
						{
							List<ItemStack> customDrops = block.getDrops();
							
							for (ItemStack item : customDrops)
							{
								e.getPlayer().getWorld().dropItemNaturally(loc, item);
							}
							
							DataStorage.RemoveBlock(loc);
							e.setDropItems(false);
						}
					}
				}
				else
				{
					List<ItemStack> customDrops = CustomDrops.GetDrops(e.getBlock().getType());
					
					if (customDrops != null)
					{
						for (ItemStack item : customDrops)
						{
							e.getPlayer().getWorld().dropItemNaturally(loc, item);
						}
					}
				}
			}
		}
	}
	
	@EventHandler
	public void onEntityDeath(EntityDeathEvent e)
	{
		Player killer = e.getEntity().getKiller();
		
		if (killer != null)
		{
			ItemStack holding = killer.getItemOnCursor();
			
			if (holding != null)
			{
				CustomItem item = Helper.GetItem(holding);
				
				if (item != null)
				{
					item.onKilledEntity(e);
				}
			}
		}
		
		List<ItemStack> customDrops = CustomDrops.GetDrops(e.getEntityType());
		
		if (customDrops != null)
		{
			e.getDrops().addAll(customDrops);
		}
	}
	
	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent e)
	{
		if (!e.isCancelled() && e.hasItem())
		{
			CustomItem item = Helper.GetItem(e.getItem());
			
			if (item != null)
			{
				if (e.getAction() == Action.LEFT_CLICK_AIR)
				{
					item.onLeftClick(true, e.getPlayer(), e.getItem());
				}
				else if (e.getAction() == Action.RIGHT_CLICK_AIR)
				{
					item.onRightClick(true, e.getPlayer(), e.getItem());
				}
				else if (e.getAction() == Action.LEFT_CLICK_BLOCK)
				{
					item.onLeftClick(false, e.getPlayer(), e.getItem());
				}
				else if (e.getAction() == Action.RIGHT_CLICK_BLOCK)
				{
					item.onRightClick(false, e.getPlayer(), e.getItem());
				}
			}
		}
	}
}