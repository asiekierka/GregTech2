/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.world.World
 *  net.minecraftforge.common.ForgeDirection
 *  thaumcraft.api.EnumTag
 *  thaumcraft.api.ObjectTags
 *  thaumcraft.common.AuraManager
 */
package gregtechmod.common.tileentities;

import gregtechmod.api.BaseMetaTileEntity;
import gregtechmod.api.MetaTileEntity;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;
import thaumcraft.api.EnumTag;
import thaumcraft.api.ObjectTags;
import thaumcraft.common.AuraManager;

public class GT_MetaTileEntity_DragonEggEnergySiphon
extends MetaTileEntity {
    public static int sDragonEggEnergyPerTick = 128;
    public static boolean sAllowMultipleEggs = false;
    public static GT_MetaTileEntity_DragonEggEnergySiphon mActiveSiphon = null;

    public GT_MetaTileEntity_DragonEggEnergySiphon(int aID, String mName, String mNameRegional) {
        super(aID, mName, mNameRegional);
    }

    public GT_MetaTileEntity_DragonEggEnergySiphon() {
    }

    @Override
    public boolean unbreakable() {
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
    public boolean isEnetOutput() {
        return true;
    }

    @Override
    public boolean isOutputFacing(short aSide) {
        return aSide != 1;
    }

    @Override
    public int maxEUOutput() {
        return sDragonEggEnergyPerTick;
    }

    @Override
    public int getInvSize() {
        return 0;
    }

    @Override
    public int maxEUStore() {
        return sDragonEggEnergyPerTick;
    }

    @Override
    public void onRightclick(EntityPlayer aPlayer) {
    }

    @Override
    public boolean isAccessAllowed(EntityPlayer aPlayer) {
        return true;
    }

    @Override
    public MetaTileEntity newMetaEntity(BaseMetaTileEntity aTileEntity) {
        return new GT_MetaTileEntity_DragonEggEnergySiphon();
    }

    @Override
    public void saveNBTData(NBTTagCompound aNBT) {
    }

    @Override
    public void loadNBTData(NBTTagCompound aNBT) {
    }

    @Override
    public void onFirstTick() {
        mActiveSiphon = null;
    }

    @Override
    public void onPostTick() {
        if (!this.mBaseMetaTileEntity.worldObj.isRemote) {
            this.mBaseMetaTileEntity.mActive = false;
            if (this.hasEgg()) {
                this.mBaseMetaTileEntity.mActive = true;
                if (this.mBaseMetaTileEntity.increaseStoredEU(sDragonEggEnergyPerTick, false)) {
                    try {
                        ObjectTags tTags = new ObjectTags();
                        switch (this.mBaseMetaTileEntity.worldObj.rand.nextInt(1000)) {
                            case 0: {
                                tTags.add(EnumTag.MECHANISM, 3);
                                break;
                            }
                            case 1: {
                                tTags.add(EnumTag.CONTROL, 1);
                                break;
                            }
                            case 2: {
                                tTags.add(EnumTag.VOID, 1);
                                break;
                            }
                            case 3: {
                                tTags.add(EnumTag.FLUX, 2);
                                break;
                            }
                            case 4: {
                                tTags.add(EnumTag.ELDRITCH, 2);
                                break;
                            }
                            case 5: {
                                tTags.add(EnumTag.EXCHANGE, 1);
                                break;
                            }
                            case 6: {
                                tTags.add(EnumTag.MAGIC, 1);
                                break;
                            }
                            case 7: {
                                tTags.add(EnumTag.POWER, 1);
                                break;
                            }
                            case 8: {
                                tTags.add(EnumTag.MOTION, 3);
                                break;
                            }
                            case 9: {
                                tTags.add(EnumTag.SPIRIT, 5);
                                break;
                            }
                            default: {
                                tTags = null;
                            }
                        }
                        if (tTags != null) {
                            AuraManager.addFluxToClosest((World)this.mBaseMetaTileEntity.worldObj, (float)this.mBaseMetaTileEntity.xCoord, (float)this.mBaseMetaTileEntity.yCoord, (float)this.mBaseMetaTileEntity.zCoord, (ObjectTags)tTags);
                        }
                    }
                    catch (Throwable throwable) {
                        // empty catch block
                    }
                }
                if (mActiveSiphon != this && !sAllowMultipleEggs) {
                    if (mActiveSiphon == null || GT_MetaTileEntity_DragonEggEnergySiphon.mActiveSiphon.mBaseMetaTileEntity == null || GT_MetaTileEntity_DragonEggEnergySiphon.mActiveSiphon.mBaseMetaTileEntity.isInvalid() || !mActiveSiphon.hasEgg()) {
                        mActiveSiphon = this;
                    } else {
                        this.mBaseMetaTileEntity.doExplosion(Integer.MAX_VALUE);
                    }
                }
            } else if (mActiveSiphon == this) {
                mActiveSiphon = null;
            }
        }
    }

    @Override
    public String getMainInfo() {
        return this.mBaseMetaTileEntity.mActive ? "Active" : "Inactive";
    }

    @Override
    public String getSecondaryInfo() {
        return "Output: " + sDragonEggEnergyPerTick + " EU/t";
    }

    @Override
    public String getTertiaryInfo() {
        return "";
    }

    @Override
    public boolean isGivingInformation() {
        return true;
    }

    @Override
    protected String getDescription() {
        return "Harness the teleportation Power of the Dragon Egg!";
    }

    @Override
    public void inValidate() {
        if (mActiveSiphon == this) {
            mActiveSiphon = null;
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
        if (aSide == 1) {
            return 89;
        }
        return aActive ? 87 : 83;
    }

    public boolean hasEgg() {
        return Block.dragonEgg.blockID == this.mBaseMetaTileEntity.worldObj.getBlockId(this.mBaseMetaTileEntity.xCoord, this.mBaseMetaTileEntity.yCoord + 1, this.mBaseMetaTileEntity.zCoord);
    }
}

