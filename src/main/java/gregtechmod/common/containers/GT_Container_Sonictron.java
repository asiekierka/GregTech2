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

import gregtechmod.GT_Mod;
import gregtechmod.common.GT_Slot_Holo;
import gregtechmod.common.containers.GT_ContainerMetaID_Machine;
import gregtechmod.common.tileentities.GT_TileEntity_Sonictron;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class GT_Container_Sonictron
extends GT_ContainerMetaID_Machine {
    public GT_Container_Sonictron(InventoryPlayer aInventoryPlayer, GT_TileEntity_Sonictron aTileEntity, int aID) {
        super(aInventoryPlayer, aTileEntity, aID);
    }

    @Override
    public void addSlots(InventoryPlayer aInventoryPlayer) {
        for (int j = 0; j < 8; ++j) {
            for (int i = 0; i < 8; ++i) {
                this.addSlotToContainer(new GT_Slot_Holo((IInventory)this.mTileEntity, i + j * 8, 24 + 16 * i, 19 + 16 * j, false, true, 24));
            }
        }
    }

    @Override
    public ItemStack slotClick(int aSlotIndex, int aMouseclick, int aShifthold, EntityPlayer aEntityPlayer) {
        if (aSlotIndex < 0) {
            return super.slotClick(aSlotIndex, aMouseclick, aShifthold, aEntityPlayer);
        }
        Slot tSlot = (Slot)this.inventorySlots.get(aSlotIndex);
        ItemStack tStack = tSlot.getStack();
        if (tSlot != null) {
            if (aShifthold == 1) {
                tSlot.putStack(null);
                return null;
            }
            if (aMouseclick == 0) {
                if (tStack == null) {
                    tSlot.putStack(((ItemStack)GT_Mod.mSoundItems.get(0)).copy());
                    return null;
                }
                for (int i = 1; i < GT_Mod.mSoundItems.size(); ++i) {
                    if (!((ItemStack)GT_Mod.mSoundItems.get(i - 1)).isItemEqual(tStack)) continue;
                    tSlot.putStack(((ItemStack)GT_Mod.mSoundItems.get(i)).copy());
                    return null;
                }
                tSlot.putStack(null);
                return null;
            }
            if (tStack == null) {
                return null;
            }
            for (int i = 0; i < GT_Mod.mSoundItems.size(); ++i) {
                if (!((ItemStack)GT_Mod.mSoundItems.get(i)).isItemEqual(tStack)) continue;
                ++tStack.stackSize;
                tStack.stackSize %= (Integer)GT_Mod.mSoundCounts.get(i) + 1;
                if (tStack.stackSize == 0) {
                    ++tStack.stackSize;
                }
                return null;
            }
        }
        return null;
    }

    @Override
    public boolean doesBindPlayerInventory() {
        return false;
    }
}

