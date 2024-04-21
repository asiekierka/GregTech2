package gregtechmod.common.items;

import gregtechmod.GT_Mod;
import gregtechmod.common.GT_Coordinate;
import gregtechmod.common.GT_LanguageManager;
import gregtechmod.common.blocks.GT_BlockMetaID_Block;

import java.util.ArrayList;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class GT_MetaBlock_Item extends ItemBlock {
    public GT_MetaBlock_Item(int par1) {
        super(par1);
        setMaxDamage(0);
        setHasSubtypes(true);
        setItemName(GT_LanguageManager.mNameList0[0]);
        setCreativeTab(GT_Mod.tabGregTech);
    }

    @SideOnly(Side.CLIENT)
    public int getIconFromDamage(int aMeta) {
        return GT_Mod.instance.mBlocks[0].getBlockTextureFromSideAndMetadata(1, aMeta);
    }

    @Override
    public int getMetadata(int par1) {
        return par1;
    }

    @Override
    public boolean onItemUseFirst(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ) {
    	if (stack.getItemDamage() == 6)
    		if (1 < GT_BlockMetaID_Block.stepToFindOrCallLESUController(world, x, y, z, new ArrayList<GT_Coordinate>(), false))
    			return true;
    	return false;
    }
    
    public String getItemNameIS(ItemStack aItemStack) {
    	return getItemName() + "." + GT_LanguageManager.mNameList0[aItemStack.getItemDamage()];
    }
}
