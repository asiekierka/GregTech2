package gregtechmod.common.tileentities;

import gregtechmod.GT_Mod;
import gregtechmod.api.BaseMetaTileEntity;
import gregtechmod.api.GT_Recipe;
import gregtechmod.api.MetaTileEntity;
import gregtechmod.common.GT_ModHandler;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.ForgeDirection;
import net.minecraftforge.liquids.LiquidStack;

public class GT_MetaTileEntity_Grinder extends MetaTileEntity {

	public int mProgresstime = 0, mMaxProgresstime = 0, mEUt = 0, mUpdate = 5, mWaterAmount = 0;
	private ItemStack mOutputItem1, mOutputItem2, mOutputItem3, mOutputItem4;
	public boolean mMachine = false;
	
	public GT_MetaTileEntity_Grinder(int aID, String mName, String mNameRegional) {
		super(aID, mName, mNameRegional);
	}
	
	public GT_MetaTileEntity_Grinder() {
		
	}

	@Override public boolean isSimpleMachine()						{return false;}
	@Override public boolean isFacingValid(int aFacing)				{return aFacing > 1;}
	@Override public boolean isEnetInput() 							{return true;}
	@Override public boolean isInputFacing(short aSide)				{return true;}
    @Override public int maxEUInput()								{return 128;}
    @Override public int maxEUStore()								{return 10000;}
	@Override public int getInvSize()								{return 6;}
	@Override public void onRightclick(EntityPlayer aPlayer)		{aPlayer.openGui(GT_Mod.instance, 112, mBaseMetaTileEntity.worldObj, mBaseMetaTileEntity.xCoord, mBaseMetaTileEntity.yCoord, mBaseMetaTileEntity.zCoord);}
	@Override public boolean isAccessAllowed(EntityPlayer aPlayer)	{return true;}
	public int getProgresstime()									{return mProgresstime;}
	public int maxProgresstime()									{return mMaxProgresstime;}
    
	@Override
	public MetaTileEntity newMetaEntity(BaseMetaTileEntity aTileEntity) {
		return new GT_MetaTileEntity_Grinder();
	}
	
	@Override
	public void saveNBTData(NBTTagCompound aNBT) {
    	aNBT.setInteger("mEUt", mEUt);
    	aNBT.setInteger("mWaterAmount", mWaterAmount);
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
        if (mOutputItem3 != null) {
            NBTTagCompound tNBT = new NBTTagCompound();
        	mOutputItem3.writeToNBT(tNBT);
            aNBT.setTag("mOutputItem3", tNBT);
        }
        if (mOutputItem4 != null) {
            NBTTagCompound tNBT = new NBTTagCompound();
        	mOutputItem4.writeToNBT(tNBT);
            aNBT.setTag("mOutputItem4", tNBT);
        }
	}
	
	@Override
	public void loadNBTData(NBTTagCompound aNBT) {
    	mUpdate = 5;
    	mEUt = aNBT.getInteger("mEUt");
    	mWaterAmount = aNBT.getInteger("mWaterAmount");
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
    	NBTTagCompound tNBT3 = (NBTTagCompound)aNBT.getTag("mOutputItem3");
    	if (tNBT3 != null) {
    		mOutputItem3 = ItemStack.loadItemStackFromNBT(tNBT3);
    	}
    	NBTTagCompound tNBT4 = (NBTTagCompound)aNBT.getTag("mOutputItem4");
    	if (tNBT4 != null) {
    		mOutputItem4 = ItemStack.loadItemStackFromNBT(tNBT4);
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
    	for (int i = -1; i < 2; i++) for (int j = -1; j < 2; j++) for (int k = -1; k < 2; k++) {
    		if (i!=0||j!=0||k!=0) {
    			if (mBaseMetaTileEntity.worldObj.getBlockId(mBaseMetaTileEntity.xCoord-xDir+i, mBaseMetaTileEntity.yCoord-yDir+j, mBaseMetaTileEntity.zCoord-zDir+k) != GT_Mod.instance.mBlocks[0].blockID) return false;
            	if (mBaseMetaTileEntity.worldObj.getBlockMetadata(mBaseMetaTileEntity.xCoord-xDir+i, mBaseMetaTileEntity.yCoord-yDir+j, mBaseMetaTileEntity.zCoord-zDir+k) != (j==0?14:13)) return false;
    		} else {
    			if (mBaseMetaTileEntity.worldObj.getBlockId(mBaseMetaTileEntity.xCoord-xDir+i, mBaseMetaTileEntity.yCoord-yDir+j, mBaseMetaTileEntity.zCoord-zDir+k) != 8 && mBaseMetaTileEntity.worldObj.getBlockId(mBaseMetaTileEntity.xCoord-xDir+i, mBaseMetaTileEntity.yCoord-yDir+j, mBaseMetaTileEntity.zCoord-zDir+k) != 9) return false;
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
	    	}
    		mBaseMetaTileEntity.mActive = mMachine;
	    	if (mMaxProgresstime > 0) {
	    		if (mMachine && mBaseMetaTileEntity.decreaseStoredEnergy(mEUt, false)) {
	    			if (++mProgresstime>mMaxProgresstime) {
	    				addOutputProducts();
	    				mOutputItem1 = null;
	    				mOutputItem2 = null;
	    				mOutputItem3 = null;
	    				mOutputItem4 = null;
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
    	
    	if (mOutputItem3 != null)
	    	if (mInventory[4] == null)
	    		mInventory[4] = mOutputItem3.copy();
	    	else if (mInventory[4].isItemEqual(mOutputItem3))
	    		mInventory[4].stackSize = Math.min(mOutputItem3.getMaxStackSize(), mOutputItem3.stackSize + mInventory[4].stackSize);
    	
    	if (mOutputItem4 != null)
	    	if (mInventory[5] == null)
	    		mInventory[5] = mOutputItem4.copy();
	    	else if (mInventory[5].isItemEqual(mOutputItem4))
	    		mInventory[5].stackSize = Math.min(mOutputItem4.getMaxStackSize(), mOutputItem4.stackSize + mInventory[5].stackSize);
    }
    
    private boolean spaceForOutput(int aRecipe) {
    	if (mInventory[2] == null || GT_Recipe.sGrinderRecipes.get(aRecipe).mOutput1 == null || (mInventory[2].stackSize + GT_Recipe.sGrinderRecipes.get(aRecipe).mOutput1.stackSize <= mInventory[2].getMaxStackSize() && mInventory[2].isItemEqual(GT_Recipe.sGrinderRecipes.get(aRecipe).mOutput1)))
    	if (mInventory[3] == null || GT_Recipe.sGrinderRecipes.get(aRecipe).mOutput2 == null || (mInventory[3].stackSize + GT_Recipe.sGrinderRecipes.get(aRecipe).mOutput2.stackSize <= mInventory[3].getMaxStackSize() && mInventory[3].isItemEqual(GT_Recipe.sGrinderRecipes.get(aRecipe).mOutput2)))
    	if (mInventory[4] == null || GT_Recipe.sGrinderRecipes.get(aRecipe).mOutput3 == null || (mInventory[4].stackSize + GT_Recipe.sGrinderRecipes.get(aRecipe).mOutput3.stackSize <= mInventory[4].getMaxStackSize() && mInventory[4].isItemEqual(GT_Recipe.sGrinderRecipes.get(aRecipe).mOutput3)))
    	if (mInventory[5] == null || GT_Recipe.sGrinderRecipes.get(aRecipe).mOutput4 == null || (mInventory[5].stackSize + GT_Recipe.sGrinderRecipes.get(aRecipe).mOutput4.stackSize <= mInventory[5].getMaxStackSize() && mInventory[5].isItemEqual(GT_Recipe.sGrinderRecipes.get(aRecipe).mOutput4)))
    		return true;
    	return false;
    }
    
    private boolean checkRecipe() {
    	if (!mMachine) return false;
    	
    	int tRecipe = GT_Recipe.findEqualGrinderRecipeIndex(mInventory[0], mInventory[1]);
    	
    	if (tRecipe != -1) {
    		if (spaceForOutput(tRecipe)) {
    			if (GT_Recipe.sGrinderRecipes.get(tRecipe).isRecipeInputEqual(true, true, mInventory[0], mInventory[1])) {
		        	if (mInventory[0] != null) if (mInventory[0].stackSize == 0) mInventory[0] = null;
		        	if (mInventory[1] != null) if (mInventory[1].stackSize == 0) mInventory[1] = null;
		        	
		        	mMaxProgresstime = GT_Recipe.sGrinderRecipes.get(tRecipe).mDuration;
		        	mEUt = GT_Recipe.sGrinderRecipes.get(tRecipe).mEUt;
		        	
		        	if (GT_Recipe.sGrinderRecipes.get(tRecipe).mOutput1 == null) {
		        		mOutputItem1 = null;
		        	} else {
			        	mOutputItem1 = GT_Recipe.sGrinderRecipes.get(tRecipe).mOutput1.copy();
		        	}
		        	if (GT_Recipe.sGrinderRecipes.get(tRecipe).mOutput2 == null) {
		        		mOutputItem2 = null;
		        	} else {
			        	mOutputItem2 = GT_Recipe.sGrinderRecipes.get(tRecipe).mOutput2.copy();
		        	}
		        	if (GT_Recipe.sGrinderRecipes.get(tRecipe).mOutput3 == null) {
		        		mOutputItem3 = null;
		        	} else {
			        	mOutputItem3 = GT_Recipe.sGrinderRecipes.get(tRecipe).mOutput3.copy();
		        	}
		        	if (GT_Recipe.sGrinderRecipes.get(tRecipe).mOutput4 == null) {
		        		mOutputItem4 = null;
		        	} else {
			        	mOutputItem4 = GT_Recipe.sGrinderRecipes.get(tRecipe).mOutput4.copy();
		        	}
		        	return true;
    			}
    		}
    	} else {
    		ItemStack tWaterStack = (mWaterAmount>=1000?GT_ModHandler.getIC2Item("waterCell", mWaterAmount/1000):null);
        	tRecipe = GT_Recipe.findEqualGrinderRecipeIndex(mInventory[0], tWaterStack);
    		if (tRecipe != -1 && spaceForOutput(tRecipe)) {
    			if (GT_Recipe.sGrinderRecipes.get(tRecipe).isRecipeInputEqual(true, true, mInventory[0], tWaterStack)) {
		        	if (mInventory[0] != null) if (mInventory[0].stackSize == 0) mInventory[0] = null;
		        	mWaterAmount-=((mWaterAmount/1000)-tWaterStack.stackSize)*1000;
		        	
		        	mMaxProgresstime = GT_Recipe.sGrinderRecipes.get(tRecipe).mDuration;
		        	mEUt = GT_Recipe.sGrinderRecipes.get(tRecipe).mEUt;
		        	
		        	if (GT_Recipe.sGrinderRecipes.get(tRecipe).mOutput1 == null) {
		        		mOutputItem1 = null;
		        	} else {
			        	mOutputItem1 = GT_Recipe.sGrinderRecipes.get(tRecipe).mOutput1.copy();
		        	}
		        	if (GT_Recipe.sGrinderRecipes.get(tRecipe).mOutput2 == null) {
		        		mOutputItem2 = null;
		        	} else {
			        	mOutputItem2 = GT_Recipe.sGrinderRecipes.get(tRecipe).mOutput2.copy();
		        	}
		        	if (GT_Recipe.sGrinderRecipes.get(tRecipe).mOutput3 == null) {
		        		mOutputItem3 = null;
		        	} else {
			        	mOutputItem3 = GT_Recipe.sGrinderRecipes.get(tRecipe).mOutput3.copy();
		        	}
		        	if (GT_Recipe.sGrinderRecipes.get(tRecipe).mOutput4 == null) {
		        		mOutputItem4 = null;
		        	} else {
		        		if (!GT_Recipe.sGrinderRecipes.get(tRecipe).mOutput4.isItemEqual(GT_ModHandler.getIC2Item("cell", 1)))
		        			mOutputItem4 = GT_Recipe.sGrinderRecipes.get(tRecipe).mOutput4.copy();
		        	}
		        	return true;
    			}
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
		if (aSide != ForgeDirection.UP && aSide != ForgeDirection.DOWN) return 4;
		return 1;
	}
	
	
	@Override
	public int getTextureIndex(int aSide, int aFacing) {
		if (ForgeDirection.getOrientation(aSide) == ForgeDirection.getOrientation(aFacing))
			return 68+(mBaseMetaTileEntity.mActive?1:0);
		if (ForgeDirection.getOrientation(aSide).getOpposite() == ForgeDirection.getOrientation(aFacing))
			return 71;
		return 70;
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
	
	@Override
	public int fill(LiquidStack resource, boolean doFill) {
		if (resource == null || resource.itemID <= 0) return 0;
		LiquidStack tLiquid = new LiquidStack(Block.waterStill, mWaterAmount);
		if (!tLiquid.isLiquidEqual(resource)) return 0;
		
		int space = getCapacity() - tLiquid.amount;
		if (resource.amount <= space) {
			if (doFill && space > 0) {
				mWaterAmount += resource.amount;
			}
			return resource.amount;
		} else {
			if (doFill && space > 0) {
				mWaterAmount = getCapacity();
			}
			return space;
		}
	}
	
	@Override public LiquidStack getLiquid() {return new LiquidStack(Block.waterStill, mWaterAmount);}
	@Override public int getTankPressure() {return -100;}
	@Override public void setLiquid(LiquidStack liquid) {}
	@Override public void setCapacity(int capacity) {}
	@Override public int getCapacity() {return 10000;}
}
