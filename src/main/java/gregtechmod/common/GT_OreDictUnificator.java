package gregtechmod.common;

import gregtechmod.GT_Mod;

import java.util.HashMap;
import java.util.Iterator;

import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

public class GT_OreDictUnificator {
	
	public static HashMap<String, ItemStack> sMap = new HashMap<String, ItemStack>();
	
	public static void add(String aOreDictName, ItemStack aOreStack) {
		set(aOreDictName, aOreStack, false);
	}

	public static void set(String aOreDictName, ItemStack aOreStack) {
		set(aOreDictName, aOreStack, true);
	}
	
	public static void set(String aOreDictName, ItemStack aOreStack, boolean aOverwrite) {
		if (aOreDictName == null || aOreDictName.equals("") || aOreDictName.startsWith("itemDust") || aOreStack == null || aOreStack.getItemDamage() < 0) return;
		if (sMap.get(aOreDictName) == null) {
			sMap.put(aOreDictName, aOreStack.copy());
			GT_ModHandler.registerOre(aOreDictName, aOreStack);
		} else {
			try {
				if (aOverwrite && GT_Mod.mConfig.mConfigFileAdvRecipes.get("specialunificationtargets", aOreStack.getItemName(), true).getBoolean(true)) {
					sMap.remove(aOreDictName);
					sMap.put(aOreDictName, aOreStack.copy());
				}
			} catch(Throwable e) {}
			GT_ModHandler.registerOre(aOreDictName, aOreStack);
		}
	}
	
	public static ItemStack get(ItemStack aOreStack) {
		if (aOreStack == null) return null;
		Iterator<String> tIterator = sMap.keySet().iterator();
		while (tIterator.hasNext()) {
			String tName = tIterator.next();
			Iterator<ItemStack> tOreDictStackList = OreDictionary.getOres(tName).iterator();
			while (tOreDictStackList.hasNext()) {
				if (aOreStack.isItemEqual(tOreDictStackList.next())) {
					ItemStack rStack = sMap.get(tName).copy();
					rStack.stackSize = aOreStack.stackSize;
					return rStack;
				}
			}
		}
		return null;
	}
	
	public static ItemStack get(String aOreDictName, ItemStack aReplacement, int aAmount) {
		ItemStack rStack = sMap.get(aOreDictName);
		if (!sMap.containsKey(aOreDictName)) GT_Log.out.println("Unknown Key for Unification, Typo? " + aOreDictName);
		if (rStack == null) rStack = aReplacement; else rStack = rStack.copy();
		if (rStack != null) rStack.stackSize = aAmount;
		return rStack;
	}
}
