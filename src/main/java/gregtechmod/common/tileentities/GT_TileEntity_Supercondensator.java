/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.EntityPlayer
 */
package gregtechmod.common.tileentities;

import gregtechmod.common.GT_LanguageManager;
import gregtechmod.common.tileentities.GT_TileEntityMetaID_Machine;
import net.minecraft.entity.player.EntityPlayer;

public class GT_TileEntity_Supercondensator
extends GT_TileEntityMetaID_Machine {
    @Override
    public boolean isFacingValid(int aFacing) {
        return aFacing != this.getFacing();
    }

    @Override
    public boolean isAccessible(EntityPlayer aPlayer) {
        return false;
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
        return this.worldObj.isBlockIndirectlyGettingPowered(this.xCoord, this.yCoord, this.zCoord) ? 10000000 : 8192;
    }

    @Override
    public int maxEUInput() {
        return 1000000;
    }

    @Override
    public int maxEUOutput() {
        return this.worldObj.isBlockIndirectlyGettingPowered(this.xCoord, this.yCoord, this.zCoord) ? 1000000 : 8192;
    }

    @Override
    public String getInvName() {
        return GT_LanguageManager.mNameList1[15];
    }

    @Override
    public float getWrenchDropRate() {
        return 1.0f;
    }

    @Override
    public int getTexture(int aSide, int aMeta) {
        if (aSide == this.getFacing()) {
            return 103;
        }
        return 108;
    }
}

