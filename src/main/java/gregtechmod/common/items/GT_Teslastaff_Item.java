package gregtechmod.common.items;

import gregtechmod.GT_Mod;
import ic2.api.ElectricItem;
import ic2.api.IElectricItem;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.minecraft.util.DamageSource;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class GT_Teslastaff_Item extends ItemTool implements IElectricItem {
	public int mCharge, mTransfer, mTier;
	
    public GT_Teslastaff_Item(int aID) {
        super(aID, 0, EnumToolMaterial.GOLD, new Block[0]);
		setTextureFile("/gregtechmod/textures/items.png");
		setCreativeTab(GT_Mod.tabGregTech);
		setMaxStackSize(1);
		setMaxDamage(100);
		setNoRepair();
        mCharge = 10000000;
        mTransfer = 8192;
        mTier = 4;
    }

    @Override
    public boolean hitEntity(ItemStack aStack, EntityLiving aTarget, EntityLiving aPlayer) {
        if (aTarget instanceof EntityPlayer && aPlayer instanceof EntityPlayer && ElectricItem.canUse(aStack, 9000000)) {
            EntityPlayer tTarget = (EntityPlayer)aTarget, tPlayer = (EntityPlayer)aPlayer;
            ElectricItem.use(aStack, 9000000, tPlayer);
            for (int i = 0; i < 4; i++) {
            	if (tTarget.inventory.armorInventory[i] != null) {
            		if (tTarget.inventory.armorInventory[i].getItem() instanceof IElectricItem) {
            			tTarget.inventory.armorInventory[i] = null;
            		}
            	}
            }
            aPlayer.attackEntityFrom(DamageSource.magic, 19);
            aTarget.attackEntityFrom(DamageSource.magic, 19);
        }
        return true;
    }
    
    @SideOnly(Side.CLIENT)
    public void getSubItems(int var1, CreativeTabs var2, List var3) {
        ItemStack tCharged = new ItemStack(this, 1), tUncharged = new ItemStack(this, 1, getMaxDamage());
        ElectricItem.charge(tCharged, Integer.MAX_VALUE, Integer.MAX_VALUE, true, false);
        var3.add(tCharged);
        var3.add(tUncharged);
    }

    @Override
    public boolean isFull3D() {
        return true;
    }

    @Override
    public boolean getShareTag() {
        return true;
    }
    
	@Override
	public boolean canProvideEnergy() {
		return false;
	}
	
	@Override
	public int getChargedItemId() {
		return shiftedIndex;
	}
	
	@Override
	public int getEmptyItemId() {
		return shiftedIndex;
	}
	
	@Override
	public int getMaxCharge() {
		return mCharge;
	}
	
	@Override
	public int getTier() {
		return mTier;
	}
	
	@Override
	public int getTransferLimit() {
		return mTransfer;
	}
}
