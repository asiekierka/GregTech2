/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.tileentity.TileEntity
 */
package gregtechmod.common.tileentities;

import gregtechmod.GT_Mod;
import gregtechmod.api.BaseMetaTileEntity;
import gregtechmod.common.GT_LanguageManager;
import gregtechmod.common.tileentities.GT_TileEntityMetaID_Machine;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

public class GT_TileEntity_Quantumchest
extends GT_TileEntityMetaID_Machine {
    private int mItemCount = 0;
    private int mItemID = 0;
    private int mItemMeta = 0;

    @Override
    public boolean isFacingValid(int aFacing) {
        return aFacing > 1;
    }

    @Override
    public boolean isAccessible(EntityPlayer aPlayer) {
        return true;
    }

    @Override
    public int getInventorySlotCount() {
        return 4;
    }

    @Override
    public boolean isValidSlot(int aIndex) {
        return false;
    }

    @Override
    public void storeAdditionalData(NBTTagCompound aNBT) {
        aNBT.setInteger("mItemCount", this.mItemCount);
        aNBT.setInteger("mItemID", this.mItemID);
        aNBT.setInteger("mItemMeta", this.mItemMeta);
    }

    @Override
    public void getAdditionalData(NBTTagCompound aNBT) {
        this.mItemCount = aNBT.getInteger("mItemCount");
        this.mItemID = aNBT.getInteger("mItemID");
        this.mItemMeta = aNBT.getInteger("mItemMeta");
    }

    @Override
    public void onPostTickUpdate() {
        if (!this.worldObj.isRemote && this.mTickTimer == 100L) {
            int tX = this.xCoord;
            int tY = this.yCoord;
            int tZ = this.zCoord;
            if (this.mInventory[0] != null) {
                this.mItemCount += this.mInventory[0].stackSize;
            }
            if (this.mInventory[1] != null) {
                this.mItemCount += this.mInventory[1].stackSize;
            }
            if (this.mInventory[2] != null) {
                this.mItemCount += this.mInventory[2].stackSize;
            }
            if (this.mInventory[3] != null) {
                this.mItemCount += this.mInventory[3].stackSize;
            }
            NBTTagCompound tNBT = new NBTTagCompound();
            tNBT.setInteger("mID", 49);
            tNBT.setShort("mFacing", this.getFacing());
            tNBT.setString("mOwnerName", this.mOwnerName);
            tNBT.setInteger("mItemCount", this.mItemCount);
            tNBT.setInteger("mItemID", this.mItemID);
            tNBT.setInteger("mItemMeta", this.mItemMeta);
            this.worldObj.setBlockMetadataWithNotify(tX, tY, tZ, 0);
            this.worldObj.removeBlockTileEntity(tX, tY, tZ);
            BaseMetaTileEntity tTileEntity = GT_Mod.constructBaseMetaTileEntity();
            this.worldObj.setBlockTileEntity(tX, tY, tZ, (TileEntity)tTileEntity);
            tTileEntity.setInitialValuesAsNBT(tNBT);
        }
    }

    @Override
    public String getInvName() {
        return GT_LanguageManager.mNameList1[3];
    }

    @Override
    public int getTexture(int aSide, int aMeta) {
        return 0;
    }
}

