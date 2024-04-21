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
import gregtechmod.common.GT_ModHandler;
import gregtechmod.common.tileentities.GT_MetaTileEntity_ElectricBufferSmall;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.common.ForgeDirection;

public class GT_MetaTileEntity_Scrapboxinator
extends GT_MetaTileEntity_ElectricBufferSmall {
    public GT_MetaTileEntity_Scrapboxinator(int aID, String mName, String mNameRegional) {
        super(aID, mName, mNameRegional);
    }

    public GT_MetaTileEntity_Scrapboxinator() {
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
    public int getMinimumStoredEU() {
        return 2000;
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
    public int getInvSize() {
        return 3;
    }

    @Override
    public void onRightclick(EntityPlayer aPlayer) {
        aPlayer.openGui((Object)GT_Mod.instance, 111, this.mBaseMetaTileEntity.worldObj, this.mBaseMetaTileEntity.xCoord, this.mBaseMetaTileEntity.yCoord, this.mBaseMetaTileEntity.zCoord);
    }

    @Override
    public void onPostTick() {
        if (!this.mBaseMetaTileEntity.worldObj.isRemote && this.mBaseMetaTileEntity.getStoredEnergy() >= 500 && (this.mBaseMetaTileEntity.mTickTimer % 200L == 0L || this.mSuccess > 0 && this.mBaseMetaTileEntity.mTickTimer % 5L == 0L || this.mSuccess >= 20) && this.mInventory[0] == null && this.mInventory[1] != null && this.mInventory[1].stackSize > 0) {
            if (this.mInventory[1].isItemEqual(GT_ModHandler.getIC2Item("scrapBox", 1))) {
                this.mInventory[0] = GT_ModHandler.getRandomScrapboxDrop(this.mBaseMetaTileEntity.worldObj);
                this.mBaseMetaTileEntity.decrStackSize(1, 1);
                this.mBaseMetaTileEntity.decreaseStoredEnergy(100, true);
                this.mSuccess = 30;
            } else if (this.mInventory[1].isItemEqual(GT_ModHandler.getIC2Item("scrap", 1))) {
                if (this.mInventory[1].stackSize > 8) {
                    this.mInventory[0] = GT_ModHandler.getRandomScrapboxDrop(this.mBaseMetaTileEntity.worldObj);
                    this.mBaseMetaTileEntity.decrStackSize(1, 9);
                    this.mBaseMetaTileEntity.decreaseStoredEnergy(200, true);
                    this.mSuccess = 30;
                }
            } else {
                this.mInventory[0] = this.mInventory[1];
                this.mInventory[1] = null;
            }
        }
        super.onPostTick();
    }

    @Override
    public int getInvSideIndex(ForgeDirection aSide, int aFacing) {
        if (aSide == ForgeDirection.getOrientation((int)this.mBaseMetaTileEntity.getFacing()).getOpposite()) {
            return 0;
        }
        return 1;
    }

    @Override
    public int getInvSideLength(ForgeDirection aSide, int aFacing) {
        return 1;
    }

    @Override
    public MetaTileEntity newMetaEntity(BaseMetaTileEntity aTileEntity) {
        return new GT_MetaTileEntity_Scrapboxinator();
    }

    @Override
    public int getTextureIndex(int aSide, int aFacing, boolean aActive, boolean aRedstone) {
        if (ForgeDirection.getOrientation((int)aSide).getOpposite() == ForgeDirection.getOrientation((int)aFacing)) {
            return 113 + (aRedstone ? 8 : 0);
        }
        return 119 + (aRedstone ? 8 : 0);
    }

    @Override
    protected String getDescription() {
        return "Makes Scrapboxes and Scrap into random Stuff!";
    }
}

