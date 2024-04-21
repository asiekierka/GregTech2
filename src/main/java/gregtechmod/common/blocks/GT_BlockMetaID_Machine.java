package gregtechmod.common.blocks;

import gregtechmod.GT_Mod;
import gregtechmod.api.BaseMetaTileEntity;
import gregtechmod.api.IDebugableBlock;
import gregtechmod.api.IIgnoreRightclickOnMachine;
import gregtechmod.common.GT_ModHandler;
import gregtechmod.common.items.GT_MetaItem_Component;
import gregtechmod.common.tileentities.GT_TileEntityMetaID_Machine;
import gregtechmod.common.tileentities.GT_TileEntity_AESU;
import gregtechmod.common.tileentities.GT_TileEntity_Centrifuge;
import gregtechmod.common.tileentities.GT_TileEntity_ChargeOMat;
import gregtechmod.common.tileentities.GT_TileEntity_ComputerCube;
import gregtechmod.common.tileentities.GT_TileEntity_Fusionreactor;
import gregtechmod.common.tileentities.GT_TileEntity_IDSU;
import gregtechmod.common.tileentities.GT_TileEntity_LESU;
import gregtechmod.common.tileentities.GT_TileEntity_Lightningrod;
import gregtechmod.common.tileentities.GT_TileEntity_Matterfabricator;
import gregtechmod.common.tileentities.GT_TileEntity_PlayerDetector;
import gregtechmod.common.tileentities.GT_TileEntity_Quantumchest;
import gregtechmod.common.tileentities.GT_TileEntity_Sonictron;
import gregtechmod.common.tileentities.GT_TileEntity_Supercondensator;
import gregtechmod.common.tileentities.GT_TileEntity_Superconductor;
import gregtechmod.common.tileentities.GT_TileEntity_UUMAssembler;
import ic2.api.IWrenchable;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;
import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class GT_BlockMetaID_Machine extends BlockContainer implements IDebugableBlock {
	
	public GT_BlockMetaID_Machine(int aID) {
        super(aID, Material.iron);
        setHardness(3.0F);
        setResistance(10.0F);
        setBlockName("BlockMetaID_Machine");
        setStepSound(Block.soundMetalFootstep);
        setTextureFile("/gregtechmod/textures/terrain.png");
		setCreativeTab(GT_Mod.tabGregTech);
        setRequiresSelfNotify();
        for (int i = 0; i < 16; i++) MinecraftForge.setBlockHarvestLevel(this, i, "wrench",  1);
	}
	
	@Override
    public int getBlockTexture(IBlockAccess aIBlockAccess, int aX, int aY, int aZ, int aSide) {
		int tMeta = aIBlockAccess.getBlockMetadata(aX, aY, aZ);
		TileEntity tTileEntity = aIBlockAccess.getBlockTileEntity(aX, aY, aZ);
		
		if (tTileEntity == null) return 0;
		
		if (tTileEntity instanceof GT_TileEntityMetaID_Machine) {
			return ((GT_TileEntityMetaID_Machine)tTileEntity).getTexture(aSide, tMeta);
		}
		
		if (tTileEntity instanceof BaseMetaTileEntity && ((BaseMetaTileEntity)tTileEntity).mMetaTileEntity != null) {
			return ((BaseMetaTileEntity)tTileEntity).getTexture(aSide, tMeta);
		}
		
		return 0;
	}
	
	@Override
	public int getBlockTextureFromSideAndMetadata(int aSide, int aMeta) {
		if (aMeta > 0 && aMeta < 16) return ((GT_TileEntityMetaID_Machine)createNewTileEntity(null, aMeta)).getTexture(aSide, aMeta);
		if (BaseMetaTileEntity.mMetaTileList[aMeta] != null) return BaseMetaTileEntity.mMetaTileList[aMeta].getTextureIndex(aSide, 3);
		return 0;
	}
	
	@Override
	public boolean onBlockActivated(World aWorld, int aX, int aY, int aZ, EntityPlayer aPlayer, int par1, float par2, float par3, float par4) {
		TileEntity tTileEntity = aWorld.getBlockTileEntity(aX, aY, aZ);

	    if (tTileEntity == null || aPlayer.isSneaking()) return false;

	    ItemStack tPlayerItem = aPlayer.inventory.getCurrentItem();
	    if (tPlayerItem != null && tPlayerItem.getItem() != null) {
	    	if (tPlayerItem.getItem() instanceof IIgnoreRightclickOnMachine)						return false;
	    	if (tPlayerItem.getItem() == GT_ModHandler.getIC2Item("ecMeter"			, 1).getItem()) return false;
	    	if (tPlayerItem.getItem() == GT_ModHandler.getIC2Item("wrench"			, 1).getItem()) return false;
	    	if (tPlayerItem.getItem() == GT_ModHandler.getIC2Item("electricWrench"	, 1).getItem()) return false;
	    }
	    
		if (tTileEntity instanceof GT_TileEntityMetaID_Machine) {
		    if (!((GT_TileEntityMetaID_Machine)tTileEntity).isUseableByPlayer(aPlayer)) return false;

		    int tMetaData = aWorld.getBlockMetadata(aX, aY, aZ), tGuiID = tMetaData;
		    
		    if (tMetaData == 4) tGuiID = getComputerCubeGUIID(tTileEntity);
		    if (tMetaData == 13)
		    	((GT_TileEntity_PlayerDetector)tTileEntity).rightClick(aPlayer);
		    else
		    	aPlayer.openGui(GT_Mod.instance, tGuiID, aWorld, aX, aY, aZ);
		    return true;
		}
		
		if (tTileEntity instanceof BaseMetaTileEntity) {
		    if (!((BaseMetaTileEntity)tTileEntity).isUseableByPlayer(aPlayer)) return false;
		    ((BaseMetaTileEntity)tTileEntity).onRightclick(aPlayer);
		    return true;
		}
		
		return false;
	}

	public static int getComputerCubeGUIID(TileEntity aTileEntity) {
	    switch (((GT_TileEntity_ComputerCube)aTileEntity).mMode) {
	    case 1:
	    	return 32;
	    case 2:
	    	return 34;
	    case 3:
	    	return 35;
	    case 4:
	    	return 36;
	    case 5:
	    	return 37;
	    case 6:
	    	return 38;
	    default:
	    	return 4;
	    }
	}
	
	@Override
    public int getDamageValue(World world, int x, int y, int z) {
	    TileEntity tileEntity = world.getBlockTileEntity(x, y, z);
	    
	    if (tileEntity == null) return 0;
	    
	    if (tileEntity instanceof BaseMetaTileEntity) {
        	return ((BaseMetaTileEntity)tileEntity).mID;
	    } else {
	        return world.getBlockMetadata(x, y, z);
	    }
    }

	@Override
	public ArrayList<ItemStack> getBlockDropped(World world, int x, int y, int z, int metadata, int fortune) {
        ArrayList<ItemStack> ret = new ArrayList<ItemStack>();
    	ret.add(GT_MetaItem_Component.instance.getStack(22, 1));
        return ret;
    }
	
	@Override
	public void breakBlock(World world, int x, int y, int z, int par5, int par6) {
	    dropItems(world, x, y, z);
	    super.breakBlock(world, x, y, z, par5, par6);
	}
	
	private void dropItems(World aWorld, int aX, int aY, int aZ){
	    Random rand = new Random();
	
	    TileEntity tTileEntity = aWorld.getBlockTileEntity(aX, aY, aZ);
	    
	    if (tTileEntity == null) return;
	    
	    if (tTileEntity instanceof GT_TileEntityMetaID_Machine) {
		    GT_TileEntityMetaID_Machine inventory = (GT_TileEntityMetaID_Machine)tTileEntity;
		    
	        for (int i = 0; i < inventory.getSizeInventory(); i++) {
	            ItemStack item = inventory.getStackInSlot(i);
	
	            if (item != null && item.stackSize > 0 && inventory.isValidSlot(i)) {
	                float rx = rand.nextFloat() * 0.8F + 0.1F;
		            float ry = rand.nextFloat() * 0.8F + 0.1F;
		            float rz = rand.nextFloat() * 0.8F + 0.1F;
		
		            EntityItem entityItem = new EntityItem(aWorld, aX + rx, aY + ry, aZ + rz, new ItemStack(item.itemID, item.stackSize, item.getItemDamage()));
		            if (item.hasTagCompound()) {
		                entityItem.func_92014_d().setTagCompound((NBTTagCompound) item.getTagCompound().copy());
		            }
		            
		            float factor = 0.05F;
		            entityItem.motionX = rand.nextGaussian() * factor;
		            entityItem.motionY = rand.nextGaussian() * factor + 0.2F;
		            entityItem.motionZ = rand.nextGaussian() * factor;
		            aWorld.spawnEntityInWorld(entityItem);
		            item.stackSize = 0;
		        }
		    }
	    }

	    if (tTileEntity instanceof BaseMetaTileEntity && ((BaseMetaTileEntity)tTileEntity).mMetaTileEntity != null) {
	    	BaseMetaTileEntity inventory = (BaseMetaTileEntity)tTileEntity;
		    
	    	try {
	    		
	    		//aWorld.spawnEntityInWorld(new EntityItem(aWorld, aX+0.5, aY+0.5, aZ+0.5, new ItemStack(blockID, 1, inventory.mID)));
	    		
		        for (int i = 0; i < inventory.getSizeInventory(); i++) {
		            ItemStack item = inventory.getStackInSlot(i);
		            
		            if (item != null && item.stackSize > 0 && inventory.isValidSlot(i)) {
		                float rx = rand.nextFloat() * 0.8F + 0.1F;
			            float ry = rand.nextFloat() * 0.8F + 0.1F;
			            float rz = rand.nextFloat() * 0.8F + 0.1F;
			
			            EntityItem entityItem = new EntityItem(aWorld, aX + rx, aY + ry, aZ + rz, new ItemStack(item.itemID, item.stackSize, item.getItemDamage()));
			            if (item.hasTagCompound()) {
			                entityItem.func_92014_d().setTagCompound((NBTTagCompound) item.getTagCompound().copy());
			            }
			
			            float factor = 0.05F;
			            entityItem.motionX = rand.nextGaussian() * factor;
			            entityItem.motionY = rand.nextGaussian() * factor + 0.2F;
			            entityItem.motionZ = rand.nextGaussian() * factor;
			            aWorld.spawnEntityInWorld(entityItem);
			            item.stackSize = 0;
			        }
			    }
	    	} catch(Throwable e) {}
	    }
	}
	
	@Override
    public boolean isProvidingWeakPower(IBlockAccess var1, int x, int y, int z, int var5) {
        TileEntity tTileEntity = var1.getBlockTileEntity(x, y, z);
        
        if (tTileEntity == null) return false;
        
        if (tTileEntity instanceof GT_TileEntityMetaID_Machine)
            return ((GT_TileEntityMetaID_Machine)tTileEntity).mRedstone;
        if (tTileEntity instanceof BaseMetaTileEntity)
            return ((BaseMetaTileEntity)tTileEntity).mRedstone;
        return false;
    }
	
	@Override
    public boolean canConnectRedstone(IBlockAccess var1, int var2, int var3, int var4, int var5) {
        return true;
    }

	@Override
    public boolean canProvidePower() {
        return true;
    }
	
	@Override
    public boolean isBlockNormalCube(World world, int x, int y, int z)  {
        return false;
    }

	@Override
	public boolean isBlockSolidOnSide(World world, int x, int y, int z, ForgeDirection side) {
        return true;
    }
    
	@Override
	public TileEntity createNewTileEntity(World aWorld) {
		return new BaseMetaTileEntity();
	}
	
	@Override
	public TileEntity createNewTileEntity(World aWorld, int aMeta) {
		switch(aMeta) {
		case  1: return new GT_TileEntity_Fusionreactor();
		case  2: return new GT_TileEntity_Lightningrod();
		case  3: return new GT_TileEntity_Quantumchest();
		case  4: return new GT_TileEntity_ComputerCube();
		case  5: return new GT_TileEntity_UUMAssembler();
		case  6: return new GT_TileEntity_Sonictron();
		case  7: return new GT_TileEntity_LESU();
		case  8: return new GT_TileEntity_IDSU();
		case  9: return new GT_TileEntity_AESU();
		case 10: return new GT_TileEntity_ChargeOMat();
		case 11: return new GT_TileEntity_Centrifuge();
		case 12: return new GT_TileEntity_Superconductor();
		case 13: return new GT_TileEntity_PlayerDetector();
		case 14: return new GT_TileEntity_Matterfabricator();
		case 15: return new GT_TileEntity_Supercondensator();
		default: return new BaseMetaTileEntity();
		}
	}
	
	@Override @SideOnly(Side.CLIENT)
    public void getSubBlocks(int par1, CreativeTabs par2CreativeTabs, List par3List) {
        for (int i = 1; i < BaseMetaTileEntity.mMetaTileList.length; ++i) {
        	if (i < 16)
        		par3List.add(new ItemStack(par1, 1, i));
        	else
        		if (BaseMetaTileEntity.mMetaTileList[i] != null)
        			par3List.add(new ItemStack(par1, 1, i));
        }
    }
    
	@Override
    public boolean canCreatureSpawn(EnumCreatureType type, World world, int x, int y, int z) {
		return false;
	}
	
	@Override
    public void onBlockPlacedBy(World aWorld, int aX, int aY, int aZ, EntityLiving aPlayer) {
		TileEntity tTileEntity = aWorld.getBlockTileEntity(aX, aY, aZ);
		
		if (tTileEntity == null) return;
		
		if (tTileEntity instanceof IWrenchable) {
			IWrenchable var6 = (IWrenchable)tTileEntity;
	        if (aPlayer == null) {
	            var6.setFacing((short)1);
	        } else {
	            int var7 = MathHelper.floor_double((double)(aPlayer.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;
	            int var8 = Math.round(aPlayer.rotationPitch);
	            if (var8 >= 65 && var6.wrenchCanSetFacing(null, 1)) {
	                var6.setFacing((short)1);
	            } else if (var8 <= -65 && var6.wrenchCanSetFacing(null, 0)) {
	                var6.setFacing((short)0);
	            } else {
	                switch (var7) {
	                case 0: var6.setFacing((short)2); break;
	                case 1: var6.setFacing((short)5); break;
	                case 2: var6.setFacing((short)3); break;
	                case 3: var6.setFacing((short)4); break;
	                }
	            }
	        }
		}
    }

	@Override
	public ArrayList<String> getDebugInfo(EntityPlayer aPlayer, int aX, int aY, int aZ, int aLogLevel) {
		TileEntity tTileEntity = aPlayer.worldObj.getBlockTileEntity(aX, aY, aZ);
		if (tTileEntity == null) return null;
		
		if (tTileEntity instanceof GT_TileEntityMetaID_Machine) {
	        return ((GT_TileEntityMetaID_Machine)tTileEntity).getDebugInfo(aPlayer, aLogLevel);
		}

		if (tTileEntity instanceof BaseMetaTileEntity) {
	        return ((BaseMetaTileEntity)tTileEntity).getDebugInfo(aPlayer, aLogLevel);
		}
		
		return null;
	}
}
