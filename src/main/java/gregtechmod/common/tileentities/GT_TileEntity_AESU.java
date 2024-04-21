package gregtechmod.common.tileentities;

import gregtechmod.common.GT_LanguageManager;
import ic2.api.Direction;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.ForgeDirection;

public class GT_TileEntity_AESU extends GT_TileEntityMetaID_Machine {
	
	public int mOutput = 32;

	public int getTier()								{return 4;}
    public boolean isFacingValid(int aFacing) 			{return aFacing != getFacing();}
    public boolean isAccessible(EntityPlayer aPlayer)	{return true;}
    public boolean isEnetOutput()      					{return true;}
    public boolean isEnetInput()       					{return true;}
    public boolean isOutputFacing(short aDirection) 	{return ((aDirection + 4) % 6) == getFacing();}
    public boolean isInputFacing(short aDirection)  	{return ((aDirection + 4) % 6) != getFacing();}
    public int maxEUStore()            					{return 100000000;}
    public int maxEUInput()            					{return 2048;}
    public int maxEUOutput()           					{return mOutput;}
    public int getInventorySlotCount() 					{return 3;}
    
    public void storeAdditionalData(NBTTagCompound aNBT) {
    	aNBT.setInteger("mOutput", mOutput);
    }

    public void getAdditionalData(NBTTagCompound aNBT) {
    	mOutput = aNBT.getInteger("mOutput");
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
    
	@Override public int getStartInventorySide(ForgeDirection aSide) {
		if (aSide == ForgeDirection.UP || aSide == ForgeDirection.EAST || aSide == ForgeDirection.WEST) return 0;
		return 1;
	}
	
	@Override public int getSizeInventorySide(ForgeDirection aSide) {
		return 1;
	}
	
	public boolean isValidSlot(int aIndex) {
    	return aIndex != 2;
    }
	
    @Override public String getInvName() {return GT_LanguageManager.mNameList1[9];}
    
    @Override
    public int getTexture(int aSide, int aMeta) {
    	if (aSide == getFacing())
    		return 18;
    	else
    		return 16;
    }
    
	@Override
	public boolean isTeleporterCompatible(Direction side) {
		return true;
	}
}
