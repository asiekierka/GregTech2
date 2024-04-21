/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.InventoryPlayer
 *  net.minecraft.inventory.IInventory
 *  net.minecraft.inventory.Slot
 */
package gregtechmod.common.containers;

import gregtechmod.api.BaseMetaTileEntity;
import gregtechmod.common.GT_Slot_Output;
import gregtechmod.common.containers.GT_ContainerMetaTile_Machine;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;

public class GT_Container_AdvancedPump
extends GT_ContainerMetaTile_Machine {
    public int mContent = 0;

    public GT_Container_AdvancedPump(InventoryPlayer aInventoryPlayer, BaseMetaTileEntity aTileEntity, int aID) {
        super(aInventoryPlayer, aTileEntity, aID);
    }

    @Override
    public void addSlots(InventoryPlayer aInventoryPlayer) {
        this.addSlotToContainer(new Slot((IInventory)this.mTileEntity, 0, 116, 17));
        this.addSlotToContainer(new Slot((IInventory)this.mTileEntity, 1, 80, 17));
        this.addSlotToContainer(new GT_Slot_Output((IInventory)this.mTileEntity, 2, 80, 53));
    }

    @Override
    public int getSlotCount() {
        return 3;
    }

    @Override
    public int getShiftClickSlotCount() {
        return 2;
    }
}

