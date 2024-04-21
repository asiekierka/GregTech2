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

import gregtechmod.GT_Mod;
import gregtechmod.api.BaseMetaTileEntity;
import gregtechmod.api.MetaTileEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.ForgeDirection;

public class GT_MetaTileEntity_Barrel
extends MetaTileEntity {
    public int mItemCount = 0;
    public int mItemID = 0;
    public int mItemMeta = 0;

    public GT_MetaTileEntity_Barrel(int aID, String mName, String mNameRegional) {
        super(aID, mName, mNameRegional);
    }

    public GT_MetaTileEntity_Barrel() {
    }

    @Override
    public boolean unbreakable() {
        return true;
    }

    @Override
    public boolean ownerWrench() {
        return this.mItemCount > 0;
    }

    @Override
    public boolean isSimpleMachine() {
        return true;
    }

    @Override
    public int getInvSize() {
        return 3;
    }

    @Override
    public boolean isFacingValid(int aFacing) {
        return true;
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
    public int getMaxItemCount() {
        return GT_Mod.instance.mBarrelItemCount - 192;
    }

    @Override
    public void setItemCount(int aCount) {
        this.mItemCount = aCount;
    }

    @Override
    public void onRightclick(EntityPlayer aPlayer) {
        ItemStack tPlayerItem = aPlayer.inventory.getCurrentItem();
        if (tPlayerItem == null) {
            if (this.mItemID > 0) {
                for (int i = 0; this.mItemCount < this.getMaxItemCount() && i < aPlayer.inventory.getSizeInventory(); ++i) {
                    if (aPlayer.inventory.getStackInSlot(i) != null && aPlayer.inventory.getStackInSlot((int)i).itemID == this.mItemID && aPlayer.inventory.getStackInSlot(i).getItemDamage() == this.mItemMeta && !aPlayer.inventory.getStackInSlot(i).hasTagCompound()) {
                        this.mItemCount += aPlayer.inventory.getStackInSlot((int)i).stackSize;
                        if (aPlayer.inventory.getStackInSlot((int)i).stackSize == 111) {
                            this.mItemCount = this.getMaxItemCount();
                        } else if (this.mItemCount > this.getMaxItemCount()) {
                            aPlayer.inventory.getStackInSlot((int)i).stackSize = this.mItemCount - this.getMaxItemCount();
                            this.mItemCount = this.getMaxItemCount();
                        } else {
                            aPlayer.inventory.getStackInSlot((int)i).stackSize = 0;
                        }
                    }
                    if (aPlayer.inventory.getStackInSlot(i) == null || aPlayer.inventory.getStackInSlot((int)i).stackSize > 0) continue;
                    aPlayer.inventory.setInventorySlotContents(i, null);
                }
                aPlayer.sendChatToPlayer(this.mItemCount + (this.mInventory[0] == null ? 0 : this.mInventory[0].stackSize) + (this.mInventory[1] == null ? 0 : this.mInventory[1].stackSize) + (this.mInventory[2] == null ? 0 : this.mInventory[2].stackSize) + " of " + new ItemStack(this.mItemID, 1, this.mItemMeta).getDisplayName());
            }
        } else {
            if (this.mItemID <= 0) {
                this.mItemID = tPlayerItem.itemID;
                this.mItemMeta = tPlayerItem.getItemDamage();
                this.mItemCount = 0;
            }
            if (this.mItemID > 0) {
                if (this.mItemCount < this.getMaxItemCount() && tPlayerItem.itemID == this.mItemID && tPlayerItem.getItemDamage() == this.mItemMeta && !tPlayerItem.hasTagCompound()) {
                    this.mItemCount += tPlayerItem.stackSize;
                    if (tPlayerItem.stackSize == 111) {
                        this.mItemCount = this.getMaxItemCount();
                    } else if (this.mItemCount > this.getMaxItemCount()) {
                        tPlayerItem.stackSize = this.mItemCount - this.getMaxItemCount();
                        this.mItemCount = this.getMaxItemCount();
                    } else {
                        tPlayerItem.stackSize = 0;
                    }
                } else {
                    aPlayer.sendChatToPlayer(this.mItemCount + (this.mInventory[0] == null ? 0 : this.mInventory[0].stackSize) + (this.mInventory[1] == null ? 0 : this.mInventory[1].stackSize) + (this.mInventory[2] == null ? 0 : this.mInventory[2].stackSize) + " of " + new ItemStack(this.mItemID, 1, this.mItemMeta).getDisplayName());
                }
            }
        }
        if (aPlayer.inventoryContainer != null) {
            aPlayer.inventoryContainer.detectAndSendChanges();
        }
    }

    @Override
    public void onLeftclick(EntityPlayer aPlayer) {
        if (this.mInventory[0] != null && this.mInventory[0].stackSize > 0) {
            ItemStack tOutput = this.mInventory[0].copy();
            if (aPlayer.isSneaking()) {
                tOutput.stackSize = 1;
            }
            this.mBaseMetaTileEntity.decrStackSize(0, tOutput.stackSize);
            EntityItem tEntity = new EntityItem(this.mBaseMetaTileEntity.worldObj, (double)(this.mBaseMetaTileEntity.xCoord + ForgeDirection.getOrientation((int)this.mBaseMetaTileEntity.getFacing()).offsetX) + 0.5, (double)(this.mBaseMetaTileEntity.yCoord + ForgeDirection.getOrientation((int)this.mBaseMetaTileEntity.getFacing()).offsetY) + 0.5, (double)(this.mBaseMetaTileEntity.zCoord + ForgeDirection.getOrientation((int)this.mBaseMetaTileEntity.getFacing()).offsetZ) + 0.5, tOutput);
            tEntity.motionX = 0.0;
            tEntity.motionY = 0.0;
            tEntity.motionZ = 0.0;
            this.mBaseMetaTileEntity.worldObj.spawnEntityInWorld((Entity)tEntity);
        }
    }

    @Override
    public MetaTileEntity newMetaEntity(BaseMetaTileEntity aTileEntity) {
        return new GT_MetaTileEntity_Barrel();
    }

    @Override
    public void saveNBTData(NBTTagCompound aNBT) {
        aNBT.setInteger("mItemCount", this.mItemCount);
        aNBT.setInteger("mItemID", this.mItemID);
        aNBT.setInteger("mItemMeta", this.mItemMeta);
    }

    @Override
    public void loadNBTData(NBTTagCompound aNBT) {
        this.mItemCount = aNBT.getInteger("mItemCount");
        this.mItemID = aNBT.getInteger("mItemID");
        this.mItemMeta = aNBT.getInteger("mItemMeta");
    }

    @Override
    public void onPostTick() {
        if (!this.mBaseMetaTileEntity.worldObj.isRemote && this.mItemID != 0) {
            if (this.mInventory[1] == null) {
                this.mInventory[1] = new ItemStack(this.mItemID, 0, this.mItemMeta);
            } else if (this.mItemCount < this.getMaxItemCount() && this.mInventory[1].itemID == this.mItemID && this.mInventory[1].getItemDamage() == this.mItemMeta && !this.mInventory[1].hasTagCompound()) {
                this.mItemCount += this.mInventory[1].stackSize;
                if (this.mItemCount > this.getMaxItemCount()) {
                    this.mInventory[1].stackSize = this.mItemCount - this.getMaxItemCount();
                    this.mItemCount = this.getMaxItemCount();
                } else {
                    this.mInventory[1].stackSize = 0;
                }
            }
            if (this.mInventory[2] == null) {
                this.mInventory[2] = new ItemStack(this.mItemID, 0, this.mItemMeta);
            } else if (this.mItemCount < this.getMaxItemCount() && this.mInventory[2].itemID == this.mItemID && this.mInventory[2].getItemDamage() == this.mItemMeta && !this.mInventory[2].hasTagCompound()) {
                this.mItemCount += this.mInventory[2].stackSize;
                if (this.mItemCount > this.getMaxItemCount()) {
                    this.mInventory[2].stackSize = this.mItemCount - this.getMaxItemCount();
                    this.mItemCount = this.getMaxItemCount();
                } else {
                    this.mInventory[2].stackSize = 0;
                }
            }
            if (this.mItemCount > 0) {
                if (this.mInventory[0] == null) {
                    this.mInventory[0] = new ItemStack(this.mItemID, 0, this.mItemMeta);
                }
                if (this.mInventory[0] != null && this.mInventory[0].itemID == this.mItemID && this.mInventory[0].getItemDamage() == this.mItemMeta && !this.mInventory[0].hasTagCompound()) {
                    while (this.mInventory[0].stackSize < this.mInventory[0].getMaxStackSize() && this.mItemCount > 0) {
                        --this.mItemCount;
                        ++this.mInventory[0].stackSize;
                    }
                }
            }
        }
    }

    @Override
    public int getInvSideIndex(ForgeDirection aSide, int aFacing) {
        return this.mInventory[0] == null ? 1 : 0;
    }

    @Override
    public int getInvSideLength(ForgeDirection aSide, int aFacing) {
        return this.mItemID == 0 ? 0 : (this.mInventory[0] == null ? 2 : 3);
    }

    @Override
    public int getTextureIndex(int aSide, int aFacing, boolean aActive, boolean aRedstone) {
        if (aSide == aFacing) {
            return 37;
        }
        if (aSide == 0) {
            return 32;
        }
        if (aSide == 1) {
            return 29;
        }
        return 40;
    }

    public ItemStack getStoredItem() {
        if (this.mItemID == 0) {
            return null;
        }
        return new ItemStack(this.mItemID, 1, this.mItemMeta);
    }

    @Override
    public String getMainInfo() {
        if (this.getStoredItem() == null) {
            return "";
        }
        return this.getStoredItem().getDisplayName();
    }

    @Override
    public String getSecondaryInfo() {
        if (this.getStoredItem() == null) {
            return "";
        }
        return "" + (this.mItemCount + (this.mInventory[0] == null ? 0 : this.mInventory[0].stackSize) + (this.mInventory[1] == null ? 0 : this.mInventory[1].stackSize) + (this.mInventory[2] == null ? 0 : this.mInventory[2].stackSize));
    }

    @Override
    public String getTertiaryInfo() {
        return "Max: " + (this.getMaxItemCount() + 192);
    }

    @Override
    public boolean isGivingInformation() {
        return true;
    }

    @Override
    protected String getDescription() {
        return "Eight times better than a Barrel!";
    }

    @Override
    public boolean isDigitalChest() {
        return true;
    }

    @Override
    public ItemStack[] getStoredItemData() {
        return new ItemStack[]{new ItemStack(this.mItemID, this.mItemCount, this.mItemMeta)};
    }
}

