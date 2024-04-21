/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.inventory.IInventory
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraftforge.common.ForgeDirection
 */
package gregtechmod.common.tileentities;

import gregtechmod.GT_Mod;
import gregtechmod.api.BaseMetaTileEntity;
import gregtechmod.api.MetaTileEntity;
import gregtechmod.common.GT_Utility;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.ForgeDirection;

public class GT_MetaTileEntity_Safe
extends MetaTileEntity {
    public int success = 0;

    public GT_MetaTileEntity_Safe(int aID, String mName, String mNameRegional) {
        super(aID, mName, mNameRegional);
    }

    public GT_MetaTileEntity_Safe() {
    }

    @Override
    public boolean isSimpleMachine() {
        return true;
    }

    @Override
    public int getInvSize() {
        return 29;
    }

    @Override
    public boolean isValidSlot(int aIndex) {
        return aIndex < 28;
    }

    @Override
    public boolean isFacingValid(int aFacing) {
        return aFacing > 1;
    }

    @Override
    public void onRightclick(EntityPlayer aPlayer) {
        aPlayer.openGui((Object)GT_Mod.instance, 128, this.mBaseMetaTileEntity.worldObj, this.mBaseMetaTileEntity.xCoord, this.mBaseMetaTileEntity.yCoord, this.mBaseMetaTileEntity.zCoord);
    }

    @Override
    public boolean isAccessAllowed(EntityPlayer aPlayer) {
        return true;
    }

    @Override
    public boolean ownerControl() {
        return true;
    }

    @Override
    public boolean isEnetOutput() {
        return false;
    }

    @Override
    public boolean isEnetInput() {
        return false;
    }

    @Override
    public boolean isOutputFacing(short aSide) {
        return false;
    }

    @Override
    public boolean isInputFacing(short aSide) {
        return false;
    }

    @Override
    public MetaTileEntity newMetaEntity(BaseMetaTileEntity aTileEntity) {
        return new GT_MetaTileEntity_Safe();
    }

    @Override
    public void saveNBTData(NBTTagCompound aNBT) {
    }

    @Override
    public void loadNBTData(NBTTagCompound aNBT) {
    }

    @Override
    public void onPostTick() {
        if (!this.mBaseMetaTileEntity.worldObj.isRemote) {
            if (this.mInventory[0] != null && (this.success-- > 0 || this.mBaseMetaTileEntity.mTickTimer % 200L == 20L)) {
                int i;
                for (i = 1; this.mInventory[0] != null && i < this.getInvSize() - 1; ++i) {
                    if (this.mInventory[i] == null || !this.mInventory[i].isItemEqual(this.mInventory[0])) continue;
                    this.success = GT_Utility.moveStackFromSlotAToSlotB((IInventory)this.mBaseMetaTileEntity, (IInventory)this.mBaseMetaTileEntity, 0, i, 64, 1, 64, 1);
                }
                for (i = 1; this.mInventory[0] != null && i < this.getInvSize() - 1; ++i) {
                    if (this.mInventory[i] != null) continue;
                    this.success = GT_Utility.moveStackFromSlotAToSlotB((IInventory)this.mBaseMetaTileEntity, (IInventory)this.mBaseMetaTileEntity, 0, i, 64, 1, 64, 1);
                }
            }
            if (this.mInventory[28] == null) {
                if (this.mInventory[0] != null && this.mInventory[0].stackSize < 1) {
                    this.mInventory[0] = null;
                }
            } else {
                this.mInventory[28].stackSize = 0;
                if (this.mInventory[0] == null || this.mInventory[0].stackSize < 1) {
                    this.mInventory[0] = this.mInventory[28].copy();
                }
            }
        }
    }

    @Override
    public int getInvSideIndex(ForgeDirection aSide, int aFacing) {
        return 0;
    }

    @Override
    public int getInvSideLength(ForgeDirection aSide, int aFacing) {
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
        return aSide == aFacing ? 214 : 40;
    }

    @Override
    protected String getDescription() {
        return "This is completly 'Safe', except against Explosions";
    }
}

