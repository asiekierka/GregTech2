/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.entity.EntityLiving
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.ItemBlock
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.world.World
 */
package gregtechmod.common.items;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import gregtechmod.GT_Mod;
import gregtechmod.api.BaseMetaTileEntity;
import gregtechmod.api.GregTech_API;
import gregtechmod.common.GT_LanguageManager;
import gregtechmod.common.blocks.GT_BlockMetaID_Block;
import gregtechmod.common.tileentities.GT_TileEntityMetaID_Machine;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class GT_MetaMachine_Item
extends ItemBlock {
    public static int mItemID = 0;
    public String[] mString0 = new String[1024];
    public String[] mString1 = new String[1024];
    public String[] mString2 = new String[1024];
    public String[] mString3 = new String[1024];
    public String[] mString4 = new String[1024];
    public String[] mString5 = new String[1024];
    public String[] mString6 = new String[1024];

    public GT_MetaMachine_Item(int par1) {
        super(par1);
        mItemID = par1;
        this.setMaxDamage(0);
        this.setHasSubtypes(true);
        this.setItemName(GT_LanguageManager.mNameList1[0]);
        this.setCreativeTab(GT_Mod.tabGregTech);
    }

    @SideOnly(value=Side.CLIENT)
    public int getIconFromDamage(int aMeta) {
        return GT_Mod.instance.mBlocks[1].getBlockTextureFromSideAndMetadata(1, aMeta);
    }

    public void addInformation(ItemStack aStack, EntityPlayer aPlayer, List aList, boolean par4) {
        NBTTagCompound aNBT;
        int tDamage = aStack.getItemDamage();
        if (tDamage < 0 || tDamage >= 1024) {
            return;
        }
        if (this.mString1[tDamage] == null) {
            if (tDamage <= 0) {
                this.mString0[tDamage] = "";
                this.mString1[tDamage] = "If you got this from a Wrench, which is NOT directly belonging to IC2";
                this.mString2[tDamage] = "then please report it at the corresponding Wrench-Mod, and NOT to GregTech.";
                this.mString3[tDamage] = "My Machines implement the IWrenchable Interface properly, as you can see using the IC2-Wrench.";
                this.mString4[tDamage] = "";
                this.mString5[tDamage] = "";
                this.mString6[tDamage] = "";
            } else if (tDamage < 16) {
                GT_TileEntityMetaID_Machine tTileEntity = (GT_TileEntityMetaID_Machine)GT_Mod.instance.mBlocks[1].createTileEntity(aPlayer.worldObj, tDamage);
                tTileEntity.worldObj = aPlayer.worldObj;
                tTileEntity.xCoord = 0;
                tTileEntity.yCoord = 0;
                tTileEntity.zCoord = 0;
                if (tTileEntity != null) {
                    this.mString0[tDamage] = "";
                    this.mString1[tDamage] = tTileEntity.getMaxSafeInput() > 0 ? "Max EU/p IN: " + tTileEntity.getMaxSafeInput() : "";
                    this.mString2[tDamage] = tTileEntity.getOutput() > 0 ? "Max EU/p OUT: " + tTileEntity.getOutput() : "";
                    this.mString3[tDamage] = tTileEntity.getCapacity() > 10000 ? "EU Storage: " + tTileEntity.getCapacity() : "";
                    this.mString4[tDamage] = "";
                    this.mString5[tDamage] = "";
                    this.mString6[tDamage] = "";
                }
            } else {
                BaseMetaTileEntity tTileEntity = (BaseMetaTileEntity)GT_Mod.instance.mBlocks[1].createTileEntity(aPlayer.worldObj, 0);
                tTileEntity.worldObj = aPlayer.worldObj;
                tTileEntity.xCoord = 0;
                tTileEntity.yCoord = 0;
                tTileEntity.zCoord = 0;
                if (tTileEntity != null) {
                    tTileEntity.createNewMetatileEntity(tDamage);
                    if (tTileEntity.getDescription() != null) {
                        this.mString0[tDamage] = tTileEntity.getDescription();
                    }
                    this.mString1[tDamage] = tTileEntity.getMaxSafeInput() > 0 ? "Max EU/p IN: " + tTileEntity.getMaxSafeInput() : "";
                    this.mString2[tDamage] = tTileEntity.getOutput() > 0 ? "Max EU/p OUT: " + tTileEntity.getOutput() : "";
                    this.mString3[tDamage] = tTileEntity.getMaxOutputPackets() > 1 ? "Amount of Output Packets: " + tTileEntity.getMaxOutputPackets() : "";
                    this.mString4[tDamage] = tTileEntity.getCapacity() > 10000 ? "EU Storage: " + tTileEntity.getCapacity() : "";
                    this.mString5[tDamage] = (tTileEntity.mMetaTileEntity.isOverclockerUpgradable() ? "O " : "") + (tTileEntity.mMetaTileEntity.isTransformerUpgradable() ? "T " : "") + (tTileEntity.mMetaTileEntity.isBatteryUpgradable() ? "B " : "") + (tTileEntity.mMetaTileEntity.maxMJStore() > 0 ? "M " : "");
                    if (!this.mString5[tDamage].equals("")) {
                        this.mString5[tDamage] = "Possible Upgrades: " + this.mString5[tDamage];
                    }
                    this.mString6[tDamage] = "";
                }
            }
        }
        if (!this.mString0[tDamage].equals("")) {
            aList.add(this.mString0[tDamage]);
        }
        if (!this.mString1[tDamage].equals("")) {
            aList.add(this.mString1[tDamage]);
        }
        if (!this.mString2[tDamage].equals("")) {
            aList.add(this.mString2[tDamage]);
        }
        if (!this.mString3[tDamage].equals("")) {
            aList.add(this.mString3[tDamage]);
        }
        if (!this.mString4[tDamage].equals("")) {
            aList.add(this.mString4[tDamage]);
        }
        if (!this.mString5[tDamage].equals("")) {
            aList.add(this.mString5[tDamage]);
        }
        if (!this.mString6[tDamage].equals("")) {
            aList.add(this.mString6[tDamage]);
        }
        if ((aNBT = aStack.getTagCompound()) != null) {
            if (aNBT.getBoolean("mMJConverter")) {
                aList.add("has MJ-Converter");
            }
            byte tAmount = 0;
            tAmount = aNBT.getByte("mOverclockers");
            if (tAmount > 0) {
                aList.add(tAmount + " Overclocker Upgrades");
            }
            if ((tAmount = aNBT.getByte("mTransformers")) > 0) {
                aList.add(tAmount + " Transformer Upgrades");
            }
            if ((tAmount = aNBT.getByte("mBatteries")) > 0) {
                aList.add(tAmount + " Battery Upgrades");
            }
            if ((tAmount = aNBT.getByte("mLiBatteries")) > 0) {
                aList.add(tAmount + " Lithium Battery Upgrades");
            }
            if ((tAmount = aNBT.getByte("mRSEnergyCells")) > 0) {
                aList.add(tAmount + " Energy Cell Upgrades");
            }
        }
    }

    public boolean onItemUseFirst(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ) {
        return stack.getItemDamage() == 7 && 0 < GT_BlockMetaID_Block.stepToFindOrCallLESUController(world, x, y, z, new ArrayList(), false);
    }

    public String getItemNameIS(ItemStack aStack) {
        int tDamage = aStack.getItemDamage();
        if (tDamage < 0 || tDamage >= 1024) {
            return "";
        }
        if (tDamage < 16) {
            return this.getItemName() + "." + GT_LanguageManager.mNameList1[tDamage];
        }
        if (GregTech_API.mMetaTileList[tDamage] != null) {
            return this.getItemName() + "." + GregTech_API.mMetaTileList[tDamage].mName;
        }
        return "";
    }

    public boolean placeBlockAt(ItemStack aStack, EntityPlayer aPlayer, World aWorld, int aX, int aY, int aZ, int side, float hitX, float hitY, float hitZ, int aMeta) {
        if (aStack.getItemDamage() > 15) {
            if (!aWorld.setBlockAndMetadataWithNotify(aX, aY, aZ, this.getBlockID(), 0)) {
                return false;
            }
            BaseMetaTileEntity tTileEntity = (BaseMetaTileEntity)aWorld.getBlockTileEntity(aX, aY, aZ);
            if (tTileEntity != null) {
                if (aStack.getTagCompound() != null) {
                    aStack.getTagCompound().setInteger("mID", aStack.getItemDamage());
                    tTileEntity.setInitialValuesAsNBT(aStack.getTagCompound());
                } else {
                    tTileEntity.createNewMetatileEntity(aStack.getItemDamage());
                }
            }
        } else if (!aWorld.setBlockAndMetadataWithNotify(aX, aY, aZ, this.getBlockID(), aStack.getItemDamage())) {
            return false;
        }
        if (aWorld.getBlockId(aX, aY, aZ) == this.getBlockID()) {
            Block.blocksList[this.getBlockID()].onBlockPlacedBy(aWorld, aX, aY, aZ, (EntityLiving)aPlayer);
            Block.blocksList[this.getBlockID()].onPostBlockPlaced(aWorld, aX, aY, aZ, aStack.getItemDamage());
        }
        return true;
    }
}

