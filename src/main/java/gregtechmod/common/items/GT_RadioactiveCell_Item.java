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
import java.util.ArrayList;
import java.util.List;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class GT_RadioactiveCell_Item
extends GT_Generic_Item
implements IReactorComponent,
ICapsuleCellContainer {
    private int maxDelay;
    private int cellCount;
    private int pulserate;
    private ItemStack mDepleted;

    public GT_RadioactiveCell_Item(int aID, int aMaxDelay, int aCellcount, int aPulseRate, ItemStack aDepleted) {
        super(aID);
        this.setMaxStackSize(1);
        this.setMaxDamage(10000);
        this.setNoRepair();
        this.pulserate = aPulseRate;
        this.maxDelay = aMaxDelay;
        this.cellCount = Math.max(1, aCellcount);
        this.mDepleted = aDepleted;
    }

    private boolean outputPulseForStack(ItemStack aStack) {
        NBTTagCompound tNBT = aStack.getTagCompound();
        if (tNBT == null) {
            tNBT = new NBTTagCompound();
            aStack.setTagCompound(tNBT);
        }
        tNBT.setInteger("output", tNBT.getInteger("output") + 1);
        return this.pulserate > 0 || tNBT.getInteger("output") % -this.pulserate == 0;
    }

    private boolean incrementPulseForStack(ItemStack aStack) {
        NBTTagCompound tNBT = aStack.getTagCompound();
        if (tNBT == null) {
            tNBT = new NBTTagCompound();
            aStack.setTagCompound(tNBT);
        }
        tNBT.setInteger("pulse", tNBT.getInteger("pulse") + 1);
        return this.pulserate > 0 || tNBT.getInteger("pulse") % -this.pulserate == 0;
    }

    private void setDurabilityForStack(ItemStack aStack, int aDurability) {
        NBTTagCompound tNBT = aStack.getTagCompound();
        if (tNBT == null) {
            tNBT = new NBTTagCompound();
            aStack.setTagCompound(tNBT);
        }
        tNBT.setInteger("durability", aDurability);
        if (this.maxDelay > 0) {
            double var4 = ((double)this.maxDelay - (double)aDurability) / (double)this.maxDelay;
            int var6 = (int)((double)aStack.getMaxDamage() * var4);
            if (var6 >= aStack.getMaxDamage()) {
                var6 = aStack.getMaxDamage() - 1;
            }
            aStack.setItemDamage(aStack.getMaxDamage() - var6);
        }
    }

    public int getDurabilityOfStack(ItemStack aStack) {
        NBTTagCompound tNBT = aStack.getTagCompound();
        if (tNBT == null) {
            tNBT = new NBTTagCompound();
            aStack.setTagCompound(tNBT);
        }
        return tNBT.getInteger("durability");
    }

    public int getMaxNuclearDurability() {
        return this.maxDelay;
    }

    public void addInformation(ItemStack aStack, EntityPlayer aPlayer, List aList, boolean aF3_H) {
        aList.add("Time left: " + (this.maxDelay - this.getDurabilityOfStack(aStack)) + " secs");
    }

    public boolean acceptUraniumPulse(IReactor aReactor, ItemStack aStack, ItemStack pulsingStack, int youX, int youY, int pulseX, int pulseY) {
        if (aStack.stackSize > 1) {
            return false;
        }
        if (this.pulserate > 0) {
            for (int i = 0; i < this.pulserate; ++i) {
                aReactor.addOutput(1);
            }
        }
        if (this.pulserate < 0 && this.incrementPulseForStack(aStack)) {
            aReactor.addOutput(1);
        }
        return true;
    }

    public boolean canStoreHeat(IReactor aReactor, ItemStack aStack, int x, int y) {
        return false;
    }

    public int getMaxHeat(IReactor aReactor, ItemStack aStack, int x, int y) {
        return 0;
    }

    public int getCurrentHeat(IReactor aReactor, ItemStack aStack, int x, int y) {
        return 0;
    }

    public float influenceExplosion(IReactor aReactor, ItemStack aStack) {
        return aStack.stackSize * this.cellCount * (this.pulserate > 0 ? this.pulserate : 1);
    }

    public int alterHeat(IReactor aReactor, ItemStack aStack, int x, int y, int aHeat) {
        return aHeat;
    }

    public void processChamber(IReactor aReactor, ItemStack aStack, int x, int y) {
        if (aStack.stackSize > 1) {
            return;
        }
        if (aReactor.produceEnergy()) {
            for (int j = 0; j < this.cellCount; ++j) {
                int i;
                int tPulsables = 1 + this.cellCount / 2;
                for (i = 0; i < tPulsables; ++i) {
                    this.acceptUraniumPulse(aReactor, aStack, aStack, x, y, x, y);
                }
                if (this.pulserate == 0 || !this.outputPulseForStack(aStack)) continue;
                for (i = 0; i < this.pulserate || i == 0; ++i) {
                    int tAddedHeat;
                    int tDistributedHeatPerStack;
                    ArrayList tList = new ArrayList();
                    this.checkHeatAcceptor(aReactor, x - 1, y, tList);
                    this.checkHeatAcceptor(aReactor, x + 1, y, tList);
                    this.checkHeatAcceptor(aReactor, x, y - 1, tList);
                    this.checkHeatAcceptor(aReactor, x, y + 1, tList);
                    for (tAddedHeat = this.sumUp(tPulsables += this.checkPulseable(aReactor, x - 1, y, aStack, x, y) + this.checkPulseable(aReactor, x + 1, y, aStack, x, y) + this.checkPulseable(aReactor, x, y - 1, aStack, x, y) + this.checkPulseable(aReactor, x, y + 1, aStack, x, y)) * 4; tList.size() > 0 && tAddedHeat > 0; tAddedHeat += tDistributedHeatPerStack) {
                        tDistributedHeatPerStack = tAddedHeat / tList.size();
                        tAddedHeat -= tDistributedHeatPerStack;
                        tDistributedHeatPerStack = ((IReactorComponent)((ItemStackCoord)tList.get((int)0)).stack.getItem()).alterHeat(aReactor, ((ItemStackCoord)tList.get((int)0)).stack, ((ItemStackCoord)tList.get((int)0)).x, ((ItemStackCoord)tList.get((int)0)).y, tDistributedHeatPerStack);
                        tList.remove(0);
                    }
                    if (tAddedHeat <= 0) continue;
                    aReactor.addHeat(tAddedHeat);
                }
            }
            if (this.getDurabilityOfStack(aStack) >= this.maxDelay - 1) {
                aReactor.setItemAt(x, y, null);
                if (this.mDepleted != null) {
                    for (int i = 0; i < this.cellCount; ++i) {
                        if (GT_Mod.Randomizer.nextInt(4) != 0) continue;
                        if (aReactor.getItemAt(x, y) == null) {
                            aReactor.setItemAt(x, y, this.mDepleted.copy());
                            continue;
                        }
                        aReactor.getItemAt((int)x, (int)y).stackSize += this.mDepleted.stackSize;
                    }
                }
            } else {
                this.setDurabilityForStack(aStack, this.getDurabilityOfStack(aStack) + 1);
            }
        }
    }

    private int checkPulseable(IReactor var1, int var2, int var3, ItemStack var4, int var5, int var6) {
        ItemStack var7 = var1.getItemAt(var2, var3);
        return var7 != null && var7.getItem() instanceof IReactorComponent && ((IReactorComponent)var7.getItem()).acceptUraniumPulse(var1, var7, var4, var2, var3, var5, var6) ? 1 : 0;
    }

    private int sumUp(int a) {
        int b = 0;
        for (int c = 1; c <= a; ++c) {
            b += c;
        }
        return b;
    }

    private void checkHeatAcceptor(IReactor var1, int var2, int var3, ArrayList var4) {
        ItemStack var5 = var1.getItemAt(var2, var3);
        if (var5 != null && var5.getItem() instanceof IReactorComponent && ((IReactorComponent)var5.getItem()).canStoreHeat(var1, var5, var2, var3)) {
            var4.add(new ItemStackCoord(this, var5, var2, var3));
        }
    }

    @Override
    public int CapsuleCellContainerCount(ItemStack aStack) {
        return this.cellCount;
    }

    protected class ItemStackCoord {
        public ItemStack stack;
        public int x;
        public int y;
        final GT_RadioactiveCell_Item mThis;

        public ItemStackCoord(GT_RadioactiveCell_Item var1, ItemStack var2, int var3, int var4) {
            this.mThis = var1;
            this.stack = var2;
            this.x = var3;
            this.y = var4;
        }
    }
}

