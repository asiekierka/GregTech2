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

import gregtechmod.common.GT_LanguageManager;
import gregtechmod.common.items.GT_MetaItem_Abstract;
import net.minecraft.item.ItemStack;
import net.minecraftforge.liquids.LiquidContainerData;
import net.minecraftforge.liquids.LiquidContainerRegistry;
import net.minecraftforge.liquids.LiquidDictionary;
import net.minecraftforge.liquids.LiquidStack;

public class GT_MetaItem_Plasma
extends GT_MetaItem_Abstract {
    public static GT_MetaItem_Abstract instance;

    public GT_MetaItem_Plasma(int par1) {
        super(par1);
        instance = this;
        this.setTextureFile("/gregtechmod/textures/metaitems/plasma.png");
        this.setItemName("GT_Plasma");
    }

    public static ItemStack[] getStackList() {
        return GT_MetaItem_Plasma.instance.mStackList;
    }

    public static ItemStack addItem(int aMeta, String aName, String aMaterial, String aToolTip, ItemStack aFullContainer, ItemStack aEmptyContainer) {
        GT_LanguageManager.addStringLocalization(instance.getItemName() + "." + aMeta + ".name", aName + " Plasma");
        GT_MetaItem_Plasma.instance.mToolTipList[aMeta] = aToolTip;
        GT_MetaItem_Plasma.instance.mStackList[aMeta] = aMaterial != null && !aMaterial.equals("") ? LiquidDictionary.getOrCreateLiquid((String)("plasma" + aMaterial), (LiquidStack)new LiquidStack(GT_MetaItem_Plasma.instance.itemID, 1000, aMeta)).asItemStack() : new LiquidStack(GT_MetaItem_Plasma.instance.itemID, 1000, aMeta).asItemStack();
        if (aFullContainer != null && aEmptyContainer != null) {
            LiquidContainerRegistry.registerLiquid((LiquidContainerData)new LiquidContainerData(new LiquidStack(GT_MetaItem_Plasma.instance.mStackList[aMeta].itemID, 1000, GT_MetaItem_Plasma.instance.mStackList[aMeta].getItemDamage()), aFullContainer, aEmptyContainer));
        }
        return instance.getUnunifiedStack(aMeta, 1000);
    }
}

