package gregtechmod.common.items;

import gregtechmod.GT_Mod;
import gregtechmod.common.GT_Client;
import gregtechmod.common.GT_LanguageManager;
import gregtechmod.common.render.GT_GasFX;
import net.minecraft.item.ItemStack;
import net.minecraftforge.liquids.LiquidContainerData;
import net.minecraftforge.liquids.LiquidContainerRegistry;
import net.minecraftforge.liquids.LiquidDictionary;
import net.minecraftforge.liquids.LiquidStack;

public class GT_MetaItem_Gas extends GT_MetaItem_Abstract {

	public static GT_MetaItem_Abstract instance;
	
	public GT_MetaItem_Gas(int par1) {
		super(par1);
		instance = this;
		setTextureFile("/gregtechmod/textures/metaitems/gasses.png");
		setItemName("GT_Gasses");
	}
	
	public static ItemStack[] getStackList() {
		return instance.mStackList;
	}
	
	public static ItemStack addItem(int aMeta, String aName, String aMaterial, String aToolTip, ItemStack aFullContainer, ItemStack aEmptyContainer) {
		instance.mNameList[aMeta] = aName;
		GT_LanguageManager.addStringLocalization(instance.getItemName() + "." + aMeta + ".name", "Gaseous " + aName);
		
		instance.mToolTipList[aMeta] = aToolTip;
		
		if (aMaterial != null && !aMaterial.equals("")) {
			instance.mStackList[aMeta] = LiquidDictionary.getOrCreateLiquid("gas"+aMaterial, new LiquidStack(instance.shiftedIndex, 1000, aMeta)).asItemStack();
		} else {
			instance.mStackList[aMeta] = new LiquidStack(instance.shiftedIndex, 1000, aMeta).asItemStack();
		}
		
		if (aFullContainer != null && aEmptyContainer != null)
			LiquidContainerRegistry.registerLiquid(new LiquidContainerData(new LiquidStack(instance.mStackList[aMeta].itemID, 1000, instance.mStackList[aMeta].getItemDamage()), aFullContainer, aEmptyContainer));
		
		return instance.getUnunifiedStack(aMeta, 1000);
	}
}
