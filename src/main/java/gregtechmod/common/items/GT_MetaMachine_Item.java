package gregtechmod.common.items;

import gregtechmod.GT_Mod;
import gregtechmod.api.BaseMetaTileEntity;
import gregtechmod.common.GT_Coordinate;
import gregtechmod.common.GT_LanguageManager;
import gregtechmod.common.blocks.GT_BlockMetaID_Block;
import gregtechmod.common.tileentities.GT_TileEntityMetaID_Machine;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class GT_MetaMachine_Item extends ItemBlock {
	public static int mItemID = 0;
	
    public GT_MetaMachine_Item(int par1) {
        super(par1);
        mItemID = par1;
        setMaxDamage(0);
        setHasSubtypes(true);
        setItemName(GT_LanguageManager.mNameList1[0]);
		setCreativeTab(GT_Mod.tabGregTech);
    }
    
    @Override @SideOnly(Side.CLIENT)
    public int getIconFromDamage(int aMeta) {
        return GT_Mod.instance.mBlocks[1].getBlockTextureFromSideAndMetadata(1, aMeta);
    }
    
	@Override
    public void addInformation(ItemStack aStack, EntityPlayer aPlayer, List aList, boolean par4) {
		if (aStack.getItemDamage() <= 0) {
			aList.add("If you got this from a Wrench, which is NOT directly belonging to IC2");
			aList.add("then please report it at the corresponding Wrench-Mod, and NOT to GregTech.");
		} else if (aStack.getItemDamage() < 16) {
			GT_TileEntityMetaID_Machine tTileEntity = (GT_TileEntityMetaID_Machine)GT_Mod.instance.mBlocks[1].createTileEntity(aPlayer.worldObj, aStack.getItemDamage());
			tTileEntity.worldObj = aPlayer.worldObj; tTileEntity.xCoord = 0; tTileEntity.yCoord = 0; tTileEntity.zCoord = 0;
			if (tTileEntity != null) {
				if (tTileEntity.getMaxSafeInput()	> 0) aList.add("Max EU/p IN: "  + tTileEntity.getMaxSafeInput());
				if (tTileEntity.getOutput()			> 0) aList.add("Max EU/t OUT: " + tTileEntity.getOutput());
			}
		} else {
			BaseMetaTileEntity tTileEntity = (BaseMetaTileEntity)GT_Mod.instance.mBlocks[1].createTileEntity(aPlayer.worldObj, 0);
			tTileEntity.worldObj = aPlayer.worldObj; tTileEntity.xCoord = 0; tTileEntity.yCoord = 0; tTileEntity.zCoord = 0;
			if (tTileEntity != null) {
				tTileEntity.createNewMetatileEntity(aStack.getItemDamage());
				if (tTileEntity.getMaxSafeInput()	> 0) aList.add("Max EU/p IN: "  + tTileEntity.getMaxSafeInput());
				if (tTileEntity.getOutput()			> 0) aList.add("Max EU/t OUT: " + tTileEntity.getOutput());
			}
		}
    }
	
    @Override
    public boolean onItemUseFirst(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ) {
        if (stack.getItemDamage() == 7)
    		if (0 < GT_BlockMetaID_Block.stepToFindOrCallLESUController(world, x, y, z, new ArrayList<GT_Coordinate>(), false))
    			return true;
        return false;
    }

    @Override
    public String getItemNameIS(ItemStack aStack) {
    	if (aStack.getItemDamage() < 16) return getItemName() + "." + GT_LanguageManager.mNameList1[aStack.getItemDamage()];
    	
    	if (BaseMetaTileEntity.mMetaTileList[aStack.getItemDamage()] != null) {
    		return getItemName() + "." + BaseMetaTileEntity.mMetaTileList[aStack.getItemDamage()].mName;
    	}
    	
    	return "";
    }
    
    @Override
    public boolean placeBlockAt(ItemStack aStack, EntityPlayer aPlayer, World aWorld, int aX, int aY, int aZ, int side, float hitX, float hitY, float hitZ, int aMeta) {
       if (aStack.getItemDamage()>15) {
    	   if (!aWorld.setBlockAndMetadataWithNotify(aX, aY, aZ, getBlockID(), 0)) {
    		   return false;
    	   }
    	   BaseMetaTileEntity tTileEntity = (BaseMetaTileEntity)aWorld.getBlockTileEntity(aX, aY, aZ);
    	   if (tTileEntity != null) {
    		   tTileEntity.createNewMetatileEntity(aStack.getItemDamage());
    	   }
       } else {
    	   if (!aWorld.setBlockAndMetadataWithNotify(aX, aY, aZ, getBlockID(), aStack.getItemDamage())) {
               return false;
           }
       }
       
       if (aWorld.getBlockId(aX, aY, aZ) == getBlockID()) {
           //Block.blocksList[getBlockID()].updateBlockMetadata(aWorld, aX, aY, aZ, side, hitX, hitY, hitZ);
           Block.blocksList[getBlockID()].onBlockPlacedBy(aWorld, aX, aY, aZ, aPlayer);
           Block.blocksList[getBlockID()].func_85105_g(aWorld, aX, aY, aZ, aStack.getItemDamage());
       }
       return true;
    }
}
