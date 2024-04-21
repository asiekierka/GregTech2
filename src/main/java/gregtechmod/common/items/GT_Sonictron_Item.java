/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.nbt.NBTTagList
 *  net.minecraft.world.World
 */
package gregtechmod.common.items;

import gregtechmod.GT_Mod;
import gregtechmod.api.IIgnoreRightclickOnMachine;
import gregtechmod.api.IPlayerTickingItem;
import gregtechmod.common.items.GT_Generic_Item;
import gregtechmod.common.tileentities.GT_TileEntity_Sonictron;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.world.World;

public class GT_Sonictron_Item
extends GT_Generic_Item
implements IIgnoreRightclickOnMachine,
IPlayerTickingItem {
    public GT_Sonictron_Item(int par1) {
        super(par1);
        this.setMaxStackSize(1);
        this.setNoRepair();
    }

    public ItemStack onItemRightClick(ItemStack aStack, World aWorld, EntityPlayer aPlayer) {
        GT_Sonictron_Item.setCurrentIndex(aStack, 0);
        return aStack;
    }

    public boolean onItemUse(ItemStack aStack, EntityPlayer par2EntityPlayer, World par3World, int par4, int par5, int par6, int par7, float par8, float par9, float par10) {
        GT_TileEntity_Sonictron tSonictron;
        if (!par3World.isRemote && par3World.getBlockId(par4, par5, par6) == GT_Mod.instance.mBlocks[1].blockID && par3World.getBlockMetadata(par4, par5, par6) == 6 && (tSonictron = (GT_TileEntity_Sonictron)par3World.getBlockTileEntity(par4, par5, par6)) != null) {
            ItemStack[] tInventory = GT_Sonictron_Item.getNBTInventory(aStack);
            if (par2EntityPlayer.isSneaking()) {
                this.copyInventory(tSonictron.mInventory, tInventory);
            } else {
                this.copyInventory(tInventory, tSonictron.mInventory);
            }
            GT_Sonictron_Item.setNBTInventory(aStack, tInventory);
            return true;
        }
        GT_Sonictron_Item.setCurrentIndex(aStack, -1);
        return false;
    }

    private void copyInventory(ItemStack[] aInventory, ItemStack[] aNewContent) {
        for (int i = 0; i < 64; ++i) {
            aInventory[i] = aNewContent[i] == null ? null : aNewContent[i].copy();
        }
    }

    public static int getCurrentIndex(ItemStack aStack) {
        NBTTagCompound tNBTTagCompound = aStack.getTagCompound();
        if (tNBTTagCompound == null) {
            tNBTTagCompound = new NBTTagCompound();
        }
        return tNBTTagCompound.getInteger("mCurrentIndex");
    }

    public static int getTickTimer(ItemStack aStack) {
        NBTTagCompound tNBTTagCompound = aStack.getTagCompound();
        if (tNBTTagCompound == null) {
            tNBTTagCompound = new NBTTagCompound();
        }
        return tNBTTagCompound.getInteger("mTickTimer");
    }

    public static NBTTagCompound setCurrentIndex(ItemStack aStack, int aIndex) {
        NBTTagCompound tNBTTagCompound = aStack.getTagCompound();
        if (tNBTTagCompound == null) {
            tNBTTagCompound = new NBTTagCompound();
        }
        tNBTTagCompound.setInteger("mCurrentIndex", aIndex);
        return tNBTTagCompound;
    }

    public static NBTTagCompound setTickTimer(ItemStack aStack, int aTime) {
        NBTTagCompound tNBTTagCompound = aStack.getTagCompound();
        if (tNBTTagCompound == null) {
            tNBTTagCompound = new NBTTagCompound();
        }
        tNBTTagCompound.setInteger("mTickTimer", aTime);
        return tNBTTagCompound;
    }

    public static ItemStack[] getNBTInventory(ItemStack aStack) {
        ItemStack[] tInventory = new ItemStack[64];
        NBTTagCompound tNBT = aStack.getTagCompound();
        if (tNBT == null) {
            return tInventory;
        }
        NBTTagList tNBT_ItemList = tNBT.getTagList("Inventory");
        for (int i = 0; i < tNBT_ItemList.tagCount(); ++i) {
            NBTTagCompound tag = (NBTTagCompound)tNBT_ItemList.tagAt(i);
            byte slot = tag.getByte("Slot");
            if (slot < 0 || slot >= tInventory.length) continue;
            tInventory[slot] = ItemStack.loadItemStackFromNBT((NBTTagCompound)tag);
        }
        return tInventory;
    }

    public static NBTTagCompound setNBTInventory(ItemStack aStack, ItemStack[] aInventory) {
        NBTTagCompound tNBT = aStack.getTagCompound();
        if (tNBT == null) {
            tNBT = new NBTTagCompound();
        }
        NBTTagList tNBT_ItemList = new NBTTagList();
        for (int i = 0; i < aInventory.length; ++i) {
            ItemStack stack = aInventory[i];
            if (stack == null) continue;
            NBTTagCompound tag = new NBTTagCompound();
            tag.setByte("Slot", (byte)i);
            stack.writeToNBT(tag);
            tNBT_ItemList.appendTag((NBTBase)tag);
        }
        tNBT.setTag("Inventory", (NBTBase)tNBT_ItemList);
        aStack.setTagCompound(tNBT);
        return tNBT;
    }

    public void onCreated(ItemStack aStack, World aWorld, EntityPlayer aPlayer) {
        GT_Sonictron_Item.setNBTInventory(aStack, new ItemStack[64]);
    }

    @Override
    public boolean onTick(EntityPlayer aPlayer, ItemStack aStack, int aTimer, boolean aIsArmor) {
        int tTickTimer = GT_Sonictron_Item.getTickTimer(aStack);
        int tCurrentIndex = GT_Sonictron_Item.getCurrentIndex(aStack);
        if (tTickTimer++ % 2 == 0 && tCurrentIndex > -1) {
            ItemStack[] tInventory = GT_Sonictron_Item.getNBTInventory(aStack);
            GT_Mod.gregtechproxy.doSound(tInventory[tCurrentIndex], aPlayer.worldObj, aPlayer.posX, aPlayer.posY, aPlayer.posZ);
            if (++tCurrentIndex > 63) {
                tCurrentIndex = -1;
            }
        }
        GT_Sonictron_Item.setTickTimer(aStack, tTickTimer);
        GT_Sonictron_Item.setCurrentIndex(aStack, tCurrentIndex);
        return true;
    }

    public boolean getShareTag() {
        return true;
    }
}

