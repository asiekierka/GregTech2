/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.InventoryPlayer
 *  net.minecraft.inventory.Container
 *  net.minecraft.inventory.IInventory
 *  net.minecraft.inventory.Slot
 *  net.minecraft.item.ItemStack
 */
package gregtechmod.common.containers;

import gregtechmod.common.GT_Slot_Holo;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class GT_Container_Item_Destructopack
extends Container {
    ItemStack mItem;

    public GT_Container_Item_Destructopack(InventoryPlayer aInventoryPlayer, ItemStack aItem) {
        int i;
        this.mItem = aItem;
        IInventory tInventory = new IInventory(){

            public void setInventorySlotContents(int var1, ItemStack var2) {
            }

            public void openChest() {
            }

            public void onInventoryChanged() {
            }

            public boolean isUseableByPlayer(EntityPlayer var1) {
                return false;
            }

            public ItemStack getStackInSlotOnClosing(int var1) {
                return null;
            }

            public ItemStack getStackInSlot(int var1) {
                return null;
            }

            public int getSizeInventory() {
                return 0;
            }

            public int getInventoryStackLimit() {
                return 0;
            }

            public String getInvName() {
                return null;
            }

            public ItemStack decrStackSize(int var1, int var2) {
                return null;
            }

            public void closeChest() {
            }
        };
        this.addSlotToContainer(new GT_Slot_Holo(tInventory, 0, 80, 17, false, false, 1));
        for (i = 0; i < 3; ++i) {
            for (int j = 0; j < 9; ++j) {
                this.addSlotToContainer(new Slot((IInventory)aInventoryPlayer, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
            }
        }
        for (i = 0; i < 9; ++i) {
            this.addSlotToContainer(new Slot((IInventory)aInventoryPlayer, i, 8 + i * 18, 142));
        }
    }

    public boolean canInteractWith(EntityPlayer player) {
        return true;
    }

    public ItemStack slotClick(int aSlotIndex, int aMouseclick, int aShifthold, EntityPlayer aPlayer) {
        if (aSlotIndex < 0) {
            return super.slotClick(aSlotIndex, aMouseclick, aShifthold, aPlayer);
        }
        if (aSlotIndex < 1) {
            if (aPlayer.inventory.getItemStack() != null) {
                if (aMouseclick == 0) {
                    if (aShifthold == 1) {
                        for (int i = 0; i < aPlayer.inventory.getSizeInventory(); ++i) {
                            ItemStack tStack = aPlayer.inventory.getStackInSlot(i);
                            if (tStack == null || !tStack.isItemEqual(aPlayer.inventory.getItemStack())) continue;
                            aPlayer.inventory.setInventorySlotContents(i, null);
                        }
                    }
                    aPlayer.inventory.setItemStack(null);
                } else if (aPlayer.inventory.getItemStack().stackSize < 2) {
                    aPlayer.inventory.setItemStack(null);
                } else {
                    --aPlayer.inventory.getItemStack().stackSize;
                    return aPlayer.inventory.getItemStack();
                }
            }
            return null;
        }
        return super.slotClick(aSlotIndex, aMouseclick, aShifthold, aPlayer);
    }

    public ItemStack transferStackInSlot(EntityPlayer par1EntityPlayer, int aSlotIndex) {
        Slot slotObject = (Slot)this.inventorySlots.get(aSlotIndex);
        slotObject.putStack(null);
        return null;
    }
}

