package com.brokenmatrix.modcore;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import com.brokenmatrix.modcore.tools.DataStorage;

public class ModCore extends JavaPlugin
{
	private FileConfiguration config;
	private List<JavaPlugin> mods;
	
	@Override
	public void onLoad()
	{
		mods = new ArrayList<JavaPlugin>();
	}
	
	@Override
	public void onEnable()
	{
		saveDefaultConfig();
		loadData();
		
		this.getCommand("modcore").setExecutor(new CommandModCore(this));
		
		getServer().getPluginManager().registerEvents(new EventListener(), this);
	}
	
	@Override
	public void onDisable()
	{
		save();
	}
	
	public void notifyOfMod(JavaPlugin mod)
	{
		mods.add(mod);
	}
	
	public List<JavaPlugin> getMods()
	{
		return mods;
	}
	
	private void loadData()
	{
		config = getConfig();
		
		List<String> customBlocks = config.getStringList("customBlocks");
		List<String> extraBlockData = config.getStringList("extraBlockData");
		
		if (customBlocks == null)
		{
			customBlocks = new ArrayList<String>();
		}
		if (extraBlockData == null)
		{
			extraBlockData = new ArrayList<String>();
		}
		
		DataStorage.Load(customBlocks, extraBlockData);
	}
	
	private void save()
	{
		File file = new File(getDataFolder(), "config.yml");
		
		List<String> customBlocks = new ArrayList<String>();
		List<String> extraBlockData = new ArrayList<String>();
		
		DataStorage.Save(customBlocks, extraBlockData);
		
		try
		{
			config.set("customBlocks", customBlocks);
			config.set("extraBlockData", extraBlockData);
			config.save(file);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
}
