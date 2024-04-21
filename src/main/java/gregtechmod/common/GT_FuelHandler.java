package gregtechmod.common;

import gregtechmod.GT_Mod;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.IFuelHandler;

public class GT_FuelHandler implements IFuelHandler {
	@Override
	public int getBurnTime(ItemStack aFuel) {
		if (aFuel == null) return 0;
		if (aFuel.isItemEqual(new ItemStack(Item.sign, 1)))		return   600;
		if (aFuel.isItemEqual(GT_Mod.getGregTechItem(1, 1,15)))	return   100;
		if (aFuel.isItemEqual(GT_Mod.getGregTechItem(0, 1,15)))	return  1600;
		return 0;
	}
}
