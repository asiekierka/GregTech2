/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.item.ItemStack
 */
package gregtechmod.common.items;

import gregtechmod.api.ICapsuleCellContainer;
import gregtechmod.common.GT_LanguageManager;
import gregtechmod.common.GT_ModHandler;
import gregtechmod.common.GT_OreDictUnificator;
import gregtechmod.common.items.GT_MetaItem_Abstract;
import net.minecraft.item.ItemStack;

public class GT_MetaItem_Cell
extends GT_MetaItem_Abstract
implements ICapsuleCellContainer {
    public static GT_MetaItem_Abstract instance;

    public GT_MetaItem_Cell(int par1) {
        super(par1);
        instance = this;
        this.setTextureFile("/gregtechmod/textures/metaitems/cells.png");
        this.setItemName("GT_Cells");
    }

    public static ItemStack[] getStackList() {
        return GT_MetaItem_Cell.instance.mStackList;
    }

    public static ItemStack addItem(int aMeta, String aName, String aMaterial, String aToolTip) {
        GT_LanguageManager.addStringLocalization(instance.getItemName() + "." + aMeta + ".name", aName);
        GT_MetaItem_Cell.instance.mToolTipList[aMeta] = aToolTip;
        GT_MetaItem_Cell.instance.mStackList[aMeta] = new ItemStack(GT_MetaItem_Cell.instance.itemID, 1, aMeta);
        GT_ModHandler.addExtractionRecipe(instance.getUnunifiedStack(aMeta, 1), GT_ModHandler.getIC2Item("cell", 1));
        if (aMaterial != null && !aMaterial.equals("")) {
            GT_OreDictUnificator.registerOre(aMaterial, instance.getUnunifiedStack(aMeta, 1));
            if (aMaterial.equals("molecule_1me")) {
                GT_OreDictUnificator.registerOre("molecule_1c_4h", instance.getUnunifiedStack(aMeta, 1));
            }
            if (aMaterial.equals("molecule_1d")) {
                GT_OreDictUnificator.registerOre("molecule_1h2", instance.getUnunifiedStack(aMeta, 1));
            }
            if (aMaterial.equals("molecule_1t")) {
                GT_OreDictUnificator.registerOre("molecule_1h3", instance.getUnunifiedStack(aMeta, 1));
            }
        }
        return instance.getUnunifiedStack(aMeta, 1);
    }

    @Override
    public int CapsuleCellContainerCount(ItemStack aStack) {
        return 1;
    }
}

