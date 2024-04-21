package gregtechmod.common.items;

import gregtechmod.common.GT_LanguageManager;
import gregtechmod.common.GT_ModHandler;
import gregtechmod.common.GT_OreDictUnificator;
import net.minecraft.item.ItemStack;

public class GT_MetaItem_Dust extends GT_MetaItem_Abstract {

	public static GT_MetaItem_Abstract instance;
	
	public GT_MetaItem_Dust(int par1) {
		super(par1);
		instance = this;
		setTextureFile("/gregtechmod/textures/metaitems/dusts.png");
		setItemName("GT_Dusts");
	}
	
	public static ItemStack[] getStackList() {
		return instance.mStackList;
	}
	
	public static ItemStack addItem(int aMeta, String aName, String aMaterial, String aToolTip) {
		instance.mNameList[aMeta] = aName;
		GT_LanguageManager.addStringLocalization(instance.getItemName() + "." + aMeta + ".name", aName);
		
		instance.mToolTipList[aMeta] = aToolTip;
		
		instance.mStackList[aMeta] = new ItemStack(instance.shiftedIndex, 1, aMeta);
		if (aMaterial != null && !aMaterial.equals("")) {
			GT_OreDictUnificator.add("dust" + aMaterial, instance.getUnunifiedStack(aMeta, 1));
			GT_ModHandler.registerOre("itemDust" + aMaterial, instance.getUnunifiedStack(aMeta, 1));
		}
		GT_MetaItem_SmallDust.addItem(aMeta, aName, aMaterial, instance.getStack(aMeta, 1));
		return instance.getUnunifiedStack(aMeta, 1);
	}
}
