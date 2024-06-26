/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  ic2.api.Ic2Recipes
 *  net.minecraft.block.Block
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemBlock
 *  net.minecraft.item.ItemStack
 *  net.minecraft.item.crafting.FurnaceRecipes
 *  net.minecraftforge.common.MinecraftForge
 *  net.minecraftforge.event.ForgeSubscribe
 *  net.minecraftforge.liquids.LiquidStack
 *  net.minecraftforge.oredict.OreDictionary$OreRegisterEvent
 */
package gregtechmod.common;

import gregtechmod.GT_Mod;
import gregtechmod.common.GT_ModHandler;
import gregtechmod.common.GT_OreDictUnificator;
import gregtechmod.common.GT_Utility;
import gregtechmod.common.items.GT_MetaItem_Cell;
import gregtechmod.common.items.GT_MetaItem_Component;
import gregtechmod.common.items.GT_MetaItem_Dust;
import gregtechmod.common.items.GT_MetaItem_Material;
import gregtechmod.common.items.GT_MetaItem_SmallDust;
import ic2.api.Ic2Recipes;
import java.util.ArrayList;
import java.util.Iterator;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.liquids.LiquidStack;
import net.minecraftforge.oredict.OreDictionary;

public class GT_OreDictHandler {
    private ArrayList mEvents = new ArrayList();
    private ArrayList mIgnoredNames = new ArrayList();
    private ArrayList mValidPrefixes = new ArrayList();
    private boolean mActivated = false;

    public GT_OreDictHandler() {
        MinecraftForge.EVENT_BUS.register((Object)this);
        this.mIgnoredNames.add("superconductor");
        this.mIgnoredNames.add("aluminumWire");
        this.mIgnoredNames.add("aluminiumWire");
        this.mIgnoredNames.add("silverWire");
        this.mIgnoredNames.add("tinWire");
        this.mIgnoredNames.add("eliteBattery");
        this.mIgnoredNames.add("advancedBattery");
        this.mIgnoredNames.add("transformer");
        this.mIgnoredNames.add("coil");
        this.mIgnoredNames.add("wireMill");
        this.mIgnoredNames.add("multimeter");
        this.mIgnoredNames.add("itemMultimeter");
        this.mIgnoredNames.add("chunkLazurite");
        this.mIgnoredNames.add("itemRecord");
        this.mIgnoredNames.add("aluminumNatural");
        this.mIgnoredNames.add("aluminiumNatural");
        this.mIgnoredNames.add("naturalAluminum");
        this.mIgnoredNames.add("naturalAluminium");
        this.mIgnoredNames.add("antimatterMilligram");
        this.mIgnoredNames.add("antimatterGram");
        this.mIgnoredNames.add("strangeMatter");
        this.mIgnoredNames.add("HSLivingmetalIngot");
        this.mIgnoredNames.add("oilMoving");
        this.mIgnoredNames.add("oilStill");
        this.mIgnoredNames.add("oilBucket");
        this.mIgnoredNames.add("petroleumOre");
        this.mIgnoredNames.add("dieselFuel");
        this.mIgnoredNames.add("lava");
        this.mIgnoredNames.add("water");
        this.mIgnoredNames.add("obsidianRod");
        this.mIgnoredNames.add("motor");
        this.mIgnoredNames.add("wrench");
        this.mIgnoredNames.add("batteryBox");
        this.mIgnoredNames.add("coalGenerator");
        this.mIgnoredNames.add("electricFurnace");
        this.mIgnoredNames.add("batteryBox");
        this.mIgnoredNames.add("batteryBox");
        this.mIgnoredNames.add("batteryBox");
        this.mIgnoredNames.add("batteryBox");
        this.mIgnoredNames.add("bronzeTube");
        this.mIgnoredNames.add("ironTube");
        this.mIgnoredNames.add("netherTube");
        this.mIgnoredNames.add("obbyTube");
        this.mIgnoredNames.add("unfinishedTank");
        this.mIgnoredNames.add("valvePart");
        this.mIgnoredNames.add("leatherSeal");
        this.mIgnoredNames.add("leatherSlimeSeal");
        this.mIgnoredNames.add("enrichedUranium");
        this.mValidPrefixes.add("crafting");
        this.mValidPrefixes.add("ore");
        this.mValidPrefixes.add("oreNether");
        this.mValidPrefixes.add("oreEnd");
        this.mValidPrefixes.add("netherOre");
        this.mValidPrefixes.add("endOre");
        this.mValidPrefixes.add("stone");
        this.mValidPrefixes.add("pulp");
        this.mValidPrefixes.add("dust");
        this.mValidPrefixes.add("dustSmall");
        this.mValidPrefixes.add("nugget");
        this.mValidPrefixes.add("ingot");
        this.mValidPrefixes.add("gem");
        this.mValidPrefixes.add("log");
        this.mValidPrefixes.add("tree");
        this.mValidPrefixes.add("flower");
        this.mValidPrefixes.add("item");
        this.mValidPrefixes.add("brick");
        this.mValidPrefixes.add("plank");
        this.mValidPrefixes.add("sand");
        this.mValidPrefixes.add("glass");
        this.mValidPrefixes.add("dye");
        this.mValidPrefixes.add("slab");
        this.mValidPrefixes.add("stair");
        this.mValidPrefixes.add("clump");
        this.mValidPrefixes.add("paper");
        this.mValidPrefixes.add("book");
        this.mValidPrefixes.add("plasma_");
        this.mValidPrefixes.add("molecule_");
        this.registerOre(new OreDictionary.OreRegisterEvent("logWood", new ItemStack(Block.wood, 1, -1)));
        this.registerOre(new OreDictionary.OreRegisterEvent("slabWood", new ItemStack((Block)Block.woodSingleSlab, 1, -1)));
        this.registerOre(new OreDictionary.OreRegisterEvent("plankWood", new ItemStack(Block.planks, 1, -1)));
        this.registerOre(new OreDictionary.OreRegisterEvent("stickWood", new ItemStack(Item.stick, 1, 0)));
        this.registerOre(new OreDictionary.OreRegisterEvent("treeLeaves", new ItemStack((Block)Block.leaves, 1, -1)));
        this.registerOre(new OreDictionary.OreRegisterEvent("treeSapling", new ItemStack(Block.sapling, 1, -1)));
        String[] dyes = new String[]{"dyeBlack", "dyeRed", "dyeGreen", "dyeBrown", "dyeBlue", "dyePurple", "dyeCyan", "dyeLightGray", "dyeGray", "dyePink", "dyeLime", "dyeYellow", "dyeLightBlue", "dyeMagenta", "dyeOrange", "dyeWhite"};
        for (int i = 0; i < 16; ++i) {
            this.registerOre(new OreDictionary.OreRegisterEvent(dyes[i], new ItemStack(Item.dyePowder, 1, i)));
        }
    }

    @ForgeSubscribe
    public void registerOre(OreDictionary.OreRegisterEvent aEvent) {
        if (aEvent.Ore == null || aEvent.Name == null || aEvent.Name.equals("") || this.mIgnoredNames.contains(aEvent.Name)) {
            return;
        }
        if (aEvent.Ore.stackSize != 1) {
            System.out.println("'" + aEvent.Name + "' is either being misused by another Mod or has been wrongly registered, as the stackSize of the Event-Stack is not 1.");
        }
        aEvent.Ore.stackSize = 1;
        if (aEvent.Name.contains("Xych") || aEvent.Name.contains("xych") || aEvent.Name.contains("xyOre")) {
            return;
        }
        if (this.mValidPrefixes.contains(aEvent.Name)) {
            System.out.println("'" + aEvent.Name + "' is an invalid OreDictionary Name, as it is identical to the prefix '" + aEvent.Name + "'!");
            return;
        }
        if (aEvent.Name.startsWith("itemDust")) {
            System.out.println("'" + aEvent.Name + "' is an invalid OreDictionary Name, as it uses the prefix 'itemDust' instead of 'dust'. It will be re-registered with the correct 'dust'-prefix, and then be thrown into the recycle Bin to become Scrap.");
            GT_OreDictUnificator.registerOre(aEvent.Name.replaceFirst("itemDust", "dust"), aEvent.Ore.copy());
            aEvent.Ore.itemID = GT_ModHandler.getIC2Item((String)"scrap", (int)1).getItem().itemID;
            aEvent.Ore.setItemDamage(0);
            return;
        }
        if (aEvent.Name.equals("blueDye")) {
            System.out.println("'blueDye'?, Are you sure that it shouldn't be using the more valid Name 'dyeBlue'?");
            return;
        }
        if (aEvent.Name.equals("sapling")) {
            System.out.println("'sapling'?, Are you sure that it shouldn't be using the more valid Name 'treeSapling'?");
            return;
        }
        if (aEvent.Name.equals("saplingPalm")) {
            System.out.println("'saplingPalm'?, Are you sure that it shouldn't be using the more valid Name 'treeSaplingPalm'?");
            return;
        }
        if (aEvent.Name.equals("leaves")) {
            System.out.println("'leaves'?, Are you sure that it shouldn't be using the more valid Name 'treeLeaves'?");
            return;
        }
        if (aEvent.Name.equals("leavesPalm")) {
            System.out.println("'leavesPalm'?, Are you sure that it shouldn't be using the more valid Name 'treeLeavesPalm'?");
            return;
        }
        if (aEvent.Name.startsWith("ore") && !(aEvent.Ore.getItem() instanceof ItemBlock)) {
            System.err.println("WARNING: Detected Invalid OreDictionary Tag.");
            System.err.println("WARNING: Only Blocks can have the 'ore'-prefix");
            System.err.println("WARNING: A Nonblock-Item registered as " + aEvent.Name);
            System.err.println("WARNING: This can cause crashes! You were lucky it didn't crash");
            System.err.println("WARNING: Spamming Log to make this fatal error more visible");
            for (int i = 0; i < 100; ++i) {
                System.err.println("WARNING: !!!");
            }
        }
        if (aEvent.Name.equals("battery")) {
            GT_OreDictUnificator.registerOre("crafting10kEUStore", aEvent.Ore);
            return;
        }
        if (aEvent.Name.equals("basicCircuit")) {
            GT_OreDictUnificator.registerOre("craftingCircuitTier02", aEvent.Ore);
            return;
        }
        if (aEvent.Name.equals("advancedCircuit")) {
            GT_OreDictUnificator.registerOre("craftingCircuitTier04", aEvent.Ore);
            return;
        }
        if (aEvent.Name.equals("eliteCircuit")) {
            GT_OreDictUnificator.registerOre("craftingCircuitTier06", aEvent.Ore);
            return;
        }
        if (aEvent.Name.equals("MonazitOre")) {
            GT_OreDictUnificator.registerOre("oreMonazit", aEvent.Ore);
            return;
        }
        if (aEvent.Name.equals("blockQuickSilver")) {
            GT_OreDictUnificator.registerOre("blockQuicksilver", aEvent.Ore);
            return;
        }
        if (aEvent.Name.equals("ingotQuickSilver")) {
            GT_OreDictUnificator.registerOre("ingotQuicksilver", aEvent.Ore);
            return;
        }
        if (aEvent.Name.equals("dustQuickSilver")) {
            GT_OreDictUnificator.registerOre("dustQuicksilver", aEvent.Ore);
            return;
        }
        if (aEvent.Name.equals("itemQuickSilver")) {
            GT_OreDictUnificator.registerOre("itemQuicksilver", aEvent.Ore);
            return;
        }
        if (aEvent.Name.equals("dustCharCoal")) {
            GT_OreDictUnificator.registerOre("dustCharcoal", aEvent.Ore);
            return;
        }
        if (aEvent.Name.equals("quartzCrystal")) {
            GT_OreDictUnificator.registerOre("crystalQuartz", aEvent.Ore);
            return;
        }
        if (aEvent.Name.equals("quartz")) {
            GT_OreDictUnificator.registerOre("crystalQuartz", aEvent.Ore);
            return;
        }
        if (aEvent.Name.equals("woodGas")) {
            GT_OreDictUnificator.registerOre("gasWood", aEvent.Ore);
            return;
        }
        if (aEvent.Name.equals("woodLog")) {
            GT_OreDictUnificator.registerOre("logWood", aEvent.Ore);
            return;
        }
        if (aEvent.Name.equals("pulpWood")) {
            GT_OreDictUnificator.registerOre("dustWood", aEvent.Ore);
            return;
        }
        if (aEvent.Name.startsWith("denseOre")) {
            GT_OreDictUnificator.registerOre(aEvent.Name.replaceFirst("denseOre", "oreDense"), aEvent.Ore);
            return;
        }
        if (aEvent.Name.startsWith("netherOre")) {
            GT_OreDictUnificator.registerOre(aEvent.Name.replaceFirst("netherOre", "oreNether"), aEvent.Ore);
            return;
        }
        if (aEvent.Name.startsWith("endOre")) {
            GT_OreDictUnificator.registerOre(aEvent.Name.replaceFirst("endOre", "oreEnd"), aEvent.Ore);
            return;
        }
        if (aEvent.Name.startsWith("itemDrop")) {
            GT_OreDictUnificator.registerOre(aEvent.Name.replaceFirst("itemDrop", "item"), aEvent.Ore);
            return;
        }
        if (aEvent.Name.equals("craftiongRawMachineTier01")) {
            GT_OreDictUnificator.registerOre("craftingRawMachineTier00", aEvent.Ore);
        }
        if (aEvent.Name.equals("dustDiamond")) {
            GT_OreDictUnificator.registerOre("itemDiamond", aEvent.Ore);
        }
        if (aEvent.Name.equals("gemDiamond")) {
            GT_OreDictUnificator.registerOre("itemDiamond", aEvent.Ore);
        }
        if (aEvent.Name.equals("dustLapis")) {
            GT_OreDictUnificator.registerOre("itemLazurite", aEvent.Ore);
        }
        if (aEvent.Name.equals("dustLapisLazuli")) {
            GT_OreDictUnificator.registerOre("itemLazurite", aEvent.Ore);
        }
        if (aEvent.Name.equals("dustLazurite")) {
            GT_OreDictUnificator.registerOre("itemLazurite", aEvent.Ore);
        }
        if (aEvent.Name.equals("dustQuicksilver")) {
            GT_OreDictUnificator.registerOre("itemQuicksilver", aEvent.Ore);
        }
        if (aEvent.Name.equals("ingotQuicksilver")) {
            GT_OreDictUnificator.registerOre("itemQuicksilver", aEvent.Ore);
        }
        if (aEvent.Name.equals("dustSulfur")) {
            GT_OreDictUnificator.registerOre("craftingSulfurToGunpowder", aEvent.Ore);
        }
        if (aEvent.Name.equals("dustSaltpeter")) {
            GT_OreDictUnificator.registerOre("craftingSaltpeterToGunpowder", aEvent.Ore);
        }
        if (aEvent.Name.equals("itemForcicium") || aEvent.Name.equals("ForciciumItem")) {
            GT_ModHandler.mForcicium = aEvent.Ore.copy();
        }
        if (aEvent.Name.equals("nuggetCopper") && aEvent.Ore.getItemName().equals("item.ItemNugget") && GT_ModHandler.mCopperNugget == null && aEvent.Ore.getItemDamage() == 1) {
            GT_ModHandler.mNuggetIron = new ItemStack(aEvent.Ore.getItem(), 1, 0);
            GT_ModHandler.mNuggetCopper = new ItemStack(aEvent.Ore.getItem(), 1, 1);
            GT_ModHandler.mNuggetTin = new ItemStack(aEvent.Ore.getItem(), 1, 2);
            GT_ModHandler.mNuggetSilver = new ItemStack(aEvent.Ore.getItem(), 1, 3);
            GT_ModHandler.mNuggetLead = new ItemStack(aEvent.Ore.getItem(), 1, 4);
        }
        if (aEvent.Name.equals("nuggetCopper") && aEvent.Ore.getItemName().equals("item.nuggetCopper") && GT_ModHandler.mCopperNugget == null && aEvent.Ore.getItemDamage() == 3) {
            GT_ModHandler.mIronNugget = new ItemStack(aEvent.Ore.getItem(), 1, 0);
            GT_ModHandler.mSilverNugget = new ItemStack(aEvent.Ore.getItem(), 1, 1);
            GT_ModHandler.mTinNugget = new ItemStack(aEvent.Ore.getItem(), 1, 2);
            GT_ModHandler.mCopperNugget = new ItemStack(aEvent.Ore.getItem(), 1, 3);
        }
        if (aEvent.Name.equals("dustNikolite") && aEvent.Ore.getItemName().equals("item.nikolite") && GT_ModHandler.mNikolite == null && aEvent.Ore.getItemDamage() == 6) {
            GT_ModHandler.mRuby = new ItemStack(aEvent.Ore.getItem(), 1, 0);
            GT_ModHandler.mGreenSapphire = new ItemStack(aEvent.Ore.getItem(), 1, 1);
            GT_ModHandler.mSapphire = new ItemStack(aEvent.Ore.getItem(), 1, 2);
            GT_ModHandler.mSilver = new ItemStack(aEvent.Ore.getItem(), 1, 3);
            GT_ModHandler.mTin = new ItemStack(aEvent.Ore.getItem(), 1, 4);
            GT_ModHandler.mCopper = new ItemStack(aEvent.Ore.getItem(), 1, 5);
            GT_ModHandler.mNikolite = new ItemStack(aEvent.Ore.getItem(), 1, 6);
        }
        if (aEvent.Name.equals("ingotBrass") && aEvent.Ore.getItemName().equals("item.ingotBrass") && GT_ModHandler.mBrass == null && aEvent.Ore.getItemDamage() == 2) {
            GT_ModHandler.mRedAlloy = new ItemStack(aEvent.Ore.getItem(), 1, 0);
            GT_ModHandler.mBlueAlloy = new ItemStack(aEvent.Ore.getItem(), 1, 1);
            GT_ModHandler.mBrass = new ItemStack(aEvent.Ore.getItem(), 1, 2);
            GT_ModHandler.mSiliconBoule = new ItemStack(aEvent.Ore.getItem(), 1, 3);
            GT_ModHandler.mSiliconWafer = new ItemStack(aEvent.Ore.getItem(), 1, 4);
            GT_ModHandler.mBlueWafer = new ItemStack(aEvent.Ore.getItem(), 1, 5);
            GT_ModHandler.mRedWafer = new ItemStack(aEvent.Ore.getItem(), 1, 6);
            GT_ModHandler.mRPTinPlate = new ItemStack(aEvent.Ore.getItem(), 1, 7);
            GT_ModHandler.mFineCopper = new ItemStack(aEvent.Ore.getItem(), 1, 8);
            GT_ModHandler.mFineIron = new ItemStack(aEvent.Ore.getItem(), 1, 9);
            GT_ModHandler.mCopperCoil = new ItemStack(aEvent.Ore.getItem(), 1, 10);
            GT_ModHandler.mBlutricMotor = new ItemStack(aEvent.Ore.getItem(), 1, 11);
            GT_ModHandler.mCanvas = new ItemStack(aEvent.Ore.getItem(), 1, 12);
            ItemStack tStack1 = GT_ModHandler.mSiliconWafer.copy();
            tStack1.stackSize = 16;
            GT_Mod.addSawmillRecipe(GT_ModHandler.mSiliconBoule.copy(), -1, tStack1, null, GT_ModHandler.getEmptyCell(1));
        }
        if (aEvent.Name.startsWith("plate") || aEvent.Name.startsWith("ore") || aEvent.Name.startsWith("dust") || aEvent.Name.startsWith("gem") || aEvent.Name.startsWith("ingot") || aEvent.Name.startsWith("nugget") || aEvent.Name.startsWith("block")) {
            GT_OreDictUnificator.addAssociation(aEvent.Name, aEvent.Ore.copy());
        }
        if (this.mActivated) {
            this.registerRecipes(aEvent);
        } else {
            this.mEvents.add(aEvent);
        }
    }

    public void activateHandler() {
        this.mActivated = true;
        Iterator tIterator = this.mEvents.iterator();
        while (tIterator.hasNext()) {
            this.registerRecipes((OreDictionary.OreRegisterEvent)tIterator.next());
        }
        this.mEvents = null;
    }

    /*
     * Opcode count of 13438 triggered aggressive code reduction.  Override with --aggressivesizethreshold.
     */
    public void registerRecipes(OreDictionary.OreRegisterEvent aEvent) {
        if (aEvent.Ore == null || aEvent.Ore.getItem() == null) {
            return;
        }
        aEvent.Ore.stackSize = 1;
        int tCellCount = 0;
        int aMeta = aEvent.Ore.getItemDamage();
        Item aItem = aEvent.Ore.getItem();
        String aEventName = aEvent.Name;
        boolean aNether = false;
        boolean aDense = false;
        if (aEvent.Name.startsWith("plate") || aEvent.Name.startsWith("ore") || aEvent.Name.startsWith("dust") || aEvent.Name.startsWith("gem") || aEvent.Name.startsWith("ingot") || aEvent.Name.startsWith("nugget") || aEvent.Name.startsWith("block")) {
            GT_OreDictUnificator.add(aEvent.Name, aEvent.Ore.copy());
        }
        if (aEventName.startsWith("oreDense")) {
            aEventName = aEventName.replaceFirst("oreDense", "ore");
            aDense = true;
        }
        if (aEventName.startsWith("oreNether")) {
            aEventName = aEventName.replaceFirst("oreNether", "ore");
            aNether = true;
        }
        if (aEventName.startsWith("oreEnd")) {
            aEventName = aEventName.replaceFirst("oreEnd", "ore");
        }
        if (aEventName.startsWith("drop")) {
            aEventName = aEventName.replaceFirst("drop", "item");
        }
        if (aEventName.startsWith("stone")) {
            if (aItem.itemID < 4096) {
                GT_Mod.addJackHammerMinableBlock(Block.blocksList[aItem.itemID]);
            }
            if (aItem instanceof ItemBlock && aItem.getItemStackLimit() > GT_Mod.instance.mBlockStackSize) {
                aItem.setMaxStackSize(GT_Mod.instance.mBlockStackSize);
            }
            if (aEventName.equals("stoneVanilla")) {
                GT_ModHandler.addPulverisationRecipe(new ItemStack(aItem, 1, aMeta), new ItemStack(Block.sand, 1, 0), null, 10, false);
            } else if (aEventName.equals("stoneSand")) {
                GT_ModHandler.addPulverisationRecipe(new ItemStack(aItem, 1, aMeta), new ItemStack(Block.sand, 1, 0), null, 10, false);
            } else if (aEventName.equals("stoneRedrock") || aEventName.equals("stoneRedRock")) {
                GT_ModHandler.addPulverisationRecipe(new ItemStack(aItem, 1, aMeta), GT_MetaItem_Dust.instance.getStack(64, 1), GT_MetaItem_Dust.instance.getStack(64, 1), 10, false);
            } else if (aEventName.equals("stoneMarble")) {
                GT_ModHandler.addPulverisationRecipe(new ItemStack(aItem, 1, aMeta), GT_MetaItem_Dust.instance.getStack(65, 1), GT_MetaItem_Dust.instance.getStack(65, 1), 10, false);
            } else if (aEventName.equals("stoneBasalt")) {
                GT_ModHandler.addPulverisationRecipe(new ItemStack(aItem, 1, aMeta), GT_MetaItem_Dust.instance.getStack(66, 1), GT_MetaItem_Dust.instance.getStack(66, 1), 10, false);
            } else if (aEventName.equals("stoneEnd")) {
                GT_ModHandler.addPulverisationRecipe(new ItemStack(aItem, 1, aMeta), GT_MetaItem_Dust.instance.getStack(10, 1), GT_MetaItem_Dust.instance.getStack(10, 1), 10, false);
            } else if (aEventName.equals("stoneNetherrack")) {
                GT_ModHandler.addPulverisationRecipe(new ItemStack(aItem, 1, aMeta), GT_MetaItem_Dust.instance.getStack(6, 1), GT_MetaItem_Dust.instance.getStack(6, 1), 10, false);
            } else if (!aEventName.equals("stoneNetherBrick") && !aEventName.equals("stoneNetherQuartz")) {
                System.out.println("Stone Name: " + aEvent.Name + " !!!Unknown kind of Stone detected!!! Please report to GregTech Intergalactical for additional compatiblity. This is not an Error, it's just an Information. This Ore will still get added to the List of the IC2-Miner, but with a low Value.");
            }
        } else if (aEventName.startsWith("ore")) {
            if (aItem instanceof ItemBlock && aItem.getItemStackLimit() > GT_Mod.instance.mOreStackSize) {
                aItem.setMaxStackSize(GT_Mod.instance.mOreStackSize);
            }
            if (aEventName.equals("oreLapis")) {
                GT_Mod.addGrinderRecipe(new ItemStack(aItem, 1, aMeta), -1, new ItemStack(Item.dyePowder, aDense || aNether ? 18 : 12, 4), GT_MetaItem_Dust.instance.getStack(2, 3), null, GT_ModHandler.getEmptyCell(1));
                GT_ModHandler.addPulverisationRecipe(new ItemStack(aItem, 1, aMeta), new ItemStack(Item.dyePowder, 12, 4), GT_MetaItem_Dust.instance.getStack(2, 1), 0, true);
                GT_ModHandler.addValuableOre(aItem.itemID, aMeta, 3);
            } else if (aEventName.equals("oreSodalite")) {
                GT_Mod.addGrinderRecipe(new ItemStack(aItem, 1, aMeta), -1, GT_MetaItem_Dust.instance.getStack(5, aDense || aNether ? 18 : 12), GT_MetaItem_Dust.instance.getStack(18, 3), null, GT_ModHandler.getEmptyCell(1));
                GT_ModHandler.addPulverisationRecipe(new ItemStack(aItem, 1, aMeta), GT_MetaItem_Dust.instance.getStack(5, 12), GT_MetaItem_Dust.instance.getStack(18, 1), 0, false);
                GT_ModHandler.addValuableOre(aItem.itemID, aMeta, 3);
            } else if (aEventName.equals("oreRedstone")) {
                GT_Mod.addGrinderRecipe(new ItemStack(aItem, 1, aMeta), -1, new ItemStack(Item.redstone, aDense || aNether ? 15 : 10), GT_MetaItem_SmallDust.instance.getStack(250, 2), null, GT_ModHandler.getEmptyCell(1));
                GT_ModHandler.addPulverisationRecipe(new ItemStack(aItem, 1, aMeta), new ItemStack(Item.redstone, 10), new ItemStack(Item.lightStoneDust, 1), 0, true);
                GT_ModHandler.addValuableOre(aItem.itemID, aMeta, 3);
            } else if (aEventName.equals("oreNikolite")) {
                GT_Mod.addGrinderRecipe(new ItemStack(aItem, 1, aMeta), -1, GT_OreDictUnificator.getFirstOre("dustNikolite", aDense || aNether ? 18 : 12), GT_MetaItem_SmallDust.instance.getStack(36, aDense || aNether ? 2 : 1), null, GT_ModHandler.getEmptyCell(1));
                GT_ModHandler.addPulverisationRecipe(new ItemStack(aItem, 1, aMeta), GT_OreDictUnificator.getFirstOre("dustNikolite", aDense || aNether ? 15 : 10), GT_MetaItem_Dust.instance.getStack(36, 1), 0, false);
                GT_ModHandler.addValuableOre(aItem.itemID, aMeta, 3);
            } else if (aEventName.equals("oreIron")) {
                GT_ModHandler.addPulverisationRecipe(new ItemStack(aItem, 1, aMeta), GT_OreDictUnificator.get("dustIron", null, aDense || aNether ? 3 : 2), GT_OreDictUnificator.get("dustNickel", null, 1), 0, false);
                if (!aDense || aNether) {
                    GT_ModHandler.addOreToIngotSmeltingRecipe(new ItemStack(aItem, 1, aMeta), GT_OreDictUnificator.get("ingotIron", null, 1));
                }
                GT_Mod.addGrinderRecipe(new ItemStack(aItem, 1, aMeta), -1, GT_OreDictUnificator.get("dustIron", null, aDense || aNether ? 4 : 2), GT_MetaItem_SmallDust.instance.getStack(244, 1), GT_MetaItem_SmallDust.instance.getStack(28, 1), GT_ModHandler.getEmptyCell(1));
                GT_Mod.addBlastRecipe(new ItemStack(aItem, 1, aMeta), GT_MetaItem_Cell.instance.getStack(33, 1), GT_OreDictUnificator.get("ingotRefinedIron", null, aDense || aNether ? 5 : 3), GT_ModHandler.getEmptyCell(1), 100, 128, 1000);
                GT_ModHandler.addValuableOre(aItem.itemID, aMeta, 3);
            } else if (aEventName.equals("oreGold")) {
                GT_ModHandler.addPulverisationRecipe(new ItemStack(aItem, 1, aMeta), GT_OreDictUnificator.get("dustGold", null, aDense || aNether ? 3 : 2), GT_OreDictUnificator.get("dustCopper", null, 1), 0, false);
                if (!aDense || aNether) {
                    GT_ModHandler.addOreToIngotSmeltingRecipe(new ItemStack(aItem, 1, aMeta), GT_OreDictUnificator.get("ingotGold", null, 1));
                }
                GT_Mod.addGrinderRecipe(new ItemStack(aItem, 1, aMeta), -1, GT_OreDictUnificator.get("dustGold", null, aDense || aNether ? 4 : 2), GT_MetaItem_SmallDust.instance.getStack(243, 1), GT_MetaItem_SmallDust.instance.getStack(28, 1), GT_ModHandler.getEmptyCell(1));
                GT_Mod.addGrinderRecipe(new ItemStack(aItem, 1, aMeta), GT_MetaItem_Cell.instance.getStack(16, 1), GT_OreDictUnificator.get("dustGold", null, aDense || aNether ? 5 : 3), GT_MetaItem_SmallDust.instance.getStack(243, 1), GT_MetaItem_SmallDust.instance.getStack(28, 1), GT_ModHandler.getEmptyCell(1));
                GT_Mod.addGrinderRecipe(new ItemStack(aItem, 1, aMeta), GT_MetaItem_Cell.instance.getStack(32, 1), GT_OreDictUnificator.get("dustGold", null, aDense || aNether ? 4 : 2), GT_OreDictUnificator.get("dustCopper", null, 1), GT_MetaItem_SmallDust.instance.getStack(28, 1), GT_ModHandler.getEmptyCell(1));
                GT_ModHandler.addValuableOre(aItem.itemID, aMeta, 4);
            } else if (aEventName.equals("oreSilver")) {
                GT_ModHandler.addPulverisationRecipe(new ItemStack(aItem, 1, aMeta), GT_OreDictUnificator.get("dustSilver", null, aDense || aNether ? 3 : 2), GT_OreDictUnificator.get("dustLead", null, 1), 0, false);
                if (!aDense || aNether) {
                    GT_ModHandler.addOreToIngotSmeltingRecipe(new ItemStack(aItem, 1, aMeta), GT_OreDictUnificator.get("ingotSilver", null, 1));
                }
                GT_Mod.addGrinderRecipe(new ItemStack(aItem, 1, aMeta), -1, GT_OreDictUnificator.get("dustSilver", null, aDense || aNether ? 4 : 2), GT_MetaItem_SmallDust.instance.getStack(23, 2), null, GT_ModHandler.getEmptyCell(1));
                GT_Mod.addGrinderRecipe(new ItemStack(aItem, 1, aMeta), GT_MetaItem_Cell.instance.getStack(16, 1), GT_OreDictUnificator.get("dustSilver", null, aDense || aNether ? 5 : 3), GT_MetaItem_SmallDust.instance.getStack(23, 2), null, GT_ModHandler.getEmptyCell(1));
                GT_ModHandler.addValuableOre(aItem.itemID, aMeta, 3);
            } else if (aEventName.equals("oreLead")) {
                GT_ModHandler.addValuableOre(aItem.itemID, aMeta, 3);
                GT_ModHandler.addPulverisationRecipe(new ItemStack(aItem, 1, aMeta), GT_MetaItem_Dust.instance.getStack(23, aDense || aNether ? 4 : 2), GT_OreDictUnificator.get("dustSilver", null, 1), 0, false);
                if (!aDense || aNether) {
                    GT_ModHandler.addOreToIngotSmeltingRecipe(new ItemStack(aItem, 1, aMeta), GT_OreDictUnificator.get("ingotLead", null, 1));
                }
                GT_Mod.addGrinderRecipe(new ItemStack(aItem, 1, aMeta), -1, GT_MetaItem_Dust.instance.getStack(23, aDense || aNether ? 4 : 2), GT_MetaItem_SmallDust.instance.getStack(246, 1), null, GT_ModHandler.getEmptyCell(1));
                GT_Mod.addGrinderRecipe(new ItemStack(aItem, 1, aMeta), GT_MetaItem_Cell.instance.getStack(16, 1), GT_OreDictUnificator.get("dustLead", null, aDense || aNether ? 4 : 2), GT_MetaItem_Dust.instance.getStack(246, 1), null, GT_ModHandler.getEmptyCell(1));
            } else if (aEventName.equals("oreSilverLead") || aEventName.equals("oreGalena")) {
                GT_ModHandler.addPulverisationRecipe(new ItemStack(aItem, 1, aMeta), GT_OreDictUnificator.get("dustGalena", null, aDense || aNether ? 3 : 2), GT_OreDictUnificator.get("dustSulfur", null, 1), aDense || aNether ? 100 : 50, false);
                GT_Mod.addGrinderRecipe(new ItemStack(aItem, 1, aMeta), -1, GT_OreDictUnificator.get("dustGalena", null, aDense || aNether ? 4 : 2), GT_OreDictUnificator.get("dustSulfur", null, aDense || aNether ? 2 : 1), null, GT_ModHandler.getEmptyCell(1));
                GT_Mod.addGrinderRecipe(new ItemStack(aItem, 1, aMeta), GT_MetaItem_Cell.instance.getStack(16, 1), GT_OreDictUnificator.get("dustGalena", null, aDense || aNether ? 4 : 2), GT_OreDictUnificator.get("dustSulfur", null, aDense || aNether ? 2 : 1), GT_OreDictUnificator.get("dustSilver", null, aDense || aNether ? 2 : 1), GT_ModHandler.getEmptyCell(1));
                GT_ModHandler.addValuableOre(aItem.itemID, aMeta, 4);
            } else if (aEventName.equals("oreDiamond")) {
                GT_Mod.addGrinderRecipe(new ItemStack(aItem, 1, aMeta), -1, new ItemStack(Item.diamond, aDense || aNether ? 2 : 1), GT_MetaItem_SmallDust.instance.getStack(36, 6), GT_ModHandler.getIC2Item("hydratedCoalDust", 1), GT_ModHandler.getEmptyCell(1));
                GT_ModHandler.addPulverisationRecipe(new ItemStack(aItem, 1, aMeta), GT_MetaItem_Dust.instance.getStack(36, aDense || aNether ? 3 : 2), GT_OreDictUnificator.get("dustCoal", null, 1), 0, true);
                GT_ModHandler.addValuableOre(aItem.itemID, aMeta, 5);
            } else if (aEventName.equals("oreEmerald")) {
                GT_Mod.addGrinderRecipe(new ItemStack(aItem, 1, aMeta), -1, new ItemStack(Item.emerald, aDense || aNether ? 2 : 1), GT_MetaItem_SmallDust.instance.getStack(35, aDense || aNether ? 12 : 6), GT_MetaItem_SmallDust.instance.getStack(37, 2), GT_ModHandler.getEmptyCell(1));
                GT_ModHandler.addPulverisationRecipe(new ItemStack(aItem, 1, aMeta), GT_MetaItem_Dust.instance.getStack(35, aDense || aNether ? 3 : 2), GT_MetaItem_Dust.instance.getStack(37, 1), 0, true);
                GT_ModHandler.addValuableOre(aItem.itemID, aMeta, 5);
            } else if (aEventName.equals("oreRuby")) {
                GT_ModHandler.addValuableOre(aItem.itemID, aMeta, 4);
                GT_Mod.addGrinderRecipe(new ItemStack(aItem, 1, aMeta), -1, GT_OreDictUnificator.get("gemRuby", null, aDense || aNether ? 2 : 1), GT_MetaItem_SmallDust.instance.getStack(32, aDense || aNether ? 12 : 6), GT_MetaItem_SmallDust.instance.getStack(54, 2), GT_ModHandler.getEmptyCell(1));
                GT_ModHandler.addPulverisationRecipe(new ItemStack(aItem, 1, aMeta), GT_MetaItem_Dust.instance.getStack(32, aDense || aNether ? 3 : 2), GT_MetaItem_Dust.instance.getStack(54, 1), 0, true);
            } else if (aEventName.equals("oreSapphire")) {
                GT_ModHandler.addValuableOre(aItem.itemID, aMeta, 4);
                GT_Mod.addGrinderRecipe(new ItemStack(aItem, 1, aMeta), -1, GT_OreDictUnificator.get("gemSapphire", null, aDense || aNether ? 2 : 1), GT_MetaItem_SmallDust.instance.getStack(33, aDense || aNether ? 12 : 6), GT_MetaItem_SmallDust.instance.getStack(34, 2), GT_ModHandler.getEmptyCell(1));
                GT_ModHandler.addPulverisationRecipe(new ItemStack(aItem, 1, aMeta), GT_MetaItem_Dust.instance.getStack(33, aDense || aNether ? 3 : 2), GT_MetaItem_Dust.instance.getStack(34, 1), 0, true);
            } else if (aEventName.equals("oreGreenSapphire")) {
                GT_ModHandler.addValuableOre(aItem.itemID, aMeta, 4);
                GT_Mod.addGrinderRecipe(new ItemStack(aItem, 1, aMeta), -1, GT_OreDictUnificator.get("gemGreenSapphire", null, aDense || aNether ? 2 : 1), GT_MetaItem_SmallDust.instance.getStack(34, aDense || aNether ? 12 : 6), GT_MetaItem_SmallDust.instance.getStack(33, 2), GT_ModHandler.getEmptyCell(1));
                GT_ModHandler.addPulverisationRecipe(new ItemStack(aItem, 1, aMeta), GT_MetaItem_Dust.instance.getStack(34, aDense || aNether ? 3 : 2), GT_MetaItem_Dust.instance.getStack(33, 1), 0, true);
            } else if (aEventName.equals("oreOlivine")) {
                GT_ModHandler.addValuableOre(aItem.itemID, aMeta, 4);
                GT_Mod.addGrinderRecipe(new ItemStack(aItem, 1, aMeta), -1, GT_MetaItem_Material.instance.getStack(37, aDense || aNether ? 2 : 1), GT_MetaItem_SmallDust.instance.getStack(37, aDense || aNether ? 12 : 6), GT_MetaItem_SmallDust.instance.getStack(35, 2), GT_ModHandler.getEmptyCell(1));
                GT_ModHandler.addPulverisationRecipe(new ItemStack(aItem, 1, aMeta), GT_MetaItem_Dust.instance.getStack(37, aDense || aNether ? 3 : 2), GT_MetaItem_Dust.instance.getStack(35, 1), 0, true);
            } else if (aEventName.equals("oreCoal")) {
                GT_Mod.addGrinderRecipe(new ItemStack(aItem, 1, aMeta), -1, new ItemStack(Item.coal, aDense || aNether ? 2 : 1), GT_OreDictUnificator.get("dustCoal", null, aDense || aNether ? 2 : 1), GT_OreDictUnificator.get("dustSmallThorium", null, 1), GT_ModHandler.getEmptyCell(1));
                GT_ModHandler.addPulverisationRecipe(new ItemStack(aItem, 1, aMeta), GT_OreDictUnificator.get("dustCoal", null, aDense || aNether ? 3 : 2), GT_OreDictUnificator.get("dustThorium", null, 1), 0, true);
                GT_ModHandler.addValuableOre(aItem.itemID, aMeta, 1);
            } else if (aEventName.equals("oreCopper")) {
                GT_ModHandler.addValuableOre(aItem.itemID, aMeta, 3);
                GT_ModHandler.addPulverisationRecipe(new ItemStack(aItem, 1, aMeta), GT_OreDictUnificator.get("dustCopper", null, aDense || aNether ? 3 : 2), GT_OreDictUnificator.get("dustGold", null, 1), 0, false);
                if (!aDense || aNether) {
                    GT_ModHandler.addOreToIngotSmeltingRecipe(new ItemStack(aItem, 1, aMeta), GT_OreDictUnificator.get("ingotCopper", null, 1));
                }
                GT_Mod.addGrinderRecipe(new ItemStack(aItem, 1, aMeta), -1, GT_OreDictUnificator.get("dustCopper", null, aDense || aNether ? 4 : 2), GT_MetaItem_SmallDust.instance.getStack(242, 1), GT_MetaItem_SmallDust.instance.getStack(28, 1), GT_ModHandler.getEmptyCell(1));
                GT_Mod.addGrinderRecipe(new ItemStack(aItem, 1, aMeta), GT_MetaItem_Cell.instance.getStack(32, 1), GT_OreDictUnificator.get("dustCopper", null, aDense || aNether ? 5 : 3), GT_MetaItem_SmallDust.instance.getStack(242, 1), GT_MetaItem_SmallDust.instance.getStack(28, 1), GT_ModHandler.getEmptyCell(1));
                GT_Mod.addGrinderRecipe(new ItemStack(aItem, 1, aMeta), GT_MetaItem_Cell.instance.getStack(16, 1), GT_OreDictUnificator.get("dustCopper", null, aDense || aNether ? 4 : 2), GT_OreDictUnificator.get("dustGold", null, 1), null, GT_ModHandler.getEmptyCell(1));
            } else if (aEventName.equals("oreTin")) {
                GT_ModHandler.addValuableOre(aItem.itemID, aMeta, 3);
                GT_ModHandler.addPulverisationRecipe(new ItemStack(aItem, 1, aMeta), GT_OreDictUnificator.get("dustTin", null, aDense || aNether ? 3 : 2), GT_OreDictUnificator.get("dustIron", null, 1), 0, false);
                if (!aDense || aNether) {
                    GT_ModHandler.addOreToIngotSmeltingRecipe(new ItemStack(aItem, 1, aMeta), GT_OreDictUnificator.get("ingotTin", null, 1));
                }
                GT_Mod.addGrinderRecipe(new ItemStack(aItem, 1, aMeta), -1, GT_OreDictUnificator.get("dustTin", null, aDense || aNether ? 4 : 2), GT_MetaItem_SmallDust.instance.getStack(241, 1), GT_MetaItem_SmallDust.instance.getStack(24, 1), GT_ModHandler.getEmptyCell(1));
                GT_Mod.addGrinderRecipe(new ItemStack(aItem, 1, aMeta), GT_MetaItem_Cell.instance.getStack(32, 1), GT_OreDictUnificator.get("dustTin", null, aDense || aNether ? 4 : 2), GT_MetaItem_SmallDust.instance.getStack(241, 1), GT_MetaItem_Dust.instance.getStack(24, 1), GT_ModHandler.getEmptyCell(1));
            } else if (aEventName.equals("oreZinc")) {
                GT_ModHandler.addValuableOre(aItem.itemID, aMeta, 2);
                GT_Mod.addGrinderRecipe(new ItemStack(aItem, 1, aMeta), -1, GT_MetaItem_Dust.instance.getStack(24, aDense || aNether ? 4 : 2), GT_MetaItem_SmallDust.instance.getStack(244, 2), null, GT_ModHandler.getEmptyCell(1));
                GT_Mod.addGrinderRecipe(new ItemStack(aItem, 1, aMeta), GT_MetaItem_Cell.instance.getStack(32, 1), GT_MetaItem_Dust.instance.getStack(24, aDense || aNether ? 6 : 3), GT_MetaItem_SmallDust.instance.getStack(244, 2), null, GT_ModHandler.getEmptyCell(1));
                if (!aDense || aNether) {
                    GT_ModHandler.addOreToIngotSmeltingRecipe(new ItemStack(aItem, 1, aMeta), GT_MetaItem_Material.instance.getStack(24, 1));
                }
                GT_ModHandler.addPulverisationRecipe(new ItemStack(aItem, 1, aMeta), GT_MetaItem_Dust.instance.getStack(24, aDense || aNether ? 4 : 2), GT_OreDictUnificator.get("dustTin", null, 1), 0, false);
            } else if (aEventName.equals("oreCassiterite") || aEventName.equals("oreCasserite")) {
                GT_ModHandler.addValuableOre(aItem.itemID, aMeta, 3);
                GT_ModHandler.addPulverisationRecipe(new ItemStack(aItem, 1, aMeta), GT_OreDictUnificator.get("dustTin", null, aDense || aNether ? 3 : 2), GT_OreDictUnificator.get("dustTin", null, 1), 0, false);
                if (!aDense || aNether) {
                    GT_ModHandler.addOreToIngotSmeltingRecipe(new ItemStack(aItem, 1, aMeta), GT_OreDictUnificator.get("ingotTin", null, 1));
                }
                GT_Mod.addGrinderRecipe(new ItemStack(aItem, 1, aMeta), -1, GT_OreDictUnificator.get("dustTin", null, aDense || aNether ? 6 : 3), null, null, GT_ModHandler.getEmptyCell(1));
            } else if (aEventName.equals("oreIridium")) {
                GT_ModHandler.addValuableOre(aItem.itemID, aMeta, 10);
                GT_Mod.addGrinderRecipe(new ItemStack(aItem, 1, aMeta), -1, GT_ModHandler.getIC2Item("iridiumOre", aDense || aNether ? 3 : 2), GT_MetaItem_SmallDust.instance.getStack(27, 2), null, GT_ModHandler.getEmptyCell(1));
                GT_Mod.addGrinderRecipe(new ItemStack(aItem, 1, aMeta), GT_MetaItem_Cell.instance.getStack(16, 1), GT_ModHandler.getIC2Item("iridiumOre", aDense || aNether ? 3 : 2), GT_MetaItem_Dust.instance.getStack(27, 1), null, GT_ModHandler.getEmptyCell(1));
                GT_ModHandler.addPulverisationRecipe(new ItemStack(aItem, 1, aMeta), GT_ModHandler.getIC2Item("iridiumOre", aDense || aNether ? 3 : 2), GT_MetaItem_Dust.instance.getStack(27, 1), 0, true);
            } else if (aEventName.equals("oreCooperite") || aEventName.equals("oreSheldonite")) {
                GT_ModHandler.addValuableOre(aItem.itemID, aMeta, 10);
                GT_Mod.addGrinderRecipe(new ItemStack(aItem, 1, aMeta), -1, GT_MetaItem_Dust.instance.getStack(27, aDense || aNether ? 4 : 2), GT_MetaItem_Dust.instance.getStack(28, 1), GT_OreDictUnificator.get("nuggetIridium", null, 2), GT_ModHandler.getEmptyCell(1));
                GT_Mod.addGrinderRecipe(new ItemStack(aItem, 1, aMeta), GT_MetaItem_Cell.instance.getStack(16, 1), GT_MetaItem_Dust.instance.getStack(27, aDense || aNether ? 6 : 3), GT_MetaItem_Dust.instance.getStack(28, 1), GT_OreDictUnificator.get("nuggetIridium", null, 2), GT_ModHandler.getEmptyCell(1));
                if (!aDense || aNether) {
                    GT_ModHandler.addOreToIngotSmeltingRecipe(new ItemStack(aItem, 1, aMeta), GT_MetaItem_Material.instance.getStack(27, 1));
                }
                GT_ModHandler.addPulverisationRecipe(new ItemStack(aItem, 1, aMeta), GT_MetaItem_Dust.instance.getStack(27, aDense || aNether ? 4 : 2), GT_ModHandler.getIC2Item("iridiumOre", 1), 0, true);
            } else if (aEventName.equals("orePlatinum")) {
                GT_ModHandler.addValuableOre(aItem.itemID, aMeta, 7);
                GT_Mod.addGrinderRecipe(new ItemStack(aItem, 1, aMeta), -1, GT_MetaItem_Dust.instance.getStack(27, aDense || aNether ? 4 : 2), GT_OreDictUnificator.get("nuggetIridium", null, 2), GT_MetaItem_SmallDust.instance.getStack(28, 1), GT_ModHandler.getEmptyCell(1));
                GT_Mod.addGrinderRecipe(new ItemStack(aItem, 1, aMeta), GT_MetaItem_Cell.instance.getStack(16, 1), GT_MetaItem_Dust.instance.getStack(27, aDense || aNether ? 6 : 3), GT_OreDictUnificator.get("nuggetIridium", null, 2), GT_MetaItem_SmallDust.instance.getStack(28, 1), GT_ModHandler.getEmptyCell(1));
                if (!aDense || aNether) {
                    GT_ModHandler.addOreToIngotSmeltingRecipe(new ItemStack(aItem, 1, aMeta), GT_MetaItem_Material.instance.getStack(27, 1));
                }
                GT_ModHandler.addPulverisationRecipe(new ItemStack(aItem, 1, aMeta), GT_MetaItem_Dust.instance.getStack(27, aDense || aNether ? 4 : 2), GT_ModHandler.getIC2Item("iridiumOre", 1), 0, false);
            } else if (aEventName.equals("oreNickel")) {
                GT_ModHandler.addValuableOre(aItem.itemID, aMeta, 4);
                GT_Mod.addGrinderRecipe(new ItemStack(aItem, 1, aMeta), -1, GT_MetaItem_Dust.instance.getStack(28, aDense || aNether ? 6 : 3), GT_MetaItem_SmallDust.instance.getStack(27, 1), GT_MetaItem_SmallDust.instance.getStack(243, 1), GT_ModHandler.getEmptyCell(1));
                GT_Mod.addGrinderRecipe(new ItemStack(aItem, 1, aMeta), GT_MetaItem_Cell.instance.getStack(16, 1), GT_MetaItem_Dust.instance.getStack(28, aDense || aNether ? 6 : 3), GT_MetaItem_Dust.instance.getStack(27, 1), null, GT_ModHandler.getEmptyCell(1));
                if (!aDense || aNether) {
                    GT_ModHandler.addOreToIngotSmeltingRecipe(new ItemStack(aItem, 1, aMeta), GT_MetaItem_Material.instance.getStack(28, 1));
                }
                GT_ModHandler.addPulverisationRecipe(new ItemStack(aItem, 1, aMeta), GT_MetaItem_Dust.instance.getStack(28, aDense || aNether ? 4 : 2), GT_OreDictUnificator.get("dustTin", null, 1), 0, false);
            } else if (aEventName.equals("orePyrite")) {
                GT_ModHandler.addValuableOre(aItem.itemID, aMeta, 1);
                GT_Mod.addGrinderRecipe(new ItemStack(aItem, 1, aMeta), -1, GT_MetaItem_Dust.instance.getStack(3, 5), GT_MetaItem_Dust.instance.getStack(8, 2), null, GT_ModHandler.getEmptyCell(1));
                GT_Mod.addBlastRecipe(new ItemStack(aItem, 1, aMeta), GT_MetaItem_Cell.instance.getStack(33, 1), GT_OreDictUnificator.get("ingotRefinedIron", null, 2), GT_ModHandler.getEmptyCell(1), 100, 128, 1500);
                GT_ModHandler.addInductionSmelterRecipe(new ItemStack(aItem, 1, aMeta), new ItemStack(Block.sand, 1), new ItemStack(Item.ingotIron, 1), GT_ModHandler.getTEItem("slagRich", 1), 300, 10);
                GT_ModHandler.addInductionSmelterRecipe(new ItemStack(aItem, 1, aMeta), GT_ModHandler.getTEItem("slagRich", 1), new ItemStack(Item.ingotIron, 2), GT_ModHandler.getTEItem("slag", 1), 300, 95);
                GT_ModHandler.addOreToIngotSmeltingRecipe(new ItemStack(aItem, 1, aMeta), new ItemStack(Item.ingotIron, 1));
                GT_ModHandler.addPulverisationRecipe(new ItemStack(aItem, 1, aMeta), GT_MetaItem_Dust.instance.getStack(3, 5), GT_OreDictUnificator.get("dustIron", null, 1), 0, true);
            } else if (aEventName.equals("oreCinnabar")) {
                GT_ModHandler.addValuableOre(aItem.itemID, aMeta, 3);
                GT_Mod.addGrinderRecipe(new ItemStack(aItem, 1, aMeta), -1, GT_MetaItem_Dust.instance.getStack(11, 5), GT_MetaItem_SmallDust.instance.getStack(249, 2), GT_MetaItem_SmallDust.instance.getStack(250, 1), GT_ModHandler.getEmptyCell(1));
                GT_ModHandler.addPulverisationRecipe(new ItemStack(aItem, 1, aMeta), GT_MetaItem_Dust.instance.getStack(11, 3), new ItemStack(Item.redstone, 1), 0, true);
            } else if (aEventName.equals("oreSphalerite")) {
                GT_ModHandler.addValuableOre(aItem.itemID, aMeta, 2);
                GT_Mod.addGrinderRecipe(new ItemStack(aItem, 1, aMeta), -1, GT_MetaItem_Dust.instance.getStack(14, 5), GT_MetaItem_Dust.instance.getStack(24, 1), GT_MetaItem_SmallDust.instance.getStack(55, 1), GT_ModHandler.getEmptyCell(1));
                GT_Mod.addGrinderRecipe(new ItemStack(aItem, 1, aMeta), GT_MetaItem_Cell.instance.getStack(32, 1), GT_MetaItem_Dust.instance.getStack(14, 5), GT_MetaItem_Dust.instance.getStack(24, 3), GT_MetaItem_SmallDust.instance.getStack(55, 1), GT_ModHandler.getEmptyCell(1));
                GT_ModHandler.addPulverisationRecipe(new ItemStack(aItem, 1, aMeta), GT_MetaItem_Dust.instance.getStack(14, 4), GT_MetaItem_Dust.instance.getStack(24, 1), 0, true);
            } else if (aEventName.equals("oreAluminium") || aEventName.equals("oreAluminum")) {
                GT_ModHandler.addValuableOre(aItem.itemID, aMeta, 2);
                GT_Mod.addGrinderRecipe(new ItemStack(aItem, 1, aMeta), -1, GT_MetaItem_Dust.instance.getStack(18, aDense || aNether ? 6 : 3), GT_MetaItem_SmallDust.instance.getStack(19, 1), null, GT_ModHandler.getEmptyCell(1));
                if (!aDense || aNether) {
                    GT_ModHandler.addOreToIngotSmeltingRecipe(new ItemStack(aItem, 1, aMeta), GT_Mod.instance.mAluminiumBlastFurnace ? GT_OreDictUnificator.get("dustAluminium", null, 1) : GT_OreDictUnificator.get("ingotAluminium", null, 1));
                }
                GT_ModHandler.addPulverisationRecipe(new ItemStack(aItem, 1, aMeta), GT_MetaItem_Dust.instance.getStack(18, aDense || aNether ? 4 : 2), GT_MetaItem_Dust.instance.getStack(19, 1), 0, true);
            } else if (!aEventName.equals("oreNaturalAluminium") && !aEventName.equals("oreNaturalAluminum")) {
                if (aEventName.equals("oreSteel")) {
                    GT_ModHandler.addValuableOre(aItem.itemID, aMeta, 4);
                    GT_Mod.addGrinderRecipe(new ItemStack(aItem, 1, aMeta), -1, GT_OreDictUnificator.get("dustSteel", null, aDense || aNether ? 4 : 2), GT_OreDictUnificator.get("dustSmallNickel", null, 2), null, GT_ModHandler.getEmptyCell(1));
                    if (!aDense || aNether) {
                        GT_ModHandler.addOreToIngotSmeltingRecipe(new ItemStack(aItem, 1, aMeta), GT_Mod.instance.mTitaniumBlastFurnace ? GT_OreDictUnificator.get("dustSteel", null, 1) : GT_OreDictUnificator.get("ingotSteel", null, 1));
                    }
                    GT_ModHandler.addPulverisationRecipe(new ItemStack(aItem, 1, aMeta), GT_OreDictUnificator.get("dustSteel", null, aDense || aNether ? 4 : 2), GT_OreDictUnificator.get("dustNickel", null, 2), 0, true);
                } else if (aEventName.equals("oreTitan") || aEventName.equals("oreTitanium")) {
                    GT_ModHandler.addValuableOre(aItem.itemID, aMeta, 5);
                    GT_Mod.addGrinderRecipe(new ItemStack(aItem, 1, aMeta), -1, GT_MetaItem_Dust.instance.getStack(19, aDense || aNether ? 4 : 2), GT_MetaItem_SmallDust.instance.getStack(18, 2), null, GT_ModHandler.getEmptyCell(1));
                    if (!aDense || aNether) {
                        GT_ModHandler.addOreToIngotSmeltingRecipe(new ItemStack(aItem, 1, aMeta), GT_Mod.instance.mTitaniumBlastFurnace ? GT_OreDictUnificator.get("dustTitanium", null, 1) : GT_OreDictUnificator.get("ingotTitanium", null, 1));
                    }
                    GT_ModHandler.addPulverisationRecipe(new ItemStack(aItem, 1, aMeta), GT_MetaItem_Dust.instance.getStack(19, aDense || aNether ? 4 : 2), GT_MetaItem_Dust.instance.getStack(18, 1), 0, true);
                } else if (aEventName.equals("oreChrome") || aEventName.equals("oreChromium")) {
                    GT_ModHandler.addValuableOre(aItem.itemID, aMeta, 10);
                    GT_Mod.addGrinderRecipe(new ItemStack(aItem, 1, aMeta), -1, GT_MetaItem_Dust.instance.getStack(20, aDense || aNether ? 4 : 2), GT_MetaItem_SmallDust.instance.getStack(32, 1), null, GT_ModHandler.getEmptyCell(1));
                    if (!aDense || aNether) {
                        GT_ModHandler.addOreToIngotSmeltingRecipe(new ItemStack(aItem, 1, aMeta), GT_Mod.instance.mChromeBlastFurnace ? GT_OreDictUnificator.get("dustChrome", null, 1) : GT_OreDictUnificator.get("ingotChrome", null, 1));
                    }
                    GT_ModHandler.addPulverisationRecipe(new ItemStack(aItem, 1, aMeta), GT_MetaItem_Dust.instance.getStack(20, aDense || aNether ? 4 : 2), GT_MetaItem_Dust.instance.getStack(32, 1), 0, true);
                } else if (aEventName.equals("oreElectrum")) {
                    GT_ModHandler.addValuableOre(aItem.itemID, aMeta, 5);
                    GT_Mod.addGrinderRecipe(new ItemStack(aItem, 1, aMeta), -1, GT_MetaItem_Dust.instance.getStack(21, aDense || aNether ? 4 : 2), GT_MetaItem_SmallDust.instance.getStack(242, 1), GT_MetaItem_SmallDust.instance.getStack(246, 1), GT_ModHandler.getEmptyCell(1));
                    if (!aDense || aNether) {
                        GT_ModHandler.addOreToIngotSmeltingRecipe(new ItemStack(aItem, 1, aMeta), GT_MetaItem_Material.instance.getStack(21, 1));
                    }
                    GT_ModHandler.addPulverisationRecipe(new ItemStack(aItem, 1, aMeta), GT_MetaItem_Material.instance.getStack(21, aDense || aNether ? 4 : 2), GT_OreDictUnificator.get("dustGold", null, 1), 0, false);
                } else if (aEventName.equals("oreTungsten") || aEventName.equals("oreTungstate")) {
                    GT_ModHandler.addValuableOre(aItem.itemID, aMeta, 4);
                    GT_ModHandler.addPulverisationRecipe(new ItemStack(aItem, 1, aMeta), GT_MetaItem_Dust.instance.getStack(22, aDense || aNether ? 4 : 2), GT_MetaItem_Dust.instance.getStack(12, 1), 0, true);
                    if (!aDense || aNether) {
                        GT_ModHandler.addOreToIngotSmeltingRecipe(new ItemStack(aItem, 1, aMeta), GT_Mod.instance.mTungstenBlastFurnace ? GT_OreDictUnificator.get("dustTungsten", null, 1) : GT_OreDictUnificator.get("ingotTungsten", null, 1));
                    }
                    GT_Mod.addGrinderRecipe(new ItemStack(aItem, 1, aMeta), -1, GT_MetaItem_Dust.instance.getStack(22, aDense || aNether ? 4 : 2), GT_MetaItem_SmallDust.instance.getStack(241, 3), GT_MetaItem_SmallDust.instance.getStack(12, 3), GT_ModHandler.getEmptyCell(1));
                } else if (aEventName.equals("oreBauxite")) {
                    GT_ModHandler.addValuableOre(aItem.itemID, aMeta, 2);
                    GT_Mod.addGrinderRecipe(new ItemStack(aItem, 1, aMeta), -1, GT_MetaItem_Dust.instance.getStack(17, aDense || aNether ? 8 : 4), GT_MetaItem_Dust.instance.getStack(18, aDense || aNether ? 2 : 1), null, GT_ModHandler.getEmptyCell(1));
                    GT_ModHandler.addPulverisationRecipe(new ItemStack(aItem, 1, aMeta), GT_MetaItem_Dust.instance.getStack(17, aDense || aNether ? 8 : 4), GT_MetaItem_Dust.instance.getStack(18, 1), 0, true);
                } else if (aEventName.equals("oreApatite")) {
                    GT_ModHandler.addValuableOre(aItem.itemID, aMeta, 1);
                    GT_ModHandler.addPulverisationRecipe(new ItemStack(aItem, 1, aMeta), GT_OreDictUnificator.getFirstOre("gemApatite", aDense || aNether ? 16 : 8), GT_OreDictUnificator.get("dustPhosphorus", null, 1), 0, false);
                    GT_Mod.addGrinderRecipe(new ItemStack(aItem, 1, aMeta), -1, GT_OreDictUnificator.getFirstOre("gemApatite", aDense || aNether ? 24 : 12), GT_OreDictUnificator.get("dustPhosphorus", null, 1), null, GT_ModHandler.getEmptyCell(1));
                } else if (aEventName.equals("oreSulfur")) {
                    GT_ModHandler.addValuableOre(aItem.itemID, aMeta, 1);
                    GT_ModHandler.addSmeltingRecipe(new ItemStack(aItem, 1, aMeta), GT_OreDictUnificator.get("dustSulfur", null, 3));
                    GT_ModHandler.addPulverisationRecipe(new ItemStack(aItem, 1, aMeta), GT_MetaItem_Dust.instance.getStack(8, aDense || aNether ? 16 : 8), GT_OreDictUnificator.get("dustSulfur", null, 1), 0, false);
                    GT_Mod.addGrinderRecipe(new ItemStack(aItem, 1, aMeta), -1, GT_MetaItem_Dust.instance.getStack(8, aDense || aNether ? 20 : 10), null, null, GT_ModHandler.getEmptyCell(1));
                } else if (aEventName.equals("oreSaltpeter")) {
                    GT_ModHandler.addValuableOre(aItem.itemID, aMeta, 2);
                    GT_ModHandler.addSmeltingRecipe(new ItemStack(aItem, 1, aMeta), GT_OreDictUnificator.get("dustSaltpeter", null, 3));
                    GT_ModHandler.addPulverisationRecipe(new ItemStack(aItem, 1, aMeta), GT_MetaItem_Dust.instance.getStack(9, aDense || aNether ? 10 : 5), GT_OreDictUnificator.get("dustSaltpeter", null, 1), 0, false);
                    GT_Mod.addGrinderRecipe(new ItemStack(aItem, 1, aMeta), -1, GT_MetaItem_Dust.instance.getStack(9, aDense || aNether ? 14 : 7), null, null, GT_ModHandler.getEmptyCell(1));
                } else if (aEventName.equals("orePhosphorite")) {
                    GT_ModHandler.addSmeltingRecipe(new ItemStack(aItem, 1, aMeta), GT_OreDictUnificator.get("dustPhosphorus", null, 2));
                    GT_ModHandler.addPulverisationRecipe(new ItemStack(aItem, 1, aMeta), GT_OreDictUnificator.get("dustPhosphorus", null, aDense || aNether ? 6 : 3), GT_OreDictUnificator.get("dustPhosphorus", null, 1), 0, false);
                    GT_ModHandler.addValuableOre(aItem.itemID, aMeta, 2);
                } else if (aEventName.equals("oreMagnesium")) {
                    GT_Mod.addGrinderRecipe(new ItemStack(aItem, 1, aMeta), -1, GT_MetaItem_Dust.instance.getStack(13, aDense || aNether ? 4 : 2), GT_MetaItem_SmallDust.instance.getStack(241, 2), GT_MetaItem_SmallDust.instance.getStack(28, 1), GT_ModHandler.getEmptyCell(1));
                    GT_ModHandler.addValuableOre(aItem.itemID, aMeta, 2);
                    GT_ModHandler.addPulverisationRecipe(new ItemStack(aItem, 1, aMeta), GT_MetaItem_Dust.instance.getStack(13, aDense || aNether ? 4 : 2), GT_MetaItem_Dust.instance.getStack(28, 1), 0, false);
                } else if (aEventName.equals("oreManganese")) {
                    GT_Mod.addGrinderRecipe(new ItemStack(aItem, 1, aMeta), -1, GT_MetaItem_Dust.instance.getStack(12, aDense || aNether ? 4 : 2), GT_MetaItem_SmallDust.instance.getStack(241, 2), GT_MetaItem_SmallDust.instance.getStack(28, 1), GT_ModHandler.getEmptyCell(1));
                    GT_ModHandler.addValuableOre(aItem.itemID, aMeta, 2);
                    GT_ModHandler.addPulverisationRecipe(new ItemStack(aItem, 1, aMeta), GT_MetaItem_Dust.instance.getStack(12, aDense || aNether ? 4 : 2), GT_MetaItem_Dust.instance.getStack(28, 1), 0, false);
                } else if (aEventName.equals("oreMonazit")) {
                    GT_Mod.addGrinderRecipe(new ItemStack(aItem, 1, aMeta), -1, new ItemStack(GT_ModHandler.mForcicium.getItem(), aDense || aNether ? 20 : 10, GT_ModHandler.mForcicium.getItemDamage()), GT_OreDictUnificator.get("dustThorium", null, 2), null, GT_ModHandler.getEmptyCell(1));
                    GT_ModHandler.addValuableOre(aItem.itemID, aMeta, 3);
                } else if (aEventName.equals("oreThorium")) {
                    GT_ModHandler.addPulverisationRecipe(new ItemStack(aItem, 1, aMeta), GT_OreDictUnificator.get("dustThorium", null, aDense || aNether ? 4 : 2), GT_OreDictUnificator.get("dustUranium", null, 1), 0, true);
                    GT_Mod.addGrinderRecipe(new ItemStack(aItem, 1, aMeta), -1, GT_OreDictUnificator.get("dustThorium", null, aDense || aNether ? 4 : 2), GT_OreDictUnificator.get("dustSmallUranium", null, 1), null, GT_ModHandler.getEmptyCell(1));
                    GT_ModHandler.addValuableOre(aItem.itemID, aMeta, 5);
                } else if (aEventName.equals("orePlutonium")) {
                    GT_ModHandler.addPulverisationRecipe(new ItemStack(aItem, 1, aMeta), GT_OreDictUnificator.get("dustPlutonium", null, aDense || aNether ? 4 : 2), GT_OreDictUnificator.get("dustUranium", null, 1), 0, true);
                    GT_Mod.addGrinderRecipe(new ItemStack(aItem, 1, aMeta), -1, GT_OreDictUnificator.get("dustPlutonium", null, aDense || aNether ? 4 : 2), GT_OreDictUnificator.get("dustUranium", null, 1), null, GT_ModHandler.getEmptyCell(1));
                    GT_ModHandler.addValuableOre(aItem.itemID, aMeta, 15);
                } else if (aEventName.equals("oreOsmium")) {
                    GT_ModHandler.addValuableOre(aItem.itemID, aMeta, 20);
                } else if (aEventName.equals("oreEximite")) {
                    GT_ModHandler.addValuableOre(aItem.itemID, aMeta, 3);
                } else if (aEventName.equals("oreMeutoite")) {
                    GT_ModHandler.addValuableOre(aItem.itemID, aMeta, 3);
                } else if (aEventName.equals("orePrometheum")) {
                    GT_ModHandler.addValuableOre(aItem.itemID, aMeta, 3);
                } else if (aEventName.equals("oreDeep Iron")) {
                    GT_ModHandler.addValuableOre(aItem.itemID, aMeta, 2);
                } else if (aEventName.equals("oreDeepIron")) {
                    GT_ModHandler.addValuableOre(aItem.itemID, aMeta, 2);
                } else if (aEventName.equals("oreInfuscolium")) {
                    GT_ModHandler.addValuableOre(aItem.itemID, aMeta, 3);
                } else if (aEventName.equals("oreOureclase")) {
                    GT_ModHandler.addValuableOre(aItem.itemID, aMeta, 3);
                } else if (aEventName.equals("oreAredrite")) {
                    GT_ModHandler.addValuableOre(aItem.itemID, aMeta, 3);
                } else if (aEventName.equals("oreAstral Silver")) {
                    GT_ModHandler.addValuableOre(aItem.itemID, aMeta, 4);
                } else if (aEventName.equals("oreAstralSilver")) {
                    GT_ModHandler.addValuableOre(aItem.itemID, aMeta, 4);
                } else if (aEventName.equals("oreCarmot")) {
                    GT_ModHandler.addValuableOre(aItem.itemID, aMeta, 4);
                } else if (aEventName.equals("oreMithril")) {
                    GT_ModHandler.addValuableOre(aItem.itemID, aMeta, 4);
                } else if (aEventName.equals("oreRubracium")) {
                    GT_ModHandler.addValuableOre(aItem.itemID, aMeta, 3);
                } else if (aEventName.equals("oreOrichalcum")) {
                    GT_ModHandler.addValuableOre(aItem.itemID, aMeta, 3);
                } else if (aEventName.equals("oreAdamantine")) {
                    GT_ModHandler.addValuableOre(aItem.itemID, aMeta, 5);
                } else if (aEventName.equals("oreAtlarus")) {
                    GT_ModHandler.addValuableOre(aItem.itemID, aMeta, 3);
                } else if (aEventName.equals("oreIgnatius")) {
                    GT_ModHandler.addValuableOre(aItem.itemID, aMeta, 3);
                } else if (aEventName.equals("oreShadow Iron")) {
                    GT_ModHandler.addValuableOre(aItem.itemID, aMeta, 4);
                } else if (aEventName.equals("oreShadowIron")) {
                    GT_ModHandler.addValuableOre(aItem.itemID, aMeta, 4);
                } else if (aEventName.equals("oreMidasium")) {
                    GT_ModHandler.addValuableOre(aItem.itemID, aMeta, 3);
                } else if (aEventName.equals("oreVyroxeres")) {
                    GT_ModHandler.addValuableOre(aItem.itemID, aMeta, 3);
                } else if (aEventName.equals("oreCeruclase")) {
                    GT_ModHandler.addValuableOre(aItem.itemID, aMeta, 3);
                } else if (aEventName.equals("oreKalendrite")) {
                    GT_ModHandler.addValuableOre(aItem.itemID, aMeta, 3);
                } else if (aEventName.equals("oreVulcanite")) {
                    GT_ModHandler.addValuableOre(aItem.itemID, aMeta, 3);
                } else if (aEventName.equals("oreSanguinite")) {
                    GT_ModHandler.addValuableOre(aItem.itemID, aMeta, 3);
                } else if (aEventName.equals("oreLemurite")) {
                    GT_ModHandler.addValuableOre(aItem.itemID, aMeta, 3);
                } else if (aEventName.equals("oreAdluorite")) {
                    GT_ModHandler.addValuableOre(aItem.itemID, aMeta, 3);
                } else if (aEventName.equals("oreBitumen")) {
                    GT_ModHandler.addValuableOre(aItem.itemID, aMeta, 2);
                } else if (aEventName.equals("orePotash")) {
                    GT_ModHandler.addValuableOre(aItem.itemID, aMeta, 2);
                } else if (aEventName.equals("oreQuartz")) {
                    GT_ModHandler.addValuableOre(aItem.itemID, aMeta, 2);
                } else if (aEventName.equals("oreUranium")) {
                    GT_ModHandler.addValuableOre(aItem.itemID, aMeta, 5);
                    GT_ModHandler.addPulverisationRecipe(new ItemStack(aItem, 1, aMeta), GT_MetaItem_Dust.instance.getStack(16, aDense || aNether ? 4 : 2), GT_OreDictUnificator.get("dustPlutonium", null, 1), 0, false);
                    GT_Mod.addGrinderRecipe(new ItemStack(aItem, 1, aMeta), -1, GT_MetaItem_Dust.instance.getStack(16, aDense || aNether ? 4 : 2), GT_OreDictUnificator.get("dustSmallPlutonium", null, 2), null, GT_ModHandler.getEmptyCell(1));
                } else {
                    GT_ModHandler.addValuableOre(aItem.itemID, aMeta, 1);
                    System.out.println("Ore Name: " + aEvent.Name + " !!!Unknown Ore detected!!! Please report to GregTech Intergalactical for additional compatiblity. This is not an Error, it's just an Information. This Ore will still get added to the List of the IC2-Miner, but with a low Value.");
                }
            }
        } else if (aEventName.startsWith("dust")) {
            if (aEventName.startsWith("dustSmall")) {
                GT_ModHandler.addShapelessCraftingRecipe(GT_OreDictUnificator.getFirstOre(aEventName.replaceFirst("dustSmall", "dust"), 1), aEventName, aEventName, aEventName, aEventName);
                GT_ModHandler.addShapelessCraftingRecipe(GT_OreDictUnificator.getFirstOre(aEventName, 4), aEventName.equals("dustSmallGunpowder") ? new ItemStack(Item.gunpowder, 1, 0) : aEventName.replaceFirst("dustSmall", "dust"));
            } else if (!aEventName.startsWith("dustDirty")) {
                if (aEventName.equals("dustWood")) {
                    GT_ModHandler.addCompressionRecipe(new ItemStack(aItem, 8, aMeta), GT_MetaItem_Material.instance.getStack(15, 1));
                } else if (aEventName.equals("dustSteel")) {
                    GT_Mod.addBlastRecipe(new ItemStack(aItem, 1, aMeta), null, GT_MetaItem_Material.instance.getStack(26, 1), null, 100, 128, 1000);
                    GT_ModHandler.addRCBlastFurnaceRecipe(new ItemStack(aItem, 1, aMeta), GT_MetaItem_Material.instance.getStack(26, 1), 1000);
                    if (GT_Mod.instance.mSteelBlastFurnace) {
                        if (FurnaceRecipes.smelting().getSmeltingResult(new ItemStack(aItem, 1, aMeta)) != null) {
                            GT_ModHandler.addSmeltingRecipe(new ItemStack(aItem, 1, aMeta), GT_OreDictUnificator.get(new ItemStack(aItem, 1, aMeta)));
                        }
                    } else {
                        GT_ModHandler.addDustToIngotSmeltingRecipe(new ItemStack(aItem, 1, aMeta), GT_OreDictUnificator.get("ingotSteel", null, 1));
                    }
                } else if (aEventName.equals("dustChrome")) {
                    GT_Mod.addBlastRecipe(new ItemStack(aItem, 1, aMeta), null, GT_MetaItem_Material.instance.getStack(20, 1), null, 800, 128, 1700);
                    if (GT_Mod.instance.mChromeBlastFurnace) {
                        if (FurnaceRecipes.smelting().getSmeltingResult(new ItemStack(aItem, 1, aMeta)) != null) {
                            GT_ModHandler.addSmeltingRecipe(new ItemStack(aItem, 1, aMeta), GT_OreDictUnificator.get(new ItemStack(aItem, 1, aMeta)));
                        }
                    } else {
                        GT_ModHandler.addDustToIngotSmeltingRecipe(new ItemStack(aItem, 1, aMeta), GT_OreDictUnificator.get("ingotChrome", null, 1));
                    }
                } else if (aEventName.equals("dustTitanium")) {
                    GT_Mod.addBlastRecipe(new ItemStack(aItem, 1, aMeta), null, GT_MetaItem_Material.instance.getStack(19, 1), null, 1000, 128, 1500);
                    if (GT_Mod.instance.mTitaniumBlastFurnace) {
                        if (FurnaceRecipes.smelting().getSmeltingResult(new ItemStack(aItem, 1, aMeta)) != null) {
                            GT_ModHandler.addSmeltingRecipe(new ItemStack(aItem, 1, aMeta), GT_OreDictUnificator.get(new ItemStack(aItem, 1, aMeta)));
                        }
                    } else {
                        GT_ModHandler.addDustToIngotSmeltingRecipe(new ItemStack(aItem, 1, aMeta), GT_OreDictUnificator.get("ingotTitanium", null, 1));
                    }
                } else if (aEventName.equals("dustAluminium") || aEventName.equals("dustAluminum")) {
                    GT_Mod.addBlastRecipe(new ItemStack(aItem, 1, aMeta), null, GT_MetaItem_Material.instance.getStack(18, 1), null, 200, 128, 1700);
                    GT_ModHandler.addRCBlastFurnaceRecipe(new ItemStack(aItem, 1, aMeta), GT_MetaItem_Material.instance.getStack(18, 1), 1000);
                    if (GT_Mod.instance.mAluminiumBlastFurnace) {
                        if (FurnaceRecipes.smelting().getSmeltingResult(new ItemStack(aItem, 1, aMeta)) != null) {
                            GT_ModHandler.addSmeltingRecipe(new ItemStack(aItem, 1, aMeta), GT_OreDictUnificator.get(new ItemStack(aItem, 1, aMeta)));
                        }
                    } else {
                        GT_ModHandler.addDustToIngotSmeltingRecipe(new ItemStack(aItem, 1, aMeta), GT_OreDictUnificator.get("ingotAluminium", null, 1));
                    }
                } else if (aEventName.equals("dustSaltpeter")) {
                    GT_Mod.addElectrolyzerRecipe(new ItemStack(aItem, 10, aMeta), 7, GT_MetaItem_Cell.instance.getStack(14, 2), GT_MetaItem_Cell.instance.getStack(15, 2), null, GT_ModHandler.getIC2Item("airCell", 3), 50, 110);
                } else if (aEventName.equals("dustRedrock") || aEventName.equals("dustRedRock")) {
                    GT_Mod.addCentrifugeRecipe(new ItemStack(aItem, 4, aMeta), 0, GT_MetaItem_Dust.instance.getStack(4, 2), GT_MetaItem_Dust.instance.getStack(7, 1), GT_OreDictUnificator.get("dustClay", null, 1), null, 100);
                } else if (aEventName.equals("dustMarble")) {
                    GT_Mod.addCentrifugeRecipe(new ItemStack(aItem, 8, aMeta), 0, GT_MetaItem_Dust.instance.getStack(13, 1), GT_MetaItem_Dust.instance.getStack(4, 7), null, null, 1055);
                } else if (aEventName.equals("dustBasalt")) {
                    GT_Mod.addCentrifugeRecipe(new ItemStack(aItem, 16, aMeta), 0, GT_MetaItem_Dust.instance.getStack(37, 1), GT_MetaItem_Dust.instance.getStack(4, 3), GT_MetaItem_Dust.instance.getStack(7, 8), GT_MetaItem_Dust.instance.getStack(63, 4), 2040);
                } else if (aEventName.equals("dustLapis") || aEventName.equals("dustLapisLazuli")) {
                    GT_Mod.addCentrifugeRecipe(new ItemStack(aItem, 4, aMeta), 0, GT_MetaItem_Dust.instance.getStack(2, 3), GT_MetaItem_SmallDust.instance.getStack(3, 1), GT_MetaItem_SmallDust.instance.getStack(4, 1), GT_MetaItem_SmallDust.instance.getStack(5, 2), 1500);
                } else if (aEventName.equals("dustSulfur")) {
                    GT_Mod.addCannerRecipe(new ItemStack(aItem, 1, aMeta), GT_ModHandler.getEmptyCell(1), GT_MetaItem_Cell.instance.getStack(36, 1), null, 100, 1);
                } else if (aEventName.equals("dustObsidian")) {
                    GT_Mod.addElectrolyzerRecipe(new ItemStack(aItem, 4, aMeta), 3, GT_MetaItem_SmallDust.instance.getStack(13, 2), GT_OreDictUnificator.get("dustSmallIron", null, 2), GT_MetaItem_Cell.instance.getStack(7, 1), GT_ModHandler.getIC2Item("airCell", 2), 500, 5);
                } else if (!aEventName.equals("dustRefinedObsidian") && !aEventName.equals("dustRefinedGlowstone")) {
                    if (aEventName.equals("dustNikolite")) {
                        GT_ModHandler.addIC2MatterAmplifier(new ItemStack(aItem, 1, aMeta), 5000);
                    } else if (aEventName.equals("dustInvar")) {
                        GT_ModHandler.addDustToIngotSmeltingRecipe(new ItemStack(aItem, 1, aMeta), GT_OreDictUnificator.get("ingotInvar", null, 1));
                        GT_Mod.addCentrifugeRecipe(new ItemStack(aItem, 3, aMeta), 0, GT_OreDictUnificator.get("dustIron", null, 2), GT_OreDictUnificator.get("dustNickel", null, 1), null, null, 1000);
                    } else if (!aEventName.equals("dustMagnesium")) {
                        if (aEventName.equals("dustManganese")) {
                            GT_ModHandler.addIC2MatterAmplifier(new ItemStack(aItem, 1, aMeta), 5000);
                        } else if (!aEventName.equals("dustHepatizon")) {
                            if (aEventName.equals("dustDamascus Steel")) {
                                GT_Mod.addCentrifugeRecipe(new ItemStack(aItem, 1, aMeta), 0, GT_MetaItem_Dust.instance.getStack(26, 1), null, null, null, 960);
                            } else if (!(aEventName.equals("dustAngmallen") || aEventName.equals("dustEximite") || aEventName.equals("dustMeutoite") || aEventName.equals("dustDesichalkos") || aEventName.equals("dustPrometheum"))) {
                                if (aEventName.equals("dustDeep Iron")) {
                                    GT_Mod.addCentrifugeRecipe(new ItemStack(aItem, 1, aMeta), 0, GT_MetaItem_Dust.instance.getStack(26, 1), null, null, null, 960);
                                } else if (!(aEventName.equals("dustInfuscolium") || aEventName.equals("dustOureclase") || aEventName.equals("dustAredrite"))) {
                                    if (aEventName.equals("dustAstral Silver")) {
                                        GT_Mod.addCentrifugeRecipe(new ItemStack(aItem, 1, aMeta), 0, GT_OreDictUnificator.get("dustSilver", null, 1), null, null, null, 960);
                                    } else if (!(aEventName.equals("dustCarmot") || aEventName.equals("dustAmordrine") || aEventName.equals("dustMithril") || aEventName.equals("dustRubracium") || aEventName.equals("dustOrichalcum") || aEventName.equals("dustAdamantine") || aEventName.equals("dustAtlarus"))) {
                                        if (aEventName.equals("dustBlack Steel")) {
                                            GT_Mod.addCentrifugeRecipe(new ItemStack(aItem, 1, aMeta), 0, GT_MetaItem_Dust.instance.getStack(26, 1), null, null, null, 960);
                                        } else if (!(aEventName.equals("dustHaderoth") || aEventName.equals("dustCelenegil") || aEventName.equals("dustTartarite") || aEventName.equals("dustIgnatius"))) {
                                            if (aEventName.equals("dustShadow Iron")) {
                                                GT_Mod.addCentrifugeRecipe(new ItemStack(aItem, 1, aMeta), 0, GT_MetaItem_Dust.instance.getStack(26, 1), null, null, null, 960);
                                            } else if (aEventName.equals("dustMidasium")) {
                                                GT_Mod.addCentrifugeRecipe(new ItemStack(aItem, 1, aMeta), 0, GT_OreDictUnificator.get("dustGold", null, 1), null, null, null, 960);
                                            } else if (!(aEventName.equals("dustVyroxeres") || aEventName.equals("dustCeruclase") || aEventName.equals("dustKalendrite") || aEventName.equals("dustVulcanite") || aEventName.equals("dustSanguinite") || aEventName.equals("dustLemurite") || aEventName.equals("dustAdluorite"))) {
                                                if (aEventName.equals("dustShadow Steel")) {
                                                    GT_Mod.addCentrifugeRecipe(new ItemStack(aItem, 1, aMeta), 0, GT_MetaItem_Dust.instance.getStack(26, 1), null, null, null, 960);
                                                } else if (!aEventName.equals("dustInolashite") && !aEventName.equals("dustGunpowder")) {
                                                    if (aEventName.equals("dustRedstone")) {
                                                        Iterator tIterator1 = GT_OreDictUnificator.getOres("stickWood").iterator();
                                                        while (tIterator1.hasNext()) {
                                                            GT_Mod.addAssemblerRecipe(((ItemStack)tIterator1.next()).copy().splitStack(1), new ItemStack(aItem, 1, aMeta), new ItemStack(Block.torchRedstoneActive, 1), 400, 1);
                                                        }
                                                        tIterator1 = GT_OreDictUnificator.getOres("ingotIron").iterator();
                                                        while (tIterator1.hasNext()) {
                                                            GT_Mod.addAssemblerRecipe(((ItemStack)tIterator1.next()).copy().splitStack(4), new ItemStack(aItem, 1, aMeta), new ItemStack(Item.compass, 1), 400, 4);
                                                        }
                                                        tIterator1 = GT_OreDictUnificator.getOres("ingotGold").iterator();
                                                        while (tIterator1.hasNext()) {
                                                            GT_Mod.addAssemblerRecipe(((ItemStack)tIterator1.next()).copy().splitStack(4), new ItemStack(aItem, 1, aMeta), new ItemStack(Item.pocketSundial, 1), 400, 4);
                                                        }
                                                        GT_ModHandler.addIC2MatterAmplifier(new ItemStack(aItem, 1, aMeta), 5000);
                                                        GT_Mod.addCentrifugeRecipe(new ItemStack(aItem, 10, aMeta), 4, GT_MetaItem_Cell.instance.getStack(7, 1), GT_MetaItem_Dust.instance.getStack(3, 5), GT_MetaItem_Dust.instance.getStack(32, 1), GT_MetaItem_Cell.instance.getStack(16, 3), 7000);
                                                    } else if (aEventName.equals("dustGlowstone")) {
                                                        GT_ModHandler.addIC2MatterAmplifier(new ItemStack(aItem, 1, aMeta), 25000);
                                                        Iterator tIterator1 = GT_OreDictUnificator.getOres("itemLazurite").iterator();
                                                        while (tIterator1.hasNext()) {
                                                            GT_Mod.addAssemblerRecipe(((ItemStack)tIterator1.next()).copy().splitStack(1), new ItemStack(aItem, 1, aMeta), GT_MetaItem_Component.instance.getStack(24, 2), 800, 2);
                                                        }
                                                        GT_Mod.addCentrifugeRecipe(new ItemStack(aItem, 16, aMeta), 1, new ItemStack(Item.redstone, 8), GT_OreDictUnificator.get("dustGold", null, 8), GT_MetaItem_Cell.instance.getStack(3, 1), null, 25000);
                                                    } else if (aEventName.equals("dustClay")) {
                                                        GT_Mod.addElectrolyzerRecipe(new ItemStack(aItem, 8, aMeta), 5, GT_MetaItem_Cell.instance.getStack(5, 1), GT_MetaItem_Cell.instance.getStack(7, 2), GT_MetaItem_Dust.instance.getStack(18, 2), GT_MetaItem_Cell.instance.getStack(12, 2), 200, 50);
                                                    } else if (aEventName.equals("dustBronze")) {
                                                        GT_ModHandler.addDustToIngotSmeltingRecipe(new ItemStack(aItem, 1, aMeta), GT_OreDictUnificator.get("ingotBronze", null, 1));
                                                        GT_Mod.addCentrifugeRecipe(new ItemStack(aItem, GT_Mod.instance.mForestryBronzeNerf || GT_ModHandler.getRecipeOutput(new ItemStack[]{GT_OreDictUnificator.get("ingotCopper", null, 1), GT_OreDictUnificator.get("ingotCopper", null, 1), null, GT_OreDictUnificator.get("ingotCopper", null, 1), GT_OreDictUnificator.get("ingotTin", null, 1), null, null, null, null}) == null ? 1 : 2, aMeta), 0, GT_MetaItem_SmallDust.instance.getStack(243, 6), GT_MetaItem_SmallDust.instance.getStack(244, 2), null, null, 1500);
                                                    } else if (aEventName.equals("dustBrass")) {
                                                        GT_ModHandler.addDustToIngotSmeltingRecipe(new ItemStack(aItem, 1, aMeta), GT_OreDictUnificator.get("ingotBrass", null, 1));
                                                        GT_Mod.addCentrifugeRecipe(new ItemStack(aItem, GT_Mod.instance.mForestryBronzeNerf || GT_ModHandler.getRecipeOutput(new ItemStack[]{GT_OreDictUnificator.get("ingotCopper", null, 1), GT_OreDictUnificator.get("ingotCopper", null, 1), null, GT_OreDictUnificator.get("ingotCopper", null, 1), GT_OreDictUnificator.get("ingotTin", null, 1), null, null, null, null}) == null ? 1 : 2, aMeta), 0, GT_MetaItem_SmallDust.instance.getStack(243, 3), GT_MetaItem_SmallDust.instance.getStack(24, 1), null, null, 1500);
                                                    } else if (aEventName.equals("dustCopper")) {
                                                        GT_ModHandler.addDustToIngotSmeltingRecipe(new ItemStack(aItem, 1, aMeta), GT_OreDictUnificator.get("ingotCopper", null, 1));
                                                        GT_Mod.addCentrifugeRecipe(new ItemStack(aItem, 3, aMeta), 0, GT_MetaItem_SmallDust.instance.getStack(242, 1), GT_MetaItem_SmallDust.instance.getStack(28, 1), null, null, 2400);
                                                    } else if (aEventName.equals("dustGold")) {
                                                        GT_ModHandler.addDustToIngotSmeltingRecipe(new ItemStack(aItem, 1, aMeta), GT_OreDictUnificator.get("ingotGold", null, 1));
                                                        GT_Mod.addCentrifugeRecipe(new ItemStack(aItem, 3, aMeta), 0, GT_MetaItem_SmallDust.instance.getStack(243, 1), GT_MetaItem_SmallDust.instance.getStack(28, 1), null, null, 2400);
                                                    } else if (aEventName.equals("dustNickel")) {
                                                        GT_ModHandler.addDustToIngotSmeltingRecipe(new ItemStack(aItem, 1, aMeta), GT_OreDictUnificator.get("ingotNickel", null, 1));
                                                        GT_Mod.addCentrifugeRecipe(new ItemStack(aItem, 3, aMeta), 0, GT_MetaItem_SmallDust.instance.getStack(241, 1), GT_MetaItem_SmallDust.instance.getStack(242, 1), GT_MetaItem_SmallDust.instance.getStack(243, 1), null, 3450);
                                                    } else if (aEventName.equals("dustIron")) {
                                                        GT_ModHandler.addDustToIngotSmeltingRecipe(new ItemStack(aItem, 1, aMeta), GT_OreDictUnificator.get("ingotIron", null, 1));
                                                        GT_Mod.addCentrifugeRecipe(new ItemStack(aItem, 2, aMeta), 0, GT_MetaItem_SmallDust.instance.getStack(244, 1), GT_MetaItem_SmallDust.instance.getStack(28, 1), null, null, 1500);
                                                    } else if (aEventName.equals("dustRefinedIron")) {
                                                        GT_ModHandler.addDustToIngotSmeltingRecipe(new ItemStack(aItem, 1, aMeta), GT_OreDictUnificator.get("ingotRefinedIron", null, 1));
                                                        GT_ModHandler.addExtractionRecipe(new ItemStack(aItem, 1, aMeta), GT_OreDictUnificator.get("dustIron", null, 1));
                                                    } else if (aEventName.equals("dustTin")) {
                                                        GT_ModHandler.addDustToIngotSmeltingRecipe(new ItemStack(aItem, 1, aMeta), GT_OreDictUnificator.get("ingotTin", null, 1));
                                                        GT_Mod.addCentrifugeRecipe(new ItemStack(aItem, 2, aMeta), 0, GT_MetaItem_SmallDust.instance.getStack(24, 1), GT_MetaItem_SmallDust.instance.getStack(241, 1), null, null, 2100);
                                                    } else if (aEventName.equals("dustZinc")) {
                                                        GT_ModHandler.addDustToIngotSmeltingRecipe(new ItemStack(aItem, 1, aMeta), GT_OreDictUnificator.get("ingotZinc", null, 1));
                                                        GT_Mod.addCentrifugeRecipe(new ItemStack(aItem, 1, aMeta), 0, GT_MetaItem_SmallDust.instance.getStack(244, 1), null, null, null, 1050);
                                                    } else if (aEventName.equals("dustQuicksilver")) {
                                                        System.out.println("'dustQuickSilver'?, To melt that, you don't even need a Furnace...");
                                                        GT_Mod.addCentrifugeRecipe(new ItemStack(aItem, 1, aMeta), 1, GT_MetaItem_Cell.instance.getStack(16, 1), null, null, null, 100);
                                                    } else if (aEventName.equals("dustGarnetRed")) {
                                                        GT_Mod.addCentrifugeRecipe(new ItemStack(aItem, 16, aMeta), 0, GT_OreDictUnificator.get("dustPyrope", null, 3), GT_OreDictUnificator.get("dustAlmandine", null, 5), GT_OreDictUnificator.get("dustSpessartine", null, 8), null, 3000);
                                                        GT_Mod.addImplosionRecipe(new ItemStack(aItem, 4, aMeta), 16, GT_MetaItem_Material.instance.getStack(54, 3), GT_MetaItem_Dust.instance.getStack(63, 8));
                                                    } else if (aEventName.equals("dustGarnetYellow")) {
                                                        GT_Mod.addCentrifugeRecipe(new ItemStack(aItem, 16, aMeta), 0, GT_OreDictUnificator.get("dustAndradite", null, 5), GT_OreDictUnificator.get("dustGrossular", null, 8), GT_OreDictUnificator.get("dustUvarovite", null, 3), null, 3500);
                                                        GT_Mod.addImplosionRecipe(new ItemStack(aItem, 4, aMeta), 16, GT_MetaItem_Material.instance.getStack(55, 3), GT_MetaItem_Dust.instance.getStack(63, 8));
                                                    } else if (aEventName.equals("dustPyrope")) {
                                                        GT_Mod.addElectrolyzerRecipe(new ItemStack(aItem, 20, aMeta), 9, GT_Mod.getGregTechItem(1, 3, 13), GT_Mod.getGregTechItem(1, 2, 18), GT_Mod.getGregTechItem(2, 3, 7), GT_ModHandler.getIC2Item("airCell", 6), 1790, 50);
                                                    } else if (aEventName.equals("dustAlmandine")) {
                                                        GT_Mod.addElectrolyzerRecipe(new ItemStack(aItem, 20, aMeta), 9, GT_OreDictUnificator.get("dustIron", null, 3), GT_Mod.getGregTechItem(1, 2, 18), GT_Mod.getGregTechItem(2, 3, 7), GT_ModHandler.getIC2Item("airCell", 6), 1640, 50);
                                                    } else if (aEventName.equals("dustSpessartine")) {
                                                        GT_Mod.addElectrolyzerRecipe(new ItemStack(aItem, 20, aMeta), 9, GT_Mod.getGregTechItem(1, 2, 18), GT_MetaItem_Dust.instance.getStack(12, 3), GT_Mod.getGregTechItem(2, 3, 7), GT_ModHandler.getIC2Item("airCell", 6), 1810, 50);
                                                    } else if (aEventName.equals("dustAndradite")) {
                                                        GT_Mod.addElectrolyzerRecipe(new ItemStack(aItem, 20, aMeta), 12, GT_Mod.getGregTechItem(2, 3, 11), GT_OreDictUnificator.get("dustIron", null, 2), GT_Mod.getGregTechItem(2, 3, 7), GT_ModHandler.getIC2Item("airCell", 6), 1280, 50);
                                                    } else if (aEventName.equals("dustGrossular")) {
                                                        GT_Mod.addElectrolyzerRecipe(new ItemStack(aItem, 20, aMeta), 12, GT_Mod.getGregTechItem(2, 3, 11), GT_Mod.getGregTechItem(1, 2, 18), GT_Mod.getGregTechItem(2, 3, 7), GT_ModHandler.getIC2Item("airCell", 6), 2050, 50);
                                                    } else if (aEventName.equals("dustUvarovite")) {
                                                        GT_Mod.addElectrolyzerRecipe(new ItemStack(aItem, 20, aMeta), 12, GT_Mod.getGregTechItem(2, 3, 11), GT_Mod.getGregTechItem(1, 2, 20), GT_Mod.getGregTechItem(2, 3, 7), GT_ModHandler.getIC2Item("airCell", 6), 2200, 50);
                                                    } else if (aEventName.equals("dustSphalerite")) {
                                                        GT_Mod.addElectrolyzerRecipe(new ItemStack(aItem, 2, aMeta), 0, GT_Mod.getGregTechItem(1, 1, 24), GT_Mod.getGregTechItem(1, 1, 8), null, null, 150, 100);
                                                    } else if (aEventName.equals("dustCinnabar")) {
                                                        GT_Mod.addElectrolyzerRecipe(new ItemStack(aItem, 2, aMeta), 1, GT_Mod.getGregTechItem(2, 1, 16), GT_Mod.getGregTechItem(1, 1, 8), null, null, 100, 128);
                                                    } else if (aEventName.equals("dustPlatinum")) {
                                                        GT_ModHandler.addIC2MatterAmplifier(new ItemStack(aItem, 1, aMeta), 100000);
                                                        GT_Mod.addCentrifugeRecipe(new ItemStack(aItem, 1, aMeta), 0, GT_OreDictUnificator.get("nuggetIridium", null, 1), GT_MetaItem_SmallDust.instance.getStack(28, 1), null, null, 3000);
                                                        GT_ModHandler.addDustToIngotSmeltingRecipe(new ItemStack(aItem, 1, aMeta), GT_MetaItem_Material.instance.getStack(27, 1));
                                                    } else if (aEventName.equals("dustIridium")) {
                                                        GT_ModHandler.addIC2MatterAmplifier(new ItemStack(aItem, 1, aMeta), 100000);
                                                        GT_Mod.addCentrifugeRecipe(new ItemStack(aItem, 1, aMeta), 0, GT_OreDictUnificator.get("nuggetPlatinum", null, 1), GT_MetaItem_SmallDust.instance.getStack(28, 1), null, null, 3000);
                                                        GT_ModHandler.addCompressionRecipe(new ItemStack(aItem, 1, aMeta), GT_OreDictUnificator.get("ingotIridium", null, 1));
                                                    } else if (aEventName.equals("dustOsmium")) {
                                                        GT_ModHandler.addIC2MatterAmplifier(new ItemStack(aItem, 1, aMeta), 200000);
                                                        GT_ModHandler.addCompressionRecipe(new ItemStack(aItem, 1, aMeta), GT_OreDictUnificator.get("ingotOsmium", null, 1));
                                                    } else if (aEventName.equals("dustLead")) {
                                                        GT_ModHandler.addDustToIngotSmeltingRecipe(new ItemStack(aItem, 1, aMeta), GT_OreDictUnificator.get("ingotLead", null, 1));
                                                        GT_Mod.addCentrifugeRecipe(new ItemStack(aItem, 2, aMeta), 0, GT_OreDictUnificator.get("dustSmallSilver", null, 1), null, null, null, 2400);
                                                    } else if (aEventName.equals("dustSilver")) {
                                                        GT_ModHandler.addDustToIngotSmeltingRecipe(new ItemStack(aItem, 1, aMeta), GT_OreDictUnificator.get("ingotSilver", null, 1));
                                                        GT_Mod.addCentrifugeRecipe(new ItemStack(aItem, 2, aMeta), 0, GT_OreDictUnificator.get("dustSmallLead", null, 1), null, null, null, 2400);
                                                    } else if (aEventName.equals("dustSilverLead") || aEventName.equals("dustGalena")) {
                                                        GT_Mod.addElectrolyzerRecipe(new ItemStack(aItem, 2, aMeta), 0, GT_OreDictUnificator.get("dustSmallSilver", null, 3), GT_OreDictUnificator.get("dustSmallLead", null, 3), GT_OreDictUnificator.get("dustSmallSulfur", null, 2), null, 1000, 120);
                                                        GT_Mod.addBlastRecipe(new ItemStack(aItem, 2, aMeta), null, GT_OreDictUnificator.get("ingotSilver", null, 1), GT_OreDictUnificator.get("ingotLead", null, 1), 20, 128, 1500);
                                                    } else if (aEventName.equals("dustEnderPearl")) {
                                                        GT_ModHandler.addIC2MatterAmplifier(new ItemStack(aItem, 1, aMeta), 50000);
                                                        GT_Mod.addElectrolyzerRecipe(new ItemStack(aItem, 16, aMeta), 16, GT_Mod.getGregTechItem(2, 5, 15), GT_Mod.getGregTechItem(2, 1, 10), GT_Mod.getGregTechItem(2, 4, 14), GT_Mod.getGregTechItem(2, 6, 13), 1300, 50);
                                                    } else if (aEventName.equals("dustEnderEye")) {
                                                        GT_ModHandler.addIC2MatterAmplifier(new ItemStack(aItem, 1, aMeta), 75000);
                                                        GT_Mod.addCentrifugeRecipe(new ItemStack(aItem, 2, aMeta), 0, GT_Mod.getGregTechItem(1, 1, 0), new ItemStack(Item.blazePowder, 1), null, null, 1850);
                                                    } else if (aEventName.equals("dustOlivine")) {
                                                        GT_Mod.addAssemblerRecipe(new ItemStack(aItem, 8, aMeta), GT_ModHandler.getIC2Item("advancedCircuit", 1), GT_MetaItem_Component.instance.getStack(3, 4), 6400, 8);
                                                        GT_ModHandler.addIC2MatterAmplifier(new ItemStack(aItem, 1, aMeta), 50000);
                                                        GT_Mod.addElectrolyzerRecipe(new ItemStack(aItem, 9, aMeta), 3, GT_Mod.getGregTechItem(1, 2, 13), GT_OreDictUnificator.get("dustIron", null, 2), GT_Mod.getGregTechItem(2, 1, 7), GT_ModHandler.getIC2Item("airCell", 2), 600, 60);
                                                        GT_Mod.addImplosionRecipe(new ItemStack(aItem, 4, aMeta), 24, GT_MetaItem_Material.instance.getStack(37, 3), GT_MetaItem_Dust.instance.getStack(63, 12));
                                                    } else if (aEventName.equals("dustEmerald")) {
                                                        GT_Mod.addAssemblerRecipe(new ItemStack(aItem, 8, aMeta), GT_ModHandler.getIC2Item("advancedCircuit", 1), GT_MetaItem_Component.instance.getStack(3, 4), 6400, 8);
                                                        GT_ModHandler.addIC2MatterAmplifier(new ItemStack(aItem, 1, aMeta), 50000);
                                                        GT_Mod.addElectrolyzerRecipe(new ItemStack(aItem, 29, aMeta), 18, GT_Mod.getGregTechItem(1, 2, 18), GT_Mod.getGregTechItem(2, 3, 10), GT_Mod.getGregTechItem(2, 6, 7), GT_ModHandler.getIC2Item("airCell", 9), 600, 50);
                                                        GT_Mod.addImplosionRecipe(new ItemStack(aItem, 4, aMeta), 24, new ItemStack(Item.emerald, 3), GT_MetaItem_Dust.instance.getStack(63, 12));
                                                    } else if (aEventName.equals("dustDiamond")) {
                                                        GT_ModHandler.addIC2MatterAmplifier(new ItemStack(aItem, 1, aMeta), 125000);
                                                        GT_Mod.addImplosionRecipe(new ItemStack(aItem, 4, aMeta), 32, GT_ModHandler.getIC2Item("industrialDiamond", 3), GT_MetaItem_Dust.instance.getStack(63, 16));
                                                    } else if (aEventName.equals("dustRuby")) {
                                                        GT_ModHandler.addIC2MatterAmplifier(new ItemStack(aItem, 1, aMeta), 50000);
                                                        GT_Mod.addElectrolyzerRecipe(new ItemStack(aItem, 9, aMeta), 3, GT_Mod.getGregTechItem(1, 2, 18), GT_Mod.getGregTechItem(1, 1, 20), GT_ModHandler.getIC2Item("airCell", 3), null, 500, 50);
                                                        GT_Mod.addImplosionRecipe(new ItemStack(aItem, 4, aMeta), 24, GT_OreDictUnificator.get("gemRuby", GT_MetaItem_Material.instance.getStack(32, 1), 3), GT_MetaItem_Dust.instance.getStack(63, 12));
                                                    } else if (aEventName.equals("dustSapphire")) {
                                                        GT_ModHandler.addIC2MatterAmplifier(new ItemStack(aItem, 1, aMeta), 50000);
                                                        GT_Mod.addElectrolyzerRecipe(new ItemStack(aItem, 8, aMeta), 3, GT_Mod.getGregTechItem(1, 2, 18), GT_ModHandler.getIC2Item("airCell", 3), null, null, 400, 50);
                                                        GT_Mod.addImplosionRecipe(new ItemStack(aItem, 4, aMeta), 24, GT_OreDictUnificator.get("gemSapphire", GT_MetaItem_Material.instance.getStack(33, 1), 3), GT_MetaItem_Dust.instance.getStack(63, 12));
                                                    } else if (aEventName.equals("dustGreenSapphire")) {
                                                        GT_ModHandler.addIC2MatterAmplifier(new ItemStack(aItem, 1, aMeta), 50000);
                                                        GT_Mod.addElectrolyzerRecipe(new ItemStack(aItem, 1, aMeta), 0, GT_Mod.getGregTechItem(1, 1, 33), null, null, null, 100, 50);
                                                        GT_Mod.addImplosionRecipe(new ItemStack(aItem, 4, aMeta), 24, GT_OreDictUnificator.get("gemGreenSapphire", GT_MetaItem_Material.instance.getStack(34, 1), 3), GT_MetaItem_Dust.instance.getStack(63, 12));
                                                    } else if (aEventName.equals("dustAsh")) {
                                                        GT_Mod.addElectrolyzerRecipe(new ItemStack(aItem, 2, aMeta), 1, GT_Mod.getGregTechItem(2, 1, 8), null, null, null, 25, 50);
                                                    } else if (aEventName.equals("dustDarkAsh")) {
                                                        ItemStack tStack1;
                                                        GT_Mod.addCentrifugeRecipe(new ItemStack(aItem, 2, aMeta), 0, GT_MetaItem_Dust.instance.getStack(62, 1), (tStack1 = GT_ModHandler.getTEItem("slag", 1)) == null ? GT_MetaItem_Dust.instance.getStack(62, 1) : tStack1, null, null, 250);
                                                    } else if (aEventName.equals("dustCoal")) {
                                                        GT_ModHandler.addLiquidTransposerFillRecipe(new ItemStack(aItem, 1, aMeta), new LiquidStack(Block.waterStill, 125), GT_ModHandler.getIC2Item("hydratedCoalDust", 1), 125);
                                                        GT_Mod.addElectrolyzerRecipe(new ItemStack(aItem, 1, aMeta), 2, GT_Mod.getGregTechItem(2, 2, 8), null, null, null, 40, 50);
                                                        for (ItemStack tStack1 : GT_OreDictUnificator.getOres("ingotRefinedIron")) {
                                                            GT_Mod.addBlastRecipe(new ItemStack(tStack1.getItem(), 1, tStack1.getItemDamage()), new ItemStack(aItem, 2, aMeta), GT_OreDictUnificator.get("ingotSteel", null, 1), GT_MetaItem_Dust.instance.getStack(63, 2), 500, 128, 1000);
                                                        }
                                                    } else if (aEventName.equals("dustCharcoal")) {
                                                        GT_Mod.addElectrolyzerRecipe(new ItemStack(aItem, 1, aMeta), 1, GT_Mod.getGregTechItem(2, 1, 8), null, null, null, 20, 50);
                                                    } else if (aEventName.equals("dustUranium")) {
                                                        GT_ModHandler.addIC2MatterAmplifier(new ItemStack(aItem, 1, aMeta), 1000000);
                                                        GT_ModHandler.addCompressionRecipe(new ItemStack(aItem, 1, aMeta), GT_OreDictUnificator.get("ingotUranium", null, 1));
                                                        GT_Mod.addCentrifugeRecipe(new ItemStack(aItem, 4, aMeta), 4, GT_ModHandler.getIC2Item("reactorUraniumSimple", 4), GT_OreDictUnificator.get("dustSmallPlutonium", 1), GT_OreDictUnificator.get("dustThorium", 2), GT_OreDictUnificator.get("dustTungsten", 1), 10000);
                                                    } else if (aEventName.equals("dustPlutonium")) {
                                                        GT_ModHandler.addIC2MatterAmplifier(new ItemStack(aItem, 1, aMeta), 2000000);
                                                        GT_ModHandler.addCompressionRecipe(new ItemStack(aItem, 1, aMeta), GT_OreDictUnificator.getFirstOre("ingotPlutonium", 1));
                                                    } else if (aEventName.equals("dustThorium")) {
                                                        GT_ModHandler.addIC2MatterAmplifier(new ItemStack(aItem, 1, aMeta), 500000);
                                                        GT_ModHandler.addCompressionRecipe(new ItemStack(aItem, 1, aMeta), GT_OreDictUnificator.getFirstOre("ingotThorium", 1));
                                                    } else if (aEventName.equals("dustTungsten")) {
                                                        GT_ModHandler.addIC2MatterAmplifier(new ItemStack(aItem, 1, aMeta), 50000);
                                                        GT_Mod.addBlastRecipe(new ItemStack(aItem, 1, aMeta), null, GT_MetaItem_Material.instance.getStack(22, 1), null, 2000, 128, 2500);
                                                        GT_Mod.addCannerRecipe(new ItemStack(aItem, 1, aMeta), GT_ModHandler.getEmptyCell(1), GT_MetaItem_Cell.instance.getStack(4, 1), null, 100, 1);
                                                        if (GT_Mod.instance.mTungstenBlastFurnace) {
                                                            if (FurnaceRecipes.smelting().getSmeltingResult(new ItemStack(aItem, 1, aMeta)) != null) {
                                                                GT_ModHandler.addSmeltingRecipe(new ItemStack(aItem, 1, aMeta), GT_OreDictUnificator.get(new ItemStack(aItem, 1, aMeta)));
                                                            }
                                                        } else {
                                                            GT_ModHandler.addDustToIngotSmeltingRecipe(new ItemStack(aItem, 1, aMeta), GT_OreDictUnificator.get("ingotTungsten", null, 1));
                                                        }
                                                    } else if (aEventName.equals("dustLazurite")) {
                                                        GT_ModHandler.addCompressionRecipe(new ItemStack(aItem, 8, aMeta), GT_MetaItem_Material.instance.getStack(35, 1));
                                                        GT_Mod.addElectrolyzerRecipe(new ItemStack(aItem, 29, aMeta), 11, GT_Mod.getGregTechItem(1, 3, 18), GT_Mod.getGregTechItem(2, 3, 7), GT_Mod.getGregTechItem(2, 4, 11), GT_Mod.getGregTechItem(2, 4, 12), 1475, 100);
                                                    } else if (aEventName.equals("dustPyrite")) {
                                                        GT_Mod.addElectrolyzerRecipe(new ItemStack(aItem, 3, aMeta), 0, GT_OreDictUnificator.get("dustIron", null, 1), GT_MetaItem_Dust.instance.getStack(8, 2), null, null, 120, 128);
                                                    } else if (aEventName.equals("dustCalcite")) {
                                                        GT_Mod.addCannerRecipe(new ItemStack(aItem, 1, aMeta), GT_ModHandler.getEmptyCell(1), GT_MetaItem_Cell.instance.getStack(33, 1), null, 100, 1);
                                                        GT_Mod.addElectrolyzerRecipe(new ItemStack(aItem, 10, aMeta), 7, GT_Mod.getGregTechItem(2, 2, 11), GT_Mod.getGregTechItem(2, 2, 8), GT_ModHandler.getIC2Item("airCell", 3), null, 700, 80);
                                                    } else if (aEventName.equals("dustSodalite")) {
                                                        GT_Mod.addElectrolyzerRecipe(new ItemStack(aItem, 23, aMeta), 8, GT_Mod.getGregTechItem(2, 4, 12), GT_Mod.getGregTechItem(1, 3, 18), GT_Mod.getGregTechItem(2, 3, 7), GT_Mod.getGregTechItem(2, 1, 13), 1350, 90);
                                                    } else if (aEventName.equals("dustNetherrack")) {
                                                        GT_Mod.addCentrifugeRecipe(new ItemStack(aItem, 16, aMeta), 0, new ItemStack(Item.redstone, 1), GT_MetaItem_Dust.instance.getStack(8, 4), GT_OreDictUnificator.get("dustCoal", null, 1), new ItemStack(Item.goldNugget, 1), 2400);
                                                    } else if (aEventName.equals("dustEndstone")) {
                                                        GT_Mod.addCentrifugeRecipe(new ItemStack(aItem, 16, aMeta), 2, GT_MetaItem_Cell.instance.getStack(6, 1), GT_MetaItem_Cell.instance.getStack(3, 1), GT_MetaItem_SmallDust.instance.getStack(22, 1), new ItemStack(Block.sand, 12), 4800);
                                                    } else if (aEventName.equals("dustFlint")) {
                                                        GT_Mod.addElectrolyzerRecipe(new ItemStack(aItem, 8, aMeta), 2, GT_MetaItem_Cell.instance.getStack(7, 1), GT_ModHandler.getIC2Item("airCell", 1), null, null, 1000, 5);
                                                    } else if (aEventName.equals("dustBauxite")) {
                                                        GT_Mod.addElectrolyzerRecipe(new ItemStack(aItem, 12, aMeta), 8, GT_MetaItem_Dust.instance.getStack(18, 8), GT_MetaItem_SmallDust.instance.getStack(19, 2), GT_MetaItem_Cell.instance.getStack(0, 5), GT_ModHandler.getIC2Item("airCell", 3), 2000, 128);
                                                    } else if (aEventName.equals("dustElectrum")) {
                                                        GT_ModHandler.addDustToIngotSmeltingRecipe(new ItemStack(aItem, 1, aMeta), GT_OreDictUnificator.get("ingotElectrum", null, 1));
                                                        GT_Mod.addCentrifugeRecipe(new ItemStack(aItem, 1, aMeta), 0, GT_OreDictUnificator.get("dustSmallGold", null, 2), GT_OreDictUnificator.get("dustSmallSilver", null, 2), null, null, 975);
                                                    } else if (!aEventName.equals("dustPhosphorus")) {
                                                        if (aEventName.equals("dustWheat") || aEventName.equals("dustFlour")) {
                                                            GT_ModHandler.addSmeltingRecipe(new ItemStack(aItem, 1, aMeta), new ItemStack(Item.bread, 1, 0));
                                                        } else if (!aEventName.equals("dustQuartz")) {
                                                            System.out.println("Dust Name: " + aEvent.Name + " !!!Unknown Dust detected!!! Please report to GregTech Intergalactical for additional compatiblity. This is not an Error, it's just an Information.");
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        } else if (aEventName.startsWith("gem")) {
            ItemStack tStack1 = GT_OreDictUnificator.getFirstOre(aEventName.replaceFirst("gem", "block"), 1);
            if (null != tStack1 && GT_Mod.mConfig.addAdvConfig("StorageBlockCompressing", aEventName, true)) {
                GT_ModHandler.addCompressionRecipe(new ItemStack(aItem, 9, aMeta), tStack1.copy());
            }
            if (aEventName.equals("gemRuby")) {
                GT_ModHandler.addPulverisationRecipe(new ItemStack(aItem, 1, aMeta), GT_MetaItem_Dust.instance.getStack(32, 1), null, 0, false);
            } else if (aEventName.equals("gemSapphire")) {
                GT_ModHandler.addPulverisationRecipe(new ItemStack(aItem, 1, aMeta), GT_MetaItem_Dust.instance.getStack(33, 1), null, 0, false);
            } else if (aEventName.equals("gemGreenSapphire")) {
                GT_ModHandler.addPulverisationRecipe(new ItemStack(aItem, 1, aMeta), GT_MetaItem_Dust.instance.getStack(34, 1), null, 0, false);
            } else if (aEventName.equals("gemDiamond")) {
                GT_ModHandler.addPulverisationRecipe(new ItemStack(aItem, 1, aMeta), GT_MetaItem_Dust.instance.getStack(36, 1), null, 0, false);
            } else if (aEventName.equals("gemEmerald")) {
                GT_Mod.addAssemblerRecipe(new ItemStack(aItem, 8, aMeta), GT_ModHandler.getIC2Item("advancedCircuit", 1), GT_MetaItem_Component.instance.getStack(3, 4), 3200, 4);
                GT_ModHandler.addPulverisationRecipe(new ItemStack(aItem, 1, aMeta), GT_MetaItem_Dust.instance.getStack(35, 1), null, 0, false);
            } else if (aEventName.equals("gemOlivine")) {
                GT_Mod.addAssemblerRecipe(new ItemStack(aItem, 8, aMeta), GT_ModHandler.getIC2Item("advancedCircuit", 1), GT_MetaItem_Component.instance.getStack(3, 4), 6400, 8);
                GT_ModHandler.addPulverisationRecipe(new ItemStack(aItem, 1, aMeta), GT_MetaItem_Dust.instance.getStack(37, 1), null, 0, false);
            } else if (aEventName.equals("gemGarnet") || aEventName.equals("gemGarnetRed")) {
                GT_ModHandler.addPulverisationRecipe(new ItemStack(aItem, 1, aMeta), GT_MetaItem_Dust.instance.getStack(54, 1), null, 0, false);
            } else if (aEventName.equals("gemGarnetYellow")) {
                GT_ModHandler.addPulverisationRecipe(new ItemStack(aItem, 1, aMeta), GT_MetaItem_Dust.instance.getStack(55, 1), null, 0, false);
            } else if (aEventName.equals("gemApatite")) {
                GT_ModHandler.addPulverisationRecipe(new ItemStack(aItem, 1, aMeta), GT_ModHandler.getIC2Item("fertilizer", 4), GT_OreDictUnificator.get("dustPhosphorus", null, 1), 50, false);
            } else {
                System.out.println("Gem Name: " + aEvent.Name + " !!!Unknown Gem detected!!! Please report to GregTech Intergalactical for additional compatiblity. This is not an Error, it's just an Information.");
            }
        } else if (aEventName.startsWith("crystal") && !aEventName.startsWith("crystalline")) {
            if (!aEventName.equals("crystalQuartz")) {
                System.out.println("Crystal Name: " + aEvent.Name + " !!!Unknown Crystal detected!!! Please report to GregTech Intergalactical for additional compatiblity. This is not an Error, it's just an Information.");
            }
        } else if (aEventName.startsWith("ingot")) {
            if (aEvent.Name.equals("ingotCopper")) {
                GT_Utility.removeSimpleIC2MachineRecipe(new ItemStack(aItem, 1, aMeta), null, Ic2Recipes.getCompressorRecipes());
            }
            GT_Mod.addBenderRecipe(new ItemStack(aItem, 1, aMeta), GT_OreDictUnificator.getFirstOre(aEventName.replaceFirst("ingot", "plate"), 1), 50, 20);
            ItemStack tStack12a = GT_OreDictUnificator.getFirstOre(aEventName.replaceFirst("ingot", "block"), 1);
            if (null != tStack12a && GT_Mod.mConfig.addAdvConfig("StorageBlockCompressing", aEventName, true)) {
                GT_ModHandler.addCompressionRecipe(new ItemStack(aItem, 9, aMeta), tStack12a.copy());
            }
            if (GT_ModHandler.mBCStoneGear != null && null != (tStack12a = GT_ModHandler.getRecipeOutput(new ItemStack[]{null, new ItemStack(aItem, 1, aMeta), null, new ItemStack(aItem, 1, aMeta), GT_ModHandler.mBCStoneGear, new ItemStack(aItem, 1, aMeta), null, new ItemStack(aItem, 1, aMeta), null})) || null != (tStack12a = GT_ModHandler.getRecipeOutput(new ItemStack[]{null, new ItemStack(aItem, 1, aMeta), null, new ItemStack(aItem, 1, aMeta), new ItemStack(Item.ingotIron, 1), new ItemStack(aItem, 1, aMeta), null, new ItemStack(aItem, 1, aMeta), null})) || null != (tStack12a = GT_ModHandler.getRecipeOutput(new ItemStack[]{null, new ItemStack(aItem, 1, aMeta), null, new ItemStack(aItem, 1, aMeta), new ItemStack(Block.cobblestone, 1), new ItemStack(aItem, 1, aMeta), null, new ItemStack(aItem, 1, aMeta), null}))) {
                GT_Mod.addPlateCuttingRecipe(new ItemStack(aItem, 4, aMeta), tStack12a, 800, 1);
                GT_Mod.addAssemblerRecipe(GT_ModHandler.mBCStoneGear == null ? new ItemStack(Block.cobblestone, 1) : GT_ModHandler.mBCStoneGear, new ItemStack(aItem, 4, aMeta), tStack12a, 1600, 2);
            }
            if (aEventName.equals("ingotUranium")) {
                GT_Mod.addCannerRecipe(new ItemStack(aItem, 1, aMeta), GT_ModHandler.getEmptyCell(1), GT_ModHandler.getIC2Item("reactorUraniumSimple", 1), null, 100, 2);
                GT_ModHandler.addPulverisationRecipe(new ItemStack(aItem, 1, aMeta), GT_OreDictUnificator.get("dustUranium", null, 1), null, 0, false);
            } else if (aEventName.equals("ingotPlutonium")) {
                GT_Mod.addCannerRecipe(new ItemStack(aItem, 1, aMeta), GT_ModHandler.getEmptyCell(1), GT_Mod.getGregTechItem(51, 1, 0), null, 100, 2);
                GT_ModHandler.addPulverisationRecipe(new ItemStack(aItem, 1, aMeta), GT_OreDictUnificator.get("dustPlutonium", null, 1), null, 0, false);
            } else if (aEventName.equals("ingotThorium")) {
                GT_Mod.addCannerRecipe(new ItemStack(aItem, 1, aMeta), GT_ModHandler.getEmptyCell(1), GT_Mod.getGregTechItem(48, 1, 0), null, 100, 2);
                GT_ModHandler.addPulverisationRecipe(new ItemStack(aItem, 1, aMeta), GT_OreDictUnificator.get("dustThorium", null, 1), null, 0, false);
            } else if (aEventName.equals("ingotQuicksilver")) {
                System.out.println("'ingotQuickSilver'?, Don't tell me there is an Armor made of that highly toxic and very likely to be melting Material!");
                GT_ModHandler.addDustToIngotSmeltingRecipe(GT_OreDictUnificator.get("dustCinnabar", null, 1), new ItemStack(aItem, 1, aMeta));
            } else if (aEventName.equals("ingotManganese")) {
                GT_ModHandler.addDustToIngotSmeltingRecipe(GT_MetaItem_Dust.instance.getStack(12, 1), new ItemStack(aItem, 1, aMeta));
                GT_ModHandler.addPulverisationRecipe(new ItemStack(aItem, 1, aMeta), GT_OreDictUnificator.get("dustManganese", null, 1), null, 0, false);
            } else if (aEventName.equals("ingotMagnesium")) {
                GT_ModHandler.addDustToIngotSmeltingRecipe(GT_MetaItem_Dust.instance.getStack(13, 1), new ItemStack(aItem, 1, aMeta));
                GT_ModHandler.addPulverisationRecipe(new ItemStack(aItem, 1, aMeta), GT_OreDictUnificator.get("dustMagnesium", null, 1), null, 0, false);
            } else if (!(aEventName.equals("ingotTungstenSteel") || aEventName.equals("ingotInvar") || aEventName.equals("ingotHepatizon") || aEventName.equals("ingotDamascus Steel") || aEventName.equals("ingotAngmallen") || aEventName.equals("ingotEximite") || aEventName.equals("ingotMeutoite") || aEventName.equals("ingotDesichalkos") || aEventName.equals("ingotPrometheum") || aEventName.equals("ingotDeep Iron") || aEventName.equals("ingotInfuscolium") || aEventName.equals("ingotOureclase") || aEventName.equals("ingotAredrite") || aEventName.equals("ingotAstral Silver") || aEventName.equals("ingotCarmot") || aEventName.equals("ingotAmordrine") || aEventName.equals("ingotMithril") || aEventName.equals("ingotRubracium") || aEventName.equals("ingotOrichalcum") || aEventName.equals("ingotAdamantine") || aEventName.equals("ingotAtlarus") || aEventName.equals("ingotBlack Steel") || aEventName.equals("ingotHaderoth") || aEventName.equals("ingotCelenegil") || aEventName.equals("ingotTartarite") || aEventName.equals("ingotIgnatius") || aEventName.equals("ingotShadow Iron") || aEventName.equals("ingotMidasium") || aEventName.equals("ingotVyroxeres") || aEventName.equals("ingotCeruclase") || aEventName.equals("ingotKalendrite") || aEventName.equals("ingotVulcanite") || aEventName.equals("ingotSanguinite") || aEventName.equals("ingotLemurite") || aEventName.equals("ingotAdluorite") || aEventName.equals("ingotShadow Steel") || aEventName.equals("ingotInolashite") || aEventName.equals("ingotAmordrine") || aEventName.equals("ingotLivingMetal") || aEventName.equals("ingotQuartz") || aEventName.equals("ingotGraphit") || aEventName.equals("ingotDuraluminum") || aEventName.equals("ingotIridium") || aEventName.equals("ingotOsmium") || aEventName.equals("ingotRefinedObsidian") || aEventName.equals("ingotRefinedGlowstone"))) {
                if (aEventName.equals("ingotSilver")) {
                    GT_ModHandler.addPulverisationRecipe(new ItemStack(aItem, 1, aMeta), GT_OreDictUnificator.get("dustSilver", null, 1), null, 0, false);
                    for (ItemStack tStack12 : GT_OreDictUnificator.getOres("ingotGold")) {
                        GT_Mod.addAlloySmelterRecipe(new ItemStack(tStack12.getItem(), 1, tStack12.getItemDamage()), new ItemStack(aItem, 1, aMeta), GT_OreDictUnificator.get("ingotElectrum", null, 2), 100, 16);
                    }
                } else if (aEventName.equals("ingotBronze")) {
                    GT_ModHandler.addPulverisationRecipe(new ItemStack(aItem, 1, aMeta), GT_OreDictUnificator.get("dustBronze", null, 1), null, 0, false);
                } else if (aEventName.equals("ingotCopper")) {
                    GT_Mod.addWiremillRecipe(new ItemStack(aItem, 1, aMeta), GT_ModHandler.getIC2Item("copperCableItem", 3), 100, 2);
                    for (ItemStack tStack12 : GT_OreDictUnificator.getOres("ingotTin")) {
                        GT_Mod.addAlloySmelterRecipe(new ItemStack(aItem, 3, aMeta), new ItemStack(tStack12.getItem(), 1, tStack12.getItemDamage()), GT_OreDictUnificator.get("ingotBronze", null, 2), 100, 16);
                    }
                    for (ItemStack tStack12 : GT_OreDictUnificator.getOres("ingotZinc")) {
                        GT_Mod.addAlloySmelterRecipe(new ItemStack(aItem, 3, aMeta), new ItemStack(tStack12.getItem(), 1, tStack12.getItemDamage()), GT_OreDictUnificator.get("ingotBrass", null, 4), 200, 16);
                    }
                    for (ItemStack tStack12 : GT_OreDictUnificator.getOres("ingotZinc")) {
                        GT_ModHandler.addInductionSmelterRecipe(new ItemStack(aItem, 3, aMeta), new ItemStack(tStack12.getItem(), 1, tStack12.getItemDamage()), GT_OreDictUnificator.get("ingotBrass", null, 4), null, 400, 0);
                    }
                    GT_ModHandler.addPulverisationRecipe(new ItemStack(aItem, 1, aMeta), GT_OreDictUnificator.get("dustCopper", null, 1), null, 0, false);
                } else if (aEventName.equals("ingotTin")) {
                    GT_Mod.addWiremillRecipe(new ItemStack(aItem, 1, aMeta), GT_ModHandler.getIC2Item("tinCableItem", 4), 150, 1);
                    for (ItemStack tStack12 : GT_OreDictUnificator.getOres("ingotCopper")) {
                        GT_Mod.addAlloySmelterRecipe(new ItemStack(tStack12.getItem(), 3, tStack12.getItemDamage()), new ItemStack(aItem, 1, aMeta), GT_OreDictUnificator.get("ingotBronze", null, 2), 100, 16);
                    }
                    GT_ModHandler.addPulverisationRecipe(new ItemStack(aItem, 1, aMeta), GT_OreDictUnificator.get("dustTin", null, 1), null, 0, false);
                } else if (aEventName.equals("ingotRefinedIron")) {
                    GT_Mod.addAssemblerRecipe(new ItemStack(aItem, 8, aMeta), null, GT_ModHandler.getIC2Item("machine", 1), 400, 8);
                    GT_Mod.addWiremillRecipe(new ItemStack(aItem, 1, aMeta), GT_ModHandler.getIC2Item("ironCableItem", 6), 200, 2);
                    for (ItemStack tStack12 : GT_OreDictUnificator.getOres("dustCoal")) {
                        GT_Mod.addBlastRecipe(new ItemStack(aItem, 1, aMeta), new ItemStack(tStack12.getItem(), 2, tStack12.getItemDamage()), GT_OreDictUnificator.get("ingotSteel", null, 1), GT_MetaItem_Dust.instance.getStack(63, 2), 500, 128, 1000);
                    }
                    GT_ModHandler.addPulverisationRecipe(new ItemStack(aItem, 1, aMeta), GT_OreDictUnificator.get("dustRefinedIron", GT_OreDictUnificator.get("dustIron", null, 1), 1), null, 0, true);
                } else if (aEventName.equals("ingotIron")) {
                    Iterator tIterator1 = GT_OreDictUnificator.getOres("dustRedstone").iterator();
                    while (tIterator1.hasNext()) {
                        GT_Mod.addAssemblerRecipe(new ItemStack(aItem, 4, aMeta), ((ItemStack)tIterator1.next()).copy().splitStack(1), new ItemStack(Item.compass, 1), 400, 4);
                    }
                    for (ItemStack tStack12 : GT_OreDictUnificator.getOres("ingotNickel")) {
                        GT_Mod.addAlloySmelterRecipe(new ItemStack(aItem, 2, aMeta), new ItemStack(tStack12.getItem(), 1, tStack12.getItemDamage()), GT_OreDictUnificator.get("ingotInvar", null, 3), 150, 16);
                    }
                    GT_ModHandler.addPulverisationRecipe(new ItemStack(aItem, 1, aMeta), GT_OreDictUnificator.get("dustIron", null, 1), null, 0, false);
                    GT_ModHandler.addSmeltingRecipe(new ItemStack(aItem, 1, aMeta), GT_OreDictUnificator.get("ingotRefinedIron", null, 1));
                    GT_ModHandler.addInductionSmelterRecipe(new ItemStack(aItem, 2, aMeta), new ItemStack(Block.sand, 1, 0), GT_OreDictUnificator.get("ingotRefinedIron", null, 2), GT_ModHandler.getTEItem("slag", 1), 400, 25);
                } else if (aEventName.equals("ingotGold")) {
                    GT_Mod.addAssemblerRecipe(GT_ModHandler.mBCIronGear, new ItemStack(aItem, 4, aMeta), GT_ModHandler.mBCGoldGear, 800, 1);
                    Iterator tIterator1 = GT_OreDictUnificator.getOres("dustRedstone").iterator();
                    while (tIterator1.hasNext()) {
                        GT_Mod.addAssemblerRecipe(new ItemStack(aItem, 4, aMeta), ((ItemStack)tIterator1.next()).copy().splitStack(1), new ItemStack(Item.pocketSundial, 1), 400, 4);
                    }
                    GT_Mod.addWiremillRecipe(new ItemStack(aItem, 1, aMeta), GT_ModHandler.getIC2Item("goldCableItem", 6), 200, 1);
                    for (ItemStack tStack12 : GT_OreDictUnificator.getOres("ingotSilver")) {
                        GT_Mod.addAlloySmelterRecipe(new ItemStack(aItem, 1, aMeta), new ItemStack(tStack12.getItem(), 1, tStack12.getItemDamage()), GT_OreDictUnificator.get("ingotElectrum", null, 2), 100, 16);
                    }
                    GT_ModHandler.addPulverisationRecipe(new ItemStack(aItem, 1, aMeta), GT_OreDictUnificator.get("dustGold", null, 1), null, 0, false);
                } else if (aEventName.equals("ingotAluminium")) {
                    GT_ModHandler.addPulverisationRecipe(new ItemStack(aItem, 1, aMeta), GT_MetaItem_Dust.instance.getStack(18, 1), null, 0, false);
                    GT_ModHandler.removeFurnaceSmelting(null, new ItemStack(aItem, 1, aMeta));
                } else if (aEventName.equals("ingotTitanium")) {
                    GT_ModHandler.addPulverisationRecipe(new ItemStack(aItem, 1, aMeta), GT_MetaItem_Dust.instance.getStack(19, 1), null, 0, false);
                    GT_ModHandler.removeFurnaceSmelting(null, new ItemStack(aItem, 1, aMeta));
                } else if (aEventName.equals("ingotChrome")) {
                    GT_ModHandler.addPulverisationRecipe(new ItemStack(aItem, 1, aMeta), GT_MetaItem_Dust.instance.getStack(20, 1), null, 0, false);
                    GT_ModHandler.removeFurnaceSmelting(null, new ItemStack(aItem, 1, aMeta));
                } else if (aEventName.equals("ingotElectrum")) {
                    GT_ModHandler.addPulverisationRecipe(new ItemStack(aItem, 1, aMeta), GT_MetaItem_Dust.instance.getStack(21, 1), null, 0, false);
                } else if (aEventName.equals("ingotTungsten")) {
                    GT_ModHandler.addPulverisationRecipe(new ItemStack(aItem, 1, aMeta), GT_MetaItem_Dust.instance.getStack(22, 1), null, 0, false);
                    GT_ModHandler.removeFurnaceSmelting(null, new ItemStack(aItem, 1, aMeta));
                    for (ItemStack tStack12 : GT_OreDictUnificator.getOres("ingotSteel")) {
                        GT_Mod.addBlastRecipe(new ItemStack(aItem, 1, aMeta), new ItemStack(tStack12.getItem(), 1, tStack12.getItemDamage()), GT_MetaItem_Material.instance.getStack(5, 2), GT_MetaItem_Dust.instance.getStack(63, 4), 2000, 128, 3000);
                    }
                } else if (aEventName.equals("ingotSteel")) {
                    GT_ModHandler.addPulverisationRecipe(new ItemStack(aItem, 1, aMeta), GT_MetaItem_Dust.instance.getStack(26, 1), null, 0, false);
                    GT_ModHandler.removeFurnaceSmelting(null, new ItemStack(aItem, 1, aMeta));
                    for (ItemStack tStack12 : GT_OreDictUnificator.getOres("ingotTungsten")) {
                        GT_Mod.addBlastRecipe(new ItemStack(tStack12.getItem(), 1, tStack12.getItemDamage()), new ItemStack(aItem, 1, aMeta), GT_MetaItem_Material.instance.getStack(5, 2), GT_MetaItem_Dust.instance.getStack(63, 4), 2000, 128, 3000);
                    }
                } else if (aEventName.equals("ingotLead")) {
                    GT_ModHandler.addPulverisationRecipe(new ItemStack(aItem, 1, aMeta), GT_MetaItem_Dust.instance.getStack(23, 1), null, 0, false);
                } else if (aEventName.equals("ingotZinc")) {
                    for (ItemStack tStack12 : GT_OreDictUnificator.getOres("ingotCopper")) {
                        GT_Mod.addAlloySmelterRecipe(new ItemStack(tStack12.getItem(), 3, tStack12.getItemDamage()), new ItemStack(aItem, 1, aMeta), GT_OreDictUnificator.get("ingotBrass", null, 4), 200, 16);
                    }
                    for (ItemStack tStack12 : GT_OreDictUnificator.getOres("ingotCopper")) {
                        GT_ModHandler.addInductionSmelterRecipe(new ItemStack(tStack12.getItem(), 3, tStack12.getItemDamage()), new ItemStack(aItem, 1, aMeta), GT_OreDictUnificator.get("ingotBrass", null, 4), null, 400, 0);
                    }
                    GT_ModHandler.addPulverisationRecipe(new ItemStack(aItem, 1, aMeta), GT_MetaItem_Dust.instance.getStack(24, 1), null, 0, false);
                } else if (aEventName.equals("ingotBrass")) {
                    GT_ModHandler.addPulverisationRecipe(new ItemStack(aItem, 1, aMeta), GT_MetaItem_Dust.instance.getStack(25, 1), null, 0, false);
                } else if (aEventName.equals("ingotPlatinum")) {
                    GT_ModHandler.addPulverisationRecipe(new ItemStack(aItem, 1, aMeta), GT_MetaItem_Dust.instance.getStack(27, 1), null, 0, false);
                } else if (aEventName.equals("ingotNickel")) {
                    for (ItemStack tStack12 : GT_OreDictUnificator.getOres("ingotIron")) {
                        GT_Mod.addAlloySmelterRecipe(new ItemStack(tStack12.getItem(), 2, tStack12.getItemDamage()), new ItemStack(aItem, 1, aMeta), GT_OreDictUnificator.get("ingotInvar", null, 3), 150, 16);
                    }
                    GT_ModHandler.addPulverisationRecipe(new ItemStack(aItem, 1, aMeta), GT_MetaItem_Dust.instance.getStack(28, 1), null, 0, false);
                } else {
                    System.out.println("Ingot Name: " + aEvent.Name + " !!!Unknown Ingot detected!!! Please report to GregTech Intergalactical for additional compatiblity. This is not an Error, it's just an Information.");
                }
            }
        } else if (aEventName.startsWith("plasma_")) {
            GT_Mod.addFuel(new ItemStack(aItem, 1, aMeta), GT_ModHandler.getCapsuleCellContainerCount(new ItemStack(aItem, 1, aMeta)) == 1 ? GT_ModHandler.getEmptyCell(1) : null, 8192, 4);
            GT_Mod.addVacuumFreezerRecipe(new ItemStack(aItem, 1, aMeta), GT_ModHandler.getCapsuleCellContainerCount(new ItemStack(aItem, 1, aMeta)) == 1 ? GT_OreDictUnificator.getFirstCapsulatedOre(aEventName.replaceFirst("plasma_", "molecule_"), 1) : GT_OreDictUnificator.getFirstUnCapsulatedOre(aEventName.replaceFirst("plasma_", "molecule_"), 1), 100);
        } else if (aEventName.startsWith("molecule_")) {
            if (aEventName.equals("molecule_1h")) {
                if (GT_ModHandler.getCapsuleCellContainerCount(new ItemStack(aItem, 1, aMeta)) == 1) {
                    GT_Mod.addChemicalRecipe(GT_MetaItem_Cell.instance.getStack(8, 1), new ItemStack(aItem, 4, aMeta), GT_MetaItem_Cell.instance.getStack(9, 5), 3500);
                    GT_Mod.addChemicalRecipe(new ItemStack(aItem, 4, aMeta), GT_ModHandler.getIC2Item("airCell", 1), GT_ModHandler.getIC2Item("waterCell", 5), 10);
                }
                tCellCount = 4 * GT_ModHandler.getCapsuleCellContainerCount(new ItemStack(aItem, 1, aMeta)) - 1;
                GT_Mod.addCentrifugeRecipe(new ItemStack(aItem, 4, aMeta), tCellCount < 0 ? -tCellCount : 0, GT_Mod.getGregTechItem(2, 1, 1), null, null, tCellCount > 0 ? GT_ModHandler.getEmptyCell(tCellCount) : null, 3000);
            } else if (aEventName.equals("molecule_1he")) {
                tCellCount = 16 * GT_ModHandler.getCapsuleCellContainerCount(new ItemStack(aItem, 1, aMeta)) - 1;
                GT_Mod.addCentrifugeRecipe(new ItemStack(aItem, 16, aMeta), tCellCount < 0 ? -tCellCount : 0, GT_Mod.getGregTechItem(2, 1, 6), null, null, tCellCount > 0 ? GT_ModHandler.getEmptyCell(tCellCount) : null, 10000);
            } else if (aEventName.equals("molecule_1d") || aEventName.equals("molecule_1h2")) {
                ArrayList<ItemStack> tList = GT_OreDictUnificator.getOres("molecule_1t");
                tList.addAll(GT_OreDictUnificator.getOres("molecule_1h3"));
                for (ItemStack tStack1 : tList) {
                    GT_Mod.addFusionReactorRecipe(new ItemStack(tStack1.getItem(), 1, tStack1.getItemDamage()), new ItemStack(aItem, 1, aMeta), GT_MetaItem_Cell.instance.getStack(131, 1), 128, -4096, 40000000);
                }
                for (ItemStack tStack1 : GT_OreDictUnificator.getOres("molecule_1he3")) {
                    GT_Mod.addFusionReactorRecipe(new ItemStack(tStack1.getItem(), 1, tStack1.getItemDamage()), new ItemStack(aItem, 1, aMeta), GT_MetaItem_Cell.instance.getStack(131, 1), 128, -2048, 60000000);
                }
                tCellCount = 4 * GT_ModHandler.getCapsuleCellContainerCount(new ItemStack(aItem, 1, aMeta)) - 1;
                GT_Mod.addCentrifugeRecipe(new ItemStack(aItem, 4, aMeta), tCellCount < 0 ? -tCellCount : 0, GT_Mod.getGregTechItem(2, 1, 2), null, null, tCellCount > 0 ? GT_ModHandler.getEmptyCell(tCellCount) : null, 3000);
            } else if (aEventName.equals("molecule_1t") || aEventName.equals("molecule_1h3")) {
                ArrayList<ItemStack> tList = GT_OreDictUnificator.getOres("molecule_1d");
                tList.addAll(GT_OreDictUnificator.getOres("molecule_1h2"));
                for (ItemStack tStack1 : tList) {
                    GT_Mod.addFusionReactorRecipe(new ItemStack(aItem, 1, aMeta), new ItemStack(tStack1.getItem(), 1, tStack1.getItemDamage()), GT_MetaItem_Cell.instance.getStack(131, 1), 128, -4096, 40000000);
                }
            } else if (aEventName.equals("molecule_1he3")) {
                ArrayList<ItemStack> tList = GT_OreDictUnificator.getOres("molecule_1d");
                tList.addAll(GT_OreDictUnificator.getOres("molecule_1h2"));
                for (ItemStack tStack1 : tList) {
                    GT_Mod.addFusionReactorRecipe(new ItemStack(aItem, 1, aMeta), new ItemStack(tStack1.getItem(), 1, tStack1.getItemDamage()), GT_MetaItem_Cell.instance.getStack(131, 1), 128, -2048, 60000000);
                }
            } else if (aEventName.equals("molecule_1w")) {
                for (ItemStack tStack1 : GT_OreDictUnificator.getOres("molecule_1li")) {
                    GT_Mod.addFusionReactorRecipe(new ItemStack(aItem, 1, aMeta), new ItemStack(tStack1.getItem(), 1, tStack1.getItemDamage()), GT_ModHandler.getIC2Item("iridiumOre", 1), 512, Short.MIN_VALUE, 150000000);
                }
                for (ItemStack tStack1 : GT_OreDictUnificator.getOres("molecule_1be")) {
                    GT_Mod.addFusionReactorRecipe(new ItemStack(aItem, 1, aMeta), new ItemStack(tStack1.getItem(), 1, tStack1.getItemDamage()), GT_MetaItem_Dust.instance.getStack(27, 1), 512, Short.MIN_VALUE, 100000000);
                }
            } else if (aEventName.equals("molecule_1li")) {
                for (ItemStack tStack1 : GT_OreDictUnificator.getOres("molecule_1w")) {
                    GT_Mod.addFusionReactorRecipe(new ItemStack(tStack1.getItem(), 1, tStack1.getItemDamage()), new ItemStack(aItem, 1, aMeta), GT_ModHandler.getIC2Item("iridiumOre", 1), 512, Short.MIN_VALUE, 150000000);
                }
            } else if (aEventName.equals("molecule_1be")) {
                for (ItemStack tStack1 : GT_OreDictUnificator.getOres("molecule_1w")) {
                    GT_Mod.addFusionReactorRecipe(new ItemStack(tStack1.getItem(), 1, tStack1.getItemDamage()), new ItemStack(aItem, 1, aMeta), GT_MetaItem_Dust.instance.getStack(27, 1), 512, Short.MIN_VALUE, 100000000);
                }
            } else if (aEventName.equals("molecule_1c_4h") || aEventName.equals("molecule_1me")) {
                tCellCount = 5 * GT_ModHandler.getCapsuleCellContainerCount(new ItemStack(aItem, 1, aMeta)) - 5;
                GT_Mod.addElectrolyzerRecipe(new ItemStack(aItem, 5, aMeta), tCellCount < 0 ? -tCellCount : 0, GT_Mod.getGregTechItem(2, 4, 0), GT_Mod.getGregTechItem(2, 1, 8), null, tCellCount > 0 ? GT_ModHandler.getEmptyCell(tCellCount) : null, 150, 50);
            } else if (aEventName.equals("molecule_1si")) {
                tCellCount = 2 * GT_ModHandler.getCapsuleCellContainerCount(new ItemStack(aItem, 1, aMeta));
                GT_Mod.addBlastRecipe(new ItemStack(aItem, 2, aMeta), null, GT_MetaItem_Material.instance.getStack(36, 1), tCellCount > 0 ? GT_ModHandler.getEmptyCell(tCellCount) : null, 1000, 128, 1500);
            } else if (aEventName.equals("molecule_1c")) {
                if (GT_ModHandler.getCapsuleCellContainerCount(new ItemStack(aItem, 1, aMeta)) == 1) {
                    GT_Mod.addChemicalRecipe(new ItemStack(aItem, 1, aMeta), GT_MetaItem_Cell.instance.getStack(0, 4), GT_MetaItem_Cell.instance.getStack(9, 5), 3500);
                    GT_Mod.addChemicalRecipe(GT_MetaItem_Cell.instance.getStack(15, 1), new ItemStack(aItem, 1, aMeta), GT_MetaItem_Cell.instance.getStack(39, 2), 1500);
                    GT_Mod.addChemicalRecipe(GT_MetaItem_Cell.instance.getStack(11, 1), new ItemStack(aItem, 1, aMeta), GT_MetaItem_Cell.instance.getStack(33, 2), 250);
                }
            } else if (aEventName.equals("molecule_1ca")) {
                GT_Mod.addChemicalRecipe(new ItemStack(aItem, 1, aMeta), GT_MetaItem_Cell.instance.getStack(8, 1), GT_MetaItem_Cell.instance.getStack(33, 2), 250);
            } else if (aEventName.equals("molecule_1na")) {
                if (GT_ModHandler.getCapsuleCellContainerCount(new ItemStack(aItem, 1, aMeta)) == 1) {
                    GT_Mod.addChemicalRecipe(GT_MetaItem_Cell.instance.getStack(36, 1), new ItemStack(aItem, 1, aMeta), GT_MetaItem_Cell.instance.getStack(37, 2), 100);
                }
            } else if (aEventName.equals("molecule_1s")) {
                if (GT_ModHandler.getCapsuleCellContainerCount(new ItemStack(aItem, 1, aMeta)) == 1) {
                    GT_Mod.addChemicalRecipe(new ItemStack(aItem, 1, aMeta), GT_MetaItem_Cell.instance.getStack(12, 1), GT_MetaItem_Cell.instance.getStack(37, 2), 100);
                    GT_Mod.addCentrifugeRecipe(new ItemStack(aItem, 1, aMeta), 0, GT_OreDictUnificator.get("dustSulfur", null, 1), null, null, GT_ModHandler.getEmptyCell(1), 40);
                    GT_Mod.addChemicalRecipe(new ItemStack(aItem, 1, aMeta), GT_ModHandler.getIC2Item("waterCell", 2), GT_MetaItem_Cell.instance.getStack(40, 3), 1150);
                }
            } else if (aEventName.equals("molecule_1na_1s")) {
                if (GT_ModHandler.getCapsuleCellContainerCount(new ItemStack(aItem, 1, aMeta)) == 1) {
                    GT_Mod.addChemicalRecipe(new ItemStack(aItem, 2, aMeta), GT_ModHandler.getIC2Item("airCell", 3), GT_MetaItem_Cell.instance.getStack(32, 5), 4000);
                }
            } else if (!aEventName.equals("molecule_1cl") && !aEventName.equals("molecule_1k")) {
                if (aEventName.equals("molecule_1n")) {
                    if (GT_ModHandler.getCapsuleCellContainerCount(new ItemStack(aItem, 1, aMeta)) == 1) {
                        GT_Mod.addChemicalRecipe(new ItemStack(aItem, 1, aMeta), GT_ModHandler.getIC2Item("airCell", 1), GT_MetaItem_Cell.instance.getStack(38, 2), 1250);
                        GT_Mod.addChemicalRecipe(new ItemStack(aItem, 1, aMeta), GT_MetaItem_Cell.instance.getStack(8, 1), GT_MetaItem_Cell.instance.getStack(39, 2), 1500);
                    }
                } else if (aEventName.equals("molecule_1n_1c")) {
                    if (GT_ModHandler.getCapsuleCellContainerCount(new ItemStack(aItem, 1, aMeta)) == 1) {
                        GT_Mod.addChemicalRecipe(new ItemStack(aItem, 3, aMeta), GT_ModHandler.getIC2Item("waterCell", 3), GT_MetaItem_Cell.instance.getStack(34, 6), 1750);
                    }
                } else if (aEventName.equals("molecule_2h_1s_4o")) {
                    GT_Mod.addElectrolyzerRecipe(new ItemStack(aItem, 7, aMeta), 0, GT_MetaItem_Cell.instance.getStack(0, 2), GT_MetaItem_Cell.instance.getStack(36, 1), GT_ModHandler.getIC2Item("airCell", 2), GT_ModHandler.getEmptyCell(2), 40, 100);
                } else if (!aEventName.equals("molecule_1n_2o")) {
                    if (aEventName.equals("molecule_1hg")) {
                        if (GT_ModHandler.getCapsuleCellContainerCount(new ItemStack(aItem, 1, aMeta)) == 1 && GT_ModHandler.mTCResource != null) {
                            GT_Mod.addCentrifugeRecipe(new ItemStack(aItem, 1, aMeta), 0, new ItemStack(GT_ModHandler.mTCResource.getItem(), 1, 3), null, null, GT_ModHandler.getEmptyCell(1), 40);
                        }
                    } else if (aEventName.equals("molecule_1ca_1c_3o")) {
                        if (GT_ModHandler.getCapsuleCellContainerCount(new ItemStack(aItem, 1, aMeta)) == 1) {
                            GT_Mod.addCentrifugeRecipe(new ItemStack(aItem, 1, aMeta), 0, GT_OreDictUnificator.get("dustCalcite", null, 1), null, null, GT_ModHandler.getEmptyCell(1), 40);
                        }
                    } else if (!(aEventName.equals("molecule_2na_2s_8o") || aEventName.equals("molecule_2o") || aEventName.equals("molecule_1o"))) {
                        if (aEventName.equals("molecule_3c_5h_3n_9o")) {
                            if (GT_ModHandler.getCapsuleCellContainerCount(new ItemStack(aItem, 1, aMeta)) == 1) {
                                GT_Mod.addChemicalRecipe(new ItemStack(aItem, 1, aMeta), GT_ModHandler.getIC2Item("coalfuelCell", 4), GT_MetaItem_Cell.instance.getStack(35, 5), 250);
                                GT_Mod.addChemicalRecipe(new ItemStack(aItem, 1, aMeta), GT_MetaItem_Cell.instance.getStack(18, 4), GT_MetaItem_Cell.instance.getStack(22, 5), 250);
                            }
                        } else {
                            System.out.println("Molecule Name: " + aEvent.Name + " !!!Unknown Molecule detected!!! Please report to GregTech Intergalactical for additional compatiblity. This is not an Error, it's just an Information.");
                        }
                    }
                }
            }
        } else if (aEventName.startsWith("block")) {
            if (aItem instanceof ItemBlock && GT_Mod.instance.mBlockStackSize < aItem.getItemStackLimit()) {
                aItem.setMaxStackSize(GT_Mod.instance.mBlockStackSize);
            }
            if (aEventName.equals("blockQuicksilver")) {
                System.out.println("'blockQuickSilver'?, In which Ice Desert can you actually place this as a solid Block?");
            }
            ItemStack tStack1 = GT_OreDictUnificator.getFirstOre(aEventName.replaceFirst("block", "ingot"), 1);
            ItemStack tStack2 = GT_OreDictUnificator.getFirstOre(aEventName.replaceFirst("block", "gem"), 1);
            ItemStack tStack3 = GT_OreDictUnificator.getFirstOre(aEventName.replaceFirst("block", "dust"), 1);
            GT_ModHandler.removeRecipe(new ItemStack[]{new ItemStack(aItem, 1, aMeta)});
            if (tStack1 != null) {
                GT_ModHandler.removeRecipe(new ItemStack[]{tStack1, tStack1, tStack1, tStack1, tStack1, tStack1, tStack1, tStack1, tStack1});
            }
            if (tStack2 != null) {
                GT_ModHandler.removeRecipe(new ItemStack[]{tStack2, tStack2, tStack2, tStack2, tStack2, tStack2, tStack2, tStack2, tStack2});
            }
            if (tStack3 != null) {
                GT_ModHandler.removeRecipe(new ItemStack[]{tStack3, tStack3, tStack3, tStack3, tStack3, tStack3, tStack3, tStack3, tStack3});
            }
            if (GT_Mod.mConfig.addAdvConfig("StorageBlockCrafting", aEventName, false)) {
                if (tStack1 == null && tStack2 == null && tStack3 != null) {
                    GT_ModHandler.addCraftingRecipe(GT_OreDictUnificator.getFirstOre(aEventName, 1), "XXX", "XXX", "XXX", Character.valueOf('X'), aEventName.replaceFirst("block", "dust"));
                }
                if (tStack2 != null) {
                    GT_ModHandler.addCraftingRecipe(GT_OreDictUnificator.getFirstOre(aEventName, 1), "XXX", "XXX", "XXX", Character.valueOf('X'), aEventName.replaceFirst("block", "gem"));
                }
                if (tStack1 != null) {
                    GT_ModHandler.addCraftingRecipe(GT_OreDictUnificator.getFirstOre(aEventName, 1), "XXX", "XXX", "XXX", Character.valueOf('X'), aEventName.replaceFirst("block", "ingot"));
                }
            }
            if (tStack1 != null) {
                tStack1.stackSize = 9;
            }
            if (tStack2 != null) {
                tStack2.stackSize = 9;
            }
            if (tStack3 != null) {
                tStack3.stackSize = 9;
            }
            if (GT_Mod.mConfig.addAdvConfig("StorageBlockDeCrafting", aEventName, tStack2 != null)) {
                GT_ModHandler.addShapelessCraftingRecipe(tStack3, aEventName);
                GT_ModHandler.addShapelessCraftingRecipe(tStack1, aEventName);
                GT_ModHandler.addShapelessCraftingRecipe(tStack2, aEventName);
            }
            if (tStack1 != null) {
                GT_ModHandler.addSmeltingRecipe(new ItemStack(aItem, 1, aMeta), tStack1.copy());
            }
            if (tStack3 != null) {
                GT_ModHandler.addPulverisationRecipe(new ItemStack(aItem, 1, aMeta), tStack3.copy(), null, 0, false);
            }
            if (tStack1 == null && tStack2 == null && tStack3 != null) {
                GT_ModHandler.addCompressionRecipe(tStack3.copy(), GT_OreDictUnificator.getFirstOre(aEventName, 1));
            }
        } else if (aEventName.startsWith("nugget")) {
            if (aEventName.equals("nuggetGold")) {
                GT_Mod.addAssemblerRecipe(GT_ModHandler.mBCStoneGear == null ? new ItemStack(Block.cobblestone, 1) : GT_ModHandler.mBCStoneGear, new ItemStack(aItem, 4, aMeta), GT_ModHandler.getRCItem("part.gear.gold.plate", 1), 800, 1);
            }
            if (aEventName.equals("nuggetIridium") || aEventName.equals("nuggetOsmium") || aEventName.equals("nuggetUranium") || aEventName.equals("nuggetPlutonium") || aEventName.equals("nuggetThorium")) {
                GT_ModHandler.addCompressionRecipe(new ItemStack(aItem, 9, aMeta), GT_OreDictUnificator.getFirstOre(aEventName.replaceFirst("nugget", "ingot"), 1));
            } else {
                GT_Mod.addAlloySmelterRecipe(new ItemStack(aItem, 9, aMeta), null, GT_OreDictUnificator.getFirstOre(aEventName.replaceFirst("nugget", "ingot"), 1), 200, 1);
            }
            GT_ModHandler.addShapelessCraftingRecipe(GT_OreDictUnificator.getFirstOre(aEventName.replaceFirst("nugget", "ingot"), 1), aEventName, aEventName, aEventName, aEventName, aEventName, aEventName, aEventName, aEventName, aEventName);
            GT_ModHandler.addShapelessCraftingRecipe(GT_OreDictUnificator.getFirstOre(aEventName, 9), aEventName.replaceFirst("nugget", "ingot"));
        } else if (aEventName.startsWith("element_")) {
            System.out.println("Depricated Prefix 'element_' @ " + aEvent.Name + " please change to 'molecule_'");
        } else if (aEventName.startsWith("cell_")) {
            System.out.println("Depricated Prefix 'cell_' @ " + aEvent.Name + " Cells are now detected automatically, if you register as 'molecule_', so you don't need to register with this prefix any longer");
        } else if (!aEventName.startsWith("element") && !aEventName.startsWith("cell")) {
            if (aEventName.startsWith("crafting")) {
                if (aEventName.equals("craftingLiBattery")) {
                    GT_Mod.addAssemblerRecipe(new ItemStack(aItem, 1, aMeta), GT_ModHandler.getIC2Item("cropnalyzer", 1, -1), GT_Mod.getGregTechItem(63, 1, GT_Mod.getGregTechItem(63, 1, 0).getMaxDamage() - 1), 12800, 16);
                    Iterator tIterator1 = GT_OreDictUnificator.getOres("plateAluminium").iterator();
                    while (tIterator1.hasNext()) {
                        GT_Mod.addAssemblerRecipe(new ItemStack(aItem, 1, aMeta), ((ItemStack)tIterator1.next()).copy().splitStack(4), GT_MetaItem_Component.instance.getStack(26, 1), 3200, 4);
                    }
                }
            } else if (!(aEventName.startsWith("clump") || aEventName.startsWith("glass") || aEventName.startsWith("gear") || aEventName.startsWith("flower"))) {
                if (aEventName.startsWith("dye")) {
                    GT_OreDictUnificator.addDye(new ItemStack(aItem, 1, aMeta));
                } else if (aEventName.startsWith("plate")) {
                    if (!aEventName.equals("plateIridiumAlloy")) {
                        GT_ModHandler.removeRecipe(new ItemStack(aItem, 1, aMeta));
                    }
                    GT_ModHandler.addSmeltingRecipe(new ItemStack(aItem, 1, aMeta), GT_OreDictUnificator.getFirstOre(aEventName.replaceFirst("plate", "ingot"), 1));
                    GT_ModHandler.addPulverisationRecipe(new ItemStack(aItem, 1, aMeta), GT_OreDictUnificator.getFirstOre(aEventName.replaceFirst("plate", "dust"), 1), null, 0, false);
                    if (aEventName.equals("plateCopper")) {
                        GT_ModHandler.addCompressionRecipe(new ItemStack(aItem, 8, aMeta), GT_ModHandler.getIC2Item("denseCopperPlate", 1));
                    } else if (aEventName.equals("plateIron")) {
                        GT_Mod.addBenderRecipe(new ItemStack(aItem, 3, aMeta), GT_ModHandler.getRCItem("part.rail.standard", 4), 150, 20);
                    } else if (aEventName.equals("plateBronze")) {
                        GT_Mod.addAssemblerRecipe(new ItemStack(aItem, 8, aMeta), GT_MetaItem_Component.instance.getStack(22, 1), GT_MetaItem_Component.instance.getStack(33, 1), 400, 8);
                        GT_Mod.addBenderRecipe(new ItemStack(aItem, 1, aMeta), GT_ModHandler.getRCItem("part.rail.standard", 1), 50, 20);
                    } else if (aEventName.equals("plateBrass")) {
                        GT_Mod.addAssemblerRecipe(new ItemStack(aItem, 8, aMeta), GT_MetaItem_Component.instance.getStack(22, 1), GT_MetaItem_Component.instance.getStack(34, 1), 400, 8);
                    } else if (aEventName.equals("plateRefinedIron")) {
                        GT_Mod.addAssemblerRecipe(new ItemStack(aItem, 2, aMeta), GT_ModHandler.getIC2Item("electronicCircuit", 1), GT_MetaItem_Component.instance.getStack(22, 4), 800, 16);
                        GT_Mod.addBenderRecipe(new ItemStack(aItem, 3, aMeta), GT_ModHandler.getRCItem("part.rail.standard", 5), 150, 20);
                        Iterator tIterator1 = GT_OreDictUnificator.getOres("plateElectrum").iterator();
                        while (tIterator1.hasNext()) {
                            GT_Mod.addAssemblerRecipe(new ItemStack(aItem, 1, aMeta), ((ItemStack)tIterator1.next()).copy().splitStack(2), GT_MetaItem_Component.instance.getStack(48, 2), 800, 1);
                        }
                    } else if (aEventName.equals("plateSteel")) {
                        GT_Mod.addAssemblerRecipe(new ItemStack(aItem, 8, aMeta), GT_MetaItem_Component.instance.getStack(22, 1), GT_MetaItem_Component.instance.getStack(35, 1), 400, 8);
                        GT_Mod.addBenderRecipe(new ItemStack(aItem, 3, aMeta), GT_ModHandler.getRCItem("part.rail.standard", 8), 150, 30);
                    } else if (aEventName.equals("plateTitanium")) {
                        GT_Mod.addAssemblerRecipe(new ItemStack(aItem, 8, aMeta), GT_MetaItem_Component.instance.getStack(22, 1), GT_MetaItem_Component.instance.getStack(36, 1), 400, 8);
                        GT_Mod.addBenderRecipe(new ItemStack(aItem, 3, aMeta), GT_ModHandler.getRCItem("part.rail.standard", 16), 150, 30);
                    } else if (aEventName.equals("plateTungsten")) {
                        GT_Mod.addBenderRecipe(new ItemStack(aItem, 3, aMeta), GT_ModHandler.getRCItem("part.rail.standard", 16), 150, 30);
                    } else if (aEventName.equals("plateTungstenSteel")) {
                        GT_Mod.addBenderRecipe(new ItemStack(aItem, 1, aMeta), GT_ModHandler.getRCItem("part.rail.reinforced", 8), 100, 30);
                    } else if (aEventName.equals("plateAluminium")) {
                        Iterator tIterator1 = GT_OreDictUnificator.getOres("craftingLiBattery").iterator();
                        while (tIterator1.hasNext()) {
                            GT_Mod.addAssemblerRecipe(((ItemStack)tIterator1.next()).copy().splitStack(1), new ItemStack(aItem, 4, aMeta), GT_MetaItem_Component.instance.getStack(26, 1), 3200, 4);
                        }
                        GT_Mod.addAssemblerRecipe(new ItemStack(aItem, 2, aMeta), GT_ModHandler.getIC2Item("electronicCircuit", 1), GT_MetaItem_Component.instance.getStack(22, 3), 800, 16);
                        GT_Mod.addAssemblerRecipe(new ItemStack(aItem, 8, aMeta), GT_MetaItem_Component.instance.getStack(22, 1), GT_MetaItem_Component.instance.getStack(32, 1), 400, 8);
                        GT_Mod.addBenderRecipe(new ItemStack(aItem, 3, aMeta), GT_ModHandler.getRCItem("part.rail.standard", 2), 150, 10);
                        GT_Mod.addAssemblerRecipe(GT_ModHandler.getIC2Item("generator", 1), new ItemStack(aItem, 4, aMeta), GT_ModHandler.getIC2Item("waterMill", 2), 6400, 8);
                        tIterator1 = GT_OreDictUnificator.getOres("plateElectrum").iterator();
                        while (tIterator1.hasNext()) {
                            GT_Mod.addAssemblerRecipe(new ItemStack(aItem, 1, aMeta), ((ItemStack)tIterator1.next()).copy().splitStack(2), GT_MetaItem_Component.instance.getStack(48, 2), 800, 1);
                        }
                    } else if (aEventName.equals("plateElectrum")) {
                        GT_Mod.addAssemblerRecipe(GT_ModHandler.getIC2Item("electronicCircuit", 1), new ItemStack(aItem, 2, aMeta), GT_MetaItem_Component.instance.getStack(49, 1), 1600, 2);
                        Iterator tIterator1 = GT_OreDictUnificator.getOres("plateRefinedIron").iterator();
                        while (tIterator1.hasNext()) {
                            GT_Mod.addAssemblerRecipe(((ItemStack)tIterator1.next()).copy().splitStack(1), new ItemStack(aItem, 2, aMeta), GT_MetaItem_Component.instance.getStack(48, 2), 800, 1);
                        }
                        tIterator1 = GT_OreDictUnificator.getOres("plateAluminium").iterator();
                        while (tIterator1.hasNext()) {
                            GT_Mod.addAssemblerRecipe(((ItemStack)tIterator1.next()).copy().splitStack(1), new ItemStack(aItem, 2, aMeta), GT_MetaItem_Component.instance.getStack(48, 2), 800, 1);
                        }
                        tIterator1 = GT_OreDictUnificator.getOres("plateSilicon").iterator();
                        while (tIterator1.hasNext()) {
                            GT_Mod.addAssemblerRecipe(((ItemStack)tIterator1.next()).copy().splitStack(1), new ItemStack(aItem, 4, aMeta), GT_MetaItem_Component.instance.getStack(49, 2), 1600, 2);
                        }
                    } else if (aEventName.equals("plateSilicon")) {
                        Iterator tIterator1 = GT_OreDictUnificator.getOres("plateElectrum").iterator();
                        while (tIterator1.hasNext()) {
                            GT_Mod.addAssemblerRecipe(new ItemStack(aItem, 1, aMeta), ((ItemStack)tIterator1.next()).copy().splitStack(4), GT_MetaItem_Component.instance.getStack(49, 2), 1600, 2);
                        }
                    } else if (aEventName.equals("platePlatinum")) {
                        GT_Mod.addAssemblerRecipe(new ItemStack(aItem, 1, aMeta), GT_ModHandler.getIC2Item("advancedCircuit", 1), GT_MetaItem_Component.instance.getStack(50, 1), 3200, 4);
                    } else if (aEventName.equals("plateMagnalium")) {
                        GT_Mod.addAssemblerRecipe(GT_ModHandler.getIC2Item("generator", 1), new ItemStack(aItem, 2, aMeta), GT_ModHandler.getIC2Item("windMill", 1), 6400, 8);
                    }
                } else if (!(aEventName.startsWith("dirtyGravel") || aEventName.startsWith("cleanGravel") || aEventName.startsWith("crystalline") || aEventName.startsWith("reduced"))) {
                    if (aEventName.startsWith("paper")) {
                        GT_OreDictUnificator.addAssociation(aEvent.Name, new ItemStack(aItem, 1, aMeta));
                    } else if (aEventName.startsWith("book")) {
                        GT_OreDictUnificator.addAssociation(aEvent.Name, new ItemStack(aItem, 1, aMeta));
                    } else if (!aEventName.startsWith("FZ.") && !aEventName.startsWith("MiscPeripherals$")) {
                        if (aEventName.equals("gasWood")) {
                            tCellCount = 16 * GT_ModHandler.getCapsuleCellContainerCount(new ItemStack(aItem, 1, aMeta)) - 16;
                            GT_Mod.addElectrolyzerRecipe(new ItemStack(aItem, 16, aMeta), tCellCount < 0 ? -tCellCount : 0, GT_Mod.getGregTechItem(2, 4, 0), GT_Mod.getGregTechItem(2, 8, 8), GT_Mod.getGregTechItem(2, 4, 9), tCellCount > 0 ? GT_ModHandler.getEmptyCell(tCellCount) : null, 200, 100);
                        } else if (aEventName.equals("woodRubber") || aEventName.equals("logRubber")) {
                            if (aItem instanceof ItemBlock && GT_Mod.instance.mWoodStackSize < aItem.getItemStackLimit()) {
                                aItem.setMaxStackSize(GT_Mod.instance.mWoodStackSize);
                            }
                            GT_Mod.addCentrifugeRecipe(new ItemStack(aItem, 16, aMeta), 5, GT_ModHandler.getIC2Item("resin", 8), GT_ModHandler.getIC2Item("plantBall", 6), GT_Mod.getGregTechItem(2, 1, 9), GT_Mod.getGregTechItem(2, 4, 8), 5000);
                            GT_ModHandler.addSawmillRecipe(new ItemStack(aItem, 1, aMeta), GT_ModHandler.getIC2Item("resin", 1), GT_MetaItem_Dust.instance.getStack(15, 16));
                        } else if (aEventName.startsWith("log")) {
                            if (aItem instanceof ItemBlock && GT_Mod.instance.mWoodStackSize < aItem.getItemStackLimit()) {
                                aItem.setMaxStackSize(GT_Mod.instance.mWoodStackSize);
                            }
                            if (aMeta < 0) {
                                for (int i = 0; i < 16; ++i) {
                                    GT_OreDictUnificator.addAssociation(aEvent.Name, new ItemStack(aItem, 1, i));
                                    ItemStack tStack2 = new ItemStack(aItem, 1, i);
                                    ItemStack tStack1 = GT_ModHandler.getRecipeOutput(new ItemStack[]{tStack2, null, null, null, null, null, null, null, null});
                                    if (tStack1 == null) continue;
                                    tStack1 = tStack1.copy();
                                    tStack1.stackSize = tStack1.stackSize * 3 / 2;
                                    GT_ModHandler.addSawmillRecipe(tStack2, tStack1, GT_MetaItem_Dust.instance.getStack(15, 1));
                                }
                            } else {
                                GT_OreDictUnificator.addAssociation(aEvent.Name, new ItemStack(aItem, 1, aMeta));
                                ItemStack tStack2 = new ItemStack(aItem, 1, aMeta);
                                ItemStack tStack1 = GT_ModHandler.getRecipeOutput(new ItemStack[]{tStack2, null, null, null, null, null, null, null, null});
                                if (tStack1 != null) {
                                    tStack1 = tStack1.copy();
                                    tStack1.stackSize = tStack1.stackSize * 3 / 2;
                                    GT_ModHandler.addSawmillRecipe(tStack2, tStack1, GT_MetaItem_Dust.instance.getStack(15, 1));
                                }
                            }
                        } else if (aEventName.startsWith("slabWood")) {
                            if (aItem instanceof ItemBlock && GT_Mod.instance.mPlankStackSize < aItem.getItemStackLimit()) {
                                aItem.setMaxStackSize(GT_Mod.instance.mPlankStackSize);
                            }
                            if (aMeta < 0) {
                                for (int i = 0; i < 16; ++i) {
                                    GT_OreDictUnificator.addAssociation(aEvent.Name, new ItemStack(aItem, 1, i));
                                    GT_ModHandler.addPulverisationRecipe(new ItemStack(aItem, 1, i), GT_OreDictUnificator.get("dustSmallWood", 2), null, 0, false);
                                    GT_Mod.addCannerRecipe(new ItemStack(aItem, 3, i), GT_ModHandler.getRCItem("liquid.creosote.cell", 1), GT_ModHandler.getRCItem("part.tie.wood", 1), GT_ModHandler.getEmptyCell(1), 200, 4);
                                    GT_Mod.addCannerRecipe(new ItemStack(aItem, 3, i), GT_ModHandler.getRCItem("liquid.creosote.bucket", 1), GT_ModHandler.getRCItem("part.tie.wood", 1), new ItemStack(Item.bucketEmpty, 1), 200, 4);
                                }
                            } else {
                                GT_OreDictUnificator.addAssociation(aEvent.Name, new ItemStack(aItem, 1, aMeta));
                                GT_ModHandler.addPulverisationRecipe(new ItemStack(aItem, 1, aMeta), GT_OreDictUnificator.get("dustSmallWood", 2), null, 0, false);
                                GT_Mod.addCannerRecipe(new ItemStack(aItem, 3, aMeta), GT_ModHandler.getRCItem("liquid.creosote.cell", 1), GT_ModHandler.getRCItem("part.tie.wood", 1), GT_ModHandler.getEmptyCell(1), 200, 4);
                                GT_Mod.addCannerRecipe(new ItemStack(aItem, 3, aMeta), GT_ModHandler.getRCItem("liquid.creosote.bucket", 1), GT_ModHandler.getRCItem("part.tie.wood", 1), new ItemStack(Item.bucketEmpty, 1), 200, 4);
                            }
                        } else if (aEventName.startsWith("plankWood")) {
                            if (aItem instanceof ItemBlock && GT_Mod.instance.mPlankStackSize < aItem.getItemStackLimit()) {
                                aItem.setMaxStackSize(GT_Mod.instance.mPlankStackSize);
                            }
                            if (aMeta < 0) {
                                for (int i = 0; i < 16; ++i) {
                                    GT_OreDictUnificator.addAssociation(aEvent.Name, new ItemStack(aItem, 1, i));
                                    GT_ModHandler.addPulverisationRecipe(new ItemStack(aItem, 1, i), GT_MetaItem_Dust.instance.getStack(15, 1), null, 0, false);
                                }
                            } else {
                                GT_OreDictUnificator.addAssociation(aEvent.Name, new ItemStack(aItem, 1, aMeta));
                                GT_ModHandler.addPulverisationRecipe(new ItemStack(aItem, 1, aMeta), GT_MetaItem_Dust.instance.getStack(15, 1), null, 0, false);
                            }
                            GT_Mod.addPlateCuttingRecipe(new ItemStack(aItem, 2, aMeta), GT_ModHandler.mBCWoodGear, 800, 1);
                        } else if (aEventName.startsWith("treeSapling")) {
                            if (aItem instanceof ItemBlock && GT_Mod.instance.mWoodStackSize < aItem.getItemStackLimit()) {
                                aItem.setMaxStackSize(GT_Mod.instance.mWoodStackSize);
                            }
                            if (aMeta < 0) {
                                for (int i = 0; i < 16; ++i) {
                                    GT_OreDictUnificator.addAssociation(aEvent.Name, new ItemStack(aItem, 1, i));
                                    GT_ModHandler.addCompressionRecipe(new ItemStack(aItem, 4, i), GT_ModHandler.getIC2Item("compressedPlantBall", 1));
                                    GT_ModHandler.addPulverisationRecipe(new ItemStack(aItem, 1, i), GT_MetaItem_SmallDust.instance.getStack(15, 2), null, 0, false);
                                }
                            } else {
                                GT_OreDictUnificator.addAssociation(aEvent.Name, new ItemStack(aItem, 1, aMeta));
                                GT_ModHandler.addCompressionRecipe(new ItemStack(aItem, 4, aMeta), GT_ModHandler.getIC2Item("compressedPlantBall", 1));
                                GT_ModHandler.addPulverisationRecipe(new ItemStack(aItem, 1, aMeta), GT_MetaItem_SmallDust.instance.getStack(15, 2), null, 0, false);
                            }
                        } else if (aEventName.equals("treeLeaves")) {
                            if (aItem instanceof ItemBlock && GT_Mod.instance.mWoodStackSize < aItem.getItemStackLimit()) {
                                aItem.setMaxStackSize(GT_Mod.instance.mWoodStackSize);
                            }
                            if (aMeta < 0) {
                                for (int i = 0; i < 16; ++i) {
                                    GT_OreDictUnificator.addAssociation(aEvent.Name, new ItemStack(aItem, 1, i));
                                    GT_ModHandler.addCompressionRecipe(new ItemStack(aItem, 8, i), GT_ModHandler.getIC2Item("compressedPlantBall", 1));
                                }
                            } else {
                                GT_OreDictUnificator.addAssociation(aEvent.Name, new ItemStack(aItem, 1, aMeta));
                                GT_ModHandler.addCompressionRecipe(new ItemStack(aItem, 8, aMeta), GT_ModHandler.getIC2Item("compressedPlantBall", 1));
                            }
                        } else if (aEventName.startsWith("stickWood")) {
                            GT_Mod.addAssemblerRecipe(new ItemStack(aItem, 1, aMeta), new ItemStack(Block.cobblestone, 1, -1), new ItemStack(Block.lever, 1), 400, 1);
                            GT_Mod.addAssemblerRecipe(new ItemStack(aItem, 1, aMeta), new ItemStack(Item.coal, 1, -1), new ItemStack(Block.torchWood, 4), 400, 1);
                            GT_Mod.addAssemblerRecipe(new ItemStack(aItem, 1, aMeta), GT_ModHandler.getIC2Item("resin", 1), new ItemStack(Block.torchWood, 4), 400, 1);
                            Iterator tIterator1 = GT_OreDictUnificator.getOres("dustRedstone").iterator();
                            while (tIterator1.hasNext()) {
                                GT_Mod.addAssemblerRecipe(new ItemStack(aItem, 1, aMeta), ((ItemStack)tIterator1.next()).copy().splitStack(1), new ItemStack(Block.torchRedstoneActive, 1), 400, 1);
                            }
                            if (aMeta < 0) {
                                for (int i = 0; i < 16; ++i) {
                                    GT_OreDictUnificator.addAssociation(aEvent.Name, new ItemStack(aItem, 1, i));
                                }
                            } else {
                                GT_OreDictUnificator.addAssociation(aEvent.Name, new ItemStack(aItem, 1, aMeta));
                            }
                        } else if (!aEventName.startsWith("stair") && !aEventName.startsWith("slab")) {
                            if (aEventName.equals("itemCopperWire") || aEventName.equals("copperWire")) {
                                GT_Mod.addAssemblerRecipe(GT_MetaItem_Component.instance.getStack(48, 1), new ItemStack(aItem, 3, aMeta), GT_ModHandler.getIC2Item("electronicCircuit", 1), 800, 1);
                                GT_Mod.addAssemblerRecipe(GT_ModHandler.getIC2Item("electronicCircuit", 1), new ItemStack(aItem, 1, aMeta), GT_ModHandler.getIC2Item("frequencyTransmitter", 1), 800, 1);
                            } else if (aEventName.equals("itemLazurite") || aEventName.equals("lazurite")) {
                                Iterator tIterator1 = GT_OreDictUnificator.getOres("dustGlowstone").iterator();
                                while (tIterator1.hasNext()) {
                                    GT_Mod.addAssemblerRecipe(new ItemStack(aItem, 1, aMeta), ((ItemStack)tIterator1.next()).copy().splitStack(1), GT_MetaItem_Component.instance.getStack(24, 2), 800, 2);
                                }
                            } else if (aEventName.equals("itemDiamond") || aEventName.equals("diamond")) {
                                GT_Mod.addAssemblerRecipe(GT_ModHandler.mBCGoldGear, new ItemStack(aItem, 4, aMeta), GT_ModHandler.mBCDiamondGear, 1600, 2);
                            } else if (!(aEventName.equals("itemIridium") || aEventName.equals("iridium") || aEventName.equals("itemTar") || aEventName.equals("tar") || aEventName.equals("fuelCoke") || aEventName.equals("coke") || aEventName.equals("itemBeeswax") || aEventName.equals("beeswax"))) {
                                if (aEventName.equals("itemBeeComb") || aEventName.equals("beeComb")) {
                                    GT_OreDictUnificator.addAssociation(aEvent.Name, new ItemStack(aItem, 1, aMeta));
                                } else if (!(aEventName.equals("itemForcicium") || aEventName.equals("ForciciumItem") || aEventName.equals("brickPeat") || aEventName.equals("peat") || aEventName.equals("itemRoyalJelly") || aEventName.equals("royalJelly") || aEventName.equals("itemHoneydew") || aEventName.equals("honeydew") || aEventName.equals("itemHoney") || aEventName.equals("honey") || aEventName.equals("itemPollen") || aEventName.equals("pollen") || aEventName.equals("itemReedTypha") || aEventName.equals("reedTypha") || aEventName.equals("itemSulfuricAcid") || aEventName.equals("sulfuricAcid"))) {
                                    if (aEventName.equals("itemRubber") || aEventName.equals("rubber")) {
                                        GT_Mod.addAssemblerRecipe(GT_ModHandler.getIC2Item("copperCableItem", 1), new ItemStack(aItem, 1, aMeta), GT_ModHandler.getIC2Item("insulatedCopperCableItem", 1), 100, 2);
                                        GT_Mod.addAssemblerRecipe(GT_ModHandler.getIC2Item("goldCableItem", 1), new ItemStack(aItem, 2, aMeta), GT_ModHandler.getIC2Item("doubleInsulatedGoldCableItem", 1), 200, 2);
                                        GT_Mod.addAssemblerRecipe(GT_ModHandler.getIC2Item("ironCableItem", 1), new ItemStack(aItem, 3, aMeta), GT_ModHandler.getIC2Item("trippleInsulatedIronCableItem", 1), 300, 2);
                                        GT_Mod.addAssemblerRecipe(GT_ModHandler.getIC2Item("insulatedGoldCableItem", 1), new ItemStack(aItem, 1, aMeta), GT_ModHandler.getIC2Item("doubleInsulatedGoldCableItem", 1), 100, 2);
                                        GT_Mod.addAssemblerRecipe(GT_ModHandler.getIC2Item("insulatedIronCableItem", 1), new ItemStack(aItem, 2, aMeta), GT_ModHandler.getIC2Item("trippleInsulatedIronCableItem", 1), 200, 2);
                                        GT_Mod.addAssemblerRecipe(GT_ModHandler.getIC2Item("doubleInsulatedIronCableItem", 1), new ItemStack(aItem, 1, aMeta), GT_ModHandler.getIC2Item("trippleInsulatedIronCableItem", 1), 100, 2);
                                    } else if (!(aEventName.equals("itemPotash") || aEventName.equals("potash") || aEventName.equals("itemCompressedCarbon") || aEventName.equals("compressedCarbon"))) {
                                        if (aEventName.equals("itemManganese") || aEventName.equals("manganese")) {
                                            GT_ModHandler.addPulverisationRecipe(new ItemStack(aItem, 1, aMeta), GT_MetaItem_Dust.instance.getStack(12, 1), null, 0, false);
                                        } else if (aEventName.equals("itemMagnesium") || aEventName.equals("magnesium")) {
                                            GT_ModHandler.addPulverisationRecipe(new ItemStack(aItem, 1, aMeta), GT_MetaItem_Dust.instance.getStack(13, 1), null, 0, false);
                                        } else if (aEventName.equals("itemPhosphorite") || aEventName.equals("phosphorite") || aEventName.equals("itemPhosphorus") || aEventName.equals("phosphorus")) {
                                            GT_ModHandler.addPulverisationRecipe(new ItemStack(aItem, 1, aMeta), GT_OreDictUnificator.get("dustPhosphorus", null, 1), null, 0, false);
                                        } else if (!(aEventName.equals("itemBitumen") || aEventName.equals("bitumen") || aEventName.equals("itemBioFuel") || aEventName.equals("itemEnrichedAlloy"))) {
                                            if (aEventName.equals("itemQuicksilver") || aEventName.equals("quicksilver")) {
                                                GT_Mod.addCannerRecipe(new ItemStack(aItem, 1, aMeta), GT_ModHandler.getEmptyCell(1), GT_MetaItem_Cell.instance.getStack(16, 1), null, 100, 1);
                                            } else if (!(aEventName.equals("chunkOsmium") || aEventName.equals("itemOsmium") || aEventName.equals("osmium"))) {
                                                if (aEventName.equals("sandOil") || aEventName.equals("oilsandsOre")) {
                                                    GT_Mod.addCentrifugeRecipe(new ItemStack(aItem, 2, aMeta), 1, GT_MetaItem_Cell.instance.getStack(17, 1), new ItemStack(Block.sand, 1, 0), null, null, 1000);
                                                } else if (aEventName.equals("itemSulfur") || aEventName.equals("sulfur")) {
                                                    GT_ModHandler.addPulverisationRecipe(new ItemStack(aItem, 1, aMeta), GT_MetaItem_Dust.instance.getStack(8, 1), null, 0, false);
                                                } else if (aEventName.equals("itemAluminum") || aEventName.equals("aluminum") || aEventName.equals("itemAluminium") || aEventName.equals("aluminium")) {
                                                    GT_ModHandler.addPulverisationRecipe(new ItemStack(aItem, 1, aMeta), GT_MetaItem_Dust.instance.getStack(18, 1), null, 0, false);
                                                } else if (aEventName.equals("itemSaltpeter") || aEventName.equals("saltpeter")) {
                                                    GT_ModHandler.addPulverisationRecipe(new ItemStack(aItem, 1, aMeta), GT_MetaItem_Dust.instance.getStack(9, 1), null, 0, false);
                                                } else if (aEventName.equals("itemUranium") || aEventName.equals("uranium")) {
                                                    GT_ModHandler.addPulverisationRecipe(new ItemStack(aItem, 1, aMeta), GT_OreDictUnificator.get("dustUranium", null, 1), null, 0, false);
                                                } else if (aEventName.equals("sandCracked")) {
                                                    if (aItem.itemID < 4096) {
                                                        GT_Mod.addJackHammerMinableBlock(Block.blocksList[aItem.itemID]);
                                                    }
                                                    if (aItem instanceof ItemBlock && aItem.getItemStackLimit() > GT_Mod.instance.mBlockStackSize) {
                                                        aItem.setMaxStackSize(GT_Mod.instance.mBlockStackSize);
                                                    }
                                                    GT_Mod.addCentrifugeRecipe(new ItemStack(aItem, 16, aMeta), -1, GT_ModHandler.getFuelCan(5000), GT_MetaItem_Dust.instance.getStack(9, 8), null, new ItemStack(Block.sand, 10), 2500);
                                                } else if (aEventName.startsWith("item")) {
                                                    System.out.println("Item Name: " + aEvent.Name + " !!!Unknown Item detected!!! Please report to GregTech Intergalactical for additional compatiblity. This is not an Error, it's just an Information.");
                                                } else {
                                                    System.out.println("Thingy Name: " + aEvent.Name + " !!!Unknown 'Thingy' detected!!! This Object seems to probably not follow a valid OreDictionary Convention, or I missed a Convention. Please report to GregTech Intergalactical for additional compatiblity. This is not an Error, it's just an Information.");
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

