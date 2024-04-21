package gregtechmod.common.items;

import gregtechmod.api.ICapsuleCellContainer;
import net.minecraft.item.ItemStack;

public class GT_Cell_Item extends GT_Generic_Item implements ICapsuleCellContainer {

	public GT_Cell_Item(int aID) {
		super(aID);
	}

	@Override
	public int CapsuleCellContainerCount(ItemStack aStack) {
		return 1;
	}
	
	
}
