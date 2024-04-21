/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  ic2.api.IElectricItem
 *  net.minecraft.enchantment.Enchantment
 *  net.minecraft.entity.item.EntityEnderCrystal
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemEnchantedBook
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.nbt.NBTTagList
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.world.World
 *  net.minecraftforge.common.ForgeDirection
 *  thaumcraft.api.EnumTag
 *  thaumcraft.api.ObjectTags
 *  thaumcraft.common.AuraManager
 *  thaumcraft.common.entities.EntityWisp
 */
package gregtechmod.common.tileentities;

import gregtechmod.GT_Mod;
import gregtechmod.api.BaseMetaTileEntity;
import gregtechmod.api.MetaTileEntity;
import gregtechmod.common.GT_Log;
import ic2.api.IElectricItem;
import java.util.ArrayList;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.item.EntityEnderCrystal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemEnchantedBook;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;
import thaumcraft.api.EnumTag;
import thaumcraft.api.ObjectTags;
import thaumcraft.common.AuraManager;
import thaumcraft.common.entities.monster.EntityWisp;

public class GT_MetaTileEntity_MagicEnergyAbsorber
extends MetaTileEntity {
    public static int sEnergyPerEnderCrystal = 32;
    public static int sEnergyFromVis = 12800;
    public static ArrayList sUsedDragonCrystalList;
    public EntityEnderCrystal mTargetedCrystal;
    public boolean isActive1 = false;
    public boolean isActive2 = false;

    public GT_MetaTileEntity_MagicEnergyAbsorber(int aID, String mName, String mNameRegional) {
        super(aID, mName, mNameRegional);
    }

    public GT_MetaTileEntity_MagicEnergyAbsorber() {
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
        return Math.max(128, sEnergyPerEnderCrystal);
    }

    @Override
    public int getInvSize() {
        return 3;
    }

    @Override
    public int maxEUStore() {
        return Math.max(10000, Math.max(sEnergyFromVis, sEnergyPerEnderCrystal));
    }

    @Override
    public void onRightclick(EntityPlayer aPlayer) {
        aPlayer.openGui((Object)GT_Mod.instance, 126, this.mBaseMetaTileEntity.worldObj, this.mBaseMetaTileEntity.xCoord, this.mBaseMetaTileEntity.yCoord, this.mBaseMetaTileEntity.zCoord);
    }

    @Override
    public boolean isAccessAllowed(EntityPlayer aPlayer) {
        return true;
    }

    @Override
    public MetaTileEntity newMetaEntity(BaseMetaTileEntity aTileEntity) {
        return new GT_MetaTileEntity_MagicEnergyAbsorber();
    }

    @Override
    public void saveNBTData(NBTTagCompound aNBT) {
        aNBT.setBoolean("isActive1", this.isActive1);
        aNBT.setBoolean("isActive2", this.isActive2);
    }

    @Override
    public void loadNBTData(NBTTagCompound aNBT) {
        this.isActive1 = aNBT.getBoolean("isActive1");
        this.isActive2 = aNBT.getBoolean("isActive2");
    }

    @Override
    public void onPostTick() {
        if (!this.mBaseMetaTileEntity.worldObj.isRemote && this.mBaseMetaTileEntity.mTickTimer % 10L == 0L) {
            if (this.mBaseMetaTileEntity.getStoredEnergy() < this.maxEUOutput()) {
                try {
                    NBTTagList tEnchantments;
                    if (this.mInventory[0] != null && this.mInventory[0].isItemEnchanted() && !(this.mInventory[0].getItem() instanceof IElectricItem) && (tEnchantments = this.mInventory[0].getEnchantmentTagList()) != null) {
                        for (int i = 0; i < tEnchantments.tagCount(); ++i) {
                            Enchantment tEnchantment;
                            short tID = ((NBTTagCompound)tEnchantments.tagAt(i)).getShort("id");
                            short tLevel = ((NBTTagCompound)tEnchantments.tagAt(i)).getShort("lvl");
                            if (tID <= -1 || tID >= Enchantment.enchantmentsList.length || (tEnchantment = Enchantment.enchantmentsList[tID]) == null) continue;
                            float tPercentage = (float)tLevel / (float)tEnchantment.getMaxLevel();
                            int tValue = 100000 / tEnchantment.getWeight();
                            this.mBaseMetaTileEntity.increaseStoredEU((int)((float)tValue * tPercentage) * (GT_Mod.instance.mEnchantmentTable ? 1 : 100), true);
                        }
                        this.mInventory[0].stackTagCompound.removeTag("ench");
                    }
                    if (this.mInventory[0] != null && this.mInventory[1] == null) {
                        if (this.mInventory[0].getItem() instanceof ItemEnchantedBook) {
                            this.mInventory[0] = new ItemStack(Item.book, 1, this.mInventory[0].stackSize);
                        }
                        this.mInventory[1] = this.mInventory[0];
                        this.mInventory[0] = null;
                    }
                }
                catch (Throwable e) {
                    e.printStackTrace(GT_Log.out);
                }
            }
            if (sEnergyPerEnderCrystal > 0 && this.isActive1) {
                if (sUsedDragonCrystalList == null) {
                    sUsedDragonCrystalList = new ArrayList();
                }
                if (this.mTargetedCrystal == null) {
                    ArrayList tList = (ArrayList)this.mBaseMetaTileEntity.worldObj.getEntitiesWithinAABB(EntityEnderCrystal.class, AxisAlignedBB.getBoundingBox((double)(this.mBaseMetaTileEntity.xCoord - 64), (double)(this.mBaseMetaTileEntity.yCoord - 64), (double)(this.mBaseMetaTileEntity.zCoord - 64), (double)(this.mBaseMetaTileEntity.xCoord + 64), (double)(this.mBaseMetaTileEntity.yCoord + 64), (double)(this.mBaseMetaTileEntity.zCoord + 64)));
                    if (tList != null && !tList.isEmpty()) {
                        tList.removeAll(sUsedDragonCrystalList);
                        if (tList.size() > 0) {
                            this.mTargetedCrystal = (EntityEnderCrystal)tList.get(0);
                            if (this.mTargetedCrystal != null) {
                                sUsedDragonCrystalList.add(this.mTargetedCrystal);
                            }
                        }
                    }
                } else if (this.mTargetedCrystal.isEntityAlive()) {
                    this.mBaseMetaTileEntity.increaseStoredEU(sEnergyPerEnderCrystal * 10, false);
                } else {
                    sUsedDragonCrystalList.remove(this.mTargetedCrystal);
                    this.mTargetedCrystal = null;
                }
            }
            if (sEnergyFromVis > 0 && this.isActive2 && this.mBaseMetaTileEntity.getStoredEnergy() < sEnergyFromVis) {
                try {
                    if (AuraManager.decreaseClosestAura((World)this.mBaseMetaTileEntity.worldObj, (double)this.mBaseMetaTileEntity.xCoord, (double)this.mBaseMetaTileEntity.yCoord, (double)this.mBaseMetaTileEntity.zCoord, (int)1)) {
                        this.mBaseMetaTileEntity.increaseStoredEU(sEnergyFromVis, true);
                        ObjectTags tTags = new ObjectTags();
                        tTags.add(EnumTag.MECHANISM, 1 + this.mBaseMetaTileEntity.worldObj.rand.nextInt(3));
                        tTags.add(EnumTag.VOID, 1 + this.mBaseMetaTileEntity.worldObj.rand.nextInt(2));
                        tTags.add(EnumTag.FLUX, 1 + this.mBaseMetaTileEntity.worldObj.rand.nextInt(2));
                        AuraManager.addFluxToClosest((World)this.mBaseMetaTileEntity.worldObj, (float)this.mBaseMetaTileEntity.xCoord, (float)this.mBaseMetaTileEntity.yCoord, (float)this.mBaseMetaTileEntity.zCoord, (ObjectTags)tTags);
                        ArrayList tList = (ArrayList)this.mBaseMetaTileEntity.worldObj.getEntitiesWithinAABB(EntityWisp.class, AxisAlignedBB.getBoundingBox((double)(this.mBaseMetaTileEntity.xCoord - 8), (double)(this.mBaseMetaTileEntity.yCoord - 8), (double)(this.mBaseMetaTileEntity.zCoord - 8), (double)(this.mBaseMetaTileEntity.xCoord + 8), (double)(this.mBaseMetaTileEntity.yCoord + 8), (double)(this.mBaseMetaTileEntity.zCoord + 8)));
                        if (!tList.isEmpty()) {
                            this.mBaseMetaTileEntity.doExplosion(2048);
                        }
                    }
                }
                catch (Throwable throwable) {
                    // empty catch block
                }
            }
            this.mBaseMetaTileEntity.mActive = this.mBaseMetaTileEntity.getStoredEnergy() >= this.maxEUOutput();
        }
    }

    @Override
    public void onExplosion() {
        if (sEnergyFromVis > 0 && this.isActive2) {
            try {
                ObjectTags tTags = new ObjectTags();
                tTags.add(EnumTag.MECHANISM, 50 + this.mBaseMetaTileEntity.worldObj.rand.nextInt(50));
                tTags.add(EnumTag.DESTRUCTION, 50 + this.mBaseMetaTileEntity.worldObj.rand.nextInt(50));
                tTags.add(EnumTag.FLUX, 50 + this.mBaseMetaTileEntity.worldObj.rand.nextInt(50));
                tTags.add(EnumTag.EVIL, 50 + this.mBaseMetaTileEntity.worldObj.rand.nextInt(50));
                tTags.add(EnumTag.FIRE, 50 + this.mBaseMetaTileEntity.worldObj.rand.nextInt(50));
                tTags.add(EnumTag.DARK, 50 + this.mBaseMetaTileEntity.worldObj.rand.nextInt(50));
                tTags.add(EnumTag.POWER, 50 + this.mBaseMetaTileEntity.worldObj.rand.nextInt(50));
                tTags.add(EnumTag.VOID, 50 + this.mBaseMetaTileEntity.worldObj.rand.nextInt(50));
                AuraManager.addFluxToClosest((World)this.mBaseMetaTileEntity.worldObj, (float)this.mBaseMetaTileEntity.xCoord, (float)this.mBaseMetaTileEntity.yCoord, (float)this.mBaseMetaTileEntity.zCoord, (ObjectTags)tTags);
            }
            catch (Throwable throwable) {
                // empty catch block
            }
        }
    }

    @Override
    public void inValidate() {
        if (this.mTargetedCrystal != null && sUsedDragonCrystalList != null) {
            sUsedDragonCrystalList.remove(this.mTargetedCrystal);
        }
    }

    @Override
    public int getInvSideIndex(ForgeDirection aSide, int aFacing) {
        if (aSide == ForgeDirection.UP || aSide == ForgeDirection.DOWN) {
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
        return aActive ? 91 : 92;
    }

    @Override
    protected String getDescription() {
        return "Absorbs Magic Energy and disenchants Items";
    }
}

