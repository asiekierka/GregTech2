package gregtechmod.common.items;

import gregtechmod.GT_Mod;
import gregtechmod.api.IPlayerTickingItem;
import ic2.api.ElectricItem;
import ic2.api.IElectricItem;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeHooks;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class GT_Rockcutter_Item extends ItemTool implements IElectricItem, IPlayerTickingItem {
	public int mCharge, mTransfer, mTier;

    public Set mineableBlocks = new HashSet();
    
	public GT_Rockcutter_Item(int aID) {
		super(aID, 0, EnumToolMaterial.IRON, new Block[0]);
		setTextureFile("/gregtechmod/textures/items.png");
		setCreativeTab(GT_Mod.tabGregTech);
		setMaxStackSize(1);
		setMaxDamage(100);
		setNoRepair();
		mCharge = 10000;
		mTransfer = 100;
		mTier = 1;
        efficiencyOnProperMaterial = 2.0F;
        
        mineableBlocks.add(Block.cobblestone);
        mineableBlocks.add(Block.stoneSingleSlab);
        mineableBlocks.add(Block.stoneDoubleSlab);
        mineableBlocks.add(Block.stairCompactCobblestone);
        mineableBlocks.add(Block.stone);
        mineableBlocks.add(Block.sandStone);
        mineableBlocks.add(Block.stairsSandStone);
        mineableBlocks.add(Block.cobblestoneMossy);
        mineableBlocks.add(Block.oreIron);
        mineableBlocks.add(Block.blockSteel);
        mineableBlocks.add(Block.oreCoal);
        mineableBlocks.add(Block.blockGold);
        mineableBlocks.add(Block.oreGold);
        mineableBlocks.add(Block.oreDiamond);
        mineableBlocks.add(Block.blockDiamond);
        mineableBlocks.add(Block.ice);
        mineableBlocks.add(Block.netherrack);
        mineableBlocks.add(Block.oreLapis);
        mineableBlocks.add(Block.blockLapis);
        mineableBlocks.add(Block.oreRedstone);
        mineableBlocks.add(Block.oreRedstoneGlowing);
        mineableBlocks.add(Block.brick);
        mineableBlocks.add(Block.stairsBrick);
        mineableBlocks.add(Block.glowStone);
        mineableBlocks.add(Block.grass);
        mineableBlocks.add(Block.dirt);
        mineableBlocks.add(Block.mycelium);
        mineableBlocks.add(Block.sand);
        mineableBlocks.add(Block.gravel);
        mineableBlocks.add(Block.snow);
        mineableBlocks.add(Block.blockSnow);
        mineableBlocks.add(Block.blockClay);
        mineableBlocks.add(Block.tilledField);
        mineableBlocks.add(Block.stoneBrick);
        mineableBlocks.add(Block.stairsStoneBrickSmooth);
        mineableBlocks.add(Block.netherBrick);
        mineableBlocks.add(Block.stairsNetherBrick);
        mineableBlocks.add(Block.obsidian);
	}

	@Override
	public boolean onTick(EntityPlayer aPlayer, ItemStack aStack, int aTimer, boolean aIsArmor) {
		if (!ElectricItem.canUse(aStack, 500)) {
			if (aStack.isItemEnchanted())
				aStack.stackTagCompound.getTags().remove(aStack.stackTagCompound.getTag("ench"));
		} else {
			if (!aStack.isItemEnchanted())
			    aStack.addEnchantment(Enchantment.silkTouch, 3);
		}
		return false;
	}

	@Override
	public boolean onItemUse(ItemStack aStack, EntityPlayer aPlayer, World aWorld, int aX, int aY, int aZ, int aSide, float var8, float var9, float var10) {
		ElectricItem.use(aStack, 0, aPlayer);
		return false;
	}
	
	@Override
    public void onCreated(ItemStack aStack, World aWorld, EntityPlayer aPlayer) {
        aStack.addEnchantment(Enchantment.silkTouch, 3);
    }

	@Override
    public float getStrVsBlock(ItemStack var1, Block var2) {
        return !ElectricItem.canUse(var1, 500) ? 1.0F : (ForgeHooks.isToolEffective(var1, var2, 0) ? efficiencyOnProperMaterial : (canHarvestBlock(var2) ? efficiencyOnProperMaterial : 1.0F));
    }

	@Override
    public float getStrVsBlock(ItemStack var1, Block var2, int var3) {
        return !ElectricItem.canUse(var1, 500) ? 1.0F : (ForgeHooks.isToolEffective(var1, var2, var3) ? efficiencyOnProperMaterial : (canHarvestBlock(var2) ? efficiencyOnProperMaterial : 1.0F));
    }

	@Override
    public boolean canHarvestBlock(Block var1) {
        return var1.blockMaterial != Material.rock && var1.blockMaterial != Material.iron ? mineableBlocks.contains(var1) : true;
    }

	@Override
    public boolean hitEntity(ItemStack var1, EntityLiving var2, EntityLiving var3) {
        return true;
    }

	@Override
    public int getItemEnchantability() {
        return 0;
    }

	@Override
    public int getDamageVsEntity(Entity var1) {
        return 1;
    }

	@Override
    public boolean onBlockDestroyed(ItemStack var1, World var2, int var3, int var4, int var5, int var6, EntityLiving var7) {
        ElectricItem.use(var1, 0, (EntityPlayer)var7);
		if ((double)Block.blocksList[var3].getBlockHardness(var2, var4, var5, var6) != 0.0D && ElectricItem.canUse(var1, 500)) {
            if (var7 instanceof EntityPlayer) {
                ElectricItem.use(var1, 500, (EntityPlayer)var7);
            } else {
                ElectricItem.discharge(var1, 500, mTier, true, false);
            }
        }
        return true;
    }

	@Override
    public boolean hasEffect(ItemStack par1ItemStack) {
        return false;
    }

	@Override
    @SideOnly(Side.CLIENT)
    public void getSubItems(int var1, CreativeTabs var2, List var3) {
        ItemStack tCharged = new ItemStack(this, 1), tUncharged = new ItemStack(this, 1, getMaxDamage());
        ElectricItem.charge(tCharged, Integer.MAX_VALUE, Integer.MAX_VALUE, true, false);
        tCharged.addEnchantment(Enchantment.silkTouch, 3);
        var3.add(tCharged);
        var3.add(tUncharged);
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
