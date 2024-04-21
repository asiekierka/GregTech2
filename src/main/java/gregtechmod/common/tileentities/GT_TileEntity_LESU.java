/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  ic2.api.Direction
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraftforge.common.ForgeDirection
 */
package gregtechmod.common.tileentities;

import gregtechmod.GT_Mod;
import gregtechmod.common.GT_LanguageManager;
import gregtechmod.common.blocks.GT_BlockMetaID_Block;
import gregtechmod.common.tileentities.GT_TileEntityMetaID_Machine;
import ic2.api.Direction;
import java.util.ArrayList;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.ForgeDirection;

public class GT_TileEntity_LESU
extends GT_TileEntityMetaID_Machine {
    private boolean mNotify = true;
    private boolean mInit = true;
    private int mStorage = 2000000000;

    @Override
    public boolean isFacingValid(int aFacing) {
        return aFacing != this.getFacing();
    }

    @Override
    public boolean isAccessible(EntityPlayer aPlayer) {
        return true;
    }

    @Override
    public boolean isEnetOutput() {
        return true;
    }

    @Override
    public boolean isEnetInput() {
        return true;
    }

    @Override
    public boolean isOutputFacing(short aDirection) {
        return (aDirection + 4) % 6 == this.getFacing();
    }

    @Override
    public boolean isInputFacing(short aDirection) {
        return (aDirection + 4) % 6 != this.getFacing();
    }

    @Override
    public int maxEUStore() {
        return this.mStorage;
    }

    @Override
    public int maxEUInput() {
        return this.getTier(this.maxEUOutput()) == 1 ? 32 : (this.getTier(this.maxEUOutput()) == 2 ? 128 : 512);
    }

    @Override
    public int maxEUOutput() {
        return Math.min(Math.max(this.mStorage / 1000000 + 4, 1), 512);
    }

    @Override
    public int getInventorySlotCount() {
        return 2;
    }

    @Override
    public void storeAdditionalData(NBTTagCompound aNBT) {
        aNBT.setInteger("mStorage", this.mStorage);
    }

    @Override
    public void getAdditionalData(NBTTagCompound aNBT) {
        this.mStorage = aNBT.getInteger("mStorage");
    }

    @Override
    public void onPreTickUpdate() {
        if (!this.worldObj.isRemote && (this.mNotify || this.mInit)) {
            this.mStorage = 1000000;
            if (GT_BlockMetaID_Block.stepToFindOrCallLESUController(this.worldObj, this.xCoord, this.yCoord, this.zCoord, new ArrayList(), this.mInit) < 2) {
                this.mStorage = Math.min(2000000000, 1000000 * GT_BlockMetaID_Block.stepToGetLESUAmount(this.worldObj, this.xCoord, this.yCoord, this.zCoord, new ArrayList()));
            }
            this.mInit = false;
            this.mNotify = false;
            this.worldObj.addBlockEvent(this.xCoord, this.yCoord, this.zCoord, GT_Mod.instance.mBlocks[1].blockID, 10, this.mStorage);
            this.worldObj.notifyBlocksOfNeighborChange(this.xCoord, this.yCoord, this.zCoord, GT_Mod.instance.mBlocks[1].blockID);
        }
    }

    @Override
    public void receiveClientEvent(int aEventID, int aValue) {
        super.receiveClientEvent(aEventID, aValue);
        if (this.worldObj.isRemote) {
            switch (aEventID) {
                case 10: {
                    this.mStorage = aValue;
                }
            }
        }
    }

    @Override
    public int rechargerSlotStartIndex() {
        return 0;
    }

    @Override
    public int rechargerSlotCount() {
        return 1;
    }

    @Override
    public int dechargerSlotStartIndex() {
        return 1;
    }

    @Override
    public int dechargerSlotCount() {
        return 1;
    }

    public void notifyLESUchange() {
        this.mNotify = true;
    }

    @Override
    public int getStartInventorySide(ForgeDirection aSide) {
        if (aSide == ForgeDirection.UP || aSide == ForgeDirection.EAST || aSide == ForgeDirection.WEST) {
            return 0;
        }
        return 1;
    }

    @Override
    public int getSizeInventorySide(ForgeDirection aSide) {
        return 1;
    }

    @Override
    public String getInvName() {
        return GT_LanguageManager.mNameList1[7];
    }

    @Override
    public int getTexture(int aSide, int aMeta) {
        if (aSide == this.getFacing()) {
            return 12 + this.getTier();
        }
        return 12;
    }

    @Override
    public boolean isTeleporterCompatible(Direction side) {
        return true;
    }
}

