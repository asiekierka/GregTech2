/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraftforge.common.ForgeDirection
 */
package gregtechmod.common.tileentities;

import gregtechmod.GT_Mod;
import gregtechmod.api.BaseMetaTileEntity;
import gregtechmod.api.MetaTileEntity;
import gregtechmod.common.tileentities.GT_MetaTileEntity_ElectricBufferSmall;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.ForgeDirection;

public class GT_MetaTileEntity_RockBreaker
extends GT_MetaTileEntity_ElectricBufferSmall {
    public GT_MetaTileEntity_RockBreaker(int aID, String mName, String mNameRegional) {
        super(aID, mName, mNameRegional);
    }

    public GT_MetaTileEntity_RockBreaker() {
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
    public int getMinimumStoredEU() {
        return 2000;
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
        aPlayer.openGui((Object)GT_Mod.instance, 106, this.mBaseMetaTileEntity.worldObj, this.mBaseMetaTileEntity.xCoord, this.mBaseMetaTileEntity.yCoord, this.mBaseMetaTileEntity.zCoord);
    }

    @Override
    public void onPostTick() {
        if (!this.mBaseMetaTileEntity.worldObj.isRemote && this.mBaseMetaTileEntity.getStoredEnergy() >= 1000 && this.mBaseMetaTileEntity.mTickTimer % (long)(40 / (int)Math.pow(2.0, this.mBaseMetaTileEntity.mOverclockers)) == 0L) {
            ItemStack tOutput = new ItemStack(Block.cobblestone, 1);
            boolean tWater = true;
            if (this.mBaseMetaTileEntity.worldObj.getBlockId(this.mBaseMetaTileEntity.xCoord, this.mBaseMetaTileEntity.yCoord, this.mBaseMetaTileEntity.zCoord + 1) != 9 && this.mBaseMetaTileEntity.worldObj.getBlockId(this.mBaseMetaTileEntity.xCoord, this.mBaseMetaTileEntity.yCoord, this.mBaseMetaTileEntity.zCoord - 1) != 9 && this.mBaseMetaTileEntity.worldObj.getBlockId(this.mBaseMetaTileEntity.xCoord + 1, this.mBaseMetaTileEntity.yCoord, this.mBaseMetaTileEntity.zCoord) != 9 && this.mBaseMetaTileEntity.worldObj.getBlockId(this.mBaseMetaTileEntity.xCoord - 1, this.mBaseMetaTileEntity.yCoord, this.mBaseMetaTileEntity.zCoord) != 9) {
                tWater = false;
            }
            if (this.mBaseMetaTileEntity.worldObj.getBlockId(this.mBaseMetaTileEntity.xCoord, this.mBaseMetaTileEntity.yCoord + 1, this.mBaseMetaTileEntity.zCoord) == 11) {
                tOutput = new ItemStack(Block.stone, 1);
            } else if (this.mBaseMetaTileEntity.worldObj.getBlockId(this.mBaseMetaTileEntity.xCoord, this.mBaseMetaTileEntity.yCoord, this.mBaseMetaTileEntity.zCoord + 1) != 11 && this.mBaseMetaTileEntity.worldObj.getBlockId(this.mBaseMetaTileEntity.xCoord, this.mBaseMetaTileEntity.yCoord, this.mBaseMetaTileEntity.zCoord - 1) != 11 && this.mBaseMetaTileEntity.worldObj.getBlockId(this.mBaseMetaTileEntity.xCoord + 1, this.mBaseMetaTileEntity.yCoord, this.mBaseMetaTileEntity.zCoord) != 11 && this.mBaseMetaTileEntity.worldObj.getBlockId(this.mBaseMetaTileEntity.xCoord - 1, this.mBaseMetaTileEntity.yCoord, this.mBaseMetaTileEntity.zCoord) != 11) {
                tOutput = null;
            }
            if (tOutput != null && tWater) {
                if (this.mInventory[0] == null) {
                    if (this.mInventory[1] != null && this.mInventory[1].getItem() == Item.redstone) {
                        this.mInventory[0] = new ItemStack(Block.obsidian, 1);
                        this.mBaseMetaTileEntity.decrStackSize(1, 1);
                        this.mBaseMetaTileEntity.decreaseStoredEnergy(500 * (int)Math.pow(4.0, this.mBaseMetaTileEntity.mOverclockers), true);
                    } else {
                        this.mInventory[0] = tOutput;
                        this.mBaseMetaTileEntity.decreaseStoredEnergy(100 * (int)Math.pow(4.0, this.mBaseMetaTileEntity.mOverclockers), true);
                    }
                } else if (this.mInventory[1] != null && this.mInventory[1].getItem() == Item.redstone && this.mInventory[0].itemID == Block.obsidian.blockID && this.mInventory[0].stackSize < 64) {
                    ++this.mInventory[0].stackSize;
                    this.mBaseMetaTileEntity.decrStackSize(1, 1);
                    this.mBaseMetaTileEntity.decreaseStoredEnergy(500 * (int)Math.pow(4.0, this.mBaseMetaTileEntity.mOverclockers), true);
                } else if (this.mInventory[0].isItemEqual(tOutput) && this.mInventory[0].stackSize < 64) {
                    ++this.mInventory[0].stackSize;
                    this.mBaseMetaTileEntity.decreaseStoredEnergy(100 * (int)Math.pow(4.0, this.mBaseMetaTileEntity.mOverclockers), true);
                }
            }
        }
        super.onPostTick();
    }

    @Override
    public int getInvSideIndex(ForgeDirection aSide, int aFacing) {
        if (aSide == ForgeDirection.getOrientation((int)this.mBaseMetaTileEntity.getFacing()).getOpposite()) {
            return 0;
        }
        return 1;
    }

    @Override
    public int getInvSideLength(ForgeDirection aSide, int aFacing) {
        return 1;
    }

    @Override
    public MetaTileEntity newMetaEntity(BaseMetaTileEntity aTileEntity) {
        return new GT_MetaTileEntity_RockBreaker();
    }

    @Override
    public int getTextureIndex(int aSide, int aFacing, boolean aActive, boolean aRedstone) {
        if (ForgeDirection.getOrientation((int)aSide).getOpposite() == ForgeDirection.getOrientation((int)aFacing)) {
            return 113 + (aRedstone ? 8 : 0);
        }
        return 115 + (aRedstone ? 8 : 0);
    }

    @Override
    protected String getDescription() {
        return "Electric Cobble Generator Mk VI";
    }
}

