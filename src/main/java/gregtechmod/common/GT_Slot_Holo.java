/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.inventory.IInventory
 *  net.minecraft.inventory.Slot
 *  net.minecraft.item.ItemStack
 */
package gregtechmod.common;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class GT_Slot_Holo
extends Slot {
    public boolean mCanInsertItem;
    public boolean mCanStackItem;
    public int mMaxStacksize = 127;

    public GT_Slot_Holo(IInventory par1iInventory, int par2, int par3, int par4, boolean aCanInsertItem, boolean aCanStackItem, int aMaxStacksize) {
        super(par1iInventory, par2, par3, par4);
        this.mCanInsertItem = aCanInsertItem;
        this.mCanStackItem = aCanStackItem;
        this.mMaxStacksize = aMaxStacksize;
    }

    public boolean isItemValid(ItemStack par1ItemStack) {
        return this.mCanInsertItem;
    }

    public int getSlotStackLimit() {
        return this.mMaxStacksize;
    }

    public boolean getHasStack() {
        return false;
    }

    public ItemStack decrStackSize(int par1) {
        if (!this.mCanStackItem) {
            return null;
        }
        return super.decrStackSize(par1);
    }
}

