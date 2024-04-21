/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraftforge.common.ForgeDirection
 */
package gregtechmod.common.tileentities;

import gregtechmod.api.BaseMetaTileEntity;
import gregtechmod.api.MetaTileEntity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.ForgeDirection;

public class GT_MetaTileEntity_FusionEnergyInjector
extends MetaTileEntity {
    public BaseMetaTileEntity mFusionComputer;

    public GT_MetaTileEntity_FusionEnergyInjector(int aID, String mName, String mNameRegional) {
        super(aID, mName, mNameRegional);
    }

    public GT_MetaTileEntity_FusionEnergyInjector() {
    }

    @Override
    public boolean isSimpleMachine() {
        return false;
    }

    @Override
    public boolean isFacingValid(int aFacing) {
        return false;
    }

    @Override
    public boolean isEnetInput() {
        return true;
    }

    @Override
    public boolean isInputFacing(short aSide) {
        return true;
    }

    @Override
    public int maxEUInput() {
        return 8192;
    }

    @Override
    public int maxEUStore() {
        return 10000000;
    }

    @Override
    public int maxMJStore() {
        return this.maxEUStore();
    }

    @Override
    public int getInvSize() {
        return 0;
    }

    @Override
    public boolean isAccessAllowed(EntityPlayer aPlayer) {
        return true;
    }

    @Override
    public MetaTileEntity newMetaEntity(BaseMetaTileEntity aTileEntity) {
        return new GT_MetaTileEntity_FusionEnergyInjector();
    }

    @Override
    public void saveNBTData(NBTTagCompound aNBT) {
    }

    @Override
    public void loadNBTData(NBTTagCompound aNBT) {
    }

    @Override
    public void onPostTick() {
        if (!this.mBaseMetaTileEntity.worldObj.isRemote && this.mBaseMetaTileEntity.mTickTimer % 20L == 0L) {
            this.mBaseMetaTileEntity.mActive = this.mFusionComputer == null ? false : this.mFusionComputer.mActive;
        }
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
        return aActive ? 20 : 19;
    }

    @Override
    protected String getDescription() {
        return "Stores 10kk EU for the Fusion Reactor";
    }
}

