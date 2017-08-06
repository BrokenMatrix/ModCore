package com.brokenmatrix.modcore.blocks;

import java.util.ArrayList;
import java.util.List;

public final class Blocks
{
	private static List<CustomBlock> Blocks;
	
	static
	{
		Blocks = new ArrayList<CustomBlock>();
	}
	
	public static void Register(CustomBlock block)
	{
		block.setID(Blocks.size());
		Blocks.add(block);
	}
	
	public static CustomBlock GetBlock(int index)
	{
		if (index >= Blocks.size())
		{
			System.err.println("Block index " + index + " is not valid!");
			return null;
		}
		
		return Blocks.get(index);
	}
}
