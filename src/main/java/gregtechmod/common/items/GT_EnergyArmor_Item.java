/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  ic2.api.ElectricItem
 *  ic2.api.IElectricItem
 *  ic2.api.IMetalArmor
 *  net.minecraft.creativetab.CreativeTabs
 *  net.minecraft.entity.EntityLiving
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.EnumArmorMaterial
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemArmor
 *  net.minecraft.item.ItemStack
 *  net.minecraft.potion.Potion
 *  net.minecraft.potion.PotionEffect
 *  net.minecraft.util.DamageSource
 *  net.minecraft.util.MathHelper
 *  net.minecraftforge.common.ISpecialArmor
 *  net.minecraftforge.common.ISpecialArmor$ArmorProperties
 *  net.minecraftforge.common.MinecraftForge
 *  net.minecraftforge.event.ForgeSubscribe
 *  net.minecraftforge.event.entity.living.LivingFallEvent
 */
package gregtechmod.common.items;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import gregtechmod.GT_Mod;
import gregtechmod.api.IPlayerTickingItem;
import gregtechmod.common.GT_ModHandler;
import gregtechmod.common.GT_Utility;
import ic2.api.ElectricItem;
import ic2.api.IElectricItem;
import ic2.api.IMetalArmor;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumArmorMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraftforge.common.ISpecialArmor;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.living.LivingFallEvent;

public class GT_EnergyArmor_Item
extends ItemArmor
implements IMetalArmor,
ISpecialArmor,
IElectricItem,
IPlayerTickingItem {
    public int mCharge;
    public int mTransfer;
    public int mTier;
    public int mDamageEnergyCost;
    public int mSpecials;
    public boolean mChargeProvider;
    public double mArmorAbsorbtionPercentage;
    public static Map jumpChargeMap = new HashMap();

    public GT_EnergyArmor_Item(int aID, int aCharge, int aTransfer, int aTier, int aDamageEnergyCost, int aSpecials, double aArmorAbsorbtionPercentage, boolean aChargeProvider, int aType, int aArmorIndex) {
        super(aID, EnumArmorMaterial.DIAMOND, aArmorIndex, aType);
        this.setTextureFile("/gregtechmod/textures/items.png");
        this.setMaxStackSize(1);
        this.setMaxDamage(100);
        this.setNoRepair();
        this.mCharge = Math.max(1, aCharge);
        this.mTransfer = Math.max(1, aTransfer);
        this.mTier = Math.max(1, aTier);
        this.mSpecials = aSpecials;
        this.mChargeProvider = aChargeProvider;
        this.mDamageEnergyCost = Math.max(0, aDamageEnergyCost);
        this.mArmorAbsorbtionPercentage = aArmorAbsorbtionPercentage;
        this.setCreativeTab(GT_Mod.tabGregTech);
        MinecraftForge.EVENT_BUS.register((Object)this);
    }

    public void addInformation(ItemStack aStack, EntityPlayer aPlayer, List aList, boolean aF3_H) {
        aList.add("Tier: " + this.mTier);
        if ((this.mSpecials & 1) != 0) {
            aList.add("Rebreather");
        }
        if ((this.mSpecials & 2) != 0) {
            aList.add("Inertia Damper");
        }
        if ((this.mSpecials & 4) != 0) {
            aList.add("Food Replicator");
        }
        if ((this.mSpecials & 8) != 0) {
            aList.add("Medicine Module");
        }
        if ((this.mSpecials & 0x10) != 0) {
            aList.add("Lamp");
        }
        if ((this.mSpecials & 0x20) != 0) {
            aList.add("Solarpanel");
        }
        if ((this.mSpecials & 0x40) != 0) {
            aList.add("Extinguisher Module");
        }
        if ((this.mSpecials & 0x80) != 0) {
            aList.add("Jump Booster");
        }
        if ((this.mSpecials & 0x100) != 0) {
            aList.add("Speed Booster");
        }
        if ((this.mSpecials & 0x200) != 0) {
            aList.add("Invisibility Field");
        }
        if ((this.mSpecials & 0x400) != 0) {
            aList.add("Infinite Charge");
        }
    }

    @Override
    public boolean onTick(EntityPlayer aPlayer, ItemStack aStack, int aTimer, boolean aIsArmor) {
        if (this.mSpecials == 0 || !aIsArmor) {
            return false;
        }
        if (!aPlayer.worldObj.isRemote && (this.mSpecials & 1) != 0) {
            int var4 = aPlayer.getAir();
            if (ElectricItem.canUse((ItemStack)aStack, (int)1000) && var4 < 50) {
                aPlayer.setAir(var4 + 250);
                ElectricItem.use((ItemStack)aStack, (int)1000, (EntityPlayer)null);
            }
        }
        if (!aPlayer.worldObj.isRemote && (this.mSpecials & 4) != 0 && ElectricItem.canUse((ItemStack)aStack, (int)50000) && aPlayer.getFoodStats().needFood()) {
            aPlayer.getFoodStats().addStats(1, 0.0f);
            ElectricItem.use((ItemStack)aStack, (int)50000, null);
        }
        if ((this.mSpecials & 8) != 0) {
            if (ElectricItem.canUse((ItemStack)aStack, (int)10000) && aPlayer.isPotionActive(Potion.poison)) {
                GT_Utility.removePotion((EntityLiving)aPlayer, Potion.poison.id);
                ElectricItem.use((ItemStack)aStack, (int)10000, null);
            }
            if (ElectricItem.canUse((ItemStack)aStack, (int)100000) && aPlayer.isPotionActive(Potion.wither)) {
                GT_Utility.removePotion((EntityLiving)aPlayer, Potion.wither.id);
                ElectricItem.use((ItemStack)aStack, (int)100000, null);
            }
        }
        if ((this.mSpecials & 0x40) != 0) {
            aPlayer.setFire(0);
        }
        if (!aPlayer.worldObj.isRemote && (this.mSpecials & 0x80) != 0) {
            float var6;
            float f = var6 = jumpChargeMap.containsKey(aPlayer) ? ((Float)jumpChargeMap.get(aPlayer)).floatValue() : 1.0f;
            if (ElectricItem.canUse((ItemStack)aStack, (int)1000) && aPlayer.onGround && var6 < 1.0f) {
                var6 = 1.0f;
                ElectricItem.use((ItemStack)aStack, (int)1000, null);
            }
            if (aPlayer.motionY >= 0.0 && var6 > 0.0f && !aPlayer.isInWater()) {
                if (GT_ModHandler.getJumpKeyDown(aPlayer) && GT_ModHandler.getBoostKeyDown(aPlayer)) {
                    if (var6 == 1.0f) {
                        aPlayer.motionX *= 3.5;
                        aPlayer.motionZ *= 3.5;
                    }
                    aPlayer.motionY += (double)(var6 * 0.3f);
                    var6 = (float)((double)var6 * 0.75);
                } else if (var6 < 1.0f) {
                    var6 = 0.0f;
                }
            }
            jumpChargeMap.put(aPlayer, Float.valueOf(var6));
        }
        if ((this.mSpecials & 0x100) != 0 && ElectricItem.canUse((ItemStack)aStack, (int)100) && aPlayer.isSprinting() && (aPlayer.onGround && Math.abs(aPlayer.motionX) + Math.abs(aPlayer.motionZ) > (double)0.1f || aPlayer.isInWater())) {
            ElectricItem.use((ItemStack)aStack, (int)100, null);
            float var7 = 0.22f;
            if (aPlayer.isInWater()) {
                ElectricItem.use((ItemStack)aStack, (int)100, null);
                var7 = 0.1f;
                if (aPlayer.isJumping) {
                    aPlayer.motionY += (double)0.1f;
                }
            }
            if (var7 > 0.0f) {
                aPlayer.moveFlying(0.0f, 1.0f, var7);
            }
        }
        if ((this.mSpecials & 0x200) != 0 && ElectricItem.canUse((ItemStack)aStack, (int)10000)) {
            ElectricItem.use((ItemStack)aStack, (int)10000, null);
            aPlayer.addPotionEffect(new PotionEffect(Potion.invisibility.id, 25));
        }
        if ((this.mSpecials & 0x400) != 0) {
            ElectricItem.charge((ItemStack)aStack, (int)1000000000, (int)Integer.MAX_VALUE, (boolean)true, (boolean)false);
        }
        if (!aPlayer.worldObj.isRemote && (this.mSpecials & 0x30) != 0 && aTimer % 20 == 0) {
            ItemStack tTargetChargeItem = aStack;
            ItemStack tTargetDechargeItem = aStack;
            if (ElectricItem.charge((ItemStack)tTargetChargeItem, (int)1, (int)Integer.MAX_VALUE, (boolean)true, (boolean)true) < 1) {
                tTargetChargeItem = aPlayer.inventory.armorInventory[2];
            }
            if (ElectricItem.discharge((ItemStack)tTargetDechargeItem, (int)10, (int)Integer.MAX_VALUE, (boolean)true, (boolean)true) < 10) {
                tTargetDechargeItem = aPlayer.inventory.armorInventory[2];
            }
            if (tTargetChargeItem == null || !(tTargetChargeItem.getItem() instanceof IElectricItem)) {
                tTargetChargeItem = null;
            }
            if (tTargetDechargeItem == null || !(tTargetDechargeItem.getItem() instanceof IElectricItem) || !((IElectricItem)tTargetDechargeItem.getItem()).canProvideEnergy() && aStack != tTargetDechargeItem) {
                tTargetDechargeItem = null;
            }
            if (aPlayer.worldObj.isDaytime() && aPlayer.worldObj.canBlockSeeTheSky(MathHelper.floor_double((double)aPlayer.posX), MathHelper.floor_double((double)(aPlayer.posY + 1.0)), MathHelper.floor_double((double)aPlayer.posZ))) {
                if ((this.mSpecials & 0x20) != 0 && tTargetChargeItem != null) {
                    ElectricItem.charge((ItemStack)tTargetChargeItem, (int)20, (int)Integer.MAX_VALUE, (boolean)true, (boolean)false);
                }
            } else if ((this.mSpecials & 0x10) != 0 && tTargetDechargeItem != null && ElectricItem.canUse((ItemStack)tTargetDechargeItem, (int)10)) {
                if (aPlayer.worldObj.getBlockId(MathHelper.floor_double((double)aPlayer.posX), MathHelper.floor_double((double)(aPlayer.posY + 1.0)), MathHelper.floor_double((double)aPlayer.posZ)) == 0) {
                    aPlayer.worldObj.setBlock(MathHelper.floor_double((double)aPlayer.posX), MathHelper.floor_double((double)(aPlayer.posY + 1.0)), MathHelper.floor_double((double)aPlayer.posZ), GT_Mod.instance.mBlocks[3].blockID);
                }
                ElectricItem.use((ItemStack)tTargetDechargeItem, (int)10, (EntityPlayer)aPlayer);
            }
        }
        return true;
    }

    public boolean getShareTag() {
        return true;
    }

    @SideOnly(value=Side.CLIENT)
    public void getSubItems(int aStack, CreativeTabs var2, List var3) {
        ItemStack tCharged = new ItemStack((Item)this, 1);
        ItemStack tUncharged = new ItemStack((Item)this, 1, this.getMaxDamage());
        ElectricItem.charge((ItemStack)tCharged, (int)Integer.MAX_VALUE, (int)Integer.MAX_VALUE, (boolean)true, (boolean)false);
        var3.add(tCharged);
        var3.add(tUncharged);
    }

    public boolean canProvideEnergy() {
        return this.mChargeProvider;
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

    public int getItemEnchantability() {
        return 0;
    }

    public boolean getIsRepairable(ItemStack par1ItemStack, ItemStack par2ItemStack) {
        return false;
    }

    @ForgeSubscribe
    public void onEntityLivingFallEvent(LivingFallEvent var1) {
        if (!var1.entity.worldObj.isRemote && var1.entity instanceof EntityPlayer) {
            EntityPlayer var2 = (EntityPlayer)var1.entity;
            for (int i = 0; i < 4; ++i) {
                int var4;
                int var5;
                ItemStack var3 = var2.inventory.armorInventory[i];
                if (var3 == null || var3.getItem() != this || (this.mSpecials & 2) == 0 || (var5 = this.mDamageEnergyCost * (var4 = (int)var1.distance - 3) / 4) > ElectricItem.discharge((ItemStack)var3, (int)Integer.MAX_VALUE, (int)Integer.MAX_VALUE, (boolean)true, (boolean)true)) continue;
                ElectricItem.discharge((ItemStack)var3, (int)var5, (int)Integer.MAX_VALUE, (boolean)true, (boolean)false);
                var1.setCanceled(true);
                break;
            }
        }
    }

    public ISpecialArmor.ArmorProperties getProperties(EntityLiving var1, ItemStack var2, DamageSource var3, double var4, int var6) {
        return new ISpecialArmor.ArmorProperties(var3 == DamageSource.fall && (this.mSpecials & 2) != 0 ? 10 : 0, this.getBaseAbsorptionRatio() * this.mArmorAbsorbtionPercentage, this.mDamageEnergyCost > 0 ? 25 * ElectricItem.discharge((ItemStack)var2, (int)Integer.MAX_VALUE, (int)Integer.MAX_VALUE, (boolean)true, (boolean)true) / this.mDamageEnergyCost : 0);
    }

    public int getArmorDisplay(EntityPlayer var1, ItemStack var2, int var3) {
        return (int)Math.round(20.0 * this.getBaseAbsorptionRatio() * this.mArmorAbsorbtionPercentage);
    }

    public void damageArmor(EntityLiving var1, ItemStack var2, DamageSource var3, int var4, int var5) {
        ElectricItem.discharge((ItemStack)var2, (int)(var4 * this.mDamageEnergyCost), (int)Integer.MAX_VALUE, (boolean)true, (boolean)false);
    }

    public boolean isMetalArmor(ItemStack var1, EntityPlayer var2) {
        return true;
    }

    private double getBaseAbsorptionRatio() {
        if (this.mArmorAbsorbtionPercentage <= 0.0) {
            return 0.0;
        }
        switch (this.armorType) {
            case 0: {
                return 0.15;
            }
            case 1: {
                return 0.4;
            }
            case 2: {
                return 0.3;
            }
            case 3: {
                return 0.15;
            }
        }
        return 0.0;
    }
}

