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

import net.minecraft.block.Block;
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
public class BaseMetaTileEntity extends TileEntity implements ITankContainer, IEnergyStorage, IWrenchable, ISidedInventory, IEnergySink, IEnergySource, IGregTechDeviceInformation, IMachineBlockUpdateable, INetworkDataProvider, INetworkUpdateListener, IDigitalChest {
	public MetaTileEntity mMetaTileEntity;
	public int mRSEnergyCells = 0, mOverclockers = 0, mTransformers = 0, mLiBatteries = 0, mBatteries = 0, mDisplayErrorCode = 0, mStoredMJ = 0, mStoredEnergy = 0;
	public long mTickTimer = 0;
	public boolean mMJConverter = false, mInventoryChanged = false, mActive = false, mRedstone = false, oRedstone = false;
	public String mOwnerName = "";
	
	private boolean mIsAddedToEnet = false, oActive = false, mNeedsUpdate = true, mSendClientData = true, mReleaseEnergy = false;
	private int mFacing = -1, oFacing = -1, oOutput = 0, oX = 0, oY = 0, oZ = 0, mID = 0;
	
	public BaseMetaTileEntity() {
		
	}
	
	@Override
    public void writeToNBT(NBTTagCompound aNBT) {
    	super.writeToNBT(aNBT);
        aNBT.setInteger	("mID"				, mID);
        aNBT.setInteger	("mStoredMJ"		, mStoredMJ);
        aNBT.setInteger	("mStoredEnergy"	, mStoredEnergy);
        aNBT.setByte	("mOverclockers"	, (byte)mOverclockers);
        aNBT.setByte	("mTransformers"	, (byte)mTransformers);
        aNBT.setByte	("mLiBatteries"		, (byte)mLiBatteries);
        aNBT.setByte	("mRSEnergyCells"	, (byte)mRSEnergyCells);
        aNBT.setByte	("mBatteries"		, (byte)mBatteries);
        aNBT.setShort	("mFacing"			, (short)mFacing);
        aNBT.setString	("mOwnerName"		, mOwnerName);
    	aNBT.setBoolean	("mMJConverter"		, mMJConverter);
    	aNBT.setBoolean	("mActive"			, mActive);
    	aNBT.setBoolean	("mRedstone"		, mRedstone);
    	
    	if (hasValidMetaTileEntity()) {
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
            
    		try {
    			mMetaTileEntity.saveNBTData(aNBT);
    		} catch(Throwable e) {
    			System.err.println("Encountered CRITICAL ERROR while saving MetaTileEntity, the Chunk whould've been corrupted by now, but I prevented that. Please report immidietly to GregTech Intergalactical!!!");
    			e.printStackTrace();
    		}
    	}
    }
	
	@Override
	public void readFromNBT(NBTTagCompound aNBT) {
		super.readFromNBT(aNBT);
		setInitialValuesAsNBT(aNBT);
    }
	
	public void setInitialValuesAsNBT(NBTTagCompound aNBT) {
		if (aNBT == null) aNBT = new NBTTagCompound();
        mID				= aNBT.getInteger	("mID");
        mStoredMJ		= aNBT.getInteger	("mStoredMJ");
        mStoredEnergy	= aNBT.getInteger	("mStoredEnergy");
        mOverclockers	= aNBT.getByte		("mOverclockers");
        mTransformers	= aNBT.getByte		("mTransformers");
        mLiBatteries	= aNBT.getByte		("mLiBatteries");
        mBatteries		= aNBT.getByte		("mBatteries");
        mFacing			= aNBT.getShort		("mFacing");
        mOwnerName		= aNBT.getString	("mOwnerName");
        mMJConverter	= aNBT.getBoolean	("mMJConverter");
    	mActive			= aNBT.getBoolean	("mActive");
    	mRedstone		= aNBT.getBoolean	("mRedstone");
    	
    	if (mID != 0 && createNewMetatileEntity(mID)) {
            NBTTagList tItemList = aNBT.getTagList("Inventory");
            for (int i = 0; i < tItemList.tagCount(); i++) {
                NBTTagCompound tTag = (NBTTagCompound)tItemList.tagAt(i);
                int tSlot = tTag.getInteger("IntSlot");
                if (tSlot >= 0 && tSlot < mMetaTileEntity.mInventory.length) {
                	mMetaTileEntity.mInventory[tSlot] = ItemStack.loadItemStackFromNBT(tTag);
                }
            }
            
    		try {
    			mMetaTileEntity.loadNBTData(aNBT);
        	} catch(Throwable e) {
        		System.err.println("Encountered Exception while loading MetaTileEntity, the Server should've crashed now, but I prevented that. Please report immidietly to GregTech Intergalactical!!!");
        		e.printStackTrace();
        	}
		}
    	
    	issueTextureUpdate();
	}
	
	public boolean createNewMetatileEntity(int aID) {
		if (aID < 16 || aID >= GregTech_API.MAXIMUM_METATILE_IDS || GregTech_API.mMetaTileList[aID] == null) {
			System.err.println("MetaID " + aID + " not loadable => locking TileEntity!");
		} else {
			if (aID != 0) {
				if (hasValidMetaTileEntity()) {
					mMetaTileEntity.inValidate();
					mMetaTileEntity.mBaseMetaTileEntity = null;
				}
				mMetaTileEntity = GregTech_API.mMetaTileList[aID].newMetaEntity(this);
				mMetaTileEntity.mBaseMetaTileEntity = this;
	    		mTickTimer = 0;
				mID = aID;
				return true;
			}
		}
		return false;
	}
	
	public void updateStatus() {}
	
	@Override
    public void updateEntity() {
    	if (isInvalid()) return;
    	
    	if (!hasValidMetaTileEntity()) {
    		if (mMetaTileEntity == null) {
	    		return;
	    	} else {
	    		mMetaTileEntity.mBaseMetaTileEntity = this;
	    	}
    	}
    	
    	try {
    	
    	mTickTimer++;
    	
	    if (mTickTimer==20) {
    		if (mFacing == -1) mFacing = 0;
	    	if (isServerSide()) {
	    		worldObj.addBlockEvent(xCoord, yCoord, zCoord, getOffsetBlockID(0, 0, 0), 3, mID);
	    		issueTextureUpdate();
	    	}
    		mMetaTileEntity.onFirstTick();
    		NetworkHelper.updateTileEntityField(this, "mOwnerName");
	    }
    	
    	if (mTickTimer>20 && hasValidMetaTileEntity()) {
    		mMetaTileEntity.onPreTick();
        	
    	    if (isClientSide()) {
    	    	if (mNeedsUpdate) {
    			    worldObj.markBlockForRenderUpdate(xCoord, yCoord, zCoord);
    			    mNeedsUpdate = false;
    	    	}
    	    }
    	    
    	    if (isServerSide()) {
    	    	if (mTickTimer == 50) {
    	    		worldObj.notifyBlocksOfNeighborChange(xCoord, yCoord, zCoord, getOffsetBlockID(0, 0, 0));
    	    	}
    	    	
        	    if (mTickTimer%600==50) {
	            	mSendClientData = true;
        	    }
        	    
    	    	if (mActive != oActive) {
    	    		oActive = mActive;
    	    		issueTextureUpdate();
    	    	}
    	    	
    	    	if (mRedstone != oRedstone) {
    		    	worldObj.notifyBlocksOfNeighborChange(xCoord, yCoord, zCoord, getOffsetBlockID(0, 0, 0));
    	    		oRedstone = mRedstone;
    	    		issueTextureUpdate();
    	    	}
    	    	
    	    	if (mTickTimer > 30 && (mMetaTileEntity.isEnetOutput()||mMetaTileEntity.isEnetInput()) && !isAddedToEnergyNet()) {
    	    		mIsAddedToEnet = GT_ModHandler.addTileToEnet(this);
    	    	}
    	    	
    	    	if (xCoord != oX || yCoord != oY || zCoord != oZ) {
    	    		oX = xCoord;
    	    		oY = yCoord;
    	    		oZ = zCoord;
    		    	if (mIsAddedToEnet) GT_ModHandler.removeTileFromEnet(this);
    		    	mIsAddedToEnet = false;
    		    	issueTextureUpdate();
    	    	}
    	    	
    		    if (mFacing != oFacing) {
    		    	oFacing = mFacing;
    		    	if (isAddedToEnergyNet()) GT_ModHandler.removeTileFromEnet(this);
    		    	mIsAddedToEnet = false;
    		    	issueTextureUpdate();
    		    }
    		    
    		    if (getOutput() != oOutput) {
    		    	oOutput = getOutput();
    		    	if (isAddedToEnergyNet()) GT_ModHandler.removeTileFromEnet(this);
    		    	mIsAddedToEnet = false;
    		    }
    		    
    		    if (isAddedToEnergyNet() && GT_Mod.instance.mMachineFireExplosions && getRandomNumber(1000) == 0) {
    		    	switch (worldObj.rand.nextInt(6)) {
    		    	case 0:
    		    		if (getOffsetBlockID(+1, 0, 0) == Block.fire.blockID) doEnergyExplosion();
    		    		break;
    		    	case 1:
    		    		if (getOffsetBlockID(-1, 0, 0) == Block.fire.blockID) doEnergyExplosion();
    		    		break;
    		    	case 2:
    		    		if (getOffsetBlockID(0, +1, 0) == Block.fire.blockID) doEnergyExplosion();
    		    		break;
    		    	case 3:
    		    		if (getOffsetBlockID(0, -1, 0) == Block.fire.blockID) doEnergyExplosion();
    		    		break;
    		    	case 4:
    		    		if (getOffsetBlockID(0, 0, +1) == Block.fire.blockID) doEnergyExplosion();
    		    		break;
    		    	case 5:
    		    		if (getOffsetBlockID(0, 0, -1) == Block.fire.blockID) doEnergyExplosion();
    		    		break;
    		    	}
    		    }
    		    
    	        if (isAddedToEnergyNet() && mMetaTileEntity.isEnetOutput() && mMetaTileEntity.getEUVar() >= Math.max(getOutput(), mMetaTileEntity.getMinimumStoredEU()) && getOutput() > 0) {
    	            try {
    	            	EnergyNet tEnergyNet = EnergyNet.getForWorld(worldObj);
    	            	if (tEnergyNet != null) {
    	            		for (int i = 0; i < getMaxOutputPackets(); i++)
    	            			setStoredEU(mMetaTileEntity.getEUVar() + tEnergyNet.emitEnergyFrom(this, getOutput()) - getOutput());
    	            	}
    	            } catch(Exception e) {
    	            	
    	            }
    	        }
    	        
    	        for (int j = 0; j < mMetaTileEntity.getInputTier(); j++) {
    		        for (int i = mMetaTileEntity.dechargerSlotStartIndex(); i < mMetaTileEntity.dechargerSlotCount()+mMetaTileEntity.dechargerSlotStartIndex(); i++) {
    			        if (mMetaTileEntity.mInventory[i] != null && demandsEnergy()>0 && mMetaTileEntity.mInventory[i].getItem() instanceof IElectricItem && ((IElectricItem)mMetaTileEntity.mInventory[i].getItem()).canProvideEnergy())
    			        	increaseStoredEU(ElectricItem.discharge(mMetaTileEntity.mInventory[i], getCapacity() - mMetaTileEntity.getEUVar(), mMetaTileEntity.getInputTier(), false, false), true);
    		        }
    	        }
    	        
        	    for (int j = 0; j < mMetaTileEntity.getOutputTier(); j++) {
    		        for (int i = mMetaTileEntity.rechargerSlotStartIndex(); i < mMetaTileEntity.rechargerSlotCount()+mMetaTileEntity.rechargerSlotStartIndex(); i++) {
    			        if (mMetaTileEntity.getEUVar() > 0 && mMetaTileEntity.mInventory[i] != null && mMetaTileEntity.mInventory[i].getItem() instanceof IElectricItem)
    			        	decreaseStoredEU(ElectricItem.charge(mMetaTileEntity.mInventory[i], mMetaTileEntity.getEUVar(), mMetaTileEntity.getOutputTier(), false, false), true);
    		        }
    	        }
        	    
        	    updateStatus();
        	    
    		    if (mSendClientData) {
    	    		worldObj.addBlockEvent(xCoord, yCoord, zCoord, getOffsetBlockID(0, 0, 0), 3, mID);
    	    		issueTextureUpdate();
	    	    	mSendClientData = false;
    	    	}
    		    
    		    if (mNeedsUpdate) {
    	    		worldObj.addBlockEvent(xCoord, yCoord, zCoord, getOffsetBlockID(0, 0, 0), 0, (mFacing&7) | (mActive?8:0) | (mRedstone?16:0) | (mMetaTileEntity.getUpdateData() << 5));
    			    mNeedsUpdate = false;
    	    	}
    	    }

    	    mMetaTileEntity.onPostTick();
        	
        	onInventoryChanged();
    	}
    	
    	} catch(Throwable e) {
    		System.err.println("Encountered Exception while ticking TileEntity, the Game should've crashed now, but I prevented that. Please report immidietly to GregTech Intergalactical!!!");
    		e.printStackTrace();
    	}
    	
    	mInventoryChanged = false;
    }
    
    @Override
    public void receiveClientEvent(int aEventID, int aValue) {
		super.receiveClientEvent(aEventID, aValue);
		
		if (hasValidMetaTileEntity()) {
			try {
				mMetaTileEntity.receiveClientEvent(aEventID, aValue);
			} catch(Throwable e) {
				System.err.println("Encountered Exception while receiving Data from the Server, the Client should've been crashed by now, but I prevented that. Please report immidietly to GregTech Intergalactical!!!");
				e.printStackTrace();
			}
		}
		
		if (isClientSide()) {
			switch(aEventID) {
			case 0:
				mFacing   =   aValue& 7;
				mActive   = ((aValue& 8) != 0);
				mRedstone = ((aValue&16) != 0);
				if (hasValidMetaTileEntity()) mMetaTileEntity.onValueUpdate((short)(aValue>>5));
				issueTextureUpdate();
				break;
			case 3:
				if (mID == 0 && aValue > 0) {
			    	mID = aValue;
			    	createNewMetatileEntity(mID);
			    	issueTextureUpdate();
				}
		    	break;
			case 4:
				if (hasValidMetaTileEntity()) mMetaTileEntity.doSound(aValue, xCoord+0.5, yCoord+0.5, zCoord+0.5);
		    	break;
			case 5:
				if (hasValidMetaTileEntity()) mMetaTileEntity.startSoundLoop(aValue, xCoord+0.5, yCoord+0.5, zCoord+0.5);
		    	break;
			case 6:
				if (hasValidMetaTileEntity()) mMetaTileEntity.stopSoundLoop(aValue, xCoord+0.5, yCoord+0.5, zCoord+0.5);
		    	break;
			}
		} else {
			if (aEventID == 3 && aValue == 0) issueTextureUpdate();
		}
	}
    
	public ArrayList<String> getDebugInfo(EntityPlayer aPlayer, int aLogLevel) {
		ArrayList<String> tList = new ArrayList<String>();
		if (aLogLevel > 2) {
			tList.add("Meta-ID: " + mID + (hasValidMetaTileEntity()?" valid":" invalid") + (mMetaTileEntity==null?" MetaTileEntity == null!":" "));
		}
		if (aLogLevel > 1) {
			tList.add("Is" + (mMetaTileEntity.isAccessAllowed(aPlayer)?" ":" not ") + "accessible for you");
		}
		if (aLogLevel > 0) {
			if (getMJCapacity() > 0 && mMJConverter) tList.add(getStoredMJ() + " of " + getMJCapacity() + " MJ");
			tList.add("Machine is " + (mActive?"active":"inactive"));
			if (mOverclockers	> 0) tList.add(mOverclockers	+ " Overclocker Upgrades");
			if (mTransformers	> 0) tList.add(mTransformers	+ " Transformer Upgrades");
			if (mBatteries		> 0) tList.add(mBatteries		+ " Battery Upgrades");
			if (mLiBatteries	> 0) tList.add(mLiBatteries		+ " Lithium Battery Upgrades");
			if (mRSEnergyCells	> 0) tList.add(mRSEnergyCells	+ " Energy Cell Upgrades");
		}
		return mMetaTileEntity.getSpecialDebugInfo(aPlayer, aLogLevel, tList);
	}
	/**
	 * Causes a general Tecture update. Active-Status, Redstone-Boolean, Facing and a small value of your specified ExtraData (usually a secondary Facing) will get sent to the Client
	 */
	public void issueTextureUpdate() {mNeedsUpdate = true;}
	
	/**
	 * disconnects and reconnects TileEntity from E-net, f.e when Input/Output behaviour/facing changes
	 */
	public void issueEnetUpdate() {oOutput = -1;}
	
    public int getBlockID(int aX, int aY, int aZ) {return worldObj.getBlockId(aX, aY, aZ);}
    public int getOffsetBlockID(int aX, int aY, int aZ) {return getBlockID(xCoord+aX, yCoord+aY, zCoord+aZ);}
    public int getMetaID(int aX, int aY, int aZ) {return worldObj.getBlockMetadata(aX, aY, aZ);}
    public int getOffsetMetaID(int aX, int aY, int aZ) {return getMetaID(xCoord+aX, yCoord+aY, zCoord+aZ);}
    public TileEntity getTileEntity(int aX, int aY, int aZ) {return worldObj.getBlockTileEntity(aX, aY, aZ);}
    public TileEntity getOffsetTileEntity(int aX, int aY, int aZ) {return getTileEntity(xCoord+aX, yCoord+aY, zCoord+aZ);}
    public boolean isServerSide() {return !worldObj.isRemote;}
    public boolean isClientSide() {return  worldObj.isRemote;}
    public void openGUI(EntityPlayer aPlayer, int aID) {aPlayer.openGui(GT_Mod.instance, aID, worldObj, xCoord, yCoord, zCoord);}
    public void openGUI(EntityPlayer aPlayer, int aID, Object aMod) {aPlayer.openGui(aMod, aID, worldObj, xCoord, yCoord, zCoord);}
    public boolean getRedstone() {return worldObj.isBlockIndirectlyGettingPowered(xCoord, yCoord, zCoord);}
	public int getRandomNumber(int aRange) {return worldObj.rand.nextInt(aRange);}
    
	@Override public String getMainInfo() {if (hasValidMetaTileEntity()) return mMetaTileEntity.getMainInfo(); return "";}
	@Override public String getSecondaryInfo() {if (hasValidMetaTileEntity()) return mMetaTileEntity.getSecondaryInfo(); return "";}
	@Override public String getTertiaryInfo() {if (hasValidMetaTileEntity()) return mMetaTileEntity.getTertiaryInfo(); return "";}
	@Override public boolean isGivingInformation() {if (hasValidMetaTileEntity()) return mMetaTileEntity.isGivingInformation(); return false;}
	@Override public boolean wrenchCanSetFacing(EntityPlayer aPlayer, int aSide) {if (hasValidMetaTileEntity()) return mFacing != aSide && mMetaTileEntity.isFacingValid(aSide); return false;}
	@Override public short getFacing() {return (short)mFacing;}
	@Override public void setFacing(short aFacing) {if (hasValidMetaTileEntity() && mMetaTileEntity.isFacingValid(aFacing)) mFacing = aFacing; issueTextureUpdate(); onMachineBlockUpdate();}
	@Override public boolean wrenchCanRemove(EntityPlayer aPlayer) {if (hasValidMetaTileEntity() && mMetaTileEntity.ownerWrench()) return playerOwnsThis(aPlayer, true); return true;}
	@Override public float getWrenchDropRate() {if (hasValidMetaTileEntity() && mMetaTileEntity.isSimpleMachine()) return 1.0F; return 0.8F;}
	@Override public int getSizeInventory() {if (hasValidMetaTileEntity()) return mMetaTileEntity.getInvSize(); else return 0;}
	@Override public ItemStack getStackInSlot(int aIndex) {if (hasValidMetaTileEntity()) return mMetaTileEntity.mInventory[aIndex]; return null;}
	@Override public void setInventorySlotContents(int aIndex, ItemStack aStack) {mInventoryChanged = true; if (hasValidMetaTileEntity()) mMetaTileEntity.mInventory[aIndex] = aStack;}
	@Override public String getInvName() {if (GregTech_API.mMetaTileList[mID] != null) return GregTech_API.mMetaTileList[mID].mName; return "";}
	@Override public int getInventoryStackLimit() {return 64;}
	@Override public void openChest()  {if (hasValidMetaTileEntity()) mMetaTileEntity.onOpenGUI();}
	@Override public void closeChest() {if (hasValidMetaTileEntity()) mMetaTileEntity.onCloseGUI();}
	@Override public int getStartInventorySide(ForgeDirection aSide) {if (hasValidMetaTileEntity()) return mMetaTileEntity.getInvSideIndex(aSide, mFacing); return 0;}
	@Override public int getSizeInventorySide(ForgeDirection aSide) {if (hasValidMetaTileEntity()) return mMetaTileEntity.getInvSideLength(aSide, mFacing); return 0;}
	@Override public boolean isAddedToEnergyNet() {return mIsAddedToEnet;}
    @Override public boolean isUseableByPlayer(EntityPlayer aPlayer) {return hasValidMetaTileEntity() && playerOwnsThis(aPlayer, false) && mTickTimer>40 && getOffsetTileEntity(0, 0, 0) == this && aPlayer.getDistanceSq(xCoord + 0.5, yCoord + 0.5, zCoord + 0.5) < 64 && mMetaTileEntity.isAccessAllowed(aPlayer);}
    @Override public int getStored() {if (hasValidMetaTileEntity()) return Math.min(mMetaTileEntity.getEUVar(), getCapacity()); return 0;}
    @Override public void validate() {super.validate(); issueTextureUpdate(); mTickTimer = 0;}
    @Override public void invalidate() {if (hasValidMetaTileEntity()) {mMetaTileEntity.onRemoval(); mMetaTileEntity.inValidate(); mMetaTileEntity.mBaseMetaTileEntity = null;} if (isAddedToEnergyNet()) mIsAddedToEnet = !GT_ModHandler.removeTileFromEnet(this); super.invalidate();}
    @Override public boolean acceptsEnergyFrom(TileEntity aReceiver, Direction aDirection) {if (isInvalid()||mReleaseEnergy) return false; if (hasValidMetaTileEntity()) return mMetaTileEntity.isInputFacing((short)aDirection.toSideValue()); return false;}
    @Override public boolean emitsEnergyTo(TileEntity aReceiver, Direction aDirection) {if (isInvalid()||mReleaseEnergy) return mReleaseEnergy; if (hasValidMetaTileEntity()) return mMetaTileEntity.isOutputFacing((short)aDirection.toSideValue()); return false;}
    @Override public ItemStack getStackInSlotOnClosing(int slot) {ItemStack stack = getStackInSlot(slot); if (stack != null) setInventorySlotContents(slot, null); return stack;}
    @Override public ItemStack decrStackSize(int aIndex, int aAmount) {mInventoryChanged = true; ItemStack stack = getStackInSlot(aIndex); if (stack != null) {if (stack.stackSize <= aAmount) {if (hasValidMetaTileEntity() && mMetaTileEntity.setStackToZeroInsteadOfNull(aIndex)) stack.stackSize = 0; else setInventorySlotContents(aIndex, null);} else {stack = stack.splitStack(aAmount); if (stack.stackSize == 0) {if (hasValidMetaTileEntity() && !mMetaTileEntity.setStackToZeroInsteadOfNull(aIndex)) setInventorySlotContents(aIndex, null);}}} return stack;}
	@Override public int getMaxEnergyOutput() {if (mReleaseEnergy) return Integer.MAX_VALUE; return getOutput();}
	@Override public int demandsEnergy() {if (mReleaseEnergy || !hasValidMetaTileEntity()) return 0; return getCapacity() - getStored();}
	@Override public int injectEnergy(Direction directionFrom, int aAmount) {if (!hasValidMetaTileEntity()) return aAmount; if (aAmount > getMaxSafeInput()) {doExplosion(aAmount); return 0;} setStoredEU(mMetaTileEntity.getEUVar() + aAmount); return 0;}
	@Override public int getCapacity() {if (hasValidMetaTileEntity()) return mMetaTileEntity.maxEUStore() + mBatteries * 10000 + mLiBatteries * 100000; return 0;}
	@Override public int getOutput() {if (hasValidMetaTileEntity()) return mMetaTileEntity.maxEUOutput() * (mTransformers>0?(int)Math.pow(4, mTransformers-1):1); return 0;}
	@Override public void onMachineBlockUpdate() {if (hasValidMetaTileEntity()) mMetaTileEntity.onMachineBlockUpdate();}
	@Override public int fill(ForgeDirection from, LiquidStack resource, boolean doFill) {if (hasValidMetaTileEntity()) return mMetaTileEntity.fill(resource, doFill); return 0;}
	@Override public int fill(int tankIndex, LiquidStack resource, boolean doFill) {if (hasValidMetaTileEntity()) return mMetaTileEntity.fill(resource, doFill); return 0;}
	@Override public LiquidStack drain(ForgeDirection from, int maxDrain, boolean doDrain) {if (hasValidMetaTileEntity()) return mMetaTileEntity.drain(maxDrain, doDrain); return null;}
	@Override public LiquidStack drain(int tankIndex, int maxDrain, boolean doDrain) {if (hasValidMetaTileEntity()) return mMetaTileEntity.drain(maxDrain, doDrain); return null;}
	@Override public ILiquidTank[] getTanks(ForgeDirection direction) {if (hasValidMetaTileEntity() && mMetaTileEntity.getCapacity()>0) return new ILiquidTank[] {mMetaTileEntity}; return new ILiquidTank[] {};}
	@Override public ILiquidTank getTank(ForgeDirection direction, LiquidStack type) {if (hasValidMetaTileEntity() && mMetaTileEntity.getCapacity()>0 && (type == null || (type != null && type.isLiquidEqual(mMetaTileEntity.getLiquid())))) return mMetaTileEntity; return null;}
	@Override public void setStored(int aEU) {if (hasValidMetaTileEntity()) setStoredEU(aEU);}
	@Override public boolean isTeleporterCompatible(Direction side) {return false;}
	@Override public int getMaxSafeInput() {if (hasValidMetaTileEntity()) return mMetaTileEntity.maxEUInput()*(int)Math.pow(4, mTransformers); return Integer.MAX_VALUE;}
	
	public int getMetaTileID() {return mID;}
    public int getMaxOutputPackets() {if (hasValidMetaTileEntity()) return mMetaTileEntity.maxEUPulses()==1?mTransformers>0?4:1:mMetaTileEntity.maxEUPulses(); return 0;}
	public int getStoredEnergy() {if (hasValidMetaTileEntity()) return Math.max((getStoredMJ()*5)/2, getStored()); return 0;}
    public int getStoredMJ() {if (hasValidMetaTileEntity()) return Math.min(mMetaTileEntity.getMJVar(), getMJCapacity()); return 0;}
    public int getMJCapacity() {if (hasValidMetaTileEntity()) return mMetaTileEntity.maxMJStore() + mRSEnergyCells * 100000; return 0;}
	public String getDescription() {if (hasValidMetaTileEntity()) return mMetaTileEntity.getDescription(); return "";}
	public boolean isActive() {return mActive;}
	public boolean isValidSlot(int aIndex) {if (hasValidMetaTileEntity()) return mMetaTileEntity.isValidSlot(aIndex); return false;}
    public boolean playerOwnsThis(EntityPlayer aPlayer, boolean aCheckPrecicely) {if (!hasValidMetaTileEntity()) return false; if (aCheckPrecicely || mMetaTileEntity.ownerControl() || mOwnerName.equals("")) if (mOwnerName.equals("")&&isServerSide()) mOwnerName = aPlayer.username; else if (!aPlayer.username.equals("Player") && !mOwnerName.equals("Player") && !mOwnerName.equals(aPlayer.username)) return false; return true;}
    public boolean privateAccess() {if (!hasValidMetaTileEntity()) return false; return mMetaTileEntity.ownerControl();}
    public boolean unbreakable() {if (!hasValidMetaTileEntity()) return false; return mMetaTileEntity.unbreakable();}
    public boolean setStoredEU(int aEnergy) {if (!hasValidMetaTileEntity()) return false; if (aEnergy < 0) aEnergy = 0; mMetaTileEntity.setEUVar(aEnergy); return true;}
    public boolean setStoredMJ(int aEnergy) {if (!hasValidMetaTileEntity()) return false; if (aEnergy < 0) aEnergy = 0; mMetaTileEntity.setMJVar(aEnergy); return true;}
    public boolean increaseStoredEU(int aEnergy, boolean aIgnoreTooMuchEnergy) {if (!hasValidMetaTileEntity()) return false; if (mMetaTileEntity.getEUVar() < getCapacity()		|| aIgnoreTooMuchEnergy) {setStoredEU(mMetaTileEntity.getEUVar() + aEnergy); return true;} return false;}
    public boolean increaseStoredMJ(int aEnergy, boolean aIgnoreTooMuchEnergy) {if (!hasValidMetaTileEntity()) return false; if (mMetaTileEntity.getMJVar() < getMJCapacity()	|| aIgnoreTooMuchEnergy) {setStoredMJ(mMetaTileEntity.getMJVar() + aEnergy); return true;} return false;}
	public boolean decreaseStoredEU(int aEnergy, boolean aIgnoreTooLessEnergy) {if (!hasValidMetaTileEntity()) return false; if (mMetaTileEntity.getEUVar() - aEnergy >= 0		|| aIgnoreTooLessEnergy) {mMetaTileEntity.setEUVar(mMetaTileEntity.getEUVar() - aEnergy); if (mMetaTileEntity.getEUVar() < 0) {setStoredEU(0); return false;} return true;} return false;}
	public boolean decreaseStoredMJ(int aEnergy, boolean aIgnoreTooLessEnergy) {if (!hasValidMetaTileEntity()) return false; if (mMetaTileEntity.getMJVar() - aEnergy >= 0		|| aIgnoreTooLessEnergy) {mMetaTileEntity.setMJVar(mMetaTileEntity.getMJVar() - aEnergy); if (mMetaTileEntity.getMJVar() < 0) {setStoredMJ(0); return false;} return true;} return false;}
	public boolean decreaseStoredEnergy(int aEnergy, boolean aIgnoreTooLessEnergy) {if (!hasValidMetaTileEntity()) return false; return decreaseStoredMJ(aEnergy, false) || decreaseStoredEU(aEnergy, aIgnoreTooLessEnergy) || decreaseStoredMJ(aEnergy, true);}
	public void doEnergyExplosion() {if (getCapacity() > 0 && getStoredEnergy() >= getCapacity()/5) doExplosion(getOutput()*(getStoredEnergy() >= getCapacity()?4:getStoredEnergy() >= getCapacity()/2?2:1));}
	public int getTier(int aValue) {return aValue<=32?1:aValue<=128?2:aValue<=512?3:aValue<=2048?4:aValue<=8192?5:6;}
    public int getTexture(int aSide, int aMeta) {if (hasValidMetaTileEntity()) return mMetaTileEntity.getTextureIndex(aSide, mFacing, mActive, mRedstone); return 0;}
    public boolean hasValidMetaTileEntity() {return mMetaTileEntity != null && mMetaTileEntity.mBaseMetaTileEntity == this;}
    
    public void doExplosion(int aAmount) {
    	if (isAddedToEnergyNet() && GT_Mod.instance.mMachineWireFire) {
	        try {
	        	mReleaseEnergy = true;
	        	
	        	GT_ModHandler.removeTileFromEnet(this);
	        	GT_ModHandler.addTileToEnet(this);
	        	
	        	EnergyNet tEnergyNet = EnergyNet.getForWorld(worldObj);
	        	if (tEnergyNet != null) {
	        		tEnergyNet.emitEnergyFrom(this,   32);
	        		tEnergyNet.emitEnergyFrom(this,  128);
	        		tEnergyNet.emitEnergyFrom(this,  512);
	        		tEnergyNet.emitEnergyFrom(this, 2048);
	        		tEnergyNet.emitEnergyFrom(this, 8192);
	        	}
	        } catch(Exception e) {}
    	}
    	mReleaseEnergy = false;
    	if (hasValidMetaTileEntity()) mMetaTileEntity.onExplosion();
    	float tStrength = aAmount<10?1.0F:aAmount<32?2.0F:aAmount<128?3.0F:aAmount<512?4.0F:aAmount<2048?5.0F:aAmount<4096?6.0F:aAmount<8192?7.0F:8.0F;
    	int tX=xCoord, tY=yCoord, tZ=zCoord;
    	worldObj.setBlock(tX, tY, tZ, 0);
    	worldObj.createExplosion(null, tX+0.5, tY+0.5, tZ+0.5, tStrength, true);
    }
	
	@Override
	public int addEnergy(int aEnergy) {
		if (!hasValidMetaTileEntity()) return 0;
		if (aEnergy > 0)
			increaseStoredEU(aEnergy, true);
		else
			decreaseStoredEU(-aEnergy, true);
		return mMetaTileEntity.getEUVar();
	}
	
	@Override
	public void onNetworkUpdate(String field) {
		
	}
	
	@Override
	public List<String> getNetworkedFields() {
		ArrayList<String> rList = new ArrayList<String>();
		rList.add("mOwnerName");
		return rList;
	}
	
	@Override
	public ItemStack getWrenchDrop(EntityPlayer aPlayer) {
		ItemStack rStack = new ItemStack(GT_Mod.instance.mBlocks[1], 1, mID);
		NBTTagCompound tNBT = new NBTTagCompound();
    	if (mMJConverter	) tNBT.setBoolean("mMJConverter"	, mMJConverter);
		if (mTransformers	> 0) tNBT.setByte("mTransformers"	, (byte)mTransformers);
		if (mOverclockers	> 0) tNBT.setByte("mOverclockers"	, (byte)mOverclockers);
		if (mLiBatteries	> 0) tNBT.setByte("mLiBatteries"	, (byte)mLiBatteries);
		if (mRSEnergyCells	> 0) tNBT.setByte("mRSEnergyCells"	, (byte)mRSEnergyCells);
		if (mBatteries		> 0) tNBT.setByte("mBatteries"		, (byte)mBatteries);
		if (hasValidMetaTileEntity()) mMetaTileEntity.setItemNBT(tNBT);
		if (!tNBT.hasNoTags()) rStack.setTagCompound(tNBT);
		return rStack;
	}
	
	public int getUpgradeCount() {
		return (mMJConverter?1:0)+mTransformers+mOverclockers+mBatteries+mLiBatteries+mRSEnergyCells;
	}
	
	public void onRightclick(EntityPlayer aPlayer) {
		issueTextureUpdate();
		mSendClientData = true;
		
		if (getUpgradeCount() < 16 && hasValidMetaTileEntity() && aPlayer.inventory.getCurrentItem() != null) {
			if (!mMJConverter && getMJCapacity() > 0 && aPlayer.inventory.getCurrentItem().isItemEqual(GregTech_API.getGregTechItem(3, 1, 25))) {
				mMJConverter = true;
				if (!aPlayer.capabilities.isCreativeMode) aPlayer.inventory.getCurrentItem().stackSize--;
				return;
			}
			if (mMetaTileEntity.isOverclockerUpgradable() && mOverclockers < 4 && aPlayer.inventory.getCurrentItem().isItemEqual(GT_ModHandler.getIC2Item("overclockerUpgrade", 1))) {
				mOverclockers++;
				if (!aPlayer.capabilities.isCreativeMode) aPlayer.inventory.getCurrentItem().stackSize--;
				return;
			}
			if (mMetaTileEntity.isTransformerUpgradable()) {
				if (getMaxSafeInput() < 512 && getOutput() < 128){
					if (aPlayer.inventory.getCurrentItem().isItemEqual(GT_ModHandler.getIC2Item("transformerUpgrade", 1))) {
						mTransformers++;
						if (!aPlayer.capabilities.isCreativeMode) aPlayer.inventory.getCurrentItem().stackSize--;
						return;
					}
				} else if (getMaxSafeInput() < 8192 && getOutput() < 2048) {
					if (aPlayer.inventory.getCurrentItem().isItemEqual(GregTech_API.getGregTechItem(3, 1, 27))) {
						mTransformers++;
						if (!aPlayer.capabilities.isCreativeMode) aPlayer.inventory.getCurrentItem().stackSize--;
						return;
					}
				}
			}
			if (mMetaTileEntity.isBatteryUpgradable() && aPlayer.inventory.getCurrentItem().isItemEqual(GT_ModHandler.getIC2Item("energyStorageUpgrade", 1))) {
				mBatteries++;
				if (!aPlayer.capabilities.isCreativeMode) aPlayer.inventory.getCurrentItem().stackSize--;
				return;
			}
			if (mMetaTileEntity.isBatteryUpgradable() && aPlayer.inventory.getCurrentItem().isItemEqual(GregTech_API.getGregTechItem(3, 1, 26))) {
				mLiBatteries++;
				if (!aPlayer.capabilities.isCreativeMode) aPlayer.inventory.getCurrentItem().stackSize--;
				return;
			}
			if (mMetaTileEntity.isBatteryUpgradable() && mMJConverter && aPlayer.inventory.getCurrentItem().isItemEqual(GregTech_API.getGregTechItem(3, 1, 28))) {
				mRSEnergyCells++;
				if (!aPlayer.capabilities.isCreativeMode) aPlayer.inventory.getCurrentItem().stackSize--;
				return;
			}
		}
		
		try {
			if (aPlayer != null && hasValidMetaTileEntity() && mID > 15) mMetaTileEntity.onRightclick(aPlayer);
    	} catch(Throwable e) {
    		System.err.println("Encountered Exception while rightclicking TileEntity, the Game should've crashed now, but I prevented that. Please report immidietly to GregTech Intergalactical!!!");
    		e.printStackTrace();
    	}
	}
	
	public void onLeftclick(EntityPlayer aPlayer) {
		issueTextureUpdate();
		mSendClientData = true;
		
		try {
			if (aPlayer != null && hasValidMetaTileEntity() && mID > 15) mMetaTileEntity.onLeftclick(aPlayer);
    	} catch(Throwable e) {
    		System.err.println("Encountered Exception while leftclicking TileEntity, the Game should've crashed now, but I prevented that. Please report immidietly to GregTech Intergalactical!!!");
    		e.printStackTrace();
    	}
	}
	
	@Override
	public boolean isDigitalChest() {
		if (hasValidMetaTileEntity()) return mMetaTileEntity.isDigitalChest();
		return false;
	}
	
	@Override
	public ItemStack[] getStoredItemData() {
		if (hasValidMetaTileEntity()) return mMetaTileEntity.getStoredItemData();
		return null;
	}
	
	@Override
	public void setItemCount(int aCount) {
		if (hasValidMetaTileEntity()) mMetaTileEntity.setItemCount(aCount);
	}
	
	@Override
	public int getMaxItemCount() {
		if (hasValidMetaTileEntity()) return mMetaTileEntity.getMaxItemCount();
		return 0;
	}
}
