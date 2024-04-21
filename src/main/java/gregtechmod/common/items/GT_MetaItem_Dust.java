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
import gregtechmod.common.items.GT_MetaItem_SmallDust;
import net.minecraft.item.ItemStack;

public class GT_MetaItem_Dust
extends GT_MetaItem_Abstract {
    public static GT_MetaItem_Abstract instance;

    public GT_MetaItem_Dust(int par1) {
        super(par1);
        instance = this;
        this.setTextureFile("/gregtechmod/textures/metaitems/dusts.png");
        this.setItemName("GT_Dusts");
    }

    public static ItemStack[] getStackList() {
        return GT_MetaItem_Dust.instance.mStackList;
    }

    public static ItemStack addItem(int aMeta, String aName, String aMaterial, String aToolTip, boolean aGlow) {
        GT_LanguageManager.addStringLocalization(instance.getItemName() + "." + aMeta + ".name", aName);
        GT_MetaItem_Dust.instance.mToolTipList[aMeta] = aToolTip;
        GT_MetaItem_Dust.instance.mGlowList[aMeta] = aGlow;
        GT_MetaItem_Dust.instance.mStackList[aMeta] = new ItemStack(GT_MetaItem_Dust.instance.itemID, 1, aMeta);
        if (aMaterial != null && !aMaterial.equals("")) {
            GT_OreDictUnificator.add("dust" + aMaterial, instance.getUnunifiedStack(aMeta, 1));
        }
        GT_MetaItem_SmallDust.addItem(aMeta, aName, aMaterial, aToolTip, aGlow);
        return instance.getUnunifiedStack(aMeta, 1);
    }
}

