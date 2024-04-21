/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.inventory.IInventory
 *  net.minecraft.inventory.Slot
 *  net.minecraft.item.ItemStack
 */
package gregtechmod.common;

import gregtechmod.GT_Mod;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class GT_Slot_DataOrb
extends Slot {
    public GT_Slot_DataOrb(IInventory par1iInventory, int par2, int par3, int par4) {
        super(par1iInventory, par2, par3, par4);
    }

    public boolean isItemValid(ItemStack aStack) {
        return aStack != null && aStack.itemID == GT_Mod.instance.mItems[43].itemID;
    }
}

