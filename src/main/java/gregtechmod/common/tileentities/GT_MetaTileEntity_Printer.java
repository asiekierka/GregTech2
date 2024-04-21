/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.inventory.IInventory
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 */
package gregtechmod.common.tileentities;

import gregtechmod.GT_Mod;
import gregtechmod.api.BaseMetaTileEntity;
import gregtechmod.api.MetaTileEntity;
import gregtechmod.common.GT_ModHandler;
import gregtechmod.common.GT_OreDictUnificator;
import gregtechmod.common.GT_Utility;
import gregtechmod.common.tileentities.GT_MetaTileEntity_BasicMachine;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class GT_MetaTileEntity_Printer
extends GT_MetaTileEntity_BasicMachine {
    public GT_MetaTileEntity_Printer(int aID, String mName, String mNameRegional) {
        super(aID, mName, mNameRegional);
    }

    public GT_MetaTileEntity_Printer() {
    }

    @Override
    public void onRightclick(EntityPlayer aPlayer) {
        aPlayer.openGui((Object)GT_Mod.instance, 142, this.mBaseMetaTileEntity.worldObj, this.mBaseMetaTileEntity.xCoord, this.mBaseMetaTileEntity.yCoord, this.mBaseMetaTileEntity.zCoord);
    }

    @Override
    public int dechargerSlotStartIndex() {
        return 0;
    }

    @Override
    public int dechargerSlotCount() {
        return 0;
    }

    @Override
    public MetaTileEntity newMetaEntity(BaseMetaTileEntity aTileEntity) {
        return new GT_MetaTileEntity_Printer();
    }

    @Override
    public void checkRecipe() {
        GT_Utility.moveStackFromSlotAToSlotB((IInventory)this.mBaseMetaTileEntity, (IInventory)this.mBaseMetaTileEntity, 3, 4, 64, 1, 64, 1);
        if (this.mInventory[3] != null) {
            this.bOutputBlocked = true;
        } else if (this.mInventory[1] != null && this.mInventory[1].stackSize > 0) {
            if (this.mInventory[5] == null) {
                if (this.mInventory[1].isItemEqual(new ItemStack(Item.reed, 1, 0))) {
                    this.mEUt = 1;
                    this.mMaxProgresstime = 200;
                    this.mOutputItem1 = new ItemStack(Item.paper, 1, 0);
                    --this.mInventory[1].stackSize;
                    return;
                }
                if (GT_OreDictUnificator.isItemStackInstanceOf(this.mInventory[1], "dustWood", false)) {
                    this.mEUt = 1;
                    this.mMaxProgresstime = 200;
                    this.mOutputItem1 = new ItemStack(Item.paper, 1, 0);
                    --this.mInventory[1].stackSize;
                    return;
                }
                if (this.mInventory[2] != null && this.mInventory[2].stackSize > 0) {
                    if (this.mInventory[1].isItemEqual(new ItemStack(Item.paper, 1, 0)) && this.mInventory[1].stackSize >= 3 && this.mInventory[2].isItemEqual(new ItemStack(Item.leather, 1, 0))) {
                        this.mEUt = 2;
                        this.mMaxProgresstime = 400;
                        this.mOutputItem1 = new ItemStack(Item.book, 1, 0);
                        this.mInventory[1].stackSize -= 3;
                        --this.mInventory[2].stackSize;
                        return;
                    }
                    if (this.mInventory[1].isItemEqual(new ItemStack(Item.paper, 1, 0)) && this.mInventory[1].stackSize >= 8 && this.mInventory[2].isItemEqual(new ItemStack(Item.compass, 1, 0))) {
                        this.mEUt = 2;
                        this.mMaxProgresstime = 400;
                        this.mOutputItem1 = new ItemStack((Item)Item.emptyMap, 1, 0);
                        this.mInventory[1].stackSize -= 8;
                        --this.mInventory[2].stackSize;
                        return;
                    }
                    if (GT_OreDictUnificator.isItemStackDye(this.mInventory[2])) {
                        this.mOutputItem1 = GT_ModHandler.getRecipeOutput(new ItemStack[]{this.mInventory[1], this.mInventory[2]});
                        if (this.mOutputItem1 != null) {
                            this.mEUt = 2;
                            this.mMaxProgresstime = 200;
                            --this.mInventory[1].stackSize;
                            --this.mInventory[2].stackSize;
                            return;
                        }
                        this.mOutputItem1 = GT_ModHandler.getRecipeOutput(new ItemStack[]{this.mInventory[1], this.mInventory[1], this.mInventory[1], this.mInventory[1], this.mInventory[2], this.mInventory[1], this.mInventory[1], this.mInventory[1], this.mInventory[1]});
                        if (this.mOutputItem1 != null && this.mInventory[1].stackSize > 7) {
                            this.mEUt = 2;
                            this.mMaxProgresstime = 1600;
                            this.mInventory[1].stackSize -= 8;
                            --this.mInventory[2].stackSize;
                            return;
                        }
                    }
                }
            } else if (this.mInventory[2] != null && this.mInventory[2].stackSize > 0) {
                if ((this.mInventory[5].itemID == Item.writableBook.itemID || this.mInventory[5].itemID == Item.writtenBook.itemID) && this.mInventory[1].isItemEqual(new ItemStack(Item.book, 1, 0)) && this.mInventory[2].isItemEqual(new ItemStack(Item.dyePowder, 1, 0))) {
                    this.mEUt = 1;
                    this.mMaxProgresstime = 200;
                    this.mOutputItem1 = this.mInventory[5].copy().splitStack(1);
                    --this.mInventory[1].stackSize;
                    --this.mInventory[2].stackSize;
                    return;
                }
                if (this.mInventory[5].itemID == Item.map.itemID && this.mInventory[1].isItemEqual(new ItemStack((Item)Item.emptyMap, 1, 0)) && this.mInventory[2].isItemEqual(new ItemStack(Item.dyePowder, 1, 0))) {
                    this.mEUt = 1;
                    this.mMaxProgresstime = 100;
                    this.mOutputItem1 = this.mInventory[5].copy().splitStack(1);
                    --this.mInventory[1].stackSize;
                    --this.mInventory[2].stackSize;
                    return;
                }
            }
        }
        this.mOutputItem1 = null;
    }

    @Override
    public boolean hasTwoSeperateInputs() {
        return true;
    }

    @Override
    public int getFrontFacingInactive() {
        return 33;
    }

    @Override
    public int getFrontFacingActive() {
        return 34;
    }

    @Override
    protected String getDescription() {
        return "It's printing Books and can paint Stuff";
    }
}

