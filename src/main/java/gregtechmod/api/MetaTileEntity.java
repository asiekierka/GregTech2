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
		if (BaseMetaTileEntity.mMetaTileList[aID] == null) {
			BaseMetaTileEntity.mMetaTileList[aID] = this;
		} else {
			throw new IllegalArgumentException("Metamachine-Slot Nr. " + aID + " is already occupied!");
		}
		mID = aID;
		mName = aBasicName;
		mRegionalName = aRegionalName;
		mBaseMetaTileEntity = new BaseMetaTileEntity();
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
	public abstract int getTextureIndex(int aSide, int aFacing);
	
	public void onFirstTick() {}
	public void onPreTick() {}
	public void onPostTick() {}
	public void onRemoval() {}
	public void onOpenGUI() {}
	public void onCloseGUI() {}
	public void onRightclick(EntityPlayer aPlayer) {}

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
     * This is used to set the internal Energy to the given Parameter. I use this for the IDSU.
     */
	public void setEnergyVar(int aEU) {
		mBaseMetaTileEntity.mStoredEnergy = aEU;
	}
	
	/**
	 * @return the amount of EU, which this Device stores before starting to emit Energy.
	 */
	public int getMinimumStoredEU() {
		return 0;
	}
	
    /**
     * This is used to get the internal Energy. I use this for the IDSU.
     */
	public int getEnergyVar() {
		return mBaseMetaTileEntity.mStoredEnergy;
	}

    /**
     * Determines the Tier of the Machine, used for de-charging Tools.
     */
    public int getInputTier() {
    	return mBaseMetaTileEntity.getTier(maxEUInput());
    }
    
    /**
     * Determines the Tier of the Machine, used for charging Tools.
     */
    public int getOutputTier() {
    	return mBaseMetaTileEntity.getTier(maxEUOutput());
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
}
