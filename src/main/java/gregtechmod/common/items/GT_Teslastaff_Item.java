/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  ic2.api.ElectricItem
 *  ic2.api.IElectricItem
 *  net.minecraft.block.Block
 *  net.minecraft.creativetab.CreativeTabs
 *  net.minecraft.entity.EntityLiving
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.EnumToolMaterial
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.item.ItemTool
 *  net.minecraft.util.DamageSource
 */
package gregtechmod.common.items;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import gregtechmod.GT_Mod;
import ic2.api.ElectricItem;
import ic2.api.IElectricItem;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.minecraft.util.DamageSource;

public class GT_Teslastaff_Item
extends ItemTool
implements IElectricItem {
    public int mCharge;
    public int mTransfer;
    public int mTier;

    public GT_Teslastaff_Item(int aID) {
        super(aID, 0, EnumToolMaterial.GOLD, new Block[0]);
        this.setTextureFile("/gregtechmod/textures/items.png");
        this.setCreativeTab(GT_Mod.tabGregTech);
        this.setMaxStackSize(1);
        this.setMaxDamage(100);
        this.setNoRepair();
        this.mCharge = 10000000;
        this.mTransfer = 8192;
        this.mTier = 4;
    }

    public void addInformation(ItemStack aStack, EntityPlayer aPlayer, List aList, boolean aF3_H) {
        aList.add("No warranty!");
    }

    public boolean hitEntity(ItemStack aStack, EntityLiving aTarget, EntityLiving aPlayer) {
        if (aTarget instanceof EntityPlayer && aPlayer instanceof EntityPlayer && ElectricItem.canUse((ItemStack)aStack, (int)9000000)) {
            EntityPlayer tTarget = (EntityPlayer)aTarget;
            EntityPlayer tPlayer = (EntityPlayer)aPlayer;
            ElectricItem.use((ItemStack)aStack, (int)9000000, (EntityPlayer)tPlayer);
            for (int i = 0; i < 4; ++i) {
                if (tTarget.inventory.armorInventory[i] == null || !(tTarget.inventory.armorInventory[i].getItem() instanceof IElectricItem)) continue;
                tTarget.inventory.armorInventory[i] = null;
            }
            aPlayer.attackEntityFrom(DamageSource.magic, 19);
            aTarget.attackEntityFrom(DamageSource.magic, 19);
        }
        return true;
    }

    @SideOnly(value=Side.CLIENT)
    public void getSubItems(int var1, CreativeTabs var2, List var3) {
        ItemStack tCharged = new ItemStack((Item)this, 1);
        ItemStack tUncharged = new ItemStack((Item)this, 1, this.getMaxDamage());
        ElectricItem.charge((ItemStack)tCharged, (int)Integer.MAX_VALUE, (int)Integer.MAX_VALUE, (boolean)true, (boolean)false);
        var3.add(tCharged);
        var3.add(tUncharged);
    }

    public int getItemEnchantability() {
        return 0;
    }

    public boolean getIsRepairable(ItemStack par1ItemStack, ItemStack par2ItemStack) {
        return false;
    }

    public boolean isFull3D() {
        return true;
    }

    public boolean getShareTag() {
        return true;
    }

    public boolean canProvideEnergy() {
        return false;
    }

    public int getChargedItemId() {
        return this.itemID;
    }

    public int getEmptyItemId() {
        return this.itemID;
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

