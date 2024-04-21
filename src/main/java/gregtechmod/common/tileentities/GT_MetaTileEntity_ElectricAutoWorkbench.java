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
import gregtechmod.common.GT_ModHandler;
import gregtechmod.common.GT_OreDictUnificator;
import gregtechmod.common.GT_Utility;
import java.util.ArrayList;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.ForgeDirection;

public class GT_MetaTileEntity_ElectricAutoWorkbench
extends MetaTileEntity {
    public int mMode = 0;
    public int mCurrentSlot = 0;
    public int mThroughPut = 0;
    public int mTicksUntilNextUpdate = 20;
    public boolean mLastCraftSuccessful = false;

    public GT_MetaTileEntity_ElectricAutoWorkbench(int aID, String mName, String mNameRegional) {
        super(aID, mName, mNameRegional);
    }

    public GT_MetaTileEntity_ElectricAutoWorkbench() {
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
        return aIndex < 19;
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
        return ForgeDirection.getOrientation((int)this.mBaseMetaTileEntity.getFacing()).getOpposite() == ForgeDirection.getOrientation((int)aSide);
    }

    @Override
    public int getMinimumStoredEU() {
        return 3000;
    }

    @Override
    public int maxEUInput() {
        return 32;
    }

    @Override
    public int maxEUOutput() {
        return this.mThroughPut % 2 == 0 ? 32 : 0;
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
        return 30;
    }

    @Override
    public void onRightclick(EntityPlayer aPlayer) {
        aPlayer.openGui((Object)GT_Mod.instance, 100, this.mBaseMetaTileEntity.worldObj, this.mBaseMetaTileEntity.xCoord, this.mBaseMetaTileEntity.yCoord, this.mBaseMetaTileEntity.zCoord);
    }

    @Override
    public boolean isAccessAllowed(EntityPlayer aPlayer) {
        return true;
    }

    @Override
    public MetaTileEntity newMetaEntity(BaseMetaTileEntity aTileEntity) {
        return new GT_MetaTileEntity_ElectricAutoWorkbench();
    }

    @Override
    public void saveNBTData(NBTTagCompound aNBT) {
        aNBT.setInteger("mMode", this.mMode);
        aNBT.setInteger("mThroughPut", this.mThroughPut);
    }

    @Override
    public void loadNBTData(NBTTagCompound aNBT) {
        this.mMode = aNBT.getInteger("mMode");
        this.mThroughPut = aNBT.getInteger("mThroughPut");
    }

    public void switchModeForward() {
        this.mMode = (this.mMode + 1) % 8;
        this.switchMode();
    }

    public void switchModeBackward() {
        --this.mMode;
        if (this.mMode < 0) {
            this.mMode = 7;
        }
        this.switchMode();
    }

    private void switchMode() {
        this.mInventory[28] = null;
    }

    public void switchThrough() {
        this.mThroughPut = (this.mThroughPut + 1) % 4;
    }

    @Override
    protected String getDescription() {
        return "Automatic Crafting Table Mk III";
    }

    @Override
    public void onPostTick() {
        if (!this.mBaseMetaTileEntity.worldObj.isRemote && this.mBaseMetaTileEntity.getStoredEnergy() >= (this.mMode == 5 || this.mMode == 6 ? 128 : 2048) && --this.mTicksUntilNextUpdate < 1) {
            this.mTicksUntilNextUpdate = 32;
            if (this.mInventory[18] == null) {
                ItemStack[] tRecipe = new ItemStack[9];
                ItemStack tTempStack = null;
                ItemStack tOutput = null;
                if (this.mInventory[8] != null && this.mThroughPut < 2 && this.mMode != 0) {
                    if (this.mInventory[18] == null) {
                        this.mInventory[18] = this.mInventory[8];
                        this.mInventory[8] = null;
                    }
                } else {
                    int i;
                    if (!this.mLastCraftSuccessful) {
                        this.mCurrentSlot = (this.mCurrentSlot + 1) % 18;
                        for (i = 0; i < 17 && this.mInventory[this.mCurrentSlot] == null; ++i) {
                            this.mCurrentSlot = (this.mCurrentSlot + 1) % 18;
                        }
                    }
                    block0 : switch (this.mMode) {
                        case 0: {
                            if (this.mInventory[this.mCurrentSlot] != null && !this.isItemTypeExcluded(this.mInventory[this.mCurrentSlot])) {
                                if (this.mInventory[18] != null || this.mThroughPut >= 2 || this.mCurrentSlot >= 8) break;
                                this.mInventory[18] = this.mInventory[this.mCurrentSlot];
                                this.mInventory[this.mCurrentSlot] = null;
                                this.mTicksUntilNextUpdate = 1;
                                break;
                            }
                            for (i = 0; i < 9; ++i) {
                                tRecipe[i] = this.mInventory[i + 19];
                                if (tRecipe[i] == null) continue;
                                tRecipe[i] = tRecipe[i].copy();
                                tRecipe[i].stackSize = 1;
                            }
                            break;
                        }
                        case 1: {
                            if (this.isItemTypeExcluded(this.mInventory[this.mCurrentSlot])) {
                                if (this.mInventory[18] != null || this.mThroughPut >= 2) break;
                                this.mInventory[18] = this.mInventory[this.mCurrentSlot];
                                this.mInventory[this.mCurrentSlot] = null;
                                this.mTicksUntilNextUpdate = 1;
                                break;
                            }
                            tTempStack = this.mInventory[this.mCurrentSlot].copy();
                            tTempStack.stackSize = 1;
                            tRecipe[0] = tTempStack;
                            if (GT_ModHandler.getRecipeOutput(tRecipe) != null) break;
                            tRecipe[1] = tTempStack;
                            tRecipe[3] = tTempStack;
                            tRecipe[4] = tTempStack;
                            if (GT_ModHandler.getRecipeOutput(tRecipe) != null) break;
                            tRecipe[2] = tTempStack;
                            tRecipe[5] = tTempStack;
                            tRecipe[6] = tTempStack;
                            tRecipe[7] = tTempStack;
                            tRecipe[8] = tTempStack;
                            if (GT_ModHandler.getRecipeOutput(tRecipe) != null || this.mInventory[18] != null) break;
                            this.mInventory[18] = this.mInventory[this.mCurrentSlot];
                            this.mInventory[this.mCurrentSlot] = null;
                            this.mTicksUntilNextUpdate = 1;
                            break;
                        }
                        case 2: {
                            if (this.isItemTypeExcluded(this.mInventory[this.mCurrentSlot])) {
                                if (this.mInventory[18] != null || this.mThroughPut >= 2) break;
                                this.mInventory[18] = this.mInventory[this.mCurrentSlot];
                                this.mInventory[this.mCurrentSlot] = null;
                                this.mTicksUntilNextUpdate = 1;
                                break;
                            }
                            tTempStack = this.mInventory[this.mCurrentSlot].copy();
                            tTempStack.stackSize = 1;
                            tRecipe[0] = tTempStack;
                            if (GT_ModHandler.getRecipeOutput(tRecipe) != null || this.mInventory[18] != null) break;
                            this.mInventory[18] = this.mInventory[this.mCurrentSlot];
                            this.mInventory[this.mCurrentSlot] = null;
                            this.mTicksUntilNextUpdate = 1;
                            break;
                        }
                        case 3: {
                            if (this.isItemTypeExcluded(this.mInventory[this.mCurrentSlot])) {
                                if (this.mInventory[18] != null || this.mThroughPut >= 2) break;
                                this.mInventory[18] = this.mInventory[this.mCurrentSlot];
                                this.mInventory[this.mCurrentSlot] = null;
                                this.mTicksUntilNextUpdate = 1;
                                break;
                            }
                            tTempStack = this.mInventory[this.mCurrentSlot].copy();
                            tTempStack.stackSize = 1;
                            tRecipe[0] = tTempStack;
                            tRecipe[1] = tTempStack;
                            tRecipe[3] = tTempStack;
                            tRecipe[4] = tTempStack;
                            if (GT_ModHandler.getRecipeOutput(tRecipe) != null || this.mInventory[18] != null) break;
                            this.mInventory[18] = this.mInventory[this.mCurrentSlot];
                            this.mInventory[this.mCurrentSlot] = null;
                            this.mTicksUntilNextUpdate = 1;
                            break;
                        }
                        case 4: {
                            if (this.isItemTypeExcluded(this.mInventory[this.mCurrentSlot])) {
                                if (this.mInventory[18] != null || this.mThroughPut >= 2) break;
                                this.mInventory[18] = this.mInventory[this.mCurrentSlot];
                                this.mInventory[this.mCurrentSlot] = null;
                                this.mTicksUntilNextUpdate = 1;
                                break;
                            }
                            tTempStack = this.mInventory[this.mCurrentSlot].copy();
                            tTempStack.stackSize = 1;
                            tRecipe[0] = tTempStack;
                            tRecipe[1] = tTempStack;
                            tRecipe[2] = tTempStack;
                            tRecipe[3] = tTempStack;
                            tRecipe[4] = tTempStack;
                            tRecipe[5] = tTempStack;
                            tRecipe[6] = tTempStack;
                            tRecipe[7] = tTempStack;
                            tRecipe[8] = tTempStack;
                            if (GT_ModHandler.getRecipeOutput(tRecipe) != null || this.mInventory[18] != null) break;
                            this.mInventory[18] = this.mInventory[this.mCurrentSlot];
                            this.mInventory[this.mCurrentSlot] = null;
                            this.mTicksUntilNextUpdate = 1;
                            break;
                        }
                        case 5: {
                            if (this.isItemTypeExcluded(this.mInventory[this.mCurrentSlot])) {
                                if (this.mInventory[18] != null || this.mThroughPut >= 2) break;
                                this.mInventory[18] = this.mInventory[this.mCurrentSlot];
                                this.mInventory[this.mCurrentSlot] = null;
                                this.mTicksUntilNextUpdate = 1;
                                break;
                            }
                            tTempStack = this.mInventory[this.mCurrentSlot].copy();
                            tTempStack.stackSize = 1;
                            tRecipe[0] = tTempStack;
                            tOutput = GT_OreDictUnificator.get(tTempStack);
                            if (tOutput != null && tOutput.isItemEqual(tTempStack)) {
                                tOutput = null;
                            }
                            if (tOutput != null) break;
                            tRecipe[0] = null;
                            if (this.mInventory[18] != null) break;
                            this.mInventory[18] = this.mInventory[this.mCurrentSlot];
                            this.mInventory[this.mCurrentSlot] = null;
                            this.mTicksUntilNextUpdate = 1;
                            break;
                        }
                        case 6: {
                            if (this.isItemTypeExcluded(this.mInventory[this.mCurrentSlot]) || !GT_OreDictUnificator.isItemStackInstanceOf(this.mInventory[this.mCurrentSlot], "dustSmall", true)) {
                                if (this.mInventory[18] != null || this.mThroughPut >= 2) break;
                                this.mInventory[18] = this.mInventory[this.mCurrentSlot];
                                this.mInventory[this.mCurrentSlot] = null;
                                this.mTicksUntilNextUpdate = 1;
                                break;
                            }
                            tTempStack = this.mInventory[this.mCurrentSlot].copy();
                            tTempStack.stackSize = 1;
                            tRecipe[0] = tTempStack;
                            tRecipe[1] = tTempStack;
                            tRecipe[3] = tTempStack;
                            tRecipe[4] = tTempStack;
                            if (GT_ModHandler.getRecipeOutput(tRecipe) != null || this.mInventory[18] != null) break;
                            this.mInventory[18] = this.mInventory[this.mCurrentSlot];
                            this.mInventory[this.mCurrentSlot] = null;
                            this.mTicksUntilNextUpdate = 1;
                            break;
                        }
                        case 7: {
                            if (this.isItemTypeExcluded(this.mInventory[this.mCurrentSlot]) || !GT_OreDictUnificator.isItemStackInstanceOf(this.mInventory[this.mCurrentSlot], "nugget", true)) {
                                if (this.mInventory[18] != null || this.mThroughPut >= 2) break;
                                this.mInventory[18] = this.mInventory[this.mCurrentSlot];
                                this.mInventory[this.mCurrentSlot] = null;
                                this.mTicksUntilNextUpdate = 1;
                                break;
                            }
                            tTempStack = this.mInventory[this.mCurrentSlot].copy();
                            tTempStack.stackSize = 1;
                            tRecipe[0] = tTempStack;
                            tRecipe[1] = tTempStack;
                            tRecipe[2] = tTempStack;
                            tRecipe[3] = tTempStack;
                            tRecipe[4] = tTempStack;
                            tRecipe[5] = tTempStack;
                            tRecipe[6] = tTempStack;
                            tRecipe[7] = tTempStack;
                            tRecipe[8] = tTempStack;
                            if (GT_ModHandler.getRecipeOutput(tRecipe) != null || this.mInventory[18] != null) break;
                            this.mInventory[18] = this.mInventory[this.mCurrentSlot];
                            this.mInventory[this.mCurrentSlot] = null;
                            this.mTicksUntilNextUpdate = 1;
                            break;
                        }
                        case 8: {
                            if (this.isItemTypeExcluded(this.mInventory[this.mCurrentSlot]) || this.mInventory[this.mCurrentSlot].getItemDamage() <= 0 || !this.mInventory[this.mCurrentSlot].getItem().isRepairable()) {
                                if (this.mInventory[18] != null || this.mThroughPut >= 2) break;
                                this.mInventory[18] = this.mInventory[this.mCurrentSlot];
                                this.mInventory[this.mCurrentSlot] = null;
                                this.mTicksUntilNextUpdate = 1;
                                break;
                            }
                            tTempStack = this.mInventory[this.mCurrentSlot].copy();
                            tTempStack.stackSize = 1;
                            for (i = this.mCurrentSlot + 1; i < 18; ++i) {
                                if (this.mInventory[i] == null || !this.mInventory[i].isItemEqual(tTempStack) || this.mInventory[this.mCurrentSlot].getItemDamage() <= 0) continue;
                                tRecipe[0] = tTempStack;
                                tRecipe[1] = this.mInventory[i].copy();
                                if (GT_ModHandler.getRecipeOutput(tRecipe) != null) continue;
                                if (this.mInventory[18] != null) break block0;
                                this.mInventory[18] = this.mInventory[this.mCurrentSlot];
                                this.mInventory[this.mCurrentSlot] = null;
                                this.mTicksUntilNextUpdate = 1;
                                break block0;
                            }
                            break;
                        }
                    }
                }
                if (tOutput == null) {
                    tOutput = GT_ModHandler.getRecipeOutput(tRecipe);
                }
                if (tOutput != null || this.mMode == 0) {
                    this.mInventory[28] = tOutput;
                }
                if (tOutput == null) {
                    this.mLastCraftSuccessful = false;
                } else {
                    int j;
                    int i;
                    tTempStack = GT_OreDictUnificator.get(tOutput);
                    if (tTempStack != null) {
                        tTempStack.stackSize = tOutput.stackSize;
                        tOutput = tTempStack;
                    }
                    this.mInventory[28] = tOutput.copy();
                    ArrayList tList = this.recipeContent(tRecipe);
                    ArrayList tContent = this.benchContent();
                    if (tList.size() > 0 && tContent.size() > 0) {
                        boolean success = true;
                        for (i = 0; i < tList.size() && success; ++i) {
                            success = false;
                            for (j = 0; j < tContent.size() && !success; ++j) {
                                if (((ItemStack)tList.get((int)i)).itemID != ((ItemStack)tContent.get((int)j)).itemID || ((ItemStack)tList.get(i)).getItemDamage() != ((ItemStack)tContent.get(j)).getItemDamage() || ((ItemStack)tList.get((int)i)).stackSize > ((ItemStack)tContent.get((int)j)).stackSize) continue;
                                success = true;
                            }
                        }
                        if (success) {
                            this.mLastCraftSuccessful = true;
                            int tCellCount = -GT_Mod.getCapsuleCellContainerCount(tOutput) * tOutput.stackSize;
                            block16: for (int i2 = 8; i2 > -1; --i2) {
                                for (int j2 = 17; j2 > -1; --j2) {
                                    if (tRecipe[i2] == null || this.mInventory[j2] == null || tRecipe[i2].itemID != this.mInventory[j2].itemID || tRecipe[i2].getItemDamage() != this.mInventory[j2].getItemDamage()) continue;
                                    if (this.mInventory[j2].getItem().hasContainerItem()) {
                                        ItemStack tStack = this.mInventory[j2].getItem().getContainerItemStack(this.mInventory[j2]);
                                        if (tStack != null) {
                                            for (int k = 9; k < 18; ++k) {
                                                if (this.mInventory[k] == null) {
                                                    this.mInventory[k] = tStack.copy();
                                                } else {
                                                    if (!this.mInventory[k].isItemEqual(tStack) || this.mInventory[k].stackSize + tStack.stackSize > tStack.getMaxStackSize()) continue;
                                                    this.mInventory[k].stackSize += tStack.stackSize;
                                                }
                                                break;
                                            }
                                        }
                                    } else if (GT_Mod.getCapsuleCellContainerCount(this.mInventory[j2]) != 0) {
                                        tCellCount += GT_Mod.getCapsuleCellContainerCount(this.mInventory[j2]);
                                    }
                                    this.mBaseMetaTileEntity.decrStackSize(j2, 1);
                                    continue block16;
                                }
                            }
                            for (int k = 9; k < 18 && tCellCount > 0; ++k) {
                                if (this.mInventory[k] != null && !this.mInventory[k].isItemEqual(GT_ModHandler.getIC2Item("cell", 1))) continue;
                                if (this.mInventory[k] == null) {
                                    this.mInventory[k] = GT_ModHandler.getIC2Item("cell", 1);
                                    --tCellCount;
                                }
                                while (this.mInventory[k].stackSize < GT_ModHandler.getIC2Item("cell", 1).getItem().getItemStackLimit() && tCellCount-- > 0) {
                                    ++this.mInventory[k].stackSize;
                                }
                            }
                            this.mInventory[18] = tOutput.copy();
                            this.mBaseMetaTileEntity.decreaseStoredEnergy(this.mMode == 5 || this.mMode == 6 || this.mMode == 7 ? 128 : 2048, true);
                            this.mTicksUntilNextUpdate = 1;
                        } else {
                            this.mLastCraftSuccessful = false;
                            if (this.mInventory[8] != null && this.mInventory[18] == null && this.mThroughPut < 2) {
                                this.mInventory[18] = this.mInventory[8];
                                this.mInventory[8] = null;
                                this.mTicksUntilNextUpdate = 1;
                            }
                        }
                    }
                    if (this.mInventory[18] == null && this.mThroughPut < 2) {
                        Object tStack = null;
                        block21: for (i = 0; i < 8; ++i) {
                            j = i;
                            while (++j < 9) {
                                if (this.mInventory[i] == null || this.mInventory[j] == null || !this.mInventory[i].isItemEqual(this.mInventory[j]) || this.mInventory[i].getMaxStackSize() <= 8) continue;
                                this.mInventory[18] = this.mInventory[j];
                                this.mInventory[j] = null;
                                this.mTicksUntilNextUpdate = 1;
                                continue block21;
                            }
                        }
                    }
                }
            }
            if (this.mThroughPut < 2) {
                int xDir = ForgeDirection.getOrientation((int)this.mBaseMetaTileEntity.getFacing()).offsetX;
                int yDir = ForgeDirection.getOrientation((int)this.mBaseMetaTileEntity.getFacing()).offsetY;
                int zDir = ForgeDirection.getOrientation((int)this.mBaseMetaTileEntity.getFacing()).offsetZ;
                BaseMetaTileEntity tTileEntity1 = this.mBaseMetaTileEntity;
                TileEntity tTileEntity2 = this.mBaseMetaTileEntity.worldObj.getBlockTileEntity(this.mBaseMetaTileEntity.xCoord - xDir, this.mBaseMetaTileEntity.yCoord - yDir, this.mBaseMetaTileEntity.zCoord - zDir);
                if (tTileEntity1 != null && tTileEntity2 != null && tTileEntity1 instanceof IInventory && tTileEntity2 instanceof IInventory) {
                    this.mBaseMetaTileEntity.decreaseStoredEnergy(GT_Utility.moveOneItemStack((IInventory)tTileEntity1, (IInventory)tTileEntity2, ForgeDirection.getOrientation((int)this.mBaseMetaTileEntity.getFacing()).getOpposite(), ForgeDirection.getOrientation((int)this.mBaseMetaTileEntity.getFacing()), null, false, 64, 1, 64, 1) * 10, true);
                }
            }
        }
    }

    private boolean isItemTypeExcluded(ItemStack aStack) {
        if (aStack == null) {
            return true;
        }
        for (int i = 19; i < 28; ++i) {
            if (this.mInventory[i] == null || !this.mInventory[i].isItemEqual(aStack)) continue;
            return true;
        }
        return false;
    }

    private ArrayList recipeContent(ItemStack[] tRecipe) {
        ArrayList<ItemStack> tList = new ArrayList<ItemStack>();
        for (int i = 0; i < 9; ++i) {
            if (tRecipe[i] == null) continue;
            boolean temp = false;
            for (int j = 0; j < tList.size(); ++j) {
                if (tRecipe[i].itemID != ((ItemStack)tList.get((int)j)).itemID || tRecipe[i].getItemDamage() != ((ItemStack)tList.get(j)).getItemDamage()) continue;
                ++((ItemStack)tList.get((int)j)).stackSize;
                temp = true;
                break;
            }
            if (temp) continue;
            tList.add(tRecipe[i].copy());
        }
        return tList;
    }

    private ArrayList benchContent() {
        ArrayList<ItemStack> tList = new ArrayList<ItemStack>();
        for (int i = 0; i < 18; ++i) {
            if (this.mInventory[i] == null) continue;
            boolean temp = false;
            for (int j = 0; j < tList.size(); ++j) {
                if (this.mInventory[i].itemID != ((ItemStack)tList.get((int)j)).itemID || this.mInventory[i].getItemDamage() != ((ItemStack)tList.get(j)).getItemDamage()) continue;
                ((ItemStack)tList.get((int)j)).stackSize += this.mInventory[i].stackSize;
                temp = true;
                break;
            }
            if (temp) continue;
            tList.add(this.mInventory[i].copy());
        }
        return tList;
    }

    @Override
    public int getInvSideIndex(ForgeDirection aSide, int aFacing) {
        if (aSide == ForgeDirection.getOrientation((int)aFacing)) {
            return 0;
        }
        if (aSide.getOpposite() == ForgeDirection.getOrientation((int)aFacing)) {
            return 18;
        }
        return 9;
    }

    @Override
    public int getInvSideLength(ForgeDirection aSide, int aFacing) {
        if (aSide == ForgeDirection.getOrientation((int)aFacing)) {
            return 9;
        }
        if (aSide.getOpposite() == ForgeDirection.getOrientation((int)aFacing)) {
            return 1;
        }
        return 9;
    }

    @Override
    public int getTextureIndex(int aSide, int aFacing, boolean aActive, boolean aRedstone) {
        if (aSide == aFacing) {
            return 112;
        }
        if (ForgeDirection.getOrientation((int)aSide).getOpposite() == ForgeDirection.getOrientation((int)aFacing)) {
            return 113;
        }
        return 114;
    }
}

