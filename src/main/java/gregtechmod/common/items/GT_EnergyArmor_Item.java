package gregtechmod.common.items;

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
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class GT_EnergyArmor_Item extends ItemArmor implements IMetalArmor, ISpecialArmor, IElectricItem, IPlayerTickingItem {
	public int mCharge, mTransfer, mTier, mDamageEnergyCost, mSpecials;
	public boolean mChargeProvider;
	public double mArmorAbsorbtionPercentage;

    public static Map jumpChargeMap = new HashMap();
    
	public GT_EnergyArmor_Item(int aID, int aCharge, int aTransfer, int aTier, int aDamageEnergyCost, int aSpecials, double aArmorAbsorbtionPercentage, boolean aChargeProvider, int aType, int aArmorIndex) {
		super(aID, EnumArmorMaterial.DIAMOND, aArmorIndex, aType);
		setTextureFile("/gregtechmod/textures/items.png");
		setMaxStackSize(1);
		setMaxDamage(100);
		setNoRepair();
		mCharge = Math.max(1, aCharge);
		mTransfer = Math.max(1, aTransfer);
		mTier = Math.max(1, aTier);
		mSpecials = aSpecials;
		mChargeProvider = aChargeProvider;
		mDamageEnergyCost = Math.max(0, aDamageEnergyCost);
		mArmorAbsorbtionPercentage = aArmorAbsorbtionPercentage;
		
		setCreativeTab(GT_Mod.tabGregTech);
		
        MinecraftForge.EVENT_BUS.register(this);
	}
    
	@Override
	public boolean onTick(EntityPlayer aPlayer, ItemStack aStack, int aTimer, boolean aIsArmor) {
		if (mSpecials == 0 || !aIsArmor) return false;
		
		if (!aPlayer.worldObj.isRemote && (mSpecials & 1) != 0) {
	        int var4 = aPlayer.getAir();
            if (ElectricItem.canUse(aStack, 1000) && var4 < 50) {
            	aPlayer.setAir(var4 + 250);
                ElectricItem.use(aStack, 1000, (EntityPlayer)null);
            }
		}
		
		if (!aPlayer.worldObj.isRemote && (mSpecials & 4) != 0) {
            if (ElectricItem.canUse(aStack, 10000) && aPlayer.getFoodStats().needFood()) {
            	aPlayer.getFoodStats().addStats(1, 0.0F);
                ElectricItem.use(aStack, 10000, null);
            }
		}
		
		if ((mSpecials & 8) != 0) {
            if (ElectricItem.canUse(aStack, 10000) && aPlayer.isPotionActive(Potion.poison)) {
            	GT_Utility.removePotion(aPlayer, Potion.poison.id);
                ElectricItem.use(aStack, 10000, null);
            }
            if (ElectricItem.canUse(aStack, 100000) && aPlayer.isPotionActive(Potion.wither)) {
            	GT_Utility.removePotion(aPlayer, Potion.wither.id);
                ElectricItem.use(aStack, 100000, null);
            }
		}

		if ((mSpecials & 64) != 0) {
            aPlayer.setFire(0);
		}
		
		if (!aPlayer.worldObj.isRemote && (mSpecials & 128) != 0) {
            float var6 = jumpChargeMap.containsKey(aPlayer) ? ((Float)jumpChargeMap.get(aPlayer)).floatValue() : 1.0F;

            if (ElectricItem.canUse(aStack, 1000) && aPlayer.onGround && var6 < 1.0F) {
                var6 = 1.0F;
                ElectricItem.use(aStack, 1000, null);
            }

            if (aPlayer.motionY >= 0.0D && var6 > 0.0F && !aPlayer.isInWater()) {
                if (GT_ModHandler.getJumpKeyDown(aPlayer) && GT_ModHandler.getBoostKeyDown(aPlayer)) {
                    if (var6 == 1.0F) {
                    	aPlayer.motionX *= 3.5D;
                    	aPlayer.motionZ *= 3.5D;
                    }

                    aPlayer.motionY += (double)(var6 * 0.3F);
                    var6 = (float)((double)var6 * 0.75D);
                } else if (var6 < 1.0F) {
                    var6 = 0.0F;
                }
            }

            jumpChargeMap.put(aPlayer, Float.valueOf(var6));
		}

		if ((mSpecials & 256) != 0) {
            if (ElectricItem.canUse(aStack, 100) && aPlayer.isSprinting() && (aPlayer.onGround && Math.abs(aPlayer.motionX) + Math.abs(aPlayer.motionZ) > 0.10000000149011612D || aPlayer.isInWater())) {
                ElectricItem.use(aStack, 100, null);
                float var7 = 0.22F;
                
                if (aPlayer.isInWater()) {
                    ElectricItem.use(aStack, 100, null);
                    var7 = 0.1F;

                    if (aPlayer.isJumping) {
                    	aPlayer.motionY += 0.10000000149011612D;
                    }
                }
                
                if (var7 > 0.0F) {
                	aPlayer.moveFlying(0.0F, 1.0F, var7);
                }
            }
		}

		if ((mSpecials & 512) != 0) {
            if (ElectricItem.canUse(aStack, 10000)) {
            	ElectricItem.use(aStack, 10000, null);
                aPlayer.addPotionEffect(new PotionEffect(Potion.invisibility.id, 25));
            }
		}
		
		if ((mSpecials & 1024) != 0) {
    		ElectricItem.charge(aStack, 1000000000, Integer.MAX_VALUE, true, false);
		}
		
		if (!aPlayer.worldObj.isRemote && (mSpecials & (16|32)) != 0) {
			if (aTimer%20==0) {
				ItemStack tTargetChargeItem = aStack, tTargetDechargeItem = aStack;
				
				if (ElectricItem.charge(tTargetChargeItem, 1, Integer.MAX_VALUE, true, true) < 1) {
					tTargetChargeItem = aPlayer.inventory.armorInventory[2];
				}
				if (ElectricItem.discharge(tTargetDechargeItem, 10, Integer.MAX_VALUE, true, true) < 10) {
					tTargetDechargeItem = aPlayer.inventory.armorInventory[2];
				}
				
				if (tTargetChargeItem == null || !(tTargetChargeItem.getItem() instanceof IElectricItem)) {
					tTargetChargeItem = null;
				}
				if (tTargetDechargeItem == null || !(tTargetDechargeItem.getItem() instanceof IElectricItem) || !(((IElectricItem)tTargetDechargeItem.getItem()).canProvideEnergy() || aStack == tTargetDechargeItem)) {
					tTargetDechargeItem = null;
				}
				
				if (aPlayer.worldObj.isDaytime() && aPlayer.worldObj.canBlockSeeTheSky(MathHelper.floor_double(aPlayer.posX), MathHelper.floor_double(aPlayer.posY+1), MathHelper.floor_double(aPlayer.posZ))) {
					if ((mSpecials & 32) != 0 && tTargetChargeItem != null) {
						ElectricItem.charge(tTargetChargeItem, 20, Integer.MAX_VALUE, true, false);
					}
				} else {
					if ((mSpecials & 16) != 0 && tTargetDechargeItem != null && ElectricItem.canUse(tTargetDechargeItem, 10)) {
						if (aPlayer.worldObj.getBlockId	(MathHelper.floor_double(aPlayer.posX), MathHelper.floor_double(aPlayer.posY+1), MathHelper.floor_double(aPlayer.posZ)) == 0)
							aPlayer.worldObj.setBlock	(MathHelper.floor_double(aPlayer.posX), MathHelper.floor_double(aPlayer.posY+1), MathHelper.floor_double(aPlayer.posZ), GT_Mod.instance.mBlocks[3].blockID);
						ElectricItem.use(tTargetDechargeItem, 10, aPlayer);
					}
				}
			}
		}
		return true;
	}
	
    public boolean getShareTag() {
        return true;
    }
    
    @SideOnly(Side.CLIENT)
    public void getSubItems(int aStack, CreativeTabs var2, List var3) {
        ItemStack tCharged = new ItemStack(this, 1), tUncharged = new ItemStack(this, 1, getMaxDamage());
        ElectricItem.charge(tCharged, Integer.MAX_VALUE, Integer.MAX_VALUE, true, false);
        var3.add(tCharged);
        var3.add(tUncharged);
    }
    
	@Override
	public boolean canProvideEnergy() {
		return mChargeProvider;
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
	
    public int getItemEnchantability() {
        return 0;
    }
    
    @ForgeSubscribe
    public void onEntityLivingFallEvent(LivingFallEvent var1) {
        if (!var1.entity.worldObj.isRemote && var1.entity instanceof EntityPlayer) {
            EntityPlayer var2 = (EntityPlayer)var1.entity;
            for (int i = 0; i < 4; i++) {
	            ItemStack var3 = var2.inventory.armorInventory[i];
	            if (var3 != null && var3.getItem() == this && (mSpecials & 2) != 0) {
	                int var4 = (int)var1.distance - 3;
	                int var5 = (this.mDamageEnergyCost * var4) / 4;
	                if (var5 <= ElectricItem.discharge(var3, Integer.MAX_VALUE, Integer.MAX_VALUE, true, true)) {
	                    ElectricItem.discharge(var3, var5, Integer.MAX_VALUE, true, false);
	                    var1.setCanceled(true);
	                    break;
	                }
	            }
            }
        }
    }
    
    public ISpecialArmor.ArmorProperties getProperties(EntityLiving var1, ItemStack var2, DamageSource var3, double var4, int var6) {
    	return new ISpecialArmor.ArmorProperties((var3 == DamageSource.fall && (mSpecials & 2) != 0)?10:0, getBaseAbsorptionRatio() * mArmorAbsorbtionPercentage, mDamageEnergyCost > 0 ? 25 * ElectricItem.discharge(var2, Integer.MAX_VALUE, Integer.MAX_VALUE, true, true) / mDamageEnergyCost : 0);
    }

    public int getArmorDisplay(EntityPlayer var1, ItemStack var2, int var3) {
        return (int)Math.round(20.0D * getBaseAbsorptionRatio() * mArmorAbsorbtionPercentage);
    }

    public void damageArmor(EntityLiving var1, ItemStack var2, DamageSource var3, int var4, int var5) {
        ElectricItem.discharge(var2, var4 * mDamageEnergyCost, Integer.MAX_VALUE, true, false);
    }

    public boolean isMetalArmor(ItemStack var1, EntityPlayer var2) {
        return true;
    }
    
    private double getBaseAbsorptionRatio() {
    	if (mArmorAbsorbtionPercentage <= 0) return 0.00;
        switch (this.armorType) {
            case  0: return 0.15;
            case  1: return 0.40;
            case  2: return 0.30;
            case  3: return 0.15;
            default: return 0.00;
        }
    }
}
