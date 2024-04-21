/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  ic2.api.ElectricItem
 *  ic2.api.IElectricItem
 *  net.minecraft.creativetab.CreativeTabs
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.ItemStack
 */
package gregtechmod.common.items;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import gregtechmod.GT_Mod;
import gregtechmod.common.items.GT_Generic_Item;
import ic2.api.ElectricItem;
import ic2.api.IElectricItem;
import java.util.List;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class GT_EnergyStore_Item
extends GT_Generic_Item
implements IElectricItem {
    public int mCharge;
    public int mTransfer;
    public int mTier;
    public int mEmptyID;
    public int mFullID;

    public GT_EnergyStore_Item(int aID, int aCharge, int aTransfer, int aTier, int aEmptyID, int aFullID) {
        super(aID);
        this.setMaxStackSize(1);
        this.setMaxDamage(100);
        this.setNoRepair();
        this.mCharge = aCharge;
        this.mTransfer = aTransfer;
        this.mTier = aTier;
        this.mEmptyID = aEmptyID;
        this.mFullID = aFullID;
    }

    public boolean getShareTag() {
        return true;
    }

    @SideOnly(value=Side.CLIENT)
    public void getSubItems(int var1, CreativeTabs var2, List var3) {
        ItemStack tCharged = new ItemStack(GT_Mod.instance.mItems[this.mFullID], 1);
        ItemStack tUncharged = new ItemStack(GT_Mod.instance.mItems[this.mEmptyID], 1, this.getMaxDamage());
        ElectricItem.charge((ItemStack)tCharged, (int)Integer.MAX_VALUE, (int)Integer.MAX_VALUE, (boolean)true, (boolean)false);
        if (this.itemID == GT_Mod.instance.mItems[this.mFullID].itemID) {
            var3.add(tCharged);
        }
        if (this.itemID == GT_Mod.instance.mItems[this.mEmptyID].itemID) {
            var3.add(tUncharged);
        }
    }

    public void addInformation(ItemStack aStack, EntityPlayer aPlayer, List aList, boolean aF3_H) {
        aList.add("Tier: " + this.mTier);
    }

    public boolean canProvideEnergy() {
        return true;
    }

    public int getChargedItemId() {
        return GT_Mod.instance.mItems[this.mFullID].itemID;
    }

    public int getEmptyItemId() {
        return GT_Mod.instance.mItems[this.mEmptyID].itemID;
    }

    public int getMaxCharge() {
        return this.mCharge;
    }

    public int getTier() {
        return this.mTier;
    }

    public int getTransferLimit() {
        return this.mTransfer;
    }
}

