/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.ItemStack
 */
package gregtechmod.common.items;

import gregtechmod.common.items.GT_Generic_Item;
import java.util.List;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class GT_Mortar_Item
extends GT_Generic_Item {
    ItemStack mBrokenItem;

    public GT_Mortar_Item(int aID, int aMaxDamage, ItemStack aBrokenItem) {
        super(aID);
        this.setMaxDamage(aMaxDamage - 1);
        this.setMaxStackSize(1);
        this.setNoRepair();
        this.mBrokenItem = aBrokenItem;
    }

    public ItemStack getContainerItemStack(ItemStack aStack) {
        if (aStack.getItemDamage() >= this.getMaxDamage()) {
            return this.mBrokenItem.copy();
        }
        return new ItemStack(this.itemID, 1, aStack.getItemDamage() + 1);
    }

    public boolean hasContainerItem() {
        return true;
    }

    public boolean doesContainerItemLeaveCraftingGrid(ItemStack par1ItemStack) {
        return false;
    }

    public void addInformation(ItemStack aStack, EntityPlayer aPlayer, List aList, boolean aF3_H) {
        aList.add("Can pulverize Ingots to Dust");
    }
}

