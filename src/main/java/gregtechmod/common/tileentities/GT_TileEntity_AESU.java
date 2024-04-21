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
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.ForgeDirection;

public class GT_TileEntity_AESU
extends GT_TileEntityMetaID_Machine {
    public int mOutput = 32;

    @Override
    public int getTier() {
        return 4;
    }

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
        return 100000000;
    }

    @Override
    public int maxEUInput() {
        return 2048;
    }

    @Override
    public int maxEUOutput() {
        return this.mOutput;
    }

    @Override
    public int getInventorySlotCount() {
        return 3;
    }

    @Override
    public void storeAdditionalData(NBTTagCompound aNBT) {
        aNBT.setInteger("mOutput", this.mOutput);
    }

    @Override
    public void getAdditionalData(NBTTagCompound aNBT) {
        this.mOutput = aNBT.getInteger("mOutput");
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
    public boolean isValidSlot(int aIndex) {
        return aIndex != 2;
    }

    @Override
    public String getInvName() {
        return GT_LanguageManager.mNameList1[9];
    }

    @Override
    public int getTexture(int aSide, int aMeta) {
        if (aSide == this.getFacing()) {
            return 18;
        }
        return 16;
    }

    @Override
    public boolean isTeleporterCompatible(Direction side) {
        return true;
    }
}

