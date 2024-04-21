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

import gregtechmod.common.GT_Slot_Armor;
import gregtechmod.common.GT_Slot_Holo;
import gregtechmod.common.containers.GT_ContainerMetaID_Machine;
import gregtechmod.common.tileentities.GT_TileEntity_AESU;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class GT_Container_AESU
extends GT_ContainerMetaID_Machine {
    public GT_Container_AESU(InventoryPlayer aInventoryPlayer, GT_TileEntity_AESU aTileEntity, int aID) {
        super(aInventoryPlayer, aTileEntity, aID);
    }

    @Override
    public void addSlots(InventoryPlayer aInventoryPlayer) {
        this.addSlotToContainer(new Slot((IInventory)this.mTileEntity, 0, 128, 14));
        this.addSlotToContainer(new Slot((IInventory)this.mTileEntity, 1, 128, 50));
        this.addSlotToContainer(new GT_Slot_Holo((IInventory)this.mTileEntity, 2, 107, 5, false, false, 1));
        this.addSlotToContainer(new GT_Slot_Holo((IInventory)this.mTileEntity, 2, 107, 23, false, false, 1));
        this.addSlotToContainer(new GT_Slot_Holo((IInventory)this.mTileEntity, 2, 107, 41, false, false, 1));
        this.addSlotToContainer(new GT_Slot_Holo((IInventory)this.mTileEntity, 2, 107, 59, false, false, 1));
        this.addSlotToContainer(new GT_Slot_Armor(this, (IInventory)aInventoryPlayer, 36, 152, 59, 3));
        this.addSlotToContainer(new GT_Slot_Armor(this, (IInventory)aInventoryPlayer, 37, 152, 41, 2));
        this.addSlotToContainer(new GT_Slot_Armor(this, (IInventory)aInventoryPlayer, 38, 152, 23, 1));
        this.addSlotToContainer(new GT_Slot_Armor(this, (IInventory)aInventoryPlayer, 39, 152, 5, 0));
    }

    @Override
    public ItemStack slotClick(int aSlotIndex, int aMouseclick, int aShifthold, EntityPlayer aPlayer) {
        if (aSlotIndex < 0) {
            return super.slotClick(aSlotIndex, aMouseclick, aShifthold, aPlayer);
        }
        Slot tSlot = (Slot)this.inventorySlots.get(aSlotIndex);
        ItemStack tStack = tSlot.getStack();
        if (tSlot != null) {
            int tOutput = ((GT_TileEntity_AESU)this.mTileEntity).mOutput;
            switch (aSlotIndex) {
                case 2: {
                    ((GT_TileEntity_AESU)this.mTileEntity).mOutput = Math.min(2048, tOutput + (aShifthold == 1 ? 256 : 64));
                    return null;
                }
                case 3: {
                    ((GT_TileEntity_AESU)this.mTileEntity).mOutput = Math.min(2048, tOutput + (aShifthold == 1 ? 16 : 1));
                    return null;
                }
                case 4: {
                    ((GT_TileEntity_AESU)this.mTileEntity).mOutput = Math.max(0, tOutput - (aShifthold == 1 ? 16 : 1));
                    return null;
                }
                case 5: {
                    ((GT_TileEntity_AESU)this.mTileEntity).mOutput = Math.max(0, tOutput - (aShifthold == 1 ? 256 : 64));
                    return null;
                }
            }
        }
        return super.slotClick(aSlotIndex, aMouseclick, aShifthold, aPlayer);
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

