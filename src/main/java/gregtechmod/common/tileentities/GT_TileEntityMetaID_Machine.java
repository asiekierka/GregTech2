package gregtechmod.common.tileentities;

import gregtechmod.GT_Mod;
import gregtechmod.api.IGregTechDeviceInformation;
import gregtechmod.common.GT_ModHandler;
import ic2.api.Direction;
import ic2.api.ElectricItem;
import ic2.api.IElectricItem;
import ic2.api.IEnergyStorage;
import ic2.api.IWrenchable;
import ic2.api.energy.EnergyNet;
import ic2.api.energy.tile.IEnergySink;
import ic2.api.energy.tile.IEnergySource;
import ic2.api.network.INetworkDataProvider;
import ic2.api.network.INetworkUpdateListener;
import ic2.api.network.NetworkHelper;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.ForgeDirection;
import net.minecraftforge.common.ISidedInventory;

public class GT_TileEntityMetaID_Machine extends TileEntity implements ISidedInventory, IEnergySink, IEnergySource, IWrenchable, IEnergyStorage, IGregTechDeviceInformation, INetworkDataProvider, INetworkUpdateListener {
	
    /**
     * @return true if this Device emits Energy
     */
    public boolean isEnetOutput() {return false;}
    
    /**
     * @return true if this Device consumes Energy
     */
    public boolean isEnetInput()  {return false;}

    /**
     * @return the amount of EU, which can be stored in this Device. Default is 0 EU.
     */
    public int maxEUStore()  {return 0;}

    /**
     * @return the amount of EU/t, which can be accepted by this Device before it explodes. Default is 0 EU/t.
     */
    public int maxEUInput()  {return 0;}
    
    /**
     * @return the amount of EU/t, which can be outputted by this Device. Default is 0 EU/t.
     */
    public int maxEUOutput() {return 0;}

    /**
     * @param 0 = -X; 1 = +X; 2 = -Y; 3 = +Y; 4 = -Z; 5 = +Z
     * @return true if that Direction is an Output.
     */
    public boolean isOutputFacing(short aDirection) {return false;}
    
    /**
     * @param 0 = -X; 1 = +X; 2 = -Y; 3 = +Y; 4 = -Z; 5 = +Z
     * @return true if that Direction is an Input.
     */
    public boolean isInputFacing(short aDirection) {return false;}
    
    /**
     * @param aFacing
     * @return if aFacing would be a valid Facing for this Device
     */
    public boolean isFacingValid(int aFacing) {return false;}
    
    /**
     * Is called by invalidate right before it removes the TileEntity from the E-net
     */
    public void onRemoval() {}
    
    /**
     * @return Amount of InventorySlots for this Device
     */
    public int getInventorySlotCount() {return 0;}
    
    /**
     * Use this to add custom Data instead of using writeToNBT
     */
    public void storeAdditionalData(NBTTagCompound aNBT) {}

    /**
     * Use this to read custom Data instead of using readFromNBT
     */
    public void getAdditionalData(NBTTagCompound aNBT) {}

    /**
     * Use this instead of updateEntity (gets called before all the E-net-Stuff)
     */
    public void onPreTickUpdate() {}
    
    /**
     * Use this instead of updateEntity (gets called after all the E-net-Stuff)
     */
    public void onPostTickUpdate() {}
    
    /**
     * Use this instead of updateEntity (gets called once before anything)
     */
    public void onFirstTickUpdate() {}
    
    /**
     * @return true if the Machine can be accessed
     */
    public boolean isAccessible(EntityPlayer aPlayer) {return false;}
    
    /**
     * @return if aIndex is a valid Slot. false for things like HoloSlots.
     */
    public boolean isValidSlot(int aIndex) {return true;}
    
    /**
     * This is used to set the internal Energy to the given Parameter. I use this for the IDSU.
     */
	public void setEnergyVar(int aEU) {
		mStoredEnergy = aEU;
	}

    /**
     * This is used to get the internal Energy. I use this for the IDSU.
     */
	public int getEnergyVar() {
		return mStoredEnergy;
	}
    
    /**
     * Determines the Tier of the Machine, used for charging Tools.
     */
    public int getTier() {
    	return getTier(Math.max(maxEUOutput(), maxEUInput()));
    }
    
    public int getTier(int aValue) {
    	int tMaxEU = aValue;
    	return tMaxEU<=32?1:tMaxEU<=128?2:tMaxEU<=512?3:tMaxEU<=2048?4:tMaxEU<=8192?5:6;
    }
    
    public int getChargeTier() {
    	return getTier();
    }
    
    /**
     * gets the first RechargerSlot
     */
    public int rechargerSlotStartIndex() {
    	return 0;
    }
    
    /**
     * gets the amount of RechargerSlots
     */
    public int rechargerSlotCount() {
    	return 0;
    }
    
    /**
     * gets the first DechargerSlot
     */
    public int dechargerSlotStartIndex() {
    	return 0;
    }

    /**
     * gets the amount of DechargerSlots
     */
    public int dechargerSlotCount() {
    	return 0;
    }
    
    /**
     * gets if Protected from other Players or not
     */
    public boolean ownerControl() {
    	return false;
    }

    /**
     * returns if this Object is animated
     */
    public boolean hasAnimation() {
    	return false;
    }

    /**
     * returns the DebugLog
     */
	public ArrayList<String> getSpecialDebugInfo(EntityPlayer aPlayer, int aLogLevel, ArrayList<String> aList) {
		return aList;
	}
	
	@Override
	public String getMainInfo() {
		return "";
	}

	@Override
	public String getSecondaryInfo() {
		return "";
	}

	@Override
	public String getTertiaryInfo() {
		return "";
	}

	@Override
	public boolean isGivingInformation() {
		return false;
	}
	
	@Override public int getStartInventorySide(ForgeDirection aSide) {return 0;}
	@Override public int getSizeInventorySide(ForgeDirection aSide) {return getInventorySlotCount();}
    @Override public String getInvName() {return "Defaultmachine";}
    @Override public void openChest() {}
    @Override public void closeChest() {}
    
    // Basecode, which should not be touched in extending Devices
    
	public ItemStack[] mInventory;
	
	public boolean mIsAddedToEnet, mFirstTick, mNeedsUpdate = true, mActive, oActive, mRedstone, oRedstone;
	private int mStoredEnergy, oOutput;
	private short mFacing, oFacing;
	public String mOwnerName = "";
	
	public long mTickTimer;
	
    public GT_TileEntityMetaID_Machine() {
        mInventory = new ItemStack[getInventorySlotCount()];
        mIsAddedToEnet = false;
        mFirstTick = true;
        mStoredEnergy = 0;
        mTickTimer = 0;
        mFacing = -1;
    }


    
    @Override
	public void updateEntity() {
    	if (isInvalid()) return;
    	
    	mTickTimer++;
    	
    	if (mFirstTick) {
    		if (mFacing == -1) mFacing = 0;
    		if (mTickTimer>19) {
	    		mFirstTick = false;
	            onFirstTickUpdate();
	            NetworkHelper.updateTileEntityField(this, "mOwnerName");
	            mNeedsUpdate = true;
    		} else return;
    	}
    	
    	if (mTickTimer % 6000 == 0) mNeedsUpdate = true;
    	
    	onPreTickUpdate();
	    if (worldObj.isRemote) {
	    	if ((mNeedsUpdate || hasAnimation()) && (GT_Mod.instance.mAnimations || mTickTimer<250)) {
			    worldObj.markBlockForRenderUpdate(xCoord, yCoord, zCoord);
			    mNeedsUpdate = false;
	    	}
	    } else {
		    if (mNeedsUpdate) {
	    		worldObj.addBlockEvent(xCoord, yCoord, zCoord, GT_Mod.instance.mBlocks[1].blockID, 0, mFacing);
		    	worldObj.addBlockEvent(xCoord, yCoord, zCoord, GT_Mod.instance.mBlocks[1].blockID, 1, mActive?1:0);
		    	worldObj.addBlockEvent(xCoord, yCoord, zCoord, GT_Mod.instance.mBlocks[1].blockID, 2, mRedstone?1:0);
			    mNeedsUpdate = false;
	    	}
	    	
	    	if (mActive != oActive) {
	    		oActive = mActive;
			    mNeedsUpdate = true;
	    	}

	    	if (mRedstone != oRedstone) {
		    	worldObj.notifyBlocksOfNeighborChange(xCoord, yCoord, zCoord, worldObj.getBlockId(xCoord, yCoord, zCoord));
	    		oRedstone = mRedstone;
			    mNeedsUpdate = true;
	    	}
	    	
	    	if (mTickTimer > 30 && (isEnetOutput()||isEnetInput()) && !mIsAddedToEnet) {
	    		mIsAddedToEnet = GT_ModHandler.addTileToEnet(this);
	    	}
	    	
		    if (mFacing != oFacing) {
		    	oFacing = mFacing;
		    	if (mIsAddedToEnet) GT_ModHandler.removeTileFromEnet(this);
		    	mIsAddedToEnet = false;
		    }

		    if (getOutput() != oOutput) {
		    	oOutput = getOutput();
		    	if (mIsAddedToEnet) GT_ModHandler.removeTileFromEnet(this);
		    	mIsAddedToEnet = false;
		    	mNeedsUpdate = true;
		    }
		    
	        if (mIsAddedToEnet && isEnetOutput() && getEnergyVar() >= maxEUOutput() && maxEUOutput() > 0) {
	            try {
	            	EnergyNet tEnergyNet = EnergyNet.getForWorld(worldObj);
	            	if (tEnergyNet != null)
	            		setStoredEnergy(getEnergyVar() + tEnergyNet.emitEnergyFrom(this, maxEUOutput()) - maxEUOutput());
	            } catch(Exception e) {
	            	
	            }
	        }
	        
	        for (int j = 0; j < getChargeTier(); j++) {
		        for (int i = dechargerSlotStartIndex(); i < dechargerSlotCount()+dechargerSlotStartIndex(); i++) {
			        if (mInventory[i] != null && demandsEnergy()>0 && mInventory[i].getItem() instanceof IElectricItem) {
			            if (((IElectricItem)mInventory[i].getItem()).canProvideEnergy())
			                increaseStoredEnergy(ElectricItem.discharge(mInventory[i], maxEUStore() - getEnergyVar(), getChargeTier(), false, false));
			        }
		        }
		        
		        for (int i = rechargerSlotStartIndex(); i < rechargerSlotCount()+rechargerSlotStartIndex(); i++) {
			        if (getEnergyVar() > 0 && mInventory[i] != null && mInventory[i].getItem() instanceof IElectricItem)
			        	decreaseStoredEnergy(ElectricItem.charge(mInventory[i], getEnergyVar(), getChargeTier(), false, false), true);
		        }
	        }
	    }
    	onPostTickUpdate();
    	onInventoryChanged();
    }

    @Override
	public void receiveClientEvent(int aEventID, int aValue) {
		super.receiveClientEvent(aEventID, aValue);
		if (worldObj.isRemote) {
			switch(aEventID) {
			case 0:
				mFacing = (short)aValue;
				mNeedsUpdate = true;
				break;
			case 1:
		    	mActive = (aValue!=0);
				mNeedsUpdate = true;
		    	break;
			case 2:
		    	mRedstone = (aValue!=0);
				mNeedsUpdate = true;
		    	break;
			}
		}
	}
    
    @Override
    public void readFromNBT(NBTTagCompound aNBT) {
        super.readFromNBT(aNBT);
        
        mStoredEnergy	= aNBT.getInteger("mStoredEnergy");
        mFacing			= aNBT.getShort("mFacing");
        mOwnerName		= aNBT.getString("mOwnerName");
    	mActive			= aNBT.getBoolean("mActive");
    	mRedstone		= aNBT.getBoolean("mRedstone");
    	
        getAdditionalData(aNBT);
        
        NBTTagList tagList = aNBT.getTagList("Inventory");
        for (int i = 0; i < tagList.tagCount(); i++) {
            NBTTagCompound tag = (NBTTagCompound) tagList.tagAt(i);
            byte slot = tag.getByte("Slot");
            if (slot >= 0 && slot < mInventory.length) {
                mInventory[slot] = ItemStack.loadItemStackFromNBT(tag);
            }
        }
    }

    @Override
    public void writeToNBT(NBTTagCompound aNBT) {
        super.writeToNBT(aNBT);
        
        aNBT.setInteger("mStoredEnergy", mStoredEnergy);
        aNBT.setShort("mFacing", mFacing);
        aNBT.setString("mOwnerName", mOwnerName);
    	aNBT.setBoolean("mActive", mActive);
    	aNBT.setBoolean("mRedstone", mRedstone);
        
        storeAdditionalData(aNBT);
        
        NBTTagList itemList = new NBTTagList();
        for (int i = 0; i < mInventory.length; i++) {
            ItemStack stack = mInventory[i];
            if (stack != null) {
                NBTTagCompound tag = new NBTTagCompound();
                tag.setByte("Slot", (byte) i);
                stack.writeToNBT(tag);
                itemList.appendTag(tag);
            }
        }
        aNBT.setTag("Inventory", itemList);
    }

    @Override
    public void invalidate() {
    	onRemoval();
    	if (mIsAddedToEnet) mIsAddedToEnet = !GT_ModHandler.removeTileFromEnet(this);
        super.invalidate();
    }

	@Override
    public void validate() {
        super.validate();
        mNeedsUpdate = true;
        mTickTimer = 0;
    }
    
    @Override
	public boolean isAddedToEnergyNet() {
		return mIsAddedToEnet;
	}
    
	@Override
	public boolean acceptsEnergyFrom(TileEntity aReceiver, Direction aDirection) {
    	if (isInvalid()) return false;
		return isInputFacing(GT_ModHandler.convertIC2DirectionToShort(aDirection));
	}

	@Override
	public boolean emitsEnergyTo(TileEntity aReceiver, Direction aDirection) {
    	if (isInvalid()) return false;
		return isOutputFacing(GT_ModHandler.convertIC2DirectionToShort(aDirection));
	}

	@Override
	public boolean wrenchCanSetFacing(EntityPlayer entityPlayer, int aFacing) {
		return aFacing != mFacing && isFacingValid(aFacing);
	}

	@Override
	public short getFacing() {
		return mFacing;
	}

	@Override
	public void setFacing(short aFacing) {
        mNeedsUpdate = true;
		if (isFacingValid(aFacing)) {
			mFacing = aFacing;
		}
	}

	@Override
	public boolean wrenchCanRemove(EntityPlayer entityPlayer) {
		return true;
	}

	@Override
	public float getWrenchDropRate() {
		return 0.8F;
	}
	
    @Override
    public int getSizeInventory() {
        return mInventory.length;
    }

    @Override
    public ItemStack getStackInSlot(int slot) {
        return mInventory[slot];
    }

    @Override
    public void setInventorySlotContents(int slot, ItemStack stack) {
        mInventory[slot] = stack;
        if (stack != null && stack.stackSize > getInventoryStackLimit()) {
            stack.stackSize = getInventoryStackLimit();
        }
    }

    @Override
    public ItemStack decrStackSize(int slot, int amt) {
        ItemStack stack = getStackInSlot(slot);
        if (stack != null) {
            if (stack.stackSize <= amt) {
                setInventorySlotContents(slot, null);
            } else {
                stack = stack.splitStack(amt);
                if (stack.stackSize == 0) {
                    setInventorySlotContents(slot, null);
                }
            }
        }
        return stack;
    }

    @Override
    public ItemStack getStackInSlotOnClosing(int slot) {
        ItemStack stack = getStackInSlot(slot);
        if (stack != null) {
            setInventorySlotContents(slot, null);
        }
        return stack;
    }
    
    @Override
    public int getInventoryStackLimit() {
        return 64;
    }
    
    public boolean playerOwnsThis(EntityPlayer aPlayer) {
    	if (ownerControl())
    		if (mOwnerName.equals("")&&!worldObj.isRemote)
    			mOwnerName = aPlayer.username;
    		else
    			if (!mOwnerName.equals("Player") && !mOwnerName.equals(aPlayer.username))
    				return false;
    	return true;
    }
    
    @Override
    public boolean isUseableByPlayer(EntityPlayer aPlayer) {
    	mNeedsUpdate = true;
        return playerOwnsThis(aPlayer)&&!mFirstTick&&worldObj.getBlockTileEntity(xCoord, yCoord, zCoord) == this && aPlayer.getDistanceSq(xCoord + 0.5, yCoord + 0.5, zCoord + 0.5) < 64 && isAccessible(aPlayer);
    }
    
	@Override
	public int getStored() {
		return Math.min(getEnergyVar(), maxEUStore());
	}
	
	public boolean setStoredEnergy(int aEnergy) {
		if (aEnergy < 0) aEnergy = 0;
		setEnergyVar(aEnergy);
		return true;
	}
	
	public boolean decreaseStoredEnergy(int aEnergy, boolean aIgnoreTooLessEnergy) {
		if (getEnergyVar() - aEnergy >= 0 || aIgnoreTooLessEnergy) {
			setEnergyVar(getEnergyVar() - aEnergy);
			if (getEnergyVar() < 0) {
				setStoredEnergy(0);
				return false;
			}
			return true;
		}
		return false;
	}
	
	public boolean increaseStoredEnergy(int aEnergy) {
		if (getEnergyVar() < maxEUStore()) {
			setStoredEnergy(getEnergyVar() + aEnergy);
			return true;
		}
		return false;
	}
	
	public void doExplosion(int aAmount) {
        float tStrength = aAmount<10?1.0F:aAmount<32?2.0F:aAmount<128?3.0F:aAmount<512?4.0F:aAmount<2048?5.0F:aAmount<4096?6.0F:aAmount<8192?7.0F:8.0F;
        int tX=xCoord, tY=yCoord, tZ=zCoord;
        worldObj.setBlock(tX, tY, tZ, 0);
        worldObj.createExplosion(null, tX+0.5, tY+0.5, tZ+0.5, tStrength, true);
    }
	
	@Override
	public int getMaxEnergyOutput() {
		return maxEUOutput();
	}
	
	@Override
	public int demandsEnergy() {
		return maxEUStore() - getEnergyVar();
	}
	
	@Override
	public int injectEnergy(Direction directionFrom, int aAmount) {
		if (aAmount > maxEUInput()) {
			doExplosion(aAmount);
			return 0;
		}
		setStoredEnergy(getEnergyVar() + aAmount);
		return 0;
	}
	
	public int getTexture(int aSide, int aMeta) {
		return 0;
	}
	
	@Override
	public int getCapacity() {
		return maxEUStore();
	}
	
	@Override
	public int getOutput() {
		return maxEUOutput();
	}
	
    public boolean isActive() {
    	return mActive;
    }
    
	public ArrayList<String> getDebugInfo(EntityPlayer aPlayer, int aLogLevel) {
		ArrayList<String> tList = new ArrayList<String>();
		if (aLogLevel > 2) {
			if (isEnetInput() || isEnetOutput())	tList.add("Storage: " + getEnergyVar() + "/" + maxEUStore() + "EU");
			if (isEnetInput()) 						tList.add("Max-EU-IN: " + maxEUInput());
			if (isEnetOutput())						tList.add("Max-EU-OUT: " + maxEUOutput());
		}
		if (aLogLevel > 1) {
			if (isEnetInput() || isEnetOutput())	tList.add("Tier: " + getTier());
													tList.add("Is" + (isAccessible(aPlayer)?" ":" not") + "accessible for you");
		}
		if (aLogLevel > 0) {
													tList.add("Machine is " + (mActive?"active":"inactive"));
		}
		return getSpecialDebugInfo(aPlayer, aLogLevel, tList);
	}

	@Override
	public void setStored(int aEU) {
		setEnergyVar(aEU);
	}

	@Override
	public int addEnergy(int aEnergy) {
		if (aEnergy > 0)
			increaseStoredEnergy(aEnergy);
		else
			decreaseStoredEnergy(-aEnergy, true);
		return getStored();
	}

	@Override
	public boolean isTeleporterCompatible(Direction side) {
		return false;
	}

	@Override
	public ItemStack getWrenchDrop(EntityPlayer entityPlayer) {
		return new ItemStack(GT_Mod.instance.mBlocks[1], 1, worldObj.getBlockMetadata(xCoord, yCoord, zCoord));
	}
	
	@Override
	public void onNetworkUpdate(String field) {
		
	}

	@Override
	public List<String> getNetworkedFields() {
		ArrayList<String> rList = new ArrayList<String>();
		rList.add("mOwnerName");
		return null;
	}

	@Override
	public int getMaxSafeInput() {
		return maxEUInput();
	}
}
