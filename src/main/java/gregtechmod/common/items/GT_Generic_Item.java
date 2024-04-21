package gregtechmod.common.items;

import gregtechmod.GT_Mod;
import net.minecraft.item.Item;

public class GT_Generic_Item extends Item {

	public GT_Generic_Item(int aID) {
		super(aID);
		setTextureFile("/gregtechmod/textures/items.png");
		setCreativeTab(GT_Mod.tabGregTech);
	}
}
