package gregtechmod.common.items;

import gregtechmod.api.ICapsuleCellContainer;
import gregtechmod.common.GT_LanguageManager;
import gregtechmod.common.GT_ModHandler;
import net.minecraft.item.ItemStack;

public class GT_MetaItem_Cell extends GT_MetaItem_Abstract implements ICapsuleCellContainer {

	public static GT_MetaItem_Abstract instance;
	
	public GT_MetaItem_Cell(int par1) {
		super(par1);
		instance = this;
		setTextureFile("/gregtechmod/textures/metaitems/cells.png");
		setItemName("GT_Cells");
	}
	
	public static ItemStack[] getStackList() {
		return instance.mStackList;
	}
	
	public static ItemStack addItem(int aMeta, String aName, String aMaterial, String aToolTip) {
		instance.mNameList[aMeta] = aName;
		GT_LanguageManager.addStringLocalization(instance.getItemName() + "." + aMeta + ".name", aName);
		
		instance.mToolTipList[aMeta] = aToolTip;
		
		instance.mStackList[aMeta] = new ItemStack(instance.shiftedIndex, 1, aMeta);
		
		GT_ModHandler.addExtractionRecipe(instance.getUnunifiedStack(aMeta, 1), GT_ModHandler.getIC2Item("cell", 1));
		
		if (aMaterial != null && !aMaterial.equals("")) {
			GT_ModHandler.registerOre("element_" + aMaterial, instance.getUnunifiedStack(aMeta, 1));
			GT_ModHandler.registerOre("molecule_" + aMaterial, instance.getUnunifiedStack(aMeta, 1));
			GT_ModHandler.registerOre("cell_" + aMaterial, instance.getUnunifiedStack(aMeta, 1));
			if (aMaterial.equals("1me")) {
				GT_ModHandler.registerOre("element_1c_4h", instance.getUnunifiedStack(aMeta, 1));
				GT_ModHandler.registerOre("molecule_1c_4h", instance.getUnunifiedStack(aMeta, 1));
				GT_ModHandler.registerOre("cell_1c_4h", instance.getUnunifiedStack(aMeta, 1));
			}
			if (aMaterial.equals("1d")) {
				GT_ModHandler.registerOre("element_1h2", instance.getUnunifiedStack(aMeta, 1));
				GT_ModHandler.registerOre("molecule_1h2", instance.getUnunifiedStack(aMeta, 1));
				GT_ModHandler.registerOre("cell_1h2", instance.getUnunifiedStack(aMeta, 1));
			}
			if (aMaterial.equals("1t")) {
				GT_ModHandler.registerOre("element_1h3", instance.getUnunifiedStack(aMeta, 1));
				GT_ModHandler.registerOre("molecule_1h3", instance.getUnunifiedStack(aMeta, 1));
				GT_ModHandler.registerOre("cell_1h3", instance.getUnunifiedStack(aMeta, 1));
			}
		}
		
		return instance.getUnunifiedStack(aMeta, 1);
	}
	
	@Override
	public int CapsuleCellContainerCount(ItemStack aStack) {
		return 1;
	}
}
