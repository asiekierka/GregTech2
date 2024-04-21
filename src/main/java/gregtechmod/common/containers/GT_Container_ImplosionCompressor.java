/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.entity.player.InventoryPlayer
 *  net.minecraft.inventory.Container
 *  net.minecraft.inventory.ICrafting
 *  net.minecraft.inventory.IInventory
 *  net.minecraft.inventory.Slot
 */
package gregtechmod.common.containers;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import gregtechmod.api.BaseMetaTileEntity;
import gregtechmod.common.GT_Slot_Output;
import gregtechmod.common.containers.GT_ContainerMetaTile_Machine;
import gregtechmod.common.tileentities.GT_MetaTileEntity_ImplosionCompressor;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;

import java.util.List;

public class GT_Container_ImplosionCompressor
extends GT_ContainerMetaTile_Machine {
    public int mProgress;
    public int mMaxProgress;
    public int mProgressScale;
    public boolean mMachine = true;

    public GT_Container_ImplosionCompressor(InventoryPlayer aInventoryPlayer, BaseMetaTileEntity aTileEntity, int aID) {
        super(aInventoryPlayer, aTileEntity, aID);
    }

    @Override
    public void addSlots(InventoryPlayer aInventoryPlayer) {
        this.addSlotToContainer(new Slot((IInventory)this.mTileEntity, 0, 34, 16));
        this.addSlotToContainer(new Slot((IInventory)this.mTileEntity, 1, 34, 34));
        this.addSlotToContainer(new GT_Slot_Output((IInventory)this.mTileEntity, 2, 86, 25));
        this.addSlotToContainer(new GT_Slot_Output((IInventory)this.mTileEntity, 3, 104, 25));
    }

    @Override
    public void detectAndSendChanges() {
        super.detectAndSendChanges();
        if (this.mTileEntity.worldObj.isRemote) {
            return;
        }
        this.mMachine = ((GT_MetaTileEntity_ImplosionCompressor)this.mTileEntity.mMetaTileEntity).mMachine;
        this.mProgress = ((GT_MetaTileEntity_ImplosionCompressor)this.mTileEntity.mMetaTileEntity).getProgresstime();
        this.mMaxProgress = ((GT_MetaTileEntity_ImplosionCompressor)this.mTileEntity.mMetaTileEntity).maxProgresstime();
        this.mProgressScale = Math.max(0, Math.min(20, (this.mProgress > 0 ? 1 : 0) + this.mProgress * 20 / (this.mMaxProgress < 1 ? 1 : this.mMaxProgress)));
        for (ICrafting var1 : (List<ICrafting>) this.crafters) {
            var1.sendProgressBarUpdate((Container)this, 100, this.mProgress);
            var1.sendProgressBarUpdate((Container)this, 101, this.mMaxProgress);
            var1.sendProgressBarUpdate((Container)this, 102, this.mProgressScale);
            var1.sendProgressBarUpdate((Container)this, 103, this.mMachine ? 1 : 0);
        }
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    public void updateProgressBar(int par1, int par2) {
        super.updateProgressBar(par1, par2);
        switch (par1) {
            case 100: {
                this.mProgress = par2;
                break;
            }
            case 101: {
                this.mMaxProgress = par2;
                break;
            }
            case 102: {
                this.mProgressScale = par2;
                break;
            }
            case 103: {
                this.mMachine = par2 != 0;
            }
        }
    }

    @Override
    public int getSlotCount() {
        return 4;
    }

    @Override
    public int getShiftClickSlotCount() {
        return 2;
    }
}

