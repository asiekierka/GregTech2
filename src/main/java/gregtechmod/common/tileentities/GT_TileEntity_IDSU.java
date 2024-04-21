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

import gregtechmod.common.GT_LanguageManager;
import gregtechmod.common.tileentities.GT_TileEntityMetaID_Machine;
import ic2.api.Direction;
import java.util.HashMap;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.ForgeDirection;

public class GT_TileEntity_IDSU
extends GT_TileEntityMetaID_Machine {
    public static HashMap<Integer, Integer> sEnergyList;
    public int mPlayerHash = 0;

    @Override
    public boolean isFacingValid(int aFacing) {
        return aFacing != this.getFacing();
    }

    @Override
    public boolean isAccessible(EntityPlayer aPlayer) {
        if (this.mPlayerHash == 0 && !this.worldObj.isRemote) {
            this.mPlayerHash = aPlayer.username.hashCode();
        }
        return true;
    }

    @Override
    public boolean isEnetOutput() {
        return this.mPlayerHash != 0;
    }

    @Override
    public boolean isEnetInput() {
        return this.mPlayerHash != 0 && sEnergyList != null;
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
        return 1000000000;
    }

    @Override
    public int maxEUInput() {
        return 2048;
    }

    @Override
    public int maxEUOutput() {
        return 2048;
    }

    @Override
    public int getInventorySlotCount() {
        return 2;
    }

    @Override
    public boolean ownerControl() {
        return true;
    }

    @Override
    public void storeAdditionalData(NBTTagCompound aNBT) {
        aNBT.setInteger("mPlayerHash", this.mPlayerHash);
    }

    @Override
    public void getAdditionalData(NBTTagCompound aNBT) {
        this.mPlayerHash = aNBT.getInteger("mPlayerHash");
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

    @Override
    public void setEnergyVar(int aEU) {
        if (!this.worldObj.isRemote && sEnergyList != null) {
            sEnergyList.remove(this.mPlayerHash);
            sEnergyList.put(this.mPlayerHash, aEU);
        }
    }

    @Override
    public int getEnergyVar() {
        if (!this.worldObj.isRemote && sEnergyList != null) {
            Integer tEU = (Integer)sEnergyList.get(this.mPlayerHash);
            if (tEU == null) {
                tEU = 0;
            }
            return tEU;
        }
        return 0;
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
        return GT_LanguageManager.mNameList1[8];
    }

    @Override
    public int getTexture(int aSide, int aMeta) {
        if (aSide == this.getFacing()) {
            return 18;
        }
        return 22;
    }

    @Override
    public boolean isTeleporterCompatible(Direction side) {
        return true;
    }

    @Override
    public void doEnergyExplosion() {
    }
}

