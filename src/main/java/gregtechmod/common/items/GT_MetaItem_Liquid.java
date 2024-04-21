package gregtechmod.common.items;

import gregtechmod.GT_Mod;
import gregtechmod.common.GT_Client;
import gregtechmod.common.GT_LanguageManager;
import gregtechmod.common.render.GT_LiquidFX;
import net.minecraft.item.ItemStack;
import net.minecraftforge.liquids.LiquidContainerData;
import net.minecraftforge.liquids.LiquidContainerRegistry;
import net.minecraftforge.liquids.LiquidDictionary;
import net.minecraftforge.liquids.LiquidStack;

public class GT_MetaItem_Liquid extends GT_MetaItem_Abstract {

	public static GT_MetaItem_Abstract instance;
	
	public GT_MetaItem_Liquid(int par1) {
		super(par1);
		instance = this;
		setTextureFile("/gregtechmod/textures/metaitems/liquids.png");
		setItemName("GT_Liquids");
	}
	
	public static ItemStack[] getStackList() {
		return instance.mStackList;
	}
	
	public static ItemStack addItem(int aMeta, String aName, String aMaterial, String aToolTip, ItemStack aFullContainer, ItemStack aEmptyContainer, int aR, int aG, int aB) {
		instance.mNameList[aMeta] = aName;
		GT_LanguageManager.addStringLocalization(instance.getItemName() + "." + aMeta + ".name", "Liquid " + aName);
		
		instance.mToolTipList[aMeta] = aToolTip;
		
		if (aMaterial != null && !aMaterial.equals("")) {
			instance.mStackList[aMeta] = LiquidDictionary.getOrCreateLiquid("fluid"+aMaterial, new LiquidStack(instance.shiftedIndex, 1000, aMeta)).asItemStack();
		} else {
			instance.mStackList[aMeta] = new LiquidStack(instance.shiftedIndex, 1000, aMeta).asItemStack();
		}
		
		if (aFullContainer != null && aEmptyContainer != null)
			LiquidContainerRegistry.registerLiquid(new LiquidContainerData(new LiquidStack(instance.mStackList[aMeta].itemID, 1000, instance.mStackList[aMeta].getItemDamage()), aFullContainer, aEmptyContainer));
		
		if (GT_Mod.gregtechproxy instanceof GT_Client) {
			new GT_LiquidFX(aMeta, aR-10, aR+10, aG-10, aG+10, aB-10, aB+10);
		}
		
		return instance.getUnunifiedStack(aMeta, 1000);
	}
}
