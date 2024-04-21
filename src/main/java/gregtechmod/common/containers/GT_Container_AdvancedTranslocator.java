package gregtechmod.common.containers;

import gregtechmod.api.BaseMetaTileEntity;
import gregtechmod.common.GT_Slot_Holo;
import gregtechmod.common.tileentities.GT_MetaTileEntity_AdvancedTranslocator;

import java.util.Iterator;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.network.packet.Packet3Chat;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class GT_Container_AdvancedTranslocator extends GT_ContainerMetaTile_Machine {

	public GT_Container_AdvancedTranslocator(InventoryPlayer aInventoryPlayer, BaseMetaTileEntity aTileEntity, int aID) {
		super(aInventoryPlayer, aTileEntity, aID);
	}

    public void addSlots(InventoryPlayer aInventoryPlayer) {
        addSlotToContainer(new GT_Slot_Holo(mTileEntity,  0,  63,  6, false, true, 1));
        addSlotToContainer(new GT_Slot_Holo(mTileEntity,  1,  80,  6, false, true, 1));
        addSlotToContainer(new GT_Slot_Holo(mTileEntity,  2,  97,  6, false, true, 1));
        addSlotToContainer(new GT_Slot_Holo(mTileEntity,  3,  63, 23, false, true, 1));
        addSlotToContainer(new GT_Slot_Holo(mTileEntity,  4,  80, 23, false, true, 1));
        addSlotToContainer(new GT_Slot_Holo(mTileEntity,  5,  97, 23, false, true, 1));
        addSlotToContainer(new GT_Slot_Holo(mTileEntity,  6,  63, 40, false, true, 1));
        addSlotToContainer(new GT_Slot_Holo(mTileEntity,  7,  80, 40, false, true, 1));
        addSlotToContainer(new GT_Slot_Holo(mTileEntity,  8,  97, 40, false, true, 1));

        addSlotToContainer(new GT_Slot_Holo(mTileEntity,  9,   8, 63, false, true, 1));
        addSlotToContainer(new GT_Slot_Holo(mTileEntity,  9,  26, 63, false, true, 1));
        addSlotToContainer(new GT_Slot_Holo(mTileEntity,  9,  43,  5, false, true, 1));
        addSlotToContainer(new GT_Slot_Holo(mTileEntity,  9, 117,  5, false, true, 1));
    }

    public ItemStack slotClick(int aSlotIndex, int aMouseclick, int aShifthold, EntityPlayer aPlayer) {
    	if (aSlotIndex < 0) return super.slotClick(aSlotIndex, aMouseclick, aShifthold, aPlayer);
	    
    	Slot tSlot = (Slot)inventorySlots.get(aSlotIndex);
	    if (tSlot != null) {
	    	if (mTileEntity.mMetaTileEntity == null) return null;
		    if (aSlotIndex < 9) {
		    	ItemStack tStack = aPlayer.inventory.getItemStack();
		    	if (tStack != null) {
		    		tStack = tStack.copy();
			    	tStack.stackSize = 1;
		    	}
		    	tSlot.putStack(tStack);
		    	return null;
		    } else if (aSlotIndex == 9) {
		    	((GT_MetaTileEntity_AdvancedTranslocator)mTileEntity.mMetaTileEntity).bOutput = !((GT_MetaTileEntity_AdvancedTranslocator)mTileEntity.mMetaTileEntity).bOutput;
			    if (aPlayer instanceof EntityPlayerMP)
			    	if (((GT_MetaTileEntity_AdvancedTranslocator)mTileEntity.mMetaTileEntity).bOutput)
			    		((EntityPlayerMP)aPlayer).playerNetServerHandler.sendPacketToPlayer(new Packet3Chat("Emit Energy to Outputside"));
			    	else
			    		((EntityPlayerMP)aPlayer).playerNetServerHandler.sendPacketToPlayer(new Packet3Chat("Don't emit Energy"));
		    	return null;
		    } else if (aSlotIndex == 10) {
		    	((GT_MetaTileEntity_AdvancedTranslocator)mTileEntity.mMetaTileEntity).bInvertFilter = !((GT_MetaTileEntity_AdvancedTranslocator)mTileEntity.mMetaTileEntity).bInvertFilter;
			    if (aPlayer instanceof EntityPlayerMP)
			    	if (((GT_MetaTileEntity_AdvancedTranslocator)mTileEntity.mMetaTileEntity).bInvertFilter)
			    		((EntityPlayerMP)aPlayer).playerNetServerHandler.sendPacketToPlayer(new Packet3Chat("Inverted the Filter"));
			    	else
			    		((EntityPlayerMP)aPlayer).playerNetServerHandler.sendPacketToPlayer(new Packet3Chat("Filter works normal"));
		    	return null;
		    } else if (aSlotIndex == 11) {
		    	((GT_MetaTileEntity_AdvancedTranslocator)mTileEntity.mMetaTileEntity).mInputSide = (((GT_MetaTileEntity_AdvancedTranslocator)mTileEntity.mMetaTileEntity).mInputSide + 1) % 6;
		    	
		    	
		    } else if (aSlotIndex == 12) {
		    	((GT_MetaTileEntity_AdvancedTranslocator)mTileEntity.mMetaTileEntity).mOutputSide = (((GT_MetaTileEntity_AdvancedTranslocator)mTileEntity.mMetaTileEntity).mOutputSide + 1) % 6;
		    	
		    	
		    }
    	}
	    
    	return super.slotClick(aSlotIndex, aMouseclick, aShifthold, aPlayer);
    }
    
    public int mInputSide, mOutputSide;
    
    public void updateCraftingResults() {
        super.updateCraftingResults();
    	if (mTileEntity.worldObj.isRemote) return;
    	mInputSide  = ((GT_MetaTileEntity_AdvancedTranslocator)mTileEntity.mMetaTileEntity).mInputSide;
    	mOutputSide = ((GT_MetaTileEntity_AdvancedTranslocator)mTileEntity.mMetaTileEntity).mOutputSide;
    	
        Iterator var2 = this.crafters.iterator();
        while (var2.hasNext()) {
            ICrafting var1 = (ICrafting)var2.next();
            var1.sendProgressBarUpdate(this, 10, mInputSide);
            var1.sendProgressBarUpdate(this, 11, mOutputSide);
        }
    }
    
    public void addCraftingToCrafters(ICrafting par1ICrafting) {
        super.addCraftingToCrafters(par1ICrafting);
    }
    
    @SideOnly(Side.CLIENT)
    public void updateProgressBar(int par1, int par2) {
    	super.updateProgressBar(par1, par2);
    	switch (par1) {
    	case 10: mInputSide = par2; break;
    	case 11: mOutputSide = par2; break;
    	}
    }
    
    public int getSlotCount() {
    	return 0;
    }

    public int getAllSlotCount() {
    	return 12;
    }
    
    public int getShiftClickSlotCount() {
    	return 0;
    }
}
