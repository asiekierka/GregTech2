/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  ic2.api.CropCard
 *  ic2.api.TECrop
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraftforge.common.ForgeDirection
 */
package gregtechmod.common.tileentities;

import gregtechmod.GT_Mod;
import gregtechmod.api.BaseMetaTileEntity;
import gregtechmod.api.MetaTileEntity;
import gregtechmod.common.GT_Utility;
import gregtechmod.common.tileentities.GT_MetaTileEntity_ElectricBufferSmall;
import ic2.api.CropCard;
import ic2.api.TECrop;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.ForgeDirection;

public class GT_MetaTileEntity_CropHarvestor
extends GT_MetaTileEntity_ElectricBufferSmall {
    public GT_MetaTileEntity_CropHarvestor(int aID, String mName, String mNameRegional) {
        super(aID, mName, mNameRegional);
    }

    public GT_MetaTileEntity_CropHarvestor() {
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
        return false;
    }

    @Override
    public int maxEUStore() {
        return 10000;
    }

    @Override
    public int maxMJStore() {
        return this.maxEUStore();
    }

    @Override
    public void onRightclick(EntityPlayer aPlayer) {
        aPlayer.openGui((Object)GT_Mod.instance, 110, this.mBaseMetaTileEntity.worldObj, this.mBaseMetaTileEntity.xCoord, this.mBaseMetaTileEntity.yCoord, this.mBaseMetaTileEntity.zCoord);
    }

    @Override
    public MetaTileEntity newMetaEntity(BaseMetaTileEntity aTileEntity) {
        return new GT_MetaTileEntity_CropHarvestor();
    }

    @Override
    public void onPostTick() {
        if (!this.mBaseMetaTileEntity.worldObj.isRemote && this.mBaseMetaTileEntity.getStoredEnergy() >= 1000 && this.mBaseMetaTileEntity.mTickTimer % 20L == 0L && this.mInventory[0] == null) {
            TileEntity tTileEntity = this.mBaseMetaTileEntity.worldObj.getBlockTileEntity(this.mBaseMetaTileEntity.xCoord + ForgeDirection.getOrientation((int)this.mBaseMetaTileEntity.getFacing()).offsetX, this.mBaseMetaTileEntity.yCoord + ForgeDirection.getOrientation((int)this.mBaseMetaTileEntity.getFacing()).offsetY, this.mBaseMetaTileEntity.zCoord + ForgeDirection.getOrientation((int)this.mBaseMetaTileEntity.getFacing()).offsetZ);
            if (tTileEntity != null && tTileEntity instanceof TECrop && !CropCard.getCrop((int)((TECrop)tTileEntity).id).canGrow((TECrop)tTileEntity) && CropCard.getCrop((int)((TECrop)tTileEntity).id).canBeHarvested((TECrop)tTileEntity) && ((TECrop)tTileEntity).harvest(false)) {
                this.mBaseMetaTileEntity.decreaseStoredEnergy(800, true);
            }
            if ((this.mInventory[0] = GT_Utility.suckOneItemStackAt(this.mBaseMetaTileEntity.worldObj, this.mBaseMetaTileEntity.xCoord + ForgeDirection.getOrientation((int)this.mBaseMetaTileEntity.getFacing()).offsetX, this.mBaseMetaTileEntity.yCoord + ForgeDirection.getOrientation((int)this.mBaseMetaTileEntity.getFacing()).offsetY, this.mBaseMetaTileEntity.zCoord + ForgeDirection.getOrientation((int)this.mBaseMetaTileEntity.getFacing()).offsetZ, 1, 1, 1)) != null) {
                this.mBaseMetaTileEntity.decreaseStoredEnergy(this.mInventory[0].stackSize * 2, true);
            }
        }
        super.onPostTick();
    }

    @Override
    protected String getDescription() {
        return "Harvests the Cropstick in front of it";
    }

    @Override
    public int getTextureIndex(int aSide, int aFacing, boolean aActive, boolean aRedstone) {
        if (aSide == aFacing) {
            return 118 + (aRedstone ? 8 : 0);
        }
        if (ForgeDirection.getOrientation((int)aSide).getOpposite() == ForgeDirection.getOrientation((int)aFacing)) {
            return 113 + (aRedstone ? 8 : 0);
        }
        int tIndex = 128 + (aRedstone ? 8 : 0);
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

