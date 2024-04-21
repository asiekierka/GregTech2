package gregtechmod.common.items;

import gregtechmod.GT_Mod;
import gregtechmod.common.GT_OreDictUnificator;

import java.util.List;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class GT_MetaItem_Abstract extends Item {
	public String[] mNameList = new String[256], mToolTipList = new String[256];
	public ItemStack[] mStackList = new ItemStack[256];
	
	public GT_MetaItem_Abstract(int par1) {
		super(par1);
		setCreativeTab(GT_Mod.tabGregTech);
        setMaxDamage(0);
        setHasSubtypes(true);
	}
	
	@Override
    public int getIconFromDamage(int par1) {
        return par1;
    }
	
	@Override
    public int getMetadata(int par1) {
        return par1;
    }
	
	@Override
    public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4) {
		if (par1ItemStack.getItemDamage() < 256 && mToolTipList[par1ItemStack.getItemDamage()] != null) {
			if (mToolTipList[par1ItemStack.getItemDamage()] != null && !mToolTipList[par1ItemStack.getItemDamage()].equals("")) par3List.add(mToolTipList[par1ItemStack.getItemDamage()]);
		}
    }
	
	@Override
    public String getItemNameIS(ItemStack aStack) {
    	return getItemName() + "." + aStack.getItemDamage();
    }
	
	@Override
    @SideOnly(Side.CLIENT)
    public void getSubItems(int var1, CreativeTabs var2, List var3) {
		for (int i = 0; i < 256; i++) if (mStackList[i] != null) {
			var3.add(getUnunifiedStack(i, 1));
		}
    }
	
	public static ItemStack[] getStackList() {
		return null;
	}
	
	public ItemStack getUnunifiedStack(int aMeta, int aCount) {
		if (mStackList[aMeta] == null) return null;
		ItemStack rStack = mStackList[aMeta].copy();
		rStack.stackSize = aCount;
		return rStack;
	}
	
	public ItemStack getStack(int aMeta, int aCount) {
		if (mStackList[aMeta] == null) return null;
		ItemStack rStack = mStackList[aMeta].copy(), tStack = GT_OreDictUnificator.get(rStack);
		if (tStack != null) rStack = tStack;
		rStack.stackSize = aCount;
		return rStack;
	}
}
