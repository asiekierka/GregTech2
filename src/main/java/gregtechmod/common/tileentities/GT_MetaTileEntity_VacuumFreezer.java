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

public class GT_MetaTileEntity_VacuumFreezer
extends MetaTileEntity {
    public int mProgresstime = 0;
    public int mMaxProgresstime = 0;
    public int mEUt = 0;
    public int mUpdate = 5;
    public ItemStack mOutputItem1;
    public boolean mMachine = false;

    public GT_MetaTileEntity_VacuumFreezer(int aID, String mName, String mNameRegional) {
        super(aID, mName, mNameRegional);
    }

    public GT_MetaTileEntity_VacuumFreezer() {
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
        return aFacing == 0;
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
        return 2;
    }

    @Override
    public void onRightclick(EntityPlayer aPlayer) {
        aPlayer.openGui((Object)GT_Mod.instance, 122, this.mBaseMetaTileEntity.worldObj, this.mBaseMetaTileEntity.xCoord, this.mBaseMetaTileEntity.yCoord, this.mBaseMetaTileEntity.zCoord);
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
        return new GT_MetaTileEntity_VacuumFreezer();
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
        this.mUpdate = 5;
        this.mEUt = aNBT.getInteger("mEUt");
        this.mProgresstime = aNBT.getInteger("mProgresstime");
        this.mMaxProgresstime = aNBT.getInteger("mMaxProgresstime");
        NBTTagCompound tNBT1 = (NBTTagCompound)aNBT.getTag("mOutputItem1");
        if (tNBT1 != null) {
            this.mOutputItem1 = ItemStack.loadItemStackFromNBT((NBTTagCompound)tNBT1);
        }
    }

    private boolean checkMachine() {
        int xDir = ForgeDirection.UP.offsetX * 2;
        int yDir = ForgeDirection.UP.offsetY * 2;
        int zDir = ForgeDirection.UP.offsetZ * 2;
        for (int i = -1; i < 2; ++i) {
            for (int j = -1; j < 2; ++j) {
                for (int k = -1; k < 2; ++k) {
                    if (i != 0 || j != 0 || k != 0) {
                        if (this.mBaseMetaTileEntity.worldObj.getBlockId(this.mBaseMetaTileEntity.xCoord - xDir + i, this.mBaseMetaTileEntity.yCoord - yDir + j, this.mBaseMetaTileEntity.zCoord - zDir + k) != GT_Mod.instance.mBlocks[0].blockID) {
                            return false;
                        }
                        if (this.mBaseMetaTileEntity.worldObj.getBlockMetadata(this.mBaseMetaTileEntity.xCoord - xDir + i, this.mBaseMetaTileEntity.yCoord - yDir + j, this.mBaseMetaTileEntity.zCoord - zDir + k) == (i == 0 && j == 0 && k != 0 || i == 0 && j != 0 && k == 0 || i != 0 && j == 0 && k == 0 ? 15 : 14)) continue;
                        return false;
                    }
                    if (this.mBaseMetaTileEntity.worldObj.getBlockId(this.mBaseMetaTileEntity.xCoord - xDir + i, this.mBaseMetaTileEntity.yCoord - yDir + j, this.mBaseMetaTileEntity.zCoord - zDir + k) == 0) continue;
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public void onMachineBlockUpdate() {
        this.mUpdate = 5;
    }

    @Override
    public void onPostTick() {
        if (!this.mBaseMetaTileEntity.worldObj.isRemote) {
            if (this.mUpdate-- == 0) {
                this.mMachine = this.checkMachine();
            }
            this.mBaseMetaTileEntity.mActive = this.mMachine;
            if (this.mMachine && this.mMaxProgresstime > 0) {
                if (this.mProgresstime < 0 || this.mBaseMetaTileEntity.decreaseStoredEnergy(this.mEUt * (int)Math.pow(4.0, this.mBaseMetaTileEntity.mOverclockers), false)) {
                    if ((this.mProgresstime += (int)Math.pow(2.0, this.mBaseMetaTileEntity.mOverclockers)) >= this.mMaxProgresstime) {
                        this.addOutputProducts();
                        this.mOutputItem1 = null;
                        this.mProgresstime = 0;
                        this.mMaxProgresstime = 0;
                    }
                } else if (GT_Mod.instance.mConstantEnergy) {
                    this.mProgresstime = -10;
                    this.mBaseMetaTileEntity.mDisplayErrorCode = 1;
                }
            } else if (this.mBaseMetaTileEntity.getStoredEnergy() > 100) {
                this.checkRecipe();
            }
        }
    }

    private void addOutputProducts() {
        if (this.mOutputItem1 != null) {
            if (this.mInventory[1] == null) {
                this.mInventory[1] = this.mOutputItem1.copy();
            } else if (this.mInventory[1].isItemEqual(this.mOutputItem1)) {
                this.mInventory[1].stackSize = Math.min(this.mOutputItem1.getMaxStackSize(), this.mOutputItem1.stackSize + this.mInventory[1].stackSize);
            }
        }
    }

    private boolean spaceForOutput(int aRecipe) {
        return this.mInventory[1] == null || ((GT_Recipe)GT_Recipe.sVacuumRecipes.get((int)aRecipe)).mOutput1 == null || this.mInventory[1].stackSize + ((GT_Recipe)GT_Recipe.sVacuumRecipes.get((int)aRecipe)).mOutput1.stackSize <= this.mInventory[1].getMaxStackSize() && this.mInventory[1].isItemEqual(((GT_Recipe)GT_Recipe.sVacuumRecipes.get((int)aRecipe)).mOutput1);
    }

    private boolean checkRecipe() {
        if (!this.mMachine) {
            return false;
        }
        int tRecipe = GT_Recipe.findEqualVacuumRecipeIndex(this.mInventory[0], null);
        if (tRecipe != -1 && this.spaceForOutput(tRecipe) && GT_Recipe.isRecipeInputEqual(true, true, this.mInventory[0], null, (GT_Recipe)GT_Recipe.sVacuumRecipes.get(tRecipe))) {
            if (this.mInventory[0] != null && this.mInventory[0].stackSize == 0) {
                this.mInventory[0] = null;
            }
            this.mMaxProgresstime = ((GT_Recipe)GT_Recipe.sVacuumRecipes.get((int)tRecipe)).mDuration;
            this.mEUt = ((GT_Recipe)GT_Recipe.sVacuumRecipes.get((int)tRecipe)).mEUt;
            this.mOutputItem1 = ((GT_Recipe)GT_Recipe.sVacuumRecipes.get((int)tRecipe)).mOutput1 == null ? null : ((GT_Recipe)GT_Recipe.sVacuumRecipes.get((int)tRecipe)).mOutput1.copy();
            return true;
        }
        return false;
    }

    @Override
    public int getInvSideIndex(ForgeDirection aSide, int aFacing) {
        if (aSide == ForgeDirection.UP) {
            return 0;
        }
        return 1;
    }

    @Override
    public int getInvSideLength(ForgeDirection aSide, int aFacing) {
        return 1;
    }

    @Override
    public int getTextureIndex(int aSide, int aFacing, boolean aActive, boolean aRedstone) {
        if (ForgeDirection.getOrientation((int)aSide) == ForgeDirection.UP) {
            return 68 + (aActive ? 1 : 0);
        }
        if (ForgeDirection.getOrientation((int)aSide) == ForgeDirection.DOWN) {
            return 71;
        }
        return 77;
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
        return "Cools down anything";
    }
}

