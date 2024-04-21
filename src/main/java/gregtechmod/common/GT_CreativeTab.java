/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.registry.LanguageRegistry
 *  net.minecraft.creativetab.CreativeTabs
 *  net.minecraft.item.ItemStack
 */
package gregtechmod.common;

import cpw.mods.fml.common.registry.LanguageRegistry;
import gregtechmod.GT_Mod;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

public class GT_CreativeTab
extends CreativeTabs {
    public GT_CreativeTab() {
        super("GregTech");
        LanguageRegistry.instance().addStringLocalization("itemGroup.GregTech", "GregTech Intergalactical");
    }

    public ItemStack getIconItemStack() {
        return new ItemStack(GT_Mod.instance.mItems[55], 1, 0);
    }
}

