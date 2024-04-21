package gregtechmod.common.containers;

import gregtechmod.api.BaseMetaTileEntity;
import gregtechmod.common.GT_Slot_Holo;
import gregtechmod.common.GT_Slot_Output;

import java.util.Iterator;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class GT_ContainerMetaTile_Machine extends Container {

    protected BaseMetaTileEntity mTileEntity;
    
    public GT_ContainerMetaTile_Machine (InventoryPlayer aInventoryPlayer, BaseMetaTileEntity aTileEntity, int aID) {
        mTileEntity = aTileEntity;
        mID = aID;
        
        addSlots(aInventoryPlayer);
        
        if (doesBindPlayerInventory()) bindPlayerInventory(aInventoryPlayer);
        
        updateCraftingResults();
    }
    
    public int mEnergy, mStorage, mOutput, mInput, mID, mDisplayErrorCode;
    
    @Override
    public void updateCraftingResults() {
        super.updateCraftingResults();
    	if (mTileEntity.worldObj.isRemote) return;
        mStorage = mTileEntity.getCapacity();
    	mEnergy = mTileEntity.getStored();
    	mOutput = mTileEntity.getOutput();
    	mInput = mTileEntity.mMetaTileEntity.maxEUInput();
    	mDisplayErrorCode = mTileEntity.mDisplayErrorCode;
    	
        Iterator var2 = this.crafters.iterator();
        while (var2.hasNext()) {
            ICrafting var1 = (ICrafting)var2.next();
            var1.sendProgressBarUpdate(this, 0, mEnergy & 65535);
            var1.sendProgressBarUpdate(this, 1, mEnergy >>> 16);
            var1.sendProgressBarUpdate(this, 2, mStorage & 65535);
            var1.sendProgressBarUpdate(this, 3, mStorage >>> 16);
            var1.sendProgressBarUpdate(this, 4, mOutput);
            var1.sendProgressBarUpdate(this, 5, mInput);
            var1.sendProgressBarUpdate(this, 6, mDisplayErrorCode);
        }
    }
    
    @Override
    public void addCraftingToCrafters(ICrafting par1ICrafting) {
        super.addCraftingToCrafters(par1ICrafting);
    }
    
    @SideOnly(Side.CLIENT)
    public void updateProgressBar(int par1, int par2) {
    	super.updateProgressBar(par1, par2);
    	switch (par1) {
    	case 0: mEnergy = mEnergy & -65536 | par2; break;
    	case 1: mEnergy = mEnergy &  65535 | par2 << 16; break;
    	case 2: mStorage = mStorage & -65536 | par2; break;
    	case 3: mStorage = mStorage &  65535 | par2 << 16; break;
    	case 4: mOutput = par2; break;
    	case 5: mInput = par2; break;
    	case 6: mDisplayErrorCode = par2; break;
    	}
    }
    
    
    /**
     * To add the Slots to your GUI
     */
    public void addSlots(InventoryPlayer aInventoryPlayer) {
    	
    }
    
    /**
     * Amount of Slots in the GUI
     */
    public int getSlotCount() {
    	return 0;
    }

    /**
     * Amount of ALL Slots in the GUI including Holoslots
     */
    public int getAllSlotCount() {
    	return getSlotCount();
    }
    
    /**
     * Start-Index of the usable Slots
     */
    public int getSlotStartIndex() {
    	return 0;
    }
    
    /**
     * Amount of Slots in the GUI the player can Shift-Click into.
     */
    public int getShiftClickSlotCount() {
    	return 0;
    }

    /**
     * Is Player-Inventory visible?
     */
    public boolean doesBindPlayerInventory() {
    	return true;
    }
    
    @Override
    public boolean canInteractWith(EntityPlayer player) {
        return mTileEntity.isUseableByPlayer(player);
    }

	protected void bindPlayerInventory(InventoryPlayer aInventoryPlayer) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 9; j++) {
            	addSlotToContainer(new Slot(aInventoryPlayer, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
            }
        }

        for (int i = 0; i < 9; i++) {
            addSlotToContainer(new Slot(aInventoryPlayer, i, 8 + i * 18, 142));
        }
    }

    @Override
	public ItemStack slotClick(int aSlotIndex, int aMouseclick, int aShifthold, EntityPlayer aPlayer) {
    	return super.slotClick(aSlotIndex, aMouseclick, aShifthold, aPlayer);
	}

    @Override
	public ItemStack transferStackInSlot(EntityPlayer par1EntityPlayer, int slot) {
        ItemStack stack = null;
        Slot slotObject = (Slot)inventorySlots.get(slot);
        
        //null checks and checks if the item can be stacked (maxStackSize > 1)
        if (getSlotCount() > 0 && slotObject != null && slotObject.getHasStack() && !(slotObject instanceof GT_Slot_Holo)) {
            ItemStack stackInSlot = slotObject.getStack();
            stack = stackInSlot.copy();
            
            //TileEntity -> Player
            if (slot < getAllSlotCount()) {
            	if (doesBindPlayerInventory())
            		if (!mergeItemStack(stackInSlot, getAllSlotCount(), getAllSlotCount()+36, true)) {
            			return null;
                }
            //Player -> TileEntity
            } else if (!mergeItemStack(stackInSlot, getSlotStartIndex(), getSlotStartIndex()+getShiftClickSlotCount(), false)) {
                return null;
            }

            if (stackInSlot.stackSize == 0) {
                slotObject.putStack(null);
            } else {
                slotObject.onSlotChanged();
            }
        }
        return stack;
	}
	

    /**
     * merges provided ItemStack with the first avaliable one in the container/player inventory
     */
    @Override
    protected boolean mergeItemStack(ItemStack par1ItemStack, int aStartIndex, int aSlotCount, boolean par4) {
        boolean var5 = false;
        int var6 = aStartIndex;

        if (par4) {
            var6 = aSlotCount - 1;
        }

        Slot var7;
        ItemStack var8;

        if (par1ItemStack.isStackable()) {
            while (par1ItemStack.stackSize > 0 && (!par4 && var6 < aSlotCount || par4 && var6 >= aStartIndex)) {
                var7 = (Slot)this.inventorySlots.get(var6);
                var8 = var7.getStack();
                
                if (!(var7 instanceof GT_Slot_Holo) && !(var7 instanceof GT_Slot_Output) && var8 != null && var8.itemID == par1ItemStack.itemID && (!par1ItemStack.getHasSubtypes() || par1ItemStack.getItemDamage() == var8.getItemDamage()) && ItemStack.areItemStackTagsEqual(par1ItemStack, var8)) {
                    int var9 = var8.stackSize + par1ItemStack.stackSize;

                    if (var9 <= par1ItemStack.getMaxStackSize()) {
                        par1ItemStack.stackSize = 0;
                        var8.stackSize = var9;
                        var7.onSlotChanged();
                        var5 = true;
                    } else if (var8.stackSize < par1ItemStack.getMaxStackSize()) {
                        par1ItemStack.stackSize -= par1ItemStack.getMaxStackSize() - var8.stackSize;
                        var8.stackSize = par1ItemStack.getMaxStackSize();
                        var7.onSlotChanged();
                        var5 = true;
                    }
                }
                
                if (par4) {
                    --var6;
                } else {
                    ++var6;
                }
            }
        }

        if (par1ItemStack.stackSize > 0)
        {
            if (par4)
            {
                var6 = aSlotCount - 1;
            }
            else
            {
                var6 = aStartIndex;
            }

            while (!par4 && var6 < aSlotCount || par4 && var6 >= aStartIndex)
            {
                var7 = (Slot)this.inventorySlots.get(var6);
                var8 = var7.getStack();

                if (var8 == null)
                {
                    var7.putStack(par1ItemStack.copy());
                    var7.onSlotChanged();
                    par1ItemStack.stackSize = 0;
                    var5 = true;
                    break;
                }

                if (par4)
                {
                    --var6;
                }
                else
                {
                    ++var6;
                }
            }
        }

        return var5;
    }
}