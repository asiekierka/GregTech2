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
import gregtechmod.common.tileentities.GT_MetaTileEntity_ElectricBufferSmall;
import gregtechmod.common.tileentities.GT_MetaTileEntity_ElectricTypeSorter;
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

public class GT_Container_ElectricTypeSorter
extends GT_ContainerMetaTile_Machine {
    public int mTargetDirection = 0;
    public int mMode = 0;

    public GT_Container_ElectricTypeSorter(InventoryPlayer aInventoryPlayer, BaseMetaTileEntity aTileEntity, int aID) {
        super(aInventoryPlayer, aTileEntity, aID);
    }

    @Override
    public void addSlots(InventoryPlayer aInventoryPlayer) {
        this.addSlotToContainer(new Slot((IInventory)this.mTileEntity, 0, 25, 23));
        this.addSlotToContainer(new GT_Slot_Holo((IInventory)this.mTileEntity, 1, 71, 23, false, true, 1));
        this.addSlotToContainer(new GT_Slot_Holo((IInventory)this.mTileEntity, 1, 8, 63, false, true, 1));
        this.addSlotToContainer(new GT_Slot_Holo((IInventory)this.mTileEntity, 1, 26, 63, false, true, 1));
        this.addSlotToContainer(new GT_Slot_Holo((IInventory)this.mTileEntity, 1, 44, 63, false, true, 1));
        this.addSlotToContainer(new GT_Slot_Holo((IInventory)this.mTileEntity, 1, 134, 63, false, true, 1));
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
            if (aSlotIndex == 1) {
                if (aMouseclick == 0) {
                    ((GT_MetaTileEntity_ElectricTypeSorter)this.mTileEntity.mMetaTileEntity).switchModeForward();
                } else {
                    ((GT_MetaTileEntity_ElectricTypeSorter)this.mTileEntity.mMetaTileEntity).switchModeBackward();
                }
                return null;
            }
            if (aSlotIndex == 2) {
                boolean bl = ((GT_MetaTileEntity_ElectricBufferSmall)this.mTileEntity.mMetaTileEntity).bOutput = !((GT_MetaTileEntity_ElectricBufferSmall)this.mTileEntity.mMetaTileEntity).bOutput;
                if (aPlayer instanceof EntityPlayerMP) {
                    if (((GT_MetaTileEntity_ElectricBufferSmall)this.mTileEntity.mMetaTileEntity).bOutput) {
                        ((EntityPlayerMP)aPlayer).playerNetServerHandler.sendPacketToPlayer((Packet)new Packet3Chat("Emit Energy to Outputside"));
                    } else {
                        ((EntityPlayerMP)aPlayer).playerNetServerHandler.sendPacketToPlayer((Packet)new Packet3Chat("Don't emit Energy"));
                    }
                }
                return null;
            }
            if (aSlotIndex == 3) {
                boolean bl = ((GT_MetaTileEntity_ElectricBufferSmall)this.mTileEntity.mMetaTileEntity).bRedstoneIfFull = !((GT_MetaTileEntity_ElectricBufferSmall)this.mTileEntity.mMetaTileEntity).bRedstoneIfFull;
                if (aPlayer instanceof EntityPlayerMP) {
                    if (((GT_MetaTileEntity_ElectricBufferSmall)this.mTileEntity.mMetaTileEntity).bRedstoneIfFull) {
                        ((EntityPlayerMP)aPlayer).playerNetServerHandler.sendPacketToPlayer((Packet)new Packet3Chat("Emit Redstone if slot contains something"));
                    } else {
                        ((EntityPlayerMP)aPlayer).playerNetServerHandler.sendPacketToPlayer((Packet)new Packet3Chat("Don't emit Redstone"));
                    }
                }
                return null;
            }
            if (aSlotIndex == 4) {
                boolean bl = ((GT_MetaTileEntity_ElectricBufferSmall)this.mTileEntity.mMetaTileEntity).bInvert = !((GT_MetaTileEntity_ElectricBufferSmall)this.mTileEntity.mMetaTileEntity).bInvert;
                if (aPlayer instanceof EntityPlayerMP) {
                    if (((GT_MetaTileEntity_ElectricBufferSmall)this.mTileEntity.mMetaTileEntity).bInvert) {
                        ((EntityPlayerMP)aPlayer).playerNetServerHandler.sendPacketToPlayer((Packet)new Packet3Chat("Invert Redstone"));
                    } else {
                        ((EntityPlayerMP)aPlayer).playerNetServerHandler.sendPacketToPlayer((Packet)new Packet3Chat("Don't invert Redstone"));
                    }
                }
                return null;
            }
            if (aSlotIndex == 5) {
                ((GT_MetaTileEntity_ElectricTypeSorter)this.mTileEntity.mMetaTileEntity).mTargetDirection = (((GT_MetaTileEntity_ElectricTypeSorter)this.mTileEntity.mMetaTileEntity).mTargetDirection + 1) % 6;
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
        this.mMode = ((GT_MetaTileEntity_ElectricTypeSorter)this.mTileEntity.mMetaTileEntity).mMode;
        this.mTargetDirection = ((GT_MetaTileEntity_ElectricTypeSorter)this.mTileEntity.mMetaTileEntity).mTargetDirection;
        for (ICrafting var1 : (List<ICrafting>) this.crafters) {
            var1.sendProgressBarUpdate((Container)this, 100, this.mMode);
            var1.sendProgressBarUpdate((Container)this, 101, this.mTargetDirection);
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
                this.mTargetDirection = par2;
            }
        }
    }

    @Override
    public int getSlotCount() {
        return 1;
    }

    @Override
    public int getShiftClickSlotCount() {
        return 1;
    }
}

