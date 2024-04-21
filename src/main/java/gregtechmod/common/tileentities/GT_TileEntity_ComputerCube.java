/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  ic2.api.IReactor
 *  ic2.api.IReactorComponent
 *  ic2.core.IC2
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.item.EntityItem
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.util.ChunkCoordinates
 *  net.minecraft.world.World
 *  net.minecraftforge.common.ForgeDirection
 */
package gregtechmod.common.tileentities;

import gregtechmod.GT_Mod;
import gregtechmod.api.GT_Recipe;
import gregtechmod.common.GT_ComputercubeDescription;
import gregtechmod.common.GT_LanguageManager;
import gregtechmod.common.GT_ModHandler;
import gregtechmod.common.items.GT_MetaItem_Cell;
import gregtechmod.common.tileentities.GT_TileEntityMetaID_Machine;
import ic2.api.IReactor;
import ic2.api.IReactorComponent;
import ic2.core.IC2;
import java.util.ArrayList;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;

public class GT_TileEntity_ComputerCube
extends GT_TileEntityMetaID_Machine
implements IReactor {
    public static ArrayList sReactorList;
    public boolean mStarted = false;
    public int mMode = 0;
    public int mHeat = 0;
    public int mEUOut = 0;
    public int mMaxHeat = 10000;
    public int mEU = 0;
    public int mProgress = 0;
    public int mEUTimer = 0;
    public int mEULast1 = 0;
    public int mEULast2 = 0;
    public int mEULast3 = 0;
    public int mEULast4 = 0;
    public float mHEM = 1.0f;
    public float mExplosionStrength = 0.0f;

    @Override
    public boolean isAccessible(EntityPlayer aPlayer) {
        ItemStack tStack = aPlayer.getCurrentEquippedItem();
        if (tStack == null) {
            return true;
        }
        return !tStack.isItemEqual(GT_Mod.getGregTechItem(43, 1, 0));
    }

    @Override
    public boolean isEnetInput() {
        return true;
    }

    @Override
    public boolean isInputFacing(short aDirection) {
        return true;
    }

    @Override
    public int maxEUStore() {
        return 10000;
    }

    @Override
    public int maxEUInput() {
        return 32;
    }

    @Override
    public boolean ownerControl() {
        return true;
    }

    @Override
    public int getInventorySlotCount() {
        return 114;
    }

    @Override
    public boolean isValidSlot(int aIndex) {
        return aIndex > 53 && aIndex < 58;
    }

    public void saveNuclearReactor() {
        for (int i = 0; i < 54; ++i) {
            this.mInventory[i + 59] = this.mInventory[i] == null ? null : this.mInventory[i].copy();
        }
    }

    public void loadNuclearReactor() {
        for (int i = 0; i < 54; ++i) {
            this.mInventory[i] = this.mInventory[i + 59] == null ? null : this.mInventory[i + 59].copy();
        }
    }

    public void reset() {
        int i;
        this.mEU = 0;
        this.mHeat = 0;
        this.mEUOut = 0;
        this.mMaxHeat = 10000;
        this.mHEM = 1.0f;
        this.mExplosionStrength = 0.0f;
        this.mProgress = 0;
        this.mInventory[113] = null;
        for (i = 0; i < 54; ++i) {
            this.mInventory[i] = null;
            this.mInventory[i + 59] = null;
        }
        for (i = 54; i < 58; ++i) {
            if (this.mInventory[i] == null) continue;
            if (!this.worldObj.isRemote) {
                this.worldObj.spawnEntityInWorld((Entity)new EntityItem(this.worldObj, (double)this.xCoord + 0.5, (double)this.yCoord + 0.5, (double)this.zCoord + 0.5, this.mInventory[i]));
            }
            this.mInventory[i] = null;
        }
    }

    public void switchModeForward() {
        this.mMode = (this.mMode + 1) % 7;
        this.switchMode();
    }

    public void switchModeBackward() {
        --this.mMode;
        if (this.mMode < 0) {
            this.mMode = 6;
        }
        this.switchMode();
    }

    private void switchMode() {
        this.reset();
        if (this.mMode == 1 && !GT_Mod.instance.mReactorplanner) {
            this.switchMode();
            return;
        }
        if (this.mMode == 2 && !GT_Mod.instance.mSeedscanner) {
            this.switchMode();
            return;
        }
        if (this.mMode == 3) {
            this.showCentrifugeRecipe(0);
        }
        if (this.mMode == 4) {
            this.showFusionRecipe(0);
        }
        if (this.mMode == 5) {
            this.showDescription(0);
        }
        if (this.mMode == 6) {
            this.showElectrolyzerRecipe(0);
        }
        this.worldObj.addBlockEvent(this.xCoord, this.yCoord, this.zCoord, GT_Mod.instance.mBlocks[1].blockID, 10, this.mMode);
        this.worldObj.addBlockEvent(this.xCoord, this.yCoord, this.zCoord, GT_Mod.instance.mBlocks[1].blockID, 11, this.mMaxHeat);
    }

    public void showDescription(int aIndex) {
        this.mExplosionStrength = 0.0f;
        if (aIndex >= GT_ComputercubeDescription.sDescriptions.size() || aIndex < 0) {
            aIndex = 0;
        }
        this.mInventory[59] = ((GT_ComputercubeDescription)GT_ComputercubeDescription.sDescriptions.get((int)aIndex)).mStacks[0] == null ? null : ((GT_ComputercubeDescription)GT_ComputercubeDescription.sDescriptions.get((int)aIndex)).mStacks[0].copy();
        this.mInventory[60] = ((GT_ComputercubeDescription)GT_ComputercubeDescription.sDescriptions.get((int)aIndex)).mStacks[1] == null ? null : ((GT_ComputercubeDescription)GT_ComputercubeDescription.sDescriptions.get((int)aIndex)).mStacks[1].copy();
        this.mInventory[61] = ((GT_ComputercubeDescription)GT_ComputercubeDescription.sDescriptions.get((int)aIndex)).mStacks[2] == null ? null : ((GT_ComputercubeDescription)GT_ComputercubeDescription.sDescriptions.get((int)aIndex)).mStacks[2].copy();
        this.mInventory[62] = ((GT_ComputercubeDescription)GT_ComputercubeDescription.sDescriptions.get((int)aIndex)).mStacks[3] == null ? null : ((GT_ComputercubeDescription)GT_ComputercubeDescription.sDescriptions.get((int)aIndex)).mStacks[3].copy();
        this.mInventory[63] = ((GT_ComputercubeDescription)GT_ComputercubeDescription.sDescriptions.get((int)aIndex)).mStacks[4] == null ? null : ((GT_ComputercubeDescription)GT_ComputercubeDescription.sDescriptions.get((int)aIndex)).mStacks[4].copy();
        if (((GT_ComputercubeDescription)GT_ComputercubeDescription.sDescriptions.get((int)aIndex)).mStacks[5] == null) {
            this.mInventory[64] = null;
        } else {
            this.mInventory[64] = ((GT_ComputercubeDescription)GT_ComputercubeDescription.sDescriptions.get((int)aIndex)).mStacks[5].copy();
            this.mExplosionStrength = 100.0f;
        }
        if (((GT_ComputercubeDescription)GT_ComputercubeDescription.sDescriptions.get((int)aIndex)).mStacks[6] == null) {
            this.mInventory[65] = null;
        } else {
            this.mInventory[65] = ((GT_ComputercubeDescription)GT_ComputercubeDescription.sDescriptions.get((int)aIndex)).mStacks[6].copy();
            this.mExplosionStrength = 100.0f;
        }
        if (((GT_ComputercubeDescription)GT_ComputercubeDescription.sDescriptions.get((int)aIndex)).mStacks[7] == null) {
            this.mInventory[66] = null;
        } else {
            this.mInventory[66] = ((GT_ComputercubeDescription)GT_ComputercubeDescription.sDescriptions.get((int)aIndex)).mStacks[7].copy();
            this.mExplosionStrength = 100.0f;
        }
        if (((GT_ComputercubeDescription)GT_ComputercubeDescription.sDescriptions.get((int)aIndex)).mStacks[8] == null) {
            this.mInventory[67] = null;
        } else {
            this.mInventory[67] = ((GT_ComputercubeDescription)GT_ComputercubeDescription.sDescriptions.get((int)aIndex)).mStacks[8].copy();
            this.mExplosionStrength = 100.0f;
        }
        if (((GT_ComputercubeDescription)GT_ComputercubeDescription.sDescriptions.get((int)aIndex)).mStacks[9] == null) {
            this.mInventory[68] = null;
        } else {
            this.mInventory[68] = ((GT_ComputercubeDescription)GT_ComputercubeDescription.sDescriptions.get((int)aIndex)).mStacks[9].copy();
            this.mExplosionStrength = 100.0f;
        }
        if (((GT_ComputercubeDescription)GT_ComputercubeDescription.sDescriptions.get((int)aIndex)).mStacks[10] == null) {
            this.mInventory[69] = null;
        } else {
            this.mInventory[69] = ((GT_ComputercubeDescription)GT_ComputercubeDescription.sDescriptions.get((int)aIndex)).mStacks[10].copy();
            this.mExplosionStrength = 100.0f;
        }
        if (((GT_ComputercubeDescription)GT_ComputercubeDescription.sDescriptions.get((int)aIndex)).mStacks[11] == null) {
            this.mInventory[70] = null;
        } else {
            this.mInventory[70] = ((GT_ComputercubeDescription)GT_ComputercubeDescription.sDescriptions.get((int)aIndex)).mStacks[11].copy();
            this.mExplosionStrength = 100.0f;
        }
        if (((GT_ComputercubeDescription)GT_ComputercubeDescription.sDescriptions.get((int)aIndex)).mStacks[12] == null) {
            this.mInventory[71] = null;
        } else {
            this.mInventory[71] = ((GT_ComputercubeDescription)GT_ComputercubeDescription.sDescriptions.get((int)aIndex)).mStacks[12].copy();
            this.mExplosionStrength = 100.0f;
        }
        if (((GT_ComputercubeDescription)GT_ComputercubeDescription.sDescriptions.get((int)aIndex)).mStacks[13] == null) {
            this.mInventory[72] = null;
        } else {
            this.mInventory[72] = ((GT_ComputercubeDescription)GT_ComputercubeDescription.sDescriptions.get((int)aIndex)).mStacks[13].copy();
            this.mExplosionStrength = 100.0f;
        }
        this.mMaxHeat = aIndex;
        this.worldObj.addBlockEvent(this.xCoord, this.yCoord, this.zCoord, GT_Mod.instance.mBlocks[1].blockID, 11, this.mMaxHeat);
    }

    public void switchDescriptionPageForward() {
        if (++this.mMaxHeat >= GT_ComputercubeDescription.sDescriptions.size()) {
            this.mMaxHeat = 0;
        }
        this.showDescription(this.mMaxHeat);
    }

    public void switchDescriptionPageBackward() {
        if (--this.mMaxHeat < 0) {
            this.mMaxHeat = GT_ComputercubeDescription.sDescriptions.size() - 1;
        }
        this.showDescription(this.mMaxHeat);
    }

    public void showCentrifugeRecipe(int aIndex) {
        GT_Recipe tRecipe;
        if (aIndex >= GT_Recipe.sCentrifugeRecipes.size() || aIndex < 0) {
            aIndex = 0;
        }
        if ((tRecipe = (GT_Recipe)GT_Recipe.sCentrifugeRecipes.get(aIndex)) != null) {
            this.mInventory[59] = tRecipe.mInput1 == null ? null : tRecipe.mInput1.copy();
            this.mInventory[60] = tRecipe.mInput2 == null ? null : tRecipe.mInput2.copy();
            this.mInventory[61] = tRecipe.mOutput1 == null ? null : tRecipe.mOutput1.copy();
            this.mInventory[62] = tRecipe.mOutput2 == null ? null : tRecipe.mOutput2.copy();
            this.mInventory[63] = tRecipe.mOutput3 == null ? null : tRecipe.mOutput3.copy();
            this.mInventory[64] = tRecipe.mOutput4 == null ? null : tRecipe.mOutput4.copy();
            this.mEU = tRecipe.mDuration * 5;
            this.mMaxHeat = aIndex;
        }
        this.worldObj.addBlockEvent(this.xCoord, this.yCoord, this.zCoord, GT_Mod.instance.mBlocks[1].blockID, 11, this.mMaxHeat);
    }

    public void switchCentrifugePageForward() {
        if (++this.mMaxHeat >= GT_Recipe.sCentrifugeRecipes.size()) {
            this.mMaxHeat = 0;
        }
        this.showCentrifugeRecipe(this.mMaxHeat);
    }

    public void switchCentrifugePageBackward() {
        if (--this.mMaxHeat < 0) {
            this.mMaxHeat = GT_Recipe.sCentrifugeRecipes.size() - 1;
        }
        this.showCentrifugeRecipe(this.mMaxHeat);
    }

    public void showElectrolyzerRecipe(int aIndex) {
        GT_Recipe tRecipe;
        if (aIndex >= GT_Recipe.sElectrolyzerRecipes.size() || aIndex < 0) {
            aIndex = 0;
        }
        if ((tRecipe = (GT_Recipe)GT_Recipe.sElectrolyzerRecipes.get(aIndex)) != null) {
            this.mInventory[59] = tRecipe.mInput1 == null ? null : tRecipe.mInput1.copy();
            this.mInventory[60] = tRecipe.mInput2 == null ? null : tRecipe.mInput2.copy();
            this.mInventory[61] = tRecipe.mOutput1 == null ? null : tRecipe.mOutput1.copy();
            this.mInventory[62] = tRecipe.mOutput2 == null ? null : tRecipe.mOutput2.copy();
            this.mInventory[63] = tRecipe.mOutput3 == null ? null : tRecipe.mOutput3.copy();
            this.mInventory[64] = tRecipe.mOutput4 == null ? null : tRecipe.mOutput4.copy();
            this.mEU = tRecipe.mDuration * tRecipe.mEUt;
            this.mMaxHeat = aIndex;
        }
        this.worldObj.addBlockEvent(this.xCoord, this.yCoord, this.zCoord, GT_Mod.instance.mBlocks[1].blockID, 11, this.mMaxHeat);
    }

    public void switchElectrolyzerPageForward() {
        if (++this.mMaxHeat >= GT_Recipe.sElectrolyzerRecipes.size()) {
            this.mMaxHeat = 0;
        }
        this.showElectrolyzerRecipe(this.mMaxHeat);
    }

    public void switchElectrolyzerPageBackward() {
        if (--this.mMaxHeat < 0) {
            this.mMaxHeat = GT_Recipe.sElectrolyzerRecipes.size() - 1;
        }
        this.showElectrolyzerRecipe(this.mMaxHeat);
    }

    public void showFusionRecipe(int aIndex) {
        GT_Recipe tRecipe;
        if (aIndex >= GT_Recipe.sFusionRecipes.size() || aIndex < 0) {
            aIndex = 0;
        }
        if ((tRecipe = (GT_Recipe)GT_Recipe.sFusionRecipes.get(aIndex)) != null) {
            this.mInventory[59] = tRecipe.mInput1 == null ? null : tRecipe.mInput1.copy();
            this.mInventory[60] = tRecipe.mInput2 == null ? null : tRecipe.mInput2.copy();
            this.mInventory[61] = tRecipe.mOutput1 == null ? null : tRecipe.mOutput1.copy();
            this.mEU = tRecipe.mStartEU;
            this.mEUOut = tRecipe.mEUt;
            this.mHeat = tRecipe.mDuration;
            this.mMaxHeat = aIndex;
        }
        this.worldObj.addBlockEvent(this.xCoord, this.yCoord, this.zCoord, GT_Mod.instance.mBlocks[1].blockID, 11, this.mMaxHeat);
    }

    public void switchFusionPageForward() {
        if (++this.mMaxHeat >= GT_Recipe.sFusionRecipes.size()) {
            this.mMaxHeat = 0;
        }
        this.showFusionRecipe(this.mMaxHeat);
    }

    public void switchFusionPageBackward() {
        if (--this.mMaxHeat < 0) {
            this.mMaxHeat = GT_Recipe.sFusionRecipes.size() - 1;
        }
        this.showFusionRecipe(this.mMaxHeat);
    }

    public void switchNuclearReactor() {
        if (this.mStarted) {
            this.stopNuclearReactor();
        } else {
            this.startNuclearReactor();
        }
    }

    public void startNuclearReactor() {
        this.mStarted = true;
        this.mHeat = 0;
        this.mEU = 0;
    }

    public void stopNuclearReactor() {
        this.mStarted = false;
    }

    @Override
    public void storeAdditionalData(NBTTagCompound aNBT) {
        aNBT.setInteger("mMode", this.mMode);
        aNBT.setInteger("mProgress", this.mProgress);
        aNBT.setBoolean("mStarted", this.mStarted);
        aNBT.setInteger("mEU", this.mEU);
        aNBT.setInteger("mHeat", this.mHeat);
        aNBT.setInteger("mEUOut", this.mEUOut);
        aNBT.setInteger("mMaxHeat", this.mMaxHeat);
        aNBT.setFloat("mHEM", this.mHEM);
        aNBT.setFloat("mExplosionStrength", this.mExplosionStrength);
    }

    @Override
    public void getAdditionalData(NBTTagCompound aNBT) {
        this.mMode = aNBT.getInteger("mMode");
        this.mProgress = aNBT.getInteger("mProgress");
        this.mStarted = aNBT.getBoolean("mStarted");
        this.mEU = aNBT.getInteger("mEU");
        this.mHeat = aNBT.getInteger("mHeat");
        this.mEUOut = aNBT.getInteger("mEUOut");
        this.mMaxHeat = aNBT.getInteger("mMaxHeat");
        this.mHEM = aNBT.getFloat("mHEM");
        this.mExplosionStrength = aNBT.getFloat("mExplosionStrength");
    }

    @Override
    public void onFirstTickUpdate() {
        if (sReactorList == null) {
            sReactorList = new ArrayList();
            sReactorList.add(GT_ModHandler.getIC2Item("reactorUraniumSimple", 1).getItem());
            sReactorList.add(GT_ModHandler.getIC2Item("reactorUraniumDual", 1).getItem());
            sReactorList.add(GT_ModHandler.getIC2Item("reactorUraniumQuad", 1).getItem());
            sReactorList.add(GT_ModHandler.getIC2Item("reactorIsotopeCell", 1).getItem());
            sReactorList.add(GT_Mod.instance.mItems[48]);
            sReactorList.add(GT_Mod.instance.mItems[49]);
            sReactorList.add(GT_Mod.instance.mItems[50]);
            sReactorList.add(GT_Mod.instance.mItems[51]);
            sReactorList.add(GT_Mod.instance.mItems[52]);
            sReactorList.add(GT_Mod.instance.mItems[53]);
            sReactorList.add(GT_ModHandler.getIC2Item("reactorReflector", 1).getItem());
            sReactorList.add(GT_ModHandler.getIC2Item("reactorReflectorThick", 1).getItem());
            sReactorList.add(GT_Mod.instance.mItems[40]);
            sReactorList.add(GT_ModHandler.getIC2Item("reactorCoolantSimple", 1).getItem());
            sReactorList.add(GT_ModHandler.getIC2Item("reactorCoolantTriple", 1).getItem());
            sReactorList.add(GT_ModHandler.getIC2Item("reactorCoolantSix", 1).getItem());
            sReactorList.add(GT_Mod.instance.mItems[34]);
            sReactorList.add(GT_Mod.instance.mItems[35]);
            sReactorList.add(GT_Mod.instance.mItems[36]);
            sReactorList.add(GT_ModHandler.getIC2Item("reactorCondensator", 1).getItem());
            sReactorList.add(GT_ModHandler.getIC2Item("reactorCondensatorLap", 1).getItem());
            sReactorList.add(GT_ModHandler.getIC2Item("reactorPlating", 1).getItem());
            sReactorList.add(GT_ModHandler.getIC2Item("reactorPlatingHeat", 1).getItem());
            sReactorList.add(GT_ModHandler.getIC2Item("reactorPlatingExplosive", 1).getItem());
            sReactorList.add(GT_ModHandler.getIC2Item("reactorVent", 1).getItem());
            sReactorList.add(GT_ModHandler.getIC2Item("reactorVentCore", 1).getItem());
            sReactorList.add(GT_ModHandler.getIC2Item("reactorVentGold", 1).getItem());
            sReactorList.add(GT_ModHandler.getIC2Item("reactorVentSpread", 1).getItem());
            sReactorList.add(GT_ModHandler.getIC2Item("reactorVentDiamond", 1).getItem());
            sReactorList.add(GT_ModHandler.getIC2Item("reactorHeatSwitch", 1).getItem());
            sReactorList.add(GT_ModHandler.getIC2Item("reactorHeatSwitchCore", 1).getItem());
            sReactorList.add(GT_ModHandler.getIC2Item("reactorHeatSwitchSpread", 1).getItem());
            sReactorList.add(GT_ModHandler.getIC2Item("reactorHeatSwitchDiamond", 1).getItem());
            sReactorList.add(GT_ModHandler.getIC2Item("reactorHeatpack", 1).getItem());
            for (int i = 0; i < Item.itemsList.length; ++i) {
                if (Item.itemsList[i] == null || !(Item.itemsList[i] instanceof IReactorComponent) || sReactorList.contains(Item.itemsList[i]) || Item.itemsList[i] instanceof GT_MetaItem_Cell) continue;
                sReactorList.add(Item.itemsList[i]);
            }
        }
    }

    @Override
    public void onPostTickUpdate() {
        if (!this.worldObj.isRemote) {
            if (this.mMode == 2) {
                if (this.mInventory[55] == null) {
                    this.mInventory[55] = this.mInventory[54];
                    this.mInventory[54] = null;
                }
                if (this.mInventory[57] == null) {
                    this.mInventory[57] = this.mInventory[56];
                    this.mInventory[56] = null;
                }
                if (GT_Mod.instance.mSeedscanner && this.mInventory[55] != null && this.mInventory[55].itemID == GT_ModHandler.getIC2Item((String)"cropSeed", (int)1).itemID && this.mInventory[55].getTagCompound() != null) {
                    if (this.mInventory[55].getTagCompound().getByte("scan") < 4) {
                        if (this.mProgress >= 100) {
                            this.mInventory[55].getTagCompound().setByte("scan", (byte)4);
                            this.mProgress = 0;
                        } else if (this.decreaseStoredEnergy(100, false)) {
                            ++this.mProgress;
                        }
                    } else {
                        this.mProgress = 0;
                        if (this.mInventory[56] == null) {
                            this.mInventory[56] = this.mInventory[55];
                            this.mInventory[55] = null;
                        }
                    }
                } else {
                    this.mProgress = 0;
                    if (this.mInventory[56] == null) {
                        this.mInventory[56] = this.mInventory[55];
                        this.mInventory[55] = null;
                    }
                }
            }
            if (this.mMode == 1 && GT_Mod.instance.mReactorplanner && this.mStarted && this.decreaseStoredEnergy(32, false)) {
                for (int i = 0; i < 25 && this.mStarted; ++i) {
                    this.mEUOut = 0;
                    this.mMaxHeat = 10000;
                    this.mHEM = 1.0f;
                    this.mExplosionStrength = 10.0f;
                    float tMultiplier = 1.0f;
                    for (int y = 0; y < 6; ++y) {
                        for (int x = 0; x < 9; ++x) {
                            ItemStack tStack = this.getStackInSlot(x + y * 9);
                            if (tStack == null) continue;
                            if (tStack.getItem() instanceof IReactorComponent) {
                                IReactorComponent tComponent = (IReactorComponent)tStack.getItem();
                                tComponent.processChamber((IReactor)this, tStack, x, y);
                                float tInfluence = ((IReactorComponent)tStack.getItem()).influenceExplosion((IReactor)this, tStack);
                                if (tInfluence > 0.0f && tInfluence < 1.0f) {
                                    tMultiplier *= tInfluence;
                                    continue;
                                }
                                this.mExplosionStrength += tInfluence;
                                continue;
                            }
                            if (tStack.isItemEqual(GT_ModHandler.getIC2Item("nearDepletedUraniumCell", 1)) || tStack.isItemEqual(GT_ModHandler.getIC2Item("reEnrichedUraniumCell", 1))) {
                                this.stopNuclearReactor();
                                continue;
                            }
                            this.setInventorySlotContents(x + y * 9, null);
                        }
                    }
                    this.mEUOut *= IC2.energyGeneratorNuclear;
                    if (this.mEUOut == 0 && this.mEUTimer++ > 20 || this.mHeat >= this.mMaxHeat) {
                        this.stopNuclearReactor();
                    }
                    if (this.mEUOut != 0) {
                        this.mEUTimer = 0;
                    }
                    this.mExplosionStrength *= this.mHEM * tMultiplier;
                    this.mEU += this.mEUOut * 20;
                    int tEU = this.mEULast1;
                    this.mEULast1 = this.mEULast2;
                    this.mEULast2 = this.mEULast3;
                    this.mEULast3 = this.mEULast4;
                    this.mEULast4 = this.mEUOut;
                    this.mEUOut = (this.mEUOut + this.mEULast1 + this.mEULast2 + this.mEULast3 + tEU) / 5;
                }
            }
            if (this.mTickTimer % 20L == 0L) {
                this.worldObj.addBlockEvent(this.xCoord, this.yCoord, this.zCoord, GT_Mod.instance.mBlocks[1].blockID, 10, this.mMode);
                this.worldObj.addBlockEvent(this.xCoord, this.yCoord, this.zCoord, GT_Mod.instance.mBlocks[1].blockID, 11, this.mMaxHeat);
            }
        }
    }

    @Override
    public void receiveClientEvent(int aEventID, int aValue) {
        super.receiveClientEvent(aEventID, aValue);
        if (this.worldObj.isRemote) {
            switch (aEventID) {
                case 10: {
                    this.mNeedsUpdate = true;
                    this.mMode = aValue;
                    break;
                }
                case 11: {
                    this.mMaxHeat = aValue;
                }
            }
        }
    }

    @Override
    public int getStartInventorySide(ForgeDirection aSide) {
        if (aSide == ForgeDirection.UP) {
            return 54;
        }
        if (aSide == ForgeDirection.DOWN) {
            return 54;
        }
        return 56;
    }

    @Override
    public int getSizeInventorySide(ForgeDirection aSide) {
        return 2;
    }

    @Override
    public String getInvName() {
        return GT_LanguageManager.mNameList1[4];
    }

    @Override
    public int getTexture(int aSide, int aMeta) {
        switch (this.mMode) {
            case 0: {
                return 8;
            }
            case 1: {
                return 46;
            }
            case 2: {
                return 45;
            }
        }
        return 48 + GT_Mod.Randomizer.nextInt(16);
    }

    public ChunkCoordinates getPosition() {
        return new ChunkCoordinates(this.xCoord, this.yCoord, this.zCoord);
    }

    public World getWorld() {
        return this.worldObj;
    }

    public int getHeat() {
        return this.mHeat;
    }

    public void setHeat(int aHeat) {
        this.mHeat = aHeat;
    }

    public int addHeat(int aAmount) {
        this.mHeat += aAmount;
        return this.mHeat;
    }

    public int getMaxHeat() {
        return this.mMaxHeat;
    }

    public void setMaxHeat(int aMaxHeat) {
        this.mMaxHeat = aMaxHeat;
    }

    public float getHeatEffectModifier() {
        return this.mHEM;
    }

    public void setHeatEffectModifier(float aHEM) {
        this.mHEM = aHEM;
    }

    @Override
    public int getOutput() {
        return this.mEUOut;
    }

    public int addOutput(int aEnergy) {
        this.mEUOut += aEnergy;
        return this.mEUOut;
    }

    public int getPulsePower() {
        return 1;
    }

    public ItemStack getItemAt(int x, int y) {
        if (x < 0 || x > 8 || y < 0 || y > 5) {
            return null;
        }
        return this.getStackInSlot(x + y * 9);
    }

    public void setItemAt(int x, int y, ItemStack aStack) {
        this.setInventorySlotContents(x + y * 9, aStack);
    }

    public void explode() {
        this.stopNuclearReactor();
    }

    public int getTickRate() {
        return 1;
    }

    public boolean produceEnergy() {
        return true;
    }
}

