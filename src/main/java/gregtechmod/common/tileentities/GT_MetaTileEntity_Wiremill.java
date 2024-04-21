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
import gregtechmod.common.GT_ModHandler;
import gregtechmod.common.GT_Utility;
import gregtechmod.common.tileentities.GT_MetaTileEntity_BasicMachine;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;

public class GT_MetaTileEntity_Wiremill
extends GT_MetaTileEntity_BasicMachine {
    public GT_MetaTileEntity_Wiremill(int aID, String mName, String mNameRegional) {
        super(aID, mName, mNameRegional);
    }

    public GT_MetaTileEntity_Wiremill() {
    }

    @Override
    public void onRightclick(EntityPlayer aPlayer) {
        aPlayer.openGui((Object)GT_Mod.instance, 136, this.mBaseMetaTileEntity.worldObj, this.mBaseMetaTileEntity.xCoord, this.mBaseMetaTileEntity.yCoord, this.mBaseMetaTileEntity.zCoord);
    }

    @Override
    public MetaTileEntity newMetaEntity(BaseMetaTileEntity aTileEntity) {
        return new GT_MetaTileEntity_Wiremill();
    }

    @Override
    public void checkRecipe() {
        GT_Utility.moveStackFromSlotAToSlotB((IInventory)this.mBaseMetaTileEntity, (IInventory)this.mBaseMetaTileEntity, 1, 2, 64, 1, 64, 1);
        GT_Utility.moveStackFromSlotAToSlotB((IInventory)this.mBaseMetaTileEntity, (IInventory)this.mBaseMetaTileEntity, 3, 4, 64, 1, 64, 1);
        if (this.mInventory[2] != null && this.mInventory[2].stackSize > 0) {
            if (this.mInventory[2].stackSize > 2 && this.mInventory[2].isItemEqual(GT_ModHandler.getIC2Item("copperCableItem", 1)) && GT_ModHandler.mFineCopper != null && this.spaceForOutput(this.mOutputItem1 = GT_ModHandler.mFineCopper.copy().splitStack(1), null)) {
                this.mEUt = 1;
                this.mMaxProgresstime = 400;
                this.mInventory[2].stackSize -= 3;
                return;
            }
            if (this.mInventory[2].stackSize > 5 && this.mInventory[2].isItemEqual(GT_ModHandler.getIC2Item("ironCableItem", 1)) && GT_ModHandler.mFineIron != null && this.spaceForOutput(this.mOutputItem1 = GT_ModHandler.mFineIron.copy().splitStack(1), null)) {
                this.mEUt = 2;
                this.mMaxProgresstime = 400;
                this.mInventory[2].stackSize -= 6;
                return;
            }
            int tRecipeIndex = GT_Recipe.findEqualWiremillRecipeIndex(this.mInventory[2], null);
            if (tRecipeIndex >= 0 && this.spaceForOutput(((GT_Recipe)GT_Recipe.sWiremillRecipes.get((int)tRecipeIndex)).mOutput1, null) && GT_Recipe.isRecipeInputEqual(true, true, this.mInventory[2], null, (GT_Recipe)GT_Recipe.sWiremillRecipes.get(tRecipeIndex))) {
                this.mEUt = ((GT_Recipe)GT_Recipe.sWiremillRecipes.get((int)tRecipeIndex)).mEUt;
                this.mMaxProgresstime = ((GT_Recipe)GT_Recipe.sWiremillRecipes.get((int)tRecipeIndex)).mDuration;
                this.mOutputItem1 = ((GT_Recipe)GT_Recipe.sWiremillRecipes.get((int)tRecipeIndex)).mOutput1.copy();
                return;
            }
        }
        this.mOutputItem1 = null;
    }

    @Override
    public int getTopFacingInactive() {
        return 235;
    }

    @Override
    public int getTopFacingActive() {
        return 236;
    }

    @Override
    public int getFrontFacingInactive() {
        return 224;
    }

    @Override
    public int getFrontFacingActive() {
        return 225;
    }

    @Override
    protected String getDescription() {
        return "Produces Wires more efficiently";
    }
}

