package gregtechmod.common.items;

import gregtechmod.common.GT_LanguageManager;
import gregtechmod.common.GT_ModHandler;
import net.minecraft.item.ItemStack;

public class GT_MetaItem_Component extends GT_MetaItem_Abstract {

	public static GT_MetaItem_Abstract instance;
	
	public GT_MetaItem_Component(int par1) {
		super(par1);
		instance = this;
		setTextureFile("/gregtechmod/textures/metaitems/components.png");
		setItemName("GT_Components");
	}
	
	public static ItemStack[] getStackList() {
		return instance.mStackList;
	}
	
	public static ItemStack addItem(int aMeta, String aName, String aMaterial) {
		instance.mNameList[aMeta] = aName;
		GT_LanguageManager.addStringLocalization(instance.getItemName() + "." + aMeta + ".name", aName);
		
		instance.mStackList[aMeta] = new ItemStack(instance.shiftedIndex, 1, aMeta);
		if (aMaterial != null && !aMaterial.equals("")) {
			GT_ModHandler.registerOre(aMaterial, instance.getUnunifiedStack(aMeta, 1));
		}
		return instance.getUnunifiedStack(aMeta, 1);
	}
}
