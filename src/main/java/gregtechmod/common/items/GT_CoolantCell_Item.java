package gregtechmod.common.items;

import gregtechmod.GT_Mod;
import gregtechmod.api.ICapsuleCellContainer;
import ic2.api.IReactor;
import ic2.api.IReactorComponent;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class GT_CoolantCell_Item extends GT_Generic_Item implements IReactorComponent, ICapsuleCellContainer {

    private int heatStorage, mCellCount;
	
    public GT_CoolantCell_Item(int aID, int aMaxStore, int aCellCount) {
        super(aID);
        setMaxStackSize(1);
        setMaxDamage(100);
        setNoRepair();
        heatStorage = aMaxStore;
        mCellCount = aCellCount;
        setCreativeTab(GT_Mod.tabGregTech);
    }

	@Override
	public void processChamber(IReactor aReactor, ItemStack aStack, int x, int y) {
		return;
	}

	@Override
	public boolean acceptUraniumPulse(IReactor aReactor, ItemStack aStack, ItemStack pulsingStack, int youX, int youY, int pulseX, int pulseY) {
		return false;
	}

	@Override
	public boolean canStoreHeat(IReactor aReactor, ItemStack aStack, int x, int y) {
		return true;
	}

	@Override
	public int getMaxHeat(IReactor aReactor, ItemStack aStack, int x, int y) {
		return heatStorage;
	}

	@Override
	public int getCurrentHeat(IReactor aReactor, ItemStack aStack, int x, int y) {
		return getHeatOfStack(aStack);
	}

	@Override
	public float influenceExplosion(IReactor aReactor, ItemStack aStack) {
		return 1.0F+heatStorage/30000.0F;
	}

	@Override
    public int alterHeat(IReactor aReactor, ItemStack aStack, int x, int y, int aHeat) {
        int tHeat = getHeatOfStack(aStack);
        tHeat += aHeat;

        if (tHeat > heatStorage) {
            aReactor.setItemAt(x, y, (ItemStack)null);
            aHeat = heatStorage - tHeat + 1;
        } else {
            if (tHeat < 0) {
                aHeat = tHeat;
                tHeat = 0;
            } else {
                aHeat = 0;
            }
            setHeatForStack(aStack, tHeat);
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

        if (heatStorage > 0) {
            double var4 = (double)aHeat / (double)heatStorage;
            int var6 = (int)((double)aStack.getMaxDamage() * var4);

            if (var6 >= aStack.getMaxDamage()) {
                var6 = aStack.getMaxDamage() - 1;
            }
            aStack.setItemDamage(var6);
        }
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
		return mCellCount;
	}
}
