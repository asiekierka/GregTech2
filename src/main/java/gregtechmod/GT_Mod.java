package gregtechmod;

import gregtechmod.api.BaseMetaTileEntity;
import gregtechmod.api.GT_Recipe;
import gregtechmod.api.GregTech_API;
import gregtechmod.common.GT_BadModException;
import gregtechmod.common.GT_ComputercubeDescription;
import gregtechmod.common.GT_Config;
import gregtechmod.common.GT_ConnectionHandler;
import gregtechmod.common.GT_CreativeTab;
import gregtechmod.common.GT_LanguageManager;
import gregtechmod.common.GT_Log;
import gregtechmod.common.GT_ModHandler;
import gregtechmod.common.GT_OreDictHandler;
import gregtechmod.common.GT_OreDictUnificator;
import gregtechmod.common.GT_PacketHandler;
import gregtechmod.common.GT_Proxy;
import gregtechmod.common.GT_Utility;
import gregtechmod.common.blocks.GT_BlockMetaID_Block;
import gregtechmod.common.blocks.GT_BlockMetaID_Machine;
import gregtechmod.common.blocks.GT_BlockMetaID_Ore;
import gregtechmod.common.blocks.GT_Block_LightSource;
import gregtechmod.common.items.GT_CoolantCell_Item;
import gregtechmod.common.items.GT_Dataorb_Item;
import gregtechmod.common.items.GT_Debug_Item;
import gregtechmod.common.items.GT_Destructopack_Item;
import gregtechmod.common.items.GT_EnergyArmor_Item;
import gregtechmod.common.items.GT_EnergyStore_Item;
import gregtechmod.common.items.GT_Generic_Item;
import gregtechmod.common.items.GT_Jackhammer_Item;
import gregtechmod.common.items.GT_MetaBlock_Item;
import gregtechmod.common.items.GT_MetaItem_Abstract;
import gregtechmod.common.items.GT_MetaItem_Cell;
import gregtechmod.common.items.GT_MetaItem_Component;
import gregtechmod.common.items.GT_MetaItem_Dust;
import gregtechmod.common.items.GT_MetaItem_Gas;
import gregtechmod.common.items.GT_MetaItem_Liquid;
import gregtechmod.common.items.GT_MetaItem_Material;
import gregtechmod.common.items.GT_MetaItem_Plasma;
import gregtechmod.common.items.GT_MetaItem_SmallDust;
import gregtechmod.common.items.GT_MetaMachine_Item;
import gregtechmod.common.items.GT_MetaOre_Item;
import gregtechmod.common.items.GT_Mortar_Item;
import gregtechmod.common.items.GT_NeutronReflector_Item;
import gregtechmod.common.items.GT_RadioactiveCell_Item;
import gregtechmod.common.items.GT_Rockcutter_Item;
import gregtechmod.common.items.GT_SensorCard_Item;
import gregtechmod.common.items.GT_SensorKit_Item;
import gregtechmod.common.items.GT_Sonictron_Item;
import gregtechmod.common.items.GT_Teslastaff_Item;
import gregtechmod.common.tileentities.GT_MetaTileEntity_AdvancedTranslocator;
import gregtechmod.common.tileentities.GT_MetaTileEntity_BlastFurnace;
import gregtechmod.common.tileentities.GT_MetaTileEntity_CropHarvestor;
import gregtechmod.common.tileentities.GT_MetaTileEntity_DieselGenerator;
import gregtechmod.common.tileentities.GT_MetaTileEntity_ElectricAutoWorkbench;
import gregtechmod.common.tileentities.GT_MetaTileEntity_ElectricBufferAdvanced;
import gregtechmod.common.tileentities.GT_MetaTileEntity_ElectricBufferLarge;
import gregtechmod.common.tileentities.GT_MetaTileEntity_ElectricBufferSmall;
import gregtechmod.common.tileentities.GT_MetaTileEntity_ElectricItemClearer;
import gregtechmod.common.tileentities.GT_MetaTileEntity_ElectricSorter;
import gregtechmod.common.tileentities.GT_MetaTileEntity_Electrolyzer;
import gregtechmod.common.tileentities.GT_MetaTileEntity_GasTurbine;
import gregtechmod.common.tileentities.GT_MetaTileEntity_Grinder;
import gregtechmod.common.tileentities.GT_MetaTileEntity_ImplosionCompressor;
import gregtechmod.common.tileentities.GT_MetaTileEntity_Quantumtank;
import gregtechmod.common.tileentities.GT_MetaTileEntity_RockBreaker;
import gregtechmod.common.tileentities.GT_MetaTileEntity_Sawmill;
import gregtechmod.common.tileentities.GT_MetaTileEntity_Scrapboxinator;
import gregtechmod.common.tileentities.GT_MetaTileEntity_SemifluidGenerator;
import gregtechmod.common.tileentities.GT_MetaTileEntity_ThermalGenerator;
import gregtechmod.common.tileentities.GT_MetaTileEntity_Translocator;
import gregtechmod.common.tileentities.GT_TileEntityMetaID_Machine;
import gregtechmod.common.tileentities.GT_TileEntity_AESU;
import gregtechmod.common.tileentities.GT_TileEntity_Centrifuge;
import gregtechmod.common.tileentities.GT_TileEntity_ChargeOMat;
import gregtechmod.common.tileentities.GT_TileEntity_ComputerCube;
import gregtechmod.common.tileentities.GT_TileEntity_Fusionreactor;
import gregtechmod.common.tileentities.GT_TileEntity_IDSU;
import gregtechmod.common.tileentities.GT_TileEntity_LESU;
import gregtechmod.common.tileentities.GT_TileEntity_LightSource;
import gregtechmod.common.tileentities.GT_TileEntity_Lightningrod;
import gregtechmod.common.tileentities.GT_TileEntity_Matterfabricator;
import gregtechmod.common.tileentities.GT_TileEntity_PlayerDetector;
import gregtechmod.common.tileentities.GT_TileEntity_Quantumchest;
import gregtechmod.common.tileentities.GT_TileEntity_Sonictron;
import gregtechmod.common.tileentities.GT_TileEntity_Supercondensator;
import gregtechmod.common.tileentities.GT_TileEntity_Superconductor;
import gregtechmod.common.tileentities.GT_TileEntity_UUMAssembler;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompressedStreamTools;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.storage.SaveHandler;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.DungeonHooks;
import net.minecraftforge.liquids.LiquidContainerData;
import net.minecraftforge.liquids.LiquidContainerRegistry;
import net.minecraftforge.liquids.LiquidDictionary;
import net.minecraftforge.liquids.LiquidStack;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;
import codechicken.nei.api.API;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.Mod.PostInit;
import cpw.mods.fml.common.Mod.PreInit;
import cpw.mods.fml.common.Mod.ServerStarting;
import cpw.mods.fml.common.Mod.ServerStopping;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.event.FMLServerStoppingEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import cpw.mods.fml.relauncher.Side;

/**
 * @author Gregorius Techneticies
 */
@Mod(modid = "GregTech_Addon", name="GregTech-Addon", version="MC146_V2.72c", dependencies="required-after:IC2; after:factorization; after:Railcraft; after:ThermalExpansion; after:ThermalExpansion|Transport; after:ThermalExpansion|Energy; after:ThermalExpansion|Factory; after:XyCraft; after:MetallurgyCore; after:MetallurgyBase; after:MetallurgyEnder; after:MetallurgyFantasy; after:MetallurgyNether; after:MetallurgyPrecious; after:MetallurgyUtility; after:BuildCraft|Silicon; after:BuildCraft|Core; after:BuildCraft|Transport; after:BuildCraft|Factory; after:BuildCraft|Energy; after:BuildCraft|Builders; after:LiquidUU; after:TwilightForest; after:Forestry; after:RedPowerCore; after:RedPowerBase; after:RedPowerMachine; after:RedPowerCompat; after:RedPowerWiring; after:RedPowerLogic; after:RedPowerLighting; after:RedPowerWorld; after:RedPowerControl;")
@NetworkMod(clientSideRequired = true, serverSideRequired = false, channels = {"gregtech"}, packetHandler = GT_PacketHandler.class, connectionHandler = GT_ConnectionHandler.class)
public class GT_Mod {
    @Instance
    public static GT_Mod instance;
    
    @SidedProxy(clientSide = "gregtechmod.common.GT_Client", serverSide = "gregtechmod.common.GT_Proxy")
    public static GT_Proxy gregtechproxy;
    
    public static Random Randomizer = new Random(42);
    
    public static final CreativeTabs tabGregTech = new GT_CreativeTab();
    public static final GT_OreDictHandler sOreDict = new GT_OreDictHandler();
    public static GT_Config mConfig;
    public static File mLogFile;
    
    public boolean mBlockBreakerNerf, mDenseFluidGenerator, mThermalGenerator, mGasTurbine, mDieselGenerator, mForestryBronzeNerf, mQuantumTank, mSawmill, mHardIridiumPlate, mConstantEnergy, mDiamondDrillTitanium, mToolArmorMaceration, mNoStoneRecycling, mScrapboxinator, mCropHarvestor, mCinnabarMaceration, mSphaleriteMaceration, mItemClearer, mCloakingDevice, mCentrifugedSugar, mCircuitBoards, mThermionicTubes, mBCGates, mBCCircuits, mRCCircuits, mPyriteMaceration, mCentrifugedRedstone, mRockBreaker, mAutomationStuff, mCentrifugedLapis, mCentrifugedFlint, mLithiumbattery, mLithiumpack, mEAutoCrafter, mApatiteMaceration, mSteelETools, mMassfabricator, mCentrifugedBlaze, mRollingmachineAlloy, mLavaMiner, mCentrifugedMushrooms, mTitaniumAlloy, mQOverload, mSaltpeterMaceration, mSulphurMaceration, mAnimations, mMatterfabricator, mBetterQuarryRecipe, mBetterWatermillRecipe, mBetterMaceratorRecipe, mAluminiumAlloy, mPlayerdetector, mSilverAdvCircuit, mFusionreactor, mLightningrod, mQuantumchest, mIridiumblock, mUraniumMaceration, mSlime2Rubber, mRefIronPiston, mSteelPiston, mSilverCircuit;
    public boolean mAlreadyPlayed = false, mShowCapes, mDetectIDConflicts, mDebugMode, mXYCraft = false, mTEMachine, mForestryCasings, mJackHammer, mBeryliumReflector, mGrinder, mTinPincher, mCentrifugedSoulsand, mQInvincible, mQFire, mQLight, mQFood, mQAir, mQCure, mCentrifugedGlowstone, mPlutonium, mThorium, mCentrifugedDirt, mCentrifugedLava, mCentrifugedClay, mCentrifugedNetherrack, mCentrifugedEndstone, mCentrifugedResin, mCentrifugedSand, mCentrifugedRubberwood, mCentrifugedFood, mBetterWindRecipe, mQSuithelmet, mQSuitplate, mQSuitpants, mQSuitshoes, mLightHelmet, mBetterSolarRecipe, mBetterNukeRecipe, mSuperconductor, mLapotronpack, mTeslaStaff, mRockcutter, mNeutronReflector, mCentrifugedUranium, mDungeonCredits, mChargeOMat, mAESU, mDoNotInit, mIDSU, mSeedscanner, mReactorplanner, mHeliumCoolant, mNaKCoolant, mLESU, mSteelAlloy, mHeliumLaser, mDestructopack, mDungeonDisc, mDungeonLoot, mWatermillRecycling, mSonictron, mUUMAssembler, mLapotronSapphire, mEnergyRuby, mStorageblockMacerator, mDebug, mStorageblockCompressor, mRedstoneMaceration, mLapisMaceration, mCoalMaceration, mDiamondMaceration, mGemMaceration, mIridiumMaceration;
    
	public int mUpgradeCount, mTincellCount;
    
	public final Block[] mBlocks = new Block[4];
    public final Item[] mItems = new Item[256];
    public final int[] mItemIDs = new int[256], mBlockIDs = new int[] {255, 254, 253, 252};
    /** Needed for getting the Save Files */
    public static World mUniverse = null;
    /** Cape Lists! Dunno why, but as of now 3 people donated, without leaving a MC-Username for a Cape. */
    public static final ArrayList<String> sPremiumNames = new ArrayList<String>(), sAdminNames = new ArrayList<String>(), mBrainTechCapeList = new ArrayList<String>(), mGregTechCapeList = new ArrayList<String>(), mRubyList = new ArrayList<String>(), mSapphireList = new ArrayList<String>(), mBauxiteList = new ArrayList<String>();
    /** Yeah! I got a File for Slang! */
    public static Configuration sLangFile;
    
    public static final ArrayList<String>		mSoundNames		= new ArrayList<String>();
    public static final ArrayList<ItemStack>	mSoundItems		= new ArrayList<ItemStack>();
    public static final ArrayList<Integer>		mSoundCounts	= new ArrayList<Integer>();
    
    public GT_Mod() {
    	if (GregTech_API.isGregTechLoaded()) {
    		throw new GT_BadModException("Why did you install my Addon twice? Remove the second gregtechmod.zip out of your mods-Folder, you need only one of them. And another Question: Why the Hack did Forge not detect that before me?");
    	}
		GregTech_API.setGregTechLoaded();
		mGregTechCapeList.add("SpwnX");
		mGregTechCapeList.add("Hotchi");
		mGregTechCapeList.add("Seldron");
		mGregTechCapeList.add("yinscape");
		mGregTechCapeList.add("voooon24");
		mGregTechCapeList.add("djshiny99");
		mGregTechCapeList.add("megatronp");
		mGregTechCapeList.add("DZCreeper");
		mGregTechCapeList.add("Kane_Hart");
		mGregTechCapeList.add("chainman564");
		mGregTechCapeList.add("michaelbrady");
		mGregTechCapeList.add("CrafterOfMines57");
		
		mGregTechCapeList.add("adamros");
		
		mBrainTechCapeList.add("Friedi4321");
    }
    
    @PreInit
    public void preload(FMLPreInitializationEvent aEvent) {
    	File tFile = new File(new File(aEvent.getModConfigurationDirectory(), "GregTech"), "GregTech.cfg");
    	Configuration tConfig1 = new Configuration(tFile);
    	tConfig1.load();
    	tFile = new File(new File(aEvent.getModConfigurationDirectory(), "GregTech"), "BlockItemIDs.cfg");
    	Configuration tConfig2 = new Configuration(tFile);
    	tConfig2.load();
    	tFile = new File(new File(aEvent.getModConfigurationDirectory(), "GregTech"), "Recipes.cfg");
    	Configuration tConfig3 = new Configuration(tFile);
    	tConfig3.load();
    	tFile = new File(new File(aEvent.getModConfigurationDirectory(), "GregTech"), "RecipesAdvancedConfig.cfg");
    	Configuration tConfig4 = new Configuration(tFile);
    	tConfig4.load();
    	mLogFile = new File(aEvent.getModConfigurationDirectory().getParentFile(), "GregTech.log");
    	if (mLogFile.exists()) {
    		mAlreadyPlayed = true;
    		try {GT_Log.out = new PrintStream(mLogFile);} catch (FileNotFoundException e) {GT_Log.out = System.out;}
    	} else {
    		GT_Log.out = System.out;
    	}
    	
    	mDoNotInit = (tFile.getAbsolutePath().contains(".technic") || tFile.getAbsolutePath().contains(".tekkit") || tFile.getAbsolutePath().contains(".Technic") || tFile.getAbsolutePath().contains(".Tekkit"));
    	if (mDoNotInit) {
    		/**
    		 * Hello Tekkit user. I'm well aware that you decompiled my Addon to get it into Tekkit.
    		 * However, I will not change this Code, even though I know about that.
    		 * Why? Because its to prevent Kakermix from adding it, without being kind enough to just ask, and not to prevent Tekkit Users from playing.
    		 * If he would ask me, then I will remove this barrier in future Versions of this Addon.
    		 * Don't try to make me think you are him. I already got PMs by People, who were obviously not him.
    		 */
    		System.err.println("*"); System.err.println("*"); System.err.println("*");
    		System.err.println("Hello, Gregorius Techneticies here,");
    		System.err.println("I see you most likely use Tekkit, but this Mod wont load, until Kakermix asks me PERSONALLY for the inclusion of my Mod. So bug HIM for it.");
    		System.err.println("PS. I could have exploded your Worlds, but i didnt for Publicityreasons.");
    		System.err.println("PPS. This Addon is Part of the FTB-Pack.");
    		System.err.println("*"); System.err.println("*"); System.err.println("*");
    		tConfig1.get("Switching this wont help", "Tekkitsupport", false);
    		tConfig1.save();
        	return;
    	}
    	
    	mConfig = new GT_Config(tConfig1, tConfig2, tConfig3, tConfig4);
    	
    	sLangFile = new Configuration(new File(new File(aEvent.getModConfigurationDirectory(), "GregTech"), "GregTech.lang"));
    	sLangFile.load();
    }
    
	@Init
    public void load(FMLInitializationEvent aEvent) {
    	if (mDoNotInit) return;
    	
		ItemStack tStack = null, tStack2 = null, tStack3 = null;
		
	    // Blocks
		GameRegistry.registerBlock(mBlocks[0] = new GT_BlockMetaID_Block	(mBlockIDs[0]), GT_MetaBlock_Item.class		, GT_LanguageManager.mNameList0[0], "GregTech_Addon");
		GameRegistry.registerBlock(mBlocks[1] = new GT_BlockMetaID_Machine	(mBlockIDs[1]), GT_MetaMachine_Item.class	, GT_LanguageManager.mNameList1[0], "GregTech_Addon");
		GameRegistry.registerBlock(mBlocks[2] = new GT_BlockMetaID_Ore		(mBlockIDs[2]), GT_MetaOre_Item.class		, GT_LanguageManager.mNameList2[0], "GregTech_Addon");
		GameRegistry.registerBlock(mBlocks[3] = new GT_Block_LightSource	(mBlockIDs[3]), ItemBlock.class				, "GT_TransparentTileEntity", "GregTech_Addon");
		
		LanguageRegistry.addName(mBlocks[0], GT_LanguageManager.mRegionalNameList0[0]);
		LanguageRegistry.addName(mBlocks[1], GT_LanguageManager.mRegionalNameList1[0]);
		LanguageRegistry.addName(mBlocks[2], GT_LanguageManager.mRegionalNameList2[0]);
		
		for (int i=0;i<16;i++) {
			GT_LanguageManager.addStringLocalization("tile.BlockMetaID_Block."		+ GT_LanguageManager.mNameList0[i] + ".name", GT_LanguageManager.mRegionalNameList0[i]);
			GT_LanguageManager.addStringLocalization("tile.BlockMetaID_Machine."	+ GT_LanguageManager.mNameList1[i] + ".name", GT_LanguageManager.mRegionalNameList1[i]);
			GT_LanguageManager.addStringLocalization("tile.BlockMetaID_Ore."		+ GT_LanguageManager.mNameList2[i] + ".name", GT_LanguageManager.mRegionalNameList2[i]);
		}
		
		GT_ModHandler.registerOre("cell_2o"			, GT_ModHandler.getIC2Item("airCell", 1));
    	GT_ModHandler.registerOre("element_2o"		, GT_ModHandler.getIC2Item("airCell", 1));
    	GT_ModHandler.registerOre("molecule_2o"		, GT_ModHandler.getIC2Item("airCell", 1));
    	GT_ModHandler.registerOre("itemLazurite"	, new ItemStack(Item.dyePowder, 1, 4));
		GT_ModHandler.registerOre("blockLazurite"	, new ItemStack(Block.blockLapis, 1));
		GT_ModHandler.registerOre("gemDiamond"		, GT_ModHandler.getIC2Item("industrialDiamond", 1));
    	GT_ModHandler.registerOre("dustGunpowder"	, new ItemStack(Item.gunpowder, 1));
    	GT_ModHandler.registerOre("dustRedstone"	, new ItemStack(Item.redstone, 1));
    	GT_ModHandler.registerOre("dustGlowstone"	, new ItemStack(Item.lightStoneDust, 1));
    	
    	GT_ModHandler.registerOre("itemIridium"		, GT_ModHandler.getIC2Item("iridiumOre", 1));
    	GT_ModHandler.registerOre("plateIridium"	, GT_ModHandler.getIC2Item("iridiumPlate", 1));
    	GT_ModHandler.registerOre("plateCopper"		, GT_ModHandler.getIC2Item("denseCopperPlate", 1));
    	
    	GT_ModHandler.registerOre("itemRecord" , new ItemStack(Item.record13, 1));
    	GT_ModHandler.registerOre("itemRecord" , new ItemStack(Item.recordCat, 1));
    	GT_ModHandler.registerOre("itemRecord" , new ItemStack(Item.recordBlocks, 1));
    	GT_ModHandler.registerOre("itemRecord" , new ItemStack(Item.recordStrad, 1));
    	GT_ModHandler.registerOre("itemRecord" , new ItemStack(Item.recordStal, 1));
    	GT_ModHandler.registerOre("itemRecord" , new ItemStack(Item.recordFar, 1));
    	GT_ModHandler.registerOre("itemRecord" , new ItemStack(Item.recordMall, 1));
    	GT_ModHandler.registerOre("itemRecord" , new ItemStack(Item.recordMellohi, 1));
    	GT_ModHandler.registerOre("itemRecord" , new ItemStack(Item.recordWard, 1));
    	GT_ModHandler.registerOre("itemRecord" , new ItemStack(Item.recordChirp, 1));
    	GT_ModHandler.registerOre("itemRecord" , new ItemStack(Item.record11, 1));
    	
    	GT_ModHandler.registerOre("oreCoal"		, new ItemStack(Block.oreCoal, 1));
    	GT_ModHandler.registerOre("oreIron"		, new ItemStack(Block.oreIron, 1));
    	GT_ModHandler.registerOre("oreLapis"	, new ItemStack(Block.oreLapis, 1));
    	GT_ModHandler.registerOre("oreRedstone"	, new ItemStack(Block.oreRedstone, 1));
    	GT_ModHandler.registerOre("oreRedstone"	, new ItemStack(Block.oreRedstoneGlowing, 1));
    	GT_ModHandler.registerOre("oreGold"		, new ItemStack(Block.oreGold, 1));
    	GT_ModHandler.registerOre("oreDiamond"	, new ItemStack(Block.oreDiamond, 1));
    	GT_ModHandler.registerOre("oreEmerald"	, new ItemStack(Block.oreEmerald, 1));

    	GT_ModHandler.registerOre("oreSilver"			, new ItemStack(mBlocks[2], 1, 1));
    	GT_ModHandler.registerOre("oreIridium"			, new ItemStack(mBlocks[2], 1, 2));
    	GT_ModHandler.registerOre("oreRuby"				, new ItemStack(mBlocks[2], 1, 3));
    	GT_ModHandler.registerOre("oreSapphire"			, new ItemStack(mBlocks[2], 1, 4));
    	GT_ModHandler.registerOre("oreBauxite"			, new ItemStack(mBlocks[2], 1, 5));
    	GT_ModHandler.registerOre("oreNetherPyrite"		, new ItemStack(mBlocks[2], 1, 6));
    	GT_ModHandler.registerOre("oreNetherCinnabar"	, new ItemStack(mBlocks[2], 1, 7));
    	GT_ModHandler.registerOre("oreNetherSphalerite"	, new ItemStack(mBlocks[2], 1, 8));
    	GT_ModHandler.registerOre("oreEndTungstate"		, new ItemStack(mBlocks[2], 1, 9));
    	GT_ModHandler.registerOre("oreEndCooperite"		, new ItemStack(mBlocks[2], 1,10));
    	GT_ModHandler.registerOre("oreEndOlivine"		, new ItemStack(mBlocks[2], 1,11));
    	GT_ModHandler.registerOre("oreEndSodalite"		, new ItemStack(mBlocks[2], 1,12));
    	
    	GT_ModHandler.registerOre("rawMachineTier01", GT_ModHandler.getIC2Item("machine", 1));
    	GT_ModHandler.registerOre("rawMachineTier02", GT_ModHandler.getIC2Item("advancedMachine", 1));
    	GT_ModHandler.registerOre("rawMachineTier04", new ItemStack(mBlocks[0], 1, 10));
    	
    	GT_ModHandler.registerOre("circuitTier00"	, new ItemStack(Block.torchRedstoneIdle, 1, -1));
    	GT_ModHandler.registerOre("circuitTier00"	, new ItemStack(Block.torchRedstoneActive, 1, -1));
    	GT_ModHandler.registerOre("circuitTier00"	, new ItemStack(Block.lever, 1, -1));
    	GT_ModHandler.registerOre("circuitTier02"	, GT_ModHandler.getIC2Item("electronicCircuit", 1));
    	GT_ModHandler.registerOre("circuitTier04"	, GT_ModHandler.getIC2Item("advancedCircuit", 1));
    	GT_ModHandler.registerOre("circuitTier10"	, new ItemStack(mBlocks[1], 1, 4));
    	
    	GT_ModHandler.registerOre("craftingtable"	, new ItemStack(Block.workbench, 1));
    	GT_ModHandler.registerOre("craftingtable"	, new ItemStack(mBlocks[1], 1, 16));
		
    	if (Block.enderChest != null)
    	GT_ModHandler.registerOre("enderChest"		, new ItemStack(Block.enderChest, 1));
    	
    	GT_ModHandler.registerOre("glassReinforced"	, GT_ModHandler.getIC2Item("reinforcedGlass", 1));
    	
	    GT_OreDictUnificator.add("gemDiamond"		, new ItemStack(Item.diamond, 1));
		GT_OreDictUnificator.add("gemEmerald"		, new ItemStack(Item.emerald, 1));
		GT_OreDictUnificator.add("ingotIron"		, new ItemStack(Item.ingotIron, 1));
		GT_OreDictUnificator.add("ingotGold"		, new ItemStack(Item.ingotGold, 1));
		GT_OreDictUnificator.add("ingotTin"			, GT_ModHandler.getIC2Item("tinIngot", 1));
		GT_OreDictUnificator.add("ingotCopper"		, GT_ModHandler.getIC2Item("copperIngot", 1));
		GT_OreDictUnificator.add("ingotBronze"		, GT_ModHandler.getIC2Item("bronzeIngot", 1));
		GT_OreDictUnificator.add("ingotRefinedIron"	, GT_ModHandler.getIC2Item("refinedIronIngot", 1));
		GT_OreDictUnificator.add("ingotUranium"		, GT_ModHandler.getIC2Item("uraniumIngot", 1));
		GT_OreDictUnificator.add("dustIron"			, GT_ModHandler.getIC2Item("ironDust", 1));
		GT_OreDictUnificator.add("dustGold"			, GT_ModHandler.getIC2Item("goldDust", 1));
		GT_OreDictUnificator.add("dustSilver"		, GT_ModHandler.getIC2Item("silverDust", 1));
		GT_OreDictUnificator.add("dustTin"			, GT_ModHandler.getIC2Item("tinDust", 1));
		GT_OreDictUnificator.add("dustCopper"		, GT_ModHandler.getIC2Item("copperDust", 1));
		GT_OreDictUnificator.add("dustBronze"		, GT_ModHandler.getIC2Item("bronzeDust", 1));
		GT_OreDictUnificator.add("dustCoal"			, GT_ModHandler.getIC2Item("coalDust", 1));
		GT_OreDictUnificator.add("dustClay"			, GT_ModHandler.getIC2Item("clayDust", 1));
        GT_OreDictUnificator.add("blockCopper"		, GT_ModHandler.getIC2Item("copperBlock", 1));
        GT_OreDictUnificator.add("blockTin"			, GT_ModHandler.getIC2Item("tinBlock", 1));
        GT_OreDictUnificator.add("blockBronze"		, GT_ModHandler.getIC2Item("bronzeBlock", 1));
        GT_OreDictUnificator.add("blockUranium"		, GT_ModHandler.getIC2Item("uraniumBlock", 1));
        GT_OreDictUnificator.add("blockSilver"		, new ItemStack(mBlocks[0], 1, 3));
        GT_OreDictUnificator.add("blockRuby"		, new ItemStack(mBlocks[0], 1, 4));
        GT_OreDictUnificator.add("blockSapphire"	, new ItemStack(mBlocks[0], 1, 5));
        GT_OreDictUnificator.add("blockAluminum"	, new ItemStack(mBlocks[0], 1, 7));
        GT_OreDictUnificator.add("blockAluminium"	, new ItemStack(mBlocks[0], 1, 7));
        GT_OreDictUnificator.add("blockTitanium"	, new ItemStack(mBlocks[0], 1, 8));
        GT_OreDictUnificator.add("blockChrome"		, new ItemStack(mBlocks[0], 1, 9));
        GT_OreDictUnificator.add("blockSteel"		, new ItemStack(mBlocks[0], 1, 11));
        GT_OreDictUnificator.add("blockBrass"		, new ItemStack(mBlocks[0], 1, 12));
		
        if (mConfig.mUnificatorFR) {
        	GT_OreDictUnificator.set("ingotCopper"		, GT_ModHandler.getFRItem("ingotCopper", 1));
        	GT_OreDictUnificator.set("ingotTin"			, GT_ModHandler.getFRItem("ingotTin", 1));
        	GT_OreDictUnificator.set("ingotBronze"		, GT_ModHandler.getFRItem("ingotBronze", 1));
        	GT_OreDictUnificator.set("dustAsh"			, GT_ModHandler.getFRItem("ash", 1));
        	GT_OreDictUnificator.set("dustWood"			, GT_ModHandler.getFRItem("woodPulp", 1));
        	GT_OreDictUnificator.set("pulpWood"			, GT_ModHandler.getFRItem("woodPulp", 1));
        }
        if (mConfig.mUnificatorRC) {
        	GT_OreDictUnificator.set("ingotSteel"		, GT_ModHandler.getRCItem("part.ingot.steel", 1));
        	GT_OreDictUnificator.set("dustCharcoal"		, GT_ModHandler.getRCItem("dust.charcoal", 1));
        	GT_OreDictUnificator.set("dustObsidian"		, GT_ModHandler.getRCItem("dust.obsidian", 1));
        	GT_OreDictUnificator.set("dustSaltpeter"	, GT_ModHandler.getRCItem("dust.saltpeter", 1));
        	GT_OreDictUnificator.set("dustSulfur"		, GT_ModHandler.getRCItem("dust.sulfur", 1));
        }
        if (mConfig.mUnificatorTE) {
        	GT_OreDictUnificator.set("dustGold"			, GT_ModHandler.getTEItem("dustGold", 1));
        	GT_OreDictUnificator.set("dustBrass"		, GT_ModHandler.getTEItem("dustBrass", 1));
        	GT_OreDictUnificator.set("dustBronze"		, GT_ModHandler.getTEItem("dustBronze", 1));
        	GT_OreDictUnificator.set("dustCopper"		, GT_ModHandler.getTEItem("dustCopper", 1));
        	GT_OreDictUnificator.set("dustElectrum"		, GT_ModHandler.getTEItem("dustElectrum", 1));
        	GT_OreDictUnificator.set("dustInvar"		, GT_ModHandler.getTEItem("dustInvar", 1));
        	GT_OreDictUnificator.set("dustIron"			, GT_ModHandler.getTEItem("dustIron", 1));
        	GT_OreDictUnificator.set("dustLead"			, GT_ModHandler.getTEItem("dustLead", 1));
        	GT_OreDictUnificator.set("dustNickel"		, GT_ModHandler.getTEItem("dustNickel", 1));
        	GT_OreDictUnificator.set("dustObsidian"		, GT_ModHandler.getTEItem("dustObsidian", 1));
        	GT_OreDictUnificator.set("dustPlatinum"		, GT_ModHandler.getTEItem("dustPlatinum", 1));
        	GT_OreDictUnificator.set("dustSilver"		, GT_ModHandler.getTEItem("dustSilver", 1));
        	GT_OreDictUnificator.set("dustTin"			, GT_ModHandler.getTEItem("dustTin", 1));
        	GT_OreDictUnificator.set("ingotCopper"		, GT_ModHandler.getTEItem("ingotCopper", 1));
        	GT_OreDictUnificator.set("ingotElectrum"	, GT_ModHandler.getTEItem("ingotElectrum", 1));
        	GT_OreDictUnificator.set("ingotInvar"		, GT_ModHandler.getTEItem("ingotInvar", 1));
        	GT_OreDictUnificator.set("ingotLead"		, GT_ModHandler.getTEItem("ingotLead", 1));
        	GT_OreDictUnificator.set("ingotNickel"		, GT_ModHandler.getTEItem("ingotNickel", 1));
        	GT_OreDictUnificator.set("ingotPlatinum"	, GT_ModHandler.getTEItem("ingotPlatinum", 1));
        	GT_OreDictUnificator.set("ingotSilver"		, GT_ModHandler.getTEItem("ingotSilver", 1));
        	GT_OreDictUnificator.set("ingotTin"			, GT_ModHandler.getTEItem("ingotTin", 1));
        }
        
		// TileEntities
		GameRegistry.registerTileEntity(GT_TileEntityMetaID_Machine.class	, GT_LanguageManager.mNameList1[ 0]);
		GameRegistry.registerTileEntity(GT_TileEntity_Fusionreactor.class	, GT_LanguageManager.mNameList1[ 1]);
		GameRegistry.registerTileEntity(GT_TileEntity_Lightningrod.class	, GT_LanguageManager.mNameList1[ 2]);
		GameRegistry.registerTileEntity(GT_TileEntity_Quantumchest.class	, GT_LanguageManager.mNameList1[ 3]);
		GameRegistry.registerTileEntity(GT_TileEntity_ComputerCube.class	, GT_LanguageManager.mNameList1[ 4]);
		GameRegistry.registerTileEntity(GT_TileEntity_UUMAssembler.class	, GT_LanguageManager.mNameList1[ 5]);
		GameRegistry.registerTileEntity(GT_TileEntity_Sonictron.class		, GT_LanguageManager.mNameList1[ 6]);
		GameRegistry.registerTileEntity(GT_TileEntity_LESU.class			, GT_LanguageManager.mNameList1[ 7]);
		GameRegistry.registerTileEntity(GT_TileEntity_IDSU.class			, GT_LanguageManager.mNameList1[ 8]);
		GameRegistry.registerTileEntity(GT_TileEntity_AESU.class			, GT_LanguageManager.mNameList1[ 9]);
		GameRegistry.registerTileEntity(GT_TileEntity_ChargeOMat.class		, GT_LanguageManager.mNameList1[10]);
		GameRegistry.registerTileEntity(GT_TileEntity_Centrifuge.class		, GT_LanguageManager.mNameList1[11]);
		GameRegistry.registerTileEntity(GT_TileEntity_Superconductor.class	, GT_LanguageManager.mNameList1[12]);
		GameRegistry.registerTileEntity(GT_TileEntity_PlayerDetector.class	, GT_LanguageManager.mNameList1[13]);
		GameRegistry.registerTileEntity(GT_TileEntity_Matterfabricator.class, GT_LanguageManager.mNameList1[14]);
		GameRegistry.registerTileEntity(GT_TileEntity_Supercondensator.class, GT_LanguageManager.mNameList1[15]);
		GameRegistry.registerTileEntity(BaseMetaTileEntity.class			, "MetatileEntity");
		GameRegistry.registerTileEntity(GT_TileEntity_LightSource.class		, "GT_LightSource");
		
		new GT_MetaTileEntity_ElectricAutoWorkbench	(16, "GT_E_Craftingtable"		, "Electric Craftingtable");
		new GT_MetaTileEntity_Translocator			(17, "GT_Translocator"			, "Electric Translocator");
		new GT_MetaTileEntity_ElectricBufferSmall	(18, "GT_E_Buffer_Small"		, "Small Electric Buffer");
		new GT_MetaTileEntity_ElectricBufferLarge	(19, "GT_E_Buffer_Large"		, "Large Electric Buffer");
		new GT_MetaTileEntity_AdvancedTranslocator	(20, "GT_Adv_Translocator"		, "Advanced Translocator");
		new GT_MetaTileEntity_ElectricBufferAdvanced(21, "GT_Adv_Buffer"			, "Advanced Buffer");
		new GT_MetaTileEntity_RockBreaker			(22, "GT_RockBreaker"			, "Electric Rock Breaker");
		new GT_MetaTileEntity_ElectricSorter		(23, "GT_E_Sorter"				, "Electric Sorter");
		new GT_MetaTileEntity_ElectricItemClearer	(24, "GT_E_ItemClearer"			, "Electric Item Clearer");
		new GT_MetaTileEntity_Electrolyzer			(25, "GT_Electrolyzer"			, "Industrial Electrolyzer");
		new GT_MetaTileEntity_CropHarvestor			(26, "GT_Harvestor"				, "Crop Harvestor");
		new GT_MetaTileEntity_Scrapboxinator		(27, "GT_Scrapboxinator"		, "Scrapboxinator");
		new GT_MetaTileEntity_Grinder				(28, "GT_Grinder"				, "Industrial Grinder");
		new GT_MetaTileEntity_BlastFurnace			(29, "GT_BlastFurnace"			, "Industrial Blast Furnace");
		new GT_MetaTileEntity_Quantumtank			(30, "GT_QuantumTank"			, "Quantum Tank");
		new GT_MetaTileEntity_ImplosionCompressor	(31, "GT_ImplosionCompressor"	, "Implosion Compressor");
		new GT_MetaTileEntity_Sawmill				(32, "GT_Sawmill"				, "Industrial Sawmill");
		new GT_MetaTileEntity_DieselGenerator		(33, "GT_DieselGenerator"		, "Diesel Generator");
		new GT_MetaTileEntity_GasTurbine			(34, "GT_GasTurbine"			, "Gas Turbine");
		new GT_MetaTileEntity_ThermalGenerator		(35, "GT_ThermalGenerator"		, "Thermal Generator");
		new GT_MetaTileEntity_SemifluidGenerator	(36, "GT_SemifluidGenerator"	, "Semifluid Generator");
		
		// Items
		int tArmorID1 = gregtechproxy.addArmor("lapotronpack"), tArmorID2 = gregtechproxy.addArmor("lithiumbatpack"), tArmorID3 = gregtechproxy.addArmor("claokingdevice");
		
		mItems[ 0] = new GT_MetaItem_Material	(mItemIDs[  0]);
		mItems[ 1] = new GT_MetaItem_Dust		(mItemIDs[  1]);
		mItems[ 2] = new GT_MetaItem_Cell		(mItemIDs[  2]);
		mItems[ 3] = new GT_MetaItem_Component	(mItemIDs[  3]);
		mItems[ 4] = new GT_MetaItem_SmallDust	(mItemIDs[  4]);
		
		mItems[13] = new GT_MetaItem_Liquid		(mItemIDs[ 13]);
		mItems[14] = new GT_MetaItem_Gas		(mItemIDs[ 14]);
		mItems[15] = new GT_MetaItem_Plasma		(mItemIDs[ 15]);
		
		try {
		Class.forName("codechicken.nei.api.API");
		API.hideItem(mItems[4].shiftedIndex);
		API.hideItem(mItems[13].shiftedIndex);
		API.hideItem(mItems[14].shiftedIndex);
		API.hideItem(mItems[15].shiftedIndex);
		} catch(Throwable e) {}
		
		GT_MetaItem_Material.addItem(  0, "Copper Credit"				, "", "0.125 Credits");
		GT_MetaItem_Material.addItem(  1, "Silver Credit"				, "", "8 Credits");
		GT_MetaItem_Material.addItem(  2, "Gold Credit"				, "", "64 Credits");
		GT_MetaItem_Material.addItem(  3, "Diamond Credit"				, "", "512 Credits");
		GT_MetaItem_Material.addItem(  4, "Iridium Alloy Ingot"		, "", null);
		
		GT_MetaItem_Material.addItem( 13, "Magnalium Plate"		, "plateMagnalium"	, "MgAl2");
		
		GT_MetaItem_Material.addItem( 15, "Wood Plate"			, "plankWood"		, null);
		GT_MetaItem_Material.addItem( 16, "Iridium Ingot"		, "ingotIridium"	, "Ir");
		GT_MetaItem_Material.addItem( 17, "Silver Ingot"		, "ingotSilver"		, "Ag");
		GT_MetaItem_Material.addItem( 18, "Aluminium Ingot"		, "ingotAluminium"	, "Al");
		GT_MetaItem_Material.addItem( 19, "Titanium Ingot"		, "ingotTitanium"	, "Ti");
		GT_MetaItem_Material.addItem( 20, "Chrome Ingot"		, "ingotChrome"		, "Cr");
		GT_MetaItem_Material.addItem( 21, "Electrum Ingot"		, "ingotElectrum"	, "AgAu");
		GT_MetaItem_Material.addItem( 22, "Tungsten Ingot"		, "ingotTungsten"	, "W");
		GT_MetaItem_Material.addItem( 23, "Lead Ingot"			, "ingotLead"		, "Pb");
		GT_MetaItem_Material.addItem( 24, "Zinc Ingot"			, "ingotZinc"		, "Zn");
		GT_MetaItem_Material.addItem( 25, "Brass Ingot"			, "ingotBrass"		, "ZnCu3");
		GT_MetaItem_Material.addItem( 26, "Steel Ingot"			, "ingotSteel"		, "Fe");
		GT_MetaItem_Material.addItem( 27, "Platinum Ingot"		, "ingotPlatinum"	, "Pt");
		GT_MetaItem_Material.addItem( 28, "Nickel Ingot"		, "ingotNickel"		, "Ni");
		GT_MetaItem_Material.addItem( 29, "Invar Ingot"			, "ingotInvar"		, "Fe2Ni");
		
		GT_MetaItem_Material.addItem( 32, "Ruby"				, "gemRuby"			, "Al2O6Cr");
		GT_MetaItem_Material.addItem( 33, "Sapphire"			, "gemSapphire"		, "Al2O6");
		GT_MetaItem_Material.addItem( 34, "Green Sapphire"		, "gemGreenSapphire", "Al2O6");
		GT_MetaItem_Material.addItem( 35, "Chunk of Lazurite"	, "blockLazurite"	, "(Al6Si6Ca8Na8)8");
		GT_MetaItem_Material.addItem( 36, "Silicon Plate"		, "plateSilicon"	, "Si2");
		GT_MetaItem_Material.addItem( 37, "Olivine"				, "gemOlivine"		, "Mg2Fe2SiO4");
		
		GT_MetaItem_Material.addItem( 54, "Red Garnet"			, "gemGarnetRed"	, "(Al2Mg3Si1O4)3(Al2Fe3Si3O12)5(Al2Mn3Si3O12)8");
		GT_MetaItem_Material.addItem( 55, "Yellow Garnet"		, "gemGarnetYellow"	, "(Ca3Fe2Si3O12)5(Ca3Al2Si3O12)8(Ca3Cr2Si3O12)3");
		
		
		GT_MetaItem_Dust.addItem(  0, "Ender Pearl Dust"		, "EnderPearl"		, "BeK4N5Cl6");
		GT_MetaItem_Dust.addItem(  1, "Ender Eye Dust"			, "EnderEye"		, "BeK4N5Cl6C4S2");
		GT_MetaItem_Dust.addItem(  2, "Lazurite Dust"			, "Lazurite"		, "Al6Si6Ca8Na8");
		GT_MetaItem_Dust.addItem(  3, "Pyrite Dust"				, "Pyrite"			, "FeS2");
		GT_MetaItem_Dust.addItem(  4, "Calcite Dust"			, "Calcite"			, "CaCO3");
		GT_MetaItem_Dust.addItem(  5, "Sodalite Dust"			, "Sodalite"		, "Al3Si3Na4Cl");
		GT_MetaItem_Dust.addItem(  6, "Netherrack Dust"			, "Netherrack"		, null);
		GT_MetaItem_Dust.addItem(  7, "Flint Dust"				, "Flint"			, "SiO2");
		GT_MetaItem_Dust.addItem(  8, "Sulfur Dust"				, "Sulfur"			, "S");
		GT_MetaItem_Dust.addItem(  9, "Saltpeter Dust"			, "Saltpeter"		, "KNO3");
		GT_MetaItem_Dust.addItem( 10, "Endstone Dust"			, "Endstone"		, null);
		GT_MetaItem_Dust.addItem( 11, "Cinnabar Dust"			, "Cinnabar"		, "HgS");
		GT_MetaItem_Dust.addItem( 12, "Manganese Dust"			, "Manganese"		, "Mn");
		GT_MetaItem_Dust.addItem( 13, "Magnesium Dust"			, "Magnesium"		, "Mg");
		GT_MetaItem_Dust.addItem( 14, "Sphalerite Dust"			, "Sphalerite"		, "ZnS");
		GT_MetaItem_Dust.addItem( 15, "Wood Pulp"				, "Wood"			, null);
		GT_MetaItem_Dust.addItem( 16, "Uranium Dust"			, "Uranium"			, "U");
		GT_MetaItem_Dust.addItem( 17, "Bauxite Dust"			, "Bauxite"			, "TiAl16H10O12");
		GT_MetaItem_Dust.addItem( 18, "Aluminium Dust"			, "Aluminium"		, "Al");
		GT_MetaItem_Dust.addItem( 19, "Titanium Dust"			, "Titanium"		, "Ti");
		GT_MetaItem_Dust.addItem( 20, "Chrome Dust"				, "Chrome"			, "Cr");
		GT_MetaItem_Dust.addItem( 21, "Electrum Dust"			, "Electrum"		, "AgAu");
		GT_MetaItem_Dust.addItem( 22, "Tungsten Dust"			, "Tungsten"		, "W");
		GT_MetaItem_Dust.addItem( 23, "Lead Dust"				, "Lead"			, "Pb");
		GT_MetaItem_Dust.addItem( 24, "Zinc Dust"				, "Zinc"			, "Zn");
		GT_MetaItem_Dust.addItem( 25, "Brass Dust"				, "Brass"			, "ZnCu3");
		GT_MetaItem_Dust.addItem( 26, "Steel Dust"				, "Steel"			, "Fe");
		GT_MetaItem_Dust.addItem( 27, "Platinum Dust"			, "Platinum"		, "Pt");
		GT_MetaItem_Dust.addItem( 28, "Nickel Dust"				, "Nickel"			, "Ni");
		GT_MetaItem_Dust.addItem( 29, "Invar Dust"				, "Invar"			, "Fe2Ni");
		
		GT_MetaItem_Dust.addItem( 32, "Ruby Dust"				, "Ruby"			, "Al2O6Cr");
		GT_MetaItem_Dust.addItem( 33, "Sapphire Dust"			, "Sapphire"		, "Al2O6");
		GT_MetaItem_Dust.addItem( 34, "Green Sapphire Dust"		, "GreenSapphire"	, "Al2O6");
		GT_MetaItem_Dust.addItem( 35, "Emerald Dust"			, "Emerald"			, "Be3Al2Si6O18");
		GT_MetaItem_Dust.addItem( 36, "Diamond Dust"			, "Diamond"			, "C64");
		GT_MetaItem_Dust.addItem( 37, "Olivine Dust"			, "Olivine"			, "Mg2Fe2SiO4");
		
		GT_MetaItem_Dust.addItem( 45, "Phosphor Dust"			, "Phosphorus"		, "Ca3(PO4)2");
		GT_MetaItem_Dust.addItem( 46, "Obsidian Dust"			, "Obsidian"		, "MgFeSi2O8");
		GT_MetaItem_Dust.addItem( 47, "Charcoal Dust"			, "Charcoal"		, "C");
		
		if (mXYCraft) {
			GT_MetaItem_Dust.addItem( 48, "Xychorium Dust"		, "Xychorium", null);
			GT_MetaItem_Dust.addItem( 49, "Light Xychorium Dust", "Xychorium", null);
			GT_MetaItem_Dust.addItem( 50, "Dark Xychorium Dust"	, "Xychorium", null);
			GT_MetaItem_Dust.addItem( 51, "Red Xychorium Dust"	, "Xychorium", null);
			GT_MetaItem_Dust.addItem( 52, "Blue Xychorium Dust"	, "Xychorium", null);
			GT_MetaItem_Dust.addItem( 53, "Green Xychorium Dust", "Xychorium", null);
		}
		GT_MetaItem_Dust.addItem( 54, "Red Garnet Dust"			, "GarnetRed"	, "(Al2Mg3Si1O4)3(Al2Fe3Si3O12)5(Al2Mn3Si3O12)8");
		GT_MetaItem_Dust.addItem( 55, "Yellow Garnet Dust"		, "GarnetYellow", "(Ca3Fe2Si3O12)5(Ca3Al2Si3O12)8(Ca3Cr2Si3O12)3");
		GT_MetaItem_Dust.addItem( 56, "Pyrope Dust"				, "Pyrope"		, "Al2Mg3Si1O4");
		GT_MetaItem_Dust.addItem( 57, "Almandine Dust"			, "Almandine"	, "Al2Fe3Si3O12");
		GT_MetaItem_Dust.addItem( 58, "Spessartine Dust"		, "Spessartine"	, "Al2Mn3Si3O12");
		GT_MetaItem_Dust.addItem( 59, "Andradite Dust"			, "Andradite"	, "Ca3Fe2Si3O12");
		GT_MetaItem_Dust.addItem( 60, "Grossular Dust"			, "Grossular"	, "Ca3Al2Si3O12");
		GT_MetaItem_Dust.addItem( 61, "Uvarovite Dust"			, "Uvarovite"	, "Ca3Cr2Si3O12");
		GT_MetaItem_Dust.addItem( 62, "Ashes"					, "Ash"			, "C");
		GT_MetaItem_Dust.addItem( 63, "Dark Ashes"				, "Ash"			, "C");
		GT_MetaItem_Dust.addItem( 64, "Redrock Dust"			, "RedRock"		, "(CaCO3)2SiO2");
		GT_MetaItem_Dust.addItem( 65, "Marble Dust"				, "Marble"		, "Mg(CaCO3)7");
		GT_MetaItem_Dust.addItem( 66, "Basalt Dust"				, "Basalt"		, "(Mg2Fe2SiO4)(CaCO3)3(SiO2)8C4");
		
		GT_OreDictUnificator.add("pulpWood", GT_MetaItem_Dust.instance.getUnunifiedStack(15, 1));
		
		GT_MetaItem_SmallDust.addItem(240, "Coal Dust"			, "Coal"	, GT_OreDictUnificator.get("dustCoal", null, 1));
		GT_MetaItem_SmallDust.addItem(241, "Iron Dust"			, "Iron"	, GT_OreDictUnificator.get("dustIron", null, 1));
		GT_MetaItem_SmallDust.addItem(242, "Gold Dust"			, "Gold"	, GT_OreDictUnificator.get("dustGold", null, 1));
		GT_MetaItem_SmallDust.addItem(243, "Copper Dust"		, "Copper"	, GT_OreDictUnificator.get("dustCopper", null, 1));
		GT_MetaItem_SmallDust.addItem(244, "Tin Dust"			, "Tin"		, GT_OreDictUnificator.get("dustTin", null, 1));
		GT_MetaItem_SmallDust.addItem(245, "Bronze Dust"		, "Bronze"	, GT_OreDictUnificator.get("dustBronze", null, 1));
		GT_MetaItem_SmallDust.addItem(246, "Silver Dust"		, "Silver"	, GT_OreDictUnificator.get("dustSilver", null, 1));
		GT_MetaItem_SmallDust.addItem(247, "Clay Dust"			, "Clay"	, GT_OreDictUnificator.get("dustClay", null, 1));
		GT_MetaItem_SmallDust.addItem(248, "Gunpowder"			, "Gunpowder"	, new ItemStack(Item.gunpowder, 1));
		GT_MetaItem_SmallDust.addItem(249, "Redstone Dust"		, "Redstone"	, new ItemStack(Item.redstone, 1));
		GT_MetaItem_SmallDust.addItem(250, "Glowstone Dust"		, "Glowstone"	, new ItemStack(Item.lightStoneDust, 1));
		
		GT_MetaItem_Cell.addItem(  0, "Hydrogen Cell"			, "1h"			, "H");
		GT_MetaItem_Cell.addItem(  1, "Deuterium Cell"			, "1d"			, "H-2");
		GT_MetaItem_Cell.addItem(  2, "Tritium Cell"			, "1t"			, "H-3");
		GT_MetaItem_Cell.addItem(  3, "Helium Cell"				, "1he"			, "He");
		GT_MetaItem_Cell.addItem(  4, "Wolframium Cell"			, "1w"			, "W");
		GT_MetaItem_Cell.addItem(  5, "Lithium Cell"			, "1li"			, "Li");
		GT_MetaItem_Cell.addItem(  6, "Helium-3 Cell"			, "1he3"		, "He-3");
		GT_MetaItem_Cell.addItem(  7, "Silicon Cell"			, "1si"			, "Si");
		GT_MetaItem_Cell.addItem(  8, "Carbon Cell"				, "1c"			, "C");
		GT_MetaItem_Cell.addItem(  9, "Methane Cell"			, "1me"			, "CH4");
		GT_MetaItem_Cell.addItem( 10, "Berylium Cell"			, "1be"			, "Be");
		GT_MetaItem_Cell.addItem( 11, "Calcium Cell"			, "1ca"			, "Ca");
		GT_MetaItem_Cell.addItem( 12, "Sodium Cell"				, "1na"			, "Na");
		GT_MetaItem_Cell.addItem( 13, "Chlorite Cell"			, "1cl"			, "Cl");
		GT_MetaItem_Cell.addItem( 14, "Potassium Cell"			, "1k"			, "K");
		GT_MetaItem_Cell.addItem( 15, "Nitrogen Cell"			, "1n"			, "N");
		GT_MetaItem_Cell.addItem( 16, "Mercury Cell"			, "1hg"			, "Hg");
		GT_MetaItem_Cell.addItem( 17, "Oil Cell"				, ""			, "");
		GT_MetaItem_Cell.addItem( 18, "Diesel Cell"				, ""			, "");
		GT_MetaItem_Cell.addItem( 19, "Bio Diesel Cell"			, ""			, "");
		GT_MetaItem_Cell.addItem( 20, "Biomass Cell"			, ""			, "");
		GT_MetaItem_Cell.addItem( 21, "Creosote Cell"			, ""			, "");
		GT_MetaItem_Cell.addItem( 22, "Nitro-Diesel Cell"		, ""			, "");
		
		GT_MetaItem_Cell.addItem( 32, "Sodium Persulfate Cell"	, "2na_2s_8o"	, "Na2S2O8");
		GT_MetaItem_Cell.addItem( 33, "Calcium Carbonate Cell"	, "1ca_1c_3o"	, "CaCO3");
		GT_MetaItem_Cell.addItem( 34, "Glyceryl Cell"			, "3c_5h_3n_9o"	, "C3H5N3O9");
		GT_MetaItem_Cell.addItem( 35, "Nitro-Coalfuel Cell"		, ""			, "");
		
		GT_MetaItem_Gas.addItem(  0, "Hydrogen"		, "Hydrogen"	, "H"	, GT_MetaItem_Cell.instance.getUnunifiedStack( 0, 1), GT_ModHandler.getIC2Item("cell", 1));
		GT_MetaItem_Gas.addItem(  1, "Deuterium"	, "Deuterium"	, "H-2"	, GT_MetaItem_Cell.instance.getUnunifiedStack( 1, 1), GT_ModHandler.getIC2Item("cell", 1));
		GT_MetaItem_Gas.addItem(  2, "Tritium"		, "Tritium"		, "H-3"	, GT_MetaItem_Cell.instance.getUnunifiedStack( 2, 1), GT_ModHandler.getIC2Item("cell", 1));
		GT_MetaItem_Gas.addItem(  3, "Helium"		, "Helium"		, "He"	, GT_MetaItem_Cell.instance.getUnunifiedStack( 3, 1), GT_ModHandler.getIC2Item("cell", 1));
		GT_MetaItem_Gas.addItem(  6, "Helium-3"		, "Helium-3"	, "He-3", GT_MetaItem_Cell.instance.getUnunifiedStack( 6, 1), GT_ModHandler.getIC2Item("cell", 1));
		GT_MetaItem_Gas.addItem(  9, "Methane"		, "Methane"		, "CH4"	, GT_MetaItem_Cell.instance.getUnunifiedStack( 9, 1), GT_ModHandler.getIC2Item("cell", 1));
		GT_MetaItem_Gas.addItem( 15, "Nitrogen"		, "Nitrogen"	, "N"	, GT_MetaItem_Cell.instance.getUnunifiedStack(15, 1), GT_ModHandler.getIC2Item("cell", 1));
		
		GT_MetaItem_Liquid.addItem(  4, "Wolframium"			, "Wolframium"			, "W"			, GT_MetaItem_Cell.instance.getUnunifiedStack( 4, 1), GT_ModHandler.getIC2Item("cell", 1),  34,  32,  40);
		GT_MetaItem_Liquid.addItem(  5, "Lithium"				, "Lithium"				, "Li"			, GT_MetaItem_Cell.instance.getUnunifiedStack( 5, 1), GT_ModHandler.getIC2Item("cell", 1), 145, 191, 255);
		GT_MetaItem_Liquid.addItem(  7, "Silicon"				, "Silicon"				, "Si"			, GT_MetaItem_Cell.instance.getUnunifiedStack( 7, 1), GT_ModHandler.getIC2Item("cell", 1),  63,  36,  85);
		GT_MetaItem_Liquid.addItem( 10, "Berylium"				, "Berylium"			, "Be"			, GT_MetaItem_Cell.instance.getUnunifiedStack(10, 1), GT_ModHandler.getIC2Item("cell", 1),   0,  46,   0);
		GT_MetaItem_Liquid.addItem( 11, "Calcium"				, "Calcium"				, "Ca"			, GT_MetaItem_Cell.instance.getUnunifiedStack(11, 1), GT_ModHandler.getIC2Item("cell", 1), 234,  79,  97);
		GT_MetaItem_Liquid.addItem( 12, "Sodium"				, "Sodium"				, "Na"			, GT_MetaItem_Cell.instance.getUnunifiedStack(12, 1), GT_ModHandler.getIC2Item("cell", 1),  43,  72, 164);
		GT_MetaItem_Liquid.addItem( 13, "Chlorite"				, "Chlorite"			, "Cl"			, GT_MetaItem_Cell.instance.getUnunifiedStack(13, 1), GT_ModHandler.getIC2Item("cell", 1),  25, 100,  95);
		GT_MetaItem_Liquid.addItem( 14, "Potassium"				, "Potassium"			, "K"			, GT_MetaItem_Cell.instance.getUnunifiedStack(14, 1), GT_ModHandler.getIC2Item("cell", 1), 164, 181, 181);
		GT_MetaItem_Liquid.addItem( 16, "Mercury"				, "Mercury"				, "Hg"			, GT_MetaItem_Cell.instance.getUnunifiedStack(16, 1), GT_ModHandler.getIC2Item("cell", 1),  45, 155, 155);
		GT_MetaItem_Liquid.addItem( 22, "Nitro Diesel"			, "NitroFuel"			, ""			, GT_MetaItem_Cell.instance.getUnunifiedStack(22, 1), GT_ModHandler.getIC2Item("cell", 1), 191, 255, 100);
		GT_MetaItem_Liquid.addItem( 32, "Sodium Persulfate"		, "SodiumPersulfate"	, "Na2S2O8"		, GT_MetaItem_Cell.instance.getUnunifiedStack(32, 1), GT_ModHandler.getIC2Item("cell", 1),   0, 103,  67);
		GT_MetaItem_Liquid.addItem( 33, "Calcium Carbonate"		, "CalciumCarbonate"	, "CaCO3"		, GT_MetaItem_Cell.instance.getUnunifiedStack(33, 1), GT_ModHandler.getIC2Item("cell", 1),  91,  16,  18);
		GT_MetaItem_Liquid.addItem( 34, "Glyceryl"				, "Glyceryl"			, "C3H5N3O9"	, GT_MetaItem_Cell.instance.getUnunifiedStack(34, 1), GT_ModHandler.getIC2Item("cell", 1),  52, 157, 157);
		GT_MetaItem_Liquid.addItem( 35, "Nitro Coalfuel"		, "NitroCoalFuel"		, ""			, GT_MetaItem_Cell.instance.getUnunifiedStack(35, 1), GT_ModHandler.getIC2Item("cell", 1),   0,  43,  43);
		
		GT_MetaItem_Component.addItem( 0, "Energy Flow Circuit"	, "circuitTier07");
		GT_MetaItem_Component.addItem( 1, "Data Control Circuit", "circuitTier06");
		GT_MetaItem_Component.addItem( 2, "Superconductor"		, "itemSuperconductor");
		GT_MetaItem_Component.addItem( 3, "Data Storage Circuit", "circuitTier05");
		GT_MetaItem_Component.addItem( 4, "Computer Monitor"	, "monitorTier02");
		
		GT_MetaItem_Component.addItem(16, "BrainTech Aerospace Advanced Reinforced Duct Tape FAL-84", "itemDuctTape");
		GT_MetaItem_Component.addItem(17, "Diamond Sawblade"		, "itemDiamondBlade");
		GT_MetaItem_Component.addItem(18, "Diamond Grinder"			, "itemGrinder");
		GT_MetaItem_Component.addItem(19, "Kanthal Heating Coil"	, "heatingCoilTier01"); //33% Iron 33% Chrome 33% Aluminium
		GT_MetaItem_Component.addItem(20, "Nichrome Heating Coil"	, "heatingCoilTier02"); //20% Chrome 80% Nickel
		GT_MetaItem_Component.addItem(21, "Cupronickel Heating Coil", "heatingCoilTier00"); //50% Copper 50% Nickel
		GT_MetaItem_Component.addItem(22, "Machine Parts"			, "itemMachineParts");
		GT_MetaItem_Component.addItem(23, "Wolframium Grinder"		, "itemGrinder");
		
		GT_MetaItem_Component.addItem(32, "Aluminium Machine Hull"	, "rawMachineTier01");
		GT_MetaItem_Component.addItem(33, "Bronze Machine Hull"		, "rawMachineTier01");
		GT_MetaItem_Component.addItem(34, "Brass Machine Hull"		, "rawMachineTier00");
		GT_MetaItem_Component.addItem(35, "Steel Machine Hull"		, "rawMachineTier02");
		
		try {
		Class.forName("shedar.mods.ic2.nuclearcontrol.ItemCardBase");
		mItems[ 16] = new GT_SensorCard_Item		(mItemIDs[ 16]);
		mItems[ 17] = new GT_SensorKit_Item			(mItemIDs[ 17]);
		} catch (Throwable e) {}
		mItems[ 18] = new GT_EnergyArmor_Item		(mItemIDs[ 18], 1000000000, Integer.MAX_VALUE, 4, 10, -1, 100.0D, true, 1, tArmorID1);
		
		mItems[ 30] = new GT_Mortar_Item			(mItemIDs[ 30],       64, GT_OreDictUnificator.get("dustIron", null, 1));
		mItems[ 31] = new GT_Generic_Item			(mItemIDs[ 31]);
		mItems[ 32] = new GT_Sonictron_Item			(mItemIDs[ 32]);
		mItems[ 33] = new GT_Destructopack_Item		(mItemIDs[ 33]);
		mItems[ 34] = new GT_CoolantCell_Item		(mItemIDs[ 34],    60000, 1);
		mItems[ 35] = new GT_CoolantCell_Item		(mItemIDs[ 35],   180000, 3);
		mItems[ 36] = new GT_CoolantCell_Item		(mItemIDs[ 36],   360000, 6);
		mItems[ 37] = new GT_EnergyStore_Item 		(mItemIDs[ 37], 10000000, 8192, 4, 37, 37);
		mItems[ 38] = new GT_EnergyArmor_Item		(mItemIDs[ 38], 10000000, 8192, 4,  0,   512, 0.0D, false, 1, tArmorID3);
		mItems[ 39] = new GT_Jackhammer_Item		(mItemIDs[ 39],    10000,  7.5F,  50);
		mItems[ 40] = new GT_NeutronReflector_Item	(mItemIDs[ 40],        0);
		mItems[ 41] = new GT_Jackhammer_Item		(mItemIDs[ 41],    10000, 15.0F, 100);
		mItems[ 42] = new GT_Jackhammer_Item		(mItemIDs[ 42],   100000, 45.0F, 250);
		mItems[ 43] = new GT_Dataorb_Item			(mItemIDs[ 43]);
		mItems[ 44] = new GT_EnergyArmor_Item		(mItemIDs[ 44],    10000,   32, 1,  0, 16|32, 0.0D, false, 0, tArmorID1);
		mItems[ 45] = new GT_EnergyArmor_Item		(mItemIDs[ 45], 10000000, 8192, 4,  0,     0, 0.0D,  true, 1, tArmorID1);
		mItems[ 46] = new GT_Rockcutter_Item		(mItemIDs[ 46]);
		mItems[ 47] = new GT_Teslastaff_Item		(mItemIDs[ 47]);
		mItems[ 48] = new GT_RadioactiveCell_Item	(mItemIDs[ 48],    50000, 1,  1, null);
		mItems[ 49] = new GT_RadioactiveCell_Item	(mItemIDs[ 49],    50000, 2,  1, null);
		mItems[ 50] = new GT_RadioactiveCell_Item	(mItemIDs[ 50],    50000, 4,  1, null);
		mItems[ 51] = new GT_RadioactiveCell_Item	(mItemIDs[ 51],    20000, 1, 10, GT_ModHandler.getIC2Item("nearDepletedUraniumCell", 4));
		mItems[ 52] = new GT_RadioactiveCell_Item	(mItemIDs[ 52],    20000, 2, 10, GT_ModHandler.getIC2Item("nearDepletedUraniumCell", 4));
		mItems[ 53] = new GT_RadioactiveCell_Item	(mItemIDs[ 53],    20000, 4, 10, GT_ModHandler.getIC2Item("nearDepletedUraniumCell", 4));
	  //mItems[ 54] = new GT_RadioactiveCell_Item	(mItemIDs[ 54],      200, 1,  0, GT_MetaItem_Cell.instance.getUnunifiedStack(2, 1));
		mItems[ 55] = new GT_Debug_Item				(mItemIDs[ 55]);
		mItems[ 56] = new GT_EnergyStore_Item 		(mItemIDs[ 56],   100000,  128, 1, 56, 57).setMaxStackSize(16).setMaxDamage(0);
		mItems[ 57] = new GT_EnergyStore_Item 		(mItemIDs[ 57],   100000,  128, 1, 56, 57);
		mItems[ 58] = new GT_EnergyArmor_Item		(mItemIDs[ 58],   600000,  128, 1,    0,     0, 0.0D,  true, 1, tArmorID2);
	  //Shield
		mItems[ 60] = new GT_CoolantCell_Item		(mItemIDs[ 60],    60000, 1);
		mItems[ 61] = new GT_CoolantCell_Item		(mItemIDs[ 61],   180000, 3);
		mItems[ 62] = new GT_CoolantCell_Item		(mItemIDs[ 62],   360000, 6);
		
		for (int i = 16; i < mItems.length; i++) {
			if (mItems[i] != null) {
				mItems[i].setIconIndex(i);
				mItems[i].setItemName(GT_LanguageManager.mNameListItem[i]);
				GameRegistry.registerItem(mItems[i], (GT_LanguageManager.mNameListItem[i].startsWith("GT_")?"":"GT_")+GT_LanguageManager.mNameListItem[i], "GregTech_Addon");
				GT_LanguageManager.addStringLocalization(mItems[i].getItemName() + ".name", GT_LanguageManager.mRegionalNameListItem[i]);
			}
		}
		
    	GT_ModHandler.registerOre("LiBattery"		, new ItemStack(mItems[56], 1, -1));
    	GT_ModHandler.registerOre("LiBattery"		, new ItemStack(mItems[57], 1, -1));
    	GT_ModHandler.registerOre("circuitTier08"	, new ItemStack(mItems[43], 1));
    	
    	GT_ModHandler.registerOre("10kEUStore"		, new ItemStack(GT_ModHandler.getIC2Item("reBattery", 1).getItem(), 1, -1));
    	GT_ModHandler.registerOre("10kEUStore"		, new ItemStack(GT_ModHandler.getIC2Item("chargedReBattery", 1).getItem(), 1, -1));
    	GT_ModHandler.registerOre("60kEUPack"		, new ItemStack(GT_ModHandler.getIC2Item("batPack", 1).getItem(), 1, -1));
    	GT_ModHandler.registerOre("100kEUStore"		, new ItemStack(mItems[56], 1, -1));
    	GT_ModHandler.registerOre("100kEUStore"		, new ItemStack(mItems[57], 1, -1));
    	GT_ModHandler.registerOre("300kEUPack"		, new ItemStack(GT_ModHandler.getIC2Item("lapPack", 1).getItem(), 1, -1));
    	GT_ModHandler.registerOre("600kEUPack"		, new ItemStack(mItems[58], 1, -1));
    	GT_ModHandler.registerOre("100kEUStore"		, new ItemStack(GT_ModHandler.getIC2Item("energyCrystal", 1).getItem(), 1, -1));
    	GT_ModHandler.registerOre("1kkEUStore"		, new ItemStack(GT_ModHandler.getIC2Item("lapotronCrystal", 1).getItem(), 1, -1));
		GT_ModHandler.registerOre("10kkEUStore"		, new ItemStack(mItems[37], 1, -1));
		GT_ModHandler.registerOre("10kkEUPack"		, new ItemStack(mItems[45], 1, -1));
		
		GT_ModHandler.registerOre("10kCoolantStore"		, new ItemStack(GT_ModHandler.getIC2Item("reactorCoolantSimple", 1).getItem(), 1, -1));
		GT_ModHandler.registerOre("30kCoolantStore"		, new ItemStack(GT_ModHandler.getIC2Item("reactorCoolantTriple", 1).getItem(), 1, -1));
		GT_ModHandler.registerOre("60kCoolantStore"		, new ItemStack(GT_ModHandler.getIC2Item("reactorCoolantSix", 1).getItem(), 1, -1));
		GT_ModHandler.registerOre("60kCoolantStore"		, new ItemStack(mItems[60], 1, -1));
		GT_ModHandler.registerOre("60kCoolantStore"		, new ItemStack(mItems[34], 1, -1));
		GT_ModHandler.registerOre("180kCoolantStore"	, new ItemStack(mItems[61], 1, -1));
		GT_ModHandler.registerOre("180kCoolantStore"	, new ItemStack(mItems[35], 1, -1));
		GT_ModHandler.registerOre("360kCoolantStore"	, new ItemStack(mItems[62], 1, -1));
		GT_ModHandler.registerOre("360kCoolantStore"	, new ItemStack(mItems[36], 1, -1));

    	GT_ModHandler.registerOre("dyeCyan"			, GT_MetaItem_Dust.instance.getUnunifiedStack(2, 1));
    	GT_ModHandler.registerOre("dyeBlue"			, GT_MetaItem_Dust.instance.getUnunifiedStack(5, 1));
	}
	
    @PostInit
    public void postload(FMLPostInitializationEvent aEvent) {
    	if (mDoNotInit) return;
    	
		gregtechproxy.initialize();
		
		ItemStack tStack = null, tStack2 = null, tStack3 = null;
		LiquidStack tLiquid;
    	
		for (int i = 0; i < mItems.length; i++) {
			if (mItems[i] != null && Item.itemsList[mItems[i].shiftedIndex] != mItems[i]) {
				throw new GT_BadModException("One of the GregTech-Items got overloaded. Check the ID-Config of the Mod you just installed for Conflicts mentioned in the Log with 'CONFLICT @'. That is an Item-ID-Conflict! Don't bother ANY Modauthor with that, we most likely won't help you at all!");
			}
		}
		
		// Default Liquids
		if (null != (tLiquid = LiquidDictionary.getLiquid("Oil"			, 1000))) LiquidContainerRegistry.registerLiquid(new LiquidContainerData(tLiquid, GT_MetaItem_Cell.instance.getUnunifiedStack(17, 1), GT_ModHandler.getIC2Item("cell", 1)));
		if (null != (tLiquid = LiquidDictionary.getLiquid("Fuel"		, 1000))) LiquidContainerRegistry.registerLiquid(new LiquidContainerData(tLiquid, GT_MetaItem_Cell.instance.getUnunifiedStack(18, 1), GT_ModHandler.getIC2Item("cell", 1)));
		if (null != (tLiquid = LiquidDictionary.getLiquid("biofuel"		, 1000))) LiquidContainerRegistry.registerLiquid(new LiquidContainerData(tLiquid, GT_MetaItem_Cell.instance.getUnunifiedStack(19, 1), GT_ModHandler.getIC2Item("cell", 1)));
		if (null != (tLiquid = LiquidDictionary.getLiquid("biomass"		, 1000))) LiquidContainerRegistry.registerLiquid(new LiquidContainerData(tLiquid, GT_MetaItem_Cell.instance.getUnunifiedStack(20, 1), GT_ModHandler.getIC2Item("cell", 1)));
		if (null != (tLiquid = LiquidDictionary.getLiquid("Creosote Oil", 1000))) LiquidContainerRegistry.registerLiquid(new LiquidContainerData(tLiquid, GT_MetaItem_Cell.instance.getUnunifiedStack(21, 1), GT_ModHandler.getIC2Item("cell", 1)));
		
		// Adding various liquid Fuels
		addFuel(GT_ModHandler.getIC2Item("biofuelCell", 1)		, GT_ModHandler.getIC2Item("cell", 1),   6, 0);
		addFuel(GT_ModHandler.getIC2Item("coalfuelCell", 1)		, GT_ModHandler.getIC2Item("cell", 1),  16, 0);
		addFuel(GT_MetaItem_Cell.instance.getStack(19, 1)		, GT_ModHandler.getIC2Item("cell", 1),  32, 0);
		addFuel(GT_MetaItem_Cell.instance.getStack(35, 1)		, GT_ModHandler.getIC2Item("cell", 1),  48, 0);
		addFuel(GT_MetaItem_Cell.instance.getStack(18, 1)		, GT_ModHandler.getIC2Item("cell", 1), 384, 0);
		addFuel(GT_MetaItem_Cell.instance.getStack(22, 1)		, GT_ModHandler.getIC2Item("cell", 1), 400, 0);
		
		addFuel(GT_MetaItem_Cell.instance.getStack( 0, 1)		, GT_ModHandler.getIC2Item("cell", 1),  15, 1);
		addFuel(GT_MetaItem_Cell.instance.getStack( 9, 1)		, GT_ModHandler.getIC2Item("cell", 1),  45, 1);
		
		addFuel(GT_ModHandler.getIC2Item("lavaCell", 1)			, GT_ModHandler.getIC2Item("cell", 1),  30, 2);
		addFuel(GT_ModHandler.getIC2Item("reactorHeatpack", 1)	, GT_ModHandler.getIC2Item("cell", 1),  30, 2);
		
		addFuel(GT_MetaItem_Cell.instance.getStack(21, 1)		, GT_ModHandler.getIC2Item("cell", 1),   3, 3);
		addFuel(GT_MetaItem_Cell.instance.getStack(20, 1)		, GT_ModHandler.getIC2Item("cell", 1),   8, 3);
		addFuel(GT_MetaItem_Cell.instance.getStack(12, 1)		, GT_ModHandler.getIC2Item("cell", 1),  30, 3);
		addFuel(GT_MetaItem_Cell.instance.getStack( 5, 1)		, GT_ModHandler.getIC2Item("cell", 1),  60, 3);
		addFuel(GT_MetaItem_Cell.instance.getStack(17, 1)		, GT_ModHandler.getIC2Item("cell", 1),  64, 3);
		
		GT_ModHandler.addBoilerFuel(new LiquidStack(GT_MetaItem_Gas		.instance.getStack( 0, 1).itemID, 1000, GT_MetaItem_Gas		.instance.getStack( 0, 1).getItemDamage()),  12000);
		GT_ModHandler.addBoilerFuel(new LiquidStack(GT_MetaItem_Gas		.instance.getStack( 9, 1).itemID, 1000, GT_MetaItem_Gas		.instance.getStack( 9, 1).getItemDamage()),  18000);
		GT_ModHandler.addBoilerFuel(new LiquidStack(GT_MetaItem_Liquid	.instance.getStack(35, 1).itemID, 1000, GT_MetaItem_Liquid	.instance.getStack(35, 1).getItemDamage()),  18000);
		GT_ModHandler.addBoilerFuel(new LiquidStack(GT_MetaItem_Liquid	.instance.getStack( 5, 1).itemID, 1000, GT_MetaItem_Liquid	.instance.getStack( 5, 1).getItemDamage()),  24000);
		
		// Redpower OreDictUnification
        if (GT_ModHandler.mNikolite != null) {
        	GT_OreDictUnificator.set("dustNikolite", GT_ModHandler.mNikolite, mConfig.mUnificatorRP);
        	tStack2 = GT_ModHandler.mRuby;
        	tStack3 = GT_OreDictUnificator.get("gemRuby", null, 1);
	        if (tStack2 != null) {
	        	GT_ModHandler.removeRecipe(new ItemStack[] {tStack2,tStack3,tStack3,tStack3,tStack3,tStack3,tStack3,tStack3,tStack3});
	        	if (null != (tStack = GT_ModHandler.getRecipeOutput(new ItemStack[] {tStack2,tStack2,tStack2,tStack2,tStack2,tStack2,tStack2,tStack2,tStack2}))) {
		        	GT_OreDictUnificator.set("blockRuby", tStack, mConfig.mUnificatorRP);
	        	}
	        	GT_OreDictUnificator.set("gemRuby", tStack2, mConfig.mUnificatorRP);
	        }
        	tStack2 = GT_ModHandler.mSapphire;
        	tStack3 = GT_OreDictUnificator.get("gemSapphire", null, 1);
	        if (tStack2 != null) {
	        	GT_ModHandler.removeRecipe(new ItemStack[] {tStack2,tStack3,tStack3,tStack3,tStack3,tStack3,tStack3,tStack3,tStack3});
	        	if (null != (tStack = GT_ModHandler.getRecipeOutput(new ItemStack[] {tStack2,tStack2,tStack2,tStack2,tStack2,tStack2,tStack2,tStack2,tStack2}))) {
		        	GT_OreDictUnificator.set("blockSapphire", tStack, mConfig.mUnificatorRP);
	        	}
	        	GT_OreDictUnificator.set("gemSapphire", tStack2, mConfig.mUnificatorRP);
	        }
        	tStack2 = GT_ModHandler.mGreenSapphire;
        	tStack3 = GT_OreDictUnificator.get("gemGreenSapphire", null, 1);
	        if (tStack2 != null) {
	        	GT_ModHandler.removeRecipe(new ItemStack[] {tStack2,tStack3,tStack3,tStack3,tStack3,tStack3,tStack3,tStack3,tStack3});
	        	if (null != (tStack = GT_ModHandler.getRecipeOutput(new ItemStack[] {tStack2,tStack2,tStack2,tStack2,tStack2,tStack2,tStack2,tStack2,tStack2}))) {
		        	GT_OreDictUnificator.set("blockGreenSapphire", tStack, mConfig.mUnificatorRP);
	        	}
	        	GT_OreDictUnificator.set("gemGreenSapphire", tStack2, mConfig.mUnificatorRP);
	        }
        	tStack2 = GT_ModHandler.mSilver;
        	tStack3 = GT_OreDictUnificator.get("ingotSilver", null, 1);
	        if (tStack2 != null) {
	        	GT_ModHandler.removeRecipe(new ItemStack[] {tStack2,tStack3,tStack3,tStack3,tStack3,tStack3,tStack3,tStack3,tStack3});
	        	if (null != (tStack = GT_ModHandler.getRecipeOutput(new ItemStack[] {tStack2,tStack2,tStack2,tStack2,tStack2,tStack2,tStack2,tStack2,tStack2}))) {
		        	GT_OreDictUnificator.set("blockSilver", tStack, mConfig.mUnificatorRP);
	        	}
	        	GT_OreDictUnificator.set("ingotSilver", tStack2, mConfig.mUnificatorRP);
	        }
        	tStack2 = GT_ModHandler.mCopper;
        	tStack3 = GT_OreDictUnificator.get("ingotCopper", null, 1);
	        if (tStack2 != null) {
	        	GT_ModHandler.removeRecipe(new ItemStack[] {tStack2,tStack3,tStack3,tStack3,tStack3,tStack3,tStack3,tStack3,tStack3});
	        	if (null != (tStack = GT_ModHandler.getRecipeOutput(new ItemStack[] {tStack2,tStack2,tStack2,tStack2,tStack2,tStack2,tStack2,tStack2,tStack2}))) {
		        	GT_OreDictUnificator.set("blockCopper", tStack, mConfig.mUnificatorRP);
	        	}
	        	GT_OreDictUnificator.set("ingotCopper", tStack2, mConfig.mUnificatorRP);
	        }
        	tStack2 = GT_ModHandler.mTin;
        	tStack3 = GT_OreDictUnificator.get("ingotTin", null, 1);
	        if (tStack2 != null) {
	        	GT_ModHandler.removeRecipe(new ItemStack[] {tStack2,tStack3,tStack3,tStack3,tStack3,tStack3,tStack3,tStack3,tStack3});
	        	if (null != (tStack = GT_ModHandler.getRecipeOutput(new ItemStack[] {tStack2,tStack2,tStack2,tStack2,tStack2,tStack2,tStack2,tStack2,tStack2}))) {
		        	GT_OreDictUnificator.set("blockTin", tStack, mConfig.mUnificatorRP);
	        	}
	        	GT_OreDictUnificator.set("ingotTin", tStack2, mConfig.mUnificatorRP);
	        }
        	tStack2 = GT_ModHandler.mBrass;
        	tStack3 = GT_OreDictUnificator.get("ingotBrass", null, 1);
	        if (tStack2 != null) {
	        	GT_ModHandler.removeRecipe(new ItemStack[] {tStack2,tStack3,tStack3,tStack3,tStack3,tStack3,tStack3,tStack3,tStack3});
	        	if (null != (tStack = GT_ModHandler.getRecipeOutput(new ItemStack[] {tStack2,tStack2,tStack2,tStack2,tStack2,tStack2,tStack2,tStack2,tStack2}))) {
		        	GT_OreDictUnificator.set("blockBrass", tStack, mConfig.mUnificatorRP);
	        	}
	        	GT_OreDictUnificator.set("ingotBrass", tStack2, mConfig.mUnificatorRP);
	        }
        }
        
		// Dungeon-Loot
		if (mDungeonLoot) {
			DungeonHooks.addDungeonLoot(GT_OreDictUnificator.get("ingotSilver"		, null, 1), 100, 1, 4);
			DungeonHooks.addDungeonLoot(GT_OreDictUnificator.get("gemEmerald"		, null, 1),  20, 1, 4);
			DungeonHooks.addDungeonLoot(GT_OreDictUnificator.get("gemRuby"			, null, 1),  20, 1, 4);
			DungeonHooks.addDungeonLoot(GT_OreDictUnificator.get("gemSapphire"		, null, 1),  20, 1, 4);
			DungeonHooks.addDungeonLoot(GT_OreDictUnificator.get("gemGreenSapphire"	, null, 1),  20, 1, 4);
		}
		
		// Industrial Credits in Dungeons
		if (mDungeonCredits) {
			DungeonHooks.addDungeonLoot(GT_MetaItem_Material.instance.getStack(0, 1),  10, 4, 24);
			DungeonHooks.addDungeonLoot(GT_ModHandler.getIC2Item("coin", 1)			,   5, 4, 12);
			DungeonHooks.addDungeonLoot(GT_MetaItem_Material.instance.getStack(1, 1),   2, 1,  8);
			DungeonHooks.addDungeonLoot(GT_MetaItem_Material.instance.getStack(2, 1),   1, 1,  4);
			DungeonHooks.addDungeonLoot(GT_MetaItem_Material.instance.getStack(3, 1),   1, 1,  1);
		}
		
		// Record-Fix
		if (mDungeonDisc) {
			DungeonHooks.addDungeonLoot(new ItemStack(Item.recordChirp), 5);
			DungeonHooks.addDungeonLoot(new ItemStack(Item.record11), 5);
			DungeonHooks.addDungeonLoot(new ItemStack(Item.recordBlocks), 5);
			DungeonHooks.addDungeonLoot(new ItemStack(Item.recordStrad), 5);
			DungeonHooks.addDungeonLoot(new ItemStack(Item.recordStal), 5);
			DungeonHooks.addDungeonLoot(new ItemStack(Item.recordFar), 5);
			DungeonHooks.addDungeonLoot(new ItemStack(Item.recordMall), 5);
			DungeonHooks.addDungeonLoot(new ItemStack(Item.recordMellohi), 5);
			DungeonHooks.addDungeonLoot(new ItemStack(Item.recordWard), 5);
			DungeonHooks.addDungeonLoot(new ItemStack(Item.recordWait), 5);
		}
        
        // Stackabilities
    	GT_ModHandler.getIC2Item("overclockerUpgrade", 1).getItem().setMaxStackSize(mUpgradeCount);
    	Item.cake.setMaxStackSize(64);
    	
	    GT_ModHandler.addCompressionRecipe(new ItemStack(Block.sand, 4, 0), new ItemStack(Block.sandStone, 1, 0));
		
		GT_ModHandler.addIC2MatterAmplifier(GT_OreDictUnificator.get("dustEnderPearl"	, null, 1),  50000);
		GT_ModHandler.addIC2MatterAmplifier(GT_OreDictUnificator.get("dustRuby"			, null, 1),  50000);
		GT_ModHandler.addIC2MatterAmplifier(GT_OreDictUnificator.get("dustSapphire"		, null, 1),  50000);
		GT_ModHandler.addIC2MatterAmplifier(GT_OreDictUnificator.get("dustGreenSapphire", null, 1),  50000);
		GT_ModHandler.addIC2MatterAmplifier(GT_OreDictUnificator.get("dustOlivine"		, null, 1),  50000);
		GT_ModHandler.addIC2MatterAmplifier(GT_OreDictUnificator.get("dustDiamond"		, null, 1), 125000);
		if (mXYCraft) {
			GT_ModHandler.addIC2MatterAmplifier(GT_MetaItem_Dust.instance.getStack(48, 1),  40000);
			GT_ModHandler.addIC2MatterAmplifier(GT_MetaItem_Dust.instance.getStack(49, 1),  50000);
			GT_ModHandler.addIC2MatterAmplifier(GT_MetaItem_Dust.instance.getStack(50, 1),  50000);
			GT_ModHandler.addIC2MatterAmplifier(GT_MetaItem_Dust.instance.getStack(51, 1),  50000);
			GT_ModHandler.addIC2MatterAmplifier(GT_MetaItem_Dust.instance.getStack(52, 1),  50000);
			GT_ModHandler.addIC2MatterAmplifier(GT_MetaItem_Dust.instance.getStack(53, 1),  50000);
		}
		if (mNoStoneRecycling) {
			GT_ModHandler.addToRecyclerBlackList(new ItemStack(Block.sand, 1, -1));
			GT_ModHandler.addToRecyclerBlackList(new ItemStack(Block.sandStone, 1, -1));
			GT_ModHandler.addToRecyclerBlackList(new ItemStack(Block.glass, 1, -1));
			GT_ModHandler.addToRecyclerBlackList(new ItemStack(Block.thinGlass, 1, -1));
			GT_ModHandler.addToRecyclerBlackList(new ItemStack(Block.cobblestone, 1, -1));
			GT_ModHandler.addToRecyclerBlackList(new ItemStack(Block.stairCompactCobblestone, 1, -1));
			GT_ModHandler.addToRecyclerBlackList(new ItemStack(Block.stairsStoneBrickSmooth, 1, -1));
			GT_ModHandler.addToRecyclerBlackList(new ItemStack(Block.stoneOvenActive, 1, -1));
			GT_ModHandler.addToRecyclerBlackList(new ItemStack(Block.stoneOvenIdle, 1, -1));
			GT_ModHandler.addToRecyclerBlackList(new ItemStack(Block.stoneSingleSlab, 1, -1));
			GT_ModHandler.addToRecyclerBlackList(new ItemStack(Block.stoneDoubleSlab, 1, -1));
			GT_ModHandler.addToRecyclerBlackList(new ItemStack(Block.stoneButton, 1, -1));
			GT_ModHandler.addToRecyclerBlackList(new ItemStack(Block.stoneBrick, 1, -1));
			GT_ModHandler.addToRecyclerBlackList(new ItemStack(Block.stone, 1, -1));
		}
		
		GT_ModHandler.addScrapboxDrop(0.1F, GT_OreDictUnificator.get("gemEmerald"		, null,  1));
		GT_ModHandler.addScrapboxDrop(0.1F, GT_OreDictUnificator.get("gemRuby"			, null,  1));
		GT_ModHandler.addScrapboxDrop(0.1F, GT_OreDictUnificator.get("gemSapphire"		, null,  1));
		GT_ModHandler.addScrapboxDrop(0.1F, GT_OreDictUnificator.get("gemGreenSapphire"	, null,  1));
		GT_ModHandler.addScrapboxDrop(0.1F, GT_OreDictUnificator.get("gemOlivine"		, null,  1));
		GT_ModHandler.addScrapboxDrop(0.1F, GT_MetaItem_SmallDust.instance.getStack(28, 1));
		GT_ModHandler.addScrapboxDrop(0.1F, GT_MetaItem_SmallDust.instance.getStack(27, 1));
		GT_ModHandler.addScrapboxDrop(0.1F, GT_MetaItem_SmallDust.instance.getStack(22, 1));
		GT_ModHandler.addScrapboxDrop(0.1F, GT_MetaItem_SmallDust.instance.getStack(20, 1));
		GT_ModHandler.addScrapboxDrop(0.1F, GT_MetaItem_SmallDust.instance.getStack(19, 1));
		GT_ModHandler.addScrapboxDrop(0.1F, GT_MetaItem_SmallDust.instance.getStack(13, 1));
		GT_ModHandler.addScrapboxDrop(0.1F, GT_MetaItem_SmallDust.instance.getStack(10, 1));
		
		GT_ModHandler.addScrapboxDrop(0.5F, GT_OreDictUnificator.get("dustSilver", null, 1));
		GT_ModHandler.addScrapboxDrop(0.5F, GT_MetaItem_Dust.instance.getStack(17, 1));
		GT_ModHandler.addScrapboxDrop(0.5F, GT_MetaItem_Dust.instance.getStack(18, 1));
		GT_ModHandler.addScrapboxDrop(0.5F, GT_MetaItem_Dust.instance.getStack(21, 1));
		GT_ModHandler.addScrapboxDrop(0.5F, GT_MetaItem_Dust.instance.getStack(23, 1));
		GT_ModHandler.addScrapboxDrop(0.5F, GT_MetaItem_Dust.instance.getStack(24, 1));
		GT_ModHandler.addScrapboxDrop(0.5F, GT_MetaItem_Dust.instance.getStack(25, 1));
		GT_ModHandler.addScrapboxDrop(0.5F, GT_MetaItem_Dust.instance.getStack(26, 1));
		GT_ModHandler.addScrapboxDrop(0.5F, GT_MetaItem_Material.instance.getStack(54, 1));
		GT_ModHandler.addScrapboxDrop(0.5F, GT_MetaItem_Material.instance.getStack(55, 1));
		GT_ModHandler.addScrapboxDrop(0.5F, GT_MetaItem_Component.instance.getStack(22,1));
		
		GT_ModHandler.addScrapboxDrop(2.0F, GT_MetaItem_Dust.instance.getStack( 2, 1));
		GT_ModHandler.addScrapboxDrop(2.0F, GT_MetaItem_Dust.instance.getStack( 3, 1));
		GT_ModHandler.addScrapboxDrop(2.0F, GT_MetaItem_Dust.instance.getStack( 4, 1));
		GT_ModHandler.addScrapboxDrop(2.0F, GT_MetaItem_Dust.instance.getStack( 5, 1));
		GT_ModHandler.addScrapboxDrop(2.0F, GT_MetaItem_Dust.instance.getStack( 8, 1));
		GT_ModHandler.addScrapboxDrop(2.0F, GT_MetaItem_Dust.instance.getStack(46, 1));
		GT_ModHandler.addScrapboxDrop(2.0F, GT_MetaItem_Dust.instance.getStack(47, 1));
		
		GT_ModHandler.addScrapboxDrop(4.0F, GT_MetaItem_Dust.instance.getStack( 6, 1));
		GT_ModHandler.addScrapboxDrop(4.0F, GT_MetaItem_Dust.instance.getStack( 7, 1));
		GT_ModHandler.addScrapboxDrop(4.0F, GT_MetaItem_Dust.instance.getStack( 9, 1));
		GT_ModHandler.addScrapboxDrop(4.0F, GT_ModHandler.getIC2Item("cell", 1));
		
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Block.stoneDoubleSlab	, 1, 6), new Object[] {"BB ", "   ", "   ", 'B', new ItemStack(Block.stoneSingleSlab, 1, 0)}));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Block.stoneDoubleSlab	, 1, 0), new Object[] {"B  ", "B  ", "   ", 'B', new ItemStack(Block.stoneSingleSlab, 1, 0)}));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Block.cobblestone		, 1, 0), new Object[] {"B  ", "B  ", "   ", 'B', new ItemStack(Block.stoneSingleSlab, 1, 3)}));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Block.brick			, 1, 0), new Object[] {"B  ", "B  ", "   ", 'B', new ItemStack(Block.stoneSingleSlab, 1, 4)}));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Block.stoneBrick		, 1, 0), new Object[] {"B  ", "B  ", "   ", 'B', new ItemStack(Block.stoneSingleSlab, 1, 5)}));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Block.stoneDoubleSlab	, 1, 0), new Object[] {"BB ", "   ", "   ", 'B', new ItemStack(Block.stoneSingleSlab, 1, 6)}));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Block.stoneDoubleSlab	, 1, 6), new Object[] {"B  ", "B  ", "   ", 'B', new ItemStack(Block.stoneSingleSlab, 1, 6)}));
		
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Block.planks			, 1, 0), new Object[] {"B  ", "B  ", "   ", 'B', new ItemStack(Block.woodSingleSlab, 1, 0)}));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Block.planks			, 1, 1), new Object[] {"B  ", "B  ", "   ", 'B', new ItemStack(Block.woodSingleSlab, 1, 1)}));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Block.planks			, 1, 2), new Object[] {"B  ", "B  ", "   ", 'B', new ItemStack(Block.woodSingleSlab, 1, 2)}));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Block.planks			, 1, 3), new Object[] {"B  ", "B  ", "   ", 'B', new ItemStack(Block.woodSingleSlab, 1, 3)}));
		
		for (int i = 0; i < 16; i++) GT_ModHandler.addExtractionRecipe(new ItemStack(Block.cloth, 1, i), new ItemStack(Block.cloth, 1, 0));
		
		GT_ModHandler.addShapelessCraftingRecipe(new ItemStack(Block.cloth, 1,  0), new Object[] {new ItemStack(Block.cloth, 1, -1), "dyeWhite"});
		GT_ModHandler.addShapelessCraftingRecipe(new ItemStack(Block.cloth, 1,  1), new Object[] {new ItemStack(Block.cloth, 1,  0), "dyeOrange"});
		GT_ModHandler.addShapelessCraftingRecipe(new ItemStack(Block.cloth, 1,  2), new Object[] {new ItemStack(Block.cloth, 1,  0), "dyeMagenta"});
		GT_ModHandler.addShapelessCraftingRecipe(new ItemStack(Block.cloth, 1,  3), new Object[] {new ItemStack(Block.cloth, 1,  0), "dyeLightBlue"});
		GT_ModHandler.addShapelessCraftingRecipe(new ItemStack(Block.cloth, 1,  4), new Object[] {new ItemStack(Block.cloth, 1,  0), "dyeYellow"});
		GT_ModHandler.addShapelessCraftingRecipe(new ItemStack(Block.cloth, 1,  5), new Object[] {new ItemStack(Block.cloth, 1,  0), "dyeLime"});
		GT_ModHandler.addShapelessCraftingRecipe(new ItemStack(Block.cloth, 1,  6), new Object[] {new ItemStack(Block.cloth, 1,  0), "dyePink"});
		GT_ModHandler.addShapelessCraftingRecipe(new ItemStack(Block.cloth, 1,  7), new Object[] {new ItemStack(Block.cloth, 1,  0), "dyeGrey"});
		GT_ModHandler.addShapelessCraftingRecipe(new ItemStack(Block.cloth, 1,  8), new Object[] {new ItemStack(Block.cloth, 1,  0), "dyeLightGrey"});
		GT_ModHandler.addShapelessCraftingRecipe(new ItemStack(Block.cloth, 1,  9), new Object[] {new ItemStack(Block.cloth, 1,  0), "dyeCyan"});
		GT_ModHandler.addShapelessCraftingRecipe(new ItemStack(Block.cloth, 1, 10), new Object[] {new ItemStack(Block.cloth, 1,  0), "dyePurple"});
		GT_ModHandler.addShapelessCraftingRecipe(new ItemStack(Block.cloth, 1, 11), new Object[] {new ItemStack(Block.cloth, 1,  0), "dyeBlue"});
		GT_ModHandler.addShapelessCraftingRecipe(new ItemStack(Block.cloth, 1, 12), new Object[] {new ItemStack(Block.cloth, 1,  0), "dyeBrown"});
		GT_ModHandler.addShapelessCraftingRecipe(new ItemStack(Block.cloth, 1, 13), new Object[] {new ItemStack(Block.cloth, 1,  0), "dyeGreen"});
		GT_ModHandler.addShapelessCraftingRecipe(new ItemStack(Block.cloth, 1, 14), new Object[] {new ItemStack(Block.cloth, 1,  0), "dyeRed"});
		GT_ModHandler.addShapelessCraftingRecipe(new ItemStack(Block.cloth, 1, 15), new Object[] {new ItemStack(Block.cloth, 1,  0), "dyeBlack"});
		
		GT_ModHandler.addShapelessCraftingRecipe(GT_ModHandler.getIC2Item("painter", 1), new Object[] {GT_ModHandler.getIC2Item("blackPainter", 1)});
		GT_ModHandler.addShapelessCraftingRecipe(GT_ModHandler.getIC2Item("painter", 1), new Object[] {GT_ModHandler.getIC2Item("redPainter", 1)});
		GT_ModHandler.addShapelessCraftingRecipe(GT_ModHandler.getIC2Item("painter", 1), new Object[] {GT_ModHandler.getIC2Item("greenPainter", 1)});
		GT_ModHandler.addShapelessCraftingRecipe(GT_ModHandler.getIC2Item("painter", 1), new Object[] {GT_ModHandler.getIC2Item("brownPainter", 1)});
		GT_ModHandler.addShapelessCraftingRecipe(GT_ModHandler.getIC2Item("painter", 1), new Object[] {GT_ModHandler.getIC2Item("bluePainter", 1)});
		GT_ModHandler.addShapelessCraftingRecipe(GT_ModHandler.getIC2Item("painter", 1), new Object[] {GT_ModHandler.getIC2Item("purplePainter", 1)});
		GT_ModHandler.addShapelessCraftingRecipe(GT_ModHandler.getIC2Item("painter", 1), new Object[] {GT_ModHandler.getIC2Item("cyanPainter", 1)});
		GT_ModHandler.addShapelessCraftingRecipe(GT_ModHandler.getIC2Item("painter", 1), new Object[] {GT_ModHandler.getIC2Item("lightGreyPainter", 1)});
		GT_ModHandler.addShapelessCraftingRecipe(GT_ModHandler.getIC2Item("painter", 1), new Object[] {GT_ModHandler.getIC2Item("darkGreyPainter", 1)});
		GT_ModHandler.addShapelessCraftingRecipe(GT_ModHandler.getIC2Item("painter", 1), new Object[] {GT_ModHandler.getIC2Item("pinkPainter", 1)});
		GT_ModHandler.addShapelessCraftingRecipe(GT_ModHandler.getIC2Item("painter", 1), new Object[] {GT_ModHandler.getIC2Item("limePainter", 1)});
		GT_ModHandler.addShapelessCraftingRecipe(GT_ModHandler.getIC2Item("painter", 1), new Object[] {GT_ModHandler.getIC2Item("yellowPainter", 1)});
		GT_ModHandler.addShapelessCraftingRecipe(GT_ModHandler.getIC2Item("painter", 1), new Object[] {GT_ModHandler.getIC2Item("cloudPainter", 1)});
		GT_ModHandler.addShapelessCraftingRecipe(GT_ModHandler.getIC2Item("painter", 1), new Object[] {GT_ModHandler.getIC2Item("magentaPainter", 1)});
		GT_ModHandler.addShapelessCraftingRecipe(GT_ModHandler.getIC2Item("painter", 1), new Object[] {GT_ModHandler.getIC2Item("orangePainter", 1)});
		GT_ModHandler.addShapelessCraftingRecipe(GT_ModHandler.getIC2Item("painter", 1), new Object[] {GT_ModHandler.getIC2Item("whitePainter", 1)});
		
		GT_ModHandler.addCraftingRecipe(GT_ModHandler.getIC2Item("doubleInsulatedGoldCableItem", 4), new Object[] {"RRR", "RGR", "RRR", 'G', new ItemStack(Item.ingotGold, 1), 'R', "itemRubber"});
		GT_ModHandler.addCraftingRecipe(GT_ModHandler.getIC2Item("industrialTnt", 5), new Object[] {"FFF", "TTT", "FFF", 'T', new ItemStack(Block.tnt, 1), 'F', "dustFlint"});
		
		if (mForestryBronzeNerf && null != (tStack = GT_ModHandler.removeRecipe(new ItemStack[] {GT_OreDictUnificator.get("ingotCopper", null, 1), GT_OreDictUnificator.get("ingotCopper", null, 1), null, GT_OreDictUnificator.get("ingotCopper", null, 1), GT_OreDictUnificator.get("ingotTin", null, 1), null, null, null, null}))) {
			tStack.stackSize = 2;
			GT_ModHandler.addShapelessCraftingRecipe(tStack, new Object[] {"ingotCopper", "ingotCopper", "ingotCopper", "ingotTin"});
		}
		
		if (GT_ModHandler.removeRecipe(GT_ModHandler.getIC2Item("scaffold", 16)))
			GT_ModHandler.addCraftingRecipe(GT_ModHandler.getIC2Item("scaffold", 4), new Object[] {"WWW", " S ", "S S", 'W', "plankWood", 'S', "stickWood"});
		
    	GT_ModHandler.addCraftingRecipe(new ItemStack(Block.pistonBase, 1), new Object[] {"WWW", "CBC", "CRC", 'W', "plankWood", 'C', new ItemStack(Block.cobblestone, 1), 'B', "ingotBronze", 'R', "dustRedstone"});
		if (mRefIronPiston) GT_ModHandler.addCraftingRecipe(new ItemStack(Block.pistonBase, 1), new Object[] {"WWW", "CBC", "CRC", 'W', "plankWood", 'C', new ItemStack(Block.cobblestone, 1), 'B', "ingotAluminium", 'R', "dustRedstone"});
    	if (mRefIronPiston) GT_ModHandler.addCraftingRecipe(new ItemStack(Block.pistonBase, 1), new Object[] {"WWW", "CBC", "CRC", 'W', "plankWood", 'C', new ItemStack(Block.cobblestone, 1), 'B', "ingotRefinedIron", 'R', "dustRedstone"});
    	if (mSteelPiston)	GT_ModHandler.addCraftingRecipe(new ItemStack(Block.pistonBase, 1), new Object[] {"WWW", "CBC", "CRC", 'W', "plankWood", 'C', new ItemStack(Block.cobblestone, 1), 'B', "ingotSteel", 'R', "dustRedstone"});
    	if (mSteelPiston)	GT_ModHandler.addCraftingRecipe(new ItemStack(Block.pistonBase, 1), new Object[] {"WWW", "CBC", "CRC", 'W', "plankWood", 'C', new ItemStack(Block.cobblestone, 1), 'B', "ingotTitanium", 'R', "dustRedstone"});
    	
    	GT_ModHandler.addCraftingRecipe(new ItemStack(mItems[37], 1), new Object[] {"LLL", "LIL", "LLL", 'L', "1kkEUStore", 'I', "plateIridium"});
    	GT_ModHandler.addCraftingRecipe(GT_MetaItem_Component.instance.getStack(0, 4), new Object[] {"AWA", "LIL", "AWA", 'L', "1kkEUStore", 'I', "plateIridium", 'A', "circuitTier04", 'W', "ingotTungsten"});
        GT_ModHandler.addCraftingRecipe(GT_MetaItem_Component.instance.getStack(1, 4), new Object[] {"AEA", "EIE", "AEA", 'E', "circuitTier05", 'I', "plateIridium", 'A', "circuitTier04"});
        GT_ModHandler.addCraftingRecipe(GT_MetaItem_Component.instance.getStack(0, 1), new Object[] {"   ", "APL", "   ", 'L', "1kkEUStore", 'P', "ingotPlatinum", 'A', "circuitTier04"});
        GT_ModHandler.addCraftingRecipe(GT_MetaItem_Component.instance.getStack(1, 1), new Object[] {"   ", "APE", "   ", 'E', "circuitTier05", 'P', "ingotPlatinum", 'A', "circuitTier04"});
        GT_ModHandler.addCraftingRecipe(GT_MetaItem_Component.instance.getStack(3, 4), new Object[] {"EEE", "EAE", "EEE", 'E', new ItemStack(Item.emerald, 1), 'A', "circuitTier04"});
        GT_ModHandler.addCraftingRecipe(GT_MetaItem_Component.instance.getStack(3, 4), new Object[] {"EEE", "EAE", "EEE", 'E', "dustEmerald", 'A', "circuitTier04"});
        GT_ModHandler.addCraftingRecipe(GT_MetaItem_Component.instance.getStack(2, 4), new Object[] {"CCC", "WIW", "LLL", 'L', "circuitTier07", 'W', "ingotTungsten", 'I', "plateIridium", 'C', new ItemStack(mItems[34], 1)});
        GT_ModHandler.addCraftingRecipe(GT_MetaItem_Component.instance.getStack(2, 4), new Object[] {"CCC", "WIW", "LLL", 'L', "circuitTier07", 'W', "ingotTungsten", 'I', "plateIridium", 'C', new ItemStack(mItems[60], 1)});
        GT_ModHandler.addCraftingRecipe(GT_MetaItem_Component.instance.getStack(2, 4), new Object[] {"CCC", "WIW", "LLL", 'L', "circuitTier07", 'W', "ingotTungsten", 'I', "plateIridium", 'C', GT_ModHandler.getIC2Item("reactorCoolantSix", 1)});
        GT_ModHandler.addCraftingRecipe(new ItemStack(mItems[43], 4), new Object[] {"EEE", "ECE", "EEE", 'C', "circuitTier06", 'E', "circuitTier05"});
        GT_ModHandler.addShapelessCraftingRecipe(new ItemStack(mItems[43], 1), new Object[] {new ItemStack(mItems[43], 1)});
        GT_ModHandler.addCraftingRecipe(GT_MetaItem_Component.instance.getStack(4, 1), new Object[] {"AGA", "RPB", "ALA", 'A', "ingotAluminium", 'L', "dustGlowstone", 'R', "dyeRed", 'G', "dyeLime", 'B', "dyeBlue", 'P', new ItemStack(Block.thinGlass, 1)});
        GT_ModHandler.addCraftingRecipe(GT_MetaItem_Component.instance.getStack(17, 4), new Object[] {"DSD", "S S", "DSD", 'D', "dustDiamond", 'S', mSteelETools?"ingotSteel":"ingotRefinedIron"});
        GT_ModHandler.addCraftingRecipe(GT_MetaItem_Component.instance.getStack(18, 2), new Object[] {"DSD", "SGS", "DSD", 'G', "gemDiamond", 'D', "dustDiamond", 'S', mSteelETools?"ingotSteel":"ingotRefinedIron"});
        GT_ModHandler.addCraftingRecipe(GT_MetaItem_Component.instance.getStack(23, 2), new Object[] {"TST", "SBS", "TST", 'B', "blockSteel", 'T', "ingotTungsten", 'S', mSteelETools?"ingotSteel":"ingotRefinedIron"});

        GT_ModHandler.addCraftingRecipe(GT_MetaItem_Component.instance.getStack(22,16), new Object[] {"CIC", "ICI", "CIC", 'C', "circuitTier02", 'I', "ingotRefinedIron"});
        GT_ModHandler.addCraftingRecipe(GT_MetaItem_Component.instance.getStack(22,12), new Object[] {"CAC", "ACA", "CAC", 'C', "circuitTier02", 'A', "ingotAluminium"});
        
        GT_ModHandler.addCraftingRecipe(GT_MetaItem_Component.instance.getStack(32, 1), new Object[] {"XXX", "XMX", "XXX", 'M', "itemMachineParts", 'X', "ingotAluminium"});
        GT_ModHandler.addCraftingRecipe(GT_MetaItem_Component.instance.getStack(33, 1), new Object[] {"XXX", "XMX", "XXX", 'M', "itemMachineParts", 'X', "ingotBronze"});
        GT_ModHandler.addCraftingRecipe(GT_MetaItem_Component.instance.getStack(34, 1), new Object[] {"XXX", "XMX", "XXX", 'M', "itemMachineParts", 'X', "ingotBrass"});
        GT_ModHandler.addCraftingRecipe(GT_MetaItem_Component.instance.getStack(35, 1), new Object[] {"XXX", "XMX", "XXX", 'M', "itemMachineParts", 'X', "ingotSteel"});
        
        GT_ModHandler.addRollingMachineRecipe(GT_MetaItem_Component.instance.getStack(19, 3), new Object[] {"AAA", "BCC", "BBC", 'A', "ingotRefinedIron", 'B', "ingotChrome", 'C', "ingotAluminium"});
        GT_ModHandler.addRollingMachineRecipe(GT_MetaItem_Component.instance.getStack(20, 2), new Object[] {" B ", "BAB", " B ", 'A', "ingotChrome", 'B', "ingotNickel"});
        GT_ModHandler.addRollingMachineRecipe(GT_MetaItem_Component.instance.getStack(21, 3), new Object[] {"BAB", "A A", "BAB", 'A', "ingotCopper", 'B', "ingotNickel"});
        
	    GT_ModHandler.addRollingMachineRecipe(GT_ModHandler.getRCItem("part.rail.standard"		,  4), new Object[] {"X X", "X X", "X X", 'X', "ingotAluminium"});
	    GT_ModHandler.addRollingMachineRecipe(GT_ModHandler.getRCItem("part.rail.standard"		, 32), new Object[] {"X X", "X X", "X X", 'X', "ingotTitanium"});
	        
	    GT_ModHandler.addRollingMachineRecipe(GT_ModHandler.getRCItem("part.rebar"				,  2), new Object[] {"  X", " X ", "X  ", 'X', "ingotAluminium"});
	    GT_ModHandler.addRollingMachineRecipe(GT_ModHandler.getRCItem("part.rebar"				, 16), new Object[] {"  X", " X ", "X  ", 'X', "ingotTitanium"});
	        
	    GT_ModHandler.addRollingMachineRecipe(GT_ModHandler.getRCItem("post.metal.light.blue"	,  8), new Object[] {"XXX", " X ", "XXX", 'X', "ingotAluminium"});
	    GT_ModHandler.addRollingMachineRecipe(GT_ModHandler.getRCItem("post.metal.purple"		, 64), new Object[] {"XXX", " X ", "XXX", 'X', "ingotTitanium"});
	        
	    GT_ModHandler.addRollingMachineRecipe(GT_ModHandler.getRCItem("post.metal.light.blue"	,  8), new Object[] {"X X", "XXX", "X X", 'X', "ingotAluminium"});
	    GT_ModHandler.addRollingMachineRecipe(GT_ModHandler.getRCItem("post.metal.purple"		, 64), new Object[] {"X X", "XXX", "X X", 'X', "ingotTitanium"});
        
        GT_ModHandler.addCraftingRecipe(new ItemStack(mItems[49], 1), new Object[] {"CPC", "   ", "   ", 'C', new ItemStack(mItems[48], 1), 'P', "plateCopper"});
        GT_ModHandler.addCraftingRecipe(new ItemStack(mItems[50], 1), new Object[] {" C ", "PPP", " C ", 'C', new ItemStack(mItems[49], 1), 'P', "plateCopper"});
        GT_ModHandler.addCraftingRecipe(new ItemStack(mItems[52], 1), new Object[] {"CPC", "   ", "   ", 'C', new ItemStack(mItems[51], 1), 'P', "plateCopper"});
        GT_ModHandler.addCraftingRecipe(new ItemStack(mItems[53], 1), new Object[] {" C ", "PPP", " C ", 'C', new ItemStack(mItems[52], 1), 'P', "plateCopper"});
        
        GT_ModHandler.addCraftingRecipe(GT_ModHandler.getIC2Item("reactorVent", 1), new Object[] {"AIA", "I I", "AIA", 'I', new ItemStack(Block.fenceIron, 1), 'A', "ingotAluminium"});
        GT_ModHandler.addShapelessCraftingRecipe(GT_ModHandler.getIC2Item("reactorPlatingExplosive", 1), new Object[] {GT_ModHandler.getIC2Item("reactorPlating", 1), "ingotLead"});
        
    	GT_ModHandler.addCraftingRecipe(new ItemStack(mBlocks[0], 1,10), new Object[] {"CTC", "TMT", "CTC", 'C', "ingotChrome", 'T', "ingotTitanium", 'M', "rawMachineTier02"});
    	GT_ModHandler.addCraftingRecipe(new ItemStack(mBlocks[0], 4,13), new Object[] {"AAA", "CMC", "AAA", 'C', "circuitTier02", 'A', "ingotRefinedIron", 'M', "rawMachineTier01"});
    	GT_ModHandler.addCraftingRecipe(new ItemStack(mBlocks[0], 4,13), new Object[] {"AAA", "CMC", "AAA", 'C', "circuitTier02", 'A', "ingotAluminium", 'M', "rawMachineTier01"});
    	GT_ModHandler.addCraftingRecipe(new ItemStack(mBlocks[0], 4,14), new Object[] {"SSS", "CMC", "SSS", 'C', "circuitTier04", 'S', "ingotSteel", 'M', "rawMachineTier02"});
    	GT_ModHandler.addCraftingRecipe(new ItemStack(mBlocks[0], 4,15), new Object[] {"TTT", "CMC", "TTT", 'C', "circuitTier06", 'T', "ingotChrome", 'M', "rawMachineTier04"});
    	
    	if (mFusionreactor) {
    		GT_ModHandler.addCraftingRecipe(new ItemStack(mBlocks[1], 1, 1), new Object[] {"FHF", "MCM", "FHF", 'F', "circuitTier07", 'C', "circuitTier10", 'H', new ItemStack(mBlocks[1], 1,15), 'M', "10kkEUStore"});
    		GT_ModHandler.addCraftingRecipe(new ItemStack(mBlocks[0], 1, 1), new Object[] {"TBC", "BIB", "SBT", 'I', mNeutronReflector?new ItemStack(mItems[40], 1):"plateIridium", 'S', "itemSuperconductor", 'C', "circuitTier07", 'T', GT_ModHandler.getIC2Item("teslaCoil", 1), 'B', "rawMachineTier04"/*"heatingCoilTier02"*/});
    	}
    	if (mLightningrod) {
    		GT_ModHandler.addCraftingRecipe(new ItemStack(mBlocks[1], 1, 2), new Object[] {"IAI", "AMA", "IAI", 'I', "circuitTier07", 'A', "rawMachineTier04", 'M', new ItemStack(mBlocks[1], 1,15)});
    	}
    	if (mQuantumchest) {
    		GT_ModHandler.addCraftingRecipe(new ItemStack(mBlocks[1], 1, 3), new Object[] {"ASA", "BTB", "ALA", 'A', "circuitTier08", 'S', "monitorTier02", 'L', "circuitTier07", 'T', GT_ModHandler.getIC2Item("teleporter", 1), 'B', "rawMachineTier04"});
    		if (mQuantumTank) GT_ModHandler.addCraftingRecipe(new ItemStack(mBlocks[1], 1,30), new Object[] {"APA", "PQP", "APA", 'A', "circuitTier07", 'P', "ingotPlatinum", 'L', "circuitTier07", 'Q', new ItemStack(mBlocks[1], 1, 3)});
    	} else {
    		if (mQuantumTank) GT_ModHandler.addCraftingRecipe(new ItemStack(mBlocks[1], 1,30), new Object[] {"ASA", "BTB", "ALA", 'A', "circuitTier08", 'S', "monitorTier02", 'L', "circuitTier07", 'T', GT_ModHandler.getIC2Item("teleporter", 1), 'B', "rawMachineTier04"});
    	}
    	
    	GT_ModHandler.addCraftingRecipe(new ItemStack(mBlocks[1], 1, 4), new Object[] {"CGD", "GAG", "DGC", 'D', "circuitTier07", 'G', "monitorTier02", 'C', "circuitTier08", 'A', "rawMachineTier02"});
    	
    	if (mUUMAssembler) {
    		GT_ModHandler.addCraftingRecipe(new ItemStack(mBlocks[1], 1, 5), new Object[] {"ACA", "TQW", "SES", 'W', mEAutoCrafter?new ItemStack(mBlocks[1], 1, 16):"craftingtable", 'A', "circuitTier06", 'S', "circuitTier08", 'E', "100kEUStore", 'T', GT_ModHandler.getIC2Item("teleporter", 1), 'C', "circuitTier10", 'Q', mQuantumchest?new ItemStack(mBlocks[1], 1, 3):"enderChest"});
    	}
    	if (mLESU) {
    		GT_ModHandler.addCraftingRecipe(new ItemStack(mBlocks[0], 1, 6), new Object[] {"LLL", "LML", "LLL", 'M', "circuitTier02", 'L', "blockLazurite"});
    		GT_ModHandler.addCraftingRecipe(new ItemStack(mBlocks[1], 1, 7), new Object[] {" L ", "ACA", " M ", 'A', "circuitTier04", 'C', new ItemStack(mBlocks[0], 1, 6), 'L', GT_ModHandler.getIC2Item("lvTransformer", 1), 'M', GT_ModHandler.getIC2Item("mvTransformer", 1)});
    	}
    	if (mIDSU) {
    		GT_ModHandler.addCraftingRecipe(new ItemStack(mBlocks[1], 1, 8), new Object[] {"IMI", "MCM", "IMI", 'I', "plateIridium", 'C', "enderChest", 'M', mAESU?new ItemStack(mBlocks[1], 1, 9):"10kkEUStore"});
    	}
    	if (mAESU) {
    		GT_ModHandler.addCraftingRecipe(new ItemStack(mBlocks[1], 1, 9), new Object[] {"MMM", "MCM", "MMM", 'C', "circuitTier10", 'M', "10kkEUStore"});
    	}
    	if (mChargeOMat) {
    		GT_ModHandler.addCraftingRecipe(new ItemStack(mBlocks[1], 1,10), new Object[] {"BCB", "TMT", "BAB", 'C', "circuitTier10", 'M', "10kkEUStore", 'A', "rawMachineTier02", 'T', new ItemStack(Block.chest, 1), 'B', "circuitTier07"});
    	}
    	
    	GT_ModHandler.addCraftingRecipe(new ItemStack(mBlocks[1], 1,11), new Object[] {"RCR", "AEA", "RCR", 'C', "circuitTier04", 'A', "rawMachineTier02",  'E', GT_ModHandler.getIC2Item("extractor", 1), 'R', "ingotRefinedIron"});
    	GT_ModHandler.addCraftingRecipe(new ItemStack(mBlocks[1], 1,11), new Object[] {"RCR", "AEA", "RCR", 'C', "circuitTier04", 'A', "rawMachineTier02",  'E', GT_ModHandler.getIC2Item("extractor", 1), 'R', "ingotAluminium"});
    	GT_ModHandler.addCraftingRecipe(new ItemStack(mBlocks[1], 1,25), new Object[] {"RDR", "CEC", "RMR", 'E', GT_ModHandler.getIC2Item("electrolyzer", 1), 'M', GT_ModHandler.getIC2Item("magnetizer", 1), 'D', GT_ModHandler.getIC2Item("extractor", 1), 'R', "ingotRefinedIron", 'C', "circuitTier04"});
    	GT_ModHandler.addCraftingRecipe(new ItemStack(mBlocks[1], 1,25), new Object[] {"RDR", "CEC", "RMR", 'E', GT_ModHandler.getIC2Item("electrolyzer", 1), 'M', GT_ModHandler.getIC2Item("magnetizer", 1), 'D', GT_ModHandler.getIC2Item("extractor", 1), 'R', "ingotAluminium", 'C', "circuitTier04"});
    	
    	if (mSuperconductor) {
    		GT_ModHandler.addCraftingRecipe(new ItemStack(mBlocks[1], 4,12), new Object[] {"ALA", "CCC", "ALA", 'C', "itemSuperconductor", 'A', "rawMachineTier02", 'L', "circuitTier07"});
    	}
    	
    	GT_ModHandler.addCraftingRecipe(new ItemStack(mBlocks[1], 1,15), new Object[] {"LEL", "CAC", "LEL", 'C', "itemSuperconductor", 'A', "rawMachineTier04", 'L', "circuitTier07", 'E', "10kkEUStore"});
    	
    	if (mPlayerdetector) {
    		GT_ModHandler.addCraftingRecipe(new ItemStack(mBlocks[1], 1,13), new Object[] {" E ", "ACA", " E ", 'C', "circuitTier10", 'A', "circuitTier04", 'E', "circuitTier05"});
    	}
    	if (mMatterfabricator) {
    		GT_ModHandler.addCraftingRecipe(new ItemStack(mBlocks[1], 1,14), new Object[] {"ETE", "ALA", "ETE", 'L', "10kkEUStore", 'A', "rawMachineTier04", 'E', "circuitTier07", 'T', GT_ModHandler.getIC2Item("teleporter", 1)});
    	}
    	if (mSonictron)		{
    		GT_ModHandler.addCraftingRecipe(new ItemStack(mBlocks[1], 1, 6), new Object[] {"CRC", "NAN", "CJC", 'C', "circuitTier02", 'N', new ItemStack(Block.music, 1), 'A', "rawMachineTier02", 'J', new ItemStack(Block.jukebox, 1), 'R', "itemRecord"});
    		GT_ModHandler.addCraftingRecipe(new ItemStack(mItems[32], 1, 0), new Object[] {" A ", "ASA", " A ", 'A', "circuitTier05", 'S', new ItemStack(mBlocks[1], 1, 6)});
    	}
    	if (mEAutoCrafter) {
    		GT_ModHandler.addCraftingRecipe(new ItemStack(mBlocks[1], 1,16), new Object[] {"GBG", "CTC", "GAG", 'B', "10kEUStore", 'A', "rawMachineTier02"	, 'C', "circuitTier04", 'T', "craftingtable", 'G', "ingotElectrum"});
    	}
    	if (mAutomationStuff) {
    		GT_ModHandler.addCraftingRecipe(new ItemStack(mBlocks[1], 1,17), new Object[] {"GBG", "CPC", "GAG", 'B', "10kEUStore", 'A', "rawMachineTier00"	, 'C', "circuitTier02", 'G', "ingotElectrum", 'P', new ItemStack(Block.pistonBase, 1)});
    		GT_ModHandler.addCraftingRecipe(new ItemStack(mBlocks[1], 1,20), new Object[] {"GBG", "CPC", "GAG", 'B', "10kEUStore", 'A', "rawMachineTier02"	, 'C', "circuitTier04", 'G', "ingotElectrum", 'P', new ItemStack(Block.pistonBase, 1)});
    		
    		GT_ModHandler.addCraftingRecipe(new ItemStack(mBlocks[1], 1,23), new Object[] {"GBG", "CHC", "GAG", 'B', "10kEUStore", 'A', "monitorTier02"		, 'C', "circuitTier02", 'G', "ingotElectrum", 'H', new ItemStack(mBlocks[1], 1,17)});
    		GT_ModHandler.addCraftingRecipe(new ItemStack(mBlocks[1], 1,21), new Object[] {"GBG", "CHC", "GAG", 'B', "10kEUStore", 'A', "monitorTier02"		, 'C', "circuitTier04", 'G', "ingotElectrum", 'H', new ItemStack(mBlocks[1], 1,18)});
    		
    		GT_ModHandler.addCraftingRecipe(new ItemStack(mBlocks[1], 1,19), new Object[] {"GBG", "CHC", "GAG", 'B', "10kEUStore", 'A', "rawMachineTier00"	, 'C', "circuitTier04", 'G', "ingotElectrum", 'H', new ItemStack(Block.chest, 1)});
    		
    		GT_ModHandler.addCraftingRecipe(new ItemStack(mBlocks[1], 1,19), new Object[] {"TTT", "TCT", "TTT", 'T', new ItemStack(mBlocks[1], 1, 18)		, 'C', "circuitTier04"});
    		GT_ModHandler.addShapelessCraftingRecipe(new ItemStack(mBlocks[1], 8,18), new Object[] {new ItemStack(mBlocks[1], 1, 19)});
    	}
    	if (mItemClearer) {
    		GT_ModHandler.addCraftingRecipe(new ItemStack(mBlocks[1], 1,24), new Object[] {"GBG", "PMH", "GCG", 'B', "10kEUStore", 'M', "rawMachineTier02"	, 'C', "circuitTier02", 'G', "ingotElectrum", 'P', new ItemStack(Block.pistonStickyBase, 1), 'H', new ItemStack(mBlocks[1], 1,18)});
    	}
    	if (mRockBreaker) {
    		GT_ModHandler.addCraftingRecipe(new ItemStack(mBlocks[1], 1,22), new Object[] {"ODO", "CMC", "OAO", 'D', "itemDiamond", 'A', "rawMachineTier02"	, 'C', "circuitTier02", 'M', new ItemStack(GT_ModHandler.getIC2Item("miningDrill", 1).getItem(), 1, -1), 'O', new ItemStack(Block.obsidian, 1)});
    	}
    	if (mCropHarvestor) {
    		GT_ModHandler.addCraftingRecipe(new ItemStack(mBlocks[1], 1,26), new Object[] {"GHG", "DPM", "GCG", 'D', "itemDiamondBlade", 'M', "rawMachineTier02", 'C', "circuitTier02", 'G', "ingotElectrum", 'P', new ItemStack(Block.pistonStickyBase, 1), 'H', mItemClearer?new ItemStack(mBlocks[1], 1,24):new ItemStack(mBlocks[1], 1,18)});
    	}
    	if (mScrapboxinator) {
    		GT_ModHandler.addCraftingRecipe(new ItemStack(mBlocks[1], 1,27), new Object[] {"GHG", "DPM", "GCG", 'P', new ItemStack(Block.pressurePlatePlanks, 1), 'M', "rawMachineTier02"	, 'C', "circuitTier02", 'G', "ingotElectrum", 'D', new ItemStack(Block.dispenser, 1), 'H', mItemClearer?new ItemStack(mBlocks[1], 1,24):new ItemStack(mBlocks[1], 1,18)});
    	}
    	if (mGrinder) {
    		GT_ModHandler.addCraftingRecipe(new ItemStack(mBlocks[1], 1,28), new Object[] {"EAP", "GGG", "ABA", 'B', "rawMachineTier02", 'A', "circuitTier04", 'G', "itemGrinder", 'E', new ItemStack(mBlocks[1], 1,25), 'P', GT_ModHandler.getIC2Item("pump", 1)});
    	}
    	if (mSawmill) {
    		GT_ModHandler.addCraftingRecipe(new ItemStack(mBlocks[1], 1,32), new Object[] {"PAP", "DDD", "ABA", 'B', "rawMachineTier02", 'A', "circuitTier04", 'D', "itemDiamondBlade", 'P', GT_ModHandler.getIC2Item("pump", 1)});
        }
    	if (mDieselGenerator) {
    		GT_ModHandler.addCraftingRecipe(new ItemStack(mBlocks[1], 1,33), new Object[] {"XXX", "X X", "CGC", 'X', "ingotRefinedIron", 'C', "circuitTier02", 'G', GT_ModHandler.getIC2Item("generator", 1)});
    		GT_ModHandler.addCraftingRecipe(new ItemStack(mBlocks[1], 1,33), new Object[] {"XXX", "X X", "CGC", 'X', "ingotAluminium", 'C', "circuitTier02", 'G', GT_ModHandler.getIC2Item("generator", 1)});
    	}
    	if (mGasTurbine) {
    		GT_ModHandler.addCraftingRecipe(new ItemStack(mBlocks[1], 1,34), new Object[] {"XCX", "WPW", "XCX", 'X', "ingotInvar", 'C', "circuitTier04", 'W', GT_ModHandler.getIC2Item("windMill", 1), 'P', "glassReinforced"});
    		GT_ModHandler.addCraftingRecipe(new ItemStack(mBlocks[1], 1,34), new Object[] {"XCX", "WPW", "XCX", 'X', "ingotAluminium", 'C', "circuitTier04", 'W', GT_ModHandler.getIC2Item("windMill", 1), 'P', "glassReinforced"});
    	}
    	if (mThermalGenerator) {
    		GT_ModHandler.addCraftingRecipe(new ItemStack(mBlocks[1], 1,35), new Object[] {"XXX", "XPX", "CGC", 'X', "ingotInvar", 'C', "circuitTier02", 'G', GT_ModHandler.getIC2Item("geothermalGenerator", 1), 'P', "glassReinforced"});
    		GT_ModHandler.addCraftingRecipe(new ItemStack(mBlocks[1], 1,35), new Object[] {"XXX", "XPX", "CGC", 'X', "ingotAluminium", 'C', "circuitTier02", 'G', GT_ModHandler.getIC2Item("geothermalGenerator", 1), 'P', "glassReinforced"});
    	}
    	if (mDenseFluidGenerator) {
    		GT_ModHandler.addCraftingRecipe(new ItemStack(mBlocks[1], 1,36), new Object[] {"XXX", "XPX", "CGC", 'X', "ingotRefinedIron", 'C', "circuitTier02", 'G', GT_ModHandler.getIC2Item("generator", 1), 'P', "glassReinforced"});
    		GT_ModHandler.addCraftingRecipe(new ItemStack(mBlocks[1], 1,36), new Object[] {"XXX", "XPX", "CGC", 'X', "ingotAluminium", 'C', "circuitTier02", 'G', GT_ModHandler.getIC2Item("generator", 1), 'P', "glassReinforced"});
    	}
    	
    	GT_ModHandler.addCraftingRecipe(new ItemStack(mBlocks[1], 1,29), new Object[] {"ACA", "CMC", "FCF", 'M', "rawMachineTier02", 'A', "circuitTier02", 'C', "heatingCoilTier00", 'F', GT_ModHandler.getIC2Item("inductionFurnace", 1)});
    	GT_ModHandler.addCraftingRecipe(new ItemStack(mBlocks[1], 1,31), new Object[] {"ABA", "DCD", "ABA", 'B', "rawMachineTier02", 'D', "circuitTier02", 'C', GT_ModHandler.getIC2Item("compressor", 1), 'A', GT_ModHandler.getIC2Item("advancedAlloy", 1)});
    	
    	if (mIridiumblock)	{
    		GT_ModHandler.addShapelessCraftingRecipe(new ItemStack(mBlocks[0], 1, 2), new Object[] {GT_ModHandler.getIC2Item("reinforcedStone", 1), "ingotIridium"});
    	}
    	if (mItems[16] != null && mItems[17] != null) {
    		GT_ModHandler.addCraftingRecipe(new ItemStack(mItems[17], 1, 0), new Object[] {" F ", "ACA", " B ", 'B', "10kEUStore", 'C', "circuitTier02", 'A', "ingotAluminium", 'F', GT_ModHandler.getIC2Item("frequencyTransmitter", 1)});
    		GT_ModHandler.addShapelessCraftingRecipe(GT_ModHandler.getIC2Item("electronicCircuit", 2), new Object[] {new ItemStack(mItems[16], 1, 0)});
    	}
    	if (mDestructopack) {
    		GT_ModHandler.addCraftingRecipe(new ItemStack(mItems[33], 1, 0), new Object[] {"ARA", "RLR", "ARA", 'A', "circuitTier04", 'R', "ingotRefinedIron", 'L', Item.bucketLava});
    		GT_ModHandler.addCraftingRecipe(new ItemStack(mItems[33], 1, 0), new Object[] {"ARA", "RLR", "ARA", 'A', "circuitTier04", 'R', "ingotAluminium", 'L', Item.bucketLava});
    	}
    	if (mHeliumCoolant) {
    		GT_ModHandler.addCraftingRecipe(new ItemStack(mItems[34], 1), new Object[] {" T ", "THT", " T ", 'H', "element_1he", 'T', "ingotTin"});
    		GT_ModHandler.addCraftingRecipe(new ItemStack(mItems[35], 1), new Object[] {"TTT", "HHH", "TTT", 'H', new ItemStack(mItems[34], 1), 'T', "ingotTin"});
    		GT_ModHandler.addCraftingRecipe(new ItemStack(mItems[36], 1), new Object[] {"THT", "TCT", "THT", 'H', new ItemStack(mItems[35], 1), 'T', "ingotTin", 'C', "plateCopper"});
    		GT_ModHandler.addCraftingRecipe(GT_ModHandler.getIC2Item("overclockerUpgrade", 2), new Object[] {" H ", "WCW", "   ", 'H', new ItemStack(mItems[34], 1), 'W', GT_ModHandler.getIC2Item("insulatedCopperCableItem", 1), 'C', "circuitTier02"});
    	}
    	if (mNaKCoolant) {
    		GT_ModHandler.addCraftingRecipe(new ItemStack(mItems[60], 1), new Object[] {"TNT", "KCK", "TNT", 'N', "element_1na", 'K', "element_1k", 'T', "ingotTin", 'C', GT_ModHandler.getIC2Item("reactorCoolantSimple", 1)});
    		GT_ModHandler.addCraftingRecipe(new ItemStack(mItems[60], 1), new Object[] {"TKT", "NCN", "TKT", 'N', "element_1na", 'K', "element_1k", 'T', "ingotTin", 'C', GT_ModHandler.getIC2Item("reactorCoolantSimple", 1)});
    		GT_ModHandler.addCraftingRecipe(new ItemStack(mItems[61], 1), new Object[] {"TTT", "HHH", "TTT", 'H', new ItemStack(mItems[60], 1), 'T', "ingotTin"});
    		GT_ModHandler.addCraftingRecipe(new ItemStack(mItems[62], 1), new Object[] {"THT", "TCT", "THT", 'H', new ItemStack(mItems[61], 1), 'T', "ingotTin", 'C', "plateCopper"});
    		GT_ModHandler.addCraftingRecipe(GT_ModHandler.getIC2Item("overclockerUpgrade", 2), new Object[] {" H ", "WCW", "   ", 'H', new ItemStack(mItems[60], 1), 'W', GT_ModHandler.getIC2Item("insulatedCopperCableItem", 1), 'C', "circuitTier02"});
    	}
    	if (mNeutronReflector) {
    		GT_ModHandler.addCraftingRecipe(new ItemStack(mItems[40], 1), new Object[] {"NNN", "NIN", "NNN", 'I', "plateIridium", 'N', GT_ModHandler.getIC2Item("reactorReflectorThick", 1)});
    	}
    	if (mLapotronpack) {
    		GT_ModHandler.addCraftingRecipe(new ItemStack(mItems[45], 1), new Object[] {"CLC", "SPS", "CIC", 'L', "10kkEUStore", 'C', "circuitTier07", 'I', "plateIridium", 'P', new ItemStack(GT_ModHandler.getIC2Item("lapPack", 1).getItem(), 1, -1), 'S', "itemSuperconductor"});
    	}
    	if (mLithiumbattery) {
    		GT_ModHandler.addCraftingRecipe(new ItemStack(mItems[56], 1), new Object[] {" C ", "ALA", "ALA", 'C', GT_ModHandler.getIC2Item("doubleInsulatedGoldCableItem", 1), 'L', "element_1li", 'A', "ingotAluminium"});
    	}
    	if (mLithiumpack) {
    		GT_ModHandler.addCraftingRecipe(new ItemStack(mItems[58], 1), new Object[] {"LCL", "LAL", "L L", 'L', "LiBattery", 'C', "circuitTier04", 'A', "ingotAluminium"});
    	}
    	if (mLightHelmet) {
    		GT_ModHandler.addShapelessCraftingRecipe(new ItemStack(mItems[44], 1), new Object[] {GT_ModHandler.getIC2Item("solarHelmet", 1), GT_ModHandler.getIC2Item("luminator", 1)});	
    	}
    	
    	if (mRockcutter) {
    		GT_ModHandler.addCraftingRecipe(new ItemStack(mItems[46], 1), new Object[] {"DR ", "DR ", "DCB", 'B', "10kEUStore", 'C', "circuitTier02", 'R', mDiamondDrillTitanium?"ingotTitanium":mSteelETools?"ingotSteel":"ingotRefinedIron", 'D', "dustDiamond"});
        }
    	if (mJackHammer) {
    		GT_ModHandler.addCraftingRecipe(new ItemStack(mItems[39], 1), new Object[] {"RBR", " C ", " R ", 'B', "10kEUStore", 'C', "circuitTier02", 'R', "ingotRefinedIron"});
    		GT_ModHandler.addCraftingRecipe(new ItemStack(mItems[41], 1), new Object[] {"RBR", " C ", " R ", 'B', "10kEUStore", 'C', "circuitTier02", 'R', "ingotSteel"});
    		GT_ModHandler.addCraftingRecipe(new ItemStack(mItems[42], 1), new Object[] {"RBR", " C ", " D ", 'B', "100kEUStore", 'C', "circuitTier04", 'R', "ingotTitanium", 'D', "dustDiamond"});
        }
    	if (mTeslaStaff) {
    		GT_ModHandler.addCraftingRecipe(new ItemStack(mItems[47], 1), new Object[] {"LS ", "SI ", "  I", 'L', "10kkEUStore", 'S', "itemSuperconductor", 'I', "plateIridium"});
        }
    	if (mCloakingDevice) {
    		GT_ModHandler.addCraftingRecipe(new ItemStack(mItems[38], 1), new Object[] {"CIC", "ILI", "CIC", 'L', "10kkEUStore", 'I', "plateIridium", 'C', "ingotChrome"});
    	}
    	if (mWatermillRecycling) {
    		GT_ModHandler.addShapelessCraftingRecipe(GT_ModHandler.getIC2Item("generator", 1), new Object[] {GT_ModHandler.getIC2Item("waterMill", 1), GT_ModHandler.getIC2Item("waterMill", 1)});
    	}
    	
        GT_ModHandler.addShapelessCraftingRecipe(new ItemStack(mItems[31], 1)						, new Object[] {new ItemStack(Item.bowlEmpty, 1), new ItemStack(Item.flint, 1)});
        GT_ModHandler.addShapelessCraftingRecipe(GT_ModHandler.getIC2Item("hydratedCoalDust", 1)	, new Object[] {new ItemStack(mItems[31], 1)	, new ItemStack(Item.coal, 1)});
        GT_ModHandler.addShapelessCraftingRecipe(GT_OreDictUnificator.get("dustGold", null, 1)		, new Object[] {new ItemStack(mItems[31], 1)	, new ItemStack(Item.ingotGold, 1)});
        GT_ModHandler.addShapelessCraftingRecipe(GT_OreDictUnificator.get("dustClay", null, 1)		, new Object[] {new ItemStack(mItems[31], 1)	, new ItemStack(Block.blockClay, 1)});
        GT_ModHandler.addShapelessCraftingRecipe(GT_OreDictUnificator.get("dustCopper", null, 1)	, new Object[] {new ItemStack(mItems[31], 1)	, "ingotCopper"});
        GT_ModHandler.addShapelessCraftingRecipe(GT_OreDictUnificator.get("dustTin", null, 1)		, new Object[] {new ItemStack(mItems[31], 1)	, "ingotTin"});
        GT_ModHandler.addShapelessCraftingRecipe(GT_OreDictUnificator.get("dustSilver", null, 1)	, new Object[] {new ItemStack(mItems[31], 1)	, "ingotSilver"});
        GT_ModHandler.addShapelessCraftingRecipe(GT_OreDictUnificator.get("dustElectrum", null, 1)	, new Object[] {new ItemStack(mItems[31], 1)	, "ingotElectrum"});
        
        GT_ModHandler.addCraftingRecipe(new ItemStack(mItems[30], 1), new Object[] {" R ", "SRS", "SSS", 'S', new ItemStack(Block.stoneBrick, 1), 'R', "ingotRefinedIron"});
        GT_ModHandler.addShapelessCraftingRecipe(GT_ModHandler.getIC2Item("hydratedCoalDust", 1)	, new Object[] {new ItemStack(mItems[30], 1, -1), new ItemStack(Item.coal, 1)});
        GT_ModHandler.addShapelessCraftingRecipe(GT_OreDictUnificator.get("dustGold", null, 1)		, new Object[] {new ItemStack(mItems[30], 1, -1), new ItemStack(Item.ingotGold, 1)});
        GT_ModHandler.addShapelessCraftingRecipe(GT_OreDictUnificator.get("dustClay", null, 1)		, new Object[] {new ItemStack(mItems[30], 1, -1), new ItemStack(Block.blockClay, 1)});
        GT_ModHandler.addShapelessCraftingRecipe(GT_OreDictUnificator.get("dustCopper", null, 1)	, new Object[] {new ItemStack(mItems[30], 1, -1), "ingotCopper"});
        GT_ModHandler.addShapelessCraftingRecipe(GT_OreDictUnificator.get("dustTin", null, 1)		, new Object[] {new ItemStack(mItems[30], 1, -1), "ingotTin"});
        GT_ModHandler.addShapelessCraftingRecipe(GT_OreDictUnificator.get("dustBronze", null, 1)	, new Object[] {new ItemStack(mItems[30], 1, -1), "ingotBronze"});
        GT_ModHandler.addShapelessCraftingRecipe(GT_OreDictUnificator.get("dustSilver", null, 1)	, new Object[] {new ItemStack(mItems[30], 1, -1), "ingotSilver"});
        GT_ModHandler.addShapelessCraftingRecipe(GT_OreDictUnificator.get("dustElectrum", null, 1)	, new Object[] {new ItemStack(mItems[30], 1, -1), "ingotElectrum"});
        GT_ModHandler.addShapelessCraftingRecipe(GT_OreDictUnificator.get("dustBrass", null, 1)		, new Object[] {new ItemStack(mItems[30], 1, -1), "ingotBrass"});
        GT_ModHandler.addShapelessCraftingRecipe(GT_OreDictUnificator.get("dustIron", null, 1)		, new Object[] {new ItemStack(mItems[30], 1, -1), new ItemStack(Item.ingotIron, 1)});
        GT_ModHandler.addShapelessCraftingRecipe(GT_OreDictUnificator.get("dustIron", null, 1)		, new Object[] {new ItemStack(mItems[30], 1, -1), "ingotRefinedIron"});
        
        GT_ModHandler.removeRecipe(new ItemStack[] {GT_OreDictUnificator.get("dustGold", null, 1), GT_OreDictUnificator.get("dustSilver", null, 1)});
        GT_ModHandler.removeRecipe(new ItemStack[] {GT_OreDictUnificator.get("dustCopper", null, 1), GT_OreDictUnificator.get("dustCopper", null, 1), GT_OreDictUnificator.get("dustCopper", null, 1), GT_OreDictUnificator.get("dustTin", null, 1)});
        
        GT_ModHandler.addShapelessCraftingRecipe(GT_OreDictUnificator.get("dustElectrum", null, 2)	, new Object[] {"dustSilver", "dustGold"});
        GT_ModHandler.addShapelessCraftingRecipe(GT_OreDictUnificator.get("dustElectrum", null, 1)	, new Object[] {"dustSmallSilver", "dustSmallSilver", "dustSmallGold", "dustSmallGold"});
        GT_ModHandler.addShapelessCraftingRecipe(GT_OreDictUnificator.get("dustBrass"	, null, 4)	, new Object[] {"dustCopper", "dustCopper", "dustCopper", "dustZinc"});
        GT_ModHandler.addShapelessCraftingRecipe(GT_OreDictUnificator.get("dustBrass"	, null, 1)	, new Object[] {"dustSmallCopper", "dustSmallCopper", "dustSmallCopper", "dustSmallZinc"});
        GT_ModHandler.addShapelessCraftingRecipe(GT_OreDictUnificator.get("dustBronze"	, null, 2)	, new Object[] {"dustCopper", "dustCopper", "dustCopper", "dustTin"});
        GT_ModHandler.addShapelessCraftingRecipe(GT_OreDictUnificator.get("dustInvar"	, null, 3)	, new Object[] {"dustIron", "dustIron", "dustNickel"});
        GT_ModHandler.addShapelessCraftingRecipe(GT_MetaItem_SmallDust.instance.getStack( 29, 3)	, new Object[] {"dustSmallIron", "dustSmallIron", "dustSmallNickel"});
        GT_ModHandler.addShapelessCraftingRecipe(GT_MetaItem_SmallDust.instance.getStack(245, 2)	, new Object[] {"dustSmallCopper", "dustSmallCopper", "dustSmallCopper", "dustSmallTin"});
        GT_ModHandler.addShapelessCraftingRecipe(new ItemStack(Item.gunpowder, 3), new Object[] {"dustCoal", "dustSulfur", "dustSaltpeter", "dustSaltpeter"});
        GT_ModHandler.addShapelessCraftingRecipe(new ItemStack(Item.gunpowder, 2), new Object[] {"dustCharcoal", "dustSulfur", "dustSaltpeter", "dustSaltpeter"});
        
        GT_ModHandler.addShapelessCraftingRecipe(GT_ModHandler.getIC2Item("fertilizer", 2), new Object[] {"dustSulfur", "element_1ca"});
        GT_ModHandler.addShapelessCraftingRecipe(GT_ModHandler.getIC2Item("fertilizer", 1), new Object[] {"dustAsh", "dustAsh", "dustAsh", "dustAsh"});
        GT_ModHandler.addShapelessCraftingRecipe(GT_MetaItem_Dust.instance.getStack(9, 10), new Object[] {"element_1k", "element_1k", "element_1n", "element_1n", "element_2o", "element_2o", "element_2o"});
        GT_ModHandler.addShapelessCraftingRecipe(GT_MetaItem_Dust.instance.getStack(9,  5), new Object[] {"element_1k", "element_1n", "element_1o", "element_1o", "element_1o"});
        GT_ModHandler.addShapelessCraftingRecipe(GT_ModHandler.getIC2Item("carbonFiber",1), new Object[] {"element_1c", "element_1c", "element_1c", "element_1c", "element_1c", "element_1c", "element_1c", "element_1c", "element_1c"});
        GT_ModHandler.addShapelessCraftingRecipe(GT_MetaItem_Cell.instance.getStack(9,  4), new Object[] {"cell_1c", "cell_1h", "cell_1h", "cell_1h", "cell_1h"});
        GT_ModHandler.addShapelessCraftingRecipe(GT_ModHandler.getIC2Item("waterCell",  6), new Object[] {"cell_1h", "cell_1h", "cell_1h", "cell_1h", "cell_2o", GT_ModHandler.getIC2Item("cell", 1)});
        GT_ModHandler.addShapelessCraftingRecipe(GT_ModHandler.getIC2Item("waterCell",  3), new Object[] {"cell_1h", "cell_1h", "cell_1o"});
        GT_ModHandler.addShapelessCraftingRecipe(GT_MetaItem_Cell.instance.getStack(32, 6), new Object[] {"cell_1na", "dustSulfur", "cell_1o", "cell_1o", "cell_1o", "cell_1o", GT_ModHandler.getIC2Item("cell", 1)});
        GT_ModHandler.addShapelessCraftingRecipe(GT_MetaItem_Cell.instance.getStack(32, 6), new Object[] {"cell_1na", "dustSulfur", "cell_2o", "cell_2o", GT_ModHandler.getIC2Item("cell", 1), GT_ModHandler.getIC2Item("cell", 1), GT_ModHandler.getIC2Item("cell", 1)});
        GT_ModHandler.addShapelessCraftingRecipe(GT_MetaItem_Cell.instance.getStack(33, 5), new Object[] {"cell_1ca", "cell_1c", "cell_1o", "cell_1o", "cell_1o"});
        GT_ModHandler.addShapelessCraftingRecipe(GT_MetaItem_Cell.instance.getStack(33, 5), new Object[] {"cell_1ca", "cell_1c", "cell_2o", "cell_2o", GT_ModHandler.getIC2Item("cell", 1)});
        GT_ModHandler.addShapelessCraftingRecipe(GT_MetaItem_Cell.instance.getStack(34, 9), new Object[] {"cell_1c", "cell_1c", "cell_1c_4h", "cell_1h", "cell_1n", "cell_1n", "cell_1n", "cell_2o", "cell_2o"});
        GT_ModHandler.addShapelessCraftingRecipe(GT_MetaItem_Cell.instance.getStack(35, 5), new Object[] {"cell_3c_5h_3n_9o", GT_ModHandler.getIC2Item("coalfuelCell", 1), GT_ModHandler.getIC2Item("coalfuelCell", 1), GT_ModHandler.getIC2Item("coalfuelCell", 1), GT_ModHandler.getIC2Item("coalfuelCell", 1)});
        GT_ModHandler.addShapelessCraftingRecipe(GT_MetaItem_Cell.instance.getStack(22, 5), new Object[] {"cell_3c_5h_3n_9o", GT_MetaItem_Cell.instance.getStack(18, 1), GT_MetaItem_Cell.instance.getStack(18, 1), GT_MetaItem_Cell.instance.getStack(18, 1), GT_MetaItem_Cell.instance.getStack(18, 1)});
        
        GT_ModHandler.addCraftingRecipe(GT_ModHandler.getIC2Item("suBattery", 32), new Object[] {"C  ", "Q  ", "R  ", 'Q', "element_1hg", 'R', "dustRedstone", 'C', GT_ModHandler.getIC2Item("insulatedCopperCableItem", 1)});
        GT_ModHandler.addCraftingRecipe(GT_ModHandler.getIC2Item("suBattery", 32), new Object[] {"C  ", "Q  ", "R  ", 'Q', "dustQuicksilver", 'R', "dustRedstone", 'C', GT_ModHandler.getIC2Item("insulatedCopperCableItem", 1)});
        GT_ModHandler.addCraftingRecipe(GT_ModHandler.getIC2Item("suBattery", 32), new Object[] {"C  ", "Q  ", "R  ", 'Q', "ingotQuicksilver", 'R', "dustRedstone", 'C', GT_ModHandler.getIC2Item("insulatedCopperCableItem", 1)});
        
        GT_ModHandler.addCraftingRecipe(GT_ModHandler.getIC2Item("suBattery", 32), new Object[] {"C  ", "R  ", "Q  ", 'Q', "element_1hg", 'R', "dustRedstone", 'C', GT_ModHandler.getIC2Item("insulatedCopperCableItem", 1)});
        GT_ModHandler.addCraftingRecipe(GT_ModHandler.getIC2Item("suBattery", 32), new Object[] {"C  ", "R  ", "Q  ", 'Q', "dustQuicksilver", 'R', "dustRedstone", 'C', GT_ModHandler.getIC2Item("insulatedCopperCableItem", 1)});
        GT_ModHandler.addCraftingRecipe(GT_ModHandler.getIC2Item("suBattery", 32), new Object[] {"C  ", "R  ", "Q  ", 'Q', "ingotQuicksilver", 'R', "dustRedstone", 'C', GT_ModHandler.getIC2Item("insulatedCopperCableItem", 1)});
        
        GT_ModHandler.addCraftingRecipe(GT_ModHandler.getIC2Item("glassFiberCableItem", 4), new Object[] {"GGG", "XDX", "GGG", 'G', new ItemStack(Block.glass, 1, -1), 'X', "dustRedstone", 'D', "itemDiamond"});
        GT_ModHandler.addCraftingRecipe(GT_ModHandler.getIC2Item("glassFiberCableItem", 6), new Object[] {"GGG", "XDX", "GGG", 'G', new ItemStack(Block.glass, 1, -1), 'X', "ingotSilver", 'D', "itemDiamond"});
        GT_ModHandler.addCraftingRecipe(GT_ModHandler.getIC2Item("glassFiberCableItem", 8), new Object[] {"GGG", "XDX", "GGG", 'G', new ItemStack(Block.glass, 1, -1), 'X', "ingotElectrum", 'D', "itemDiamond"});
        
        GT_ModHandler.addCraftingRecipe(GT_ModHandler.getIC2Item("reBattery", 2), new Object[] {" C ", "TLT", "TST", 'C', GT_ModHandler.getIC2Item("insulatedCopperCableItem", 1), 'T', "ingotTin", 'S', "dustSulfur", 'L', "dustLead"});
        GT_ModHandler.addCraftingRecipe(GT_ModHandler.getIC2Item("reBattery", 2), new Object[] {" C ", "TST", "TLT", 'C', GT_ModHandler.getIC2Item("insulatedCopperCableItem", 1), 'T', "ingotTin", 'S', "dustSulfur", 'L', "dustLead"});
    	
        GT_ModHandler.addCraftingRecipe(GT_ModHandler.getIC2Item("electronicCircuit", 1), new Object[] {"CCC", "SRS", "CCC", 'C', GT_ModHandler.getIC2Item("insulatedCopperCableItem", 1), 'R', "ingotAluminium", 'S', "dustRedstone"});
    	GT_ModHandler.addCraftingRecipe(GT_ModHandler.getIC2Item("electronicCircuit", 1), new Object[] {"CSC", "CRC", "CSC", 'C', GT_ModHandler.getIC2Item("insulatedCopperCableItem", 1), 'R', "ingotAluminium", 'S', "dustRedstone"});
    	
    	if (mSilverCircuit) {
        	GT_ModHandler.addCraftingRecipe(GT_ModHandler.getIC2Item("electronicCircuit", 2), new Object[] {"CCC", "SRS", "CCC", 'C', GT_ModHandler.getIC2Item("insulatedCopperCableItem", 1), 'R', "ingotRefinedIron", 'S', "ingotElectrum"});
        	GT_ModHandler.addCraftingRecipe(GT_ModHandler.getIC2Item("electronicCircuit", 2), new Object[] {"CSC", "CRC", "CSC", 'C', GT_ModHandler.getIC2Item("insulatedCopperCableItem", 1), 'R', "ingotRefinedIron", 'S', "ingotElectrum"});

        	GT_ModHandler.addCraftingRecipe(GT_ModHandler.getIC2Item("electronicCircuit", 2), new Object[] {"CCC", "SRS", "CCC", 'C', GT_ModHandler.getIC2Item("insulatedCopperCableItem", 1), 'R', "ingotAluminium", 'S', "ingotElectrum"});
        	GT_ModHandler.addCraftingRecipe(GT_ModHandler.getIC2Item("electronicCircuit", 2), new Object[] {"CSC", "CRC", "CSC", 'C', GT_ModHandler.getIC2Item("insulatedCopperCableItem", 1), 'R', "ingotAluminium", 'S', "ingotElectrum"});
        }
    	
    	GT_ModHandler.addCraftingRecipe(GT_ModHandler.getIC2Item("advancedCircuit", 1), new Object[] {"SGS", "LCL", "SGS", 'C', GT_ModHandler.getIC2Item("electronicCircuit", 1), 'S', "dustRedstone", 'G', "dustGlowstone", 'L', "itemLazurite"});
       	GT_ModHandler.addCraftingRecipe(GT_ModHandler.getIC2Item("advancedCircuit", 1), new Object[] {"SLS", "GCG", "SLS", 'C', GT_ModHandler.getIC2Item("electronicCircuit", 1), 'S', "dustRedstone", 'G', "dustGlowstone", 'L', "itemLazurite"});
       	
       	GT_ModHandler.addCraftingRecipe(GT_ModHandler.getIC2Item("advancedCircuit", 1), new Object[] {"SGS", "LCL", "SGS", 'C', "plateSilicon", 'S', "dustRedstone", 'G', "dustGlowstone", 'L', "itemLazurite"});
       	GT_ModHandler.addCraftingRecipe(GT_ModHandler.getIC2Item("advancedCircuit", 1), new Object[] {"SLS", "GCG", "SLS", 'C', "plateSilicon", 'S', "dustRedstone", 'G', "dustGlowstone", 'L', "itemLazurite"});
       	
        if (mSilverAdvCircuit) {
           	GT_ModHandler.addCraftingRecipe(GT_ModHandler.getIC2Item("advancedCircuit", 2), new Object[] {"SGS", "LCL", "SGS", 'C', GT_ModHandler.getIC2Item("electronicCircuit", 1), 'S', "ingotElectrum", 'G', "dustGlowstone", 'L', "itemLazurite"});
           	GT_ModHandler.addCraftingRecipe(GT_ModHandler.getIC2Item("advancedCircuit", 2), new Object[] {"SLS", "GCG", "SLS", 'C', GT_ModHandler.getIC2Item("electronicCircuit", 1), 'S', "ingotElectrum", 'G', "dustGlowstone", 'L', "itemLazurite"});
           	
           	GT_ModHandler.addCraftingRecipe(GT_ModHandler.getIC2Item("advancedCircuit", 2), new Object[] {"SGS", "LCL", "SGS", 'C', "plateSilicon", 'S', "ingotElectrum", 'G', "dustGlowstone", 'L', "itemLazurite"});
           	GT_ModHandler.addCraftingRecipe(GT_ModHandler.getIC2Item("advancedCircuit", 2), new Object[] {"SLS", "GCG", "SLS", 'C', "plateSilicon", 'S', "ingotElectrum", 'G', "dustGlowstone", 'L', "itemLazurite"});
        }
        if (mSlime2Rubber) {
            GT_ModHandler.addExtractionRecipe(new ItemStack(Item.slimeBall, 1), GT_ModHandler.getIC2Item("rubber", 2));
            GT_ModHandler.addSmeltingRecipe(new ItemStack(Item.slimeBall,1), GT_ModHandler.getIC2Item("resin", 1));
        }
        if (mEnergyRuby) {
        	GT_ModHandler.addCraftingRecipe(GT_ModHandler.getIC2Item("energyCrystal", 1), new Object[] {"DDD", "DRD", "DDD", 'D', "dustRedstone", 'R', "gemRuby"});
        }
        
    	GT_ModHandler.addCraftingRecipe(GT_ModHandler.getIC2Item("lapotronCrystal", 1), new Object[] {"LCL", "LSL", "LCL", 'C', "circuitTier02", 'S', new ItemStack(GT_ModHandler.getIC2Item("energyCrystal", 1).getItem(), 1, -1), 'L', "itemLazurite"});
    	GT_ModHandler.addCraftingRecipe(GT_ModHandler.getIC2Item("lapPack", 1), new Object[] {"LCL", "LBL", "L L", 'C', "circuitTier04", 'B', "60kEUPack",  'L', "blockLazurite"});
    	
        if (mLapotronSapphire) {
        	GT_ModHandler.addCraftingRecipe(GT_ModHandler.getIC2Item("lapotronCrystal", 1), new Object[] {"LCL", "LSL", "LCL", 'C', "circuitTier02", 'S', "gemSapphire", 'L', "itemLazurite"});
        }
        if (mHeliumLaser) {
        	if (GT_ModHandler.removeRecipe(GT_ModHandler.getIC2Item("miningLaser", 1)))
        		GT_ModHandler.addCraftingRecipe(GT_ModHandler.getIC2Item("miningLaser", 1), new Object[] {"RHE", "TTC", " AA", 'C', "circuitTier04", 'H', mHeliumCoolant?new ItemStack(mItems[36], 1, 0):mNaKCoolant?new ItemStack(mItems[62], 1, 0):GT_ModHandler.getIC2Item("reactorCoolantSix", 1), 'R', "gemRuby", 'T', "ingotTitanium", 'E', "100kEUStore", 'A', GT_ModHandler.getIC2Item("advancedAlloy", 1)});
        }
        
        GT_ModHandler.removeRecipe(new ItemStack[] {GT_OreDictUnificator.get("ingotRefinedIron", null, 1), GT_OreDictUnificator.get("ingotRefinedIron", null, 1), GT_OreDictUnificator.get("ingotRefinedIron", null, 1), GT_OreDictUnificator.get("ingotBronze", null, 1), GT_OreDictUnificator.get("ingotBronze", null, 1), GT_OreDictUnificator.get("ingotBronze", null, 1), GT_OreDictUnificator.get("ingotTin", null, 1), GT_OreDictUnificator.get("ingotTin", null, 1), GT_OreDictUnificator.get("ingotTin", null, 1)});
        GT_ModHandler.addRollingMachineRecipe(GT_ModHandler.getIC2Item("mixedMetalIngot", 2), new Object[] {"RRR", "BBB", "TTT", 'R', "ingotRefinedIron", 'B', "ingotBronze", 'T', "ingotTin"});
        GT_ModHandler.addRollingMachineRecipe(GT_ModHandler.getIC2Item("mixedMetalIngot", 2), new Object[] {"RRR", "BBB", "TTT", 'R', "ingotRefinedIron", 'B', "ingotBronze", 'T', "ingotZinc"});
        GT_ModHandler.addRollingMachineRecipe(GT_ModHandler.getIC2Item("mixedMetalIngot", 2), new Object[] {"RRR", "BBB", "TTT", 'R', "ingotRefinedIron", 'B', "ingotBrass", 'T', "ingotTin"});
        GT_ModHandler.addRollingMachineRecipe(GT_ModHandler.getIC2Item("mixedMetalIngot", 2), new Object[] {"RRR", "BBB", "TTT", 'R', "ingotRefinedIron", 'B', "ingotBrass", 'T', "ingotZinc"});
        if (mTitaniumAlloy) {
            GT_ModHandler.addRollingMachineRecipe(GT_ModHandler.getIC2Item("mixedMetalIngot", 5), new Object[] {"SSS", "BBB", "TTT", 'S', "ingotTitanium", 'B', "ingotBronze", 'T', "ingotTin"});
            GT_ModHandler.addRollingMachineRecipe(GT_ModHandler.getIC2Item("mixedMetalIngot", 5), new Object[] {"SSS", "BBB", "TTT", 'S', "ingotTitanium", 'B', "ingotBronze", 'T', "ingotZinc"});
            GT_ModHandler.addRollingMachineRecipe(GT_ModHandler.getIC2Item("mixedMetalIngot", 5), new Object[] {"SSS", "BBB", "TTT", 'S', "ingotTitanium", 'B', "ingotBrass", 'T', "ingotTin"});
            GT_ModHandler.addRollingMachineRecipe(GT_ModHandler.getIC2Item("mixedMetalIngot", 5), new Object[] {"SSS", "BBB", "TTT", 'S', "ingotTitanium", 'B', "ingotBrass", 'T', "ingotZinc"});
            GT_ModHandler.addRollingMachineRecipe(GT_ModHandler.getIC2Item("mixedMetalIngot", 5), new Object[] {"RRR", "BBB", "TTT", 'R', "ingotTungsten", 'B', "ingotBronze", 'T', "ingotTin"});
            GT_ModHandler.addRollingMachineRecipe(GT_ModHandler.getIC2Item("mixedMetalIngot", 5), new Object[] {"RRR", "BBB", "TTT", 'R', "ingotTungsten", 'B', "ingotBronze", 'T', "ingotZinc"});
            GT_ModHandler.addRollingMachineRecipe(GT_ModHandler.getIC2Item("mixedMetalIngot", 5), new Object[] {"RRR", "BBB", "TTT", 'R', "ingotTungsten", 'B', "ingotBrass", 'T', "ingotTin"});
            GT_ModHandler.addRollingMachineRecipe(GT_ModHandler.getIC2Item("mixedMetalIngot", 5), new Object[] {"RRR", "BBB", "TTT", 'R', "ingotTungsten", 'B', "ingotBrass", 'T', "ingotZinc"});
            if (mAluminiumAlloy) {
            	GT_ModHandler.addRollingMachineRecipe(GT_ModHandler.getIC2Item("mixedMetalIngot", 6), new Object[] {"SSS", "BBB", "AAA", 'S', "ingotTitanium", 'B', "ingotBronze", 'A', "ingotAluminium"});
                GT_ModHandler.addRollingMachineRecipe(GT_ModHandler.getIC2Item("mixedMetalIngot", 6), new Object[] {"SSS", "BBB", "AAA", 'S', "ingotTitanium", 'B', "ingotBrass", 'A', "ingotAluminium"});
                GT_ModHandler.addRollingMachineRecipe(GT_ModHandler.getIC2Item("mixedMetalIngot", 6), new Object[] {"RRR", "BBB", "AAA", 'R', "ingotTungsten", 'B', "ingotBronze", 'A', "ingotAluminium"});
                GT_ModHandler.addRollingMachineRecipe(GT_ModHandler.getIC2Item("mixedMetalIngot", 6), new Object[] {"RRR", "BBB", "AAA", 'R', "ingotTungsten", 'B', "ingotBrass", 'A', "ingotAluminium"});
            }
        }
        if (mSteelAlloy) {
            GT_ModHandler.addRollingMachineRecipe(GT_ModHandler.getIC2Item("mixedMetalIngot", 3), new Object[] {"SSS", "BBB", "TTT", 'S', "ingotSteel", 'B', "ingotBronze", 'T', "ingotTin"});
            GT_ModHandler.addRollingMachineRecipe(GT_ModHandler.getIC2Item("mixedMetalIngot", 3), new Object[] {"SSS", "BBB", "TTT", 'S', "ingotSteel", 'B', "ingotBronze", 'T', "ingotZinc"});
            GT_ModHandler.addRollingMachineRecipe(GT_ModHandler.getIC2Item("mixedMetalIngot", 4), new Object[] {"SSS", "BBB", "AAA", 'S', "ingotSteel", 'B', "ingotBronze", 'A', "ingotAluminium"});
            GT_ModHandler.addRollingMachineRecipe(GT_ModHandler.getIC2Item("mixedMetalIngot", 3), new Object[] {"SSS", "BBB", "TTT", 'S', "ingotSteel", 'B', "ingotBrass", 'T', "ingotTin"});
            GT_ModHandler.addRollingMachineRecipe(GT_ModHandler.getIC2Item("mixedMetalIngot", 3), new Object[] {"SSS", "BBB", "TTT", 'S', "ingotSteel", 'B', "ingotBrass", 'T', "ingotZinc"});
            if (mAluminiumAlloy) {
            	GT_ModHandler.addRollingMachineRecipe(GT_ModHandler.getIC2Item("mixedMetalIngot", 4), new Object[] {"SSS", "BBB", "AAA", 'S', "ingotSteel", 'B', "ingotBrass", 'A', "ingotAluminium"});
            	GT_ModHandler.addRollingMachineRecipe(GT_ModHandler.getIC2Item("mixedMetalIngot", 4), new Object[] {"SSS", "BBB", "AAA", 'S', "ingotSteel", 'B', "ingotBronze", 'A', "ingotAluminium"});
        	}
    	}
        if (mAluminiumAlloy) {
            GT_ModHandler.addRollingMachineRecipe(GT_ModHandler.getIC2Item("mixedMetalIngot", 3), new Object[] {"RRR", "BBB", "AAA", 'R', "ingotRefinedIron", 'B', "ingotBronze", 'A', "ingotAluminium"});
            GT_ModHandler.addRollingMachineRecipe(GT_ModHandler.getIC2Item("mixedMetalIngot", 3), new Object[] {"RRR", "BBB", "AAA", 'R', "ingotRefinedIron", 'B', "ingotBrass", 'A', "ingotAluminium"});
        }
        
        if (mConfig.mUUM2Gems) {
        	GT_ModHandler.addCraftingRecipe(new ItemStack(Item.emerald, 2)	, new Object[] {"UUU", "UUU", " U ", 'U', GT_ModHandler.getIC2Item("matter", 1)});
        	GT_ModHandler.addCraftingRecipe(GT_OreDictUnificator.get("gemRuby"			, GT_MetaItem_Material.instance.getStack(32, 2), 2), new Object[] {" UU", "UUU", "UU ", 'U', GT_ModHandler.getIC2Item("matter", 1)});
        	GT_ModHandler.addCraftingRecipe(GT_OreDictUnificator.get("gemSapphire"		, GT_MetaItem_Material.instance.getStack(33, 2), 2), new Object[] {"UU ", "UUU", " UU", 'U', GT_ModHandler.getIC2Item("matter", 1)});
        	GT_ModHandler.addCraftingRecipe(GT_OreDictUnificator.get("gemGreenSapphire"	, GT_MetaItem_Material.instance.getStack(34, 2), 2), new Object[] {" UU", "UUU", " UU", 'U', GT_ModHandler.getIC2Item("matter", 1)});
        	GT_ModHandler.addCraftingRecipe(GT_OreDictUnificator.get("gemOlivine"		, GT_MetaItem_Material.instance.getStack(37, 2), 2), new Object[] {"UU ", "UUU", "UU ", 'U', GT_ModHandler.getIC2Item("matter", 1)});
        }
        if (mConfig.mUUM2Enderpearl) {
        	GT_ModHandler.addCraftingRecipe(new ItemStack(Item.enderPearl, 1), new Object[] {"UUU", "U U", "UUU", 'U', GT_ModHandler.getIC2Item("matter", 1)});
        }
        if (mConfig.mUUM2Silver) {
        	GT_ModHandler.addCraftingRecipe(new ItemStack(mBlocks[2], 7, 1), new Object[] {" U ", "UUU", "UUU", 'U', GT_ModHandler.getIC2Item("matter", 1)});
        }
        if (mConfig.mUUM2Blazerod) {
        	GT_ModHandler.addCraftingRecipe(new ItemStack(Item.blazeRod, 4), new Object[] {"U U", "UU ", "U U", 'U', GT_ModHandler.getIC2Item("matter", 1)});
        }
        if (mConfig.mUUM2Bauxite) {
        	GT_ModHandler.addCraftingRecipe(new ItemStack(mBlocks[2], 6, 5), new Object[] {"U U", " UU", "U U", 'U', GT_ModHandler.getIC2Item("matter", 1)});
        }
        if (mConfig.mUUM2Titanium) {
        	GT_ModHandler.addCraftingRecipe(GT_MetaItem_Dust.instance.getStack(19, 2), new Object[] {"UUU", " U ", " U ", 'U', GT_ModHandler.getIC2Item("matter", 1)});
        }
        if (mConfig.mUUM2Aluminium) {
        	GT_ModHandler.addCraftingRecipe(GT_MetaItem_Dust.instance.getStack(18, 16), new Object[] {" U ", " U ", "UUU", 'U', GT_ModHandler.getIC2Item("matter", 1)});
        }
        if (mConfig.mUUM2Plutonium) {
        	if (mMatterfabricator && !mMassfabricator && GT_TileEntity_Matterfabricator.sMatterFabricationRate >= 10000000)
        		GT_ModHandler.addShapelessCraftingRecipe(new ItemStack(mItems[51], 1), new Object[] {GT_ModHandler.getIC2Item("reactorUraniumSimple", 1), GT_ModHandler.getIC2Item("matter", 1)});
        	else
        		GT_ModHandler.addCraftingRecipe(new ItemStack(mItems[51], 1), new Object[] {"UUU", "URU", "UUU", 'U', GT_ModHandler.getIC2Item("matter", 1), 'R', GT_ModHandler.getIC2Item("reactorUraniumSimple", 1)});
        }
        
        if (mStorageblockCompressor) {
        	if (mConfig.mSC_Diamond	) GT_ModHandler.addCompressionRecipe(new ItemStack(Item.diamond			, 9, 0), new ItemStack(Block.blockDiamond, 1));
        	if (mConfig.mSC_Emerald	) GT_ModHandler.addCompressionRecipe(new ItemStack(Item.emerald			, 9, 0), new ItemStack(Block.blockEmerald, 1));
        	if (mConfig.mSC_Gold	) GT_ModHandler.addCompressionRecipe(new ItemStack(Item.ingotGold		, 9, 0), new ItemStack(Block.blockGold, 1));
        	if (mConfig.mSC_Iron	) GT_ModHandler.addCompressionRecipe(new ItemStack(Item.ingotIron		, 9, 0), new ItemStack(Block.blockSteel, 1));
        	if (mConfig.mSC_Glow	) GT_ModHandler.addCompressionRecipe(new ItemStack(Item.lightStoneDust	, 4, 0), new ItemStack(Block.glowStone, 1));
        	if (mConfig.mSC_Lapis	) GT_ModHandler.addCompressionRecipe(new ItemStack(Item.dyePowder		, 9, 4), new ItemStack(Block.blockLapis, 1));
        	if (mConfig.mSC_Ruby	) GT_ModHandler.addCompressionRecipe(GT_OreDictUnificator.get("gemRuby"			, null, 9), GT_OreDictUnificator.get("blockRuby"			, null, 1));
        	if (mConfig.mSC_Sapphire) GT_ModHandler.addCompressionRecipe(GT_OreDictUnificator.get("gemSapphire"		, null, 9), GT_OreDictUnificator.get("blockSapphire"		, null, 1));
        	if (mConfig.mSC_Sapphire) GT_ModHandler.addCompressionRecipe(GT_OreDictUnificator.get("gemGreenSapphire", null, 9), GT_OreDictUnificator.get("blockGreenSapphire"	, null, 1));
        	if (mConfig.mSC_Silver	) GT_ModHandler.addCompressionRecipe(GT_OreDictUnificator.get("ingotSilver"		, null, 9), GT_OreDictUnificator.get("blockSilver"			, null, 1));
        	if (mConfig.mSC_Brass	) GT_ModHandler.addCompressionRecipe(GT_OreDictUnificator.get("ingotBrass"		, null, 9), GT_OreDictUnificator.get("blockBrass"			, null, 1));
        	if (mConfig.mSC_Alu		) GT_ModHandler.addCompressionRecipe(GT_OreDictUnificator.get("ingotAluminium"	, null, 9), GT_OreDictUnificator.get("blockAluminium"		, null, 1));
        	if (mConfig.mSC_Titan	) GT_ModHandler.addCompressionRecipe(GT_OreDictUnificator.get("ingotTitanium"	, null, 9), GT_OreDictUnificator.get("blockTitanium"		, null, 1));
        	if (mConfig.mSC_Chrome	) GT_ModHandler.addCompressionRecipe(GT_OreDictUnificator.get("ingotChrome"		, null, 9), GT_OreDictUnificator.get("blockChrome"			, null, 1));
        	if (mConfig.mSC_Steel	) GT_ModHandler.addCompressionRecipe(GT_OreDictUnificator.get("ingotSteel"		, null, 9), GT_OreDictUnificator.get("blockSteel"			, null, 1));
        	if (mConfig.mSC_Copper	) GT_ModHandler.addCompressionRecipe(GT_OreDictUnificator.get("ingotCopper"		, null, 9),	GT_OreDictUnificator.get("blockCopper"			, null, 1));
        	if (mConfig.mSC_Tin		) GT_ModHandler.addCompressionRecipe(GT_OreDictUnificator.get("ingotTin"		, null, 9),	GT_OreDictUnificator.get("blockTin"				, null, 1));
        	if (mConfig.mSC_Bronze	) GT_ModHandler.addCompressionRecipe(GT_OreDictUnificator.get("ingotBronze"		, null, 9),	GT_OreDictUnificator.get("blockBronze"			, null, 1));
        	if (mConfig.mSC_Uranium	) GT_ModHandler.addCompressionRecipe(GT_OreDictUnificator.get("ingotUranium"	, null, 9),	GT_OreDictUnificator.get("blockUranium"			, null, 1));
        }
        if (mStorageblockMacerator) {
        	if (mConfig.mSM_Diamond	) GT_ModHandler.addPulverisationRecipe(new ItemStack(Block.blockDiamond, 1)	, GT_OreDictUnificator.get("dustDiamond", null, 9));
        	if (mConfig.mSM_Emerald	) GT_ModHandler.addPulverisationRecipe(new ItemStack(Block.blockEmerald, 1)	, GT_OreDictUnificator.get("dustEmerald", null, 9));
        	if (mConfig.mSM_Gold	) GT_ModHandler.addPulverisationRecipe(new ItemStack(Block.blockGold , 1)	, GT_OreDictUnificator.get("dustGold", null, 9));
        	if (mConfig.mSM_Iron	) GT_ModHandler.addPulverisationRecipe(new ItemStack(Block.blockSteel, 1)	, GT_OreDictUnificator.get("dustIron", null, 9));
        	if (mConfig.mSM_Iron	) GT_ModHandler.addPulverisationRecipe(GT_ModHandler.getIC2Item("machine",1), GT_OreDictUnificator.get("dustIron", null, 8));
        	if (mConfig.mSM_Lapis	) GT_ModHandler.addPulverisationRecipe(new ItemStack(Block.blockLapis, 1)	, new ItemStack(Item.dyePowder, 9, 4));
        	if (mConfig.mSM_Ruby	) GT_ModHandler.addPulverisationRecipe(GT_OreDictUnificator.get("blockRuby"			, null, 1), GT_OreDictUnificator.get("dustRuby"			, null, 9));
        	if (mConfig.mSM_Sapphire) GT_ModHandler.addPulverisationRecipe(GT_OreDictUnificator.get("blockSapphire"		, null, 1), GT_OreDictUnificator.get("dustSapphire"		, null, 9));
        	if (mConfig.mSM_Sapphire) GT_ModHandler.addPulverisationRecipe(GT_OreDictUnificator.get("blockGreenSapphire", null, 1), GT_OreDictUnificator.get("dustGreenSapphire", null, 9));
        	if (mConfig.mSM_Copper	) GT_ModHandler.addPulverisationRecipe(GT_OreDictUnificator.get("blockCopper"		, null, 1),	GT_OreDictUnificator.get("dustCopper"		, null, 9));
        	if (mConfig.mSM_Tin		) GT_ModHandler.addPulverisationRecipe(GT_OreDictUnificator.get("blockTin"			, null, 1), GT_OreDictUnificator.get("dustTin"			, null, 9));
        	if (mConfig.mSM_Bronze	) GT_ModHandler.addPulverisationRecipe(GT_OreDictUnificator.get("blockBronze"		, null, 1),	GT_OreDictUnificator.get("dustBronze"		, null, 9));
        	if (mConfig.mSM_Uranium	) GT_ModHandler.addPulverisationRecipe(GT_OreDictUnificator.get("blockUranium"		, null, 1), GT_OreDictUnificator.get("dustUranium"		, null, 9));
        	if (mConfig.mSM_Silver	) GT_ModHandler.addPulverisationRecipe(GT_OreDictUnificator.get("blockSilver"		, null, 1), GT_OreDictUnificator.get("dustSilver"		, null, 9));
        	if (mConfig.mSM_Alu		) GT_ModHandler.addPulverisationRecipe(GT_OreDictUnificator.get("blockAluminium"	, null, 1), GT_OreDictUnificator.get("dustAluminium"	, null, 9));
        	if (mConfig.mSM_Titan	) GT_ModHandler.addPulverisationRecipe(GT_OreDictUnificator.get("blockTitanium"		, null, 1), GT_OreDictUnificator.get("dustTitanium"		, null, 9));
        	if (mConfig.mSM_Chrome	) GT_ModHandler.addPulverisationRecipe(GT_OreDictUnificator.get("blockChrome"		, null, 1), GT_OreDictUnificator.get("dustChrome"		, null, 9));
        	if (mConfig.mSM_Steel	) GT_ModHandler.addPulverisationRecipe(GT_OreDictUnificator.get("blockSteel"		, null, 1), GT_OreDictUnificator.get("dustSteel"		, null, 9));
        	if (mConfig.mSM_Brass	) GT_ModHandler.addPulverisationRecipe(GT_OreDictUnificator.get("blockBrass"		, null, 1), GT_OreDictUnificator.get("dustBrass"		, null, 9));
        	
        	if (mConfig.mSS_Iron	) GT_ModHandler.addSmeltingRecipe(GT_ModHandler.getIC2Item("machine", 1), GT_OreDictUnificator.get("ingotRefinedIron", null, 8));
        	if (mConfig.mSS_Iron	) GT_ModHandler.addSmeltingRecipe(new ItemStack(Block.blockSteel, 1), new ItemStack(Item.ingotIron, 9));
        	if (mConfig.mSS_Gold	) GT_ModHandler.addSmeltingRecipe(new ItemStack(Block.blockGold , 1), new ItemStack(Item.ingotGold, 9));
        	if (mConfig.mSS_Copper	) GT_ModHandler.addSmeltingRecipe(GT_OreDictUnificator.get("blockCopper"	, null, 1), GT_OreDictUnificator.get("ingotCopper"		, null, 9));
        	if (mConfig.mSS_Bronze	) GT_ModHandler.addSmeltingRecipe(GT_OreDictUnificator.get("blockBronze"	, null, 1), GT_OreDictUnificator.get("ingotBronze"		, null, 9));
        	if (mConfig.mSS_Tin		) GT_ModHandler.addSmeltingRecipe(GT_OreDictUnificator.get("blockTin"		, null, 1), GT_OreDictUnificator.get("ingotTin"			, null, 9));
        	if (mConfig.mSS_Silver	) GT_ModHandler.addSmeltingRecipe(GT_OreDictUnificator.get("blockSilver"	, null, 1), GT_OreDictUnificator.get("ingotSilver"		, null, 9));
        	if (mConfig.mSS_Alu		) GT_ModHandler.addSmeltingRecipe(GT_OreDictUnificator.get("blockAluminium"	, null, 1), GT_OreDictUnificator.get("ingotAluminium"	, null, 9));
        	if (mConfig.mSS_Titan	) GT_ModHandler.addSmeltingRecipe(GT_OreDictUnificator.get("blockTitanium"	, null, 1), GT_OreDictUnificator.get("ingotTitanium"	, null, 9));
        	if (mConfig.mSS_Chrome	) GT_ModHandler.addSmeltingRecipe(GT_OreDictUnificator.get("blockChrome"	, null, 1), GT_OreDictUnificator.get("ingotChrome"		, null, 9));
        	if (mConfig.mSS_Steel	) GT_ModHandler.addSmeltingRecipe(GT_OreDictUnificator.get("blockSteel"		, null, 1), GT_OreDictUnificator.get("ingotSteel"		, null, 9));
        	if (mConfig.mSS_Brass	) GT_ModHandler.addSmeltingRecipe(GT_OreDictUnificator.get("blockBrass"		, null, 1), GT_OreDictUnificator.get("ingotBrass"		, null, 9));
        }
        
        GT_ModHandler.addPulverisationRecipe(new ItemStack(Item.stick, 1), GT_MetaItem_SmallDust.instance.getStack(15, 2));
        GT_ModHandler.addPulverisationRecipe(new ItemStack(Item.coal, 1, 1), GT_MetaItem_Dust.instance.getStack(47, 1));
        
        if (!GT_ModHandler.isRCloaded()) {
        	GT_ModHandler.addPulverisationRecipe(new ItemStack(Block.obsidian, 1), GT_MetaItem_Dust.instance.getStack(46, 1));
        }
        
        GT_Utility.applyUsagesForMaterials(GT_OreDictUnificator.get("gemRuby"			, null, 1), GT_MetaItem_Dust.instance.getStack(32, 1));
        GT_Utility.applyUsagesForMaterials(GT_OreDictUnificator.get("gemSapphire"		, null, 1), GT_MetaItem_Dust.instance.getStack(33, 1));
        GT_Utility.applyUsagesForMaterials(GT_OreDictUnificator.get("gemGreenSapphire"	, null, 1), GT_MetaItem_Dust.instance.getStack(34, 1));
        GT_Utility.applyUsagesForMaterials(GT_OreDictUnificator.get("gemOlivine"		, null, 1), GT_MetaItem_Dust.instance.getStack(37, 1));
        GT_Utility.applyUsagesForMaterials(GT_OreDictUnificator.get("ingotAluminium"	, null, 1), GT_MetaItem_Dust.instance.getStack(18, 1));
    	GT_Utility.applyUsagesForMaterials(GT_OreDictUnificator.get("ingotTitanium"		, null, 1), GT_MetaItem_Dust.instance.getStack(19, 1));
    	GT_Utility.applyUsagesForMaterials(GT_OreDictUnificator.get("ingotChrome"		, null, 1), GT_MetaItem_Dust.instance.getStack(20, 1));
    	GT_Utility.applyUsagesForMaterials(GT_OreDictUnificator.get("ingotBrass"		, null, 1), GT_MetaItem_Dust.instance.getStack(25, 1));
    	GT_Utility.applyUsagesForMaterials(GT_OreDictUnificator.get("ingotSteel"		, null, 1), GT_MetaItem_Dust.instance.getStack(26, 1));
    	GT_Utility.applyUsagesForMaterials(GT_MetaItem_Material.instance.getStack(21, 1), GT_MetaItem_Dust.instance.getStack(21, 1));
    	GT_Utility.applyUsagesForMaterials(GT_MetaItem_Material.instance.getStack(22, 1), GT_MetaItem_Dust.instance.getStack(22, 1));
    	GT_Utility.applyUsagesForMaterials(GT_MetaItem_Material.instance.getStack(23, 1), GT_MetaItem_Dust.instance.getStack(23, 1));
    	GT_Utility.applyUsagesForMaterials(GT_MetaItem_Material.instance.getStack(24, 1), GT_MetaItem_Dust.instance.getStack(24, 1));
    	GT_Utility.applyUsagesForMaterials(GT_MetaItem_Material.instance.getStack(27, 1), GT_MetaItem_Dust.instance.getStack(27, 1));
    	GT_Utility.applyUsagesForMaterials(GT_MetaItem_Material.instance.getStack(28, 1), GT_MetaItem_Dust.instance.getStack(28, 1));
    	GT_Utility.applyUsagesForMaterials(GT_MetaItem_Material.instance.getStack(29, 1), GT_MetaItem_Dust.instance.getStack(29, 1));
    	GT_Utility.applyUsagesForMaterials(GT_MetaItem_Material.instance.getStack(30, 1), GT_MetaItem_Dust.instance.getStack(30, 1));
    	GT_Utility.applyUsagesForMaterials(GT_MetaItem_Material.instance.getStack(31, 1), GT_MetaItem_Dust.instance.getStack(31, 1));
    	GT_Utility.applyUsagesForMaterials(GT_MetaItem_Material.instance.getStack(16, 1), GT_MetaItem_Material.instance.getStack(16, 1));
    	GT_Utility.applyUsagesForMaterials(GT_MetaItem_Material.instance.getStack(17, 1), GT_OreDictUnificator.get("dustSilver", null, 1));
    	GT_Utility.applyUsagesForMaterials(GT_ModHandler.getIC2Item("iridiumOre", 1), GT_ModHandler.getIC2Item("iridiumOre", 1));
    	GT_Utility.applyUsagesForMaterials(GT_OreDictUnificator.get("ingotBronze", null, 1), GT_OreDictUnificator.get("dustBronze", null, 1));
    	GT_Utility.applyUsagesForMaterials(GT_OreDictUnificator.get("ingotCopper", null, 1), GT_OreDictUnificator.get("dustCopper", null, 1));
    	GT_Utility.applyUsagesForMaterials(GT_OreDictUnificator.get("ingotTin", null, 1), GT_OreDictUnificator.get("dustTin", null, 1));
    	GT_Utility.applyUsagesForMaterials(new ItemStack(Item.ingotIron, 1), GT_OreDictUnificator.get("dustIron", null, 1));
    	GT_Utility.applyUsagesForMaterials(new ItemStack(Item.ingotGold, 1), GT_OreDictUnificator.get("dustGold", null, 1));
    	GT_Utility.applyUsagesForMaterials(new ItemStack(Item.diamond, 1), GT_OreDictUnificator.get("dustDiamond", null, 1));
    	GT_Utility.applyUsagesForMaterials(new ItemStack(Block.planks, 1), GT_MetaItem_SmallDust.instance.getStack(15, 4));
    	
        if (mToolArmorMaceration) {
        	GT_ModHandler.addPulverisationRecipe(new ItemStack(Item.helmetChain, 1)		, GT_MetaItem_SmallDust.instance.getStack(26, 5));
        	GT_ModHandler.addPulverisationRecipe(new ItemStack(Item.plateChain, 1)		, GT_MetaItem_SmallDust.instance.getStack(26, 8));
        	GT_ModHandler.addPulverisationRecipe(new ItemStack(Item.legsChain, 1)		, GT_MetaItem_SmallDust.instance.getStack(26, 7));
        	GT_ModHandler.addPulverisationRecipe(new ItemStack(Item.bootsChain, 1)		, GT_MetaItem_SmallDust.instance.getStack(26, 4));
        	GT_ModHandler.addPulverisationRecipe(new ItemStack(Item.minecartEmpty, 1)	, GT_OreDictUnificator.get("dustIron", null, 5));
        	GT_ModHandler.addPulverisationRecipe(new ItemStack(Item.bucketEmpty, 1)		, GT_OreDictUnificator.get("dustIron", null, 3));
        	GT_ModHandler.addPulverisationRecipe(new ItemStack(Item.doorSteel, 1)		, GT_OreDictUnificator.get("dustIron", null, 6));
        	GT_ModHandler.addPulverisationRecipe(GT_ModHandler.getIC2Item("wrench", 1)	, GT_OreDictUnificator.get("dustBronze", null, 6));
        	GT_ModHandler.addPulverisationRecipe(new ItemStack(Block.fenceIron, 2)		, GT_MetaItem_SmallDust.instance.getStack(241, 3));
        	GT_ModHandler.addPulverisationRecipe(GT_ModHandler.getIC2Item("tinCan", 1)	, GT_MetaItem_SmallDust.instance.getStack(244, 5));
        	
        	if (mTincellCount%4==0)
        		GT_ModHandler.addPulverisationRecipe(GT_ModHandler.getIC2Item("cell", 1), GT_MetaItem_SmallDust.instance.getStack(244, 16/mTincellCount));
        	else
        		GT_ModHandler.addPulverisationRecipe(GT_ModHandler.getIC2Item("cell", 4), GT_MetaItem_SmallDust.instance.getStack(244, 64/mTincellCount));
        }
        
        if (!mConfig.mSCR_Glow		) GT_ModHandler.removeRecipe(new ItemStack[] {tStack = new ItemStack(Item.lightStoneDust, 1), tStack, null, tStack, tStack, null, null, null, null});
        if (!mConfig.mSCR_Bronze	) GT_ModHandler.removeRecipe(new ItemStack[] {tStack = GT_ModHandler.getIC2Item("bronzeIngot", 1), tStack, tStack, tStack, tStack, tStack, tStack, tStack, tStack});
        if (!mConfig.mSCR_Tin		) GT_ModHandler.removeRecipe(new ItemStack[] {tStack = GT_ModHandler.getIC2Item("tinIngot", 1), tStack, tStack, tStack, tStack, tStack, tStack, tStack, tStack});
        if (!mConfig.mSCR_Iron		) GT_ModHandler.removeRecipe(new ItemStack[] {tStack = new ItemStack(Item.ingotIron, 1), tStack, tStack, tStack, tStack, tStack, tStack, tStack, tStack});
        if (!mConfig.mSCR_Gold		) GT_ModHandler.removeRecipe(new ItemStack[] {tStack = new ItemStack(Item.ingotGold, 1), tStack, tStack, tStack, tStack, tStack, tStack, tStack, tStack});
        if (!mConfig.mSCR_Copper	) GT_ModHandler.removeRecipe(new ItemStack[] {tStack = GT_ModHandler.getIC2Item("copperIngot", 1), tStack, tStack, tStack, tStack, tStack, tStack, tStack, tStack});
        if (!mConfig.mSCR_Lapis		) GT_ModHandler.removeRecipe(new ItemStack[] {tStack = new ItemStack(Item.dyePowder, 1, 4), tStack, tStack, tStack, tStack, tStack, tStack, tStack, tStack});
        if (!mConfig.mSCR_Emerald	) GT_ModHandler.removeRecipe(new ItemStack[] {tStack = new ItemStack(Item.emerald, 1), tStack, tStack, tStack, tStack, tStack, tStack, tStack, tStack});
        if (!mConfig.mSCR_Diamond	) GT_ModHandler.removeRecipe(new ItemStack[] {tStack = new ItemStack(Item.diamond, 1), tStack, tStack, tStack, tStack, tStack, tStack, tStack, tStack});
        if (!mConfig.mSCR_Uranium	) GT_ModHandler.removeRecipe(new ItemStack[] {tStack = GT_ModHandler.getIC2Item("uraniumIngot", 1), tStack, tStack, tStack, tStack, tStack, tStack, tStack, tStack});
        
        if (!mConfig.mSRC_Bronze	) GT_ModHandler.removeRecipe(new ItemStack[] {GT_ModHandler.getIC2Item("bronzeBlock", 1), null, null, null, null, null, null, null, null});
        if (!mConfig.mSRC_Tin		) GT_ModHandler.removeRecipe(new ItemStack[] {GT_ModHandler.getIC2Item("tinBlock", 1), null, null, null, null, null, null, null, null});
        if (!mConfig.mSRC_Iron		) GT_ModHandler.removeRecipe(new ItemStack[] {new ItemStack(Block.blockSteel, 1), null, null, null, null, null, null, null, null});
        if (!mConfig.mSRC_Gold		) GT_ModHandler.removeRecipe(new ItemStack[] {new ItemStack(Block.blockGold, 1), null, null, null, null, null, null, null, null});
        if (!mConfig.mSRC_Copper	) GT_ModHandler.removeRecipe(new ItemStack[] {GT_ModHandler.getIC2Item("copperBlock", 1), null, null, null, null, null, null, null, null});
        if (!mConfig.mSRC_Lapis		) GT_ModHandler.removeRecipe(new ItemStack[] {new ItemStack(Block.blockLapis, 1), null, null, null, null, null, null, null, null});
        if (!mConfig.mSRC_Emerald	) GT_ModHandler.removeRecipe(new ItemStack[] {new ItemStack(Block.blockEmerald, 1), null, null, null, null, null, null, null, null});
        if (!mConfig.mSRC_Diamond	) GT_ModHandler.removeRecipe(new ItemStack[] {new ItemStack(Block.blockDiamond, 1), null, null, null, null, null, null, null, null});
        if (!mConfig.mSRC_Uranium	) GT_ModHandler.removeRecipe(new ItemStack[] {GT_ModHandler.getIC2Item("uraniumBlock", 1), null, null, null, null, null, null, null, null});
        
        if (mConfig.mSCR_Silver		) GT_ModHandler.addCraftingRecipe(GT_OreDictUnificator.get("blockSilver"	, null, 1), new Object[] {"XXX", "XXX", "XXX", 'X', GT_OreDictUnificator.get("ingotSilver"		, null, 1)});
        if (mConfig.mSCR_Ruby		) GT_ModHandler.addCraftingRecipe(GT_OreDictUnificator.get("blockRuby"		, null, 1), new Object[] {"XXX", "XXX", "XXX", 'X', GT_OreDictUnificator.get("gemRuby"			, null, 1)});
        if (mConfig.mSCR_Sapphire	) GT_ModHandler.addCraftingRecipe(GT_OreDictUnificator.get("blockSapphire"	, null, 1), new Object[] {"XXX", "XXX", "XXX", 'X', GT_OreDictUnificator.get("gemSapphire"		, null, 1)});
        if (mConfig.mSCR_Alu		) GT_ModHandler.addCraftingRecipe(GT_OreDictUnificator.get("blockAluminium"	, null, 1), new Object[] {"XXX", "XXX", "XXX", 'X', GT_OreDictUnificator.get("ingotAluminium"	, null, 1)});
        if (mConfig.mSCR_Titan		) GT_ModHandler.addCraftingRecipe(GT_OreDictUnificator.get("blockTitanium"	, null, 1), new Object[] {"XXX", "XXX", "XXX", 'X', GT_OreDictUnificator.get("ingotTitanium"	, null, 1)});
        if (mConfig.mSCR_Chrome		) GT_ModHandler.addCraftingRecipe(GT_OreDictUnificator.get("blockChrome"	, null, 1), new Object[] {"XXX", "XXX", "XXX", 'X', GT_OreDictUnificator.get("ingotChrome"		, null, 1)});
        if (mConfig.mSCR_Steel		) GT_ModHandler.addCraftingRecipe(GT_OreDictUnificator.get("blockSteel"		, null, 1), new Object[] {"XXX", "XXX", "XXX", 'X', GT_OreDictUnificator.get("ingotSteel"		, null, 1)});
        if (mConfig.mSCR_Brass		) GT_ModHandler.addCraftingRecipe(GT_OreDictUnificator.get("blockBrass"		, null, 1), new Object[] {"XXX", "XXX", "XXX", 'X', GT_OreDictUnificator.get("ingotBrass"		, null, 1)});
        
        if (mConfig.mSRC_Silver		) GT_ModHandler.addShapelessCraftingRecipe(GT_OreDictUnificator.get("ingotSilver"	, null, 9), new Object[] {GT_OreDictUnificator.get("blockSilver"	, null, 1)});
        if (mConfig.mSRC_Ruby		) GT_ModHandler.addShapelessCraftingRecipe(GT_OreDictUnificator.get("gemRuby"		, null, 9), new Object[] {GT_OreDictUnificator.get("blockRuby"		, null, 1)});
        if (mConfig.mSRC_Sapphire	) GT_ModHandler.addShapelessCraftingRecipe(GT_OreDictUnificator.get("gemSapphire"	, null, 9), new Object[] {GT_OreDictUnificator.get("blockSapphire"	, null, 1)});
        if (mConfig.mSRC_Alu		) GT_ModHandler.addShapelessCraftingRecipe(GT_OreDictUnificator.get("ingotAluminium", null, 9), new Object[] {GT_OreDictUnificator.get("blockAluminium"	, null, 1)});
        if (mConfig.mSRC_Titan		) GT_ModHandler.addShapelessCraftingRecipe(GT_OreDictUnificator.get("ingotTitanium"	, null, 9), new Object[] {GT_OreDictUnificator.get("blockTitanium"	, null, 1)});
        if (mConfig.mSRC_Chrome		) GT_ModHandler.addShapelessCraftingRecipe(GT_OreDictUnificator.get("ingotChrome"	, null, 9), new Object[] {GT_OreDictUnificator.get("blockChrome"	, null, 1)});
        if (mConfig.mSRC_Steel		) GT_ModHandler.addShapelessCraftingRecipe(GT_OreDictUnificator.get("ingotSteel"	, null, 9), new Object[] {GT_OreDictUnificator.get("blockSteel"		, null, 1)});
        if (mConfig.mSRC_Brass		) GT_ModHandler.addShapelessCraftingRecipe(GT_OreDictUnificator.get("ingotBrass"	, null, 9), new Object[] {GT_OreDictUnificator.get("blockBrass"		, null, 1)});
        
        GT_ModHandler.addPulverisationRecipe(new ItemStack(Item.enderPearl, 1)		, GT_MetaItem_Dust.instance.getStack( 0, 1));
        GT_ModHandler.addPulverisationRecipe(new ItemStack(Item.eyeOfEnder, 1)		, GT_MetaItem_Dust.instance.getStack( 1, 2));
        GT_ModHandler.addPulverisationRecipe(new ItemStack(Item.flint, 1)			, GT_MetaItem_SmallDust.instance.getStack( 7, 2));
        GT_ModHandler.addPulverisationRecipe(new ItemStack(Block.netherrack, 1)	, GT_MetaItem_Dust.instance.getStack( 6, 1));
        GT_ModHandler.addPulverisationRecipe(new ItemStack(Block.whiteStone, 1)	, GT_MetaItem_Dust.instance.getStack(10, 1));
        
        GT_Mod.addGrinderRecipe(new ItemStack(Block.netherrack, 16), -1, new ItemStack(Item.goldNugget, 1), GT_MetaItem_Dust.instance.getStack(6, 16), null, GT_ModHandler.getIC2Item("cell", 1));
		GT_Mod.addGrinderRecipe(new ItemStack(Block.netherrack, 8), GT_MetaItem_Cell.instance.getStack(16, 1), new ItemStack(Item.goldNugget, 1), GT_MetaItem_Dust.instance.getStack(6, 8), null, GT_ModHandler.getIC2Item("cell", 1));
		
        GT_ModHandler.addCraftingRecipe(GT_ModHandler.getIC2Item("luminator", 16), new Object[] {"RTR", "GHG", "GGG", 'H', "element_1he", 'T', "ingotTin", 'R', "ingotRefinedIron", 'G', new ItemStack(Block.glass, 1)});
        GT_ModHandler.addCraftingRecipe(GT_ModHandler.getIC2Item("luminator", 16), new Object[] {"RTR", "GHG", "GGG", 'H', "element_1hg", 'T', "ingotTin", 'R', "ingotRefinedIron", 'G', new ItemStack(Block.glass, 1)});
        GT_ModHandler.addCraftingRecipe(GT_ModHandler.getIC2Item("mfeUnit", 1), new Object[] {"GCG", "CMC", "GCG", 'C', "100kEUStore", 'G', GT_ModHandler.getIC2Item("doubleInsulatedGoldCableItem", 1), 'M', "rawMachineTier01"});
        
        //GT_ModHandler.addShapelessCraftingRecipe(new ItemStack(mItems[54], 1), new Object[] {"element_1li", GT_ModHandler.getIC2Item("cell", 1)});
        
        GT_ModHandler.addShapelessCraftingRecipe(GT_ModHandler.getIC2Item("cell", 1), new Object[] {GT_ModHandler.getIC2Item("airCell", 1)});
        GT_ModHandler.addExtractionRecipe(GT_ModHandler.getIC2Item("airCell", 1), GT_ModHandler.getIC2Item("cell", 1));
        GT_ModHandler.addExtractionRecipe(GT_ModHandler.getIC2Item("filledTinCan", 1), GT_ModHandler.getIC2Item("tinCan", 1));
    	
        GT_ModHandler.addShapelessCraftingRecipe(GT_MetaItem_Material.instance.getStack(0, 8)	, new Object[] {GT_ModHandler.getIC2Item("coin", 1)});
        GT_ModHandler.addShapelessCraftingRecipe(GT_ModHandler.getIC2Item("coin", 8)			, new Object[] {GT_MetaItem_Material.instance.getStack(1, 1)});
        GT_ModHandler.addShapelessCraftingRecipe(GT_MetaItem_Material.instance.getStack(1, 8)	, new Object[] {GT_MetaItem_Material.instance.getStack(2, 1)});
        GT_ModHandler.addShapelessCraftingRecipe(GT_MetaItem_Material.instance.getStack(2, 8)	, new Object[] {GT_MetaItem_Material.instance.getStack(3, 1)});
        
        GT_ModHandler.addShapelessCraftingRecipe(GT_ModHandler.getIC2Item("coin", 1)			, new Object[] {GT_MetaItem_Material.instance.getStack(0, 1), GT_MetaItem_Material.instance.getStack(0, 1), GT_MetaItem_Material.instance.getStack(0, 1), GT_MetaItem_Material.instance.getStack(0, 1), GT_MetaItem_Material.instance.getStack(0, 1), GT_MetaItem_Material.instance.getStack(0, 1), GT_MetaItem_Material.instance.getStack(0, 1), GT_MetaItem_Material.instance.getStack(0, 1)});
        GT_ModHandler.addShapelessCraftingRecipe(GT_MetaItem_Material.instance.getStack(1, 1)	, new Object[] {GT_ModHandler.getIC2Item("coin", 1), GT_ModHandler.getIC2Item("coin", 1), GT_ModHandler.getIC2Item("coin", 1), GT_ModHandler.getIC2Item("coin", 1), GT_ModHandler.getIC2Item("coin", 1), GT_ModHandler.getIC2Item("coin", 1), GT_ModHandler.getIC2Item("coin", 1), GT_ModHandler.getIC2Item("coin", 1)});
        GT_ModHandler.addShapelessCraftingRecipe(GT_MetaItem_Material.instance.getStack(2, 1)	, new Object[] {GT_MetaItem_Material.instance.getStack(1, 1), GT_MetaItem_Material.instance.getStack(1, 1), GT_MetaItem_Material.instance.getStack(1, 1), GT_MetaItem_Material.instance.getStack(1, 1), GT_MetaItem_Material.instance.getStack(1, 1), GT_MetaItem_Material.instance.getStack(1, 1), GT_MetaItem_Material.instance.getStack(1, 1), GT_MetaItem_Material.instance.getStack(1, 1)});
        GT_ModHandler.addShapelessCraftingRecipe(GT_MetaItem_Material.instance.getStack(3, 1)	, new Object[] {GT_MetaItem_Material.instance.getStack(2, 1), GT_MetaItem_Material.instance.getStack(2, 1), GT_MetaItem_Material.instance.getStack(2, 1), GT_MetaItem_Material.instance.getStack(2, 1), GT_MetaItem_Material.instance.getStack(2, 1), GT_MetaItem_Material.instance.getStack(2, 1), GT_MetaItem_Material.instance.getStack(2, 1), GT_MetaItem_Material.instance.getStack(2, 1)});
        
        GT_Mod.addElectrolyzerRecipe(new ItemStack(Block.waterStill, 6), 5, GT_MetaItem_Cell.instance.getStack(0, 4), GT_ModHandler.getIC2Item("airCell", 1), null, null, 775, 120);
        GT_Mod.addElectrolyzerRecipe(GT_ModHandler.getIC2Item("electrolyzedWaterCell", 6), 0, GT_MetaItem_Cell.instance.getStack(0, 4), GT_ModHandler.getIC2Item("airCell", 1), null, GT_ModHandler.getIC2Item("cell", 1), 100, 30);
        GT_Mod.addElectrolyzerRecipe(GT_ModHandler.getIC2Item("waterCell", 1), 0, GT_ModHandler.getIC2Item("electrolyzedWaterCell", 1), null, null, null, 128, 128);
        GT_Mod.addElectrolyzerRecipe(new ItemStack(Item.bucketWater, 1), 1, GT_ModHandler.getIC2Item("electrolyzedWaterCell", 1), new ItemStack(Item.bucketEmpty, 1), null, null, 128, 128);
    	GT_Mod.addElectrolyzerRecipe(new ItemStack(Item.dyePowder, 3, 15), 1, GT_MetaItem_Cell.instance.getStack(11, 1), null, null, null, 24, 106);
        
        GT_Mod.addCentrifugeRecipe(new ItemStack(Item.magmaCream, 1), 0, new ItemStack(Item.blazePowder, 1), new ItemStack(Item.slimeBall, 1), null, null, 500);
        GT_Mod.addCentrifugeRecipe(GT_MetaItem_Dust.instance.getStack(63, 2), 0, GT_MetaItem_Dust.instance.getStack(62, 1), GT_ModHandler.getTEItem("slag", 1), null, null, 250);
        
        if (mXYCraft) {
        	GT_Mod.addElectrolyzerRecipe(GT_MetaItem_Dust.instance.getStack(49, 24), 0, GT_MetaItem_Dust.instance.getStack(48, 23), GT_MetaItem_Dust.instance.getStack(36, 1), null, null, 42, 120);
        	GT_Mod.addElectrolyzerRecipe(GT_MetaItem_Dust.instance.getStack(50, 24), 0, GT_MetaItem_Dust.instance.getStack(48, 23), GT_MetaItem_Dust.instance.getStack(22, 1), null, null, 42, 120);
        	GT_Mod.addElectrolyzerRecipe(GT_MetaItem_Dust.instance.getStack(51, 24), 0, GT_MetaItem_Dust.instance.getStack(48, 23), GT_MetaItem_Dust.instance.getStack(32, 1), null, null, 42, 120);
        	GT_Mod.addElectrolyzerRecipe(GT_MetaItem_Dust.instance.getStack(52, 24), 0, GT_MetaItem_Dust.instance.getStack(48, 23), GT_MetaItem_Dust.instance.getStack(33, 1), null, null, 42, 120);
        	GT_Mod.addElectrolyzerRecipe(GT_MetaItem_Dust.instance.getStack(53, 24), 0, GT_MetaItem_Dust.instance.getStack(48, 23), GT_MetaItem_Dust.instance.getStack(35, 1), null, null, 42, 120);
        }
        if (mThorium) {
        	GT_Mod.addCentrifugeRecipe(GT_ModHandler.getIC2Item("nearDepletedUraniumCell", 2), 0, new ItemStack(mItems[48], 1), null, null, GT_ModHandler.getIC2Item("cell", 1), 500);
        }
        if (mCentrifugedUranium) {
        	GT_Mod.addCentrifugeRecipe(GT_ModHandler.getIC2Item("reEnrichedUraniumCell", 8), 0, GT_ModHandler.getIC2Item("nearDepletedUraniumCell", 3 + (GT_Mod.instance.mThorium?0:4) + (GT_Mod.instance.mPlutonium?0:1)), GT_Mod.instance.mPlutonium?GT_Mod.getGregTechItem(51, 1, 0):null, GT_Mod.instance.mThorium?GT_Mod.getGregTechItem(48, 4, 0):null, null, 20000);
    	}
        if (mCentrifugedDirt) {
        	GT_Mod.addCentrifugeRecipe(new ItemStack(Block.dirt, 64), 0, GT_ModHandler.getIC2Item("compressedPlantBall", 2), GT_ModHandler.getIC2Item("plantBall", 2), new ItemStack(Item.clay, 2), new ItemStack(Block.sand, 32), 10000);
        	GT_Mod.addCentrifugeRecipe(new ItemStack(Block.grass, 64), 0, GT_ModHandler.getIC2Item("compressedPlantBall", 2), GT_ModHandler.getIC2Item("plantBall", 4), new ItemStack(Item.clay, 2), new ItemStack(Block.sand, 32), 10000);
        	GT_Mod.addCentrifugeRecipe(new ItemStack(Block.mycelium, 64), 0, new ItemStack(Block.mushroomBrown, 16), new ItemStack(Block.mushroomRed, 16), new ItemStack(Item.clay, 8), new ItemStack(Block.sand, 32), 12500);
        }
        if (mCentrifugedFood) {
        	GT_Mod.addCentrifugeRecipe(new ItemStack(Item.appleGold			,  1, 1), 2, GT_MetaItem_Cell.instance.getStack(9, 2), new ItemStack(Item.ingotGold, 64), null, null, 10000);
        	GT_Mod.addCentrifugeRecipe(new ItemStack(Item.appleGold			,  1, 0), 1, GT_MetaItem_Cell.instance.getStack(9, 1), new ItemStack(Item.goldNugget, 6), null, null, 10000);
        	GT_Mod.addCentrifugeRecipe(new ItemStack(Item.goldenCarrot		,  1, 0), 1, GT_MetaItem_Cell.instance.getStack(9, 1), new ItemStack(Item.goldNugget, 6), null, null, 10000);
        	GT_Mod.addCentrifugeRecipe(new ItemStack(Item.speckledMelon		,  8, 0), 1, GT_MetaItem_Cell.instance.getStack(9, 1), new ItemStack(Item.goldNugget, 6), null, null, 10000);
        	GT_Mod.addCentrifugeRecipe(new ItemStack(Item.appleRed			, 32, 0), 1, GT_MetaItem_Cell.instance.getStack(9, 1), null, null, null,  5000);
        	GT_Mod.addCentrifugeRecipe(new ItemStack(Item.bowlSoup			, 16, 0), 1, GT_MetaItem_Cell.instance.getStack(9, 1), null, null, null,  5000);
        	GT_Mod.addCentrifugeRecipe(new ItemStack(Item.bread				, 64, 0), 1, GT_MetaItem_Cell.instance.getStack(9, 1), null, null, null,  5000);
        	GT_Mod.addCentrifugeRecipe(new ItemStack(Item.porkRaw			, 12, 0), 1, GT_MetaItem_Cell.instance.getStack(9, 1), null, null, null,  5000);
        	GT_Mod.addCentrifugeRecipe(new ItemStack(Item.porkCooked		, 16, 0), 1, GT_MetaItem_Cell.instance.getStack(9, 1), null, null, null,  5000);
        	GT_Mod.addCentrifugeRecipe(new ItemStack(Item.beefRaw			, 12, 0), 1, GT_MetaItem_Cell.instance.getStack(9, 1), null, null, null,  5000);
        	GT_Mod.addCentrifugeRecipe(new ItemStack(Item.beefCooked		, 16, 0), 1, GT_MetaItem_Cell.instance.getStack(9, 1), null, null, null,  5000);
        	GT_Mod.addCentrifugeRecipe(new ItemStack(Item.fishRaw			, 12, 0), 1, GT_MetaItem_Cell.instance.getStack(9, 1), null, null, null,  5000);
        	GT_Mod.addCentrifugeRecipe(new ItemStack(Item.fishCooked		, 16, 0), 1, GT_MetaItem_Cell.instance.getStack(9, 1), null, null, null,  5000);
        	GT_Mod.addCentrifugeRecipe(new ItemStack(Item.chickenRaw		, 12, 0), 1, GT_MetaItem_Cell.instance.getStack(9, 1), null, null, null,  5000);
        	GT_Mod.addCentrifugeRecipe(new ItemStack(Item.chickenCooked		, 16, 0), 1, GT_MetaItem_Cell.instance.getStack(9, 1), null, null, null,  5000);
        	GT_Mod.addCentrifugeRecipe(new ItemStack(Item.melon				, 64, 0), 1, GT_MetaItem_Cell.instance.getStack(9, 1), null, null, null,  5000);
        	GT_Mod.addCentrifugeRecipe(new ItemStack(Block.pumpkin			, 16, 0), 1, GT_MetaItem_Cell.instance.getStack(9, 1), null, null, null,  5000);
        	GT_Mod.addCentrifugeRecipe(new ItemStack(Item.rottenFlesh		, 16, 0), 1, GT_MetaItem_Cell.instance.getStack(9, 1), null, null, null,  5000);
        	GT_Mod.addCentrifugeRecipe(new ItemStack(Item.spiderEye			, 32, 0), 1, GT_MetaItem_Cell.instance.getStack(9, 1), null, null, null,  5000);
        	GT_Mod.addCentrifugeRecipe(new ItemStack(Item.carrot			, 16, 0), 1, GT_MetaItem_Cell.instance.getStack(9, 1), null, null, null,  5000);
        	GT_Mod.addCentrifugeRecipe(new ItemStack(Item.potato			, 16, 0), 1, GT_MetaItem_Cell.instance.getStack(9, 1), null, null, null,  5000);
        	GT_Mod.addCentrifugeRecipe(new ItemStack(Item.poisonousPotato	, 12, 0), 1, GT_MetaItem_Cell.instance.getStack(9, 1), null, null, null,  5000);
        	GT_Mod.addCentrifugeRecipe(new ItemStack(Item.bakedPotato		, 24, 0), 1, GT_MetaItem_Cell.instance.getStack(9, 1), null, null, null,  5000);
        	GT_Mod.addCentrifugeRecipe(new ItemStack(Item.cookie			, 64, 0), 1, GT_MetaItem_Cell.instance.getStack(9, 1), null, null, null,  5000);
        	GT_Mod.addCentrifugeRecipe(new ItemStack(Item.cake				,  8, 0), 1, GT_MetaItem_Cell.instance.getStack(9, 1), null, null, null,  5000);
        }
        if (mCentrifugedMushrooms) {
        	GT_Mod.addCentrifugeRecipe(new ItemStack(Block.mushroomCapBrown	, 64, -1), 6, GT_MetaItem_Cell.instance.getStack(9, 6), null, null, null, 30000);
        	GT_Mod.addCentrifugeRecipe(new ItemStack(Block.mushroomCapRed	, 64, -1), 6, GT_MetaItem_Cell.instance.getStack(9, 6), null, null, null, 30000);
        	GT_Mod.addCentrifugeRecipe(new ItemStack(Block.mushroomBrown	, 32), 1, GT_MetaItem_Cell.instance.getStack(9, 1), null, null, null, 5000);
        	GT_Mod.addCentrifugeRecipe(new ItemStack(Block.mushroomRed		, 32), 1, GT_MetaItem_Cell.instance.getStack(9, 1), null, null, null, 5000);
        	GT_Mod.addCentrifugeRecipe(new ItemStack(Item.netherStalkSeeds	, 32), 1, GT_MetaItem_Cell.instance.getStack(9, 1), null, null, null, 5000);
        	GT_Mod.addCentrifugeRecipe(GT_ModHandler.getIC2Item("terraWart"	, 16), 1, GT_MetaItem_Cell.instance.getStack(9, 1), null, null, null, 5000);
        }
        if (mCentrifugedSugar)
        	GT_Mod.addElectrolyzerRecipe(new ItemStack(Item.sugar, 64), 14, GT_MetaItem_Cell.instance.getStack(8, 4), GT_ModHandler.getIC2Item("waterCell", 10), null, null, 410, 32);
        if (mCentrifugedLapis)
        	GT_Mod.addCentrifugeRecipe(new ItemStack(Item.dyePowder, 64, 4), 0, GT_MetaItem_Dust.instance.getStack(2, 48), GT_MetaItem_Dust.instance.getStack(3, 4), GT_MetaItem_Dust.instance.getStack(4, 4), GT_MetaItem_Dust.instance.getStack(5, 8), 25000);
        if (mCentrifugedBlaze)
        	GT_Mod.addElectrolyzerRecipe(new ItemStack(Item.blazePowder, 8), 0, GT_OreDictUnificator.get("dustCoal", null, 2), GT_MetaItem_Dust.instance.getStack(8, 2), null, null, 600, 25);
        if (mCentrifugedSand)
        	GT_Mod.addElectrolyzerRecipe(new ItemStack(Block.sand, 16), 2, GT_MetaItem_Cell.instance.getStack(7, 1), GT_ModHandler.getIC2Item("airCell", 1), null, null, 1000, 25);
        if (mCentrifugedResin)
        	GT_Mod.addCentrifugeRecipe(GT_ModHandler.getIC2Item("resin", 8), 0, GT_ModHandler.getIC2Item("rubber", 28), GT_ModHandler.getIC2Item("compressedPlantBall", 2), GT_ModHandler.getIC2Item("plantBall", 2), null, 2500);
        if (mCentrifugedSoulsand)
        	GT_Mod.addCentrifugeRecipe(new ItemStack(Block.slowSand, 64), -1, GT_ModHandler.getFuelCan(20000), GT_MetaItem_Dust.instance.getStack(9, 16), GT_OreDictUnificator.get("dustCoal", null, 4), new ItemStack(Block.sand, 40), 10000);
        if (mCentrifugedLava) {
        	GT_Mod.addCentrifugeRecipe(GT_ModHandler.getIC2Item("lavaCell", 64)	, 0, GT_OreDictUnificator.get("ingotElectrum", null, 6), GT_OreDictUnificator.get("ingotCopper", null, 16), GT_OreDictUnificator.get("dustTungsten", null, 1), GT_OreDictUnificator.get("ingotTin", null, Math.min(64, 8 + 256/mTincellCount)), 50000);
        	GT_Mod.addCentrifugeRecipe(new ItemStack(Block.lavaStill, 64)		, 0, GT_OreDictUnificator.get("ingotElectrum", null, 6), GT_OreDictUnificator.get("ingotCopper", null, 16), GT_OreDictUnificator.get("dustTungsten", null, 1), GT_OreDictUnificator.get("ingotTin", null, 8), 40000);
        }
        if (mTinPincher) {
        	GT_Mod.addCentrifugeRecipe(GT_MetaItem_Cell.instance.getStack( 5, 1), -1, GT_ModHandler.getFuelCan(12000), null, null, GT_ModHandler.getIC2Item("cell", 1),  200);
	        GT_Mod.addCentrifugeRecipe(GT_MetaItem_Cell.instance.getStack(12, 1), -1, GT_ModHandler.getFuelCan(12000), null, null, GT_ModHandler.getIC2Item("cell", 1),  200);
        }
        
        ItemStack tRefIron = GT_OreDictUnificator.get("ingotRefinedIron", null, 1), tFlint = new ItemStack(Item.flint , 1), tTinIngot = GT_OreDictUnificator.get("ingotTin", null, 1), tIrOre = GT_ModHandler.getIC2Item("iridiumOre", 1), tReflector = GT_ModHandler.getIC2Item("reactorReflector", 1), tDiamond = new ItemStack(Item.diamond, 1), tGlowstoneDust = new ItemStack(Item.lightStoneDust, 1), tMachine = GT_ModHandler.getIC2Item("machine", 1), tAlloy = GT_ModHandler.getIC2Item("advancedAlloy", 1), tAdvCircuit = GT_ModHandler.getIC2Item("advancedCircuit", 1), tIrPlate = GT_ModHandler.getIC2Item("iridiumPlate", 1), tLapotron = GT_ModHandler.getIC2Item("lapotronCrystal", 1), tMat = GT_ModHandler.getIC2Item("matter", 1), tCoaldust = GT_OreDictUnificator.get("dustCoal", null, 1), tGlassPane = new ItemStack(Block.thinGlass, 1), tGlass = new ItemStack(Block.glass, 1), tCircuit = GT_ModHandler.getIC2Item("electronicCircuit", 1), tGenerator = GT_ModHandler.getIC2Item("generator", 1);
    	
        GT_ModHandler.addCompressionRecipe(tIrOre, GT_MetaItem_Material.instance.getStack(16, 1));
        
        GT_ModHandler.removeRecipe(new ItemStack[] {tIrOre, tAlloy, tIrOre, tAlloy, GT_ModHandler.getIC2Item("industrialDiamond", 1), tAlloy, tIrOre, tAlloy, tIrOre});
        
		GT_ModHandler.addRollingMachineRecipe(GT_MetaItem_Material.instance.getStack(13, 3), new Object[] {"AAA", "MMM", "AAA", 'A', "ingotAluminium", 'M', "dustMagnesium"});
		GT_ModHandler.addRollingMachineRecipe(GT_MetaItem_Material.instance.getStack(13, 3), new Object[] {"AAA", "MMM", "AAA", 'A', "ingotAluminium", 'M', "ingotMagnesium"});
        
        if (null != GT_ModHandler.removeRecipe(new ItemStack[] {tIrOre, tAlloy, tIrOre, tAlloy, tDiamond, tAlloy, tIrOre, tAlloy, tIrOre}))
    		GT_ModHandler.addRollingMachineRecipe(mHardIridiumPlate?GT_MetaItem_Material.instance.getStack(4, 1):GT_ModHandler.getIC2Item("iridiumPlate", 1), new Object[] {"IAI", "ADA", "IAI", 'D', "dustDiamond", 'A', tAlloy, 'I', "ingotIridium"});
        
        addImplosionRecipe(GT_MetaItem_Material.instance.getStack(4, 1), 8, GT_ModHandler.getIC2Item("iridiumPlate", 1), GT_ModHandler.getTEItem("slag", 2));
        
        if (null != GT_ModHandler.removeRecipe(new ItemStack[] {null, tTinIngot, null, tTinIngot, null, tTinIngot, null, tTinIngot, null}))
        	GT_ModHandler.addCraftingRecipe(GT_ModHandler.getIC2Item("cell", mTincellCount), new Object[] {" T ", "T T", " T ", 'T', "ingotTin"});
        
        if (mBlockBreakerNerf) {
        	if (null != (tStack = GT_ModHandler.removeRecipe(new ItemStack[] {new ItemStack(Block.cobblestone, 1), new ItemStack(Item.pickaxeSteel, 1), new ItemStack(Block.cobblestone, 1), new ItemStack(Block.cobblestone, 1), new ItemStack(Block.pistonBase, 1), new ItemStack(Block.cobblestone, 1), new ItemStack(Block.cobblestone, 1), new ItemStack(Item.redstone, 1), new ItemStack(Block.cobblestone, 1)}))) {
        		GT_ModHandler.addCraftingRecipe(tStack, new Object[] {"RGR", "RPR", "RCR" , 'G', "itemGrinder", 'C', "circuitTier04", 'R', "ingotRefinedIron", 'P', new ItemStack(Block.pistonBase, 1)});
        		GT_ModHandler.addCraftingRecipe(tStack, new Object[] {"RGR", "RPR", "RCR" , 'G', "itemGrinder", 'C', "circuitTier04", 'R', "ingotAluminium", 'P', new ItemStack(Block.pistonBase, 1)});
        	}
        }
        
        if (mBeryliumReflector)
        	if (null != GT_ModHandler.removeRecipe(new ItemStack[] {null, tReflector, null, tReflector, GT_ModHandler.getIC2Item("denseCopperPlate", 1), tReflector, null, tReflector, null}))
        		GT_ModHandler.addCraftingRecipe(GT_ModHandler.getIC2Item("reactorReflectorThick", 1), new Object[] {" N ", "NBN", " N ", 'B', "element_1be", 'N', tReflector});
        
        if (mBetterMaceratorRecipe) {
        	if (null != GT_ModHandler.removeRecipe(new ItemStack[] {tFlint, tFlint, tFlint, new ItemStack(Block.cobblestone, 1), tMachine, new ItemStack(Block.cobblestone, 1), null, tCircuit, null})) {
        		GT_ModHandler.addCraftingRecipe(GT_ModHandler.getIC2Item("macerator", 1), new Object[] {"FDF", "DMD", "FAF", 'F', tFlint, 'A', "circuitTier04", 'M', "rawMachineTier01", 'D', "itemDiamond"});
        		GT_ModHandler.addCraftingRecipe(GT_ModHandler.getIC2Item("macerator", 1), new Object[] {"FDF", "AMA", "FAF", 'F', tFlint, 'A', "circuitTier02", 'M', "rawMachineTier01", 'D', "itemGrinder"});
        	}
        }
        
    	if (mBetterWindRecipe) {
        	if (null != GT_ModHandler.removeRecipe(new ItemStack[] {new ItemStack(Item.ingotIron, 1), null, new ItemStack(Item.ingotIron, 1), null, tGenerator, null, new ItemStack(Item.ingotIron, 1), null, new ItemStack(Item.ingotIron, 1)})) {
        		GT_ModHandler.addCraftingRecipe(GT_ModHandler.getIC2Item("windMill", 1), new Object[] {" C ", " G ", "C C", 'C', GT_ModHandler.getIC2Item("carbonPlate", 1), 'G', tGenerator});
        		GT_ModHandler.addCraftingRecipe(GT_ModHandler.getIC2Item("windMill", 1), new Object[] {"C C", " G ", " C ", 'C', GT_ModHandler.getIC2Item("carbonPlate", 1), 'G', tGenerator});
        	}
        }
    	
    	GT_ModHandler.addCraftingRecipe(GT_ModHandler.getIC2Item("windMill", 2), new Object[] {" C ", " G ", " C ", 'C', "plateMagnalium", 'G', tGenerator});
    	GT_ModHandler.addCraftingRecipe(GT_ModHandler.getIC2Item("windMill", 2), new Object[] {"   ", "CGC", "   ", 'C', "plateMagnalium", 'G', tGenerator});
		GT_ModHandler.addCraftingRecipe(GT_ModHandler.getIC2Item("windMill", 1), new Object[] {"A A", " G ", "A A", 'A', "ingotAluminium", 'G', tGenerator});
    	
		if (mBetterWatermillRecipe) {
        	if (null != GT_ModHandler.removeRecipe(new ItemStack[] {new ItemStack(Item.stick, 1), new ItemStack(Block.planks, 1), new ItemStack(Item.stick, 1), new ItemStack(Block.planks, 1), tGenerator, new ItemStack(Block.planks, 1), new ItemStack(Item.stick, 1), new ItemStack(Block.planks, 1), new ItemStack(Item.stick, 1)}))
        		GT_ModHandler.addCraftingRecipe(GT_ModHandler.getIC2Item("waterMill", 2), new Object[] {" A ", "AGA", " A ", 'A', "ingotAluminium", 'G', tGenerator});
		}
		
        if (mBetterSolarRecipe) {
        	if (null != GT_ModHandler.removeRecipe(new ItemStack[] {tCoaldust, tGlass, tCoaldust, tGlass, tCoaldust, tGlass, tCircuit, tGenerator, tCircuit}))
        		GT_ModHandler.addCraftingRecipe(GT_ModHandler.getIC2Item("solarPanel", 1), new Object[] {"GGG", "SPS", "CHC", 'C', "circuitTier02", 'G', tGlassPane, 'H', tGenerator, 'P', GT_ModHandler.getIC2Item("carbonPlate", 1), 'S', "plateSilicon"});
        }
        
        if (mBetterNukeRecipe) {
        	if (null != GT_ModHandler.removeRecipe(new ItemStack[] {new ItemStack(Item.gunpowder, 1), GT_OreDictUnificator.get("ingotUranium", null, 1), new ItemStack(Item.gunpowder, 1), GT_OreDictUnificator.get("ingotUranium", null, 1), new ItemStack(Item.gunpowder, 1), GT_OreDictUnificator.get("ingotUranium", null, 1), new ItemStack(Item.gunpowder, 1), GT_OreDictUnificator.get("ingotUranium", null, 1), new ItemStack(Item.gunpowder, 1)}))
        		GT_ModHandler.addCraftingRecipe(GT_ModHandler.getIC2Item("nuke", 1), new Object[] {"CRC", "NPN", "CRC", 'C', "circuitTier04", 'R', GT_ModHandler.getIC2Item("reEnrichedUraniumCell", 1), 'P', mPlutonium?mItems[51]:"blockUranium", 'N', GT_ModHandler.getIC2Item("reactorReflectorThick", 1)});
        }
        
        if (null != GT_ModHandler.removeRecipe(new ItemStack[] {null, tDiamond, null, tDiamond, GT_ModHandler.getIC2Item("miningDrill", 1), tDiamond, null, null, null}))
        	if (mDiamondDrillTitanium)
        		GT_ModHandler.addCraftingRecipe(GT_ModHandler.getIC2Item("diamondDrill", 1), new Object[] {" D ", "DMD", "TAT", 'M', GT_ModHandler.getIC2Item("miningDrill", 1), 'D', "itemDiamond", 'T', "ingotTitanium", 'A', tAdvCircuit});
        	else
        		GT_ModHandler.addCraftingRecipe(GT_ModHandler.getIC2Item("diamondDrill", 1), new Object[] {" D ", "DMD", "   ", 'M', GT_ModHandler.getIC2Item("miningDrill", 1), 'D', "itemDiamond"});
        
        if (null != GT_ModHandler.removeRecipe(new ItemStack[] {null, tRefIron, null, tRefIron, tCircuit, tRefIron, tRefIron, GT_ModHandler.getIC2Item("reBattery", 1), tRefIron}))
            if (null != GT_ModHandler.removeRecipe(new ItemStack[] {null, tRefIron, null, tRefIron, tCircuit, tRefIron, tRefIron, GT_ModHandler.getIC2Item("chargedReBattery", 1), tRefIron}))
            	GT_ModHandler.addCraftingRecipe(GT_ModHandler.getIC2Item("miningDrill", 1), new Object[] {" S ", "SCS", "SBS", 'C', "circuitTier02", 'B', "10kEUStore", 'S', mSteelETools?"ingotSteel":"ingotRefinedIron"});
        
        if (null != GT_ModHandler.removeRecipe(new ItemStack[] {null, tRefIron, tRefIron, tRefIron, tCircuit, tRefIron, GT_ModHandler.getIC2Item("reBattery", 1), tRefIron, null}))
            if (null != GT_ModHandler.removeRecipe(new ItemStack[] {null, tRefIron, tRefIron, tRefIron, tCircuit, tRefIron, GT_ModHandler.getIC2Item("chargedReBattery", 1), tRefIron, null}))
            	GT_ModHandler.addCraftingRecipe(GT_ModHandler.getIC2Item("chainsaw", 1), new Object[] {" SS", "SCS", "BS ", 'C', "circuitTier02", 'B', "10kEUStore", 'S', mSteelETools?"ingotSteel":"ingotRefinedIron"});
        
        if (null != GT_ModHandler.removeRecipe(new ItemStack[] {tRefIron, tRefIron, null, null, tCircuit, null, null, GT_ModHandler.getIC2Item("reBattery", 1), null}))
            if (null != GT_ModHandler.removeRecipe(new ItemStack[] {tRefIron, tRefIron, null, null, tCircuit, null, null, GT_ModHandler.getIC2Item("chargedReBattery", 1), null}))
            	GT_ModHandler.addCraftingRecipe(GT_ModHandler.getIC2Item("electricHoe", 1), new Object[] {"SS ", " C ", " B ", 'C', "circuitTier02", 'B', "10kEUStore", 'S', mSteelETools?"ingotSteel":"ingotRefinedIron"});
        
        if (null != GT_ModHandler.removeRecipe(new ItemStack[] {null, null, GT_ModHandler.getIC2Item("treetap", 1), null, tCircuit, null, GT_ModHandler.getIC2Item("reBattery", 1), null, null}))
            if (null != GT_ModHandler.removeRecipe(new ItemStack[] {null, null, GT_ModHandler.getIC2Item("treetap", 1), null, tCircuit, null, GT_ModHandler.getIC2Item("chargedReBattery", 1), null, null}))
            	GT_ModHandler.addCraftingRecipe(GT_ModHandler.getIC2Item("electricTreetap", 1), new Object[] {" B ", "SCS", "S  ", 'C', "circuitTier02", 'B', "10kEUStore", 'S', mSteelETools?"ingotSteel":"ingotRefinedIron"});
        
        if (null != GT_ModHandler.removeRecipe(new ItemStack[] {null, null, GT_ModHandler.getIC2Item("wrench", 1), null, tCircuit, null, GT_ModHandler.getIC2Item("reBattery", 1), null, null}))
            if (null != GT_ModHandler.removeRecipe(new ItemStack[] {null, null, GT_ModHandler.getIC2Item("wrench", 1), null, tCircuit, null, GT_ModHandler.getIC2Item("chargedReBattery", 1), null, null}))
            	GT_ModHandler.addCraftingRecipe(GT_ModHandler.getIC2Item("electricWrench", 1), new Object[] {"S S", "SCS", " B ", 'C', "circuitTier02", 'B', "10kEUStore", 'S', mSteelETools?"ingotSteel":"ingotRefinedIron"});
        
        
        if (!mQSuithelmet)		GT_ModHandler.removeRecipe(GT_ModHandler.getIC2Item("quantumHelmet", 1));
        if (!mQSuitplate)		GT_ModHandler.removeRecipe(GT_ModHandler.getIC2Item("quantumBodyarmor", 1));
        if (!mQSuitpants)		GT_ModHandler.removeRecipe(GT_ModHandler.getIC2Item("quantumLeggings", 1));
        if (!mQSuitshoes)		GT_ModHandler.removeRecipe(GT_ModHandler.getIC2Item("quantumBoots", 1));
        if (!mMassfabricator)	GT_ModHandler.removeRecipe(GT_ModHandler.getIC2Item("massFabricator", 1));
    	
        if (!mConfig.mUUM2Rock)			GT_ModHandler.removeRecipe(new ItemStack[] {null, null, null, null, tMat, null, null, null, null});
        if (!mConfig.mUUM2Glass)		GT_ModHandler.removeRecipe(new ItemStack[] {null, tMat, null, tMat, null, tMat, null, tMat, null});
        if (!mConfig.mUUM2Grass)		GT_ModHandler.removeRecipe(new ItemStack[] {null, null, null, tMat, null, null, tMat, null, null});
        if (!mConfig.mUUM2Moss)			GT_ModHandler.removeRecipe(new ItemStack[] {null, null, null, null, tMat, null, tMat, null, tMat});
        if (!mConfig.mUUM2Sandstone)	GT_ModHandler.removeRecipe(new ItemStack[] {null, null, null, null, null, tMat, null, tMat, null});
        if (!mConfig.mUUM2Snowblock)	GT_ModHandler.removeRecipe(new ItemStack[] {tMat, null, tMat, null, null, null, null, null, null});
        if (!mConfig.mUUM2Water)		GT_ModHandler.removeRecipe(new ItemStack[] {null, null, null, null, tMat, null, null, tMat, null});
        if (!mConfig.mUUM2Lava)			GT_ModHandler.removeRecipe(new ItemStack[] {null, tMat, null, null, tMat, null, null, tMat, null});
        if (!mConfig.mUUM2Iron)			GT_ModHandler.removeRecipe(new ItemStack[] {tMat, null, tMat, null, tMat, null, tMat, null, tMat});
        if (!mConfig.mUUM2Gold)			GT_ModHandler.removeRecipe(new ItemStack[] {null, tMat, null, tMat, tMat, tMat, null, tMat, null});
        if (!mConfig.mUUM2Obsidian)		GT_ModHandler.removeRecipe(new ItemStack[] {tMat, null, tMat, tMat, null, tMat, null, null, null});
        if (!mConfig.mUUM2Netherrack)	GT_ModHandler.removeRecipe(new ItemStack[] {null, null, tMat, null, tMat, null, tMat, null, null});
        if (!mConfig.mUUM2Glowstone)	GT_ModHandler.removeRecipe(new ItemStack[] {null, tMat, null, tMat, null, tMat, tMat, tMat, tMat});
        if (!mConfig.mUUM2Wood)			GT_ModHandler.removeRecipe(new ItemStack[] {null, tMat, null, null, null, null, null, null, null});
        if (!mConfig.mUUM2Cactus)		GT_ModHandler.removeRecipe(new ItemStack[] {null, tMat, null, tMat, tMat, tMat, tMat, null, tMat});
        if (!mConfig.mUUM2Vines)		GT_ModHandler.removeRecipe(new ItemStack[] {tMat, null, null, tMat, null, null, tMat, null, null});
        if (!mConfig.mUUM2Wool)			GT_ModHandler.removeRecipe(new ItemStack[] {tMat, null, tMat, null, null, null, null, tMat, null});
        if (!mConfig.mUUM2Coal)			GT_ModHandler.removeRecipe(new ItemStack[] {null, null, tMat, tMat, null, null, null, null, tMat});
        if (!mConfig.mUUM2Diamonds)		GT_ModHandler.removeRecipe(new ItemStack[] {tMat, tMat, tMat, tMat, tMat, tMat, tMat, tMat, tMat});
        if (!mConfig.mUUM2Redstone)		GT_ModHandler.removeRecipe(new ItemStack[] {null, null, null, null, tMat, null, tMat, tMat, tMat});
        if (!mConfig.mUUM2Lapis)		GT_ModHandler.removeRecipe(new ItemStack[] {null, tMat, null, null, tMat, null, null, tMat, tMat});
        if (!mConfig.mUUM2Feathers)		GT_ModHandler.removeRecipe(new ItemStack[] {null, tMat, null, null, tMat, null, tMat, null, tMat});
        if (!mConfig.mUUM2Snowballs)	GT_ModHandler.removeRecipe(new ItemStack[] {null, null, null, null, null, null, tMat, tMat, tMat});
        if (!mConfig.mUUM2Gunpowder)	GT_ModHandler.removeRecipe(new ItemStack[] {tMat, tMat, tMat, tMat, null, null, tMat, tMat, tMat});
        if (!mConfig.mUUM2Clay)			GT_ModHandler.removeRecipe(new ItemStack[] {tMat, tMat, null, tMat, null, null, tMat, tMat, null});
        if (!mConfig.mUUM2Cocoa)		GT_ModHandler.removeRecipe(new ItemStack[] {tMat, tMat, null, null, null, tMat, tMat, tMat, null});
        if (!mConfig.mUUM2Ink)			GT_ModHandler.removeRecipe(new ItemStack[] {null, tMat, tMat, null, tMat, tMat, null, tMat, null});
        if (!mConfig.mUUM2Sugarcane)	GT_ModHandler.removeRecipe(new ItemStack[] {tMat, null, tMat, tMat, null, tMat, tMat, null, tMat});
        if (!mConfig.mUUM2Flint)		GT_ModHandler.removeRecipe(new ItemStack[] {null, tMat, null, tMat, tMat, null, tMat, tMat, null});
        if (!mConfig.mUUM2Bone)			GT_ModHandler.removeRecipe(new ItemStack[] {tMat, null, null, tMat, tMat, null, tMat, null, null});
        if (!mConfig.mUUM2Resin)		GT_ModHandler.removeRecipe(new ItemStack[] {tMat, null, tMat, null, null, null, tMat, null, tMat});
        if (!mConfig.mUUM2Iridium)		GT_ModHandler.removeRecipe(new ItemStack[] {tMat, tMat, tMat, null, tMat, null, tMat, tMat, tMat});
        if (!mConfig.mUUM2Mycelium)		GT_ModHandler.removeRecipe(new ItemStack[] {null, null, null, tMat, null, tMat, tMat, tMat, tMat});
        if (!mConfig.mUUM2Chiseled)		GT_ModHandler.removeRecipe(new ItemStack[] {tMat, tMat, null, tMat, tMat, null, tMat, null, null});
        if (!mConfig.mUUM2Copper)		GT_ModHandler.removeRecipe(new ItemStack[] {null, null, tMat, tMat, null, tMat, null, null, null});
        if (!mConfig.mUUM2Tin)			GT_ModHandler.removeRecipe(new ItemStack[] {null, null, null, tMat, null, tMat, null, null, tMat});
        
        
		Item tItem = null, tNewItem = null;
		String tName = "";
		
		addJackHammerMinableBlock(Block.cobblestone);
		addJackHammerMinableBlock(Block.stone);
		addJackHammerMinableBlock(Block.stoneBrick);
		addJackHammerMinableBlock(Block.sandStone);
		addJackHammerMinableBlock(Block.cobblestoneMossy);
		addJackHammerMinableBlock(Block.netherrack);
		addJackHammerMinableBlock(Block.glowStone);
		addJackHammerMinableBlock(Block.netherBrick);
		addJackHammerMinableBlock(Block.whiteStone);
		
		if (mTEMachine) {
			GT_ModHandler.registerOre("rawMachineTier01", GT_ModHandler.getTEItem("machineFrame", 1));
		}
		
		for (int i = 0; i < Item.itemsList.length; i++) if ((tItem = Item.itemsList[i]) != null && (tName = tItem.getItemName()) != null) {
			if (tItem.getHasSubtypes()) {
				String tNameMeta = "";
				for (int j = 0; j < 16 /*|| (tNameMeta != null && !tNameMeta.equals(""))*/; j++) {
					try {
					tNameMeta = tItem.getItemNameIS(new ItemStack(tItem, 1, j));
		    		if (tNameMeta != null && !tNameMeta.equals("")) {
		    			
		    		}
					} catch (Throwable e) {}
				}
			}
			
			if (tName.equals("item.reactorReflector"))			{Item.itemsList[i] = null; tNewItem = new GT_NeutronReflector_Item	(tItem.shiftedIndex-256, 10000);}
			if (tName.equals("item.reactorReflectorThick"))		{Item.itemsList[i] = null; tNewItem = new GT_NeutronReflector_Item	(tItem.shiftedIndex-256, 40000);}
			if (tName.equals("item.reactorUraniumSimple"))		{Item.itemsList[i] = null; tNewItem = new GT_RadioactiveCell_Item	(tItem.shiftedIndex-256, 10000, 1, 5, GT_ModHandler.getIC2Item("nearDepletedUraniumCell", 1));}
			if (tName.equals("item.reactorUraniumDual"))		{Item.itemsList[i] = null; tNewItem = new GT_RadioactiveCell_Item	(tItem.shiftedIndex-256, 10000, 2, 5, GT_ModHandler.getIC2Item("nearDepletedUraniumCell", 1));}
			if (tName.equals("item.reactorUraniumQuad"))		{Item.itemsList[i] = null; tNewItem = new GT_RadioactiveCell_Item	(tItem.shiftedIndex-256, 10000, 4, 5, GT_ModHandler.getIC2Item("nearDepletedUraniumCell", 1));}
			
			if (mQOverload) {
			//	if (tName.equals("item.itemArmorQuantumBoots"))			{Item.itemsList[i] = null; tNewItem = new GT_EnergyArmor_Item		(tItem.shiftedIndex-256, 1000000, 1000, 3, 900,  2 | 128, 1.0, false, 3, ((ItemArmor)tItem).renderIndex);}
			//	if (tName.equals("item.itemArmorQuantumLegs"))			{Item.itemsList[i] = null; tNewItem = new GT_EnergyArmor_Item		(tItem.shiftedIndex-256, 1000000, 1000, 3, 900,  256, 1.0, false, 2, ((ItemArmor)tItem).renderIndex);}
			//	if (!mQInvincible || !mQFire)
			//	if (tName.equals("item.itemArmorQuantumChestplate"))	{Item.itemsList[i] = null; tNewItem = new GT_EnergyArmor_Item		(tItem.shiftedIndex-256, 1000000, 1000, 3, 900, (mQFire?64:0), 0.8 + (mQInvincible?0.3:0.0), false, 1, ((ItemArmor)tItem).renderIndex);}
			//	if (mQLight || !mQAir || !mQFood || !mQCure)
			//	if (tName.equals("item.itemArmorQuantumHelmet"))		{Item.itemsList[i] = null; tNewItem = new GT_EnergyArmor_Item		(tItem.shiftedIndex-256, 1000000, 1000, 3, 900, (mQAir?1:0) | (mQFood?4:0) | (mQCure?8:0) | (mQLight?16:0), 1.0, false, 0, ((ItemArmor)tItem).renderIndex);}
			}
			
			if (tName.equals("tile.extrabiomes.crackedsand") && tItem.shiftedIndex < 4096) {
				addJackHammerMinableBlock(Block.blocksList[tItem.shiftedIndex]);
			}
			
			if (tName.equals("tile.extrabiomes.redrock") && tItem.shiftedIndex < 4096) {
				addJackHammerMinableBlock(Block.blocksList[tItem.shiftedIndex]);
				GT_ModHandler.registerOre("stoneRedRock", new ItemStack(tItem, 1,  0));
				GT_ModHandler.registerOre("stoneRedRock", new ItemStack(tItem, 1,  1));
				GT_ModHandler.registerOre("stoneRedRock", new ItemStack(tItem, 1,  2));
			}
			
			if (tName.equals("tile.rpstone") && tItem.shiftedIndex < 4096) {
				addJackHammerMinableBlock(Block.blocksList[tItem.shiftedIndex]);
				GT_ModHandler.registerOre("stoneMarble", new ItemStack(tItem, 1, 0));
				GT_ModHandler.registerOre("stoneBasalt", new ItemStack(tItem, 1, 1));
				GT_ModHandler.registerOre("stoneMarble", new ItemStack(tItem, 1, 2));
				GT_ModHandler.registerOre("stoneBasalt", new ItemStack(tItem, 1, 3));
				GT_ModHandler.registerOre("stoneBasalt", new ItemStack(tItem, 1, 4));
				GT_ModHandler.registerOre("stoneBasalt", new ItemStack(tItem, 1, 5));
				GT_ModHandler.registerOre("stoneBasalt", new ItemStack(tItem, 1, 6));
			}
			
			if (tName.equals("tile.enderchest")) {
				GT_ModHandler.registerOre("enderChest", new ItemStack(tItem, 1, -1));
			}
			
			if (tName.equals("tile.autoWorkbenchBlock")) {
				GT_ModHandler.registerOre("craftingtable", new ItemStack(tItem, 1, 0));
			}
			
			if (mForestryCasings && tName.equals("item.sturdyMachine")) {
				GT_ModHandler.registerOre("rawMachineTier01", new ItemStack(tItem, 1, 0));
				if (mConfig.mSM_Bronze) GT_ModHandler.addPulverisationRecipe(	new ItemStack(tItem, 1, 0), GT_OreDictUnificator.get("dustBronze" , null, 8));
	        	if (mConfig.mSS_Bronze) GT_ModHandler.addSmeltingRecipe(		new ItemStack(tItem, 1, 0), GT_OreDictUnificator.get("ingotBronze", null, 8));
			}
			
			if (mForestryCasings && tName.equals("item.hardenedMachine")) {
				GT_ModHandler.registerOre("rawMachineTier03", new ItemStack(tItem, 1, 0));
			}
			
			if (mRCCircuits) if (tName.equals("item.controllerCircuit")) {
				GT_ModHandler.registerOre("circuitTier02", new ItemStack(tItem, 1, 0));
			}
			
			if (mRCCircuits) if (tName.equals("item.receiverCircuit")) {
				GT_ModHandler.registerOre("circuitTier02", new ItemStack(tItem, 1, 0));
			}
			
			if (mCircuitBoards) if (tName.equals("item.chipsets")) {
				GT_ModHandler.registerOre("circuitTier01", new ItemStack(tItem, 1, 0));
				GT_ModHandler.registerOre("circuitTier02", new ItemStack(tItem, 1, 1));
				GT_ModHandler.registerOre("circuitTier03", new ItemStack(tItem, 1, 2));
			}
			
			if (mThermionicTubes) if (tName.equals("item.thermionicTubes")) {
				GT_ModHandler.registerOre("circuitTier01", new ItemStack(tItem, 1, 0));
				GT_ModHandler.registerOre("circuitTier01", new ItemStack(tItem, 1, 1));
				GT_ModHandler.registerOre("circuitTier01", new ItemStack(tItem, 1, 2));
				GT_ModHandler.registerOre("circuitTier01", new ItemStack(tItem, 1, 3));
				GT_ModHandler.registerOre("circuitTier02", new ItemStack(tItem, 1, 4));
				GT_ModHandler.registerOre("circuitTier03", new ItemStack(tItem, 1, 5));
			}
			
			if (mBCCircuits) if (tName.equals("item.redstoneChipset")) {
				GT_ModHandler.registerOre("circuitTier00", new ItemStack(tItem, 1, 0));
				GT_ModHandler.registerOre("circuitTier01", new ItemStack(tItem, 1, 1));
				GT_ModHandler.registerOre("circuitTier02", new ItemStack(tItem, 1, 2));
				GT_ModHandler.registerOre("circuitTier03", new ItemStack(tItem, 1, 3));
				GT_ModHandler.registerOre("circuitTier04", new ItemStack(tItem, 1, 4));
			}
			
			if (mBCGates) if (tName.equals("item.pipeGate")) {
				GT_ModHandler.registerOre("circuitTier01", new ItemStack(tItem, 1, 0));
				GT_ModHandler.registerOre("circuitTier02", new ItemStack(tItem, 1, 1));
				GT_ModHandler.registerOre("circuitTier02", new ItemStack(tItem, 1, 2));
				GT_ModHandler.registerOre("circuitTier03", new ItemStack(tItem, 1, 3));
				GT_ModHandler.registerOre("circuitTier03", new ItemStack(tItem, 1, 4));
				GT_ModHandler.registerOre("circuitTier04", new ItemStack(tItem, 1, 5));
				GT_ModHandler.registerOre("circuitTier04", new ItemStack(tItem, 1, 6));
			}
			
			if (mBCGates) if (tName.equals("item.pipeGateAutarchic")) {
				GT_ModHandler.registerOre("circuitTier02", new ItemStack(tItem, 1, 0));
				GT_ModHandler.registerOre("circuitTier03", new ItemStack(tItem, 1, 1));
				GT_ModHandler.registerOre("circuitTier03", new ItemStack(tItem, 1, 2));
				GT_ModHandler.registerOre("circuitTier04", new ItemStack(tItem, 1, 3));
				GT_ModHandler.registerOre("circuitTier04", new ItemStack(tItem, 1, 4));
				GT_ModHandler.registerOre("circuitTier05", new ItemStack(tItem, 1, 5));
				GT_ModHandler.registerOre("circuitTier05", new ItemStack(tItem, 1, 6));
			}
			
			if (tName.equals("item.woodenGearItem"))	GT_ModHandler.mBCWoodGear		= new ItemStack(tItem, 1);
			if (tName.equals("item.stoneGearItem"))		GT_ModHandler.mBCStoneGear		= new ItemStack(tItem, 1);
			if (tName.equals("item.ironGearItem"))		GT_ModHandler.mBCIronGear		= new ItemStack(tItem, 1);
			if (tName.equals("item.goldGearItem"))		GT_ModHandler.mBCGoldGear		= new ItemStack(tItem, 1);
			if (tName.equals("item.diamondGearItem"))	GT_ModHandler.mBCDiamondGear	= new ItemStack(tItem, 1);
			
			if (mCentrifugedLava) {
				if (tName.equals("item.canLava"))			GT_Mod.addCentrifugeRecipe(new ItemStack(tItem, 64), 0, GT_OreDictUnificator.get("ingotElectrum", null, 6), GT_OreDictUnificator.get("ingotCopper", null, 16), GT_OreDictUnificator.get("dustTungsten", null, 1), GT_OreDictUnificator.get("ingotTin", null, 24), 50000);
				if (tName.equals("item.refractoryLava"))	GT_Mod.addCentrifugeRecipe(new ItemStack(tItem, 64), 0, GT_OreDictUnificator.get("ingotElectrum", null, 6), GT_OreDictUnificator.get("ingotCopper", null, 16), GT_OreDictUnificator.get("dustTungsten", null, 1), GT_OreDictUnificator.get("ingotTin", null,  8), 40000);
			}
			
			if (tNewItem != null) {
				if (aEvent.getSide() == Side.CLIENT) {
					tNewItem.setCreativeTab(tItem.getCreativeTab());
					tNewItem.setTextureFile(tItem.getTextureFile());
					tNewItem.setIconIndex(tItem.getIconFromDamage(0));
				}
				tNewItem.setItemName(tName.replaceFirst("item.", ""));
				tNewItem.setMaxStackSize(tItem.getItemStackLimit());
				tNewItem = null;
			}
		}
		
		if (mBetterQuarryRecipe && GT_ModHandler.mBCDiamondGear != null) {
			GT_ModHandler.addCraftingRecipe(GT_ModHandler.removeRecipe(new ItemStack[] {GT_ModHandler.mBCIronGear, new ItemStack(Item.redstone, 1), GT_ModHandler.mBCIronGear, GT_ModHandler.mBCGoldGear, GT_ModHandler.mBCIronGear, GT_ModHandler.mBCGoldGear, GT_ModHandler.mBCDiamondGear, new ItemStack(Item.pickaxeDiamond, 1), GT_ModHandler.mBCDiamondGear}), new Object[] {"ICI", "GIG", "DPD", 'C', "circuitTier04", 'D', GT_ModHandler.mBCDiamondGear, 'G', GT_ModHandler.mBCGoldGear, 'I', GT_ModHandler.mBCIronGear, 'P', new ItemStack(GT_ModHandler.getIC2Item("diamondDrill", 1).getItem(), 1, -1)});
		}
		
		// Activate OreDictionary Handler now
        sOreDict.activateHandler();
		
    	// IC2-OD/OV-Scanner
    	if (mLavaMiner) GT_ModHandler.addValuableOre(Block.lavaStill.blockID, 0, 1);
    	GT_ModHandler.addValuableOre(Block.glowStone.blockID, 0, 1);
    	GT_ModHandler.addValuableOre(Block.slowSand.blockID, 0, 1);
    	
    	// Standardset of the ComputerCube-Database
    	GT_ComputercubeDescription.addStandardDescriptions();
    }
    
    @ServerStarting
    public void start(FMLServerStartingEvent aEvent) {
    	if (mDoNotInit) return;
    	gregtechproxy.serverStart();
    	mUniverse = null;
    	sLangFile.save();
    	GT_TileEntity_IDSU.sEnergyList = null;
    }
    
    @ServerStopping
    public void stop(FMLServerStoppingEvent aEvent) {
    	if (mDoNotInit) return;
    	writeIDSUData();
    	mUniverse = null;
    	sLangFile.save();
    	mConfig.save();
    	GT_TileEntity_IDSU.sEnergyList = null;
		try {
		if (mDebug || GT_Log.out != System.out) {
			if (mDebug) System.out.println("BEGIN GregTech-Debug");
			GT_Log.out.println("*");
			GT_Log.out.println("Printing List of all registered Objects inside the OreDictionary:");
			GT_Log.out.println("*"); GT_Log.out.println("*"); GT_Log.out.println("*");
			
		    String[] tOres = OreDictionary.getOreNames();
		    for (int i = 0; i < tOres.length; i++) {
		    	GT_Log.out.println(tOres[i]);
		    }
		    
			GT_Log.out.println("*"); GT_Log.out.println("*"); GT_Log.out.println("*");
			GT_Log.out.println("Outputting all the Names inside the Itemslist, this List can become very long");
			GT_Log.out.println("*"); GT_Log.out.println("*"); GT_Log.out.println("*");
			
			for (int i = 0; i < Item.itemsList.length; i++) {
		    	if (Item.itemsList[i] != null) {
		    		GT_Log.out.println(Item.itemsList[i].getItemName());
					if (Item.itemsList[i].getHasSubtypes()) {
						String tName = "";
						for (int j = 0; j < 16 /*|| (tName != null && !tName.equals(""))*/; j++) {
							try {
							tName = Item.itemsList[i].getItemNameIS(new ItemStack(Item.itemsList[i], 1, j));
				    		if (tName != null && !tName.equals(""))
				    			GT_Log.out.println(j + ": " + Item.itemsList[i].getItemName());
							} catch (Throwable e) {}
						}
					}
		    	}
		    }
			
			if (mDebug) {
				System.out.println("*"); System.out.println("*"); System.out.println("*");
				System.out.println("Outputting all the Names registered by Railcraft");
				System.out.println("*"); System.out.println("*"); System.out.println("*");
				
				try {
					railcraft.common.api.core.items.ItemRegistry.printItemTags();
				} catch (Throwable e) {}
				
				System.out.println("*"); System.out.println("*"); System.out.println("*");
				System.out.println("Outputting all the Names registered by Thermal Expansion");
				System.out.println("*"); System.out.println("*"); System.out.println("*");
				
				try {
					thermalexpansion.api.core.ItemRegistry.printItemNames();
				} catch (Throwable e) {}
			}
			
			GT_Log.out.println("*"); GT_Log.out.println("*"); GT_Log.out.println("*");
			GT_Log.out.println("Outputting all the Names inside the Biomeslist");
			GT_Log.out.println("*"); GT_Log.out.println("*"); GT_Log.out.println("*");
			
			for (int i = 0; i < BiomeGenBase.biomeList.length; i++) {
		    	if (BiomeGenBase.biomeList[i] != null)
		    		GT_Log.out.println(BiomeGenBase.biomeList[i].biomeID + " = " + BiomeGenBase.biomeList[i].biomeName);
		    }
			
			GT_Log.out.println("*"); GT_Log.out.println("*"); GT_Log.out.println("*");
			GT_Log.out.println("END GregTech-Debug");
			GT_Log.out.println("*"); GT_Log.out.println("*"); GT_Log.out.println("*");
    	}
		} catch(Throwable e) {}
    }
    
	public static void addFusionReactorRecipe(ItemStack aInput1, ItemStack aInput2, ItemStack aOutput, int aFusionDurationInTicks, int aFusionEnergyPerTick, int aEnergyNeededForStartingFusion) {
		if (aInput1 == null) return;
		if (aInput1.getItemDamage() >= 0) if (!GT_Mod.mConfig.mConfigFileAdvRecipes.get("fusion", aInput1.getItemName(), true).getBoolean(true)) return;
		new GT_Recipe(aInput1, aInput2, aOutput, aFusionDurationInTicks, aFusionEnergyPerTick, aEnergyNeededForStartingFusion);
	}
	
	public static void addCentrifugeRecipe(ItemStack aInput1, int aInput2, ItemStack aOutput1, ItemStack aOutput2, ItemStack aOutput3, ItemStack aOutput4, int aDuration) {
		if (aInput1 == null) return;
		if (aInput1.getItemDamage() >= 0) if (!GT_Mod.mConfig.mConfigFileAdvRecipes.get("centrifuge", aInput1.getItemName(), true).getBoolean(true)) return;
		new GT_Recipe(aInput1, aInput2>0?GT_ModHandler.getIC2Item("cell", aInput2):aInput2<0?GT_ModHandler.getIC2Item("fuelCan", -aInput2):null, aOutput1, aOutput2, aOutput3, aOutput4, aDuration);
	}
	
	public static void addElectrolyzerRecipe(ItemStack aInput1, int aInput2, ItemStack aOutput1, ItemStack aOutput2, ItemStack aOutput3, ItemStack aOutput4, int aDuration, int aEUt) {
		if (aInput1 == null) return;
		if (aInput1.getItemDamage() >= 0) if (!GT_Mod.mConfig.mConfigFileAdvRecipes.get("electrolyzer", aInput1.getItemName(), true).getBoolean(true)) return;
		new GT_Recipe(aInput1, aInput2>0?GT_ModHandler.getIC2Item("cell", aInput2):aInput2<0?GT_ModHandler.getIC2Item("fuelCan", -aInput2):null, aOutput1, aOutput2, aOutput3, aOutput4, aDuration, aEUt);
	}
	
	public static void addBlastRecipe(ItemStack aInput1, ItemStack aInput2, ItemStack aOutput1, ItemStack aOutput2, int aDuration, int aEUt, int aLevel) {
		if (aInput1 == null) return;
		if (aInput1.getItemDamage() >= 0) if (!GT_Mod.mConfig.mConfigFileAdvRecipes.get("blastfurnace", aInput1.getItemName(), true).getBoolean(true)) return;
		new GT_Recipe(aInput1, aInput2, aOutput1, aOutput2, aDuration, aEUt, aLevel);
	}
	
	public static void addImplosionRecipe(ItemStack aInput1, int aInput2, ItemStack aOutput1, ItemStack aOutput2) {
		if (aInput1 == null) return;
		if (aInput1.getItemDamage() >= 0) if (!GT_Mod.mConfig.mConfigFileAdvRecipes.get("implosion", aInput1.getItemName(), true).getBoolean(true)) return;
		new GT_Recipe(aInput1, aInput2, aOutput1, aOutput2);
	}
	
	public static void addGrinderRecipe(ItemStack aInput1, int aInput2, ItemStack aOutput1, ItemStack aOutput2, ItemStack aOutput3, ItemStack aOutput4) {
		if (aInput1 == null) return;
		if (aInput1.getItemDamage() >= 0) if (!GT_Mod.mConfig.mConfigFileAdvRecipes.get("grinder", aInput1.getItemName(), true).getBoolean(true)) return;
		new GT_Recipe(aInput1, aInput2>0?GT_ModHandler.getIC2Item("cell", aInput2):aInput2<0?GT_ModHandler.getIC2Item("waterCell", -aInput2):null, aOutput1, aOutput2, aOutput3, aOutput4);
		if (aInput2 == -1 && aOutput4 != null && aOutput4.isItemEqual(GT_ModHandler.getIC2Item("cell", 1)) && aOutput4.stackSize == 1) {
			new GT_Recipe(aInput1, new ItemStack(Item.bucketWater, 1), aOutput1, aOutput2, aOutput3, new ItemStack(Item.bucketEmpty, 1));
		}
	}
	
	public static void addGrinderRecipe(ItemStack aInput1, ItemStack aInput2, ItemStack aOutput1, ItemStack aOutput2, ItemStack aOutput3, ItemStack aOutput4) {
		if (aInput1 == null) return;
		if (aInput1.getItemDamage() >= 0) if (!GT_Mod.mConfig.mConfigFileAdvRecipes.get("grinder", aInput1.getItemName(), true).getBoolean(true)) return;
		new GT_Recipe(aInput1, aInput2, aOutput1, aOutput2, aOutput3, aOutput4);
	}
	
	public static void addSawmillRecipe(ItemStack aInput1, int aInput2, ItemStack aOutput1, ItemStack aOutput2, ItemStack aOutput3) {
		if (aInput1 == null) return;
		if (aInput1.getItemDamage() >= 0) if (!GT_Mod.mConfig.mConfigFileAdvRecipes.get("sawmill", aInput1.getItemName(), true).getBoolean(true)) return;
		new GT_Recipe(aInput1, aInput2>0?GT_ModHandler.getIC2Item("cell", aInput2):aInput2<0?GT_ModHandler.getIC2Item("waterCell", -aInput2):null, aOutput1, aOutput2, aOutput3);
		if (aInput2 == -1 && aOutput3 != null && aOutput3.isItemEqual(GT_ModHandler.getIC2Item("cell", 1)) && aOutput3.stackSize == 1) {
			new GT_Recipe(aInput1, new ItemStack(Item.bucketWater, 1), aOutput1, aOutput2, new ItemStack(Item.bucketEmpty, 1));
		}
	}
	
	public static void addSawmillRecipe(ItemStack aInput1, ItemStack aInput2, ItemStack aOutput1, ItemStack aOutput2, ItemStack aOutput3) {
		if (aInput1 == null) return;
		if (aInput1.getItemDamage() >= 0) if (!GT_Mod.mConfig.mConfigFileAdvRecipes.get("sawmill", aInput1.getItemName(), true).getBoolean(true)) return;
		new GT_Recipe(aInput1, aInput2, aOutput1, aOutput2, aOutput3);
	}

	public static void addFuel(ItemStack aInput1, ItemStack aOutput1, int aEU, int aType) {
		if (aInput1 == null) return;
		if (aInput1.getItemDamage() >= 0) if (!GT_Mod.mConfig.mConfigFileAdvRecipes.get("fuel_"+aType, aInput1.getItemName(), true).getBoolean(true)) return;
		new GT_Recipe(aInput1, aOutput1, aEU, aType);
	}
	
	public static void addJackHammerMinableBlock(Block aBlock) {
		if (aBlock != null) GT_Jackhammer_Item.mineableBlocks.add(aBlock);
	}
	
	public static void addSonictronSound(ItemStack aItemStack, String aSoundName) {
		if (aItemStack == null || aSoundName == null || aSoundName.equals("")) return;
		GT_Mod.mSoundItems.add(aItemStack);
		GT_Mod.mSoundNames.add(aSoundName);
		if (aSoundName.startsWith("note.")) {
			GT_Mod.mSoundCounts.add(25);
		} else {
			GT_Mod.mSoundCounts.add(1);
		}
	}
	
	public static void addComputercubeDescriptionSet(ItemStack[] aItemStack, String[] aText) {
		new GT_ComputercubeDescription(aText, aItemStack);
	}

    public static ItemStack getGregTechBlock(int aIndex, int aAmount, int aMeta) {
    	if (GT_Mod.instance != null) 
    		if (aIndex < GT_Mod.instance.mBlocks.length && aIndex >= 0) 
	    		if (GT_Mod.instance.mBlocks[aIndex] != null) 
	    			return new ItemStack(GT_Mod.instance.mBlocks[aIndex], aAmount, aMeta);
	    		else
	    			System.err.println("GT_API: This Blockindex wasn't initialized, you either called it before the Init-Phase (Only Post-Init or later will work), or this Item doesnt exist now");
    		else
    			System.err.println("GT_API: The Index " + aIndex + " is not part of my Itemrange");
    	else
			System.err.println("GT_API: Why did you hack the API? Was that check if my Mod is loaded too much?");
    	return null;
    }
    
    public static ItemStack getGregTechItem(int aIndex, int aAmount, int aDamage) {
    	if (GT_Mod.instance != null) 
    		if (aIndex < GT_Mod.instance.mItems.length && aIndex >= 0) 
	    		if (GT_Mod.instance.mItems[aIndex] != null) {
	    			if (GT_Mod.instance.mItems[aIndex] instanceof GT_MetaItem_Abstract) {
	    				Field tField = GT_Utility.getPublicField(GT_Mod.instance.mItems[aIndex], "instance");
	    				if (tField != null) {
	    					try {
	    						ItemStack rStack = null;
	    						rStack = ((GT_MetaItem_Abstract)tField.get(GT_Mod.instance.mItems[aIndex])).getStack(aDamage, aAmount);
	    						if (rStack != null) {
	    							return rStack;
	    						}
	    					} catch(Throwable e) {}
	    				}
	    			}
	    			return new ItemStack(GT_Mod.instance.mItems[aIndex], aAmount, aDamage);
	    		}
	    		else
	    			System.err.println("GT_API: This Itemindex wasn't initialized, you either called it before the Init-Phase (Only Post-Init or later will work), or this Item doesnt exist now");
    		else
    			System.err.println("GT_API: The Index " + aIndex + " is not part of my Itemrange");
    	else
			System.err.println("GT_API: Why did you hack the API? Was that check if my Mod is loaded too much?");
    	return null;
    }
	
	public static int getCapsuleCellContainerCount(ItemStack aStack) {
		return GT_ModHandler.getCapsuleCellContainerCount(aStack);
	}
    
	
    public File getSaveDirectory() {
    	if (mUniverse == null) return null;
        SaveHandler tSaveHandler = (SaveHandler)mUniverse.getSaveHandler();
        File rFile = null;
        Field[] tFields = SaveHandler.class.getDeclaredFields();
        for (int i = 0; i < tFields.length; ++i) {
            if (tFields[i].getType() == File.class)  {
            	tFields[i].setAccessible(true);
                try {
                    File tFile = (File)tFields[i].get(tSaveHandler);
                    if (rFile == null || rFile.getParentFile() == tFile) {
                    	rFile = tFile;
                    }
                } catch (Exception e) {
                	
                }
            }
        }
        return rFile;
    }
    
    public void writeIDSUData() {
    	if (mUniverse != null && GT_TileEntity_IDSU.sEnergyList != null && !mUniverse.isRemote) {
			try {
		        File tDirectory = getSaveDirectory();
		        if (tDirectory != null) {
			        NBTTagCompound tNBT = new NBTTagCompound();
		            NBTTagList tList = new NBTTagList();
		            Iterator<Entry<Integer, Integer>> tIterator = GT_TileEntity_IDSU.sEnergyList.entrySet().iterator();
		            while (tIterator.hasNext()) {
		                Entry<Integer, Integer> tEntry = tIterator.next();
			            NBTTagCompound tTag = new NBTTagCompound();
			            tTag.setInteger("Hash", tEntry.getKey());
				        tTag.setInteger("EU", tEntry.getValue());
					    tList.appendTag(tTag);
		            }
		            
				    tNBT.setTag("Energy", tList);
				    try {
				        CompressedStreamTools.writeCompressed(tNBT, new FileOutputStream(new File(tDirectory, "GT_IDSU_Energyvalues.dat")));
				    } catch(StackOverflowError e) {
				        GT_Log.out.println("Ignored Stackoverflow at writing!");
				    }
		        }
			} catch (Exception e) {
				e.printStackTrace();
			}
    	}
    }
    
    public void readIDSUData() {
    	if (mUniverse != null && GT_TileEntity_IDSU.sEnergyList == null && !mUniverse.isRemote) {
    		GT_TileEntity_IDSU.sEnergyList = new HashMap<Integer, Integer>();
			try {
	            File tDirectory = getSaveDirectory();
	            if (tDirectory != null) {
			        try {
			        	NBTTagCompound tNBT = CompressedStreamTools.readCompressed(new FileInputStream(new File(tDirectory, "GT_IDSU_Energyvalues.dat")));
			            NBTTagList tList = tNBT.getTagList("Energy");
				        for (int i = 0; i < tList.tagCount(); i++) {
			                NBTTagCompound tTag = (NBTTagCompound)tList.tagAt(i);
			                GT_TileEntity_IDSU.sEnergyList.put(tTag.getInteger("Hash"), tTag.getInteger("EU"));
			            }
		            } catch(StackOverflowError e) {
				        GT_Log.out.println("Ignored Stackoverflow at reading!");
			        }
	        	}
			} catch (Exception e) {
               	if (!(e instanceof java.io.FileNotFoundException))
               		e.printStackTrace();
			}
	    }
    }
}
