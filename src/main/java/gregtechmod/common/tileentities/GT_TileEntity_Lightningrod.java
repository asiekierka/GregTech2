package gregtechmod.common.tileentities;

import gregtechmod.GT_Mod;
import gregtechmod.common.GT_LanguageManager;
import gregtechmod.common.GT_ModHandler;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.player.EntityPlayer;

public class GT_TileEntity_Lightningrod extends GT_TileEntityMetaID_Machine {
	
    public boolean isAccessible(EntityPlayer aPlayer)	{return false;}
    public boolean isEnetOutput()      					{return true;}
    public boolean isEnetInput()       					{return false;}
    public boolean isOutputFacing(short aDirection) 	{return aDirection != 2 && aDirection != 3;}
    public boolean isInputFacing(short aDirection)  	{return false;}
    public int maxEUStore()            					{return 100000000;}
    public int maxEUOutput()           					{return 8192;}
    public int getInventorySlotCount() 					{return 0;}

    public void onPostTickUpdate() {
	    if (!worldObj.isRemote) {
	    	if (mTickTimer%256==0&&(worldObj.isThundering()||(worldObj.isRaining()&&GT_Mod.Randomizer.nextInt(10)==0))) {
	    		int rodvalue = 0;
	    		boolean rodcounts = true;
	        	for (int i = yCoord + 1; i < worldObj.getHeight()-1; i++) {
	        		if (rodcounts&&worldObj.getBlockId(xCoord, i, zCoord) == GT_ModHandler.getIC2Item("ironFence", 1).itemID) {
	        			rodvalue++;
	        		} else {
	        			rodcounts = false;
	        			if (worldObj.getBlockId(xCoord, i, zCoord) != 0) {
	        				rodvalue=0;
	        				break;
	        			}
	        		}
	        	}
	        	
	        	if (!worldObj.isThundering()&&yCoord+rodvalue<128) rodvalue=0;
	        	
	        	if (GT_Mod.Randomizer.nextInt(4096*worldObj.getHeight())<rodvalue*(yCoord+rodvalue)) {
	            	setStoredEnergy(25000000);
	    	    	worldObj.addWeatherEffect(new EntityLightningBolt(worldObj, xCoord, yCoord+rodvalue, zCoord));
	    		}
	    	}
	    }
    }

    @Override public String getInvName() {return GT_LanguageManager.mNameList1[2];}
    
    @Override
    public int getTexture(int aSide, int aMeta) {
    	if (aSide == 0)
    		return  3;
    	else if (aSide == 1)
    		return 21;
    	else
    		return 18;
    }
}
