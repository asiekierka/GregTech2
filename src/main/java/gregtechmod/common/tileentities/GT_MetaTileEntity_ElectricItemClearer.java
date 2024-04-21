/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraftforge.common.ForgeDirection
 */
package gregtechmod.common.tileentities;

import gregtechmod.GT_Mod;
import gregtechmod.api.BaseMetaTileEntity;
import gregtechmod.api.MetaTileEntity;
import gregtechmod.common.GT_Utility;
import gregtechmod.common.tileentities.GT_MetaTileEntity_ElectricBufferSmall;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.common.ForgeDirection;

public class GT_MetaTileEntity_ElectricItemClearer
extends GT_MetaTileEntity_ElectricBufferSmall {
    public GT_MetaTileEntity_ElectricItemClearer(int aID, String mName, String mNameRegional) {
        super(aID, mName, mNameRegional);
    }

    public GT_MetaTileEntity_ElectricItemClearer() {
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
        aPlayer.openGui((Object)GT_Mod.instance, 108, this.mBaseMetaTileEntity.worldObj, this.mBaseMetaTileEntity.xCoord, this.mBaseMetaTileEntity.yCoord, this.mBaseMetaTileEntity.zCoord);
    }

    @Override
    public MetaTileEntity newMetaEntity(BaseMetaTileEntity aTileEntity) {
        return new GT_MetaTileEntity_ElectricItemClearer();
    }

    @Override
    public void onPostTick() {
        if (!(this.mBaseMetaTileEntity.worldObj.isRemote || this.mBaseMetaTileEntity.getStoredEnergy() < 100 || this.mBaseMetaTileEntity.mTickTimer % 20L != 0L && this.mSuccess != 20 || this.mInventory[0] != null || (this.mInventory[0] = GT_Utility.suckOneItemStackAt(this.mBaseMetaTileEntity.worldObj, this.mBaseMetaTileEntity.xCoord + ForgeDirection.getOrientation((int)this.mBaseMetaTileEntity.getFacing()).offsetX * 2 - 1, this.mBaseMetaTileEntity.yCoord + ForgeDirection.getOrientation((int)this.mBaseMetaTileEntity.getFacing()).offsetY * 2 - 1, this.mBaseMetaTileEntity.zCoord + ForgeDirection.getOrientation((int)this.mBaseMetaTileEntity.getFacing()).offsetZ * 2 - 1, 3, 3, 3)) == null)) {
            this.mBaseMetaTileEntity.decreaseStoredEnergy(this.mInventory[0].stackSize * 2, true);
            this.mSuccess = 20;
        }
        super.onPostTick();
    }

    @Override
    protected String getDescription() {
        return "Clears out a 3x3x3 in front of it";
    }

    @Override
    public int getTextureIndex(int aSide, int aFacing, boolean aActive, boolean aRedstone) {
        if (aSide == aFacing) {
            return 117 + (aRedstone ? 8 : 0);
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

