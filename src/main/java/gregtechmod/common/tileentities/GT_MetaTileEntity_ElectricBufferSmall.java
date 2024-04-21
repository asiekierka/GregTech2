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

public class GT_MetaTileEntity_ElectricBufferSmall extends MetaTileEntity {

	public boolean bOutput = true, bRedstoneIfFull = false, bInvert = false;
	
	public GT_MetaTileEntity_ElectricBufferSmall(int aID, String mName, String mNameRegional) {
		super(aID, mName, mNameRegional);
	}
	
	public GT_MetaTileEntity_ElectricBufferSmall() {
		
	}

	@Override public boolean isSimpleMachine()						{return true;}
	@Override public boolean isValidSlot(int aIndex)				{return aIndex<getInvSize()-1;}
	@Override public boolean isFacingValid(int aFacing)				{return true;}
	@Override public boolean isEnetInput() 							{return true;}
	@Override public boolean isEnetOutput() 						{return true;}
	@Override public boolean isInputFacing(short aSide)				{return !isOutputFacing(aSide);}
	@Override public boolean isOutputFacing(short aSide)			{return ForgeDirection.getOrientation(mBaseMetaTileEntity.mFacing).getOpposite() == ForgeDirection.getOrientation(aSide);}
	@Override public int getMinimumStoredEU()						{return 1000;}
	@Override public int maxEUInput()								{return 32;}
    @Override public int maxEUOutput()								{return bOutput?32:0;}
    @Override public int maxEUStore()								{return 1250;}
	@Override public int getInvSize()								{return 2;}
	@Override public void onRightclick(EntityPlayer aPlayer)		{aPlayer.openGui(GT_Mod.instance, 102, mBaseMetaTileEntity.worldObj, mBaseMetaTileEntity.xCoord, mBaseMetaTileEntity.yCoord, mBaseMetaTileEntity.zCoord);}
	@Override public boolean isAccessAllowed(EntityPlayer aPlayer)	{return true;}
    
	@Override
	public MetaTileEntity newMetaEntity(BaseMetaTileEntity aTileEntity) {
		return new GT_MetaTileEntity_ElectricBufferSmall();
	}

	@Override
	public void saveNBTData(NBTTagCompound aNBT) {
    	aNBT.setBoolean("bInvert", bInvert);
    	aNBT.setBoolean("bOutput", bOutput);
    	aNBT.setBoolean("bRedstoneIfFull", bRedstoneIfFull);
	}
	
	@Override
	public void loadNBTData(NBTTagCompound aNBT) {
		bInvert = aNBT.getBoolean("bInvert");
		bOutput = aNBT.getBoolean("bOutput");
		bRedstoneIfFull = aNBT.getBoolean("bRedstoneIfFull");
	}
	
	public void onPostTick() {
		if (!mBaseMetaTileEntity.worldObj.isRemote && mBaseMetaTileEntity.getStored() >= 500 && mBaseMetaTileEntity.mTickTimer%10 == 0) {
			TileEntity tTileEntity1, tTileEntity2;
			
			int xDir = ForgeDirection.getOrientation(mBaseMetaTileEntity.mFacing).offsetX, yDir = ForgeDirection.getOrientation(mBaseMetaTileEntity.mFacing).offsetY, zDir = ForgeDirection.getOrientation(mBaseMetaTileEntity.mFacing).offsetZ;
			
			tTileEntity1 = mBaseMetaTileEntity;
			tTileEntity2 = mBaseMetaTileEntity.worldObj.getBlockTileEntity(mBaseMetaTileEntity.xCoord-xDir, mBaseMetaTileEntity.yCoord-yDir, mBaseMetaTileEntity.zCoord-zDir);
			
			if (tTileEntity1 != null && tTileEntity2 != null) {
				if (tTileEntity1 instanceof IInventory && tTileEntity2 instanceof IInventory) {
					mBaseMetaTileEntity.decreaseStoredEnergy(GT_Utility.moveOneItemStack((IInventory)tTileEntity1, (IInventory)tTileEntity2, ForgeDirection.getOrientation(mBaseMetaTileEntity.mFacing).getOpposite(), ForgeDirection.getOrientation(mBaseMetaTileEntity.mFacing), null, 1, 1, 1, false)*(getInvSize()>10?2:1), true);
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
	public int getInvSideIndex(ForgeDirection aSide, int aFacing) {
		return 0;
	}
	
	@Override
	public int getInvSideLength(ForgeDirection aSide, int aFacing) {
		return getInvSize()-1;
	}
	
	
	@Override
	public int getTextureIndex(int aSide, int aFacing) {
		if (aSide == aFacing)
			return 130+(mBaseMetaTileEntity.mActive?8:0);
		if (ForgeDirection.getOrientation(aSide).getOpposite() == ForgeDirection.getOrientation(aFacing))
			return 113+(mBaseMetaTileEntity.mActive?8:0);

		int tIndex = 130+(mBaseMetaTileEntity.mActive?8:0);
		
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
