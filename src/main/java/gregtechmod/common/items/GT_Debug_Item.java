/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  ic2.api.Direction
 *  ic2.api.ElectricItem
 *  ic2.api.IElectricItem
 *  ic2.api.IEnergyStorage
 *  ic2.api.IReactor
 *  ic2.api.IReactorChamber
 *  ic2.api.IWrenchable
 *  ic2.api.TECrop
 *  ic2.api.energy.tile.IEnergyConductor
 *  ic2.api.energy.tile.IEnergySink
 *  ic2.api.energy.tile.IEnergySource
 *  ic2.api.energy.tile.IEnergyTile
 *  net.minecraft.block.Block
 *  net.minecraft.creativetab.CreativeTabs
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.inventory.IInventory
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.network.packet.Packet
 *  net.minecraft.network.packet.Packet3Chat
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.world.World
 */
package gregtechmod.common.items;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import gregtechmod.api.IDebugableBlock;
import gregtechmod.api.IIgnoreRightclickOnMachine;
import gregtechmod.api.IPlayerTickingItem;
import gregtechmod.common.items.GT_Generic_Item;
import ic2.api.Direction;
import ic2.api.ElectricItem;
import ic2.api.IElectricItem;
import ic2.api.IEnergyStorage;
import ic2.api.IReactor;
import ic2.api.IReactorChamber;
import ic2.api.IWrenchable;
import ic2.api.TECrop;
import ic2.api.energy.tile.IEnergyConductor;
import ic2.api.energy.tile.IEnergySink;
import ic2.api.energy.tile.IEnergySource;
import ic2.api.energy.tile.IEnergyTile;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.Packet3Chat;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class GT_Debug_Item
extends GT_Generic_Item
implements IIgnoreRightclickOnMachine,
IElectricItem,
IPlayerTickingItem {
    public GT_Debug_Item(int aID) {
        super(aID);
        this.setMaxStackSize(1);
        this.setMaxDamage(0);
        this.setNoRepair();
    }

    public boolean getShareTag() {
        return true;
    }

    @Override
    public boolean onTick(EntityPlayer aPlayer, ItemStack aStack, int aTimer, boolean aIsArmor) {
        NBTTagCompound tNBT = aStack.getTagCompound();
        if (tNBT == null) {
            tNBT = new NBTTagCompound();
        }
        tNBT.setInteger("charge", 1000000000);
        aStack.setTagCompound(tNBT);
        return true;
    }

    @SideOnly(value=Side.CLIENT)
    public void getSubItems(int var1, CreativeTabs var2, List var3) {
        ItemStack tCharged = new ItemStack((Item)this, 1);
        ItemStack tUncharged = new ItemStack((Item)this, 1, this.getMaxDamage());
        ElectricItem.charge((ItemStack)tCharged, (int)1000000000, (int)Integer.MAX_VALUE, (boolean)true, (boolean)false);
        var3.add(tCharged);
    }

    public boolean canProvideEnergy() {
        return true;
    }

    public int getChargedItemId() {
        return this.itemID;
    }

    public int getEmptyItemId() {
        return this.itemID;
    }

    public int getMaxCharge() {
        return 2000000000;
    }

    public int getTier() {
        return 1;
    }

    public int getTransferLimit() {
        return 2000000000;
    }

    public boolean onItemUseFirst(ItemStack aStack, EntityPlayer aPlayer, World aWorld, int aX, int aY, int aZ, int aSide, float var8, float var9, float var10) {
        if (aPlayer instanceof EntityPlayerMP) {
            ArrayList temp;
            TileEntity tTileEntity = aWorld.getBlockTileEntity(aX, aY, aZ);
            ArrayList<String> tList = new ArrayList<String>();
            Block tBlock = Block.blocksList[aWorld.getBlockId(aX, aY, aZ)];
            tList.add("-----");
            if (tTileEntity != null && tTileEntity instanceof IInventory) {
                tList.add("Name: " + ((IInventory)tTileEntity).getInvName() + "  ID: " + tBlock.blockID + "  MetaData: " + aWorld.getBlockMetadata(aX, aY, aZ));
            } else {
                tList.add("Name: " + tBlock.getBlockName() + "  ID: " + tBlock.blockID + "  MetaData: " + aWorld.getBlockMetadata(aX, aY, aZ));
            }
            tList.add("Hardness: " + tBlock.getBlockHardness(aWorld, aX, aY, aZ) + "  Blast Resistance: " + tBlock.getExplosionResistance((Entity)aPlayer, aWorld, aX, aY, aZ, aPlayer.posX, aPlayer.posY, aPlayer.posZ));
            if (tTileEntity != null) {
                if (tTileEntity instanceof IReactorChamber) {
                    tTileEntity = (TileEntity)((IReactorChamber)tTileEntity).getReactor();
                }
                if (tTileEntity instanceof IReactor) {
                    tList.add("Heat: " + ((IReactor)tTileEntity).getHeat() + "/" + ((IReactor)tTileEntity).getMaxHeat() + "  HEM: " + ((IReactor)tTileEntity).getHeatEffectModifier() + "  Base EU Output: " + ((IReactor)tTileEntity).getOutput());
                }
                if (tTileEntity instanceof IWrenchable) {
                    tList.add("Facing: " + ((IWrenchable)tTileEntity).getFacing() + " / Chance: " + ((IWrenchable)tTileEntity).getWrenchDropRate() * 100.0f + "%");
                    tList.add(((IWrenchable)tTileEntity).wrenchCanRemove(aPlayer) ? "You can remove this with a Wrench" : "You can NOT remove this with a Wrench");
                }
                if (tTileEntity instanceof IEnergyTile) {
                    tList.add(((IEnergyTile)tTileEntity).isAddedToEnergyNet() ? "Added to E-net" : "Not added to E-net! Bug?");
                }
                if (tTileEntity instanceof IEnergySink) {
                    tList.add("Demanded Energy: " + ((IEnergySink)tTileEntity).demandsEnergy());
                    tList.add("Max Safe Input: " + ((IEnergySink)tTileEntity).getMaxSafeInput());
                }
                if (tTileEntity instanceof IEnergySource) {
                    tList.add("Max Energy Output: " + ((IEnergySource)tTileEntity).getMaxEnergyOutput());
                }
                if (tTileEntity instanceof IEnergyConductor) {
                    tList.add("Conduction Loss: " + ((IEnergyConductor)tTileEntity).getConductionLoss());
                }
                if (tTileEntity instanceof IEnergyStorage) {
                    tList.add("Contained Energy: " + ((IEnergyStorage)tTileEntity).getStored() + " of " + ((IEnergyStorage)tTileEntity).getCapacity());
                    tList.add(((IEnergyStorage)tTileEntity).isTeleporterCompatible(Direction.YP) ? "Teleporter Compatible" : "Not Teleporter Compatible");
                }
                if (tTileEntity instanceof TECrop) {
                    tList.add("Type -- Crop-ID: " + ((TECrop)tTileEntity).id + "  Growth: " + ((TECrop)tTileEntity).statGrowth + "  Gain: " + ((TECrop)tTileEntity).statGain + "  Resistance: " + ((TECrop)tTileEntity).statResistance);
                    tList.add("Plant -- Fertilizer: " + ((TECrop)tTileEntity).nutrientStorage + "  Water: " + ((TECrop)tTileEntity).waterStorage + "  Weed-Ex: " + ((TECrop)tTileEntity).exStorage + "  Scan-Level: " + ((TECrop)tTileEntity).scanLevel);
                    tList.add("Environment -- Nutrients: " + ((TECrop)tTileEntity).getNutrients() + "  Humidity: " + ((TECrop)tTileEntity).getHumidity() + "  Air-Quality: " + ((TECrop)tTileEntity).getAirQuality());
                }
            }
            if (tBlock instanceof IDebugableBlock && (temp = ((IDebugableBlock)tBlock).getDebugInfo(aPlayer, aX, aY, aZ, 3)) != null) {
                tList.addAll(temp);
            }
            for (int i = 0; i < tList.size(); ++i) {
                ((EntityPlayerMP)aPlayer).playerNetServerHandler.sendPacketToPlayer((Packet)new Packet3Chat((String)tList.get(i)));
            }
            return true;
        }
        return false;
    }
}

