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
import gregtechmod.api.BaseMetaTileEntity;
import gregtechmod.common.containers.GT_Container;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;

import java.util.List;

public class GT_ContainerMetaTile_Machine
extends GT_Container {
    protected BaseMetaTileEntity mTileEntity;
    private int mTimer = 0;
    public int mMJ = 0;
    public int mEnergy = 0;
    public int mMJStorage = 0;
    public int mStorage = 0;
    public int mOutput = 0;
    public int mInput = 0;
    public int mID = 0;
    public int mDisplayErrorCode = 0;
    public int oMJ = 0;
    public int oEnergy = 0;
    public int oMJStorage = 0;
    public int oStorage = 0;
    public int oOutput = 0;
    public int oInput = 0;
    public int oID = 0;
    public int oDisplayErrorCode = 0;

    public GT_ContainerMetaTile_Machine(InventoryPlayer aInventoryPlayer, BaseMetaTileEntity aTileEntity, int aID) {
        super(aInventoryPlayer, aTileEntity);
        this.mTileEntity = aTileEntity;
        this.mID = aID;
        if (this.mTileEntity.hasValidMetaTileEntity()) {
            this.addSlots(aInventoryPlayer);
            if (this.doesBindPlayerInventory()) {
                this.bindPlayerInventory(aInventoryPlayer);
            }
            this.detectAndSendChanges();
        } else {
            aInventoryPlayer.player.closeScreen();
        }
    }

    public void detectAndSendChanges() {
        super.detectAndSendChanges();
        if (this.mTileEntity.worldObj.isRemote) {
            return;
        }
        this.mStorage = this.mTileEntity.getCapacity();
        this.mEnergy = this.mTileEntity.getStored();
        this.mMJ = this.mTileEntity.getStoredMJ();
        this.mOutput = this.mTileEntity.getOutput();
        this.mInput = this.mTileEntity.mMetaTileEntity.maxEUInput();
        this.mDisplayErrorCode = this.mTileEntity.mDisplayErrorCode;
        ++this.mTimer;
        for (ICrafting var1 : (List<ICrafting>) this.crafters) {
            if (this.mTimer < 10 || this.mTimer % 500 == 0 || this.oEnergy != this.mEnergy) {
                var1.sendProgressBarUpdate((Container)this, 0, this.mEnergy & 0xFFFF);
                var1.sendProgressBarUpdate((Container)this, 1, this.mEnergy >>> 16);
            }
            if (this.mTimer < 10 || this.mTimer % 500 == 0 || this.oStorage != this.mStorage) {
                var1.sendProgressBarUpdate((Container)this, 2, this.mStorage & 0xFFFF);
                var1.sendProgressBarUpdate((Container)this, 3, this.mStorage >>> 16);
            }
            if (this.mTimer < 10 || this.mTimer % 500 == 0 || this.oOutput != this.mOutput) {
                var1.sendProgressBarUpdate((Container)this, 4, this.mOutput);
            }
            if (this.mTimer < 10 || this.mTimer % 500 == 0 || this.oInput != this.mInput) {
                var1.sendProgressBarUpdate((Container)this, 5, this.mInput);
            }
            if (this.mTimer < 10 || this.mTimer % 500 == 0 || this.oDisplayErrorCode != this.mDisplayErrorCode) {
                var1.sendProgressBarUpdate((Container)this, 6, this.mDisplayErrorCode);
            }
            if (this.mTimer < 10 || this.mTimer % 500 == 0 || this.oMJ != this.mMJ) {
                var1.sendProgressBarUpdate((Container)this, 7, this.mMJ & 0xFFFF);
                var1.sendProgressBarUpdate((Container)this, 8, this.mMJ >>> 16);
            }
            if (this.mTimer >= 10 && this.mTimer % 500 != 0 && this.oMJStorage == this.mMJStorage) continue;
            var1.sendProgressBarUpdate((Container)this, 9, this.mMJStorage & 0xFFFF);
            var1.sendProgressBarUpdate((Container)this, 10, this.mMJStorage >>> 16);
        }
        this.oID = this.mID;
        this.oMJ = this.mMJ;
        this.oInput = this.mInput;
        this.oOutput = this.mOutput;
        this.oEnergy = this.mEnergy;
        this.oStorage = this.mStorage;
        this.oMJStorage = this.mMJStorage;
        this.oDisplayErrorCode = this.mDisplayErrorCode;
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
                break;
            }
            case 6: {
                this.mDisplayErrorCode = par2;
                break;
            }
            case 7: {
                this.mMJ = this.mMJ & 0xFFFF0000 | par2;
                break;
            }
            case 8: {
                this.mMJ = this.mMJ & 0xFFFF | par2 << 16;
                break;
            }
            case 9: {
                this.mMJStorage = this.mMJStorage & 0xFFFF0000 | par2;
                break;
            }
            case 10: {
                this.mMJStorage = this.mMJStorage & 0xFFFF | par2 << 16;
            }
        }
    }

    @Override
    public boolean canInteractWith(EntityPlayer player) {
        return this.mTileEntity.isUseableByPlayer(player);
    }
}

