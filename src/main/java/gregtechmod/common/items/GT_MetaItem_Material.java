package gregtechmod.common.items;

import gregtechmod.common.GT_LanguageManager;
import gregtechmod.common.GT_ModHandler;
import gregtechmod.common.GT_OreDictUnificator;
import net.minecraft.item.ItemStack;

public class GT_MetaItem_Material extends GT_MetaItem_Abstract {

	public static GT_MetaItem_Abstract instance;
	
	public GT_MetaItem_Material(int par1) {
		super(par1);
		instance = this;
		setTextureFile("/gregtechmod/textures/metaitems/materials.png");
		setItemName("GT_Materials");
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
			if (aMaterial.startsWith("ingot") || aMaterial.startsWith("gem"))
				GT_OreDictUnificator.add(aMaterial, instance.getUnunifiedStack(aMeta, 1));
			else
				GT_ModHandler.registerOre(aMaterial, instance.getUnunifiedStack(aMeta, 1));
		}
		return instance.getUnunifiedStack(aMeta, 1);
	}
}
