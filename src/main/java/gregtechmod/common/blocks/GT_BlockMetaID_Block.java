/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.block.material.Material
 *  net.minecraft.creativetab.CreativeTabs
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EnumCreatureType
 *  net.minecraft.item.ItemStack
 *  net.minecraft.world.ChunkPosition
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 *  net.minecraftforge.common.MinecraftForge
 */
package gregtechmod.common.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import gregtechmod.GT_Mod;
import gregtechmod.common.tileentities.GT_TileEntity_LESU;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.item.ItemStack;
import net.minecraft.world.ChunkPosition;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;

public class GT_BlockMetaID_Block
extends Block {
    public static final int Metablockcount = 16;
    public static boolean mConnectedMachineTextures = true;

    public GT_BlockMetaID_Block(int aID) {
        super(aID, Material.iron);
        this.setHardness(3.0f);
        this.setResistance(10.0f);
        this.setBlockName("BlockMetaID_Block");
        this.setStepSound(Block.soundMetalFootstep);
        this.setTextureFile("/gregtechmod/textures/blocks.png");
        this.setCreativeTab(GT_Mod.tabGregTech);
        this.setRequiresSelfNotify();
        for (int i = 0; i < 16; ++i) {
            MinecraftForge.setBlockHarvestLevel((Block)this, (int)i, (String)"pickaxe", (int)2);
        }
    }

    public boolean isBeaconBase(World aWorld, int aX, int aY, int aZ, int beaconX, int beaconY, int beaconZ) {
        return !GT_Mod.isMachineBlock(this.blockID, aWorld.getBlockMetadata(aX, aY, aZ));
    }

    public void breakBlock(World aWorld, int aX, int aY, int aZ, int par5, int par6) {
        if (aWorld.getBlockMetadata(aX, aY, aZ) == 6) {
            GT_BlockMetaID_Block.stepToFindOrCallLESUController(aWorld, aX, aY, aZ, new ArrayList(), true);
        }
        if (GT_Mod.isMachineBlock(this.blockID, aWorld.getBlockMetadata(aX, aY, aZ))) {
            GT_Mod.causeMachineUpdate(aWorld, aX, aY, aZ);
        }
    }

    public void onBlockAdded(World aWorld, int aX, int aY, int aZ) {
        if (aWorld.getBlockMetadata(aX, aY, aZ) == 6) {
            GT_BlockMetaID_Block.stepToFindOrCallLESUController(aWorld, aX, aY, aZ, new ArrayList(), true);
        }
        if (GT_Mod.isMachineBlock(this.blockID, aWorld.getBlockMetadata(aX, aY, aZ))) {
            GT_Mod.causeMachineUpdate(aWorld, aX, aY, aZ);
        }
    }

    public boolean canCreatureSpawn(EnumCreatureType type, World world, int x, int y, int z) {
        return false;
    }

    public float getExplosionResistance(Entity par1Entity, World world, int x, int y, int z, double explosionX, double explosionY, double explosionZ) {
        if (world == null) {
            return 0.0f;
        }
        Integer tMeta = world.getBlockMetadata(x, y, z);
        if (tMeta == null) {
            tMeta = 0;
        }
        if (tMeta == 0) {
            return 30.0f;
        }
        if (tMeta == 1) {
            return 30.0f;
        }
        if (tMeta == 2) {
            return 300.0f;
        }
        if (tMeta == 3) {
            return 30.0f;
        }
        if (tMeta == 4) {
            return 30.0f;
        }
        if (tMeta == 5) {
            return 30.0f;
        }
        if (tMeta == 6) {
            return 30.0f;
        }
        if (tMeta == 7) {
            return 30.0f;
        }
        if (tMeta == 8) {
            return 200.0f;
        }
        if (tMeta == 9) {
            return 100.0f;
        }
        if (tMeta == 10) {
            return 200.0f;
        }
        if (tMeta == 11) {
            return 100.0f;
        }
        if (tMeta == 12) {
            return 30.0f;
        }
        if (tMeta == 13) {
            return 30.0f;
        }
        if (tMeta == 14) {
            return 60.0f;
        }
        if (tMeta == 15) {
            return 30.0f;
        }
        return super.getExplosionResistance(par1Entity, world, x, y, z, explosionX, explosionY, explosionZ);
    }

    public float getBlockHardness(World world, int x, int y, int z) {
        if (world == null) {
            return 0.0f;
        }
        Integer tMeta = world.getBlockMetadata(x, y, z);
        if (tMeta == null) {
            tMeta = 0;
        }
        if (tMeta == 2) {
            return 100.0f;
        }
        if (tMeta == 8) {
            return 10.0f;
        }
        if (tMeta == 9) {
            return 10.0f;
        }
        if (tMeta == 10) {
            return 10.0f;
        }
        return 3.0f;
    }

    public int getBlockTextureFromSideAndMetadata(int aSide, int aMeta) {
        return aMeta + aSide * 16;
    }

    public int damageDropped(int par1) {
        return par1;
    }

    public int getDamageValue(World par1World, int par2, int par3, int par4) {
        return par1World.getBlockMetadata(par2, par3, par4);
    }

    public int quantityDropped(Random par1Random) {
        return 1;
    }

    public int idDropped(int par1, Random par2Random, int par3) {
        return this.blockID;
    }

    @SideOnly(value=Side.CLIENT)
    public void getSubBlocks(int par1, CreativeTabs par2CreativeTabs, List par3List) {
        for (int i = 1; i < 16; ++i) {
            par3List.add(new ItemStack(par1, 1, i));
        }
    }

    public static boolean isLESUBlock(World aWorld, int aX, int aY, int aZ) {
        return GT_BlockMetaID_Block.isLESUStorage(aWorld, aX, aY, aZ) || GT_BlockMetaID_Block.isLESUController(aWorld, aX, aY, aZ);
    }

    public static boolean isLESUStorage(World aWorld, int aX, int aY, int aZ) {
        return aWorld.getBlockId(aX, aY, aZ) == GT_Mod.instance.mBlocks[0].blockID && aWorld.getBlockMetadata(aX, aY, aZ) == 6;
    }

    public static boolean isLESUController(World aWorld, int aX, int aY, int aZ) {
        return aWorld.getBlockId(aX, aY, aZ) == GT_Mod.instance.mBlocks[1].blockID && aWorld.getBlockMetadata(aX, aY, aZ) == 7;
    }

    private void createLESUBlock(World aWorld, int aX, int aY, int aZ) {
        GT_BlockMetaID_Block.stepToFindOrCallLESUController(aWorld, aX, aY, aZ, new ArrayList(), true);
    }

    private void destroyLESUBlock(World aWorld, int aX, int aY, int aZ) {
        GT_BlockMetaID_Block.stepToFindOrCallLESUController(aWorld, aX, aY, aZ, new ArrayList(), true);
    }

    public static int stepToFindOrCallLESUController(World aWorld, int aX, int aY, int aZ, ArrayList aList, boolean aCall) {
        GT_TileEntity_LESU tTileEntity;
        ChunkPosition tCoord = new ChunkPosition(aX, aY, aZ);
        aList.add(tCoord);
        int tControllerCount = 0;
        if (GT_BlockMetaID_Block.isLESUController(aWorld, aX, aY, aZ) && (tTileEntity = (GT_TileEntity_LESU)aWorld.getBlockTileEntity(aX, aY, aZ)) != null) {
            if (aCall) {
                tTileEntity.notifyLESUchange();
            }
            ++tControllerCount;
        }
        if (GT_BlockMetaID_Block.isLESUBlock(aWorld, aX + 1, aY, aZ) && !aList.contains(new ChunkPosition(aX + 1, aY, aZ))) {
            tControllerCount += GT_BlockMetaID_Block.stepToFindOrCallLESUController(aWorld, aX + 1, aY, aZ, aList, aCall);
        }
        if (GT_BlockMetaID_Block.isLESUBlock(aWorld, aX - 1, aY, aZ) && !aList.contains(new ChunkPosition(aX - 1, aY, aZ))) {
            tControllerCount += GT_BlockMetaID_Block.stepToFindOrCallLESUController(aWorld, aX - 1, aY, aZ, aList, aCall);
        }
        if (GT_BlockMetaID_Block.isLESUBlock(aWorld, aX, aY + 1, aZ) && !aList.contains(new ChunkPosition(aX, aY + 1, aZ))) {
            tControllerCount += GT_BlockMetaID_Block.stepToFindOrCallLESUController(aWorld, aX, aY + 1, aZ, aList, aCall);
        }
        if (GT_BlockMetaID_Block.isLESUBlock(aWorld, aX, aY - 1, aZ) && !aList.contains(new ChunkPosition(aX, aY - 1, aZ))) {
            tControllerCount += GT_BlockMetaID_Block.stepToFindOrCallLESUController(aWorld, aX, aY - 1, aZ, aList, aCall);
        }
        if (GT_BlockMetaID_Block.isLESUBlock(aWorld, aX, aY, aZ + 1) && !aList.contains(new ChunkPosition(aX, aY, aZ + 1))) {
            tControllerCount += GT_BlockMetaID_Block.stepToFindOrCallLESUController(aWorld, aX, aY, aZ + 1, aList, aCall);
        }
        if (GT_BlockMetaID_Block.isLESUBlock(aWorld, aX, aY, aZ - 1) && !aList.contains(new ChunkPosition(aX, aY, aZ - 1))) {
            tControllerCount += GT_BlockMetaID_Block.stepToFindOrCallLESUController(aWorld, aX, aY, aZ - 1, aList, aCall);
        }
        return tControllerCount;
    }

    public static int stepToGetLESUAmount(World aWorld, int aX, int aY, int aZ, ArrayList aList) {
        ChunkPosition tCoord = new ChunkPosition(aX, aY, aZ);
        aList.add(tCoord);
        int tStorageCount = 1;
        if (GT_BlockMetaID_Block.isLESUStorage(aWorld, aX + 1, aY, aZ) && !aList.contains(new ChunkPosition(aX + 1, aY, aZ))) {
            tStorageCount += GT_BlockMetaID_Block.stepToGetLESUAmount(aWorld, aX + 1, aY, aZ, aList);
        }
        if (GT_BlockMetaID_Block.isLESUStorage(aWorld, aX - 1, aY, aZ) && !aList.contains(new ChunkPosition(aX - 1, aY, aZ))) {
            tStorageCount += GT_BlockMetaID_Block.stepToGetLESUAmount(aWorld, aX - 1, aY, aZ, aList);
        }
        if (GT_BlockMetaID_Block.isLESUStorage(aWorld, aX, aY + 1, aZ) && !aList.contains(new ChunkPosition(aX, aY + 1, aZ))) {
            tStorageCount += GT_BlockMetaID_Block.stepToGetLESUAmount(aWorld, aX, aY + 1, aZ, aList);
        }
        if (GT_BlockMetaID_Block.isLESUStorage(aWorld, aX, aY - 1, aZ) && !aList.contains(new ChunkPosition(aX, aY - 1, aZ))) {
            tStorageCount += GT_BlockMetaID_Block.stepToGetLESUAmount(aWorld, aX, aY - 1, aZ, aList);
        }
        if (GT_BlockMetaID_Block.isLESUStorage(aWorld, aX, aY, aZ + 1) && !aList.contains(new ChunkPosition(aX, aY, aZ + 1))) {
            tStorageCount += GT_BlockMetaID_Block.stepToGetLESUAmount(aWorld, aX, aY, aZ + 1, aList);
        }
        if (GT_BlockMetaID_Block.isLESUStorage(aWorld, aX, aY, aZ - 1) && !aList.contains(new ChunkPosition(aX, aY, aZ - 1))) {
            tStorageCount += GT_BlockMetaID_Block.stepToGetLESUAmount(aWorld, aX, aY, aZ - 1, aList);
        }
        return tStorageCount;
    }

    public int getBlockTexture(IBlockAccess aWorld, int xCoord, int yCoord, int zCoord, int aSide) {
        int tMeta = aWorld.getBlockMetadata(xCoord, yCoord, zCoord);
        if (tMeta < 13 || xCoord == 0 && yCoord == 0 && zCoord == 0 || !mConnectedMachineTextures) {
            return this.getBlockTextureFromSideAndMetadata(aSide, tMeta);
        }
        int tStartIndex = tMeta == 15 ? 224 : (tMeta == 14 ? 112 : 96);
        boolean[] tConnectedSides = new boolean[]{aWorld.getBlockId(xCoord, yCoord - 1, zCoord) == this.blockID && aWorld.getBlockMetadata(xCoord, yCoord - 1, zCoord) == tMeta, aWorld.getBlockId(xCoord, yCoord + 1, zCoord) == this.blockID && aWorld.getBlockMetadata(xCoord, yCoord + 1, zCoord) == tMeta, aWorld.getBlockId(xCoord + 1, yCoord, zCoord) == this.blockID && aWorld.getBlockMetadata(xCoord + 1, yCoord, zCoord) == tMeta, aWorld.getBlockId(xCoord, yCoord, zCoord + 1) == this.blockID && aWorld.getBlockMetadata(xCoord, yCoord, zCoord + 1) == tMeta, aWorld.getBlockId(xCoord - 1, yCoord, zCoord) == this.blockID && aWorld.getBlockMetadata(xCoord - 1, yCoord, zCoord) == tMeta, aWorld.getBlockId(xCoord, yCoord, zCoord - 1) == this.blockID && aWorld.getBlockMetadata(xCoord, yCoord, zCoord - 1) == tMeta};
        switch (aSide) {
            case 0: {
                if (tConnectedSides[0]) {
                    return tStartIndex + 7;
                }
                if (tConnectedSides[4] && tConnectedSides[5] && tConnectedSides[2] && tConnectedSides[3]) {
                    return tStartIndex + 6;
                }
                if (!tConnectedSides[4] && tConnectedSides[5] && tConnectedSides[2] && tConnectedSides[3]) {
                    return tStartIndex + 2;
                }
                if (tConnectedSides[4] && !tConnectedSides[5] && tConnectedSides[2] && tConnectedSides[3]) {
                    return tStartIndex + 3;
                }
                if (tConnectedSides[4] && tConnectedSides[5] && !tConnectedSides[2] && tConnectedSides[3]) {
                    return tStartIndex + 4;
                }
                if (tConnectedSides[4] && tConnectedSides[5] && tConnectedSides[2] && !tConnectedSides[3]) {
                    return tStartIndex + 5;
                }
                if (!tConnectedSides[4] && !tConnectedSides[5] && tConnectedSides[2] && tConnectedSides[3]) {
                    return tStartIndex + 8;
                }
                if (tConnectedSides[4] && !tConnectedSides[5] && !tConnectedSides[2] && tConnectedSides[3]) {
                    return tStartIndex + 9;
                }
                if (tConnectedSides[4] && tConnectedSides[5] && !tConnectedSides[2] && !tConnectedSides[3]) {
                    return tStartIndex + 10;
                }
                if (!tConnectedSides[4] && tConnectedSides[5] && tConnectedSides[2] && !tConnectedSides[3]) {
                    return tStartIndex + 11;
                }
                if (!(tConnectedSides[4] || tConnectedSides[5] || tConnectedSides[2] || tConnectedSides[3])) {
                    return tStartIndex + 7;
                }
                if (!tConnectedSides[4] && !tConnectedSides[2]) {
                    return tStartIndex + 1;
                }
                if (!tConnectedSides[5] && !tConnectedSides[3]) {
                    return tStartIndex + 0;
                }
            }
            case 1: {
                if (tConnectedSides[1]) {
                    return tStartIndex + 7;
                }
                if (tConnectedSides[4] && tConnectedSides[5] && tConnectedSides[2] && tConnectedSides[3]) {
                    return tStartIndex + 6;
                }
                if (!tConnectedSides[4] && tConnectedSides[5] && tConnectedSides[2] && tConnectedSides[3]) {
                    return tStartIndex + 2;
                }
                if (tConnectedSides[4] && !tConnectedSides[5] && tConnectedSides[2] && tConnectedSides[3]) {
                    return tStartIndex + 3;
                }
                if (tConnectedSides[4] && tConnectedSides[5] && !tConnectedSides[2] && tConnectedSides[3]) {
                    return tStartIndex + 4;
                }
                if (tConnectedSides[4] && tConnectedSides[5] && tConnectedSides[2] && !tConnectedSides[3]) {
                    return tStartIndex + 5;
                }
                if (!tConnectedSides[4] && !tConnectedSides[5] && tConnectedSides[2] && tConnectedSides[3]) {
                    return tStartIndex + 8;
                }
                if (tConnectedSides[4] && !tConnectedSides[5] && !tConnectedSides[2] && tConnectedSides[3]) {
                    return tStartIndex + 9;
                }
                if (tConnectedSides[4] && tConnectedSides[5] && !tConnectedSides[2] && !tConnectedSides[3]) {
                    return tStartIndex + 10;
                }
                if (!tConnectedSides[4] && tConnectedSides[5] && tConnectedSides[2] && !tConnectedSides[3]) {
                    return tStartIndex + 11;
                }
                if (!(tConnectedSides[4] || tConnectedSides[5] || tConnectedSides[2] || tConnectedSides[3])) {
                    return tStartIndex + 7;
                }
                if (!tConnectedSides[2] && !tConnectedSides[4]) {
                    return tStartIndex + 1;
                }
                if (!tConnectedSides[3] && !tConnectedSides[5]) {
                    return tStartIndex + 0;
                }
            }
            case 2: {
                if (tConnectedSides[5]) {
                    return tStartIndex + 7;
                }
                if (tConnectedSides[2] && tConnectedSides[0] && tConnectedSides[4] && tConnectedSides[1]) {
                    return tStartIndex + 6;
                }
                if (!tConnectedSides[2] && tConnectedSides[0] && tConnectedSides[4] && tConnectedSides[1]) {
                    return tStartIndex + 2;
                }
                if (tConnectedSides[2] && !tConnectedSides[0] && tConnectedSides[4] && tConnectedSides[1]) {
                    return tStartIndex + 5;
                }
                if (tConnectedSides[2] && tConnectedSides[0] && !tConnectedSides[4] && tConnectedSides[1]) {
                    return tStartIndex + 4;
                }
                if (tConnectedSides[2] && tConnectedSides[0] && tConnectedSides[4] && !tConnectedSides[1]) {
                    return tStartIndex + 3;
                }
                if (!tConnectedSides[2] && !tConnectedSides[0] && tConnectedSides[4] && tConnectedSides[1]) {
                    return tStartIndex + 11;
                }
                if (tConnectedSides[2] && !tConnectedSides[0] && !tConnectedSides[4] && tConnectedSides[1]) {
                    return tStartIndex + 10;
                }
                if (tConnectedSides[2] && tConnectedSides[0] && !tConnectedSides[4] && !tConnectedSides[1]) {
                    return tStartIndex + 9;
                }
                if (!tConnectedSides[2] && tConnectedSides[0] && tConnectedSides[4] && !tConnectedSides[1]) {
                    return tStartIndex + 8;
                }
                if (!(tConnectedSides[2] || tConnectedSides[0] || tConnectedSides[4] || tConnectedSides[1])) {
                    return tStartIndex + 7;
                }
                if (!tConnectedSides[2] && !tConnectedSides[4]) {
                    return tStartIndex + 1;
                }
                if (!tConnectedSides[0] && !tConnectedSides[1]) {
                    return tStartIndex + 0;
                }
            }
            case 3: {
                if (tConnectedSides[3]) {
                    return tStartIndex + 7;
                }
                if (tConnectedSides[2] && tConnectedSides[0] && tConnectedSides[4] && tConnectedSides[1]) {
                    return tStartIndex + 6;
                }
                if (!tConnectedSides[2] && tConnectedSides[0] && tConnectedSides[4] && tConnectedSides[1]) {
                    return tStartIndex + 4;
                }
                if (tConnectedSides[2] && !tConnectedSides[0] && tConnectedSides[4] && tConnectedSides[1]) {
                    return tStartIndex + 5;
                }
                if (tConnectedSides[2] && tConnectedSides[0] && !tConnectedSides[4] && tConnectedSides[1]) {
                    return tStartIndex + 2;
                }
                if (tConnectedSides[2] && tConnectedSides[0] && tConnectedSides[4] && !tConnectedSides[1]) {
                    return tStartIndex + 3;
                }
                if (!tConnectedSides[2] && !tConnectedSides[0] && tConnectedSides[4] && tConnectedSides[1]) {
                    return tStartIndex + 10;
                }
                if (tConnectedSides[2] && !tConnectedSides[0] && !tConnectedSides[4] && tConnectedSides[1]) {
                    return tStartIndex + 11;
                }
                if (tConnectedSides[2] && tConnectedSides[0] && !tConnectedSides[4] && !tConnectedSides[1]) {
                    return tStartIndex + 8;
                }
                if (!tConnectedSides[2] && tConnectedSides[0] && tConnectedSides[4] && !tConnectedSides[1]) {
                    return tStartIndex + 9;
                }
                if (!(tConnectedSides[2] || tConnectedSides[0] || tConnectedSides[4] || tConnectedSides[1])) {
                    return tStartIndex + 7;
                }
                if (!tConnectedSides[2] && !tConnectedSides[4]) {
                    return tStartIndex + 1;
                }
                if (!tConnectedSides[0] && !tConnectedSides[1]) {
                    return tStartIndex + 0;
                }
            }
            case 4: {
                if (tConnectedSides[4]) {
                    return tStartIndex + 7;
                }
                if (tConnectedSides[0] && tConnectedSides[3] && tConnectedSides[1] && tConnectedSides[5]) {
                    return tStartIndex + 6;
                }
                if (!tConnectedSides[0] && tConnectedSides[3] && tConnectedSides[1] && tConnectedSides[5]) {
                    return tStartIndex + 5;
                }
                if (tConnectedSides[0] && !tConnectedSides[3] && tConnectedSides[1] && tConnectedSides[5]) {
                    return tStartIndex + 4;
                }
                if (tConnectedSides[0] && tConnectedSides[3] && !tConnectedSides[1] && tConnectedSides[5]) {
                    return tStartIndex + 3;
                }
                if (tConnectedSides[0] && tConnectedSides[3] && tConnectedSides[1] && !tConnectedSides[5]) {
                    return tStartIndex + 2;
                }
                if (!tConnectedSides[0] && !tConnectedSides[3] && tConnectedSides[1] && tConnectedSides[5]) {
                    return tStartIndex + 10;
                }
                if (tConnectedSides[0] && !tConnectedSides[3] && !tConnectedSides[1] && tConnectedSides[5]) {
                    return tStartIndex + 9;
                }
                if (tConnectedSides[0] && tConnectedSides[3] && !tConnectedSides[1] && !tConnectedSides[5]) {
                    return tStartIndex + 8;
                }
                if (!tConnectedSides[0] && tConnectedSides[3] && tConnectedSides[1] && !tConnectedSides[5]) {
                    return tStartIndex + 11;
                }
                if (!(tConnectedSides[0] || tConnectedSides[3] || tConnectedSides[1] || tConnectedSides[5])) {
                    return tStartIndex + 7;
                }
                if (!tConnectedSides[0] && !tConnectedSides[1]) {
                    return tStartIndex + 0;
                }
                if (!tConnectedSides[3] && !tConnectedSides[5]) {
                    return tStartIndex + 1;
                }
            }
            case 5: {
                if (tConnectedSides[2]) {
                    return tStartIndex + 7;
                }
                if (tConnectedSides[0] && tConnectedSides[3] && tConnectedSides[1] && tConnectedSides[5]) {
                    return tStartIndex + 6;
                }
                if (!tConnectedSides[0] && tConnectedSides[3] && tConnectedSides[1] && tConnectedSides[5]) {
                    return tStartIndex + 5;
                }
                if (tConnectedSides[0] && !tConnectedSides[3] && tConnectedSides[1] && tConnectedSides[5]) {
                    return tStartIndex + 2;
                }
                if (tConnectedSides[0] && tConnectedSides[3] && !tConnectedSides[1] && tConnectedSides[5]) {
                    return tStartIndex + 3;
                }
                if (tConnectedSides[0] && tConnectedSides[3] && tConnectedSides[1] && !tConnectedSides[5]) {
                    return tStartIndex + 4;
                }
                if (!tConnectedSides[0] && !tConnectedSides[3] && tConnectedSides[1] && tConnectedSides[5]) {
                    return tStartIndex + 11;
                }
                if (tConnectedSides[0] && !tConnectedSides[3] && !tConnectedSides[1] && tConnectedSides[5]) {
                    return tStartIndex + 8;
                }
                if (tConnectedSides[0] && tConnectedSides[3] && !tConnectedSides[1] && !tConnectedSides[5]) {
                    return tStartIndex + 9;
                }
                if (!tConnectedSides[0] && tConnectedSides[3] && tConnectedSides[1] && !tConnectedSides[5]) {
                    return tStartIndex + 10;
                }
                if (!(tConnectedSides[0] || tConnectedSides[3] || tConnectedSides[1] || tConnectedSides[5])) {
                    return tStartIndex + 7;
                }
                if (!tConnectedSides[0] && !tConnectedSides[1]) {
                    return tStartIndex + 0;
                }
                if (tConnectedSides[3] || tConnectedSides[5]) break;
                return tStartIndex + 1;
            }
        }
        return tStartIndex + 7;
    }
}

