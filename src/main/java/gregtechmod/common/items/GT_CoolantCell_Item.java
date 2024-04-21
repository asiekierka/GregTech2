/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  ic2.api.IReactor
 *  ic2.api.IReactorComponent
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagCompound
 */
package gregtechmod.common.items;

import gregtechmod.GT_Mod;
import gregtechmod.api.ICapsuleCellContainer;
import gregtechmod.common.items.GT_Generic_Item;
import ic2.api.IReactor;
import ic2.api.IReactorComponent;
import java.util.List;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class GT_CoolantCell_Item
extends GT_Generic_Item
implements IReactorComponent,
ICapsuleCellContainer {
    private int heatStorage;
    private int mCellCount;

    public GT_CoolantCell_Item(int aID, int aMaxStore, int aCellCount) {
        super(aID);
        this.setMaxStackSize(1);
        this.setMaxDamage(100);
        this.setNoRepair();
        this.heatStorage = aMaxStore;
        this.mCellCount = aCellCount;
        this.setCreativeTab(GT_Mod.tabGregTech);
    }

    public void processChamber(IReactor aReactor, ItemStack aStack, int x, int y) {
    }

    public boolean acceptUraniumPulse(IReactor aReactor, ItemStack aStack, ItemStack pulsingStack, int youX, int youY, int pulseX, int pulseY) {
        return false;
    }

    public boolean canStoreHeat(IReactor aReactor, ItemStack aStack, int x, int y) {
        return true;
    }

    public int getMaxHeat(IReactor aReactor, ItemStack aStack, int x, int y) {
        return this.heatStorage;
    }

    public int getCurrentHeat(IReactor aReactor, ItemStack aStack, int x, int y) {
        return this.getHeatOfStack(aStack);
    }

    public float influenceExplosion(IReactor aReactor, ItemStack aStack) {
        return 1.0f + (float)this.heatStorage / 30000.0f;
    }

    public int alterHeat(IReactor aReactor, ItemStack aStack, int x, int y, int aHeat) {
        int tHeat = this.getHeatOfStack(aStack);
        if ((tHeat += aHeat) > this.heatStorage) {
            aReactor.setItemAt(x, y, (ItemStack)null);
            aHeat = this.heatStorage - tHeat + 1;
        } else {
            if (tHeat < 0) {
                aHeat = tHeat;
                tHeat = 0;
            } else {
                aHeat = 0;
            }
            this.setHeatForStack(aStack, tHeat);
        }
        return aHeat;
    }

    private void setHeatForStack(ItemStack aStack, int aHeat) {
        NBTTagCompound tNBT = aStack.getTagCompound();
        if (tNBT == null) {
            tNBT = new NBTTagCompound();
            aStack.setTagCompound(tNBT);
        }
        tNBT.setInteger("heat", aHeat);
        if (this.heatStorage > 0) {
            double var4 = (double)aHeat / (double)this.heatStorage;
            int var6 = (int)((double)aStack.getMaxDamage() * var4);
            if (var6 >= aStack.getMaxDamage()) {
                var6 = aStack.getMaxDamage() - 1;
            }
            aStack.setItemDamage(var6);
        }
    }

    public void addInformation(ItemStack aStack, EntityPlayer aPlayer, List aList, boolean aF3_H) {
        aList.add("Stored Heat: " + this.getHeatOfStack(aStack));
    }

    private int getHeatOfStack(ItemStack aStack) {
        NBTTagCompound tNBT = aStack.getTagCompound();
        if (tNBT == null) {
            tNBT = new NBTTagCompound();
            aStack.setTagCompound(tNBT);
        }
        return tNBT.getInteger("heat");
    }

    @Override
    public int CapsuleCellContainerCount(ItemStack aStack) {
        return this.mCellCount;
    }
}

