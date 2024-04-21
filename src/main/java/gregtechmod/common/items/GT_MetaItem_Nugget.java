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

public class GT_MetaItem_Nugget
extends GT_MetaItem_Abstract {
    public static GT_MetaItem_Abstract instance;

    public GT_MetaItem_Nugget(int par1) {
        super(par1);
        instance = this;
        this.setTextureFile("/gregtechmod/textures/metaitems/nuggets.png");
        this.setItemName("GT_Nuggets");
    }

    public static ItemStack[] getStackList() {
        return GT_MetaItem_Nugget.instance.mStackList;
    }

    public static ItemStack addItem(int aMeta, String aName, String aMaterial, String aToolTip, boolean aGlow) {
        GT_LanguageManager.addStringLocalization(instance.getItemName() + "." + aMeta + ".name", aName);
        GT_MetaItem_Nugget.instance.mToolTipList[aMeta] = aToolTip;
        GT_MetaItem_Nugget.instance.mGlowList[aMeta] = aGlow;
        GT_MetaItem_Nugget.instance.mStackList[aMeta] = new ItemStack(GT_MetaItem_Nugget.instance.itemID, 1, aMeta);
        if (aMaterial != null && !aMaterial.equals("")) {
            GT_OreDictUnificator.add("nugget" + aMaterial, instance.getUnunifiedStack(aMeta, 1));
        }
        return instance.getUnunifiedStack(aMeta, 1);
    }
}

