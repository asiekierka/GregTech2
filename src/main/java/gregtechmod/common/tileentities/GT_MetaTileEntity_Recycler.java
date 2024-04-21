/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.inventory.IInventory
 */
package gregtechmod.common.tileentities;

import gregtechmod.GT_Mod;
import gregtechmod.api.BaseMetaTileEntity;
import gregtechmod.api.MetaTileEntity;
import gregtechmod.common.GT_ModHandler;
import gregtechmod.common.GT_Utility;
import gregtechmod.common.tileentities.GT_MetaTileEntity_BasicMachine;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;

public class GT_MetaTileEntity_Recycler
extends GT_MetaTileEntity_BasicMachine {
    public GT_MetaTileEntity_Recycler(int aID, String mName, String mNameRegional) {
        super(aID, mName, mNameRegional);
    }

    public GT_MetaTileEntity_Recycler() {
    }

    @Override
    public void onRightclick(EntityPlayer aPlayer) {
        aPlayer.openGui((Object)GT_Mod.instance, 134, this.mBaseMetaTileEntity.worldObj, this.mBaseMetaTileEntity.xCoord, this.mBaseMetaTileEntity.yCoord, this.mBaseMetaTileEntity.zCoord);
    }

    @Override
    public MetaTileEntity newMetaEntity(BaseMetaTileEntity aTileEntity) {
        return new GT_MetaTileEntity_Recycler();
    }

    @Override
    public void checkRecipe() {
        GT_Utility.moveStackFromSlotAToSlotB((IInventory)this.mBaseMetaTileEntity, (IInventory)this.mBaseMetaTileEntity, 1, 2, 64, 1, 64, 1);
        GT_Utility.moveStackFromSlotAToSlotB((IInventory)this.mBaseMetaTileEntity, (IInventory)this.mBaseMetaTileEntity, 3, 4, 64, 1, 64, 1);
        if (this.mInventory[2] != null && this.spaceForOutput(GT_ModHandler.getIC2Item("scrap", 1), null)) {
            this.mOutputItem1 = GT_ModHandler.getRecyclerOutput(this.mInventory[2], this.mBaseMetaTileEntity.worldObj.rand);
            this.mEUt = 1;
            this.mMaxProgresstime = 45;
            --this.mInventory[2].stackSize;
        }
    }

    @Override
    public int getFrontFacingInactive() {
        return 248;
    }

    @Override
    public int getFrontFacingActive() {
        return 249;
    }

    @Override
    public int getTopFacingInactive() {
        return 232;
    }

    @Override
    public int getTopFacingActive() {
        return 233;
    }

    @Override
    protected String getDescription() {
        return "compress, burn, obliterate and filter EVERYTHING";
    }
}

