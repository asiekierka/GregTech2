/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.entity.player.InventoryPlayer
 *  net.minecraft.inventory.Container
 *  net.minecraft.inventory.ICrafting
 *  net.minecraft.inventory.IInventory
 *  net.minecraft.inventory.Slot
 */
package gregtechmod.common.containers;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import gregtechmod.api.BaseMetaTileEntity;
import gregtechmod.common.GT_Slot_Holo;
import gregtechmod.common.GT_Slot_Output;
import gregtechmod.common.containers.GT_ContainerMetaTile_Machine;
import gregtechmod.common.tileentities.GT_MetaTileEntity_BasicTank;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;

import java.util.List;

public class GT_Container_BasicTank
extends GT_ContainerMetaTile_Machine {
    public int mContent = 0;

    public GT_Container_BasicTank(InventoryPlayer aInventoryPlayer, BaseMetaTileEntity aTileEntity, int aID) {
        super(aInventoryPlayer, aTileEntity, aID);
    }

    @Override
    public void addSlots(InventoryPlayer aInventoryPlayer) {
        this.addSlotToContainer(new Slot((IInventory)this.mTileEntity, 0, 80, 17));
        this.addSlotToContainer(new GT_Slot_Output((IInventory)this.mTileEntity, 1, 80, 53));
        this.addSlotToContainer(new GT_Slot_Holo((IInventory)this.mTileEntity, 2, 59, 42, false, false, 1));
    }

    @Override
    public void detectAndSendChanges() {
        super.detectAndSendChanges();
        if (this.mTileEntity.worldObj.isRemote) {
            return;
        }
        this.mContent = ((GT_MetaTileEntity_BasicTank)this.mTileEntity.mMetaTileEntity).mLiquid != null ? ((GT_MetaTileEntity_BasicTank)this.mTileEntity.mMetaTileEntity).mLiquid.amount : 0;
        for (ICrafting var1 : (List<ICrafting>) this.crafters) {
            var1.sendProgressBarUpdate((Container)this, 100, this.mContent & 0xFFFF);
            var1.sendProgressBarUpdate((Container)this, 101, this.mContent >>> 16);
        }
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    public void updateProgressBar(int par1, int par2) {
        super.updateProgressBar(par1, par2);
        switch (par1) {
            case 100: {
                this.mContent = this.mContent & 0xFFFF0000 | par2;
                break;
            }
            case 101: {
                this.mContent = this.mContent & 0xFFFF | par2 << 16;
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

