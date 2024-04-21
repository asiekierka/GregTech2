package gregtechmod.common.tileentities;

import gregtechmod.GT_Mod;
import gregtechmod.common.GT_LanguageManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.ForgeDirection;

public class GT_TileEntity_Sonictron extends GT_TileEntityMetaID_Machine {
	
	public int mCurrentIndex = -1;
	
    public int getInventorySlotCount() 					{return 64;}
    public boolean isValidSlot(int aIndex)				{return false;}
    
	@Override
	public float getWrenchDropRate() {
		return 1.0F;
	}
	
    public void storeAdditionalData(NBTTagCompound aNBT) {
    	aNBT.setInteger("mCurrentIndex", mCurrentIndex);
    }

    public void getAdditionalData(NBTTagCompound aNBT) {
    	mCurrentIndex = aNBT.getInteger("mCurrentIndex");
    }
    
    public void onPostTickUpdate() {
    	if (mCurrentIndex<0 && !worldObj.isRemote && worldObj.isBlockIndirectlyGettingPowered(xCoord, yCoord, zCoord)) {
    		worldObj.addBlockEvent(xCoord, yCoord, zCoord, GT_Mod.instance.mBlocks[1].blockID, 10, 0);
    		mCurrentIndex=0;
    	}
    	
    	mRedstone = (mCurrentIndex == 63);
    	
		if (mTickTimer%2==0&&mCurrentIndex>-1) {
			GT_Mod.gregtechproxy.doSound(mInventory[mCurrentIndex], worldObj, xCoord+0.5, yCoord+0.5, zCoord+0.5);
			if (++mCurrentIndex>63) mCurrentIndex=-1;
		}
    }
    
    public boolean isAccessible(EntityPlayer aPlayer) {
    	ItemStack tStack = aPlayer.getCurrentEquippedItem();
    	if (tStack == null) return true;
    	if (tStack.isItemEqual(GT_Mod.getGregTechItem(32, 1, 0))) return false;
    	if (tStack.isItemEqual(GT_Mod.getGregTechItem(43, 1, 0))) return false;
    	return true;
    }
    
    public void receiveClientEvent(int aEventID, int aValue) {
    	super.receiveClientEvent(aEventID, aValue);
    	if (worldObj.isRemote) {
	    	switch(aEventID) {
	    	case 10:
	    		mCurrentIndex = aValue;
	    		break;
	    	}
    	}
    }
    
	@Override public int getStartInventorySide(ForgeDirection aSide) {return 0;}
	@Override public int getSizeInventorySide (ForgeDirection aSide) {return 0;}
    @Override public String getInvName() {return GT_LanguageManager.mNameList1[6];}
    
    @Override
    public int getTexture(int aSide, int aMeta) {
    	return 39;
    }
}
