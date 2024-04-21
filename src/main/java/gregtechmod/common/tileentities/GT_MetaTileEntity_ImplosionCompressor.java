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

public class GT_MetaTileEntity_ImplosionCompressor
extends MetaTileEntity {
    public int mProgresstime = 0;
    public int mMaxProgresstime = 0;
    public int mEUt = 0;
    public int mUpdate = 5;
    public ItemStack mOutputItem1;
    public ItemStack mOutputItem2;
    public boolean mMachine = false;

    public GT_MetaTileEntity_ImplosionCompressor(int aID, String mName, String mNameRegional) {
        super(aID, mName, mNameRegional);
    }

    public GT_MetaTileEntity_ImplosionCompressor() {
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
        return 4;
    }

    @Override
    public void onRightclick(EntityPlayer aPlayer) {
        aPlayer.openGui((Object)GT_Mod.instance, 115, this.mBaseMetaTileEntity.worldObj, this.mBaseMetaTileEntity.xCoord, this.mBaseMetaTileEntity.yCoord, this.mBaseMetaTileEntity.zCoord);
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
        return new GT_MetaTileEntity_ImplosionCompressor();
    }

    @Override
    public void saveNBTData(NBTTagCompound aNBT) {
        NBTTagCompound tNBT;
        aNBT.setInteger("mEUt", this.mEUt);
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
        this.mUpdate = 5;
        this.mEUt = aNBT.getInteger("mEUt");
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
                        if (this.mBaseMetaTileEntity.worldObj.getBlockMetadata(this.mBaseMetaTileEntity.xCoord - xDir + i, this.mBaseMetaTileEntity.yCoord - yDir + j, this.mBaseMetaTileEntity.zCoord - zDir + k) == (i == 0 || j == 0 || k == 0 ? 14 : 13)) continue;
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
                if (this.mBaseMetaTileEntity.decreaseStoredEnergy(this.mEUt, false)) {
                    if (++this.mProgresstime > this.mMaxProgresstime) {
                        this.addOutputProducts();
                        this.mOutputItem1 = null;
                        this.mOutputItem2 = null;
                        this.mProgresstime = 0;
                        this.mMaxProgresstime = 0;
                    }
                    if (this.mProgresstime == this.mMaxProgresstime / 2) {
                        this.sendSound(0);
                    }
                }
            } else if (this.mBaseMetaTileEntity.getStoredEnergy() > 100) {
                this.checkRecipe();
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
    }

    private boolean spaceForOutput(int aRecipe) {
        return (this.mInventory[2] == null || ((GT_Recipe)GT_Recipe.sImplosionRecipes.get((int)aRecipe)).mOutput1 == null || this.mInventory[2].stackSize + ((GT_Recipe)GT_Recipe.sImplosionRecipes.get((int)aRecipe)).mOutput1.stackSize <= this.mInventory[2].getMaxStackSize() && this.mInventory[2].isItemEqual(((GT_Recipe)GT_Recipe.sImplosionRecipes.get((int)aRecipe)).mOutput1)) && (this.mInventory[3] == null || ((GT_Recipe)GT_Recipe.sImplosionRecipes.get((int)aRecipe)).mOutput2 == null || this.mInventory[3].stackSize + ((GT_Recipe)GT_Recipe.sImplosionRecipes.get((int)aRecipe)).mOutput2.stackSize <= this.mInventory[3].getMaxStackSize() && this.mInventory[3].isItemEqual(((GT_Recipe)GT_Recipe.sImplosionRecipes.get((int)aRecipe)).mOutput2));
    }

    private boolean checkRecipe() {
        if (!this.mMachine) {
            return false;
        }
        int tRecipe = GT_Recipe.findEqualImplosionRecipeIndex(this.mInventory[0], this.mInventory[1]);
        if (tRecipe != -1 && this.spaceForOutput(tRecipe) && GT_Recipe.isRecipeInputEqual(true, true, this.mInventory[0], this.mInventory[1], (GT_Recipe)GT_Recipe.sImplosionRecipes.get(tRecipe))) {
            if (this.mInventory[0] != null && this.mInventory[0].stackSize == 0) {
                this.mInventory[0] = null;
            }
            if (this.mInventory[1] != null && this.mInventory[1].stackSize == 0) {
                this.mInventory[1] = null;
            }
            this.mMaxProgresstime = ((GT_Recipe)GT_Recipe.sImplosionRecipes.get((int)tRecipe)).mDuration;
            this.mEUt = ((GT_Recipe)GT_Recipe.sImplosionRecipes.get((int)tRecipe)).mEUt;
            this.mOutputItem1 = ((GT_Recipe)GT_Recipe.sImplosionRecipes.get((int)tRecipe)).mOutput1 == null ? null : ((GT_Recipe)GT_Recipe.sImplosionRecipes.get((int)tRecipe)).mOutput1.copy();
            this.mOutputItem2 = ((GT_Recipe)GT_Recipe.sImplosionRecipes.get((int)tRecipe)).mOutput2 == null ? null : ((GT_Recipe)GT_Recipe.sImplosionRecipes.get((int)tRecipe)).mOutput2.copy();
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
        if (aSide == ForgeDirection.WEST || aSide == ForgeDirection.EAST) {
            return 2;
        }
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
        if (ForgeDirection.getOrientation((int)aSide) == ForgeDirection.NORTH || ForgeDirection.getOrientation((int)aSide) == ForgeDirection.SOUTH) {
            return 74;
        }
        return 73;
    }

    @Override
    public void doSound(int aIndex, double aX, double aY, double aZ) {
        this.mBaseMetaTileEntity.worldObj.playSoundEffect(aX, aY, aZ, "random.explode", 4.0f, (1.0f + (this.mBaseMetaTileEntity.worldObj.rand.nextFloat() - this.mBaseMetaTileEntity.worldObj.rand.nextFloat()) * 0.2f) * 0.7f);
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
        return "Over 9000 Gibbl!";
    }
}

