package gregtechmod.common;

import gregtechmod.GT_Mod;
import ic2.core.IC2;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import omnitools.crafting.LexiconManager;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.TickRegistry;
import cpw.mods.fml.relauncher.Side;

public class GT_Proxy {
	public void initialize() {
		TickRegistry.registerTickHandler(new GT_TickHandler(), Side.SERVER);
		GameRegistry.registerFuelHandler(new GT_FuelHandler());
    	GameRegistry.registerWorldGenerator(new GT_Worldgenerator());
		addSounds();
		
    	/**
    	 * Because of a Bug in IC2, which multiplies Reactor EU by 5.
    	 */
    	if (IC2.energyGeneratorNuclear > 0) {
    		IC2.energyGeneratorNuclear = Math.max(1, IC2.energyGeneratorNuclear / 5);
    	}
	}
	
	public void doSound(ItemStack aStack, World aWorld, double aX, double aY, double aZ) {
		
	}
	
	public int addArmor(String aPrefix) {
		return 0;
	}
	
	public void serverStart() {
		NetworkRegistry.instance().registerGuiHandler(GT_Mod.instance, new GT_GUIHandler());
		try {
			LexiconManager.ignoreOreType("rawMachineTier00");
			LexiconManager.ignoreOreType("rawMachineTier01");
			LexiconManager.ignoreOreType("rawMachineTier02");
			LexiconManager.ignoreOreType("rawMachineTier03");
			LexiconManager.ignoreOreType("rawMachineTier04");
			LexiconManager.ignoreOreType("rawMachineTier05");
			LexiconManager.ignoreOreType("rawMachineTier06");
			LexiconManager.ignoreOreType("rawMachineTier07");
			LexiconManager.ignoreOreType("rawMachineTier08");
			LexiconManager.ignoreOreType("rawMachineTier09");
			LexiconManager.ignoreOreType("rawMachineTier10");
			LexiconManager.ignoreOreType("circuitTier00");
			LexiconManager.ignoreOreType("circuitTier01");
			LexiconManager.ignoreOreType("circuitTier02");
			LexiconManager.ignoreOreType("circuitTier03");
			LexiconManager.ignoreOreType("circuitTier04");
			LexiconManager.ignoreOreType("circuitTier05");
			LexiconManager.ignoreOreType("circuitTier06");
			LexiconManager.ignoreOreType("circuitTier07");
			LexiconManager.ignoreOreType("circuitTier08");
			LexiconManager.ignoreOreType("circuitTier09");
			LexiconManager.ignoreOreType("circuitTier10");
			LexiconManager.ignoreOreType("craftingtable");
			LexiconManager.ignoreOreType("enderChest");
			LexiconManager.ignoreOreType("10kEUStore");
			LexiconManager.ignoreOreType("100kEUStore");
			LexiconManager.ignoreOreType("1kkEUStore");
			LexiconManager.ignoreOreType("10kkEUStore");
			LexiconManager.ignoreOreType("60kEUPack");
			LexiconManager.ignoreOreType("300kEUPack");
			LexiconManager.ignoreOreType("600kEUPack");
			LexiconManager.ignoreOreType("1kkEUPack");
			LexiconManager.ignoreOreType("monitorTier00");
			LexiconManager.ignoreOreType("monitorTier01");
			LexiconManager.ignoreOreType("monitorTier02");
			LexiconManager.ignoreOreType("monitorTier03");
			LexiconManager.ignoreOreType("monitorTier04");
			LexiconManager.ignoreOreType("heatingCoilTier00");
			LexiconManager.ignoreOreType("heatingCoilTier01");
			LexiconManager.ignoreOreType("heatingCoilTier02");
			LexiconManager.ignoreOreType("heatingCoilTier03");
			LexiconManager.ignoreOreType("heatingCoilTier04");
			LexiconManager.ignoreOreType("blockLazurite");
			LexiconManager.ignoreOreType("itemLazurite");
			LexiconManager.ignoreOreType("itemDiamond");
			LexiconManager.ignoreOreType("itemSuperconductor");
			LexiconManager.ignoreOreType("itemDiamondBlade");
			LexiconManager.ignoreOreType("itemDiamondGrinder");
			LexiconManager.ignoreOreType("10kCoolantStore");
			LexiconManager.ignoreOreType("30kCoolantStore");
			LexiconManager.ignoreOreType("60kCoolantStore");
			LexiconManager.ignoreOreType("180kCoolantStore");
			LexiconManager.ignoreOreType("360kCoolantStore");
		} catch(Throwable e) {
			
		}
	}

	public void addSounds() {
    	GT_Mod.instance.mSoundItems.add(new ItemStack(Block.blockSteel, 1));
    	GT_Mod.instance.mSoundNames.add("note.harp");
    	GT_Mod.instance.mSoundCounts.add(25);
    	GT_Mod.instance.mSoundItems.add(new ItemStack(Block.blockGold, 1));
    	GT_Mod.instance.mSoundNames.add("note.pling");
    	GT_Mod.instance.mSoundCounts.add(25);
    	GT_Mod.instance.mSoundItems.add(new ItemStack(Block.stone, 1));
    	GT_Mod.instance.mSoundNames.add("note.bd");
    	GT_Mod.instance.mSoundCounts.add(25);
    	GT_Mod.instance.mSoundItems.add(new ItemStack(Block.wood, 1));
    	GT_Mod.instance.mSoundNames.add("note.bassattack");
    	GT_Mod.instance.mSoundCounts.add(25);
    	GT_Mod.instance.mSoundItems.add(new ItemStack(Block.planks, 1));
    	GT_Mod.instance.mSoundNames.add("note.bass");
    	GT_Mod.instance.mSoundCounts.add(25);
    	GT_Mod.instance.mSoundItems.add(new ItemStack(Block.glass, 1));
    	GT_Mod.instance.mSoundNames.add("note.hat");
    	GT_Mod.instance.mSoundCounts.add(25);
    	GT_Mod.instance.mSoundItems.add(new ItemStack(Block.sand, 1));
    	GT_Mod.instance.mSoundNames.add("note.snare");
    	GT_Mod.instance.mSoundCounts.add(25);
    	GT_Mod.instance.mSoundItems.add(new ItemStack(Item.recordCat, 1));
    	GT_Mod.instance.mSoundNames.add("streaming.");
    	GT_Mod.instance.mSoundCounts.add(12);
    	GT_Mod.instance.mSoundItems.add(new ItemStack(Block.tnt, 1));
    	GT_Mod.instance.mSoundNames.add("random.explode");
    	GT_Mod.instance.mSoundCounts.add(3);
    	GT_Mod.instance.mSoundItems.add(new ItemStack(Block.fire, 1));
    	GT_Mod.instance.mSoundNames.add("fire.fire");
    	GT_Mod.instance.mSoundCounts.add(1);
    	GT_Mod.instance.mSoundItems.add(new ItemStack(Item.flintAndSteel, 1));
    	GT_Mod.instance.mSoundNames.add("fire.ignite");
    	GT_Mod.instance.mSoundCounts.add(1);
    	GT_Mod.instance.mSoundItems.add(new ItemStack(Block.lavaMoving, 1));
    	GT_Mod.instance.mSoundNames.add("liquid.lavapop");
    	GT_Mod.instance.mSoundCounts.add(1);
    	GT_Mod.instance.mSoundItems.add(new ItemStack(Block.waterMoving, 1));
    	GT_Mod.instance.mSoundNames.add("liquid.water");
    	GT_Mod.instance.mSoundCounts.add(1);
    	GT_Mod.instance.mSoundItems.add(new ItemStack(Item.bucketWater, 1));
    	GT_Mod.instance.mSoundNames.add("liquid.splash");
    	GT_Mod.instance.mSoundCounts.add(1);
    	GT_Mod.instance.mSoundItems.add(new ItemStack(Item.bucketLava, 1));
    	GT_Mod.instance.mSoundNames.add("random.fizz");
    	GT_Mod.instance.mSoundCounts.add(1);
    	GT_Mod.instance.mSoundItems.add(new ItemStack(Block.portal, 1));
    	GT_Mod.instance.mSoundNames.add("portal.portal");
    	GT_Mod.instance.mSoundCounts.add(1);
    	GT_Mod.instance.mSoundItems.add(new ItemStack(Block.endPortal, 1));
    	GT_Mod.instance.mSoundNames.add("portal.travel");
    	GT_Mod.instance.mSoundCounts.add(1);
    	GT_Mod.instance.mSoundItems.add(new ItemStack(Block.endPortalFrame, 1));
    	GT_Mod.instance.mSoundNames.add("portal.trigger");
    	GT_Mod.instance.mSoundCounts.add(1);
    	GT_Mod.instance.mSoundItems.add(new ItemStack(Block.thinGlass, 1));
    	GT_Mod.instance.mSoundNames.add("random.glass");
    	GT_Mod.instance.mSoundCounts.add(1);
    	GT_Mod.instance.mSoundItems.add(new ItemStack(Item.enderPearl, 1));
    	GT_Mod.instance.mSoundNames.add("random.orb");
    	GT_Mod.instance.mSoundCounts.add(1);
    	GT_Mod.instance.mSoundItems.add(new ItemStack(Item.eyeOfEnder, 1));
    	GT_Mod.instance.mSoundNames.add("random.levelup");
    	GT_Mod.instance.mSoundCounts.add(1);
    	GT_Mod.instance.mSoundItems.add(new ItemStack(Block.stoneButton, 1));
    	GT_Mod.instance.mSoundNames.add("random.click");
    	GT_Mod.instance.mSoundCounts.add(1);
    	GT_Mod.instance.mSoundItems.add(new ItemStack(Block.cobblestone, 1));
    	GT_Mod.instance.mSoundNames.add("damage.fallbig");
    	GT_Mod.instance.mSoundCounts.add(1);
    	GT_Mod.instance.mSoundItems.add(new ItemStack(Block.dirt, 1));
    	GT_Mod.instance.mSoundNames.add("damage.fallsmall");
    	GT_Mod.instance.mSoundCounts.add(1);
    	GT_Mod.instance.mSoundItems.add(new ItemStack(Item.swordSteel, 1));
    	GT_Mod.instance.mSoundNames.add("damage.hurtflesh");
    	GT_Mod.instance.mSoundCounts.add(1);
    	GT_Mod.instance.mSoundItems.add(new ItemStack(Item.swordDiamond, 1));
    	GT_Mod.instance.mSoundNames.add("random.hurt");
    	GT_Mod.instance.mSoundCounts.add(1);
    	GT_Mod.instance.mSoundItems.add(new ItemStack(Item.bow, 1));
    	GT_Mod.instance.mSoundNames.add("random.bow");
    	GT_Mod.instance.mSoundCounts.add(1);
    	GT_Mod.instance.mSoundItems.add(new ItemStack(Item.arrow, 1));
    	GT_Mod.instance.mSoundNames.add("random.drr");
    	GT_Mod.instance.mSoundCounts.add(1);
    	GT_Mod.instance.mSoundItems.add(new ItemStack(Item.fishingRod, 1));
    	GT_Mod.instance.mSoundNames.add("random.bowhit");
    	GT_Mod.instance.mSoundCounts.add(1);
    	GT_Mod.instance.mSoundItems.add(new ItemStack(Item.shovelSteel, 1));
    	GT_Mod.instance.mSoundNames.add("random.break");
    	GT_Mod.instance.mSoundCounts.add(1);
    	GT_Mod.instance.mSoundItems.add(new ItemStack(Item.bucketEmpty, 1));
    	GT_Mod.instance.mSoundNames.add("random.breath");
    	GT_Mod.instance.mSoundCounts.add(1);
    	GT_Mod.instance.mSoundItems.add(new ItemStack(Item.potion, 1));
    	GT_Mod.instance.mSoundNames.add("random.drink");
    	GT_Mod.instance.mSoundCounts.add(1);
    	GT_Mod.instance.mSoundItems.add(new ItemStack(Item.glassBottle, 1));
    	GT_Mod.instance.mSoundNames.add("random.burp");
    	GT_Mod.instance.mSoundCounts.add(1);
    	GT_Mod.instance.mSoundItems.add(new ItemStack(Block.enderChest==null?Block.obsidian:Block.enderChest, 1));
    	GT_Mod.instance.mSoundNames.add("random.chestopen");
    	GT_Mod.instance.mSoundCounts.add(1);
    	GT_Mod.instance.mSoundItems.add(new ItemStack(Block.chest, 1));
    	GT_Mod.instance.mSoundNames.add("random.chestclosed");
    	GT_Mod.instance.mSoundCounts.add(1);
    	GT_Mod.instance.mSoundItems.add(new ItemStack(Item.doorSteel, 1));
    	GT_Mod.instance.mSoundNames.add("random.door_open");
    	GT_Mod.instance.mSoundCounts.add(1);
    	GT_Mod.instance.mSoundItems.add(new ItemStack(Item.doorWood, 1));
    	GT_Mod.instance.mSoundNames.add("random.door_close");
    	GT_Mod.instance.mSoundCounts.add(1);
    	GT_Mod.instance.mSoundItems.add(new ItemStack(Item.porkRaw, 1));
    	GT_Mod.instance.mSoundNames.add("random.eat");
    	GT_Mod.instance.mSoundCounts.add(1);
    	GT_Mod.instance.mSoundItems.add(new ItemStack(Block.cloth, 1));
    	GT_Mod.instance.mSoundNames.add("step.cloth");
    	GT_Mod.instance.mSoundCounts.add(1);
    	GT_Mod.instance.mSoundItems.add(new ItemStack(Block.grass, 1));
    	GT_Mod.instance.mSoundNames.add("step.grass");
    	GT_Mod.instance.mSoundCounts.add(1);
    	GT_Mod.instance.mSoundItems.add(new ItemStack(Block.gravel, 1));
    	GT_Mod.instance.mSoundNames.add("step.gravel");
    	GT_Mod.instance.mSoundCounts.add(1);
    	GT_Mod.instance.mSoundItems.add(new ItemStack(Block.snow, 1));
    	GT_Mod.instance.mSoundNames.add("step.snow");
    	GT_Mod.instance.mSoundCounts.add(1);
    	GT_Mod.instance.mSoundItems.add(new ItemStack(Block.pistonBase, 1));
    	GT_Mod.instance.mSoundNames.add("tile.piston.out");
    	GT_Mod.instance.mSoundCounts.add(1);
    	GT_Mod.instance.mSoundItems.add(new ItemStack(Block.pistonStickyBase, 1));
    	GT_Mod.instance.mSoundNames.add("tile.piston.in");
    	GT_Mod.instance.mSoundCounts.add(1);
    	GT_Mod.instance.mSoundItems.add(new ItemStack(Block.cobblestoneMossy, 1));
    	GT_Mod.instance.mSoundNames.add("ambient.cave.cave");
    	GT_Mod.instance.mSoundCounts.add(1);
    	GT_Mod.instance.mSoundItems.add(new ItemStack(Block.blockLapis, 1));
    	GT_Mod.instance.mSoundNames.add("ambient.weather.rain");
    	GT_Mod.instance.mSoundCounts.add(1);
    	GT_Mod.instance.mSoundItems.add(new ItemStack(Block.blockDiamond, 1));
    	GT_Mod.instance.mSoundNames.add("ambient.weather.thunder");
    	GT_Mod.instance.mSoundCounts.add(1);
	}
}