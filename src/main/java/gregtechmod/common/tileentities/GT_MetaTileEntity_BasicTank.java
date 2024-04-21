/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraftforge.common.ForgeDirection
 *  net.minecraftforge.liquids.LiquidStack
 */
package gregtechmod.common.tileentities;

import gregtechmod.api.BaseMetaTileEntity;
import gregtechmod.api.MetaTileEntity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.ForgeDirection;
import net.minecraftforge.liquids.LiquidStack;

public class GT_MetaTileEntity_BasicTank
extends MetaTileEntity {
    public LiquidStack mLiquid = new LiquidStack(0, 0, 0);

    public GT_MetaTileEntity_BasicTank(int aID, String mName, String mNameRegional) {
        super(aID, mName, mNameRegional);
    }

    public GT_MetaTileEntity_BasicTank() {
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
    public int getInvSize() {
        return 3;
    }

    @Override
    public boolean isAccessAllowed(EntityPlayer aPlayer) {
        return true;
    }

    @Override
    public MetaTileEntity newMetaEntity(BaseMetaTileEntity aTileEntity) {
        return new GT_MetaTileEntity_BasicTank();
    }

    @Override
    public void saveNBTData(NBTTagCompound aNBT) {
        if (this.mLiquid != null) {
            aNBT.setInteger("mItemCount", this.mLiquid.amount);
            aNBT.setInteger("mItemID", this.mLiquid.itemID);
            aNBT.setInteger("mItemMeta", this.mLiquid.itemMeta);
        }
    }

    @Override
    public void loadNBTData(NBTTagCompound aNBT) {
        this.mLiquid = new LiquidStack(aNBT.getInteger("mItemID"), aNBT.getInteger("mItemCount"), aNBT.getInteger("mItemMeta"));
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
        if (aSide == 0) {
            return 38;
        }
        if (aSide == 1) {
            return 80;
        }
        return 36;
    }

    @Override
    protected String getDescription() {
        return "*Forgot to add Desciption. This is a Bug!*";
    }
}

