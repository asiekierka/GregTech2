package gregtechmod.common;

import gregtechmod.GT_Mod;
import gregtechmod.api.BaseMetaTileEntity;
import gregtechmod.common.blocks.GT_BlockMetaID_Block;
import gregtechmod.common.tileentities.GT_TileEntity_Matterfabricator;
import net.minecraftforge.common.Configuration;

public class GT_Config {
	
	public static boolean mUUM2Rock, mUUM2Glass, mUUM2Grass, mUUM2Moss, mUUM2Sandstone, mUUM2Snowblock, mUUM2Water, mUUM2Lava, mUUM2Iron, mUUM2Gold, mUUM2Obsidian, mUUM2Netherrack, mUUM2Glowstone, mUUM2Wood, mUUM2Cactus, mUUM2Vines, mUUM2Wool, mUUM2Coal, mUUM2Diamonds, mUUM2Redstone, mUUM2Lapis, mUUM2Feathers, mUUM2Snowballs, mUUM2Gunpowder, mUUM2Clay, mUUM2Cocoa, mUUM2Ink, mUUM2Sugarcane, mUUM2Flint, mUUM2Bone, mUUM2Resin, mUUM2Iridium, mUUM2Mycelium, mUUM2Chiseled, mUUM2Copper, mUUM2Tin, mUUM2Silver, mUUM2Enderpearl, mUUM2Gems, mUUM2Plutonium, mUUM2Aluminium, mUUM2Blazerod, mUUM2Bauxite, mUUM2Titanium;
	public static boolean mSC_Brass, mSM_Brass, mSS_Brass, mSRC_Brass, mSCR_Brass, mSC_Steel, mSM_Steel, mSS_Steel, mSRC_Steel, mSCR_Steel, mSS_Iron, mSS_Gold, mSS_Copper, mSS_Bronze, mSS_Tin, mSS_Silver, mSS_Alu, mSS_Titan, mSS_Chrome, mSC_Glow, mSC_Bronze, mSC_Tin, mSC_Iron, mSC_Gold, mSC_Silver, mSC_Copper, mSC_Lapis, mSC_Emerald, mSC_Diamond, mSC_Uranium, mSC_Ruby, mSC_Sapphire, mSC_Alu, mSC_Titan, mSC_Chrome, mSCR_Glow, mSCR_Bronze, mSCR_Tin, mSCR_Iron, mSCR_Gold, mSCR_Silver, mSCR_Copper, mSCR_Lapis, mSCR_Emerald, mSCR_Diamond, mSCR_Uranium, mSCR_Ruby, mSCR_Sapphire, mSCR_Alu, mSCR_Titan, mSCR_Chrome, mSRC_Bronze, mSRC_Tin, mSRC_Iron, mSRC_Gold, mSRC_Silver, mSRC_Copper, mSRC_Lapis, mSRC_Emerald, mSRC_Diamond, mSRC_Uranium, mSRC_Ruby, mSRC_Sapphire, mSRC_Alu, mSRC_Titan, mSRC_Chrome, mSM_Bronze, mSM_Tin, mSM_Iron, mSM_Gold, mSM_Silver, mSM_Copper, mSM_Lapis, mSM_Emerald, mSM_Diamond, mSM_Uranium, mSM_Ruby, mSM_Sapphire, mSM_Alu, mSM_Titan, mSM_Chrome;
	public static boolean mUnificatorRP, mUnificatorTE, mUnificatorFR, mUnificatorRC;
	
	public static Configuration mConfigFileStandard, mConfigFileIDs, mConfigFileRecipes, mConfigFileAdvRecipes;
	
	public GT_Config(Configuration aConfigFileStandard, Configuration aConfigFileIDs, Configuration aConfigFileRecipes, Configuration aConfigFileAdvRecipes) {
		mConfigFileAdvRecipes = aConfigFileAdvRecipes;
		mConfigFileStandard = aConfigFileStandard;
		mConfigFileRecipes = aConfigFileRecipes;
		mConfigFileIDs = aConfigFileIDs;
		
    	GT_Mod.instance.mDebug = mConfigFileStandard.get("general", "Debug", false).getBoolean(false);
    	GT_Mod.instance.mDebugMode = (mConfigFileStandard.get("general", "SpecialDebugMode", 0).getInt(0) == "GregoriusT".hashCode());
    	GT_Mod.instance.mAnimations = BaseMetaTileEntity.sAnimationsAllowed	= mConfigFileStandard.get("general", "Animations", true ).getBoolean(false);
    	GT_Mod.instance.mShowCapes = mConfigFileStandard.get("general", "ShowCapes", true).getBoolean(false);
    	GT_BlockMetaID_Block.mConnectedMachineTextures = mConfigFileStandard.get("general", "ConnectedMachineCasingTextures", true).getBoolean(false);
    	
    	//GT_Mod.instance.mDetectIDConflicts = mConfigFileStandard.get("general", "DetectRandomIDConflicts", true).getBoolean(false);
    	
    	GT_Mod.instance.mBlockIDs[ 0] = mConfigFileIDs.getBlock("Block"			, 4058).getInt();
    	GT_Mod.instance.mBlockIDs[ 1] = mConfigFileIDs.getBlock("Machine"		, 4059).getInt();
    	GT_Mod.instance.mBlockIDs[ 2] = mConfigFileIDs.getBlock("Ore"			, 4060).getInt();
    	GT_Mod.instance.mBlockIDs[ 3] = mConfigFileIDs.getBlock("LightSource"	, 4061).getInt();
    	
    	GT_Mod.instance.mItemIDs[  0] = mConfigFileIDs.getItem("MATERIALS"  			, 21000).getInt();
    	GT_Mod.instance.mItemIDs[  1] = mConfigFileIDs.getItem("DUSTS"  				, 21001).getInt();
    	GT_Mod.instance.mItemIDs[  2] = mConfigFileIDs.getItem("CELLS"  				, 21002).getInt();
    	GT_Mod.instance.mItemIDs[  3] = mConfigFileIDs.getItem("COMPONENTS"  			, 21003).getInt();
    	GT_Mod.instance.mItemIDs[  4] = mConfigFileIDs.getItem("SMALLDUSTS"  			, 21004).getInt();
    	
    	GT_Mod.instance.mItemIDs[ 13] = mConfigFileIDs.getItem("LIQUIDS"  				, 21013).getInt();
    	GT_Mod.instance.mItemIDs[ 14] = mConfigFileIDs.getItem("GASSES"  				, 21014).getInt();
    	GT_Mod.instance.mItemIDs[ 15] = mConfigFileIDs.getItem("PLASMA"  				, 21015).getInt();
    	GT_Mod.instance.mItemIDs[ 16] = mConfigFileIDs.getItem("NCSensorCard"			, 21016).getInt();
    	GT_Mod.instance.mItemIDs[ 17] = mConfigFileIDs.getItem("NCSensorKit"			, 21017).getInt();
    	GT_Mod.instance.mItemIDs[ 18] = mConfigFileIDs.getItem("CheatyDevice"			, 21018).getInt();
    	
    	GT_Mod.instance.mItemIDs[ 30] = mConfigFileIDs.getItem("IronMortar"				, 21030).getInt();
    	GT_Mod.instance.mItemIDs[ 31] = mConfigFileIDs.getItem("Mortar"					, 21031).getInt();
    	GT_Mod.instance.mItemIDs[ 32] = mConfigFileIDs.getItem("HandheldSonictron"		, 21032).getInt();
    	GT_Mod.instance.mItemIDs[ 33] = mConfigFileIDs.getItem("Destructopack"			, 21033).getInt();
    	GT_Mod.instance.mItemIDs[ 34] = mConfigFileIDs.getItem("Heliumcoolant060k"		, 21034).getInt();
    	GT_Mod.instance.mItemIDs[ 35] = mConfigFileIDs.getItem("Heliumcoolant120k"		, 21035).getInt();
    	GT_Mod.instance.mItemIDs[ 36] = mConfigFileIDs.getItem("Heliumcoolant180k"		, 21036).getInt();
    	GT_Mod.instance.mItemIDs[ 37] = mConfigFileIDs.getItem("LapotronicEnergycrystal", 21037).getInt();
    	GT_Mod.instance.mItemIDs[ 38] = mConfigFileIDs.getItem("CloakingDevice"			, 21038).getInt();
    	GT_Mod.instance.mItemIDs[ 39] = mConfigFileIDs.getItem("JackHammerIron"			, 21039).getInt();
    	GT_Mod.instance.mItemIDs[ 40] = mConfigFileIDs.getItem("Neutronreflector"		, 21040).getInt();
    	GT_Mod.instance.mItemIDs[ 41] = mConfigFileIDs.getItem("JackHammerSteel"		, 21041).getInt();
    	GT_Mod.instance.mItemIDs[ 42] = mConfigFileIDs.getItem("JackHammerDiamond"		, 21042).getInt();
    	GT_Mod.instance.mItemIDs[ 43] = mConfigFileIDs.getItem("Dataorb"				, 21043).getInt();
    	GT_Mod.instance.mItemIDs[ 44] = mConfigFileIDs.getItem("Lamphelmet"				, 21044).getInt();
    	GT_Mod.instance.mItemIDs[ 45] = mConfigFileIDs.getItem("Lapotronpack"			, 21045).getInt();
    	GT_Mod.instance.mItemIDs[ 46] = mConfigFileIDs.getItem("Rockcutter"				, 21046).getInt();
    	GT_Mod.instance.mItemIDs[ 47] = mConfigFileIDs.getItem("Teslastaff"				, 21047).getInt();
    	GT_Mod.instance.mItemIDs[ 48] = mConfigFileIDs.getItem("Thorium1"				, 21048).getInt();
    	GT_Mod.instance.mItemIDs[ 49] = mConfigFileIDs.getItem("Thorium2"				, 21049).getInt();
    	GT_Mod.instance.mItemIDs[ 50] = mConfigFileIDs.getItem("Thorium4"				, 21050).getInt();
    	GT_Mod.instance.mItemIDs[ 51] = mConfigFileIDs.getItem("Plutonium1"				, 21051).getInt();
    	GT_Mod.instance.mItemIDs[ 52] = mConfigFileIDs.getItem("Plutonium2"				, 21052).getInt();
    	GT_Mod.instance.mItemIDs[ 53] = mConfigFileIDs.getItem("Plutonium4"				, 21053).getInt();
    	GT_Mod.instance.mItemIDs[ 54] = mConfigFileIDs.getItem("LithiumCell"			, 21054).getInt();
    	GT_Mod.instance.mItemIDs[ 55] = mConfigFileIDs.getItem("DebugScanner"			, 21055).getInt();
    	GT_Mod.instance.mItemIDs[ 56] = mConfigFileIDs.getItem("LithiumbatteryEmpty"	, 21056).getInt();
    	GT_Mod.instance.mItemIDs[ 57] = mConfigFileIDs.getItem("LithiumbatteryFull"		, 21057).getInt();
		GT_Mod.instance.mItemIDs[ 58] = mConfigFileIDs.getItem("Lithiumpack"			, 21058).getInt();
		//Shield
		GT_Mod.instance.mItemIDs[ 60] = mConfigFileIDs.getItem("NaKcoolant060k"			, 21060).getInt();
    	GT_Mod.instance.mItemIDs[ 61] = mConfigFileIDs.getItem("NaKcoolant120k"			, 21061).getInt();
    	GT_Mod.instance.mItemIDs[ 62] = mConfigFileIDs.getItem("NaKcoolant180k"			, 21062).getInt();
    	
		GT_Mod.instance.mTinPincher				= mConfigFileRecipes.get("cheaperrecipes", "TinPincher"				, true ).getBoolean(false);
		GT_Mod.instance.mSlime2Rubber		 	= mConfigFileRecipes.get("cheaperrecipes", "Slime2Rubber"				, true ).getBoolean(false);
		GT_Mod.instance.mRefIronPiston		 	= mConfigFileRecipes.get("cheaperrecipes", "RefinedIronPiston"			, true ).getBoolean(false);
		GT_Mod.instance.mSteelPiston		 	= mConfigFileRecipes.get("cheaperrecipes", "SteelPiston"				, true ).getBoolean(false);
		GT_Mod.instance.mSilverCircuit		 	= mConfigFileRecipes.get("cheaperrecipes", "SilverCircuit"				, true ).getBoolean(false);
    	GT_Mod.instance.mSilverAdvCircuit	 	= mConfigFileRecipes.get("cheaperrecipes", "SilverAdvCircuit"			, true ).getBoolean(false);
    	GT_Mod.instance.mEnergyRuby				= mConfigFileRecipes.get("cheaperrecipes", "Ruby2Energycrystal"		, true ).getBoolean(false);
    	GT_Mod.instance.mLapotronSapphire		= mConfigFileRecipes.get("cheaperrecipes", "Sapphire2Lapotron"			, true ).getBoolean(false);
    	GT_Mod.instance.mSteelAlloy				= mConfigFileRecipes.get("cheaperrecipes", "Steel2MixedMetalIngots"	, true ).getBoolean(false);
    	GT_Mod.instance.mAluminiumAlloy			= mConfigFileRecipes.get("cheaperrecipes", "Aluminium2MixedMetal"		, true ).getBoolean(false);
    	GT_Mod.instance.mTitaniumAlloy			= mConfigFileRecipes.get("cheaperrecipes", "Titanium2MixedMetal"		, true ).getBoolean(false);
    	
    	GT_Mod.instance.mCircuitBoards			= mConfigFileRecipes.get("circuitry", "CircuitBoards"	, true ).getBoolean(false);
    	GT_Mod.instance.mThermionicTubes		= mConfigFileRecipes.get("circuitry", "ElectronTubes"	, true ).getBoolean(false);
    	GT_Mod.instance.mBCGates				= mConfigFileRecipes.get("circuitry", "BCGates"		, true ).getBoolean(false);
    	GT_Mod.instance.mBCCircuits				= mConfigFileRecipes.get("circuitry", "BCChipsets"		, true ).getBoolean(false);
    	GT_Mod.instance.mRCCircuits				= mConfigFileRecipes.get("circuitry", "RCCircuits"		, true ).getBoolean(false);
    	GT_Mod.instance.mForestryCasings		= mConfigFileRecipes.get("circuitry", "ForestryCasings", true ).getBoolean(false);
    	GT_Mod.instance.mTEMachine				= mConfigFileRecipes.get("circuitry", "TEMachines"		, true ).getBoolean(false);
    	
    	GT_Mod.instance.mCentrifugedUranium		= mConfigFileRecipes.get("centrifugeelectrolyze", "Uranium"		, true ).getBoolean(false);
    	GT_Mod.instance.mCentrifugedDirt		= mConfigFileRecipes.get("centrifugeelectrolyze", "Dirt"			, true ).getBoolean(false);
    	GT_Mod.instance.mCentrifugedLava		= mConfigFileRecipes.get("centrifugeelectrolyze", "Lava"			, true ).getBoolean(false);
    	GT_Mod.instance.mCentrifugedClay		= mConfigFileRecipes.get("centrifugeelectrolyze", "Clay"			, true ).getBoolean(false);
    	GT_Mod.instance.mCentrifugedNetherrack	= mConfigFileRecipes.get("centrifugeelectrolyze", "Netherrack"		, true ).getBoolean(false);
    	GT_Mod.instance.mCentrifugedEndstone	= mConfigFileRecipes.get("centrifugeelectrolyze", "Endstone"		, true ).getBoolean(false);
    	GT_Mod.instance.mCentrifugedResin		= mConfigFileRecipes.get("centrifugeelectrolyze", "Resin"			, true ).getBoolean(false);
    	GT_Mod.instance.mCentrifugedSand		= mConfigFileRecipes.get("centrifugeelectrolyze", "Sand"			, true ).getBoolean(false);
    	GT_Mod.instance.mCentrifugedRubberwood	= mConfigFileRecipes.get("centrifugeelectrolyze", "Rubberwood"		, true ).getBoolean(false);
    	GT_Mod.instance.mCentrifugedGlowstone	= mConfigFileRecipes.get("centrifugeelectrolyze", "Glowstone"		, true ).getBoolean(false);
    	GT_Mod.instance.mCentrifugedSoulsand	= mConfigFileRecipes.get("centrifugeelectrolyze", "Soulsand"		, true ).getBoolean(false);
    	GT_Mod.instance.mCentrifugedMushrooms	= mConfigFileRecipes.get("centrifugeelectrolyze", "Mushrooms"		, true ).getBoolean(false);
    	GT_Mod.instance.mCentrifugedBlaze		= mConfigFileRecipes.get("centrifugeelectrolyze", "Blazepowder"	, true ).getBoolean(false);
    	GT_Mod.instance.mCentrifugedFlint		= mConfigFileRecipes.get("centrifugeelectrolyze", "Flint"			, true ).getBoolean(false);
    	GT_Mod.instance.mCentrifugedLapis		= mConfigFileRecipes.get("centrifugeelectrolyze", "Lapis"			, true ).getBoolean(false);
    	GT_Mod.instance.mCentrifugedRedstone	= mConfigFileRecipes.get("centrifugeelectrolyze", "Redstone"		, true ).getBoolean(false);
    	GT_Mod.instance.mCentrifugedSugar		= mConfigFileRecipes.get("centrifugeelectrolyze", "Sugar"			, true ).getBoolean(false);
    	GT_Mod.instance.mCentrifugedFood		= mConfigFileRecipes.get("centrifugeelectrolyze", "Food"			, true ).getBoolean(false);
    	
    	GT_Mod.instance.mPlayerdetector			= mConfigFileStandard.get("addonrecipes", "Playerdetector"				, true ).getBoolean(false);
    	GT_Mod.instance.mThorium				= mConfigFileStandard.get("addonrecipes", "Thorium"						, true ).getBoolean(false);
    	GT_Mod.instance.mPlutonium				= mConfigFileStandard.get("addonrecipes", "Plutonium"					, true ).getBoolean(false);
    	GT_Mod.instance.mFusionreactor	 		= mConfigFileStandard.get("addonrecipes", "Fusionreactor"				, true ).getBoolean(false);
    	GT_Mod.instance.mLightningrod	 		= mConfigFileStandard.get("addonrecipes", "Lightningrod"				, true ).getBoolean(false);
    	GT_Mod.instance.mQuantumchest	 		= mConfigFileStandard.get("addonrecipes", "Quantumchest"				, true ).getBoolean(false);
    	GT_Mod.instance.mQuantumTank	 		= mConfigFileStandard.get("addonrecipes", "Quantumtank"					, true ).getBoolean(false);
    	GT_Mod.instance.mUUMAssembler			= mConfigFileStandard.get("addonrecipes", "UUM_Assembler"				, true ).getBoolean(false);
    	GT_Mod.instance.mSonictron				= mConfigFileStandard.get("addonrecipes", "Sonictron"					, true ).getBoolean(false);
    	GT_Mod.instance.mIridiumblock	 		= mConfigFileStandard.get("addonrecipes", "Iridiumblock"				, true ).getBoolean(false);
    	GT_Mod.instance.mDestructopack			= mConfigFileStandard.get("addonrecipes", "Destructopack"				, true ).getBoolean(false);
    	GT_Mod.instance.mLESU					= mConfigFileStandard.get("addonrecipes", "LESU"						, true ).getBoolean(false);
    	GT_Mod.instance.mIDSU					= mConfigFileStandard.get("addonrecipes", "IDSU"						, true ).getBoolean(false);
    	GT_Mod.instance.mAESU					= mConfigFileStandard.get("addonrecipes", "AESU"						, true ).getBoolean(false);
    	GT_Mod.instance.mChargeOMat				= mConfigFileStandard.get("addonrecipes", "ChargOMat"					, true ).getBoolean(false);
    	GT_Mod.instance.mSuperconductor			= mConfigFileStandard.get("addonrecipes", "Superconductorwire"			, true ).getBoolean(false);
    	GT_Mod.instance.mHeliumCoolant			= mConfigFileStandard.get("addonrecipes", "Helium2Coolantcell"			, true ).getBoolean(false);
    	GT_Mod.instance.mNaKCoolant				= mConfigFileStandard.get("addonrecipes", "NaKCoolantcell"				, true ).getBoolean(false);
    	GT_Mod.instance.mNeutronReflector		= mConfigFileStandard.get("addonrecipes", "IridiumNeutronReflector"		, true ).getBoolean(false);
    	GT_Mod.instance.mRockcutter				= mConfigFileStandard.get("addonrecipes", "Rockcutter"					, true ).getBoolean(false);
    	GT_Mod.instance.mTeslaStaff				= mConfigFileStandard.get("addonrecipes", "TeslaStaff"					, true ).getBoolean(false);
    	GT_Mod.instance.mLapotronpack			= mConfigFileStandard.get("addonrecipes", "Lapotronpack"				, true ).getBoolean(false);
    	GT_Mod.instance.mLightHelmet			= mConfigFileStandard.get("addonrecipes", "LightHelmet"					, true ).getBoolean(false);
    	GT_Mod.instance.mMatterfabricator		= mConfigFileStandard.get("addonrecipes", "Matterfabricator"			, true ).getBoolean(false);
    	GT_Mod.instance.mEAutoCrafter			= mConfigFileStandard.get("addonrecipes", "ElectricAutocrafter"			, true ).getBoolean(false);
    	GT_Mod.instance.mRockBreaker			= mConfigFileStandard.get("addonrecipes", "RockBreaker"					, true ).getBoolean(false);
    	GT_Mod.instance.mLithiumpack			= mConfigFileStandard.get("addonrecipes", "Lithiumpack"					, true ).getBoolean(false);
    	GT_Mod.instance.mLithiumbattery			= mConfigFileStandard.get("addonrecipes", "Lithiumbattery"				, true ).getBoolean(false);
    	GT_Mod.instance.mAutomationStuff		= mConfigFileStandard.get("addonrecipes", "AutomationMachines"			, true ).getBoolean(false);
    	GT_Mod.instance.mCloakingDevice			= mConfigFileStandard.get("addonrecipes", "CloakingDevice"				, true ).getBoolean(false);
    	GT_Mod.instance.mItemClearer			= mConfigFileStandard.get("addonrecipes", "ItemClearer"					, true ).getBoolean(false);
    	GT_Mod.instance.mCropHarvestor			= mConfigFileStandard.get("addonrecipes", "CropHarvestor"				, true ).getBoolean(false);
    	GT_Mod.instance.mScrapboxinator			= mConfigFileStandard.get("addonrecipes", "Scrapboxinator"				, true ).getBoolean(false);
    	GT_Mod.instance.mGrinder				= mConfigFileStandard.get("addonrecipes", "IndustrialGrinder"			, true ).getBoolean(false);
    	GT_Mod.instance.mJackHammer				= mConfigFileStandard.get("addonrecipes", "JackHammer"					, true ).getBoolean(false);
    	GT_Mod.instance.mSawmill				= mConfigFileStandard.get("addonrecipes", "Sawmill"						, true ).getBoolean(false);
    	GT_Mod.instance.mDieselGenerator		= mConfigFileStandard.get("addonrecipes", "DieselGenerator"				, true ).getBoolean(false);
    	GT_Mod.instance.mGasTurbine				= mConfigFileStandard.get("addonrecipes", "GasTurbine"					, true ).getBoolean(false);
    	GT_Mod.instance.mThermalGenerator		= mConfigFileStandard.get("addonrecipes", "ThermalGenerator"			, true ).getBoolean(false);
    	GT_Mod.instance.mDenseFluidGenerator	= mConfigFileStandard.get("addonrecipes", "SemifluidGenerator"			, true ).getBoolean(false);
    	
    	mSS_Iron				= mConfigFileRecipes.get("storageblocksmelting", "Iron"				, true ).getBoolean(false);
    	mSS_Gold				= mConfigFileRecipes.get("storageblocksmelting", "Gold"				, true ).getBoolean(false);
    	mSS_Copper				= mConfigFileRecipes.get("storageblocksmelting", "Copper"				, true ).getBoolean(false);
    	mSS_Bronze				= mConfigFileRecipes.get("storageblocksmelting", "Bronze"				, true ).getBoolean(false);
    	mSS_Tin					= mConfigFileRecipes.get("storageblocksmelting", "Tin"					, true ).getBoolean(false);
    	mSS_Silver				= mConfigFileRecipes.get("storageblocksmelting", "Silver"				, true ).getBoolean(false);
    	mSS_Alu					= mConfigFileRecipes.get("storageblocksmelting", "Aluminium"			, true ).getBoolean(false);
    	mSS_Titan				= mConfigFileRecipes.get("storageblocksmelting", "Titanium"			, true ).getBoolean(false);
    	mSS_Chrome				= mConfigFileRecipes.get("storageblocksmelting", "Chrome"				, true ).getBoolean(false);
    	mSS_Steel				= mConfigFileRecipes.get("storageblocksmelting", "Steel"				, true ).getBoolean(false);
    	mSS_Brass				= mConfigFileRecipes.get("storageblocksmelting", "Brass"				, true ).getBoolean(false);
		
		mSC_Glow				= mConfigFileRecipes.get("storageblockcompression", "Glowstone"		, true ).getBoolean(false);
		mSC_Bronze				= mConfigFileRecipes.get("storageblockcompression", "Bronze"			, true ).getBoolean(false);
		mSC_Tin					= mConfigFileRecipes.get("storageblockcompression", "Tin"				, true ).getBoolean(false);
		mSC_Iron				= mConfigFileRecipes.get("storageblockcompression", "Iron"				, true ).getBoolean(false);
		mSC_Gold				= mConfigFileRecipes.get("storageblockcompression", "Gold"				, true ).getBoolean(false);
		mSC_Silver				= mConfigFileRecipes.get("storageblockcompression", "Silver"			, true ).getBoolean(false);
		mSC_Copper				= mConfigFileRecipes.get("storageblockcompression", "Copper"			, false).getBoolean(false);
		mSC_Lapis				= mConfigFileRecipes.get("storageblockcompression", "Lapis"			, true ).getBoolean(false);
		mSC_Emerald				= mConfigFileRecipes.get("storageblockcompression", "Emerald"			, true ).getBoolean(false);
		mSC_Diamond				= mConfigFileRecipes.get("storageblockcompression", "Diamond"			, true ).getBoolean(false);
		mSC_Uranium				= mConfigFileRecipes.get("storageblockcompression", "Uranium"			, true ).getBoolean(false);
		mSC_Ruby				= mConfigFileRecipes.get("storageblockcompression", "Ruby"				, true ).getBoolean(false);
		mSC_Sapphire			= mConfigFileRecipes.get("storageblockcompression", "Sapphire"			, true ).getBoolean(false);
		mSC_Alu					= mConfigFileRecipes.get("storageblockcompression", "Aluminium"		, true ).getBoolean(false);
		mSC_Titan				= mConfigFileRecipes.get("storageblockcompression", "Titanium"			, true ).getBoolean(false);
		mSC_Chrome				= mConfigFileRecipes.get("storageblockcompression", "Chrome"			, true ).getBoolean(false);
		mSC_Steel				= mConfigFileRecipes.get("storageblockcompression", "Steel"			, true ).getBoolean(false);
		mSC_Brass				= mConfigFileRecipes.get("storageblockcompression", "Brass"			, true ).getBoolean(false);
		
		mSCR_Glow				= mConfigFileRecipes.get("storageblockcrafting", "Glowstone"		, false).getBoolean(false);
		mSCR_Bronze				= mConfigFileRecipes.get("storageblockcrafting", "Bronze"			, false).getBoolean(false);
		mSCR_Tin				= mConfigFileRecipes.get("storageblockcrafting", "Tin"				, false).getBoolean(false);
		mSCR_Iron				= mConfigFileRecipes.get("storageblockcrafting", "Iron"			, false).getBoolean(false);
		mSCR_Gold				= mConfigFileRecipes.get("storageblockcrafting", "Gold"			, false).getBoolean(false);
		mSCR_Silver				= mConfigFileRecipes.get("storageblockcrafting", "Silver"			, false).getBoolean(false);
		mSCR_Copper				= mConfigFileRecipes.get("storageblockcrafting", "Copper"			, true ).getBoolean(false);
		mSCR_Lapis				= mConfigFileRecipes.get("storageblockcrafting", "Lapis"			, false).getBoolean(false);
		mSCR_Emerald			= mConfigFileRecipes.get("storageblockcrafting", "Emerald"			, false).getBoolean(false);
		mSCR_Diamond			= mConfigFileRecipes.get("storageblockcrafting", "Diamond"			, false).getBoolean(false);
		mSCR_Uranium			= mConfigFileRecipes.get("storageblockcrafting", "Uranium"			, false).getBoolean(false);
		mSCR_Ruby				= mConfigFileRecipes.get("storageblockcrafting", "Ruby"			, false).getBoolean(false);
		mSCR_Sapphire			= mConfigFileRecipes.get("storageblockcrafting", "Sapphire"		, false).getBoolean(false);
		mSCR_Alu				= mConfigFileRecipes.get("storageblockcrafting", "Aluminium"		, false).getBoolean(false);
		mSCR_Titan				= mConfigFileRecipes.get("storageblockcrafting", "Titanium"		, false).getBoolean(false);
		mSCR_Chrome				= mConfigFileRecipes.get("storageblockcrafting", "Chrome"			, false).getBoolean(false);
		mSCR_Steel				= mConfigFileRecipes.get("storageblockcrafting", "Steel"			, false).getBoolean(false);
		mSCR_Brass				= mConfigFileRecipes.get("storageblockcrafting", "Brass"			, false).getBoolean(false);
		
		mSRC_Bronze				= mConfigFileRecipes.get("storageblockreversecrafting", "Bronze"		, false).getBoolean(false);
		mSRC_Tin				= mConfigFileRecipes.get("storageblockreversecrafting", "Tin"			, false).getBoolean(false);
		mSRC_Iron				= mConfigFileRecipes.get("storageblockreversecrafting", "Iron"			, false).getBoolean(false);
		mSRC_Gold				= mConfigFileRecipes.get("storageblockreversecrafting", "Gold"			, false).getBoolean(false);
		mSRC_Silver				= mConfigFileRecipes.get("storageblockreversecrafting", "Silver"		, false).getBoolean(false);
		mSRC_Copper				= mConfigFileRecipes.get("storageblockreversecrafting", "Copper"		, false).getBoolean(false);
		mSRC_Lapis				= mConfigFileRecipes.get("storageblockreversecrafting", "Lapis"		, false).getBoolean(false);
		mSRC_Emerald			= mConfigFileRecipes.get("storageblockreversecrafting", "Emerald"		, true ).getBoolean(false);
		mSRC_Diamond			= mConfigFileRecipes.get("storageblockreversecrafting", "Diamond"		, true ).getBoolean(false);
		mSRC_Uranium			= mConfigFileRecipes.get("storageblockreversecrafting", "Uranium"		, false).getBoolean(false);
		mSRC_Ruby				= mConfigFileRecipes.get("storageblockreversecrafting", "Ruby"			, true ).getBoolean(false);
		mSRC_Sapphire			= mConfigFileRecipes.get("storageblockreversecrafting", "Sapphire"		, true ).getBoolean(false);
		mSRC_Alu				= mConfigFileRecipes.get("storageblockreversecrafting", "Aluminium"	, false).getBoolean(false);
		mSRC_Titan				= mConfigFileRecipes.get("storageblockreversecrafting", "Titanium"		, false).getBoolean(false);
		mSRC_Chrome				= mConfigFileRecipes.get("storageblockreversecrafting", "Chrome"		, false).getBoolean(false);
		mSRC_Steel				= mConfigFileRecipes.get("storageblockreversecrafting", "Steel"		, false).getBoolean(false);
		mSRC_Brass				= mConfigFileRecipes.get("storageblockreversecrafting", "Brass"		, false).getBoolean(false);
		
		mSM_Bronze				= mConfigFileRecipes.get("storageblockmaceration", "Bronze"			, true ).getBoolean(false);
		mSM_Tin					= mConfigFileRecipes.get("storageblockmaceration", "Tin"				, true ).getBoolean(false);
		mSM_Iron				= mConfigFileRecipes.get("storageblockmaceration", "Iron"				, true ).getBoolean(false);
		mSM_Gold				= mConfigFileRecipes.get("storageblockmaceration", "Gold"				, true ).getBoolean(false);
		mSM_Silver				= mConfigFileRecipes.get("storageblockmaceration", "Silver"			, true ).getBoolean(false);
		mSM_Copper				= mConfigFileRecipes.get("storageblockmaceration", "Copper"			, true ).getBoolean(false);
		mSM_Lapis				= mConfigFileRecipes.get("storageblockmaceration", "Lapis"				, true ).getBoolean(false);
		mSM_Emerald				= mConfigFileRecipes.get("storageblockmaceration", "Emerald"			, true ).getBoolean(false);
		mSM_Diamond				= mConfigFileRecipes.get("storageblockmaceration", "Diamond"			, true ).getBoolean(false);
		mSM_Uranium				= mConfigFileRecipes.get("storageblockmaceration", "Uranium"			, true ).getBoolean(false);
		mSM_Ruby				= mConfigFileRecipes.get("storageblockmaceration", "Ruby"				, true ).getBoolean(false);
		mSM_Sapphire			= mConfigFileRecipes.get("storageblockmaceration", "Sapphire"			, true ).getBoolean(false);
		mSM_Alu					= mConfigFileRecipes.get("storageblockmaceration", "Aluminium"			, true ).getBoolean(false);
		mSM_Titan				= mConfigFileRecipes.get("storageblockmaceration", "Titanium"			, true ).getBoolean(false);
		mSM_Chrome				= mConfigFileRecipes.get("storageblockmaceration", "Chrome"			, true ).getBoolean(false);
		mSM_Steel				= mConfigFileRecipes.get("storageblockmaceration", "Steel"				, true ).getBoolean(false);
		mSM_Brass				= mConfigFileRecipes.get("storageblockmaceration", "Brass"				, true ).getBoolean(false);
    	
		GT_Mod.instance.mStorageblockCompressor = mConfigFileRecipes.get("usefullrecipes", "Compress2Storageblock"		, true ).getBoolean(false);
		GT_Mod.instance.mStorageblockMacerator  = mConfigFileRecipes.get("usefullrecipes", "Storageblock2Macerator"	, true ).getBoolean(false);
		GT_Mod.instance.mWatermillRecycling  	= mConfigFileRecipes.get("usefullrecipes", "WatermillReverseCrafting"	, true ).getBoolean(false);
		GT_Mod.instance.mBetterNukeRecipe		= mConfigFileRecipes.get("usefullrecipes", "ExpensiveNukeRecipe"		, true ).getBoolean(false);
		GT_Mod.instance.mBetterSolarRecipe		= mConfigFileRecipes.get("usefullrecipes", "ExpensiveSolarRecipe"		, true ).getBoolean(false);
		GT_Mod.instance.mBetterWindRecipe		= mConfigFileRecipes.get("usefullrecipes", "ExpensiveWindRecipe"		, true ).getBoolean(false);
		GT_Mod.instance.mTincellCount			= mConfigFileRecipes.get("usefullrecipes", "TincellsPer4Tin"			, 8).getInt();
		GT_Mod.instance.mBetterMaceratorRecipe	= mConfigFileRecipes.get("usefullrecipes", "ExpensiveMaceratorRecipe"	, true ).getBoolean(false);
		GT_Mod.instance.mBetterWatermillRecipe	= mConfigFileRecipes.get("usefullrecipes", "ExpensiveWatermillRecipe"	, true ).getBoolean(false);
		GT_Mod.instance.mBetterQuarryRecipe		= mConfigFileRecipes.get("usefullrecipes", "ExpensiveBCQuarryRecipe"	, true ).getBoolean(false);
		GT_Mod.instance.mRollingmachineAlloy	= mConfigFileRecipes.get("usefullrecipes", "MMIngotOnlyRollingMachine"	, true ).getBoolean(false);
		GT_Mod.instance.mSteelETools			= mConfigFileRecipes.get("usefullrecipes", "ElectricSteelTools"		, true ).getBoolean(false);
		GT_Mod.instance.mDiamondDrillTitanium	= mConfigFileRecipes.get("usefullrecipes", "TitaniumDiamondDrill"		, true ).getBoolean(false);
		GT_Mod.instance.mToolArmorMaceration	= mConfigFileRecipes.get("usefullrecipes", "ToolAndArmorMaceration"	, true ).getBoolean(false);
		GT_Mod.instance.mHeliumLaser			= mConfigFileRecipes.get("usefullrecipes", "ExpensiveLaser"			, true ).getBoolean(false);
		GT_Mod.instance.mBeryliumReflector		= mConfigFileRecipes.get("usefullrecipes", "BeryliumReflector"			, true ).getBoolean(false);
    	GT_Mod.instance.mHardIridiumPlate		= mConfigFileRecipes.get("usefullrecipes", "DifficultIridiumPlate"		, true ).getBoolean(false);
		
		GT_Mod.instance.mQSuithelmet			= mConfigFileRecipes.get("ic2recipes", "QSuithelmet"				, true ).getBoolean(false);
		GT_Mod.instance.mQSuitplate				= mConfigFileRecipes.get("ic2recipes", "QSuitplate"				, true ).getBoolean(false);
		GT_Mod.instance.mQSuitpants				= mConfigFileRecipes.get("ic2recipes", "QSuitpants"				, true ).getBoolean(false);
		GT_Mod.instance.mQSuitshoes				= mConfigFileRecipes.get("ic2recipes", "QSuitshoes"				, true ).getBoolean(false);
		GT_Mod.instance.mMassfabricator			= mConfigFileRecipes.get("ic2recipes", "Massfabricator"			, false).getBoolean(false);
    	
		GT_Mod.instance.mQOverload				= mConfigFileStandard.get("quantumsuit", "OverloadQSuitItems"		, false).getBoolean(false);
		GT_Mod.instance.mQFood					= mConfigFileStandard.get("quantumsuit", "QHelmetHungerRefill"		, true ).getBoolean(false);
		GT_Mod.instance.mQAir					= mConfigFileStandard.get("quantumsuit", "QHelmetAirRefill"			, true ).getBoolean(false);
		GT_Mod.instance.mQCure					= mConfigFileStandard.get("quantumsuit", "QHelmetPoisonCure"		, true ).getBoolean(false);
		GT_Mod.instance.mQLight					= mConfigFileStandard.get("quantumsuit", "QHelmetLamp"				, false).getBoolean(false);
		GT_Mod.instance.mQInvincible			= mConfigFileStandard.get("quantumsuit", "QChestInvincibility"		, false).getBoolean(false);
		GT_Mod.instance.mQFire					= mConfigFileStandard.get("quantumsuit", "QChestFireprotection"		, true ).getBoolean(false);
		
    	mUUM2Gems 				= mConfigFileRecipes.get("uumrecipes", "UUM2Gems"				, true ).getBoolean(false);
    	mUUM2Enderpearl			= mConfigFileRecipes.get("uumrecipes", "UUM2Enderpearl"		, true ).getBoolean(false);
    	mUUM2Silver				= mConfigFileRecipes.get("uumrecipes", "UUM2Silver"			, true ).getBoolean(false);
    	mUUM2Rock				= mConfigFileRecipes.get("uumrecipes", "UUM2Rock"				, true ).getBoolean(false);
    	mUUM2Glass				= mConfigFileRecipes.get("uumrecipes", "UUM2Glass"				, true ).getBoolean(false);
    	mUUM2Grass				= mConfigFileRecipes.get("uumrecipes", "UUM2Grass"				, true ).getBoolean(false);
    	mUUM2Moss				= mConfigFileRecipes.get("uumrecipes", "UUM2Moss"				, true ).getBoolean(false);
    	mUUM2Sandstone			= mConfigFileRecipes.get("uumrecipes", "UUM2Sandstone"			, true ).getBoolean(false);
    	mUUM2Snowblock			= mConfigFileRecipes.get("uumrecipes", "UUM2Snowblock"			, true ).getBoolean(false);
    	mUUM2Water				= mConfigFileRecipes.get("uumrecipes", "UUM2Water"				, true ).getBoolean(false);
    	mUUM2Lava				= mConfigFileRecipes.get("uumrecipes", "UUM2Lava"				, true ).getBoolean(false);
    	mUUM2Iron				= mConfigFileRecipes.get("uumrecipes", "UUM2Iron"				, true ).getBoolean(false);
    	mUUM2Gold				= mConfigFileRecipes.get("uumrecipes", "UUM2Gold"				, true ).getBoolean(false);
    	mUUM2Obsidian			= mConfigFileRecipes.get("uumrecipes", "UUM2Obsidian"			, true ).getBoolean(false);
    	mUUM2Netherrack			= mConfigFileRecipes.get("uumrecipes", "UUM2Netherrack"		, true ).getBoolean(false);
    	mUUM2Glowstone			= mConfigFileRecipes.get("uumrecipes", "UUM2Glowstone"			, true ).getBoolean(false);
    	mUUM2Wood				= mConfigFileRecipes.get("uumrecipes", "UUM2Wood"				, true ).getBoolean(false);
    	mUUM2Cactus				= mConfigFileRecipes.get("uumrecipes", "UUM2Cactus"			, true ).getBoolean(false);
    	mUUM2Vines				= mConfigFileRecipes.get("uumrecipes", "UUM2Vines"				, true ).getBoolean(false);
    	mUUM2Wool				= mConfigFileRecipes.get("uumrecipes", "UUM2Wool"				, true ).getBoolean(false);
    	mUUM2Coal				= mConfigFileRecipes.get("uumrecipes", "UUM2Coal"				, true ).getBoolean(false);
    	mUUM2Diamonds			= mConfigFileRecipes.get("uumrecipes", "UUM2Diamonds"			, true ).getBoolean(false);
    	mUUM2Redstone			= mConfigFileRecipes.get("uumrecipes", "UUM2Redstone"			, true ).getBoolean(false);
    	mUUM2Lapis				= mConfigFileRecipes.get("uumrecipes", "UUM2Lapis"				, true ).getBoolean(false);
    	mUUM2Feathers			= mConfigFileRecipes.get("uumrecipes", "UUM2Feathers"			, true ).getBoolean(false);
    	mUUM2Snowballs			= mConfigFileRecipes.get("uumrecipes", "UUM2Snowballs"			, true ).getBoolean(false);
    	mUUM2Gunpowder			= mConfigFileRecipes.get("uumrecipes", "UUM2Gunpowder"			, true ).getBoolean(false);
    	mUUM2Clay				= mConfigFileRecipes.get("uumrecipes", "UUM2Clay"				, true ).getBoolean(false);
    	mUUM2Cocoa				= mConfigFileRecipes.get("uumrecipes", "UUM2Cocoa"				, true ).getBoolean(false);
    	mUUM2Ink				= mConfigFileRecipes.get("uumrecipes", "UUM2Ink"				, true ).getBoolean(false);
    	mUUM2Sugarcane			= mConfigFileRecipes.get("uumrecipes", "UUM2Sugarcane"			, true ).getBoolean(false);
    	mUUM2Flint				= mConfigFileRecipes.get("uumrecipes", "UUM2Flint"				, true ).getBoolean(false);
    	mUUM2Bone				= mConfigFileRecipes.get("uumrecipes", "UUM2Bone"				, true ).getBoolean(false);
    	mUUM2Resin				= mConfigFileRecipes.get("uumrecipes", "UUM2Resin"				, true ).getBoolean(false);
    	mUUM2Iridium			= mConfigFileRecipes.get("uumrecipes", "UUM2Iridium"			, true ).getBoolean(false);
    	mUUM2Mycelium			= mConfigFileRecipes.get("uumrecipes", "UUM2Mycelium"			, true ).getBoolean(false);
    	mUUM2Chiseled			= mConfigFileRecipes.get("uumrecipes", "UUM2ChiseledStone"		, true ).getBoolean(false);
    	mUUM2Copper				= mConfigFileRecipes.get("uumrecipes", "UUM2Copper"			, true ).getBoolean(false);
    	mUUM2Tin				= mConfigFileRecipes.get("uumrecipes", "UUM2Tin"				, true ).getBoolean(false);
    	mUUM2Blazerod			= mConfigFileRecipes.get("uumrecipes", "UUM2Blazerods"			, true ).getBoolean(false);
    	mUUM2Bauxite			= mConfigFileRecipes.get("uumrecipes", "UUM2Bauxite"			, true ).getBoolean(false);
    	mUUM2Titanium			= mConfigFileRecipes.get("uumrecipes", "UUM2Titanium"			, true ).getBoolean(false);
    	mUUM2Aluminium			= mConfigFileRecipes.get("uumrecipes", "UUM2Aluminium"			, true ).getBoolean(false);
    	mUUM2Plutonium			= mConfigFileRecipes.get("uumrecipes", "UUM2Plutonium"			, true ).getBoolean(false);
    	
    	GT_Mod.instance.mUraniumMaceration		= mConfigFileRecipes.get("silktouchrecipes", "UraniumOreMaceration"	, true ).getBoolean(false);
    	GT_Mod.instance.mIridiumMaceration		= mConfigFileRecipes.get("silktouchrecipes", "IridiumOreMaceration"	, true ).getBoolean(false);
    	GT_Mod.instance.mGemMaceration			= mConfigFileRecipes.get("silktouchrecipes", "GemOreMaceration"		, true ).getBoolean(false);
    	GT_Mod.instance.mLapisMaceration		= mConfigFileRecipes.get("silktouchrecipes", "LapisOreMaceration"		, true ).getBoolean(false);
    	GT_Mod.instance.mDiamondMaceration		= mConfigFileRecipes.get("silktouchrecipes", "DiamondOreMaceration"	, true ).getBoolean(false);
    	GT_Mod.instance.mCoalMaceration			= mConfigFileRecipes.get("silktouchrecipes", "CoalOreMaceration"		, true ).getBoolean(false);
    	GT_Mod.instance.mRedstoneMaceration		= mConfigFileRecipes.get("silktouchrecipes", "RedstoneOreMaceration"	, true ).getBoolean(false);
    	GT_Mod.instance.mSaltpeterMaceration	= mConfigFileRecipes.get("silktouchrecipes", "SaltpeterOreMaceration"	, true ).getBoolean(false);
    	GT_Mod.instance.mSulphurMaceration		= mConfigFileRecipes.get("silktouchrecipes", "SulphurOreMaceration"	, true ).getBoolean(false);
    	GT_Mod.instance.mApatiteMaceration		= mConfigFileRecipes.get("silktouchrecipes", "ApatiteOreMaceration"	, true ).getBoolean(false);
    	GT_Mod.instance.mPyriteMaceration		= mConfigFileRecipes.get("silktouchrecipes", "PyriteOreMaceration"		, true ).getBoolean(false);
    	GT_Mod.instance.mCinnabarMaceration		= mConfigFileRecipes.get("silktouchrecipes", "CinnabarOreMaceration"	, true ).getBoolean(false);
    	GT_Mod.instance.mSphaleriteMaceration	= mConfigFileRecipes.get("silktouchrecipes", "SphaleriteOreMaceration"	, true ).getBoolean(false);
    	
    	GT_Mod.instance.mDungeonDisc			= mConfigFileStandard.get("dungeon", "DungeonMusicDiscFix"		, false).getBoolean(false);
    	GT_Mod.instance.mDungeonLoot			= mConfigFileStandard.get("dungeon", "DungeonLoot"				, true ).getBoolean(false);
    	GT_Mod.instance.mDungeonCredits			= mConfigFileStandard.get("dungeon", "DungeonICredits"			, false).getBoolean(false);
    	
    	GT_Worldgenerator.sIridiumProbability	= mConfigFileStandard.get("worldgeneration", "IridiumProbability"		, 20).getInt();
    	GT_Worldgenerator.sAsteroids			= mConfigFileStandard.get("worldgeneration", "EnderAsteroids"			, true ).getBoolean(false);
    	GT_Worldgenerator.sGeneratedOres[ 1]	= mConfigFileStandard.get("worldgeneration", "Silverore"				, true ).getBoolean(false);
    	GT_Worldgenerator.sGeneratedOres[ 2]	= mConfigFileStandard.get("worldgeneration", "Iridiumore"				, true ).getBoolean(false);
    	GT_Worldgenerator.sGeneratedOres[ 3]	= mConfigFileStandard.get("worldgeneration", "Rubyore"					, true ).getBoolean(false);
    	GT_Worldgenerator.sGeneratedOres[ 4]	= mConfigFileStandard.get("worldgeneration", "Sapphireore"				, true ).getBoolean(false);
    	GT_Worldgenerator.sGeneratedOres[ 5]	= mConfigFileStandard.get("worldgeneration", "Bauxiteore"				, true ).getBoolean(false);
    	GT_Worldgenerator.sGeneratedOres[ 6]	= mConfigFileStandard.get("worldgeneration", "Pyriteore"				, true ).getBoolean(false);
    	GT_Worldgenerator.sGeneratedOres[ 7]	= mConfigFileStandard.get("worldgeneration", "Cinnabarore"				, true ).getBoolean(false);
    	GT_Worldgenerator.sGeneratedOres[ 8]	= mConfigFileStandard.get("worldgeneration", "Sphaleriteore"			, true ).getBoolean(false);
    	GT_Worldgenerator.sGeneratedOres[ 9]	= mConfigFileStandard.get("worldgeneration", "Tungstateore"				, true ).getBoolean(false);
    	GT_Worldgenerator.sGeneratedOres[10]	= mConfigFileStandard.get("worldgeneration", "Cooperiteore"				, true ).getBoolean(false);
    	GT_Worldgenerator.sGeneratedOres[11]	= mConfigFileStandard.get("worldgeneration", "Olivineore"				, true ).getBoolean(false);
    	GT_Worldgenerator.sGeneratedOres[12]	= mConfigFileStandard.get("worldgeneration", "Sodaliteore"				, true ).getBoolean(false);
    	
    	mUnificatorRP = mConfigFileStandard.get("unificatortargets", "Redpower"			, true ).getBoolean(false);
    	mUnificatorRC = mConfigFileStandard.get("unificatortargets", "Railcraft"		, false).getBoolean(false);
    	mUnificatorTE = mConfigFileStandard.get("unificatortargets", "ThermalExpansion"	, false).getBoolean(false);
    	mUnificatorFR = mConfigFileStandard.get("unificatortargets", "Forestry"			, false).getBoolean(false);
    	
    	GT_Mod.instance.mBlockBreakerNerf		= mConfigFileStandard.get("features", "RPBlockBreakerNerf"		, false).getBoolean(false);
    	GT_Mod.instance.mForestryBronzeNerf		= mConfigFileStandard.get("features", "ForestryBronzeNerf"		, true ).getBoolean(false);
    	GT_Mod.instance.mConstantEnergy			= mConfigFileStandard.get("features", "ConstantEnergyMachine"	, true ).getBoolean(false);
    	GT_Mod.instance.mNoStoneRecycling		= mConfigFileStandard.get("features", "DisableStoneInRecycler"	, false).getBoolean(false);
    	GT_Mod.instance.mLavaMiner	 			= mConfigFileStandard.get("features", "MinerListLavaAddition"	, true ).getBoolean(false);
    	GT_Mod.instance.mReactorplanner		 	= mConfigFileStandard.get("features", "Reactorplanner"			, true ).getBoolean(false);
    	GT_Mod.instance.mSeedscanner		 	= mConfigFileStandard.get("features", "Seedscanner"				, true ).getBoolean(false);
    	GT_TileEntity_Matterfabricator.sMatterFabricationRate = mConfigFileStandard.get("features", "MatterFabricationRate"	, 16666666).getInt();
    	GT_Mod.instance.mUpgradeCount			= mConfigFileStandard.get("features", "UpgradeStacksize"		, 4).getInt();
    	
    	if (GT_TileEntity_Matterfabricator.sMatterFabricationRate <= 0) GT_TileEntity_Matterfabricator.sMatterFabricationRate = 300000000;
    	if (GT_TileEntity_Matterfabricator.sMatterFabricationRate >= 300000000) GT_TileEntity_Matterfabricator.sMatterFabricationRate = 300000000;
    	GT_Mod.instance.mTincellCount = Math.min(64, Math.max(1, GT_Mod.instance.mTincellCount));
    	//GT_Mod.instance.mXYCraft = GT_Mod.instance.mDebugMode;
    	
    	if (GT_Mod.instance.mMassfabricator && GT_TileEntity_Matterfabricator.sMatterFabricationRate > 200000) GT_TileEntity_Matterfabricator.sMatterFabricationRate = 150000;
    	
    	save();
	}
	
	public void save() {
		mConfigFileAdvRecipes.save();
    	mConfigFileStandard.save();
    	mConfigFileRecipes.save();
    	mConfigFileIDs.save();
	}
}
