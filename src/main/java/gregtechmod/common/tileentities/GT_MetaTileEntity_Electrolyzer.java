/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraftforge.common.ForgeDirection
 *  net.minecraftforge.liquids.LiquidStack
 */
package gregtechmod.common.tileentities;

import gregtechmod.GT_Mod;
import gregtechmod.api.BaseMetaTileEntity;
import gregtechmod.api.GT_Recipe;
import gregtechmod.api.MetaTileEntity;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.ForgeDirection;
import net.minecraftforge.liquids.LiquidStack;

public class GT_MetaTileEntity_Electrolyzer
extends MetaTileEntity {
    private int mProgresstime = 0;
    private int mMaxProgresstime = 0;
    private int mEUt = 0;
    private int mStoredWater = 0;
    private ItemStack mOutputItem1;
    private ItemStack mOutputItem2;
    private ItemStack mOutputItem3;
    private ItemStack mOutputItem4;

    public GT_MetaTileEntity_Electrolyzer(int aID, String mName, String mNameRegional) {
        super(aID, mName, mNameRegional);
    }

    public GT_MetaTileEntity_Electrolyzer() {
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
    public boolean isValidSlot(int aIndex) {
        return aIndex < 6;
    }

    @Override
    public boolean isSimpleMachine() {
        return false;
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
        return 10000;
    }

    @Override
    public int maxMJStore() {
        return this.maxEUStore();
    }

    @Override
    public int getInvSize() {
        return 7;
    }

    @Override
    public void onRightclick(EntityPlayer aPlayer) {
        aPlayer.openGui((Object)GT_Mod.instance, 109, this.mBaseMetaTileEntity.worldObj, this.mBaseMetaTileEntity.xCoord, this.mBaseMetaTileEntity.yCoord, this.mBaseMetaTileEntity.zCoord);
    }

    @Override
    public boolean isAccessAllowed(EntityPlayer aPlayer) {
        return true;
    }

    public int getProgresstime() {
        return this.mProgresstime;
    }

    public int maxProgresstime() {
        return this.mMaxProgresstime;
    }

    @Override
    public MetaTileEntity newMetaEntity(BaseMetaTileEntity aTileEntity) {
        return new GT_MetaTileEntity_Electrolyzer();
    }

    @Override
    public void saveNBTData(NBTTagCompound aNBT) {
        NBTTagCompound tNBT;
        aNBT.setInteger("mEUt", this.mEUt);
        aNBT.setInteger("mStoredWater", this.mStoredWater);
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
        if (this.mOutputItem3 != null) {
            tNBT = new NBTTagCompound();
            this.mOutputItem3.writeToNBT(tNBT);
            aNBT.setTag("mOutputItem3", (NBTBase)tNBT);
        }
        if (this.mOutputItem4 != null) {
            tNBT = new NBTTagCompound();
            this.mOutputItem4.writeToNBT(tNBT);
            aNBT.setTag("mOutputItem4", (NBTBase)tNBT);
        }
    }

    @Override
    public void loadNBTData(NBTTagCompound aNBT) {
        NBTTagCompound tNBT4;
        NBTTagCompound tNBT3;
        NBTTagCompound tNBT2;
        this.mEUt = aNBT.getInteger("mEUt");
        this.mStoredWater = aNBT.getInteger("mStoredWater");
        this.mProgresstime = aNBT.getInteger("mProgresstime");
        this.mMaxProgresstime = aNBT.getInteger("mMaxProgresstime");
        NBTTagCompound tNBT1 = (NBTTagCompound)aNBT.getTag("mOutputItem1");
        if (tNBT1 != null) {
            this.mOutputItem1 = ItemStack.loadItemStackFromNBT((NBTTagCompound)tNBT1);
        }
        if ((tNBT2 = (NBTTagCompound)aNBT.getTag("mOutputItem2")) != null) {
            this.mOutputItem2 = ItemStack.loadItemStackFromNBT((NBTTagCompound)tNBT2);
        }
        if ((tNBT3 = (NBTTagCompound)aNBT.getTag("mOutputItem3")) != null) {
            this.mOutputItem3 = ItemStack.loadItemStackFromNBT((NBTTagCompound)tNBT3);
        }
        if ((tNBT4 = (NBTTagCompound)aNBT.getTag("mOutputItem4")) != null) {
            this.mOutputItem4 = ItemStack.loadItemStackFromNBT((NBTTagCompound)tNBT4);
        }
    }

    @Override
    public void onPostTick() {
        if (!this.mBaseMetaTileEntity.worldObj.isRemote) {
            if (this.mStoredWater >= 1000 && (this.mInventory[6] == null || this.mInventory[6].stackSize < 64)) {
                if (this.mInventory[6] == null) {
                    this.mInventory[6] = new ItemStack(Block.waterStill, 0);
                }
                this.mStoredWater -= 1000;
                ++this.mInventory[6].stackSize;
            }
            if (this.mMaxProgresstime > 0) {
                this.mBaseMetaTileEntity.mActive = true;
                if (this.mProgresstime < 0 || this.mBaseMetaTileEntity.decreaseStoredEnergy(this.mEUt * (int)Math.pow(4.0, this.mBaseMetaTileEntity.mOverclockers), false)) {
                    if ((this.mProgresstime += (int)Math.pow(2.0, this.mBaseMetaTileEntity.mOverclockers)) >= this.mMaxProgresstime) {
                        this.addOutputProducts();
                        this.mOutputItem1 = null;
                        this.mOutputItem2 = null;
                        this.mOutputItem3 = null;
                        this.mOutputItem4 = null;
                        this.mProgresstime = 0;
                        this.mMaxProgresstime = 0;
                        this.mBaseMetaTileEntity.mDisplayErrorCode = 0;
                    }
                } else {
                    this.mBaseMetaTileEntity.mActive = false;
                    if (GT_Mod.instance.mConstantEnergy) {
                        this.mProgresstime = -10;
                        this.mBaseMetaTileEntity.mDisplayErrorCode = 1;
                    }
                }
            } else {
                this.mBaseMetaTileEntity.mActive = false;
                if (this.mBaseMetaTileEntity.getStoredEnergy() > 100) {
                    this.checkRecipe();
                }
            }
        }
    }

    private void addOutputProducts() {
        if (this.mOutputItem1 != null) {
            if (this.mInventory[2] == null) {
                this.mInventory[2] = this.mOutputItem1.copy();
            } else if (this.mInventory[2].isItemEqual(this.mOutputItem1)) {
                this.mInventory[2].stackSize = Math.min(this.mOutputItem1.getMaxStackSize(), this.mOutputItem1.stackSize + this.mInventory[2].stackSize);
            }
        }
        if (this.mOutputItem2 != null) {
            if (this.mInventory[3] == null) {
                this.mInventory[3] = this.mOutputItem2.copy();
            } else if (this.mInventory[3].isItemEqual(this.mOutputItem2)) {
                this.mInventory[3].stackSize = Math.min(this.mOutputItem2.getMaxStackSize(), this.mOutputItem2.stackSize + this.mInventory[3].stackSize);
            }
        }
        if (this.mOutputItem3 != null) {
            if (this.mInventory[4] == null) {
                this.mInventory[4] = this.mOutputItem3.copy();
            } else if (this.mInventory[4].isItemEqual(this.mOutputItem3)) {
                this.mInventory[4].stackSize = Math.min(this.mOutputItem3.getMaxStackSize(), this.mOutputItem3.stackSize + this.mInventory[4].stackSize);
            }
        }
        if (this.mOutputItem4 != null) {
            if (this.mInventory[5] == null) {
                this.mInventory[5] = this.mOutputItem4.copy();
            } else if (this.mInventory[5].isItemEqual(this.mOutputItem4)) {
                this.mInventory[5].stackSize = Math.min(this.mOutputItem4.getMaxStackSize(), this.mOutputItem4.stackSize + this.mInventory[5].stackSize);
            }
        }
    }

    private boolean spaceForOutput(int aRecipe) {
        return (this.mInventory[2] == null || ((GT_Recipe)GT_Recipe.sElectrolyzerRecipes.get((int)aRecipe)).mOutput1 == null || this.mInventory[2].stackSize + ((GT_Recipe)GT_Recipe.sElectrolyzerRecipes.get((int)aRecipe)).mOutput1.stackSize <= this.mInventory[2].getMaxStackSize() && this.mInventory[2].isItemEqual(((GT_Recipe)GT_Recipe.sElectrolyzerRecipes.get((int)aRecipe)).mOutput1)) && (this.mInventory[3] == null || ((GT_Recipe)GT_Recipe.sElectrolyzerRecipes.get((int)aRecipe)).mOutput2 == null || this.mInventory[3].stackSize + ((GT_Recipe)GT_Recipe.sElectrolyzerRecipes.get((int)aRecipe)).mOutput2.stackSize <= this.mInventory[3].getMaxStackSize() && this.mInventory[3].isItemEqual(((GT_Recipe)GT_Recipe.sElectrolyzerRecipes.get((int)aRecipe)).mOutput2)) && (this.mInventory[4] == null || ((GT_Recipe)GT_Recipe.sElectrolyzerRecipes.get((int)aRecipe)).mOutput3 == null || this.mInventory[4].stackSize + ((GT_Recipe)GT_Recipe.sElectrolyzerRecipes.get((int)aRecipe)).mOutput3.stackSize <= this.mInventory[4].getMaxStackSize() && this.mInventory[4].isItemEqual(((GT_Recipe)GT_Recipe.sElectrolyzerRecipes.get((int)aRecipe)).mOutput3)) && (this.mInventory[5] == null || ((GT_Recipe)GT_Recipe.sElectrolyzerRecipes.get((int)aRecipe)).mOutput4 == null || this.mInventory[5].stackSize + ((GT_Recipe)GT_Recipe.sElectrolyzerRecipes.get((int)aRecipe)).mOutput4.stackSize <= this.mInventory[5].getMaxStackSize() && this.mInventory[5].isItemEqual(((GT_Recipe)GT_Recipe.sElectrolyzerRecipes.get((int)aRecipe)).mOutput4));
    }

    private boolean checkRecipe() {
        int tRecipe = GT_Recipe.findEqualElectrolyzerRecipeIndex(this.mInventory[0], this.mInventory[1]);
        if (tRecipe != -1 && this.spaceForOutput(tRecipe) && GT_Recipe.isRecipeInputEqual(true, true, this.mInventory[0], this.mInventory[1], (GT_Recipe)GT_Recipe.sElectrolyzerRecipes.get(tRecipe))) {
            if (this.mInventory[0] != null && this.mInventory[0].stackSize == 0) {
                this.mInventory[0] = null;
            }
            if (this.mInventory[1] != null && this.mInventory[1].stackSize == 0) {
                this.mInventory[1] = null;
            }
            this.mMaxProgresstime = ((GT_Recipe)GT_Recipe.sElectrolyzerRecipes.get((int)tRecipe)).mDuration;
            this.mEUt = ((GT_Recipe)GT_Recipe.sElectrolyzerRecipes.get((int)tRecipe)).mEUt;
            this.mOutputItem1 = ((GT_Recipe)GT_Recipe.sElectrolyzerRecipes.get((int)tRecipe)).mOutput1 == null ? null : ((GT_Recipe)GT_Recipe.sElectrolyzerRecipes.get((int)tRecipe)).mOutput1.copy();
            this.mOutputItem2 = ((GT_Recipe)GT_Recipe.sElectrolyzerRecipes.get((int)tRecipe)).mOutput2 == null ? null : ((GT_Recipe)GT_Recipe.sElectrolyzerRecipes.get((int)tRecipe)).mOutput2.copy();
            this.mOutputItem3 = ((GT_Recipe)GT_Recipe.sElectrolyzerRecipes.get((int)tRecipe)).mOutput3 == null ? null : ((GT_Recipe)GT_Recipe.sElectrolyzerRecipes.get((int)tRecipe)).mOutput3.copy();
            this.mOutputItem4 = ((GT_Recipe)GT_Recipe.sElectrolyzerRecipes.get((int)tRecipe)).mOutput4 == null ? null : ((GT_Recipe)GT_Recipe.sElectrolyzerRecipes.get((int)tRecipe)).mOutput4.copy();
            return true;
        }
        tRecipe = GT_Recipe.findEqualElectrolyzerRecipeIndex(this.mInventory[6], this.mInventory[1]);
        if (tRecipe != -1 && this.spaceForOutput(tRecipe) && GT_Recipe.isRecipeInputEqual(true, true, this.mInventory[6], this.mInventory[1], (GT_Recipe)GT_Recipe.sElectrolyzerRecipes.get(tRecipe))) {
            if (this.mInventory[6] != null && this.mInventory[6].stackSize == 0) {
                this.mInventory[6] = null;
            }
            if (this.mInventory[1] != null && this.mInventory[1].stackSize == 0) {
                this.mInventory[1] = null;
            }
            this.mMaxProgresstime = ((GT_Recipe)GT_Recipe.sElectrolyzerRecipes.get((int)tRecipe)).mDuration;
            this.mEUt = ((GT_Recipe)GT_Recipe.sElectrolyzerRecipes.get((int)tRecipe)).mEUt;
            this.mOutputItem1 = ((GT_Recipe)GT_Recipe.sElectrolyzerRecipes.get((int)tRecipe)).mOutput1 == null ? null : ((GT_Recipe)GT_Recipe.sElectrolyzerRecipes.get((int)tRecipe)).mOutput1.copy();
            this.mOutputItem2 = ((GT_Recipe)GT_Recipe.sElectrolyzerRecipes.get((int)tRecipe)).mOutput2 == null ? null : ((GT_Recipe)GT_Recipe.sElectrolyzerRecipes.get((int)tRecipe)).mOutput2.copy();
            this.mOutputItem3 = ((GT_Recipe)GT_Recipe.sElectrolyzerRecipes.get((int)tRecipe)).mOutput3 == null ? null : ((GT_Recipe)GT_Recipe.sElectrolyzerRecipes.get((int)tRecipe)).mOutput3.copy();
            this.mOutputItem4 = ((GT_Recipe)GT_Recipe.sElectrolyzerRecipes.get((int)tRecipe)).mOutput4 == null ? null : ((GT_Recipe)GT_Recipe.sElectrolyzerRecipes.get((int)tRecipe)).mOutput4.copy();
            return true;
        }
        return false;
    }

    @Override
    public int getInvSideIndex(ForgeDirection aSide, int aFacing) {
        if (aSide == ForgeDirection.UP) {
            return 0;
        }
        if (aSide == ForgeDirection.DOWN) {
            return 1;
        }
        return 2;
    }

    @Override
    public int getInvSideLength(ForgeDirection aSide, int aFacing) {
        if (aSide != ForgeDirection.UP && aSide != ForgeDirection.DOWN) {
            return 4;
        }
        return 1;
    }

    @Override
    public int getTextureIndex(int aSide, int aFacing, boolean aActive, boolean aRedstone) {
        if (aSide == 0) {
            return 32;
        }
        if (aSide == 1) {
            return 29;
        }
        return aActive ? 65 : 64;
    }

    @Override
    public String getMainInfo() {
        return "Progress:";
    }

    @Override
    public String getSecondaryInfo() {
        return this.mProgresstime / 20 + "secs";
    }

    @Override
    public String getTertiaryInfo() {
        return "/" + this.mMaxProgresstime / 20 + "secs";
    }

    @Override
    public boolean isGivingInformation() {
        return true;
    }

    @Override
    protected String getDescription() {
        return "Give your Water a shocking Experience with this Device";
    }

    @Override
    public int fill(LiquidStack resource, boolean doFill) {
        if (resource == null || resource.itemID <= 0) {
            return 0;
        }
        LiquidStack tLiquid = new LiquidStack(Block.waterStill, this.mStoredWater);
        if (!tLiquid.isLiquidEqual(resource)) {
            return 0;
        }
        int space = this.getCapacity() - tLiquid.amount;
        if (resource.amount <= space) {
            if (doFill && space > 0) {
                this.mStoredWater += resource.amount;
            }
            return resource.amount;
        }
        if (doFill && space > 0) {
            this.mStoredWater = this.getCapacity();
        }
        return space;
    }

    @Override
    public LiquidStack getLiquid() {
        return new LiquidStack(Block.waterStill, this.mStoredWater);
    }

    @Override
    public int getTankPressure() {
        return -100;
    }

    @Override
    public void setLiquid(LiquidStack liquid) {
    }

    @Override
    public void setCapacity(int capacity) {
    }

    @Override
    public int getCapacity() {
        return 10000;
    }
}

