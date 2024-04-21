/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.inventory.IInventory
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagCompound
 */
package gregtechmod.common.tileentities;

import gregtechmod.GT_Mod;
import gregtechmod.api.BaseMetaTileEntity;
import gregtechmod.api.GT_Recipe;
import gregtechmod.api.MetaTileEntity;
import gregtechmod.common.GT_OreDictUnificator;
import gregtechmod.common.GT_Utility;
import gregtechmod.common.tileentities.GT_MetaTileEntity_BasicMachine;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class GT_MetaTileEntity_AlloySmelter
extends GT_MetaTileEntity_BasicMachine {
    public int mHeatingCoilTier = 0;

    public GT_MetaTileEntity_AlloySmelter(int aID, String mName, String mNameRegional) {
        super(aID, mName, mNameRegional);
    }

    public GT_MetaTileEntity_AlloySmelter() {
    }

    @Override
    public void saveNBTData(NBTTagCompound aNBT) {
        super.saveNBTData(aNBT);
        aNBT.setByte("mHeatingCoilTier", (byte)this.mHeatingCoilTier);
    }

    @Override
    public void loadNBTData(NBTTagCompound aNBT) {
        super.loadNBTData(aNBT);
        this.mHeatingCoilTier = aNBT.getByte("mHeatingCoilTier");
    }

    @Override
    public void setItemNBT(NBTTagCompound aNBT) {
        super.setItemNBT(aNBT);
        if (this.mHeatingCoilTier > 0) {
            aNBT.setByte("mHeatingCoilTier", (byte)this.mHeatingCoilTier);
        }
    }

    @Override
    public void onRightclick(EntityPlayer aPlayer) {
        ItemStack tPlayerItem = aPlayer.inventory.getCurrentItem();
        if (this.mHeatingCoilTier <= 0 && GT_OreDictUnificator.isItemStackInstanceOf(tPlayerItem, "craftingHeatingCoilTier01", false)) {
            if (!aPlayer.capabilities.isCreativeMode) {
                --tPlayerItem.stackSize;
            }
            this.mHeatingCoilTier = 1;
            return;
        }
        if (this.mHeatingCoilTier == 1 && GT_OreDictUnificator.isItemStackInstanceOf(tPlayerItem, "craftingHeatingCoilTier02", false)) {
            if (!aPlayer.capabilities.isCreativeMode) {
                --tPlayerItem.stackSize;
            }
            this.mHeatingCoilTier = 2;
            return;
        }
        if (this.mHeatingCoilTier == 2 && GT_OreDictUnificator.isItemStackInstanceOf(tPlayerItem, "craftingHeatingCoilTier03", false)) {
            if (!aPlayer.capabilities.isCreativeMode) {
                --tPlayerItem.stackSize;
            }
            this.mHeatingCoilTier = 3;
            return;
        }
        aPlayer.openGui((Object)GT_Mod.instance, 137, this.mBaseMetaTileEntity.worldObj, this.mBaseMetaTileEntity.xCoord, this.mBaseMetaTileEntity.yCoord, this.mBaseMetaTileEntity.zCoord);
    }

    @Override
    public MetaTileEntity newMetaEntity(BaseMetaTileEntity aTileEntity) {
        return new GT_MetaTileEntity_AlloySmelter();
    }

    @Override
    public void checkRecipe() {
        int tRecipeIndex;
        GT_Utility.moveStackFromSlotAToSlotB((IInventory)this.mBaseMetaTileEntity, (IInventory)this.mBaseMetaTileEntity, 3, 4, 64, 1, 64, 1);
        if ((this.mInventory[1] != null || this.mInventory[2] != null) && (tRecipeIndex = GT_Recipe.findEqualAlloySmelterRecipeIndex(this.mInventory[1], this.mInventory[2])) >= 0 && this.spaceForOutput(((GT_Recipe)GT_Recipe.sAlloySmelterRecipes.get((int)tRecipeIndex)).mOutput1, null) && GT_Recipe.isRecipeInputEqual(true, true, this.mInventory[1], this.mInventory[2], (GT_Recipe)GT_Recipe.sAlloySmelterRecipes.get(tRecipeIndex))) {
            this.mEUt = ((GT_Recipe)GT_Recipe.sAlloySmelterRecipes.get((int)tRecipeIndex)).mEUt;
            this.mMaxProgresstime = ((GT_Recipe)GT_Recipe.sAlloySmelterRecipes.get((int)tRecipeIndex)).mDuration / (1 + this.mHeatingCoilTier);
            this.mOutputItem1 = ((GT_Recipe)GT_Recipe.sAlloySmelterRecipes.get((int)tRecipeIndex)).mOutput1.copy();
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
        return 252;
    }

    @Override
    public int getFrontFacingActive() {
        return 253;
    }

    @Override
    protected String getDescription() {
        return "HighTech combination Smelter";
    }
}

