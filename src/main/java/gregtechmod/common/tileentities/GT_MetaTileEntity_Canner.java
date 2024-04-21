/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.inventory.IInventory
 *  net.minecraft.item.ItemStack
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
import net.minecraft.item.ItemStack;

public class GT_MetaTileEntity_Canner
extends GT_MetaTileEntity_BasicMachine {
    public GT_MetaTileEntity_Canner(int aID, String mName, String mNameRegional) {
        super(aID, mName, mNameRegional);
    }

    public GT_MetaTileEntity_Canner() {
    }

    @Override
    public void onRightclick(EntityPlayer aPlayer) {
        aPlayer.openGui((Object)GT_Mod.instance, 138, this.mBaseMetaTileEntity.worldObj, this.mBaseMetaTileEntity.xCoord, this.mBaseMetaTileEntity.yCoord, this.mBaseMetaTileEntity.zCoord);
    }

    @Override
    public MetaTileEntity newMetaEntity(BaseMetaTileEntity aTileEntity) {
        return new GT_MetaTileEntity_Canner();
    }

    @Override
    public void checkRecipe() {
        int tRecipeIndex;
        GT_Utility.moveStackFromSlotAToSlotB((IInventory)this.mBaseMetaTileEntity, (IInventory)this.mBaseMetaTileEntity, 3, 4, 64, 1, 64, 1);
        if ((this.mInventory[1] != null || this.mInventory[2] != null) && (tRecipeIndex = GT_Recipe.findEqualCannerRecipeIndex(this.mInventory[1], this.mInventory[2])) >= 0 && this.spaceForOutput(((GT_Recipe)GT_Recipe.sCannerRecipes.get((int)tRecipeIndex)).mOutput1, ((GT_Recipe)GT_Recipe.sCannerRecipes.get((int)tRecipeIndex)).mOutput2) && GT_Recipe.isRecipeInputEqual(true, true, this.mInventory[1], this.mInventory[2], (GT_Recipe)GT_Recipe.sCannerRecipes.get(tRecipeIndex))) {
            this.mEUt = ((GT_Recipe)GT_Recipe.sCannerRecipes.get((int)tRecipeIndex)).mEUt;
            this.mMaxProgresstime = ((GT_Recipe)GT_Recipe.sCannerRecipes.get((int)tRecipeIndex)).mDuration;
            this.mOutputItem1 = ItemStack.copyItemStack((ItemStack)((GT_Recipe)GT_Recipe.sCannerRecipes.get((int)tRecipeIndex)).mOutput1);
            this.mOutputItem2 = ItemStack.copyItemStack((ItemStack)((GT_Recipe)GT_Recipe.sCannerRecipes.get((int)tRecipeIndex)).mOutput2);
            return;
        }
        this.mOutputItem1 = null;
    }

    @Override
    public boolean hasTwoSeperateInputs() {
        return true;
    }

    @Override
    public int getFrontFacingInactive() {
        return 254;
    }

    @Override
    public int getFrontFacingActive() {
        return 255;
    }

    @Override
    protected String getDescription() {
        return "Unmobile Food Canning Machine GTA4";
    }
}

