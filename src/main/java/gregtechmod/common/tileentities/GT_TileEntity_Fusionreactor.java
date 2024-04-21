/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.tileentity.TileEntity
 */
package gregtechmod.common.tileentities;

import gregtechmod.GT_Mod;
import gregtechmod.api.BaseMetaTileEntity;
import gregtechmod.common.GT_LanguageManager;
import gregtechmod.common.tileentities.GT_TileEntityMetaID_Machine;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

public class GT_TileEntity_Fusionreactor
extends GT_TileEntityMetaID_Machine {
    @Override
    public boolean isAccessible(EntityPlayer aPlayer) {
        return false;
    }

    @Override
    public void onPostTickUpdate() {
        if (!this.worldObj.isRemote && this.mTickTimer == 100L) {
            int tX = this.xCoord;
            int tY = this.yCoord;
            int tZ = this.zCoord;
            NBTTagCompound tNBT = new NBTTagCompound();
            tNBT.setInteger("mID", 80);
            tNBT.setShort("mFacing", (short)2);
            tNBT.setString("mOwnerName", this.mOwnerName);
            this.worldObj.setBlockMetadataWithNotify(tX, tY, tZ, 0);
            this.worldObj.removeBlockTileEntity(tX, tY, tZ);
            BaseMetaTileEntity tTileEntity = GT_Mod.constructBaseMetaTileEntity();
            this.worldObj.setBlockTileEntity(tX, tY, tZ, (TileEntity)tTileEntity);
            tTileEntity.setInitialValuesAsNBT(tNBT);
        }
    }

    @Override
    public String getInvName() {
        return GT_LanguageManager.mNameList1[1];
    }

    @Override
    public int getTexture(int aSide, int aMeta) {
        if (aSide == 0) {
            return 3;
        }
        if (aSide == 1) {
            return 48 + (int)this.mTickTimer % 16;
        }
        if (aSide == 2 || aSide == 3) {
            return 103;
        }
        return this.mActive ? 20 : 19;
    }
}

