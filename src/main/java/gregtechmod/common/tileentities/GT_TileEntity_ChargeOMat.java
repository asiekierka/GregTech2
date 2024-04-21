package gregtechmod.common.tileentities;

import gregtechmod.common.GT_LanguageManager;
import ic2.api.Direction;
import ic2.api.ElectricItem;
import ic2.api.IElectricItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.ForgeDirection;

public class GT_TileEntity_ChargeOMat extends GT_TileEntityMetaID_Machine {
	
	public int getTier()								{return 5;}
    public boolean isAccessible(EntityPlayer aPlayer)	{return true;}
    public boolean isEnetOutput()      					{return  worldObj.isBlockIndirectlyGettingPowered(xCoord, yCoord, zCoord);}
    public boolean isEnetInput()       					{return !worldObj.isBlockIndirectlyGettingPowered(xCoord, yCoord, zCoord);}
    public boolean isOutputFacing(short aDirection) 	{return true;}
    public boolean isInputFacing(short aDirection)  	{return true;}
    public int maxEUStore()            					{return 10000000;}
    public int maxEUInput()            					{return 2048;}
    public int maxEUOutput()           					{return 2048;}
    public int getInventorySlotCount() 					{return 18;}
    
    public void onPostTickUpdate() {
    	if (!worldObj.isRemote) {
    		if (mTickTimer%20==0) {
    			ItemStack tStack = null;
        		EntityPlayer tPlayer = worldObj.getClosestPlayer(xCoord+0.5, yCoord+0.5, zCoord+0.5, 3.0);
        		if (tPlayer != null) {
        	        for (int j = 0; j < getChargeTier(); j++) {
        	        	if (isEnetOutput()) {
	        		        for (int i = 0; i < 4; i++)
	        			        if (demandsEnergy()>0 && (tStack = tPlayer.inventory.armorInventory[i]) != null && tStack.getItem() instanceof IElectricItem)
	        			            increaseStoredEnergy(ElectricItem.discharge(tStack, maxEUStore() - getEnergyVar(), getChargeTier(), false, false));
        	        	} else {
	        		        for (int i = 0; i < 4; i++)
	        			        if (getEnergyVar() > 0 && (tStack = tPlayer.inventory.armorInventory[i]) != null && tStack.getItem() instanceof IElectricItem)
	        			        	decreaseStoredEnergy(ElectricItem.charge(tStack, getEnergyVar(), getChargeTier(), false, false), true);
        	        	}
        	        }
        		}
    		}
    		if (mTickTimer%100==0) {
	    		for (int i = 0; i < 9; i++) {
	    			if (mInventory[i] != null) {
	    				if (mInventory[i].getItem() instanceof IElectricItem) {
	    					if (worldObj.isBlockIndirectlyGettingPowered(xCoord, yCoord, zCoord)) {
	    						if (!((IElectricItem)mInventory[i].getItem()).canProvideEnergy() || ElectricItem.discharge(mInventory[i], 1000000, getTier(), true, true) <= 0) {
	    	    					for (int j = 9; j < 18; j++) {
	    	    						if (mInventory[j] == null) {
	    	    							mInventory[j] = mInventory[i];
	    	    							mInventory[i] = null;
	    	    							break;
	    	    						}
	    	    					}
	    						}
	    					} else {
	    						if (ElectricItem.charge(mInventory[i], 1000000, getTier(), true, true) <= 0) {
	    	    					for (int j = 9; j < 18; j++) {
	    	    						if (mInventory[j] == null) {
	    	    							mInventory[j] = mInventory[i];
	    	    							mInventory[i] = null;
	    	    							break;
	    	    						}
	    	    					}
	    						}
	    					}
	    				} else {
	    					for (int j = 9; j < 18; j++) {
	    						if (mInventory[j] == null) {
	    							mInventory[j] = mInventory[i];
	    							mInventory[i] = null;
	    							break;
	    						}
	    					}
	    				}
	    			}
    			}
    		}
    	}
    }
    
    public int rechargerSlotStartIndex() {
    	return 0;
    }
    
    public int rechargerSlotCount() {
    	return worldObj.isBlockIndirectlyGettingPowered(xCoord, yCoord, zCoord)?0:9;
    }
    
    public int dechargerSlotStartIndex() {
    	return 0;
    }
    
    public int dechargerSlotCount() {
    	return worldObj.isBlockIndirectlyGettingPowered(xCoord, yCoord, zCoord)?9:0;
    }
    
	@Override public int getStartInventorySide(ForgeDirection aSide) {
		if (aSide == ForgeDirection.UP || aSide == ForgeDirection.DOWN) return 0;
		return 9;
	}
	
	@Override public int getSizeInventorySide(ForgeDirection aSide) {
		return 9;
	}
	
    @Override public String getInvName() {return GT_LanguageManager.mNameList1[10];}
    
    @Override
    public int getTexture(int aSide, int aMeta) {
    	if (aSide == 0)
    		return  3;
    	else if (aSide == 1)
    		return  6;
    	else
    		return 0;
    }
    
	@Override
	public boolean isTeleporterCompatible(Direction side) {
		return true;
	}
}
