/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  appeng.api.Util
 *  forestry.api.core.ItemInterface
 *  forestry.api.recipes.RecipeManagers
 *  ic2.api.Direction
 *  ic2.api.Ic2Recipes
 *  ic2.api.Items
 *  ic2.api.energy.EnergyNet
 *  ic2.api.energy.tile.IEnergySource
 *  ic2.core.IC2
 *  ic2.core.item.ItemScrapbox
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.inventory.Container
 *  net.minecraft.inventory.InventoryCrafting
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.item.crafting.CraftingManager
 *  net.minecraft.item.crafting.FurnaceRecipes
 *  net.minecraft.item.crafting.IRecipe
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.world.World
 *  net.minecraftforge.liquids.LiquidStack
 *  net.minecraftforge.oredict.ShapedOreRecipe
 *  railcraft.common.api.core.items.ItemRegistry
 *  railcraft.common.api.crafting.RailcraftCraftingManager
 *  railcraft.common.api.fuel.FuelManager
 *  thermalexpansion.api.core.ItemRegistry
 *  thermalexpansion.api.crafting.CraftingHelpers
 *  thermalexpansion.api.crafting.CraftingManagers
 */
package gregtechmod.common;

import appeng.api.Util;
import forestry.api.core.ItemInterface;
import forestry.api.recipes.RecipeManagers;
import gregtechmod.GT_Mod;
import gregtechmod.api.ICapsuleCellContainer;
import gregtechmod.common.GT_Log;
import gregtechmod.common.GT_OreDictUnificator;
import gregtechmod.common.GT_Utility;
import gregtechmod.common.items.GT_MetaItem_Dust;
import gregtechmod.common.items.GT_MetaItem_SmallDust;
import ic2.api.Direction;
import ic2.api.Ic2Recipes;
import ic2.api.Items;
import ic2.api.energy.EnergyNet;
import ic2.api.energy.tile.IEnergySource;
import ic2.core.IC2;
import ic2.core.item.ItemScrapbox;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.liquids.LiquidStack;
import net.minecraftforge.oredict.ShapedOreRecipe;
import railcraft.common.api.core.items.ItemRegistry;
import railcraft.common.api.crafting.RailcraftCraftingManager;
import railcraft.common.api.fuel.FuelManager;
import thermalexpansion.api.crafting.CraftingHelpers;
import thermalexpansion.api.crafting.CraftingManagers;

public class GT_ModHandler {
    public static ItemStack mTCFluxEssence = null;
    public static ItemStack mTCWispEssence = null;
    public static ItemStack mTCResource = null;
    public static ItemStack mTCCrystal = null;
    public static ItemStack mNuggetIron = null;
    public static ItemStack mNuggetCopper = null;
    public static ItemStack mNuggetTin = null;
    public static ItemStack mNuggetSilver = null;
    public static ItemStack mNuggetLead = null;
    public static ItemStack mForcicium = null;
    public static ItemStack mIronNugget = null;
    public static ItemStack mSilverNugget = null;
    public static ItemStack mTinNugget = null;
    public static ItemStack mCopperNugget = null;
    public static ItemStack mRuby = null;
    public static ItemStack mGreenSapphire = null;
    public static ItemStack mSapphire = null;
    public static ItemStack mSilver = null;
    public static ItemStack mTin = null;
    public static ItemStack mCopper = null;
    public static ItemStack mNikolite = null;
    public static ItemStack mRedAlloy = null;
    public static ItemStack mBlueAlloy = null;
    public static ItemStack mBrass = null;
    public static ItemStack mSiliconBoule = null;
    public static ItemStack mSiliconWafer = null;
    public static ItemStack mBlueWafer = null;
    public static ItemStack mRedWafer = null;
    public static ItemStack mRPTinPlate = null;
    public static ItemStack mFineCopper = null;
    public static ItemStack mFineIron = null;
    public static ItemStack mCopperCoil = null;
    public static ItemStack mBlutricMotor = null;
    public static ItemStack mCanvas = null;
    public static ItemStack mDiamondDrawplate = null;
    public static ItemStack mBCWoodGear = null;
    public static ItemStack mBCStoneGear = null;
    public static ItemStack mBCIronGear = null;
    public static ItemStack mBCGoldGear = null;
    public static ItemStack mBCDiamondGear = null;
    public static ItemStack mIC_Cell = null;

    public static boolean isIC2loaded() {
        return GT_ModHandler.getIC2Item("resin", 1) != null;
    }

    public static boolean isRCloaded() {
        return GT_ModHandler.getRCItem("machine.alpha.rolling.machine", 1) != null;
    }

    public static boolean isTEloaded() {
        return GT_ModHandler.getTEItem("slag", 1) != null;
    }

    public static ItemStack getEmptyCell(int aAmount) {
        if (mIC_Cell == null) {
            mIC_Cell = GT_ModHandler.getIC2Item("cell", 1);
        }
        return new ItemStack(mIC_Cell.getItem(), aAmount, 0);
    }

    public static ItemStack getIC2Item(String aItem, int aAmount) {
        if (aItem == null || aItem.equals("")) {
            return null;
        }
        ItemStack rStack = null;
        rStack = Items.getItem((String)aItem);
        if (rStack == null) {
            return null;
        }
        rStack = rStack.copy();
        rStack.stackSize = aAmount;
        return rStack;
    }

    public static ItemStack getIC2Item(String aItem, int aAmount, int aMeta) {
        if (aItem == null || aItem.equals("")) {
            return null;
        }
        ItemStack rStack = null;
        rStack = Items.getItem((String)aItem);
        if (rStack == null) {
            return null;
        }
        rStack = rStack.copy();
        rStack.stackSize = aAmount;
        rStack.setItemDamage(aMeta);
        return rStack;
    }

    public static ItemStack getRCItem(String aItem, int aAmount) {
        if (aItem == null || aItem.equals("")) {
            return null;
        }
        ItemStack rStack = null;
        try {
            rStack = ItemRegistry.getItem((String)aItem, (int)aAmount);
        }
        catch (Throwable throwable) {
            // empty catch block
        }
        if (rStack == null) {
            return null;
        }
        rStack = rStack.copy();
        rStack.stackSize = aAmount;
        return rStack;
    }

    public static ItemStack getTEItem(String aItem, int aAmount) {
        if (aItem == null || aItem.equals("")) {
            return null;
        }
        ItemStack rStack = null;
        try {
            rStack = thermalexpansion.api.core.ItemRegistry.getItem((String)aItem, (int)aAmount);
        }
        catch (Throwable throwable) {
            // empty catch block
        }
        if (rStack == null) {
            return null;
        }
        rStack = rStack.copy();
        rStack.stackSize = aAmount;
        return rStack;
    }

    public static ItemStack getFRItem(String aItem, int aAmount) {
        if (aItem == null || aItem.equals("")) {
            return null;
        }
        ItemStack rStack = null;
        try {
            rStack = ItemInterface.getItem((String)aItem);
        }
        catch (Throwable throwable) {
            // empty catch block
        }
        if (rStack == null) {
            return null;
        }
        rStack = rStack.copy();
        rStack.stackSize = aAmount;
        return rStack;
    }

    public static ItemStack getFuelCan(int aValue) {
        if (aValue <= 0) {
            return GT_ModHandler.getIC2Item("fuelCan", 1);
        }
        ItemStack rFuelCanStack = GT_ModHandler.getIC2Item("filledFuelCan", 1);
        if (rFuelCanStack == null) {
            return null;
        }
        NBTTagCompound tNBT = new NBTTagCompound();
        tNBT.setInteger("value", aValue);
        rFuelCanStack.setTagCompound(tNBT);
        return rFuelCanStack;
    }

    public static int getFuelCanValue(ItemStack aFuelCan) {
        if (aFuelCan == null || !aFuelCan.isItemEqual(GT_ModHandler.getIC2Item("filledFuelCan", 1))) {
            return 0;
        }
        NBTTagCompound tNBT = aFuelCan.getTagCompound();
        if (tNBT == null) {
            return 0;
        }
        return tNBT.getInteger("value") * 5;
    }

    public static void addBoilerFuel(LiquidStack aLiquid, int aValue) {
        if (aLiquid == null || aValue <= 0) {
            return;
        }
        if (!GT_Mod.mConfig.addAdvConfig("boilerfuels", aLiquid.asItemStack(), true)) {
            return;
        }
        try {
            FuelManager.addBoilerFuel((LiquidStack)aLiquid, (int)aValue);
        }
        catch (Throwable throwable) {
            // empty catch block
        }
    }

    public static boolean getModeKeyDown(EntityPlayer aPlayer) {
        try {
            return IC2.keyboard.isModeSwitchKeyDown(aPlayer);
        }
        catch (Throwable throwable) {
            return false;
        }
    }

    public static boolean getBoostKeyDown(EntityPlayer aPlayer) {
        try {
            return IC2.keyboard.isBoostKeyDown(aPlayer);
        }
        catch (Throwable throwable) {
            return false;
        }
    }

    public static boolean getJumpKeyDown(EntityPlayer aPlayer) {
        try {
            return IC2.keyboard.isJumpKeyDown(aPlayer);
        }
        catch (Throwable throwable) {
            return false;
        }
    }

    public static short convertIC2DirectionToShort(Direction aDirection) {
        switch (aDirection) {
            case XP: {
                return 1;
            }
            case YN: {
                return 2;
            }
            case YP: {
                return 3;
            }
            case ZN: {
                return 4;
            }
            case ZP: {
                return 5;
            }
        }
        return 0;
    }

    public static boolean addValuableOre(int aID, int aMeta, int aValue) {
        if (aValue <= 0) {
            return false;
        }
        IC2.addValuableOre((int)aID, (int)aMeta, (int)aValue);
        return true;
    }

    public static boolean addScrapboxDrop(float aChance, ItemStack aOutput) {
        if ((aOutput = GT_OreDictUnificator.get(aOutput)) == null || aChance <= 0.0f) {
            return false;
        }
        if (!GT_Mod.mConfig.addAdvConfig("scrapboxdrops", aOutput, true)) {
            return false;
        }
        Ic2Recipes.addScrapboxDrop((ItemStack)aOutput.copy().splitStack(1), (float)aChance);
        return true;
    }

    public static boolean addToRecyclerBlackList(ItemStack aRecycledStack) {
        if (aRecycledStack == null) {
            return false;
        }
        Ic2Recipes.addRecyclerBlacklistItem((ItemStack)aRecycledStack.copy().splitStack(1));
        return true;
    }

    public static boolean addSmeltingRecipe(ItemStack aInput, ItemStack aOutput) {
        aOutput = GT_OreDictUnificator.get(aOutput);
        if (aInput == null || aOutput == null) {
            return false;
        }
        if (!GT_Mod.mConfig.addAdvConfig("furnace", aInput, true)) {
            return false;
        }
        FurnaceRecipes.smelting().addSmelting(aInput.itemID, aInput.getItemDamage(), aOutput.copy(), 0.0f);
        return true;
    }

    public static boolean addSqueezerRecipe(ItemStack aInput, LiquidStack aOutput, int aTime) {
        if (aInput == null || aOutput == null) {
            return false;
        }
        if (!GT_Mod.mConfig.addAdvConfig("squeezer", aInput, true)) {
            return false;
        }
        try {
            RecipeManagers.squeezerManager.addRecipe(aTime > 0 ? aTime : 100, new ItemStack[]{aInput.copy()}, aOutput);
        }
        catch (Throwable e) {
            return false;
        }
        return true;
    }

    public static boolean addLiquidTransposerRecipe(ItemStack aEmptyContainer, LiquidStack aLiquid, ItemStack aFullContainer, int aMJ) {
        aFullContainer = GT_OreDictUnificator.get(aFullContainer);
        if (aEmptyContainer == null || aFullContainer == null || aLiquid == null) {
            return false;
        }
        if (!GT_Mod.mConfig.addAdvConfig("liquidtransposer", aFullContainer, true)) {
            return false;
        }
        try {
            CraftingManagers.transposerManager.addFillRecipe(aMJ, aEmptyContainer, aFullContainer, aLiquid, true, false);
        }
        catch (Throwable e) {
            // empty catch block
        }
        return true;
    }

    public static boolean addLiquidTransposerFillRecipe(ItemStack aEmptyContainer, LiquidStack aLiquid, ItemStack aFullContainer, int aMJ) {
        aFullContainer = GT_OreDictUnificator.get(aFullContainer);
        if (aEmptyContainer == null || aFullContainer == null || aLiquid == null) {
            return false;
        }
        if (!GT_Mod.mConfig.addAdvConfig("liquidtransposerfilling", aFullContainer, true)) {
            return false;
        }
        try {
            CraftingManagers.transposerManager.addFillRecipe(aMJ, aEmptyContainer, aFullContainer, aLiquid, false, false);
        }
        catch (Throwable e) {
            // empty catch block
        }
        return true;
    }

    public static boolean addLiquidTransposerEmptyRecipe(ItemStack aFullContainer, LiquidStack aLiquid, ItemStack aEmptyContainer, int aMJ) {
        aEmptyContainer = GT_OreDictUnificator.get(aEmptyContainer);
        if (aFullContainer == null || aEmptyContainer == null || aLiquid == null) {
            return false;
        }
        if (!GT_Mod.mConfig.addAdvConfig("liquidtransposeremptying", aFullContainer, true)) {
            return false;
        }
        try {
            CraftingManagers.transposerManager.addExtractionRecipe(aMJ, aFullContainer, aEmptyContainer, aLiquid, 100, false, false);
        }
        catch (Throwable e) {
            // empty catch block
        }
        return true;
    }

    public static boolean addExtractionRecipe(ItemStack aInput, ItemStack aOutput) {
        aOutput = GT_OreDictUnificator.get(aOutput);
        if (aInput == null || aOutput == null) {
            return false;
        }
        GT_Utility.removeSimpleIC2MachineRecipe(aInput, null, Ic2Recipes.getExtractorRecipes());
        if (!GT_Mod.mConfig.addAdvConfig("extractor", aInput, true)) {
            return false;
        }
        Ic2Recipes.addExtractorRecipe((ItemStack)aInput.copy(), (ItemStack)aOutput.copy());
        return true;
    }

    public static boolean addRCBlastFurnaceRecipe(ItemStack aInput, ItemStack aOutput, int aTime) {
        aOutput = GT_OreDictUnificator.get(aOutput);
        if (aInput == null || aOutput == null || aTime <= 0) {
            return false;
        }
        if (!GT_Mod.mConfig.addAdvConfig("rcblastfurnace", aInput, true)) {
            return false;
        }
        aInput = aInput.copy();
        aOutput = aOutput.copy();
        if (!GT_ModHandler.isRCloaded()) {
            return false;
        }
        RailcraftCraftingManager.blastFurnace.addRecipe(aInput.itemID, aInput.getItemDamage(), aTime, aOutput);
        return true;
    }

    public static boolean addPulverisationRecipe(ItemStack aInput, ItemStack aOutput1, ItemStack aOutput2, int aChance, boolean aOverwrite) {
        block17: {
            aOutput1 = GT_OreDictUnificator.get(aOutput1);
            aOutput2 = GT_OreDictUnificator.get(aOutput2);
            if (aInput == null || aOutput1 == null) {
                return false;
            }
            GT_Utility.removeSimpleIC2MachineRecipe(aInput, null, Ic2Recipes.getMaceratorRecipes());
            if (!GT_Mod.mConfig.addAdvConfig("maceration", aInput, true)) {
                return false;
            }
            Ic2Recipes.addMaceratorRecipe((ItemStack)aInput.copy(), (ItemStack)aOutput1.copy());
            if (!aOutput1.isItemEqual(GT_MetaItem_Dust.instance.getStack(15, 1)) && !aOutput1.isItemEqual(GT_MetaItem_SmallDust.instance.getStack(15, 1))) {
                try {
                    if (GT_OreDictUnificator.isItemStackInstanceOf(aInput, "ingot", true)) {
                        Util.getGrinderRecipeManage().addRecipe(aInput, aOutput1, 5);
                    }
                }
                catch (Throwable e) {
                    // empty catch block
                }
                try {
                    ItemStack tStack = aInput.copy();
                    tStack.stackSize = 1;
                    HashMap<ItemStack, Float> tOutput = new HashMap<ItemStack, Float>();
                    tOutput.put(aOutput1.copy(), Float.valueOf(1.0f / (float)aInput.stackSize));
                    if (aOutput2 != null) {
                        tOutput.put(aOutput2.copy(), Float.valueOf(0.01f * (float)(aChance <= 0 ? 10 : aChance)));
                    }
                    RailcraftCraftingManager.rockCrusher.addRecipe(tStack.copy(), tOutput);
                }
                catch (Throwable e) {
                    // empty catch block
                }
                try {
                    if (aOutput2 == null) {
                        CraftingManagers.pulverizerManager.addRecipe(400, aInput.copy(), aOutput1.copy(), aOverwrite);
                        break block17;
                    }
                    CraftingManagers.pulverizerManager.addRecipe(400, aInput.copy(), aOutput1.copy(), aOutput2.copy(), aChance <= 0 ? 10 : aChance, aOverwrite);
                }
                catch (Throwable e) {}
            } else {
                try {
                    if (aOutput2 == null) {
                        CraftingManagers.sawmillManager.addRecipe(80, aInput.copy(), aOutput1.copy(), aOverwrite);
                    } else {
                        CraftingManagers.sawmillManager.addRecipe(80, aInput.copy(), aOutput1.copy(), aOutput2.copy(), aChance <= 0 ? 10 : aChance, aOverwrite);
                    }
                }
                catch (Throwable e) {
                    // empty catch block
                }
            }
        }
        return true;
    }

    public static boolean addSawmillRecipe(ItemStack aInput1, ItemStack aOutput1, ItemStack aOutput2) {
        aOutput1 = GT_OreDictUnificator.get(aOutput1);
        aOutput2 = GT_OreDictUnificator.get(aOutput2);
        if (aInput1 == null || aOutput1 == null) {
            return false;
        }
        if (!GT_Mod.mConfig.addAdvConfig("sawmill", aInput1, true)) {
            return false;
        }
        try {
            CraftingManagers.sawmillManager.addRecipe(160, aInput1, aOutput1, aOutput2, 100, false);
        }
        catch (Throwable throwable) {
            // empty catch block
        }
        GT_Mod.addSawmillRecipe(aInput1, -1, aOutput1, aOutput2, GT_ModHandler.getEmptyCell(1));
        return true;
    }

    public static boolean addInductionSmelterRecipe(ItemStack aInput1, ItemStack aInput2, ItemStack aOutput1, ItemStack aOutput2, int aEnergy, int aChance) {
        aOutput1 = GT_OreDictUnificator.get(aOutput1);
        aOutput2 = GT_OreDictUnificator.get(aOutput2);
        if (aInput1 == null || aOutput1 == null) {
            return false;
        }
        if (!GT_Mod.mConfig.addAdvConfig("inductionsmelter", aOutput1, true)) {
            return false;
        }
        try {
            CraftingManagers.smelterManager.addRecipe(aEnergy, aInput1, aInput2, aOutput1, aOutput2, aChance, false);
        }
        catch (Throwable e) {
            // empty catch block
        }
        return true;
    }

    public static boolean addDustToIngotSmeltingRecipe(ItemStack aInput, ItemStack aOutput) {
        aOutput = GT_OreDictUnificator.get(aOutput);
        if (aInput == null || aOutput == null) {
            return false;
        }
        try {
            new CraftingHelpers();
            CraftingHelpers.addSmelterDustToIngotsRecipe((ItemStack)aInput, (ItemStack)aOutput);
        }
        catch (Throwable throwable) {
            // empty catch block
        }
        GT_ModHandler.addSmeltingRecipe(aInput, aOutput);
        return true;
    }

    public static boolean addOreToIngotSmeltingRecipe(ItemStack aInput, ItemStack aOutput) {
        aOutput = GT_OreDictUnificator.get(aOutput);
        if (aInput == null || aOutput == null) {
            return false;
        }
        try {
            new CraftingHelpers();
            CraftingHelpers.addSmelterOreToIngotsRecipe((ItemStack)aInput, (ItemStack)aOutput);
        }
        catch (Throwable throwable) {
            // empty catch block
        }
        FurnaceRecipes.smelting().addSmelting(aInput.itemID, aInput.getItemDamage(), aOutput.copy(), 0.0f);
        return true;
    }

    public static boolean addCompressionRecipe(ItemStack aInput, ItemStack aOutput) {
        aOutput = GT_OreDictUnificator.get(aOutput);
        if (aInput == null || aOutput == null) {
            return false;
        }
        GT_Utility.removeSimpleIC2MachineRecipe(aInput, null, Ic2Recipes.getCompressorRecipes());
        if (!GT_Mod.mConfig.addAdvConfig("compression", aInput, true)) {
            return false;
        }
        Ic2Recipes.addCompressorRecipe((ItemStack)aInput.copy(), (ItemStack)aOutput.copy());
        return true;
    }

    public static boolean addIC2MatterAmplifier(ItemStack aAmplifier, int aValue) {
        if (aAmplifier == null || aValue <= 0) {
            return false;
        }
        if (!GT_Mod.mConfig.addAdvConfig("massfabamplifier", aAmplifier, true)) {
            return false;
        }
        Ic2Recipes.addMatterAmplifier((ItemStack)aAmplifier.copy(), (int)aValue);
        return true;
    }

    public static boolean addRollingMachineRecipe(ItemStack aResult, Object[] aRecipe) {
        if ((aResult = GT_OreDictUnificator.get(aResult)) == null || aRecipe == null) {
            return false;
        }
        if (GT_Mod.instance.mRollingmachineAlloy) {
            try {
                RailcraftCraftingManager.rollingMachine.getRecipeList().add(new ShapedOreRecipe(aResult.copy(), aRecipe));
            }
            catch (Throwable e) {
                return GT_ModHandler.addCraftingRecipe(aResult.copy(), aRecipe);
            }
        } else {
            return GT_ModHandler.addCraftingRecipe(aResult.copy(), aRecipe);
        }
        return true;
    }

    public static boolean addUUMRecipe(ItemStack aResult, Object ... aRecipe) {
        if (GT_Mod.mConfig.addAdvConfig("uumrecipe", aResult, true)) {
            return GT_ModHandler.addCraftingRecipe(aResult, aRecipe);
        }
        return GT_ModHandler.addCraftingRecipe(null, aRecipe);
    }

    public static boolean addCraftingRecipe(ItemStack aResult, Object ... aRecipe) {
        aResult = GT_OreDictUnificator.get(aResult);
        if (aRecipe == null) {
            return false;
        }
        try {
            String shape = "";
            int idx = 0;
            if (aRecipe[idx] instanceof Boolean) {
                ++idx;
            }
            while (aRecipe[idx] instanceof String) {
                String s = (String)aRecipe[idx++];
                shape = shape + s;
                while (s.length() < 3) {
                    s = s + " ";
                }
                if (s.length() <= 3) continue;
                throw new IllegalArgumentException();
            }
            if (aRecipe[idx] instanceof Boolean) {
                ++idx;
            }
            HashMap<Character, ItemStack> itemMap = new HashMap<Character, ItemStack>();
            itemMap.put(Character.valueOf(' '), null);
            while (idx < aRecipe.length) {
                Character chr = (Character)aRecipe[idx];
                Object in = aRecipe[idx + 1];
                Object val = null;
                if (in instanceof ItemStack) {
                    itemMap.put(chr, ((ItemStack)in).copy());
                } else if (in instanceof String) {
                    ItemStack tStack = GT_OreDictUnificator.getFirstOre((String)in, 1);
                    if (tStack == null) break;
                    itemMap.put(chr, tStack);
                } else {
                    throw new IllegalArgumentException();
                }
                idx += 2;
            }
            ItemStack[] tRecipe = new ItemStack[9];
            int x = -1;
            for (char chr : shape.toCharArray()) {
                tRecipe[++x] = (ItemStack)itemMap.get(Character.valueOf(chr));
                if (tRecipe[x] == null || tRecipe[x].getItemDamage() >= 0) continue;
                tRecipe[x].setItemDamage(0);
            }
            GT_ModHandler.removeRecipe(tRecipe);
        }
        catch (Throwable e) {
            e.printStackTrace(GT_Log.out);
        }
        if (aResult == null) {
            return false;
        }
        Ic2Recipes.addCraftingRecipe((ItemStack)aResult.copy(), (Object[])aRecipe);
        return true;
    }

    public static boolean addShapelessCraftingRecipe(ItemStack aResult, Object ... aRecipe) {
        if ((aResult = GT_OreDictUnificator.get(aResult)) == null || aRecipe == null) {
            return false;
        }
        try {
            ItemStack[] tRecipe = new ItemStack[9];
            int i = 0;
            for (Object tObject : aRecipe) {
                if (tObject instanceof ItemStack) {
                    tRecipe[i] = (ItemStack)tObject;
                } else if (tObject instanceof String) {
                    tRecipe[i] = GT_OreDictUnificator.getFirstOre((String)tObject, 1);
                } else if (!(tObject instanceof Boolean)) {
                    throw new IllegalArgumentException();
                }
                ++i;
            }
            GT_ModHandler.removeRecipe(tRecipe);
        }
        catch (Throwable e) {
            e.printStackTrace(GT_Log.out);
        }
        Ic2Recipes.addShapelessCraftingRecipe((ItemStack)aResult.copy(), (Object[])aRecipe);
        return true;
    }

    public static boolean removeFurnaceSmelting(ItemStack aInput, ItemStack aOutput) {
        if (aInput != null || aOutput != null) {
            // empty if block
        }
        return false;
    }

    public static ItemStack removeRecipe(ItemStack[] aRecipe) {
        if (aRecipe == null) {
            return null;
        }
        ItemStack rReturn = null;
        InventoryCrafting aCrafting = new InventoryCrafting(new Container(){

            public boolean canInteractWith(EntityPlayer var1) {
                return false;
            }
        }, 3, 3);
        for (int i = 0; i < aRecipe.length && i < 9; ++i) {
            aCrafting.setInventorySlotContents(i, aRecipe[i]);
        }
        ArrayList tList = (ArrayList)CraftingManager.getInstance().getRecipeList();
        for (int i = 0; i < tList.size(); ++i) {
            if (!((IRecipe)tList.get(i)).matches(aCrafting, GT_Mod.mUniverse)) continue;
            rReturn = ((IRecipe)tList.get(i)).getRecipeOutput();
            tList.remove(i--);
        }
        return rReturn;
    }

    public static boolean removeRecipe(ItemStack aOutput) {
        if (aOutput == null) {
            return false;
        }
        boolean rReturn = false;
        ArrayList tList = (ArrayList)CraftingManager.getInstance().getRecipeList();
        for (int i = 0; i < tList.size(); ++i) {
            ItemStack tStack = ((IRecipe)tList.get(i)).getRecipeOutput();
            if (tStack == null || !tStack.isItemEqual(aOutput)) continue;
            tList.remove(i--);
            rReturn = true;
        }
        return rReturn;
    }

    public static ItemStack getRecipeOutput(ItemStack[] aRecipe) {
        if (aRecipe == null) {
            return null;
        }
        InventoryCrafting aCrafting = new InventoryCrafting(new Container(){

            public boolean canInteractWith(EntityPlayer var1) {
                return false;
            }
        }, 3, 3);
        for (int i = 0; i < 9 && i < aRecipe.length; ++i) {
            aCrafting.setInventorySlotContents(i, aRecipe[i]);
        }
        ArrayList tList = (ArrayList)CraftingManager.getInstance().getRecipeList();
        for (int i = 0; i < tList.size(); ++i) {
            if (!((IRecipe)tList.get(i)).matches(aCrafting, GT_Mod.mUniverse)) continue;
            return ((IRecipe)tList.get(i)).getRecipeOutput().copy();
        }
        return null;
    }

    public static ArrayList getRecipeOutputs(ItemStack[] aRecipe) {
        if (aRecipe == null) {
            return null;
        }
        ArrayList<ItemStack> rList = new ArrayList<ItemStack>();
        InventoryCrafting aCrafting = new InventoryCrafting(new Container(){

            public boolean canInteractWith(EntityPlayer var1) {
                return false;
            }
        }, 3, 3);
        for (int i = 0; i < 9 && i < aRecipe.length; ++i) {
            aCrafting.setInventorySlotContents(i, aRecipe[i]);
        }
        ArrayList tList = (ArrayList)CraftingManager.getInstance().getRecipeList();
        for (int i = 0; i < tList.size(); ++i) {
            if (!((IRecipe)tList.get(i)).matches(aCrafting, GT_Mod.mUniverse)) continue;
            rList.add(((IRecipe)tList.get(i)).getRecipeOutput().copy());
        }
        return rList;
    }

    public static ItemStack getMaceratorOutput(ItemStack aInput, boolean aRemoveInput) {
        if (aInput == null) {
            return null;
        }
        return Ic2Recipes.getMaceratorOutputFor((ItemStack)aInput, (boolean)aRemoveInput);
    }

    public static ItemStack getExtractorOutput(ItemStack aInput, boolean aRemoveInput) {
        if (aInput == null) {
            return null;
        }
        return Ic2Recipes.getExtractorOutputFor((ItemStack)aInput, (boolean)aRemoveInput);
    }

    public static ItemStack getCompressorOutput(ItemStack aInput, boolean aRemoveInput) {
        if (aInput == null) {
            return null;
        }
        return Ic2Recipes.getCompressorOutputFor((ItemStack)aInput, (boolean)aRemoveInput);
    }

    public static ItemStack getSmeltingOutput(ItemStack aInput) {
        if (aInput == null) {
            return null;
        }
        return FurnaceRecipes.smelting().getSmeltingResult(aInput);
    }

    public static ItemStack getRecyclerOutput(ItemStack aInput, Random aRandom) {
        if (aInput == null || aRandom == null) {
            return null;
        }
        if (Ic2Recipes.isRecyclerInputBlacklisted((ItemStack)aInput)) {
            return null;
        }
        if (aRandom.nextInt(8) > 0) {
            return null;
        }
        return GT_ModHandler.getIC2Item("scrap", 1);
    }

    public static ItemStack getRandomScrapboxDrop(World aWorld) {
        ItemStack rStack = GT_OreDictUnificator.get(ItemScrapbox.getDrop((World)aWorld));
        if (rStack == null) {
            return GT_ModHandler.getRandomScrapboxDrop(aWorld);
        }
        if (rStack.isItemEqual(new ItemStack(Item.minecartEmpty, 1))) {
            return GT_ModHandler.getRandomScrapboxDrop(aWorld);
        }
        if (rStack.isItemEqual(new ItemStack(Item.helmetGold, 1))) {
            return GT_ModHandler.getRandomScrapboxDrop(aWorld);
        }
        return rStack;
    }

    public static boolean addTileToEnet(TileEntity aTileEntity) {
        try {
            EnergyNet.getForWorld((World)aTileEntity.worldObj).addTileEntity(aTileEntity);
            return true;
        }
        catch (Exception exception) {
            return false;
        }
    }

    public static boolean removeTileFromEnet(TileEntity aTileEntity) {
        try {
            EnergyNet.getForWorld((World)aTileEntity.worldObj).removeTileEntity(aTileEntity);
            return true;
        }
        catch (Exception exception) {
            return false;
        }
    }

    public static int emitEnergyToEnet(int aAmount, TileEntity aTileEntity) {
        try {
            return EnergyNet.getForWorld((World)aTileEntity.worldObj).emitEnergyFrom((IEnergySource)aTileEntity, aAmount);
        }
        catch (Exception exception) {
            return aAmount;
        }
    }

    public static int getCapsuleCellContainerCountMultipliedWithStackSize(ItemStack aStack) {
        if (aStack == null) {
            return 0;
        }
        return GT_ModHandler.getCapsuleCellContainerCount(aStack) * aStack.stackSize;
    }

    public static int getCapsuleCellContainerCount(ItemStack aStack) {
        if (aStack == null) {
            return 0;
        }
        if (aStack.isItemEqual(GT_ModHandler.getEmptyCell(1))) {
            return 1;
        }
        Item tItem = aStack.getItem();
        if (tItem == null) {
            return 0;
        }
        ItemStack tStack = null;
        if (tItem.hasContainerItem() && null != (tStack = tItem.getContainerItemStack(aStack)) && tStack.isItemEqual(GT_ModHandler.getEmptyCell(1))) {
            return tStack.stackSize;
        }
        if (tItem instanceof ICapsuleCellContainer) {
            return ((ICapsuleCellContainer)tItem).CapsuleCellContainerCount(aStack);
        }
        tStack = GT_Utility.getContainerForFilledItem(aStack);
        if (tStack != null && tStack.isItemEqual(GT_ModHandler.getEmptyCell(1))) {
            return 1;
        }
        if (tItem == GT_ModHandler.getIC2Item("airCell", 1).getItem()) {
            return 1;
        }
        if (tItem == GT_ModHandler.getIC2Item("waterCell", 1).getItem()) {
            return 1;
        }
        if (tItem == GT_ModHandler.getIC2Item("lavaCell", 1).getItem()) {
            return 1;
        }
        if (tItem == GT_ModHandler.getIC2Item("hydratedCoalCell", 1).getItem()) {
            return 1;
        }
        if (tItem == GT_ModHandler.getIC2Item("coalfuelCell", 1).getItem()) {
            return 1;
        }
        if (tItem == GT_ModHandler.getIC2Item("bioCell", 1).getItem()) {
            return 1;
        }
        if (tItem == GT_ModHandler.getIC2Item("biofuelCell", 1).getItem()) {
            return 1;
        }
        if (tItem == GT_ModHandler.getIC2Item("electrolyzedWaterCell", 1).getItem()) {
            return 1;
        }
        if (tItem == GT_ModHandler.getIC2Item("nearDepletedUraniumCell", 1).getItem()) {
            return 1;
        }
        if (tItem == GT_ModHandler.getIC2Item("reactorIsotopeCell", 1).getItem()) {
            return 1;
        }
        if (tItem == GT_ModHandler.getIC2Item("reEnrichedUraniumCell", 1).getItem()) {
            return 1;
        }
        if (tItem == GT_ModHandler.getIC2Item("hydratingCell", 1).getItem()) {
            return 1;
        }
        if (tItem == GT_ModHandler.getIC2Item("reactorCoolantSimple", 1).getItem()) {
            return 1;
        }
        if (tItem == GT_ModHandler.getIC2Item("reactorCoolantTriple", 1).getItem()) {
            return 3;
        }
        if (tItem == GT_ModHandler.getIC2Item("reactorCoolantSix", 1).getItem()) {
            return 6;
        }
        return 0;
    }
}

