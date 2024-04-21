/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.item.ItemStack
 */
package gregtechmod.common.items;

import gregtechmod.common.GT_LanguageManager;
import gregtechmod.common.GT_ModHandler;
import gregtechmod.common.GT_OreDictUnificator;
import gregtechmod.common.items.GT_MetaItem_Abstract;
import net.minecraft.item.ItemStack;

public class GT_MetaItem_SmallDust
extends GT_MetaItem_Abstract {
    public static GT_MetaItem_Abstract instance;

    public GT_MetaItem_SmallDust(int par1) {
        super(par1);
        instance = this;
        this.setTextureFile("/gregtechmod/textures/metaitems/dustssmall.png");
        this.setItemName("GT_SmallDusts");
        GT_ModHandler.addToRecyclerBlackList(new ItemStack(this.itemID, 1, -1));
    }

    public static ItemStack[] getStackList() {
        return GT_MetaItem_SmallDust.instance.mStackList;
    }

    public static ItemStack addItem(int aMeta, String aName, String aMaterial, String aToolTip, boolean aGlow) {
        GT_LanguageManager.addStringLocalization(instance.getItemName() + "." + aMeta + ".name", "Tiny Pile of " + aName);
        GT_MetaItem_SmallDust.instance.mToolTipList[aMeta] = aToolTip;
        GT_MetaItem_SmallDust.instance.mGlowList[aMeta] = aGlow;
        GT_MetaItem_SmallDust.instance.mStackList[aMeta] = new ItemStack(GT_MetaItem_SmallDust.instance.itemID, 1, aMeta);
        if (aMaterial != null && !aMaterial.equals("")) {
            GT_OreDictUnificator.add("dustSmall" + aMaterial, instance.getUnunifiedStack(aMeta, 1));
        }
        return instance.getUnunifiedStack(aMeta, 1);
    }
}

