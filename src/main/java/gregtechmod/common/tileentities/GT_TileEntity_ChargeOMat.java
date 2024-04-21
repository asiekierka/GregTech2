/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  ic2.api.Direction
 *  ic2.api.ElectricItem
 *  ic2.api.IElectricItem
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.ItemStack
 *  net.minecraftforge.common.ForgeDirection
 */
package gregtechmod.common.tileentities;

import gregtechmod.common.GT_LanguageManager;
import gregtechmod.common.tileentities.GT_TileEntityMetaID_Machine;
import ic2.api.Direction;
import ic2.api.ElectricItem;
import ic2.api.IElectricItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.ForgeDirection;

public class GT_TileEntity_ChargeOMat
extends GT_TileEntityMetaID_Machine {
    @Override
    public int getTier() {
        return 5;
    }

    @Override
    public boolean isAccessible(EntityPlayer aPlayer) {
        return true;
    }

    @Override
    public boolean isEnetOutput() {
        return this.worldObj.isBlockIndirectlyGettingPowered(this.xCoord, this.yCoord, this.zCoord);
    }

    @Override
    public boolean isEnetInput() {
        return !this.worldObj.isBlockIndirectlyGettingPowered(this.xCoord, this.yCoord, this.zCoord);
    }

    @Override
    public boolean isOutputFacing(short aDirection) {
        return true;
    }

    @Override
    public boolean isInputFacing(short aDirection) {
        return true;
    }

    @Override
    public int maxEUStore() {
        return 10000000;
    }

    @Override
    public int maxEUInput() {
        return 2048;
    }

    @Override
    public int maxEUOutput() {
        return 2048;
    }

    @Override
    public int getInventorySlotCount() {
        return 18;
    }

    /*
     * Unable to fully structure code
     */
    @Override
    public void onPostTickUpdate() {
        if (!this.worldObj.isRemote) {
            if (mTickTimer % 20 == 0) {
                ItemStack tStack = null;
                EntityPlayer tPlayer = worldObj.getClosestPlayer(xCoord + 0.5, yCoord + 0.5, zCoord + 0.5, 3.0);
                if (tPlayer != null) {
                    for (int j = 0; j < getChargeTier(); j++) {
                        if (isEnetOutput()) {
                            for (int i = 0; i < 4; i++)
                                if (demandsEnergy() > 0 && (tStack = tPlayer.inventory.armorInventory[i]) != null && tStack.getItem() instanceof IElectricItem)
                                    increaseStoredEnergy(ElectricItem.discharge(tStack, maxEUStore() - getEnergyVar(), getChargeTier(), false, false));
                        } else {
                            for (int i = 0; i < 4; i++)
                                if (getEnergyVar() > 0 && (tStack = tPlayer.inventory.armorInventory[i]) != null && tStack.getItem() instanceof IElectricItem)
                                    decreaseStoredEnergy(ElectricItem.charge(tStack, getEnergyVar(), getChargeTier(), false, false), true);
                        }
                    }
                }
            }
            if (mTickTimer % 100 == 0) {
                for (int i = 0; i < 9; i++) {
                    if (mInventory[i] != null) {
                        if (mInventory[i].getItem() instanceof IElectricItem) {
                            if (worldObj.isBlockIndirectlyGettingPowered(xCoord, yCoord, zCoord)) {
                                if (!((IElectricItem) mInventory[i].getItem()).canProvideEnergy() || ElectricItem.discharge(mInventory[i], 1000000, getTier(), true, true) <= 0) {
                                    for (int j = 9; j < 18; j++) {
                                        if (mInventory[j] == null) {
                                            mInventory[j] = mInventory[i];
                                            mInventory[i] = null;
                                            break;
                                        }
                                    }
                                }
                            } else {
                                if (ElectricItem.charge(mInventory[i], 1000000, getTier(), true, true) <= 0) {
                                    for (int j = 9; j < 18; j++) {
                                        if (mInventory[j] == null) {
                                            mInventory[j] = mInventory[i];
                                            mInventory[i] = null;
                                            break;
                                        }
                                    }
                                }
                            }
                        } else {
                            for (int j = 9; j < 18; j++) {
                                if (mInventory[j] == null) {
                                    mInventory[j] = mInventory[i];
                                    mInventory[i] = null;
                                    break;
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    @Override
    public int rechargerSlotStartIndex() {
        return 0;
    }

    @Override
    public int rechargerSlotCount() {
        return this.worldObj.isBlockIndirectlyGettingPowered(this.xCoord, this.yCoord, this.zCoord) ? 0 : 9;
    }

    @Override
    public int dechargerSlotStartIndex() {
        return 0;
    }

    @Override
    public int dechargerSlotCount() {
        return this.worldObj.isBlockIndirectlyGettingPowered(this.xCoord, this.yCoord, this.zCoord) ? 9 : 0;
    }

    @Override
    public int getStartInventorySide(ForgeDirection aSide) {
        if (aSide == ForgeDirection.UP || aSide == ForgeDirection.DOWN) {
            return 0;
        }
        return 9;
    }

    @Override
    public int getSizeInventorySide(ForgeDirection aSide) {
        return 9;
    }

    @Override
    public String getInvName() {
        return GT_LanguageManager.mNameList1[10];
    }

    @Override
    public int getTexture(int aSide, int aMeta) {
        if (aSide == 0) {
            return 3;
        }
        if (aSide == 1) {
            return 6;
        }
        return 9;
    }

    @Override
    public boolean isTeleporterCompatible(Direction side) {
        return true;
    }
}

