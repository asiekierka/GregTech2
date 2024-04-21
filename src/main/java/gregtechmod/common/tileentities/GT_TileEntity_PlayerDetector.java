/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.network.packet.Packet
 *  net.minecraft.network.packet.Packet3Chat
 */
package gregtechmod.common.tileentities;

import gregtechmod.common.GT_LanguageManager;
import gregtechmod.common.tileentities.GT_TileEntityMetaID_Machine;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.Packet3Chat;

import java.util.List;

public class GT_TileEntity_PlayerDetector
extends GT_TileEntityMetaID_Machine {
    public int mMode = 0;

    @Override
    public boolean isAccessible(EntityPlayer aPlayer) {
        return true;
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
    public boolean ownerControl() {
        return true;
    }

    @Override
    public int maxEUInput() {
        return 32;
    }

    @Override
    public float getWrenchDropRate() {
        return 1.0f;
    }

    @Override
    public void storeAdditionalData(NBTTagCompound aNBT) {
        aNBT.setInteger("mMode", this.mMode);
    }

    @Override
    public void getAdditionalData(NBTTagCompound aNBT) {
        this.mMode = aNBT.getInteger("mMode");
    }

    @Override
    public void onPostTickUpdate() {
        if (!this.worldObj.isRemote && this.mTickTimer % 20L == 0L && this.mOwnerName != null && !this.mOwnerName.equals("")) {
            this.mActive = false;
            this.mRedstone = false;
            if (this.decreaseStoredEnergy(50, false)) {
                for (EntityPlayer tPlayer : (List<EntityPlayer>) this.worldObj.playerEntities) {
                    if (!(tPlayer.getDistanceSq((double)this.xCoord + 0.5, (double)this.yCoord + 0.5, (double)this.zCoord + 0.5) <= 256.0)) continue;
                    if (this.mMode == 0) {
                        this.mActive = true;
                        this.mRedstone = true;
                        break;
                    }
                    if (this.playerOwnsThis(tPlayer)) {
                        if (this.mMode != 1) continue;
                        this.mActive = true;
                        this.mRedstone = true;
                        break;
                    }
                    if (this.mMode != 2) continue;
                    this.mActive = true;
                    this.mRedstone = true;
                    break;
                }
            }
        }
    }

    public void rightClick(EntityPlayer aPlayer) {
        this.mMode = (this.mMode + 1) % 3;
        String tMessage = "";
        switch (this.mMode) {
            case 0: {
                tMessage = "Detects all Players";
                break;
            }
            case 1: {
                tMessage = "Detects only you";
                break;
            }
            case 2: {
                tMessage = "Detects only other Players";
            }
        }
        if (aPlayer instanceof EntityPlayerMP) {
            ((EntityPlayerMP)aPlayer).playerNetServerHandler.sendPacketToPlayer((Packet)new Packet3Chat(tMessage));
        }
    }

    @Override
    public String getInvName() {
        return GT_LanguageManager.mNameList1[13];
    }

    @Override
    public int getTexture(int aSide, int aMeta) {
        return this.mActive ? 24 : 23;
    }
}

