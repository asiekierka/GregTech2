package gregtechmod.common.containers;

import gregtechmod.api.BaseMetaTileEntity;
import gregtechmod.common.GT_Slot_Holo;
import gregtechmod.common.tileentities.GT_MetaTileEntity_ElectricBufferSmall;
import gregtechmod.common.tileentities.GT_MetaTileEntity_ElectricSorter;

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

public class GT_Container_ElectricSorter extends GT_ContainerMetaTile_Machine {

	public GT_Container_ElectricSorter(InventoryPlayer aInventoryPlayer, BaseMetaTileEntity aTileEntity, int aID) {
		super(aInventoryPlayer, aTileEntity, aID);
	}

    public void addSlots(InventoryPlayer aInventoryPlayer) {
        addSlotToContainer(new Slot(mTileEntity,  0,  25,  23));
    	
        addSlotToContainer(new GT_Slot_Holo(mTileEntity,  1,  63,  6, false, true, 1));
        addSlotToContainer(new GT_Slot_Holo(mTileEntity,  2,  80,  6, false, true, 1));
        addSlotToContainer(new GT_Slot_Holo(mTileEntity,  3,  97,  6, false, true, 1));
        addSlotToContainer(new GT_Slot_Holo(mTileEntity,  4,  63, 23, false, true, 1));
        addSlotToContainer(new GT_Slot_Holo(mTileEntity,  5,  80, 23, false, true, 1));
        addSlotToContainer(new GT_Slot_Holo(mTileEntity,  6,  97, 23, false, true, 1));
        addSlotToContainer(new GT_Slot_Holo(mTileEntity,  7,  63, 40, false, true, 1));
        addSlotToContainer(new GT_Slot_Holo(mTileEntity,  8,  80, 40, false, true, 1));
        addSlotToContainer(new GT_Slot_Holo(mTileEntity,  9,  97, 40, false, true, 1));

        addSlotToContainer(new GT_Slot_Holo(mTileEntity, 10,   8, 63, false, true, 1));
        addSlotToContainer(new GT_Slot_Holo(mTileEntity, 10,  26, 63, false, true, 1));
        addSlotToContainer(new GT_Slot_Holo(mTileEntity, 10,  44, 63, false, true, 1));
        addSlotToContainer(new GT_Slot_Holo(mTileEntity, 10, 134, 63, false, true, 1));
    }

    public ItemStack slotClick(int aSlotIndex, int aMouseclick, int aShifthold, EntityPlayer aPlayer) {
    	if (aSlotIndex < 1) return super.slotClick(aSlotIndex, aMouseclick, aShifthold, aPlayer);
	    
    	Slot tSlot = (Slot)inventorySlots.get(aSlotIndex);
	    if (tSlot != null) {
	    	if (mTileEntity.mMetaTileEntity == null) return null;
		    if (aSlotIndex < 10) {
		    	ItemStack tStack = aPlayer.inventory.getItemStack();
		    	if (tStack != null) {
		    		tStack = tStack.copy();
			    	tStack.stackSize = 1;
		    	}
		    	tSlot.putStack(tStack);
		    	return null;
		    } else if (aSlotIndex == 10) {
		    	((GT_MetaTileEntity_ElectricBufferSmall)mTileEntity.mMetaTileEntity).bOutput = !((GT_MetaTileEntity_ElectricBufferSmall)mTileEntity.mMetaTileEntity).bOutput;
			    if (aPlayer instanceof EntityPlayerMP)
			    	if (((GT_MetaTileEntity_ElectricBufferSmall)mTileEntity.mMetaTileEntity).bOutput)
			    		((EntityPlayerMP)aPlayer).playerNetServerHandler.sendPacketToPlayer(new Packet3Chat("Emit Energy to Outputside"));
			    	else
			    		((EntityPlayerMP)aPlayer).playerNetServerHandler.sendPacketToPlayer(new Packet3Chat("Don't emit Energy"));
		    	return null;
		    } else if (aSlotIndex == 11) {
		    	((GT_MetaTileEntity_ElectricBufferSmall)mTileEntity.mMetaTileEntity).bRedstoneIfFull = !((GT_MetaTileEntity_ElectricBufferSmall)mTileEntity.mMetaTileEntity).bRedstoneIfFull;
		    	if (aPlayer instanceof EntityPlayerMP)
				    if (((GT_MetaTileEntity_ElectricBufferSmall)mTileEntity.mMetaTileEntity).bRedstoneIfFull)
			    		((EntityPlayerMP)aPlayer).playerNetServerHandler.sendPacketToPlayer(new Packet3Chat("Emit Redstone if slot contains something"));
			    	else
			    		((EntityPlayerMP)aPlayer).playerNetServerHandler.sendPacketToPlayer(new Packet3Chat("Don't emit Redstone"));
		    	return null;
		    } else if (aSlotIndex == 12) {
		    	((GT_MetaTileEntity_ElectricBufferSmall)mTileEntity.mMetaTileEntity).bInvert = !((GT_MetaTileEntity_ElectricBufferSmall)mTileEntity.mMetaTileEntity).bInvert;
		    	if (aPlayer instanceof EntityPlayerMP)
				    if (((GT_MetaTileEntity_ElectricBufferSmall)mTileEntity.mMetaTileEntity).bInvert)
			    		((EntityPlayerMP)aPlayer).playerNetServerHandler.sendPacketToPlayer(new Packet3Chat("Invert Redstone"));
			    	else
			    		((EntityPlayerMP)aPlayer).playerNetServerHandler.sendPacketToPlayer(new Packet3Chat("Don't invert Redstone"));
		    	return null;
		    } else if (aSlotIndex == 13) {
		    	((GT_MetaTileEntity_ElectricSorter)mTileEntity.mMetaTileEntity).mTargetDirection = (((GT_MetaTileEntity_ElectricSorter)mTileEntity.mMetaTileEntity).mTargetDirection + 1) % 6;
		    }
    	}
	    
    	return super.slotClick(aSlotIndex, aMouseclick, aShifthold, aPlayer);
    }
    
    public int mTargetDirection;
    
    public void updateCraftingResults() {
        super.updateCraftingResults();
    	if (mTileEntity.worldObj.isRemote) return;
    	mTargetDirection  = ((GT_MetaTileEntity_ElectricSorter)mTileEntity.mMetaTileEntity).mTargetDirection;
    	
        Iterator var2 = this.crafters.iterator();
        while (var2.hasNext()) {
            ICrafting var1 = (ICrafting)var2.next();
            var1.sendProgressBarUpdate(this, 10, mTargetDirection);
        }
    }
    
    public void addCraftingToCrafters(ICrafting par1ICrafting) {
        super.addCraftingToCrafters(par1ICrafting);
    }
    
    @SideOnly(Side.CLIENT)
    public void updateProgressBar(int par1, int par2) {
    	super.updateProgressBar(par1, par2);
    	switch (par1) {
    	case 10: mTargetDirection = par2; break;
    	}
    }
    
    public int getSlotCount() {
    	return 1;
    }

    public int getAllSlotCount() {
    	return 14;
    }
    
    public int getShiftClickSlotCount() {
    	return 1;
    }
}
