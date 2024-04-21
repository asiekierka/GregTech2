package gregtechmod.common.items;

import gregtechmod.common.GT_LanguageManager;
import net.minecraft.item.ItemStack;
import net.minecraftforge.liquids.LiquidContainerData;
import net.minecraftforge.liquids.LiquidContainerRegistry;
import net.minecraftforge.liquids.LiquidDictionary;
import net.minecraftforge.liquids.LiquidStack;

public class GT_MetaItem_Plasma extends GT_MetaItem_Abstract {

	public static GT_MetaItem_Abstract instance;
	
	public GT_MetaItem_Plasma(int par1) {
		super(par1);
		instance = this;
		setTextureFile("/gregtechmod/textures/metaitems/plasma.png");
		setItemName("GT_Plasma");
	}
	
	public static ItemStack[] getStackList() {
		return instance.mStackList;
	}
	
	public static ItemStack addItem(int aMeta, String aName, String aMaterial, String aToolTip, ItemStack aFullContainer, ItemStack aEmptyContainer) {
		instance.mNameList[aMeta] = aName;
		GT_LanguageManager.addStringLocalization(instance.getItemName() + "." + aMeta + ".name", aName + " Plasma");
		
		instance.mToolTipList[aMeta] = aToolTip;
		
		if (aMaterial != null && !aMaterial.equals("")) {
			instance.mStackList[aMeta] = LiquidDictionary.getOrCreateLiquid("plasma"+aMaterial, new LiquidStack(instance.shiftedIndex, 1000, aMeta)).asItemStack();
		} else {
			instance.mStackList[aMeta] = new LiquidStack(instance.shiftedIndex, 1000, aMeta).asItemStack();
		}
		
		if (aFullContainer != null && aEmptyContainer != null)
			LiquidContainerRegistry.registerLiquid(new LiquidContainerData(new LiquidStack(instance.mStackList[aMeta].itemID, 1000, instance.mStackList[aMeta].getItemDamage()), aFullContainer, aEmptyContainer));
		
		return instance.getUnunifiedStack(aMeta, 1000);
	}
}
