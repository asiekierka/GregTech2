/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.inventory.IInventory
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraftforge.common.ForgeDirection
 */
package gregtechmod.common.tileentities;

import gregtechmod.GT_Mod;
import gregtechmod.api.BaseMetaTileEntity;
import gregtechmod.api.MetaTileEntity;
import gregtechmod.common.GT_Utility;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.ForgeDirection;

public class GT_MetaTileEntity_ElectricBufferSmall
extends MetaTileEntity {
    public boolean bOutput = true;
    public boolean bRedstoneIfFull = false;
    public boolean bInvert = false;
    public int mSuccess = 0;

    public GT_MetaTileEntity_ElectricBufferSmall(int aID, String mName, String mNameRegional) {
        super(aID, mName, mNameRegional);
    }

    public GT_MetaTileEntity_ElectricBufferSmall() {
    }

    @Override
    public boolean isTransformerUpgradable() {
        return true;
    }

    @Override
    public boolean isOverclockerUpgradable() {
        return false;
    }

    @Override
    public boolean isBatteryUpgradable() {
        return true;
    }

    @Override
    public boolean isSimpleMachine() {
        return true;
    }

    @Override
    public boolean isValidSlot(int aIndex) {
        return aIndex < this.getInvSize() - 1;
    }

    @Override
    public boolean isFacingValid(int aFacing) {
        return true;
    }

    @Override
    public boolean isEnetInput() {
        return true;
    }

    @Override
    public boolean isEnetOutput() {
        return true;
    }

    @Override
    public boolean isInputFacing(short aSide) {
        return !this.isOutputFacing(aSide);
    }

    @Override
    public boolean isOutputFacing(short aSide) {
        return ForgeDirection.getOrientation((int)this.mBaseMetaTileEntity.getFacing()).getOpposite() == ForgeDirection.getOrientation((int)aSide);
    }

    @Override
    public int getMinimumStoredEU() {
        return 1000;
    }

    @Override
    public int maxEUInput() {
        return 32;
    }

    @Override
    public int maxEUOutput() {
        return this.bOutput ? 32 : 0;
    }

    @Override
    public int maxEUStore() {
        return 1250;
    }

    @Override
    public int maxMJStore() {
        return this.maxEUStore();
    }

    @Override
    public int getInvSize() {
        return 2;
    }

    @Override
    public void onRightclick(EntityPlayer aPlayer) {
        aPlayer.openGui((Object)GT_Mod.instance, 102, this.mBaseMetaTileEntity.worldObj, this.mBaseMetaTileEntity.xCoord, this.mBaseMetaTileEntity.yCoord, this.mBaseMetaTileEntity.zCoord);
    }

    @Override
    public boolean isAccessAllowed(EntityPlayer aPlayer) {
        return true;
    }

    @Override
    public MetaTileEntity newMetaEntity(BaseMetaTileEntity aTileEntity) {
        return new GT_MetaTileEntity_ElectricBufferSmall();
    }

    @Override
    public void saveNBTData(NBTTagCompound aNBT) {
        aNBT.setBoolean("bInvert", this.bInvert);
        aNBT.setBoolean("bOutput", this.bOutput);
        aNBT.setBoolean("bRedstoneIfFull", this.bRedstoneIfFull);
    }

    @Override
    public void loadNBTData(NBTTagCompound aNBT) {
        this.bInvert = aNBT.getBoolean("bInvert");
        this.bOutput = aNBT.getBoolean("bOutput");
        this.bRedstoneIfFull = aNBT.getBoolean("bRedstoneIfFull");
    }

    @Override
    public void onPostTick() {
        if (!this.mBaseMetaTileEntity.worldObj.isRemote && this.mBaseMetaTileEntity.getStoredEnergy() >= 500 && (this.mBaseMetaTileEntity.mTickTimer % 200L == 0L || this.mBaseMetaTileEntity.mTickTimer % 5L == 0L && (this.mSuccess > 0 || this.mInventory[0] != null && this.mBaseMetaTileEntity.mTickTimer % 10L == 0L && this.getInvSize() <= 2) || this.mSuccess >= 20 || this.mBaseMetaTileEntity.mInventoryChanged)) {
            --this.mSuccess;
            if (this.getInvSize() > 2 || this.mInventory[0] != null) {
                int tCost;
                int xDir = ForgeDirection.getOrientation((int)this.mBaseMetaTileEntity.getFacing()).offsetX;
                int yDir = ForgeDirection.getOrientation((int)this.mBaseMetaTileEntity.getFacing()).offsetY;
                int zDir = ForgeDirection.getOrientation((int)this.mBaseMetaTileEntity.getFacing()).offsetZ;
                BaseMetaTileEntity tTileEntity1 = this.mBaseMetaTileEntity;
                TileEntity tTileEntity2 = this.mBaseMetaTileEntity.worldObj.getBlockTileEntity(this.mBaseMetaTileEntity.xCoord - xDir, this.mBaseMetaTileEntity.yCoord - yDir, this.mBaseMetaTileEntity.zCoord - zDir);
                if (tTileEntity1 != null && tTileEntity2 != null && tTileEntity1 instanceof IInventory && tTileEntity2 instanceof IInventory && (tCost = GT_Utility.moveOneItemStack((IInventory)tTileEntity1, (IInventory)tTileEntity2, ForgeDirection.getOrientation((int)this.mBaseMetaTileEntity.getFacing()).getOpposite(), ForgeDirection.getOrientation((int)this.mBaseMetaTileEntity.getFacing()), null, false, 64, 1, 64, 1) * (this.getInvSize() > 10 ? 2 : 1)) > 0) {
                    this.mSuccess = 20;
                    this.mBaseMetaTileEntity.decreaseStoredEnergy(tCost, true);
                }
            }
            this.mBaseMetaTileEntity.mRedstone = this.bInvert;
            if (this.bRedstoneIfFull) {
                this.mBaseMetaTileEntity.mRedstone = !this.bInvert;
                for (int i = 0; i < this.mInventory.length; ++i) {
                    if (!this.isValidSlot(i) || this.mInventory[i] != null) continue;
                    this.mBaseMetaTileEntity.mRedstone = this.bInvert;
                    this.mBaseMetaTileEntity.decreaseStoredEnergy(1, true);
                    break;
                }
            }
        }
    }

    @Override
    protected String getDescription() {
        return "A small directable Hopper with an inbuilt Energy Conduit!";
    }

    @Override
    public int getInvSideIndex(ForgeDirection aSide, int aFacing) {
        return 0;
    }

    @Override
    public int getInvSideLength(ForgeDirection aSide, int aFacing) {
        return this.getInvSize() - 1;
    }

    @Override
    public int getTextureIndex(int aSide, int aFacing, boolean aActive, boolean aRedstone) {
        if (aSide == aFacing) {
            return 130 + (aRedstone ? 8 : 0);
        }
        if (ForgeDirection.getOrientation((int)aSide).getOpposite() == ForgeDirection.getOrientation((int)aFacing)) {
            return 113 + (aRedstone ? 8 : 0);
        }
        int tIndex = 130 + (aRedstone ? 8 : 0);
        switch (aFacing) {
            case 0: {
                return tIndex + 64;
            }
            case 1: {
                return tIndex + 32;
            }
            case 2: {
                switch (aSide) {
                    case 0: {
                        return tIndex + 32;
                    }
                    case 1: {
                        return tIndex + 32;
                    }
                    case 4: {
                        return tIndex + 16;
                    }
                    case 5: {
                        return tIndex + 48;
                    }
                }
            }
            case 3: {
                switch (aSide) {
                    case 0: {
                        return tIndex + 64;
                    }
                    case 1: {
                        return tIndex + 64;
                    }
                    case 4: {
                        return tIndex + 48;
                    }
                    case 5: {
                        return tIndex + 16;
                    }
                }
            }
            case 4: {
                switch (aSide) {
                    case 0: {
                        return tIndex + 16;
                    }
                    case 1: {
                        return tIndex + 16;
                    }
                    case 2: {
                        return tIndex + 48;
                    }
                    case 3: {
                        return tIndex + 16;
                    }
                }
            }
            case 5: {
                switch (aSide) {
                    case 0: {
                        return tIndex + 48;
                    }
                    case 1: {
                        return tIndex + 48;
                    }
                    case 2: {
                        return tIndex + 16;
                    }
                    case 3: {
                        return tIndex + 48;
                    }
                }
            }
        }
        return tIndex;
    }
}

