package gregtechmod.common.items;

import net.minecraft.item.ItemStack;

public class GT_Mortar_Item extends GT_Generic_Item {

	ItemStack mBrokenItem;
	
	public GT_Mortar_Item(int aID, int aMaxDamage, ItemStack aBrokenItem) {
		super(aID);
		setMaxDamage(aMaxDamage-1);
		setMaxStackSize(1);
		mBrokenItem = aBrokenItem;
	}
	
    public ItemStack getContainerItemStack(ItemStack aStack) {
        if (aStack.getItemDamage() >= getMaxDamage()) return mBrokenItem.copy();
        return new ItemStack(shiftedIndex, 1, aStack.getItemDamage()+1);
    }
    
    public boolean hasContainerItem() {
        return true;
    }
    
    public boolean doesContainerItemLeaveCraftingGrid(ItemStack par1ItemStack) {
        return false;
    }
}
