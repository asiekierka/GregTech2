/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraftforge.common.ForgeDirection
 */
package gregtechmod.common.tileentities;

import gregtechmod.GT_Mod;
import gregtechmod.api.BaseMetaTileEntity;
import gregtechmod.api.GT_Recipe;
import gregtechmod.api.MetaTileEntity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.ForgeDirection;

public class GT_MetaTileEntity_ChemicalReactor
extends MetaTileEntity {
    private int mProgresstime = 0;
    private int mMaxProgresstime = 0;
    private int mEUt = 0;
    private ItemStack mOutputItem1;

    public GT_MetaTileEntity_ChemicalReactor(int aID, String mName, String mNameRegional) {
        super(aID, mName, mNameRegional);
    }

    public GT_MetaTileEntity_ChemicalReactor() {
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
        return 32;
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
        return 3;
    }

    @Override
    public void onRightclick(EntityPlayer aPlayer) {
        aPlayer.openGui((Object)GT_Mod.instance, 124, this.mBaseMetaTileEntity.worldObj, this.mBaseMetaTileEntity.xCoord, this.mBaseMetaTileEntity.yCoord, this.mBaseMetaTileEntity.zCoord);
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
        return new GT_MetaTileEntity_ChemicalReactor();
    }

    @Override
    public void saveNBTData(NBTTagCompound aNBT) {
        aNBT.setInteger("mEUt", this.mEUt);
        aNBT.setInteger("mProgresstime", this.mProgresstime);
        aNBT.setInteger("mMaxProgresstime", this.mMaxProgresstime);
        if (this.mOutputItem1 != null) {
            NBTTagCompound tNBT = new NBTTagCompound();
            this.mOutputItem1.writeToNBT(tNBT);
            aNBT.setTag("mOutputItem1", (NBTBase)tNBT);
        }
    }

    @Override
    public void loadNBTData(NBTTagCompound aNBT) {
        this.mEUt = aNBT.getInteger("mEUt");
        this.mProgresstime = aNBT.getInteger("mProgresstime");
        this.mMaxProgresstime = aNBT.getInteger("mMaxProgresstime");
        NBTTagCompound tNBT1 = (NBTTagCompound)aNBT.getTag("mOutputItem1");
        if (tNBT1 != null) {
            this.mOutputItem1 = ItemStack.loadItemStackFromNBT((NBTTagCompound)tNBT1);
        }
    }

    @Override
    public void onPostTick() {
        if (!this.mBaseMetaTileEntity.worldObj.isRemote) {
            if (this.mMaxProgresstime > 0) {
                this.mBaseMetaTileEntity.mActive = true;
                if (this.mProgresstime < 0 || this.mBaseMetaTileEntity.decreaseStoredEnergy(this.mEUt * (int)Math.pow(4.0, this.mBaseMetaTileEntity.mOverclockers), false)) {
                    if ((this.mProgresstime += (int)Math.pow(2.0, this.mBaseMetaTileEntity.mOverclockers)) >= this.mMaxProgresstime) {
                        this.addOutputProducts();
                        this.mOutputItem1 = null;
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
    }

    private boolean spaceForOutput(int aRecipe) {
        return this.mInventory[2] == null || ((GT_Recipe)GT_Recipe.sChemicalRecipes.get((int)aRecipe)).mOutput1 == null || this.mInventory[2].stackSize + ((GT_Recipe)GT_Recipe.sChemicalRecipes.get((int)aRecipe)).mOutput1.stackSize <= this.mInventory[2].getMaxStackSize() && this.mInventory[2].isItemEqual(((GT_Recipe)GT_Recipe.sChemicalRecipes.get((int)aRecipe)).mOutput1);
    }

    private boolean checkRecipe() {
        int tRecipe = GT_Recipe.findEqualChemicalRecipeIndex(this.mInventory[0], this.mInventory[1]);
        if (tRecipe != -1 && this.spaceForOutput(tRecipe) && GT_Recipe.isRecipeInputEqual(true, true, this.mInventory[0], this.mInventory[1], (GT_Recipe)GT_Recipe.sChemicalRecipes.get(tRecipe))) {
            if (this.mInventory[0] != null && this.mInventory[0].stackSize == 0) {
                this.mInventory[0] = null;
            }
            if (this.mInventory[1] != null && this.mInventory[1].stackSize == 0) {
                this.mInventory[1] = null;
            }
            this.mMaxProgresstime = ((GT_Recipe)GT_Recipe.sChemicalRecipes.get((int)tRecipe)).mDuration;
            this.mEUt = ((GT_Recipe)GT_Recipe.sChemicalRecipes.get((int)tRecipe)).mEUt;
            this.mOutputItem1 = ((GT_Recipe)GT_Recipe.sChemicalRecipes.get((int)tRecipe)).mOutput1 == null ? null : ((GT_Recipe)GT_Recipe.sChemicalRecipes.get((int)tRecipe)).mOutput1.copy();
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
            return 1;
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
        return aActive ? 67 : 66;
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
        return "Let 2 chemical Substances react together with this!";
    }
}

