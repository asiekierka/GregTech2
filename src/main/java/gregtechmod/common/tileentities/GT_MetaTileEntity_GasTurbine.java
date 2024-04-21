/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.ItemStack
 *  net.minecraftforge.liquids.ILiquidTank
 *  net.minecraftforge.liquids.LiquidContainerRegistry
 *  net.minecraftforge.liquids.LiquidEvent
 *  net.minecraftforge.liquids.LiquidEvent$LiquidFillingEvent
 *  net.minecraftforge.liquids.LiquidStack
 */
package gregtechmod.common.tileentities;

import gregtechmod.GT_Mod;
import gregtechmod.api.BaseMetaTileEntity;
import gregtechmod.api.GT_Recipe;
import gregtechmod.api.MetaTileEntity;
import gregtechmod.common.GT_Utility;
import gregtechmod.common.tileentities.GT_MetaTileEntity_BasicTank;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.liquids.ILiquidTank;
import net.minecraftforge.liquids.LiquidContainerRegistry;
import net.minecraftforge.liquids.LiquidEvent;
import net.minecraftforge.liquids.LiquidStack;

public class GT_MetaTileEntity_GasTurbine
extends GT_MetaTileEntity_BasicTank {
    public GT_MetaTileEntity_GasTurbine(int aID, String mName, String mNameRegional) {
        super(aID, mName, mNameRegional);
    }

    public GT_MetaTileEntity_GasTurbine() {
    }

    @Override
    public boolean isSimpleMachine() {
        return false;
    }

    @Override
    public boolean isValidSlot(int aIndex) {
        return aIndex < 2;
    }

    @Override
    public boolean isFacingValid(int aFacing) {
        return false;
    }

    @Override
    public boolean isEnetOutput() {
        return true;
    }

    @Override
    public boolean isOutputFacing(short aSide) {
        return true;
    }

    @Override
    public int maxEUOutput() {
        return 16;
    }

    @Override
    public int maxEUStore() {
        return 1000000;
    }

    @Override
    public void onRightclick(EntityPlayer aPlayer) {
        aPlayer.openGui((Object)GT_Mod.instance, 118, this.mBaseMetaTileEntity.worldObj, this.mBaseMetaTileEntity.xCoord, this.mBaseMetaTileEntity.yCoord, this.mBaseMetaTileEntity.zCoord);
    }

    @Override
    public boolean isAccessAllowed(EntityPlayer aPlayer) {
        return true;
    }

    @Override
    public MetaTileEntity newMetaEntity(BaseMetaTileEntity aTileEntity) {
        return new GT_MetaTileEntity_GasTurbine();
    }

    @Override
    public void onPostTick() {
        block25: {
            ItemStack tOutput;
            block28: {
                block26: {
                    block27: {
                        if (this.mLiquid == null || this.mLiquid.itemID != 0 && this.mLiquid.amount == 0) {
                            this.mLiquid = new LiquidStack(0, 0, 0);
                        }
                        if (this.mBaseMetaTileEntity.worldObj.isRemote || this.mBaseMetaTileEntity.mTickTimer % 10L != 0L) break block25;
                        if (this.mLiquid.itemID > 0 && this.mLiquid.amount > 0) break block26;
                        if (this.mBaseMetaTileEntity.getStoredEnergy() >= this.maxEUOutput()) break block27;
                        this.mInventory[2] = null;
                        break block28;
                    }
                    if (this.mInventory[2] != null) break block28;
                    this.mInventory[2] = new ItemStack((Block)Block.fire, 1);
                    break block28;
                }
                this.mInventory[2] = this.mLiquid.asItemStack();
                while (this.mBaseMetaTileEntity.getStoredEnergy() < this.maxEUOutput() * 10 && this.mLiquid.amount > 0) {
                    if (!this.mBaseMetaTileEntity.increaseStoredEU(this.getFuelValue(this.mLiquid), true)) continue;
                    --this.mLiquid.amount;
                }
            }
            if (this.mInventory[0] != null && this.mBaseMetaTileEntity.getStoredEnergy() <= 100) {
                boolean temp;
                for (int i = 0; i < GT_Recipe.sTurbineFuels.size(); ++i) {
                    if (!this.mInventory[0].isItemEqual(((GT_Recipe)GT_Recipe.sTurbineFuels.get((int)i)).mInput1)) continue;
                    if (LiquidContainerRegistry.getLiquidForFilledItem((ItemStack)this.mInventory[0]) != null) break;
                    if (((GT_Recipe)GT_Recipe.sTurbineFuels.get((int)i)).mOutput1 != null) {
                        if (this.mInventory[1] == null) {
                            if (!this.mBaseMetaTileEntity.increaseStoredEU(((GT_Recipe)GT_Recipe.sTurbineFuels.get((int)i)).mStartEU * 1000, true)) break;
                            this.mBaseMetaTileEntity.decrStackSize(0, 1);
                            this.mInventory[1] = ((GT_Recipe)GT_Recipe.sTurbineFuels.get((int)i)).mOutput1.copy();
                            break;
                        }
                        if (!this.mInventory[1].isItemEqual(((GT_Recipe)GT_Recipe.sTurbineFuels.get((int)i)).mOutput1) || this.mInventory[1].stackSize + ((GT_Recipe)GT_Recipe.sTurbineFuels.get((int)i)).mOutput1.stackSize > this.mInventory[1].getMaxStackSize() || !this.mBaseMetaTileEntity.increaseStoredEU(((GT_Recipe)GT_Recipe.sTurbineFuels.get((int)i)).mStartEU * 1000, true)) break;
                        this.mBaseMetaTileEntity.decrStackSize(0, 1);
                        this.mInventory[1].stackSize += ((GT_Recipe)GT_Recipe.sTurbineFuels.get((int)i)).mOutput1.stackSize;
                        break;
                    }
                    if (!this.mBaseMetaTileEntity.increaseStoredEU(((GT_Recipe)GT_Recipe.sTurbineFuels.get((int)i)).mStartEU * 1000, true)) break;
                    this.mBaseMetaTileEntity.decrStackSize(0, 1);
                    break;
                }
                if (this.mInventory[0] != null && this.mLiquid.itemID != 0 && LiquidContainerRegistry.isFilledContainer((ItemStack)this.mInventory[0]) && LiquidContainerRegistry.containsLiquid((ItemStack)this.mInventory[0], (LiquidStack)this.mLiquid) && this.isValidFuel(LiquidContainerRegistry.getLiquidForFilledItem((ItemStack)this.mInventory[0])) && LiquidContainerRegistry.getLiquidForFilledItem((ItemStack)this.mInventory[0]).amount + this.mLiquid.amount <= this.getCapacity()) {
                    temp = true;
                    ItemStack tContainer = GT_Utility.getContainerForFilledItem(this.mInventory[0]);
                    if (null != tContainer) {
                        if (this.mInventory[1] == null) {
                            this.mInventory[1] = tContainer.copy();
                        } else if (this.mInventory[1].isItemEqual(tContainer) && this.mInventory[1].stackSize + tContainer.stackSize <= tContainer.getMaxStackSize()) {
                            this.mInventory[1].stackSize += tContainer.stackSize;
                        } else {
                            temp = false;
                        }
                    }
                    if (temp) {
                        this.mLiquid.amount += LiquidContainerRegistry.getLiquidForFilledItem((ItemStack)this.mInventory[0]).amount;
                        this.mBaseMetaTileEntity.decrStackSize(0, 1);
                    }
                } else if (this.mInventory[0] != null && this.mLiquid.itemID == 0 && LiquidContainerRegistry.isFilledContainer((ItemStack)this.mInventory[0]) && this.isValidFuel(LiquidContainerRegistry.getLiquidForFilledItem((ItemStack)this.mInventory[0])) && LiquidContainerRegistry.getLiquidForFilledItem((ItemStack)this.mInventory[0]).amount <= this.getCapacity()) {
                    temp = true;
                    ItemStack tContainer = GT_Utility.getContainerForFilledItem(this.mInventory[0]);
                    if (null != tContainer) {
                        if (this.mInventory[1] == null) {
                            this.mInventory[1] = tContainer.copy();
                        } else if (this.mInventory[1].isItemEqual(tContainer) && this.mInventory[1].stackSize + tContainer.stackSize <= tContainer.getMaxStackSize()) {
                            this.mInventory[1].stackSize += tContainer.stackSize;
                        } else {
                            temp = false;
                        }
                    }
                    if (temp) {
                        LiquidStack tLiquid = LiquidContainerRegistry.getLiquidForFilledItem((ItemStack)this.mInventory[0]);
                        this.mLiquid.itemID = tLiquid.itemID;
                        this.mLiquid.itemMeta = tLiquid.itemMeta;
                        this.mLiquid.amount = tLiquid.amount;
                        this.mBaseMetaTileEntity.decrStackSize(0, 1);
                    }
                }
            }
            if (LiquidContainerRegistry.isEmptyContainer((ItemStack)this.mInventory[0]) && (tOutput = LiquidContainerRegistry.fillLiquidContainer((LiquidStack)this.mLiquid, (ItemStack)this.mInventory[0])) != null && (this.mInventory[1] == null || tOutput.isItemEqual(this.mInventory[1]) && this.mInventory[1].stackSize < tOutput.getMaxStackSize())) {
                tOutput.stackSize = 1;
                this.mLiquid.amount -= LiquidContainerRegistry.getLiquidForFilledItem((ItemStack)tOutput).amount;
                this.mBaseMetaTileEntity.decrStackSize(0, 1);
                if (this.mInventory[1] == null) {
                    this.mInventory[1] = tOutput;
                } else {
                    ++this.mInventory[1].stackSize;
                }
                if (this.mLiquid.amount <= 0) {
                    this.mLiquid.itemID = 0;
                    this.mLiquid.itemMeta = 0;
                    this.mLiquid.amount = 0;
                }
            }
            this.mBaseMetaTileEntity.mActive = this.mBaseMetaTileEntity.getStoredEnergy() >= this.maxEUOutput();
        }
    }

    public boolean isValidFuel(LiquidStack aLiquid) {
        return this.getFuelValue(aLiquid) > 0;
    }

    public int getFuelValue(LiquidStack aLiquid) {
        if (aLiquid == null) {
            return 0;
        }
        for (int i = 0; i < GT_Recipe.sTurbineFuels.size(); ++i) {
            LiquidStack tLiquid = LiquidContainerRegistry.getLiquidForFilledItem((ItemStack)((GT_Recipe)GT_Recipe.sTurbineFuels.get((int)i)).mInput1);
            if (tLiquid == null || !aLiquid.isLiquidEqual(tLiquid)) continue;
            return ((GT_Recipe)GT_Recipe.sTurbineFuels.get((int)i)).mStartEU;
        }
        return 0;
    }

    @Override
    public int getTextureIndex(int aSide, int aFacing, boolean aActive, boolean aRedstone) {
        if (aSide == 0) {
            return 82;
        }
        if (aSide == 1) {
            return 81;
        }
        return 40;
    }

    @Override
    protected String getDescription() {
        return "Generate Power from Flatulence, uhh I mean Methane";
    }

    @Override
    public LiquidStack getLiquid() {
        return this.mLiquid;
    }

    @Override
    public int getCapacity() {
        return 10000;
    }

    @Override
    public int fill(LiquidStack resource, boolean doFill) {
        if (resource == null || resource.itemID <= 0 || !this.isValidFuel(resource)) {
            return 0;
        }
        if (this.mLiquid == null || this.mLiquid.itemID <= 0) {
            if (resource.amount <= this.getCapacity()) {
                if (doFill) {
                    this.mLiquid = resource.copy();
                }
                return resource.amount;
            }
            if (doFill) {
                this.mLiquid = resource.copy();
                this.mLiquid.amount = this.getCapacity();
                if (this.mBaseMetaTileEntity != null) {
                    LiquidEvent.fireEvent((LiquidEvent)new LiquidEvent.LiquidFillingEvent(this.mLiquid, this.mBaseMetaTileEntity.worldObj, this.mBaseMetaTileEntity.xCoord, this.mBaseMetaTileEntity.yCoord, this.mBaseMetaTileEntity.zCoord, (ILiquidTank)this));
                }
            }
            return this.getCapacity();
        }
        if (!this.mLiquid.isLiquidEqual(resource)) {
            return 0;
        }
        int space = this.getCapacity() - this.mLiquid.amount;
        if (resource.amount <= space) {
            if (doFill) {
                this.mLiquid.amount += resource.amount;
            }
            return resource.amount;
        }
        if (doFill) {
            this.mLiquid.amount = this.getCapacity();
        }
        return space;
    }

    @Override
    public LiquidStack drain(int maxDrain, boolean doDrain) {
        return null;
    }

    @Override
    public int getTankPressure() {
        return -100;
    }
}

