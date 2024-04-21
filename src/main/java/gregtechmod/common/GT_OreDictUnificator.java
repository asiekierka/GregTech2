/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraftforge.oredict.OreDictionary
 *  net.minecraftforge.oredict.OreDictionary$OreRegisterEvent
 */
package gregtechmod.common;

import gregtechmod.GT_Mod;
import gregtechmod.common.GT_Log;
import gregtechmod.common.GT_ModHandler;
import gregtechmod.common.GT_Utility;
import java.util.ArrayList;
import java.util.HashMap;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

public class GT_OreDictUnificator {
    private static HashMap<String, ItemStack> sName2OreMap = new HashMap<String, ItemStack>();
    private static HashMap<Integer, String> sItemhash2NameMap = new HashMap<Integer, String>();
    private static ArrayList<Integer> sDyeList = new ArrayList<Integer>();

    public static void add(String aOreDictName, ItemStack aOreStack) {
        GT_OreDictUnificator.set(aOreDictName, aOreStack, false);
    }

    public static void set(String aOreDictName, ItemStack aOreStack) {
        GT_OreDictUnificator.set(aOreDictName, aOreStack, true);
    }

    public static void set(String aOreDictName, ItemStack aOreStack, boolean aOverwrite) {
        if (aOreDictName == null || aOreDictName.equals("") || aOreDictName.startsWith("itemDust") || aOreStack == null || aOreStack.getItemDamage() < 0) {
            return;
        }
        aOreStack = aOreStack.copy().splitStack(1);
        GT_OreDictUnificator.addAssociation(aOreDictName, aOreStack);
        if (sName2OreMap.get(aOreDictName) == null) {
            sName2OreMap.put(aOreDictName, aOreStack);
            GT_OreDictUnificator.registerOre(aOreDictName, aOreStack);
        } else {
            if (aOverwrite && GT_Mod.mConfig.addAdvConfig("specialunificationtargets", aOreStack, true)) {
                sName2OreMap.remove(aOreDictName);
                sName2OreMap.put(aOreDictName, aOreStack);
            }
            GT_OreDictUnificator.registerOre(aOreDictName, aOreStack);
        }
    }

    public static void override(String aOreDictName, ItemStack aOreStack) {
        if (aOreDictName == null || aOreDictName.equals("") || aOreDictName.startsWith("itemDust") || aOreStack == null || aOreStack.getItemDamage() < 0) {
            return;
        }
        if (aOreStack.getItemName() == null || aOreStack.getItemName().equals("") || GT_Mod.mConfig.addAdvConfig("specialunificationtargets", aOreStack, true)) {
            GT_OreDictUnificator.set(aOreDictName, aOreStack);
        }
    }

    public static ItemStack getFirstOre(String aOreDictName, int aAmount) {
        if (sName2OreMap.containsKey(aOreDictName)) {
            return GT_OreDictUnificator.get(aOreDictName, null, aAmount);
        }
        ItemStack rStack = null;
        ArrayList<ItemStack> tList = GT_OreDictUnificator.getOres(aOreDictName);
        if (tList.size() > 0) {
            rStack = ((ItemStack)tList.get(0)).copy();
        }
        if (rStack != null) {
            rStack.stackSize = aAmount;
        }
        return rStack;
    }

    public static ItemStack getFirstCapsulatedOre(String aOreDictName, int aAmount) {
        if (sName2OreMap.containsKey(aOreDictName)) {
            return GT_OreDictUnificator.get(aOreDictName, null, aAmount);
        }
        ItemStack rStack = null;
        ArrayList<ItemStack> tList = GT_OreDictUnificator.getOres(aOreDictName);
        for (ItemStack tStack : tList) {
            if (tStack == null || GT_ModHandler.getCapsuleCellContainerCount(tStack) != 1) continue;
            rStack = tStack.copy().splitStack(aAmount);
            break;
        }
        return rStack;
    }

    public static ItemStack getFirstUnCapsulatedOre(String aOreDictName, int aAmount) {
        if (sName2OreMap.containsKey(aOreDictName)) {
            return GT_OreDictUnificator.get(aOreDictName, null, aAmount);
        }
        ItemStack rStack = null;
        ArrayList<ItemStack> tList = GT_OreDictUnificator.getOres(aOreDictName);
        for (ItemStack tStack : tList) {
            if (tStack == null || GT_ModHandler.getCapsuleCellContainerCount(tStack) > 0) continue;
            rStack = tStack.copy().splitStack(aAmount);
            break;
        }
        return rStack;
    }

    public static ItemStack get(String aOreDictName, int aAmount) {
        return GT_OreDictUnificator.get(aOreDictName, null, aAmount);
    }

    public static ItemStack get(String aOreDictName, ItemStack aReplacement, int aAmount) {
        ItemStack rStack = (ItemStack)sName2OreMap.get(aOreDictName);
        if (!sName2OreMap.containsKey(aOreDictName) && aReplacement == null) {
            GT_Log.out.println("Unknown Key for Unification, Typo? " + aOreDictName);
        }
        if ((rStack = rStack == null ? (aReplacement == null ? null : aReplacement.copy()) : rStack.copy()) != null) {
            rStack.stackSize = aAmount;
        }
        return rStack;
    }

    public static ItemStack get(ItemStack aOreStack) {
        if (aOreStack == null) {
            return null;
        }
        String tName = (String)sItemhash2NameMap.get(GT_Utility.stackToInt(aOreStack));
        ItemStack rStack = null;
        if (tName != null) {
            rStack = (ItemStack)sName2OreMap.get(tName);
        }
        if ((rStack = rStack == null ? aOreStack.copy() : rStack.copy()) != null) {
            rStack.stackSize = aOreStack.stackSize;
        }
        return rStack;
    }

    public static void addAssociation(String aOreDictName, ItemStack aOreStack) {
        if (aOreDictName == null || aOreDictName.equals("") || aOreStack == null) {
            return;
        }
        sItemhash2NameMap.put(GT_Utility.stackToInt(aOreStack), aOreDictName);
    }

    public static String getAssociation(ItemStack aOreStack) {
        return (String)sItemhash2NameMap.get(GT_Utility.stackToInt(aOreStack));
    }

    public static boolean isItemStackInstanceOf(ItemStack aOreStack, String aOreName, boolean aPrefix) {
        if (aOreStack == null || aOreName == null || aOreName.equals("")) {
            return false;
        }
        String tString = (String)sItemhash2NameMap.get(GT_Utility.stackToInt(aOreStack));
        if (tString == null) {
            ItemStack tOreStack = aOreStack.copy();
            tOreStack.setItemDamage(-1);
            tString = (String)sItemhash2NameMap.get(GT_Utility.stackToInt(tOreStack));
            if (tString == null) {
                if (!aPrefix) {
                    for (ItemStack tOreStacks : GT_OreDictUnificator.getOres(aOreName)) {
                        if (!GT_Utility.areStacksEqual(tOreStacks, aOreStack)) continue;
                        return true;
                    }
                }
                return false;
            }
        }
        return aPrefix ? tString.startsWith(aOreName) : tString.equals(aOreName);
    }

    public static boolean isItemStackDye(ItemStack aStack) {
        return aStack != null && sDyeList.contains(GT_Utility.stackToInt(aStack));
    }

    public static void addDye(ItemStack aStack) {
        if (aStack != null) {
            sDyeList.add(GT_Utility.stackToInt(aStack));
        }
    }

    public static void registerOre(String aName, ItemStack aStack) {
        if (aName == null || aName.equals("") || aStack == null || aStack.getItem() == null) {
            return;
        }
        ArrayList tList = GT_OreDictUnificator.getOres(aName);
        for (int i = 0; i < tList.size(); ++i) {
            if (!((ItemStack)tList.get(i)).isItemEqual(aStack)) continue;
            return;
        }
        aStack = aStack.copy().splitStack(1);
        if (aName.equals("dustGunpowder")) {
            GT_Mod.sOreDict.registerOre(new OreDictionary.OreRegisterEvent(aName, aStack));
        } else {
            OreDictionary.registerOre((String)aName, (ItemStack)aStack);
        }
    }

    public static ArrayList<ItemStack> getOres(String aOreName) {
        ArrayList<ItemStack> rList = new ArrayList<ItemStack>();
        rList.addAll(OreDictionary.getOres((String)aOreName));
        if (aOreName.equals("dustGunpowder")) {
            rList.add(new ItemStack(Item.gunpowder, 1, 0));
        }
        return rList;
    }
}

