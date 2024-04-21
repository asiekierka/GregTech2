/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.MathHelper
 */
package gregtechmod.common.tileentities;

import java.util.Iterator;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;

public class GT_TileEntity_LightSource
extends TileEntity {
    private int mTickTimer = 0;

    public void updateEntity() {
        if (this.worldObj.isRemote) {
            return;
        }
        Iterator tIterator = this.worldObj.playerEntities.iterator();
        boolean temp = true;
        if (++this.mTickTimer % 20 == 0) {
            while (tIterator.hasNext() && temp) {
                EntityPlayer tPlayer = (EntityPlayer)tIterator.next();
                if (MathHelper.floor_double((double)tPlayer.posX) != this.xCoord || MathHelper.floor_double((double)(tPlayer.posY + 1.0)) != this.yCoord || MathHelper.floor_double((double)tPlayer.posZ) != this.zCoord) continue;
                temp = false;
            }
            if (temp) {
                this.worldObj.setBlockWithNotify(this.xCoord, this.yCoord, this.zCoord, 0);
            }
        }
    }
}

