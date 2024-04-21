/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.item.ItemStack
 *  net.minecraftforge.liquids.LiquidContainerData
 *  net.minecraftforge.liquids.LiquidContainerRegistry
 *  net.minecraftforge.liquids.LiquidDictionary
 *  net.minecraftforge.liquids.LiquidStack
 */
package gregtechmod.common.items;

import gregtechmod.GT_Mod;
import gregtechmod.common.GT_Client;
import gregtechmod.common.GT_LanguageManager;
import gregtechmod.common.GT_ModHandler;
import gregtechmod.common.items.GT_MetaItem_Abstract;
import gregtechmod.common.render.GT_LiquidFX;
import net.minecraft.item.ItemStack;
import net.minecraftforge.liquids.LiquidContainerData;
import net.minecraftforge.liquids.LiquidContainerRegistry;
import net.minecraftforge.liquids.LiquidDictionary;
import net.minecraftforge.liquids.LiquidStack;

public class GT_MetaItem_Liquid
extends GT_MetaItem_Abstract {
    public static GT_MetaItem_Abstract instance;

    public GT_MetaItem_Liquid(int par1) {
        super(par1);
        instance = this;
        this.setTextureFile("/gregtechmod/textures/metaitems/liquids.png");
        this.setItemName("GT_Liquids");
    }

    public static ItemStack[] getStackList() {
        return GT_MetaItem_Liquid.instance.mStackList;
    }

    public static ItemStack addItem(int aMeta, String aName, String aMaterial, String aToolTip, ItemStack aFullContainer, ItemStack aEmptyContainer, int aR, int aG, int aB) {
        GT_LanguageManager.addStringLocalization(instance.getItemName() + "." + aMeta + ".name", "Liquid " + aName);
        GT_MetaItem_Liquid.instance.mToolTipList[aMeta] = aToolTip;
        GT_MetaItem_Liquid.instance.mStackList[aMeta] = aMaterial != null && !aMaterial.equals("") ? LiquidDictionary.getOrCreateLiquid((String)("fluid" + aMaterial), (LiquidStack)new LiquidStack(GT_MetaItem_Liquid.instance.itemID, 1000, aMeta)).asItemStack() : new LiquidStack(GT_MetaItem_Liquid.instance.itemID, 1000, aMeta).asItemStack();
        if (aFullContainer != null && aEmptyContainer != null) {
            LiquidContainerRegistry.registerLiquid((LiquidContainerData)new LiquidContainerData(new LiquidStack(GT_MetaItem_Liquid.instance.mStackList[aMeta].itemID, 1000, GT_MetaItem_Liquid.instance.mStackList[aMeta].getItemDamage()), aFullContainer, aEmptyContainer));
        }
        if (GT_Mod.gregtechproxy instanceof GT_Client) {
            new GT_LiquidFX(aMeta, aR - 10, aR + 10, aG - 10, aG + 10, aB - 10, aB + 10);
        }
        GT_ModHandler.addLiquidTransposerRecipe(aEmptyContainer, new LiquidStack(GT_MetaItem_Liquid.instance.mStackList[aMeta].itemID, 1000, GT_MetaItem_Liquid.instance.mStackList[aMeta].getItemDamage()), aFullContainer, 160);
        return instance.getUnunifiedStack(aMeta, 1000);
    }
}

