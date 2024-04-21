/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.ItemStack
 *  net.minecraftforge.liquids.ILiquidTank
 *  net.minecraftforge.liquids.LiquidContainerRegistry
 *  net.minecraftforge.liquids.LiquidEvent
 *  net.minecraftforge.liquids.LiquidEvent$LiquidDrainingEvent
 *  net.minecraftforge.liquids.LiquidEvent$LiquidFillingEvent
 *  net.minecraftforge.liquids.LiquidStack
 */
package gregtechmod.common.tileentities;

import gregtechmod.GT_Mod;
import gregtechmod.api.BaseMetaTileEntity;
import gregtechmod.api.MetaTileEntity;
import gregtechmod.common.GT_ModHandler;
import gregtechmod.common.GT_Utility;
import gregtechmod.common.tileentities.GT_MetaTileEntity_BasicTank;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.liquids.ILiquidTank;
import net.minecraftforge.liquids.LiquidContainerRegistry;
import net.minecraftforge.liquids.LiquidEvent;
import net.minecraftforge.liquids.LiquidStack;

public class GT_MetaTileEntity_FusionInjector
extends GT_MetaTileEntity_BasicTank {
    public BaseMetaTileEntity mFusionComputer;

    public GT_MetaTileEntity_FusionInjector(int aID, String mName, String mNameRegional) {
        super(aID, mName, mNameRegional);
    }

    public GT_MetaTileEntity_FusionInjector() {
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
    public void onRightclick(EntityPlayer aPlayer) {
        aPlayer.openGui((Object)GT_Mod.instance, 144, this.mBaseMetaTileEntity.worldObj, this.mBaseMetaTileEntity.xCoord, this.mBaseMetaTileEntity.yCoord, this.mBaseMetaTileEntity.zCoord);
    }

    @Override
    public boolean isAccessAllowed(EntityPlayer aPlayer) {
        return true;
    }

    @Override
    public MetaTileEntity newMetaEntity(BaseMetaTileEntity aTileEntity) {
        return new GT_MetaTileEntity_FusionInjector();
    }

    @Override
    public void onPostTick() {
        if (this.mLiquid == null || this.mLiquid.itemID != 0 && this.mLiquid.amount == 0) {
            this.mLiquid = new LiquidStack(0, 0, 0);
        }
        if (!this.mBaseMetaTileEntity.worldObj.isRemote && this.mBaseMetaTileEntity.mTickTimer % 20L == 0L) {
            ItemStack tOutput;
            this.mInventory[2] = this.mLiquid.itemID <= 0 || this.mLiquid.amount <= 0 ? null : this.mLiquid.asItemStack();
            if (this.mInventory[0] != null) {
                boolean temp;
                if (this.mInventory[0] != null && this.mLiquid.itemID != 0 && LiquidContainerRegistry.isFilledContainer((ItemStack)this.mInventory[0]) && LiquidContainerRegistry.containsLiquid((ItemStack)this.mInventory[0], (LiquidStack)this.mLiquid) && LiquidContainerRegistry.getLiquidForFilledItem((ItemStack)this.mInventory[0]).amount + this.mLiquid.amount <= this.getCapacity()) {
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
                } else if (this.mInventory[0] != null && this.mLiquid.itemID == 0 && LiquidContainerRegistry.isFilledContainer((ItemStack)this.mInventory[0]) && LiquidContainerRegistry.getLiquidForFilledItem((ItemStack)this.mInventory[0]).amount <= this.getCapacity()) {
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
            this.mBaseMetaTileEntity.mActive = this.mFusionComputer == null ? false : this.mFusionComputer.mActive;
        }
    }

    public ItemStack getMaterial() {
        if (this.mInventory[0] == null) {
            ItemStack tStack = LiquidContainerRegistry.fillLiquidContainer((LiquidStack)this.mLiquid, (ItemStack)GT_ModHandler.getEmptyCell(1));
            if (tStack == null) {
                return null;
            }
            tStack.stackSize = this.mLiquid.amount / LiquidContainerRegistry.getLiquidForFilledItem((ItemStack)tStack).amount;
            return tStack;
        }
        return this.mInventory[0];
    }

    public boolean consumeMaterial(ItemStack aStack) {
        if (aStack == null) {
            return false;
        }
        if (this.mLiquid == null || !LiquidContainerRegistry.containsLiquid((ItemStack)aStack, (LiquidStack)this.mLiquid) || LiquidContainerRegistry.getLiquidForFilledItem((ItemStack)aStack).amount * aStack.stackSize > this.mLiquid.amount) {
            if (this.mInventory[0] != null && this.mInventory[0].isItemEqual(aStack) && this.mInventory[0].stackSize >= aStack.stackSize) {
                ItemStack tOutputCells = GT_ModHandler.getEmptyCell(GT_ModHandler.getCapsuleCellContainerCount(aStack));
                if (tOutputCells != null && tOutputCells.stackSize > 0) {
                    if (this.mInventory[1] == null) {
                        this.mInventory[1] = tOutputCells;
                    } else if (this.mInventory[1].isItemEqual(tOutputCells)) {
                        this.mInventory[1].stackSize = Math.min(this.mInventory[1].getMaxStackSize(), this.mInventory[1].stackSize + tOutputCells.stackSize);
                    }
                }
                this.mBaseMetaTileEntity.decrStackSize(0, aStack.stackSize);
                return true;
            }
        } else {
            this.mLiquid.amount -= LiquidContainerRegistry.getLiquidForFilledItem((ItemStack)aStack).amount * aStack.stackSize;
            return true;
        }
        return false;
    }

    @Override
    public int getTextureIndex(int aSide, int aFacing, boolean aActive, boolean aRedstone) {
        if (aSide == 0 || aSide == 1) {
            return 16;
        }
        return aActive ? 20 : 19;
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
    protected String getDescription() {
        return "To inject your fusable Liquids into the Coils";
    }

    @Override
    public int fill(LiquidStack resource, boolean doFill) {
        if (resource == null || resource.itemID <= 0) {
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
        if (this.mLiquid.amount <= 0) {
            this.mLiquid = null;
        }
        if (doDrain && this.mBaseMetaTileEntity != null) {
            LiquidEvent.fireEvent((LiquidEvent)new LiquidEvent.LiquidDrainingEvent(drained, this.mBaseMetaTileEntity.worldObj, this.mBaseMetaTileEntity.xCoord, this.mBaseMetaTileEntity.yCoord, this.mBaseMetaTileEntity.zCoord, (ILiquidTank)this));
        }
        return drained;
    }

    @Override
    public int getTankPressure() {
        return -100;
    }
}

