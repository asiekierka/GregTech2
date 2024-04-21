package gregtechmod.common.tileentities;

import gregtechmod.GT_Mod;
import gregtechmod.api.BaseMetaTileEntity;
import gregtechmod.api.MetaTileEntity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.common.ForgeDirection;

public class GT_MetaTileEntity_ElectricBufferLarge extends GT_MetaTileEntity_ElectricBufferSmall {
	
	public GT_MetaTileEntity_ElectricBufferLarge(int aID, String mName, String mNameRegional) {
		super(aID, mName, mNameRegional);
	}
	
	public GT_MetaTileEntity_ElectricBufferLarge() {
		
	}

	@Override public boolean isSimpleMachine()						{return true;}
    @Override public int maxEUStore()								{return 10000;}
	@Override public int getInvSize()								{return 28;}
	@Override public void onRightclick(EntityPlayer aPlayer)		{aPlayer.openGui(GT_Mod.instance, 103, mBaseMetaTileEntity.worldObj, mBaseMetaTileEntity.xCoord, mBaseMetaTileEntity.yCoord, mBaseMetaTileEntity.zCoord);}
    
	@Override
	public MetaTileEntity newMetaEntity(BaseMetaTileEntity aTileEntity) {
		return new GT_MetaTileEntity_ElectricBufferLarge();
	}
	
	@Override
	public int getTextureIndex(int aSide, int aFacing) {
		if (aSide == aFacing)
			return 131+(mBaseMetaTileEntity.mActive?8:0);
		if (ForgeDirection.getOrientation(aSide).getOpposite() == ForgeDirection.getOrientation(aFacing))
			return 113+(mBaseMetaTileEntity.mActive?8:0);
		int tIndex = 131+(mBaseMetaTileEntity.mActive?8:0);
		
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
