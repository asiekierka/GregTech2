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

import gregtechmod.api.BaseMetaTileEntity;
import gregtechmod.api.MetaTileEntity;
import gregtechmod.common.GT_Utility;
import java.util.ArrayList;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.ForgeDirection;

public class GT_MetaTileEntity_AdvancedTranslocator
extends MetaTileEntity {
    public boolean bOutput = true;
    public boolean bInvertFilter = false;
    public int mInputSide = 0;
    public int mOutputSide = 0;
    public int mSuccess = 0;

    public GT_MetaTileEntity_AdvancedTranslocator(int aID, String mName, String mNameRegional) {
        super(aID, mName, mNameRegional);
    }

    public GT_MetaTileEntity_AdvancedTranslocator() {
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
        return false;
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
        return ForgeDirection.getOrientation((int)this.mBaseMetaTileEntity.getFacing()).getOpposite() == ForgeDirection.getOrientation((int)aSide) || ForgeDirection.getOrientation((int)this.mBaseMetaTileEntity.getFacing()) == ForgeDirection.getOrientation((int)aSide);
    }

    @Override
    public int getMinimumStoredEU() {
        return 2000;
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
        return 10000;
    }

    @Override
    public int maxMJStore() {
        return this.maxEUStore();
    }

    @Override
    public int getInvSize() {
        return 10;
    }

    @Override
    public void onRightclick(EntityPlayer aPlayer) {
        this.mBaseMetaTileEntity.openGUI(aPlayer, 104);
    }

    @Override
    public boolean isAccessAllowed(EntityPlayer aPlayer) {
        return true;
    }

    @Override
    public MetaTileEntity newMetaEntity(BaseMetaTileEntity aTileEntity) {
        return new GT_MetaTileEntity_AdvancedTranslocator();
    }

    @Override
    public void saveNBTData(NBTTagCompound aNBT) {
        aNBT.setBoolean("bOutput", this.bOutput);
        aNBT.setBoolean("bInvertFilter", this.bInvertFilter);
        aNBT.setInteger("mInputSide", this.mInputSide);
        aNBT.setInteger("mOutputSide", this.mOutputSide);
    }

    @Override
    public void loadNBTData(NBTTagCompound aNBT) {
        this.bOutput = aNBT.getBoolean("bOutput");
        this.bInvertFilter = aNBT.getBoolean("bInvertFilter");
        this.mInputSide = aNBT.getInteger("mInputSide");
        this.mOutputSide = aNBT.getInteger("mOutputSide");
    }

    @Override
    public void onPostTick() {
        if (!this.mBaseMetaTileEntity.worldObj.isRemote && this.mBaseMetaTileEntity.getStoredEnergy() >= 1800 && (this.mBaseMetaTileEntity.mTickTimer % 200L == 0L || this.mBaseMetaTileEntity.mTickTimer % 5L == 0L && this.mSuccess > 0 || this.mSuccess >= 20)) {
            --this.mSuccess;
            int xDir = ForgeDirection.getOrientation((int)this.mBaseMetaTileEntity.getFacing()).offsetX;
            int yDir = ForgeDirection.getOrientation((int)this.mBaseMetaTileEntity.getFacing()).offsetY;
            int zDir = ForgeDirection.getOrientation((int)this.mBaseMetaTileEntity.getFacing()).offsetZ;
            TileEntity tTileEntity1 = this.mBaseMetaTileEntity.worldObj.getBlockTileEntity(this.mBaseMetaTileEntity.xCoord + xDir, this.mBaseMetaTileEntity.yCoord + yDir, this.mBaseMetaTileEntity.zCoord + zDir);
            TileEntity tTileEntity2 = this.mBaseMetaTileEntity.worldObj.getBlockTileEntity(this.mBaseMetaTileEntity.xCoord - xDir, this.mBaseMetaTileEntity.yCoord - yDir, this.mBaseMetaTileEntity.zCoord - zDir);
            if (tTileEntity1 != null && tTileEntity2 != null && tTileEntity1 instanceof IInventory && tTileEntity2 instanceof IInventory) {
                ArrayList<ItemStack> tList = new ArrayList<ItemStack>();
                for (int i = 0; i < 9; ++i) {
                    tList.add(this.mInventory[i]);
                }
                int tCost = GT_Utility.moveOneItemStack((IInventory)tTileEntity1, (IInventory)tTileEntity2, ForgeDirection.getOrientation((int)this.mInputSide), ForgeDirection.getOrientation((int)this.mOutputSide), tList, this.bInvertFilter, 64, 1, 64, 1) * (this.mInventory[0] == null && this.mInventory[1] == null && this.mInventory[2] == null && this.mInventory[3] == null && this.mInventory[4] == null && this.mInventory[5] == null && this.mInventory[6] == null && this.mInventory[7] == null && this.mInventory[8] == null ? 1 : 2);
                if (tCost > 0) {
                    this.mBaseMetaTileEntity.decreaseStoredEnergy(tCost, true);
                    this.mSuccess = 30;
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
        return 0;
    }

    @Override
    protected String getDescription() {
        return "No matter how often you click that dang Button, the second Facing WON'T CHANGE!!!";
    }

    @Override
    public int getTextureIndex(int aSide, int aFacing, boolean aActive, boolean aRedstone) {
        if (aSide == aFacing) {
            return 112 + (aRedstone ? 8 : 0);
        }
        if (ForgeDirection.getOrientation((int)aSide).getOpposite() == ForgeDirection.getOrientation((int)aFacing)) {
            return 113 + (aRedstone ? 8 : 0);
        }
        int tIndex = 129 + (aRedstone ? 8 : 0);
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

