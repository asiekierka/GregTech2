/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.inventory.Container
 *  net.minecraft.inventory.InventoryCrafting
 *  net.minecraft.item.ItemStack
 *  net.minecraft.item.crafting.CraftingManager
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraftforge.common.ForgeDirection
 *  net.minecraftforge.liquids.ILiquidTank
 *  net.minecraftforge.liquids.ITankContainer
 *  net.minecraftforge.liquids.LiquidContainerRegistry
 *  net.minecraftforge.liquids.LiquidDictionary
 *  net.minecraftforge.liquids.LiquidStack
 */
package gregtechmod.common.tileentities;

import gregtechmod.api.IDigitalChest;
import gregtechmod.common.GT_LanguageManager;
import gregtechmod.common.GT_ModHandler;
import gregtechmod.common.tileentities.GT_TileEntityMetaID_Machine;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.ForgeDirection;
import net.minecraftforge.liquids.ILiquidTank;
import net.minecraftforge.liquids.ITankContainer;
import net.minecraftforge.liquids.LiquidContainerRegistry;
import net.minecraftforge.liquids.LiquidDictionary;
import net.minecraftforge.liquids.LiquidStack;

public class GT_TileEntity_UUMAssembler
extends GT_TileEntityMetaID_Machine
implements IDigitalChest,
ITankContainer {
    public LiquidStack mUUMperBlob;
    public int mItemCount = 0;
    public int mItemID;
    public int mItemMeta;
    public int mStoredLiquidUUM;
    private int[] mUUMprices;
    private InternalTank mTank;

    @Override
    public boolean isAccessible(EntityPlayer aPlayer) {
        return true;
    }

    @Override
    public int getInventorySlotCount() {
        return 52;
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
        return 100000;
    }

    @Override
    public int maxEUInput() {
        return 128;
    }

    public GT_TileEntity_UUMAssembler() {
        this.mItemID = GT_ModHandler.getIC2Item((String)"matter", (int)1).itemID;
        this.mItemMeta = GT_ModHandler.getIC2Item("matter", 1).getItemDamage();
        this.mStoredLiquidUUM = 0;
        this.mUUMprices = new int[20];
        this.mTank = new InternalTank(this);
        this.mUUMperBlob = LiquidContainerRegistry.getLiquidForFilledItem((ItemStack)GT_ModHandler.getIC2Item("matter", 1));
        if (this.mUUMperBlob == null) {
            this.mUUMperBlob = LiquidDictionary.getLiquid((String)"liquidUU", (int)1000);
        }
    }

    @Override
    public void storeAdditionalData(NBTTagCompound aNBT) {
        aNBT.setInteger("mItemCount", this.mItemCount);
        aNBT.setInteger("mStoredLiquidUUM", this.mStoredLiquidUUM);
        aNBT.setIntArray("mUUMprices", this.mUUMprices);
    }

    @Override
    public void getAdditionalData(NBTTagCompound aNBT) {
        this.mItemCount = aNBT.getInteger("mItemCount");
        this.mStoredLiquidUUM = aNBT.getInteger("mStoredLiquidUUM");
        this.mUUMprices = aNBT.getIntArray("mUUMprices");
    }

    @Override
    public void onPostTickUpdate() {
        if (!this.worldObj.isRemote) {
            if (this.mUUMprices == null || this.mUUMprices.length < 20) {
                this.mUUMprices = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
            }
            if (this.mUUMperBlob != null && this.mStoredLiquidUUM >= this.mUUMperBlob.amount) {
                this.mStoredLiquidUUM -= this.mUUMperBlob.amount;
                ++this.mItemCount;
            }
            if (this.mInventory[0] != null && this.mItemCount + 256 < Integer.MAX_VALUE && this.mInventory[0].itemID == this.mItemID && this.mInventory[0].getItemDamage() == this.mItemMeta) {
                this.mItemCount += this.mInventory[0].stackSize;
                this.setInventorySlotContents(0, null);
            }
            if (this.mItemCount > 0) {
                if (this.mInventory[1] == null) {
                    --this.mItemCount;
                    this.mInventory[1] = new ItemStack(this.mItemID, 1, this.mItemMeta);
                }
                if (this.mInventory[1].itemID == this.mItemID && this.mInventory[1].getItemDamage() == this.mItemMeta) {
                    while (this.mInventory[1].stackSize < this.mInventory[1].getMaxStackSize() && this.mItemCount > 0) {
                        --this.mItemCount;
                        ++this.mInventory[1].stackSize;
                    }
                }
            }
            this.updateRecipe();
        }
        if (this.mTickTimer % 10L == 0L) {
            this.checkUUMRecipesForCreation();
        }
    }

    public void updateRecipe() {
        InventoryCrafting aCrafting = new InventoryCrafting(new Container(){

            public boolean canInteractWith(EntityPlayer var1) {
                return false;
            }
        }, 3, 3);
        for (int i = 0; i < 9; ++i) {
            ItemStack tItemStack = this.mInventory[i + 2];
            if (tItemStack != null) {
                tItemStack = tItemStack.copy();
            }
            aCrafting.setInventorySlotContents(i, tItemStack);
        }
        ItemStack tStack = CraftingManager.getInstance().findMatchingRecipe(aCrafting, this.worldObj);
        if (tStack == null) {
            this.setInventorySlotContents(11, null);
        } else {
            this.setInventorySlotContents(11, tStack.copy());
        }
    }

    public void setUUMprice(int aIndex, int aPrice) {
        this.mUUMprices[aIndex] = aPrice;
    }

    private void checkUUMRecipesForCreation() {
        if (this.mInventory[1] != null && this.mInventory[1].isItemEqual(GT_ModHandler.getIC2Item("matter", 1))) {
            for (int i = 0; i < 20; ++i) {
                int tUUMprice = this.mUUMprices[i];
                int tEnergycosts = 512 * tUUMprice;
                if (tUUMprice <= 0 || this.getStored() < tEnergycosts || this.mInventory[i + 12] == null || this.mInventory[i + 32] != null || this.mInventory[1] == null || !this.mInventory[1].isItemEqual(GT_ModHandler.getIC2Item("matter", 1)) || this.mInventory[1].stackSize < tUUMprice || !this.decreaseStoredEnergy(tEnergycosts, true)) continue;
                this.mInventory[i + 32] = this.mInventory[i + 12].copy();
                this.decrStackSize(1, tUUMprice);
            }
        }
    }

    @Override
    public boolean isValidSlot(int aIndex) {
        return aIndex < 2 || aIndex > 31;
    }

    @Override
    public int getStartInventorySide(ForgeDirection aSide) {
        if (aSide == ForgeDirection.UP || aSide == ForgeDirection.DOWN) {
            return 0;
        }
        return 32;
    }

    @Override
    public int getSizeInventorySide(ForgeDirection aSide) {
        if (aSide == ForgeDirection.UP || aSide == ForgeDirection.DOWN) {
            return 2;
        }
        return 20;
    }

    public int getContentCount() {
        return this.mItemCount;
    }

    @Override
    public String getInvName() {
        return GT_LanguageManager.mNameList1[5];
    }

    @Override
    public String getMainInfo() {
        return GT_ModHandler.getIC2Item("matter", 1).getDisplayName();
    }

    @Override
    public String getSecondaryInfo() {
        return "" + (this.mItemCount > 0 ? this.mItemCount + 66 : 0);
    }

    @Override
    public String getTertiaryInfo() {
        return "";
    }

    @Override
    public boolean isGivingInformation() {
        return true;
    }

    @Override
    public int getTexture(int aSide, int aMeta) {
        if (aSide == 0) {
            return 3;
        }
        if (aSide == 1) {
            return 7;
        }
        return 11;
    }

    @Override
    public boolean isDigitalChest() {
        return true;
    }

    @Override
    public ItemStack[] getStoredItemData() {
        return new ItemStack[]{new ItemStack(this.mItemID, this.mItemCount, this.mItemMeta)};
    }

    public int fill(ForgeDirection from, LiquidStack resource, boolean doFill) {
        return this.mTank.fill(resource, doFill);
    }

    public int fill(int tankIndex, LiquidStack resource, boolean doFill) {
        return this.mTank.fill(resource, doFill);
    }

    public LiquidStack drain(ForgeDirection from, int maxDrain, boolean doDrain) {
        return null;
    }

    public LiquidStack drain(int tankIndex, int maxDrain, boolean doDrain) {
        return null;
    }

    public ILiquidTank[] getTanks(ForgeDirection direction) {
        return new ILiquidTank[]{this.mTank};
    }

    public ILiquidTank getTank(ForgeDirection direction, LiquidStack type) {
        return this.mTank;
    }

    @Override
    public void setItemCount(int aCount) {
        this.mItemCount = aCount;
    }

    @Override
    public int getMaxItemCount() {
        return 2000000000;
    }

    private class InternalTank
    implements ILiquidTank {
        public GT_TileEntity_UUMAssembler mAssembler;

        public InternalTank(GT_TileEntity_UUMAssembler aAssembler) {
            this.mAssembler = aAssembler;
        }

        public LiquidStack getLiquid() {
            return new LiquidStack(this.mAssembler.mUUMperBlob.itemID, this.mAssembler.mStoredLiquidUUM, this.mAssembler.mUUMperBlob.itemMeta);
        }

        public int getCapacity() {
            return 10000;
        }

        public int fill(LiquidStack resource, boolean doFill) {
            if (this.mAssembler.mUUMperBlob == null || this.mAssembler.mUUMperBlob.itemID <= 0) {
                return 0;
            }
            if (resource == null || resource.itemID <= 0) {
                return 0;
            }
            if (!this.mAssembler.mUUMperBlob.isLiquidEqual(resource)) {
                return 0;
            }
            if (doFill) {
                this.mAssembler.mStoredLiquidUUM += resource.amount;
            }
            return resource.amount;
        }

        public LiquidStack drain(int maxDrain, boolean doDrain) {
            return null;
        }

        public int getTankPressure() {
            return -100;
        }
    }
}

