package gregtechmod.common;

import gregtechmod.GT_Mod;
import gregtechmod.common.items.GT_MetaItem_Cell;
import gregtechmod.common.items.GT_MetaItem_Dust;
import gregtechmod.common.items.GT_MetaItem_Material;
import gregtechmod.common.items.GT_MetaItem_SmallDust;

import java.util.ArrayList;
import java.util.Iterator;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.OreDictionary.OreRegisterEvent;

public class GT_OreDictHandler {
	
	private ArrayList<OreDictionary.OreRegisterEvent> mEvents = new ArrayList<OreDictionary.OreRegisterEvent>();
	private ArrayList<String> mIgnoredNames = new ArrayList<String>();
	private boolean mActivated = false;
	
	public GT_OreDictHandler() {
		MinecraftForge.EVENT_BUS.register(this);
		
		mIgnoredNames.add("itemRecord");
        mIgnoredNames.add("aluminumNatural");
        mIgnoredNames.add("aluminiumNatural");
        mIgnoredNames.add("antimatterMilligram");
        mIgnoredNames.add("antimatterGram");
        mIgnoredNames.add("strangeMatter");
        mIgnoredNames.add("HSLivingmetalIngot");
        mIgnoredNames.add("oilMoving");
        mIgnoredNames.add("oilStill");
        mIgnoredNames.add("oilBucket");
        mIgnoredNames.add("oilsandsOre");
        mIgnoredNames.add("petroleumOre");
        mIgnoredNames.add("dieselFuel");
        mIgnoredNames.add("lava");
        mIgnoredNames.add("water");
        mIgnoredNames.add("craftingtable");
        mIgnoredNames.add("enderChest");
        mIgnoredNames.add("obsidianRod");
        mIgnoredNames.add("LiBattery");
        mIgnoredNames.add("basicCircuit");
        mIgnoredNames.add("advancedCircuit");
        mIgnoredNames.add("eliteCircuit");
        mIgnoredNames.add("motor");
        mIgnoredNames.add("wrench");
        mIgnoredNames.add("battery");
        mIgnoredNames.add("batteryBox");
        mIgnoredNames.add("copperWire");
        mIgnoredNames.add("coalGenerator");
        mIgnoredNames.add("electricFurnace");
        mIgnoredNames.add("batteryBox");
        mIgnoredNames.add("batteryBox");
        mIgnoredNames.add("batteryBox");
        mIgnoredNames.add("batteryBox");
        mIgnoredNames.add("itemDuctTape");
        mIgnoredNames.add("itemDiamondBlade");
        mIgnoredNames.add("itemGrinder");
        mIgnoredNames.add("itemMachineParts");
        mIgnoredNames.add("itemSuperconductor");
		
		/** because of the failed OreDictEvents for vanilla Stuff */
		registerOre(new OreRegisterEvent("logWood", new ItemStack(Block.wood, 1, -1)));
		registerOre(new OreRegisterEvent("plankWood", new ItemStack(Block.planks, 1, -1)));
		registerOre(new OreRegisterEvent("treeSapling", new ItemStack(Block.sapling, 1, -1)));
	}
    
	@ForgeSubscribe
    public void registerOre(OreDictionary.OreRegisterEvent aEvent) {
		if (aEvent.Name.equals("dustNikolite") && GT_ModHandler.mNikolite == null && aEvent.Ore.getItemDamage() == 6) {
    		GT_ModHandler.mRuby = new ItemStack(aEvent.Ore.getItem(), 1, 0);
    		GT_ModHandler.mGreenSapphire = new ItemStack(aEvent.Ore.getItem(), 1, 1);
    		GT_ModHandler.mSapphire = new ItemStack(aEvent.Ore.getItem(), 1, 2);
    		GT_ModHandler.mSilver = new ItemStack(aEvent.Ore.getItem(), 1, 3);
    		GT_ModHandler.mTin = new ItemStack(aEvent.Ore.getItem(), 1, 4);
    		GT_ModHandler.mCopper = new ItemStack(aEvent.Ore.getItem(), 1, 5);
    		GT_ModHandler.mNikolite = new ItemStack(aEvent.Ore.getItem(), 1, 6);
		}
		if (aEvent.Name.equals("ingotBrass") && GT_ModHandler.mBrass == null && aEvent.Ore.getItemDamage() == 2) {
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
    		
    		ItemStack tStack1 = GT_ModHandler.mSiliconWafer.copy(); tStack1.stackSize = 16;
    		GT_Mod.addSawmillRecipe(GT_ModHandler.mSiliconBoule.copy(), -1, tStack1, null, GT_ModHandler.getIC2Item("cell", 1));
		}
    	if (mActivated)
    		registerRecipe(aEvent);
    	else
        	mEvents.add(aEvent);
    }
	
	/**
	 * Gets called during the PostLoad-Phase
	 */
    public void activateHandler() {
    	mActivated = true;
    	Iterator<OreDictionary.OreRegisterEvent> tIterator = mEvents.iterator();
		while (tIterator.hasNext()) registerRecipe(tIterator.next());
		mEvents = null;
    }
    
    public void registerRecipe(OreDictionary.OreRegisterEvent aEvent) {
    	if (aEvent.Ore == null || aEvent.Ore.getItem() == null) return;
    	ArrayList<ItemStack> tList;
    	Iterator<ItemStack> tIterator1, tIterator2, tIterator3;
    	ItemStack tStack1, tStack2, tStack3;
    	int tCellCount = 0, aMeta = aEvent.Ore.getItemDamage();
    	Item aItem = aEvent.Ore.getItem();
    	String aEventName = aEvent.Name;
    	
    	if (aEventName.startsWith("oreNether")) aEventName = aEventName.replaceFirst("oreNether", "ore");
    	if (aEventName.startsWith("netherOre")) aEventName = aEventName.replaceFirst("netherOre", "ore");
    	if (aEventName.startsWith("oreEnd")) aEventName = aEventName.replaceFirst("oreEnd", "ore");
    	if (aEventName.startsWith("endOre")) aEventName = aEventName.replaceFirst("endOre", "ore");
    	if (aEventName.startsWith("drop")) aEventName = aEventName.replaceFirst("drop", "item");
    	
    	if (aEventName.equals("quartzCrystal"))			{OreDictionary.registerOre("crystalQuartz", aEvent.Ore); return;}
    	if (aEventName.equals("quartz"))				{OreDictionary.registerOre("crystalQuartz", aEvent.Ore); return;}
    	if (aEventName.equals("woodGas"))				{OreDictionary.registerOre("gasWood", aEvent.Ore); return;}
    	if (aEventName.equals("woodLog"))				{OreDictionary.registerOre("logWood", aEvent.Ore); return;}
    	if (aEventName.equals("pulpWood"))				{OreDictionary.registerOre("itemDustWood", aEvent.Ore); return;}
    	if (aEventName.equals("rawMachineTier01"))		{OreDictionary.registerOre("rawMachineTier00", aEvent.Ore);}
    	if (aEventName.startsWith("itemDust"))			{OreDictionary.registerOre(aEventName.replaceFirst("itemDust", "dust"), aEvent.Ore); return;}
    	if (aEventName.startsWith("itemDrop"))			{OreDictionary.registerOre(aEventName.replaceFirst("itemDrop", "item"), aEvent.Ore); return;}
    	
    	if (mIgnoredNames.contains(aEventName)) {
    		
    	}
	    else if (aEventName.startsWith("stone")) {
	    	if (aEventName.equals("stone") || aEventName.equals("stoneVanilla")) {
	    		GT_ModHandler.addPulverisationRecipe(new ItemStack(aItem, 1, aMeta), new ItemStack(Block.sand, 1, 0), null, 10);
	    	}
	    	else if (aEventName.equals("stoneRedrock") || aEventName.equals("stoneRedRock")) {
	    		GT_ModHandler.addPulverisationRecipe(new ItemStack(aItem, 1, aMeta), GT_MetaItem_Dust.instance.getStack(64, 1), GT_MetaItem_Dust.instance.getStack(64, 1), 10);
	    	}
	    	else if (aEventName.equals("stoneMarble")) {
	    		GT_ModHandler.addPulverisationRecipe(new ItemStack(aItem, 1, aMeta), GT_MetaItem_Dust.instance.getStack(65, 1), GT_MetaItem_Dust.instance.getStack(65, 1), 10);
	    	}
	    	else if (aEventName.equals("stoneBasalt")) {
	    		GT_ModHandler.addPulverisationRecipe(new ItemStack(aItem, 1, aMeta), GT_MetaItem_Dust.instance.getStack(66, 1), GT_MetaItem_Dust.instance.getStack(66, 1), 10);
	    	}
	    	else {
				System.out.println("Stone Name: " + aEvent.Name + " !!!Unknown kind of Stone detected!!! Please report to GregTech Intergalactical for additional compatiblity. This is not an Error, it's just an Information. This Ore will still get added to the List of the IC2-Miner, but with a low Value.");
	    	}
	    }
		else if (aEventName.startsWith("ore")) {
	    	if (aEventName.equals("oreLapis")) {
	    		if (GT_Mod.instance.mLapisMaceration) {
	    			GT_Mod.addGrinderRecipe(new ItemStack(aItem, 1, aMeta), -1, new ItemStack(Item.dyePowder, 12, 4), GT_MetaItem_Dust.instance.getStack(2, 3), null, GT_ModHandler.getIC2Item("cell", 1));
	    			GT_ModHandler.addPulverisationRecipe(new ItemStack(aItem, 1, aMeta), new ItemStack(Item.dyePowder, 12, 4), GT_MetaItem_Dust.instance.getStack(2, 1), 0);
	    		}
	    		GT_ModHandler.addValuableOre(aEvent.Ore.itemID, aMeta, 3);
	    	}
	    	else if (aEventName.equals("oreSodalite")) {
	    		if (GT_Mod.instance.mLapisMaceration) {
	    			GT_Mod.addGrinderRecipe(new ItemStack(aItem, 1, aMeta), -1, GT_MetaItem_Dust.instance.getStack(5, 12), GT_MetaItem_Dust.instance.getStack(18, 3), null, GT_ModHandler.getIC2Item("cell", 1));
	    			GT_ModHandler.addPulverisationRecipe(new ItemStack(aItem, 1, aMeta), GT_MetaItem_Dust.instance.getStack(5, 12), GT_MetaItem_Dust.instance.getStack(18, 1), 0);
	    		}
	    		GT_ModHandler.addValuableOre(aEvent.Ore.itemID, aMeta, 3);
	    	}
	    	else if (aEventName.equals("oreRedstone")) {
	    		if (GT_Mod.instance.mRedstoneMaceration) {
	    			GT_Mod.addGrinderRecipe(new ItemStack(aItem, 1, aMeta), -1, new ItemStack(Item.redstone, 10), GT_MetaItem_SmallDust.instance.getStack(250, 2), null, GT_ModHandler.getIC2Item("cell", 1));
	    			GT_ModHandler.addPulverisationRecipe(new ItemStack(aItem, 1, aMeta), new ItemStack(Item.redstone, 10), new ItemStack(Item.lightStoneDust, 1), 0);
	    		}
	    		GT_ModHandler.addValuableOre(aEvent.Ore.itemID, aMeta, 3);
	    	}
	    	else if (aEventName.equals("oreNikolite")) {
	    		if (GT_Mod.instance.mRedstoneMaceration && GT_ModHandler.mNikolite != null) {
	    			GT_Mod.addGrinderRecipe(new ItemStack(aItem, 1, aMeta), -1, new ItemStack(GT_ModHandler.mNikolite.getItem(), 10, GT_ModHandler.mNikolite.getItemDamage()), GT_MetaItem_SmallDust.instance.getStack(36, 1), null, GT_ModHandler.getIC2Item("cell", 1));
	    			GT_ModHandler.addPulverisationRecipe(new ItemStack(aItem, 1, aMeta), new ItemStack(GT_ModHandler.mNikolite.getItem(), 10, GT_ModHandler.mNikolite.getItemDamage()), GT_MetaItem_Dust.instance.getStack(36, 1), 0);
	    		}
	    		GT_ModHandler.addValuableOre(aEvent.Ore.itemID, aMeta, 3);
	    	}
	    	else if (aEventName.equals("oreIron")) {
	    	    GT_ModHandler.addPulverisationRecipe(new ItemStack(aItem, 1, aMeta), GT_OreDictUnificator.get("dustIron", null, 2));
	    		GT_Mod.addGrinderRecipe(new ItemStack(aItem, 1, aMeta), -1, GT_OreDictUnificator.get("dustIron", null, 2), GT_MetaItem_SmallDust.instance.getStack(244, 1), GT_MetaItem_SmallDust.instance.getStack(28, 1), GT_ModHandler.getIC2Item("cell", 1));
	    		GT_Mod.addBlastRecipe(new ItemStack(aItem, 1, aMeta), GT_MetaItem_Cell.instance.getStack(33, 1), GT_OreDictUnificator.get("ingotRefinedIron", null, 3), GT_ModHandler.getIC2Item("cell", 1), 100, 128, 1000);
	    		GT_ModHandler.addValuableOre(aEvent.Ore.itemID, aMeta, 3);
	    	}
	    	else if (aEventName.equals("oreGold")) {
	    	    GT_ModHandler.addPulverisationRecipe(new ItemStack(aItem, 1, aMeta), GT_OreDictUnificator.get("dustGold", null, 2));
	    		GT_Mod.addGrinderRecipe(new ItemStack(aItem, 1, aMeta), -1, GT_OreDictUnificator.get("dustGold", null, 2), GT_MetaItem_SmallDust.instance.getStack(243, 1), GT_MetaItem_SmallDust.instance.getStack(28, 1), GT_ModHandler.getIC2Item("cell", 1));
	    		GT_Mod.addGrinderRecipe(new ItemStack(aItem, 1, aMeta), GT_MetaItem_Cell.instance.getStack(16, 1), GT_OreDictUnificator.get("dustGold", null, 3), GT_MetaItem_SmallDust.instance.getStack(243, 1), GT_MetaItem_SmallDust.instance.getStack(28, 1), GT_ModHandler.getIC2Item("cell", 1));
	    		GT_Mod.addGrinderRecipe(new ItemStack(aItem, 1, aMeta), GT_MetaItem_Cell.instance.getStack(32, 1), GT_OreDictUnificator.get("dustGold", null, 2), GT_OreDictUnificator.get("dustCopper", null, 1), GT_MetaItem_SmallDust.instance.getStack(28, 1), GT_ModHandler.getIC2Item("cell", 1));
				GT_ModHandler.addValuableOre(aEvent.Ore.itemID, aMeta, 4);
	    	}
	    	else if (aEventName.equals("oreSilver")) {
	    	    GT_ModHandler.addPulverisationRecipe(new ItemStack(aItem, 1, aMeta), GT_OreDictUnificator.get("dustSilver", null, 2));
	    		GT_Mod.addGrinderRecipe(new ItemStack(aItem, 1, aMeta), -1, GT_OreDictUnificator.get("dustSilver", null, 2), GT_MetaItem_SmallDust.instance.getStack(23, 2), null, GT_ModHandler.getIC2Item("cell", 1));
	    		GT_Mod.addGrinderRecipe(new ItemStack(aItem, 1, aMeta), GT_MetaItem_Cell.instance.getStack(16, 1), GT_OreDictUnificator.get("dustSilver", null, 3), GT_MetaItem_SmallDust.instance.getStack(23, 2), null, GT_ModHandler.getIC2Item("cell", 1));
				GT_ModHandler.addValuableOre(aEvent.Ore.itemID, aMeta, 3);
	    	}
	    	else if (aEventName.equals("oreDiamond")) {
	    		if (GT_Mod.instance.mDiamondMaceration) {
	    			GT_Mod.addGrinderRecipe(new ItemStack(aItem, 1, aMeta), -1, new ItemStack(Item.diamond, 1), GT_MetaItem_SmallDust.instance.getStack(36, 6), GT_ModHandler.getIC2Item("hydratedCoalDust", 1), GT_ModHandler.getIC2Item("cell", 1));
	    			GT_ModHandler.addPulverisationRecipe(new ItemStack(aItem, 1, aMeta), GT_MetaItem_Dust.instance.getStack(36, 2), GT_OreDictUnificator.get("dustCoal", null, 1), 0);
				}
	    		GT_ModHandler.addValuableOre(aEvent.Ore.itemID, aMeta, 5);
	    	}
	    	else if (aEventName.equals("oreEmerald")) {
	    		if (GT_Mod.instance.mGemMaceration) {
	    			GT_Mod.addGrinderRecipe(new ItemStack(aItem, 1, aMeta), -1, new ItemStack(Item.emerald, 1), GT_MetaItem_SmallDust.instance.getStack(35, 6), GT_MetaItem_SmallDust.instance.getStack(37, 2), GT_ModHandler.getIC2Item("cell", 1));
	    			GT_ModHandler.addPulverisationRecipe(new ItemStack(aItem, 1, aMeta), GT_MetaItem_Dust.instance.getStack(35, 2), GT_MetaItem_Dust.instance.getStack(37, 1), 0);
	    		}
	    		GT_ModHandler.addValuableOre(aEvent.Ore.itemID, aMeta, 5);
	    	}
	    	else if (aEventName.equals("oreRuby")) {
				GT_ModHandler.addValuableOre(aEvent.Ore.itemID, aMeta, 4);
		        if (GT_Mod.instance.mGemMaceration) {
		        	GT_Mod.addGrinderRecipe(new ItemStack(aItem, 1, aMeta), -1, GT_OreDictUnificator.get("gemRuby", GT_MetaItem_Material.instance.getStack(32, 1), 1), GT_MetaItem_SmallDust.instance.getStack(32, 6), GT_MetaItem_SmallDust.instance.getStack(54, 2), GT_ModHandler.getIC2Item("cell", 1));
	    			GT_ModHandler.addPulverisationRecipe(new ItemStack(aItem, 1, aMeta), GT_MetaItem_Dust.instance.getStack(32, 2), GT_MetaItem_Dust.instance.getStack(54, 1), 0);
		    	}
	    	}
	    	else if (aEventName.equals("oreSapphire")) {
				GT_ModHandler.addValuableOre(aEvent.Ore.itemID, aMeta, 4);
				if (GT_Mod.instance.mGemMaceration) {
		        	GT_Mod.addGrinderRecipe(new ItemStack(aItem, 1, aMeta), -1, GT_OreDictUnificator.get("gemSapphire", GT_MetaItem_Material.instance.getStack(33, 1), 1), GT_MetaItem_SmallDust.instance.getStack(33, 6), GT_MetaItem_SmallDust.instance.getStack(34, 2), GT_ModHandler.getIC2Item("cell", 1));
	    			GT_ModHandler.addPulverisationRecipe(new ItemStack(aItem, 1, aMeta), GT_MetaItem_Dust.instance.getStack(33, 2), GT_MetaItem_Dust.instance.getStack(34, 1), 0);
				}
	    	}
	    	else if (aEventName.equals("oreGreenSapphire")) {
				GT_ModHandler.addValuableOre(aEvent.Ore.itemID, aMeta, 4);
		        if (GT_Mod.instance.mGemMaceration) {
		        	GT_Mod.addGrinderRecipe(new ItemStack(aItem, 1, aMeta), -1, GT_OreDictUnificator.get("gemGreenSapphire", GT_MetaItem_Material.instance.getStack(34, 1), 1), GT_MetaItem_SmallDust.instance.getStack(34, 6), GT_MetaItem_SmallDust.instance.getStack(33, 2), GT_ModHandler.getIC2Item("cell", 1));
	    			GT_ModHandler.addPulverisationRecipe(new ItemStack(aItem, 1, aMeta), GT_MetaItem_Dust.instance.getStack(34, 2), GT_MetaItem_Dust.instance.getStack(33, 1), 0);
				}
	    	}
	    	else if (aEventName.equals("oreOlivine")) {
				GT_ModHandler.addValuableOre(aEvent.Ore.itemID, aMeta, 4);
	    		if (GT_Mod.instance.mGemMaceration) {
	    			GT_Mod.addGrinderRecipe(new ItemStack(aItem, 1, aMeta), -1, GT_MetaItem_Material.instance.getStack(37, 1), GT_MetaItem_SmallDust.instance.getStack(37, 6), GT_MetaItem_SmallDust.instance.getStack(35, 2), GT_ModHandler.getIC2Item("cell", 1));
	    			GT_ModHandler.addPulverisationRecipe(new ItemStack(aItem, 1, aMeta), GT_MetaItem_Dust.instance.getStack(37, 2), GT_MetaItem_Dust.instance.getStack(35, 1), 0);
	    	    }
	    	}
	    	else if (aEventName.equals("oreCoal")) {
	    		if (GT_Mod.instance.mCoalMaceration) {
	    			GT_Mod.addGrinderRecipe(new ItemStack(aItem, 1, aMeta), -1, new ItemStack(Item.coal, 1), GT_ModHandler.getIC2Item("hydratedCoalDust", 1), GT_MetaItem_SmallDust.instance.getStack(240, 2), GT_ModHandler.getIC2Item("cell", 1));
	    			GT_ModHandler.addPulverisationRecipe(new ItemStack(aItem, 1, aMeta), GT_OreDictUnificator.get("dustCoal", null, 2), GT_OreDictUnificator.get("dustCoal", null, 1), 0);
	    	    }
	    		GT_ModHandler.addValuableOre(aEvent.Ore.itemID, aMeta, 1);
	    	}
	    	else if (aEventName.equals("oreCopper")) {
	    		GT_ModHandler.addValuableOre(aEvent.Ore.itemID, aMeta, 3);
	    		GT_ModHandler.addPulverisationRecipe(new ItemStack(aItem, 1, aMeta), GT_OreDictUnificator.get("dustCopper", null, 2), GT_OreDictUnificator.get("dustGold", null, 1), 0);
		        GT_Mod.addGrinderRecipe(new ItemStack(aItem, 1, aMeta), -1, GT_OreDictUnificator.get("dustCopper", null, 2), GT_MetaItem_SmallDust.instance.getStack(242, 1), GT_MetaItem_SmallDust.instance.getStack(28, 1), GT_ModHandler.getIC2Item("cell", 1));
	    		GT_Mod.addGrinderRecipe(new ItemStack(aItem, 1, aMeta), GT_MetaItem_Cell.instance.getStack(32, 1), GT_OreDictUnificator.get("dustCopper", null, 3), GT_MetaItem_SmallDust.instance.getStack(242, 1), GT_MetaItem_SmallDust.instance.getStack(28, 1), GT_ModHandler.getIC2Item("cell", 1));
	    		GT_Mod.addGrinderRecipe(new ItemStack(aItem, 1, aMeta), GT_MetaItem_Cell.instance.getStack(16, 1), GT_OreDictUnificator.get("dustCopper", null, 2), GT_OreDictUnificator.get("dustGold", null, 1), null, GT_ModHandler.getIC2Item("cell", 1));
			}
	        else if (aEventName.equals("oreTin")) {
				GT_ModHandler.addValuableOre(aEvent.Ore.itemID, aMeta, 3);
	    		GT_ModHandler.addPulverisationRecipe(new ItemStack(aItem, 1, aMeta), GT_OreDictUnificator.get("dustTin", null, 2), GT_OreDictUnificator.get("dustIron", null, 1), 0);
		        GT_Mod.addGrinderRecipe(new ItemStack(aItem, 1, aMeta), -1, GT_OreDictUnificator.get("dustTin", null, 2), GT_MetaItem_SmallDust.instance.getStack(241, 1), GT_MetaItem_SmallDust.instance.getStack(24, 1), GT_ModHandler.getIC2Item("cell", 1));
				GT_Mod.addGrinderRecipe(new ItemStack(aItem, 1, aMeta), GT_MetaItem_Cell.instance.getStack(32, 1), GT_OreDictUnificator.get("dustTin", null, 2), GT_MetaItem_SmallDust.instance.getStack(241, 1), GT_MetaItem_Dust.instance.getStack(24, 1), GT_ModHandler.getIC2Item("cell", 1));
			}
	    	else if (aEventName.equals("oreZinc")) {
	    		GT_ModHandler.addValuableOre(aEvent.Ore.itemID, aMeta, 2);
	    		GT_Mod.addGrinderRecipe(new ItemStack(aItem, 1, aMeta), -1, GT_MetaItem_Dust.instance.getStack(24, 2), GT_MetaItem_SmallDust.instance.getStack(244, 2), null, GT_ModHandler.getIC2Item("cell", 1));
    			GT_Mod.addGrinderRecipe(new ItemStack(aItem, 1, aMeta), GT_MetaItem_Cell.instance.getStack(32, 1), GT_MetaItem_Dust.instance.getStack(24, 4), GT_MetaItem_SmallDust.instance.getStack(244, 2), null, GT_ModHandler.getIC2Item("cell", 1));

    			GT_ModHandler.addOreToIngotSmeltingRecipe(new ItemStack(aItem, 1, aMeta), GT_MetaItem_Material.instance.getStack(24, 1));
    			GT_ModHandler.addPulverisationRecipe(new ItemStack(aItem, 1, aMeta), GT_MetaItem_Dust.instance.getStack(24, 2), GT_OreDictUnificator.get("dustTin", null, 1), 0);
	    	}
	    	else if (aEventName.equals("oreIridium")) {
				GT_ModHandler.addValuableOre(aEvent.Ore.itemID, aMeta, 10);
		        if (GT_Mod.instance.mIridiumMaceration) {
		        	GT_Mod.addGrinderRecipe(new ItemStack(aItem, 1, aMeta), -1, GT_ModHandler.getIC2Item("iridiumOre", 2), GT_MetaItem_SmallDust.instance.getStack(27, 2), null, GT_ModHandler.getIC2Item("cell", 1));
		        	GT_Mod.addGrinderRecipe(new ItemStack(aItem, 1, aMeta), GT_MetaItem_Cell.instance.getStack(16, 1), GT_ModHandler.getIC2Item("iridiumOre", 2), GT_MetaItem_Dust.instance.getStack(27, 1), null, GT_ModHandler.getIC2Item("cell", 1));
		        	GT_ModHandler.addPulverisationRecipe(new ItemStack(aItem, 1, aMeta), GT_ModHandler.getIC2Item("iridiumOre", 2), GT_MetaItem_Dust.instance.getStack(27, 1), 0);
		        }
	    	}
	    	else if (aEventName.equals("oreCooperite") || aEventName.equals("oreSheldonite")) {
				GT_ModHandler.addValuableOre(aEvent.Ore.itemID, aMeta, 10);
				if (GT_Mod.instance.mIridiumMaceration) {
		        	GT_Mod.addGrinderRecipe(new ItemStack(aItem, 4, aMeta), -4, GT_MetaItem_Dust.instance.getStack(27, 8), GT_MetaItem_Dust.instance.getStack(28, 4), GT_ModHandler.getIC2Item("iridiumOre", 1), GT_ModHandler.getIC2Item("cell", 4));
		        	GT_Mod.addGrinderRecipe(new ItemStack(aItem, 4, aMeta), GT_MetaItem_Cell.instance.getStack(16, 4), GT_MetaItem_Dust.instance.getStack(27, 12), GT_MetaItem_Dust.instance.getStack(28, 4), GT_ModHandler.getIC2Item("iridiumOre", 1), GT_ModHandler.getIC2Item("cell", 4));
		        	
		    		GT_ModHandler.addOreToIngotSmeltingRecipe(new ItemStack(aItem, 1, aMeta), GT_MetaItem_Material.instance.getStack(27, 1));
	    			GT_ModHandler.addPulverisationRecipe(new ItemStack(aItem, 1, aMeta), GT_MetaItem_Dust.instance.getStack(27, 2), GT_ModHandler.getIC2Item("iridiumOre", 1), 0);
	        	}
	    	}
	    	else if (aEventName.equals("orePlatinum")) {
	    		GT_ModHandler.addValuableOre(aEvent.Ore.itemID, aMeta, 7);
	    		GT_Mod.addGrinderRecipe(new ItemStack(aItem, 1, aMeta), -1, GT_MetaItem_Dust.instance.getStack(27, 2), GT_ModHandler.getIC2Item("iridiumOre", 1), GT_MetaItem_SmallDust.instance.getStack(28, 1), GT_ModHandler.getIC2Item("cell", 1));
	    		GT_Mod.addGrinderRecipe(new ItemStack(aItem, 1, aMeta), GT_MetaItem_Cell.instance.getStack(16, 1), GT_MetaItem_Dust.instance.getStack(27, 3), GT_ModHandler.getIC2Item("iridiumOre", 1), GT_MetaItem_SmallDust.instance.getStack(28, 1), GT_ModHandler.getIC2Item("cell", 1));
	    	    
	    		GT_ModHandler.addOreToIngotSmeltingRecipe(new ItemStack(aItem, 1, aMeta), GT_MetaItem_Material.instance.getStack(27, 1));
    			GT_ModHandler.addPulverisationRecipe(new ItemStack(aItem, 1, aMeta), GT_MetaItem_Dust.instance.getStack(27, 2), GT_ModHandler.getIC2Item("iridiumOre", 1), 0);
	        }
	    	else if (aEventName.equals("oreNickel")) {
	    		GT_ModHandler.addValuableOre(aEvent.Ore.itemID, aMeta, 4);
	    		GT_Mod.addGrinderRecipe(new ItemStack(aItem, 1, aMeta), -1, GT_MetaItem_Dust.instance.getStack(28, 3), GT_MetaItem_SmallDust.instance.getStack(27, 1), GT_MetaItem_SmallDust.instance.getStack(243, 1), GT_ModHandler.getIC2Item("cell", 1));
	    		
	    		GT_ModHandler.addOreToIngotSmeltingRecipe(new ItemStack(aItem, 1, aMeta), GT_MetaItem_Material.instance.getStack(28, 1));
    			GT_ModHandler.addPulverisationRecipe(new ItemStack(aItem, 1, aMeta), GT_MetaItem_Dust.instance.getStack(28, 2), GT_OreDictUnificator.get("dustTin", null, 1), 0);
	        }
	    	else if (aEventName.equals("orePyrite")) {
				GT_ModHandler.addValuableOre(aEvent.Ore.itemID, aMeta, 1);
		        if (GT_Mod.instance.mPyriteMaceration) {
		        	GT_Mod.addGrinderRecipe(new ItemStack(aItem, 1, aMeta), -1, GT_MetaItem_Dust.instance.getStack(3, 5), GT_MetaItem_Dust.instance.getStack(8, 2), null, GT_ModHandler.getIC2Item("cell", 1));
		        	GT_Mod.addBlastRecipe(new ItemStack(aItem, 1, aMeta), GT_MetaItem_Cell.instance.getStack(33, 1), GT_OreDictUnificator.get("ingotRefinedIron", null, 2), GT_ModHandler.getIC2Item("cell", 1), 100, 128, 1500);
		        	
	    			GT_ModHandler.addOreToIngotSmeltingRecipe(new ItemStack(aItem, 1, aMeta), new ItemStack(Item.ingotIron, 1));
	    			GT_ModHandler.addPulverisationRecipe(new ItemStack(aItem, 1, aMeta), GT_MetaItem_Dust.instance.getStack(3, 5), GT_OreDictUnificator.get("dustIron", null, 1), 0);
		        }
	    	}
	    	else if (aEventName.equals("oreCinnabar")) {
				GT_ModHandler.addValuableOre(aEvent.Ore.itemID, aMeta, 3);
		        if (GT_Mod.instance.mCinnabarMaceration) {
		        	GT_Mod.addGrinderRecipe(new ItemStack(aItem, 1, aMeta), -1, GT_MetaItem_Dust.instance.getStack(11, 5), GT_MetaItem_SmallDust.instance.getStack(249, 2), GT_MetaItem_SmallDust.instance.getStack(250, 1), GT_ModHandler.getIC2Item("cell", 1));
	    			
		        	GT_ModHandler.addPulverisationRecipe(new ItemStack(aItem, 1, aMeta), GT_MetaItem_Dust.instance.getStack(11, 3), new ItemStack(Item.redstone, 1), 0);
		        }
	    	}
	    	else if (aEventName.equals("oreSphalerite")) {
				GT_ModHandler.addValuableOre(aEvent.Ore.itemID, aMeta, 2);
		        if (GT_Mod.instance.mSphaleriteMaceration) {
		        	GT_Mod.addGrinderRecipe(new ItemStack(aItem, 1, aMeta), -1, GT_MetaItem_Dust.instance.getStack(14, 5), GT_MetaItem_Dust.instance.getStack(24, 1), GT_MetaItem_SmallDust.instance.getStack(55, 1), GT_ModHandler.getIC2Item("cell", 1));
		        	GT_Mod.addGrinderRecipe(new ItemStack(aItem, 1, aMeta), GT_MetaItem_Cell.instance.getStack(32, 1), GT_MetaItem_Dust.instance.getStack(14, 5), GT_MetaItem_Dust.instance.getStack(24, 3), GT_MetaItem_SmallDust.instance.getStack(55, 1), GT_ModHandler.getIC2Item("cell", 1));
		        	
		        	GT_ModHandler.addPulverisationRecipe(new ItemStack(aItem, 1, aMeta), GT_MetaItem_Dust.instance.getStack(14, 4), GT_MetaItem_Dust.instance.getStack(24, 1), 0);
		        }
	    	}
	    	else if (aEventName.equals("oreAluminium") || aEventName.equals("oreAluminum")) {
				GT_ModHandler.addValuableOre(aEvent.Ore.itemID, aMeta, 2);
		        GT_Mod.addGrinderRecipe(new ItemStack(aItem, 1, aMeta), -1, GT_MetaItem_Dust.instance.getStack(18, 3), GT_MetaItem_SmallDust.instance.getStack(19, 1), null, GT_ModHandler.getIC2Item("cell", 1));
		    	
		        GT_ModHandler.addPulverisationRecipe(new ItemStack(aItem, 1, aMeta), GT_MetaItem_Dust.instance.getStack(18, 2), GT_MetaItem_Dust.instance.getStack(19, 1), 0);
		    }
	    	else if (aEventName.equals("oreNaturalAluminium") || aEventName.equals("oreNaturalAluminum")) {
				if (GT_Mod.instance.mXYCraft) {
		    		GT_ModHandler.addValuableOre(aEvent.Ore.itemID, aMeta, 2);
			        GT_Mod.addGrinderRecipe(new ItemStack(aItem, 1, aMeta), -1, GT_MetaItem_Dust.instance.getStack(18, 3), GT_MetaItem_SmallDust.instance.getStack(19, 1), null, GT_ModHandler.getIC2Item("cell", 1));
			    	
			        GT_ModHandler.addPulverisationRecipe(new ItemStack(aItem, 1, aMeta), GT_MetaItem_Dust.instance.getStack(18, 2), GT_MetaItem_Dust.instance.getStack(19, 1), 0);
				}
			}
	    	else if (aEventName.equals("oreTitanium")) {
				GT_ModHandler.addValuableOre(aEvent.Ore.itemID, aMeta, 5);
		        GT_Mod.addGrinderRecipe(new ItemStack(aItem, 1, aMeta), -1, GT_MetaItem_Dust.instance.getStack(19, 2), GT_MetaItem_SmallDust.instance.getStack(18, 2), null, GT_ModHandler.getIC2Item("cell", 1));
		        
		        GT_ModHandler.addPulverisationRecipe(new ItemStack(aItem, 1, aMeta), GT_MetaItem_Dust.instance.getStack(19, 2), GT_MetaItem_Dust.instance.getStack(18, 1), 0);
			}
	    	else if (aEventName.equals("oreChrome")) {
				GT_ModHandler.addValuableOre(aEvent.Ore.itemID, aMeta, 10);
		        GT_Mod.addGrinderRecipe(new ItemStack(aItem, 1, aMeta), -1, GT_MetaItem_Dust.instance.getStack(20, 2), GT_MetaItem_SmallDust.instance.getStack(32, 1), null, GT_ModHandler.getIC2Item("cell", 1));
		        
		        GT_ModHandler.addPulverisationRecipe(new ItemStack(aItem, 1, aMeta), GT_MetaItem_Dust.instance.getStack(20, 2), GT_MetaItem_Dust.instance.getStack(32, 1), 0);
			}
	    	else if (aEventName.equals("oreElectrum")) {
				GT_ModHandler.addValuableOre(aEvent.Ore.itemID, aMeta, 5);
		        GT_Mod.addGrinderRecipe(new ItemStack(aItem, 1, aMeta), -1, GT_MetaItem_Dust.instance.getStack(21, 2), GT_MetaItem_SmallDust.instance.getStack(242, 1), GT_MetaItem_SmallDust.instance.getStack(246, 1), GT_ModHandler.getIC2Item("cell", 1));
		    	
		        GT_ModHandler.addOreToIngotSmeltingRecipe(new ItemStack(aItem, 1, aMeta), GT_MetaItem_Material.instance.getStack(21, 1));
    			GT_ModHandler.addPulverisationRecipe(new ItemStack(aItem, 1, aMeta), GT_MetaItem_Material.instance.getStack(21, 2), GT_OreDictUnificator.get("dustGold", null, 1), 0);
	        }
	    	else if (aEventName.equals("oreTungsten") || aEventName.equals("oreTungstate")) {
				GT_ModHandler.addValuableOre(aEvent.Ore.itemID, aMeta, 4);
	    		GT_ModHandler.addPulverisationRecipe(new ItemStack(aItem, 1, aMeta), GT_MetaItem_Dust.instance.getStack(22, 2), GT_MetaItem_Dust.instance.getStack(12, 1), 0);
	    	    GT_Mod.addGrinderRecipe(new ItemStack(aItem, 1, aMeta), -1, GT_MetaItem_Dust.instance.getStack(22, 2), GT_MetaItem_SmallDust.instance.getStack(241, 3), GT_MetaItem_SmallDust.instance.getStack(12, 3), GT_ModHandler.getIC2Item("cell", 1));
			}
	    	else if (aEventName.equals("oreLead")) {
	    		GT_ModHandler.addValuableOre(aEvent.Ore.itemID, aMeta, 3);
		        GT_ModHandler.addPulverisationRecipe(new ItemStack(aItem, 1, aMeta), GT_MetaItem_Dust.instance.getStack(23, 2));
		        GT_Mod.addGrinderRecipe(new ItemStack(aItem, 1, aMeta), -1, GT_MetaItem_Dust.instance.getStack(23, 2), GT_MetaItem_SmallDust.instance.getStack(246, 2), null, GT_ModHandler.getIC2Item("cell", 1));
			}
	    	else if (aEventName.equals("oreBauxite")) {
				GT_ModHandler.addValuableOre(aEvent.Ore.itemID, aMeta, 2);
		        GT_Mod.addGrinderRecipe(new ItemStack(aItem, 1, aMeta), -1, GT_MetaItem_Dust.instance.getStack(17, 4), GT_MetaItem_Dust.instance.getStack(18, 1), null, GT_ModHandler.getIC2Item("cell", 1));
		        
		        GT_ModHandler.addPulverisationRecipe(new ItemStack(aItem, 1, aMeta), GT_MetaItem_Dust.instance.getStack(17, 4), GT_MetaItem_Dust.instance.getStack(18, 1), 0);
			}
	    	else if (aEventName.equals("oreApatite")) {
				GT_ModHandler.addValuableOre(aEvent.Ore.itemID, aMeta, 1);
	    		tList = OreDictionary.getOres("gemApatite");
	    		if (tList.size() > 0 && GT_Mod.instance.mApatiteMaceration) {
	    			GT_ModHandler.addPulverisationRecipe(new ItemStack(aItem, 1, aMeta), new ItemStack(tList.get(0).getItem(), 2, tList.get(0).getItemDamage()));
	    			GT_Mod.addGrinderRecipe(new ItemStack(aItem, 1, aMeta), -1, new ItemStack(tList.get(0).getItem(), 3, tList.get(0).getItemDamage()), GT_OreDictUnificator.get("dustPhosphorus", null, 1), null, GT_ModHandler.getIC2Item("cell", 1));
	    	    }
	    	}
	    	else if (aEventName.equals("oreSulfur")) {
				GT_ModHandler.addValuableOre(aEvent.Ore.itemID, aMeta, 1);
	    		if (GT_Mod.instance.mSulphurMaceration) {
	    			GT_ModHandler.addPulverisationRecipe(new ItemStack(aItem, 1, aMeta), GT_MetaItem_Dust.instance.getStack(8, 8));
	    			GT_Mod.addGrinderRecipe(new ItemStack(aItem, 1, aMeta), -1, GT_MetaItem_Dust.instance.getStack(8, 10), null, null, GT_ModHandler.getIC2Item("cell", 1));
	    	    }
	    	}
	    	else if (aEventName.equals("oreSaltpeter")) {
				GT_ModHandler.addValuableOre(aEvent.Ore.itemID, aMeta, 2);
	    		if (GT_Mod.instance.mSaltpeterMaceration) {
	    			GT_ModHandler.addPulverisationRecipe(new ItemStack(aItem, 1, aMeta), GT_MetaItem_Dust.instance.getStack(9, 5));
	    			GT_Mod.addGrinderRecipe(new ItemStack(aItem, 1, aMeta), -1, GT_MetaItem_Dust.instance.getStack(9, 7), null, null, GT_ModHandler.getIC2Item("cell", 1));
	    		}
	    	}
	    	else if (aEventName.equals("orePhosphorite")) {
	    		GT_ModHandler.addValuableOre(aEvent.Ore.itemID, aMeta, 2);
	    	}
	    	else if (aEventName.equals("oreMagnesium")) {
	    		GT_Mod.addGrinderRecipe(new ItemStack(aItem, 1, aMeta), -1, GT_MetaItem_Dust.instance.getStack(13, 2), GT_MetaItem_SmallDust.instance.getStack(241, 2), GT_MetaItem_SmallDust.instance.getStack(28, 1), GT_ModHandler.getIC2Item("cell", 1));
		        GT_ModHandler.addValuableOre(aEvent.Ore.itemID, aMeta, 2);
		        
		        GT_ModHandler.addPulverisationRecipe(new ItemStack(aItem, 1, aMeta), GT_MetaItem_Dust.instance.getStack(13, 2), GT_MetaItem_Dust.instance.getStack(28, 1), 0);
			}
	    	else if (aEventName.equals("oreManganese")) {
	    		GT_Mod.addGrinderRecipe(new ItemStack(aItem, 1, aMeta), -1, GT_MetaItem_Dust.instance.getStack(12, 2), GT_MetaItem_SmallDust.instance.getStack(241, 2), GT_MetaItem_SmallDust.instance.getStack(28, 1), GT_ModHandler.getIC2Item("cell", 1));
		        GT_ModHandler.addValuableOre(aEvent.Ore.itemID, aMeta, 2);
		        
		        GT_ModHandler.addPulverisationRecipe(new ItemStack(aItem, 1, aMeta), GT_MetaItem_Dust.instance.getStack(12, 2), GT_MetaItem_Dust.instance.getStack(28, 1), 0);
	    	}
	    	else if (aEventName.equals("oreOsmium")) {
				GT_ModHandler.addValuableOre(aEvent.Ore.itemID, aMeta, 20);
	    	}
	    	else if (aEventName.equals("oreEximite")) {
	    		GT_ModHandler.addValuableOre(aEvent.Ore.itemID, aMeta, 3);
	    	}
	    	else if (aEventName.equals("oreMeutoite")) {
	    		GT_ModHandler.addValuableOre(aEvent.Ore.itemID, aMeta, 3);
	    	}
	    	else if (aEventName.equals("orePrometheum")) {
	    		GT_ModHandler.addValuableOre(aEvent.Ore.itemID, aMeta, 3);
	    	}
	    	else if (aEventName.equals("oreDeep Iron")) {
	    		GT_ModHandler.addValuableOre(aEvent.Ore.itemID, aMeta, 2);
	    	}
	    	else if (aEventName.equals("oreDeepIron")) {
	    		GT_ModHandler.addValuableOre(aEvent.Ore.itemID, aMeta, 2);
	    	}
	    	else if (aEventName.equals("oreInfuscolium")) {
	    		GT_ModHandler.addValuableOre(aEvent.Ore.itemID, aMeta, 3);
	    	}
	    	else if (aEventName.equals("oreOureclase")) {
	    		GT_ModHandler.addValuableOre(aEvent.Ore.itemID, aMeta, 3);
	    	}
	    	else if (aEventName.equals("oreAredrite")) {
	    		GT_ModHandler.addValuableOre(aEvent.Ore.itemID, aMeta, 3);
	    	}
	    	else if (aEventName.equals("oreAstral Silver")) {
	    		GT_ModHandler.addValuableOre(aEvent.Ore.itemID, aMeta, 4);
	    	}
	    	else if (aEventName.equals("oreAstralSilver")) {
	    		GT_ModHandler.addValuableOre(aEvent.Ore.itemID, aMeta, 4);
	    	}
	    	else if (aEventName.equals("oreCarmot")) {
	    		GT_ModHandler.addValuableOre(aEvent.Ore.itemID, aMeta, 4);
	    	}
	    	else if (aEventName.equals("oreMithril")) {
	    		GT_ModHandler.addValuableOre(aEvent.Ore.itemID, aMeta, 4);
	    	}
	    	else if (aEventName.equals("oreRubracium")) {
	    		GT_ModHandler.addValuableOre(aEvent.Ore.itemID, aMeta, 3);
	    	}
	    	else if (aEventName.equals("oreOrichalcum")) {
	    		GT_ModHandler.addValuableOre(aEvent.Ore.itemID, aMeta, 3);
	    	}
	    	else if (aEventName.equals("oreAdamantine")) {
	    		GT_ModHandler.addValuableOre(aEvent.Ore.itemID, aMeta, 5);
	    	}
	    	else if (aEventName.equals("oreAtlarus")) {
	    		GT_ModHandler.addValuableOre(aEvent.Ore.itemID, aMeta, 3);
	    	}
	    	else if (aEventName.equals("oreIgnatius")) {
	    		GT_ModHandler.addValuableOre(aEvent.Ore.itemID, aMeta, 3);
	    	}
	    	else if (aEventName.equals("oreShadow Iron")) {
	    		GT_ModHandler.addValuableOre(aEvent.Ore.itemID, aMeta, 4);
	    	}
	    	else if (aEventName.equals("oreShadowIron")) {
	    		GT_ModHandler.addValuableOre(aEvent.Ore.itemID, aMeta, 4);
	    	}
	    	else if (aEventName.equals("oreMidasium")) {
	    		GT_ModHandler.addValuableOre(aEvent.Ore.itemID, aMeta, 3);
	    	}
	    	else if (aEventName.equals("oreVyroxeres")) {
	    		GT_ModHandler.addValuableOre(aEvent.Ore.itemID, aMeta, 3);
	    	}
	    	else if (aEventName.equals("oreCeruclase")) {
	    		GT_ModHandler.addValuableOre(aEvent.Ore.itemID, aMeta, 3);
	    	}
	    	else if (aEventName.equals("oreKalendrite")) {
	    		GT_ModHandler.addValuableOre(aEvent.Ore.itemID, aMeta, 3);
	    	}
	    	else if (aEventName.equals("oreVulcanite")) {
	    		GT_ModHandler.addValuableOre(aEvent.Ore.itemID, aMeta, 3);
	    	}
	    	else if (aEventName.equals("oreSanguinite")) {
	    		GT_ModHandler.addValuableOre(aEvent.Ore.itemID, aMeta, 3);
	    	}
	    	else if (aEventName.equals("oreLemurite")) {
	    		GT_ModHandler.addValuableOre(aEvent.Ore.itemID, aMeta, 3);
	    	}
	    	else if (aEventName.equals("oreAdluorite")) {
	    		GT_ModHandler.addValuableOre(aEvent.Ore.itemID, aMeta, 3);
	    	}
	    	else if (aEventName.equals("oreBitumen")) {
	    		GT_ModHandler.addValuableOre(aEvent.Ore.itemID, aMeta, 2);
	    	}
	    	else if (aEventName.equals("orePotash")) {
	    		GT_ModHandler.addValuableOre(aEvent.Ore.itemID, aMeta, 2);
	    	}
	    	else if (aEventName.equals("oreUranium")) {
				GT_ModHandler.addValuableOre(aEvent.Ore.itemID, aMeta, 5);
	    		if (GT_Mod.instance.mUraniumMaceration) {
	    			GT_ModHandler.addPulverisationRecipe(new ItemStack(aItem, 1, aMeta), GT_MetaItem_Dust.instance.getStack(16, 2), GT_MetaItem_Dust.instance.getStack(22, 1), 0);
	    			GT_Mod.addGrinderRecipe(new ItemStack(aItem, 1, aMeta), -1, GT_MetaItem_Dust.instance.getStack(16, 2), GT_MetaItem_SmallDust.instance.getStack(22, 2), null, GT_ModHandler.getIC2Item("cell", 1));
		    	} else {
	    			GT_ModHandler.addPulverisationRecipe(new ItemStack(aItem, 1, aMeta), GT_MetaItem_Dust.instance.getStack(16, 1));
	    		}
	    	}
	    	else {
				GT_ModHandler.addValuableOre(aEvent.Ore.itemID, aMeta, 1);
				System.out.println("Ore Name: " + aEvent.Name + " !!!Unknown Ore detected!!! Please report to GregTech Intergalactical for additional compatiblity. This is not an Error, it's just an Information. This Ore will still get added to the List of the IC2-Miner, but with a low Value.");
	    	}
	    }
	    else if (aEventName.startsWith("dust")) {
    		GT_OreDictUnificator.add(aEventName, new ItemStack(aItem, 1, aMeta));
	    	if (aEventName.startsWith("dustSmall")) {
	    		
	    	}
	    	else if (aEventName.equals("dustWood")) {
	            GT_ModHandler.addCompressionRecipe(new ItemStack(aItem, 8, aMeta), GT_MetaItem_Material.instance.getStack(15, 1));
	    	}
	    	else if (aEventName.equals("dustSteel")) {
	            GT_Mod.addBlastRecipe(new ItemStack(aItem, 1, aMeta), null, GT_MetaItem_Material.instance.getStack(26, 1), null,  100, 128, 1000);
		    	GT_ModHandler.addRCBlastFurnaceRecipe(new ItemStack(aItem, 1, aMeta), GT_MetaItem_Material.instance.getStack(26, 1), 1000);
	    	}
	    	else if (aEventName.equals("dustChrome")) {
	            GT_Mod.addBlastRecipe(new ItemStack(aItem, 1, aMeta), null, GT_MetaItem_Material.instance.getStack(20, 1), null,  800, 128, 1700);
	    	}
	    	else if (aEventName.equals("dustTitanium")) {
	            GT_Mod.addBlastRecipe(new ItemStack(aItem, 1, aMeta), null, GT_MetaItem_Material.instance.getStack(19, 1), null, 1000, 128, 1500);
	    	}
	    	else if (aEventName.equals("dustAluminium") || aEventName.equals("dustAluminum")) {
	            GT_Mod.addBlastRecipe(new ItemStack(aItem, 1, aMeta), null, GT_MetaItem_Material.instance.getStack(18, 1), null,  200, 128, 1700);
		    	GT_ModHandler.addRCBlastFurnaceRecipe(new ItemStack(aItem, 1, aMeta), GT_MetaItem_Material.instance.getStack(18, 1), 1000);
	    	}
	    	else if (aEventName.equals("dustSaltpeter")) {
	        	GT_Mod.addElectrolyzerRecipe(new ItemStack(aItem, 10, aMeta), 7, GT_MetaItem_Cell.instance.getStack(14, 2), GT_MetaItem_Cell.instance.getStack(15, 2), null, GT_ModHandler.getIC2Item("airCell", 3), 50, 110);
	    	}
	    	else if (aEventName.equals("dustRedrock") || aEventName.equals("dustRedRock")) {
	    		GT_Mod.addCentrifugeRecipe(new ItemStack(aItem, 3, aMeta), 0, GT_MetaItem_Dust.instance.getStack(4, 2), GT_MetaItem_Dust.instance.getStack(7, 1), null, null, 100);	
			}
	    	else if (aEventName.equals("dustMarble")) {
	    		GT_Mod.addCentrifugeRecipe(new ItemStack(aItem, 8, aMeta), 0, GT_MetaItem_Dust.instance.getStack(13, 1), GT_MetaItem_Dust.instance.getStack(4, 7), null, null, 1055);	
			}
	    	else if (aEventName.equals("dustBasalt")) {
	    		GT_Mod.addCentrifugeRecipe(new ItemStack(aItem, 16, aMeta), 0, GT_MetaItem_Dust.instance.getStack(37, 1), GT_MetaItem_Dust.instance.getStack(4, 3), GT_MetaItem_Dust.instance.getStack(7, 8), GT_MetaItem_Dust.instance.getStack(63, 4), 2040);	
			}
	    	else if (aEventName.equals("dustSulfur")) {
	    		
	    	}
	    	else if (aEventName.equals("dustObsidian")) {
	    		if (GT_Mod.instance.mCentrifugedFlint)
		    		GT_Mod.addElectrolyzerRecipe(new ItemStack(aItem, 8, aMeta), 6, GT_MetaItem_Dust.instance.getStack(13, 1), GT_OreDictUnificator.get("dustIron", null, 1), GT_MetaItem_Cell.instance.getStack(7, 2), GT_ModHandler.getIC2Item("airCell", 4), 1000, 5);
	    	}
	    	else if (aEventName.equals("dustNikolite")) {
	    		
	    	}
	    	else if (aEventName.equals("dustXychorium")) {
	    		
	    	}
	    	else if (aEventName.equals("dustInvar")) {
	    		GT_ModHandler.addDustToIngotSmeltingRecipe(new ItemStack(aItem, 1, aMeta), GT_OreDictUnificator.get("ingotInvar", null, 1));
	    	}
	    	else if (aEventName.equals("dustMagnesium")) {
	    		
	    	}
		    else if (aEventName.equals("dustManganese")) {
		    	
		    }
		    else if (aEventName.equals("dustHepatizon")) {
		    	
		    }
		    else if (aEventName.equals("dustDamascus Steel")) {
		    	GT_Mod.addCentrifugeRecipe(new ItemStack(aItem, 1, aMeta), 0, GT_MetaItem_Dust.instance.getStack(26, 1), null, null, null, 960);	
			}
		    else if (aEventName.equals("dustAngmallen")) {
		    	
		    }
		    else if (aEventName.equals("dustEximite")) {
		    	
		    }
		    else if (aEventName.equals("dustMeutoite")) {
		    	
		    }
		    else if (aEventName.equals("dustDesichalkos")) {
		    	
		    }
		    else if (aEventName.equals("dustPrometheum")) {
		    	
		    }
		    else if (aEventName.equals("dustDeep Iron")) {
		    	GT_Mod.addCentrifugeRecipe(new ItemStack(aItem, 1, aMeta), 0, GT_MetaItem_Dust.instance.getStack(26, 1), null, null, null, 960);	
			}
		    else if (aEventName.equals("dustInfuscolium")) {
		    	
		    }
		    else if (aEventName.equals("dustOureclase")) {
		    	
		    }
		    else if (aEventName.equals("dustAredrite")) {
		    	
		    }
		    else if (aEventName.equals("dustAstral Silver")) {
		    	GT_Mod.addCentrifugeRecipe(new ItemStack(aItem, 1, aMeta), 0, GT_OreDictUnificator.get("dustSilver", null, 1), null, null, null, 960);	
		    }
		    else if (aEventName.equals("dustCarmot")) {
		    	
		    }
		    else if (aEventName.equals("dustAmordrine")) {
		    	
		    }
		    else if (aEventName.equals("dustMithril")) {
		    	
		    }
		    else if (aEventName.equals("dustRubracium")) {
		    	
		    }
		    else if (aEventName.equals("dustOrichalcum")) {
		    	
		    }
		    else if (aEventName.equals("dustAdamantine")) {
		    	
		    }
		    else if (aEventName.equals("dustAtlarus")) {
		    	
		    }
		    else if (aEventName.equals("dustBlack Steel")) {
		    	GT_Mod.addCentrifugeRecipe(new ItemStack(aItem, 1, aMeta), 0, GT_MetaItem_Dust.instance.getStack(26, 1), null, null, null, 960);	
			}
		    else if (aEventName.equals("dustHaderoth")) {
		    	
		    }
		    else if (aEventName.equals("dustCelenegil")) {
		    	
		    }
		    else if (aEventName.equals("dustTartarite")) {
		    	
		    }
		    else if (aEventName.equals("dustIgnatius")) {
		    	
		    }
		    else if (aEventName.equals("dustShadow Iron")) {
		    	GT_Mod.addCentrifugeRecipe(new ItemStack(aItem, 1, aMeta), 0, GT_MetaItem_Dust.instance.getStack(26, 1), null, null, null, 960);	
			}
		    else if (aEventName.equals("dustMidasium")) {
		    	GT_Mod.addCentrifugeRecipe(new ItemStack(aItem, 1, aMeta), 0, GT_OreDictUnificator.get("dustGold", null, 1), null, null, null, 960);	
			}
		    else if (aEventName.equals("dustVyroxeres")) {
		    	
		    }
		    else if (aEventName.equals("dustCeruclase")) {
		    	
		    }
		    else if (aEventName.equals("dustKalendrite")) {
		    	
		    }
		    else if (aEventName.equals("dustVulcanite")) {
		    	
		    }
		    else if (aEventName.equals("dustSanguinite")) {
		    	
		    }
		    else if (aEventName.equals("dustLemurite")) {
		    	
		    }
		    else if (aEventName.equals("dustAdluorite")) {
		    	
		    }
		    else if (aEventName.equals("dustShadow Steel")) {
		    	GT_Mod.addCentrifugeRecipe(new ItemStack(aItem, 1, aMeta), 0, GT_MetaItem_Dust.instance.getStack(26, 1), null, null, null, 960);	
			}
		    else if (aEventName.equals("dustInolashite")) {
		    	
		    }
		    else if (aEventName.equals("dustAmordrine ")) {
		    	
		    }
		    else if (aEventName.equals("dustGunpowder")) {
		    	
		    }
		    else if (aEventName.equals("dustRedstone")) {
		    	if (GT_Mod.instance.mCentrifugedRedstone)
		        	GT_Mod.addCentrifugeRecipe(new ItemStack(aItem, 64, aMeta), 26, GT_MetaItem_Cell.instance.getStack(7, 6), GT_MetaItem_Dust.instance.getStack(3, 32), GT_MetaItem_Dust.instance.getStack(32, 6), GT_MetaItem_Cell.instance.getStack(16, 20), 22000);
		    }
		    else if (aEventName.equals("dustGlowstone")) {
		    	if (GT_Mod.instance.mCentrifugedGlowstone)
		        	GT_Mod.addCentrifugeRecipe(new ItemStack(aItem, 16, aMeta), 1, new ItemStack(Item.redstone, 8), GT_OreDictUnificator.get("dustGold", null, 8), GT_MetaItem_Cell.instance.getStack(3, 1), null, 5000);
		    }
	    	else if (aEventName.equals("dustClay")) {
	    		if (GT_Mod.instance.mCentrifugedClay)
	            	GT_Mod.addElectrolyzerRecipe(new ItemStack(aItem, 8, aMeta), 5, GT_MetaItem_Cell.instance.getStack(5, 1), GT_MetaItem_Cell.instance.getStack(7, 2), GT_MetaItem_Dust.instance.getStack(18, 2), GT_MetaItem_Cell.instance.getStack(12, 2), 200, 50);
	    	}
	    	else if (aEventName.equals("dustBronze")) {
	    		GT_Mod.addCentrifugeRecipe(new ItemStack(aItem, (GT_Mod.instance.mForestryBronzeNerf || GT_ModHandler.getRecipeOutput(new ItemStack[] {GT_OreDictUnificator.get("ingotCopper", null, 1), GT_OreDictUnificator.get("ingotCopper", null, 1), null, GT_OreDictUnificator.get("ingotCopper", null, 1), GT_OreDictUnificator.get("ingotTin", null, 1), null, null, null, null})==null?1:2), aMeta), 0, GT_MetaItem_SmallDust.instance.getStack(243, 6), GT_MetaItem_SmallDust.instance.getStack(244, 2), null, null, 1500);
	    	}
	    	else if (aEventName.equals("dustBrass")) {
	    		GT_ModHandler.addDustToIngotSmeltingRecipe(new ItemStack(aItem, 1, aMeta), GT_OreDictUnificator.get("ingotBrass", null, 1));
	    		GT_Mod.addCentrifugeRecipe(new ItemStack(aItem, (GT_Mod.instance.mForestryBronzeNerf || GT_ModHandler.getRecipeOutput(new ItemStack[] {GT_OreDictUnificator.get("ingotCopper", null, 1), GT_OreDictUnificator.get("ingotCopper", null, 1), null, GT_OreDictUnificator.get("ingotCopper", null, 1), GT_OreDictUnificator.get("ingotTin", null, 1), null, null, null, null})==null?1:2), aMeta), 0, GT_MetaItem_SmallDust.instance.getStack(243, 3), GT_MetaItem_SmallDust.instance.getStack( 24, 1), null, null, 1500);
	    	}
	    	else if (aEventName.equals("dustCopper")) {
	    		GT_Mod.addCentrifugeRecipe(new ItemStack(aItem, 3, aMeta), 0, GT_MetaItem_SmallDust.instance.getStack(242, 1), GT_MetaItem_SmallDust.instance.getStack(28, 1), null, null, 2400);
	    	}
	    	else if (aEventName.equals("dustGold")) {
	    		GT_Mod.addCentrifugeRecipe(new ItemStack(aItem, 3, aMeta), 0, GT_MetaItem_SmallDust.instance.getStack(243, 1), GT_MetaItem_SmallDust.instance.getStack(28, 1), null, null, 2400);
	    	}
		    else if (aEventName.equals("dustNickel")) {
	        	GT_Mod.addCentrifugeRecipe(new ItemStack(aItem, 3, aMeta), 0, GT_MetaItem_SmallDust.instance.getStack(241, 1), GT_MetaItem_SmallDust.instance.getStack(242, 1), GT_MetaItem_SmallDust.instance.getStack(243, 1), null, 3450);
	    	    GT_ModHandler.addDustToIngotSmeltingRecipe(new ItemStack(aItem, 1, aMeta), GT_MetaItem_Material.instance.getStack(28, 1));
		    }
	    	else if (aEventName.equals("dustIron")) {
	        	GT_Mod.addCentrifugeRecipe(new ItemStack(aItem, 2, aMeta), 0, GT_MetaItem_SmallDust.instance.getStack(244, 1), GT_MetaItem_SmallDust.instance.getStack(28, 1), null, null, 1500);
	        	GT_Mod.addBlastRecipe(new ItemStack(aItem, 1, aMeta), GT_OreDictUnificator.get("dustCoal", null, 2), GT_MetaItem_Material.instance.getStack(26, 1), GT_MetaItem_Dust.instance.getStack(63, 4), 500, 128, 1000);
	        }
		    else if (aEventName.equals("dustTin")) {
	        	GT_Mod.addCentrifugeRecipe(new ItemStack(aItem, 2, aMeta), 0, GT_MetaItem_SmallDust.instance.getStack( 24, 1), GT_MetaItem_SmallDust.instance.getStack(241, 1), null, null, 2100);
	    	}
		    else if (aEventName.equals("dustZinc")) {
	        	GT_Mod.addCentrifugeRecipe(new ItemStack(aItem, 1, aMeta), 0, GT_MetaItem_SmallDust.instance.getStack(244, 1), null, null, null, 1050);
	    	    GT_ModHandler.addDustToIngotSmeltingRecipe(new ItemStack(aItem, 1, aMeta), GT_MetaItem_Material.instance.getStack(24, 1));
		    }
	    	else if (aEventName.equals("dustQuicksilver")) {
	    		GT_Mod.addCentrifugeRecipe(new ItemStack(aItem, 1, aMeta), 1, GT_MetaItem_Cell.instance.getStack(16, 1), null, null, null, 100);
	    	}
	    	else if (aEventName.equals("dustQuickSilver")) {
	    		OreDictionary.registerOre("dustQuicksilver", aEvent.Ore);
	    	}
		    else if (aEventName.equals("dustGarnetRed")) {
		    	GT_Mod.addCentrifugeRecipe(new ItemStack(aItem, 16, aMeta), 0, GT_OreDictUnificator.get("dustPyrope", null, 3), GT_OreDictUnificator.get("dustAlmandine", null, 5), GT_OreDictUnificator.get("dustSpessartine", null, 8), null, 3000);
		    	GT_Mod.addImplosionRecipe(new ItemStack(aItem, 4, aMeta), 16, GT_MetaItem_Material.instance.getStack(54, 3), GT_MetaItem_Dust.instance.getStack(63, 8));
		    }
		    else if (aEventName.equals("dustGarnetYellow")) {
		    	GT_Mod.addCentrifugeRecipe(new ItemStack(aItem, 16, aMeta), 0, GT_OreDictUnificator.get("dustAndradite", null, 5), GT_OreDictUnificator.get("dustGrossular", null, 8), GT_OreDictUnificator.get("dustUvarovite", null, 3), null, 3500);
		    	GT_Mod.addImplosionRecipe(new ItemStack(aItem, 4, aMeta), 16, GT_MetaItem_Material.instance.getStack(55, 3), GT_MetaItem_Dust.instance.getStack(63, 8));
		    }
		    else if (aEventName.equals("dustPyrope")) {
		    	GT_Mod.addElectrolyzerRecipe(new ItemStack(aItem, 20, aMeta), 9, GT_Mod.getGregTechItem(1, 3, 13), GT_Mod.getGregTechItem(1, 2, 18), GT_Mod.getGregTechItem(2, 3, 7), GT_ModHandler.getIC2Item("airCell", 6), 1790, 50);
		    }
		    else if (aEventName.equals("dustAlmandine")) {
		    	GT_Mod.addElectrolyzerRecipe(new ItemStack(aItem, 20, aMeta), 9, GT_OreDictUnificator.get("dustIron", null, 3), GT_Mod.getGregTechItem(1, 2, 18), GT_Mod.getGregTechItem(2, 3, 7), GT_ModHandler.getIC2Item("airCell", 6), 1640, 50);
		    }
		    else if (aEventName.equals("dustSpessartine")) {
		    	GT_Mod.addElectrolyzerRecipe(new ItemStack(aItem, 20, aMeta), 9, GT_Mod.getGregTechItem(1, 2, 18), GT_MetaItem_Dust.instance.getStack(12, 3), GT_Mod.getGregTechItem(2, 3, 7), GT_ModHandler.getIC2Item("airCell", 6), 1810, 50);
		    }
		    else if (aEventName.equals("dustAndradite")) {
		    	GT_Mod.addElectrolyzerRecipe(new ItemStack(aItem, 20, aMeta), 12, GT_Mod.getGregTechItem(2, 3, 11), GT_OreDictUnificator.get("dustIron", null, 2), GT_Mod.getGregTechItem(2, 3, 7), GT_ModHandler.getIC2Item("airCell", 6), 1280, 50);
		    }
		    else if (aEventName.equals("dustGrossular")) {
		    	GT_Mod.addElectrolyzerRecipe(new ItemStack(aItem, 20, aMeta), 12, GT_Mod.getGregTechItem(2, 3, 11), GT_Mod.getGregTechItem(1, 2, 18), GT_Mod.getGregTechItem(2, 3, 7), GT_ModHandler.getIC2Item("airCell", 6), 2050, 50);
		    }
		    else if (aEventName.equals("dustUvarovite")) {
		    	GT_Mod.addElectrolyzerRecipe(new ItemStack(aItem, 20, aMeta), 12, GT_Mod.getGregTechItem(2, 3, 11), GT_Mod.getGregTechItem(1, 2, 20), GT_Mod.getGregTechItem(2, 3, 7), GT_ModHandler.getIC2Item("airCell", 6), 2200, 50);
		    }
		    else if (aEventName.equals("dustOlivine")) {
		    	GT_Mod.addElectrolyzerRecipe(new ItemStack(aItem, 9, aMeta), 3, GT_Mod.getGregTechItem(1, 2, 13), GT_OreDictUnificator.get("dustIron", null, 2), GT_Mod.getGregTechItem(2, 1, 7), GT_ModHandler.getIC2Item("airCell", 2), 600, 60);
		    	GT_Mod.addImplosionRecipe(new ItemStack(aItem, 4, aMeta), 24, GT_MetaItem_Material.instance.getStack(37, 3), GT_MetaItem_Dust.instance.getStack(63, 12));
		    }
		    else if (aEventName.equals("dustSphalerite")) {
		    	GT_Mod.addElectrolyzerRecipe(new ItemStack(aItem, 2, aMeta), 0, GT_Mod.getGregTechItem(1, 1, 24), GT_Mod.getGregTechItem(1, 1, 8), null, null, 150, 100);
	    	}
		    else if (aEventName.equals("dustCinnabar")) {
		    	GT_Mod.addElectrolyzerRecipe(new ItemStack(aItem, 2, aMeta), 1, GT_Mod.getGregTechItem(2, 1, 16), GT_Mod.getGregTechItem(1, 1, 8), null, null, 100, 128);
	    	}
	    	else if (aEventName.equals("dustPlatinum")) {
		    	GT_Mod.addCentrifugeRecipe(new ItemStack(aItem, 8, aMeta), 0, GT_ModHandler.getIC2Item("iridiumOre", 1), GT_MetaItem_Dust.instance.getStack(28, 1), null, null, 20000);
	    	    GT_ModHandler.addDustToIngotSmeltingRecipe(new ItemStack(aItem, 1, aMeta), GT_MetaItem_Material.instance.getStack(27, 1));
	    	}
		    else if (aEventName.equals("dustLead")) {
	    		GT_ModHandler.addDustToIngotSmeltingRecipe(new ItemStack(aItem, 1, aMeta), GT_OreDictUnificator.get("ingotLead"	, null, 1));
	        	GT_Mod.addCentrifugeRecipe(new ItemStack(aItem, 8, aMeta), 0, GT_OreDictUnificator.get("dustSilver", null, 1), null, null, null, 9800);
	    	}
	    	else if (aEventName.equals("dustSilver")) {
	        	GT_Mod.addCentrifugeRecipe(new ItemStack(aItem, 8, aMeta), 0, GT_Mod.getGregTechItem(1, 1, 23), null, null, null, 9800);
	    	}
	    	else if (aEventName.equals("dustEnderPearl")) {
		    	GT_Mod.addElectrolyzerRecipe(new ItemStack(aItem, 16, aMeta), 16, GT_Mod.getGregTechItem(2, 5, 15), GT_Mod.getGregTechItem(2, 1, 10), GT_Mod.getGregTechItem(2, 4, 14), GT_Mod.getGregTechItem(2, 6, 13), 1300, 50);
		    }
		    else if (aEventName.equals("dustEnderEye")) {
		    	GT_Mod.addCentrifugeRecipe(new ItemStack(aItem, 16, aMeta), 0, GT_Mod.getGregTechItem(1, 8, 0), new ItemStack(Item.blazePowder, 8), null, null, 7000);
		    }
		    else if (aEventName.equals("dustEmerald")) {
		    	GT_Mod.addElectrolyzerRecipe(new ItemStack(aItem, 29, aMeta), 18, GT_Mod.getGregTechItem(1, 2, 18), GT_Mod.getGregTechItem(2, 3, 10), GT_Mod.getGregTechItem(2, 6, 7), GT_ModHandler.getIC2Item("airCell", 9), 600, 50);
		    	GT_Mod.addImplosionRecipe(new ItemStack(aItem, 4, aMeta), 24, new ItemStack(Item.emerald, 3), GT_MetaItem_Dust.instance.getStack(63, 12));
			}
		    else if (aEventName.equals("dustDiamond")) {
		    	OreDictionary.registerOre("itemDiamond", aEvent.Ore);
		    	GT_Mod.addImplosionRecipe(new ItemStack(aItem, 4, aMeta), 32, new ItemStack(Item.diamond, 3), GT_MetaItem_Dust.instance.getStack(63, 16));
		    }
		    else if (aEventName.equals("dustRuby")) {
		    	GT_Mod.addElectrolyzerRecipe(new ItemStack(aItem, 9, aMeta), 3, GT_Mod.getGregTechItem(1, 2, 18), GT_Mod.getGregTechItem(1, 1, 20), GT_ModHandler.getIC2Item("airCell", 3), null, 500, 50);
		    	GT_Mod.addImplosionRecipe(new ItemStack(aItem, 4, aMeta), 24, GT_OreDictUnificator.get("gemRuby", GT_MetaItem_Material.instance.getStack(32, 1), 3), GT_MetaItem_Dust.instance.getStack(63, 12));
			}
		    else if (aEventName.equals("dustSapphire")) {
		    	GT_Mod.addElectrolyzerRecipe(new ItemStack(aItem, 8, aMeta), 3, GT_Mod.getGregTechItem(1, 2, 18), GT_ModHandler.getIC2Item("airCell", 3), null, null, 400, 50);
		    	GT_Mod.addImplosionRecipe(new ItemStack(aItem, 4, aMeta), 24, GT_OreDictUnificator.get("gemSapphire", GT_MetaItem_Material.instance.getStack(33, 1), 3), GT_MetaItem_Dust.instance.getStack(63, 12));
			}
		    else if (aEventName.equals("dustGreenSapphire")) {
		    	GT_Mod.addElectrolyzerRecipe(new ItemStack(aItem, 1, aMeta), 0, GT_Mod.getGregTechItem(1, 1, 33), null, null, null, 100, 50);
		    	GT_Mod.addImplosionRecipe(new ItemStack(aItem, 4, aMeta), 24, GT_OreDictUnificator.get("gemGreenSapphire", GT_MetaItem_Material.instance.getStack(34, 1), 3), GT_MetaItem_Dust.instance.getStack(63, 12));
			}
		    else if (aEventName.equals("dustAsh")) {
		    	GT_Mod.addElectrolyzerRecipe(new ItemStack(aItem, 2, aMeta), 1, GT_Mod.getGregTechItem(2, 1, 8), null, null, null, 25, 50);
		    }
		    else if (aEventName.equals("dustCoal")) {
		    	GT_Mod.addElectrolyzerRecipe(new ItemStack(aItem, 1, aMeta), 2, GT_Mod.getGregTechItem(2, 2, 8), null, null, null, 40, 50);
		    	GT_Mod.addBlastRecipe(GT_OreDictUnificator.get("dustIron", null, 1), new ItemStack(aItem, 2, aMeta), GT_MetaItem_Material.instance.getStack(26, 1), GT_MetaItem_Dust.instance.getStack(63, 4), 500, 128, 1000);
		    	if (GT_Mod.instance.mThorium) {
		        	GT_Mod.addCentrifugeRecipe(new ItemStack(aItem, 64, aMeta), 1, GT_Mod.getGregTechItem(48, 1, 0), null, null, null, 5000);
		    	}
		    }
		    else if (aEventName.equals("dustCharcoal")) {
		    	GT_Mod.addElectrolyzerRecipe(new ItemStack(aItem, 1, aMeta), 1, GT_Mod.getGregTechItem(2, 1, 8), null, null, null, 20, 50);
		    }
		    else if (aEventName.equals("dustUranium")) {
		    	GT_ModHandler.addCompressionRecipe(new ItemStack(aItem, 1, aMeta), GT_OreDictUnificator.get("ingotUranium", null, 1));
		       	if (GT_Mod.instance.mCentrifugedUranium)
		        	GT_Mod.addCentrifugeRecipe(new ItemStack(aItem, 16, aMeta), (GT_Mod.instance.mThorium?4:8) + (GT_Mod.instance.mPlutonium?1:8) + 16, GT_ModHandler.getIC2Item("reactorUraniumSimple", 16), GT_Mod.instance.mPlutonium?GT_Mod.getGregTechItem(51, 1, 0):GT_ModHandler.getIC2Item("nearDepletedUraniumCell", 8), GT_Mod.instance.mThorium?GT_Mod.getGregTechItem(48, 4, 0):GT_ModHandler.getIC2Item("nearDepletedUraniumCell", 8), GT_Mod.getGregTechItem(1, 1, 22), 50000);
		    }
		    else if (aEventName.equals("dustTungsten")) {
		        GT_Mod.addBlastRecipe(new ItemStack(aItem, 1, aMeta), null, GT_MetaItem_Material.instance.getStack(22, 1), null, 2000, 128, 2500);
		       	GT_Mod.addElectrolyzerRecipe(new ItemStack(aItem, 1, aMeta), 1, GT_Mod.getGregTechItem(2, 1, 4), null, null, null, 25, 50);
		    }
		    else if (aEventName.equals("dustLazurite")) {
		    	OreDictionary.registerOre("itemLazurite", aEvent.Ore);
		    	GT_ModHandler.addCompressionRecipe(new ItemStack(aItem, 8, aMeta), GT_MetaItem_Material.instance.getStack(35, 1));
		       	GT_Mod.addElectrolyzerRecipe(new ItemStack(aItem, 59, aMeta), 22, GT_Mod.getGregTechItem(1, 6, 18), GT_Mod.getGregTechItem(2, 6, 7), GT_Mod.getGregTechItem(2, 8, 11), GT_Mod.getGregTechItem(2, 8, 12), 2950, 100);
		    }
		    else if (aEventName.equals("dustPyrite")) {
		    	GT_Mod.addElectrolyzerRecipe(new ItemStack(aItem, 3, aMeta), 0, GT_OreDictUnificator.get("dustIron", null, 1), GT_MetaItem_Dust.instance.getStack(8, 2), null, null, 120, 128);
		    }
		    else if (aEventName.equals("dustCalcite")) {
		       	GT_Mod.addElectrolyzerRecipe(new ItemStack(aItem, 10, aMeta), 7, GT_Mod.getGregTechItem(2, 2, 11), GT_Mod.getGregTechItem(2, 2, 8), GT_ModHandler.getIC2Item("airCell", 3), null, 700, 80);
		    }
		    else if (aEventName.equals("dustSodalite")) {
		        GT_Mod.addElectrolyzerRecipe(new ItemStack(aItem, 23, aMeta), 8, GT_Mod.getGregTechItem(2, 4, 12), GT_Mod.getGregTechItem(1, 3, 18), GT_Mod.getGregTechItem(2, 3, 7), GT_Mod.getGregTechItem(2, 1, 13), 1350, 90);
		    }
		    else if (aEventName.equals("dustNetherrack")) {
		    	if (GT_Mod.instance.mCentrifugedNetherrack)
		    		GT_Mod.addCentrifugeRecipe(new ItemStack(aItem, 16, aMeta), 0, new ItemStack(Item.redstone, 1), GT_MetaItem_Dust.instance.getStack(8, 4), GT_OreDictUnificator.get("dustCoal", null, 1), new ItemStack(Item.goldNugget, 1), 2400);
		    }
		    else if (aEventName.equals("dustEndstone")) {
		    	if (GT_Mod.instance.mCentrifugedEndstone)
		        	GT_Mod.addCentrifugeRecipe(new ItemStack(aItem, 16, aMeta), 2, GT_MetaItem_Cell.instance.getStack(6, 1), GT_MetaItem_Cell.instance.getStack(3, 1), GT_MetaItem_SmallDust.instance.getStack(22, 1), new ItemStack(Block.sand, 12), 4800);
		    }
		    else if (aEventName.equals("dustFlint")) {
		    	if (GT_Mod.instance.mCentrifugedFlint)
		    		GT_Mod.addElectrolyzerRecipe(new ItemStack(aItem, 8, aMeta), 2, GT_MetaItem_Cell.instance.getStack(7, 1), GT_ModHandler.getIC2Item("airCell", 1), null, null, 1000, 5);
		    }
		    else if (aEventName.equals("dustBauxite")) {
		    	GT_Mod.addElectrolyzerRecipe(new ItemStack(aItem, 12, aMeta), 8, GT_MetaItem_Dust.instance.getStack(18, 8), GT_MetaItem_SmallDust.instance.getStack(19, 2), GT_MetaItem_Cell.instance.getStack(0, 5), GT_ModHandler.getIC2Item("airCell", 3), 2000, 128);
		    }
		    else if (aEventName.equals("dustTungsten")) {
	        	GT_Mod.addElectrolyzerRecipe(new ItemStack(aItem, 1, aMeta), 4, GT_Mod.getGregTechItem(2, 4, 4), null, null, null, 250, 50);
	    	}
	    	else if (aEventName.equals("dustElectrum")) {
	    		GT_ModHandler.addDustToIngotSmeltingRecipe(new ItemStack(aItem, 1, aMeta), GT_OreDictUnificator.get("ingotElectrum"	, null, 1));
	        	GT_Mod.addCentrifugeRecipe(new ItemStack(aItem, 2, aMeta), 0, GT_OreDictUnificator.get("dustGold", null, 1), GT_OreDictUnificator.get("dustSilver", null, 1), null, null, 1850);
	    	}
	    	else if (aEventName.equals("dustPhosphorus")) {
	    		
	    	}
	    	else {
				System.out.println("Dust Name: " + aEvent.Name + " !!!Unknown Dust detected!!! Please report to GregTech Intergalactical for additional compatiblity. This is not an Error, it's just an Information.");
	    	}
	    }
	    else if (aEventName.startsWith("gem")) {
    		if (!aEventName.equals("gemXychorium"))
    			GT_OreDictUnificator.add(aEventName, new ItemStack(aItem, 1, aMeta));
	    	if (aEventName.equals("gemRuby")) {
	    		GT_ModHandler.addPulverisationRecipe(new ItemStack(aItem, 1, aMeta), GT_MetaItem_Dust.instance.getStack(32, 1));
	        }
	    	else if (aEventName.equals("gemSapphire")) {
	    		GT_ModHandler.addPulverisationRecipe(new ItemStack(aItem, 1, aMeta), GT_MetaItem_Dust.instance.getStack(33, 1));
	        }
	    	else if (aEventName.equals("gemGreenSapphire")) {
	    		GT_ModHandler.addPulverisationRecipe(new ItemStack(aItem, 1, aMeta), GT_MetaItem_Dust.instance.getStack(34, 1));
	        }
	    	else if (aEventName.equals("gemEmerald")) {
	    		GT_ModHandler.addPulverisationRecipe(new ItemStack(aItem, 1, aMeta), GT_MetaItem_Dust.instance.getStack(35, 1));
	        }
	    	else if (aEventName.equals("gemDiamond")) {
		    	OreDictionary.registerOre("itemDiamond", aEvent.Ore);
	    		GT_ModHandler.addPulverisationRecipe(new ItemStack(aItem, 1, aMeta), GT_MetaItem_Dust.instance.getStack(36, 1));
	        }
	    	else if (aEventName.equals("gemOlivine")) {
	    		GT_ModHandler.addPulverisationRecipe(new ItemStack(aItem, 1, aMeta), GT_MetaItem_Dust.instance.getStack(37, 1));
	    	}
	    	else if (aEventName.equals("gemGarnet")) {
	    		GT_ModHandler.addPulverisationRecipe(new ItemStack(aItem, 1, aMeta), GT_MetaItem_Dust.instance.getStack(54, 1));
	    	}
	    	else if (aEventName.equals("gemGarnetRed")) {
	    		GT_ModHandler.addPulverisationRecipe(new ItemStack(aItem, 1, aMeta), GT_MetaItem_Dust.instance.getStack(54, 1));
	    	}
	    	else if (aEventName.equals("gemGarnetYellow")) {
	    		GT_ModHandler.addPulverisationRecipe(new ItemStack(aItem, 1, aMeta), GT_MetaItem_Dust.instance.getStack(55, 1));
	    	}
	    	else if (aEventName.equals("gemApatite")) {
	    		GT_ModHandler.addPulverisationRecipe(new ItemStack(aItem, 1, aMeta), GT_ModHandler.getIC2Item("fertilizer", 4));
	    	}
	    	else if (aEventName.equals("gemLightXychorium")) {
	    		if (GT_Mod.instance.mXYCraft)
	    			GT_ModHandler.addPulverisationRecipe(new ItemStack(aItem, 1, aMeta), GT_MetaItem_Dust.instance.getStack(49, 1));
		    }
	    	else if (aEventName.equals("gemDarkXychorium")) {
	    		if (GT_Mod.instance.mXYCraft)
	    			GT_ModHandler.addPulverisationRecipe(new ItemStack(aItem, 1, aMeta), GT_MetaItem_Dust.instance.getStack(50, 1));
	    	}
	    	else if (aEventName.equals("gemRedXychorium")) {
	    		if (GT_Mod.instance.mXYCraft)
	    			GT_ModHandler.addPulverisationRecipe(new ItemStack(aItem, 1, aMeta), GT_MetaItem_Dust.instance.getStack(51, 1));
			}
	    	else if (aEventName.equals("gemBlueXychorium")) {
	    		if (GT_Mod.instance.mXYCraft)
	    			GT_ModHandler.addPulverisationRecipe(new ItemStack(aItem, 1, aMeta), GT_MetaItem_Dust.instance.getStack(52, 1));
			}
	    	else if (aEventName.equals("gemGreenXychorium")) {
	    		if (GT_Mod.instance.mXYCraft)
	    			GT_ModHandler.addPulverisationRecipe(new ItemStack(aItem, 1, aMeta), GT_MetaItem_Dust.instance.getStack(53, 1));
			}
	    	else if (aEventName.equals("gemXychorium")) {
	    		
	    	}
	    	else {
				System.out.println("Gem Name: " + aEvent.Name + " !!!Unknown Gem detected!!! Please report to GregTech Intergalactical for additional compatiblity. This is not an Error, it's just an Information.");
	    	}
	    }
	    else if (aEventName.startsWith("crystal") && !aEventName.startsWith("crystalline")) {
	    	if (aEventName.equals("crystalQuartz")) {
	    		
	    	}
	    	else {
				System.out.println("Crystal Name: " + aEvent.Name + " !!!Unknown Crystal detected!!! Please report to GregTech Intergalactical for additional compatiblity. This is not an Error, it's just an Information.");
	    	}
	    }
	    else if (aEventName.startsWith("ingot")) {
    		GT_OreDictUnificator.add(aEventName, new ItemStack(aItem, 1, aMeta));
	    	if (aEventName.equals("ingotUranium")) {
	    		GT_ModHandler.addPulverisationRecipe(new ItemStack(aItem, 1, aMeta), GT_OreDictUnificator.get("dustUranium", null, 1));
	    	}
	    	else if (aEventName.equals("ingotLightXychoridite")) {
	    		if (GT_Mod.instance.mXYCraft)
	    			GT_ModHandler.addPulverisationRecipe(new ItemStack(aItem, 1, aMeta), GT_MetaItem_Dust.instance.getStack(49, 1));
	    	}
	    	else if (aEventName.equals("ingotDarkXychoridite")) {
	    		if (GT_Mod.instance.mXYCraft)
	    			GT_ModHandler.addPulverisationRecipe(new ItemStack(aItem, 1, aMeta), GT_MetaItem_Dust.instance.getStack(50, 1));
	    	}
	    	else if (aEventName.equals("ingotRedXychoridite")) {
	    		if (GT_Mod.instance.mXYCraft)
	    			GT_ModHandler.addPulverisationRecipe(new ItemStack(aItem, 1, aMeta), GT_MetaItem_Dust.instance.getStack(51, 1));
	    	}
	    	else if (aEventName.equals("ingotBlueXychoridite")) {
	    		if (GT_Mod.instance.mXYCraft)
	    			GT_ModHandler.addPulverisationRecipe(new ItemStack(aItem, 1, aMeta), GT_MetaItem_Dust.instance.getStack(52, 1));
	    	}
	    	else if (aEventName.equals("ingotGreenXychoridite")) {
	    		if (GT_Mod.instance.mXYCraft)
	    			GT_ModHandler.addPulverisationRecipe(new ItemStack(aItem, 1, aMeta), GT_MetaItem_Dust.instance.getStack(53, 1));
	    	}
	    	else if (aEventName.equals("ingotQuicksilver")) {
	    		GT_ModHandler.addDustToIngotSmeltingRecipe(GT_MetaItem_Dust.instance.getStack(11, 1), new ItemStack(aItem, 1, aMeta));
	    	}
		    else if (aEventName.equals("ingotManganese")) {
	    		GT_ModHandler.addDustToIngotSmeltingRecipe(GT_MetaItem_Dust.instance.getStack(12, 1), new ItemStack(aItem, 1, aMeta));
		    }
	    	else if (aEventName.equals("ingotMagnesium")) {
	    		GT_ModHandler.addDustToIngotSmeltingRecipe(GT_MetaItem_Dust.instance.getStack(13, 1), new ItemStack(aItem, 1, aMeta));
	    	}
		    else if (aEventName.equals("ingotInvar")) {
		    	
		    }
		    else if (aEventName.equals("ingotHepatizon")) {
		    	
		    }
		    else if (aEventName.equals("ingotDamascus Steel")) {
		    	
		    }
		    else if (aEventName.equals("ingotAngmallen")) {
		    	
		    }
		    else if (aEventName.equals("ingotEximite")) {
		    	
		    }
		    else if (aEventName.equals("ingotMeutoite")) {
		    	
		    }
		    else if (aEventName.equals("ingotDesichalkos")) {
		    	
		    }
		    else if (aEventName.equals("ingotPrometheum")) {
		    	
		    }
		    else if (aEventName.equals("ingotDeep Iron")) {
		    	
		    }
		    else if (aEventName.equals("ingotInfuscolium")) {
		    	
		    }
		    else if (aEventName.equals("ingotOureclase")) {
		    	
		    }
		    else if (aEventName.equals("ingotAredrite")) {
		    	
		    }
		    else if (aEventName.equals("ingotAstral Silver")) {
		    	
		    }
		    else if (aEventName.equals("ingotCarmot")) {
		    	
		    }
		    else if (aEventName.equals("ingotAmordrine")) {
		    	
		    }
		    else if (aEventName.equals("ingotMithril")) {
		    	
		    }
		    else if (aEventName.equals("ingotRubracium")) {
		    	
		    }
		    else if (aEventName.equals("ingotOrichalcum")) {
		    	
		    }
		    else if (aEventName.equals("ingotAdamantine")) {
		    	
		    }
		    else if (aEventName.equals("ingotAtlarus")) {
		    	
		    }
		    else if (aEventName.equals("ingotBlack Steel")) {
		    	
		    }
		    else if (aEventName.equals("ingotHaderoth")) {
		    	
		    }
		    else if (aEventName.equals("ingotCelenegil")) {
		    	
		    }
		    else if (aEventName.equals("ingotTartarite")) {
		    	
		    }
		    else if (aEventName.equals("ingotIgnatius")) {
		    	
		    }
		    else if (aEventName.equals("ingotShadow Iron")) {
		    	
		    }
		    else if (aEventName.equals("ingotMidasium")) {
		    	
		    }
		    else if (aEventName.equals("ingotVyroxeres")) {
		    	
		    }
		    else if (aEventName.equals("ingotCeruclase")) {
		    	
		    }
		    else if (aEventName.equals("ingotKalendrite")) {
		    	
		    }
		    else if (aEventName.equals("ingotVulcanite")) {
		    	
		    }
		    else if (aEventName.equals("ingotSanguinite")) {
		    	
		    }
		    else if (aEventName.equals("ingotLemurite")) {
		    	
		    }
		    else if (aEventName.equals("ingotAdluorite")) {
		    	
		    }
		    else if (aEventName.equals("ingotShadow Steel")) {
		    	
		    }
		    else if (aEventName.equals("ingotInolashite")) {
		    	
		    }
		    else if (aEventName.equals("ingotAmordrine")) {
		    	
		    }
		    else if (aEventName.equals("ingotLivingMetal")) {
		    	
		    }
	    	else if (aEventName.equals("ingotIridium")) {
	    		
	    	}
	    	else if (aEventName.equals("ingotSilver")) {
	    		GT_ModHandler.addPulverisationRecipe(new ItemStack(aItem, 1, aMeta), GT_OreDictUnificator.get("dustSilver", null, 1));
	        }
	    	else if (aEventName.equals("ingotBronze")) {
	    		GT_ModHandler.addPulverisationRecipe(new ItemStack(aItem, 1, aMeta), GT_OreDictUnificator.get("dustBronze", null, 1));
	        }
	    	else if (aEventName.equals("ingotCopper")) {
	    		GT_ModHandler.addPulverisationRecipe(new ItemStack(aItem, 1, aMeta), GT_OreDictUnificator.get("dustCopper", null, 1));
	        }
	    	else if (aEventName.equals("ingotTin")) {
	    		GT_ModHandler.addPulverisationRecipe(new ItemStack(aItem, 1, aMeta), GT_OreDictUnificator.get("dustTin", null, 1));
	        }
	    	else if (aEventName.equals("ingotRefinedIron")) {
	    		GT_ModHandler.addPulverisationRecipe(new ItemStack(aItem, 1, aMeta), GT_OreDictUnificator.get("dustIron", null, 1));
	        }
	    	else if (aEventName.equals("ingotIron")) {
	    		GT_ModHandler.addPulverisationRecipe(new ItemStack(aItem, 1, aMeta), GT_OreDictUnificator.get("dustIron", null, 1));
	        }
	    	else if (aEventName.equals("ingotGold")) {
	    		GT_ModHandler.addPulverisationRecipe(new ItemStack(aItem, 1, aMeta), GT_OreDictUnificator.get("dustGold", null, 1));
	        }
	    	else if (aEventName.equals("ingotAluminium") || aEventName.equals("ingotAluminum")) {
	    		GT_ModHandler.addPulverisationRecipe(new ItemStack(aItem, 1, aMeta), GT_MetaItem_Dust.instance.getStack(18, 1));
	        }
	    	else if (aEventName.equals("ingotTitanium")) {
	    		GT_ModHandler.addPulverisationRecipe(new ItemStack(aItem, 1, aMeta), GT_MetaItem_Dust.instance.getStack(19, 1));
	        }
	    	else if (aEventName.equals("ingotChrome")) {
	    		GT_ModHandler.addPulverisationRecipe(new ItemStack(aItem, 1, aMeta), GT_MetaItem_Dust.instance.getStack(20, 1));
	        }
	    	else if (aEventName.equals("ingotElectrum")) {
	    		GT_ModHandler.addPulverisationRecipe(new ItemStack(aItem, 1, aMeta), GT_MetaItem_Dust.instance.getStack(21, 1));
	        }
	    	else if (aEventName.equals("ingotTungsten")) {
	    		GT_ModHandler.addPulverisationRecipe(new ItemStack(aItem, 1, aMeta), GT_MetaItem_Dust.instance.getStack(22, 1));
	        }
	    	else if (aEventName.equals("ingotLead")) {
	    		GT_ModHandler.addPulverisationRecipe(new ItemStack(aItem, 1, aMeta), GT_MetaItem_Dust.instance.getStack(23, 1));
	        }
	    	else if (aEventName.equals("ingotZinc")) {
	    		GT_ModHandler.addPulverisationRecipe(new ItemStack(aItem, 1, aMeta), GT_MetaItem_Dust.instance.getStack(24, 1));
	        }
	    	else if (aEventName.equals("ingotBrass")) {
	    		GT_ModHandler.addPulverisationRecipe(new ItemStack(aItem, 1, aMeta), GT_MetaItem_Dust.instance.getStack(25, 1));
	        }
	    	else if (aEventName.equals("ingotSteel")) {
	    		GT_ModHandler.addPulverisationRecipe(new ItemStack(aItem, 1, aMeta), GT_MetaItem_Dust.instance.getStack(26, 1));
	        }
	    	else if (aEventName.equals("ingotPlatinum")) {
	    		GT_ModHandler.addPulverisationRecipe(new ItemStack(aItem, 1, aMeta), GT_MetaItem_Dust.instance.getStack(27, 1));
	        }
	    	else if (aEventName.equals("ingotNickel")) {
	    		GT_ModHandler.addPulverisationRecipe(new ItemStack(aItem, 1, aMeta), GT_MetaItem_Dust.instance.getStack(28, 1));
	        }
	    	else {
				System.out.println("Ingot Name: " + aEvent.Name + " !!!Unknown Ingot detected!!! Please report to GregTech Intergalactical for additional compatiblity. This is not an Error, it's just an Information.");
	    	}
	    }
	    else if (aEventName.startsWith("element_")) {
		    if (aEventName.equals("element_1h")) {
	    		tCellCount =  4*GT_Mod.getCapsuleCellContainerCount(new ItemStack(aItem, 1, aMeta)) - 1;
	        	GT_Mod.addCentrifugeRecipe(new ItemStack(aItem, 4, aMeta), tCellCount<0?-tCellCount:0, GT_Mod.getGregTechItem(2, 1, 1), null, null, tCellCount>0?GT_ModHandler.getIC2Item("cell", tCellCount):null, 3000);
	    	}
		    else if (aEventName.equals("element_1he")) {
	    		tCellCount = 16*GT_Mod.getCapsuleCellContainerCount(new ItemStack(aItem, 1, aMeta)) - 1;
	    		GT_Mod.addCentrifugeRecipe(new ItemStack(aItem, 16, aMeta), tCellCount<0?-tCellCount:0, GT_Mod.getGregTechItem(2, 1, 6), null, null, tCellCount>0?GT_ModHandler.getIC2Item("cell", tCellCount):null, 10000);
	    	}
		    else if (aEventName.equals("element_1d") || aEventName.equals("element_1h2")) {
	    		tList = OreDictionary.getOres("element_1t");
	    		tList.addAll(OreDictionary.getOres("element_1h3"));
	    		tIterator1 = tList.iterator();
	    		while (tIterator1.hasNext()) GT_Mod.addFusionReactorRecipe(new ItemStack((tStack1 = tIterator1.next()).getItem(), 1, tStack1.getItemDamage()), new ItemStack(aItem, 1, aMeta), GT_Mod.getGregTechItem(2, 1, 3), 1024, 32768, 40000000);
	    		tList = OreDictionary.getOres("element_1he3");
	    		tIterator1 = tList.iterator();
	    		while (tIterator1.hasNext()) GT_Mod.addFusionReactorRecipe(new ItemStack((tStack1 = tIterator1.next()).getItem(), 1, tStack1.getItemDamage()), new ItemStack(aItem, 1, aMeta), GT_Mod.getGregTechItem(2, 1, 3), 2048, 32768, 60000000);
	    		
	    		tCellCount =  4*GT_Mod.getCapsuleCellContainerCount(new ItemStack(aItem, 1, aMeta)) - 1;
	    		GT_Mod.addCentrifugeRecipe(new ItemStack(aItem, 4, aMeta), tCellCount<0?-tCellCount:0, GT_Mod.getGregTechItem(2, 1, 2), null, null, tCellCount>0?GT_ModHandler.getIC2Item("cell", tCellCount):null, 3000);
	    	}
		    else if (aEventName.equals("element_1t") || aEventName.equals("element_1h3")) {
	    		tList = OreDictionary.getOres("element_1d");
	    		tList.addAll(OreDictionary.getOres("element_1h2"));
	    		tIterator1 = tList.iterator();
	    		while (tIterator1.hasNext()) GT_Mod.addFusionReactorRecipe(new ItemStack((tStack1 = tIterator1.next()).getItem(), 1, tStack1.getItemDamage()), new ItemStack(aItem, 1, aMeta), GT_Mod.getGregTechItem(2, 1, 3), 1024, 32768, 40000000);
	    	}
		    else if (aEventName.equals("element_1he3")) {
	    		tList = OreDictionary.getOres("element_1d");
	    		tIterator1 = tList.iterator();
	    		while (tIterator1.hasNext()) GT_Mod.addFusionReactorRecipe(new ItemStack((tStack1 = tIterator1.next()).getItem(), 1, tStack1.getItemDamage()), new ItemStack(aItem, 1, aMeta), GT_Mod.getGregTechItem(2, 1, 3), 2048, 32768, 60000000);
	    	}
		    else if (aEventName.equals("element_1w")) {
	    		tList = OreDictionary.getOres("element_1li");
	    		tIterator1 = tList.iterator();
	    		while (tIterator1.hasNext()) GT_Mod.addFusionReactorRecipe(new ItemStack((tStack1 = tIterator1.next()).getItem(), 1, tStack1.getItemDamage()), new ItemStack(aItem, 1, aMeta), GT_ModHandler.getIC2Item("iridiumOre", 1), 1024, -2048, 90000000);
	    		tList = OreDictionary.getOres("element_1be");
	    		tIterator1 = tList.iterator();
	    		while (tIterator1.hasNext()) GT_Mod.addFusionReactorRecipe(new ItemStack((tStack1 = tIterator1.next()).getItem(), 1, tStack1.getItemDamage()), new ItemStack(aItem, 1, aMeta), GT_MetaItem_Dust.instance.getStack(27, 1), 1024, -2048, 80000000);
	    	}
		    else if (aEventName.equals("element_1li")) {
	    		tList = OreDictionary.getOres("element_1w");
	    		tIterator1 = tList.iterator();
	    		while (tIterator1.hasNext()) GT_Mod.addFusionReactorRecipe(new ItemStack((tStack1 = tIterator1.next()).getItem(), 1, tStack1.getItemDamage()), new ItemStack(aItem, 1, aMeta), GT_ModHandler.getIC2Item("iridiumOre", 1), 1024, -2048, 90000000);
	    	}
		    else if (aEventName.equals("element_1be")) {
	    		tList = OreDictionary.getOres("element_1w");
	    		tIterator1 = tList.iterator();
	    		while (tIterator1.hasNext()) GT_Mod.addFusionReactorRecipe(new ItemStack((tStack1 = tIterator1.next()).getItem(), 1, tStack1.getItemDamage()), new ItemStack(aItem, 1, aMeta), GT_MetaItem_Dust.instance.getStack(27, 1), 1024, -2048, 80000000);
	    	}
		    else if (aEventName.equals("element_1c_4h") || aEventName.equals("element_1me")) {
	    		tCellCount =  5*GT_Mod.getCapsuleCellContainerCount(new ItemStack(aItem, 1, aMeta)) - 5;
	    		GT_Mod.addElectrolyzerRecipe(new ItemStack(aItem, 5, aMeta), tCellCount<0?-tCellCount:0, GT_Mod.getGregTechItem(2, 4, 0), GT_Mod.getGregTechItem(2, 1, 8), null, tCellCount>0?GT_ModHandler.getIC2Item("cell", tCellCount):null, 150, 50);
	    	}
		    else if (aEventName.equals("element_1si")) {
	    		tCellCount = 2*GT_Mod.getCapsuleCellContainerCount(new ItemStack(aItem, 1, aMeta));
	    		GT_Mod.addBlastRecipe(new ItemStack(aItem, 2, aMeta), null, GT_MetaItem_Material.instance.getStack(36, 1), tCellCount>0?GT_ModHandler.getIC2Item("cell", tCellCount):null, 1000, 128, 1500);
	    	}
		    else if (aEventName.equals("element_1c")) {
		    	
		    }
		    else if (aEventName.equals("element_1ca")) {
		    	
		    }
		    else if (aEventName.equals("element_1na")) {
		    	
		    }
		    else if (aEventName.equals("element_1cl")) {
		    	
		    }
		    else if (aEventName.equals("element_1k")) {
		    	
		    }
		    else if (aEventName.equals("element_1n")) {
		    	
		    }
		    else if (aEventName.equals("element_1hg")) {
		    	
		    }
		    else if (aEventName.equals("element_1ca_1c_3o")) {
		    	
		    }
		    else if (aEventName.equals("element_2na_2s_8o")) {
		    	
		    }
		    else if (aEventName.equals("element_2o")) {
		    	
		    }
		    else if (aEventName.equals("element_1o")) {
		    	
		    }
		    else if (aEventName.equals("element_3c_5h_3n_9o")) {
		    	
		    }
	    	else {
				System.out.println("Molecule Name: " + aEvent.Name + " !!!Unknown Molecule detected!!! Please report to GregTech Intergalactical for additional compatiblity. This is not an Error, it's just an Information.");
	    	}
    	}
	    else if (aEventName.startsWith("molecule_")) {
	    	
	    }
	    else if (aEventName.startsWith("cell_")) {
	    	
	    }
	    else if (aEventName.startsWith("element")) {
	    	
	    }
	    else if (aEventName.startsWith("glass")) {
	    	
	    }
	    else if (aEventName.startsWith("nugget")) {
	    	
	    }
	    else if (aEventName.startsWith("block")) {
	    	
	    }
	    else if (aEventName.startsWith("stair")) {
	    	
	    }
	    else if (aEventName.startsWith("slab")) {
	    	
	    }
	    else if (aEventName.startsWith("gear")) {
	    	
	    }
	    else if (aEventName.startsWith("xyOre")) {
	    	
	    }
	    else if (aEventName.startsWith("dye")) {
	    	
	    }
	    else if (aEventName.startsWith("plate")) {
	    	
	    }
	    else if (aEventName.startsWith("circuitTier")) {
	    	
	    }
	    else if (aEventName.startsWith("monitorTier")) {
	    	
	    }
	    else if (aEventName.startsWith("heatingCoilTier")) {
	    	
	    }
	    else if (aEventName.startsWith("rawMachineTier")) {
	    	
	    }
	    else if (aEventName.endsWith("CoolantStore")) {
	    	
	    }
	    else if (aEventName.endsWith("EUStore")) {
	    	
	    }
	    else if (aEventName.endsWith("EUPack")) {
	    	
	    }
	    else if (aEventName.startsWith("dirtyGravel")) {
	    	
	    }
	    else if (aEventName.startsWith("cleanGravel")) {
	    	
	    }
	    else if (aEventName.startsWith("crystalline")) {
	    	
	    }
	    else if (aEventName.startsWith("reduced")) {
	    	
	    }
	    else if (aEventName.startsWith("FZ.")) {
	    	
	    }
	    else if (aEventName.startsWith("xych")) {
	    	
	    }
	    else if (aEventName.startsWith("MiscPeripherals$")) {
	    	
	    }
	    else if (aEventName.equals("gasWood")) {
    		tCellCount =  16*GT_Mod.getCapsuleCellContainerCount(new ItemStack(aItem, 1, aMeta)) - 16;
    		GT_Mod.addElectrolyzerRecipe(new ItemStack(aItem, 16, aMeta), tCellCount<0?-tCellCount:0, GT_Mod.getGregTechItem(2, 4, 0), GT_Mod.getGregTechItem(2, 8, 8), GT_Mod.getGregTechItem(2, 4, 9), tCellCount>0?GT_ModHandler.getIC2Item("cell", tCellCount):null, 200, 100);
    	}
	    else if (aEventName.equals("woodRubber") || aEventName.equals("logRubber")) {
    		if (GT_Mod.instance.mCentrifugedRubberwood)
    			GT_Mod.addCentrifugeRecipe(new ItemStack(aItem, 16, aMeta), 12, GT_ModHandler.getIC2Item("resin", 8), GT_ModHandler.getIC2Item("plantBall", 6), GT_Mod.getGregTechItem(2, 4, 9), GT_Mod.getGregTechItem(2, 8, 8), 5000);
    		GT_Mod.addSawmillRecipe(new ItemStack(aItem, 1, aMeta), -1, GT_ModHandler.getIC2Item("resin", 1), GT_MetaItem_Dust.instance.getStack(15, 16), GT_ModHandler.getIC2Item("cell", 1));
	    }
	    else if (aEventName.equals("logWood")) {
	    	if (aMeta < 0) {
		    	for (int i = 0; i < 16; i++) {
			    	tStack2 = new ItemStack(aItem, 1, i);
			    	tStack1 = GT_ModHandler.getRecipeOutput(new ItemStack[] {tStack2, null, null, null, null, null, null, null, null});
			    	if (tStack1 != null) {
			    		tStack1 = tStack1.copy();
			    		tStack1.stackSize = (tStack1.stackSize * 3) / 2;
			    		GT_Mod.addSawmillRecipe(tStack2, -1, tStack1, GT_MetaItem_Dust.instance.getStack(15, 1), GT_ModHandler.getIC2Item("cell", 1));
			    	}
	    		}
	    	} else {
		    	tStack2 = new ItemStack(aItem, 1, aMeta);
		    	tStack1 = GT_ModHandler.getRecipeOutput(new ItemStack[] {tStack2, null, null, null, null, null, null, null, null});
		    	if (tStack1 != null) {
		    		tStack1 = tStack1.copy();
		    		tStack1.stackSize = (tStack1.stackSize * 3) / 2;
		    		GT_Mod.addSawmillRecipe(tStack2, -1, tStack1, GT_MetaItem_Dust.instance.getStack(15, 1), GT_ModHandler.getIC2Item("cell", 1));
		    	}
	    	}
	    }
	    else if (aEventName.equals("plankWood")) {
	    	if (aMeta < 0) {
		    	for (int i = 0; i < 16; i++) {
			    	//GT_Mod.addSawmillRecipe(new ItemStack(aItem, 1, i), -1, GT_MetaItem_Dust.instance.getStack(15, 2), null, GT_ModHandler.getIC2Item("cell", 1));
			    	GT_ModHandler.addPulverisationRecipe(new ItemStack(aItem, 1, i), GT_MetaItem_Dust.instance.getStack(15, 1));
			    }
	    	} else {
		    	//GT_Mod.addSawmillRecipe(new ItemStack(aItem, 1, aMeta), -1, GT_MetaItem_Dust.instance.getStack(15, 2), null, GT_ModHandler.getIC2Item("cell", 1));
		    	GT_ModHandler.addPulverisationRecipe(new ItemStack(aItem, 1, aMeta), GT_MetaItem_Dust.instance.getStack(15, 1));
	    	}
	    }
	    else if (aEventName.equals("treeSapling")) {
	    	if (aMeta < 0) {
		    	for (int i = 0; i < 16; i++) {
			    	//GT_Mod.addSawmillRecipe(new ItemStack(aItem, 1, i), -1, GT_MetaItem_Dust.instance.getStack(15, 1), null, GT_ModHandler.getIC2Item("cell", 1));
			    	GT_ModHandler.addPulverisationRecipe(new ItemStack(aItem, 1, i), GT_MetaItem_SmallDust.instance.getStack(15, 2));
		    	}
	    	} else {
		    	//GT_Mod.addSawmillRecipe(new ItemStack(aItem, 1, aMeta), -1, GT_MetaItem_Dust.instance.getStack(15, 1), null, GT_ModHandler.getIC2Item("cell", 1));
		    	GT_ModHandler.addPulverisationRecipe(new ItemStack(aItem, 1, aMeta), GT_MetaItem_SmallDust.instance.getStack(15, 2));
	    	}
	    }
	    else if (aEventName.equals("treeLeaves")) {
	    	
	    }
    	else if (aEventName.equals("itemForcicium") || aEventName.equals("ForciciumItem")) {
	    	
	    }
    	else if (aEventName.equals("itemTar") || aEventName.equals("tar")) {
	    	
	    }
	    else if (aEventName.equals("itemLazurite") || aEventName.equals("lazurite")) {
	    	
	    }
	    else if (aEventName.equals("itemDiamond") || aEventName.equals("diamond")) {
	    	
	    }
	    else if (aEventName.equals("itemIridium") || aEventName.equals("iridium")) {
	    	
	    }
	    else if (aEventName.equals("fuelCoke") || aEventName.equals("coke")) {
	    	
	    }
	    else if (aEventName.equals("itemBeeswax") || aEventName.equals("beeswax")) {
	    	
	    }
	    else if (aEventName.equals("brickPeat") || aEventName.equals("peat")) {
	    	
	    }
	    else if (aEventName.equals("itemRoyalJelly") || aEventName.equals("royalJelly")) {
	    	
	    }
	    else if (aEventName.equals("itemHoneydew") || aEventName.equals("honeydew")) {
	    	
	    }
	    else if (aEventName.equals("itemHoney") || aEventName.equals("honey")) {
	    	
	    }
	    else if (aEventName.equals("itemPollen") || aEventName.equals("pollen")) {
	    	
	    }
	    else if (aEventName.equals("itemReedTypha") || aEventName.equals("reedTypha")) {
	    	
	    }
	    else if (aEventName.equals("itemSulfuricAcid") || aEventName.equals("sulfuricAcid")) {
	    	
	    }
	    else if (aEventName.equals("itemRubber") || aEventName.equals("rubber")) {
	    	
	    }
	    else if (aEventName.equals("itemPotash") || aEventName.equals("potash")) {
	    	
	    }
	    else if (aEventName.equals("itemManganese") || aEventName.equals("manganese")) {
	    	GT_ModHandler.addPulverisationRecipe(new ItemStack(aItem, 1, aMeta), GT_MetaItem_Dust.instance.getStack(12, 1));
	    }
	    else if (aEventName.equals("itemMagnesium") || aEventName.equals("magnesium")) {
	    	GT_ModHandler.addPulverisationRecipe(new ItemStack(aItem, 1, aMeta), GT_MetaItem_Dust.instance.getStack(13, 1));
	    }
	    else if (aEventName.equals("itemPhosphorite") || aEventName.equals("phosphorite") || aEventName.equals("itemPhosphorus") || aEventName.equals("phosphorus")) {
	    	GT_ModHandler.addPulverisationRecipe(new ItemStack(aItem, 1, aMeta), GT_OreDictUnificator.get("dustPhosphorus", null, 1));
	    }
	    else if (aEventName.equals("itemBitumen") || aEventName.equals("bitumen")) {
	    	
	    }
	    else if (aEventName.equals("itemSulfur") || aEventName.equals("sulfur")) {
	    	GT_ModHandler.addPulverisationRecipe(new ItemStack(aItem, 1, aMeta), GT_MetaItem_Dust.instance.getStack(8, 1));
	    }
	    else if (aEventName.equals("itemAluminum") || aEventName.equals("aluminum") || aEventName.equals("itemAluminium") || aEventName.equals("aluminium")) {
	    	GT_ModHandler.addPulverisationRecipe(new ItemStack(aItem, 1, aMeta), GT_MetaItem_Dust.instance.getStack(18, 1));
	    }
	    else if (aEventName.equals("itemSaltpeter") || aEventName.equals("saltpeter")) {
	    	GT_ModHandler.addPulverisationRecipe(new ItemStack(aItem, 1, aMeta), GT_MetaItem_Dust.instance.getStack(9, 1));
	    }
	    else if (aEventName.equals("itemUranium") || aEventName.equals("uranium")) {
	    	GT_ModHandler.addPulverisationRecipe(new ItemStack(aItem, 1, aMeta), GT_OreDictUnificator.get("dustUranium", null, 1));
	    }
	    else if (aEventName.equals("sandCracked")) {
	    	GT_Mod.addCentrifugeRecipe(new ItemStack(aItem, 64, aMeta), -1, GT_ModHandler.getFuelCan(10000), GT_MetaItem_Dust.instance.getStack(9, 32), null, new ItemStack(Block.sand, 40), 10000);
	    }
	    else if (aEventName.equals("MonazitOre")) {
	    	
	    }
	    else if (aEventName.startsWith("item")) {
	    	System.out.println("Item Name: " + aEvent.Name + " !!!Unknown Item detected!!! Please report to GregTech Intergalactical for additional compatiblity. This is not an Error, it's just an Information.");
	    }
    	else {
			System.out.println("Thingy Name: " + aEvent.Name + " !!!Unknown 'Thingy' detected!!! This Object seems to probably not follow a valid OreDictionary Convention, or I missed a Convention. Please report to GregTech Intergalactical for additional compatiblity. This is not an Error, it's just an Information.");
    	}
    }
}
