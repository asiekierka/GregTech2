/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.inventory.IInventory
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraftforge.common.ForgeDirection
 */
package gregtechmod.common.tileentities;

import gregtechmod.GT_Mod;
import gregtechmod.api.BaseMetaTileEntity;
import gregtechmod.api.MetaTileEntity;
import gregtechmod.common.GT_Utility;
import gregtechmod.common.tileentities.GT_MetaTileEntity_ElectricBufferSmall;
import java.util.ArrayList;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.ForgeDirection;

public class GT_MetaTileEntity_ElectricRegulatorAdvanced
extends GT_MetaTileEntity_ElectricBufferSmall {
    public int[] mTargetSlots = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};

    public GT_MetaTileEntity_ElectricRegulatorAdvanced(int aID, String mName, String mNameRegional) {
        super(aID, mName, mNameRegional);
    }

    public GT_MetaTileEntity_ElectricRegulatorAdvanced() {
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
    public int maxEUInput() {
        return 128;
    }

    @Override
    public int maxEUPulses() {
        return 4;
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
        aPlayer.openGui((Object)GT_Mod.instance, 123, this.mBaseMetaTileEntity.worldObj, this.mBaseMetaTileEntity.xCoord, this.mBaseMetaTileEntity.yCoord, this.mBaseMetaTileEntity.zCoord);
    }

    @Override
    public boolean isValidSlot(int aIndex) {
        return aIndex < 9;
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
    public int getInvSize() {
        return 19;
    }

    @Override
    public boolean isAccessAllowed(EntityPlayer aPlayer) {
        return true;
    }

    @Override
    public MetaTileEntity newMetaEntity(BaseMetaTileEntity aTileEntity) {
        return new GT_MetaTileEntity_ElectricRegulatorAdvanced();
    }

    @Override
    public void saveNBTData(NBTTagCompound aNBT) {
        super.saveNBTData(aNBT);
        aNBT.setInteger("mTargetSlot1", this.mTargetSlots[0]);
        aNBT.setInteger("mTargetSlot2", this.mTargetSlots[1]);
        aNBT.setInteger("mTargetSlot3", this.mTargetSlots[2]);
        aNBT.setInteger("mTargetSlot4", this.mTargetSlots[3]);
        aNBT.setInteger("mTargetSlot5", this.mTargetSlots[4]);
        aNBT.setInteger("mTargetSlot6", this.mTargetSlots[5]);
        aNBT.setInteger("mTargetSlot7", this.mTargetSlots[6]);
        aNBT.setInteger("mTargetSlot8", this.mTargetSlots[7]);
        aNBT.setInteger("mTargetSlot9", this.mTargetSlots[8]);
    }

    @Override
    public void loadNBTData(NBTTagCompound aNBT) {
        super.loadNBTData(aNBT);
        this.mTargetSlots[0] = aNBT.getInteger("mTargetSlot1");
        this.mTargetSlots[1] = aNBT.getInteger("mTargetSlot2");
        this.mTargetSlots[2] = aNBT.getInteger("mTargetSlot3");
        this.mTargetSlots[3] = aNBT.getInteger("mTargetSlot4");
        this.mTargetSlots[4] = aNBT.getInteger("mTargetSlot5");
        this.mTargetSlots[5] = aNBT.getInteger("mTargetSlot6");
        this.mTargetSlots[6] = aNBT.getInteger("mTargetSlot7");
        this.mTargetSlots[7] = aNBT.getInteger("mTargetSlot8");
        this.mTargetSlots[8] = aNBT.getInteger("mTargetSlot9");
    }

    @Override
    public void onPostTick() {
        if (!this.mBaseMetaTileEntity.worldObj.isRemote && this.mBaseMetaTileEntity.getStoredEnergy() >= 500 && this.mBaseMetaTileEntity.mTickTimer % 10L == 0L) {
            int xDir = ForgeDirection.getOrientation((int)this.mBaseMetaTileEntity.getFacing()).offsetX;
            int yDir = ForgeDirection.getOrientation((int)this.mBaseMetaTileEntity.getFacing()).offsetY;
            int zDir = ForgeDirection.getOrientation((int)this.mBaseMetaTileEntity.getFacing()).offsetZ;
            BaseMetaTileEntity tTileEntity1 = this.mBaseMetaTileEntity;
            TileEntity tTileEntity2 = this.mBaseMetaTileEntity.worldObj.getBlockTileEntity(this.mBaseMetaTileEntity.xCoord - xDir, this.mBaseMetaTileEntity.yCoord - yDir, this.mBaseMetaTileEntity.zCoord - zDir);
            if (tTileEntity1 != null && tTileEntity2 != null && tTileEntity1 instanceof IInventory && tTileEntity2 instanceof IInventory) {
                ArrayList<ItemStack> tList = new ArrayList<ItemStack>();
                tList.add(null);
                int tCosts = 0;
                for (int i = 0; i < 9 && tCosts == 0; ++i) {
                    if (this.mInventory[i + 9] == null) continue;
                    tList.set(0, this.mInventory[i + 9]);
                    tCosts = GT_Utility.moveOneItemStackIntoSlot((IInventory)tTileEntity1, (IInventory)tTileEntity2, ForgeDirection.getOrientation((int)this.mBaseMetaTileEntity.getFacing()).getOpposite(), this.mTargetSlots[i], tList, false, this.mInventory[i + 9].stackSize, this.mInventory[i + 9].stackSize, 64, 1) * 3;
                    if (tCosts <= 0) continue;
                    this.mBaseMetaTileEntity.decreaseStoredEnergy(tCosts, true);
                }
            }
        }
    }

    @Override
    public int getInvSideIndex(ForgeDirection aSide, int aFacing) {
        return 0;
    }

    @Override
    public int getInvSideLength(ForgeDirection aSide, int aFacing) {
        return 9;
    }

    @Override
    protected String getDescription() {
        return "Lets you save up to 8 additional Machines when used correctly!";
    }

    @Override
    public int getTextureIndex(int aSide, int aFacing, boolean aActive, boolean aRedstone) {
        if (aSide == aFacing) {
            return 135 + (aRedstone ? 8 : 0);
        }
        if (ForgeDirection.getOrientation((int)aSide).getOpposite() == ForgeDirection.getOrientation((int)aFacing)) {
            return 113 + (aRedstone ? 8 : 0);
        }
        int tIndex = 135 + (aRedstone ? 8 : 0);
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

