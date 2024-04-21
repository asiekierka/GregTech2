/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.InventoryPlayer
 *  net.minecraft.inventory.Container
 *  net.minecraft.inventory.ICrafting
 *  net.minecraft.inventory.IInventory
 *  net.minecraft.inventory.Slot
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 */
package gregtechmod.common.containers;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import gregtechmod.GT_Mod;
import gregtechmod.common.GT_ModHandler;
import gregtechmod.common.GT_Slot_Holo;
import gregtechmod.common.GT_Slot_Output;
import gregtechmod.common.blocks.GT_BlockMetaID_Machine;
import gregtechmod.common.containers.GT_ContainerMetaID_Machine;
import gregtechmod.common.tileentities.GT_TileEntity_ComputerCube;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import java.util.List;

public class GT_Container_ComputerCube
extends GT_ContainerMetaID_Machine {
    public int mEUOut;
    public int mHeat;
    public int mMaxHeat;
    public int mHEM;
    public int mExplosionStrength;
    public int mEU;
    public int mProgress;

    public GT_Container_ComputerCube(InventoryPlayer aInventoryPlayer, GT_TileEntity_ComputerCube aTileEntity, int aID) {
        super(aInventoryPlayer, aTileEntity, aID);
    }

    @Override
    public void addSlots(InventoryPlayer aInventoryPlayer) {
        this.addSlotToContainer(new GT_Slot_Holo((IInventory)this.mTileEntity, 58, 156 + (this.mID == 5 ? 50 : 0), 4, false, false, 1));
        switch (this.mID) {
            case 0: {
                break;
            }
            case 1: {
                this.addSlotToContainer(new GT_Slot_Holo((IInventory)this.mTileEntity, 58, 156, 86, false, false, 1));
                this.addSlotToContainer(new GT_Slot_Holo((IInventory)this.mTileEntity, 58, 156, 70, false, false, 1));
                this.addSlotToContainer(new GT_Slot_Holo((IInventory)this.mTileEntity, 58, 156, 54, false, false, 1));
                for (int y = 0; y < 6; ++y) {
                    for (int x = 0; x < 9; ++x) {
                        this.addSlotToContainer(new GT_Slot_Holo((IInventory)this.mTileEntity, x + y * 9, 5 + x * 16, 5 + y * 16, false, false, 64));
                    }
                }
                this.addSlotToContainer(new GT_Slot_Holo((IInventory)this.mTileEntity, 113, 153, 28, false, false, 64));
                break;
            }
            case 2: {
                this.addSlotToContainer(new Slot((IInventory)this.mTileEntity, 54, 8, 28));
                this.addSlotToContainer(new Slot((IInventory)this.mTileEntity, 55, 26, 28));
                this.addSlotToContainer(new GT_Slot_Output((IInventory)this.mTileEntity, 56, 134, 28));
                this.addSlotToContainer(new GT_Slot_Output((IInventory)this.mTileEntity, 57, 152, 28));
                break;
            }
            case 3: {
                this.addSlotToContainer(new GT_Slot_Holo((IInventory)this.mTileEntity, 58, 88, 65, false, false, 1));
                this.addSlotToContainer(new GT_Slot_Holo((IInventory)this.mTileEntity, 58, 104, 65, false, false, 1));
                this.addSlotToContainer(new GT_Slot_Holo((IInventory)this.mTileEntity, 59, 122, 35, false, false, 64));
                this.addSlotToContainer(new GT_Slot_Holo((IInventory)this.mTileEntity, 60, 92, 5, false, false, 64));
                this.addSlotToContainer(new GT_Slot_Holo((IInventory)this.mTileEntity, 61, 122, 5, false, false, 64));
                this.addSlotToContainer(new GT_Slot_Holo((IInventory)this.mTileEntity, 62, 152, 35, false, false, 64));
                this.addSlotToContainer(new GT_Slot_Holo((IInventory)this.mTileEntity, 63, 122, 65, false, false, 64));
                this.addSlotToContainer(new GT_Slot_Holo((IInventory)this.mTileEntity, 64, 92, 35, false, false, 64));
                break;
            }
            case 4: {
                this.addSlotToContainer(new GT_Slot_Holo((IInventory)this.mTileEntity, 58, 88, 65, false, false, 1));
                this.addSlotToContainer(new GT_Slot_Holo((IInventory)this.mTileEntity, 58, 104, 65, false, false, 1));
                this.addSlotToContainer(new GT_Slot_Holo((IInventory)this.mTileEntity, 59, 122, 5, false, false, 64));
                this.addSlotToContainer(new GT_Slot_Holo((IInventory)this.mTileEntity, 60, 122, 65, false, false, 64));
                this.addSlotToContainer(new GT_Slot_Holo((IInventory)this.mTileEntity, 61, 152, 35, false, false, 64));
                break;
            }
            case 5: {
                this.addSlotToContainer(new GT_Slot_Holo((IInventory)this.mTileEntity, 58, 190, 146, false, false, 1));
                this.addSlotToContainer(new GT_Slot_Holo((IInventory)this.mTileEntity, 58, 206, 146, false, false, 1));
                this.addSlotToContainer(new GT_Slot_Holo((IInventory)this.mTileEntity, 59, 206, 38, false, false, 64));
                this.addSlotToContainer(new GT_Slot_Holo((IInventory)this.mTileEntity, 60, 206, 56, false, false, 64));
                this.addSlotToContainer(new GT_Slot_Holo((IInventory)this.mTileEntity, 61, 206, 74, false, false, 64));
                this.addSlotToContainer(new GT_Slot_Holo((IInventory)this.mTileEntity, 62, 206, 92, false, false, 64));
                this.addSlotToContainer(new GT_Slot_Holo((IInventory)this.mTileEntity, 63, 206, 110, false, false, 64));
                this.addSlotToContainer(new GT_Slot_Holo((IInventory)this.mTileEntity, 64, 153, 7, false, false, 64));
                this.addSlotToContainer(new GT_Slot_Holo((IInventory)this.mTileEntity, 65, 169, 7, false, false, 64));
                this.addSlotToContainer(new GT_Slot_Holo((IInventory)this.mTileEntity, 66, 185, 7, false, false, 64));
                this.addSlotToContainer(new GT_Slot_Holo((IInventory)this.mTileEntity, 67, 153, 23, false, false, 64));
                this.addSlotToContainer(new GT_Slot_Holo((IInventory)this.mTileEntity, 68, 169, 23, false, false, 64));
                this.addSlotToContainer(new GT_Slot_Holo((IInventory)this.mTileEntity, 69, 185, 23, false, false, 64));
                this.addSlotToContainer(new GT_Slot_Holo((IInventory)this.mTileEntity, 70, 153, 39, false, false, 64));
                this.addSlotToContainer(new GT_Slot_Holo((IInventory)this.mTileEntity, 71, 169, 39, false, false, 64));
                this.addSlotToContainer(new GT_Slot_Holo((IInventory)this.mTileEntity, 72, 185, 39, false, false, 64));
                break;
            }
            case 6: {
                this.addSlotToContainer(new GT_Slot_Holo((IInventory)this.mTileEntity, 58, 88, 65, false, false, 1));
                this.addSlotToContainer(new GT_Slot_Holo((IInventory)this.mTileEntity, 58, 104, 65, false, false, 1));
                this.addSlotToContainer(new GT_Slot_Holo((IInventory)this.mTileEntity, 59, 122, 35, false, false, 64));
                this.addSlotToContainer(new GT_Slot_Holo((IInventory)this.mTileEntity, 60, 92, 5, false, false, 64));
                this.addSlotToContainer(new GT_Slot_Holo((IInventory)this.mTileEntity, 61, 122, 5, false, false, 64));
                this.addSlotToContainer(new GT_Slot_Holo((IInventory)this.mTileEntity, 62, 152, 35, false, false, 64));
                this.addSlotToContainer(new GT_Slot_Holo((IInventory)this.mTileEntity, 63, 122, 65, false, false, 64));
                this.addSlotToContainer(new GT_Slot_Holo((IInventory)this.mTileEntity, 64, 92, 35, false, false, 64));
            }
        }
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    @Override
    public ItemStack slotClick(int aSlotIndex, int aMouseclick, int aShifthold, EntityPlayer aPlayer) {
        if (aSlotIndex < 0) {
            return super.slotClick(aSlotIndex, aMouseclick, aShifthold, aPlayer);
        }
        if (this.mID != ((GT_TileEntity_ComputerCube)this.mTileEntity).mMode) {
            return null;
        }
        Slot tSlot = (Slot)this.inventorySlots.get(aSlotIndex);
        ItemStack tStack = tSlot.getStack();
        if (tSlot == null) return null;
        if (aSlotIndex == 0) {
            if (aMouseclick == 0) {
                ((GT_TileEntity_ComputerCube)this.mTileEntity).switchModeForward();
            } else {
                ((GT_TileEntity_ComputerCube)this.mTileEntity).switchModeBackward();
            }
            aPlayer.openGui((Object)GT_Mod.instance, GT_BlockMetaID_Machine.getComputerCubeGUIID(this.mTileEntity), this.mTileEntity.worldObj, this.mTileEntity.xCoord, this.mTileEntity.yCoord, this.mTileEntity.zCoord);
            return null;
        } else if (aSlotIndex <= 2 && this.mID == 3) {
            if (aSlotIndex == 1) {
                ((GT_TileEntity_ComputerCube)this.mTileEntity).switchCentrifugePageBackward();
                this.onCraftMatrixChanged((IInventory)this.mTileEntity);
                return null;
            } else {
                if (aSlotIndex != 2) return null;
                ((GT_TileEntity_ComputerCube)this.mTileEntity).switchCentrifugePageForward();
                this.onCraftMatrixChanged((IInventory)this.mTileEntity);
            }
            return null;
        } else if (aSlotIndex <= 2 && this.mID == 6) {
            if (aSlotIndex == 1) {
                ((GT_TileEntity_ComputerCube)this.mTileEntity).switchElectrolyzerPageBackward();
                this.onCraftMatrixChanged((IInventory)this.mTileEntity);
                return null;
            } else {
                if (aSlotIndex != 2) return null;
                ((GT_TileEntity_ComputerCube)this.mTileEntity).switchElectrolyzerPageForward();
                this.onCraftMatrixChanged((IInventory)this.mTileEntity);
            }
            return null;
        } else if (aSlotIndex <= 2 && this.mID == 4) {
            if (aSlotIndex == 1) {
                ((GT_TileEntity_ComputerCube)this.mTileEntity).switchFusionPageBackward();
                this.onCraftMatrixChanged((IInventory)this.mTileEntity);
                return null;
            } else {
                if (aSlotIndex != 2) return null;
                ((GT_TileEntity_ComputerCube)this.mTileEntity).switchFusionPageForward();
                this.onCraftMatrixChanged((IInventory)this.mTileEntity);
            }
            return null;
        } else if (aSlotIndex <= 2 && this.mID == 5) {
            if (aSlotIndex == 1) {
                ((GT_TileEntity_ComputerCube)this.mTileEntity).switchDescriptionPageBackward();
                this.onCraftMatrixChanged((IInventory)this.mTileEntity);
                return null;
            } else {
                if (aSlotIndex != 2) return null;
                ((GT_TileEntity_ComputerCube)this.mTileEntity).switchDescriptionPageForward();
                this.onCraftMatrixChanged((IInventory)this.mTileEntity);
            }
            return null;
        } else {
            if (aSlotIndex > 58 || this.mID != 1) return super.slotClick(aSlotIndex, aMouseclick, aShifthold, aPlayer);
            if (aSlotIndex == 1) {
                ((GT_TileEntity_ComputerCube)this.mTileEntity).switchNuclearReactor();
                this.onCraftMatrixChanged((IInventory)this.mTileEntity);
                return null;
            } else if (aSlotIndex == 2) {
                ((GT_TileEntity_ComputerCube)this.mTileEntity).loadNuclearReactor();
                this.onCraftMatrixChanged((IInventory)this.mTileEntity);
                return null;
            } else if (aSlotIndex == 3) {
                ((GT_TileEntity_ComputerCube)this.mTileEntity).saveNuclearReactor();
                return null;
            } else {
                if (aShifthold == 1) {
                    tSlot.putStack(null);
                    return null;
                }
                if (aMouseclick == 0) {
                    if (tStack == null) {
                        if (this.getSlot(58).getStack() != null && aSlotIndex != 58) {
                            tSlot.putStack(this.getSlot(58).getStack().copy());
                            return null;
                        } else {
                            tSlot.putStack(new ItemStack((Item)GT_TileEntity_ComputerCube.sReactorList.get(0), 1));
                        }
                        return null;
                    }
                    for (int i = 1; i < GT_TileEntity_ComputerCube.sReactorList.size(); ++i) {
                        if (((Item)GT_TileEntity_ComputerCube.sReactorList.get((int)(i - 1))).itemID != tStack.itemID) continue;
                        tSlot.putStack(new ItemStack((Item)GT_TileEntity_ComputerCube.sReactorList.get(i), 1, 0));
                        if (tSlot.getStack() == null || tSlot.getStack().getItem() != GT_ModHandler.getIC2Item("reactorIsotopeCell", 1).getItem()) return null;
                        tSlot.getStack().setItemDamage(tSlot.getStack().getMaxDamage() - 1);
                        return null;
                    }
                    tSlot.putStack(null);
                    return null;
                }
                if (tStack == null) {
                    return null;
                }
                if (tStack.stackSize < tStack.getMaxStackSize()) {
                    ++tStack.stackSize;
                    return null;
                }
                tStack.stackSize = 1;
                return null;
            }
        }
    }

    @Override
    public boolean doesBindPlayerInventory() {
        return this.mID != 1 && this.mID != 5;
    }

    @Override
    public void detectAndSendChanges() {
        super.detectAndSendChanges();
        if (this.mTileEntity.worldObj.isRemote) {
            return;
        }
        this.mEUOut = ((GT_TileEntity_ComputerCube)this.mTileEntity).mEUOut;
        this.mHeat = ((GT_TileEntity_ComputerCube)this.mTileEntity).mHeat;
        this.mMaxHeat = ((GT_TileEntity_ComputerCube)this.mTileEntity).mMaxHeat;
        this.mHEM = (int)(((GT_TileEntity_ComputerCube)this.mTileEntity).mHEM * 10000.0f);
        this.mExplosionStrength = (int)(((GT_TileEntity_ComputerCube)this.mTileEntity).mExplosionStrength * 100.0f);
        this.mEU = ((GT_TileEntity_ComputerCube)this.mTileEntity).mEU;
        this.mProgress = ((GT_TileEntity_ComputerCube)this.mTileEntity).mProgress;
        for (ICrafting var1 : (List<ICrafting>) this.crafters) {
            var1.sendProgressBarUpdate((Container)this, 101, this.mEUOut);
            var1.sendProgressBarUpdate((Container)this, 102, this.mHeat & 0xFFFF);
            var1.sendProgressBarUpdate((Container)this, 103, this.mMaxHeat & 0xFFFF);
            var1.sendProgressBarUpdate((Container)this, 104, this.mHEM);
            var1.sendProgressBarUpdate((Container)this, 105, this.mExplosionStrength);
            var1.sendProgressBarUpdate((Container)this, 106, this.mHeat >>> 16);
            var1.sendProgressBarUpdate((Container)this, 107, this.mMaxHeat >>> 16);
            var1.sendProgressBarUpdate((Container)this, 108, this.mEU & 0xFFFF);
            var1.sendProgressBarUpdate((Container)this, 109, this.mEU >>> 16);
            var1.sendProgressBarUpdate((Container)this, 110, this.mProgress);
        }
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    public void updateProgressBar(int par1, int par2) {
        super.updateProgressBar(par1, par2);
        switch (par1) {
            case 101: {
                this.mEUOut = par2;
                break;
            }
            case 102: {
                this.mHeat = this.mHeat & 0xFFFF0000 | par2;
                break;
            }
            case 103: {
                this.mMaxHeat = this.mMaxHeat & 0xFFFF0000 | par2;
                break;
            }
            case 104: {
                this.mHEM = par2;
                break;
            }
            case 105: {
                this.mExplosionStrength = par2;
                break;
            }
            case 106: {
                this.mHeat = this.mHeat & 0xFFFF | par2 << 16;
                break;
            }
            case 107: {
                this.mMaxHeat = this.mMaxHeat & 0xFFFF | par2 << 16;
                break;
            }
            case 108: {
                this.mEU = this.mEU & 0xFFFF0000 | par2;
            }
            case 109: {
                this.mEU = this.mEU & 0xFFFF | par2 << 16;
                break;
            }
            case 110: {
                this.mProgress = par2;
            }
        }
    }

    @Override
    public int getSlotStartIndex() {
        return 1;
    }

    @Override
    public int getSlotCount() {
        return this.mID == 2 ? 4 : 0;
    }

    @Override
    public int getShiftClickSlotCount() {
        return this.mID == 2 ? 2 : 0;
    }
}

