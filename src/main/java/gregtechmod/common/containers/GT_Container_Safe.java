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

import gregtechmod.api.BaseMetaTileEntity;
import gregtechmod.common.GT_Slot_Holo;
import gregtechmod.common.containers.GT_ContainerMetaTile_Machine;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class GT_Container_Safe
extends GT_ContainerMetaTile_Machine {
    public GT_Container_Safe(InventoryPlayer aInventoryPlayer, BaseMetaTileEntity aTileEntity, int aID) {
        super(aInventoryPlayer, aTileEntity, aID);
    }

    @Override
    public void addSlots(InventoryPlayer aInventoryPlayer) {
        for (int y = 0; y < 3; ++y) {
            for (int x = 0; x < 9; ++x) {
                this.addSlotToContainer(new Slot((IInventory)this.mTileEntity, 1 + x + y * 9, 8 + x * 18, 23 + y * 18));
            }
        }
        this.addSlotToContainer(new GT_Slot_Holo((IInventory)this.mTileEntity, 28, 80, 5, false, false, 0));
    }

    @Override
    public ItemStack slotClick(int aSlotIndex, int aMouseclick, int aShifthold, EntityPlayer aPlayer) {
        if (aSlotIndex != 27) {
            return super.slotClick(aSlotIndex, aMouseclick, aShifthold, aPlayer);
        }
        Slot tSlot = (Slot)this.inventorySlots.get(aSlotIndex);
        if (tSlot != null && aShifthold == 0 && aMouseclick == 1) {
            ItemStack tStack = aPlayer.inventory.getItemStack();
            if (tStack == null) {
                tSlot.putStack(null);
            } else {
                tStack = tStack.copy();
                tStack.stackSize = 0;
                tSlot.putStack(tStack);
            }
        }
        return null;
    }

    @Override
    public int getSlotCount() {
        return 27;
    }

    @Override
    public int getShiftClickSlotCount() {
        return 27;
    }
}

