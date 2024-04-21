/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraftforge.common.ForgeDirection
 *  net.minecraftforge.liquids.LiquidContainerRegistry
 *  net.minecraftforge.liquids.LiquidStack
 */
package gregtechmod.common.tileentities;

import gregtechmod.GT_Mod;
import gregtechmod.api.BaseMetaTileEntity;
import gregtechmod.api.GT_Recipe;
import gregtechmod.api.MetaTileEntity;
import gregtechmod.common.tileentities.GT_MetaTileEntity_FusionEnergyInjector;
import gregtechmod.common.tileentities.GT_MetaTileEntity_FusionExtractor;
import gregtechmod.common.tileentities.GT_MetaTileEntity_FusionInjector;
import java.util.ArrayList;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.ForgeDirection;
import net.minecraftforge.liquids.LiquidContainerRegistry;
import net.minecraftforge.liquids.LiquidStack;

public class GT_MetaTileEntity_FusionComputer
extends MetaTileEntity {
    public int mProgresstime = 0;
    public int mMaxProgresstime = 0;
    public int mEUt = 0;
    public int mUpdate = 100;
    private ItemStack mOutputItem1;
    public boolean mMachine = true;
    private ArrayList<BaseMetaTileEntity> mPlasmaExtractors = new ArrayList<BaseMetaTileEntity>();
    private ArrayList<BaseMetaTileEntity> mEnergyInjectors = new ArrayList<BaseMetaTileEntity>();
    private ArrayList<BaseMetaTileEntity> mPrimaryInjectors = new ArrayList<BaseMetaTileEntity>();
    private ArrayList<BaseMetaTileEntity> mSecondaryInjectors = new ArrayList<BaseMetaTileEntity>();

    public GT_MetaTileEntity_FusionComputer(int aID, String mName, String mNameRegional) {
        super(aID, mName, mNameRegional);
    }

    public GT_MetaTileEntity_FusionComputer() {
    }

    @Override
    public boolean isSimpleMachine() {
        return false;
    }

    @Override
    public boolean isValidSlot(int aIndex) {
        return false;
    }

    @Override
    public boolean isFacingValid(int aFacing) {
        return aFacing > 1;
    }

    @Override
    public int getInvSize() {
        return 1;
    }

    @Override
    public int maxEUStore() {
        return 160000000;
    }

    @Override
    public int getEUVar() {
        return this.getStoredEU();
    }

    @Override
    public void onRightclick(EntityPlayer aPlayer) {
        this.mBaseMetaTileEntity.openGUI(aPlayer, 143);
    }

    @Override
    public boolean isAccessAllowed(EntityPlayer aPlayer) {
        return true;
    }

    @Override
    public MetaTileEntity newMetaEntity(BaseMetaTileEntity aTileEntity) {
        return new GT_MetaTileEntity_FusionComputer();
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
        this.mUpdate = 100;
        this.mEUt = aNBT.getInteger("mEUt");
        this.mProgresstime = aNBT.getInteger("mProgresstime");
        this.mMaxProgresstime = aNBT.getInteger("mMaxProgresstime");
        NBTTagCompound tNBT1 = (NBTTagCompound)aNBT.getTag("mOutputItem1");
        if (tNBT1 != null) {
            this.mOutputItem1 = ItemStack.loadItemStackFromNBT((NBTTagCompound)tNBT1);
        }
    }

    private void setComputerOf(MetaTileEntity aMetaTileEntity, boolean setreset) {
        if (aMetaTileEntity != null) {
            if (aMetaTileEntity instanceof GT_MetaTileEntity_FusionInjector) {
                BaseMetaTileEntity baseMetaTileEntity = ((GT_MetaTileEntity_FusionInjector)aMetaTileEntity).mFusionComputer = setreset ? this.mBaseMetaTileEntity : null;
                if (setreset) {
                    if (aMetaTileEntity.mBaseMetaTileEntity.yCoord > this.mBaseMetaTileEntity.yCoord) {
                        this.mPrimaryInjectors.add(aMetaTileEntity.mBaseMetaTileEntity);
                    } else {
                        this.mSecondaryInjectors.add(aMetaTileEntity.mBaseMetaTileEntity);
                    }
                }
            }
            if (aMetaTileEntity instanceof GT_MetaTileEntity_FusionEnergyInjector) {
                BaseMetaTileEntity baseMetaTileEntity = ((GT_MetaTileEntity_FusionEnergyInjector)aMetaTileEntity).mFusionComputer = setreset ? this.mBaseMetaTileEntity : null;
                if (setreset) {
                    this.mEnergyInjectors.add(aMetaTileEntity.mBaseMetaTileEntity);
                }
            }
            if (aMetaTileEntity instanceof GT_MetaTileEntity_FusionExtractor) {
                BaseMetaTileEntity baseMetaTileEntity = ((GT_MetaTileEntity_FusionExtractor)aMetaTileEntity).mFusionComputer = setreset ? this.mBaseMetaTileEntity : null;
                if (setreset) {
                    this.mPlasmaExtractors.add(aMetaTileEntity.mBaseMetaTileEntity);
                }
            }
        }
    }

    private void reset() {
        for (BaseMetaTileEntity tTileEntity : this.mPlasmaExtractors) {
            this.setComputerOf(tTileEntity.mMetaTileEntity, false);
        }
        for (BaseMetaTileEntity tTileEntity : this.mPrimaryInjectors) {
            this.setComputerOf(tTileEntity.mMetaTileEntity, false);
        }
        for (BaseMetaTileEntity tTileEntity : this.mSecondaryInjectors) {
            this.setComputerOf(tTileEntity.mMetaTileEntity, false);
        }
        for (BaseMetaTileEntity tTileEntity : this.mEnergyInjectors) {
            this.setComputerOf(tTileEntity.mMetaTileEntity, false);
        }
        this.mPlasmaExtractors = new ArrayList();
        this.mPrimaryInjectors = new ArrayList();
        this.mSecondaryInjectors = new ArrayList();
        this.mEnergyInjectors = new ArrayList();
    }

    private boolean checkMachine() {
        this.reset();
        int xCenter = this.mBaseMetaTileEntity.xCoord + ForgeDirection.getOrientation((int)this.mBaseMetaTileEntity.getFacing()).offsetX * 5;
        int yCenter = this.mBaseMetaTileEntity.yCoord;
        int zCenter = this.mBaseMetaTileEntity.zCoord + ForgeDirection.getOrientation((int)this.mBaseMetaTileEntity.getFacing()).offsetZ * 5;
        if ((this.isAdvancedMachineCasing(xCenter + 5, yCenter, zCenter) || xCenter + 5 == this.mBaseMetaTileEntity.xCoord) && (this.isAdvancedMachineCasing(xCenter - 5, yCenter, zCenter) || xCenter - 5 == this.mBaseMetaTileEntity.xCoord) && (this.isAdvancedMachineCasing(xCenter, yCenter, zCenter + 5) || zCenter + 5 == this.mBaseMetaTileEntity.zCoord) && (this.isAdvancedMachineCasing(xCenter, yCenter, zCenter - 5) || zCenter - 5 == this.mBaseMetaTileEntity.zCoord) && this.checkCoils(xCenter, yCenter, zCenter) && this.checkHulls(xCenter, yCenter, zCenter) && this.checkUpperOrLowerHulls(xCenter, yCenter + 1, zCenter) && this.checkUpperOrLowerHulls(xCenter, yCenter - 1, zCenter) && this.addIfEnergyInjector(xCenter + 4, yCenter, zCenter + 3) && this.addIfEnergyInjector(xCenter + 4, yCenter, zCenter - 3) && this.addIfEnergyInjector(xCenter + 4, yCenter, zCenter + 5) && this.addIfEnergyInjector(xCenter + 4, yCenter, zCenter - 5) && this.addIfEnergyInjector(xCenter - 4, yCenter, zCenter + 3) && this.addIfEnergyInjector(xCenter - 4, yCenter, zCenter - 3) && this.addIfEnergyInjector(xCenter - 4, yCenter, zCenter + 5) && this.addIfEnergyInjector(xCenter - 4, yCenter, zCenter - 5) && this.addIfEnergyInjector(xCenter + 3, yCenter, zCenter + 4) && this.addIfEnergyInjector(xCenter - 3, yCenter, zCenter + 4) && this.addIfEnergyInjector(xCenter + 5, yCenter, zCenter + 4) && this.addIfEnergyInjector(xCenter - 5, yCenter, zCenter + 4) && this.addIfEnergyInjector(xCenter + 3, yCenter, zCenter - 4) && this.addIfEnergyInjector(xCenter - 3, yCenter, zCenter - 4) && this.addIfEnergyInjector(xCenter + 5, yCenter, zCenter - 4) && this.addIfEnergyInjector(xCenter - 5, yCenter, zCenter - 4) && this.addIfExtractor(xCenter + 1, yCenter, zCenter - 5) && this.addIfExtractor(xCenter + 1, yCenter, zCenter + 5) && this.addIfExtractor(xCenter - 1, yCenter, zCenter - 5) && this.addIfExtractor(xCenter - 1, yCenter, zCenter + 5) && this.addIfExtractor(xCenter + 1, yCenter, zCenter - 7) && this.addIfExtractor(xCenter + 1, yCenter, zCenter + 7) && this.addIfExtractor(xCenter - 1, yCenter, zCenter - 7) && this.addIfExtractor(xCenter - 1, yCenter, zCenter + 7) && this.addIfExtractor(xCenter + 5, yCenter, zCenter - 1) && this.addIfExtractor(xCenter + 5, yCenter, zCenter + 1) && this.addIfExtractor(xCenter - 5, yCenter, zCenter - 1) && this.addIfExtractor(xCenter - 5, yCenter, zCenter + 1) && this.addIfExtractor(xCenter + 7, yCenter, zCenter - 1) && this.addIfExtractor(xCenter + 7, yCenter, zCenter + 1) && this.addIfExtractor(xCenter - 7, yCenter, zCenter - 1) && this.addIfExtractor(xCenter - 7, yCenter, zCenter + 1) && this.addIfInjector(xCenter + 1, yCenter + 1, zCenter - 6) && this.addIfInjector(xCenter + 1, yCenter + 1, zCenter + 6) && this.addIfInjector(xCenter - 1, yCenter + 1, zCenter - 6) && this.addIfInjector(xCenter - 1, yCenter + 1, zCenter + 6) && this.addIfInjector(xCenter - 6, yCenter + 1, zCenter + 1) && this.addIfInjector(xCenter + 6, yCenter + 1, zCenter + 1) && this.addIfInjector(xCenter - 6, yCenter + 1, zCenter - 1) && this.addIfInjector(xCenter + 6, yCenter + 1, zCenter - 1) && this.addIfInjector(xCenter + 1, yCenter - 1, zCenter - 6) && this.addIfInjector(xCenter + 1, yCenter - 1, zCenter + 6) && this.addIfInjector(xCenter - 1, yCenter - 1, zCenter - 6) && this.addIfInjector(xCenter - 1, yCenter - 1, zCenter + 6) && this.addIfInjector(xCenter - 6, yCenter - 1, zCenter + 1) && this.addIfInjector(xCenter + 6, yCenter - 1, zCenter + 1) && this.addIfInjector(xCenter - 6, yCenter - 1, zCenter - 1) && this.addIfInjector(xCenter + 6, yCenter - 1, zCenter - 1)) {
            return true;
        }
        this.reset();
        return false;
    }

    private boolean checkCoils(int aX, int aY, int aZ) {
        return this.isFusionCoil(aX + 6, aY, aZ - 1) && this.isFusionCoil(aX + 6, aY, aZ) && this.isFusionCoil(aX + 6, aY, aZ + 1) && this.isFusionCoil(aX + 5, aY, aZ - 3) && this.isFusionCoil(aX + 5, aY, aZ - 2) && this.isFusionCoil(aX + 5, aY, aZ + 2) && this.isFusionCoil(aX + 5, aY, aZ + 3) && this.isFusionCoil(aX + 4, aY, aZ - 4) && this.isFusionCoil(aX + 4, aY, aZ + 4) && this.isFusionCoil(aX + 3, aY, aZ - 5) && this.isFusionCoil(aX + 3, aY, aZ + 5) && this.isFusionCoil(aX + 2, aY, aZ - 5) && this.isFusionCoil(aX + 2, aY, aZ + 5) && this.isFusionCoil(aX + 1, aY, aZ - 6) && this.isFusionCoil(aX + 1, aY, aZ + 6) && this.isFusionCoil(aX, aY, aZ - 6) && this.isFusionCoil(aX, aY, aZ + 6) && this.isFusionCoil(aX - 1, aY, aZ - 6) && this.isFusionCoil(aX - 1, aY, aZ + 6) && this.isFusionCoil(aX - 2, aY, aZ - 5) && this.isFusionCoil(aX - 2, aY, aZ + 5) && this.isFusionCoil(aX - 3, aY, aZ - 5) && this.isFusionCoil(aX - 3, aY, aZ + 5) && this.isFusionCoil(aX - 4, aY, aZ - 4) && this.isFusionCoil(aX - 4, aY, aZ + 4) && this.isFusionCoil(aX - 5, aY, aZ - 3) && this.isFusionCoil(aX - 5, aY, aZ - 2) && this.isFusionCoil(aX - 5, aY, aZ + 2) && this.isFusionCoil(aX - 5, aY, aZ + 3) && this.isFusionCoil(aX - 6, aY, aZ - 1) && this.isFusionCoil(aX - 6, aY, aZ) && this.isFusionCoil(aX - 6, aY, aZ + 1);
    }

    private boolean checkUpperOrLowerHulls(int aX, int aY, int aZ) {
        return this.isAdvancedMachineCasing(aX + 6, aY, aZ) && this.isAdvancedMachineCasing(aX + 5, aY, aZ - 3) && this.isAdvancedMachineCasing(aX + 5, aY, aZ - 2) && this.isAdvancedMachineCasing(aX + 5, aY, aZ + 2) && this.isAdvancedMachineCasing(aX + 5, aY, aZ + 3) && this.isAdvancedMachineCasing(aX + 4, aY, aZ - 4) && this.isAdvancedMachineCasing(aX + 4, aY, aZ + 4) && this.isAdvancedMachineCasing(aX + 3, aY, aZ - 5) && this.isAdvancedMachineCasing(aX + 3, aY, aZ + 5) && this.isAdvancedMachineCasing(aX + 2, aY, aZ - 5) && this.isAdvancedMachineCasing(aX + 2, aY, aZ + 5) && this.isAdvancedMachineCasing(aX, aY, aZ - 6) && this.isAdvancedMachineCasing(aX, aY, aZ + 6) && this.isAdvancedMachineCasing(aX - 2, aY, aZ - 5) && this.isAdvancedMachineCasing(aX - 2, aY, aZ + 5) && this.isAdvancedMachineCasing(aX - 3, aY, aZ - 5) && this.isAdvancedMachineCasing(aX - 3, aY, aZ + 5) && this.isAdvancedMachineCasing(aX - 4, aY, aZ - 4) && this.isAdvancedMachineCasing(aX - 4, aY, aZ + 4) && this.isAdvancedMachineCasing(aX - 5, aY, aZ - 3) && this.isAdvancedMachineCasing(aX - 5, aY, aZ - 2) && this.isAdvancedMachineCasing(aX - 5, aY, aZ + 2) && this.isAdvancedMachineCasing(aX - 5, aY, aZ + 3) && this.isAdvancedMachineCasing(aX - 6, aY, aZ);
    }

    private boolean checkHulls(int aX, int aY, int aZ) {
        return this.isAdvancedMachineCasing(aX + 6, aY, aZ - 3) && this.isAdvancedMachineCasing(aX + 6, aY, aZ - 2) && this.isAdvancedMachineCasing(aX + 6, aY, aZ + 2) && this.isAdvancedMachineCasing(aX + 6, aY, aZ + 3) && this.isAdvancedMachineCasing(aX + 3, aY, aZ - 6) && this.isAdvancedMachineCasing(aX + 3, aY, aZ + 6) && this.isAdvancedMachineCasing(aX + 2, aY, aZ - 6) && this.isAdvancedMachineCasing(aX + 2, aY, aZ + 6) && this.isAdvancedMachineCasing(aX - 2, aY, aZ - 6) && this.isAdvancedMachineCasing(aX - 2, aY, aZ + 6) && this.isAdvancedMachineCasing(aX - 3, aY, aZ - 6) && this.isAdvancedMachineCasing(aX - 3, aY, aZ + 6) && this.isAdvancedMachineCasing(aX - 7, aY, aZ) && this.isAdvancedMachineCasing(aX + 7, aY, aZ) && this.isAdvancedMachineCasing(aX, aY, aZ - 7) && this.isAdvancedMachineCasing(aX, aY, aZ + 7) && this.isAdvancedMachineCasing(aX - 6, aY, aZ - 3) && this.isAdvancedMachineCasing(aX - 6, aY, aZ - 2) && this.isAdvancedMachineCasing(aX - 6, aY, aZ + 2) && this.isAdvancedMachineCasing(aX - 6, aY, aZ + 3) && this.isAdvancedMachineCasing(aX - 4, aY, aZ - 2) && this.isAdvancedMachineCasing(aX - 4, aY, aZ + 2) && this.isAdvancedMachineCasing(aX + 4, aY, aZ - 2) && this.isAdvancedMachineCasing(aX + 4, aY, aZ + 2) && this.isAdvancedMachineCasing(aX - 2, aY, aZ - 4) && this.isAdvancedMachineCasing(aX - 2, aY, aZ + 4) && this.isAdvancedMachineCasing(aX + 2, aY, aZ - 4) && this.isAdvancedMachineCasing(aX + 2, aY, aZ + 4);
    }

    private boolean addIfEnergyInjector(int aX, int aY, int aZ) {
        if (this.isEnergyInjector(aX, aY, aZ)) {
            this.setComputerOf(this.getMetaTileEntity(aX, aY, aZ), true);
            return true;
        }
        return this.isAdvancedMachineCasing(aX, aY, aZ);
    }

    private boolean addIfInjector(int aX, int aY, int aZ) {
        if (this.isInjector(aX, aY, aZ)) {
            this.setComputerOf(this.getMetaTileEntity(aX, aY, aZ), true);
            return true;
        }
        return this.isAdvancedMachineCasing(aX, aY, aZ);
    }

    private boolean addIfExtractor(int aX, int aY, int aZ) {
        if (this.isExtractor(aX, aY, aZ)) {
            this.setComputerOf(this.getMetaTileEntity(aX, aY, aZ), true);
            return true;
        }
        return this.isAdvancedMachineCasing(aX, aY, aZ);
    }

    private boolean isAdvancedMachineCasing(int aX, int aY, int aZ) {
        return this.mBaseMetaTileEntity.getBlockID(aX, aY, aZ) == GT_Mod.instance.mBlocks[0].blockID && this.mBaseMetaTileEntity.getMetaID(aX, aY, aZ) == 15;
    }

    private boolean isFusionCoil(int aX, int aY, int aZ) {
        return this.mBaseMetaTileEntity.getBlockID(aX, aY, aZ) == GT_Mod.instance.mBlocks[0].blockID && this.mBaseMetaTileEntity.getMetaID(aX, aY, aZ) == 1;
    }

    private boolean isEnergyInjector(int aX, int aY, int aZ) {
        MetaTileEntity tMetaTileEntity = this.getMetaTileEntity(aX, aY, aZ);
        if (tMetaTileEntity == null) {
            return false;
        }
        return tMetaTileEntity instanceof GT_MetaTileEntity_FusionEnergyInjector;
    }

    private boolean isInjector(int aX, int aY, int aZ) {
        MetaTileEntity tMetaTileEntity = this.getMetaTileEntity(aX, aY, aZ);
        if (tMetaTileEntity == null) {
            return false;
        }
        return tMetaTileEntity instanceof GT_MetaTileEntity_FusionInjector;
    }

    private boolean isExtractor(int aX, int aY, int aZ) {
        MetaTileEntity tMetaTileEntity = this.getMetaTileEntity(aX, aY, aZ);
        if (tMetaTileEntity == null) {
            return false;
        }
        return tMetaTileEntity instanceof GT_MetaTileEntity_FusionExtractor;
    }

    private MetaTileEntity getMetaTileEntity(int aX, int aY, int aZ) {
        TileEntity tTileEntity = this.mBaseMetaTileEntity.getTileEntity(aX, aY, aZ);
        if (!(tTileEntity instanceof BaseMetaTileEntity)) {
            return null;
        }
        if (!((BaseMetaTileEntity)tTileEntity).hasValidMetaTileEntity()) {
            return null;
        }
        return ((BaseMetaTileEntity)tTileEntity).mMetaTileEntity;
    }

    @Override
    public void onMachineBlockUpdate() {
        this.mUpdate = 100;
    }

    @Override
    public void onPostTick() {
        if (this.mBaseMetaTileEntity.isServerSide()) {
            if (this.mUpdate-- == 0) {
                this.mMachine = this.checkMachine();
            }
            if (this.mMaxProgresstime > 0) {
                if (this.mMachine && this.decreaseStoredEU(-this.mEUt)) {
                    if (++this.mProgresstime > this.mMaxProgresstime) {
                        this.addOutput(this.mOutputItem1);
                        this.mOutputItem1 = null;
                        this.mProgresstime = 0;
                        this.mMaxProgresstime = 0;
                        this.checkRecipe();
                    }
                } else {
                    this.addOutput(this.mOutputItem1);
                    this.mOutputItem1 = null;
                    this.mProgresstime = 0;
                    this.mMaxProgresstime = 0;
                }
            } else {
                this.checkRecipe();
            }
            this.mBaseMetaTileEntity.mActive = this.mMachine && this.mMaxProgresstime > 0;
        }
    }

    private boolean checkRecipe() {
        if (!this.mMachine || this.mBaseMetaTileEntity.getRedstone()) {
            return false;
        }
        int tRecipe = GT_Recipe.findEqualFusionRecipeIndex(this.getPrimaryInput(), this.getSecondaryInput());
        if (tRecipe != -1 && this.consumeInput(((GT_Recipe)GT_Recipe.sFusionRecipes.get((int)tRecipe)).mInput1, ((GT_Recipe)GT_Recipe.sFusionRecipes.get((int)tRecipe)).mInput2, this.mBaseMetaTileEntity.mActive ? 0 : ((GT_Recipe)GT_Recipe.sFusionRecipes.get((int)tRecipe)).mStartEU)) {
            this.mMaxProgresstime = ((GT_Recipe)GT_Recipe.sFusionRecipes.get((int)tRecipe)).mDuration;
            this.mEUt = ((GT_Recipe)GT_Recipe.sFusionRecipes.get((int)tRecipe)).mEUt;
            this.mOutputItem1 = ((GT_Recipe)GT_Recipe.sFusionRecipes.get((int)tRecipe)).mOutput1 == null ? null : ((GT_Recipe)GT_Recipe.sFusionRecipes.get((int)tRecipe)).mOutput1.copy();
            return true;
        }
        return false;
    }

    private ItemStack getPrimaryInput() {
        for (BaseMetaTileEntity tTileEntity : this.mPrimaryInjectors) {
            ItemStack rStack;
            if (!tTileEntity.hasValidMetaTileEntity() || !(tTileEntity.mMetaTileEntity instanceof GT_MetaTileEntity_FusionInjector) || (rStack = ((GT_MetaTileEntity_FusionInjector)tTileEntity.mMetaTileEntity).getMaterial()) == null) continue;
            return rStack;
        }
        return null;
    }

    private ItemStack getSecondaryInput() {
        for (BaseMetaTileEntity tTileEntity : this.mSecondaryInjectors) {
            ItemStack rStack;
            if (!tTileEntity.hasValidMetaTileEntity() || !(tTileEntity.mMetaTileEntity instanceof GT_MetaTileEntity_FusionInjector) || (rStack = ((GT_MetaTileEntity_FusionInjector)tTileEntity.mMetaTileEntity).getMaterial()) == null) continue;
            return rStack;
        }
        return null;
    }

    private int getStoredEU() {
        int rEU = 0;
        for (BaseMetaTileEntity tTileEntity : this.mEnergyInjectors) {
            rEU += tTileEntity.getStored();
        }
        return rEU;
    }

    private boolean decreaseStoredEU(int aEU) {
        if (aEU <= 0) {
            return true;
        }
        if (this.getStoredEU() < aEU) {
            return false;
        }
        for (BaseMetaTileEntity tTileEntity : this.mEnergyInjectors) {
            if (aEU > tTileEntity.getStored()) {
                aEU -= tTileEntity.getStored();
                if (tTileEntity.decreaseStoredEU(tTileEntity.getStored(), true)) continue;
                return false;
            }
            return tTileEntity.decreaseStoredEU(aEU, true);
        }
        return false;
    }

    private boolean consumeInput(ItemStack aInput1, ItemStack aInput2, int aEU) {
        if (aInput1 != null && aInput2 != null && (aEU <= 0 || this.getStoredEU() >= aEU)) {
            ItemStack tStack;
            for (BaseMetaTileEntity tTileEntity : this.mPrimaryInjectors) {
                if (!tTileEntity.hasValidMetaTileEntity() || !(tTileEntity.mMetaTileEntity instanceof GT_MetaTileEntity_FusionInjector) || (tStack = ((GT_MetaTileEntity_FusionInjector)tTileEntity.mMetaTileEntity).getMaterial()) == null || !tStack.isItemEqual(aInput1) || tStack.stackSize < aInput1.stackSize) continue;
                for (BaseMetaTileEntity tTileEntity2 : this.mSecondaryInjectors) {
                    if (!tTileEntity2.hasValidMetaTileEntity() || !(tTileEntity2.mMetaTileEntity instanceof GT_MetaTileEntity_FusionInjector) || !((GT_MetaTileEntity_FusionInjector)tTileEntity2.mMetaTileEntity).consumeMaterial(aInput2)) continue;
                    return this.decreaseStoredEU(aEU) && ((GT_MetaTileEntity_FusionInjector)tTileEntity.mMetaTileEntity).consumeMaterial(aInput1);
                }
                return false;
            }
            for (BaseMetaTileEntity tTileEntity : this.mSecondaryInjectors) {
                if (!tTileEntity.hasValidMetaTileEntity() || !(tTileEntity.mMetaTileEntity instanceof GT_MetaTileEntity_FusionInjector) || (tStack = ((GT_MetaTileEntity_FusionInjector)tTileEntity.mMetaTileEntity).getMaterial()) == null || !tStack.isItemEqual(aInput1) || tStack.stackSize < aInput1.stackSize) continue;
                for (BaseMetaTileEntity tTileEntity2 : this.mPrimaryInjectors) {
                    if (!tTileEntity2.hasValidMetaTileEntity() || !(tTileEntity2.mMetaTileEntity instanceof GT_MetaTileEntity_FusionInjector) || !((GT_MetaTileEntity_FusionInjector)tTileEntity2.mMetaTileEntity).consumeMaterial(aInput2)) continue;
                    return this.decreaseStoredEU(aEU) && ((GT_MetaTileEntity_FusionInjector)tTileEntity.mMetaTileEntity).consumeMaterial(aInput1);
                }
                return false;
            }
        }
        return false;
    }

    private void addOutput(ItemStack aOutput) {
        if (aOutput == null) {
            return;
        }
        LiquidStack tLiquid = LiquidContainerRegistry.getLiquidForFilledItem((ItemStack)aOutput);
        if (tLiquid == null) {
            for (BaseMetaTileEntity tTileEntity : this.mPlasmaExtractors) {
                ItemStack tStack = tTileEntity.getStackInSlot(1);
                if (tStack == null) {
                    tTileEntity.setInventorySlotContents(1, aOutput.copy());
                    return;
                }
                if (!tStack.isItemEqual(aOutput) || tStack.stackSize + aOutput.stackSize > tStack.getMaxStackSize()) continue;
                tStack.stackSize += aOutput.stackSize;
                return;
            }
        } else {
            for (BaseMetaTileEntity tTileEntity : this.mPlasmaExtractors) {
                if (tTileEntity.mMetaTileEntity.fill(tLiquid, false) != tLiquid.amount) continue;
                tTileEntity.mMetaTileEntity.fill(tLiquid, true);
                return;
            }
        }
    }

    @Override
    public int getInvSideIndex(ForgeDirection aSide, int aFacing) {
        return 0;
    }

    @Override
    public int getInvSideLength(ForgeDirection aSide, int aFacing) {
        return 0;
    }

    @Override
    public int getTextureIndex(int aSide, int aFacing, boolean aActive, boolean aRedstone) {
        if (aSide != aFacing) {
            return aActive ? 20 : 19;
        }
        return 48 + GT_Mod.Randomizer.nextInt(16);
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
        return "FUUUUUUU-SION, HAH!";
    }
}

