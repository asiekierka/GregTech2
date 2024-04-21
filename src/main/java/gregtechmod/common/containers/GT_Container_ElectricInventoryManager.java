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
import gregtechmod.common.containers.GT_ContainerMetaTile_Machine;
import gregtechmod.common.tileentities.GT_MetaTileEntity_ElectricInventoryManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

import java.util.List;

public class GT_Container_ElectricInventoryManager
extends GT_ContainerMetaTile_Machine {
    public int[] mTargetDirections = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
    public int[] mRangeDirections = new int[]{0, 0, 0, 0};
    public int mTargetInOut = 0;
    public int mTargetEnergy = 0;

    public GT_Container_ElectricInventoryManager(InventoryPlayer aInventoryPlayer, BaseMetaTileEntity aTileEntity, int aID) {
        super(aInventoryPlayer, aTileEntity, aID);
    }

    @Override
    public void addSlots(InventoryPlayer aInventoryPlayer) {
        this.addSlotToContainer(new Slot((IInventory)this.mTileEntity, 0, 155, 5));
        this.addSlotToContainer(new Slot((IInventory)this.mTileEntity, 1, 155, 23));
        this.addSlotToContainer(new Slot((IInventory)this.mTileEntity, 2, 155, 41));
        this.addSlotToContainer(new GT_Slot_Holo((IInventory)this.mTileEntity, 3, 5, 5, false, true, 1));
        this.addSlotToContainer(new GT_Slot_Holo((IInventory)this.mTileEntity, 4, 5, 23, false, true, 1));
        this.addSlotToContainer(new GT_Slot_Holo((IInventory)this.mTileEntity, 5, 5, 41, false, true, 1));
        this.addSlotToContainer(new GT_Slot_Holo((IInventory)this.mTileEntity, 6, 61, 5, false, true, 1));
        this.addSlotToContainer(new GT_Slot_Holo((IInventory)this.mTileEntity, 7, 61, 23, false, true, 1));
        this.addSlotToContainer(new GT_Slot_Holo((IInventory)this.mTileEntity, 8, 61, 41, false, true, 1));
        this.addSlotToContainer(new GT_Slot_Holo((IInventory)this.mTileEntity, 9, 80, 5, false, true, 1));
        this.addSlotToContainer(new GT_Slot_Holo((IInventory)this.mTileEntity, 10, 80, 23, false, true, 1));
        this.addSlotToContainer(new GT_Slot_Holo((IInventory)this.mTileEntity, 11, 80, 41, false, true, 1));
        this.addSlotToContainer(new GT_Slot_Holo((IInventory)this.mTileEntity, 12, 136, 5, false, true, 1));
        this.addSlotToContainer(new GT_Slot_Holo((IInventory)this.mTileEntity, 13, 136, 23, false, true, 1));
        this.addSlotToContainer(new GT_Slot_Holo((IInventory)this.mTileEntity, 14, 136, 41, false, true, 1));
        this.addSlotToContainer(new GT_Slot_Holo((IInventory)this.mTileEntity, 15, 24, 5, false, true, 1));
        this.addSlotToContainer(new GT_Slot_Holo((IInventory)this.mTileEntity, 15, 24, 23, false, true, 1));
        this.addSlotToContainer(new GT_Slot_Holo((IInventory)this.mTileEntity, 15, 24, 41, false, true, 1));
        this.addSlotToContainer(new GT_Slot_Holo((IInventory)this.mTileEntity, 15, 42, 5, false, true, 1));
        this.addSlotToContainer(new GT_Slot_Holo((IInventory)this.mTileEntity, 15, 42, 23, false, true, 1));
        this.addSlotToContainer(new GT_Slot_Holo((IInventory)this.mTileEntity, 15, 42, 41, false, true, 1));
        this.addSlotToContainer(new GT_Slot_Holo((IInventory)this.mTileEntity, 15, 99, 5, false, true, 1));
        this.addSlotToContainer(new GT_Slot_Holo((IInventory)this.mTileEntity, 15, 99, 23, false, true, 1));
        this.addSlotToContainer(new GT_Slot_Holo((IInventory)this.mTileEntity, 15, 99, 41, false, true, 1));
        this.addSlotToContainer(new GT_Slot_Holo((IInventory)this.mTileEntity, 15, 117, 5, false, true, 1));
        this.addSlotToContainer(new GT_Slot_Holo((IInventory)this.mTileEntity, 15, 117, 23, false, true, 1));
        this.addSlotToContainer(new GT_Slot_Holo((IInventory)this.mTileEntity, 15, 117, 41, false, true, 1));
        this.addSlotToContainer(new GT_Slot_Holo((IInventory)this.mTileEntity, 15, 24, 60, false, true, 1));
        this.addSlotToContainer(new GT_Slot_Holo((IInventory)this.mTileEntity, 15, 42, 60, false, true, 1));
        this.addSlotToContainer(new GT_Slot_Holo((IInventory)this.mTileEntity, 15, 99, 60, false, true, 1));
        this.addSlotToContainer(new GT_Slot_Holo((IInventory)this.mTileEntity, 15, 117, 60, false, true, 1));
        this.addSlotToContainer(new GT_Slot_Holo((IInventory)this.mTileEntity, 15, 5, 60, false, true, 1));
        this.addSlotToContainer(new GT_Slot_Holo((IInventory)this.mTileEntity, 15, 61, 60, false, true, 1));
        this.addSlotToContainer(new GT_Slot_Holo((IInventory)this.mTileEntity, 15, 80, 60, false, true, 1));
        this.addSlotToContainer(new GT_Slot_Holo((IInventory)this.mTileEntity, 15, 136, 60, false, true, 1));
    }

    @Override
    public ItemStack slotClick(int aSlotIndex, int aMouseclick, int aShifthold, EntityPlayer aPlayer) {
        if (aSlotIndex < 3 || aSlotIndex >= this.getAllSlotCount()) {
            return super.slotClick(aSlotIndex, aMouseclick, aShifthold, aPlayer);
        }
        Slot tSlot = (Slot)this.inventorySlots.get(aSlotIndex);
        if (tSlot != null) {
            if (this.mTileEntity.mMetaTileEntity == null) {
                return null;
            }
            if (aSlotIndex < 15) {
                ItemStack tStack = aPlayer.inventory.getItemStack();
                if (tStack != null) {
                    tStack = tStack.copy();
                    if (aMouseclick != 0) {
                        tStack.setItemDamage(-1);
                    }
                    tSlot.putStack(tStack);
                } else if (tSlot.getStack() != null) {
                    if (aMouseclick == 0) {
                        tSlot.getStack().stackSize = tSlot.getStack().stackSize - (aShifthold == 1 ? 8 : 1);
                        if (tSlot.getStack().stackSize <= 0) {
                            tSlot.putStack(null);
                        }
                    } else {
                        tSlot.getStack().stackSize = tSlot.getStack().stackSize + (aShifthold == 1 ? 8 : 1);
                        if (tSlot.getStack().stackSize > tSlot.getStack().getMaxStackSize()) {
                            tSlot.getStack().stackSize = tSlot.getStack().getMaxStackSize();
                        }
                    }
                }
                return null;
            }
            if (aSlotIndex >= 27 && aSlotIndex <= 30) {
                ((GT_MetaTileEntity_ElectricInventoryManager)this.mTileEntity.mMetaTileEntity).iterateRangeDirection(aSlotIndex - 27);
            } else if (aSlotIndex >= 31 && aSlotIndex <= 34) {
                ((GT_MetaTileEntity_ElectricInventoryManager)this.mTileEntity.mMetaTileEntity).switchRangeEnergy(aSlotIndex - 31);
            } else if (aSlotIndex % 3 == 0) {
                if (aMouseclick != 0) {
                    ((GT_MetaTileEntity_ElectricInventoryManager)this.mTileEntity.mMetaTileEntity).switchSlot1InOut((aSlotIndex - 15) / 3);
                } else {
                    ((GT_MetaTileEntity_ElectricInventoryManager)this.mTileEntity.mMetaTileEntity).iterateSlot1Direction((aSlotIndex - 15) / 3);
                }
            } else if (aSlotIndex % 3 == 1) {
                if (aMouseclick != 0) {
                    ((GT_MetaTileEntity_ElectricInventoryManager)this.mTileEntity.mMetaTileEntity).switchSlot2InOut((aSlotIndex - 16) / 3);
                } else {
                    ((GT_MetaTileEntity_ElectricInventoryManager)this.mTileEntity.mMetaTileEntity).iterateSlot2Direction((aSlotIndex - 16) / 3);
                }
            } else if (aSlotIndex % 3 == 2) {
                if (aMouseclick != 0) {
                    ((GT_MetaTileEntity_ElectricInventoryManager)this.mTileEntity.mMetaTileEntity).switchSlot3InOut((aSlotIndex - 17) / 3);
                } else {
                    ((GT_MetaTileEntity_ElectricInventoryManager)this.mTileEntity.mMetaTileEntity).iterateSlot3Direction((aSlotIndex - 17) / 3);
                }
            }
        }
        return null;
    }

    @Override
    public void detectAndSendChanges() {
        super.detectAndSendChanges();
        if (this.mTileEntity.worldObj.isRemote) {
            return;
        }
        this.mTargetDirections = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        this.mRangeDirections = new int[]{0, 0, 0, 0};
        this.mRangeDirections[0] = ((GT_MetaTileEntity_ElectricInventoryManager)this.mTileEntity.mMetaTileEntity).getRangeDirection(0);
        this.mRangeDirections[1] = ((GT_MetaTileEntity_ElectricInventoryManager)this.mTileEntity.mMetaTileEntity).getRangeDirection(1);
        this.mRangeDirections[2] = ((GT_MetaTileEntity_ElectricInventoryManager)this.mTileEntity.mMetaTileEntity).getRangeDirection(2);
        this.mRangeDirections[3] = ((GT_MetaTileEntity_ElectricInventoryManager)this.mTileEntity.mMetaTileEntity).getRangeDirection(3);
        this.mTargetDirections[0] = ((GT_MetaTileEntity_ElectricInventoryManager)this.mTileEntity.mMetaTileEntity).getSlot1Direction(0);
        this.mTargetDirections[1] = ((GT_MetaTileEntity_ElectricInventoryManager)this.mTileEntity.mMetaTileEntity).getSlot2Direction(0);
        this.mTargetDirections[2] = ((GT_MetaTileEntity_ElectricInventoryManager)this.mTileEntity.mMetaTileEntity).getSlot3Direction(0);
        this.mTargetDirections[3] = ((GT_MetaTileEntity_ElectricInventoryManager)this.mTileEntity.mMetaTileEntity).getSlot1Direction(1);
        this.mTargetDirections[4] = ((GT_MetaTileEntity_ElectricInventoryManager)this.mTileEntity.mMetaTileEntity).getSlot2Direction(1);
        this.mTargetDirections[5] = ((GT_MetaTileEntity_ElectricInventoryManager)this.mTileEntity.mMetaTileEntity).getSlot3Direction(1);
        this.mTargetDirections[6] = ((GT_MetaTileEntity_ElectricInventoryManager)this.mTileEntity.mMetaTileEntity).getSlot1Direction(2);
        this.mTargetDirections[7] = ((GT_MetaTileEntity_ElectricInventoryManager)this.mTileEntity.mMetaTileEntity).getSlot2Direction(2);
        this.mTargetDirections[8] = ((GT_MetaTileEntity_ElectricInventoryManager)this.mTileEntity.mMetaTileEntity).getSlot3Direction(2);
        this.mTargetDirections[9] = ((GT_MetaTileEntity_ElectricInventoryManager)this.mTileEntity.mMetaTileEntity).getSlot1Direction(3);
        this.mTargetDirections[10] = ((GT_MetaTileEntity_ElectricInventoryManager)this.mTileEntity.mMetaTileEntity).getSlot2Direction(3);
        this.mTargetDirections[11] = ((GT_MetaTileEntity_ElectricInventoryManager)this.mTileEntity.mMetaTileEntity).getSlot3Direction(3);
        this.mTargetInOut = 0;
        this.mTargetInOut |= ((GT_MetaTileEntity_ElectricInventoryManager)this.mTileEntity.mMetaTileEntity).getSlot1InOut(0) ? 1 : 0;
        this.mTargetInOut |= ((GT_MetaTileEntity_ElectricInventoryManager)this.mTileEntity.mMetaTileEntity).getSlot2InOut(0) ? 2 : 0;
        this.mTargetInOut |= ((GT_MetaTileEntity_ElectricInventoryManager)this.mTileEntity.mMetaTileEntity).getSlot3InOut(0) ? 4 : 0;
        this.mTargetInOut |= ((GT_MetaTileEntity_ElectricInventoryManager)this.mTileEntity.mMetaTileEntity).getSlot1InOut(1) ? 8 : 0;
        this.mTargetInOut |= ((GT_MetaTileEntity_ElectricInventoryManager)this.mTileEntity.mMetaTileEntity).getSlot2InOut(1) ? 16 : 0;
        this.mTargetInOut |= ((GT_MetaTileEntity_ElectricInventoryManager)this.mTileEntity.mMetaTileEntity).getSlot3InOut(1) ? 32 : 0;
        this.mTargetInOut |= ((GT_MetaTileEntity_ElectricInventoryManager)this.mTileEntity.mMetaTileEntity).getSlot1InOut(2) ? 64 : 0;
        this.mTargetInOut |= ((GT_MetaTileEntity_ElectricInventoryManager)this.mTileEntity.mMetaTileEntity).getSlot2InOut(2) ? 128 : 0;
        this.mTargetInOut |= ((GT_MetaTileEntity_ElectricInventoryManager)this.mTileEntity.mMetaTileEntity).getSlot3InOut(2) ? 256 : 0;
        this.mTargetInOut |= ((GT_MetaTileEntity_ElectricInventoryManager)this.mTileEntity.mMetaTileEntity).getSlot1InOut(3) ? 512 : 0;
        this.mTargetInOut |= ((GT_MetaTileEntity_ElectricInventoryManager)this.mTileEntity.mMetaTileEntity).getSlot2InOut(3) ? 1024 : 0;
        this.mTargetInOut |= ((GT_MetaTileEntity_ElectricInventoryManager)this.mTileEntity.mMetaTileEntity).getSlot3InOut(3) ? 2048 : 0;
        this.mTargetEnergy = 0;
        this.mTargetEnergy |= ((GT_MetaTileEntity_ElectricInventoryManager)this.mTileEntity.mMetaTileEntity).getRangeEnergy(0) ? 1 : 0;
        this.mTargetEnergy |= ((GT_MetaTileEntity_ElectricInventoryManager)this.mTileEntity.mMetaTileEntity).getRangeEnergy(1) ? 2 : 0;
        this.mTargetEnergy |= ((GT_MetaTileEntity_ElectricInventoryManager)this.mTileEntity.mMetaTileEntity).getRangeEnergy(2) ? 4 : 0;
        this.mTargetEnergy |= ((GT_MetaTileEntity_ElectricInventoryManager)this.mTileEntity.mMetaTileEntity).getRangeEnergy(3) ? 8 : 0;
        for (ICrafting var1 : (List<ICrafting>) this.crafters) {
            int i;
            for (i = 0; i < 12; ++i) {
                var1.sendProgressBarUpdate((Container)this, 100 + i, this.mTargetDirections[i]);
            }
            var1.sendProgressBarUpdate((Container)this, 113, this.mTargetInOut);
            var1.sendProgressBarUpdate((Container)this, 114, this.mTargetEnergy);
            for (i = 0; i < 4; ++i) {
                var1.sendProgressBarUpdate((Container)this, 115 + i, this.mRangeDirections[i]);
            }
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
                this.mTargetDirections[0] = par2;
                break;
            }
            case 101: {
                this.mTargetDirections[1] = par2;
                break;
            }
            case 102: {
                this.mTargetDirections[2] = par2;
                break;
            }
            case 103: {
                this.mTargetDirections[3] = par2;
                break;
            }
            case 104: {
                this.mTargetDirections[4] = par2;
                break;
            }
            case 105: {
                this.mTargetDirections[5] = par2;
                break;
            }
            case 106: {
                this.mTargetDirections[6] = par2;
                break;
            }
            case 107: {
                this.mTargetDirections[7] = par2;
                break;
            }
            case 108: {
                this.mTargetDirections[8] = par2;
                break;
            }
            case 109: {
                this.mTargetDirections[9] = par2;
                break;
            }
            case 110: {
                this.mTargetDirections[10] = par2;
                break;
            }
            case 111: {
                this.mTargetDirections[11] = par2;
                break;
            }
            case 113: {
                this.mTargetInOut = par2;
                break;
            }
            case 114: {
                this.mTargetEnergy = par2;
                break;
            }
            case 115: {
                this.mRangeDirections[0] = par2;
                break;
            }
            case 116: {
                this.mRangeDirections[1] = par2;
                break;
            }
            case 117: {
                this.mRangeDirections[2] = par2;
                break;
            }
            case 118: {
                this.mRangeDirections[3] = par2;
            }
        }
    }

    @Override
    public int getSlotCount() {
        return 3;
    }

    @Override
    public int getShiftClickSlotCount() {
        return 3;
    }
}

