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
import gregtechmod.common.tileentities.GT_MetaTileEntity_MagicEnergyAbsorber;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

import java.util.List;

public class GT_Container_MagicEnergyAbsorber
extends GT_ContainerMetaTile_Machine {
    public boolean isActive1 = false;
    public boolean isActive2 = false;

    public GT_Container_MagicEnergyAbsorber(InventoryPlayer aInventoryPlayer, BaseMetaTileEntity aTileEntity, int aID) {
        super(aInventoryPlayer, aTileEntity, aID);
    }

    @Override
    public void addSlots(InventoryPlayer aInventoryPlayer) {
        this.addSlotToContainer(new Slot((IInventory)this.mTileEntity, 0, 80, 17));
        this.addSlotToContainer(new GT_Slot_Output((IInventory)this.mTileEntity, 1, 80, 53));
        this.addSlotToContainer(new GT_Slot_Holo((IInventory)this.mTileEntity, 2, 10, 35, false, false, 1));
        this.addSlotToContainer(new GT_Slot_Holo((IInventory)this.mTileEntity, 2, 10, 18, false, false, 1));
    }

    @Override
    public ItemStack slotClick(int aSlotIndex, int aMouseclick, int aShifthold, EntityPlayer aPlayer) {
        if (aSlotIndex < 2) {
            return super.slotClick(aSlotIndex, aMouseclick, aShifthold, aPlayer);
        }
        Slot tSlot = (Slot)this.inventorySlots.get(aSlotIndex);
        if (tSlot != null) {
            if (this.mTileEntity.mMetaTileEntity == null) {
                return null;
            }
            if (aSlotIndex == 2) {
                ((GT_MetaTileEntity_MagicEnergyAbsorber)this.mTileEntity.mMetaTileEntity).isActive1 = !((GT_MetaTileEntity_MagicEnergyAbsorber)this.mTileEntity.mMetaTileEntity).isActive1;
                return null;
            }
            if (aSlotIndex == 3) {
                ((GT_MetaTileEntity_MagicEnergyAbsorber)this.mTileEntity.mMetaTileEntity).isActive2 = !((GT_MetaTileEntity_MagicEnergyAbsorber)this.mTileEntity.mMetaTileEntity).isActive2;
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
        this.isActive1 = ((GT_MetaTileEntity_MagicEnergyAbsorber)this.mTileEntity.mMetaTileEntity).isActive1;
        this.isActive2 = ((GT_MetaTileEntity_MagicEnergyAbsorber)this.mTileEntity.mMetaTileEntity).isActive2;
        for (ICrafting var1 : (List<ICrafting>) this.crafters) {
            var1.sendProgressBarUpdate((Container)this, 100, this.isActive1 ? 1 : 0);
            var1.sendProgressBarUpdate((Container)this, 101, this.isActive2 ? 1 : 0);
        }
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    public void updateProgressBar(int par1, int par2) {
        super.updateProgressBar(par1, par2);
        switch (par1) {
            case 100: {
                this.isActive1 = par2 != 0;
                break;
            }
            case 101: {
                this.isActive2 = par2 != 0;
            }
        }
    }

    @Override
    public int getSlotCount() {
        return 2;
    }

    @Override
    public int getShiftClickSlotCount() {
        return 1;
    }
}

