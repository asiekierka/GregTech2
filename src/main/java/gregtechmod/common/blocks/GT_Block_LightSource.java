/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.BlockContainer
 *  net.minecraft.block.material.Material
 *  net.minecraft.creativetab.CreativeTabs
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.world.World
 */
package gregtechmod.common.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import gregtechmod.common.tileentities.GT_TileEntity_LightSource;
import java.util.List;
import java.util.Random;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

public class GT_Block_LightSource
extends BlockContainer {
    public GT_Block_LightSource(int aID) {
        super(aID, Material.air);
        this.setBlockName("GT_LightSource");
        this.setTextureFile("/gregtechmod/textures/void.png");
        this.setRequiresSelfNotify();
        this.setLightValue(1.0f);
        this.setLightOpacity(1);
        this.setBlockBounds(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f);
    }

    public AxisAlignedBB getCollisionBoundingBoxFromPool(World par1World, int par2, int par3, int par4) {
        return null;
    }

    public boolean canCollideCheck(int par1, boolean par2) {
        return par2 && par1 == 0;
    }

    public int getRenderBlockPass() {
        return 1;
    }

    public boolean isOpaqueCube() {
        return false;
    }

    public boolean renderAsNormalBlock() {
        return false;
    }

    public int damageDropped(int par1) {
        return 0;
    }

    public int quantityDropped(Random par1Random) {
        return 0;
    }

    public int idDropped(int par1, Random par2Random, int par3) {
        return 0;
    }

    public TileEntity createNewTileEntity(World aWorld) {
        return new GT_TileEntity_LightSource();
    }

    @SideOnly(value=Side.CLIENT)
    public void getSubBlocks(int par1, CreativeTabs par2CreativeTabs, List par3List) {
    }
}

