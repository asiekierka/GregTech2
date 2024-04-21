package gregtechmod.common.items;

import gregtechmod.common.GT_LanguageManager;
import gregtechmod.common.GT_ModHandler;
import net.minecraft.item.ItemStack;

public class GT_MetaItem_SmallDust extends GT_MetaItem_Abstract {

	public static GT_MetaItem_Abstract instance;
	
	public GT_MetaItem_SmallDust(int par1) {
		super(par1);
		instance = this;
		setTextureFile("/gregtechmod/textures/metaitems/dustssmall.png");
		setItemName("GT_SmallDusts");
        GT_ModHandler.addToRecyclerBlackList(new ItemStack(shiftedIndex, 1, -1));
	}
	
	public static ItemStack[] getStackList() {
		return instance.mStackList;
	}
	
	public static ItemStack addItem(int aMeta, String aName, String aMaterial, ItemStack aDust) {
		instance.mNameList[aMeta] = aName;
		GT_LanguageManager.addStringLocalization(instance.getItemName() + "." + aMeta + ".name", "Tiny Pile of " + aName);
		
		instance.mStackList[aMeta] = new ItemStack(instance.shiftedIndex, 1, aMeta);
		if (aMaterial != null && !aMaterial.equals("")) {
			GT_ModHandler.registerOre("itemDustSmall" + aMaterial, instance.getUnunifiedStack(aMeta, 1));
		}
		if (aDust != null) {
			aDust.stackSize = 1;
			GT_ModHandler.addShapelessCraftingRecipe(aDust.copy(), new Object[] {instance.getUnunifiedStack(aMeta, 1), instance.getUnunifiedStack(aMeta, 1), instance.getUnunifiedStack(aMeta, 1), instance.getUnunifiedStack(aMeta, 1)});
			GT_ModHandler.addShapelessCraftingRecipe(instance.getUnunifiedStack(aMeta, 4), new Object[] {(aMaterial!=null&&!aMaterial.equals(""))?"dust"+aMaterial:aDust.copy()});
		}
		return instance.getUnunifiedStack(aMeta, 1);
	}
}