package gregtechmod.common.containers;

import gregtechmod.common.GT_Slot_Holo;
import gregtechmod.common.GT_Slot_Output;
import gregtechmod.common.tileentities.GT_TileEntity_Centrifuge;

import java.util.Iterator;

import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class GT_Container_Centrifuge extends GT_ContainerMetaID_Machine {

	public GT_Container_Centrifuge(InventoryPlayer aInventoryPlayer, GT_TileEntity_Centrifuge aTileEntity, int aID) {
		super(aInventoryPlayer, aTileEntity, aID);
	}

    public void addSlots(InventoryPlayer aInventoryPlayer) {
        addSlotToContainer(new Slot				(mTileEntity, 0,  80,  35));
        addSlotToContainer(new Slot				(mTileEntity, 1,  50,   5));
        addSlotToContainer(new GT_Slot_Output	(mTileEntity, 2,  80,   5));
        addSlotToContainer(new GT_Slot_Output	(mTileEntity, 3, 110,  35));
        addSlotToContainer(new GT_Slot_Output	(mTileEntity, 4,  80,  65));
        addSlotToContainer(new GT_Slot_Output	(mTileEntity, 5,  50,  35));
        addSlotToContainer(new GT_Slot_Holo		(mTileEntity, 6, 110,  65, false, false, 64));
    }

    public int mProgress, mMaxProgress, mProgressScale, mDisplayErrorCode;
    
    public void updateCraftingResults() {
        super.updateCraftingResults();
    	if (mTileEntity.worldObj.isRemote) return;
    	mProgress = ((GT_TileEntity_Centrifuge)mTileEntity).getProgresstime();
    	mMaxProgress = ((GT_TileEntity_Centrifuge)mTileEntity).maxProgresstime();
    	mProgressScale = Math.max(0, Math.min(10, (mProgress>0?1:0) + (mProgress * 10) / (mMaxProgress<1?1:mMaxProgress)));
    	mDisplayErrorCode = ((GT_TileEntity_Centrifuge)mTileEntity).mDisplayErrorCode;
    	
        Iterator var2 = this.crafters.iterator();
        while (var2.hasNext()) {
            ICrafting var1 = (ICrafting)var2.next();
            var1.sendProgressBarUpdate(this, 10, mProgress);
            var1.sendProgressBarUpdate(this, 11, mMaxProgress);
            var1.sendProgressBarUpdate(this, 12, mProgressScale);
            var1.sendProgressBarUpdate(this, 13, mDisplayErrorCode);
        }
    }
    
    @SideOnly(Side.CLIENT)
    public void updateProgressBar(int par1, int par2) {
    	super.updateProgressBar(par1, par2);
    	switch (par1) {
    	case 10: mProgress = par2; break;
    	case 11: mMaxProgress = par2; break;
    	case 12: mProgressScale = par2; break;
    	case 13: mDisplayErrorCode = par2; break;
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
