package gregtechmod.common.tileentities;

import gregtechmod.common.GT_LanguageManager;

import java.util.Iterator;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.packet.Packet3Chat;

public class GT_TileEntity_PlayerDetector extends GT_TileEntityMetaID_Machine {

	public int mMode = 0;
	
    public boolean isAccessible(EntityPlayer aPlayer)	{return true;}
    public boolean isEnetInput()       					{return true;}
    public boolean isInputFacing(short aDirection)  	{return true;}
    public int maxEUStore()            					{return 10000;}
    public boolean ownerControl()						{return true;}
    public int maxEUInput()  							{return   32;}
    
	@Override
	public float getWrenchDropRate() {
		return 1.0F;
	}
    
    public void storeAdditionalData(NBTTagCompound aNBT) {
    	aNBT.setInteger("mMode", mMode);
    }

    public void getAdditionalData(NBTTagCompound aNBT) {
    	mMode = aNBT.getInteger("mMode");
    }
    
    public void onPostTickUpdate() {
    	if (!worldObj.isRemote) {
    		if (mTickTimer%20==0 && mOwnerName != null && !mOwnerName.equals("")) {
				mActive = false;
				mRedstone = false;
    			if (decreaseStoredEnergy(50, false)) {
    				Iterator<EntityPlayer> tIterator = worldObj.playerEntities.iterator();
    				while (tIterator.hasNext()) {
    					EntityPlayer tPlayer = tIterator.next();
    					if (tPlayer.getDistanceSq(xCoord+0.5, yCoord+0.5, zCoord+0.5) <= 256) {
	    					if (mMode == 0) {
	    	    				mActive = true;
								mRedstone = true;
								break;
	    					}
	    					if (playerOwnsThis(tPlayer)) {
	    						if (mMode == 1) {
	    		    				mActive = true;
	    							mRedstone = true;
	    							break;
	    						}
	    					} else {
	    						if (mMode == 2) {
	    		    				mActive = true;
	    							mRedstone = true;
	    							break;
	    						}
	    					}
    					}
    				}
    			}
    		}
    	}
    }
	
    public void rightClick(EntityPlayer aPlayer) {
    	mMode = (mMode + 1) % 3;
    	
    	String tMessage = "";
    	
    	switch (mMode) {
    	case  0:
    		tMessage = "Detects all Players";
    		break;
    	case  1:
    		tMessage = "Detects only you";
    		break;
    	case  2:
    		tMessage = "Detects only other Players";
    		break;
    	}
    	
        if (aPlayer instanceof EntityPlayerMP) {
            ((EntityPlayerMP)aPlayer).playerNetServerHandler.sendPacketToPlayer(new Packet3Chat(tMessage));
        } else {
        	
        }
    }
    
    @Override public String getInvName() {return GT_LanguageManager.mNameList1[13];}
    
    @Override
    public int getTexture(int aSide, int aMeta) {
    	return mActive?24:23;
    }
}
