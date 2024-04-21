package gregtechmod.common.tileentities;

import gregtechmod.GT_Mod;
import gregtechmod.api.BaseMetaTileEntity;
import gregtechmod.api.MetaTileEntity;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.ForgeDirection;

public class GT_MetaTileEntity_RockBreaker extends GT_MetaTileEntity_ElectricBufferSmall {
	
	public GT_MetaTileEntity_RockBreaker(int aID, String mName, String mNameRegional) {
		super(aID, mName, mNameRegional);
	}
	
	public GT_MetaTileEntity_RockBreaker() {
		
	}

	@Override public boolean isSimpleMachine()						{return false;}
	@Override public int getMinimumStoredEU()						{return 2000;}
	@Override public int maxEUStore()								{return 10000;}
	@Override public int getInvSize()								{return 3;}
	@Override public void onRightclick(EntityPlayer aPlayer)		{aPlayer.openGui(GT_Mod.instance, 106, mBaseMetaTileEntity.worldObj, mBaseMetaTileEntity.xCoord, mBaseMetaTileEntity.yCoord, mBaseMetaTileEntity.zCoord);}
	
	public void onPostTick() {
		if (!mBaseMetaTileEntity.worldObj.isRemote && mBaseMetaTileEntity.getStored() >= 1000 && mBaseMetaTileEntity.mTickTimer%40 == 0) {
			ItemStack tOutput = new ItemStack(Block.cobblestone, 1);
			
			boolean tWater = true;

			if (mBaseMetaTileEntity.worldObj.getBlockId(mBaseMetaTileEntity.xCoord, mBaseMetaTileEntity.yCoord, mBaseMetaTileEntity.zCoord+1) != 9)
			if (mBaseMetaTileEntity.worldObj.getBlockId(mBaseMetaTileEntity.xCoord, mBaseMetaTileEntity.yCoord, mBaseMetaTileEntity.zCoord-1) != 9)
			if (mBaseMetaTileEntity.worldObj.getBlockId(mBaseMetaTileEntity.xCoord+1, mBaseMetaTileEntity.yCoord, mBaseMetaTileEntity.zCoord) != 9)
			if (mBaseMetaTileEntity.worldObj.getBlockId(mBaseMetaTileEntity.xCoord-1, mBaseMetaTileEntity.yCoord, mBaseMetaTileEntity.zCoord) != 9)
				tWater = false;
			
			if (mBaseMetaTileEntity.worldObj.getBlockId(mBaseMetaTileEntity.xCoord, mBaseMetaTileEntity.yCoord+1, mBaseMetaTileEntity.zCoord) == 11)
				tOutput = new ItemStack(Block.stone, 1);
			else
				if (mBaseMetaTileEntity.worldObj.getBlockId(mBaseMetaTileEntity.xCoord, mBaseMetaTileEntity.yCoord, mBaseMetaTileEntity.zCoord+1) != 11)
				if (mBaseMetaTileEntity.worldObj.getBlockId(mBaseMetaTileEntity.xCoord, mBaseMetaTileEntity.yCoord, mBaseMetaTileEntity.zCoord-1) != 11)
				if (mBaseMetaTileEntity.worldObj.getBlockId(mBaseMetaTileEntity.xCoord+1, mBaseMetaTileEntity.yCoord, mBaseMetaTileEntity.zCoord) != 11)
				if (mBaseMetaTileEntity.worldObj.getBlockId(mBaseMetaTileEntity.xCoord-1, mBaseMetaTileEntity.yCoord, mBaseMetaTileEntity.zCoord) != 11)
					tOutput = null;
			
			if (tOutput != null && tWater) {
				if (mInventory[0] == null) {
					if (mInventory[1] != null && mInventory[1].getItem() == Item.redstone) {
						mInventory[0] = new ItemStack(Block.obsidian, 1);
						mBaseMetaTileEntity.decrStackSize(1, 1);
						mBaseMetaTileEntity.decreaseStoredEnergy(500, true);
					} else {
						mInventory[0] = tOutput;
						mBaseMetaTileEntity.decreaseStoredEnergy(100, true);
					}
				} else {
					if (mInventory[1] != null && mInventory[1].getItem() == Item.redstone && mInventory[0].itemID == Block.obsidian.blockID && mInventory[0].stackSize < 64) {
						mInventory[0].stackSize++;
						mBaseMetaTileEntity.decrStackSize(1, 1);
						mBaseMetaTileEntity.decreaseStoredEnergy(500, true);
					} else {
						if (mInventory[0].isItemEqual(tOutput) && mInventory[0].stackSize < 64) {
							mInventory[0].stackSize++;
							mBaseMetaTileEntity.decreaseStoredEnergy(100, true);
						}
					}
				}
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
		return new GT_MetaTileEntity_RockBreaker();
	}
	
	@Override
	public int getTextureIndex(int aSide, int aFacing) {
		if (ForgeDirection.getOrientation(aSide).getOpposite() == ForgeDirection.getOrientation(aFacing))
			return 113+(mBaseMetaTileEntity.mActive?8:0);
		return 115+(mBaseMetaTileEntity.mActive?8:0);
	}
}
