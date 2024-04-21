package gregtechmod.api;

import gregtechmod.common.GT_LanguageManager;

import java.util.ArrayList;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.ForgeDirection;
import net.minecraftforge.liquids.ILiquidTank;
import net.minecraftforge.liquids.LiquidStack;

/**
 * Do not include this Class in a Copy of my API. It will very likely cause incompatiblities!
 * 
 * Extend this Class to add a new Metamachine
 * Call the Constructor with the desired ID at the load-phase (not preload and also not postload!)
 * Implement the newMetaEntity-Method to return a new ready instance of your MetaTileEntity
 */
public abstract class MetaTileEntity implements ILiquidTank {
	public String mName, mRegionalName;
	public int mID;
	
	public BaseMetaTileEntity mBaseMetaTileEntity;
	
	/**
	 * The Inventory of the MetaTileEntity. Amount of Slots can be larger than 256. HAYO!
	 */
	public ItemStack[] mInventory = new ItemStack[getInvSize()];
	
	/**
	 * This registers your Machine at the List.
	 * Use only ID's larger than 255, because i reserved these ones.
	 * @param aID the ID
	 * @example for Constructor overload.
	 * 
	 * 	public GT_MetaTileEntity_EBench(int aID, String mName, String mNameRegional) {
	 * 		super(aID, mName, mNameRegional);
	 * 	}
	 */
	public MetaTileEntity(int aID, String aBasicName, String aRegionalName) {
		if (GregTech_API.mMetaTileList[aID] == null) {
			GregTech_API.mMetaTileList[aID] = this;
		} else {
			throw new IllegalArgumentException("MetaMachine-Slot Nr. " + aID + " is already occupied!");
		}
		mID = aID;
		mName = aBasicName.replaceAll(" ", "_");
		mRegionalName = aRegionalName;
		mBaseMetaTileEntity = GregTech_API.constructBaseMetaTileEntity();
		mBaseMetaTileEntity.mMetaTileEntity = this;
		GT_LanguageManager.addStringLocalization("tile.BlockMetaID_Machine." + mName + ".name", mRegionalName);
	}
	
	/**
	 * This is the normal Constructor.
	 */
	public MetaTileEntity() {}
	
	/**
	 * @param aTileEntity is just because the internal Variable "mBaseMetaTileEntity" is set after this Call.
	 * @return a newly created and ready MetaTileEntity
	 */
	public abstract MetaTileEntity newMetaEntity(BaseMetaTileEntity aTileEntity);
	
	/**
	 * ^= writeToNBT
	 */
	public abstract void saveNBTData(NBTTagCompound aNBT);
	
	/**
	 * ^= readFromNBT
	 */
	public abstract void loadNBTData(NBTTagCompound aNBT);
	
	/**
	 * Adds the NBT-Information to the ItemStack, when being wrenched
	 * Used to store Machine specific Upgrade Data.
	 */
	public void setItemNBT(NBTTagCompound aNBT) {}
	
	/**
	 * From ISidedInventory
	 */
	public abstract int getInvSideIndex(ForgeDirection aSide, int aFacing);
	
	/**
	 * From ISidedInventory
	 */
	public abstract int getInvSideLength(ForgeDirection aSide, int aFacing);
	
	/**
	 * Amount of all InventorySlots
	 */
	public abstract int getInvSize();
	
	/**
	 * Index of the Texture
	 */
	public abstract int getTextureIndex(int aSide, int aFacing, boolean aActive, boolean aRedstone);

	/**
	 * get a small Description
	 */
	protected abstract String getDescription();
	
	public void inValidate() {}
	public void onExplosion() {}
	public void onFirstTick() {}
	public void onPreTick() {}
	public void onPostTick() {}
	public void onRemoval() {}
	public void onOpenGUI() {}
	public void onCloseGUI() {}
	public void onRightclick(EntityPlayer aPlayer) {}
	public void onLeftclick(EntityPlayer aPlayer) {}
	public void onValueUpdate(short aValue) {}
	public short getUpdateData() {return 0;}
	
	/**
	 * Only called Client Side
	 */
    public void doSound(int aIndex, double aX, double aY, double aZ) {}
    public void startSoundLoop(int aIndex, double aX, double aY, double aZ) {}
    public void stopSoundLoop(int aIndex, double aX, double aY, double aZ) {}
	
    /**
     * Send the Event for the Sound Triggers, only usable Server Side
     */
    public void sendSound(int aIndex) {
    	mBaseMetaTileEntity.worldObj.addBlockEvent(mBaseMetaTileEntity.xCoord, mBaseMetaTileEntity.yCoord, mBaseMetaTileEntity.zCoord, mBaseMetaTileEntity.worldObj.getBlockId(mBaseMetaTileEntity.xCoord, mBaseMetaTileEntity.yCoord, mBaseMetaTileEntity.zCoord), 4, aIndex);
    }
    
    public void sendLoopStart(int aIndex) {
    	mBaseMetaTileEntity.worldObj.addBlockEvent(mBaseMetaTileEntity.xCoord, mBaseMetaTileEntity.yCoord, mBaseMetaTileEntity.zCoord, mBaseMetaTileEntity.worldObj.getBlockId(mBaseMetaTileEntity.xCoord, mBaseMetaTileEntity.yCoord, mBaseMetaTileEntity.zCoord), 5, aIndex);
    }
    
    public void sendLoopEnd(int aIndex) {
    	mBaseMetaTileEntity.worldObj.addBlockEvent(mBaseMetaTileEntity.xCoord, mBaseMetaTileEntity.yCoord, mBaseMetaTileEntity.zCoord, mBaseMetaTileEntity.worldObj.getBlockId(mBaseMetaTileEntity.xCoord, mBaseMetaTileEntity.yCoord, mBaseMetaTileEntity.zCoord), 6, aIndex);
    }
    
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
     * @return the amount of EU/t, which can be accepted by this Device before it explodes.
     */
    public int maxEUInput()  {return 0;}
    
    /**
     * @return the amount of EU/t, which can be outputted by this Device.
     */
    public int maxEUOutput() {return 0;}
    
    /**
     * @return the amount of E-net Impulses of the maxEUOutput size, which can be outputted by this Device.
     * Default is 1 Pulse, this shouldn't be set to smaller Values than 1, as it won't output anything in that Case!
     */
    public int maxEUPulses()  {return 1;}
    
    /**
     * @return true if that Side is an Output.
     */
    public boolean isOutputFacing(short aSide) {return false;}
    
    /**
     * @return true if that Side is an Input.
     */
    public boolean isInputFacing(short aSide) {return false;}
    
    /**
     * @param aFacing
     * @return if aFacing would be a valid Facing for this Device
     */
    public boolean isFacingValid(int aFacing) {return false;}
    
    /**
     * @return true if the Machine can be accessed
     */
    public boolean isAccessAllowed(EntityPlayer aPlayer) {return false;}
    
    /**
     * @return if aIndex is a valid Slot. false for things like HoloSlots. Is used for determining if an Item is dropped upon Blockdestruction
     */
    public boolean isValidSlot(int aIndex) {return true;}
    
    /**
     * @return if aIndex can be set to Zero stacksize, when being removed.
     */
    public boolean setStackToZeroInsteadOfNull(int aIndex) {
    	return false;
    }
    
    /**
     * This is used to set the internal Energy to the given Parameter. I use this for the IDSU.
     */
	public void setEUVar(int aEU) {
		mBaseMetaTileEntity.mStoredEnergy = aEU;
	}
	
    /**
     * This is used to get the internal Energy. I use this for the IDSU.
     */
	public int getEUVar() {
		return mBaseMetaTileEntity.mStoredEnergy;
	}

    /**
     * This is used to set the internal Energy to the given Parameter. I use this for the IDSU.
     */
	public void setMJVar(int aMJ) {
		mBaseMetaTileEntity.mStoredMJ = aMJ;
	}
	
    /**
     * This is used to get the internal Energy. I use this for the IDSU.
     */
	public int getMJVar() {
		return mBaseMetaTileEntity.mStoredMJ;
	}

    /**
     * @return the amount of EU, which can be stored in this Device. Default is 0 EU.
     */
    public int maxMJStore()  {return 0;}
    
	/**
	 * @return the amount of EU, which this Device stores before starting to emit Energy.
	 */
	public int getMinimumStoredEU() {
		return 0;
	}
	
    /**
     * Determines the Tier of the Machine, used for de-charging Tools.
     */
    public int getInputTier() {
    	return mBaseMetaTileEntity.getTier(mBaseMetaTileEntity.getMaxSafeInput());
    }
    
    /**
     * Determines the Tier of the Machine, used for charging Tools.
     */
    public int getOutputTier() {
    	return mBaseMetaTileEntity.getTier(mBaseMetaTileEntity.getOutput());
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
     * gets if Protected from other Players Wrenches or not.
     */
    public boolean ownerWrench() {
    	return ownerControl();
    }
    
    /**
     * gets if it has hardness -1 and so is only breakable via Wrench (just to prevent accidently breaking the Blocks)
     */
    public boolean unbreakable() {
    	return false;
    }
    
    /**
     * returns the DebugLog
     */
	public ArrayList<String> getSpecialDebugInfo(EntityPlayer aPlayer, int aLogLevel, ArrayList<String> aList) {
		return aList;
	}
	
	public String getMainInfo() {return "";}
	public String getSecondaryInfo() {return "";}
	public String getTertiaryInfo() {return "";}
	public boolean isGivingInformation() {return false;}
	public LiquidStack getLiquid() {return null;}
	public int fill(LiquidStack resource, boolean doFill) {return 0;}
	public LiquidStack drain(int maxDrain, boolean doDrain) {return null;}
	public int getTankPressure() {return 0;}
	public int getCapacity() {return 0;}
	public void setLiquid(LiquidStack liquid) {}
	public void setCapacity(int capacity) {}
	public void onMachineBlockUpdate() {}
	public void receiveClientEvent(int aEventID, int aValue) {}
	public boolean isSimpleMachine() {return false;}
	public boolean isDigitalChest() {return false;}
	public ItemStack[] getStoredItemData() {return null;}
	public void setItemCount(int aCount) {}
	public int getMaxItemCount() {return 0;}
	public boolean isOverclockerUpgradable() {return false;}
	public boolean isTransformerUpgradable() {return false;}
	public boolean isBatteryUpgradable() {return false;}
}
