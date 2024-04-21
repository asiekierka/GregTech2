/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.InventoryPlayer
 *  net.minecraft.inventory.IInventory
 *  net.minecraft.inventory.Slot
 */
package gregtechmod.common.containers;

import gregtechmod.common.GT_Slot_Armor;
import gregtechmod.common.containers.GT_ContainerMetaID_Machine;
import gregtechmod.common.tileentities.GT_TileEntity_LESU;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;

public class GT_Container_LESU
extends GT_ContainerMetaID_Machine {
    public GT_Container_LESU(InventoryPlayer aInventoryPlayer, GT_TileEntity_LESU aTileEntity, int aID) {
        super(aInventoryPlayer, aTileEntity, aID);
    }

    @Override
    public void addSlots(InventoryPlayer aInventoryPlayer) {
        this.addSlotToContainer(new Slot((IInventory)this.mTileEntity, 0, 128, 14));
        this.addSlotToContainer(new Slot((IInventory)this.mTileEntity, 1, 128, 50));
        this.addSlotToContainer(new GT_Slot_Armor(this, (IInventory)aInventoryPlayer, 36, 152, 59, 3));
        this.addSlotToContainer(new GT_Slot_Armor(this, (IInventory)aInventoryPlayer, 37, 152, 41, 2));
        this.addSlotToContainer(new GT_Slot_Armor(this, (IInventory)aInventoryPlayer, 38, 152, 23, 1));
        this.addSlotToContainer(new GT_Slot_Armor(this, (IInventory)aInventoryPlayer, 39, 152, 5, 0));
    }

    @Override
    public int getSlotCount() {
        return 2;
    }

    @Override
    public int getShiftClickSlotCount() {
        return 2;
    }
}

