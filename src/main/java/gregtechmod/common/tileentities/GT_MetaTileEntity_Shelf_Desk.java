/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.ItemStack
 */
package gregtechmod.common.tileentities;

import gregtechmod.api.BaseMetaTileEntity;
import gregtechmod.api.MetaTileEntity;
import gregtechmod.common.tileentities.GT_MetaTileEntity_Shelf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class GT_MetaTileEntity_Shelf_Desk
extends GT_MetaTileEntity_Shelf {
    public GT_MetaTileEntity_Shelf_Desk(int aID, String mName, String mNameRegional) {
        super(aID, mName, mNameRegional);
    }

    public GT_MetaTileEntity_Shelf_Desk() {
    }

    @Override
    public MetaTileEntity newMetaEntity(BaseMetaTileEntity aTileEntity) {
        return new GT_MetaTileEntity_Shelf_Desk();
    }

    @Override
    public int getTextureIndex(int aSide, int aFacing, boolean aActive, boolean aRedstone) {
        if (aSide == aFacing) {
            return 222;
        }
        if (aSide == 0) {
            return 32;
        }
        if (aSide == 1) {
            return 29;
        }
        return 40;
    }

    @Override
    public void onRightclick(EntityPlayer aPlayer) {
        ItemStack tStack = aPlayer.inventory.getStackInSlot(aPlayer.inventory.currentItem);
        if (tStack == null) {
            if (this.mInventory[0] != null && this.mInventory[0].stackSize > 0) {
                aPlayer.inventory.setInventorySlotContents(aPlayer.inventory.currentItem, this.mInventory[0]);
                this.mBaseMetaTileEntity.setInventorySlotContents(0, null);
                this.mBaseMetaTileEntity.issueTextureUpdate();
                this.mType = 0;
            }
        } else if (this.mInventory[0] == null) {
            aPlayer.inventory.setInventorySlotContents(aPlayer.inventory.currentItem, null);
            this.mBaseMetaTileEntity.setInventorySlotContents(0, tStack);
            this.mBaseMetaTileEntity.issueTextureUpdate();
        }
    }
}

