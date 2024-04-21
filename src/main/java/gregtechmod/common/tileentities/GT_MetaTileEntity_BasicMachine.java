/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.inventory.IInventory
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraftforge.common.ForgeDirection
 */
package gregtechmod.common.tileentities;

import gregtechmod.api.MetaTileEntity;
import gregtechmod.common.GT_OreDictUnificator;
import gregtechmod.common.GT_Utility;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.ForgeDirection;

public abstract class GT_MetaTileEntity_BasicMachine
extends MetaTileEntity {
    public boolean bOutput = false;
    public boolean bOutputBlocked = false;
    public boolean bItemTransfer = true;
    public boolean bSeperatedInputs = this.hasTwoSeperateInputs();
    public int mMainFacing = -1;
    public int mProgresstime = 0;
    public int mMaxProgresstime = 0;
    public int mEUt = 0;
    public ItemStack mOutputItem1;
    public ItemStack mOutputItem2;

    public GT_MetaTileEntity_BasicMachine(int aID, String mName, String mNameRegional) {
        super(aID, mName, mNameRegional);
    }

    public GT_MetaTileEntity_BasicMachine() {
    }

    @Override
    public boolean isTransformerUpgradable() {
        return true;
    }

    @Override
    public boolean isOverclockerUpgradable() {
        return true;
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
        return aIndex > 0;
    }

    @Override
    public boolean isFacingValid(int aFacing) {
        return this.mMainFacing >= 0 || aFacing > 1;
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
        if (aSide == this.mBaseMetaTileEntity.getFacing()) {
            return false;
        }
        return !this.isOutputFacing(aSide);
    }

    @Override
    public boolean isOutputFacing(short aSide) {
        if (aSide == this.mBaseMetaTileEntity.getFacing()) {
            return false;
        }
        return this.bOutput ? ForgeDirection.getOrientation((int)this.mBaseMetaTileEntity.getFacing()).getOpposite() == ForgeDirection.getOrientation((int)aSide) : false;
    }

    @Override
    public int getMinimumStoredEU() {
        return 1000;
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
        return 2000;
    }

    @Override
    public int maxMJStore() {
        return this.maxEUStore();
    }

    @Override
    public int getInvSize() {
        return 6;
    }

    @Override
    public int dechargerSlotStartIndex() {
        return 5;
    }

    @Override
    public int dechargerSlotCount() {
        return 1;
    }

    @Override
    public void onRightclick(EntityPlayer aPlayer) {
    }

    @Override
    public boolean isAccessAllowed(EntityPlayer aPlayer) {
        return true;
    }

    @Override
    public void saveNBTData(NBTTagCompound aNBT) {
        NBTTagCompound tNBT;
        aNBT.setBoolean("bOutput", this.bOutput);
        aNBT.setBoolean("bItemTransfer", this.bItemTransfer);
        aNBT.setBoolean("bSeperatedInputs", this.bSeperatedInputs);
        aNBT.setInteger("mEUt", this.mEUt);
        aNBT.setInteger("mMainFacing", this.mMainFacing);
        aNBT.setInteger("mProgresstime", this.mProgresstime);
        aNBT.setInteger("mMaxProgresstime", this.mMaxProgresstime);
        if (this.mOutputItem1 != null) {
            tNBT = new NBTTagCompound();
            this.mOutputItem1.writeToNBT(tNBT);
            aNBT.setTag("mOutputItem1", (NBTBase)tNBT);
        }
        if (this.mOutputItem2 != null) {
            tNBT = new NBTTagCompound();
            this.mOutputItem2.writeToNBT(tNBT);
            aNBT.setTag("mOutputItem2", (NBTBase)tNBT);
        }
    }

    @Override
    public void loadNBTData(NBTTagCompound aNBT) {
        NBTTagCompound tNBT2;
        this.bOutput = aNBT.getBoolean("bOutput");
        this.bItemTransfer = aNBT.getBoolean("bItemTransfer");
        this.bSeperatedInputs = aNBT.getBoolean("bSeperatedInputs");
        this.mEUt = aNBT.getInteger("mEUt");
        this.mMainFacing = aNBT.getInteger("mMainFacing");
        this.mProgresstime = aNBT.getInteger("mProgresstime");
        this.mMaxProgresstime = aNBT.getInteger("mMaxProgresstime");
        NBTTagCompound tNBT1 = (NBTTagCompound)aNBT.getTag("mOutputItem1");
        if (tNBT1 != null) {
            this.mOutputItem1 = ItemStack.loadItemStackFromNBT((NBTTagCompound)tNBT1);
        }
        if ((tNBT2 = (NBTTagCompound)aNBT.getTag("mOutputItem2")) != null) {
            this.mOutputItem2 = ItemStack.loadItemStackFromNBT((NBTTagCompound)tNBT2);
        }
    }

    @Override
    public void onPostTick() {
        if (this.mMainFacing == -1 && this.mBaseMetaTileEntity.getFacing() > 1) {
            this.mMainFacing = this.mBaseMetaTileEntity.getFacing();
            this.mBaseMetaTileEntity.issueTextureUpdate();
        }
        if (!this.mBaseMetaTileEntity.worldObj.isRemote) {
            int tCost;
            int zDir;
            int yDir;
            int xDir;
            TileEntity tTileEntity2;
            if (this.mMaxProgresstime > 0 && (this.mProgresstime < 0 || this.mBaseMetaTileEntity.decreaseStoredEnergy(this.mEUt * (int)Math.pow(4.0, this.mBaseMetaTileEntity.mOverclockers), false)) && (this.mProgresstime += (int)Math.pow(2.0, this.mBaseMetaTileEntity.mOverclockers)) >= this.mMaxProgresstime) {
                this.addOutputProducts();
                this.mOutputItem1 = null;
                this.mOutputItem2 = null;
                this.mProgresstime = 0;
                this.mMaxProgresstime = 0;
            }
            if (this.mMaxProgresstime <= 0 && this.mBaseMetaTileEntity.getStoredEnergy() > 900) {
                this.checkRecipe();
                if (this.mInventory[1] != null && this.mInventory[1].stackSize <= 0) {
                    this.mInventory[1] = null;
                }
                if (this.mInventory[2] != null && this.mInventory[2].stackSize <= 0) {
                    this.mInventory[2] = null;
                }
                this.mOutputItem1 = GT_OreDictUnificator.get(this.mOutputItem1);
                this.mOutputItem2 = GT_OreDictUnificator.get(this.mOutputItem2);
            }
            if (this.bItemTransfer && (this.mBaseMetaTileEntity.mActive && this.mMaxProgresstime <= 0 || this.mBaseMetaTileEntity.mTickTimer % 1200L == 0L || this.bOutputBlocked && this.mBaseMetaTileEntity.mTickTimer % 20L == 0L) && ForgeDirection.getOrientation((int)this.mBaseMetaTileEntity.getFacing()).getOpposite() != ForgeDirection.getOrientation((int)this.mMainFacing) && this.mBaseMetaTileEntity.getStoredEnergy() >= 500 && (this.mInventory[3] != null || this.mInventory[4] != null) && (tTileEntity2 = this.mBaseMetaTileEntity.worldObj.getBlockTileEntity(this.mBaseMetaTileEntity.xCoord - (xDir = ForgeDirection.getOrientation((int)this.mBaseMetaTileEntity.getFacing()).offsetX), this.mBaseMetaTileEntity.yCoord - (yDir = ForgeDirection.getOrientation((int)this.mBaseMetaTileEntity.getFacing()).offsetY), this.mBaseMetaTileEntity.zCoord - (zDir = ForgeDirection.getOrientation((int)this.mBaseMetaTileEntity.getFacing()).offsetZ))) != null && tTileEntity2 instanceof IInventory && (tCost = GT_Utility.moveOneItemStack((IInventory)this.mBaseMetaTileEntity, (IInventory)tTileEntity2, ForgeDirection.getOrientation((int)this.mBaseMetaTileEntity.getFacing()).getOpposite(), ForgeDirection.getOrientation((int)this.mBaseMetaTileEntity.getFacing()), null, false, 64, 1, 64, 1)) > 0) {
                this.mBaseMetaTileEntity.decreaseStoredEnergy(tCost, true);
                tCost = GT_Utility.moveOneItemStack((IInventory)this.mBaseMetaTileEntity, (IInventory)tTileEntity2, ForgeDirection.getOrientation((int)this.mBaseMetaTileEntity.getFacing()).getOpposite(), ForgeDirection.getOrientation((int)this.mBaseMetaTileEntity.getFacing()), null, false, 64, 1, 64, 1);
                if (tCost > 0) {
                    this.mBaseMetaTileEntity.decreaseStoredEnergy(tCost, true);
                }
            }
            this.mBaseMetaTileEntity.mActive = this.mMaxProgresstime > 0;
        }
    }

    @Override
    public int getInvSideIndex(ForgeDirection aSide, int aFacing) {
        if (ForgeDirection.getOrientation((int)aFacing).getOpposite() == aSide) {
            return 3;
        }
        if (this.bSeperatedInputs && (aSide == ForgeDirection.UP || aSide == ForgeDirection.DOWN)) {
            return 2;
        }
        return 1;
    }

    @Override
    public int getInvSideLength(ForgeDirection aSide, int aFacing) {
        if (ForgeDirection.getOrientation((int)this.mMainFacing) == aSide) {
            return 0;
        }
        if (ForgeDirection.getOrientation((int)aFacing).getOpposite() == aSide) {
            return 2;
        }
        return this.bSeperatedInputs ? 1 : 2;
    }

    @Override
    public void onValueUpdate(short aValue) {
        this.mMainFacing = aValue;
    }

    @Override
    public short getUpdateData() {
        return (short)this.mMainFacing;
    }

    @Override
    public int getTextureIndex(int aSide, int aFacing, boolean aActive, boolean aRedstone) {
        if (this.mMainFacing < 2) {
            if (aSide == aFacing) {
                if (aActive) {
                    return this.getFrontFacingActive();
                }
                return this.getFrontFacingInactive();
            }
            if (aSide == 0) {
                return 32;
            }
            if (aSide == 1) {
                if (aActive) {
                    return this.getTopFacingActive();
                }
                return this.getTopFacingInactive();
            }
            if (aActive) {
                return this.getSideFacingActive();
            }
            return this.getSideFacingInactive();
        }
        if (aSide == this.mMainFacing) {
            if (aActive) {
                return this.getFrontFacingActive();
            }
            return this.getFrontFacingInactive();
        }
        if (ForgeDirection.getOrientation((int)aSide).getOpposite() == ForgeDirection.getOrientation((int)aFacing)) {
            if (aSide == 0) {
                return 38;
            }
            if (aSide == 1) {
                return 79;
            }
            return 36;
        }
        if (aSide == 0) {
            return 32;
        }
        if (aSide == 1) {
            if (aActive) {
                return this.getTopFacingActive();
            }
            return this.getTopFacingInactive();
        }
        if (aActive) {
            return this.getSideFacingActive();
        }
        return this.getSideFacingInactive();
    }

    private void addOutputProducts() {
        if (this.mOutputItem1 != null) {
            if (this.mInventory[3] == null) {
                this.mInventory[3] = this.mOutputItem1.copy();
            } else if (this.mInventory[3].isItemEqual(this.mOutputItem1)) {
                this.mInventory[3].stackSize = Math.min(this.mOutputItem1.getMaxStackSize(), this.mOutputItem1.stackSize + this.mInventory[3].stackSize);
            }
        }
        if (this.mOutputItem2 != null) {
            if (this.mInventory[4] == null) {
                this.mInventory[4] = this.mOutputItem2.copy();
            } else if (this.mInventory[4].isItemEqual(this.mOutputItem2)) {
                this.mInventory[4].stackSize = Math.min(this.mOutputItem2.getMaxStackSize(), this.mOutputItem2.stackSize + this.mInventory[4].stackSize);
            }
        }
    }

    protected boolean spaceForOutput(ItemStack aOutput1, ItemStack aOutput2) {
        if ((this.mInventory[3] == null || aOutput1 == null || this.mInventory[3].stackSize + aOutput1.stackSize <= this.mInventory[3].getMaxStackSize() && this.mInventory[3].isItemEqual(aOutput1)) && (this.mInventory[4] == null || aOutput2 == null || this.mInventory[4].stackSize + aOutput2.stackSize <= this.mInventory[4].getMaxStackSize() && this.mInventory[4].isItemEqual(aOutput2))) {
            return true;
        }
        this.bOutputBlocked = true;
        return false;
    }

    public void checkRecipe() {
    }

    public boolean hasTwoSeperateInputs() {
        return false;
    }

    public int getSideFacingActive() {
        return 40;
    }

    public int getSideFacingInactive() {
        return 40;
    }

    public int getFrontFacingActive() {
        return 40;
    }

    public int getFrontFacingInactive() {
        return 40;
    }

    public int getTopFacingActive() {
        return 29;
    }

    public int getTopFacingInactive() {
        return 29;
    }

    @Override
    protected String getDescription() {
        return "*Forgot to add Desciption. This is a Bug!*";
    }
}

