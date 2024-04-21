package gregtechmod.common.tileentities;

import gregtechmod.common.GT_LanguageManager;
import gregtechmod.common.GT_ModHandler;
import ic2.api.Ic2Recipes;

import java.util.Iterator;
import java.util.Map.Entry;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.ForgeDirection;

public class GT_TileEntity_Matterfabricator extends GT_TileEntityMetaID_Machine {
	
	public static int sStore = 10000000, sMatterFabricationRate = 166666;
	
	private int mProgresstime = 0, mEULast = 0;

    public boolean isAccessible(EntityPlayer aPlayer)	{return true;}
    public boolean isEnetInput()       					{return true;}
    public boolean isInputFacing(short aDirection)  	{return true;}
    public int getProgresstime()						{return mProgresstime;}
    public int maxProgresstime()						{return sMatterFabricationRate;}
    public int maxEUStore()            					{return sStore;}
    public int maxEUInput()            					{return 8192;}
    public int getInventorySlotCount() 					{return 9;}
    
    public void storeAdditionalData(NBTTagCompound aNBT) {
    	aNBT.setInteger("mProgresstime", mProgresstime);
    }

    public void getAdditionalData(NBTTagCompound aNBT) {
    	mProgresstime = aNBT.getInteger("mProgresstime");
    }
    
    public void onPostTickUpdate() {
	    if (!worldObj.isRemote) {
	    	mActive = (mEULast != getEnergyVar());
	    	mEULast = getEnergyVar();
	    	if (mProgresstime>maxProgresstime()) {
	    		if (spaceForOutput()) {
		    		mProgresstime -= maxProgresstime();
		    		addOutputProducts();
	    		}
	    	} else if (getStored() > 0 && !worldObj.isBlockIndirectlyGettingPowered(xCoord, yCoord, zCoord)) {
		    	Iterator<Entry<ItemStack, Integer>> tIterator = Ic2Recipes.getMatterAmplifiers().iterator();
		    	while (mProgresstime <= maxProgresstime() && tIterator.hasNext()) {
		    		Entry<ItemStack, Integer> tEntry = tIterator.next();
		    		ItemStack tStack = tEntry.getKey();
		    		int tValue = (int)(((long)tEntry.getValue()*(long)maxProgresstime())/(long)166666);
			    	if (tValue > 0) {
			    		if (tValue > sStore) sStore = Math.max(tValue, 10000000);
			    		for (int i = 1; mProgresstime <= maxProgresstime() &&  i < getInventorySlotCount(); i++) {
				    		if (mInventory[i] != null) {
				    			if (mInventory[i].isItemEqual(tStack)) {
						    		while (mProgresstime <= maxProgresstime() && decreaseStoredEnergy(tValue, false)) {
					    				mProgresstime += tValue;
					    				mInventory[i].stackSize--;
							    		if (mInventory[i].stackSize <= 0) {
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
		}
    }
    
    private void addOutputProducts() {
	    if (mInventory[0] == null)
	    	mInventory[0] = GT_ModHandler.getIC2Item("matter", 1);
	    else if (mInventory[0].isItemEqual(GT_ModHandler.getIC2Item("matter", 1)))
	    	mInventory[0].stackSize = Math.min(64, 1 + mInventory[0].stackSize);
    }
    
    private boolean spaceForOutput() {
    	return (mInventory[0] == null || (mInventory[0].isItemEqual(GT_ModHandler.getIC2Item("matter", 1)) && mInventory[0].stackSize < 64));
    }
    
	@Override public int getStartInventorySide(ForgeDirection aSide) {
		if (aSide == ForgeDirection.UP || aSide == ForgeDirection.DOWN) return 1;
		return 0;
	}
	
	@Override public int getSizeInventorySide(ForgeDirection aSide) {
		if (aSide == ForgeDirection.UP || aSide == ForgeDirection.DOWN) return 8;
		return 1;
	}
	
    @Override public String getInvName() {return GT_LanguageManager.mNameList1[14];}
    
	@Override
	public String getMainInfo() {
		return "Progress:";
	}
	@Override
	public String getSecondaryInfo() {
		return Math.max(0, ((long)getProgresstime()) / Math.max(1, ((long)maxProgresstime())/100)) + "%";
	}
	@Override
	public String getTertiaryInfo() {
		return "Energy: " + getStored();
	}
	@Override
	public boolean isGivingInformation() {
		return true;
	}
    
    @Override
    public int getTexture(int aSide, int aMeta) {
    	return mActive?31:30;
    }
}
