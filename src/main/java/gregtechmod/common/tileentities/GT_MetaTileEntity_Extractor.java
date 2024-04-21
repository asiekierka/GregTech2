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

public class GT_MetaTileEntity_Extractor
extends GT_MetaTileEntity_BasicMachine {
    public GT_MetaTileEntity_Extractor(int aID, String mName, String mNameRegional) {
        super(aID, mName, mNameRegional);
    }

    public GT_MetaTileEntity_Extractor() {
    }

    @Override
    public void onRightclick(EntityPlayer aPlayer) {
        aPlayer.openGui((Object)GT_Mod.instance, 132, this.mBaseMetaTileEntity.worldObj, this.mBaseMetaTileEntity.xCoord, this.mBaseMetaTileEntity.yCoord, this.mBaseMetaTileEntity.zCoord);
    }

    @Override
    public MetaTileEntity newMetaEntity(BaseMetaTileEntity aTileEntity) {
        return new GT_MetaTileEntity_Extractor();
    }

    @Override
    public void checkRecipe() {
        GT_Utility.moveStackFromSlotAToSlotB((IInventory)this.mBaseMetaTileEntity, (IInventory)this.mBaseMetaTileEntity, 1, 2, 64, 1, 64, 1);
        GT_Utility.moveStackFromSlotAToSlotB((IInventory)this.mBaseMetaTileEntity, (IInventory)this.mBaseMetaTileEntity, 3, 4, 64, 1, 64, 1);
        if (this.mInventory[2] != null && this.spaceForOutput(GT_ModHandler.getExtractorOutput(this.mInventory[2], false), null) && null != (this.mOutputItem1 = GT_ModHandler.getExtractorOutput(this.mInventory[2], true))) {
            this.mEUt = 2;
            this.mMaxProgresstime = 400;
        }
    }

    @Override
    public int getSideFacingInactive() {
        return 242;
    }

    @Override
    public int getSideFacingActive() {
        return 227;
    }

    @Override
    public int getFrontFacingInactive() {
        return 246;
    }

    @Override
    public int getFrontFacingActive() {
        return 247;
    }

    @Override
    public int getTopFacingInactive() {
        return 230;
    }

    @Override
    public int getTopFacingActive() {
        return 231;
    }

    @Override
    protected String getDescription() {
        return "Dejuicer-Device of Doom - D123";
    }
}

