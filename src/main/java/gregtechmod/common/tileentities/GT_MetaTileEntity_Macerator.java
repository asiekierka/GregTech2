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

public class GT_MetaTileEntity_Macerator
extends GT_MetaTileEntity_BasicMachine {
    public GT_MetaTileEntity_Macerator(int aID, String mName, String mNameRegional) {
        super(aID, mName, mNameRegional);
    }

    public GT_MetaTileEntity_Macerator() {
    }

    @Override
    public void onRightclick(EntityPlayer aPlayer) {
        aPlayer.openGui((Object)GT_Mod.instance, 131, this.mBaseMetaTileEntity.worldObj, this.mBaseMetaTileEntity.xCoord, this.mBaseMetaTileEntity.yCoord, this.mBaseMetaTileEntity.zCoord);
    }

    @Override
    public MetaTileEntity newMetaEntity(BaseMetaTileEntity aTileEntity) {
        return new GT_MetaTileEntity_Macerator();
    }

    @Override
    public void checkRecipe() {
        GT_Utility.moveStackFromSlotAToSlotB((IInventory)this.mBaseMetaTileEntity, (IInventory)this.mBaseMetaTileEntity, 1, 2, 64, 1, 64, 1);
        GT_Utility.moveStackFromSlotAToSlotB((IInventory)this.mBaseMetaTileEntity, (IInventory)this.mBaseMetaTileEntity, 3, 4, 64, 1, 64, 1);
        if (this.mInventory[2] != null && this.spaceForOutput(GT_ModHandler.getMaceratorOutput(this.mInventory[2], false), null) && null != (this.mOutputItem1 = GT_ModHandler.getMaceratorOutput(this.mInventory[2], true))) {
            this.mEUt = 2;
            this.mMaxProgresstime = 400;
        }
    }

    @Override
    public int getFrontFacingInactive() {
        return 244;
    }

    @Override
    public int getFrontFacingActive() {
        return 245;
    }

    @Override
    public int getTopFacingInactive() {
        return 228;
    }

    @Override
    public int getTopFacingActive() {
        return 229;
    }

    @Override
    protected String getDescription() {
        return "Maceratron-E-901";
    }
}

