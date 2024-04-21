package gregtechmod.common.tileentities;

import gregtechmod.GT_Mod;
import gregtechmod.common.GT_Coordinate;
import gregtechmod.common.GT_LanguageManager;
import gregtechmod.common.blocks.GT_BlockMetaID_Block;
import ic2.api.Direction;

import java.util.ArrayList;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.ForgeDirection;

public class GT_TileEntity_LESU extends GT_TileEntityMetaID_Machine {
	
	private boolean mNotify = true, mInit = true;
	private int mStorage = 2000000000;

    public boolean isFacingValid(int aFacing) 			{return aFacing != getFacing();}
    public boolean isAccessible(EntityPlayer aPlayer)	{return true;}
    public boolean isEnetOutput()      					{return true;}
    public boolean isEnetInput()       					{return true;}
    public boolean isOutputFacing(short aDirection) 	{return ((aDirection + 4) % 6) == getFacing();}
    public boolean isInputFacing(short aDirection)  	{return ((aDirection + 4) % 6) != getFacing();}
    public int maxEUStore()            					{return mStorage;}
    public int maxEUInput()            					{return getTier(maxEUOutput())==1?32:getTier(maxEUOutput())==2?128:512;}
    public int maxEUOutput()           					{return Math.min(Math.max(mStorage/1000000+4, 1), 512);}
    public int getInventorySlotCount() 					{return 2;}
    
    public void storeAdditionalData(NBTTagCompound aNBT) {
    	aNBT.setInteger("mStorage", mStorage);
    }

    public void getAdditionalData(NBTTagCompound aNBT) {
    	mStorage = aNBT.getInteger("mStorage");
    }
    
    public void onPreTickUpdate() {
	    if (!worldObj.isRemote) {
	    	if (mNotify || mInit) {
    			mStorage = 1000000;
	    		if (GT_BlockMetaID_Block.stepToFindOrCallLESUController(worldObj, xCoord, yCoord, zCoord, new ArrayList<GT_Coordinate>(), mInit) < 2) {
	    			mStorage = Math.min(2000000000, 1000000*GT_BlockMetaID_Block.stepToGetLESUAmount(worldObj, xCoord, yCoord, zCoord, new ArrayList<GT_Coordinate>()));
	    		}
	    		mNotify = mInit = false;
	    		worldObj.addBlockEvent(xCoord, yCoord, zCoord, GT_Mod.instance.mBlocks[1].blockID, 10, mStorage);
	    		worldObj.notifyBlocksOfNeighborChange(xCoord, yCoord, zCoord, GT_Mod.instance.mBlocks[1].blockID);
	    	}
    	}
    }

    @Override
    public void receiveClientEvent(int aEventID, int aValue) {
    	super.receiveClientEvent(aEventID, aValue);
    	if (worldObj.isRemote) {
	    	switch(aEventID) {
	    	case 10:
	    		mStorage = aValue;
	    		break;
	    	}
    	}
    }
    
    public int rechargerSlotStartIndex() {
    	return 0;
    }
    
    public int rechargerSlotCount() {
    	return 1;
    }
    
    public int dechargerSlotStartIndex() {
    	return 1;
    }
    
    public int dechargerSlotCount() {
    	return 1;
    }
    
    public void notifyLESUchange() {
    	mNotify = true;
    }
    
	@Override public int getStartInventorySide(ForgeDirection aSide) {
		if (aSide == ForgeDirection.UP || aSide == ForgeDirection.EAST || aSide == ForgeDirection.WEST) return 0;
		return 1;
	}
	
	@Override public int getSizeInventorySide(ForgeDirection aSide) {
		return 1;
	}
	
    @Override public String getInvName() {return GT_LanguageManager.mNameList1[7];}
    
    @Override
    public int getTexture(int aSide, int aMeta) {
    	if (aSide == getFacing())
    		return 12+getTier();
    	else
    		return 12;
    }
    
	@Override
	public boolean isTeleporterCompatible(Direction side) {
		return true;
	}
}
