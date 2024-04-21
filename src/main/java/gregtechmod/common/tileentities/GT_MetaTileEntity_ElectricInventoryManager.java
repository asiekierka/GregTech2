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
import java.util.ArrayList;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.ForgeDirection;

public class GT_MetaTileEntity_ElectricInventoryManager
extends MetaTileEntity {
    public int[] mSlotRange = new int[4];
    public boolean mWorkedLastTick = false;

    public GT_MetaTileEntity_ElectricInventoryManager(int aID, String mName, String mNameRegional) {
        super(aID, mName, mNameRegional);
    }

    public GT_MetaTileEntity_ElectricInventoryManager() {
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
    public int maxEUInput() {
        return 128;
    }

    @Override
    public int maxEUOutput() {
        return 32;
    }

    @Override
    public int maxEUPulses() {
        return 4;
    }

    @Override
    public int maxEUStore() {
        return 100000;
    }

    @Override
    public int maxMJStore() {
        return this.maxEUStore();
    }

    @Override
    public void onRightclick(EntityPlayer aPlayer) {
        aPlayer.openGui((Object)GT_Mod.instance, 129, this.mBaseMetaTileEntity.worldObj, this.mBaseMetaTileEntity.xCoord, this.mBaseMetaTileEntity.yCoord, this.mBaseMetaTileEntity.zCoord);
    }

    @Override
    public boolean isValidSlot(int aIndex) {
        return aIndex < 3;
    }

    @Override
    public boolean isInputFacing(short aSide) {
        return !this.isOutputFacing(aSide);
    }

    @Override
    public boolean isOutputFacing(short aSide) {
        for (int i = 0; i < this.mSlotRange.length; ++i) {
            if (aSide != this.getRangeDirection(i) || !this.getRangeEnergy(i)) continue;
            return true;
        }
        return false;
    }

    @Override
    public int getMinimumStoredEU() {
        return 50000;
    }

    @Override
    public int getInvSize() {
        return 16;
    }

    @Override
    public boolean isAccessAllowed(EntityPlayer aPlayer) {
        return true;
    }

    @Override
    public MetaTileEntity newMetaEntity(BaseMetaTileEntity aTileEntity) {
        return new GT_MetaTileEntity_ElectricInventoryManager();
    }

    @Override
    public void saveNBTData(NBTTagCompound aNBT) {
        aNBT.setInteger("mSlotRange0", this.mSlotRange[0]);
        aNBT.setInteger("mSlotRange1", this.mSlotRange[1]);
        aNBT.setInteger("mSlotRange2", this.mSlotRange[2]);
        aNBT.setInteger("mSlotRange3", this.mSlotRange[3]);
    }

    @Override
    public void loadNBTData(NBTTagCompound aNBT) {
        this.mSlotRange[0] = aNBT.getInteger("mSlotRange0");
        this.mSlotRange[1] = aNBT.getInteger("mSlotRange1");
        this.mSlotRange[2] = aNBT.getInteger("mSlotRange2");
        this.mSlotRange[3] = aNBT.getInteger("mSlotRange3");
    }

    public void iterateRangeDirection(int aIndex) {
        this.mSlotRange[aIndex] = this.mSlotRange[aIndex] & 0xFFFFFFF8 | ((this.mSlotRange[aIndex] & 7) + 1) % 6;
    }

    public void switchRangeEnergy(int aIndex) {
        this.mSlotRange[aIndex] = this.mSlotRange[aIndex] & 0xFFFFFFF7 | ((this.mSlotRange[aIndex] & 8) > 0 ? 0 : 8);
        this.mBaseMetaTileEntity.issueEnetUpdate();
    }

    public void iterateSlot1Direction(int aIndex) {
        this.mSlotRange[aIndex] = this.mSlotRange[aIndex] & 0xFFFFFF8F | (((this.mSlotRange[aIndex] & 0x70) >> 4) + 1) % 6 << 4;
    }

    public void iterateSlot2Direction(int aIndex) {
        this.mSlotRange[aIndex] = this.mSlotRange[aIndex] & 0xFFFFFC7F | (((this.mSlotRange[aIndex] & 0x380) >> 7) + 1) % 6 << 7;
    }

    public void iterateSlot3Direction(int aIndex) {
        this.mSlotRange[aIndex] = this.mSlotRange[aIndex] & 0xFFFFE3FF | (((this.mSlotRange[aIndex] & 0x1C00) >> 10) + 1) % 6 << 10;
    }

    public void switchSlot1InOut(int aIndex) {
        this.mSlotRange[aIndex] = this.mSlotRange[aIndex] & 0xFFFFDFFF | ((this.mSlotRange[aIndex] & 0x2000) > 0 ? 0 : 8192);
    }

    public void switchSlot2InOut(int aIndex) {
        this.mSlotRange[aIndex] = this.mSlotRange[aIndex] & 0xFFFFBFFF | ((this.mSlotRange[aIndex] & 0x4000) > 0 ? 0 : 16384);
    }

    public void switchSlot3InOut(int aIndex) {
        this.mSlotRange[aIndex] = this.mSlotRange[aIndex] & 0xFFFF7FFF | ((this.mSlotRange[aIndex] & 0x8000) > 0 ? 0 : 32768);
    }

    public int getRangeDirection(int aIndex) {
        return this.mSlotRange[aIndex] & 7;
    }

    public int getSlot1Direction(int aIndex) {
        return (this.mSlotRange[aIndex] & 0x70) >> 4;
    }

    public int getSlot2Direction(int aIndex) {
        return (this.mSlotRange[aIndex] & 0x380) >> 7;
    }

    public int getSlot3Direction(int aIndex) {
        return (this.mSlotRange[aIndex] & 0x1C00) >> 10;
    }

    public boolean getRangeEnergy(int aIndex) {
        return (this.mSlotRange[aIndex] & 8) > 0;
    }

    public boolean getSlot1InOut(int aIndex) {
        return (this.mSlotRange[aIndex] & 0x2000) > 0;
    }

    public boolean getSlot2InOut(int aIndex) {
        return (this.mSlotRange[aIndex] & 0x4000) > 0;
    }

    public boolean getSlot3InOut(int aIndex) {
        return (this.mSlotRange[aIndex] & 0x8000) > 0;
    }

    @Override
    public void onPostTick() {
        if (!this.mBaseMetaTileEntity.worldObj.isRemote && this.mBaseMetaTileEntity.getStoredEnergy() >= 5000 && (this.mBaseMetaTileEntity.mTickTimer % 100L == 0L || this.mWorkedLastTick || this.mBaseMetaTileEntity.mInventoryChanged)) {
            this.mWorkedLastTick = false;
            TileEntity[] tTileEntities = new TileEntity[]{this.mBaseMetaTileEntity.worldObj.getBlockTileEntity(this.mBaseMetaTileEntity.xCoord, this.mBaseMetaTileEntity.yCoord - 1, this.mBaseMetaTileEntity.zCoord), this.mBaseMetaTileEntity.worldObj.getBlockTileEntity(this.mBaseMetaTileEntity.xCoord, this.mBaseMetaTileEntity.yCoord + 1, this.mBaseMetaTileEntity.zCoord), this.mBaseMetaTileEntity.worldObj.getBlockTileEntity(this.mBaseMetaTileEntity.xCoord, this.mBaseMetaTileEntity.yCoord, this.mBaseMetaTileEntity.zCoord - 1), this.mBaseMetaTileEntity.worldObj.getBlockTileEntity(this.mBaseMetaTileEntity.xCoord, this.mBaseMetaTileEntity.yCoord, this.mBaseMetaTileEntity.zCoord + 1), this.mBaseMetaTileEntity.worldObj.getBlockTileEntity(this.mBaseMetaTileEntity.xCoord - 1, this.mBaseMetaTileEntity.yCoord, this.mBaseMetaTileEntity.zCoord), this.mBaseMetaTileEntity.worldObj.getBlockTileEntity(this.mBaseMetaTileEntity.xCoord + 1, this.mBaseMetaTileEntity.yCoord, this.mBaseMetaTileEntity.zCoord), null, null};
            int tCost = 0;
            for (int i = 0; i < 4; ++i) {
                if (tTileEntities[this.getRangeDirection(i)] == null || !(tTileEntities[this.getRangeDirection(i)] instanceof IInventory)) continue;
                ArrayList<ItemStack> tList = new ArrayList<ItemStack>();
                tList.add(null);
                ItemStack tStack = this.mInventory[3 + i * 3 + 0];
                if (tStack == null) {
                    tCost = this.getSlot1InOut(i) ? (tCost += 5 * GT_Utility.moveOneItemStack((IInventory)this.mBaseMetaTileEntity, (IInventory)tTileEntities[this.getRangeDirection(i)], ForgeDirection.getOrientation((int)this.getSlot1Direction(i)), ForgeDirection.getOrientation((int)this.getSlot1Direction(i)), null, false, 64, 1, 64, 1)) : (tCost += 5 * GT_Utility.moveOneItemStack((IInventory)tTileEntities[this.getRangeDirection(i)], (IInventory)this.mBaseMetaTileEntity, ForgeDirection.getOrientation((int)this.getSlot1Direction(i)), ForgeDirection.getOrientation((int)this.getSlot1Direction(i)), null, false, 64, 1, 64, 1));
                } else {
                    tList.set(0, tStack);
                    tCost = this.getSlot1InOut(i) ? (tCost += 5 * GT_Utility.moveOneItemStack((IInventory)this.mBaseMetaTileEntity, (IInventory)tTileEntities[this.getRangeDirection(i)], ForgeDirection.getOrientation((int)this.getSlot1Direction(i)), ForgeDirection.getOrientation((int)this.getSlot1Direction(i)), tList, false, tStack.stackSize, 1, 64, 1)) : (tCost += 5 * GT_Utility.moveOneItemStack((IInventory)tTileEntities[this.getRangeDirection(i)], (IInventory)this.mBaseMetaTileEntity, ForgeDirection.getOrientation((int)this.getSlot1Direction(i)), ForgeDirection.getOrientation((int)this.getSlot1Direction(i)), tList, false, tStack.stackSize, 1, 64, 1));
                }
                tStack = this.mInventory[3 + i * 3 + 1];
                if (tStack == null) {
                    tCost = this.getSlot2InOut(i) ? (tCost += 5 * GT_Utility.moveOneItemStack((IInventory)this.mBaseMetaTileEntity, (IInventory)tTileEntities[this.getRangeDirection(i)], ForgeDirection.getOrientation((int)this.getSlot2Direction(i)), ForgeDirection.getOrientation((int)this.getSlot2Direction(i)), null, false, 64, 1, 64, 1)) : (tCost += 5 * GT_Utility.moveOneItemStack((IInventory)tTileEntities[this.getRangeDirection(i)], (IInventory)this.mBaseMetaTileEntity, ForgeDirection.getOrientation((int)this.getSlot2Direction(i)), ForgeDirection.getOrientation((int)this.getSlot2Direction(i)), null, false, 64, 1, 64, 1));
                } else {
                    tList.set(0, tStack);
                    tCost = this.getSlot2InOut(i) ? (tCost += 5 * GT_Utility.moveOneItemStack((IInventory)this.mBaseMetaTileEntity, (IInventory)tTileEntities[this.getRangeDirection(i)], ForgeDirection.getOrientation((int)this.getSlot2Direction(i)), ForgeDirection.getOrientation((int)this.getSlot2Direction(i)), tList, false, tStack.stackSize, 1, 64, 1)) : (tCost += 5 * GT_Utility.moveOneItemStack((IInventory)tTileEntities[this.getRangeDirection(i)], (IInventory)this.mBaseMetaTileEntity, ForgeDirection.getOrientation((int)this.getSlot2Direction(i)), ForgeDirection.getOrientation((int)this.getSlot2Direction(i)), tList, false, tStack.stackSize, 1, 64, 1));
                }
                tStack = this.mInventory[3 + i * 3 + 2];
                if (tStack == null) {
                    if (this.getSlot3InOut(i)) {
                        tCost += 5 * GT_Utility.moveOneItemStack((IInventory)this.mBaseMetaTileEntity, (IInventory)tTileEntities[this.getRangeDirection(i)], ForgeDirection.getOrientation((int)this.getSlot3Direction(i)), ForgeDirection.getOrientation((int)this.getSlot3Direction(i)), null, false, 64, 1, 64, 1);
                        continue;
                    }
                    tCost += 5 * GT_Utility.moveOneItemStack((IInventory)tTileEntities[this.getRangeDirection(i)], (IInventory)this.mBaseMetaTileEntity, ForgeDirection.getOrientation((int)this.getSlot3Direction(i)), ForgeDirection.getOrientation((int)this.getSlot3Direction(i)), null, false, 64, 1, 64, 1);
                    continue;
                }
                tList.set(0, tStack);
                if (this.getSlot3InOut(i)) {
                    tCost += 5 * GT_Utility.moveOneItemStack((IInventory)this.mBaseMetaTileEntity, (IInventory)tTileEntities[this.getRangeDirection(i)], ForgeDirection.getOrientation((int)this.getSlot3Direction(i)), ForgeDirection.getOrientation((int)this.getSlot3Direction(i)), tList, false, tStack.stackSize, 1, 64, 1);
                    continue;
                }
                tCost += 5 * GT_Utility.moveOneItemStack((IInventory)tTileEntities[this.getRangeDirection(i)], (IInventory)this.mBaseMetaTileEntity, ForgeDirection.getOrientation((int)this.getSlot3Direction(i)), ForgeDirection.getOrientation((int)this.getSlot3Direction(i)), tList, false, tStack.stackSize, 1, 64, 1);
            }
            if (tCost > 0) {
                this.mWorkedLastTick = true;
                this.mBaseMetaTileEntity.decreaseStoredEnergy(tCost, true);
            }
        }
    }

    @Override
    protected String getDescription() {
        return "It's simpler than you think. I promise.";
    }

    @Override
    public int getInvSideIndex(ForgeDirection aSide, int aFacing) {
        return 0;
    }

    @Override
    public int getInvSideLength(ForgeDirection aSide, int aFacing) {
        return 3;
    }

    @Override
    public int getTextureIndex(int aSide, int aFacing, boolean aActive, boolean aRedstone) {
        switch (ForgeDirection.getOrientation((int)aSide)) {
            case DOWN: {
                return 113 + (aRedstone ? 8 : 0);
            }
            case UP: {
                return 112 + (aRedstone ? 8 : 0);
            }
            case NORTH: {
                return 116 + (aRedstone ? 8 : 0);
            }
            case SOUTH: {
                return 213 + (aRedstone ? 8 : 0);
            }
            case WEST: {
                return 212 + (aRedstone ? 8 : 0);
            }
            case EAST: {
                return 117 + (aRedstone ? 8 : 0);
            }
        }
        return 0;
    }
}

