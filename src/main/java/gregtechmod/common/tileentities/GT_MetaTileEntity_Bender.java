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
import gregtechmod.api.GT_Recipe;
import gregtechmod.api.MetaTileEntity;
import gregtechmod.common.GT_Utility;
import gregtechmod.common.tileentities.GT_MetaTileEntity_BasicMachine;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;

public class GT_MetaTileEntity_Bender
extends GT_MetaTileEntity_BasicMachine {
    public GT_MetaTileEntity_Bender(int aID, String mName, String mNameRegional) {
        super(aID, mName, mNameRegional);
    }

    public GT_MetaTileEntity_Bender() {
    }

    @Override
    public void onRightclick(EntityPlayer aPlayer) {
        aPlayer.openGui((Object)GT_Mod.instance, 140, this.mBaseMetaTileEntity.worldObj, this.mBaseMetaTileEntity.xCoord, this.mBaseMetaTileEntity.yCoord, this.mBaseMetaTileEntity.zCoord);
    }

    @Override
    public MetaTileEntity newMetaEntity(BaseMetaTileEntity aTileEntity) {
        return new GT_MetaTileEntity_Bender();
    }

    @Override
    public void checkRecipe() {
        int tRecipeIndex;
        GT_Utility.moveStackFromSlotAToSlotB((IInventory)this.mBaseMetaTileEntity, (IInventory)this.mBaseMetaTileEntity, 1, 2, 64, 1, 64, 1);
        GT_Utility.moveStackFromSlotAToSlotB((IInventory)this.mBaseMetaTileEntity, (IInventory)this.mBaseMetaTileEntity, 3, 4, 64, 1, 64, 1);
        if (this.mInventory[2] != null && this.mInventory[2].stackSize > 0 && (tRecipeIndex = GT_Recipe.findEqualBenderRecipeIndex(this.mInventory[2], null)) >= 0 && this.spaceForOutput(((GT_Recipe)GT_Recipe.sBenderRecipes.get((int)tRecipeIndex)).mOutput1, null) && GT_Recipe.isRecipeInputEqual(true, true, this.mInventory[2], null, (GT_Recipe)GT_Recipe.sBenderRecipes.get(tRecipeIndex))) {
            this.mEUt = ((GT_Recipe)GT_Recipe.sBenderRecipes.get((int)tRecipeIndex)).mEUt;
            this.mMaxProgresstime = ((GT_Recipe)GT_Recipe.sBenderRecipes.get((int)tRecipeIndex)).mDuration;
            this.mOutputItem1 = ((GT_Recipe)GT_Recipe.sBenderRecipes.get((int)tRecipeIndex)).mOutput1.copy();
            return;
        }
        this.mOutputItem1 = null;
    }

    @Override
    public int getFrontFacingInactive() {
        return 238;
    }

    @Override
    public int getFrontFacingActive() {
        return 239;
    }

    @Override
    protected String getDescription() {
        return "Boo, he's bad! We want BENDER!!!";
    }
}

