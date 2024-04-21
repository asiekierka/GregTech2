/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.entity.player.InventoryPlayer
 *  net.minecraft.inventory.Container
 *  net.minecraft.inventory.ICrafting
 *  net.minecraft.inventory.IInventory
 *  net.minecraft.inventory.Slot
 *  net.minecraft.item.ItemStack
 *  net.minecraft.network.packet.Packet
 *  net.minecraft.network.packet.Packet3Chat
 */
package gregtechmod.common.containers;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import gregtechmod.api.BaseMetaTileEntity;
import gregtechmod.common.GT_Slot_Holo;
import gregtechmod.common.containers.GT_ContainerMetaTile_Machine;
import gregtechmod.common.tileentities.GT_MetaTileEntity_ElectricRegulatorAdvanced;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.Packet3Chat;

import java.util.List;

public class GT_Container_ElectricRegulatorAdvanced
extends GT_ContainerMetaTile_Machine {
    public int[] mTargetSlots = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};

    public GT_Container_ElectricRegulatorAdvanced(InventoryPlayer aInventoryPlayer, BaseMetaTileEntity aTileEntity, int aID) {
        super(aInventoryPlayer, aTileEntity, aID);
    }

    @Override
    public void addSlots(InventoryPlayer aInventoryPlayer) {
        this.addSlotToContainer(new Slot((IInventory)this.mTileEntity, 0, 8, 6));
        this.addSlotToContainer(new Slot((IInventory)this.mTileEntity, 1, 26, 6));
        this.addSlotToContainer(new Slot((IInventory)this.mTileEntity, 2, 44, 6));
        this.addSlotToContainer(new Slot((IInventory)this.mTileEntity, 3, 8, 24));
        this.addSlotToContainer(new Slot((IInventory)this.mTileEntity, 4, 26, 24));
        this.addSlotToContainer(new Slot((IInventory)this.mTileEntity, 5, 44, 24));
        this.addSlotToContainer(new Slot((IInventory)this.mTileEntity, 6, 8, 42));
        this.addSlotToContainer(new Slot((IInventory)this.mTileEntity, 7, 26, 42));
        this.addSlotToContainer(new Slot((IInventory)this.mTileEntity, 8, 44, 42));
        this.addSlotToContainer(new GT_Slot_Holo((IInventory)this.mTileEntity, 9, 64, 7, false, true, 1));
        this.addSlotToContainer(new GT_Slot_Holo((IInventory)this.mTileEntity, 10, 81, 7, false, true, 1));
        this.addSlotToContainer(new GT_Slot_Holo((IInventory)this.mTileEntity, 11, 98, 7, false, true, 1));
        this.addSlotToContainer(new GT_Slot_Holo((IInventory)this.mTileEntity, 12, 64, 24, false, true, 1));
        this.addSlotToContainer(new GT_Slot_Holo((IInventory)this.mTileEntity, 13, 81, 24, false, true, 1));
        this.addSlotToContainer(new GT_Slot_Holo((IInventory)this.mTileEntity, 14, 98, 24, false, true, 1));
        this.addSlotToContainer(new GT_Slot_Holo((IInventory)this.mTileEntity, 15, 64, 41, false, true, 1));
        this.addSlotToContainer(new GT_Slot_Holo((IInventory)this.mTileEntity, 16, 81, 41, false, true, 1));
        this.addSlotToContainer(new GT_Slot_Holo((IInventory)this.mTileEntity, 17, 98, 41, false, true, 1));
        this.addSlotToContainer(new GT_Slot_Holo((IInventory)this.mTileEntity, 18, 119, 7, false, true, 1));
        this.addSlotToContainer(new GT_Slot_Holo((IInventory)this.mTileEntity, 18, 136, 7, false, true, 1));
        this.addSlotToContainer(new GT_Slot_Holo((IInventory)this.mTileEntity, 18, 153, 7, false, true, 1));
        this.addSlotToContainer(new GT_Slot_Holo((IInventory)this.mTileEntity, 18, 119, 24, false, true, 1));
        this.addSlotToContainer(new GT_Slot_Holo((IInventory)this.mTileEntity, 18, 136, 24, false, true, 1));
        this.addSlotToContainer(new GT_Slot_Holo((IInventory)this.mTileEntity, 18, 153, 24, false, true, 1));
        this.addSlotToContainer(new GT_Slot_Holo((IInventory)this.mTileEntity, 18, 119, 41, false, true, 1));
        this.addSlotToContainer(new GT_Slot_Holo((IInventory)this.mTileEntity, 18, 136, 41, false, true, 1));
        this.addSlotToContainer(new GT_Slot_Holo((IInventory)this.mTileEntity, 18, 153, 41, false, true, 1));
        this.addSlotToContainer(new GT_Slot_Holo((IInventory)this.mTileEntity, 18, 8, 63, false, true, 1));
    }

    @Override
    public ItemStack slotClick(int aSlotIndex, int aMouseclick, int aShifthold, EntityPlayer aPlayer) {
        if (aSlotIndex < 1) {
            return super.slotClick(aSlotIndex, aMouseclick, aShifthold, aPlayer);
        }
        Slot tSlot = (Slot)this.inventorySlots.get(aSlotIndex);
        if (tSlot != null) {
            if (this.mTileEntity.mMetaTileEntity == null) {
                return null;
            }
            if (aSlotIndex == 27) {
                boolean bl = ((GT_MetaTileEntity_ElectricRegulatorAdvanced)this.mTileEntity.mMetaTileEntity).bOutput = !((GT_MetaTileEntity_ElectricRegulatorAdvanced)this.mTileEntity.mMetaTileEntity).bOutput;
                if (aPlayer instanceof EntityPlayerMP) {
                    if (((GT_MetaTileEntity_ElectricRegulatorAdvanced)this.mTileEntity.mMetaTileEntity).bOutput) {
                        ((EntityPlayerMP)aPlayer).playerNetServerHandler.sendPacketToPlayer((Packet)new Packet3Chat("Emit Energy to Outputside"));
                    } else {
                        ((EntityPlayerMP)aPlayer).playerNetServerHandler.sendPacketToPlayer((Packet)new Packet3Chat("Don't emit Energy"));
                    }
                }
                return null;
            }
            if (aSlotIndex >= 9 && aSlotIndex < 18) {
                ItemStack tStack = aPlayer.inventory.getItemStack();
                if (tStack != null) {
                    tStack = tStack.copy();
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
            if (aSlotIndex >= 18 && aSlotIndex < 27) {
                ((GT_MetaTileEntity_ElectricRegulatorAdvanced)this.mTileEntity.mMetaTileEntity).mTargetSlots[aSlotIndex - 18] = Math.min(99, Math.max(0, ((GT_MetaTileEntity_ElectricRegulatorAdvanced)this.mTileEntity.mMetaTileEntity).mTargetSlots[aSlotIndex - 18] + (aMouseclick == 0 ? -1 : 1) * (aShifthold == 0 ? 1 : 16)));
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
        this.mTargetSlots = new int[9];
        for (int i = 0; i < 9; ++i) {
            this.mTargetSlots[i] = ((GT_MetaTileEntity_ElectricRegulatorAdvanced)this.mTileEntity.mMetaTileEntity).mTargetSlots[i];
        }
        for (ICrafting var1 : (List<ICrafting>) this.crafters) {
            for (int i = 0; i < 9; ++i) {
                var1.sendProgressBarUpdate((Container)this, 100 + i, this.mTargetSlots[i]);
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
                this.mTargetSlots[0] = par2;
                break;
            }
            case 101: {
                this.mTargetSlots[1] = par2;
                break;
            }
            case 102: {
                this.mTargetSlots[2] = par2;
                break;
            }
            case 103: {
                this.mTargetSlots[3] = par2;
                break;
            }
            case 104: {
                this.mTargetSlots[4] = par2;
                break;
            }
            case 105: {
                this.mTargetSlots[5] = par2;
                break;
            }
            case 106: {
                this.mTargetSlots[6] = par2;
                break;
            }
            case 107: {
                this.mTargetSlots[7] = par2;
                break;
            }
            case 108: {
                this.mTargetSlots[8] = par2;
            }
        }
    }

    @Override
    public int getSlotCount() {
        return 9;
    }

    @Override
    public int getShiftClickSlotCount() {
        return 9;
    }
}

