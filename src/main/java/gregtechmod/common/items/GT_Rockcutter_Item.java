/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  ic2.api.ElectricItem
 *  ic2.api.IElectricItem
 *  net.minecraft.block.Block
 *  net.minecraft.block.material.Material
 *  net.minecraft.creativetab.CreativeTabs
 *  net.minecraft.enchantment.Enchantment
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLiving
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.EnumToolMaterial
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.item.ItemTool
 *  net.minecraft.world.World
 *  net.minecraftforge.common.ForgeHooks
 */
package gregtechmod.common.items;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
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
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeHooks;

public class GT_Rockcutter_Item
extends ItemTool
implements IElectricItem,
IPlayerTickingItem {
    public int mCharge;
    public int mTransfer;
    public int mTier;
    public Set mineableBlocks = new HashSet();

    public GT_Rockcutter_Item(int aID) {
        super(aID, 0, EnumToolMaterial.IRON, new Block[0]);
        this.setTextureFile("/gregtechmod/textures/items.png");
        this.setCreativeTab(GT_Mod.tabGregTech);
        this.setMaxStackSize(1);
        this.setMaxDamage(100);
        this.setNoRepair();
        this.mCharge = 10000;
        this.mTransfer = 100;
        this.mTier = 1;
        this.efficiencyOnProperMaterial = 2.0f;
        this.mineableBlocks.add(Block.cobblestone);
        this.mineableBlocks.add(Block.stoneSingleSlab);
        this.mineableBlocks.add(Block.stoneDoubleSlab);
        this.mineableBlocks.add(Block.stairCompactCobblestone);
        this.mineableBlocks.add(Block.stone);
        this.mineableBlocks.add(Block.sandStone);
        this.mineableBlocks.add(Block.stairsSandStone);
        this.mineableBlocks.add(Block.cobblestoneMossy);
        this.mineableBlocks.add(Block.oreIron);
        this.mineableBlocks.add(Block.blockSteel);
        this.mineableBlocks.add(Block.oreCoal);
        this.mineableBlocks.add(Block.blockGold);
        this.mineableBlocks.add(Block.oreGold);
        this.mineableBlocks.add(Block.oreDiamond);
        this.mineableBlocks.add(Block.blockDiamond);
        this.mineableBlocks.add(Block.ice);
        this.mineableBlocks.add(Block.netherrack);
        this.mineableBlocks.add(Block.oreLapis);
        this.mineableBlocks.add(Block.blockLapis);
        this.mineableBlocks.add(Block.oreRedstone);
        this.mineableBlocks.add(Block.oreRedstoneGlowing);
        this.mineableBlocks.add(Block.brick);
        this.mineableBlocks.add(Block.stairsBrick);
        this.mineableBlocks.add(Block.glowStone);
        this.mineableBlocks.add(Block.grass);
        this.mineableBlocks.add(Block.dirt);
        this.mineableBlocks.add(Block.mycelium);
        this.mineableBlocks.add(Block.sand);
        this.mineableBlocks.add(Block.gravel);
        this.mineableBlocks.add(Block.snow);
        this.mineableBlocks.add(Block.blockSnow);
        this.mineableBlocks.add(Block.blockClay);
        this.mineableBlocks.add(Block.tilledField);
        this.mineableBlocks.add(Block.stoneBrick);
        this.mineableBlocks.add(Block.stairsStoneBrickSmooth);
        this.mineableBlocks.add(Block.netherBrick);
        this.mineableBlocks.add(Block.stairsNetherBrick);
        this.mineableBlocks.add(Block.obsidian);
    }

    public void addInformation(ItemStack aStack, EntityPlayer aPlayer, List aList, boolean aF3_H) {
        aList.add("Tier: " + this.mTier);
    }

    @Override
    public boolean onTick(EntityPlayer aPlayer, ItemStack aStack, int aTimer, boolean aIsArmor) {
        if (!ElectricItem.canUse((ItemStack)aStack, (int)500)) {
            if (aStack.isItemEnchanted()) {
                aStack.stackTagCompound.getTags().remove(aStack.stackTagCompound.getTag("ench"));
            }
        } else if (!aStack.isItemEnchanted()) {
            aStack.addEnchantment(Enchantment.silkTouch, 3);
        }
        return false;
    }

    public boolean onItemUse(ItemStack aStack, EntityPlayer aPlayer, World aWorld, int aX, int aY, int aZ, int aSide, float var8, float var9, float var10) {
        ElectricItem.use((ItemStack)aStack, (int)0, (EntityPlayer)aPlayer);
        return false;
    }

    public void onCreated(ItemStack aStack, World aWorld, EntityPlayer aPlayer) {
        aStack.addEnchantment(Enchantment.silkTouch, 3);
    }

    public float getStrVsBlock(ItemStack var1, Block var2) {
        return !ElectricItem.canUse((ItemStack)var1, (int)500) ? 1.0f : (ForgeHooks.isToolEffective((ItemStack)var1, (Block)var2, (int)0) ? this.efficiencyOnProperMaterial : (this.canHarvestBlock(var2) ? this.efficiencyOnProperMaterial : 1.0f));
    }

    public float getStrVsBlock(ItemStack var1, Block var2, int var3) {
        return !ElectricItem.canUse((ItemStack)var1, (int)500) ? 1.0f : (ForgeHooks.isToolEffective((ItemStack)var1, (Block)var2, (int)var3) ? this.efficiencyOnProperMaterial : (this.canHarvestBlock(var2) ? this.efficiencyOnProperMaterial : 1.0f));
    }

    public boolean canHarvestBlock(Block var1) {
        return var1.blockMaterial != Material.rock && var1.blockMaterial != Material.iron ? this.mineableBlocks.contains(var1) : true;
    }

    public boolean hitEntity(ItemStack var1, EntityLiving var2, EntityLiving var3) {
        return true;
    }

    public int getItemEnchantability() {
        return 0;
    }

    public boolean getIsRepairable(ItemStack par1ItemStack, ItemStack par2ItemStack) {
        return false;
    }

    public int getDamageVsEntity(Entity var1) {
        return 1;
    }

    public boolean onBlockDestroyed(ItemStack var1, World var2, int var3, int var4, int var5, int var6, EntityLiving var7) {
        ElectricItem.use((ItemStack)var1, (int)0, (EntityPlayer)((EntityPlayer)var7));
        if ((double)Block.blocksList[var3].getBlockHardness(var2, var4, var5, var6) != 0.0 && ElectricItem.canUse((ItemStack)var1, (int)500)) {
            if (var7 instanceof EntityPlayer) {
                ElectricItem.use((ItemStack)var1, (int)500, (EntityPlayer)((EntityPlayer)var7));
            } else {
                ElectricItem.discharge((ItemStack)var1, (int)500, (int)this.mTier, (boolean)true, (boolean)false);
            }
        }
        return true;
    }

    public boolean hasEffect(ItemStack par1ItemStack) {
        return false;
    }

    @SideOnly(value=Side.CLIENT)
    public void getSubItems(int var1, CreativeTabs var2, List var3) {
        ItemStack tCharged = new ItemStack((Item)this, 1);
        ItemStack tUncharged = new ItemStack((Item)this, 1, this.getMaxDamage());
        ElectricItem.charge((ItemStack)tCharged, (int)Integer.MAX_VALUE, (int)Integer.MAX_VALUE, (boolean)true, (boolean)false);
        tCharged.addEnchantment(Enchantment.silkTouch, 3);
        var3.add(tCharged);
        var3.add(tUncharged);
    }

    public boolean getShareTag() {
        return true;
    }

    public boolean canProvideEnergy() {
        return false;
    }

    public int getChargedItemId() {
        return this.itemID;
    }

    public int getEmptyItemId() {
        return this.itemID;
    }

    public int getMaxCharge() {
        return this.mCharge;
    }

    public int getTier() {
        return this.mTier;
    }

    public int getTransferLimit() {
        return this.mTransfer;
    }
}

