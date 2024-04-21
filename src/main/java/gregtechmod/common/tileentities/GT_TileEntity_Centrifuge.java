/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.tileentity.TileEntity
 */
package gregtechmod.common.tileentities;

import gregtechmod.GT_Mod;
import gregtechmod.api.BaseMetaTileEntity;
import gregtechmod.common.GT_LanguageManager;
import gregtechmod.common.tileentities.GT_TileEntityMetaID_Machine;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

public class GT_TileEntity_Centrifuge
extends GT_TileEntityMetaID_Machine {
    private int mProgresstime = 0;
    private int mMaxProgresstime = 0;
    private int mStoredLava = 0;
    private ItemStack mOutputItem1;
    private ItemStack mOutputItem2;
    private ItemStack mOutputItem3;
    private ItemStack mOutputItem4;

    @Override
    public boolean isValidSlot(int aIndex) {
        return aIndex < 6;
    }

    @Override
    public int getInventorySlotCount() {
        return 7;
    }

    @Override
    public void storeAdditionalData(NBTTagCompound aNBT) {
        NBTTagCompound tNBT;
        aNBT.setInteger("mStoredLava", this.mStoredLava);
        aNBT.setInteger("mProgresstime", this.mProgresstime);
        aNBT.setInteger("mMaxProgresstime", this.mMaxProgresstime);
        if (this.mOutputItem1 != null) {
            tNBT = new NBTTagCompound();
            this.mOutputItem1.writeToNBT(tNBT);
            aNBT.setTag("mOutputItem1", (NBTBase)tNBT);
        }
        if (this.mOutputItem2 != null) {
            tNBT = new NBTTagCompound();
            this.mOutputItem2.writeToNBT(tNBT);
            aNBT.setTag("mOutputItem2", (NBTBase)tNBT);
        }
        if (this.mOutputItem3 != null) {
            tNBT = new NBTTagCompound();
            this.mOutputItem3.writeToNBT(tNBT);
            aNBT.setTag("mOutputItem3", (NBTBase)tNBT);
        }
        if (this.mOutputItem4 != null) {
            tNBT = new NBTTagCompound();
            this.mOutputItem4.writeToNBT(tNBT);
            aNBT.setTag("mOutputItem4", (NBTBase)tNBT);
        }
    }

    @Override
    public void getAdditionalData(NBTTagCompound aNBT) {
        NBTTagCompound tNBT4;
        NBTTagCompound tNBT3;
        NBTTagCompound tNBT2;
        this.mStoredLava = aNBT.getInteger("mStoredLava");
        this.mProgresstime = aNBT.getInteger("mProgresstime");
        this.mMaxProgresstime = aNBT.getInteger("mMaxProgresstime");
        NBTTagCompound tNBT1 = (NBTTagCompound)aNBT.getTag("mOutputItem1");
        if (tNBT1 != null) {
            this.mOutputItem1 = ItemStack.loadItemStackFromNBT((NBTTagCompound)tNBT1);
        }
        if ((tNBT2 = (NBTTagCompound)aNBT.getTag("mOutputItem2")) != null) {
            this.mOutputItem2 = ItemStack.loadItemStackFromNBT((NBTTagCompound)tNBT2);
        }
        if ((tNBT3 = (NBTTagCompound)aNBT.getTag("mOutputItem3")) != null) {
            this.mOutputItem3 = ItemStack.loadItemStackFromNBT((NBTTagCompound)tNBT3);
        }
        if ((tNBT4 = (NBTTagCompound)aNBT.getTag("mOutputItem4")) != null) {
            this.mOutputItem4 = ItemStack.loadItemStackFromNBT((NBTTagCompound)tNBT4);
        }
    }

    @Override
    public void onPostTickUpdate() {
        if (!this.worldObj.isRemote && this.mTickTimer == 100L) {
            NBTTagCompound tNBT2;
            int tX = this.xCoord;
            int tY = this.yCoord;
            int tZ = this.zCoord;
            NBTTagCompound tNBT = new NBTTagCompound();
            tNBT.setInteger("mID", 62);
            tNBT.setInteger("mEUt", 5);
            tNBT.setShort("mFacing", this.getFacing());
            tNBT.setString("mOwnerName", this.mOwnerName);
            tNBT.setInteger("mProgresstime", this.mProgresstime);
            tNBT.setInteger("mMaxProgresstime", this.mMaxProgresstime);
            tNBT.setInteger("mStoredLava", this.mStoredLava + (this.mInventory[6] == null ? 0 : this.mInventory[6].stackSize * 1000));
            if (this.mOutputItem1 != null) {
                tNBT2 = new NBTTagCompound();
                this.mOutputItem1.writeToNBT(tNBT2);
                tNBT.setTag("mOutputItem1", (NBTBase)tNBT2);
            }
            if (this.mOutputItem2 != null) {
                tNBT2 = new NBTTagCompound();
                this.mOutputItem2.writeToNBT(tNBT2);
                tNBT.setTag("mOutputItem2", (NBTBase)tNBT2);
            }
            if (this.mOutputItem3 != null) {
                tNBT2 = new NBTTagCompound();
                this.mOutputItem3.writeToNBT(tNBT2);
                tNBT.setTag("mOutputItem3", (NBTBase)tNBT2);
            }
            if (this.mOutputItem4 != null) {
                tNBT2 = new NBTTagCompound();
                this.mOutputItem4.writeToNBT(tNBT2);
                tNBT.setTag("mOutputItem4", (NBTBase)tNBT2);
            }
            this.worldObj.setBlockMetadataWithNotify(tX, tY, tZ, 0);
            this.worldObj.removeBlockTileEntity(tX, tY, tZ);
            BaseMetaTileEntity tTileEntity = GT_Mod.constructBaseMetaTileEntity();
            this.worldObj.setBlockTileEntity(tX, tY, tZ, (TileEntity)tTileEntity);
            tTileEntity.setInitialValuesAsNBT(tNBT);
        }
    }

    @Override
    public String getInvName() {
        return GT_LanguageManager.mNameList1[11];
    }

    @Override
    public int getTexture(int aSide, int aMeta) {
        return 32;
    }
}

