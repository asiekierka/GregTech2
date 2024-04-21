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
 */
package gregtechmod.common.containers;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import gregtechmod.common.containers.GT_Container;
import gregtechmod.common.tileentities.GT_TileEntityMetaID_Machine;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;

import java.util.List;

public class GT_ContainerMetaID_Machine
extends GT_Container {
    protected GT_TileEntityMetaID_Machine mTileEntity;
    public int mEnergy;
    public int mStorage;
    public int mOutput;
    public int mInput;
    public int mID;

    public GT_ContainerMetaID_Machine(InventoryPlayer aInventoryPlayer, GT_TileEntityMetaID_Machine aTileEntity, int aID) {
        super(aInventoryPlayer, aTileEntity);
        this.mTileEntity = aTileEntity;
        this.mID = aID;
        this.addSlots(aInventoryPlayer);
        if (this.doesBindPlayerInventory()) {
            this.bindPlayerInventory(aInventoryPlayer);
        }
        this.detectAndSendChanges();
    }

    public void detectAndSendChanges() {
        super.detectAndSendChanges();
        if (this.mTileEntity.worldObj.isRemote) {
            return;
        }
        this.mStorage = this.mTileEntity.maxEUStore();
        this.mEnergy = this.mTileEntity.getStored();
        this.mOutput = this.mTileEntity.maxEUOutput();
        this.mInput = this.mTileEntity.maxEUInput();
        for (ICrafting var1 : (List<ICrafting>) this.crafters) {
            var1.sendProgressBarUpdate((Container)this, 0, this.mEnergy & 0xFFFF);
            var1.sendProgressBarUpdate((Container)this, 1, this.mEnergy >>> 16);
            var1.sendProgressBarUpdate((Container)this, 2, this.mStorage & 0xFFFF);
            var1.sendProgressBarUpdate((Container)this, 3, this.mStorage >>> 16);
            var1.sendProgressBarUpdate((Container)this, 4, this.mOutput);
            var1.sendProgressBarUpdate((Container)this, 5, this.mInput);
        }
    }

    public void addCraftingToCrafters(ICrafting par1ICrafting) {
        super.addCraftingToCrafters(par1ICrafting);
    }

    @SideOnly(value=Side.CLIENT)
    public void updateProgressBar(int par1, int par2) {
        super.updateProgressBar(par1, par2);
        switch (par1) {
            case 0: {
                this.mEnergy = this.mEnergy & 0xFFFF0000 | par2;
                break;
            }
            case 1: {
                this.mEnergy = this.mEnergy & 0xFFFF | par2 << 16;
                break;
            }
            case 2: {
                this.mStorage = this.mStorage & 0xFFFF0000 | par2;
                break;
            }
            case 3: {
                this.mStorage = this.mStorage & 0xFFFF | par2 << 16;
                break;
            }
            case 4: {
                this.mOutput = par2;
                break;
            }
            case 5: {
                this.mInput = par2;
            }
        }
    }

    @Override
    public boolean canInteractWith(EntityPlayer player) {
        return this.mTileEntity.isUseableByPlayer(player);
    }
}

