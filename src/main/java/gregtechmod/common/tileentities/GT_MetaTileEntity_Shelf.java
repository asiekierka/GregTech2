/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.item.EntityItem
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraftforge.common.ForgeDirection
 */
package gregtechmod.common.tileentities;

import gregtechmod.api.BaseMetaTileEntity;
import gregtechmod.api.MetaTileEntity;
import gregtechmod.common.GT_ModHandler;
import gregtechmod.common.GT_OreDictUnificator;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.ForgeDirection;

public class GT_MetaTileEntity_Shelf
extends MetaTileEntity {
    public int mType = 0;

    public GT_MetaTileEntity_Shelf(int aID, String mName, String mNameRegional) {
        super(aID, mName, mNameRegional);
    }

    public GT_MetaTileEntity_Shelf() {
    }

    @Override
    public boolean isSimpleMachine() {
        return true;
    }

    @Override
    public int getInvSize() {
        return 1;
    }

    @Override
    public boolean isFacingValid(int aFacing) {
        return aFacing > 1;
    }

    @Override
    public boolean isAccessAllowed(EntityPlayer aPlayer) {
        return true;
    }

    @Override
    public boolean ownerControl() {
        return false;
    }

    @Override
    public boolean isEnetOutput() {
        return false;
    }

    @Override
    public boolean isEnetInput() {
        return false;
    }

    @Override
    public boolean isOutputFacing(short aSide) {
        return false;
    }

    @Override
    public boolean isInputFacing(short aSide) {
        return false;
    }

    @Override
    public void onRightclick(EntityPlayer aPlayer) {
        ItemStack tStack = aPlayer.inventory.getStackInSlot(aPlayer.inventory.currentItem);
        if (tStack == null) {
            if (this.mInventory[0] != null && this.mInventory[0].stackSize > 0) {
                aPlayer.inventory.setInventorySlotContents(aPlayer.inventory.currentItem, this.mInventory[0]);
                this.mBaseMetaTileEntity.setInventorySlotContents(0, null);
                this.mBaseMetaTileEntity.issueTextureUpdate();
                this.mType = 0;
            }
        } else if (this.mInventory[0] == null) {
            if (GT_OreDictUnificator.isItemStackInstanceOf(tStack, "paper", true)) {
                aPlayer.inventory.setInventorySlotContents(aPlayer.inventory.currentItem, null);
                this.mBaseMetaTileEntity.setInventorySlotContents(0, tStack);
                this.mBaseMetaTileEntity.issueTextureUpdate();
                this.mType = 1;
            } else if (GT_OreDictUnificator.isItemStackInstanceOf(tStack, "book", true)) {
                aPlayer.inventory.setInventorySlotContents(aPlayer.inventory.currentItem, null);
                this.mBaseMetaTileEntity.setInventorySlotContents(0, tStack);
                this.mBaseMetaTileEntity.issueTextureUpdate();
                this.mType = 2;
            } else if (tStack.itemID == GT_ModHandler.getIC2Item((String)"filledTinCan", (int)1).itemID || tStack.itemID == GT_ModHandler.getIC2Item((String)"tinCan", (int)1).itemID) {
                aPlayer.inventory.setInventorySlotContents(aPlayer.inventory.currentItem, null);
                this.mBaseMetaTileEntity.setInventorySlotContents(0, tStack);
                this.mBaseMetaTileEntity.issueTextureUpdate();
                this.mType = 3;
            }
        }
    }

    @Override
    public void onLeftclick(EntityPlayer aPlayer) {
        if (this.mInventory[0] != null && this.mInventory[0].stackSize > 0) {
            ItemStack tOutput = this.mInventory[0].copy();
            if (!aPlayer.isSneaking()) {
                tOutput.stackSize = 1;
            }
            this.mBaseMetaTileEntity.decrStackSize(0, tOutput.stackSize);
            EntityItem tEntity = new EntityItem(this.mBaseMetaTileEntity.worldObj, (double)(this.mBaseMetaTileEntity.xCoord + ForgeDirection.getOrientation((int)this.mBaseMetaTileEntity.getFacing()).offsetX) + 0.5, (double)(this.mBaseMetaTileEntity.yCoord + ForgeDirection.getOrientation((int)this.mBaseMetaTileEntity.getFacing()).offsetY) + 0.5, (double)(this.mBaseMetaTileEntity.zCoord + ForgeDirection.getOrientation((int)this.mBaseMetaTileEntity.getFacing()).offsetZ) + 0.5, tOutput);
            tEntity.motionX = 0.0;
            tEntity.motionY = 0.0;
            tEntity.motionZ = 0.0;
            this.mBaseMetaTileEntity.worldObj.spawnEntityInWorld((Entity)tEntity);
            if (this.mInventory[0] == null) {
                this.mType = 0;
            }
        }
    }

    @Override
    public MetaTileEntity newMetaEntity(BaseMetaTileEntity aTileEntity) {
        return new GT_MetaTileEntity_Shelf();
    }

    @Override
    public void saveNBTData(NBTTagCompound aNBT) {
        aNBT.setInteger("mType", this.mType);
    }

    @Override
    public void loadNBTData(NBTTagCompound aNBT) {
        this.mType = aNBT.getInteger("mType");
    }

    @Override
    public void onValueUpdate(short aValue) {
        this.mType = aValue;
    }

    @Override
    public short getUpdateData() {
        return (short)this.mType;
    }

    @Override
    public int getInvSideIndex(ForgeDirection aSide, int aFacing) {
        return 0;
    }

    @Override
    public int getInvSideLength(ForgeDirection aSide, int aFacing) {
        return 0;
    }

    @Override
    public int getTextureIndex(int aSide, int aFacing, boolean aActive, boolean aRedstone) {
        if (aSide == aFacing) {
            return 208 + this.mType;
        }
        return 10;
    }

    @Override
    protected String getDescription() {
        return "Decorative Item Storage";
    }
}

