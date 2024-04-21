package gregtechmod.common.tileentities;

import gregtechmod.GT_Mod;
import gregtechmod.api.BaseMetaTileEntity;
import gregtechmod.api.MetaTileEntity;
import ic2.core.item.ItemScrapbox;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.common.ForgeDirection;

public class GT_MetaTileEntity_Scrapboxinator extends GT_MetaTileEntity_ElectricBufferSmall {
	
	public GT_MetaTileEntity_Scrapboxinator(int aID, String mName, String mNameRegional) {
		super(aID, mName, mNameRegional);
	}
	
	public GT_MetaTileEntity_Scrapboxinator() {
		
	}

	@Override public boolean isSimpleMachine()						{return false;}
	@Override public int getMinimumStoredEU()						{return 2000;}
	@Override public int maxEUStore()								{return 10000;}
	@Override public int getInvSize()								{return 3;}
	@Override public void onRightclick(EntityPlayer aPlayer)		{aPlayer.openGui(GT_Mod.instance, 111, mBaseMetaTileEntity.worldObj, mBaseMetaTileEntity.xCoord, mBaseMetaTileEntity.yCoord, mBaseMetaTileEntity.zCoord);}
	
	public void onPostTick() {
		if (!mBaseMetaTileEntity.worldObj.isRemote && mBaseMetaTileEntity.getStored() >= 500 && mBaseMetaTileEntity.mTickTimer%50 == 0) {
			if (mInventory[0] == null && mInventory[1] != null && mInventory[1].stackSize > 0 && mInventory[1].getItem() instanceof ItemScrapbox) {
				mInventory[0] = ItemScrapbox.getDrop(mBaseMetaTileEntity.worldObj);
				mBaseMetaTileEntity.decrStackSize(1, 1);
				mBaseMetaTileEntity.decreaseStoredEnergy(100, true);
			}
		}
		
		super.onPostTick();
	}
	
	public int getInvSideIndex(ForgeDirection aSide, int aFacing) {
		if (aSide == ForgeDirection.getOrientation(mBaseMetaTileEntity.mFacing).getOpposite()) return 0;
		return 1;
	}
	
	@Override
	public int getInvSideLength(ForgeDirection aSide, int aFacing) {
		return 1;
	}
	
	@Override
	public MetaTileEntity newMetaEntity(BaseMetaTileEntity aTileEntity) {
		return new GT_MetaTileEntity_Scrapboxinator();
	}
	
	@Override
	public int getTextureIndex(int aSide, int aFacing) {
		if (ForgeDirection.getOrientation(aSide).getOpposite() == ForgeDirection.getOrientation(aFacing))
			return 113+(mBaseMetaTileEntity.mActive?8:0);
		return 119+(mBaseMetaTileEntity.mActive?8:0);
	}
}
