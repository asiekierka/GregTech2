/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.inventory.Container
 *  net.minecraft.inventory.IInventory
 *  net.minecraft.inventory.Slot
 *  net.minecraft.item.ItemArmor
 *  net.minecraft.item.ItemStack
 */
package gregtechmod.common;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;

public class GT_Slot_Armor
extends Slot {
    final int armorType;
    final Container parent;

    public GT_Slot_Armor(Container par1ContainerPlayer, IInventory par2IInventory, int par3, int par4, int par5, int par6) {
        super(par2IInventory, par3, par4, par5);
        this.parent = par1ContainerPlayer;
        this.armorType = par6;
    }

    public int getSlotStackLimit() {
        return 1;
    }

    public boolean isItemValid(ItemStack par1ItemStack) {
        return par1ItemStack.getItem() instanceof ItemArmor ? ((ItemArmor)par1ItemStack.getItem()).armorType == this.armorType : (par1ItemStack.getItem().itemID == Block.pumpkin.blockID ? this.armorType == 0 : false);
    }

    @SideOnly(value=Side.CLIENT)
    public int getBackgroundIconIndex() {
        return 15 + this.armorType * 16;
    }
}

