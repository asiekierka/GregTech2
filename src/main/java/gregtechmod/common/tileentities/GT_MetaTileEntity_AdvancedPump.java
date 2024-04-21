/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.world.ChunkPosition
 *  net.minecraftforge.common.ForgeDirection
 *  net.minecraftforge.liquids.ILiquid
 *  net.minecraftforge.liquids.ILiquidTank
 *  net.minecraftforge.liquids.LiquidContainerRegistry
 *  net.minecraftforge.liquids.LiquidEvent
 *  net.minecraftforge.liquids.LiquidEvent$LiquidDrainingEvent
 *  net.minecraftforge.liquids.LiquidStack
 */
package gregtechmod.common.tileentities;

import gregtechmod.GT_Mod;
import gregtechmod.api.BaseMetaTileEntity;
import gregtechmod.api.MetaTileEntity;
import gregtechmod.common.GT_ModHandler;
import java.util.ArrayList;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.ChunkPosition;
import net.minecraftforge.common.ForgeDirection;
import net.minecraftforge.liquids.ILiquid;
import net.minecraftforge.liquids.ILiquidTank;
import net.minecraftforge.liquids.LiquidContainerRegistry;
import net.minecraftforge.liquids.LiquidEvent;
import net.minecraftforge.liquids.LiquidStack;

public class GT_MetaTileEntity_AdvancedPump
extends MetaTileEntity {
    public LiquidStack mLiquid = new LiquidStack(0, 0, 0);
    public ArrayList mPumpList = new ArrayList();
    public int mPumpedBlockID1 = -1;
    public int mPumpedBlockID2 = -1;

    public GT_MetaTileEntity_AdvancedPump(int aID, String mName, String mNameRegional) {
        super(aID, mName, mNameRegional);
    }

    public GT_MetaTileEntity_AdvancedPump() {
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
    public boolean isValidSlot(int aIndex) {
        return true;
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
        return 128;
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
    public int getInvSize() {
        return 3;
    }

    @Override
    public void onRightclick(EntityPlayer aPlayer) {
        aPlayer.openGui((Object)GT_Mod.instance, 130, this.mBaseMetaTileEntity.worldObj, this.mBaseMetaTileEntity.xCoord, this.mBaseMetaTileEntity.yCoord, this.mBaseMetaTileEntity.zCoord);
    }

    @Override
    public boolean isAccessAllowed(EntityPlayer aPlayer) {
        return true;
    }

    @Override
    public MetaTileEntity newMetaEntity(BaseMetaTileEntity aTileEntity) {
        return new GT_MetaTileEntity_AdvancedPump();
    }

    @Override
    public void saveNBTData(NBTTagCompound aNBT) {
        if (this.mLiquid != null) {
            aNBT.setInteger("mItemCount", this.mLiquid.amount);
            aNBT.setInteger("mItemID", this.mLiquid.itemID);
            aNBT.setInteger("mItemMeta", this.mLiquid.itemMeta);
        }
        aNBT.setInteger("mPumpedBlockID1", this.mPumpedBlockID1);
        aNBT.setInteger("mPumpedBlockID2", this.mPumpedBlockID2);
    }

    @Override
    public void loadNBTData(NBTTagCompound aNBT) {
        this.mLiquid = new LiquidStack(aNBT.getInteger("mItemID"), aNBT.getInteger("mItemCount"), aNBT.getInteger("mItemMeta"));
        this.mPumpedBlockID1 = aNBT.getInteger("mPumpedBlockID1");
        this.mPumpedBlockID2 = aNBT.getInteger("mPumpedBlockID2");
    }

    @Override
    public void onPostTick() {
        if (this.mLiquid == null) {
            this.mLiquid = new LiquidStack(0, 0, 0);
        }
        if (!this.mBaseMetaTileEntity.worldObj.isRemote && this.mBaseMetaTileEntity.mTickTimer % 10L == 0L) {
            ItemStack tOutput;
            if (LiquidContainerRegistry.isEmptyContainer((ItemStack)this.mInventory[1]) && (tOutput = LiquidContainerRegistry.fillLiquidContainer((LiquidStack)this.mLiquid, (ItemStack)this.mInventory[1])) != null && (this.mInventory[2] == null || tOutput.isItemEqual(this.mInventory[2]) && this.mInventory[2].stackSize < tOutput.getMaxStackSize())) {
                tOutput.stackSize = 1;
                this.mLiquid.amount -= LiquidContainerRegistry.getLiquidForFilledItem((ItemStack)tOutput).amount;
                this.mBaseMetaTileEntity.decrStackSize(1, 1);
                if (this.mInventory[2] == null) {
                    this.mInventory[2] = tOutput;
                } else {
                    ++this.mInventory[2].stackSize;
                }
                if (this.mLiquid.amount <= 0) {
                    this.mLiquid.itemID = 0;
                    this.mLiquid.itemMeta = 0;
                    this.mLiquid.amount = 0;
                }
            }
            if (this.mBaseMetaTileEntity.getStoredEnergy() > 2560 && this.mLiquid.amount + 1000 <= this.getCapacity()) {
                boolean tMovedOneDown = false;
                if (this.mBaseMetaTileEntity.mTickTimer % 100L == 0L) {
                    tMovedOneDown = this.moveOneDown();
                }
                if (this.mPumpedBlockID1 <= 0 || this.mPumpedBlockID2 <= 0 || this.mLiquid.itemID <= 0) {
                    this.getFluid(this.mBaseMetaTileEntity.worldObj.getBlockId(this.mBaseMetaTileEntity.xCoord, this.getYOfPumpHead() - 1, this.mBaseMetaTileEntity.zCoord));
                    if (this.mPumpedBlockID1 <= 0 || this.mPumpedBlockID2 <= 0 || this.mLiquid.itemID <= 0) {
                        this.getFluid(this.mBaseMetaTileEntity.worldObj.getBlockId(this.mBaseMetaTileEntity.xCoord, this.getYOfPumpHead(), this.mBaseMetaTileEntity.zCoord + 1));
                    }
                    if (this.mPumpedBlockID1 <= 0 || this.mPumpedBlockID2 <= 0 || this.mLiquid.itemID <= 0) {
                        this.getFluid(this.mBaseMetaTileEntity.worldObj.getBlockId(this.mBaseMetaTileEntity.xCoord, this.getYOfPumpHead(), this.mBaseMetaTileEntity.zCoord - 1));
                    }
                    if (this.mPumpedBlockID1 <= 0 || this.mPumpedBlockID2 <= 0 || this.mLiquid.itemID <= 0) {
                        this.getFluid(this.mBaseMetaTileEntity.worldObj.getBlockId(this.mBaseMetaTileEntity.xCoord + 1, this.getYOfPumpHead(), this.mBaseMetaTileEntity.zCoord));
                    }
                    if (this.mPumpedBlockID1 <= 0 || this.mPumpedBlockID2 <= 0 || this.mLiquid.itemID <= 0) {
                        this.getFluid(this.mBaseMetaTileEntity.worldObj.getBlockId(this.mBaseMetaTileEntity.xCoord - 1, this.getYOfPumpHead(), this.mBaseMetaTileEntity.zCoord));
                    }
                } else if (this.getYOfPumpHead() < this.mBaseMetaTileEntity.yCoord) {
                    if (tMovedOneDown || this.mPumpList.isEmpty() && this.mBaseMetaTileEntity.mTickTimer % 200L == 100L || this.mBaseMetaTileEntity.mTickTimer % 72000L == 100L) {
                        this.mPumpList.clear();
                        int yHead = this.getYOfPumpHead();
                        for (int y = this.mBaseMetaTileEntity.yCoord - 1; this.mPumpList.isEmpty() && y >= yHead; --y) {
                            this.scanForFluid(this.mBaseMetaTileEntity.xCoord, y, this.mBaseMetaTileEntity.zCoord, this.mPumpList, this.mBaseMetaTileEntity.xCoord, this.mBaseMetaTileEntity.zCoord, 64);
                        }
                    }
                    if (!tMovedOneDown && !this.mPumpList.isEmpty()) {
                        this.consumeFluid(((ChunkPosition)this.mPumpList.get((int)(this.mPumpList.size() - 1))).x, ((ChunkPosition)this.mPumpList.get((int)(this.mPumpList.size() - 1))).y, ((ChunkPosition)this.mPumpList.get((int)(this.mPumpList.size() - 1))).z);
                        this.mPumpList.remove(this.mPumpList.size() - 1);
                    }
                }
            }
            this.mBaseMetaTileEntity.mActive = !this.mPumpList.isEmpty();
        }
    }

    private boolean moveOneDown() {
        if (this.mInventory[0] == null || this.mInventory[0].stackSize < 1 || !this.mInventory[0].isItemEqual(GT_ModHandler.getIC2Item("miningPipe", 1))) {
            return false;
        }
        int yHead = this.getYOfPumpHead();
        if (yHead <= 0) {
            return false;
        }
        this.consumeFluid(this.mBaseMetaTileEntity.xCoord, yHead - 1, this.mBaseMetaTileEntity.zCoord);
        if (this.mBaseMetaTileEntity.worldObj.getBlockId(this.mBaseMetaTileEntity.xCoord, yHead - 1, this.mBaseMetaTileEntity.zCoord) != 0) {
            return false;
        }
        if (!this.mBaseMetaTileEntity.worldObj.setBlock(this.mBaseMetaTileEntity.xCoord, yHead - 1, this.mBaseMetaTileEntity.zCoord, GT_ModHandler.getIC2Item((String)"miningPipeTip", (int)1).itemID)) {
            return false;
        }
        if (yHead != this.mBaseMetaTileEntity.yCoord) {
            this.mBaseMetaTileEntity.worldObj.setBlock(this.mBaseMetaTileEntity.xCoord, yHead, this.mBaseMetaTileEntity.zCoord, GT_ModHandler.getIC2Item((String)"miningPipe", (int)1).itemID);
        }
        this.mBaseMetaTileEntity.decrStackSize(0, 1);
        return true;
    }

    private int getYOfPumpHead() {
        int y = this.mBaseMetaTileEntity.yCoord - 1;
        while (this.mBaseMetaTileEntity.worldObj.getBlockId(this.mBaseMetaTileEntity.xCoord, y, this.mBaseMetaTileEntity.zCoord) == GT_ModHandler.getIC2Item((String)"miningPipe", (int)1).itemID) {
            --y;
        }
        if (y == this.mBaseMetaTileEntity.yCoord - 1) {
            if (this.mBaseMetaTileEntity.worldObj.getBlockId(this.mBaseMetaTileEntity.xCoord, y, this.mBaseMetaTileEntity.zCoord) != GT_ModHandler.getIC2Item((String)"miningPipeTip", (int)1).itemID) {
                return y + 1;
            }
        } else if (this.mBaseMetaTileEntity.worldObj.getBlockId(this.mBaseMetaTileEntity.xCoord, y, this.mBaseMetaTileEntity.zCoord) != GT_ModHandler.getIC2Item((String)"miningPipeTip", (int)1).itemID) {
            this.mBaseMetaTileEntity.worldObj.setBlock(this.mBaseMetaTileEntity.xCoord, y, this.mBaseMetaTileEntity.zCoord, GT_ModHandler.getIC2Item((String)"miningPipeTip", (int)1).itemID);
        }
        return y;
    }

    private void scanForFluid(int aX, int aY, int aZ, ArrayList aList, int mX, int mZ, int mDist) {
        boolean pX = this.addIfFluidAndNotAlreadyAdded(aX + 1, aY, aZ, aList);
        boolean nX = this.addIfFluidAndNotAlreadyAdded(aX - 1, aY, aZ, aList);
        boolean pZ = this.addIfFluidAndNotAlreadyAdded(aX, aY, aZ + 1, aList);
        boolean nZ = this.addIfFluidAndNotAlreadyAdded(aX, aY, aZ - 1, aList);
        if (pX && aX < mX + mDist) {
            this.scanForFluid(aX + 1, aY, aZ, aList, mX, mZ, mDist);
        }
        if (nX && aX > mX - mDist) {
            this.scanForFluid(aX - 1, aY, aZ, aList, mX, mZ, mDist);
        }
        if (pZ && aZ < mZ + mDist) {
            this.scanForFluid(aX, aY, aZ + 1, aList, mX, mZ, mDist);
        }
        if (nZ && aZ > mZ - mDist) {
            this.scanForFluid(aX, aY, aZ - 1, aList, mX, mZ, mDist);
        }
        if (this.addIfFluidAndNotAlreadyAdded(aX, aY + 1, aZ, aList) || aX == mX && aZ == mZ && aY < this.mBaseMetaTileEntity.yCoord) {
            this.scanForFluid(aX, aY + 1, aZ, aList, mX, mZ, mDist);
        }
    }

    private boolean addIfFluidAndNotAlreadyAdded(int aX, int aY, int aZ, ArrayList aList) {
        int tID;
        ChunkPosition tCoordinate = new ChunkPosition(aX, aY, aZ);
        if (!(aList.contains(tCoordinate) || this.mPumpedBlockID1 != (tID = this.mBaseMetaTileEntity.worldObj.getBlockId(aX, aY, aZ)) && this.mPumpedBlockID2 != tID)) {
            aList.add(tCoordinate);
            return true;
        }
        return false;
    }

    private void getFluid(int aID) {
        if (aID > 0) {
            int tID;
            if (aID == Block.lavaStill.blockID || aID == Block.lavaMoving.blockID) {
                this.mPumpedBlockID1 = Block.lavaStill.blockID;
                this.mPumpedBlockID2 = Block.lavaMoving.blockID;
                this.mLiquid = new LiquidStack(Block.lavaStill.blockID, 0, 0);
                return;
            }
            if (aID == Block.waterStill.blockID || aID == Block.waterMoving.blockID) {
                this.mPumpedBlockID1 = Block.waterStill.blockID;
                this.mPumpedBlockID2 = Block.waterMoving.blockID;
                this.mLiquid = new LiquidStack(Block.waterStill.blockID, 0, 0);
                return;
            }
            if (Block.blocksList[aID] instanceof ILiquid && (tID = ((ILiquid)Block.blocksList[aID]).stillLiquidId()) > 0) {
                this.mPumpedBlockID1 = aID;
                this.mPumpedBlockID2 = tID;
                this.mLiquid = new LiquidStack(tID, 0, 0);
                return;
            }
        }
        this.mPumpedBlockID1 = -1;
        this.mPumpedBlockID2 = -1;
        this.mLiquid = new LiquidStack(0, 0, 0);
    }

    private boolean consumeFluid(int aX, int aY, int aZ) {
        int tID = this.mBaseMetaTileEntity.worldObj.getBlockId(aX, aY, aZ);
        if (this.mPumpedBlockID1 == tID || this.mPumpedBlockID2 == tID) {
            if (this.mBaseMetaTileEntity.worldObj.getBlockMetadata(aX, aY, aZ) == 0) {
                this.mLiquid.amount += 1000;
                this.mBaseMetaTileEntity.decreaseStoredEnergy(1280, true);
            } else {
                this.mBaseMetaTileEntity.decreaseStoredEnergy(320, true);
            }
            this.mBaseMetaTileEntity.worldObj.setBlockAndMetadata(aX, aY, aZ, 0, 0);
            return true;
        }
        return false;
    }

    @Override
    public int getInvSideIndex(ForgeDirection aSide, int aFacing) {
        if (aSide == ForgeDirection.UP) {
            return 0;
        }
        if (aSide == ForgeDirection.DOWN || aSide == ForgeDirection.NORTH || aSide == ForgeDirection.SOUTH) {
            return 1;
        }
        return 2;
    }

    @Override
    public int getInvSideLength(ForgeDirection aSide, int aFacing) {
        return 1;
    }

    @Override
    public int getTextureIndex(int aSide, int aFacing, boolean aActive, boolean aRedstone) {
        if (aSide == 0) {
            return 110;
        }
        if (aSide == 1) {
            return aActive ? 69 : 68;
        }
        return 109;
    }

    @Override
    protected String getDescription() {
        return "The best way of emptying Oceans!";
    }

    @Override
    public LiquidStack getLiquid() {
        return this.mLiquid;
    }

    @Override
    public int getCapacity() {
        return 16000;
    }

    @Override
    public int fill(LiquidStack resource, boolean doFill) {
        return 0;
    }

    @Override
    public LiquidStack drain(int maxDrain, boolean doDrain) {
        if (this.mLiquid == null || this.mLiquid.itemID <= 0) {
            return null;
        }
        if (this.mLiquid.amount <= 0) {
            return null;
        }
        int used = maxDrain;
        if (this.mLiquid.amount < used) {
            used = this.mLiquid.amount;
        }
        if (doDrain) {
            this.mLiquid.amount -= used;
        }
        LiquidStack drained = new LiquidStack(this.mLiquid.itemID, used, this.mLiquid.itemMeta);
        if (doDrain && this.mBaseMetaTileEntity != null) {
            LiquidEvent.fireEvent((LiquidEvent)new LiquidEvent.LiquidDrainingEvent(drained, this.mBaseMetaTileEntity.worldObj, this.mBaseMetaTileEntity.xCoord, this.mBaseMetaTileEntity.yCoord, this.mBaseMetaTileEntity.zCoord, (ILiquidTank)this));
        }
        return drained;
    }

    @Override
    public int getTankPressure() {
        return 100;
    }
}

