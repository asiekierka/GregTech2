/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.inventory.IInventory
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemFood
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraftforge.common.ForgeDirection
 */
package gregtechmod.common.tileentities;

import gregtechmod.api.BaseMetaTileEntity;
import gregtechmod.api.GT_Recipe;
import gregtechmod.api.MetaTileEntity;
import gregtechmod.common.GT_OreDictUnificator;
import gregtechmod.common.GT_Utility;
import gregtechmod.common.tileentities.GT_MetaTileEntity_ElectricBufferSmall;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.ForgeDirection;

public class GT_MetaTileEntity_ElectricTypeSorter
extends GT_MetaTileEntity_ElectricBufferSmall {
    public int mTargetDirection;
    public int oTargetDirection;
    public int mMode = 0;
    public static String[] sTypeList = new String[]{"ore", "gem", "nugget", "dustSmall", "dust", "ingot", "block", "treeLeaves", "treeSapling", "log", "plank", "", "", "beeComb"};

    public GT_MetaTileEntity_ElectricTypeSorter(int aID, String mName, String mNameRegional) {
        super(aID, mName, mNameRegional);
    }

    public GT_MetaTileEntity_ElectricTypeSorter() {
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
    public int maxEUStore() {
        return 10000;
    }

    @Override
    public int maxMJStore() {
        return this.maxEUStore();
    }

    @Override
    public int getMinimumStoredEU() {
        return 2000;
    }

    @Override
    public boolean isValidSlot(int aIndex) {
        return aIndex < 1;
    }

    @Override
    public boolean isOutputFacing(short aSide) {
        return ForgeDirection.getOrientation((int)this.mBaseMetaTileEntity.getFacing()).getOpposite() == ForgeDirection.getOrientation((int)aSide) || ForgeDirection.getOrientation((int)this.mTargetDirection) == ForgeDirection.getOrientation((int)aSide);
    }

    @Override
    public int getInvSize() {
        return 2;
    }

    @Override
    public void onRightclick(EntityPlayer aPlayer) {
        this.mBaseMetaTileEntity.openGUI(aPlayer, 139);
    }

    @Override
    public MetaTileEntity newMetaEntity(BaseMetaTileEntity aTileEntity) {
        return new GT_MetaTileEntity_ElectricTypeSorter();
    }

    @Override
    public void saveNBTData(NBTTagCompound aNBT) {
        super.saveNBTData(aNBT);
        aNBT.setInteger("mMode", this.mMode);
        aNBT.setInteger("mTargetDirection", this.mTargetDirection);
    }

    @Override
    public void loadNBTData(NBTTagCompound aNBT) {
        super.loadNBTData(aNBT);
        this.mMode = aNBT.getInteger("mMode");
        this.mTargetDirection = aNBT.getInteger("mTargetDirection");
        this.oTargetDirection = -1;
    }

    public void switchModeForward() {
        ++this.mMode;
        this.switchMode();
    }

    public void switchModeBackward() {
        --this.mMode;
        this.switchMode();
    }

    public void switchMode() {
        if (this.mMode >= sTypeList.length) {
            this.mMode = 0;
        }
        if (this.mMode < 0) {
            this.mMode = sTypeList.length - 1;
        }
    }

    @Override
    public void onPostTick() {
        if (!this.mBaseMetaTileEntity.worldObj.isRemote && this.mBaseMetaTileEntity.getStoredEnergy() >= 1000 && (this.mBaseMetaTileEntity.mTickTimer % 20L == 0L || this.mSuccess > 0 && this.mBaseMetaTileEntity.mTickTimer % 5L == 0L)) {
            if (this.mInventory[0] != null) {
                BaseMetaTileEntity tTileEntity1 = this.mBaseMetaTileEntity;
                int xDir = ForgeDirection.getOrientation((int)this.mBaseMetaTileEntity.getFacing()).offsetX;
                int yDir = ForgeDirection.getOrientation((int)this.mBaseMetaTileEntity.getFacing()).offsetY;
                int zDir = ForgeDirection.getOrientation((int)this.mBaseMetaTileEntity.getFacing()).offsetZ;
                TileEntity tTileEntity2 = this.mBaseMetaTileEntity.worldObj.getBlockTileEntity(this.mBaseMetaTileEntity.xCoord - xDir, this.mBaseMetaTileEntity.yCoord - yDir, this.mBaseMetaTileEntity.zCoord - zDir);
                xDir = ForgeDirection.getOrientation((int)this.mTargetDirection).getOpposite().offsetX;
                yDir = ForgeDirection.getOrientation((int)this.mTargetDirection).getOpposite().offsetY;
                zDir = ForgeDirection.getOrientation((int)this.mTargetDirection).getOpposite().offsetZ;
                TileEntity tTileEntity3 = this.mBaseMetaTileEntity.worldObj.getBlockTileEntity(this.mBaseMetaTileEntity.xCoord - xDir, this.mBaseMetaTileEntity.yCoord - yDir, this.mBaseMetaTileEntity.zCoord - zDir);
                int tPrice = 0;
                if (tTileEntity1 != null && tTileEntity3 != null && tTileEntity1 instanceof IInventory && tTileEntity3 instanceof IInventory && (this.mMode == 11 && this.mInventory[0].getItem() instanceof ItemFood || this.mMode == 12 && GT_Recipe.findEqualGrinderRecipeIndex(this.mInventory[0], new ItemStack(Item.bucketWater, 1)) > -1 || GT_OreDictUnificator.isItemStackInstanceOf(this.mInventory[0], sTypeList[this.mMode], true))) {
                    tPrice = GT_Utility.moveOneItemStack((IInventory)tTileEntity1, (IInventory)tTileEntity3, ForgeDirection.getOrientation((int)0), ForgeDirection.getOrientation((int)this.mTargetDirection).getOpposite(), null, false, 64, 1, 64, 1) * 3;
                    this.mBaseMetaTileEntity.decreaseStoredEnergy(tPrice, true);
                }
                if (tPrice <= 0 && tTileEntity1 != null && tTileEntity2 != null && tTileEntity1 instanceof IInventory && tTileEntity2 instanceof IInventory) {
                    tPrice = GT_Utility.moveOneItemStack((IInventory)tTileEntity1, (IInventory)tTileEntity2, ForgeDirection.getOrientation((int)0), ForgeDirection.getOrientation((int)this.mBaseMetaTileEntity.getFacing()), null, false, 64, 1, 64, 1) * 2;
                    this.mBaseMetaTileEntity.decreaseStoredEnergy(tPrice, true);
                }
                if (tPrice > 0) {
                    this.mSuccess = 30;
                }
            }
            this.mBaseMetaTileEntity.mRedstone = this.bInvert;
            if (this.bRedstoneIfFull) {
                this.mBaseMetaTileEntity.decreaseStoredEnergy(1, true);
                if (this.mInventory[0] != null) {
                    boolean bl = this.mBaseMetaTileEntity.mRedstone = !this.bInvert;
                }
            }
        }
        if (!this.mBaseMetaTileEntity.worldObj.isRemote && this.mTargetDirection != this.oTargetDirection && this.mBaseMetaTileEntity.mTickTimer > 50L) {
            this.oTargetDirection = this.mTargetDirection;
            this.mBaseMetaTileEntity.issueTextureUpdate();
        }
    }

    @Override
    public int getInvSideIndex(ForgeDirection aSide, int aFacing) {
        return 0;
    }

    @Override
    public int getInvSideLength(ForgeDirection aSide, int aFacing) {
        return 1;
    }

    @Override
    protected String getDescription() {
        return "Like the regular Sorter, but with special Item Types instead of Filter Items";
    }

    @Override
    public int getTextureIndex(int aSide, int aFacing, boolean aActive, boolean aRedstone) {
        if (aSide == this.mTargetDirection) {
            return 116 + (aRedstone ? 8 : 0);
        }
        if (aSide == aFacing) {
            return 133 + (aRedstone ? 8 : 0);
        }
        if (ForgeDirection.getOrientation((int)aSide).getOpposite() == ForgeDirection.getOrientation((int)aFacing)) {
            return 113 + (aRedstone ? 8 : 0);
        }
        int tIndex = 133 + (aRedstone ? 8 : 0);
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

    @Override
    public void onValueUpdate(short aValue) {
        this.mTargetDirection = aValue;
    }

    @Override
    public short getUpdateData() {
        return (short)this.mTargetDirection;
    }
}

