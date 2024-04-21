package gregtechmod.common.tileentities;

import gregtechmod.GT_Mod;
import gregtechmod.api.IQuantumChest;
import gregtechmod.common.GT_LanguageManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.ForgeDirection;

public class GT_TileEntity_Quantumchest extends GT_TileEntityMetaID_Machine implements IQuantumChest {

	private int mItemCount = 0, mItemID = 0, mItemMeta = 0;
	
    public boolean isAccessible(EntityPlayer aPlayer)	{return true;}
    public int getInventorySlotCount() 					{return 4;}
    
	@Override public int getStartInventorySide(ForgeDirection aSide) {
		return 1;
	}
	
	@Override public int getSizeInventorySide(ForgeDirection aSide) {
		return getInventorySlotCount() - 1;
	}
    
	public ItemStack getStoredItem() {
		if (mItemID == 0) return null;
		return new ItemStack(mItemID, 1, mItemMeta);
	}

    @Override
    public void receiveClientEvent(int aEventID, int aValue) {
    	super.receiveClientEvent(aEventID, aValue);
    	if (worldObj.isRemote) {
	    	switch(aEventID) {
	    	case 10:
	    		mItemCount = aValue;
	    		break;
	    	case 11:
	    		mItemID = aValue;
	    		break;
	    	case 12:
	    		mItemMeta = aValue;
	    		break;
	    	}
    	}
    }
    
    public void storeAdditionalData(NBTTagCompound aNBT) {
    	aNBT.setInteger("mItemCount", mItemCount);
    	aNBT.setInteger("mItemID"	, mItemID);
    	aNBT.setInteger("mItemMeta"	, mItemMeta);
    }

    public void getAdditionalData(NBTTagCompound aNBT) {
    	mItemCount	= aNBT.getInteger("mItemCount");
    	mItemID		= aNBT.getInteger("mItemID");
    	mItemMeta	= aNBT.getInteger("mItemMeta");
    }
    
    public void onPostTickUpdate() {
	    if (!worldObj.isRemote) {
	    	if (mItemCount == 0 && mInventory[0] != null && mInventory[1] == null && mInventory[2] == null && mInventory[3] == null) {
	    		if (mInventory[0].getTagCompound() == null && mInventory[0].getMaxStackSize() > 1) {
	    			mItemID		= mInventory[0].itemID;
	    			mItemMeta	= mInventory[0].getItemDamage();
	    		}
	    	}
	    	
	    	if (mInventory[0] != null && mItemCount + 128 < Integer.MAX_VALUE) {
	    		if (mInventory[0].itemID == mItemID && mInventory[0].getItemDamage() == mItemMeta) {
	        		mItemCount += mInventory[0].stackSize;
	        		mInventory[0] = null;
	    		}
	    	}
	    	
	    	if (mItemCount == 0 && mInventory[1] == null) {
	    		mInventory[1] = mInventory[2];
	    		if (mInventory[1] != null) {
	    			if (mInventory[3] != null) mInventory[1].stackSize += mInventory[3].stackSize;
	    		} else {
	    			mInventory[1] = mInventory[3];
	    		}
	    		mInventory[2] = null;
	    		mInventory[3] = null;
	    	}
	    	
	    	if (mItemID != 0 && (mInventory[2] == null || (mInventory[2].itemID == mItemID && mInventory[2].getItemDamage() == mItemMeta)))
				if (mItemCount > 0 && mInventory[2] == null) {
		    		mInventory[2] = new ItemStack(mItemID, 1, mItemMeta);
		    		mItemCount--;
		    	} else {
			    	if (mItemCount + 128 < Integer.MAX_VALUE) {
			    		if (mInventory[2] != null && mInventory[2].stackSize > 1) {
			    			mItemCount += mInventory[2].stackSize-1;
			    			mInventory[2].stackSize = 1;
			    		}
			    	}
		    	}
				
			if (mItemID != 0 && (mInventory[3] == null || (mInventory[3].itemID == mItemID && mInventory[3].getItemDamage() == mItemMeta)))
				if (mItemCount > 0 && mInventory[3] == null) {
		    		mInventory[3] = new ItemStack(mItemID, 1, mItemMeta);
		    		mItemCount--;
				} else {
			    	if (mItemCount + 128 < Integer.MAX_VALUE) {
			    		if (mInventory[3] != null && mInventory[3].stackSize > 1) {
			    			mItemCount += mInventory[3].stackSize-1;
			    			mInventory[3].stackSize = 1;
			    		}
			    	}
				}
			
			if (mItemCount > 0 && mInventory[2] != null && mInventory[3] != null) {
				if (mInventory[1] == null) {
	    			mItemCount--;
	    			mInventory[1] = new ItemStack(mItemID, 1, mItemMeta);
	    		}
	    		if (mInventory[1].itemID == mItemID && mInventory[1].getItemDamage() == mItemMeta) {
	    	    	while (mInventory[1].stackSize < mInventory[1].getMaxStackSize() && mItemCount > 0) {
	    	    		mItemCount--;
	    	    		mInventory[1].stackSize++;
	    			}
	    		}
			}

    		if (mTickTimer%20==0) {
    			worldObj.addBlockEvent(xCoord, yCoord, zCoord, GT_Mod.instance.mBlocks[1].blockID, 10, mItemCount);
    			worldObj.addBlockEvent(xCoord, yCoord, zCoord, GT_Mod.instance.mBlocks[1].blockID, 11, mItemID);
    			worldObj.addBlockEvent(xCoord, yCoord, zCoord, GT_Mod.instance.mBlocks[1].blockID, 12, mItemMeta);
    		}
		}
    }
    
    public int getContentCount() {
    	return mItemCount;
    }

    @Override public String getInvName() {return GT_LanguageManager.mNameList1[3];}
    
    @Override
    public int getTexture(int aSide, int aMeta) {
    	if (aSide == 0)
    		return 38;
    	else if (aSide == 1)
    		return 37;
    	else
    		return 40;
    }
    
	@Override
	public String getMainInfo() {
		if (getStoredItem() == null) return "";
		return getStoredItem().getDisplayName();
	}
	@Override
	public String getSecondaryInfo() {
		if (getStoredItem() == null) return "";
		return ""+(mItemCount>0?mItemCount+66:0);
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
	public boolean isQuantumChest() {
		return true;
	}
	@Override
	public ItemStack[] getStoredItemData() {
		return new ItemStack[] {new ItemStack(mItemID, mItemCount, mItemMeta)};
	}
}
