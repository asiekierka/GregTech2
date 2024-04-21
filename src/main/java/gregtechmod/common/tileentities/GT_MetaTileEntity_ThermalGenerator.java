package gregtechmod.common.tileentities;

import gregtechmod.GT_Mod;
import gregtechmod.api.BaseMetaTileEntity;
import gregtechmod.api.GT_Recipe;
import gregtechmod.api.MetaTileEntity;
import gregtechmod.common.GT_Utility;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.ForgeDirection;
import net.minecraftforge.liquids.LiquidContainerRegistry;
import net.minecraftforge.liquids.LiquidEvent;
import net.minecraftforge.liquids.LiquidStack;

public class GT_MetaTileEntity_ThermalGenerator extends MetaTileEntity {
	
	public LiquidStack mLiquid = new LiquidStack(0, 0, 0);
	
	public GT_MetaTileEntity_ThermalGenerator(int aID, String mName, String mNameRegional) {
		super(aID, mName, mNameRegional);
	}
	
	public GT_MetaTileEntity_ThermalGenerator() {
		
	}
	
	@Override public boolean isSimpleMachine()						{return false;}
	@Override public boolean isValidSlot(int aIndex)				{return aIndex < 2;}
	@Override public boolean isFacingValid(int aFacing)				{return false;}
	@Override public boolean isEnetOutput() 						{return true;}
	@Override public boolean isOutputFacing(short aSide)			{return true;}
	@Override public int maxEUOutput()								{return 24;}
	@Override public int getInvSize()								{return 3;}
	@Override public int maxEUStore()								{return 1000000;}
	@Override public void onRightclick(EntityPlayer aPlayer)		{aPlayer.openGui(GT_Mod.instance, 119, mBaseMetaTileEntity.worldObj, mBaseMetaTileEntity.xCoord, mBaseMetaTileEntity.yCoord, mBaseMetaTileEntity.zCoord);}
	@Override public boolean isAccessAllowed(EntityPlayer aPlayer)	{return true;}
    
	@Override
	public MetaTileEntity newMetaEntity(BaseMetaTileEntity aTileEntity) {
		return new GT_MetaTileEntity_ThermalGenerator();
	}
	
	@Override
	public void saveNBTData(NBTTagCompound aNBT) {
		if (mLiquid != null) {
			aNBT.setInteger("mItemCount", mLiquid.amount);
    		aNBT.setInteger("mItemID"	, mLiquid.itemID);
    		aNBT.setInteger("mItemMeta"	, mLiquid.itemMeta);
		}
	}
	
	@Override
	public void loadNBTData(NBTTagCompound aNBT) {
    	mLiquid = new LiquidStack(aNBT.getInteger("mItemID"), aNBT.getInteger("mItemCount"), aNBT.getInteger("mItemMeta"));
	}
	
    @Override
    public void onPostTick() {
    	if (mLiquid == null || (mLiquid.itemID != 0 && mLiquid.amount == 0)) mLiquid = new LiquidStack(0, 0, 0);
    	
    	if (!mBaseMetaTileEntity.worldObj.isRemote && mBaseMetaTileEntity.mTickTimer%10==0) {
    		
    		if (mLiquid.itemID <= 0 || mLiquid.amount <= 0) {
    			mInventory[2] = null;
    		} else {
    			mInventory[2] = mLiquid.asItemStack();
    			if (mBaseMetaTileEntity.getStored() <= 100) {
    				while (mBaseMetaTileEntity.getStored() < maxEUOutput()*10 && mLiquid.amount > 0) {
    					if (mBaseMetaTileEntity.increaseStoredEnergy(getFuelValue(mLiquid))) mLiquid.amount--;
    				}
    			}
    		}
    		if (mInventory[0] != null && mBaseMetaTileEntity.getStored() <= 100) {
    			for (int i = 0; i < GT_Recipe.sHotFuels.size(); i++) {
    				if (mInventory[0].isItemEqual(GT_Recipe.sHotFuels.get(i).mInput1)) {
    					if (LiquidContainerRegistry.getLiquidForFilledItem(mInventory[0]) == null) {
    						if (GT_Recipe.sHotFuels.get(i).mOutput1 != null) {
    							if (mInventory[1] == null) {
    								if (mBaseMetaTileEntity.increaseStoredEnergy(GT_Recipe.sHotFuels.get(i).mStartEU*1000)) {
    									mBaseMetaTileEntity.decrStackSize(0, 1);
    									mInventory[1] = GT_Recipe.sHotFuels.get(i).mOutput1.copy();
    								}
    							} else if (mInventory[1].isItemEqual(GT_Recipe.sHotFuels.get(i).mOutput1) && mInventory[1].stackSize + GT_Recipe.sHotFuels.get(i).mOutput1.stackSize <= mInventory[1].getMaxStackSize()) {
    								if (mBaseMetaTileEntity.increaseStoredEnergy(GT_Recipe.sHotFuels.get(i).mStartEU*1000)) {
        								mBaseMetaTileEntity.decrStackSize(0, 1);
        	    						mInventory[1].stackSize += GT_Recipe.sHotFuels.get(i).mOutput1.stackSize;
    								}
    							}
    						} else {
    							if (mBaseMetaTileEntity.increaseStoredEnergy(GT_Recipe.sHotFuels.get(i).mStartEU*1000)) {
    								mBaseMetaTileEntity.decrStackSize(0, 1);
    							}
    						}
    					}
    					break;
    				}
    			}
    			if (mInventory[0] != null && mLiquid.itemID != 0 && LiquidContainerRegistry.isFilledContainer(mInventory[0]) && LiquidContainerRegistry.containsLiquid(mInventory[0], mLiquid) && isValidFuel(LiquidContainerRegistry.getLiquidForFilledItem(mInventory[0])) && LiquidContainerRegistry.getLiquidForFilledItem(mInventory[0]).amount + mLiquid.amount <= getCapacity()) {
    				boolean temp = true;
    				ItemStack tContainer;
    				if (null != (tContainer = GT_Utility.getContainerForFilledItem(mInventory[0]))) {
    					if (mInventory[1] == null) {
    						mInventory[1] = tContainer.copy();
    					} else if (mInventory[1].isItemEqual(tContainer) && mInventory[1].stackSize + tContainer.stackSize <= tContainer.getMaxStackSize()) {
    						mInventory[1].stackSize+=tContainer.stackSize;
    					} else {
    						temp = false;
    					}
    				}
    				if (temp) {
	    				mLiquid.amount+=LiquidContainerRegistry.getLiquidForFilledItem(mInventory[0]).amount;
	    				mBaseMetaTileEntity.decrStackSize(0, 1);
    				}
    			} else if (mInventory[0] != null && mLiquid.itemID == 0 && LiquidContainerRegistry.isFilledContainer(mInventory[0]) && isValidFuel(LiquidContainerRegistry.getLiquidForFilledItem(mInventory[0])) && LiquidContainerRegistry.getLiquidForFilledItem(mInventory[0]).amount <= getCapacity()) {
    				boolean temp = true;
    				ItemStack tContainer;
    				if (null != (tContainer = GT_Utility.getContainerForFilledItem(mInventory[0]))) {
    					if (mInventory[1] == null) {
    						mInventory[1] = tContainer.copy();
    					} else if (mInventory[1].isItemEqual(tContainer) && mInventory[1].stackSize + tContainer.stackSize <= tContainer.getMaxStackSize()) {
    						mInventory[1].stackSize+=tContainer.stackSize;
    					} else {
    						temp = false;
    					}
    				}
    				if (temp) {
	    				LiquidStack tLiquid = LiquidContainerRegistry.getLiquidForFilledItem(mInventory[0]);
	    				mLiquid.itemID=tLiquid.itemID;
	    				mLiquid.itemMeta=tLiquid.itemMeta;
	    				mLiquid.amount=tLiquid.amount;
	    				mBaseMetaTileEntity.decrStackSize(0, 1);
    				}
    			}
    		}
			mBaseMetaTileEntity.mActive = (mBaseMetaTileEntity.getStored() >= maxEUOutput());
    	}
    }
    
    public boolean isValidFuel(LiquidStack aLiquid) {
    	return getFuelValue(aLiquid)>0;
    }
    
    /**
     * @return EU per MB
     */
    public int getFuelValue(LiquidStack aLiquid) {
    	if (aLiquid == null) return 0;
    	LiquidStack tLiquid;
    	for (int i = 0; i < GT_Recipe.sHotFuels.size(); i++) {
    		if ((tLiquid = LiquidContainerRegistry.getLiquidForFilledItem(GT_Recipe.sHotFuels.get(i).mInput1)) != null) if (aLiquid.isLiquidEqual(tLiquid)) return GT_Recipe.sHotFuels.get(i).mStartEU;
        }
    	return 0;
    }
    
	@Override
	public int getInvSideIndex(ForgeDirection aSide, int aFacing) {
		if (aSide == ForgeDirection.UP || aSide == ForgeDirection.DOWN) return 0;
		return 1;
	}
	
	@Override
	public int getInvSideLength(ForgeDirection aSide, int aFacing) {
		return 1;
	}
	
	@Override
	public int getTextureIndex(int aSide, int aFacing) {
    	if (aSide == 0)
    		return 38;
    	else if (aSide == 1)
    		return mBaseMetaTileEntity.mActive?84:83;
    	else
    		return 36;
	}

	@Override
	public String getMainInfo() {
		if (mLiquid == null || mLiquid.itemID == 0 || mLiquid.asItemStack() == null) return "";
		return "" + mLiquid.asItemStack().getDisplayName();
	}
	@Override
	public String getSecondaryInfo() {
		return "" + mLiquid.amount;
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
	public LiquidStack getLiquid() {
		return mLiquid;
	}
	
	@Override
	public int getCapacity() {
		return 10000;
	}

	@Override
	public int fill(LiquidStack resource, boolean doFill) {
		if (resource == null || resource.itemID <= 0 || !isValidFuel(resource))
			return 0;
		
		if (mLiquid == null || mLiquid.itemID <= 0) {
			if (resource.amount <= getCapacity()) {
				if (doFill)
					mLiquid = resource.copy();
				return resource.amount;
			} else {
				if (doFill) {
					mLiquid = resource.copy();
					mLiquid.amount = getCapacity();
					if (mBaseMetaTileEntity!=null)
						LiquidEvent.fireEvent(new LiquidEvent.LiquidFillingEvent(mLiquid, mBaseMetaTileEntity.worldObj, mBaseMetaTileEntity.xCoord, mBaseMetaTileEntity.yCoord, mBaseMetaTileEntity.zCoord, this));
				}
				return getCapacity();
			}
		}
		
		if (!mLiquid.isLiquidEqual(resource))
			return 0;

		int space = getCapacity() - mLiquid.amount;
		if (resource.amount <= space) {
			if (doFill)
				mLiquid.amount += resource.amount;
			return resource.amount;
		} else {
			if (doFill)
				mLiquid.amount = getCapacity();
			return space;
		}

	}
	@Override
	public LiquidStack drain(int maxDrain, boolean doDrain) {
		return null;
	}

	@Override
	public int getTankPressure() {
		return -100;
	}
}
