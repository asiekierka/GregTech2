/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.creativetab.CreativeTabs
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 */
package gregtechmod.common.items;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import gregtechmod.GT_Mod;
import gregtechmod.common.GT_OreDictUnificator;
import java.util.Arrays;
import java.util.List;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class GT_MetaItem_Abstract
extends Item {
    public String[] mToolTipList = new String[256];
    public ItemStack[] mStackList = new ItemStack[256];
    public boolean[] mGlowList = new boolean[256];

    public GT_MetaItem_Abstract(int par1) {
        super(par1);
        this.setCreativeTab(GT_Mod.tabGregTech);
        this.setMaxDamage(0);
        this.setHasSubtypes(true);
        Arrays.fill(this.mGlowList, false);
        Arrays.fill(this.mToolTipList, "");
    }

    public int getIconFromDamage(int par1) {
        return par1;
    }

    public int getMetadata(int par1) {
        return par1;
    }

    public void addInformation(ItemStack aStack, EntityPlayer aPlayer, List aList, boolean aF3_H) {
        if (aStack.getItemDamage() >= 0 && aStack.getItemDamage() < 256 && !this.mToolTipList[aStack.getItemDamage()].equals("")) {
            aList.add(this.mToolTipList[aStack.getItemDamage()]);
        }
    }

    public String getItemNameIS(ItemStack aStack) {
        return this.getItemName() + "." + aStack.getItemDamage();
    }

    @SideOnly(value=Side.CLIENT)
    public void getSubItems(int var1, CreativeTabs var2, List var3) {
        for (int i = 0; i < 256; ++i) {
            if (this.mStackList[i] == null) continue;
            var3.add(this.getUnunifiedStack(i, 1));
        }
    }

    public boolean hasEffect(ItemStack aStack) {
        return aStack.getItemDamage() >= 0 && aStack.getItemDamage() < 256 && this.mGlowList[aStack.getItemDamage()] || super.hasEffect(aStack);
    }

    public static ItemStack[] getStackList() {
        return null;
    }

    public ItemStack getUnunifiedStack(int aMeta, int aCount) {
        if (aMeta < 0 || aMeta >= 256 || this.mStackList[aMeta] == null) {
            return null;
        }
        ItemStack rStack = this.mStackList[aMeta].copy();
        rStack.stackSize = aCount;
        return rStack;
    }

    public ItemStack getStack(int aMeta, int aCount) {
        if (aMeta < 0 || aMeta >= 256 || this.mStackList[aMeta] == null) {
            return null;
        }
        ItemStack rStack = GT_OreDictUnificator.get(this.mStackList[aMeta]);
        rStack.stackSize = aCount;
        return rStack;
    }
}

