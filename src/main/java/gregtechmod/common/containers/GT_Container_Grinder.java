package gregtechmod.common.containers;

import gregtechmod.api.BaseMetaTileEntity;
import gregtechmod.common.GT_Slot_Output;
import gregtechmod.common.tileentities.GT_MetaTileEntity_Grinder;

import java.util.Iterator;

import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class GT_Container_Grinder extends GT_ContainerMetaTile_Machine {

	public GT_Container_Grinder(InventoryPlayer aInventoryPlayer, BaseMetaTileEntity aTileEntity, int aID) {
		super(aInventoryPlayer, aTileEntity, aID);
	}

    public void addSlots(InventoryPlayer aInventoryPlayer) {
        addSlotToContainer(new Slot(mTileEntity, 0,  34,  16));
        addSlotToContainer(new Slot(mTileEntity, 1,  34,  34));
        addSlotToContainer(new GT_Slot_Output(mTileEntity, 2,  86,  25));
        addSlotToContainer(new GT_Slot_Output(mTileEntity, 3, 104,  25));
        addSlotToContainer(new GT_Slot_Output(mTileEntity, 4, 122,  25));
        addSlotToContainer(new GT_Slot_Output(mTileEntity, 5, 140,  25));
    }

    public int mProgress, mMaxProgress, mProgressScale, mWaterAmount;
    public boolean mMachine = true;
    
    public void updateCraftingResults() {
        super.updateCraftingResults();
    	if (mTileEntity.worldObj.isRemote) return;
    	mMachine = ((GT_MetaTileEntity_Grinder)mTileEntity.mMetaTileEntity).mMachine;
    	mWaterAmount = ((GT_MetaTileEntity_Grinder)mTileEntity.mMetaTileEntity).mWaterAmount;
    	mProgress = ((GT_MetaTileEntity_Grinder)mTileEntity.mMetaTileEntity).getProgresstime();
    	mMaxProgress = ((GT_MetaTileEntity_Grinder)mTileEntity.mMetaTileEntity).maxProgresstime();
    	mProgressScale = Math.max(0, Math.min(20, (mProgress>0?1:0) + (mProgress * 20) / (mMaxProgress<1?1:mMaxProgress)));
    	
        Iterator var2 = this.crafters.iterator();
        while (var2.hasNext()) {
            ICrafting var1 = (ICrafting)var2.next();
            var1.sendProgressBarUpdate(this, 10, mProgress);
            var1.sendProgressBarUpdate(this, 11, mMaxProgress);
            var1.sendProgressBarUpdate(this, 12, mProgressScale);
            var1.sendProgressBarUpdate(this, 13, mMachine?1:0);
            var1.sendProgressBarUpdate(this, 14, mWaterAmount);
        }
    }
    
    @SideOnly(Side.CLIENT)
    public void updateProgressBar(int par1, int par2) {
    	super.updateProgressBar(par1, par2);
    	switch (par1) {
    	case 10: mProgress = par2; break;
    	case 11: mMaxProgress = par2; break;
    	case 12: mProgressScale = par2; break;
    	case 13: mMachine = (par2!=0); break;
    	case 14: mWaterAmount = par2; break;
    	}
    }
    
    public int getSlotCount() {
    	return 6;
    }

    public int getShiftClickSlotCount() {
    	return 2;
    }
}
