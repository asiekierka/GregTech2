package gregtechmod.common.items;

import gregtechmod.GT_Mod;
import gregtechmod.common.GT_LanguageManager;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class GT_MetaOre_Item extends ItemBlock {
    public GT_MetaOre_Item(int par1) {
        super(par1);
        setMaxDamage(0);
        setHasSubtypes(true);
        setItemName(GT_LanguageManager.mNameList2[0]);
		setCreativeTab(GT_Mod.tabGregTech);
    }

    /**
     * Gets an icon index based on an item's damage value
     */
    @SideOnly(Side.CLIENT)
    @Override
    public int getIconFromDamage(int aMeta) {
        return GT_Mod.instance.mBlocks[2].getBlockTextureFromSideAndMetadata(1, aMeta);
    }
    
    /**
     * Returns the metadata of the block which this Item (ItemBlock) can place
     */
    @Override
    public int getMetadata(int par1) {
        return par1;
    }

    @Override
    public String getItemNameIS(ItemStack aItemStack) {
    	return getItemName() + "." + GT_LanguageManager.mNameList2[aItemStack.getItemDamage()];
    }
}
