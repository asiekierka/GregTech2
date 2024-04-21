/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.InventoryPlayer
 *  net.minecraft.inventory.Container
 *  net.minecraft.inventory.ICrafting
 *  net.minecraft.inventory.IInventory
 *  net.minecraft.inventory.Slot
 *  net.minecraft.item.ItemStack
 */
package gregtechmod.common.containers;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import gregtechmod.api.BaseMetaTileEntity;
import gregtechmod.common.GT_Slot_Holo;
import gregtechmod.common.GT_Slot_Output;
import gregtechmod.common.containers.GT_ContainerMetaTile_Machine;
import gregtechmod.common.tileentities.GT_MetaTileEntity_ElectricAutoWorkbench;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

import java.util.List;

public class GT_Container_ElectricAutoWorkbench
extends GT_ContainerMetaTile_Machine {
    public int mMode;
    public int mThroughPut;

    public GT_Container_ElectricAutoWorkbench(InventoryPlayer aInventoryPlayer, BaseMetaTileEntity aTileEntity, int aID) {
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
        this.addSlotToContainer(new GT_Slot_Output((IInventory)this.mTileEntity, 9, 8, 60));
        this.addSlotToContainer(new GT_Slot_Output((IInventory)this.mTileEntity, 10, 26, 60));
        this.addSlotToContainer(new GT_Slot_Output((IInventory)this.mTileEntity, 11, 44, 60));
        this.addSlotToContainer(new GT_Slot_Output((IInventory)this.mTileEntity, 12, 62, 60));
        this.addSlotToContainer(new GT_Slot_Output((IInventory)this.mTileEntity, 13, 80, 60));
        this.addSlotToContainer(new GT_Slot_Output((IInventory)this.mTileEntity, 14, 98, 60));
        this.addSlotToContainer(new GT_Slot_Output((IInventory)this.mTileEntity, 15, 116, 60));
        this.addSlotToContainer(new GT_Slot_Output((IInventory)this.mTileEntity, 16, 134, 60));
        this.addSlotToContainer(new GT_Slot_Output((IInventory)this.mTileEntity, 17, 152, 60));
        this.addSlotToContainer(new GT_Slot_Output((IInventory)this.mTileEntity, 18, 152, 41));
        this.addSlotToContainer(new GT_Slot_Holo((IInventory)this.mTileEntity, 19, 64, 6, false, false, 1));
        this.addSlotToContainer(new GT_Slot_Holo((IInventory)this.mTileEntity, 20, 81, 6, false, false, 1));
        this.addSlotToContainer(new GT_Slot_Holo((IInventory)this.mTileEntity, 21, 98, 6, false, false, 1));
        this.addSlotToContainer(new GT_Slot_Holo((IInventory)this.mTileEntity, 22, 64, 23, false, false, 1));
        this.addSlotToContainer(new GT_Slot_Holo((IInventory)this.mTileEntity, 23, 81, 23, false, false, 1));
        this.addSlotToContainer(new GT_Slot_Holo((IInventory)this.mTileEntity, 24, 98, 23, false, false, 1));
        this.addSlotToContainer(new GT_Slot_Holo((IInventory)this.mTileEntity, 25, 64, 40, false, false, 1));
        this.addSlotToContainer(new GT_Slot_Holo((IInventory)this.mTileEntity, 26, 81, 40, false, false, 1));
        this.addSlotToContainer(new GT_Slot_Holo((IInventory)this.mTileEntity, 27, 98, 40, false, false, 1));
        this.addSlotToContainer(new GT_Slot_Holo((IInventory)this.mTileEntity, 28, 152, 5, false, false, 1));
        this.addSlotToContainer(new GT_Slot_Holo((IInventory)this.mTileEntity, 29, 121, 41, false, false, 1));
        this.addSlotToContainer(new GT_Slot_Holo((IInventory)this.mTileEntity, 29, 121, 5, false, false, 1));
    }

    @Override
    public ItemStack slotClick(int aSlotIndex, int aMouseclick, int aShifthold, EntityPlayer aPlayer) {
        if (aSlotIndex < 18) {
            return super.slotClick(aSlotIndex, aMouseclick, aShifthold, aPlayer);
        }
        Slot tSlot = (Slot)this.inventorySlots.get(aSlotIndex);
        if (tSlot != null) {
            if (this.mTileEntity.mMetaTileEntity == null) {
                return null;
            }
            if (aSlotIndex > 18 && aSlotIndex < 28) {
                ItemStack tStack = aPlayer.inventory.getItemStack();
                if (tStack != null) {
                    tStack = tStack.copy();
                    tStack.stackSize = 1;
                }
                tSlot.putStack(tStack);
                return null;
            }
            if (aSlotIndex == 28) {
                return null;
            }
            if (aSlotIndex == 29) {
                if (aMouseclick == 0) {
                    ((GT_MetaTileEntity_ElectricAutoWorkbench)this.mTileEntity.mMetaTileEntity).switchModeForward();
                } else {
                    ((GT_MetaTileEntity_ElectricAutoWorkbench)this.mTileEntity.mMetaTileEntity).switchModeBackward();
                }
                return null;
            }
            if (aSlotIndex == 30) {
                ((GT_MetaTileEntity_ElectricAutoWorkbench)this.mTileEntity.mMetaTileEntity).switchThrough();
                return null;
            }
        }
        return super.slotClick(aSlotIndex, aMouseclick, aShifthold, aPlayer);
    }

    @Override
    public void detectAndSendChanges() {
        super.detectAndSendChanges();
        if (this.mTileEntity.worldObj.isRemote) {
            return;
        }
        this.mMode = ((GT_MetaTileEntity_ElectricAutoWorkbench)this.mTileEntity.mMetaTileEntity).mMode;
        this.mThroughPut = ((GT_MetaTileEntity_ElectricAutoWorkbench)this.mTileEntity.mMetaTileEntity).mThroughPut;
        for (ICrafting var1 : (List<ICrafting>) this.crafters) {
            var1.sendProgressBarUpdate((Container)this, 100, this.mMode);
            var1.sendProgressBarUpdate((Container)this, 101, this.mThroughPut);
        }
    }

    @Override
    public void addCraftingToCrafters(ICrafting par1ICrafting) {
        super.addCraftingToCrafters(par1ICrafting);
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    public void updateProgressBar(int par1, int par2) {
        super.updateProgressBar(par1, par2);
        switch (par1) {
            case 100: {
                this.mMode = par2;
                break;
            }
            case 101: {
                this.mThroughPut = par2;
            }
        }
    }

    @Override
    public int getSlotCount() {
        return 19;
    }

    @Override
    public int getShiftClickSlotCount() {
        return 9;
    }
}

