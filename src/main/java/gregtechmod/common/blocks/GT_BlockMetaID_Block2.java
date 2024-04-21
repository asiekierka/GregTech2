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
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 *  net.minecraftforge.common.MinecraftForge
 */
package gregtechmod.common.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import gregtechmod.GT_Mod;
import gregtechmod.common.blocks.GT_BlockMetaID_Block;
import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.item.ItemStack;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;

public class GT_BlockMetaID_Block2
extends Block {
    public static final int Metablockcount = 13;

    public GT_BlockMetaID_Block2(int aID) {
        super(aID, Material.iron);
        this.setHardness(3.0f);
        this.setResistance(10.0f);
        this.setBlockName("BlockMetaID_Block2");
        this.setStepSound(Block.soundMetalFootstep);
        this.setTextureFile("/gregtechmod/textures/blocks2.png");
        this.setCreativeTab(GT_Mod.tabGregTech);
        this.setRequiresSelfNotify();
        for (int i = 0; i < 16; ++i) {
            MinecraftForge.setBlockHarvestLevel((Block)this, (int)i, (String)"pickaxe", (int)2);
        }
    }

    public boolean isBeaconBase(World worldObj, int x, int y, int z, int beaconX, int beaconY, int beaconZ) {
        int tMeta = worldObj.getBlockMetadata(x, y, z);
        return tMeta != 8 && tMeta != 9;
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
            return 60.0f;
        }
        if (tMeta == 1) {
            return 30.0f;
        }
        if (tMeta == 2) {
            return 30.0f;
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
            return 100.0f;
        }
        if (tMeta == 7) {
            return 45.0f;
        }
        if (tMeta == 8) {
            return 300.0f;
        }
        if (tMeta == 9) {
            return 400.0f;
        }
        if (tMeta == 10) {
            return 60.0f;
        }
        if (tMeta == 11) {
            return 900.0f;
        }
        if (tMeta == 12) {
            return 600.0f;
        }
        if (tMeta == 13) {
            return 30.0f;
        }
        if (tMeta == 14) {
            return 30.0f;
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
        if (tMeta == 8) {
            return 100.0f;
        }
        if (tMeta == 9) {
            return 200.0f;
        }
        return 3.0f;
    }

    public int getBlockTexture(IBlockAccess aWorld, int xCoord, int yCoord, int zCoord, int aSide) {
        int tMeta = aWorld.getBlockMetadata(xCoord, yCoord, zCoord);
        if (tMeta < 8 || tMeta > 9 || xCoord == 0 && yCoord == 0 && zCoord == 0 || !GT_BlockMetaID_Block.mConnectedMachineTextures) {
            return this.getBlockTextureFromSideAndMetadata(aSide, tMeta);
        }
        int tStartIndex = tMeta == 8 ? 96 : (tMeta == 9 ? 112 : 0);
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
        for (int i = 0; i < 13; ++i) {
            par3List.add(new ItemStack(par1, 1, i));
        }
    }
}

