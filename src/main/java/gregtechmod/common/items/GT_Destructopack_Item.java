package gregtechmod.common.items;

import gregtechmod.GT_Mod;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class GT_Destructopack_Item extends GT_Generic_Item {
	
	public GT_Destructopack_Item(int par1) {
		super(par1);
		setMaxStackSize(1);
		setNoRepair();
	}

    @Override
	public ItemStack onItemRightClick(ItemStack aStack, World aWorld, EntityPlayer aPlayer) {
		aPlayer.openGui(GT_Mod.instance, 33, aWorld, 0, 0, 0);
		return aStack;
	}
}
