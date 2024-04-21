/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.ChunkCoordinates
 *  net.minecraft.world.World
 */
package gregtechmod.common.items;

import gregtechmod.GT_Mod;
import gregtechmod.api.IGregTechDeviceInformation;
import gregtechmod.api.IIgnoreRightclickOnMachine;
import java.util.List;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.world.World;

public class GT_SensorKit_Item
extends Item
implements IIgnoreRightclickOnMachine {
    public GT_SensorKit_Item(int aID) {
        super(aID);
        this.setCreativeTab(GT_Mod.tabGregTech);
        this.setMaxStackSize(1);
    }

    public String getTextureFile() {
        return "/gregtechmod/textures/items.png";
    }

    public void addInformation(ItemStack aStack, EntityPlayer aPlayer, List aList, boolean aF3_H) {
        aList.add("Attach to GregTech Machines");
    }

    protected ChunkCoordinates getTargetCoordinates(World world, int x, int y, int z, ItemStack stack) {
        TileEntity tTileEntity = world.getBlockTileEntity(x, y, z);
        if (tTileEntity != null && tTileEntity instanceof IGregTechDeviceInformation && ((IGregTechDeviceInformation)tTileEntity).isGivingInformation()) {
            ChunkCoordinates coordinates = new ChunkCoordinates();
            coordinates.posX = x;
            coordinates.posY = y;
            coordinates.posZ = z;
            return coordinates;
        }
        return null;
    }

    private void setCoordinates(ItemStack aStack, int x, int y, int z) {
        if (aStack != null) {
            NBTTagCompound tNBT = aStack.getTagCompound();
            if (tNBT == null) {
                tNBT = new NBTTagCompound();
            }
            tNBT.setInteger("x", x);
            tNBT.setInteger("y", y);
            tNBT.setInteger("z", z);
            aStack.setTagCompound(tNBT);
        }
    }

    public boolean onItemUseFirst(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ) {
        if (player == null) {
            return false;
        }
        boolean isServer = player instanceof EntityPlayerMP;
        if (!isServer) {
            return false;
        }
        ChunkCoordinates position = this.getTargetCoordinates(world, x, y, z, stack);
        if (position != null) {
            ItemStack sensorLocationCard = new ItemStack(GT_Mod.instance.mItems[16], 1);
            this.setCoordinates(sensorLocationCard, position.posX, position.posY, position.posZ);
            player.inventory.mainInventory[player.inventory.currentItem] = sensorLocationCard;
            return true;
        }
        return false;
    }
}

