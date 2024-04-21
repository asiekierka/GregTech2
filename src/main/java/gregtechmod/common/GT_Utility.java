package gregtechmod.common;

import gregtechmod.GT_Mod;
import gregtechmod.common.items.GT_EnergyArmor_Item;
import gregtechmod.common.items.GT_MetaItem_SmallDust;
import ic2.api.ElectricItem;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import net.minecraftforge.common.ForgeDirection;
import net.minecraftforge.common.ISidedInventory;
import net.minecraftforge.liquids.LiquidContainerData;
import net.minecraftforge.liquids.LiquidContainerRegistry;

public class GT_Utility {
	
	public static void applyUsagesForMaterials(ItemStack aMat, ItemStack aOutput) {
		if (aMat == null || aOutput == null) return;
		aMat = aMat.copy();
		aOutput = aOutput.copy();
		ItemStack tMt2 = new ItemStack(Item.stick, 1), tStack;
		ArrayList<ItemStack> tList;
		if (aOutput.stackSize < 1) aOutput.stackSize = 1;
		boolean bSawdust = aOutput.isItemEqual(GT_MetaItem_SmallDust.instance.getStack(15, 1));
		
		if (!aMat.isItemEqual(new ItemStack(Item.ingotIron, 1))) {
			if ((tStack = GT_ModHandler.getRecipeOutput(new ItemStack[] {aMat, null, aMat, null, aMat, null, null, null, null})) != null)
				if (tStack.isItemEqual(new ItemStack(Item.bucketEmpty, 1)))
					GT_ModHandler.removeRecipe(new ItemStack[] {aMat, null, aMat, null, aMat, null, null, null, null});
			if ((tStack = GT_ModHandler.getRecipeOutput(new ItemStack[] {null, null, null, aMat, null, aMat, null, aMat, null})) != null)
				if (tStack.isItemEqual(new ItemStack(Item.bucketEmpty, 1)))
					GT_ModHandler.removeRecipe(new ItemStack[] {null, null, null, aMat, null, aMat, null, aMat, null});
			if ((tStack = GT_ModHandler.getRecipeOutput(new ItemStack[] {aMat, null, aMat, aMat, aMat, aMat, null, null, null})) != null)
				if (tStack.isItemEqual(new ItemStack(Item.minecartEmpty, 1)))
					GT_ModHandler.removeRecipe(new ItemStack[] {aMat, null, aMat, aMat, aMat, aMat, null, null, null});
			if ((tStack = GT_ModHandler.getRecipeOutput(new ItemStack[] {null, null, null, aMat, null, aMat, aMat, aMat, aMat})) != null)
				if (tStack.isItemEqual(new ItemStack(Item.minecartEmpty, 1)))
					GT_ModHandler.removeRecipe(new ItemStack[] {null, null, null, aMat, null, aMat, aMat, aMat, aMat});
		}
		
		int aOutItem = aOutput.itemID, aOutDamage = aOutput.getItemDamage(), aOutAmount = aOutput.stackSize;
    	
		if (GT_Mod.instance.mToolArmorMaceration) {
			if (null != (tList = GT_ModHandler.getRecipeOutputs(new ItemStack[] {aMat, aMat, aMat, aMat, null, aMat, null, null, null}))) for (int i = 0; i < tList.size(); i++) GT_ModHandler.addPulverisationRecipe(tList.get(i), new ItemStack(aOutItem, 5*aOutAmount+(bSawdust?0:0), aOutDamage));
	    	if (null != (tList = GT_ModHandler.getRecipeOutputs(new ItemStack[] {aMat, null, aMat, aMat, aMat, aMat, aMat, aMat, aMat}))) for (int i = 0; i < tList.size(); i++) GT_ModHandler.addPulverisationRecipe(tList.get(i), new ItemStack(aOutItem, 8*aOutAmount+(bSawdust?0:0), aOutDamage));
	    	if (null != (tList = GT_ModHandler.getRecipeOutputs(new ItemStack[] {aMat, aMat, aMat, aMat, null, aMat, aMat, null, aMat}))) for (int i = 0; i < tList.size(); i++) GT_ModHandler.addPulverisationRecipe(tList.get(i), new ItemStack(aOutItem, 7*aOutAmount+(bSawdust?0:0), aOutDamage));
	    	if (null != (tList = GT_ModHandler.getRecipeOutputs(new ItemStack[] {null, null, null, aMat, null, aMat, aMat, null, aMat}))) for (int i = 0; i < tList.size(); i++) GT_ModHandler.addPulverisationRecipe(tList.get(i), new ItemStack(aOutItem, 4*aOutAmount+(bSawdust?0:0), aOutDamage));
	    	if (null != (tList = GT_ModHandler.getRecipeOutputs(new ItemStack[] {null, aMat, null, null, aMat, null, null, tMt2, null}))) for (int i = 0; i < tList.size(); i++) GT_ModHandler.addPulverisationRecipe(tList.get(i), new ItemStack(aOutItem, 2*aOutAmount+(bSawdust?2:0), aOutDamage));
	    	if (null != (tList = GT_ModHandler.getRecipeOutputs(new ItemStack[] {aMat, aMat, aMat, aMat, tMt2, aMat, null, tMt2, null}))) for (int i = 0; i < tList.size(); i++) GT_ModHandler.addPulverisationRecipe(tList.get(i), new ItemStack(aOutItem, 5*aOutAmount+(bSawdust?4:0), aOutDamage));
	    	if (null != (tList = GT_ModHandler.getRecipeOutputs(new ItemStack[] {aMat, aMat, aMat, null, tMt2, null, null, tMt2, null}))) for (int i = 0; i < tList.size(); i++) GT_ModHandler.addPulverisationRecipe(tList.get(i), new ItemStack(aOutItem, 3*aOutAmount+(bSawdust?4:0), aOutDamage));
	    	if (null != (tList = GT_ModHandler.getRecipeOutputs(new ItemStack[] {null, aMat, null, null, tMt2, null, null, tMt2, null}))) for (int i = 0; i < tList.size(); i++) GT_ModHandler.addPulverisationRecipe(tList.get(i), new ItemStack(aOutItem, 1*aOutAmount+(bSawdust?4:0), aOutDamage));
	    	if (null != (tList = GT_ModHandler.getRecipeOutputs(new ItemStack[] {aMat, aMat, null, aMat, tMt2, null, null, tMt2, null}))) for (int i = 0; i < tList.size(); i++) GT_ModHandler.addPulverisationRecipe(tList.get(i), new ItemStack(aOutItem, 3*aOutAmount+(bSawdust?4:0), aOutDamage));
	    	if (null != (tList = GT_ModHandler.getRecipeOutputs(new ItemStack[] {null, aMat, aMat, null, tMt2, aMat, null, tMt2, null}))) for (int i = 0; i < tList.size(); i++) GT_ModHandler.addPulverisationRecipe(tList.get(i), new ItemStack(aOutItem, 3*aOutAmount+(bSawdust?4:0), aOutDamage));
	    	if (null != (tList = GT_ModHandler.getRecipeOutputs(new ItemStack[] {aMat, aMat, null, null, tMt2, null, null, tMt2, null}))) for (int i = 0; i < tList.size(); i++) GT_ModHandler.addPulverisationRecipe(tList.get(i), new ItemStack(aOutItem, 2*aOutAmount+(bSawdust?4:0), aOutDamage));
	    	if (null != (tList = GT_ModHandler.getRecipeOutputs(new ItemStack[] {null, aMat, null, aMat, null, null, null, aMat, tMt2}))) for (int i = 0; i < tList.size(); i++) GT_ModHandler.addPulverisationRecipe(tList.get(i), new ItemStack(aOutItem, 3*aOutAmount+(bSawdust?2:0), aOutDamage));
	    	if (null != (tList = GT_ModHandler.getRecipeOutputs(new ItemStack[] {null, aMat, null, null, null, aMat, tMt2, aMat, null}))) for (int i = 0; i < tList.size(); i++) GT_ModHandler.addPulverisationRecipe(tList.get(i), new ItemStack(aOutItem, 3*aOutAmount+(bSawdust?2:0), aOutDamage));
	    	if (null != (tList = GT_ModHandler.getRecipeOutputs(new ItemStack[] {null, aMat, null, aMat, null, aMat, null, null, tMt2}))) for (int i = 0; i < tList.size(); i++) GT_ModHandler.addPulverisationRecipe(tList.get(i), new ItemStack(aOutItem, 3*aOutAmount+(bSawdust?2:0), aOutDamage));
	    	if (null != (tList = GT_ModHandler.getRecipeOutputs(new ItemStack[] {null, aMat, null, aMat, null, aMat, tMt2, null, null}))) for (int i = 0; i < tList.size(); i++) GT_ModHandler.addPulverisationRecipe(tList.get(i), new ItemStack(aOutItem, 3*aOutAmount+(bSawdust?2:0), aOutDamage));
	    	if (null != (tList = GT_ModHandler.getRecipeOutputs(new ItemStack[] {null, aMat, null, null, tMt2, null, null, aMat, null}))) for (int i = 0; i < tList.size(); i++) GT_ModHandler.addPulverisationRecipe(tList.get(i), new ItemStack(aOutItem, 2*aOutAmount+(bSawdust?2:0), aOutDamage));
	    	if (null != (tList = GT_ModHandler.getRecipeOutputs(new ItemStack[] {null, tMt2, null, null, tMt2, null, aMat, aMat, aMat}))) for (int i = 0; i < tList.size(); i++) GT_ModHandler.addPulverisationRecipe(tList.get(i), new ItemStack(aOutItem, 3*aOutAmount+(bSawdust?4:0), aOutDamage));
	    	if (null != (tList = GT_ModHandler.getRecipeOutputs(new ItemStack[] {null, tMt2, null, null, tMt2, null, null, aMat, null}))) for (int i = 0; i < tList.size(); i++) GT_ModHandler.addPulverisationRecipe(tList.get(i), new ItemStack(aOutItem, 1*aOutAmount+(bSawdust?4:0), aOutDamage));
	    	if (null != (tList = GT_ModHandler.getRecipeOutputs(new ItemStack[] {aMat, tMt2, null, null, tMt2, null, aMat, aMat, null}))) for (int i = 0; i < tList.size(); i++) GT_ModHandler.addPulverisationRecipe(tList.get(i), new ItemStack(aOutItem, 3*aOutAmount+(bSawdust?4:0), aOutDamage));
	    	if (null != (tList = GT_ModHandler.getRecipeOutputs(new ItemStack[] {null, tMt2, aMat, null, tMt2, null, null, aMat, aMat}))) for (int i = 0; i < tList.size(); i++) GT_ModHandler.addPulverisationRecipe(tList.get(i), new ItemStack(aOutItem, 3*aOutAmount+(bSawdust?4:0), aOutDamage));
	    	if (null != (tList = GT_ModHandler.getRecipeOutputs(new ItemStack[] {null, tMt2, null, null, tMt2, null, aMat, aMat, null}))) for (int i = 0; i < tList.size(); i++) GT_ModHandler.addPulverisationRecipe(tList.get(i), new ItemStack(aOutItem, 2*aOutAmount+(bSawdust?4:0), aOutDamage));
	    	if (null != (tList = GT_ModHandler.getRecipeOutputs(new ItemStack[] {aMat, null, null, null, tMt2, null, null, null, tMt2}))) for (int i = 0; i < tList.size(); i++) GT_ModHandler.addPulverisationRecipe(tList.get(i), new ItemStack(aOutItem, 1*aOutAmount+(bSawdust?4:0), aOutDamage));
	    	if (null != (tList = GT_ModHandler.getRecipeOutputs(new ItemStack[] {null, null, aMat, null, tMt2, null, tMt2, null, null}))) for (int i = 0; i < tList.size(); i++) GT_ModHandler.addPulverisationRecipe(tList.get(i), new ItemStack(aOutItem, 1*aOutAmount+(bSawdust?4:0), aOutDamage));
	    	if (null != (tList = GT_ModHandler.getRecipeOutputs(new ItemStack[] {aMat, null, null, null, tMt2, null, null, null, null}))) for (int i = 0; i < tList.size(); i++) GT_ModHandler.addPulverisationRecipe(tList.get(i), new ItemStack(aOutItem, 1*aOutAmount+(bSawdust?2:0), aOutDamage));
	    	if (null != (tList = GT_ModHandler.getRecipeOutputs(new ItemStack[] {null, null, aMat, null, tMt2, null, null, null, null}))) for (int i = 0; i < tList.size(); i++) GT_ModHandler.addPulverisationRecipe(tList.get(i), new ItemStack(aOutItem, 1*aOutAmount+(bSawdust?2:0), aOutDamage));
	    	if (null != (tList = GT_ModHandler.getRecipeOutputs(new ItemStack[] {aMat, tMt2, null, null, null, null, null, null, null}))) for (int i = 0; i < tList.size(); i++) GT_ModHandler.addPulverisationRecipe(tList.get(i), new ItemStack(aOutItem, 1*aOutAmount+(bSawdust?2:0), aOutDamage));
	    	if (null != (tList = GT_ModHandler.getRecipeOutputs(new ItemStack[] {aMat, null, null, tMt2, null, null, null, null, null}))) for (int i = 0; i < tList.size(); i++) GT_ModHandler.addPulverisationRecipe(tList.get(i), new ItemStack(aOutItem, 1*aOutAmount+(bSawdust?2:0), aOutDamage));
		}
	}
	
	public static Field getPublicField(Object aObject, String aField) {
		Field rField = null;
		try {
			rField = aObject.getClass().getDeclaredField(aField);
		} catch (Throwable e) {}
		return rField;
	}
	
    public static boolean getPotion(EntityLiving aPlayer, int aPotionIndex) {
        try  {
        	Field tPotionHashmap = null;
        	
            Field[] var3 = EntityLiving.class.getDeclaredFields();
            int var4 = var3.length;

            for (int var5 = 0; var5 < var4; ++var5) {
                Field var6 = var3[var5];
                if (var6.getType() == HashMap.class) {
                    tPotionHashmap = var6;
                    tPotionHashmap.setAccessible(true);
                    break;
                }
            }

            if (tPotionHashmap != null) return ((HashMap)tPotionHashmap.get(aPlayer)).get(Integer.valueOf(aPotionIndex)) != null;
        } catch (Throwable var7) {
        	
        }
    	return false;
    }
	
    public static void removePotion(EntityLiving aPlayer, int aPotionIndex) {
        try  {
        	Field tPotionHashmap = null;
        	
            Field[] var3 = EntityLiving.class.getDeclaredFields();
            int var4 = var3.length;

            for (int var5 = 0; var5 < var4; ++var5) {
                Field var6 = var3[var5];
                if (var6.getType() == HashMap.class) {
                    tPotionHashmap = var6;
                    tPotionHashmap.setAccessible(true);
                    break;
                }
            }

            if (tPotionHashmap != null) ((HashMap)tPotionHashmap.get(aPlayer)).remove(Integer.valueOf(aPotionIndex));
        } catch (Throwable var7) {
        	
        }
    }
	
	public static boolean getFullInvisibility(EntityPlayer aPlayer) {
		if (GT_Utility.getPotion(aPlayer, Integer.valueOf(Potion.invisibility.id))) {
			for (int i = 0; i < 4; i++) {
				if (aPlayer.inventory.armorInventory[i] != null) {
					if (aPlayer.inventory.armorInventory[i].getItem() instanceof GT_EnergyArmor_Item) {
						if ((((GT_EnergyArmor_Item)aPlayer.inventory.armorInventory[i].getItem()).mSpecials & 512) != 0) {
							if (ElectricItem.canUse(aPlayer.inventory.armorInventory[i], 10000)) {
								return true;
							}
						}
					}
				}
			}
		}
		return false;
	}
	
	public static ItemStack suckOneItemStackAt(World aWorld, int aX, int aY, int aZ) {
		Chunk tChunk = aWorld.getChunkFromBlockCoords(aX, aZ);
		ArrayList<EntityItem> tList = new ArrayList<EntityItem>();
		tChunk.getEntitiesOfTypeWithinAAAB(EntityItem.class, AxisAlignedBB.getBoundingBox(aX, aY, aZ, aX+1, aY+1, aZ+1), tList, null);
		if (tList.size()>0) {
			tChunk.removeEntity(tList.get(0));
			tList.get(0).setDead();
			return tList.get(0).func_92014_d();
		}
		return null;
	}
	
	private static boolean doCheckChest1 = true, doCheckChest2 = true;
	
	public static int moveOneItemStack(IInventory aTileEntity1, IInventory aTileEntity2, ForgeDirection aGrabFrom, ForgeDirection aPutTo, ArrayList<ItemStack> aFilter, int aComplexityCostSidedSided, int aComplexityCostSidedNonSided, int aComplexityCostNonSided, boolean aInvertFilter) {
		try {
		if (aTileEntity1 instanceof ISidedInventory) {
			ISidedInventory tTileEntity1 = (ISidedInventory)aTileEntity1;
			for (int i = 0; i < tTileEntity1.getSizeInventorySide(aGrabFrom); i++) {
				if (listContainsItem(aFilter, tTileEntity1.getStackInSlot(i+tTileEntity1.getStartInventorySide(aGrabFrom)), true, aInvertFilter)) {
					if (aTileEntity2 instanceof ISidedInventory) {
						ISidedInventory tTileEntity2 = (ISidedInventory)aTileEntity2;
						for (int j = 0; j < tTileEntity2.getSizeInventorySide(aPutTo); j++) {
							if (tTileEntity2.getStackInSlot(j+tTileEntity2.getStartInventorySide(aPutTo)) == null) {
								if (tTileEntity1.getStackInSlot(i+tTileEntity1.getStartInventorySide(aGrabFrom)).stackSize <= tTileEntity1.getStackInSlot(i+tTileEntity1.getStartInventorySide(aGrabFrom)).getMaxStackSize()) {
									tTileEntity2.setInventorySlotContents(j+tTileEntity2.getStartInventorySide(aPutTo)
									, tTileEntity1.getStackInSlot(i+tTileEntity1.getStartInventorySide(aGrabFrom)));
									tTileEntity1.setInventorySlotContents(i+tTileEntity1.getStartInventorySide(aGrabFrom), null);
									return tTileEntity2.getStackInSlot(j+tTileEntity2.getStartInventorySide(aPutTo)).stackSize*aComplexityCostSidedSided;
								} else {
									ItemStack tStack = tTileEntity1.getStackInSlot(i+tTileEntity1.getStartInventorySide(aGrabFrom));
									tStack.stackSize -= tStack.getMaxStackSize();
									tStack = tStack.copy();
									tStack.stackSize = tStack.getMaxStackSize();
									tTileEntity2.setInventorySlotContents(j+tTileEntity2.getStartInventorySide(aPutTo), tStack);
									return tTileEntity2.getStackInSlot(j+tTileEntity2.getStartInventorySide(aPutTo)).stackSize*aComplexityCostSidedSided;
								}
							} else {
								if (tTileEntity2.getStackInSlot(j+tTileEntity2.getStartInventorySide(aPutTo)).isItemEqual(tTileEntity1.getStackInSlot(i+tTileEntity1.getStartInventorySide(aGrabFrom)))) {
									int tTransferredStackSize = Math.min(tTileEntity1.getStackInSlot(i+tTileEntity1.getStartInventorySide(aGrabFrom)).stackSize, tTileEntity2.getStackInSlot(j+tTileEntity2.getStartInventorySide(aPutTo)).getMaxStackSize() - tTileEntity2.getStackInSlot(j+tTileEntity2.getStartInventorySide(aPutTo)).stackSize);
									if (tTransferredStackSize > 0 && tTileEntity2.getStackInSlot(j+tTileEntity2.getStartInventorySide(aPutTo)).getTagCompound() == null && tTileEntity1.getStackInSlot(i+tTileEntity1.getStartInventorySide(aGrabFrom)).getTagCompound() == null) {
										tTileEntity1.decrStackSize(i+tTileEntity1.getStartInventorySide(aGrabFrom), tTransferredStackSize);
										tTileEntity2.getStackInSlot(j+tTileEntity2.getStartInventorySide(aPutTo)).stackSize += tTransferredStackSize;
										return tTransferredStackSize*aComplexityCostSidedSided;
									}
								}
							}
						}
					} else {
						for (int j = 0; j < aTileEntity2.getSizeInventory(); j++) {
							if (aTileEntity2.getStackInSlot(j) == null) {
								if (tTileEntity1.getStackInSlot(i+tTileEntity1.getStartInventorySide(aGrabFrom)).stackSize <= tTileEntity1.getStackInSlot(i+tTileEntity1.getStartInventorySide(aGrabFrom)).getMaxStackSize()) {
									aTileEntity2.setInventorySlotContents(j
									, tTileEntity1.getStackInSlot(i+tTileEntity1.getStartInventorySide(aGrabFrom)));
									tTileEntity1.setInventorySlotContents(i+tTileEntity1.getStartInventorySide(aGrabFrom), null);
									return aTileEntity2.getStackInSlot(j).stackSize*aComplexityCostSidedNonSided;
								} else {
									ItemStack tStack = tTileEntity1.getStackInSlot(i+tTileEntity1.getStartInventorySide(aGrabFrom));
									tStack.stackSize -= tStack.getMaxStackSize();
									tStack = tStack.copy();
									tStack.stackSize = tStack.getMaxStackSize();
									aTileEntity2.setInventorySlotContents(j, tStack);
									return aTileEntity2.getStackInSlot(j).stackSize*aComplexityCostSidedNonSided;
								}
							} else {
								if (aTileEntity2.getStackInSlot(j).isItemEqual(tTileEntity1.getStackInSlot(i+tTileEntity1.getStartInventorySide(aGrabFrom)))) {
									int tTransferredStackSize = Math.min(tTileEntity1.getStackInSlot(i+tTileEntity1.getStartInventorySide(aGrabFrom)).stackSize, aTileEntity2.getStackInSlot(j).getMaxStackSize() - aTileEntity2.getStackInSlot(j).stackSize);
									if (tTransferredStackSize > 0 && aTileEntity2.getStackInSlot(j).getTagCompound() == null && tTileEntity1.getStackInSlot(i+tTileEntity1.getStartInventorySide(aGrabFrom)).getTagCompound() == null) {
										tTileEntity1.decrStackSize(i+tTileEntity1.getStartInventorySide(aGrabFrom), tTransferredStackSize);
										aTileEntity2.getStackInSlot(j).stackSize += tTransferredStackSize;
										return tTransferredStackSize*aComplexityCostSidedNonSided;
									}
								}
							}
						}
					}
				}
			}
		} else {
			for (int i = 0; i < aTileEntity1.getSizeInventory(); i++) {
				if (listContainsItem(aFilter, aTileEntity1.getStackInSlot(i), true, aInvertFilter)) {
					if (aTileEntity2 instanceof ISidedInventory) {
						ISidedInventory tTileEntity2 = (ISidedInventory)aTileEntity2;
						for (int j = 0; j < tTileEntity2.getSizeInventorySide(aPutTo); j++) {
							if (tTileEntity2.getStackInSlot(j+tTileEntity2.getStartInventorySide(aPutTo)) == null) {
								if (aTileEntity1.getStackInSlot(i).stackSize <= aTileEntity1.getStackInSlot(i).getMaxStackSize()) {
									tTileEntity2.setInventorySlotContents(j+tTileEntity2.getStartInventorySide(aPutTo)
									, aTileEntity1.getStackInSlot(i));
									aTileEntity1.setInventorySlotContents(i, null);
									return tTileEntity2.getStackInSlot(j+tTileEntity2.getStartInventorySide(aPutTo)).stackSize*aComplexityCostSidedNonSided;
								} else {
									ItemStack tStack = aTileEntity1.getStackInSlot(i);
									tStack.stackSize -= tStack.getMaxStackSize();
									tStack = tStack.copy();
									tStack.stackSize = tStack.getMaxStackSize();
									tTileEntity2.setInventorySlotContents(j+tTileEntity2.getStartInventorySide(aPutTo), tStack);
									return tTileEntity2.getStackInSlot(j+tTileEntity2.getStartInventorySide(aPutTo)).stackSize*aComplexityCostSidedNonSided;
								}
							} else {
								if (tTileEntity2.getStackInSlot(j+tTileEntity2.getStartInventorySide(aPutTo)).isItemEqual(aTileEntity1.getStackInSlot(i))) {
									int tTransferredStackSize = Math.min(aTileEntity1.getStackInSlot(i).stackSize, tTileEntity2.getStackInSlot(j+tTileEntity2.getStartInventorySide(aPutTo)).getMaxStackSize() - tTileEntity2.getStackInSlot(j+tTileEntity2.getStartInventorySide(aPutTo)).stackSize);
									if (tTransferredStackSize > 0 && tTileEntity2.getStackInSlot(j+tTileEntity2.getStartInventorySide(aPutTo)).getTagCompound() == null && aTileEntity1.getStackInSlot(i).getTagCompound() == null) {
										aTileEntity1.decrStackSize(i, tTransferredStackSize);
										tTileEntity2.getStackInSlot(j+tTileEntity2.getStartInventorySide(aPutTo)).stackSize += tTransferredStackSize;
										return tTransferredStackSize*aComplexityCostSidedNonSided;
									}
								}
							}
						}
					} else {
						for (int j = 0; j < aTileEntity2.getSizeInventory(); j++) {
							if (aTileEntity2.getStackInSlot(j) == null) {
								if (aTileEntity1.getStackInSlot(i).stackSize <= aTileEntity1.getStackInSlot(i).getMaxStackSize()) {
									aTileEntity2.setInventorySlotContents(j
									, aTileEntity1.getStackInSlot(i));
									aTileEntity1.setInventorySlotContents(i, null);
									return aTileEntity2.getStackInSlot(j).stackSize * aComplexityCostNonSided;
								} else {
									ItemStack tStack = aTileEntity1.getStackInSlot(i);
									tStack.stackSize -= tStack.getMaxStackSize();
									tStack = tStack.copy();
									tStack.stackSize = tStack.getMaxStackSize();
									aTileEntity2.setInventorySlotContents(j, tStack);
									return aTileEntity2.getStackInSlot(j).stackSize * aComplexityCostNonSided;
								}
							} else {
								if (aTileEntity2.getStackInSlot(j).isItemEqual(aTileEntity1.getStackInSlot(i))) {
									int tTransferredStackSize = Math.min(aTileEntity1.getStackInSlot(i).stackSize, aTileEntity2.getStackInSlot(j).getMaxStackSize() - aTileEntity2.getStackInSlot(j).stackSize);
									if (tTransferredStackSize > 0 && aTileEntity2.getStackInSlot(j).getTagCompound() == null && aTileEntity1.getStackInSlot(i).getTagCompound() == null) {
										aTileEntity1.decrStackSize(i, tTransferredStackSize);
										aTileEntity2.getStackInSlot(j).stackSize += tTransferredStackSize;
										return tTransferredStackSize * aComplexityCostNonSided;
									}
								}
							}
						}
					}
				}
			}
		}
		} catch(NullPointerException e) {
			return 32*aComplexityCostSidedNonSided;
		}
		if (doCheckChest1 && aTileEntity1 instanceof TileEntityChest) {
			TileEntityChest tTileEntity1 = (TileEntityChest)aTileEntity1;
			if (tTileEntity1.adjacentChestChecked) {
				doCheckChest1 = false;
				int tAmount = 0;
				if (tTileEntity1.adjacentChestXNeg != null) {
					tAmount = moveOneItemStack(tTileEntity1.adjacentChestXNeg, aTileEntity2, aGrabFrom, aPutTo, aFilter, aComplexityCostSidedSided, aComplexityCostSidedNonSided, aComplexityCostNonSided, aInvertFilter);
				}
				if (tTileEntity1.adjacentChestZNeg != null) {
					tAmount = moveOneItemStack(tTileEntity1.adjacentChestZNeg, aTileEntity2, aGrabFrom, aPutTo, aFilter, aComplexityCostSidedSided, aComplexityCostSidedNonSided, aComplexityCostNonSided, aInvertFilter);
				}
				if (tTileEntity1.adjacentChestXPos != null) {
					tAmount = moveOneItemStack(tTileEntity1.adjacentChestXPos, aTileEntity2, aGrabFrom, aPutTo, aFilter, aComplexityCostSidedSided, aComplexityCostSidedNonSided, aComplexityCostNonSided, aInvertFilter);
				}
				if (tTileEntity1.adjacentChestZPosition != null) {
					tAmount = moveOneItemStack(tTileEntity1.adjacentChestZPosition, aTileEntity2, aGrabFrom, aPutTo, aFilter, aComplexityCostSidedSided, aComplexityCostSidedNonSided, aComplexityCostNonSided, aInvertFilter);
				}
				doCheckChest1 = true;
				if (tAmount != 0) return tAmount;
			}
		}
		if (doCheckChest2 && aTileEntity2 instanceof TileEntityChest) {
			TileEntityChest tTileEntity2 = (TileEntityChest)aTileEntity2;
			if (tTileEntity2.adjacentChestChecked) {
				doCheckChest2 = false;
				int tAmount = 0;
				if (tTileEntity2.adjacentChestXNeg != null) {
					tAmount = moveOneItemStack(aTileEntity1, tTileEntity2.adjacentChestXNeg, aGrabFrom, aPutTo, aFilter, aComplexityCostSidedSided, aComplexityCostSidedNonSided, aComplexityCostNonSided, aInvertFilter);
				}
				if (tTileEntity2.adjacentChestZNeg != null) {
					tAmount = moveOneItemStack(aTileEntity1, tTileEntity2.adjacentChestZNeg, aGrabFrom, aPutTo, aFilter, aComplexityCostSidedSided, aComplexityCostSidedNonSided, aComplexityCostNonSided, aInvertFilter);
				}
				if (tTileEntity2.adjacentChestXPos != null) {
					tAmount = moveOneItemStack(aTileEntity1, tTileEntity2.adjacentChestXPos, aGrabFrom, aPutTo, aFilter, aComplexityCostSidedSided, aComplexityCostSidedNonSided, aComplexityCostNonSided, aInvertFilter);
				}
				if (tTileEntity2.adjacentChestZPosition != null) {
					tAmount = moveOneItemStack(aTileEntity1, tTileEntity2.adjacentChestZPosition, aGrabFrom, aPutTo, aFilter, aComplexityCostSidedSided, aComplexityCostSidedNonSided, aComplexityCostNonSided, aInvertFilter);
				}
				doCheckChest2 = true;
				if (tAmount != 0) return tAmount;
			}
		}
		return 0;
	}
	
	public static int moveOneItemStackIntoSlot(IInventory aTileEntity1, IInventory aTileEntity2, ForgeDirection aGrabFrom, int aPutTo, ArrayList<ItemStack> aFilter, int aComplexityCostSidedSided, int aComplexityCostSidedNonSided, int aComplexityCostNonSided, boolean aInvertFilter) {
		try {
		if (aTileEntity1 instanceof ISidedInventory) {
			ISidedInventory tTileEntity1 = (ISidedInventory)aTileEntity1;
			for (int i = 0; i < tTileEntity1.getSizeInventorySide(aGrabFrom); i++) {
				if (listContainsItem(aFilter, tTileEntity1.getStackInSlot(i+tTileEntity1.getStartInventorySide(aGrabFrom)), true, aInvertFilter)) {
					if (aTileEntity2 instanceof ISidedInventory) {
						ISidedInventory tTileEntity2 = (ISidedInventory)aTileEntity2;
						for (int d = 0; d < ForgeDirection.values().length; d++) {
							for (int j = 0; j < tTileEntity2.getSizeInventorySide(ForgeDirection.values()[d]); j++) {
								if (aPutTo == j + tTileEntity2.getStartInventorySide(ForgeDirection.values()[d])) {
									if (tTileEntity2.getStackInSlot(j+tTileEntity2.getStartInventorySide(ForgeDirection.values()[d])) == null) {
										if (tTileEntity1.getStackInSlot(i+tTileEntity1.getStartInventorySide(aGrabFrom)).stackSize <= tTileEntity1.getStackInSlot(i+tTileEntity1.getStartInventorySide(aGrabFrom)).getMaxStackSize()) {
											tTileEntity2.setInventorySlotContents(j+tTileEntity2.getStartInventorySide(ForgeDirection.values()[d])
											, tTileEntity1.getStackInSlot(i+tTileEntity1.getStartInventorySide(aGrabFrom)));
											tTileEntity1.setInventorySlotContents(i+tTileEntity1.getStartInventorySide(aGrabFrom), null);
											return tTileEntity2.getStackInSlot(j+tTileEntity2.getStartInventorySide(ForgeDirection.values()[d])).stackSize*aComplexityCostSidedSided;
										} else {
											ItemStack tStack = tTileEntity1.getStackInSlot(i+tTileEntity1.getStartInventorySide(aGrabFrom));
											tStack.stackSize -= tStack.getMaxStackSize();
											tStack = tStack.copy();
											tStack.stackSize = tStack.getMaxStackSize();
											tTileEntity2.setInventorySlotContents(j+tTileEntity2.getStartInventorySide(ForgeDirection.values()[d]), tStack);
											return tTileEntity2.getStackInSlot(j+tTileEntity2.getStartInventorySide(ForgeDirection.values()[d])).stackSize*aComplexityCostSidedSided;
										}
									} else {
										if (tTileEntity2.getStackInSlot(j+tTileEntity2.getStartInventorySide(ForgeDirection.values()[d])).isItemEqual(tTileEntity1.getStackInSlot(i+tTileEntity1.getStartInventorySide(aGrabFrom)))) {
											int tTransferredStackSize = Math.min(tTileEntity1.getStackInSlot(i+tTileEntity1.getStartInventorySide(aGrabFrom)).stackSize, tTileEntity2.getStackInSlot(j+tTileEntity2.getStartInventorySide(ForgeDirection.values()[d])).getMaxStackSize() - tTileEntity2.getStackInSlot(j+tTileEntity2.getStartInventorySide(ForgeDirection.values()[d])).stackSize);
											if (tTransferredStackSize > 0 && tTileEntity2.getStackInSlot(j+tTileEntity2.getStartInventorySide(ForgeDirection.values()[d])).getTagCompound() == null && tTileEntity1.getStackInSlot(i+tTileEntity1.getStartInventorySide(aGrabFrom)).getTagCompound() == null) {
												tTileEntity1.decrStackSize(i+tTileEntity1.getStartInventorySide(aGrabFrom), tTransferredStackSize);
												tTileEntity2.getStackInSlot(j+tTileEntity2.getStartInventorySide(ForgeDirection.values()[d])).stackSize += tTransferredStackSize;
												return tTransferredStackSize*aComplexityCostSidedSided;
											}
										}
									}
								}
							}
						}
					} else {
						if (aPutTo < aTileEntity2.getSizeInventory()) {
							if (aTileEntity2.getStackInSlot(aPutTo) == null) {
								if (tTileEntity1.getStackInSlot(i+tTileEntity1.getStartInventorySide(aGrabFrom)).stackSize <= tTileEntity1.getStackInSlot(i+tTileEntity1.getStartInventorySide(aGrabFrom)).getMaxStackSize()) {
									aTileEntity2.setInventorySlotContents(aPutTo
									, tTileEntity1.getStackInSlot(i+tTileEntity1.getStartInventorySide(aGrabFrom)));
									tTileEntity1.setInventorySlotContents(i+tTileEntity1.getStartInventorySide(aGrabFrom), null);
									return aTileEntity2.getStackInSlot(aPutTo).stackSize*aComplexityCostSidedNonSided;
								} else {
									ItemStack tStack = tTileEntity1.getStackInSlot(i+tTileEntity1.getStartInventorySide(aGrabFrom));
									tStack.stackSize -= tStack.getMaxStackSize();
									tStack = tStack.copy();
									tStack.stackSize = tStack.getMaxStackSize();
									aTileEntity2.setInventorySlotContents(aPutTo, tStack);
									return aTileEntity2.getStackInSlot(aPutTo).stackSize*aComplexityCostSidedNonSided;
								}
							} else {
								if (aTileEntity2.getStackInSlot(aPutTo).isItemEqual(tTileEntity1.getStackInSlot(i+tTileEntity1.getStartInventorySide(aGrabFrom)))) {
									int tTransferredStackSize = Math.min(tTileEntity1.getStackInSlot(i+tTileEntity1.getStartInventorySide(aGrabFrom)).stackSize, aTileEntity2.getStackInSlot(aPutTo).getMaxStackSize() - aTileEntity2.getStackInSlot(aPutTo).stackSize);
									if (tTransferredStackSize > 0 && aTileEntity2.getStackInSlot(aPutTo).getTagCompound() == null && tTileEntity1.getStackInSlot(i+tTileEntity1.getStartInventorySide(aGrabFrom)).getTagCompound() == null) {
										tTileEntity1.decrStackSize(i+tTileEntity1.getStartInventorySide(aGrabFrom), tTransferredStackSize);
										aTileEntity2.getStackInSlot(aPutTo).stackSize += tTransferredStackSize;
										return tTransferredStackSize*aComplexityCostSidedNonSided;
									}
								}
							}
						}
					}
				}
			}
		} else {
			for (int i = 0; i < aTileEntity1.getSizeInventory(); i++) {
				if (listContainsItem(aFilter, aTileEntity1.getStackInSlot(i), true, aInvertFilter)) {
					if (aTileEntity2 instanceof ISidedInventory) {
						ISidedInventory tTileEntity2 = (ISidedInventory)aTileEntity2;
						for (int d = 0; d < ForgeDirection.values().length; d++) {
							for (int j = 0; j < tTileEntity2.getSizeInventorySide(ForgeDirection.values()[d]); j++) {
								if (aPutTo == j + tTileEntity2.getStartInventorySide(ForgeDirection.values()[d])) {
									if (tTileEntity2.getStackInSlot(j+tTileEntity2.getStartInventorySide(ForgeDirection.values()[d])) == null) {
										if (aTileEntity1.getStackInSlot(i).stackSize <= aTileEntity1.getStackInSlot(i).getMaxStackSize()) {
											tTileEntity2.setInventorySlotContents(j+tTileEntity2.getStartInventorySide(ForgeDirection.values()[d])
											, aTileEntity1.getStackInSlot(i));
											aTileEntity1.setInventorySlotContents(i, null);
											return tTileEntity2.getStackInSlot(j+tTileEntity2.getStartInventorySide(ForgeDirection.values()[d])).stackSize*aComplexityCostSidedNonSided;
										} else {
											ItemStack tStack = aTileEntity1.getStackInSlot(i);
											tStack.stackSize -= tStack.getMaxStackSize();
											tStack = tStack.copy();
											tStack.stackSize = tStack.getMaxStackSize();
											tTileEntity2.setInventorySlotContents(j+tTileEntity2.getStartInventorySide(ForgeDirection.values()[d]), tStack);
											return tTileEntity2.getStackInSlot(j+tTileEntity2.getStartInventorySide(ForgeDirection.values()[d])).stackSize*aComplexityCostSidedNonSided;
										}
									} else {
										if (tTileEntity2.getStackInSlot(j+tTileEntity2.getStartInventorySide(ForgeDirection.values()[d])).isItemEqual(aTileEntity1.getStackInSlot(i))) {
											int tTransferredStackSize = Math.min(aTileEntity1.getStackInSlot(i).stackSize, tTileEntity2.getStackInSlot(j+tTileEntity2.getStartInventorySide(ForgeDirection.values()[d])).getMaxStackSize() - tTileEntity2.getStackInSlot(j+tTileEntity2.getStartInventorySide(ForgeDirection.values()[d])).stackSize);
											if (tTransferredStackSize > 0 && tTileEntity2.getStackInSlot(j+tTileEntity2.getStartInventorySide(ForgeDirection.values()[d])).getTagCompound() == null && aTileEntity1.getStackInSlot(i).getTagCompound() == null) {
												aTileEntity1.decrStackSize(i, tTransferredStackSize);
												tTileEntity2.getStackInSlot(j+tTileEntity2.getStartInventorySide(ForgeDirection.values()[d])).stackSize += tTransferredStackSize;
												return tTransferredStackSize*aComplexityCostSidedNonSided;
											}
										}
									}
								}
							}
						}
					} else {
						if (aPutTo < aTileEntity2.getSizeInventory()) {
							if (aTileEntity2.getStackInSlot(aPutTo) == null) {
								if (aTileEntity1.getStackInSlot(i).stackSize <= aTileEntity1.getStackInSlot(i).getMaxStackSize()) {
									aTileEntity2.setInventorySlotContents(aPutTo
									, aTileEntity1.getStackInSlot(i));
									aTileEntity1.setInventorySlotContents(i, null);
									return aTileEntity2.getStackInSlot(aPutTo).stackSize * aComplexityCostNonSided;
								} else {
									ItemStack tStack = aTileEntity1.getStackInSlot(i);
									tStack.stackSize -= tStack.getMaxStackSize();
									tStack = tStack.copy();
									tStack.stackSize = tStack.getMaxStackSize();
									aTileEntity2.setInventorySlotContents(aPutTo, tStack);
									return aTileEntity2.getStackInSlot(aPutTo).stackSize * aComplexityCostNonSided;
								}
							} else {
								if (aTileEntity2.getStackInSlot(aPutTo).isItemEqual(aTileEntity1.getStackInSlot(i))) {
									int tTransferredStackSize = Math.min(aTileEntity1.getStackInSlot(i).stackSize, aTileEntity2.getStackInSlot(aPutTo).getMaxStackSize() - aTileEntity2.getStackInSlot(aPutTo).stackSize);
									if (tTransferredStackSize > 0 && aTileEntity2.getStackInSlot(aPutTo).getTagCompound() == null && aTileEntity1.getStackInSlot(i).getTagCompound() == null) {
										aTileEntity1.decrStackSize(i, tTransferredStackSize);
										aTileEntity2.getStackInSlot(aPutTo).stackSize += tTransferredStackSize;
										return tTransferredStackSize * aComplexityCostNonSided;
									}
								}
							}
						}
					}
				}
			}
		}
		} catch(NullPointerException e) {
			return 32*aComplexityCostSidedNonSided;
		}
		return 0;
	}
	
	public static boolean listContainsItem(ArrayList<ItemStack> aList, ItemStack aStack, boolean aTrueIfListEmpty, boolean aInvertFilter) {
		if (aStack == null || aStack.stackSize < 1) return false;
		if (aList == null) return aTrueIfListEmpty;
		while (aList.contains(null)) aList.remove(null);
		if (aList.size() < 1) return aTrueIfListEmpty;
		Iterator<ItemStack> tIterator = aList.iterator();
		ItemStack tStack = null;
		while (tIterator.hasNext()) if ((tStack = tIterator.next())!= null && (aStack.isItemEqual(tStack) != aInvertFilter)) return true;
		return false;
	}
	
	public static ItemStack getContainerForFilledItem(ItemStack aStack) {
		if (aStack == null || aStack.isItemEqual(GT_ModHandler.getIC2Item("matter", 1))) return null;
		LiquidContainerData tData[] = LiquidContainerRegistry.getRegisteredLiquidContainerData();
		for (int i = 0; i < tData.length; i++)
			if (tData[i].filled != null && tData[i].filled.isItemEqual(aStack))
				return tData[i].container;
		return null;
	}
	
	public static boolean removeSimpleIC2MachineRecipe(ItemStack aInput, ItemStack aOutput, List<Map.Entry<ItemStack, ItemStack>> aRecipeList) {
		if (aInput == null && aOutput == null) return false;
		Iterator<Map.Entry<ItemStack, ItemStack>> tIterator = aRecipeList.iterator();
		while (tIterator.hasNext()) {
			Map.Entry<ItemStack, ItemStack> tEntry = tIterator.next();
			ItemStack tInput = tEntry.getKey(), tOutput = tEntry.getValue();
			if ((aInput == null || tInput.isItemEqual(aInput)) && (aOutput == null || tOutput.isItemEqual(aOutput))) {
				aRecipeList.remove(tEntry);
				removeSimpleIC2MachineRecipe(aInput, aOutput, aRecipeList);
				return true;
			}
		}
		return false;
	}
	
	public static int stackToInt(ItemStack aStack) {
		if (aStack == null) return 0;
		return aStack.itemID | (((short)aStack.getItemDamage()) << 16);
	}
	
	public static ItemStack intToStack(int aStack) {
		if (aStack == 0) return null;
		return new ItemStack(aStack & 65535, 1, aStack >> 16);
	}
}
