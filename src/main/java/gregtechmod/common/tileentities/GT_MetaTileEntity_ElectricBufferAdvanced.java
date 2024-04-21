package gregtechmod.common.tileentities;

import gregtechmod.GT_Mod;
import gregtechmod.api.BaseMetaTileEntity;
import gregtechmod.api.MetaTileEntity;
import gregtechmod.common.GT_Utility;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.ForgeDirection;

public class GT_MetaTileEntity_ElectricBufferAdvanced extends GT_MetaTileEntity_ElectricBufferSmall {
	
	public int mTargetSlot = 0;
	
	public GT_MetaTileEntity_ElectricBufferAdvanced(int aID, String mName, String mNameRegional) {
		super(aID, mName, mNameRegional);
	}
	
	public GT_MetaTileEntity_ElectricBufferAdvanced() {
		
	}

	@Override public boolean isSimpleMachine()						{return true;}
    @Override public int maxEUStore()								{return 10000;}
	@Override public void onRightclick(EntityPlayer aPlayer)		{aPlayer.openGui(GT_Mod.instance, 105, mBaseMetaTileEntity.worldObj, mBaseMetaTileEntity.xCoord, mBaseMetaTileEntity.yCoord, mBaseMetaTileEntity.zCoord);}
    
	@Override
	public MetaTileEntity newMetaEntity(BaseMetaTileEntity aTileEntity) {
		return new GT_MetaTileEntity_ElectricBufferAdvanced();
	}

	@Override
	public void saveNBTData(NBTTagCompound aNBT) {
		super.saveNBTData(aNBT);
    	aNBT.setInteger("mTargetSlot", mTargetSlot);
	}
	
	@Override
	public void loadNBTData(NBTTagCompound aNBT) {
		super.loadNBTData(aNBT);
		mTargetSlot	= aNBT.getInteger("mTargetSlot");
	}
	
	public void onPostTick() {
		if (!mBaseMetaTileEntity.worldObj.isRemote && mBaseMetaTileEntity.getStored() >= 500 && mBaseMetaTileEntity.mTickTimer%10 == 0) {
			TileEntity tTileEntity1, tTileEntity2;
			
			int xDir = ForgeDirection.getOrientation(mBaseMetaTileEntity.mFacing).offsetX, yDir = ForgeDirection.getOrientation(mBaseMetaTileEntity.mFacing).offsetY, zDir = ForgeDirection.getOrientation(mBaseMetaTileEntity.mFacing).offsetZ;
			
			tTileEntity1 = mBaseMetaTileEntity;
			tTileEntity2 = mBaseMetaTileEntity.worldObj.getBlockTileEntity(mBaseMetaTileEntity.xCoord-xDir, mBaseMetaTileEntity.yCoord-yDir, mBaseMetaTileEntity.zCoord-zDir);
			
			if (tTileEntity1 != null && tTileEntity2 != null) {
				if (tTileEntity1 instanceof IInventory && tTileEntity2 instanceof IInventory) {
					mBaseMetaTileEntity.decreaseStoredEnergy(GT_Utility.moveOneItemStackIntoSlot((IInventory)tTileEntity1, (IInventory)tTileEntity2, ForgeDirection.getOrientation(mBaseMetaTileEntity.mFacing).getOpposite(), mTargetSlot, null, 3, 3, 3, false), true);
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
		if (!mBaseMetaTileEntity.worldObj.isRemote && mBaseMetaTileEntity.mTickTimer > 50) mBaseMetaTileEntity.mActive = mBaseMetaTileEntity.mRedstone;
	}
	
	@Override
	public int getTextureIndex(int aSide, int aFacing) {
		if (aSide == aFacing)
			return 132+(mBaseMetaTileEntity.mActive?8:0);
		if (ForgeDirection.getOrientation(aSide).getOpposite() == ForgeDirection.getOrientation(aFacing))
			return 113+(mBaseMetaTileEntity.mActive?8:0);
		int tIndex = 132+(mBaseMetaTileEntity.mActive?8:0);
		
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
