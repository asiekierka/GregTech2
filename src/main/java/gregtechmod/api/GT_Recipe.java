package gregtechmod.api;

import gregtechmod.common.GT_ModHandler;
import gregtechmod.common.tileentities.GT_TileEntity_Fusionreactor;

import java.util.ArrayList;

import net.minecraft.item.ItemStack;

/**
 * This File contains the functions used for Recipes. Please do not include this File AT ALL in your Moddownload as it ruins compatibility
 * This is just the Core of my Recipe System, if you just want to GET the Recipes I add, then you can access this File.
 * Do NOT add Recipes using the Constructors inside this Class, The GregTech_API File calls the correct Functions for these Constructors.
 */
public class GT_Recipe {
	public static ArrayList<GT_Recipe> sFusionRecipes = new ArrayList<GT_Recipe>();
	public static ArrayList<GT_Recipe> sCentrifugeRecipes = new ArrayList<GT_Recipe>();
	public static ArrayList<GT_Recipe> sElectrolyzerRecipes = new ArrayList<GT_Recipe>();
	public static ArrayList<GT_Recipe> sGrinderRecipes = new ArrayList<GT_Recipe>();
	public static ArrayList<GT_Recipe> sBlastRecipes = new ArrayList<GT_Recipe>();
	public static ArrayList<GT_Recipe> sImplosionRecipes = new ArrayList<GT_Recipe>();
	public static ArrayList<GT_Recipe> sSawmillRecipes = new ArrayList<GT_Recipe>();
	public static ArrayList<GT_Recipe> sDieselFuels = new ArrayList<GT_Recipe>();
	public static ArrayList<GT_Recipe> sTurbineFuels = new ArrayList<GT_Recipe>();
	public static ArrayList<GT_Recipe> sHotFuels = new ArrayList<GT_Recipe>();
	public static ArrayList<GT_Recipe> sDenseLiquidFuels = new ArrayList<GT_Recipe>();
	public static ArrayList<GT_Recipe> sPlasmaFuels = new ArrayList<GT_Recipe>();
	
	public final ItemStack mInput1, mInput2, mOutput1, mOutput2, mOutput3, mOutput4;
	public final int mDuration, mEUt, mStartEU;
	
	public GT_Recipe(ItemStack aInput1, ItemStack aOutput1, int aStartEU, int aType) {
		mInput1   = aInput1==null?null:aInput1.copy();
		mInput2   = null;
		mOutput1  = aOutput1;
		mOutput2  = null;
		mOutput3  = null;
		mOutput4  = null;
		mDuration = 0;
		mEUt      = 0;
		// That's EU per MilliBucket! If there is no Liquid for this Object, then it gets multiplied with 1000!
		mStartEU  = Math.max(1, aStartEU);
		
		if (mInput1 != null) {
			switch (aType) {
			// Diesel Generator
			case 0: sDieselFuels.add(this); break;
			// Gas Turbine
			case 1: sTurbineFuels.add(this); break;
			// Thermal Generator
			case 2: sHotFuels.add(this); break;
			// Fluid Generator
			case 3: sDenseLiquidFuels.add(this); break;
			// Plasma Generator
			case 4: sPlasmaFuels.add(this); break;
			}
		}
	}
	
	public GT_Recipe(ItemStack aInput1, ItemStack aInput2, ItemStack aOutput1, int aDuration, int aEUt, int aStartEU) {
		mInput1   = aInput1==null?null:aInput1.copy();
		mInput2   = aInput2==null?null:aInput2.copy();
		mOutput1  = aOutput1==null?null:aOutput1.copy();
		mOutput2  = null;
		mOutput3  = null;
		mOutput4  = null;
		mDuration = Math.max(aDuration, 1);
		mEUt      = aEUt;
		mStartEU  = Math.max(Math.min(aStartEU, GT_TileEntity_Fusionreactor.maxEUStore), 0);
		
		if (mInput1 != null && mInput2 != null && findEqualFusionRecipeIndex(mInput1, mInput2) == -1) {
			sFusionRecipes.add(this);
		}
	}

	public GT_Recipe(ItemStack aInput1, ItemStack aInput2, ItemStack aOutput1, ItemStack aOutput2, ItemStack aOutput3, ItemStack aOutput4, int aDuration) {
		mInput1   = aInput1==null?null:aInput1.copy();
		mInput2   = aInput2==null?null:aInput2.copy();
		mOutput1  = aOutput1==null?null:aOutput1.copy();
		mOutput2  = aOutput2==null?null:aOutput2.copy();
		mOutput3  = aOutput3==null?null:aOutput3.copy();
		mOutput4  = aOutput4==null?null:aOutput4.copy();
		mDuration = Math.max(aDuration, 1);
		mEUt      = 0;
		mStartEU  = 0;
		
		if (mInput1 != null && mOutput1 != null && findEqualCentrifugeRecipeIndex(mInput1, mInput2) == -1) {
			sCentrifugeRecipes.add(this);
		}
	}

	public GT_Recipe(ItemStack aInput1, ItemStack aInput2, ItemStack aOutput1, ItemStack aOutput2, ItemStack aOutput3, ItemStack aOutput4, int aDuration, int aEUt) {
		mInput1   = aInput1==null?null:aInput1.copy();
		mInput2   = aInput2==null?null:aInput2.copy();
		mOutput1  = aOutput1==null?null:aOutput1.copy();
		mOutput2  = aOutput2==null?null:aOutput2.copy();
		mOutput3  = aOutput3==null?null:aOutput3.copy();
		mOutput4  = aOutput4==null?null:aOutput4.copy();
		mDuration = Math.max(aDuration, 1);
		mEUt      = aEUt>0?aEUt:1;
		mStartEU  = 0;
		
		if (mInput1 != null && mOutput1 != null && findEqualElectrolyzerRecipeIndex(mInput1, mInput2) == -1) {
			sElectrolyzerRecipes.add(this);
		}
	}

	public GT_Recipe(ItemStack aInput1, ItemStack aInput2, ItemStack aOutput1, ItemStack aOutput2, ItemStack aOutput3) {
		mInput1   = aInput1==null?null:aInput1.copy();
		mInput2   = aInput2==null?null:aInput2.copy();
		mOutput1  = aOutput1==null?null:aOutput1.copy();
		mOutput2  = aOutput2==null?null:aOutput2.copy();
		mOutput3  = aOutput3==null?null:aOutput3.copy();
		mOutput4  = null;
		mDuration = 200*(mInput1!=null?mInput1.stackSize:1);
		mEUt      = 32;
		mStartEU  = 0;
		
		if (mInput1 != null && mOutput1 != null && findEqualGrinderRecipeIndex(mInput1, mInput2) == -1) {
			sSawmillRecipes.add(this);
		}
	}
	
	public GT_Recipe(ItemStack aInput1, ItemStack aInput2, ItemStack aOutput1, ItemStack aOutput2, ItemStack aOutput3, ItemStack aOutput4) {
		mInput1   = aInput1==null?null:aInput1.copy();
		mInput2   = aInput2==null?null:aInput2.copy();
		mOutput1  = aOutput1==null?null:aOutput1.copy();
		mOutput2  = aOutput2==null?null:aOutput2.copy();
		mOutput3  = aOutput3==null?null:aOutput3.copy();
		mOutput4  = aOutput4==null?null:aOutput4.copy();
		mDuration = 100*(mInput1!=null?mInput1.stackSize:1);
		mEUt      = 128;
		mStartEU  = 0;
		
		if (mInput1 != null && mOutput1 != null && findEqualGrinderRecipeIndex(mInput1, mInput2) == -1) {
			sGrinderRecipes.add(this);
		}
	}

	public GT_Recipe(ItemStack aInput1, ItemStack aInput2, ItemStack aOutput1, ItemStack aOutput2, int aDuration, int aEUt, int aLevel) {
		mInput1   = aInput1==null?null:aInput1.copy();
		mInput2   = aInput2==null?null:aInput2.copy();
		mOutput1  = aOutput1==null?null:aOutput1.copy();
		mOutput2  = aOutput2==null?null:aOutput2.copy();
		mOutput3  = null;
		mOutput4  = null;
		mDuration = Math.max(aDuration, 1);
		mEUt      = aEUt>0?aEUt:1;
		mStartEU  = aLevel>0?aLevel:100;
		
		if (mInput1 != null && mOutput1 != null && findEqualBlastRecipeIndex(mInput1, mInput2) == -1) {
			sBlastRecipes.add(this);
		}
	}
	
	public GT_Recipe(ItemStack aInput1, int aInput2, ItemStack aOutput1, ItemStack aOutput2) {
		mInput1   = aInput1==null?null:aInput1.copy();
		mInput2   = GT_ModHandler.getIC2Item("industrialTnt", aInput2>0?aInput2<64?aInput2:64:1);
		mOutput1  = aOutput1==null?null:aOutput1.copy();
		mOutput2  = aOutput2==null?null:aOutput2.copy();
		mOutput3  = null;
		mOutput4  = null;
		mDuration = 20;
		mEUt      = 32;
		mStartEU  = 0;
		
		if (mInput1 != null && mOutput1 != null && findEqualImplosionRecipeIndex(mInput1, mInput2) == -1) {
			sImplosionRecipes.add(this);
		}
	}
	
	public static int findEqualFusionRecipeIndex(ItemStack aInput1, ItemStack aInput2) {
		for (int i = 0; i < sFusionRecipes.size(); i++) {
			if (sFusionRecipes.get(i).isRecipeInputEqual(true, false, aInput1, aInput2))
				return i;
		}
		return -1;
	}

	public static int findEqualCentrifugeRecipeIndex(ItemStack aInput1, ItemStack aInput2) {
		for (int i = 0; i < sCentrifugeRecipes.size(); i++) {
			if (sCentrifugeRecipes.get(i).isRecipeInputEqual(false, false, aInput1, aInput2))
				return i;
		}
		return -1;
	}

	public static int findEqualElectrolyzerRecipeIndex(ItemStack aInput1, ItemStack aInput2) {
		for (int i = 0; i < sElectrolyzerRecipes.size(); i++) {
			if (sElectrolyzerRecipes.get(i).isRecipeInputEqual(false, false, aInput1, aInput2))
				return i;
		}
		return -1;
	}

	public static int findEqualSawmillRecipeIndex(ItemStack aInput1, ItemStack aInput2) {
		for (int i = 0; i < sSawmillRecipes.size(); i++) {
			if (sSawmillRecipes.get(i).isRecipeInputEqual(false, false, aInput1, aInput2))
				return i;
		}
		return -1;
	}
	
	public static int findEqualGrinderRecipeIndex(ItemStack aInput1, ItemStack aInput2) {
		for (int i = 0; i < sGrinderRecipes.size(); i++) {
			if (sGrinderRecipes.get(i).isRecipeInputEqual(false, false, aInput1, aInput2))
				return i;
		}
		return -1;
	}
	
	public static int findEqualBlastRecipeIndex(ItemStack aInput1, ItemStack aInput2) {
		for (int i = 0; i < sBlastRecipes.size(); i++) {
			if (sBlastRecipes.get(i).isRecipeInputEqual(true, false, aInput1, aInput2))
				return i;
		}
		return -1;
	}

	public static int findEqualImplosionRecipeIndex(ItemStack aInput1, ItemStack aInput2) {
		for (int i = 0; i < sImplosionRecipes.size(); i++) {
			if (sImplosionRecipes.get(i).isRecipeInputEqual(true, false, aInput1, aInput2))
				return i;
		}
		return -1;
	}
	
	public boolean isRecipeInputEqual(boolean aShapeless, boolean aDecreaseStacksizeBySuccess, ItemStack aInput1, ItemStack aInput2) {
		if (aShapeless) if (isRecipeInputEqual(false, aDecreaseStacksizeBySuccess, aInput2, aInput1)) return true;
		if (aInput1 == null)
			return false;
		else {
			if (aInput1.getItem() == mInput1.getItem() && (aInput1.getItemDamage() == mInput1.getItemDamage() || mInput1.getItemDamage() == -1) && aInput1.stackSize >= mInput1.stackSize) {
				if (mInput2 != null && (aInput2 == null || !(aInput2.getItem() == mInput2.getItem() && (aInput2.getItemDamage() == mInput2.getItemDamage() || mInput2.getItemDamage() == -1)) || aInput2.stackSize < mInput2.stackSize)) return false;
				if (aDecreaseStacksizeBySuccess) {
					aInput1.stackSize -= mInput1.stackSize;
					if (mInput2 != null) aInput2.stackSize -= mInput2.stackSize;
				}
				return true;
			}
		}
		return false;
	}
}