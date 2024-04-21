/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  ic2.api.Ic2Recipes
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraftforge.common.ForgeDirection
 */
package gregtechmod.common.tileentities;

import gregtechmod.GT_Mod;
import gregtechmod.common.GT_LanguageManager;
import gregtechmod.common.GT_ModHandler;
import gregtechmod.common.tileentities.GT_TileEntityMetaID_Machine;
import ic2.api.Ic2Recipes;
import java.util.Iterator;
import java.util.Map;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.ForgeDirection;

public class GT_TileEntity_Matterfabricator
extends GT_TileEntityMetaID_Machine {
    public static int sMatterFabricationRate = 166666;
    private int mProgresstime = 0;
    private int mProgressLast = 0;
    private int mEULast = 0;
    private int mAmplifier = 0;
    private int mAmplifierLast = 0;

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

    public int getProgresstime() {
        return this.mProgresstime;
    }

    public int maxProgresstime() {
        return sMatterFabricationRate;
    }

    @Override
    public int maxEUStore() {
        return 1000000;
    }

    @Override
    public int maxEUInput() {
        return 8192;
    }

    @Override
    public int getInventorySlotCount() {
        return 9;
    }

    @Override
    public void storeAdditionalData(NBTTagCompound aNBT) {
        aNBT.setInteger("mProgresstime", this.mProgresstime);
        aNBT.setInteger("mAmplifier", this.mAmplifier);
    }

    @Override
    public void getAdditionalData(NBTTagCompound aNBT) {
        this.mProgresstime = aNBT.getInteger("mProgresstime");
        this.mAmplifier = aNBT.getInteger("mAmplifier");
    }

    @Override
    public void onPostTickUpdate() {
        if (!this.worldObj.isRemote) {
            this.mActive = this.mEULast != this.getEnergyVar() || this.mAmplifierLast != this.mAmplifier || this.mProgressLast != this.mProgresstime;
            this.mEULast = this.getEnergyVar();
            this.mAmplifierLast = this.mAmplifier;
            this.mProgressLast = this.mProgresstime;
            if (this.getStored() > 0 && !this.worldObj.isBlockIndirectlyGettingPowered(this.xCoord, this.yCoord, this.zCoord)) {
                Iterator tIterator = Ic2Recipes.getMatterAmplifiers().iterator();
                while (this.mAmplifier < 100000 && tIterator.hasNext()) {
                    Map.Entry tEntry = (Map.Entry)tIterator.next();
                    ItemStack tStack = (ItemStack)tEntry.getKey();
                    int tValue = (int)((long)((Integer)tEntry.getValue()).intValue() * (long)this.maxProgresstime() / 166666L);
                    if (tValue <= 0) continue;
                    for (int i = 1; this.mAmplifier < 100000 && i < this.getInventorySlotCount(); ++i) {
                        if (this.mInventory[i] == null || !this.mInventory[i].isItemEqual(tStack)) continue;
                        this.mAmplifier += tValue;
                        this.decrStackSize(i, 1);
                    }
                }
                int tUsed = Math.min(GT_Mod.instance.mMassfabricator ? this.getCapacity() : 8192, Math.min(this.getStored(), this.mAmplifier));
                if (tUsed > 0) {
                    this.mProgresstime += tUsed;
                    this.mAmplifier -= tUsed;
                    this.decreaseStoredEnergy(tUsed, true);
                }
                while (this.mProgresstime > this.maxProgresstime() && this.spaceForOutput()) {
                    this.mProgresstime -= this.maxProgresstime();
                    this.addOutputProducts();
                }
            }
        }
    }

    private void addOutputProducts() {
        if (this.mInventory[0] == null) {
            this.mInventory[0] = GT_ModHandler.getIC2Item("matter", 1);
        } else if (this.mInventory[0].isItemEqual(GT_ModHandler.getIC2Item("matter", 1))) {
            this.mInventory[0].stackSize = Math.min(64, 1 + this.mInventory[0].stackSize);
        }
    }

    private boolean spaceForOutput() {
        return this.mInventory[0] == null || this.mInventory[0].isItemEqual(GT_ModHandler.getIC2Item("matter", 1)) && this.mInventory[0].stackSize < 64;
    }

    @Override
    public int getStartInventorySide(ForgeDirection aSide) {
        if (aSide == ForgeDirection.UP || aSide == ForgeDirection.DOWN) {
            return 1;
        }
        return 0;
    }

    @Override
    public int getSizeInventorySide(ForgeDirection aSide) {
        if (aSide == ForgeDirection.UP || aSide == ForgeDirection.DOWN) {
            return 8;
        }
        return 1;
    }

    @Override
    public String getInvName() {
        return GT_LanguageManager.mNameList1[14];
    }

    @Override
    public String getMainInfo() {
        return "Progress:";
    }

    @Override
    public String getSecondaryInfo() {
        return Math.max(0L, (long)this.getProgresstime() / Math.max(1L, (long)this.maxProgresstime() / 100L)) + "%";
    }

    @Override
    public String getTertiaryInfo() {
        return "Energy: " + this.getStored();
    }

    @Override
    public boolean isGivingInformation() {
        return true;
    }

    @Override
    public int getTexture(int aSide, int aMeta) {
        return this.mActive ? 31 : 30;
    }
}

