package gregtechmod.common.containers;

import gregtechmod.api.BaseMetaTileEntity;
import gregtechmod.common.GT_Slot_Holo;
import gregtechmod.common.tileentities.GT_MetaTileEntity_ElectricBufferSmall;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.network.packet.Packet3Chat;

public class GT_Container_Scrapboxinator extends GT_ContainerMetaTile_Machine {

	public GT_Container_Scrapboxinator(InventoryPlayer aInventoryPlayer, BaseMetaTileEntity aTileEntity, int aID) {
		super(aInventoryPlayer, aTileEntity, aID);
	}

    public void addSlots(InventoryPlayer aInventoryPlayer) {
        addSlotToContainer(new Slot(mTileEntity,  0,  80,  23));
        addSlotToContainer(new Slot(mTileEntity,  1,  80,  41));
        addSlotToContainer(new GT_Slot_Holo(mTileEntity,  2,   8, 63, false, true, 1));
        addSlotToContainer(new GT_Slot_Holo(mTileEntity,  2,  26, 63, false, true, 1));
        addSlotToContainer(new GT_Slot_Holo(mTileEntity,  2,  44, 63, false, true, 1));
    }

    public ItemStack slotClick(int aSlotIndex, int aMouseclick, int aShifthold, EntityPlayer aPlayer) {
    	if (aSlotIndex < 2) return super.slotClick(aSlotIndex, aMouseclick, aShifthold, aPlayer);
	    
    	Slot tSlot = (Slot)inventorySlots.get(aSlotIndex);
	    if (tSlot != null) {
	    	if (mTileEntity.mMetaTileEntity == null) return null;
		    if (aSlotIndex == 2) {
		    	((GT_MetaTileEntity_ElectricBufferSmall)mTileEntity.mMetaTileEntity).bOutput = !((GT_MetaTileEntity_ElectricBufferSmall)mTileEntity.mMetaTileEntity).bOutput;
			    if (aPlayer instanceof EntityPlayerMP)
			    	if (((GT_MetaTileEntity_ElectricBufferSmall)mTileEntity.mMetaTileEntity).bOutput)
			    		((EntityPlayerMP)aPlayer).playerNetServerHandler.sendPacketToPlayer(new Packet3Chat("Emit Energy to Outputside"));
			    	else
			    		((EntityPlayerMP)aPlayer).playerNetServerHandler.sendPacketToPlayer(new Packet3Chat("Don't emit Energy"));
		    	return null;
		    } else if (aSlotIndex == 3) {
		    	((GT_MetaTileEntity_ElectricBufferSmall)mTileEntity.mMetaTileEntity).bRedstoneIfFull = !((GT_MetaTileEntity_ElectricBufferSmall)mTileEntity.mMetaTileEntity).bRedstoneIfFull;
		    	if (aPlayer instanceof EntityPlayerMP)
				    if (((GT_MetaTileEntity_ElectricBufferSmall)mTileEntity.mMetaTileEntity).bRedstoneIfFull)
			    		((EntityPlayerMP)aPlayer).playerNetServerHandler.sendPacketToPlayer(new Packet3Chat("Emit Redstone if slot contains something"));
			    	else
			    		((EntityPlayerMP)aPlayer).playerNetServerHandler.sendPacketToPlayer(new Packet3Chat("Don't emit Redstone"));
		    	return null;
		    } else if (aSlotIndex == 4) {
		    	((GT_MetaTileEntity_ElectricBufferSmall)mTileEntity.mMetaTileEntity).bInvert = !((GT_MetaTileEntity_ElectricBufferSmall)mTileEntity.mMetaTileEntity).bInvert;
		    	if (aPlayer instanceof EntityPlayerMP)
				    if (((GT_MetaTileEntity_ElectricBufferSmall)mTileEntity.mMetaTileEntity).bInvert)
			    		((EntityPlayerMP)aPlayer).playerNetServerHandler.sendPacketToPlayer(new Packet3Chat("Invert Redstone"));
			    	else
			    		((EntityPlayerMP)aPlayer).playerNetServerHandler.sendPacketToPlayer(new Packet3Chat("Don't invert Redstone"));
		    	return null;
		    }
    	}
	    
    	return super.slotClick(aSlotIndex, aMouseclick, aShifthold, aPlayer);
    }
    
    public int getSlotCount() {
    	return 2;
    }

    public int getAllSlotCount() {
    	return 5;
    }
    
    public int getShiftClickSlotCount() {
    	return 1;
    }

    public int getSlotStartIndex() {
    	return 1;
    }
}
