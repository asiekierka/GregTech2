package gregtechmod.common.items;

import gregtechmod.GT_Mod;
import ic2.api.ElectricItem;
import ic2.api.IElectricItem;

import java.util.List;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class GT_EnergyStore_Item extends GT_Generic_Item implements IElectricItem {
	public int mCharge, mTransfer, mTier, mEmptyID, mFullID;
	
	public GT_EnergyStore_Item(int aID, int aCharge, int aTransfer, int aTier, int aEmptyID, int aFullID) {
		super(aID);
		setMaxStackSize(1);
		setMaxDamage(100);
		setNoRepair();
		mCharge = aCharge;
		mTransfer = aTransfer;
		mTier = aTier;
		mEmptyID = aEmptyID;
		mFullID = aFullID;
	}
    
    public boolean getShareTag() {
        return true;
    }
    
    @SideOnly(Side.CLIENT)
    public void getSubItems(int var1, CreativeTabs var2, List var3) {
        ItemStack tCharged = new ItemStack(GT_Mod.instance.mItems[mFullID], 1), tUncharged = new ItemStack(GT_Mod.instance.mItems[mEmptyID], 1, getMaxDamage());
        ElectricItem.charge(tCharged, Integer.MAX_VALUE, Integer.MAX_VALUE, true, false);
        if (shiftedIndex == GT_Mod.instance.mItems[mFullID].shiftedIndex) var3.add(tCharged);
        if (shiftedIndex == GT_Mod.instance.mItems[mEmptyID].shiftedIndex) var3.add(tUncharged);
    }
    
	@Override
	public boolean canProvideEnergy() {
		return true;
	}

	@Override
	public int getChargedItemId() {
		return GT_Mod.instance.mItems[mFullID].shiftedIndex;
	}

	@Override
	public int getEmptyItemId() {
		return GT_Mod.instance.mItems[mEmptyID].shiftedIndex;
	}

	@Override
	public int getMaxCharge() {
		return mCharge;
	}

	@Override
	public int getTier() {
		return mTier;
	}

	@Override
	public int getTransferLimit() {
		return mTransfer;
	}
}
