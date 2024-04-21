package gregtechmod.common.containers;

import gregtechmod.common.GT_Slot_Holo;
import gregtechmod.common.GT_Slot_Output;
import gregtechmod.common.tileentities.GT_TileEntity_Quantumchest;

import java.util.Iterator;

import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class GT_Container_Quantumchest extends GT_ContainerMetaID_Machine {

	public GT_Container_Quantumchest(InventoryPlayer aInventoryPlayer, GT_TileEntity_Quantumchest aTileEntity, int aID) {
		super(aInventoryPlayer, aTileEntity, aID);
	}

    public void addSlots(InventoryPlayer aInventoryPlayer) {
        addSlotToContainer(new Slot(mTileEntity, 0,  80,  17));
        addSlotToContainer(new GT_Slot_Output(mTileEntity, 1,  80,  53));
        addSlotToContainer(new GT_Slot_Holo(mTileEntity, 3,  59,  42, false, false, 64));
    }

    public int mContent;
    
    public void updateCraftingResults() {
        super.updateCraftingResults();
    	if (mTileEntity.worldObj.isRemote) return;
    	mContent = ((GT_TileEntity_Quantumchest)mTileEntity).getContentCount();
    	
        Iterator var2 = this.crafters.iterator();
        while (var2.hasNext()) {
            ICrafting var1 = (ICrafting)var2.next();
            var1.sendProgressBarUpdate(this, 10, mContent & 65535);
            var1.sendProgressBarUpdate(this, 11, mContent >>> 16);
        }
    }
    
    @SideOnly(Side.CLIENT)
    public void updateProgressBar(int par1, int par2) {
    	super.updateProgressBar(par1, par2);
    	switch (par1) {
    	case 10: mContent = mContent & -65536 | par2; break;
    	case 11: mContent = mContent &  65535 | par2 << 16; break;
    	}
    }
    
    public int getSlotCount() {
    	return 3;
    }

    public int getShiftClickSlotCount() {
    	return 1;
    }
}
