package gregtechmod.common.tileentities;

import gregtechmod.GT_Mod;
import gregtechmod.api.BaseMetaTileEntity;
import gregtechmod.api.MetaTileEntity;
import gregtechmod.common.GT_Utility;

import java.util.ArrayList;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.ForgeDirection;

public class GT_MetaTileEntity_Translocator extends MetaTileEntity {

	public boolean bOutput = true, bInvertFilter = false;
	
	public GT_MetaTileEntity_Translocator(int aID, String mName, String mNameRegional) {
		super(aID, mName, mNameRegional);
	}
	
	public GT_MetaTileEntity_Translocator() {
		
	}

	@Override public boolean isSimpleMachine()						{return true;}
	@Override public boolean isValidSlot(int aIndex)				{return false;}
	@Override public boolean isFacingValid(int aFacing)				{return true;}
	@Override public boolean isEnetInput() 							{return true;}
	@Override public boolean isEnetOutput() 						{return true;}
	@Override public boolean isInputFacing(short aSide)				{return !isOutputFacing(aSide);}
	@Override public boolean isOutputFacing(short aSide)			{return ForgeDirection.getOrientation(mBaseMetaTileEntity.mFacing).getOpposite() == ForgeDirection.getOrientation(aSide) || ForgeDirection.getOrientation(mBaseMetaTileEntity.mFacing) == ForgeDirection.getOrientation(aSide);}
	@Override public int getMinimumStoredEU()						{return 2000;}
	@Override public int maxEUInput()								{return 32;}
    @Override public int maxEUOutput()								{return bOutput?32:0;}
    @Override public int maxEUStore()								{return 10000;}
	@Override public int getInvSize()								{return 10;}
	@Override public void onRightclick(EntityPlayer aPlayer)		{aPlayer.openGui(GT_Mod.instance, 101, mBaseMetaTileEntity.worldObj, mBaseMetaTileEntity.xCoord, mBaseMetaTileEntity.yCoord, mBaseMetaTileEntity.zCoord);}
	@Override public boolean isAccessAllowed(EntityPlayer aPlayer)	{return true;}
    
	@Override
	public MetaTileEntity newMetaEntity(BaseMetaTileEntity aTileEntity) {
		return new GT_MetaTileEntity_Translocator();
	}
	
	@Override
	public void saveNBTData(NBTTagCompound aNBT) {
    	aNBT.setBoolean("bOutput", bOutput);
    	aNBT.setBoolean("bInvertFilter", bInvertFilter);
	}
	
	@Override
	public void loadNBTData(NBTTagCompound aNBT) {
		bOutput = aNBT.getBoolean("bOutput");
		bInvertFilter = aNBT.getBoolean("bInvertFilter");
	}
	
	public void onPostTick() {
		if (!mBaseMetaTileEntity.worldObj.isRemote && mBaseMetaTileEntity.getStored() >= 1000 && mBaseMetaTileEntity.mTickTimer%10 == 0) {
			TileEntity tTileEntity1, tTileEntity2;
			
			int xDir = ForgeDirection.getOrientation(mBaseMetaTileEntity.mFacing).offsetX, yDir = ForgeDirection.getOrientation(mBaseMetaTileEntity.mFacing).offsetY, zDir = ForgeDirection.getOrientation(mBaseMetaTileEntity.mFacing).offsetZ;
			
			tTileEntity1 = mBaseMetaTileEntity.worldObj.getBlockTileEntity(mBaseMetaTileEntity.xCoord+xDir, mBaseMetaTileEntity.yCoord+yDir, mBaseMetaTileEntity.zCoord+zDir);
			tTileEntity2 = mBaseMetaTileEntity.worldObj.getBlockTileEntity(mBaseMetaTileEntity.xCoord-xDir, mBaseMetaTileEntity.yCoord-yDir, mBaseMetaTileEntity.zCoord-zDir);
			
			if (tTileEntity1 != null && tTileEntity2 != null) {
				if (tTileEntity1 instanceof IInventory && tTileEntity2 instanceof IInventory) {
					ArrayList<ItemStack> tList = new ArrayList<ItemStack>();
					for (int i = 0; i < 9; i++) tList.add(mInventory[i]);
					mBaseMetaTileEntity.decreaseStoredEnergy(GT_Utility.moveOneItemStack((IInventory)tTileEntity1, (IInventory)tTileEntity2, ForgeDirection.getOrientation(mBaseMetaTileEntity.mFacing).getOpposite(), ForgeDirection.getOrientation(mBaseMetaTileEntity.mFacing), tList, 3, 2, 1, bInvertFilter)*((mInventory[0] == null && mInventory[1] == null && mInventory[2] == null && mInventory[3] == null && mInventory[4] == null && mInventory[5] == null && mInventory[6] == null && mInventory[7] == null && mInventory[8] == null)?1:2), true);
				}
			}
		}
		if (!mBaseMetaTileEntity.worldObj.isRemote && mBaseMetaTileEntity.mTickTimer > 50) mBaseMetaTileEntity.mActive = mBaseMetaTileEntity.mRedstone;
	}
	
	@Override
	public int getInvSideIndex(ForgeDirection aSide, int aFacing) {
		return 0;
	}
	
	@Override
	public int getInvSideLength(ForgeDirection aSide, int aFacing) {
		return 0;
	}
	
	
	@Override
	public int getTextureIndex(int aSide, int aFacing) {
		if (aSide == aFacing)
			return 112+(mBaseMetaTileEntity.mActive?8:0);
		if (ForgeDirection.getOrientation(aSide).getOpposite() == ForgeDirection.getOrientation(aFacing))
			return 113+(mBaseMetaTileEntity.mActive?8:0);
		
		int tIndex = 128+(mBaseMetaTileEntity.mActive?8:0);
		
		switch (aFacing) {
		case 0:
			return tIndex+64;
		case 1:
			return tIndex+32;
		case 2: switch (aSide) {
			case 0: return tIndex+32;
			case 1: return tIndex+32;
			case 4: return tIndex+16;
			case 5: return tIndex+48;
			}
		case 3: switch (aSide) {
			case 0: return tIndex+64;
			case 1: return tIndex+64;
			case 4: return tIndex+48;
			case 5: return tIndex+16;
			}
		case 4: switch (aSide) {
			case 0: return tIndex+16;
			case 1: return tIndex+16;
			case 2: return tIndex+48;
			case 3: return tIndex+16;
			}
		case 5: switch (aSide) {
			case 0: return tIndex+48;
			case 1: return tIndex+48;
			case 2: return tIndex+16;
			case 3: return tIndex+48;
			}
		}
		return tIndex;
	}
}
