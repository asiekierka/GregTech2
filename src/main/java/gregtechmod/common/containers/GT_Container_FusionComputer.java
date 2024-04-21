/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.InventoryPlayer
 *  net.minecraft.inventory.IInventory
 */
package gregtechmod.common.containers;

import gregtechmod.api.BaseMetaTileEntity;
import gregtechmod.common.GT_Slot_Holo;
import gregtechmod.common.containers.GT_ContainerMetaTile_Machine;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.IInventory;

public class GT_Container_FusionComputer
extends GT_ContainerMetaTile_Machine {
    public GT_Container_FusionComputer(InventoryPlayer aInventoryPlayer, BaseMetaTileEntity aTileEntity, int aID) {
        super(aInventoryPlayer, aTileEntity, aID);
    }

    @Override
    public void addSlots(InventoryPlayer aInventoryPlayer) {
        this.addSlotToContainer(new GT_Slot_Holo((IInventory)this.mTileEntity, 0, 155, 23, false, false, 1));
        this.addSlotToContainer(new GT_Slot_Holo((IInventory)this.mTileEntity, 0, 155, 41, false, false, 1));
        this.addSlotToContainer(new GT_Slot_Holo((IInventory)this.mTileEntity, 0, 155, 59, false, false, 1));
        this.addSlotToContainer(new GT_Slot_Holo((IInventory)this.mTileEntity, 0, 155, 77, false, false, 1));
        this.addSlotToContainer(new GT_Slot_Holo((IInventory)this.mTileEntity, 0, 155, 95, false, false, 1));
        this.addSlotToContainer(new GT_Slot_Holo((IInventory)this.mTileEntity, 0, 155, 113, false, false, 1));
    }

    @Override
    public int getSlotCount() {
        return 0;
    }

    @Override
    public int getShiftClickSlotCount() {
        return 0;
    }

    @Override
    public boolean doesBindPlayerInventory() {
        return false;
    }
}

