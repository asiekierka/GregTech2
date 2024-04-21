/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.item.Item
 */
package gregtechmod.common.items;

import gregtechmod.GT_Mod;
import net.minecraft.item.Item;

public class GT_Generic_Item
extends Item {
    public GT_Generic_Item(int aID) {
        super(aID);
        this.setTextureFile("/gregtechmod/textures/items.png");
        this.setCreativeTab(GT_Mod.tabGregTech);
    }
}

