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
import gregtechmod.common.tileentities.GT_MetaTileEntity_BasicMachine;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

import java.util.List;

public class GT_Container_BasicMachine
extends GT_ContainerMetaTile_Machine {
    public int mProgressBar = 0;
    public boolean mOutput = false;
    public boolean mItemTransfer = false;
    public boolean mSeperatedInputs = false;

    public GT_Container_BasicMachine(InventoryPlayer aInventoryPlayer, BaseMetaTileEntity aTileEntity, int aID) {
        super(aInventoryPlayer, aTileEntity, aID);
    }

    @Override
    public void addSlots(InventoryPlayer aInventoryPlayer) {
        this.addSlotToContainer(new Slot((IInventory)this.mTileEntity, 1, 35, 25));
        this.addSlotToContainer(new Slot((IInventory)this.mTileEntity, 2, 53, 25));
        this.addSlotToContainer(new Slot((IInventory)this.mTileEntity, 5, 80, 63));
        this.addSlotToContainer(new GT_Slot_Output((IInventory)this.mTileEntity, 3, 107, 25));
        this.addSlotToContainer(new GT_Slot_Output((IInventory)this.mTileEntity, 4, 125, 25));
        this.addSlotToContainer(new GT_Slot_Holo((IInventory)this.mTileEntity, 0, 8, 63, false, true, 1));
        this.addSlotToContainer(new GT_Slot_Holo((IInventory)this.mTileEntity, 0, 26, 63, false, true, 1));
        this.addSlotToContainer(new GT_Slot_Holo((IInventory)this.mTileEntity, 0, 44, 63, false, true, 1));
    }

    @Override
    public ItemStack slotClick(int aSlotIndex, int aMouseclick, int aShifthold, EntityPlayer aPlayer) {
        if (aSlotIndex < 5) {
            return super.slotClick(aSlotIndex, aMouseclick, aShifthold, aPlayer);
        }
        Slot tSlot = (Slot)this.inventorySlots.get(aSlotIndex);
        if (tSlot != null) {
            if (!this.mTileEntity.hasValidMetaTileEntity()) {
                return null;
            }
            if (aSlotIndex == 5) {
                ((GT_MetaTileEntity_BasicMachine)this.mTileEntity.mMetaTileEntity).bOutput = !((GT_MetaTileEntity_BasicMachine)this.mTileEntity.mMetaTileEntity).bOutput;
                return null;
            }
            if (aSlotIndex == 6) {
                ((GT_MetaTileEntity_BasicMachine)this.mTileEntity.mMetaTileEntity).bItemTransfer = !((GT_MetaTileEntity_BasicMachine)this.mTileEntity.mMetaTileEntity).bItemTransfer;
                return null;
            }
            if (aSlotIndex == 7) {
                ((GT_MetaTileEntity_BasicMachine)this.mTileEntity.mMetaTileEntity).bSeperatedInputs = !((GT_MetaTileEntity_BasicMachine)this.mTileEntity.mMetaTileEntity).bSeperatedInputs;
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
        this.mOutput = ((GT_MetaTileEntity_BasicMachine)this.mTileEntity.mMetaTileEntity).bOutput;
        this.mItemTransfer = ((GT_MetaTileEntity_BasicMachine)this.mTileEntity.mMetaTileEntity).bItemTransfer;
        this.mSeperatedInputs = ((GT_MetaTileEntity_BasicMachine)this.mTileEntity.mMetaTileEntity).bSeperatedInputs;
        this.mProgressBar = Math.max(0, Math.min(20, (((GT_MetaTileEntity_BasicMachine)this.mTileEntity.mMetaTileEntity).mProgresstime > 0 ? 1 : 0) + ((GT_MetaTileEntity_BasicMachine)this.mTileEntity.mMetaTileEntity).mProgresstime * 20 / (((GT_MetaTileEntity_BasicMachine)this.mTileEntity.mMetaTileEntity).mMaxProgresstime < 1 ? 1 : ((GT_MetaTileEntity_BasicMachine)this.mTileEntity.mMetaTileEntity).mMaxProgresstime)));
        for (ICrafting var1 : (List<ICrafting>) this.crafters) {
            var1.sendProgressBarUpdate((Container)this, 100, this.mProgressBar);
            var1.sendProgressBarUpdate((Container)this, 101, this.mOutput ? 1 : 0);
            var1.sendProgressBarUpdate((Container)this, 102, this.mItemTransfer ? 1 : 0);
            var1.sendProgressBarUpdate((Container)this, 103, this.mSeperatedInputs ? 1 : 0);
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
                this.mProgressBar = Math.min(20, par2);
                break;
            }
            case 101: {
                this.mOutput = par2 != 0;
                break;
            }
            case 102: {
                this.mItemTransfer = par2 != 0;
                break;
            }
            case 103: {
                this.mSeperatedInputs = par2 != 0;
            }
        }
    }

    @Override
    public int getSlotCount() {
        return 5;
    }

    @Override
    public int getShiftClickSlotCount() {
        return 2;
    }
}

