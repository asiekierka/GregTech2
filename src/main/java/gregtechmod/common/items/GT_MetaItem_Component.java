/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.item.ItemStack
 */
package gregtechmod.common.items;

import gregtechmod.common.GT_LanguageManager;
import gregtechmod.common.GT_OreDictUnificator;
import gregtechmod.common.items.GT_MetaItem_Abstract;
import net.minecraft.item.ItemStack;

public class GT_MetaItem_Component
extends GT_MetaItem_Abstract {
    public static GT_MetaItem_Abstract instance;

    public GT_MetaItem_Component(int par1) {
        super(par1);
        instance = this;
        this.setTextureFile("/gregtechmod/textures/metaitems/components.png");
        this.setItemName("GT_Components");
    }

    public static ItemStack[] getStackList() {
        return GT_MetaItem_Component.instance.mStackList;
    }

    public static ItemStack addItem(int aMeta, String aName, String aMaterial, String aToolTip) {
        GT_LanguageManager.addStringLocalization(instance.getItemName() + "." + aMeta + ".name", aName);
        GT_MetaItem_Component.instance.mToolTipList[aMeta] = aToolTip;
        GT_MetaItem_Component.instance.mStackList[aMeta] = new ItemStack(GT_MetaItem_Component.instance.itemID, 1, aMeta);
        if (aMaterial != null && !aMaterial.equals("")) {
            GT_OreDictUnificator.registerOre(aMaterial, instance.getUnunifiedStack(aMeta, 1));
        }
        return instance.getUnunifiedStack(aMeta, 1);
    }
}

