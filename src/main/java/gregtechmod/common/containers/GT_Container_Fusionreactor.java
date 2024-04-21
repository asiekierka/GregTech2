package gregtechmod.common.containers;

import gregtechmod.common.GT_Slot_Output;
import gregtechmod.common.tileentities.GT_TileEntity_Fusionreactor;

import java.util.Iterator;

import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class GT_Container_Fusionreactor extends GT_ContainerMetaID_Machine {

	public GT_Container_Fusionreactor(InventoryPlayer aInventoryPlayer, GT_TileEntity_Fusionreactor aTileEntity, int aID) {
		super(aInventoryPlayer, aTileEntity, aID);
	}

    public void addSlots(InventoryPlayer aInventoryPlayer) {
        addSlotToContainer(new Slot(mTileEntity, 0,  88,  17));
        addSlotToContainer(new Slot(mTileEntity, 1,  88,  53));
        addSlotToContainer(new GT_Slot_Output(mTileEntity, 2, 148,  35));
    }

    public int mProgress, mMaxProgress, mActive, mCoils, mProgressScale;
    
    public void updateCraftingResults() {
        super.updateCraftingResults();
    	if (mTileEntity.worldObj.isRemote) return;
    	mActive = ((GT_TileEntity_Fusionreactor)mTileEntity).isActive()?1:0;
    	mCoils = ((GT_TileEntity_Fusionreactor)mTileEntity).hasCoils()?1:0;
    	mProgress = ((GT_TileEntity_Fusionreactor)mTileEntity).getFusiontime();
    	mMaxProgress = ((GT_TileEntity_Fusionreactor)mTileEntity).maxFusiontime();
    	mProgressScale = Math.max(0, Math.min(24, (mProgress>0?1:0) + (mProgress * 24) / (mMaxProgress<1?1:mMaxProgress)));
    	
        Iterator var2 = this.crafters.iterator();
        while (var2.hasNext()) {
            ICrafting var1 = (ICrafting)var2.next();
            var1.sendProgressBarUpdate(this, 10, mCoils);
            var1.sendProgressBarUpdate(this, 11, mActive);
            var1.sendProgressBarUpdate(this, 12, mProgress);
            var1.sendProgressBarUpdate(this, 13, mMaxProgress);
            var1.sendProgressBarUpdate(this, 14, mProgressScale);
        }
    }
    
    @SideOnly(Side.CLIENT)
    public void updateProgressBar(int par1, int par2) {
    	super.updateProgressBar(par1, par2);
    	switch (par1) {
    	case 10: mCoils = par2; break;
    	case 11: mActive = par2; break;
    	case 12: mProgress = par2; break;
    	case 13: mMaxProgress = par2; break;
    	case 14: mProgressScale = par2; break;
    	}
    }
    
    public int getSlotCount() {
    	return 3;
    }

    public int getShiftClickSlotCount() {
    	return 2;
    }
}
