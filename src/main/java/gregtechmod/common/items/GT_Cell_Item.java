/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.item.ItemStack
 */
package gregtechmod.common.items;

import gregtechmod.api.ICapsuleCellContainer;
import gregtechmod.common.items.GT_Generic_Item;
import net.minecraft.item.ItemStack;

public class GT_Cell_Item
extends GT_Generic_Item
implements ICapsuleCellContainer {
    public GT_Cell_Item(int aID) {
        super(aID);
    }

    @Override
    public int CapsuleCellContainerCount(ItemStack aStack) {
        return 1;
    }
}

