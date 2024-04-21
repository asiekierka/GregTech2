/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.ItemStack
 *  net.minecraft.world.World
 */
package gregtechmod.common.items;

import gregtechmod.GT_Mod;
import gregtechmod.common.items.GT_Generic_Item;
import java.util.List;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class GT_Destructopack_Item
extends GT_Generic_Item {
    public GT_Destructopack_Item(int par1) {
        super(par1);
        this.setMaxStackSize(1);
        this.setNoRepair();
    }

    public ItemStack onItemRightClick(ItemStack aStack, World aWorld, EntityPlayer aPlayer) {
        aPlayer.openGui((Object)GT_Mod.instance, 33, aWorld, 0, 0, 0);
        return aStack;
    }

    public void addInformation(ItemStack aStack, EntityPlayer aPlayer, List aList, boolean aF3_H) {
        aList.add("Mobile Trash Bin");
    }
}

