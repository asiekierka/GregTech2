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

public class GT_Container_ElectricBufferLarge extends GT_ContainerMetaTile_Machine {

	public GT_Container_ElectricBufferLarge(InventoryPlayer aInventoryPlayer, BaseMetaTileEntity aTileEntity, int aID) {
		super(aInventoryPlayer, aTileEntity, aID);
	}

    public void addSlots(InventoryPlayer aInventoryPlayer) {
    	for (int y = 0; y < 3; y++) for (int x = 0; x < 9; x++) {
            addSlotToContainer(new Slot(mTileEntity, x+y*9, 8+x*18, 5+y*18));
        }
        addSlotToContainer(new GT_Slot_Holo(mTileEntity,  27,   8, 63, false, true, 1));
        addSlotToContainer(new GT_Slot_Holo(mTileEntity,  27,  26, 63, false, true, 1));
        addSlotToContainer(new GT_Slot_Holo(mTileEntity,  27,  44, 63, false, true, 1));
    }

    public ItemStack slotClick(int aSlotIndex, int aMouseclick, int aShifthold, EntityPlayer aPlayer) {
    	if (aSlotIndex < 27) return super.slotClick(aSlotIndex, aMouseclick, aShifthold, aPlayer);
	    
    	Slot tSlot = (Slot)inventorySlots.get(aSlotIndex);
	    if (tSlot != null) {
	    	if (mTileEntity.mMetaTileEntity == null) return null;
		    if (aSlotIndex == 27) {
		    	((GT_MetaTileEntity_ElectricBufferSmall)mTileEntity.mMetaTileEntity).bOutput = !((GT_MetaTileEntity_ElectricBufferSmall)mTileEntity.mMetaTileEntity).bOutput;
			    if (aPlayer instanceof EntityPlayerMP)
			    	if (((GT_MetaTileEntity_ElectricBufferSmall)mTileEntity.mMetaTileEntity).bOutput)
			    		((EntityPlayerMP)aPlayer).playerNetServerHandler.sendPacketToPlayer(new Packet3Chat("Emit Energy to Outputside"));
			    	else
			    		((EntityPlayerMP)aPlayer).playerNetServerHandler.sendPacketToPlayer(new Packet3Chat("Don't emit Energy"));
		    	return null;
		    } else if (aSlotIndex == 28) {
		    	((GT_MetaTileEntity_ElectricBufferSmall)mTileEntity.mMetaTileEntity).bRedstoneIfFull = !((GT_MetaTileEntity_ElectricBufferSmall)mTileEntity.mMetaTileEntity).bRedstoneIfFull;
		    	if (aPlayer instanceof EntityPlayerMP)
				    if (((GT_MetaTileEntity_ElectricBufferSmall)mTileEntity.mMetaTileEntity).bRedstoneIfFull)
			    		((EntityPlayerMP)aPlayer).playerNetServerHandler.sendPacketToPlayer(new Packet3Chat("Emit Redstone if no Slot is free"));
			    	else
			    		((EntityPlayerMP)aPlayer).playerNetServerHandler.sendPacketToPlayer(new Packet3Chat("Don't emit Redstone"));
		    	return null;
		    } else if (aSlotIndex == 29) {
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
    	return 27;
    }

    public int getAllSlotCount() {
    	return 30;
    }
    
    public int getShiftClickSlotCount() {
    	return 27;
    }
}
