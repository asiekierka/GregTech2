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
import gregtechmod.common.GT_Slot_Armor;
import gregtechmod.common.containers.GT_ContainerMetaID_Machine;
import gregtechmod.common.tileentities.GT_TileEntity_IDSU;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;

import java.util.List;

public class GT_Container_IDSU
extends GT_ContainerMetaID_Machine {
    public int mPlayerHash;

    public GT_Container_IDSU(InventoryPlayer aInventoryPlayer, GT_TileEntity_IDSU aTileEntity, int aID) {
        super(aInventoryPlayer, aTileEntity, aID);
    }

    @Override
    public void addSlots(InventoryPlayer aInventoryPlayer) {
        this.addSlotToContainer(new Slot((IInventory)this.mTileEntity, 0, 128, 14));
        this.addSlotToContainer(new Slot((IInventory)this.mTileEntity, 1, 128, 50));
        this.addSlotToContainer(new GT_Slot_Armor(this, (IInventory)aInventoryPlayer, 36, 152, 59, 3));
        this.addSlotToContainer(new GT_Slot_Armor(this, (IInventory)aInventoryPlayer, 37, 152, 41, 2));
        this.addSlotToContainer(new GT_Slot_Armor(this, (IInventory)aInventoryPlayer, 38, 152, 23, 1));
        this.addSlotToContainer(new GT_Slot_Armor(this, (IInventory)aInventoryPlayer, 39, 152, 5, 0));
    }

    @Override
    public void detectAndSendChanges() {
        super.detectAndSendChanges();
        this.mPlayerHash = ((GT_TileEntity_IDSU)this.mTileEntity).mPlayerHash;
        for (ICrafting var1 : (List<ICrafting>) this.crafters) {
            var1.sendProgressBarUpdate((Container)this, 100, this.mPlayerHash & 0xFFFF);
            var1.sendProgressBarUpdate((Container)this, 101, this.mPlayerHash >>> 16);
        }
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    public void updateProgressBar(int par1, int par2) {
        super.updateProgressBar(par1, par2);
        switch (par1) {
            case 100: {
                this.mPlayerHash = this.mPlayerHash & 0xFFFF0000 | par2;
                break;
            }
            case 101: {
                this.mPlayerHash = this.mPlayerHash & 0xFFFF | par2 << 16;
            }
        }
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

