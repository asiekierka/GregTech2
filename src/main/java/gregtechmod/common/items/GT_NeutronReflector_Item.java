/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  ic2.api.IReactor
 *  ic2.api.IReactorComponent
 *  net.minecraft.item.ItemStack
 */
package gregtechmod.common.items;

import gregtechmod.common.items.GT_Generic_Item;
import ic2.api.IReactor;
import ic2.api.IReactorComponent;
import net.minecraft.item.ItemStack;

public class GT_NeutronReflector_Item
extends GT_Generic_Item
implements IReactorComponent {
    public GT_NeutronReflector_Item(int aID, int aMaxDamage) {
        super(aID);
        this.setMaxStackSize(64);
        this.setMaxDamage(aMaxDamage);
    }

    public void processChamber(IReactor aReactor, ItemStack aStack, int x, int y) {
    }

    public boolean acceptUraniumPulse(IReactor aReactor, ItemStack aStack, ItemStack pulsingStack, int x, int y, int pulseX, int pulseY) {
        if (aStack.stackSize > 1) {
            return false;
        }
        ((IReactorComponent)pulsingStack.getItem()).acceptUraniumPulse(aReactor, pulsingStack, aStack, pulseX, pulseY, x, y);
        if (this.getMaxDamage() > 0) {
            if (aStack.getItemDamage() + 1 >= this.getMaxDamage()) {
                aReactor.setItemAt(x, y, null);
            } else {
                aStack.setItemDamage(aStack.getItemDamage() + 1);
            }
        }
        return true;
    }

    public boolean canStoreHeat(IReactor aReactor, ItemStack aStack, int x, int y) {
        return false;
    }

    public int getMaxHeat(IReactor aReactor, ItemStack aStack, int x, int y) {
        return 0;
    }

    public int getCurrentHeat(IReactor aReactor, ItemStack aStack, int x, int y) {
        return 0;
    }

    public float influenceExplosion(IReactor aReactor, ItemStack aStack) {
        return -1.0f;
    }

    public int alterHeat(IReactor aReactor, ItemStack aStack, int x, int y, int aHeat) {
        return aHeat;
    }

    private void setHeatForStack(ItemStack aStack, int aHeat) {
    }

    private int getHeatOfStack(ItemStack aStack) {
        return 0;
    }
}

