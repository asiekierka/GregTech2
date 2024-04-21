/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  ic2.api.IWrenchable
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockContainer
 *  net.minecraft.block.material.Material
 *  net.minecraft.creativetab.CreativeTabs
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLiving
 *  net.minecraft.entity.EnumCreatureType
 *  net.minecraft.entity.item.EntityItem
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.MathHelper
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 *  net.minecraftforge.common.ForgeDirection
 *  net.minecraftforge.common.MinecraftForge
 */
package gregtechmod.common.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import gregtechmod.GT_Mod;
import gregtechmod.api.BaseMetaTileEntity;
import gregtechmod.api.GregTech_API;
import gregtechmod.api.IDebugableBlock;
import gregtechmod.api.IIgnoreRightclickOnMachine;
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
import net.minecraft.entity.Entity;
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

public class GT_BlockMetaID_Machine
extends BlockContainer
implements IDebugableBlock {
    public GT_BlockMetaID_Machine(int aID) {
        super(aID, Material.iron);
        this.setHardness(3.0f);
        this.setResistance(10.0f);
        this.setBlockName("BlockMetaID_Machine");
        this.setStepSound(Block.soundMetalFootstep);
        this.setTextureFile("/gregtechmod/textures/terrain.png");
        this.setCreativeTab(GT_Mod.tabGregTech);
        this.setRequiresSelfNotify();
        for (int i = 0; i < 16; ++i) {
            MinecraftForge.setBlockHarvestLevel((Block)this, (int)i, (String)"wrench", (int)1);
        }
    }

    public int getBlockTexture(IBlockAccess aIBlockAccess, int aX, int aY, int aZ, int aSide) {
        int tMeta = aIBlockAccess.getBlockMetadata(aX, aY, aZ);
        TileEntity tTileEntity = aIBlockAccess.getBlockTileEntity(aX, aY, aZ);
        if (tTileEntity == null) {
            return 0;
        }
        if (tTileEntity instanceof GT_TileEntityMetaID_Machine) {
            return ((GT_TileEntityMetaID_Machine)tTileEntity).getTexture(aSide, tMeta);
        }
        if (tTileEntity instanceof BaseMetaTileEntity && ((BaseMetaTileEntity)tTileEntity).mMetaTileEntity != null) {
            return ((BaseMetaTileEntity)tTileEntity).getTexture(aSide, tMeta);
        }
        return 0;
    }

    public int getBlockTextureFromSideAndMetadata(int aSide, int aMeta) {
        if (aMeta < 0 || aMeta >= 1024) {
            return 0;
        }
        if (aMeta > 0 && aMeta < 16) {
            return ((GT_TileEntityMetaID_Machine)this.createNewTileEntity(null, aMeta)).getTexture(aSide, aMeta);
        }
        if (GregTech_API.mMetaTileList[aMeta] != null) {
            return GregTech_API.mMetaTileList[aMeta].getTextureIndex(aSide, 4, true, false);
        }
        return 0;
    }

    public float getBlockHardness(World aWorld, int aX, int aY, int aZ) {
        TileEntity tTileEntity = aWorld.getBlockTileEntity(aX, aY, aZ);
        if (tTileEntity instanceof BaseMetaTileEntity) {
            if (((BaseMetaTileEntity)tTileEntity).privateAccess()) {
                return -1.0f;
            }
            if (((BaseMetaTileEntity)tTileEntity).unbreakable()) {
                return -1.0f;
            }
        }
        if (tTileEntity instanceof GT_TileEntityMetaID_Machine && ((GT_TileEntityMetaID_Machine)tTileEntity).ownerControl()) {
            return -1.0f;
        }
        return super.getBlockHardness(aWorld, aX, aY, aZ);
    }

    public float getPlayerRelativeBlockHardness(EntityPlayer aPlayer, World aWorld, int aX, int aY, int aZ) {
        TileEntity tTileEntity = aWorld.getBlockTileEntity(aX, aY, aZ);
        if (tTileEntity instanceof BaseMetaTileEntity) {
            if (((BaseMetaTileEntity)tTileEntity).privateAccess()) {
                return -1.0f;
            }
            if (((BaseMetaTileEntity)tTileEntity).unbreakable()) {
                return -1.0f;
            }
        }
        if (tTileEntity instanceof GT_TileEntityMetaID_Machine && ((GT_TileEntityMetaID_Machine)tTileEntity).ownerControl()) {
            return -1.0f;
        }
        return super.getPlayerRelativeBlockHardness(aPlayer, aWorld, aX, aY, aZ);
    }

    public boolean onBlockActivated(World aWorld, int aX, int aY, int aZ, EntityPlayer aPlayer, int par1, float par2, float par3, float par4) {
        TileEntity tTileEntity = aWorld.getBlockTileEntity(aX, aY, aZ);
        if (tTileEntity == null || aPlayer.isSneaking()) {
            return false;
        }
        ItemStack tPlayerItem = aPlayer.inventory.getCurrentItem();
        if (tPlayerItem != null && tPlayerItem.getItem() != null && tPlayerItem.getItem() instanceof IIgnoreRightclickOnMachine) {
            return false;
        }
        if (aWorld.isRemote) {
            return true;
        }
        if (tTileEntity instanceof GT_TileEntityMetaID_Machine) {
            int tMetaData;
            if (!((GT_TileEntityMetaID_Machine)tTileEntity).isUseableByPlayer(aPlayer)) {
                return false;
            }
            int tGuiID = tMetaData = aWorld.getBlockMetadata(aX, aY, aZ);
            if (tMetaData == 4) {
                tGuiID = GT_BlockMetaID_Machine.getComputerCubeGUIID(tTileEntity);
            }
            if (tMetaData == 13) {
                ((GT_TileEntity_PlayerDetector)tTileEntity).rightClick(aPlayer);
            } else {
                aPlayer.openGui((Object)GT_Mod.instance, tGuiID, aWorld, aX, aY, aZ);
            }
            return true;
        }
        if (tTileEntity instanceof BaseMetaTileEntity) {
            if (!((BaseMetaTileEntity)tTileEntity).isUseableByPlayer(aPlayer)) {
                return false;
            }
            ((BaseMetaTileEntity)tTileEntity).onRightclick(aPlayer);
            return true;
        }
        return false;
    }

    public void onBlockClicked(World aWorld, int aX, int aY, int aZ, EntityPlayer aPlayer) {
        TileEntity tTileEntity = aWorld.getBlockTileEntity(aX, aY, aZ);
        if (tTileEntity != null) {
            if (tTileEntity instanceof GT_TileEntityMetaID_Machine) {
                ((GT_TileEntityMetaID_Machine)tTileEntity).onLeftclick(aPlayer);
            } else if (tTileEntity instanceof BaseMetaTileEntity) {
                ((BaseMetaTileEntity)tTileEntity).onLeftclick(aPlayer);
            }
        }
    }

    public static int getComputerCubeGUIID(TileEntity aTileEntity) {
        switch (((GT_TileEntity_ComputerCube)aTileEntity).mMode) {
            case 1: {
                return 32;
            }
            case 2: {
                return 34;
            }
            case 3: {
                return 35;
            }
            case 4: {
                return 36;
            }
            case 5: {
                return 37;
            }
            case 6: {
                return 38;
            }
        }
        return 4;
    }

    public int getDamageValue(World world, int x, int y, int z) {
        TileEntity tileEntity = world.getBlockTileEntity(x, y, z);
        if (tileEntity == null) {
            return 0;
        }
        if (tileEntity instanceof BaseMetaTileEntity) {
            return ((BaseMetaTileEntity)tileEntity).getMetaTileID();
        }
        return world.getBlockMetadata(x, y, z);
    }

    public ArrayList getBlockDropped(World world, int x, int y, int z, int metadata, int fortune) {
        ArrayList<ItemStack> ret = new ArrayList<ItemStack>();
        ret.add(GT_MetaItem_Component.instance.getStack(22, 1));
        return ret;
    }

    public void breakBlock(World aWorld, int aX, int aY, int aZ, int par5, int par6) {
        this.dropItems(aWorld, aX, aY, aZ);
        GT_Mod.causeMachineUpdate(aWorld, aX, aY, aZ);
        super.breakBlock(aWorld, aX, aY, aZ, par5, par6);
    }

    private void dropItems(World aWorld, int aX, int aY, int aZ) {
        float factor;
        EntityItem entityItem;
        float rz;
        float ry;
        float rx;
        ItemStack item;
        int i;
        Random rand = new Random();
        TileEntity tTileEntity = aWorld.getBlockTileEntity(aX, aY, aZ);
        if (tTileEntity == null) {
            return;
        }
        if (tTileEntity instanceof GT_TileEntityMetaID_Machine) {
            GT_TileEntityMetaID_Machine inventory = (GT_TileEntityMetaID_Machine)tTileEntity;
            for (i = 0; i < inventory.getSizeInventory(); ++i) {
                item = inventory.getStackInSlot(i);
                if (item == null || item.stackSize <= 0 || !inventory.isValidSlot(i)) continue;
                rx = rand.nextFloat() * 0.8f + 0.1f;
                ry = rand.nextFloat() * 0.8f + 0.1f;
                rz = rand.nextFloat() * 0.8f + 0.1f;
                entityItem = new EntityItem(aWorld, (double)((float)aX + rx), (double)((float)aY + ry), (double)((float)aZ + rz), new ItemStack(item.itemID, item.stackSize, item.getItemDamage()));
                if (item.hasTagCompound()) {
                    entityItem.getEntityItem().setTagCompound((NBTTagCompound)item.getTagCompound().copy());
                }
                factor = 0.05f;
                entityItem.motionX = rand.nextGaussian() * (double)factor;
                entityItem.motionY = rand.nextGaussian() * (double)factor + (double)0.2f;
                entityItem.motionZ = rand.nextGaussian() * (double)factor;
                aWorld.spawnEntityInWorld((Entity)entityItem);
                item.stackSize = 0;
            }
        }
        if (tTileEntity instanceof BaseMetaTileEntity && ((BaseMetaTileEntity)tTileEntity).mMetaTileEntity != null) {
            BaseMetaTileEntity inventory = (BaseMetaTileEntity)tTileEntity;
            try {
                for (i = 0; i < inventory.getSizeInventory(); ++i) {
                    item = inventory.getStackInSlot(i);
                    if (item == null || item.stackSize <= 0 || !inventory.isValidSlot(i)) continue;
                    rx = rand.nextFloat() * 0.8f + 0.1f;
                    ry = rand.nextFloat() * 0.8f + 0.1f;
                    rz = rand.nextFloat() * 0.8f + 0.1f;
                    entityItem = new EntityItem(aWorld, (double)((float)aX + rx), (double)((float)aY + ry), (double)((float)aZ + rz), new ItemStack(item.itemID, item.stackSize, item.getItemDamage()));
                    if (item.hasTagCompound()) {
                        entityItem.getEntityItem().setTagCompound((NBTTagCompound)item.getTagCompound().copy());
                    }
                    factor = 0.05f;
                    entityItem.motionX = rand.nextGaussian() * (double)factor;
                    entityItem.motionY = rand.nextGaussian() * (double)factor + (double)0.2f;
                    entityItem.motionZ = rand.nextGaussian() * (double)factor;
                    aWorld.spawnEntityInWorld((Entity)entityItem);
                    item.stackSize = 0;
                }
            }
            catch (Throwable e) {
                // empty catch block
            }
        }
    }

    public boolean isProvidingWeakPower(IBlockAccess var1, int x, int y, int z, int var5) {
        TileEntity tTileEntity = var1.getBlockTileEntity(x, y, z);
        if (tTileEntity == null) {
            return false;
        }
        if (tTileEntity instanceof GT_TileEntityMetaID_Machine) {
            return ((GT_TileEntityMetaID_Machine)tTileEntity).mRedstone;
        }
        if (tTileEntity instanceof BaseMetaTileEntity) {
            return ((BaseMetaTileEntity)tTileEntity).mRedstone;
        }
        return false;
    }

    public boolean removeBlockByPlayer(World world, EntityPlayer player, int x, int y, int z) {
        TileEntity tTileEntity = world.getBlockTileEntity(x, y, z);
        if (GT_Mod.instance.mMachineNonWrenchExplosions && tTileEntity != null && (player == null || !player.capabilities.isCreativeMode)) {
            if (tTileEntity instanceof GT_TileEntityMetaID_Machine) {
                ((GT_TileEntityMetaID_Machine)tTileEntity).doEnergyExplosion();
            } else if (tTileEntity instanceof BaseMetaTileEntity) {
                ((BaseMetaTileEntity)tTileEntity).doEnergyExplosion();
            }
        }
        return world.setBlockWithNotify(x, y, z, 0);
    }

    public void dropBlockAsItemWithChance(World world, int x, int y, int z, int par5, float chance, int par7) {
        if (!world.isRemote && GT_Mod.instance.mMachineNonWrenchExplosions) {
            TileEntity tTileEntity = world.getBlockTileEntity(x, y, z);
            if (tTileEntity != null && chance < 1.0f) {
                if (tTileEntity instanceof GT_TileEntityMetaID_Machine) {
                    ((GT_TileEntityMetaID_Machine)tTileEntity).doEnergyExplosion();
                } else if (tTileEntity instanceof BaseMetaTileEntity) {
                    ((BaseMetaTileEntity)tTileEntity).doEnergyExplosion();
                }
            } else {
                super.dropBlockAsItemWithChance(world, x, y, z, par5, chance, par7);
            }
        }
    }

    public boolean canConnectRedstone(IBlockAccess var1, int var2, int var3, int var4, int var5) {
        return true;
    }

    public boolean canProvidePower() {
        return true;
    }

    public boolean isBlockNormalCube(World world, int x, int y, int z) {
        return false;
    }

    public boolean isBlockSolidOnSide(World world, int x, int y, int z, ForgeDirection side) {
        return true;
    }

    public boolean canBeReplacedByLeaves(World world, int x, int y, int z) {
        return false;
    }

    public TileEntity createNewTileEntity(World aWorld) {
        return GT_Mod.constructBaseMetaTileEntity();
    }

    public TileEntity createNewTileEntity(World aWorld, int aMeta) {
        switch (aMeta) {
            case 1: {
                return new GT_TileEntity_Fusionreactor();
            }
            case 2: {
                return new GT_TileEntity_Lightningrod();
            }
            case 3: {
                return new GT_TileEntity_Quantumchest();
            }
            case 4: {
                return new GT_TileEntity_ComputerCube();
            }
            case 5: {
                return new GT_TileEntity_UUMAssembler();
            }
            case 6: {
                return new GT_TileEntity_Sonictron();
            }
            case 7: {
                return new GT_TileEntity_LESU();
            }
            case 8: {
                return new GT_TileEntity_IDSU();
            }
            case 9: {
                return new GT_TileEntity_AESU();
            }
            case 10: {
                return new GT_TileEntity_ChargeOMat();
            }
            case 11: {
                return new GT_TileEntity_Centrifuge();
            }
            case 12: {
                return new GT_TileEntity_Superconductor();
            }
            case 13: {
                return new GT_TileEntity_PlayerDetector();
            }
            case 14: {
                return new GT_TileEntity_Matterfabricator();
            }
            case 15: {
                return new GT_TileEntity_Supercondensator();
            }
        }
        return GT_Mod.constructBaseMetaTileEntity();
    }

    @SideOnly(value=Side.CLIENT)
    public void getSubBlocks(int par1, CreativeTabs par2CreativeTabs, List par3List) {
        for (int i = 1; i < GregTech_API.mMetaTileList.length; ++i) {
            if (i < 16 && i != 3) {
                par3List.add(new ItemStack(par1, 1, i));
                continue;
            }
            if (i <= 15 || GregTech_API.mMetaTileList[i] == null) continue;
            par3List.add(new ItemStack(par1, 1, i));
        }
    }

    public boolean canCreatureSpawn(EnumCreatureType type, World world, int x, int y, int z) {
        return false;
    }

    public void onBlockPlacedBy(World aWorld, int aX, int aY, int aZ, EntityLiving aPlayer) {
        TileEntity tTileEntity = aWorld.getBlockTileEntity(aX, aY, aZ);
        if (tTileEntity == null) {
            return;
        }
        if (tTileEntity instanceof IWrenchable) {
            IWrenchable var6 = (IWrenchable)tTileEntity;
            if (aPlayer == null) {
                var6.setFacing((short)1);
            } else {
                int var7 = MathHelper.floor_double((double)((double)(aPlayer.rotationYaw * 4.0f / 360.0f) + 0.5)) & 3;
                int var8 = Math.round(aPlayer.rotationPitch);
                if (var8 >= 65 && var6.wrenchCanSetFacing(null, 1)) {
                    var6.setFacing((short)1);
                } else if (var8 <= -65 && var6.wrenchCanSetFacing(null, 0)) {
                    var6.setFacing((short)0);
                } else {
                    switch (var7) {
                        case 0: {
                            var6.setFacing((short)2);
                            break;
                        }
                        case 1: {
                            var6.setFacing((short)5);
                            break;
                        }
                        case 2: {
                            var6.setFacing((short)3);
                            break;
                        }
                        case 3: {
                            var6.setFacing((short)4);
                        }
                    }
                }
            }
        }
    }

    public void onBlockAdded(World aWorld, int aX, int aY, int aZ) {
        if (GT_Mod.isMachineBlock(this.blockID, aWorld.getBlockMetadata(aX, aY, aZ))) {
            GT_Mod.causeMachineUpdate(aWorld, aX, aY, aZ);
        }
    }

    @Override
    public ArrayList getDebugInfo(EntityPlayer aPlayer, int aX, int aY, int aZ, int aLogLevel) {
        TileEntity tTileEntity = aPlayer.worldObj.getBlockTileEntity(aX, aY, aZ);
        if (tTileEntity == null) {
            return null;
        }
        if (tTileEntity instanceof GT_TileEntityMetaID_Machine) {
            return ((GT_TileEntityMetaID_Machine)tTileEntity).getDebugInfo(aPlayer, aLogLevel);
        }
        if (tTileEntity instanceof BaseMetaTileEntity) {
            return ((BaseMetaTileEntity)tTileEntity).getDebugInfo(aPlayer, aLogLevel);
        }
        return null;
    }

    public int getFlammability(IBlockAccess world, int x, int y, int z, int metadata, ForgeDirection face) {
        return 0;
    }

    public boolean isFlammable(IBlockAccess world, int x, int y, int z, int metadata, ForgeDirection face) {
        return GT_Mod.instance.mMachineFlammable;
    }

    public int getFireSpreadSpeed(World world, int x, int y, int z, int metadata, ForgeDirection face) {
        return GT_Mod.instance.mMachineFlammable ? 100 : 0;
    }

    public boolean isFireSource(World world, int x, int y, int z, int metadata, ForgeDirection side) {
        return GT_Mod.instance.mMachineFlammable;
    }
}

