/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.InventoryPlayer
 *  net.minecraft.inventory.IInventory
 *  net.minecraft.inventory.Slot
 *  net.minecraft.item.ItemStack
 */
package gregtechmod.common.containers;

import gregtechmod.common.GT_ModHandler;
import gregtechmod.common.GT_Slot_Holo;
import gregtechmod.common.containers.GT_ContainerMetaID_Machine;
import gregtechmod.common.tileentities.GT_TileEntity_UUMAssembler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class GT_Container_UUMAssembler
extends GT_ContainerMetaID_Machine {
    public GT_Container_UUMAssembler(InventoryPlayer aInventoryPlayer, GT_TileEntity_UUMAssembler aTileEntity, int aID) {
        super(aInventoryPlayer, aTileEntity, aID);
    }

    @Override
    public void addSlots(InventoryPlayer aInventoryPlayer) {
        this.addSlotToContainer(new Slot((IInventory)this.mTileEntity, 0, 152, 5));
        this.addSlotToContainer(new Slot((IInventory)this.mTileEntity, 1, 152, 41));
        this.addSlotToContainer(new GT_Slot_Holo((IInventory)this.mTileEntity, 2, 98, 6, false, true, 1));
        this.addSlotToContainer(new GT_Slot_Holo((IInventory)this.mTileEntity, 3, 115, 6, false, true, 1));
        this.addSlotToContainer(new GT_Slot_Holo((IInventory)this.mTileEntity, 4, 132, 6, false, true, 1));
        this.addSlotToContainer(new GT_Slot_Holo((IInventory)this.mTileEntity, 5, 98, 23, false, true, 1));
        this.addSlotToContainer(new GT_Slot_Holo((IInventory)this.mTileEntity, 6, 115, 23, false, true, 1));
        this.addSlotToContainer(new GT_Slot_Holo((IInventory)this.mTileEntity, 7, 132, 23, false, true, 1));
        this.addSlotToContainer(new GT_Slot_Holo((IInventory)this.mTileEntity, 8, 98, 40, false, true, 1));
        this.addSlotToContainer(new GT_Slot_Holo((IInventory)this.mTileEntity, 9, 115, 40, false, true, 1));
        this.addSlotToContainer(new GT_Slot_Holo((IInventory)this.mTileEntity, 10, 132, 40, false, true, 1));
        this.addSlotToContainer(new GT_Slot_Holo((IInventory)this.mTileEntity, 11, 115, 58, false, true, 64));
        this.addSlotToContainer(new GT_Slot_Holo((IInventory)this.mTileEntity, 12, 9, 6, false, true, 64));
        this.addSlotToContainer(new GT_Slot_Holo((IInventory)this.mTileEntity, 13, 25, 6, false, true, 64));
        this.addSlotToContainer(new GT_Slot_Holo((IInventory)this.mTileEntity, 14, 41, 6, false, true, 64));
        this.addSlotToContainer(new GT_Slot_Holo((IInventory)this.mTileEntity, 15, 57, 6, false, true, 64));
        this.addSlotToContainer(new GT_Slot_Holo((IInventory)this.mTileEntity, 16, 73, 6, false, true, 64));
        this.addSlotToContainer(new GT_Slot_Holo((IInventory)this.mTileEntity, 17, 9, 22, false, true, 64));
        this.addSlotToContainer(new GT_Slot_Holo((IInventory)this.mTileEntity, 18, 25, 22, false, true, 64));
        this.addSlotToContainer(new GT_Slot_Holo((IInventory)this.mTileEntity, 19, 41, 22, false, true, 64));
        this.addSlotToContainer(new GT_Slot_Holo((IInventory)this.mTileEntity, 20, 57, 22, false, true, 64));
        this.addSlotToContainer(new GT_Slot_Holo((IInventory)this.mTileEntity, 21, 73, 22, false, true, 64));
        this.addSlotToContainer(new GT_Slot_Holo((IInventory)this.mTileEntity, 22, 9, 38, false, true, 64));
        this.addSlotToContainer(new GT_Slot_Holo((IInventory)this.mTileEntity, 23, 25, 38, false, true, 64));
        this.addSlotToContainer(new GT_Slot_Holo((IInventory)this.mTileEntity, 24, 41, 38, false, true, 64));
        this.addSlotToContainer(new GT_Slot_Holo((IInventory)this.mTileEntity, 25, 57, 38, false, true, 64));
        this.addSlotToContainer(new GT_Slot_Holo((IInventory)this.mTileEntity, 26, 73, 38, false, true, 64));
        this.addSlotToContainer(new GT_Slot_Holo((IInventory)this.mTileEntity, 27, 9, 54, false, true, 64));
        this.addSlotToContainer(new GT_Slot_Holo((IInventory)this.mTileEntity, 28, 25, 54, false, true, 64));
        this.addSlotToContainer(new GT_Slot_Holo((IInventory)this.mTileEntity, 29, 41, 54, false, true, 64));
        this.addSlotToContainer(new GT_Slot_Holo((IInventory)this.mTileEntity, 30, 57, 54, false, true, 64));
        this.addSlotToContainer(new GT_Slot_Holo((IInventory)this.mTileEntity, 31, 73, 54, false, true, 64));
    }

    @Override
    public ItemStack slotClick(int aSlotIndex, int aMouseclick, int aShifthold, EntityPlayer aEntityPlayer) {
        Slot tSlot;
        if (aSlotIndex < 0) {
            return super.slotClick(aSlotIndex, aMouseclick, aShifthold, aEntityPlayer);
        }
        if (aSlotIndex > 1 && (tSlot = (Slot)this.inventorySlots.get(aSlotIndex)) != null) {
            if (aSlotIndex > 1 && aSlotIndex < 11) {
                if (tSlot.getStack() == null || tSlot.getStack().stackSize == 0) {
                    ItemStack tStack = GT_ModHandler.getIC2Item("matter", 1);
                    tSlot.putStack(tStack);
                    return tStack;
                }
                tSlot.putStack(null);
                return null;
            }
            if (aSlotIndex == 11) {
                if (tSlot.getStack() != null) {
                    this.addToList(tSlot.getStack());
                    tSlot.putStack(null);
                    return null;
                }
            } else if (aSlotIndex > 11 && aSlotIndex < 32) {
                tSlot.putStack(null);
                return null;
            }
        }
        return super.slotClick(aSlotIndex, aMouseclick, aShifthold, aEntityPlayer);
    }

    private void addToList(ItemStack aOutput) {
        Slot tSlot;
        int i;
        int tUUMprice = 0;
        for (i = 2; i < 11; ++i) {
            if (((Slot)this.inventorySlots.get(i)).getStack() == null) continue;
            ++tUUMprice;
        }
        for (i = 12; i < 32; ++i) {
            tSlot = (Slot)this.inventorySlots.get(i);
            if (tSlot == null || tSlot.getStack() == null || !tSlot.getStack().isItemEqual(aOutput)) continue;
            return;
        }
        for (i = 12; i < 32; ++i) {
            tSlot = (Slot)this.inventorySlots.get(i);
            if (tSlot == null || tSlot.getStack() != null || this.mTileEntity.mInventory[i + 20] == null || !this.mTileEntity.mInventory[i + 20].isItemEqual(aOutput)) continue;
            tSlot.putStack(aOutput);
            ((GT_TileEntity_UUMAssembler)this.mTileEntity).setUUMprice(i - 12, tUUMprice);
            return;
        }
        for (i = 12; i < 32; ++i) {
            tSlot = (Slot)this.inventorySlots.get(i);
            if (tSlot == null || tSlot.getStack() != null || this.mTileEntity.mInventory[i + 20] != null) continue;
            tSlot.putStack(aOutput);
            ((GT_TileEntity_UUMAssembler)this.mTileEntity).setUUMprice(i - 12, tUUMprice);
            return;
        }
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

