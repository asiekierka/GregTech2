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

public class GT_MetaTileEntity_ElectricSorter extends GT_MetaTileEntity_ElectricBufferSmall {
	
	public int mTargetDirection, oTargetDirection;
	
	public GT_MetaTileEntity_ElectricSorter(int aID, String mName, String mNameRegional) {
		super(aID, mName, mNameRegional);
	}
	
	public GT_MetaTileEntity_ElectricSorter() {
		
	}

	@Override public boolean isSimpleMachine()						{return true;}
    @Override public int maxEUStore()								{return 10000;}
	@Override public int getMinimumStoredEU()						{return 2000;}
    @Override public boolean isValidSlot(int aIndex)				{return aIndex<1;}
    @Override public boolean isOutputFacing(short aSide)			{return ForgeDirection.getOrientation(mBaseMetaTileEntity.mFacing).getOpposite() == ForgeDirection.getOrientation(aSide) || ForgeDirection.getOrientation(mTargetDirection) == ForgeDirection.getOrientation(aSide);}
	@Override public int getInvSize()								{return 11;}
	@Override public void onRightclick(EntityPlayer aPlayer)		{aPlayer.openGui(GT_Mod.instance, 107, mBaseMetaTileEntity.worldObj, mBaseMetaTileEntity.xCoord, mBaseMetaTileEntity.yCoord, mBaseMetaTileEntity.zCoord);}
    
	@Override
	public MetaTileEntity newMetaEntity(BaseMetaTileEntity aTileEntity) {
		return new GT_MetaTileEntity_ElectricSorter();
	}

	@Override
	public void saveNBTData(NBTTagCompound aNBT) {
		super.saveNBTData(aNBT);
    	aNBT.setInteger("mTargetDirection", mTargetDirection);
	}
	
	@Override
	public void loadNBTData(NBTTagCompound aNBT) {
		super.loadNBTData(aNBT);
		mTargetDirection = aNBT.getInteger("mTargetDirection");
    	oTargetDirection = -1;
	}
	
	public void onPostTick() {
		if (!mBaseMetaTileEntity.worldObj.isRemote && mBaseMetaTileEntity.getStored() >= 1000 && mBaseMetaTileEntity.mTickTimer%10 == 0) {
			TileEntity tTileEntity1, tTileEntity2, tTileEntity3;
			int xDir, yDir, zDir;
			
			tTileEntity1 = mBaseMetaTileEntity;
			xDir = ForgeDirection.getOrientation(mBaseMetaTileEntity.mFacing).offsetX;
			yDir = ForgeDirection.getOrientation(mBaseMetaTileEntity.mFacing).offsetY;
			zDir = ForgeDirection.getOrientation(mBaseMetaTileEntity.mFacing).offsetZ;
			tTileEntity2 = mBaseMetaTileEntity.worldObj.getBlockTileEntity(mBaseMetaTileEntity.xCoord-xDir, mBaseMetaTileEntity.yCoord-yDir, mBaseMetaTileEntity.zCoord-zDir);
			xDir = ForgeDirection.getOrientation(mTargetDirection).getOpposite().offsetX;
			yDir = ForgeDirection.getOrientation(mTargetDirection).getOpposite().offsetY;
			zDir = ForgeDirection.getOrientation(mTargetDirection).getOpposite().offsetZ;
			tTileEntity3 = mBaseMetaTileEntity.worldObj.getBlockTileEntity(mBaseMetaTileEntity.xCoord-xDir, mBaseMetaTileEntity.yCoord-yDir, mBaseMetaTileEntity.zCoord-zDir);
			
			ArrayList<ItemStack> tList = new ArrayList<ItemStack>();
			for (int i = 1; i < 10; i++) tList.add(mInventory[i]);
			
			int tPrice = 0;
			
			if (tTileEntity1 != null && tTileEntity3 != null && tTileEntity1 instanceof IInventory && tTileEntity3 instanceof IInventory) {
				mBaseMetaTileEntity.decreaseStoredEnergy(tPrice = GT_Utility.moveOneItemStack((IInventory)tTileEntity1, (IInventory)tTileEntity3, ForgeDirection.getOrientation(0), ForgeDirection.getOrientation(mTargetDirection).getOpposite(), tList, 3, 3, 3, false), true);
			}
			if (tPrice <= 0) {
				if (tTileEntity1 != null && tTileEntity2 != null && tTileEntity1 instanceof IInventory && tTileEntity2 instanceof IInventory) {
					mBaseMetaTileEntity.decreaseStoredEnergy(tPrice = GT_Utility.moveOneItemStack((IInventory)tTileEntity1, (IInventory)tTileEntity2, ForgeDirection.getOrientation(0), ForgeDirection.getOrientation(mBaseMetaTileEntity.mFacing), null, 2, 2, 2, false), true);
				}
			}
			
			mBaseMetaTileEntity.mRedstone = bInvert;
			
			if (bRedstoneIfFull) {
				mBaseMetaTileEntity.mRedstone = !bInvert;
				for (int i = 0; i < mInventory.length; i++) if (isValidSlot(i)) {
					if (mInventory[i] == null) {
						mBaseMetaTileEntity.mRedstone = bInvert;
						mBaseMetaTileEntity.decreaseStoredEnergy(1, true);
						break;
					}
				}
			}
		}
		if (!mBaseMetaTileEntity.worldObj.isRemote && mBaseMetaTileEntity.mTickTimer > 50) {
			if (mTargetDirection != oTargetDirection) {
				oTargetDirection = mTargetDirection;
				mBaseMetaTileEntity.oOutput = -1;
				mBaseMetaTileEntity.worldObj.addBlockEvent(mBaseMetaTileEntity.xCoord, mBaseMetaTileEntity.yCoord, mBaseMetaTileEntity.zCoord, mBaseMetaTileEntity.worldObj.getBlockId(mBaseMetaTileEntity.xCoord, mBaseMetaTileEntity.yCoord, mBaseMetaTileEntity.zCoord), 10, mTargetDirection);
			}
		}
		if (!mBaseMetaTileEntity.worldObj.isRemote && mBaseMetaTileEntity.mTickTimer > 50) mBaseMetaTileEntity.mActive = mBaseMetaTileEntity.mRedstone;
	}
	
    @Override
    public void receiveClientEvent(int aEventID, int aValue) {
		super.receiveClientEvent(aEventID, aValue);
		if (mBaseMetaTileEntity.worldObj.isRemote) {
			switch(aEventID) {
			case 10:
		    	mTargetDirection = aValue;
		    	mBaseMetaTileEntity.mNeedsUpdate = true;
		    	break;
			}
		}
	}
	
	@Override
	public int getInvSideIndex(ForgeDirection aSide, int aFacing) {
		return 0;
	}
	
	@Override
	public int getInvSideLength(ForgeDirection aSide, int aFacing) {
		return 1;
	}
	
	@Override
	public int getTextureIndex(int aSide, int aFacing) {
		if (aSide == mTargetDirection)
			return 116+(mBaseMetaTileEntity.mActive?8:0);
		if (aSide == aFacing)
			return 133+(mBaseMetaTileEntity.mActive?8:0);
		if (ForgeDirection.getOrientation(aSide).getOpposite() == ForgeDirection.getOrientation(aFacing))
			return 113+(mBaseMetaTileEntity.mActive?8:0);
		int tIndex = 133+(mBaseMetaTileEntity.mActive?8:0);
		
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
