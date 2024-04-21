/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  ic2.api.energy.tile.IEnergyConductor
 *  ic2.api.energy.tile.IEnergyTile
 *  net.minecraft.entity.player.EntityPlayer
 */
package gregtechmod.common.tileentities;

import gregtechmod.common.GT_LanguageManager;
import gregtechmod.common.tileentities.GT_TileEntityMetaID_Machine;
import ic2.api.energy.tile.IEnergyConductor;
import ic2.api.energy.tile.IEnergyTile;
import net.minecraft.entity.player.EntityPlayer;

public class GT_TileEntity_Superconductor
extends GT_TileEntityMetaID_Machine
implements IEnergyConductor {
    @Override
    public boolean isFacingValid(int aFacing) {
        return false;
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
        return true;
    }

    @Override
    public boolean isInputFacing(short aDirection) {
        return true;
    }

    @Override
    public int maxEUInput() {
        return Integer.MAX_VALUE;
    }

    @Override
    public int maxEUOutput() {
        return Integer.MAX_VALUE;
    }

    @Override
    public String getInvName() {
        return GT_LanguageManager.mNameList1[12];
    }

    @Override
    public float getWrenchDropRate() {
        return 1.0f;
    }

    @Override
    public int getTexture(int aSide, int aMeta) {
        if (this.xCoord == 0 && this.yCoord == 0 && this.zCoord == 0) {
            return 103;
        }
        boolean[] tConnectedSides = new boolean[]{this.worldObj.getBlockTileEntity(this.xCoord, this.yCoord - 1, this.zCoord) instanceof IEnergyTile, this.worldObj.getBlockTileEntity(this.xCoord, this.yCoord + 1, this.zCoord) instanceof IEnergyTile, this.worldObj.getBlockTileEntity(this.xCoord + 1, this.yCoord, this.zCoord) instanceof IEnergyTile, this.worldObj.getBlockTileEntity(this.xCoord, this.yCoord, this.zCoord + 1) instanceof IEnergyTile, this.worldObj.getBlockTileEntity(this.xCoord - 1, this.yCoord, this.zCoord) instanceof IEnergyTile, this.worldObj.getBlockTileEntity(this.xCoord, this.yCoord, this.zCoord - 1) instanceof IEnergyTile};
        switch (aSide) {
            case 0: {
                if (tConnectedSides[0]) {
                    return 103;
                }
                if (tConnectedSides[4] && tConnectedSides[5] && tConnectedSides[2] && tConnectedSides[3]) {
                    return 102;
                }
                if (!tConnectedSides[4] && tConnectedSides[5] && tConnectedSides[2] && tConnectedSides[3]) {
                    return 98;
                }
                if (tConnectedSides[4] && !tConnectedSides[5] && tConnectedSides[2] && tConnectedSides[3]) {
                    return 99;
                }
                if (tConnectedSides[4] && tConnectedSides[5] && !tConnectedSides[2] && tConnectedSides[3]) {
                    return 100;
                }
                if (tConnectedSides[4] && tConnectedSides[5] && tConnectedSides[2] && !tConnectedSides[3]) {
                    return 101;
                }
                if (!tConnectedSides[4] && !tConnectedSides[5] && tConnectedSides[2] && tConnectedSides[3]) {
                    return 104;
                }
                if (tConnectedSides[4] && !tConnectedSides[5] && !tConnectedSides[2] && tConnectedSides[3]) {
                    return 105;
                }
                if (tConnectedSides[4] && tConnectedSides[5] && !tConnectedSides[2] && !tConnectedSides[3]) {
                    return 106;
                }
                if (!tConnectedSides[4] && tConnectedSides[5] && tConnectedSides[2] && !tConnectedSides[3]) {
                    return 107;
                }
                if (!(tConnectedSides[4] || tConnectedSides[5] || tConnectedSides[2] || tConnectedSides[3])) {
                    return 103;
                }
                if (!tConnectedSides[4] && !tConnectedSides[2]) {
                    return 97;
                }
                if (!tConnectedSides[5] && !tConnectedSides[3]) {
                    return 96;
                }
            }
            case 1: {
                if (tConnectedSides[1]) {
                    return 103;
                }
                if (tConnectedSides[4] && tConnectedSides[5] && tConnectedSides[2] && tConnectedSides[3]) {
                    return 102;
                }
                if (!tConnectedSides[4] && tConnectedSides[5] && tConnectedSides[2] && tConnectedSides[3]) {
                    return 98;
                }
                if (tConnectedSides[4] && !tConnectedSides[5] && tConnectedSides[2] && tConnectedSides[3]) {
                    return 99;
                }
                if (tConnectedSides[4] && tConnectedSides[5] && !tConnectedSides[2] && tConnectedSides[3]) {
                    return 100;
                }
                if (tConnectedSides[4] && tConnectedSides[5] && tConnectedSides[2] && !tConnectedSides[3]) {
                    return 101;
                }
                if (!tConnectedSides[4] && !tConnectedSides[5] && tConnectedSides[2] && tConnectedSides[3]) {
                    return 104;
                }
                if (tConnectedSides[4] && !tConnectedSides[5] && !tConnectedSides[2] && tConnectedSides[3]) {
                    return 105;
                }
                if (tConnectedSides[4] && tConnectedSides[5] && !tConnectedSides[2] && !tConnectedSides[3]) {
                    return 106;
                }
                if (!tConnectedSides[4] && tConnectedSides[5] && tConnectedSides[2] && !tConnectedSides[3]) {
                    return 107;
                }
                if (!(tConnectedSides[4] || tConnectedSides[5] || tConnectedSides[2] || tConnectedSides[3])) {
                    return 103;
                }
                if (!tConnectedSides[2] && !tConnectedSides[4]) {
                    return 97;
                }
                if (!tConnectedSides[3] && !tConnectedSides[5]) {
                    return 96;
                }
            }
            case 2: {
                if (tConnectedSides[5]) {
                    return 103;
                }
                if (tConnectedSides[2] && tConnectedSides[0] && tConnectedSides[4] && tConnectedSides[1]) {
                    return 102;
                }
                if (!tConnectedSides[2] && tConnectedSides[0] && tConnectedSides[4] && tConnectedSides[1]) {
                    return 98;
                }
                if (tConnectedSides[2] && !tConnectedSides[0] && tConnectedSides[4] && tConnectedSides[1]) {
                    return 101;
                }
                if (tConnectedSides[2] && tConnectedSides[0] && !tConnectedSides[4] && tConnectedSides[1]) {
                    return 100;
                }
                if (tConnectedSides[2] && tConnectedSides[0] && tConnectedSides[4] && !tConnectedSides[1]) {
                    return 99;
                }
                if (!tConnectedSides[2] && !tConnectedSides[0] && tConnectedSides[4] && tConnectedSides[1]) {
                    return 107;
                }
                if (tConnectedSides[2] && !tConnectedSides[0] && !tConnectedSides[4] && tConnectedSides[1]) {
                    return 106;
                }
                if (tConnectedSides[2] && tConnectedSides[0] && !tConnectedSides[4] && !tConnectedSides[1]) {
                    return 105;
                }
                if (!tConnectedSides[2] && tConnectedSides[0] && tConnectedSides[4] && !tConnectedSides[1]) {
                    return 104;
                }
                if (!(tConnectedSides[2] || tConnectedSides[0] || tConnectedSides[4] || tConnectedSides[1])) {
                    return 103;
                }
                if (!tConnectedSides[2] && !tConnectedSides[4]) {
                    return 97;
                }
                if (!tConnectedSides[0] && !tConnectedSides[1]) {
                    return 96;
                }
            }
            case 3: {
                if (tConnectedSides[3]) {
                    return 103;
                }
                if (tConnectedSides[2] && tConnectedSides[0] && tConnectedSides[4] && tConnectedSides[1]) {
                    return 102;
                }
                if (!tConnectedSides[2] && tConnectedSides[0] && tConnectedSides[4] && tConnectedSides[1]) {
                    return 100;
                }
                if (tConnectedSides[2] && !tConnectedSides[0] && tConnectedSides[4] && tConnectedSides[1]) {
                    return 101;
                }
                if (tConnectedSides[2] && tConnectedSides[0] && !tConnectedSides[4] && tConnectedSides[1]) {
                    return 98;
                }
                if (tConnectedSides[2] && tConnectedSides[0] && tConnectedSides[4] && !tConnectedSides[1]) {
                    return 99;
                }
                if (!tConnectedSides[2] && !tConnectedSides[0] && tConnectedSides[4] && tConnectedSides[1]) {
                    return 106;
                }
                if (tConnectedSides[2] && !tConnectedSides[0] && !tConnectedSides[4] && tConnectedSides[1]) {
                    return 107;
                }
                if (tConnectedSides[2] && tConnectedSides[0] && !tConnectedSides[4] && !tConnectedSides[1]) {
                    return 104;
                }
                if (!tConnectedSides[2] && tConnectedSides[0] && tConnectedSides[4] && !tConnectedSides[1]) {
                    return 105;
                }
                if (!(tConnectedSides[2] || tConnectedSides[0] || tConnectedSides[4] || tConnectedSides[1])) {
                    return 103;
                }
                if (!tConnectedSides[2] && !tConnectedSides[4]) {
                    return 97;
                }
                if (!tConnectedSides[0] && !tConnectedSides[1]) {
                    return 96;
                }
            }
            case 4: {
                if (tConnectedSides[4]) {
                    return 103;
                }
                if (tConnectedSides[0] && tConnectedSides[3] && tConnectedSides[1] && tConnectedSides[5]) {
                    return 102;
                }
                if (!tConnectedSides[0] && tConnectedSides[3] && tConnectedSides[1] && tConnectedSides[5]) {
                    return 101;
                }
                if (tConnectedSides[0] && !tConnectedSides[3] && tConnectedSides[1] && tConnectedSides[5]) {
                    return 100;
                }
                if (tConnectedSides[0] && tConnectedSides[3] && !tConnectedSides[1] && tConnectedSides[5]) {
                    return 99;
                }
                if (tConnectedSides[0] && tConnectedSides[3] && tConnectedSides[1] && !tConnectedSides[5]) {
                    return 98;
                }
                if (!tConnectedSides[0] && !tConnectedSides[3] && tConnectedSides[1] && tConnectedSides[5]) {
                    return 106;
                }
                if (tConnectedSides[0] && !tConnectedSides[3] && !tConnectedSides[1] && tConnectedSides[5]) {
                    return 105;
                }
                if (tConnectedSides[0] && tConnectedSides[3] && !tConnectedSides[1] && !tConnectedSides[5]) {
                    return 104;
                }
                if (!tConnectedSides[0] && tConnectedSides[3] && tConnectedSides[1] && !tConnectedSides[5]) {
                    return 107;
                }
                if (!(tConnectedSides[0] || tConnectedSides[3] || tConnectedSides[1] || tConnectedSides[5])) {
                    return 103;
                }
                if (!tConnectedSides[0] && !tConnectedSides[1]) {
                    return 96;
                }
                if (!tConnectedSides[3] && !tConnectedSides[5]) {
                    return 97;
                }
            }
            case 5: {
                if (tConnectedSides[2]) {
                    return 103;
                }
                if (tConnectedSides[0] && tConnectedSides[3] && tConnectedSides[1] && tConnectedSides[5]) {
                    return 102;
                }
                if (!tConnectedSides[0] && tConnectedSides[3] && tConnectedSides[1] && tConnectedSides[5]) {
                    return 101;
                }
                if (tConnectedSides[0] && !tConnectedSides[3] && tConnectedSides[1] && tConnectedSides[5]) {
                    return 98;
                }
                if (tConnectedSides[0] && tConnectedSides[3] && !tConnectedSides[1] && tConnectedSides[5]) {
                    return 99;
                }
                if (tConnectedSides[0] && tConnectedSides[3] && tConnectedSides[1] && !tConnectedSides[5]) {
                    return 100;
                }
                if (!tConnectedSides[0] && !tConnectedSides[3] && tConnectedSides[1] && tConnectedSides[5]) {
                    return 107;
                }
                if (tConnectedSides[0] && !tConnectedSides[3] && !tConnectedSides[1] && tConnectedSides[5]) {
                    return 104;
                }
                if (tConnectedSides[0] && tConnectedSides[3] && !tConnectedSides[1] && !tConnectedSides[5]) {
                    return 105;
                }
                if (!tConnectedSides[0] && tConnectedSides[3] && tConnectedSides[1] && !tConnectedSides[5]) {
                    return 106;
                }
                if (!(tConnectedSides[0] || tConnectedSides[3] || tConnectedSides[1] || tConnectedSides[5])) {
                    return 103;
                }
                if (!tConnectedSides[0] && !tConnectedSides[1]) {
                    return 96;
                }
                if (tConnectedSides[3] || tConnectedSides[5]) break;
                return 97;
            }
        }
        return 103;
    }

    public double getConductionLoss() {
        return 0.0;
    }

    public int getInsulationEnergyAbsorption() {
        return 8192;
    }

    public int getInsulationBreakdownEnergy() {
        return Integer.MAX_VALUE;
    }

    public int getConductorBreakdownEnergy() {
        return Integer.MAX_VALUE;
    }

    public void removeInsulation() {
        this.doExplosion(10000);
    }

    public void removeConductor() {
        this.doExplosion(10000);
    }

    @Override
    public void doEnergyExplosion() {
    }
}

