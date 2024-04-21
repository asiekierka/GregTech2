package gregtechmod.common.tileentities;

import gregtechmod.GT_Mod;
import gregtechmod.api.BaseMetaTileEntity;
import gregtechmod.api.MetaTileEntity;
import gregtechmod.common.GT_Utility;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.common.ForgeDirection;

public class GT_MetaTileEntity_ElectricItemClearer extends GT_MetaTileEntity_ElectricBufferSmall {
	
	public GT_MetaTileEntity_ElectricItemClearer(int aID, String mName, String mNameRegional) {
		super(aID, mName, mNameRegional);
	}
	
	public GT_MetaTileEntity_ElectricItemClearer() {
		
	}

	@Override public boolean isSimpleMachine()						{return false;}
    @Override public int maxEUStore()								{return 10000;}
	@Override public void onRightclick(EntityPlayer aPlayer)		{aPlayer.openGui(GT_Mod.instance, 108, mBaseMetaTileEntity.worldObj, mBaseMetaTileEntity.xCoord, mBaseMetaTileEntity.yCoord, mBaseMetaTileEntity.zCoord);}
    
	@Override
	public MetaTileEntity newMetaEntity(BaseMetaTileEntity aTileEntity) {
		return new GT_MetaTileEntity_ElectricItemClearer();
	}
	
	public void onPostTick() {
		if (!mBaseMetaTileEntity.worldObj.isRemote && mBaseMetaTileEntity.getStored() >= 100 && mBaseMetaTileEntity.mTickTimer%20 == 0 && mInventory[0] == null) {
			if ((mInventory[0] = GT_Utility.suckOneItemStackAt(mBaseMetaTileEntity.worldObj, mBaseMetaTileEntity.xCoord+ForgeDirection.getOrientation(mBaseMetaTileEntity.mFacing).offsetX, mBaseMetaTileEntity.yCoord+ForgeDirection.getOrientation(mBaseMetaTileEntity.mFacing).offsetY, mBaseMetaTileEntity.zCoord+ForgeDirection.getOrientation(mBaseMetaTileEntity.mFacing).offsetZ)) != null) {
				mBaseMetaTileEntity.decreaseStoredEnergy(mInventory[0].stackSize*2, true);
			}
		}
		super.onPostTick();
	}
	
	@Override
	public int getTextureIndex(int aSide, int aFacing) {
		if (aSide == aFacing)
			return 117+(mBaseMetaTileEntity.mActive?8:0);
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
