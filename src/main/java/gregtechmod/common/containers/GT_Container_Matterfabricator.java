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
import gregtechmod.common.GT_Slot_Output;
import gregtechmod.common.containers.GT_ContainerMetaID_Machine;
import gregtechmod.common.tileentities.GT_TileEntity_Matterfabricator;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;

import java.util.List;

public class GT_Container_Matterfabricator
extends GT_ContainerMetaID_Machine {
    public int mPercentualProgress;

    public GT_Container_Matterfabricator(InventoryPlayer aInventoryPlayer, GT_TileEntity_Matterfabricator aTileEntity, int aID) {
        super(aInventoryPlayer, aTileEntity, aID);
    }

    @Override
    public void addSlots(InventoryPlayer aInventoryPlayer) {
        this.addSlotToContainer(new Slot((IInventory)this.mTileEntity, 1, 8, 14));
        this.addSlotToContainer(new Slot((IInventory)this.mTileEntity, 2, 26, 14));
        this.addSlotToContainer(new Slot((IInventory)this.mTileEntity, 3, 44, 14));
        this.addSlotToContainer(new Slot((IInventory)this.mTileEntity, 4, 62, 14));
        this.addSlotToContainer(new Slot((IInventory)this.mTileEntity, 5, 8, 32));
        this.addSlotToContainer(new Slot((IInventory)this.mTileEntity, 6, 26, 32));
        this.addSlotToContainer(new Slot((IInventory)this.mTileEntity, 7, 44, 32));
        this.addSlotToContainer(new Slot((IInventory)this.mTileEntity, 8, 62, 32));
        this.addSlotToContainer(new GT_Slot_Output((IInventory)this.mTileEntity, 0, 128, 14));
    }

    @Override
    public void detectAndSendChanges() {
        super.detectAndSendChanges();
        if (this.mTileEntity.worldObj.isRemote) {
            return;
        }
        this.mPercentualProgress = Math.max(0, Math.min(30000, ((GT_TileEntity_Matterfabricator)this.mTileEntity).getProgresstime() / Math.max(1, ((GT_TileEntity_Matterfabricator)this.mTileEntity).maxProgresstime() / 100)));
        for (ICrafting var1 : (List<ICrafting>) this.crafters) {
            var1.sendProgressBarUpdate((Container)this, 100, this.mPercentualProgress);
        }
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    public void updateProgressBar(int par1, int par2) {
        super.updateProgressBar(par1, par2);
        switch (par1) {
            case 100: {
                this.mPercentualProgress = par2;
            }
        }
    }

    @Override
    public int getSlotCount() {
        return 9;
    }

    @Override
    public int getShiftClickSlotCount() {
        return 8;
    }
}

