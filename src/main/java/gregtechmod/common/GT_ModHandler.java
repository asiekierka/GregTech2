package gregtechmod.common;

import gregtechmod.GT_Mod;
import gregtechmod.api.ICapsuleCellContainer;
import gregtechmod.common.items.GT_MetaItem_Dust;
import gregtechmod.common.items.GT_MetaItem_SmallDust;
import ic2.api.Direction;
import ic2.api.Ic2Recipes;
import ic2.api.Items;
import ic2.api.energy.EnergyNet;
import ic2.api.energy.tile.IEnergySource;
import ic2.core.IC2;

import java.util.ArrayList;
import java.util.HashMap;

import net.minecraft.block.Block;
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
import net.minecraftforge.liquids.LiquidStack;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;
import railcraft.common.api.crafting.RailcraftCraftingManager;
import thermalexpansion.api.core.ItemRegistry;
import thermalexpansion.api.crafting.CraftingHelpers;
import thermalexpansion.api.crafting.CraftingManagers;

public class GT_ModHandler {
	public static ItemStack
			mRuby = null,
			mGreenSapphire = null,
			mSapphire = null,
			mSilver = null,
			mTin = null,
			mCopper = null,
			mNikolite = null,
			mRedAlloy = null,
			mBlueAlloy = null,
			mBrass = null,
			mSiliconBoule = null,
			mSiliconWafer = null,
			mBlueWafer = null,
			mRedWafer = null,
			mRPTinPlate = null,
			mFineCopper = null,
			mFineIron = null,
			mCopperCoil = null,
			mBlutricMotor = null,
			mCanvas = null,
			mBCWoodGear = null,
			mBCStoneGear = null,
			mBCIronGear = null,
			mBCGoldGear = null,
			mBCDiamondGear = null;
	
	public static boolean isIC2loaded() {
		return getIC2Item("resin", 1) != null;
	}
	
	public static boolean isRCloaded() {
		return getRCItem("machine.alpha.rolling.machine", 1) != null;
	}
	
	public static boolean isTEloaded() {
		return getTEItem("slag", 1) != null;
	}
	
	public static ItemStack getIC2Item(String aItem, int aAmount) {
		if (aItem == null || aItem.equals("")) return null;
		ItemStack rStack = null;
		rStack = Items.getItem(aItem);
		if (rStack == null) return null;
		rStack = rStack.copy();
		rStack.stackSize = aAmount;
		return rStack;
	}

	public static ItemStack getRCItem(String aItem, int aAmount) {
		if (aItem == null || aItem.equals("")) return null;
		ItemStack rStack = null;
		try {rStack = railcraft.common.api.core.items.ItemRegistry.getItem(aItem, aAmount);} catch(Throwable e) {}
		if (rStack == null) return null;
		rStack = rStack.copy();
		rStack.stackSize = aAmount;
		return rStack;
	}
	
	public static ItemStack getTEItem(String aItem, int aAmount) {
		if (aItem == null || aItem.equals("")) return null;
		ItemStack rStack = null;
		try {rStack = thermalexpansion.api.core.ItemRegistry.getItem(aItem, aAmount);} catch(Throwable e) {}
		if (rStack == null) return null;
		rStack = rStack.copy();
		rStack.stackSize = aAmount;
		return rStack;
	}

	public static ItemStack getFRItem(String aItem, int aAmount) {
		if (aItem == null || aItem.equals("")) return null;
		ItemStack rStack = null;
		try {rStack = forestry.api.core.ItemInterface.getItem(aItem);} catch(Throwable e) {}
		if (rStack == null) return null;
		rStack = rStack.copy();
		rStack.stackSize = aAmount;
		return rStack;
	}
	
	/**
	 * @param aValue Fuelvalue in EU / 5
	 */
	public static ItemStack getFuelCan(int aValue) {
		if (aValue <= 0) return getIC2Item("fuelCan", 1);
		ItemStack rFuelCanStack = getIC2Item("filledFuelCan", 1);
		if (rFuelCanStack == null) return null;
		NBTTagCompound tNBT = new NBTTagCompound();
        tNBT.setInteger("value", aValue);
        rFuelCanStack.setTagCompound(tNBT);
        return rFuelCanStack;
	}
	
	/**
	 * @param aFuelCan the Item you want to check
	 * @return the exact Value in EU the Fuel Can is worth if its even a Fuel Can.
	 */
	public static int getFuelCanValue(ItemStack aFuelCan) {
		if (aFuelCan == null || !aFuelCan.isItemEqual(getIC2Item("filledFuelCan", 1))) return 0;
		NBTTagCompound tNBT = aFuelCan.getTagCompound();
		if (tNBT == null) return 0;
		return tNBT.getInteger("value")*5;
	}
	
	public static void addBoilerFuel(LiquidStack aLiquid, int aValue) {
		if (aLiquid == null || aValue <= 0) return;
		try {if (!GT_Mod.mConfig.mConfigFileAdvRecipes.get("boilerfuels", aLiquid.asItemStack().getItemName(), true).getBoolean(true)) return;} catch(Throwable e) {}
		try {
			railcraft.common.api.fuel.FuelManager.addBoilerFuel(aLiquid, aValue);
		} catch(Throwable e) {}
	}
	
    public static void registerOre(String aName, ItemStack aStack) {
    	if (aName == null || aName.equals("") || aStack == null || aStack.getItem() == null) return;
    	ArrayList<ItemStack> tList = OreDictionary.getOres(aName);
    	for (int i = 0; i < tList.size(); i++) if (tList.get(i).isItemEqual(aStack)) return;
    	aStack = aStack.copy();
    	aStack.stackSize = 1;
    	OreDictionary.registerOre(aName, aStack);
    }
    
	public static boolean getModeKeyDown(EntityPlayer aPlayer) {
		try {
			return IC2.keyboard.isModeSwitchKeyDown(aPlayer);
		} catch(Throwable e) {}
		return false;
	}
	
	public static boolean getBoostKeyDown(EntityPlayer aPlayer) {
		try {
			return IC2.keyboard.isBoostKeyDown(aPlayer);
		} catch(Throwable e) {}
		return false;
	}
	
	public static boolean getJumpKeyDown(EntityPlayer aPlayer) {
		try {
			return IC2.keyboard.isJumpKeyDown(aPlayer);
		} catch(Throwable e) {}
		return false;
	}
	
	public static short convertIC2DirectionToShort(Direction aDirection) {
		switch (aDirection) {
		case XP: return 1;
		case YN: return 2;
		case YP: return 3;
		case ZN: return 4;
		case ZP: return 5;
		default: return 0;
		}
	}
	
	public static boolean addValuableOre(int aID, int aMeta, int aValue) {
		if (aValue <= 0) return false;
    	IC2.addValuableOre(aID, aMeta, aValue);
		return true;
	}

	public static boolean addScrapboxDrop(float aChance, ItemStack aOutput) {
		if (aOutput == null || aChance <= 0) return false;
		if (!GT_Mod.mConfig.mConfigFileAdvRecipes.get("scrapboxdrops", aOutput.getItemName(), true).getBoolean(true)) return false;
		Ic2Recipes.addScrapboxDrop(aOutput.copy(), aChance);
		return true;
	}
	
	public static boolean addToRecyclerBlackList(ItemStack aRecycledStack) {
		if (aRecycledStack == null) return false;
		Ic2Recipes.addRecyclerBlacklistItem(aRecycledStack.copy());
		return true;
	}
	
	public static boolean addSmeltingRecipe(ItemStack aInput, ItemStack aOutput) {
		if (aInput == null || aOutput == null) return false;
		try {if (!GT_Mod.mConfig.mConfigFileAdvRecipes.get("furnace", aInput.getItemName(), true).getBoolean(true)) return false;} catch(Throwable e) {}
		FurnaceRecipes.smelting().addSmelting(aInput.itemID, aInput.getItemDamage(), aOutput.copy(), 0.0F);
		return true;
	}
	
	public static boolean addExtractionRecipe(ItemStack aInput, ItemStack aOutput) {
		if (aInput == null || aOutput == null) return false;
		try {if (!GT_Mod.mConfig.mConfigFileAdvRecipes.get("extractor", aInput.getItemName(), true).getBoolean(true)) return false;} catch(Throwable e) {}
		GT_Utility.removeSimpleIC2MachineRecipe(aInput, null, Ic2Recipes.getExtractorRecipes());
		Ic2Recipes.addExtractorRecipe(aInput.copy(), aOutput.copy());
		return true;
	}
	
	public static boolean addRCBlastFurnaceRecipe(ItemStack aInput, ItemStack aOutput, int aTime) {
		if (aInput == null || aOutput == null || aTime <= 0) return false;
		try {if (!GT_Mod.mConfig.mConfigFileAdvRecipes.get("rcblastfurnace", aInput.getItemName(), true).getBoolean(true)) return false;} catch(Throwable e) {}
		aInput = aInput.copy();
		aOutput = aOutput.copy();
		if (!isRCloaded()) return false;
		RailcraftCraftingManager.blastFurnace.addRecipe(aInput.itemID, aInput.getItemDamage(), aTime, aOutput);
		return true;
	}
	
	public static boolean addPulverisationRecipe(ItemStack aInput, ItemStack aOutput1, ItemStack aOutput2, int aChance) {
		if (aInput == null || aOutput1 == null) return false;
		try {if (!GT_Mod.mConfig.mConfigFileAdvRecipes.get("maceration", aInput.getItemName(), true).getBoolean(true)) return false;} catch(Throwable e) {}
	    GT_Utility.removeSimpleIC2MachineRecipe(aInput, null, Ic2Recipes.getMaceratorRecipes());
		Ic2Recipes.addMaceratorRecipe(aInput.copy(), aOutput1.copy());
		if (!aOutput1.isItemEqual(GT_MetaItem_Dust.instance.getStack(15, 1)) && !aOutput1.isItemEqual(GT_MetaItem_SmallDust.instance.getStack(15, 1))) {
			try {
				ItemStack tStack = aInput.copy();
				tStack.stackSize = 1;
				HashMap<ItemStack, Float> tOutput = new HashMap<ItemStack, Float>();
				tOutput.put(aOutput1.copy(), 1.0F/(float)aInput.stackSize);
				if (aOutput2 != null) tOutput.put(aOutput2.copy(), 0.01F*(aChance<=0?10:aChance));
				RailcraftCraftingManager.rockCrusher.addRecipe(tStack.copy(), tOutput);
			} catch(Throwable e) {}
			try {
				if (aOutput2 == null)
					CraftingManagers.pulverizerManager.addRecipe(400, aInput.copy(), aOutput1.copy());
				else
					CraftingManagers.pulverizerManager.addRecipe(400, aInput.copy(), aOutput1.copy(), aOutput2.copy(), aChance<=0?5:aChance);
			} catch(Throwable e) {}
		} else {
			try {
				if (aOutput2 == null)
					CraftingManagers.sawmillManager.addRecipe(80, aInput.copy(), aOutput1.copy());
				else
					CraftingManagers.sawmillManager.addRecipe(80, aInput.copy(), aOutput1.copy(), aOutput2.copy(), aChance<=0?10:aChance);
			} catch(Throwable e) {}
		}
		return true;
	}
	
	public static boolean addPulverisationRecipe(ItemStack aInput, ItemStack aOutput) {
		return addPulverisationRecipe(aInput, aOutput, null, 0);
	}
	
	public static boolean addDustToIngotSmeltingRecipe(ItemStack aInput, ItemStack aOutput) {
		if (aInput == null || aOutput == null) return false;
		try {
	        ItemStack dust = aInput.copy();
	        dust.stackSize = 2;
	        
	        ItemStack ingots2 = aOutput.copy();
	        ingots2.stackSize = 2;
	        
	        ItemStack ingots3 = aOutput.copy();
	        ingots3.stackSize = 3;
	        
	        ItemStack slag = ItemRegistry.getItem("slag", 1);
	        ItemStack richSlag = ItemRegistry.getItem("slagRich", 1);
	        
	        CraftingManagers.smelterManager.addRecipe(80, dust, new ItemStack(Block.sand, 1), ingots2, slag, 25);
	        CraftingManagers.smelterManager.addRecipe(320, dust, richSlag, ingots3, slag, 33);
		} catch(Throwable e) {}
		addSmeltingRecipe(aInput, aOutput);
		return true;
	}

	public static boolean addOreToIngotSmeltingRecipe(ItemStack aInput, ItemStack aOutput) {
		if (aInput == null || aOutput == null) return false;
		try {
			new CraftingHelpers().addSmelterOreToIngotsRecipe(aInput, aOutput);
		} catch(Throwable e) {}
		FurnaceRecipes.smelting().addSmelting(aInput.itemID, aInput.getItemDamage(), aOutput.copy(), 0.0F);
		return true;
	}
	
	public static boolean addCompressionRecipe(ItemStack aInput, ItemStack aOutput) {
		if (aInput == null || aOutput == null) return false;
		try {if (!GT_Mod.mConfig.mConfigFileAdvRecipes.get("compression", aInput.getItemName(), true).getBoolean(true)) return false;} catch(Throwable e) {}
		GT_Utility.removeSimpleIC2MachineRecipe(aInput, null, Ic2Recipes.getCompressorRecipes());
		Ic2Recipes.addCompressorRecipe(aInput.copy(), aOutput.copy());
		return true;
	}

	/**
	 * @param aValue Scrap = 5000, Scrapbox = 45000, Diamond Dust 125000
	 */
	public static boolean addIC2MatterAmplifier(ItemStack aAmplifier, int aValue) {
		if (aAmplifier == null || aValue <= 0) return false;
		try {if (!GT_Mod.mConfig.mConfigFileAdvRecipes.get("massfabamplifier", aAmplifier.getItemName(), true).getBoolean(true)) return false;} catch(Throwable e) {}
		Ic2Recipes.addMatterAmplifier(aAmplifier.copy(), aValue);
		return true;
	}
	
	public static boolean addRollingMachineRecipe(ItemStack aResult, Object[] aRecipe) {
		if (aResult == null || aRecipe == null) return false;
		if (GT_Mod.instance.mRollingmachineAlloy) {
			try {
				RailcraftCraftingManager.rollingMachine.getRecipeList().add(new ShapedOreRecipe(aResult.copy(), aRecipe));
			} catch(Throwable e) {
				return addCraftingRecipe(aResult.copy(), aRecipe);
			}
		} else {
			return addCraftingRecipe(aResult.copy(), aRecipe);
		}
		return true;
	}

	public static boolean addCraftingRecipe(ItemStack aResult, Object... aRecipe) {
		if (aResult == null || aRecipe == null) return false;
		Ic2Recipes.addCraftingRecipe(aResult.copy(), aRecipe);
		return true;
	}

	public static boolean addShapelessCraftingRecipe(ItemStack aResult, Object... aRecipe) {
		if (aResult == null || aRecipe == null) return false;
		Ic2Recipes.addShapelessCraftingRecipe(aResult.copy(), aRecipe);
		return true;
	}
	
	/**
	 * Removes a Craftingrecipe and gives you the former output of it.
	 * @param aRecipe The content of the Craftinggrid as ItemStackArray with length 9
	 * @return the output of the old Recipe or null if there was nothing.
	 */
    public static ItemStack removeRecipe(ItemStack[] aRecipe) {
    	if (aRecipe == null) return null;
    	ItemStack rReturn = null;
		InventoryCrafting aCrafting = new InventoryCrafting(new Container() {public boolean canInteractWith(EntityPlayer var1) {return false;}}, 3, 3);
		for (int i = 0; i < aRecipe.length && i < 9; i++) aCrafting.setInventorySlotContents(i, aRecipe[i]);
		ArrayList<IRecipe> tList = (ArrayList<IRecipe>)CraftingManager.getInstance().getRecipeList();
		for (int i = 0; i < tList.size(); i++) {
			if (tList.get(i).matches(aCrafting, GT_Mod.mUniverse)) {
				rReturn = tList.get(i).getRecipeOutput();
				tList.remove(i--);
			}
		}
		return rReturn;
    }

	/**
	 * Removes a Craftingrecipe.
	 * @param aOutput The output of the Recipe.
	 * @return if it has removed at least one Recipe.
	 */
    public static boolean removeRecipe(ItemStack aOutput) {
    	if (aOutput == null) return false;
    	boolean rReturn = false;
    	ItemStack tStack;
		ArrayList<IRecipe> tList = (ArrayList<IRecipe>)CraftingManager.getInstance().getRecipeList();
		for (int i = 0; i < tList.size(); i++) {
			if ((tStack = tList.get(i).getRecipeOutput()) != null) {
				if (tStack.isItemEqual(aOutput)) {
					tList.remove(i--);
					rReturn = true;
				}
			}
		}
		return rReturn;
    }
    
    public static ItemStack getRecipeOutput(ItemStack[] aRecipe) {
    	if (aRecipe == null) return null;
		InventoryCrafting aCrafting = new InventoryCrafting(new Container() {public boolean canInteractWith(EntityPlayer var1) {return false;}}, 3, 3);
		for (int i = 0; i < 9 ; i++) aCrafting.setInventorySlotContents(i, aRecipe[i]);
		ArrayList<IRecipe> tList = (ArrayList<IRecipe>)CraftingManager.getInstance().getRecipeList();
		for (int i = 0; i < tList.size(); i++) {
			if (tList.get(i).matches(aCrafting, GT_Mod.mUniverse)) {
				return tList.get(i).getRecipeOutput().copy();
			}
		}
		return null;
    }

    public static ArrayList<ItemStack> getRecipeOutputs(ItemStack[] aRecipe) {
    	if (aRecipe == null) return null;
    	ArrayList<ItemStack> rList = new ArrayList<ItemStack>();
    	InventoryCrafting aCrafting = new InventoryCrafting(new Container() {public boolean canInteractWith(EntityPlayer var1) {return false;}}, 3, 3);
		for (int i = 0; i < 9 ; i++) aCrafting.setInventorySlotContents(i, aRecipe[i]);
		ArrayList<IRecipe> tList = (ArrayList<IRecipe>)CraftingManager.getInstance().getRecipeList();
		for (int i = 0; i < tList.size(); i++) {
			if (tList.get(i).matches(aCrafting, GT_Mod.mUniverse)) {
				rList.add(tList.get(i).getRecipeOutput().copy());
			}
		}
		if (rList.size() == 0) return null;
		return rList;
    }
    
	public static boolean addTileToEnet(TileEntity aTileEntity) {
		try {
			EnergyNet.getForWorld(aTileEntity.worldObj).addTileEntity(aTileEntity);
			return true;
		} catch(Exception e) {}
		return false;
	}
	
	public static boolean removeTileFromEnet(TileEntity aTileEntity) {
		try {
			EnergyNet.getForWorld(aTileEntity.worldObj).removeTileEntity(aTileEntity);
			return true;
		} catch(Exception e) {}
		return false;
	}
	
	public static int emitEnergyToEnet(int aAmount, TileEntity aTileEntity) {
		try {
			return EnergyNet.getForWorld(aTileEntity.worldObj).emitEnergyFrom((IEnergySource)aTileEntity, aAmount);
		} catch(Exception e) {}
		return aAmount;
	}
	
	public static int getCapsuleCellContainerCount(ItemStack aStack) {
		if (aStack == null) return 0;
		Item tItem = aStack.getItem();
		if (tItem == null) return 0;
		if (tItem instanceof ICapsuleCellContainer) return ((ICapsuleCellContainer)tItem).CapsuleCellContainerCount(aStack);
		if (tItem == GT_ModHandler.getIC2Item("cell"					, 1).getItem()) return 1;
		if (tItem == GT_ModHandler.getIC2Item("airCell"					, 1).getItem()) return 1;
		if (tItem == GT_ModHandler.getIC2Item("waterCell"				, 1).getItem()) return 1;
		if (tItem == GT_ModHandler.getIC2Item("lavaCell"				, 1).getItem()) return 1;
		if (tItem == GT_ModHandler.getIC2Item("hydratedCoalCell"		, 1).getItem()) return 1;
		if (tItem == GT_ModHandler.getIC2Item("coalfuelCell"			, 1).getItem()) return 1;
		if (tItem == GT_ModHandler.getIC2Item("bioCell"					, 1).getItem()) return 1;
		if (tItem == GT_ModHandler.getIC2Item("biofuelCell"				, 1).getItem()) return 1;
		if (tItem == GT_ModHandler.getIC2Item("electrolyzedWaterCell"	, 1).getItem()) return 1;
		if (tItem == GT_ModHandler.getIC2Item("nearDepletedUraniumCell"	, 1).getItem()) return 1;
		if (tItem == GT_ModHandler.getIC2Item("reactorIsotopeCell"		, 1).getItem()) return 1;
		if (tItem == GT_ModHandler.getIC2Item("reEnrichedUraniumCell"	, 1).getItem()) return 1;
		if (tItem == GT_ModHandler.getIC2Item("hydratingCell"			, 1).getItem()) return 1;
		if (tItem == GT_ModHandler.getIC2Item("reactorCoolantSimple"	, 1).getItem()) return 1;
		if (tItem == GT_ModHandler.getIC2Item("reactorCoolantTriple"	, 1).getItem()) return 3;
		if (tItem == GT_ModHandler.getIC2Item("reactorCoolantSix"		, 1).getItem()) return 6;
		return 0;
	}
}
