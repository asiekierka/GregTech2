/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.item.ItemBlock
 *  net.minecraft.item.ItemStack
 */
package gregtechmod.common.items;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import gregtechmod.GT_Mod;
import gregtechmod.common.GT_LanguageManager;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class GT_MetaOre_Item
extends ItemBlock {
    public GT_MetaOre_Item(int par1) {
        super(par1);
        this.setMaxDamage(0);
        this.setHasSubtypes(true);
        this.setItemName(GT_LanguageManager.mNameList2[0]);
        this.setCreativeTab(GT_Mod.tabGregTech);
    }

    @SideOnly(value=Side.CLIENT)
    public int getIconFromDamage(int aMeta) {
        return GT_Mod.instance.mBlocks[2].getBlockTextureFromSideAndMetadata(1, aMeta);
    }

    public int getMetadata(int par1) {
        return par1;
    }

    public String getItemNameIS(ItemStack aItemStack) {
        return this.getItemName() + "." + GT_LanguageManager.mNameList2[aItemStack.getItemDamage()];
    }
}

