/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.IFuelHandler
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 */
package gregtechmod.common;

import cpw.mods.fml.common.IFuelHandler;
import gregtechmod.GT_Mod;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class GT_FuelHandler
implements IFuelHandler {
    public int getBurnTime(ItemStack aFuel) {
        if (aFuel == null) {
            return 0;
        }
        if (aFuel.isItemEqual(new ItemStack(Item.sign, 1))) {
            return 600;
        }
        if (aFuel.isItemEqual(GT_Mod.getGregTechItem(1, 1, 15))) {
            return 100;
        }
        if (aFuel.isItemEqual(GT_Mod.getGregTechItem(0, 1, 15))) {
            return 1600;
        }
        return 0;
    }
}

