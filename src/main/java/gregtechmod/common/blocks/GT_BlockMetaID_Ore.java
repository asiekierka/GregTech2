/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.block.material.Material
 *  net.minecraft.creativetab.CreativeTabs
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 *  net.minecraftforge.common.MinecraftForge
 */
package gregtechmod.common.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import gregtechmod.GT_Mod;
import gregtechmod.common.GT_ModHandler;
import gregtechmod.common.GT_OreDictUnificator;
import gregtechmod.common.items.GT_MetaItem_Dust;
import gregtechmod.common.items.GT_MetaItem_Material;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;

public class GT_BlockMetaID_Ore
extends Block {
    public static final int Metablockcount = 13;

    public GT_BlockMetaID_Ore(int aID) {
        super(aID, Material.rock);
        this.setHardness(3.0f);
        this.setResistance(10.0f);
        this.setBlockName("BlockMetaID_Ore");
        this.setStepSound(Block.soundStoneFootstep);
        this.setTextureFile("/gregtechmod/textures/blocks.png");
        this.setCreativeTab(GT_Mod.tabGregTech);
        this.setRequiresSelfNotify();
        MinecraftForge.setBlockHarvestLevel((Block)this, (int)0, (String)"pickaxe", (int)1);
        MinecraftForge.setBlockHarvestLevel((Block)this, (int)1, (String)"pickaxe", (int)1);
        MinecraftForge.setBlockHarvestLevel((Block)this, (int)2, (String)"pickaxe", (int)3);
        MinecraftForge.setBlockHarvestLevel((Block)this, (int)3, (String)"pickaxe", (int)2);
        MinecraftForge.setBlockHarvestLevel((Block)this, (int)4, (String)"pickaxe", (int)2);
        MinecraftForge.setBlockHarvestLevel((Block)this, (int)5, (String)"pickaxe", (int)1);
        MinecraftForge.setBlockHarvestLevel((Block)this, (int)6, (String)"pickaxe", (int)1);
        MinecraftForge.setBlockHarvestLevel((Block)this, (int)7, (String)"pickaxe", (int)2);
        MinecraftForge.setBlockHarvestLevel((Block)this, (int)8, (String)"pickaxe", (int)1);
        MinecraftForge.setBlockHarvestLevel((Block)this, (int)9, (String)"pickaxe", (int)2);
        MinecraftForge.setBlockHarvestLevel((Block)this, (int)10, (String)"pickaxe", (int)3);
        MinecraftForge.setBlockHarvestLevel((Block)this, (int)11, (String)"pickaxe", (int)3);
        MinecraftForge.setBlockHarvestLevel((Block)this, (int)12, (String)"pickaxe", (int)2);
        MinecraftForge.setBlockHarvestLevel((Block)this, (int)13, (String)"pickaxe", (int)2);
        MinecraftForge.setBlockHarvestLevel((Block)this, (int)14, (String)"pickaxe", (int)2);
        MinecraftForge.setBlockHarvestLevel((Block)this, (int)15, (String)"pickaxe", (int)2);
    }

    public void dropBlockAsItemWithChance(World par1World, int par2, int par3, int par4, int par5, float par6, int par7) {
        super.dropBlockAsItemWithChance(par1World, par2, par3, par4, par5, par6, par7);
        int var8 = 0;
        switch (par5) {
            case 2: {
                var8 = 30 + par1World.rand.nextInt(21);
                break;
            }
            case 3: {
                var8 = 3 + par1World.rand.nextInt(5);
                break;
            }
            case 4: {
                var8 = 3 + par1World.rand.nextInt(5);
                break;
            }
            case 6: {
                var8 = 1 + par1World.rand.nextInt(1);
                break;
            }
            case 7: {
                var8 = 3 + par1World.rand.nextInt(3);
                break;
            }
            case 8: {
                var8 = 1 + par1World.rand.nextInt(1);
            }
        }
        if (var8 > 0) {
            this.dropXpOnBlockBreak(par1World, par2, par3, par4, var8);
        }
    }

    public ArrayList getBlockDropped(World aWorld, int aX, int aY, int aZ, int aMeta, int aFortune) {
        ArrayList<ItemStack> rList = new ArrayList<ItemStack>();
        switch (aMeta) {
            case 0: {
                break;
            }
            case 1: {
                rList.add(new ItemStack(this.blockID, 1, aMeta));
                break;
            }
            case 2: {
                rList.add(GT_ModHandler.getIC2Item("iridiumOre", 1 + aWorld.rand.nextInt(1 + aFortune / 2)));
                break;
            }
            case 3: {
                rList.add(GT_OreDictUnificator.get("gemRuby", null, 1 + aWorld.rand.nextInt(1 + aFortune)));
                if (aWorld.rand.nextInt(Math.max(1, 32 / (aFortune + 1))) != 0) break;
                rList.add(GT_MetaItem_Material.instance.getStack(54, 1));
                break;
            }
            case 4: {
                rList.add(GT_OreDictUnificator.get("gemSapphire", null, 1 + aWorld.rand.nextInt(1 + aFortune)));
                if (aWorld.rand.nextInt(Math.max(1, 64 / (aFortune + 1))) != 0) break;
                rList.add(GT_OreDictUnificator.get("gemGreenSapphire", null, 1));
                break;
            }
            case 5: {
                rList.add(new ItemStack(this.blockID, 1, aMeta));
                break;
            }
            case 6: {
                rList.add(GT_MetaItem_Dust.instance.getStack(3, 2 + aWorld.rand.nextInt(1 + aFortune)));
                break;
            }
            case 7: {
                rList.add(GT_MetaItem_Dust.instance.getStack(11, 2 + aWorld.rand.nextInt(1 + aFortune)));
                if (aWorld.rand.nextInt(Math.max(1, 4 / (aFortune + 1))) != 0) break;
                rList.add(new ItemStack(Item.redstone, 1));
                break;
            }
            case 8: {
                rList.add(GT_MetaItem_Dust.instance.getStack(14, 2 + aWorld.rand.nextInt(1 + aFortune)));
                if (aWorld.rand.nextInt(Math.max(1, 4 / (aFortune + 1))) == 0) {
                    rList.add(GT_MetaItem_Dust.instance.getStack(24, 1));
                }
                if (aWorld.rand.nextInt(Math.max(1, 32 / (aFortune + 1))) != 0) break;
                rList.add(GT_MetaItem_Material.instance.getStack(55, 1));
                break;
            }
            case 9: {
                rList.add(new ItemStack(this.blockID, 1, aMeta));
                break;
            }
            case 10: {
                rList.add(new ItemStack(this.blockID, 1, aMeta));
                break;
            }
            case 11: {
                rList.add(GT_OreDictUnificator.get("gemOlivine", null, 1 + aWorld.rand.nextInt(1 + aFortune)));
                break;
            }
            case 12: {
                rList.add(GT_MetaItem_Dust.instance.getStack(5, 6 + 3 * aWorld.rand.nextInt(1 + aFortune)));
                if (aWorld.rand.nextInt(Math.max(1, 4 / (aFortune + 1))) != 0) break;
                rList.add(GT_MetaItem_Dust.instance.getStack(18, 1));
                break;
            }
            case 13: {
                break;
            }
            case 14: {
                break;
            }
        }
        return rList;
    }

    public boolean canDragonDestroy(World world, int x, int y, int z) {
        return false;
    }

    public float getBlockHardness(World world, int x, int y, int z) {
        if (world == null) {
            return 0.0f;
        }
        Integer tMeta = world.getBlockMetadata(x, y, z);
        if (tMeta == null) {
            tMeta = 0;
        }
        switch (world.getBlockMetadata(x, y, z)) {
            case 1: {
                return 3.0f;
            }
            case 2: {
                return 20.0f;
            }
            case 3: {
                return 4.0f;
            }
            case 4: {
                return 4.0f;
            }
            case 5: {
                return 3.0f;
            }
            case 6: {
                return 2.0f;
            }
            case 7: {
                return 3.0f;
            }
            case 8: {
                return 2.0f;
            }
            case 9: {
                return 4.0f;
            }
            case 10: {
                return 3.5f;
            }
        }
        return super.getBlockHardness(world, x, y, z);
    }

    public int getBlockTexture(IBlockAccess aWorld, int aX, int aY, int aZ, int aSide) {
        return this.getBlockTextureFromSideAndMetadata(Math.abs(aSide ^ aX ^ aY ^ aZ) % 6, aWorld.getBlockMetadata(aX, aY, aZ));
    }

    public int getBlockTextureFromSideAndMetadata(int aSide, int aMeta) {
        return 128 + aMeta + aSide * 16;
    }

    public int getDamageValue(World par1World, int par2, int par3, int par4) {
        return par1World.getBlockMetadata(par2, par3, par4);
    }

    @SideOnly(value=Side.CLIENT)
    public void getSubBlocks(int par1, CreativeTabs par2CreativeTabs, List par3List) {
        for (int i = 1; i < 13; ++i) {
            par3List.add(new ItemStack(par1, 1, i));
        }
    }
}

