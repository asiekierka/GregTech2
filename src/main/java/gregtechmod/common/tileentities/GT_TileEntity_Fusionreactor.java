package gregtechmod.common.tileentities;

import gregtechmod.GT_Mod;
import gregtechmod.api.GT_Recipe;
import gregtechmod.common.GT_LanguageManager;
import gregtechmod.common.GT_ModHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.ForgeDirection;

public class GT_TileEntity_Fusionreactor extends GT_TileEntityMetaID_Machine {
	
	public static int maxEUStore = 100000000;
	
	private boolean mCoils = true;
	private int mFusiontime = 0, mMaxFusiontime = 0, mFusionEnergy = 0, mCellOutput = 0;
	private ItemStack mOutputItem;

    public boolean isAccessible(EntityPlayer aPlayer)	{return true;}
    public boolean isEnetOutput()      					{return true;}
    public boolean isEnetInput()       					{return true;}
    public boolean isOutputFacing(short aDirection) 	{return aDirection == 4 || aDirection == 5;}
    public boolean isInputFacing(short aDirection)  	{return aDirection == 0 || aDirection == 1;}
    public boolean hasCoils()							{return mCoils;}
    public int getFusiontime()							{return mFusiontime;}
    public int maxFusiontime()							{return mMaxFusiontime;}
    public int maxEUStore()            					{if (mCoils) return maxEUStore; else return 0;}
    public int maxEUInput()            					{return 8192;}
    public int maxEUOutput()           					{return worldObj.isBlockIndirectlyGettingPowered(xCoord, yCoord, zCoord)?0:1000000;}
    public int getInventorySlotCount() 					{return 3;}
    
    public void storeAdditionalData(NBTTagCompound aNBT) {
    	aNBT.setInteger("mFusiontime", mFusiontime);
    	aNBT.setInteger("mMaxFusiontime", mMaxFusiontime);
    	aNBT.setInteger("mFusionEnergy", mFusionEnergy);
    	aNBT.setInteger("mCellOutput", mCellOutput);
    	
        if (mOutputItem != null) {
            NBTTagCompound tNBT = new NBTTagCompound();
        	mOutputItem.writeToNBT(tNBT);
            aNBT.setTag("mOutputItem", tNBT);
        }
    }

    public void getAdditionalData(NBTTagCompound aNBT) {
    	mFusiontime = aNBT.getInteger("mFusiontime");
    	mMaxFusiontime = aNBT.getInteger("mMaxFusiontime");
    	mFusionEnergy = aNBT.getInteger("mFusionEnergy");
    	mCellOutput = aNBT.getInteger("mCellOutput");
    	
    	NBTTagCompound tNBT = (NBTTagCompound)aNBT.getTag("mOutputItem");
    	if (tNBT != null) {
    		mOutputItem = ItemStack.loadItemStackFromNBT(tNBT);
    	}
    }

    public void onFirstTickUpdate() {
    	checkCoils();
    }
    
    public void onPostTickUpdate() {
	    if (!worldObj.isRemote) {
	    	if (mActive) {
	    		if (++mFusiontime>mMaxFusiontime) {
	    			mFusiontime = 0;
	    			addOutputProduct(mOutputItem);
	    			if (!checkFuel()) {
	    				mActive = false;
	    			}
	    		}
	    		if (mFusionEnergy < 0) {
	    			if (!decreaseStoredEnergy(-mFusionEnergy, true)) {
	    				mActive = false;
		    			mFusiontime = 0;
	    			}
	    		} else {
	    			increaseStoredEnergy(mFusionEnergy);
	    		}
	    	} else {
	    		if (checkFuel()) {
	    			mActive = true;
	    		}
	    	}
	    	
	    	if (mInventory[2] == null && mCellOutput > 0) {
	    		mCellOutput--;
    			addOutputProduct(GT_ModHandler.getIC2Item("cell", 1));
	    	}
	    	
	    	if (mTickTimer%1200 == 0) checkCoils();
		}
    }
    
    private void addOutputProduct(ItemStack aOutputItem) {
    	if (aOutputItem != null)
	    	if (mInventory[2] == null)
	    		mInventory[2] = aOutputItem.copy();
	    	else if (mInventory[2].isItemEqual(aOutputItem))
	    		mInventory[2].stackSize = Math.min(64, aOutputItem.stackSize + mInventory[2].stackSize);
    }
    
    private boolean checkCoils() {
    	if (isCoil(xCoord+3, yCoord, zCoord+1))
    	if (isCoil(xCoord+3, yCoord, zCoord  ))
        if (isCoil(xCoord+3, yCoord, zCoord-1))
        if (isCoil(xCoord-3, yCoord, zCoord+1))
        if (isCoil(xCoord-3, yCoord, zCoord  ))
        if (isCoil(xCoord-3, yCoord, zCoord-1))
        if (isCoil(xCoord+2, yCoord, zCoord+2))
        if (isCoil(xCoord+2, yCoord, zCoord+1))
        if (isCoil(xCoord+2, yCoord, zCoord-1))
        if (isCoil(xCoord+2, yCoord, zCoord-2))
        if (isCoil(xCoord-2, yCoord, zCoord+2))
        if (isCoil(xCoord-2, yCoord, zCoord+1))
        if (isCoil(xCoord-2, yCoord, zCoord-1))
        if (isCoil(xCoord-2, yCoord, zCoord-2))
        if (isCoil(xCoord+1, yCoord, zCoord+3))
        if (isCoil(xCoord+1, yCoord, zCoord+2))
        if (isCoil(xCoord+1, yCoord, zCoord-2))
        if (isCoil(xCoord+1, yCoord, zCoord-3))
        if (isCoil(xCoord-1, yCoord, zCoord+3))
        if (isCoil(xCoord-1, yCoord, zCoord+2))
        if (isCoil(xCoord-1, yCoord, zCoord-2))
        if (isCoil(xCoord-1, yCoord, zCoord-3))
        if (isCoil(xCoord  , yCoord, zCoord+3))
        if (isCoil(xCoord  , yCoord, zCoord-3))
        	return mCoils=true;
    	return mCoils=false;
    }
    
    private boolean isCoil(int aX, int aY, int aZ) {
    	if (worldObj.getBlockId(aX, aY, aZ) == GT_Mod.instance.mBlocks[0].blockID)
        	if (worldObj.getBlockMetadata(aX, aY, aZ) == 1)
        		return true;
    	return false;
    }
    
    private boolean checkFuel() {
    	int tRecipe = GT_Recipe.findEqualFusionRecipeIndex(mInventory[0], mInventory[1]);
    	
    	if (tRecipe != -1) {
    		if (checkCoils() && (getStored() >= GT_Recipe.sFusionRecipes.get(tRecipe).mStartEU || mActive)) {
	    		if (GT_Recipe.sFusionRecipes.get(tRecipe).isRecipeInputEqual(true, true, mInventory[0], mInventory[1])) {
	        		if (!mActive) decreaseStoredEnergy(GT_Recipe.sFusionRecipes.get(tRecipe).mStartEU, true);

	        		mCellOutput += GT_Mod.getCapsuleCellContainerCount(GT_Recipe.sFusionRecipes.get(tRecipe).mInput1);
	        		mCellOutput += GT_Mod.getCapsuleCellContainerCount(GT_Recipe.sFusionRecipes.get(tRecipe).mInput2);
	        		mCellOutput -= GT_Mod.getCapsuleCellContainerCount(GT_Recipe.sFusionRecipes.get(tRecipe).mOutput1);
	        		
	        		if (mInventory[0] != null) if (mInventory[0].stackSize == 0) mInventory[0] = null;
	        		if (mInventory[1] != null) if (mInventory[1].stackSize == 0) mInventory[1] = null;
	        		
	        		mMaxFusiontime = GT_Recipe.sFusionRecipes.get(tRecipe).mDuration;
	        		mFusionEnergy = GT_Recipe.sFusionRecipes.get(tRecipe).mEUt;
	        		if (GT_Recipe.sFusionRecipes.get(tRecipe).mOutput1 == null) {
	        			mOutputItem = null;
	        		} else {
		        		mOutputItem = GT_Recipe.sFusionRecipes.get(tRecipe).mOutput1.copy();
	        		}
	        		return true;
	    		}
    		}
    	}
    	
    	return false;
    }
    
	@Override public int getStartInventorySide(ForgeDirection aSide) {
		if (aSide == ForgeDirection.UP) return 0;
		if (aSide == ForgeDirection.DOWN) return 1;
		return 2;
	}
	
	@Override public int getSizeInventorySide(ForgeDirection aSide) {
		return 1;
	}
	
    @Override public String getInvName() {return GT_LanguageManager.mNameList1[1];}
    
    @Override
    public int getTexture(int aSide, int aMeta) {
    	if (aSide == 0)
    		return  3;
    	else if (aSide == 1)
    		return 48 + ((int)mTickTimer%16);
    	else if (aSide == 2||aSide == 3)
    		return 103;
    	else
    		return mActive?20:19;
    }
}
