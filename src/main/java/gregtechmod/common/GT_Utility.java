/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  ic2.api.ElectricItem
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLiving
 *  net.minecraft.entity.item.EntityItem
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.inventory.IInventory
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.potion.Potion
 *  net.minecraft.tileentity.TileEntityChest
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.world.World
 *  net.minecraft.world.chunk.Chunk
 *  net.minecraftforge.common.ForgeDirection
 *  net.minecraftforge.common.ISidedInventory
 *  net.minecraftforge.liquids.LiquidContainerData
 *  net.minecraftforge.liquids.LiquidContainerRegistry
 */
package gregtechmod.common;

import gregtechmod.common.GT_Log;
import gregtechmod.common.GT_ModHandler;
import gregtechmod.common.GT_OreDictUnificator;
import gregtechmod.common.items.GT_EnergyArmor_Item;
import gregtechmod.common.items.GT_MetaItem_SmallDust;
import ic2.api.ElectricItem;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import net.minecraft.entity.Entity;
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
    private static boolean doCheckChest1 = true;
    private static boolean doCheckChest2 = true;

    public static void applyUsagesForMaterials(ItemStack aMat, ItemStack aOutput, boolean aBackSmelting, boolean aBackMacerating) {
        int i;
        if (aMat == null || aOutput == null) {
            return;
        }
        aMat = aMat.copy();
        aOutput = aOutput.copy();
        ItemStack tMt2 = new ItemStack(Item.stick, 1);
        if (aOutput.stackSize < 1) {
            aOutput.stackSize = 1;
        }
        boolean bSawdust = aOutput.isItemEqual(GT_MetaItem_SmallDust.instance.getStack(15, 1));
        if (!aMat.isItemEqual(new ItemStack(Item.ingotIron, 1))) {
            ItemStack tStack = GT_ModHandler.getRecipeOutput(new ItemStack[]{aMat, null, aMat, null, aMat, null, null, null, null});
            if (tStack != null && tStack.isItemEqual(new ItemStack(Item.bucketEmpty, 1))) {
                GT_ModHandler.removeRecipe(new ItemStack[]{aMat, null, aMat, null, aMat, null, null, null, null});
            }
            if ((tStack = GT_ModHandler.getRecipeOutput(new ItemStack[]{null, null, null, aMat, null, aMat, null, aMat, null})) != null && tStack.isItemEqual(new ItemStack(Item.bucketEmpty, 1))) {
                GT_ModHandler.removeRecipe(new ItemStack[]{null, null, null, aMat, null, aMat, null, aMat, null});
            }
            if ((tStack = GT_ModHandler.getRecipeOutput(new ItemStack[]{aMat, null, aMat, aMat, aMat, aMat, null, null, null})) != null && tStack.isItemEqual(new ItemStack(Item.minecartEmpty, 1))) {
                GT_ModHandler.removeRecipe(new ItemStack[]{aMat, null, aMat, aMat, aMat, aMat, null, null, null});
            }
            if ((tStack = GT_ModHandler.getRecipeOutput(new ItemStack[]{null, null, null, aMat, null, aMat, aMat, aMat, aMat})) != null && tStack.isItemEqual(new ItemStack(Item.minecartEmpty, 1))) {
                GT_ModHandler.removeRecipe(new ItemStack[]{null, null, null, aMat, null, aMat, aMat, aMat, aMat});
            }
        }
        int aOutItem = aOutput.itemID;
        int aOutDamage = aOutput.getItemDamage();
        int aOutAmount = aOutput.stackSize;
        int aInItem = aMat.itemID;
        int aInDamage = aMat.getItemDamage();
        int aInAmount = aMat.stackSize;
        ArrayList tList = GT_ModHandler.getRecipeOutputs(new ItemStack[]{aMat, aMat, aMat, aMat, null, aMat, null, null, null});
        for (i = 0; i < tList.size(); ++i) {
            if (aBackMacerating) {
                GT_ModHandler.addPulverisationRecipe((ItemStack)tList.get(i), new ItemStack(aOutItem, 5 * aOutAmount + (bSawdust ? 0 : 0), aOutDamage), null, 0, false);
            }
            if (!aBackSmelting || ((ItemStack)tList.get((int)i)).stackSize != 1 || aInAmount != 1) continue;
            GT_ModHandler.addSmeltingRecipe((ItemStack)tList.get(i), new ItemStack(aInItem, 5, aInDamage));
        }
        tList = GT_ModHandler.getRecipeOutputs(new ItemStack[]{aMat, null, aMat, aMat, aMat, aMat, aMat, aMat, aMat});
        for (i = 0; i < tList.size(); ++i) {
            if (aBackMacerating) {
                GT_ModHandler.addPulverisationRecipe((ItemStack)tList.get(i), new ItemStack(aOutItem, 8 * aOutAmount + (bSawdust ? 0 : 0), aOutDamage), null, 0, false);
            }
            if (!aBackSmelting || ((ItemStack)tList.get((int)i)).stackSize != 1 || aInAmount != 1) continue;
            GT_ModHandler.addSmeltingRecipe((ItemStack)tList.get(i), new ItemStack(aInItem, 8, aInDamage));
        }
        tList = GT_ModHandler.getRecipeOutputs(new ItemStack[]{aMat, aMat, aMat, aMat, null, aMat, aMat, null, aMat});
        for (i = 0; i < tList.size(); ++i) {
            if (aBackMacerating) {
                GT_ModHandler.addPulverisationRecipe((ItemStack)tList.get(i), new ItemStack(aOutItem, 7 * aOutAmount + (bSawdust ? 0 : 0), aOutDamage), null, 0, false);
            }
            if (!aBackSmelting || ((ItemStack)tList.get((int)i)).stackSize != 1 || aInAmount != 1) continue;
            GT_ModHandler.addSmeltingRecipe((ItemStack)tList.get(i), new ItemStack(aInItem, 7, aInDamage));
        }
        tList = GT_ModHandler.getRecipeOutputs(new ItemStack[]{null, null, null, aMat, null, aMat, aMat, null, aMat});
        for (i = 0; i < tList.size(); ++i) {
            if (aBackMacerating) {
                GT_ModHandler.addPulverisationRecipe((ItemStack)tList.get(i), new ItemStack(aOutItem, 4 * aOutAmount + (bSawdust ? 0 : 0), aOutDamage), null, 0, false);
            }
            if (!aBackSmelting || ((ItemStack)tList.get((int)i)).stackSize != 1 || aInAmount != 1) continue;
            GT_ModHandler.addSmeltingRecipe((ItemStack)tList.get(i), new ItemStack(aInItem, 4, aInDamage));
        }
        tList = GT_ModHandler.getRecipeOutputs(new ItemStack[]{null, aMat, null, null, aMat, null, null, tMt2, null});
        for (i = 0; i < tList.size(); ++i) {
            if (aBackMacerating) {
                GT_ModHandler.addPulverisationRecipe((ItemStack)tList.get(i), new ItemStack(aOutItem, 2 * aOutAmount + (bSawdust ? 2 : 0), aOutDamage), bSawdust ? null : GT_OreDictUnificator.get("dustWood", null, 1), 50, false);
            }
            if (!aBackSmelting || ((ItemStack)tList.get((int)i)).stackSize != 1 || aInAmount != 1) continue;
            GT_ModHandler.addSmeltingRecipe((ItemStack)tList.get(i), new ItemStack(aInItem, 2, aInDamage));
        }
        tList = GT_ModHandler.getRecipeOutputs(new ItemStack[]{aMat, aMat, aMat, aMat, tMt2, aMat, null, tMt2, null});
        for (i = 0; i < tList.size(); ++i) {
            if (aBackMacerating) {
                GT_ModHandler.addPulverisationRecipe((ItemStack)tList.get(i), new ItemStack(aOutItem, 5 * aOutAmount + (bSawdust ? 4 : 0), aOutDamage), bSawdust ? null : GT_OreDictUnificator.get("dustWood", null, 1), 100, false);
            }
            if (!aBackSmelting || ((ItemStack)tList.get((int)i)).stackSize != 1 || aInAmount != 1) continue;
            GT_ModHandler.addSmeltingRecipe((ItemStack)tList.get(i), new ItemStack(aInItem, 5, aInDamage));
        }
        tList = GT_ModHandler.getRecipeOutputs(new ItemStack[]{aMat, aMat, aMat, null, tMt2, null, null, tMt2, null});
        for (i = 0; i < tList.size(); ++i) {
            if (aBackMacerating) {
                GT_ModHandler.addPulverisationRecipe((ItemStack)tList.get(i), new ItemStack(aOutItem, 3 * aOutAmount + (bSawdust ? 4 : 0), aOutDamage), bSawdust ? null : GT_OreDictUnificator.get("dustWood", null, 1), 100, false);
            }
            if (!aBackSmelting || ((ItemStack)tList.get((int)i)).stackSize != 1 || aInAmount != 1) continue;
            GT_ModHandler.addSmeltingRecipe((ItemStack)tList.get(i), new ItemStack(aInItem, 3, aInDamage));
        }
        tList = GT_ModHandler.getRecipeOutputs(new ItemStack[]{null, aMat, null, null, tMt2, null, null, tMt2, null});
        for (i = 0; i < tList.size(); ++i) {
            if (aBackMacerating) {
                GT_ModHandler.addPulverisationRecipe((ItemStack)tList.get(i), new ItemStack(aOutItem, 1 * aOutAmount + (bSawdust ? 4 : 0), aOutDamage), bSawdust ? null : GT_OreDictUnificator.get("dustWood", null, 1), 100, false);
            }
            if (!aBackSmelting || ((ItemStack)tList.get((int)i)).stackSize != 1 || aInAmount != 1) continue;
            GT_ModHandler.addSmeltingRecipe((ItemStack)tList.get(i), new ItemStack(aInItem, 1, aInDamage));
        }
        tList = GT_ModHandler.getRecipeOutputs(new ItemStack[]{aMat, aMat, null, aMat, tMt2, null, null, tMt2, null});
        for (i = 0; i < tList.size(); ++i) {
            if (aBackMacerating) {
                GT_ModHandler.addPulverisationRecipe((ItemStack)tList.get(i), new ItemStack(aOutItem, 3 * aOutAmount + (bSawdust ? 4 : 0), aOutDamage), bSawdust ? null : GT_OreDictUnificator.get("dustWood", null, 1), 100, false);
            }
            if (!aBackSmelting || ((ItemStack)tList.get((int)i)).stackSize != 1 || aInAmount != 1) continue;
            GT_ModHandler.addSmeltingRecipe((ItemStack)tList.get(i), new ItemStack(aInItem, 3, aInDamage));
        }
        tList = GT_ModHandler.getRecipeOutputs(new ItemStack[]{null, aMat, aMat, null, tMt2, aMat, null, tMt2, null});
        for (i = 0; i < tList.size(); ++i) {
            if (aBackMacerating) {
                GT_ModHandler.addPulverisationRecipe((ItemStack)tList.get(i), new ItemStack(aOutItem, 3 * aOutAmount + (bSawdust ? 4 : 0), aOutDamage), bSawdust ? null : GT_OreDictUnificator.get("dustWood", null, 1), 100, false);
            }
            if (!aBackSmelting || ((ItemStack)tList.get((int)i)).stackSize != 1 || aInAmount != 1) continue;
            GT_ModHandler.addSmeltingRecipe((ItemStack)tList.get(i), new ItemStack(aInItem, 3, aInDamage));
        }
        tList = GT_ModHandler.getRecipeOutputs(new ItemStack[]{aMat, aMat, null, null, tMt2, null, null, tMt2, null});
        for (i = 0; i < tList.size(); ++i) {
            if (aBackMacerating) {
                GT_ModHandler.addPulverisationRecipe((ItemStack)tList.get(i), new ItemStack(aOutItem, 2 * aOutAmount + (bSawdust ? 4 : 0), aOutDamage), bSawdust ? null : GT_OreDictUnificator.get("dustWood", null, 1), 100, false);
            }
            if (!aBackSmelting || ((ItemStack)tList.get((int)i)).stackSize != 1 || aInAmount != 1) continue;
            GT_ModHandler.addSmeltingRecipe((ItemStack)tList.get(i), new ItemStack(aInItem, 2, aInDamage));
        }
        tList = GT_ModHandler.getRecipeOutputs(new ItemStack[]{null, aMat, aMat, null, tMt2, null, null, tMt2, null});
        for (i = 0; i < tList.size(); ++i) {
            if (aBackMacerating) {
                GT_ModHandler.addPulverisationRecipe((ItemStack)tList.get(i), new ItemStack(aOutItem, 2 * aOutAmount + (bSawdust ? 4 : 0), aOutDamage), bSawdust ? null : GT_OreDictUnificator.get("dustWood", null, 1), 100, false);
            }
            if (!aBackSmelting || ((ItemStack)tList.get((int)i)).stackSize != 1 || aInAmount != 1) continue;
            GT_ModHandler.addSmeltingRecipe((ItemStack)tList.get(i), new ItemStack(aInItem, 2, aInDamage));
        }
        tList = GT_ModHandler.getRecipeOutputs(new ItemStack[]{null, aMat, null, aMat, null, null, null, aMat, tMt2});
        for (i = 0; i < tList.size(); ++i) {
            if (aBackMacerating) {
                GT_ModHandler.addPulverisationRecipe((ItemStack)tList.get(i), new ItemStack(aOutItem, 3 * aOutAmount + (bSawdust ? 2 : 0), aOutDamage), bSawdust ? null : GT_OreDictUnificator.get("dustWood", null, 1), 50, false);
            }
            if (!aBackSmelting || ((ItemStack)tList.get((int)i)).stackSize != 1 || aInAmount != 1) continue;
            GT_ModHandler.addSmeltingRecipe((ItemStack)tList.get(i), new ItemStack(aInItem, 3, aInDamage));
        }
        tList = GT_ModHandler.getRecipeOutputs(new ItemStack[]{null, aMat, null, null, null, aMat, tMt2, aMat, null});
        for (i = 0; i < tList.size(); ++i) {
            if (aBackMacerating) {
                GT_ModHandler.addPulverisationRecipe((ItemStack)tList.get(i), new ItemStack(aOutItem, 3 * aOutAmount + (bSawdust ? 2 : 0), aOutDamage), bSawdust ? null : GT_OreDictUnificator.get("dustWood", null, 1), 50, false);
            }
            if (!aBackSmelting || ((ItemStack)tList.get((int)i)).stackSize != 1 || aInAmount != 1) continue;
            GT_ModHandler.addSmeltingRecipe((ItemStack)tList.get(i), new ItemStack(aInItem, 3, aInDamage));
        }
        tList = GT_ModHandler.getRecipeOutputs(new ItemStack[]{null, aMat, null, aMat, null, aMat, null, null, tMt2});
        for (i = 0; i < tList.size(); ++i) {
            if (aBackMacerating) {
                GT_ModHandler.addPulverisationRecipe((ItemStack)tList.get(i), new ItemStack(aOutItem, 3 * aOutAmount + (bSawdust ? 2 : 0), aOutDamage), bSawdust ? null : GT_OreDictUnificator.get("dustWood", null, 1), 50, false);
            }
            if (!aBackSmelting || ((ItemStack)tList.get((int)i)).stackSize != 1 || aInAmount != 1) continue;
            GT_ModHandler.addSmeltingRecipe((ItemStack)tList.get(i), new ItemStack(aInItem, 3, aInDamage));
        }
        tList = GT_ModHandler.getRecipeOutputs(new ItemStack[]{null, aMat, null, aMat, null, aMat, tMt2, null, null});
        for (i = 0; i < tList.size(); ++i) {
            if (aBackMacerating) {
                GT_ModHandler.addPulverisationRecipe((ItemStack)tList.get(i), new ItemStack(aOutItem, 3 * aOutAmount + (bSawdust ? 2 : 0), aOutDamage), bSawdust ? null : GT_OreDictUnificator.get("dustWood", null, 1), 50, false);
            }
            if (!aBackSmelting || ((ItemStack)tList.get((int)i)).stackSize != 1 || aInAmount != 1) continue;
            GT_ModHandler.addSmeltingRecipe((ItemStack)tList.get(i), new ItemStack(aInItem, 3, aInDamage));
        }
        tList = GT_ModHandler.getRecipeOutputs(new ItemStack[]{null, aMat, null, null, tMt2, null, null, aMat, null});
        for (i = 0; i < tList.size(); ++i) {
            if (aBackMacerating) {
                GT_ModHandler.addPulverisationRecipe((ItemStack)tList.get(i), new ItemStack(aOutItem, 2 * aOutAmount + (bSawdust ? 2 : 0), aOutDamage), bSawdust ? null : GT_OreDictUnificator.get("dustWood", null, 1), 50, false);
            }
            if (!aBackSmelting || ((ItemStack)tList.get((int)i)).stackSize != 1 || aInAmount != 1) continue;
            GT_ModHandler.addSmeltingRecipe((ItemStack)tList.get(i), new ItemStack(aInItem, 2, aInDamage));
        }
        tList = GT_ModHandler.getRecipeOutputs(new ItemStack[]{null, tMt2, null, null, tMt2, null, aMat, aMat, aMat});
        for (i = 0; i < tList.size(); ++i) {
            if (aBackMacerating) {
                GT_ModHandler.addPulverisationRecipe((ItemStack)tList.get(i), new ItemStack(aOutItem, 3 * aOutAmount + (bSawdust ? 4 : 0), aOutDamage), bSawdust ? null : GT_OreDictUnificator.get("dustWood", null, 1), 100, false);
            }
            if (!aBackSmelting || ((ItemStack)tList.get((int)i)).stackSize != 1 || aInAmount != 1) continue;
            GT_ModHandler.addSmeltingRecipe((ItemStack)tList.get(i), new ItemStack(aInItem, 3, aInDamage));
        }
        tList = GT_ModHandler.getRecipeOutputs(new ItemStack[]{null, tMt2, null, null, tMt2, null, null, aMat, null});
        for (i = 0; i < tList.size(); ++i) {
            if (aBackMacerating) {
                GT_ModHandler.addPulverisationRecipe((ItemStack)tList.get(i), new ItemStack(aOutItem, 1 * aOutAmount + (bSawdust ? 4 : 0), aOutDamage), bSawdust ? null : GT_OreDictUnificator.get("dustWood", null, 1), 100, false);
            }
            if (!aBackSmelting || ((ItemStack)tList.get((int)i)).stackSize != 1 || aInAmount != 1) continue;
            GT_ModHandler.addSmeltingRecipe((ItemStack)tList.get(i), new ItemStack(aInItem, 1, aInDamage));
        }
        tList = GT_ModHandler.getRecipeOutputs(new ItemStack[]{aMat, tMt2, null, null, tMt2, null, aMat, aMat, null});
        for (i = 0; i < tList.size(); ++i) {
            if (aBackMacerating) {
                GT_ModHandler.addPulverisationRecipe((ItemStack)tList.get(i), new ItemStack(aOutItem, 3 * aOutAmount + (bSawdust ? 4 : 0), aOutDamage), bSawdust ? null : GT_OreDictUnificator.get("dustWood", null, 1), 100, false);
            }
            if (!aBackSmelting || ((ItemStack)tList.get((int)i)).stackSize != 1 || aInAmount != 1) continue;
            GT_ModHandler.addSmeltingRecipe((ItemStack)tList.get(i), new ItemStack(aInItem, 3, aInDamage));
        }
        tList = GT_ModHandler.getRecipeOutputs(new ItemStack[]{null, tMt2, aMat, null, tMt2, null, null, aMat, aMat});
        for (i = 0; i < tList.size(); ++i) {
            if (aBackMacerating) {
                GT_ModHandler.addPulverisationRecipe((ItemStack)tList.get(i), new ItemStack(aOutItem, 3 * aOutAmount + (bSawdust ? 4 : 0), aOutDamage), bSawdust ? null : GT_OreDictUnificator.get("dustWood", null, 1), 100, false);
            }
            if (!aBackSmelting || ((ItemStack)tList.get((int)i)).stackSize != 1 || aInAmount != 1) continue;
            GT_ModHandler.addSmeltingRecipe((ItemStack)tList.get(i), new ItemStack(aInItem, 3, aInDamage));
        }
        tList = GT_ModHandler.getRecipeOutputs(new ItemStack[]{null, tMt2, null, null, tMt2, null, aMat, aMat, null});
        for (i = 0; i < tList.size(); ++i) {
            if (aBackMacerating) {
                GT_ModHandler.addPulverisationRecipe((ItemStack)tList.get(i), new ItemStack(aOutItem, 2 * aOutAmount + (bSawdust ? 4 : 0), aOutDamage), bSawdust ? null : GT_OreDictUnificator.get("dustWood", null, 1), 100, false);
            }
            if (!aBackSmelting || ((ItemStack)tList.get((int)i)).stackSize != 1 || aInAmount != 1) continue;
            GT_ModHandler.addSmeltingRecipe((ItemStack)tList.get(i), new ItemStack(aInItem, 2, aInDamage));
        }
        tList = GT_ModHandler.getRecipeOutputs(new ItemStack[]{aMat, null, null, null, tMt2, null, null, null, tMt2});
        for (i = 0; i < tList.size(); ++i) {
            if (aBackMacerating) {
                GT_ModHandler.addPulverisationRecipe((ItemStack)tList.get(i), new ItemStack(aOutItem, 1 * aOutAmount + (bSawdust ? 4 : 0), aOutDamage), bSawdust ? null : GT_OreDictUnificator.get("dustWood", null, 1), 100, false);
            }
            if (!aBackSmelting || ((ItemStack)tList.get((int)i)).stackSize != 1 || aInAmount != 1) continue;
            GT_ModHandler.addSmeltingRecipe((ItemStack)tList.get(i), new ItemStack(aInItem, 1, aInDamage));
        }
        tList = GT_ModHandler.getRecipeOutputs(new ItemStack[]{null, null, aMat, null, tMt2, null, tMt2, null, null});
        for (i = 0; i < tList.size(); ++i) {
            if (aBackMacerating) {
                GT_ModHandler.addPulverisationRecipe((ItemStack)tList.get(i), new ItemStack(aOutItem, 1 * aOutAmount + (bSawdust ? 4 : 0), aOutDamage), bSawdust ? null : GT_OreDictUnificator.get("dustWood", null, 1), 100, false);
            }
            if (!aBackSmelting || ((ItemStack)tList.get((int)i)).stackSize != 1 || aInAmount != 1) continue;
            GT_ModHandler.addSmeltingRecipe((ItemStack)tList.get(i), new ItemStack(aInItem, 1, aInDamage));
        }
        tList = GT_ModHandler.getRecipeOutputs(new ItemStack[]{aMat, null, null, null, tMt2, null, null, null, null});
        for (i = 0; i < tList.size(); ++i) {
            if (aBackMacerating) {
                GT_ModHandler.addPulverisationRecipe((ItemStack)tList.get(i), new ItemStack(aOutItem, 1 * aOutAmount + (bSawdust ? 2 : 0), aOutDamage), bSawdust ? null : GT_OreDictUnificator.get("dustWood", null, 1), 50, false);
            }
            if (!aBackSmelting || ((ItemStack)tList.get((int)i)).stackSize != 1 || aInAmount != 1) continue;
            GT_ModHandler.addSmeltingRecipe((ItemStack)tList.get(i), new ItemStack(aInItem, 1, aInDamage));
        }
        tList = GT_ModHandler.getRecipeOutputs(new ItemStack[]{null, null, aMat, null, tMt2, null, null, null, null});
        for (i = 0; i < tList.size(); ++i) {
            if (aBackMacerating) {
                GT_ModHandler.addPulverisationRecipe((ItemStack)tList.get(i), new ItemStack(aOutItem, 1 * aOutAmount + (bSawdust ? 2 : 0), aOutDamage), bSawdust ? null : GT_OreDictUnificator.get("dustWood", null, 1), 50, false);
            }
            if (!aBackSmelting || ((ItemStack)tList.get((int)i)).stackSize != 1 || aInAmount != 1) continue;
            GT_ModHandler.addSmeltingRecipe((ItemStack)tList.get(i), new ItemStack(aInItem, 1, aInDamage));
        }
        tList = GT_ModHandler.getRecipeOutputs(new ItemStack[]{aMat, tMt2, null, null, null, null, null, null, null});
        for (i = 0; i < tList.size(); ++i) {
            if (aBackMacerating) {
                GT_ModHandler.addPulverisationRecipe((ItemStack)tList.get(i), new ItemStack(aOutItem, 1 * aOutAmount + (bSawdust ? 2 : 0), aOutDamage), bSawdust ? null : GT_OreDictUnificator.get("dustWood", null, 1), 50, false);
            }
            if (!aBackSmelting || ((ItemStack)tList.get((int)i)).stackSize != 1 || aInAmount != 1) continue;
            GT_ModHandler.addSmeltingRecipe((ItemStack)tList.get(i), new ItemStack(aInItem, 1, aInDamage));
        }
        tList = GT_ModHandler.getRecipeOutputs(new ItemStack[]{aMat, null, null, tMt2, null, null, null, null, null});
        for (i = 0; i < tList.size(); ++i) {
            if (aBackMacerating) {
                GT_ModHandler.addPulverisationRecipe((ItemStack)tList.get(i), new ItemStack(aOutItem, 1 * aOutAmount + (bSawdust ? 2 : 0), aOutDamage), bSawdust ? null : GT_OreDictUnificator.get("dustWood", null, 1), 50, false);
            }
            if (!aBackSmelting || ((ItemStack)tList.get((int)i)).stackSize != 1 || aInAmount != 1) continue;
            GT_ModHandler.addSmeltingRecipe((ItemStack)tList.get(i), new ItemStack(aInItem, 1, aInDamage));
        }
    }

    public static Field getPublicField(Object aObject, String aField) {
        Field rField = null;
        try {
            rField = aObject.getClass().getDeclaredField(aField);
        }
        catch (Throwable throwable) {
            // empty catch block
        }
        return rField;
    }

    public static Field getField(Object aObject, String aField) {
        Field rField = null;
        try {
            rField = aObject.getClass().getDeclaredField(aField);
            rField.setAccessible(true);
        }
        catch (Throwable throwable) {
            // empty catch block
        }
        return rField;
    }

    public static Field getField(Class aObject, String aField) {
        Field rField = null;
        try {
            rField = aObject.getDeclaredField(aField);
            rField.setAccessible(true);
        }
        catch (Throwable throwable) {
            // empty catch block
        }
        return rField;
    }

    public static boolean getPotion(EntityLiving aPlayer, int aPotionIndex) {
        try {
            Field tPotionHashmap = null;
            for (Field var6 : EntityLiving.class.getDeclaredFields()) {
                if (var6.getType() != HashMap.class) continue;
                tPotionHashmap = var6;
                tPotionHashmap.setAccessible(true);
                break;
            }
            if (tPotionHashmap != null) {
                return ((HashMap)tPotionHashmap.get(aPlayer)).get(aPotionIndex) != null;
            }
        }
        catch (Throwable throwable) {
            // empty catch block
        }
        return false;
    }

    public static void removePotion(EntityLiving aPlayer, int aPotionIndex) {
        try {
            Field tPotionHashmap = null;
            for (Field var6 : EntityLiving.class.getDeclaredFields()) {
                if (var6.getType() != HashMap.class) continue;
                tPotionHashmap = var6;
                tPotionHashmap.setAccessible(true);
                break;
            }
            if (tPotionHashmap != null) {
                ((HashMap)tPotionHashmap.get(aPlayer)).remove(aPotionIndex);
            }
        }
        catch (Throwable throwable) {
            // empty catch block
        }
    }

    public static boolean getFullInvisibility(EntityPlayer aPlayer) {
        if (GT_Utility.getPotion((EntityLiving)aPlayer, Potion.invisibility.id)) {
            for (int i = 0; i < 4; ++i) {
                if (aPlayer.inventory.armorInventory[i] == null || !(aPlayer.inventory.armorInventory[i].getItem() instanceof GT_EnergyArmor_Item) || (((GT_EnergyArmor_Item)aPlayer.inventory.armorInventory[i].getItem()).mSpecials & 0x200) == 0 || !ElectricItem.canUse((ItemStack)aPlayer.inventory.armorInventory[i], (int)10000)) continue;
                return true;
            }
        }
        return false;
    }

    public static ItemStack suckOneItemStackAt(World aWorld, int aX, int aY, int aZ, int aL, int aH, int aW) {
        Chunk tChunk = aWorld.getChunkFromBlockCoords(aX, aZ);
        ArrayList tList = new ArrayList();
        tChunk.getEntitiesOfTypeWithinAAAB(EntityItem.class, AxisAlignedBB.getBoundingBox((double)aX, (double)aY, (double)aZ, (double)(aX + aL), (double)(aY + aH), (double)(aZ + aW)), tList, null);
        if (tList.size() > 0) {
            tChunk.removeEntity((Entity)tList.get(0));
            ((EntityItem)tList.get(0)).setDead();
            return ((EntityItem)tList.get(0)).getEntityItem();
        }
        return null;
    }

    public static boolean isValidSlot(IInventory aTileEntity, int aSlot) {
        if (aTileEntity != null && aSlot >= 0) {
            if (aTileEntity instanceof ISidedInventory) {
                ISidedInventory tTileEntity = (ISidedInventory)aTileEntity;
                for (int i = 0; i < 7; ++i) {
                    ForgeDirection tSide = ForgeDirection.getOrientation((int)i);
                    if (aSlot < tTileEntity.getStartInventorySide(tSide) || aSlot >= tTileEntity.getStartInventorySide(tSide) + tTileEntity.getSizeInventorySide(tSide)) continue;
                    return true;
                }
            } else {
                return aSlot < aTileEntity.getSizeInventory();
            }
        }
        return false;
    }

    public static int getIndexFromInventorySide(IInventory aTileEntity, ForgeDirection aGrabFrom, int aSubIndex) {
        if (aTileEntity != null) {
            if (aTileEntity instanceof ISidedInventory) {
                if (aSubIndex < ((ISidedInventory)aTileEntity).getSizeInventorySide(aGrabFrom)) {
                    return ((ISidedInventory)aTileEntity).getStartInventorySide(aGrabFrom) + aSubIndex;
                }
            } else if (aSubIndex < aTileEntity.getSizeInventory()) {
                return aSubIndex;
            }
        }
        return -1;
    }

    public static int moveStackFromSlotAToSlotB(IInventory aTileEntity1, IInventory aTileEntity2, int aGrabFrom, int aPutTo, int aMaxTargetStackSize, int aMinTargetStackSize, int aMaxMoveAtOnce, int aMinMoveAtOnce) {
        if (aTileEntity1 == null || aTileEntity2 == null || aMaxTargetStackSize <= 0 || aMinTargetStackSize <= 0 || aMinTargetStackSize > aMaxTargetStackSize || aMaxMoveAtOnce <= 0 || aMinMoveAtOnce > aMaxMoveAtOnce) {
            return 0;
        }
        ItemStack tStack1 = aTileEntity1.getStackInSlot(aGrabFrom);
        ItemStack tStack2 = aTileEntity2.getStackInSlot(aPutTo);
        ItemStack tStack3 = null;
        if (tStack1 != null) {
            if (!(tStack2 == null || tStack1.isItemEqual(tStack2) && ItemStack.areItemStackTagsEqual((ItemStack)tStack1, (ItemStack)tStack2))) {
                return 0;
            }
            tStack3 = tStack1.copy();
            aMaxTargetStackSize = Math.min(aMaxTargetStackSize, Math.min(tStack3.getMaxStackSize(), Math.min(tStack2 == null ? Integer.MAX_VALUE : tStack2.getMaxStackSize(), aTileEntity2.getInventoryStackLimit())));
            tStack3.stackSize = Math.min(tStack3.stackSize, aMaxTargetStackSize - (tStack2 == null ? 0 : tStack2.stackSize));
            if (tStack3.stackSize > aMaxMoveAtOnce) {
                tStack3.stackSize = aMaxMoveAtOnce;
            }
            if (tStack3.stackSize + (tStack2 == null ? 0 : tStack2.stackSize) >= aMinTargetStackSize && tStack3.stackSize >= aMinMoveAtOnce && (tStack3 = aTileEntity1.decrStackSize(aGrabFrom, tStack3.stackSize)) != null) {
                if (tStack2 == null) {
                    aTileEntity2.setInventorySlotContents(aPutTo, tStack3.copy());
                } else {
                    tStack2.stackSize += tStack3.stackSize;
                }
                return tStack3.stackSize;
            }
        }
        return 0;
    }

    public static int moveOneItemStack(IInventory aTileEntity1, IInventory aTileEntity2, ForgeDirection aGrabFrom, ForgeDirection aPutTo, ArrayList aFilter, boolean aInvertFilter, int aMaxTargetStackSize, int aMinTargetStackSize, int aMaxMoveAtOnce, int aMinMoveAtOnce) {
        int tAmount;
        if (aTileEntity1 == null || aTileEntity2 == null || aGrabFrom == null || aPutTo == null || aMaxTargetStackSize <= 0 || aMinTargetStackSize <= 0 || aMaxMoveAtOnce <= 0 || aMinTargetStackSize > aMaxTargetStackSize || aMinMoveAtOnce > aMaxMoveAtOnce) {
            return 0;
        }
        int i = -1;
        int tGrabIndex = -1;
        int tPutIndex = -1;
        int tMovedItemCount = 0;
        Object tStack1 = null;
        Object tStack2 = null;
        Object tStack3 = null;
        while ((tGrabIndex = GT_Utility.getIndexFromInventorySide(aTileEntity1, aGrabFrom, ++i)) >= 0) {
            int j = -1;
            while ((tPutIndex = GT_Utility.getIndexFromInventorySide(aTileEntity2, aPutTo, ++j)) >= 0) {
                if (!GT_Utility.listContainsItem(aFilter, aTileEntity1.getStackInSlot(tGrabIndex), true, aInvertFilter) || (tMovedItemCount = GT_Utility.moveStackFromSlotAToSlotB(aTileEntity1, aTileEntity2, tGrabIndex, tPutIndex, aMaxTargetStackSize, aMinTargetStackSize, aMaxMoveAtOnce, aMinMoveAtOnce)) <= 0) continue;
                return tMovedItemCount;
            }
        }
        if (doCheckChest1 && aTileEntity1 instanceof TileEntityChest) {
            TileEntityChest tTileEntity1 = (TileEntityChest)aTileEntity1;
            if (tTileEntity1.adjacentChestChecked) {
                doCheckChest1 = false;
                tAmount = 0;
                if (tTileEntity1.adjacentChestXNeg != null) {
                    tAmount = GT_Utility.moveOneItemStack((IInventory)tTileEntity1.adjacentChestXNeg, aTileEntity2, aGrabFrom, aPutTo, aFilter, aInvertFilter, aMaxTargetStackSize, aMinTargetStackSize, aMaxMoveAtOnce, aMinMoveAtOnce);
                } else if (tTileEntity1.adjacentChestZNeg != null) {
                    tAmount = GT_Utility.moveOneItemStack((IInventory)tTileEntity1.adjacentChestZNeg, aTileEntity2, aGrabFrom, aPutTo, aFilter, aInvertFilter, aMaxTargetStackSize, aMinTargetStackSize, aMaxMoveAtOnce, aMinMoveAtOnce);
                } else if (tTileEntity1.adjacentChestXPos != null) {
                    tAmount = GT_Utility.moveOneItemStack((IInventory)tTileEntity1.adjacentChestXPos, aTileEntity2, aGrabFrom, aPutTo, aFilter, aInvertFilter, aMaxTargetStackSize, aMinTargetStackSize, aMaxMoveAtOnce, aMinMoveAtOnce);
                } else if (tTileEntity1.adjacentChestZPosition != null) {
                    tAmount = GT_Utility.moveOneItemStack((IInventory)tTileEntity1.adjacentChestZPosition, aTileEntity2, aGrabFrom, aPutTo, aFilter, aInvertFilter, aMaxTargetStackSize, aMinTargetStackSize, aMaxMoveAtOnce, aMinMoveAtOnce);
                }
                doCheckChest1 = true;
                if (tAmount != 0) {
                    return tAmount;
                }
            }
        }
        if (doCheckChest2 && aTileEntity2 instanceof TileEntityChest) {
            TileEntityChest tTileEntity2 = (TileEntityChest)aTileEntity2;
            if (tTileEntity2.adjacentChestChecked) {
                doCheckChest2 = false;
                tAmount = 0;
                if (tTileEntity2.adjacentChestXNeg != null) {
                    tAmount = GT_Utility.moveOneItemStack(aTileEntity1, (IInventory)tTileEntity2.adjacentChestXNeg, aGrabFrom, aPutTo, aFilter, aInvertFilter, aMaxTargetStackSize, aMinTargetStackSize, aMaxMoveAtOnce, aMinMoveAtOnce);
                } else if (tTileEntity2.adjacentChestZNeg != null) {
                    tAmount = GT_Utility.moveOneItemStack(aTileEntity1, (IInventory)tTileEntity2.adjacentChestZNeg, aGrabFrom, aPutTo, aFilter, aInvertFilter, aMaxTargetStackSize, aMinTargetStackSize, aMaxMoveAtOnce, aMinMoveAtOnce);
                } else if (tTileEntity2.adjacentChestXPos != null) {
                    tAmount = GT_Utility.moveOneItemStack(aTileEntity1, (IInventory)tTileEntity2.adjacentChestXPos, aGrabFrom, aPutTo, aFilter, aInvertFilter, aMaxTargetStackSize, aMinTargetStackSize, aMaxMoveAtOnce, aMinMoveAtOnce);
                } else if (tTileEntity2.adjacentChestZPosition != null) {
                    tAmount = GT_Utility.moveOneItemStack(aTileEntity1, (IInventory)tTileEntity2.adjacentChestZPosition, aGrabFrom, aPutTo, aFilter, aInvertFilter, aMaxTargetStackSize, aMinTargetStackSize, aMaxMoveAtOnce, aMinMoveAtOnce);
                }
                doCheckChest2 = true;
                if (tAmount != 0) {
                    return tAmount;
                }
            }
        }
        return 0;
    }

    public static int moveOneItemStackIntoSlot(IInventory aTileEntity1, IInventory aTileEntity2, ForgeDirection aGrabFrom, int aPutTo, ArrayList aFilter, boolean aInvertFilter, int aMaxTargetStackSize, int aMinTargetStackSize, int aMaxMoveAtOnce, int aMinMoveAtOnce) {
        if (aTileEntity1 == null || aTileEntity2 == null || aGrabFrom == null || aPutTo < 0 || aMaxTargetStackSize <= 0 || aMinTargetStackSize <= 0 || aMaxMoveAtOnce <= 0 || aMinTargetStackSize > aMaxTargetStackSize || aMinMoveAtOnce > aMaxMoveAtOnce || !GT_Utility.isValidSlot(aTileEntity2, aPutTo)) {
            return 0;
        }
        try {
            int i = -1;
            int j = -1;
            int tGrabIndex = -1;
            int tMovedItemCount = 0;
            Object tStack1 = null;
            Object tStack2 = null;
            Object tStack3 = null;
            while ((tGrabIndex = GT_Utility.getIndexFromInventorySide(aTileEntity1, aGrabFrom, ++i)) >= 0) {
                if (!GT_Utility.listContainsItem(aFilter, aTileEntity1.getStackInSlot(tGrabIndex), true, aInvertFilter) || (tMovedItemCount = GT_Utility.moveStackFromSlotAToSlotB(aTileEntity1, aTileEntity2, tGrabIndex, aPutTo, aMaxTargetStackSize, aMinTargetStackSize, aMaxMoveAtOnce, aMinMoveAtOnce)) <= 0) continue;
                return tMovedItemCount;
            }
        }
        catch (NullPointerException e) {
            e.printStackTrace(GT_Log.out);
        }
        return 0;
    }

    public static boolean listContainsItem(ArrayList aList, ItemStack aStack, boolean aTrueIfListEmpty, boolean aInvertFilter) {
        if (aStack == null || aStack.stackSize < 1) {
            return false;
        }
        if (aList == null) {
            return aTrueIfListEmpty;
        }
        while (aList.contains(null)) {
            aList.remove(null);
        }
        if (aList.size() < 1) {
            return aTrueIfListEmpty;
        }
        Iterator tIterator = aList.iterator();
        ItemStack tStack = null;
        while (tIterator.hasNext()) {
            tStack = (ItemStack)tIterator.next();
            if (tStack == null || !GT_Utility.areStacksEqual(aStack, tStack)) continue;
            return !aInvertFilter;
        }
        return aInvertFilter;
    }

    public static boolean areStacksEqual(ItemStack aStack1, ItemStack aStack2) {
        if (aStack1 == null || aStack2 == null || aStack1.itemID != aStack2.itemID) {
            return false;
        }
        return aStack1.getItemDamage() == aStack2.getItemDamage() || aStack1.getItemDamage() == -1 || aStack2.getItemDamage() == -1;
    }

    public static ItemStack getContainerForFilledItem(ItemStack aStack) {
        if (aStack == null || aStack.isItemEqual(GT_ModHandler.getIC2Item("matter", 1))) {
            return null;
        }
        LiquidContainerData[] tData = LiquidContainerRegistry.getRegisteredLiquidContainerData();
        for (int i = 0; i < tData.length; ++i) {
            if (tData[i].filled == null || !tData[i].filled.isItemEqual(aStack)) continue;
            return tData[i].container;
        }
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
        if (aStack == null) {
            return 0;
        }
        return aStack.itemID | aStack.getItemDamage() << 16;
    }
}

