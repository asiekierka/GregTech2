package gregtechmod.api;

import gregtechmod.GT_Mod;
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
import net.minecraftforge.liquids.ILiquidTank;
import net.minecraftforge.liquids.ITankContainer;
import net.minecraftforge.liquids.LiquidStack;

/**
 * Do never include this Class in a Copy of my API. It will cause incompatiblities!
 * 
 * This is only here to not give you Compile-Errors. The GT_ModHander can easyly be commented out for your compiling.
 * Note that this file will contain all the needed Code of the Interfaces for wrenching, energynet and similar things.
 */
public class BaseMetaTileEntity extends TileEntity implements ITankContainer, IEnergyStorage, IWrenchable, ISidedInventory, IEnergySink, IEnergySource, IGregTechDeviceInformation, IMachineBlockUpdateable, INetworkDataProvider, INetworkUpdateListener {
	private static final short MAXIMUM_METATILE_IDS = 1024;
	public static boolean sAnimationsAllowed = true;
	
	public static MetaTileEntity[] mMetaTileList = new MetaTileEntity[MAXIMUM_METATILE_IDS];
	
	public MetaTileEntity mMetaTileEntity;
	public int mFacing = -1, oFacing = -1, oOutput = 0, mStoredEnergy = 0, mID = 0, mDisplayErrorCode = 0;
	public long mTickTimer = 0;
	public boolean mActive = false, oActive = false, mIsAddedToEnet = false, mNeedsUpdate = true, mRedstone = false, oRedstone = false;
	public String mOwnerName = "";
	
	@Override
    public void writeToNBT(NBTTagCompound aNBT) {
    	super.writeToNBT(aNBT);
        aNBT.setInteger	("mID"			, mID);
        aNBT.setInteger	("mStoredEnergy", mStoredEnergy);
        aNBT.setShort	("mFacing"		, (short)mFacing);
        aNBT.setString	("mOwnerName"	, mOwnerName);
    	aNBT.setBoolean	("mActive"		, mActive);
    	aNBT.setBoolean	("mRedstone"	, mRedstone);
    	
    	if (mMetaTileEntity != null) {
    		mMetaTileEntity.saveNBTData(aNBT);
	        NBTTagList tItemList = new NBTTagList();
	        for (int i = 0; i < mMetaTileEntity.mInventory.length; i++) {
	            ItemStack tStack = mMetaTileEntity.mInventory[i];
	            if (tStack != null) {
	                NBTTagCompound tTag = new NBTTagCompound();
	                tTag.setInteger("IntSlot", i);
	                tStack.writeToNBT(tTag);
	                tItemList.appendTag(tTag);
	            }
	        }
	        aNBT.setTag("Inventory", tItemList);
    	}
    }

	@Override
	public void readFromNBT(NBTTagCompound aNBT) {
		super.readFromNBT(aNBT);
        mID				= aNBT.getInteger	("mID");
        mStoredEnergy	= aNBT.getInteger	("mStoredEnergy");
        mFacing			= aNBT.getShort		("mFacing");
        mOwnerName		= aNBT.getString	("mOwnerName");
    	mActive			= aNBT.getBoolean	("mActive");
    	mRedstone		= aNBT.getBoolean	("mRedstone");
    	
    	if (mID != 0 && createNewMetatileEntity(mID)) {
		    mMetaTileEntity.loadNBTData(aNBT);
	        NBTTagList tItemList = aNBT.getTagList("Inventory");
	        for (int i = 0; i < tItemList.tagCount(); i++) {
	            NBTTagCompound tTag = (NBTTagCompound)tItemList.tagAt(i);
	            int tSlot = tTag.getInteger("IntSlot");
	            if (tSlot >= 0 && tSlot < mMetaTileEntity.mInventory.length) {
	            	mMetaTileEntity.mInventory[tSlot] = ItemStack.loadItemStackFromNBT(tTag);
	            }
	        }
		}
    }
	
	public boolean createNewMetatileEntity(int aID) {
		if (aID >= MAXIMUM_METATILE_IDS || mMetaTileList[aID] == null) {
			System.err.println("MetaID " + aID + " not loadable => locking TileEntity!");
		} else {
			if (aID != 0) {
				mMetaTileEntity = mMetaTileList[aID].newMetaEntity(this);
				mMetaTileEntity.mBaseMetaTileEntity = this;
				mID = aID;
				return true;
			}
		}
		return false;
	}

	@Override
    public void updateEntity() {
    	if (isInvalid() || mMetaTileEntity == null) return;
    	
    	mTickTimer++;

	    if (mTickTimer==20) {
    		if (mFacing == -1) mFacing = 0;
	    	if (!worldObj.isRemote) {
	    		worldObj.addBlockEvent(xCoord, yCoord, zCoord, worldObj.getBlockId(xCoord, yCoord, zCoord), 3, mID);
	    	}
    		mMetaTileEntity.onFirstTick();
    		NetworkHelper.updateTileEntityField(this, "mOwnerName");
	    }
    	
    	if (mTickTimer>20) {
        	mMetaTileEntity.onPreTick();
        	
    	    if (worldObj.isRemote) {
    	    	if ((mNeedsUpdate || mMetaTileEntity.hasAnimation()) && (sAnimationsAllowed || mTickTimer<250)) {
    			    worldObj.markBlockForRenderUpdate(xCoord, yCoord, zCoord);
    			    mNeedsUpdate = false;
    	    	}
    	    } else {
    		    if (mNeedsUpdate || mTickTimer%1000==50) {
    	    		worldObj.addBlockEvent(xCoord, yCoord, zCoord, worldObj.getBlockId(xCoord, yCoord, zCoord), 0, mFacing);
    		    	worldObj.addBlockEvent(xCoord, yCoord, zCoord, worldObj.getBlockId(xCoord, yCoord, zCoord), 1, mActive?1:0);
    		    	worldObj.addBlockEvent(xCoord, yCoord, zCoord, worldObj.getBlockId(xCoord, yCoord, zCoord), 2, mRedstone?1:0);
    	    		worldObj.addBlockEvent(xCoord, yCoord, zCoord, worldObj.getBlockId(xCoord, yCoord, zCoord), 3, mID);
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
    	    	
    	    	if (mTickTimer > 30 && (mMetaTileEntity.isEnetOutput()||mMetaTileEntity.isEnetInput()) && !mIsAddedToEnet) {
    	    		mIsAddedToEnet = GT_ModHandler.addTileToEnet(this);
    	    	}
    	    	
    		    if (mFacing != oFacing) {
    		    	oFacing = mFacing;
    		    	if (mIsAddedToEnet) GT_ModHandler.removeTileFromEnet(this);
    		    	mIsAddedToEnet = false;
    		    	mNeedsUpdate = true;
    		    }
    	    	
    		    if (getOutput() != oOutput) {
    		    	oOutput = getOutput();
    		    	if (mIsAddedToEnet) GT_ModHandler.removeTileFromEnet(this);
    		    	mIsAddedToEnet = false;
    		    }
    		    
    	        if (mIsAddedToEnet && mMetaTileEntity.isEnetOutput() && mMetaTileEntity.getEnergyVar() >= Math.max(mMetaTileEntity.maxEUOutput(), mMetaTileEntity.getMinimumStoredEU()) && mMetaTileEntity.maxEUOutput() > 0) {
    	            try {
    	            	EnergyNet tEnergyNet = EnergyNet.getForWorld(worldObj);
    	            	if (tEnergyNet != null)
    	            		setStoredEnergy(mMetaTileEntity.getEnergyVar() + tEnergyNet.emitEnergyFrom(this, mMetaTileEntity.maxEUOutput()) - mMetaTileEntity.maxEUOutput());
    	            } catch(Exception e) {
    	            	
    	            }
    	        }
    	        
    	        for (int j = 0; j < mMetaTileEntity.getInputTier(); j++) {
    		        for (int i = mMetaTileEntity.dechargerSlotStartIndex(); i < mMetaTileEntity.dechargerSlotCount()+mMetaTileEntity.dechargerSlotStartIndex(); i++) {
    			        if (mMetaTileEntity.mInventory[i] != null && demandsEnergy()>0 && mMetaTileEntity.mInventory[i].getItem() instanceof IElectricItem && ((IElectricItem)mMetaTileEntity.mInventory[i].getItem()).canProvideEnergy())
    			        	increaseStoredEnergy(ElectricItem.discharge(mMetaTileEntity.mInventory[i], mMetaTileEntity.maxEUStore() - mMetaTileEntity.getEnergyVar(), mMetaTileEntity.getInputTier(), false, false));
    		        }
    	        }

        	    for (int j = 0; j < mMetaTileEntity.getOutputTier(); j++) {
    		        for (int i = mMetaTileEntity.rechargerSlotStartIndex(); i < mMetaTileEntity.rechargerSlotCount()+mMetaTileEntity.rechargerSlotStartIndex(); i++) {
    			        if (mMetaTileEntity.getEnergyVar() > 0 && mMetaTileEntity.mInventory[i] != null && mMetaTileEntity.mInventory[i].getItem() instanceof IElectricItem)
    			        	decreaseStoredEnergy(ElectricItem.charge(mMetaTileEntity.mInventory[i], mMetaTileEntity.getEnergyVar(), mMetaTileEntity.getOutputTier(), false, false), true);
    		        }
    	        }
    	    }

        	mMetaTileEntity.onPostTick();
        	
        	onInventoryChanged();
    	}
    }
    
    @Override
    public void receiveClientEvent(int aEventID, int aValue) {
		super.receiveClientEvent(aEventID, aValue);
		
		if (mMetaTileEntity != null) mMetaTileEntity.receiveClientEvent(aEventID, aValue);
		
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
			case 3:
				if (mID == 0) {
			    	mID = aValue;
			    	createNewMetatileEntity(mID);
					mNeedsUpdate = true;
				}
		    	break;
			}
		}
	}
    
	public ArrayList<String> getDebugInfo(EntityPlayer aPlayer, int aLogLevel) {
		ArrayList<String> tList = new ArrayList<String>();
		if (aLogLevel > 2) {
			tList.add(mIsAddedToEnet?"Is registered at E-net":"Is not registerd at E-net");
			tList.add(demandsEnergy()>0?"Needs Energy":"Doesn't need Energy");
			tList.add("Facing: " + mFacing);
		}
		if (aLogLevel > 1) {
			if (mMetaTileEntity.isEnetInput() || mMetaTileEntity.isEnetOutput()) tList.add("Storage: " + mMetaTileEntity.getEnergyVar() + "/" + mMetaTileEntity.maxEUStore() + "EU");
			if (mMetaTileEntity.isEnetInput()) tList.add("Max-EU-IN: " + mMetaTileEntity.maxEUInput());
			if (mMetaTileEntity.isEnetOutput()) tList.add("Max-EU-OUT: " + mMetaTileEntity.maxEUOutput());
			if (mMetaTileEntity.isEnetInput()) tList.add("Tier IN: " + mMetaTileEntity.getInputTier());
			if (mMetaTileEntity.isEnetOutput()) tList.add("Tier OUT: " + mMetaTileEntity.getOutputTier());
			tList.add("Is" + (mMetaTileEntity.isAccessAllowed(aPlayer)?" ":" not") + " accessible for you");
		}
		if (aLogLevel > 0) {
			tList.add("Machine is " + (mActive?"active":"inactive"));
		}
		return mMetaTileEntity.getSpecialDebugInfo(aPlayer, aLogLevel, tList);
	}
	
    /**
     * Here at this Line begins "Code-I-will-most-likely-never-touch-again"
     */
	@Override public String getMainInfo() {if (mMetaTileEntity != null) return mMetaTileEntity.getMainInfo(); return "";}
	@Override public String getSecondaryInfo() {if (mMetaTileEntity != null) return mMetaTileEntity.getSecondaryInfo(); return "";}
	@Override public String getTertiaryInfo() {if (mMetaTileEntity != null) return mMetaTileEntity.getTertiaryInfo(); return "";}
	@Override public boolean isGivingInformation() {if (mMetaTileEntity != null) return mMetaTileEntity.isGivingInformation(); return false;}
	@Override public boolean wrenchCanSetFacing(EntityPlayer aPlayer, int aSide) {if (mMetaTileEntity != null) return mFacing != aSide && mMetaTileEntity.isFacingValid(aSide); return false;}
	@Override public short getFacing() {return (short)mFacing;}
	@Override public void setFacing(short aFacing) {if (mMetaTileEntity != null && mMetaTileEntity.isFacingValid(aFacing)) mFacing = aFacing; mNeedsUpdate = true; onMachineBlockUpdate();}
	@Override public boolean wrenchCanRemove(EntityPlayer entityPlayer) {return true;}
	@Override public float getWrenchDropRate() {if (mMetaTileEntity != null && mMetaTileEntity.isSimpleMachine()) return 1.0F; return 0.8F;}
	@Override public int getSizeInventory() {if (mMetaTileEntity != null) return mMetaTileEntity.getInvSize(); else return 0;}
	@Override public ItemStack getStackInSlot(int aIndex) {if (mMetaTileEntity != null) return mMetaTileEntity.mInventory[aIndex]; return null;}
	@Override public void setInventorySlotContents(int aIndex, ItemStack aStack) {if (mMetaTileEntity != null) mMetaTileEntity.mInventory[aIndex] = aStack;}
	@Override public String getInvName() {if (BaseMetaTileEntity.mMetaTileList[mID] != null) return BaseMetaTileEntity.mMetaTileList[mID].mName; return "";}
	@Override public int getInventoryStackLimit() {return 64;}
	@Override public void openChest()  {if (mMetaTileEntity != null) mMetaTileEntity.onOpenGUI();}
	@Override public void closeChest() {if (mMetaTileEntity != null) mMetaTileEntity.onCloseGUI();}
	@Override public int getStartInventorySide(ForgeDirection aSide) {if (mMetaTileEntity != null) return mMetaTileEntity.getInvSideIndex(aSide, mFacing); return 0;}
	@Override public int getSizeInventorySide(ForgeDirection aSide) {if (mMetaTileEntity != null) return mMetaTileEntity.getInvSideLength(aSide, mFacing); return 0;}
	@Override public boolean isAddedToEnergyNet() {return mIsAddedToEnet;}
    @Override public boolean isUseableByPlayer(EntityPlayer aPlayer) {return playerOwnsThis(aPlayer)&&mTickTimer>10&&worldObj.getBlockTileEntity(xCoord, yCoord, zCoord) == this && aPlayer.getDistanceSq(xCoord + 0.5, yCoord + 0.5, zCoord + 0.5) < 64 && mMetaTileEntity != null && mMetaTileEntity.isAccessAllowed(aPlayer);}
    @Override public int getStored() {if (mMetaTileEntity != null) return Math.min(mMetaTileEntity.getEnergyVar(), getCapacity()); return 0;}
    @Override public void validate() {super.validate(); mNeedsUpdate = true; mTickTimer = 0;}
    @Override public void invalidate() {if (mMetaTileEntity != null) mMetaTileEntity.onRemoval(); if (mIsAddedToEnet) mIsAddedToEnet = !GT_ModHandler.removeTileFromEnet(this); super.invalidate();}
    @Override public boolean acceptsEnergyFrom(TileEntity aReceiver, Direction aDirection) {if (isInvalid()) return false; if (mMetaTileEntity != null) return mMetaTileEntity.isInputFacing((short)aDirection.toSideValue()); return false;}
    @Override public boolean emitsEnergyTo(TileEntity aReceiver, Direction aDirection) {if (isInvalid()) return false; if (mMetaTileEntity != null) return mMetaTileEntity.isOutputFacing((short)aDirection.toSideValue()); return false;}
    @Override public ItemStack getStackInSlotOnClosing(int slot) {ItemStack stack = getStackInSlot(slot); if (stack != null) setInventorySlotContents(slot, null); return stack;}
    @Override public ItemStack decrStackSize(int aIndex, int aAmount) {ItemStack stack = getStackInSlot(aIndex); if (stack != null) {if (stack.stackSize <= aAmount) {setInventorySlotContents(aIndex, null);} else {stack = stack.splitStack(aAmount); if (stack.stackSize == 0) {setInventorySlotContents(aIndex, null);}}} return stack;}
	@Override public int getMaxEnergyOutput() {if (mMetaTileEntity != null) return mMetaTileEntity.maxEUOutput(); return 0;}
	@Override public int demandsEnergy() {if (mMetaTileEntity != null) return getCapacity() - mMetaTileEntity.getEnergyVar(); return 0;}
	@Override public int injectEnergy(Direction directionFrom, int aAmount) {if (mMetaTileEntity == null) return aAmount; if (aAmount > mMetaTileEntity.maxEUInput()) {doExplosion(aAmount); return 0;} setStoredEnergy(mMetaTileEntity.getEnergyVar() + aAmount); return 0;}
	@Override public int getCapacity() {if (mMetaTileEntity != null) return mMetaTileEntity.maxEUStore(); return 0;}
	@Override public int getOutput() {if (mMetaTileEntity != null) return mMetaTileEntity.maxEUOutput(); return 0;}
	@Override public void onMachineBlockUpdate() {if (mMetaTileEntity != null) mMetaTileEntity.onMachineBlockUpdate();}
	@Override public int fill(ForgeDirection from, LiquidStack resource, boolean doFill) {if (mMetaTileEntity != null) return mMetaTileEntity.fill(resource, doFill); return 0;}
	@Override public int fill(int tankIndex, LiquidStack resource, boolean doFill) {if (mMetaTileEntity != null) return mMetaTileEntity.fill(resource, doFill); return 0;}
	@Override public LiquidStack drain(ForgeDirection from, int maxDrain, boolean doDrain) {if (mMetaTileEntity != null) return mMetaTileEntity.drain(maxDrain, doDrain); return null;}
	@Override public LiquidStack drain(int tankIndex, int maxDrain, boolean doDrain) {if (mMetaTileEntity != null) return mMetaTileEntity.drain(maxDrain, doDrain); return null;}
	@Override public ILiquidTank[] getTanks(ForgeDirection direction) {if (mMetaTileEntity != null && mMetaTileEntity.getCapacity()>0) return new ILiquidTank[] {mMetaTileEntity}; return new ILiquidTank[] {};}
	@Override public ILiquidTank getTank(ForgeDirection direction, LiquidStack type) {if (mMetaTileEntity != null && (type == null || (type != null && type.isLiquidEqual(mMetaTileEntity.getLiquid())))) return mMetaTileEntity; return null;}
	@Override public ItemStack getWrenchDrop(EntityPlayer entityPlayer) {return new ItemStack(GT_Mod.instance.mBlocks[1], 1, mID);}
	@Override public void setStored(int aEU) {setStoredEnergy(aEU);}
	@Override public boolean isTeleporterCompatible(Direction side) {return false;}
	@Override public int getMaxSafeInput() {if (mMetaTileEntity == null) return Integer.MAX_VALUE; return mMetaTileEntity.maxEUInput();}
	
	public boolean getRedstone() {return worldObj.isBlockIndirectlyGettingPowered(xCoord, yCoord, zCoord);}
	public boolean isActive() {return mActive;}
	public boolean isValidSlot(int aIndex) {if (mMetaTileEntity != null) return mMetaTileEntity.isValidSlot(aIndex); return false;}
    public boolean playerOwnsThis(EntityPlayer aPlayer) {if (mMetaTileEntity == null) return false; if (mMetaTileEntity.ownerControl()) if (mOwnerName.equals("")&&!worldObj.isRemote) mOwnerName = aPlayer.username; else if (!mOwnerName.equals("Player") && !mOwnerName.equals(aPlayer.username)) return false; return true;}
    public boolean setStoredEnergy(int aEnergy) {if (aEnergy < 0) aEnergy = 0; mMetaTileEntity.setEnergyVar(aEnergy); return true;}
    public boolean increaseStoredEnergy(int aEnergy) {if (mMetaTileEntity == null) return false; if (mMetaTileEntity.getEnergyVar() < getCapacity()) {setStoredEnergy(mMetaTileEntity.getEnergyVar() + aEnergy); return true;} return false;}
	public boolean decreaseStoredEnergy(int aEnergy, boolean aIgnoreTooLessEnergy) {if (mMetaTileEntity == null) return false; if (mMetaTileEntity.getEnergyVar() - aEnergy >= 0 || aIgnoreTooLessEnergy) {mMetaTileEntity.setEnergyVar(mMetaTileEntity.getEnergyVar() - aEnergy); if (mMetaTileEntity.getEnergyVar() < 0) {setStoredEnergy(0); return false;} return true;} return false;}
	public void onRightclick(EntityPlayer aPlayer) {mNeedsUpdate = true; if (mMetaTileEntity != null) mMetaTileEntity.onRightclick(aPlayer);}
	public void doExplosion(int aAmount) {float tStrength = aAmount<10?1.0F:aAmount<32?2.0F:aAmount<128?3.0F:aAmount<512?4.0F:aAmount<2048?5.0F:aAmount<4096?6.0F:aAmount<8192?7.0F:8.0F; int tX=xCoord, tY=yCoord, tZ=zCoord; worldObj.setBlock(tX, tY, tZ, 0); worldObj.createExplosion(null, tX+0.5, tY+0.5, tZ+0.5, tStrength, true);}
	public int getTier(int aValue) {return aValue<=32?1:aValue<=128?2:aValue<=512?3:aValue<=2048?4:aValue<=8192?5:6;}
    public int getTexture(int aSide, int aMeta) {if (mMetaTileEntity != null) return mMetaTileEntity.getTextureIndex(aSide, mFacing); return 0;}
    
	@Override
	public int addEnergy(int aEnergy) {
		if (aEnergy > 0)
			increaseStoredEnergy(aEnergy);
		else
			decreaseStoredEnergy(-aEnergy, true);
		return getStored();
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
}
