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
 *  net.minecraft.tileentity.TileEntity
 */
package gregtechmod.common.containers;

import gregtechmod.common.GT_Slot_Armor;
import gregtechmod.common.GT_Slot_Holo;
import gregtechmod.common.GT_Slot_Output;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;

public class GT_Container
extends Container {
    public GT_Container(InventoryPlayer aInventoryPlayer, TileEntity aTileEntity) {
    }

    public void addSlots(InventoryPlayer aInventoryPlayer) {
    }

    public int getSlotCount() {
        return 0;
    }

    public int getAllSlotCount() {
        if (this.inventorySlots != null) {
            if (this.doesBindPlayerInventory()) {
                return this.inventorySlots.size() - 36;
            }
            return this.inventorySlots.size();
        }
        return this.getSlotCount();
    }

    public int getSlotStartIndex() {
        return 0;
    }

    public int getShiftClickSlotCount() {
        return 0;
    }

    public boolean doesBindPlayerInventory() {
        return true;
    }

    public boolean canInteractWith(EntityPlayer aPlayer) {
        return false;
    }

    protected void bindPlayerInventory(InventoryPlayer aInventoryPlayer) {
        int i;
        for (i = 0; i < 3; ++i) {
            for (int j = 0; j < 9; ++j) {
                this.addSlotToContainer(new Slot((IInventory)aInventoryPlayer, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
            }
        }
        for (i = 0; i < 9; ++i) {
            this.addSlotToContainer(new Slot((IInventory)aInventoryPlayer, i, 8 + i * 18, 142));
        }
    }

    public ItemStack slotClick(int aSlotIndex, int aMouseclick, int aShifthold, EntityPlayer aPlayer) {
        Slot aSlot;
        if (aSlotIndex >= 0) {
            if (this.inventorySlots.get(aSlotIndex) == null || this.inventorySlots.get(aSlotIndex) instanceof GT_Slot_Holo) {
                return null;
            }
            if (!(this.inventorySlots.get(aSlotIndex) instanceof GT_Slot_Armor || aSlotIndex >= this.getAllSlotCount() || aSlotIndex >= this.getSlotStartIndex() && aSlotIndex < this.getSlotStartIndex() + this.getSlotCount())) {
                return null;
            }
        }
        ItemStack rStack = null;
        InventoryPlayer aPlayerInventory = aPlayer.inventory;
        if (!(aShifthold != 0 && aShifthold != 1 || aMouseclick != 0 && aMouseclick != 1)) {
            if (aSlotIndex == -999) {
                if (aPlayerInventory.getItemStack() != null && aSlotIndex == -999) {
                    if (aMouseclick == 0) {
                        aPlayer.dropPlayerItem(aPlayerInventory.getItemStack());
                        aPlayerInventory.setItemStack((ItemStack)null);
                    }
                    if (aMouseclick == 1) {
                        aPlayer.dropPlayerItem(aPlayerInventory.getItemStack().splitStack(1));
                        if (aPlayerInventory.getItemStack().stackSize == 0) {
                            aPlayerInventory.setItemStack((ItemStack)null);
                        }
                    }
                }
            } else if (aShifthold == 1) {
                ItemStack tTempStack;
                Slot aSlot2 = (Slot)this.inventorySlots.get(aSlotIndex);
                if (aSlot2 != null && aSlot2.canTakeStack(aPlayer) && (tTempStack = this.transferStackInSlot(aPlayer, aSlotIndex)) != null) {
                    int var12 = tTempStack.itemID;
                    rStack = tTempStack.copy();
                    if (aSlot2 != null && aSlot2.getStack() != null && aSlot2.getStack().itemID == var12) {
                        this.slotClick(aSlotIndex, aMouseclick, aShifthold, aPlayer);
                    }
                }
            } else {
                if (aSlotIndex < 0) {
                    return null;
                }
                Slot aSlot3 = (Slot)this.inventorySlots.get(aSlotIndex);
                if (aSlot3 != null) {
                    ItemStack tTempStack = aSlot3.getStack();
                    ItemStack var13 = aPlayerInventory.getItemStack();
                    if (tTempStack != null) {
                        rStack = tTempStack.copy();
                    }
                    if (tTempStack == null) {
                        if (var13 != null && aSlot3.isItemValid(var13)) {
                            int tTempStackSize;
                            int n = tTempStackSize = aMouseclick == 0 ? var13.stackSize : 1;
                            if (tTempStackSize > aSlot3.getSlotStackLimit()) {
                                tTempStackSize = aSlot3.getSlotStackLimit();
                            }
                            aSlot3.putStack(var13.splitStack(tTempStackSize));
                            if (var13.stackSize == 0) {
                                aPlayerInventory.setItemStack((ItemStack)null);
                            }
                        }
                    } else if (aSlot3.canTakeStack(aPlayer)) {
                        int tTempStackSize;
                        if (var13 == null) {
                            int tTempStackSize2 = aMouseclick == 0 ? tTempStack.stackSize : (tTempStack.stackSize + 1) / 2;
                            ItemStack aHoldStack = aSlot3.decrStackSize(tTempStackSize2);
                            aPlayerInventory.setItemStack(aHoldStack);
                            if (tTempStack.stackSize == 0) {
                                aSlot3.putStack((ItemStack)null);
                            }
                            aSlot3.onPickupFromSlot(aPlayer, aPlayerInventory.getItemStack());
                        } else if (aSlot3.isItemValid(var13)) {
                            if (tTempStack.itemID == var13.itemID && tTempStack.getItemDamage() == var13.getItemDamage() && ItemStack.areItemStackTagsEqual((ItemStack)tTempStack, (ItemStack)var13)) {
                                int tTempStackSize3;
                                int n = tTempStackSize3 = aMouseclick == 0 ? var13.stackSize : 1;
                                if (tTempStackSize3 > aSlot3.getSlotStackLimit() - tTempStack.stackSize) {
                                    tTempStackSize3 = aSlot3.getSlotStackLimit() - tTempStack.stackSize;
                                }
                                if (tTempStackSize3 > var13.getMaxStackSize() - tTempStack.stackSize) {
                                    tTempStackSize3 = var13.getMaxStackSize() - tTempStack.stackSize;
                                }
                                var13.splitStack(tTempStackSize3);
                                if (var13.stackSize == 0) {
                                    aPlayerInventory.setItemStack((ItemStack)null);
                                }
                                tTempStack.stackSize += tTempStackSize3;
                            } else if (var13.stackSize <= aSlot3.getSlotStackLimit()) {
                                aSlot3.putStack(var13);
                                aPlayerInventory.setItemStack(tTempStack);
                            }
                        } else if (tTempStack.itemID == var13.itemID && var13.getMaxStackSize() > 1 && (!tTempStack.getHasSubtypes() || tTempStack.getItemDamage() == var13.getItemDamage()) && ItemStack.areItemStackTagsEqual((ItemStack)tTempStack, (ItemStack)var13) && (tTempStackSize = tTempStack.stackSize) > 0 && tTempStackSize + var13.stackSize <= var13.getMaxStackSize()) {
                            var13.stackSize += tTempStackSize;
                            tTempStack = aSlot3.decrStackSize(tTempStackSize);
                            if (tTempStack.stackSize == 0) {
                                aSlot3.putStack((ItemStack)null);
                            }
                            aSlot3.onPickupFromSlot(aPlayer, aPlayerInventory.getItemStack());
                        }
                    }
                    aSlot3.onSlotChanged();
                }
            }
        } else if (aShifthold == 2 && aMouseclick >= 0 && aMouseclick < 9) {
            Slot aSlot4 = (Slot)this.inventorySlots.get(aSlotIndex);
            if (aSlot4.canTakeStack(aPlayer)) {
                ItemStack tTempStack = aPlayerInventory.getStackInSlot(aMouseclick);
                boolean var9 = tTempStack == null || aSlot4.inventory == aPlayerInventory && aSlot4.isItemValid(tTempStack);
                int tTempStackSize = -1;
                if (!var9) {
                    tTempStackSize = aPlayerInventory.getFirstEmptyStack();
                    var9 |= tTempStackSize > -1;
                }
                if (aSlot4.getHasStack() && var9) {
                    ItemStack aHoldStack = aSlot4.getStack();
                    aPlayerInventory.setInventorySlotContents(aMouseclick, aHoldStack);
                    if (!(aSlot4.inventory == aPlayerInventory && aSlot4.isItemValid(tTempStack) || tTempStack == null)) {
                        if (tTempStackSize > -1) {
                            aPlayerInventory.addItemStackToInventory(tTempStack);
                            aSlot4.decrStackSize(aHoldStack.stackSize);
                            aSlot4.putStack((ItemStack)null);
                            aSlot4.onPickupFromSlot(aPlayer, aHoldStack);
                        }
                    } else {
                        aSlot4.decrStackSize(aHoldStack.stackSize);
                        aSlot4.putStack(tTempStack);
                        aSlot4.onPickupFromSlot(aPlayer, aHoldStack);
                    }
                } else if (!aSlot4.getHasStack() && tTempStack != null && aSlot4.isItemValid(tTempStack)) {
                    aPlayerInventory.setInventorySlotContents(aMouseclick, (ItemStack)null);
                    aSlot4.putStack(tTempStack);
                }
            }
        } else if (aShifthold == 3 && aPlayer.capabilities.isCreativeMode && aPlayerInventory.getItemStack() == null && aSlotIndex >= 0 && (aSlot = (Slot)this.inventorySlots.get(aSlotIndex)) != null && aSlot.getHasStack()) {
            ItemStack tTempStack = aSlot.getStack().copy();
            tTempStack.stackSize = tTempStack.getMaxStackSize();
            aPlayerInventory.setItemStack(tTempStack);
        }
        return rStack;
    }

    public ItemStack transferStackInSlot(EntityPlayer aPlayer, int aSlotIndex) {
        ItemStack stack = null;
        Slot slotObject = (Slot)this.inventorySlots.get(aSlotIndex);
        if (this.getSlotCount() > 0 && slotObject != null && slotObject.getHasStack() && !(slotObject instanceof GT_Slot_Holo)) {
            ItemStack stackInSlot = slotObject.getStack();
            stack = stackInSlot.copy();
            if (aSlotIndex < this.getAllSlotCount() ? this.doesBindPlayerInventory() && !this.mergeItemStack(stackInSlot, this.getAllSlotCount(), this.getAllSlotCount() + 36, true) : !this.mergeItemStack(stackInSlot, this.getSlotStartIndex(), this.getSlotStartIndex() + this.getShiftClickSlotCount(), false)) {
                return null;
            }
            if (stackInSlot.stackSize == 0) {
                slotObject.putStack(null);
            } else {
                slotObject.onSlotChanged();
            }
        }
        return stack;
    }

    protected boolean mergeItemStack(ItemStack aStack, int aStartIndex, int aSlotCount, boolean par4) {
        ItemStack var8;
        Slot var7;
        boolean var5 = false;
        int var6 = aStartIndex;
        if (par4) {
            var6 = aSlotCount - 1;
        }
        if (aStack.isStackable()) {
            while (aStack.stackSize > 0 && (!par4 && var6 < aSlotCount || par4 && var6 >= aStartIndex)) {
                var7 = (Slot)this.inventorySlots.get(var6);
                var8 = var7.getStack();
                if (!(var7 instanceof GT_Slot_Holo || var7 instanceof GT_Slot_Output || var8 == null || var8.itemID != aStack.itemID || aStack.getHasSubtypes() && aStack.getItemDamage() != var8.getItemDamage() || !ItemStack.areItemStackTagsEqual((ItemStack)aStack, (ItemStack)var8))) {
                    int var9 = var8.stackSize + aStack.stackSize;
                    if (var9 <= aStack.getMaxStackSize()) {
                        aStack.stackSize = 0;
                        var8.stackSize = var9;
                        var7.onSlotChanged();
                        var5 = true;
                    } else if (var8.stackSize < aStack.getMaxStackSize()) {
                        aStack.stackSize -= aStack.getMaxStackSize() - var8.stackSize;
                        var8.stackSize = aStack.getMaxStackSize();
                        var7.onSlotChanged();
                        var5 = true;
                    }
                }
                if (par4) {
                    --var6;
                    continue;
                }
                ++var6;
            }
        }
        if (aStack.stackSize > 0) {
            var6 = par4 ? aSlotCount - 1 : aStartIndex;
            while (!par4 && var6 < aSlotCount || par4 && var6 >= aStartIndex) {
                var7 = (Slot)this.inventorySlots.get(var6);
                var8 = var7.getStack();
                if (var8 == null) {
                    var7.putStack(aStack.copy());
                    var7.onSlotChanged();
                    aStack.stackSize = 0;
                    var5 = true;
                    break;
                }
                if (par4) {
                    --var6;
                    continue;
                }
                ++var6;
            }
        }
        return var5;
    }
}

