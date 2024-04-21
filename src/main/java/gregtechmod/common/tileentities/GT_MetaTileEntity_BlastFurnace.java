package gregtechmod.common.tileentities;

import gregtechmod.GT_Mod;
import gregtechmod.api.BaseMetaTileEntity;
import gregtechmod.api.GT_Recipe;
import gregtechmod.api.MetaTileEntity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.ForgeDirection;

public class GT_MetaTileEntity_BlastFurnace extends MetaTileEntity {

	public int mProgresstime = 0, mMaxProgresstime = 0, mEUt = 0, mHeatCapacity = 0, mUpdate = 5;
	public ItemStack mOutputItem1, mOutputItem2;
	public boolean mMachine = false;
	
	public GT_MetaTileEntity_BlastFurnace(int aID, String mName, String mNameRegional) {
		super(aID, mName, mNameRegional);
	}
	
	public GT_MetaTileEntity_BlastFurnace() {
		
	}

	@Override public boolean isSimpleMachine()						{return false;}
	@Override public boolean isFacingValid(int aFacing)				{return aFacing > 1;}
	@Override public boolean isEnetInput() 							{return true;}
	@Override public boolean isInputFacing(short aSide)				{return true;}
    @Override public int maxEUInput()								{return 128;}
    @Override public int maxEUStore()								{return 10000;}
	@Override public int getInvSize()								{return 4;}
	@Override public void onRightclick(EntityPlayer aPlayer)		{aPlayer.openGui(GT_Mod.instance, 113, mBaseMetaTileEntity.worldObj, mBaseMetaTileEntity.xCoord, mBaseMetaTileEntity.yCoord, mBaseMetaTileEntity.zCoord);}
	@Override public boolean isAccessAllowed(EntityPlayer aPlayer)	{return true;}
	public int getProgresstime()									{return mProgresstime;}
	public int maxProgresstime()									{return mMaxProgresstime;}
    
	@Override
	public MetaTileEntity newMetaEntity(BaseMetaTileEntity aTileEntity) {
		return new GT_MetaTileEntity_BlastFurnace();
	}
	
	@Override
	public void saveNBTData(NBTTagCompound aNBT) {
    	aNBT.setInteger("mEUt", mEUt);
    	aNBT.setInteger("mProgresstime", mProgresstime);
    	aNBT.setInteger("mMaxProgresstime", mMaxProgresstime);
    	
        if (mOutputItem1 != null) {
            NBTTagCompound tNBT = new NBTTagCompound();
        	mOutputItem1.writeToNBT(tNBT);
            aNBT.setTag("mOutputItem1", tNBT);
        }
        if (mOutputItem2 != null) {
            NBTTagCompound tNBT = new NBTTagCompound();
        	mOutputItem2.writeToNBT(tNBT);
            aNBT.setTag("mOutputItem2", tNBT);
        }
	}
	
	@Override
	public void loadNBTData(NBTTagCompound aNBT) {
    	mUpdate = 5;
    	mEUt = aNBT.getInteger("mEUt");
    	mProgresstime = aNBT.getInteger("mProgresstime");
    	mMaxProgresstime = aNBT.getInteger("mMaxProgresstime");
    	
    	NBTTagCompound tNBT1 = (NBTTagCompound)aNBT.getTag("mOutputItem1");
    	if (tNBT1 != null) {
    		mOutputItem1 = ItemStack.loadItemStackFromNBT(tNBT1);
    	}
    	NBTTagCompound tNBT2 = (NBTTagCompound)aNBT.getTag("mOutputItem2");
    	if (tNBT2 != null) {
    		mOutputItem2 = ItemStack.loadItemStackFromNBT(tNBT2);
    	}
	}
	
    @Override
    public void receiveClientEvent(int aEventID, int aValue) {
    	super.receiveClientEvent(aEventID, aValue);
    	if (mBaseMetaTileEntity.worldObj.isRemote) {
	    	switch(aEventID) {
	    	case 10:
	    		mProgresstime = aValue;
	    		break;
	    	case 11:
	    		mMaxProgresstime = aValue;
	    		break;
	    	}
    	}
    }
    
    private boolean checkMachine() {
    	int xDir = ForgeDirection.getOrientation(mBaseMetaTileEntity.mFacing).offsetX*2, yDir = ForgeDirection.getOrientation(mBaseMetaTileEntity.mFacing).offsetY*2, zDir = ForgeDirection.getOrientation(mBaseMetaTileEntity.mFacing).offsetZ*2;
    	mHeatCapacity = 0;
    	for (int i = -1; i < 2; i++) for (int j = 0; j < 4; j++) for (int k = -1; k < 2; k++) {
    		if (i!=0||(j!=1&&j!=2)||k!=0) {
    			if (mBaseMetaTileEntity.worldObj.getBlockId(mBaseMetaTileEntity.xCoord-xDir+i, mBaseMetaTileEntity.yCoord-yDir+j, mBaseMetaTileEntity.zCoord-zDir+k) != GT_Mod.instance.mBlocks[0].blockID) return false;
            	int tMeta = mBaseMetaTileEntity.worldObj.getBlockMetadata(mBaseMetaTileEntity.xCoord-xDir+i, mBaseMetaTileEntity.yCoord-yDir+j, mBaseMetaTileEntity.zCoord-zDir+k);
    			if (tMeta == 13)
            		mHeatCapacity += 30;
            	else if (tMeta == 14)
            		mHeatCapacity += 50;
            	else if (tMeta == 15)
            		mHeatCapacity += 100;
            	else return false;
    		} else {
    			if (mBaseMetaTileEntity.worldObj.getBlockId(mBaseMetaTileEntity.xCoord-xDir+i, mBaseMetaTileEntity.yCoord-yDir+j, mBaseMetaTileEntity.zCoord-zDir+k) == 10 || mBaseMetaTileEntity.worldObj.getBlockId(mBaseMetaTileEntity.xCoord-xDir+i, mBaseMetaTileEntity.yCoord-yDir+j, mBaseMetaTileEntity.zCoord-zDir+k) == 11) mHeatCapacity += 250;
    		}
    	}
    	return true;
    }
    
    @Override
    public void onMachineBlockUpdate() {
    	mUpdate = 5;
    }
    
    @Override
    public void onPostTick() {
	    if (!mBaseMetaTileEntity.worldObj.isRemote) {
	    	if (mUpdate--==0) {
	        	mMachine = checkMachine();
	        	if (!mMachine) mHeatCapacity = 0;
	    	}
    		mBaseMetaTileEntity.mActive = mMachine;
	    	if (mMaxProgresstime > 0) {
	    		if (mMachine && mBaseMetaTileEntity.decreaseStoredEnergy(mEUt, false)) {
	    			if (++mProgresstime>mMaxProgresstime) {
	    				addOutputProducts();
	    				mOutputItem1 = null;
	    				mOutputItem2 = null;
	    				mProgresstime = 0;
	    				mMaxProgresstime = 0;
	    				mBaseMetaTileEntity.mDisplayErrorCode = 0;
	    			}
	    		} else {
    				if (GT_Mod.instance.mConstantEnergy) mProgresstime = 0;
    				mBaseMetaTileEntity.mDisplayErrorCode = 1;
	    		}
	    	} else {
	    		if (mBaseMetaTileEntity.getStored() > 100) checkRecipe();
	    	}
	    	
	    	if (mBaseMetaTileEntity.mTickTimer%20==0) {
	    		mBaseMetaTileEntity.worldObj.addBlockEvent(mBaseMetaTileEntity.xCoord, mBaseMetaTileEntity.yCoord, mBaseMetaTileEntity.zCoord, GT_Mod.instance.mBlocks[1].blockID, 10, mProgresstime);
	    		mBaseMetaTileEntity.worldObj.addBlockEvent(mBaseMetaTileEntity.xCoord, mBaseMetaTileEntity.yCoord, mBaseMetaTileEntity.zCoord, GT_Mod.instance.mBlocks[1].blockID, 11, mMaxProgresstime);
    		}
		}
    }
    
    private void addOutputProducts() {
    	if (mOutputItem1 != null)
	    	if (mInventory[2] == null)
	    		mInventory[2] = mOutputItem1.copy();
	    	else if (mInventory[2].isItemEqual(mOutputItem1))
	    		mInventory[2].stackSize = Math.min(mOutputItem1.getMaxStackSize(), mOutputItem1.stackSize + mInventory[2].stackSize);
    	
    	if (mOutputItem2 != null)
	    	if (mInventory[3] == null)
	    		mInventory[3] = mOutputItem2.copy();
	    	else if (mInventory[3].isItemEqual(mOutputItem2))
	    		mInventory[3].stackSize = Math.min(mOutputItem2.getMaxStackSize(), mOutputItem2.stackSize + mInventory[3].stackSize);
    }
    
    private boolean spaceForOutput(int aRecipe) {
    	if (mInventory[2] == null || GT_Recipe.sBlastRecipes.get(aRecipe).mOutput1 == null || (mInventory[2].stackSize + GT_Recipe.sBlastRecipes.get(aRecipe).mOutput1.stackSize <= mInventory[2].getMaxStackSize() && mInventory[2].isItemEqual(GT_Recipe.sBlastRecipes.get(aRecipe).mOutput1)))
    	if (mInventory[3] == null || GT_Recipe.sBlastRecipes.get(aRecipe).mOutput2 == null || (mInventory[3].stackSize + GT_Recipe.sBlastRecipes.get(aRecipe).mOutput2.stackSize <= mInventory[3].getMaxStackSize() && mInventory[3].isItemEqual(GT_Recipe.sBlastRecipes.get(aRecipe).mOutput2)))
    		return true;
    	return false;
    }
    
    private boolean checkRecipe() {
    	if (!mMachine) return false;
    	
    	int tRecipe = GT_Recipe.findEqualBlastRecipeIndex(mInventory[0], mInventory[1]);

    	if (tRecipe != -1) {
    		if (mHeatCapacity >= GT_Recipe.sBlastRecipes.get(tRecipe).mStartEU && spaceForOutput(tRecipe) && GT_Recipe.sBlastRecipes.get(tRecipe).isRecipeInputEqual(true, true, mInventory[0], mInventory[1])) {
	        	if (mInventory[0] != null) if (mInventory[0].stackSize == 0) mInventory[0] = null;
	        	if (mInventory[1] != null) if (mInventory[1].stackSize == 0) mInventory[1] = null;
	        	
	        	mMaxProgresstime = GT_Recipe.sBlastRecipes.get(tRecipe).mDuration;
	        	mEUt = GT_Recipe.sBlastRecipes.get(tRecipe).mEUt;
	        	
	        	if (GT_Recipe.sBlastRecipes.get(tRecipe).mOutput1 == null) {
	        		mOutputItem1 = null;
	        	} else {
		        	mOutputItem1 = GT_Recipe.sBlastRecipes.get(tRecipe).mOutput1.copy();
	        	}
	        	if (GT_Recipe.sBlastRecipes.get(tRecipe).mOutput2 == null) {
	        		mOutputItem2 = null;
	        	} else {
		        	mOutputItem2 = GT_Recipe.sBlastRecipes.get(tRecipe).mOutput2.copy();
	        	}
	        	return true;
    		}
    	}
    	
    	return false;
    }
    
	@Override
	public int getInvSideIndex(ForgeDirection aSide, int aFacing) {
		if (aSide == ForgeDirection.UP) return 0;
		if (aSide == ForgeDirection.DOWN) return 1;
		return 2;
	}
	
	@Override
	public int getInvSideLength(ForgeDirection aSide, int aFacing) {
		if (aSide != ForgeDirection.UP && aSide != ForgeDirection.DOWN) return 2;
		return 1;
	}
	
	
	@Override
	public int getTextureIndex(int aSide, int aFacing) {
		if (ForgeDirection.getOrientation(aSide) == ForgeDirection.getOrientation(aFacing))
			return 68+(mBaseMetaTileEntity.mActive?1:0);
		if (ForgeDirection.getOrientation(aSide).getOpposite() == ForgeDirection.getOrientation(aFacing))
			return 71;
		return 72;
	}
	
	@Override
	public String getMainInfo() {
		return "Progress:";
	}
	@Override
	public String getSecondaryInfo() {
		return (mProgresstime/20)+"secs";
	}
	@Override
	public String getTertiaryInfo() {
		return "/"+(mMaxProgresstime/20)+"secs";
	}
	@Override
	public boolean isGivingInformation() {
		return true;
	}
}
