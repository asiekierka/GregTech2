package gregtechmod.common.items;

import gregtechmod.GT_Mod;
import gregtechmod.api.ICapsuleCellContainer;
import ic2.api.IReactor;
import ic2.api.IReactorComponent;

import java.util.ArrayList;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class GT_RadioactiveCell_Item extends GT_Generic_Item implements IReactorComponent, ICapsuleCellContainer {

    private int maxDelay, cellCount, power;
    private ItemStack mDepleted;
	
    /**
     * Get a new Cell of Radioactive Material for your Nuclearreactor
     * @param aMaxDelay maximum Delay in seconds (Uranium == 10000) NBT is used for this instead of Damagevalue
     * @param aCellcount amount of Cells (Simple, Double, Quad etc)
     * @param aPower power in EU per pulse (Uranium == 5) will be at least 1 in any case
     * @param aDepleted the Depletedcell-Item of this Cell (amount will get multiplied by Cellcount for Double-/Quadcells)
     */
    public GT_RadioactiveCell_Item(int aID, int aMaxDelay, int aCellcount, int aPower, ItemStack aDepleted) {
        super(aID);
        setMaxStackSize(1);
        setMaxDamage(10000);
        setNoRepair();
        maxDelay = aMaxDelay;
        cellCount = Math.max(1, aCellcount);
        power = aPower;
        mDepleted = aDepleted;
    }
    
    private void setDurabilityForStack(ItemStack aStack, int aDurability) {
        NBTTagCompound tNBT = aStack.getTagCompound();
        if (tNBT == null) {
            tNBT = new NBTTagCompound();
            aStack.setTagCompound(tNBT);
        }
        
        tNBT.setInteger("durability", aDurability);

        if (maxDelay > 0) {
            double var4 = ((double)maxDelay-aDurability) / (double)maxDelay;
            int var6 = (int)((double)aStack.getMaxDamage() * var4);

            if (var6 >= aStack.getMaxDamage()) {
                var6 = aStack.getMaxDamage() - 1;
            }
            aStack.setItemDamage(aStack.getMaxDamage() - var6);
        }
    }

    private int getDurabilityOfStack(ItemStack aStack) {
        NBTTagCompound tNBT = aStack.getTagCompound();
        if (tNBT == null) {
            tNBT = new NBTTagCompound();
            aStack.setTagCompound(tNBT);
        }
        return tNBT.getInteger("durability");
    }
    
	@Override
	public boolean acceptUraniumPulse(IReactor aReactor, ItemStack aStack, ItemStack pulsingStack, int youX, int youY, int pulseX, int pulseY) {
		if (aStack.stackSize > 1) return false;
		aReactor.addOutput(Math.max(power, 1));
		return true;
	}

	@Override
	public boolean canStoreHeat(IReactor aReactor, ItemStack aStack, int x, int y) {
		return false;
	}

	@Override
	public int getMaxHeat(IReactor aReactor, ItemStack aStack, int x, int y) {
		return 0;
	}

	@Override
	public int getCurrentHeat(IReactor aReactor, ItemStack aStack, int x, int y) {
		return 0;
	}

	@Override
	public float influenceExplosion(IReactor aReactor, ItemStack aStack) {
		return (float)(aStack.stackSize * cellCount * Math.max(power / 2, 1));
	}

	@Override
    public int alterHeat(IReactor aReactor, ItemStack aStack, int x, int y, int aHeat) {
        return aHeat;
    }
    
    public void processChamber(IReactor aReactor, ItemStack aStack, int x, int y) {
		if (aStack.stackSize > 1) return ;
        if (aReactor.produceEnergy()) {
            for (int var5 = 0; var5 < cellCount; ++var5) {
                int tPulsables = 1 + cellCount / 2;

                for (int i = 0; i < tPulsables; ++i) {
                    acceptUraniumPulse(aReactor, aStack, aStack, x, y, x, y);
                }

                tPulsables += checkPulseable(aReactor, x - 1, y, aStack, x, y) + this.checkPulseable(aReactor, x + 1, y, aStack, x, y) + this.checkPulseable(aReactor, x, y - 1, aStack, x, y) + this.checkPulseable(aReactor, x, y + 1, aStack, x, y);
                int tAddedHeat = sumUp(tPulsables) * Math.max(power-1, 1);
                ArrayList tList = new ArrayList<ItemStackCoord>();
                this.checkHeatAcceptor(aReactor, x - 1, y, tList);
                this.checkHeatAcceptor(aReactor, x + 1, y, tList);
                this.checkHeatAcceptor(aReactor, x, y - 1, tList);
                this.checkHeatAcceptor(aReactor, x, y + 1, tList);

                while (tList.size() > 0 && tAddedHeat > 0) {
                    int tDistributedHeatPerStack = tAddedHeat / tList.size();
                    tAddedHeat -= tDistributedHeatPerStack;
                    tDistributedHeatPerStack = ((IReactorComponent)((ItemStackCoord)tList.get(0)).stack.getItem()).alterHeat(aReactor, ((ItemStackCoord)tList.get(0)).stack, ((ItemStackCoord)tList.get(0)).x, ((ItemStackCoord)tList.get(0)).y, tDistributedHeatPerStack);
                    tAddedHeat += tDistributedHeatPerStack;
                    tList.remove(0);
                }

                if (tAddedHeat > 0) {
                    aReactor.addHeat(tAddedHeat);
                }
            }
            
            if (getDurabilityOfStack(aStack) >= maxDelay - 1) {
                aReactor.setItemAt(x, y, null);
                if (mDepleted != null) {
	                for (int i = 0; i < cellCount; i++) {
	                	if (GT_Mod.Randomizer.nextInt(4) == 0) {
	                		if (aReactor.getItemAt(x, y) == null) {
	                            aReactor.setItemAt(x, y, mDepleted.copy());
	                		} else {
	                			aReactor.getItemAt(x, y).stackSize += mDepleted.stackSize;
	                		}
	                	}
                	}
                }
            } else {
                setDurabilityForStack(aStack, getDurabilityOfStack(aStack) + 1);
            }
        }
    }

    private int checkPulseable(IReactor var1, int var2, int var3, ItemStack var4, int var5, int var6) {
        ItemStack var7 = var1.getItemAt(var2, var3);
        return var7 != null && var7.getItem() instanceof IReactorComponent && ((IReactorComponent)var7.getItem()).acceptUraniumPulse(var1, var7, var4, var2, var3, var5, var6) ? 1 : 0;
    }
    
    private int sumUp(int var1) {
        int var2 = 0;

        for (int var3 = 1; var3 <= var1; ++var3) {
            var2 += var3;
        }

        return var2;
    }

    private void checkHeatAcceptor(IReactor var1, int var2, int var3, ArrayList var4) {
        ItemStack var5 = var1.getItemAt(var2, var3);

        if (var5 != null && var5.getItem() instanceof IReactorComponent && ((IReactorComponent)var5.getItem()).canStoreHeat(var1, var5, var2, var3)) {
            var4.add(new ItemStackCoord(this, var5, var2, var3));
        }
    }
    
    protected class ItemStackCoord {
        public ItemStack stack;
        public int x;
        public int y;

        final GT_RadioactiveCell_Item mThis;

        public ItemStackCoord(GT_RadioactiveCell_Item var1, ItemStack var2, int var3, int var4) {
        	mThis = var1;
            stack = var2;
            x = var3;
            y = var4;
        }
    }

	@Override
	public int CapsuleCellContainerCount(ItemStack aStack) {
		return cellCount;
	}
}
