package gregtechmod.common;

import gregtechmod.GT_Mod;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class GT_CreativeTab extends CreativeTabs {

	public GT_CreativeTab() {
		super("GregTech");
		LanguageRegistry.instance().addStringLocalization("itemGroup.GregTech", "GregTech Intergalactical");
	}
	
	@Override
    public ItemStack getIconItemStack() {
        return new ItemStack(GT_Mod.instance.mItems[55], 1, 0);
    }
}
