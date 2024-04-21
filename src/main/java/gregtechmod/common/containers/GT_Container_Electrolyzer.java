package gregtechmod.common.containers;

import gregtechmod.api.BaseMetaTileEntity;
import gregtechmod.common.GT_Slot_Holo;
import gregtechmod.common.GT_Slot_Output;
import gregtechmod.common.tileentities.GT_MetaTileEntity_Electrolyzer;

import java.util.Iterator;

import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class GT_Container_Electrolyzer extends GT_ContainerMetaTile_Machine {

	public GT_Container_Electrolyzer(InventoryPlayer aInventoryPlayer, BaseMetaTileEntity aTileEntity, int aID) {
		super(aInventoryPlayer, aTileEntity, aID);
	}

    public void addSlots(InventoryPlayer aInventoryPlayer) {
        addSlotToContainer(new Slot				(mTileEntity, 0,  80,  46));
        addSlotToContainer(new Slot				(mTileEntity, 1,  50,  46));
        addSlotToContainer(new GT_Slot_Output	(mTileEntity, 2,  50,  16));
        addSlotToContainer(new GT_Slot_Output	(mTileEntity, 3,  70,  16));
        addSlotToContainer(new GT_Slot_Output	(mTileEntity, 4,  90,  16));
        addSlotToContainer(new GT_Slot_Output	(mTileEntity, 5, 110,  16));
        addSlotToContainer(new GT_Slot_Holo		(mTileEntity, 6, 110,  46, false, false, 64));
    }

    public int mProgress, mMaxProgress, mProgressScale;
    
    public void updateCraftingResults() {
        super.updateCraftingResults();
    	if (mTileEntity.worldObj.isRemote) return;
    	mProgress = ((GT_MetaTileEntity_Electrolyzer)mTileEntity.mMetaTileEntity).getProgresstime();
    	mMaxProgress = ((GT_MetaTileEntity_Electrolyzer)mTileEntity.mMetaTileEntity).maxProgresstime();
    	mProgressScale = Math.max(0, Math.min(10, (mProgress>0?1:0) + (mProgress * 10) / (mMaxProgress<1?1:mMaxProgress)));
    	
        Iterator var2 = this.crafters.iterator();
        while (var2.hasNext()) {
            ICrafting var1 = (ICrafting)var2.next();
            var1.sendProgressBarUpdate(this, 10, mProgress);
            var1.sendProgressBarUpdate(this, 11, mMaxProgress);
            var1.sendProgressBarUpdate(this, 12, mProgressScale);
        }
    }
    
    @SideOnly(Side.CLIENT)
    public void updateProgressBar(int par1, int par2) {
    	super.updateProgressBar(par1, par2);
    	switch (par1) {
    	case 10: mProgress = par2; break;
    	case 11: mMaxProgress = par2; break;
    	case 12: mProgressScale = par2; break;
    	}
    }

    public int getAllSlotCount() {
    	return 7;
    }
    
    public int getSlotCount() {
    	return 6;
    }

    public int getShiftClickSlotCount() {
    	return 2;
    }
}
