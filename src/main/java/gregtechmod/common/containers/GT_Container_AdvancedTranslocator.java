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
import gregtechmod.common.tileentities.GT_MetaTileEntity_AdvancedTranslocator;
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

public class GT_Container_AdvancedTranslocator
extends GT_ContainerMetaTile_Machine {
    public int mInputSide;
    public int mOutputSide;

    public GT_Container_AdvancedTranslocator(InventoryPlayer aInventoryPlayer, BaseMetaTileEntity aTileEntity, int aID) {
        super(aInventoryPlayer, aTileEntity, aID);
    }

    @Override
    public void addSlots(InventoryPlayer aInventoryPlayer) {
        this.addSlotToContainer(new GT_Slot_Holo((IInventory)this.mTileEntity, 0, 63, 6, false, true, 1));
        this.addSlotToContainer(new GT_Slot_Holo((IInventory)this.mTileEntity, 1, 80, 6, false, true, 1));
        this.addSlotToContainer(new GT_Slot_Holo((IInventory)this.mTileEntity, 2, 97, 6, false, true, 1));
        this.addSlotToContainer(new GT_Slot_Holo((IInventory)this.mTileEntity, 3, 63, 23, false, true, 1));
        this.addSlotToContainer(new GT_Slot_Holo((IInventory)this.mTileEntity, 4, 80, 23, false, true, 1));
        this.addSlotToContainer(new GT_Slot_Holo((IInventory)this.mTileEntity, 5, 97, 23, false, true, 1));
        this.addSlotToContainer(new GT_Slot_Holo((IInventory)this.mTileEntity, 6, 63, 40, false, true, 1));
        this.addSlotToContainer(new GT_Slot_Holo((IInventory)this.mTileEntity, 7, 80, 40, false, true, 1));
        this.addSlotToContainer(new GT_Slot_Holo((IInventory)this.mTileEntity, 8, 97, 40, false, true, 1));
        this.addSlotToContainer(new GT_Slot_Holo((IInventory)this.mTileEntity, 9, 8, 63, false, true, 1));
        this.addSlotToContainer(new GT_Slot_Holo((IInventory)this.mTileEntity, 9, 26, 63, false, true, 1));
        this.addSlotToContainer(new GT_Slot_Holo((IInventory)this.mTileEntity, 9, 43, 5, false, true, 1));
        this.addSlotToContainer(new GT_Slot_Holo((IInventory)this.mTileEntity, 9, 117, 5, false, true, 1));
    }

    @Override
    public ItemStack slotClick(int aSlotIndex, int aMouseclick, int aShifthold, EntityPlayer aPlayer) {
        if (aSlotIndex < 0) {
            return super.slotClick(aSlotIndex, aMouseclick, aShifthold, aPlayer);
        }
        Slot tSlot = (Slot)this.inventorySlots.get(aSlotIndex);
        if (tSlot != null) {
            if (this.mTileEntity.mMetaTileEntity == null) {
                return null;
            }
            if (aSlotIndex < 9) {
                ItemStack tStack = aPlayer.inventory.getItemStack();
                if (tStack != null) {
                    tStack = tStack.copy();
                    tStack.stackSize = 1;
                }
                tSlot.putStack(tStack);
                return null;
            }
            if (aSlotIndex == 9) {
                boolean bl = ((GT_MetaTileEntity_AdvancedTranslocator)this.mTileEntity.mMetaTileEntity).bOutput = !((GT_MetaTileEntity_AdvancedTranslocator)this.mTileEntity.mMetaTileEntity).bOutput;
                if (aPlayer instanceof EntityPlayerMP) {
                    if (((GT_MetaTileEntity_AdvancedTranslocator)this.mTileEntity.mMetaTileEntity).bOutput) {
                        ((EntityPlayerMP)aPlayer).playerNetServerHandler.sendPacketToPlayer((Packet)new Packet3Chat("Emit Energy to Outputside"));
                    } else {
                        ((EntityPlayerMP)aPlayer).playerNetServerHandler.sendPacketToPlayer((Packet)new Packet3Chat("Don't emit Energy"));
                    }
                }
                return null;
            }
            if (aSlotIndex == 10) {
                boolean bl = ((GT_MetaTileEntity_AdvancedTranslocator)this.mTileEntity.mMetaTileEntity).bInvertFilter = !((GT_MetaTileEntity_AdvancedTranslocator)this.mTileEntity.mMetaTileEntity).bInvertFilter;
                if (aPlayer instanceof EntityPlayerMP) {
                    if (((GT_MetaTileEntity_AdvancedTranslocator)this.mTileEntity.mMetaTileEntity).bInvertFilter) {
                        ((EntityPlayerMP)aPlayer).playerNetServerHandler.sendPacketToPlayer((Packet)new Packet3Chat("Inverted the Filter"));
                    } else {
                        ((EntityPlayerMP)aPlayer).playerNetServerHandler.sendPacketToPlayer((Packet)new Packet3Chat("Filter works normal"));
                    }
                }
                return null;
            }
            if (aSlotIndex == 11) {
                ((GT_MetaTileEntity_AdvancedTranslocator)this.mTileEntity.mMetaTileEntity).mInputSide = (((GT_MetaTileEntity_AdvancedTranslocator)this.mTileEntity.mMetaTileEntity).mInputSide + 1) % 6;
            } else if (aSlotIndex == 12) {
                ((GT_MetaTileEntity_AdvancedTranslocator)this.mTileEntity.mMetaTileEntity).mOutputSide = (((GT_MetaTileEntity_AdvancedTranslocator)this.mTileEntity.mMetaTileEntity).mOutputSide + 1) % 6;
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
        this.mInputSide = ((GT_MetaTileEntity_AdvancedTranslocator)this.mTileEntity.mMetaTileEntity).mInputSide;
        this.mOutputSide = ((GT_MetaTileEntity_AdvancedTranslocator)this.mTileEntity.mMetaTileEntity).mOutputSide;
        for (ICrafting var1 : (List<ICrafting>) this.crafters) {
            var1.sendProgressBarUpdate((Container)this, 100, this.mInputSide);
            var1.sendProgressBarUpdate((Container)this, 101, this.mOutputSide);
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
                this.mInputSide = par2;
                break;
            }
            case 101: {
                this.mOutputSide = par2;
            }
        }
    }
}

