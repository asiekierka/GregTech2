/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.nbt.NBTTagList
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.world.World
 */
package gregtechmod.common.items;

import gregtechmod.api.IIgnoreRightclickOnMachine;
import gregtechmod.common.items.GT_Generic_Item;
import gregtechmod.common.tileentities.GT_TileEntity_ComputerCube;
import gregtechmod.common.tileentities.GT_TileEntity_Sonictron;
import java.util.List;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class GT_Dataorb_Item
extends GT_Generic_Item
implements IIgnoreRightclickOnMachine {
    public GT_Dataorb_Item(int par1) {
        super(par1);
        this.setNoRepair();
    }

    public ItemStack onItemRightClick(ItemStack aStack, World aWorld, EntityPlayer aPlayer) {
        return aStack;
    }

    public boolean onItemUse(ItemStack aStack, EntityPlayer aPlayer, World par3World, int par4, int par5, int par6, int par7, float par8, float par9, float par10) {
        GT_TileEntity_Sonictron tSonictron;
        if (aStack.stackSize > 1 || aPlayer.worldObj.isRemote) {
            return false;
        }
        TileEntity tTileEntity = par3World.getBlockTileEntity(par4, par5, par6);
        if (tTileEntity == null) {
            return false;
        }
        if (tTileEntity instanceof GT_TileEntity_ComputerCube) {
            GT_TileEntity_ComputerCube tComputer = (GT_TileEntity_ComputerCube)tTileEntity;
            if (tComputer != null && tComputer.mMode == 1 && tComputer.playerOwnsThis(aPlayer)) {
                ItemStack[] tInventory = GT_Dataorb_Item.getNBTInventory(aStack);
                if (aPlayer.isSneaking()) {
                    if (GT_Dataorb_Item.getDataTitle(aStack).equals("Reactorplan-Data")) {
                        this.copyInventory(tComputer.mInventory, tInventory, 54);
                    }
                } else {
                    this.copyInventory(tInventory, tComputer.mInventory, 54);
                    GT_Dataorb_Item.setDataTitle(aStack, "Reactorplan-Data");
                    GT_Dataorb_Item.setDataName(aStack, "" + tInventory.hashCode());
                }
                GT_Dataorb_Item.setNBTInventory(aStack, tInventory);
                return true;
            }
        } else if (tTileEntity instanceof GT_TileEntity_Sonictron && (tSonictron = (GT_TileEntity_Sonictron)tTileEntity) != null) {
            ItemStack[] tInventory = GT_Dataorb_Item.getNBTInventory(aStack);
            if (aPlayer.isSneaking()) {
                if (GT_Dataorb_Item.getDataTitle(aStack).equals("Sonictron-Data")) {
                    this.copyInventory(tSonictron.mInventory, tInventory, 64);
                }
            } else {
                this.copyInventory(tInventory, tSonictron.mInventory, 64);
                GT_Dataorb_Item.setDataTitle(aStack, "Sonictron-Data");
                GT_Dataorb_Item.setDataName(aStack, "" + tInventory.hashCode());
            }
            GT_Dataorb_Item.setNBTInventory(aStack, tInventory);
            return true;
        }
        return false;
    }

    public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4) {
        if (!GT_Dataorb_Item.getDataTitle(par1ItemStack).equals("")) {
            par3List.add(GT_Dataorb_Item.getDataTitle(par1ItemStack));
            par3List.add(GT_Dataorb_Item.getDataName(par1ItemStack));
        }
    }

    private void copyInventory(ItemStack[] aInventory, ItemStack[] aNewContent, int aIndexlength) {
        for (int i = 0; i < aIndexlength; ++i) {
            aInventory[i] = aNewContent[i] == null ? null : aNewContent[i].copy();
        }
    }

    public static String getDataName(ItemStack aStack) {
        NBTTagCompound tNBTTagCompound = aStack.getTagCompound();
        if (tNBTTagCompound == null) {
            tNBTTagCompound = new NBTTagCompound();
        }
        return tNBTTagCompound.getString("mDataName");
    }

    public static String getDataTitle(ItemStack aStack) {
        NBTTagCompound tNBTTagCompound = aStack.getTagCompound();
        if (tNBTTagCompound == null) {
            tNBTTagCompound = new NBTTagCompound();
        }
        return tNBTTagCompound.getString("mDataTitle");
    }

    public static NBTTagCompound setDataName(ItemStack aStack, String aDataName) {
        NBTTagCompound tNBTTagCompound = aStack.getTagCompound();
        if (tNBTTagCompound == null) {
            tNBTTagCompound = new NBTTagCompound();
        }
        tNBTTagCompound.setString("mDataName", aDataName);
        return tNBTTagCompound;
    }

    public static NBTTagCompound setDataTitle(ItemStack aStack, String aDataTitle) {
        NBTTagCompound tNBTTagCompound = aStack.getTagCompound();
        if (tNBTTagCompound == null) {
            tNBTTagCompound = new NBTTagCompound();
        }
        tNBTTagCompound.setString("mDataTitle", aDataTitle);
        return tNBTTagCompound;
    }

    public static ItemStack[] getNBTInventory(ItemStack aStack) {
        ItemStack[] tInventory = new ItemStack[256];
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

    public boolean getShareTag() {
        return true;
    }
}

