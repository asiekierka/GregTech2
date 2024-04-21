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
import gregtechmod.common.GT_Slot_Output;
import gregtechmod.common.containers.GT_ContainerMetaID_Machine;
import gregtechmod.common.tileentities.GT_TileEntity_ChargeOMat;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;

public class GT_Container_ChargeOMat
extends GT_ContainerMetaID_Machine {
    public GT_Container_ChargeOMat(InventoryPlayer aInventoryPlayer, GT_TileEntity_ChargeOMat aTileEntity, int aID) {
        super(aInventoryPlayer, aTileEntity, aID);
    }

    @Override
    public void addSlots(InventoryPlayer aInventoryPlayer) {
        this.addSlotToContainer(new Slot((IInventory)this.mTileEntity, 0, 8, 5));
        this.addSlotToContainer(new Slot((IInventory)this.mTileEntity, 1, 26, 5));
        this.addSlotToContainer(new Slot((IInventory)this.mTileEntity, 2, 44, 5));
        this.addSlotToContainer(new Slot((IInventory)this.mTileEntity, 3, 8, 23));
        this.addSlotToContainer(new Slot((IInventory)this.mTileEntity, 4, 26, 23));
        this.addSlotToContainer(new Slot((IInventory)this.mTileEntity, 5, 44, 23));
        this.addSlotToContainer(new Slot((IInventory)this.mTileEntity, 6, 8, 41));
        this.addSlotToContainer(new Slot((IInventory)this.mTileEntity, 7, 26, 41));
        this.addSlotToContainer(new Slot((IInventory)this.mTileEntity, 8, 44, 41));
        this.addSlotToContainer(new GT_Slot_Output((IInventory)this.mTileEntity, 9, 97, 5));
        this.addSlotToContainer(new GT_Slot_Output((IInventory)this.mTileEntity, 10, 115, 5));
        this.addSlotToContainer(new GT_Slot_Output((IInventory)this.mTileEntity, 11, 133, 5));
        this.addSlotToContainer(new GT_Slot_Output((IInventory)this.mTileEntity, 12, 97, 23));
        this.addSlotToContainer(new GT_Slot_Output((IInventory)this.mTileEntity, 13, 115, 23));
        this.addSlotToContainer(new GT_Slot_Output((IInventory)this.mTileEntity, 14, 133, 23));
        this.addSlotToContainer(new GT_Slot_Output((IInventory)this.mTileEntity, 15, 97, 41));
        this.addSlotToContainer(new GT_Slot_Output((IInventory)this.mTileEntity, 16, 115, 41));
        this.addSlotToContainer(new GT_Slot_Output((IInventory)this.mTileEntity, 17, 133, 41));
        this.addSlotToContainer(new GT_Slot_Armor(this, (IInventory)aInventoryPlayer, 36, 152, 59, 3));
        this.addSlotToContainer(new GT_Slot_Armor(this, (IInventory)aInventoryPlayer, 37, 152, 41, 2));
        this.addSlotToContainer(new GT_Slot_Armor(this, (IInventory)aInventoryPlayer, 38, 152, 23, 1));
        this.addSlotToContainer(new GT_Slot_Armor(this, (IInventory)aInventoryPlayer, 39, 152, 5, 0));
    }

    @Override
    public int getSlotCount() {
        return 18;
    }

    @Override
    public int getShiftClickSlotCount() {
        return 9;
    }
}

