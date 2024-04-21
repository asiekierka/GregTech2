/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.effect.EntityLightningBolt
 *  net.minecraft.entity.player.EntityPlayer
 */
package gregtechmod.common.tileentities;

import gregtechmod.GT_Mod;
import gregtechmod.common.GT_LanguageManager;
import gregtechmod.common.GT_ModHandler;
import gregtechmod.common.tileentities.GT_TileEntityMetaID_Machine;
import net.minecraft.entity.Entity;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.player.EntityPlayer;

public class GT_TileEntity_Lightningrod
extends GT_TileEntityMetaID_Machine {
    @Override
    public boolean isAccessible(EntityPlayer aPlayer) {
        return false;
    }

    @Override
    public boolean isEnetOutput() {
        return true;
    }

    @Override
    public boolean isEnetInput() {
        return false;
    }

    @Override
    public boolean isOutputFacing(short aDirection) {
        return aDirection != 2 && aDirection != 3;
    }

    @Override
    public boolean isInputFacing(short aDirection) {
        return false;
    }

    @Override
    public int maxEUStore() {
        return 100000000;
    }

    @Override
    public int maxEUOutput() {
        return 8192;
    }

    @Override
    public int getInventorySlotCount() {
        return 0;
    }

    @Override
    public void onPostTickUpdate() {
        if (!this.worldObj.isRemote && this.mTickTimer % 256L == 0L && (this.worldObj.isThundering() || this.worldObj.isRaining() && GT_Mod.Randomizer.nextInt(10) == 0)) {
            int rodvalue = 0;
            boolean rodvalid = true;
            for (int i = this.yCoord + 1; i < this.worldObj.getHeight() - 1; ++i) {
                if (rodvalid && this.worldObj.getBlockId(this.xCoord, i, this.zCoord) == GT_ModHandler.getIC2Item((String)"ironFence", (int)1).itemID) {
                    ++rodvalue;
                    continue;
                }
                rodvalid = false;
                if (this.worldObj.getBlockId(this.xCoord, i, this.zCoord) == 0) continue;
                rodvalue = 0;
                break;
            }
            if (!this.worldObj.isThundering() && this.yCoord + rodvalue < 128) {
                rodvalue = 0;
            }
            if (GT_Mod.Randomizer.nextInt(4096 * this.worldObj.getHeight()) < rodvalue * (this.yCoord + rodvalue)) {
                this.setStoredEnergy(25000000);
                this.worldObj.addWeatherEffect((Entity)new EntityLightningBolt(this.worldObj, (double)this.xCoord, (double)(this.yCoord + rodvalue), (double)this.zCoord));
            }
        }
    }

    @Override
    public String getInvName() {
        return GT_LanguageManager.mNameList1[2];
    }

    @Override
    public int getTexture(int aSide, int aMeta) {
        if (aSide == 0) {
            return 3;
        }
        if (aSide == 1) {
            return 21;
        }
        return 18;
    }
}

