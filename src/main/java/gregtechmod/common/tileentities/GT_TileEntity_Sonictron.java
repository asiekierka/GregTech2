/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraftforge.common.ForgeDirection
 */
package gregtechmod.common.tileentities;

import gregtechmod.GT_Mod;
import gregtechmod.common.GT_LanguageManager;
import gregtechmod.common.tileentities.GT_TileEntityMetaID_Machine;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.ForgeDirection;

public class GT_TileEntity_Sonictron
extends GT_TileEntityMetaID_Machine {
    public int mCurrentIndex = -1;

    @Override
    public int getInventorySlotCount() {
        return 64;
    }

    @Override
    public boolean isValidSlot(int aIndex) {
        return false;
    }

    @Override
    public float getWrenchDropRate() {
        return 1.0f;
    }

    @Override
    public void storeAdditionalData(NBTTagCompound aNBT) {
        aNBT.setInteger("mCurrentIndex", this.mCurrentIndex);
    }

    @Override
    public void getAdditionalData(NBTTagCompound aNBT) {
        this.mCurrentIndex = aNBT.getInteger("mCurrentIndex");
    }

    @Override
    public void onPostTickUpdate() {
        if (this.mCurrentIndex < 0 && !this.worldObj.isRemote && this.worldObj.isBlockIndirectlyGettingPowered(this.xCoord, this.yCoord, this.zCoord)) {
            this.worldObj.addBlockEvent(this.xCoord, this.yCoord, this.zCoord, GT_Mod.instance.mBlocks[1].blockID, 10, 0);
            this.mCurrentIndex = 0;
        }
        boolean bl = this.mRedstone = this.mCurrentIndex == 63;
        if (this.mTickTimer % 2L == 0L && this.mCurrentIndex > -1) {
            GT_Mod.gregtechproxy.doSound(this.mInventory[this.mCurrentIndex], this.worldObj, (double)this.xCoord + 0.5, (double)this.yCoord + 0.5, (double)this.zCoord + 0.5);
            if (++this.mCurrentIndex > 63) {
                this.mCurrentIndex = -1;
            }
        }
    }

    @Override
    public boolean isAccessible(EntityPlayer aPlayer) {
        ItemStack tStack = aPlayer.getCurrentEquippedItem();
        if (tStack == null) {
            return true;
        }
        if (tStack.isItemEqual(GT_Mod.getGregTechItem(32, 1, 0))) {
            return false;
        }
        return !tStack.isItemEqual(GT_Mod.getGregTechItem(43, 1, 0));
    }

    @Override
    public void receiveClientEvent(int aEventID, int aValue) {
        super.receiveClientEvent(aEventID, aValue);
        if (this.worldObj.isRemote) {
            switch (aEventID) {
                case 10: {
                    this.mCurrentIndex = aValue;
                }
            }
        }
    }

    @Override
    public int getStartInventorySide(ForgeDirection aSide) {
        return 0;
    }

    @Override
    public int getSizeInventorySide(ForgeDirection aSide) {
        return 0;
    }

    @Override
    public String getInvName() {
        return GT_LanguageManager.mNameList1[6];
    }

    @Override
    public int getTexture(int aSide, int aMeta) {
        return 39;
    }

    @Override
    public void doEnergyExplosion() {
    }
}

