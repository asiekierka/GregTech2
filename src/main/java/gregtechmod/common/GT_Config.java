/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.item.ItemStack
 *  net.minecraftforge.common.Configuration
 *  net.minecraftforge.common.Property
 */
package gregtechmod.common;

import gregtechmod.GT_Mod;
import gregtechmod.api.GT_Recipe;
import gregtechmod.common.GT_OreDictUnificator;
import gregtechmod.common.GT_Worldgenerator;
import gregtechmod.common.blocks.GT_BlockMetaID_Block;
import gregtechmod.common.tileentities.GT_MetaTileEntity_DragonEggEnergySiphon;
import gregtechmod.common.tileentities.GT_MetaTileEntity_MagicEnergyAbsorber;
import gregtechmod.common.tileentities.GT_TileEntity_Matterfabricator;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.Property;

public class GT_Config {
    public static boolean mUnificatorRP;
    public static boolean mUnificatorTE;
    public static boolean mUnificatorFR;
    public static boolean mUnificatorRC;
    public static boolean mUnificatorTC;
    private static Configuration mConfigFileStandard;
    private static Configuration mConfigFileIDs;
    private static Configuration mConfigFileRecipes;
    private static Configuration mConfigFileAdvRecipes;

    public GT_Config(Configuration aConfigFileStandard, Configuration aConfigFileIDs, Configuration aConfigFileRecipes, Configuration aConfigFileAdvRecipes) {
        mConfigFileAdvRecipes = aConfigFileAdvRecipes;
        mConfigFileStandard = aConfigFileStandard;
        mConfigFileRecipes = aConfigFileRecipes;
        mConfigFileIDs = aConfigFileIDs;
        GT_Mod.instance.mDebug = GT_Recipe.mDebug = mConfigFileStandard.get("general", "Debug", false).getBoolean(false);
        GT_Mod.instance.mDebugMode = mConfigFileStandard.get("general", "SpecialDebugMode", 0).getInt(0) == "GregoriusT".hashCode();
        GT_Mod.instance.mAnimations = mConfigFileStandard.get("general", "Animations", true).getBoolean(false);
        GT_Mod.instance.mShowCapes = mConfigFileStandard.get("general", "ShowCapes", true).getBoolean(false);
        GT_Mod.instance.mOnline = mConfigFileStandard.get("general", "online", true).getBoolean(false);
        GT_BlockMetaID_Block.mConnectedMachineTextures = mConfigFileStandard.get("general", "ConnectedMachineCasingTextures", true).getBoolean(false);
        GT_Mod.instance.mBlockIDs[0] = mConfigFileIDs.getBlock("Block", 4058).getInt();
        GT_Mod.instance.mBlockIDs[1] = mConfigFileIDs.getBlock("Machine", 4059).getInt();
        GT_Mod.instance.mBlockIDs[2] = mConfigFileIDs.getBlock("Ore", 4060).getInt();
        GT_Mod.instance.mBlockIDs[3] = mConfigFileIDs.getBlock("LightSource", 4061).getInt();
        GT_Mod.instance.mBlockIDs[4] = mConfigFileIDs.getBlock("Block2", 4057).getInt();
        GT_Mod.instance.mItemIDs[0] = mConfigFileIDs.getItem("MATERIALS", 21000).getInt();
        GT_Mod.instance.mItemIDs[1] = mConfigFileIDs.getItem("DUSTS", 21001).getInt();
        GT_Mod.instance.mItemIDs[2] = mConfigFileIDs.getItem("CELLS", 21002).getInt();
        GT_Mod.instance.mItemIDs[3] = mConfigFileIDs.getItem("COMPONENTS", 21003).getInt();
        GT_Mod.instance.mItemIDs[4] = mConfigFileIDs.getItem("SMALLDUSTS", 21004).getInt();
        GT_Mod.instance.mItemIDs[5] = mConfigFileIDs.getItem("NUGGETS", 21005).getInt();
        GT_Mod.instance.mItemIDs[13] = mConfigFileIDs.getItem("LIQUIDS", 21013).getInt();
        GT_Mod.instance.mItemIDs[14] = mConfigFileIDs.getItem("GASSES", 21014).getInt();
        GT_Mod.instance.mItemIDs[15] = mConfigFileIDs.getItem("PLASMA", 21015).getInt();
        GT_Mod.instance.mItemIDs[16] = mConfigFileIDs.getItem("NCSensorCard", 21016).getInt();
        GT_Mod.instance.mItemIDs[17] = mConfigFileIDs.getItem("NCSensorKit", 21017).getInt();
        GT_Mod.instance.mItemIDs[18] = mConfigFileIDs.getItem("CheatyDevice", 21018).getInt();
        GT_Mod.instance.mItemIDs[30] = mConfigFileIDs.getItem("IronMortar", 21030).getInt();
        GT_Mod.instance.mItemIDs[31] = mConfigFileIDs.getItem("Mortar", 21031).getInt();
        GT_Mod.instance.mItemIDs[32] = mConfigFileIDs.getItem("HandheldSonictron", 21032).getInt();
        GT_Mod.instance.mItemIDs[33] = mConfigFileIDs.getItem("Destructopack", 21033).getInt();
        GT_Mod.instance.mItemIDs[34] = mConfigFileIDs.getItem("Heliumcoolant060k", 21034).getInt();
        GT_Mod.instance.mItemIDs[35] = mConfigFileIDs.getItem("Heliumcoolant120k", 21035).getInt();
        GT_Mod.instance.mItemIDs[36] = mConfigFileIDs.getItem("Heliumcoolant180k", 21036).getInt();
        GT_Mod.instance.mItemIDs[37] = mConfigFileIDs.getItem("LapotronicEnergycrystal", 21037).getInt();
        GT_Mod.instance.mItemIDs[38] = mConfigFileIDs.getItem("CloakingDevice", 21038).getInt();
        GT_Mod.instance.mItemIDs[39] = mConfigFileIDs.getItem("JackHammerIron", 21039).getInt();
        GT_Mod.instance.mItemIDs[40] = mConfigFileIDs.getItem("Neutronreflector", 21040).getInt();
        GT_Mod.instance.mItemIDs[41] = mConfigFileIDs.getItem("JackHammerSteel", 21041).getInt();
        GT_Mod.instance.mItemIDs[42] = mConfigFileIDs.getItem("JackHammerDiamond", 21042).getInt();
        GT_Mod.instance.mItemIDs[43] = mConfigFileIDs.getItem("Dataorb", 21043).getInt();
        GT_Mod.instance.mItemIDs[44] = mConfigFileIDs.getItem("Lamphelmet", 21044).getInt();
        GT_Mod.instance.mItemIDs[45] = mConfigFileIDs.getItem("Lapotronpack", 21045).getInt();
        GT_Mod.instance.mItemIDs[46] = mConfigFileIDs.getItem("Rockcutter", 21046).getInt();
        GT_Mod.instance.mItemIDs[47] = mConfigFileIDs.getItem("Teslastaff", 21047).getInt();
        GT_Mod.instance.mItemIDs[48] = mConfigFileIDs.getItem("Thorium1", 21048).getInt();
        GT_Mod.instance.mItemIDs[49] = mConfigFileIDs.getItem("Thorium2", 21049).getInt();
        GT_Mod.instance.mItemIDs[50] = mConfigFileIDs.getItem("Thorium4", 21050).getInt();
        GT_Mod.instance.mItemIDs[51] = mConfigFileIDs.getItem("Plutonium1", 21051).getInt();
        GT_Mod.instance.mItemIDs[52] = mConfigFileIDs.getItem("Plutonium2", 21052).getInt();
        GT_Mod.instance.mItemIDs[53] = mConfigFileIDs.getItem("Plutonium4", 21053).getInt();
        GT_Mod.instance.mItemIDs[54] = mConfigFileIDs.getItem("LithiumCell", 21054).getInt();
        GT_Mod.instance.mItemIDs[55] = mConfigFileIDs.getItem("DebugScanner", 21055).getInt();
        GT_Mod.instance.mItemIDs[56] = mConfigFileIDs.getItem("LithiumbatteryEmpty", 21056).getInt();
        GT_Mod.instance.mItemIDs[57] = mConfigFileIDs.getItem("LithiumbatteryFull", 21057).getInt();
        GT_Mod.instance.mItemIDs[58] = mConfigFileIDs.getItem("Lithiumpack", 21058).getInt();
        GT_Mod.instance.mItemIDs[60] = mConfigFileIDs.getItem("NaKcoolant060k", 21060).getInt();
        GT_Mod.instance.mItemIDs[61] = mConfigFileIDs.getItem("NaKcoolant120k", 21061).getInt();
        GT_Mod.instance.mItemIDs[62] = mConfigFileIDs.getItem("NaKcoolant180k", 21062).getInt();
        GT_Mod.instance.mItemIDs[63] = mConfigFileIDs.getItem("Scanner", 21063).getInt();
        GT_Mod.instance.mEnergyRuby = mConfigFileRecipes.get("cheaperrecipes", "Ruby2Energycrystal", true).getBoolean(false);
        GT_Mod.instance.mLapotronSapphire = mConfigFileRecipes.get("cheaperrecipes", "Sapphire2Lapotron", true).getBoolean(false);
        GT_Mod.instance.mPlayerdetector = mConfigFileStandard.get("addonrecipes", "Playerdetector", true).getBoolean(false);
        GT_Mod.instance.mFusionreactor = mConfigFileStandard.get("addonrecipes", "Fusionreactor", true).getBoolean(false);
        GT_Mod.instance.mLightningrod = mConfigFileStandard.get("addonrecipes", "Lightningrod", true).getBoolean(false);
        GT_Mod.instance.mQuantumchest = mConfigFileStandard.get("addonrecipes", "Quantumchest", true).getBoolean(false);
        GT_Mod.instance.mQuantumTank = mConfigFileStandard.get("addonrecipes", "Quantumtank", true).getBoolean(false);
        GT_Mod.instance.mSonictron = mConfigFileStandard.get("addonrecipes", "Sonictron", true).getBoolean(false);
        GT_Mod.instance.mIridiumblock = mConfigFileStandard.get("addonrecipes", "Iridiumblock", true).getBoolean(false);
        GT_Mod.instance.mDestructopack = mConfigFileStandard.get("addonrecipes", "Destructopack", true).getBoolean(false);
        GT_Mod.instance.mLESU = mConfigFileStandard.get("addonrecipes", "LESU", true).getBoolean(false);
        GT_Mod.instance.mIDSU = mConfigFileStandard.get("addonrecipes", "IDSU", true).getBoolean(false);
        GT_Mod.instance.mAESU = mConfigFileStandard.get("addonrecipes", "AESU", true).getBoolean(false);
        GT_Mod.instance.mChargeOMat = mConfigFileStandard.get("addonrecipes", "ChargOMat", true).getBoolean(false);
        GT_Mod.instance.mSuperconductor = mConfigFileStandard.get("addonrecipes", "Superconductorwire", true).getBoolean(false);
        GT_Mod.instance.mHeliumCoolant = mConfigFileStandard.get("addonrecipes", "Helium2Coolantcell", true).getBoolean(false);
        GT_Mod.instance.mNaKCoolant = mConfigFileStandard.get("addonrecipes", "NaKCoolantcell", true).getBoolean(false);
        GT_Mod.instance.mNeutronReflector = mConfigFileStandard.get("addonrecipes", "IridiumNeutronReflector", true).getBoolean(false);
        GT_Mod.instance.mRockcutter = mConfigFileStandard.get("addonrecipes", "Rockcutter", true).getBoolean(false);
        GT_Mod.instance.mTeslaStaff = mConfigFileStandard.get("addonrecipes", "TeslaStaff", true).getBoolean(false);
        GT_Mod.instance.mLapotronpack = mConfigFileStandard.get("addonrecipes", "Lapotronpack", true).getBoolean(false);
        GT_Mod.instance.mLightHelmet = mConfigFileStandard.get("addonrecipes", "LightHelmet", true).getBoolean(false);
        GT_Mod.instance.mMatterfabricator = mConfigFileStandard.get("addonrecipes", "Matterfabricator", true).getBoolean(false);
        GT_Mod.instance.mEAutoCrafter = mConfigFileStandard.get("addonrecipes", "ElectricAutocrafter", true).getBoolean(false);
        GT_Mod.instance.mRockBreaker = mConfigFileStandard.get("addonrecipes", "RockBreaker", true).getBoolean(false);
        GT_Mod.instance.mLithiumpack = mConfigFileStandard.get("addonrecipes", "Lithiumpack", true).getBoolean(false);
        GT_Mod.instance.mLithiumbattery = mConfigFileStandard.get("addonrecipes", "Lithiumbattery", true).getBoolean(false);
        GT_Mod.instance.mAutomationStuff = mConfigFileStandard.get("addonrecipes", "AutomationMachines", true).getBoolean(false);
        GT_Mod.instance.mCloakingDevice = mConfigFileStandard.get("addonrecipes", "CloakingDevice", true).getBoolean(false);
        GT_Mod.instance.mItemClearer = mConfigFileStandard.get("addonrecipes", "ItemClearer", true).getBoolean(false);
        GT_Mod.instance.mCropHarvestor = mConfigFileStandard.get("addonrecipes", "CropHarvestor", true).getBoolean(false);
        GT_Mod.instance.mScrapboxinator = mConfigFileStandard.get("addonrecipes", "Scrapboxinator", true).getBoolean(false);
        GT_Mod.instance.mGrinder = mConfigFileStandard.get("addonrecipes", "IndustrialGrinder", true).getBoolean(false);
        GT_Mod.instance.mJackHammer = mConfigFileStandard.get("addonrecipes", "JackHammer", true).getBoolean(false);
        GT_Mod.instance.mSawmill = mConfigFileStandard.get("addonrecipes", "Sawmill", true).getBoolean(false);
        GT_Mod.instance.mDragonEggSiphon = mConfigFileStandard.get("addonrecipes", "DragonEggEnergySiphon", true).getBoolean(false);
        GT_Mod.instance.mMagicAbsorber = mConfigFileStandard.get("addonrecipes", "MagicEnergyAbsorber", true).getBoolean(false);
        GT_Mod.instance.mDistillationTower = mConfigFileStandard.get("addonrecipes", "DistillationTower", true).getBoolean(false);
        GT_Mod.instance.mAdvSafe = mConfigFileStandard.get("addonrecipes", "AdvancedSafe", true).getBoolean(false);
        GT_Mod.instance.mAdvancedPump = mConfigFileStandard.get("addonrecipes", "AdvancedPump", true).getBoolean(false);
        GT_Mod.instance.mDigitalChest = mConfigFileStandard.get("addonrecipes", "DigitalChest", true).getBoolean(false);
        GT_Mod.instance.mAutoMachines = mConfigFileStandard.get("addonrecipes", "AutomaticBasicMachines", true).getBoolean(false);
        GT_Mod.instance.mWiremill = mConfigFileStandard.get("addonrecipes", "Wiremill", true).getBoolean(false);
        GT_Mod.instance.mAlloySmelter = mConfigFileStandard.get("addonrecipes", "AlloySmelter", true).getBoolean(false);
        GT_Mod.instance.mBetterNukeRecipe = mConfigFileRecipes.get("usefullrecipes", "ExpensiveNukeRecipe", true).getBoolean(false);
        GT_Mod.instance.mBetterSolarRecipe = mConfigFileRecipes.get("usefullrecipes", "ExpensiveSolarRecipe", true).getBoolean(false);
        GT_Mod.instance.mBetterWindRecipe = mConfigFileRecipes.get("usefullrecipes", "ExpensiveWindRecipe", true).getBoolean(false);
        GT_Mod.instance.mTincellCount = Math.min(64, Math.max(1, mConfigFileRecipes.get("usefullrecipes", "TincellsPer4Tin", 4).getInt()));
        GT_Mod.instance.mBetterMaceratorRecipe = mConfigFileRecipes.get("usefullrecipes", "ExpensiveMaceratorRecipe", true).getBoolean(false);
        GT_Mod.instance.mBetterWatermillRecipe = mConfigFileRecipes.get("usefullrecipes", "ExpensiveWatermillRecipe", true).getBoolean(false);
        GT_Mod.instance.mBetterQuarryRecipe = mConfigFileRecipes.get("usefullrecipes", "ExpensiveBCQuarryRecipe", true).getBoolean(false);
        GT_Mod.instance.mRollingmachineAlloy = mConfigFileRecipes.get("usefullrecipes", "MMIngotOnlyRollingMachine", true).getBoolean(false);
        GT_Mod.instance.mSteelETools = mConfigFileRecipes.get("usefullrecipes", "ElectricSteelTools", true).getBoolean(false);
        GT_Mod.instance.mDiamondDrillTitanium = mConfigFileRecipes.get("usefullrecipes", "TitaniumDiamondDrill", true).getBoolean(false);
        GT_Mod.instance.mHeliumLaser = mConfigFileRecipes.get("usefullrecipes", "ExpensiveLaser", true).getBoolean(false);
        GT_Mod.instance.mBeryliumReflector = mConfigFileRecipes.get("usefullrecipes", "BeryliumReflector", true).getBoolean(false);
        GT_Mod.instance.mHardIridiumPlate = mConfigFileRecipes.get("usefullrecipes", "DifficultIridiumPlate", true).getBoolean(false);
        GT_Mod.instance.mExpensiveNanosaber = mConfigFileRecipes.get("usefullrecipes", "ExpensiveNanoSaber", true).getBoolean(false);
        GT_Mod.instance.mQSuithelmet = mConfigFileRecipes.get("ic2recipes", "QSuithelmet", true).getBoolean(false);
        GT_Mod.instance.mQSuitplate = mConfigFileRecipes.get("ic2recipes", "QSuitplate", true).getBoolean(false);
        GT_Mod.instance.mQSuitpants = mConfigFileRecipes.get("ic2recipes", "QSuitpants", true).getBoolean(false);
        GT_Mod.instance.mQSuitshoes = mConfigFileRecipes.get("ic2recipes", "QSuitshoes", true).getBoolean(false);
        GT_Mod.instance.mMassfabricator = mConfigFileRecipes.get("ic2recipes", "Massfabricator", false).getBoolean(false);
        GT_Mod.instance.mDungeonDisc = mConfigFileStandard.get("dungeon", "DungeonMusicDiscFix", false).getBoolean(false);
        GT_Mod.instance.mDungeonLoot = mConfigFileStandard.get("dungeon", "DungeonLoot", true).getBoolean(false);
        GT_Mod.instance.mDungeonCredits = mConfigFileStandard.get("dungeon", "DungeonICredits", false).getBoolean(false);
        GT_Worldgenerator.sIridiumProbability = mConfigFileStandard.get("worldgeneration", "IridiumProbability", 20).getInt();
        GT_Worldgenerator.sAsteroids = mConfigFileStandard.get("worldgeneration", "EnderAsteroids", true).getBoolean(false);
        GT_Worldgenerator.sGeneratedOres[1] = mConfigFileStandard.get("worldgeneration", "Galenaore", true).getBoolean(false);
        GT_Worldgenerator.sGeneratedOres[2] = mConfigFileStandard.get("worldgeneration", "Iridiumore", true).getBoolean(false);
        GT_Worldgenerator.sGeneratedOres[3] = mConfigFileStandard.get("worldgeneration", "Rubyore", true).getBoolean(false);
        GT_Worldgenerator.sGeneratedOres[4] = mConfigFileStandard.get("worldgeneration", "Sapphireore", true).getBoolean(false);
        GT_Worldgenerator.sGeneratedOres[5] = mConfigFileStandard.get("worldgeneration", "Bauxiteore", true).getBoolean(false);
        GT_Worldgenerator.sGeneratedOres[6] = mConfigFileStandard.get("worldgeneration", "Pyriteore", true).getBoolean(false);
        GT_Worldgenerator.sGeneratedOres[7] = mConfigFileStandard.get("worldgeneration", "Cinnabarore", true).getBoolean(false);
        GT_Worldgenerator.sGeneratedOres[8] = mConfigFileStandard.get("worldgeneration", "Sphaleriteore", true).getBoolean(false);
        GT_Worldgenerator.sGeneratedOres[9] = mConfigFileStandard.get("worldgeneration", "Tungstateore", true).getBoolean(false);
        GT_Worldgenerator.sGeneratedOres[10] = mConfigFileStandard.get("worldgeneration", "Cooperiteore", true).getBoolean(false);
        GT_Worldgenerator.sGeneratedOres[11] = mConfigFileStandard.get("worldgeneration", "Olivineore", true).getBoolean(false);
        GT_Worldgenerator.sGeneratedOres[12] = mConfigFileStandard.get("worldgeneration", "Sodaliteore", true).getBoolean(false);
        GT_Mod.instance.mTungstenBlastFurnace = mConfigFileStandard.get("basicmetalblastfurnacerequirement", "Tungsten", true).getBoolean(false);
        GT_Mod.instance.mTitaniumBlastFurnace = mConfigFileStandard.get("basicmetalblastfurnacerequirement", "Titanium", true).getBoolean(false);
        GT_Mod.instance.mAluminiumBlastFurnace = mConfigFileStandard.get("basicmetalblastfurnacerequirement", "Aluminium", true).getBoolean(false);
        GT_Mod.instance.mChromeBlastFurnace = mConfigFileStandard.get("basicmetalblastfurnacerequirement", "Chrome", true).getBoolean(false);
        GT_Mod.instance.mSteelBlastFurnace = mConfigFileStandard.get("basicmetalblastfurnacerequirement", "Steel", true).getBoolean(false);
        GT_Mod.instance.mMachineFlammable = mConfigFileStandard.get("machines", "machines_flammable", true).getBoolean(false);
        GT_Mod.instance.mMachineFireExplosions = mConfigFileStandard.get("machines", "fire_causes_explosions", true).getBoolean(false);
        GT_Mod.instance.mMachineNonWrenchExplosions = mConfigFileStandard.get("machines", "explosions_on_nonwrenching", true).getBoolean(false);
        GT_Mod.instance.mMachineWireFire = mConfigFileStandard.get("machines", "wirefire_on_explosion", true).getBoolean(false);
        GT_Mod.instance.mMachineRainExplosions = mConfigFileStandard.get("machines", "rain_causes_explosions", true).getBoolean(false);
        GT_Mod.instance.mMachineThunderExplosions = mConfigFileStandard.get("machines", "lightning_causes_explosions", true).getBoolean(false);
        GT_Mod.instance.mConstantEnergy = mConfigFileStandard.get("machines", "constant_need_of_energy", true).getBoolean(false);
        mUnificatorTC = mConfigFileStandard.get("unificatortargets", "Thaumcraft", true).getBoolean(false);
        mUnificatorRP = mConfigFileStandard.get("unificatortargets", "Redpower", true).getBoolean(false);
        mUnificatorRC = mConfigFileStandard.get("unificatortargets", "Railcraft", false).getBoolean(false);
        mUnificatorTE = mConfigFileStandard.get("unificatortargets", "ThermalExpansion", false).getBoolean(false);
        mUnificatorFR = mConfigFileStandard.get("unificatortargets", "Forestry", false).getBoolean(false);
        GT_Mod.instance.mEnchantmentTable = mConfigFileStandard.get("features", "EnchantmentTableAllowed", true).getBoolean(false);
        GT_Mod.instance.mBlockBreakerNerf = mConfigFileStandard.get("features", "RPBlockBreakerNerf", false).getBoolean(false);
        GT_Mod.instance.mForestryBronzeNerf = mConfigFileStandard.get("features", "ForestryBronzeNerf", true).getBoolean(false);
        GT_Mod.instance.mNoStoneRecycling = mConfigFileStandard.get("features", "DisableStoneInRecycler", false).getBoolean(false);
        GT_Mod.instance.mNoMobRecycling = mConfigFileStandard.get("features", "DisableEasyMobGrindersInRecycler", false).getBoolean(false);
        GT_Mod.instance.mReactorplanner = mConfigFileStandard.get("features", "Reactorplanner", true).getBoolean(false);
        GT_Mod.instance.mSeedscanner = mConfigFileStandard.get("features", "Seedscanner", true).getBoolean(false);
        GT_TileEntity_Matterfabricator.sMatterFabricationRate = mConfigFileStandard.get("features", "MatterFabricationRate", 16666666).getInt();
        GT_MetaTileEntity_MagicEnergyAbsorber.sEnergyPerEnderCrystal = mConfigFileStandard.get("features", "EnderCrystalEnergyPerTick", 32).getInt();
        GT_MetaTileEntity_MagicEnergyAbsorber.sEnergyFromVis = mConfigFileStandard.get("features", "VisEnergyPerUnit", 12800).getInt();
        GT_MetaTileEntity_DragonEggEnergySiphon.sDragonEggEnergyPerTick = mConfigFileStandard.get("features", "DragonEggEnergyPerTick", 128).getInt();
        GT_MetaTileEntity_DragonEggEnergySiphon.sAllowMultipleEggs = mConfigFileStandard.get("features", "AllowDuplicatedDragonEggs", false).getBoolean(false);
        GT_Mod.instance.mUpgradeCount = Math.min(64, Math.max(1, mConfigFileStandard.get("features", "UpgradeStacksize", 4).getInt()));
        GT_Mod.instance.mOreStackSize = Math.min(64, Math.max(16, mConfigFileStandard.get("features", "MaxOreStackSize", 64).getInt()));
        GT_Mod.instance.mWoodStackSize = Math.min(64, Math.max(16, mConfigFileStandard.get("features", "MaxLogStackSize", 64).getInt()));
        GT_Mod.instance.mPlankStackSize = Math.min(64, Math.max(16, mConfigFileStandard.get("features", "MaxPlankStackSize", 64).getInt()));
        GT_Mod.instance.mBlockStackSize = Math.min(64, Math.max(16, mConfigFileStandard.get("features", "MaxOtherBlockStackSize", 64).getInt()));
        GT_Mod.instance.mBarrelItemCount = Math.max(193, mConfigFileStandard.get("features", "DigitalChestMaxItemCount", 32768).getInt());
        GT_Mod.instance.mQuantumItemCount = Math.max(GT_Mod.instance.mBarrelItemCount, mConfigFileStandard.get("features", "QuantumChestMaxItemCount", 2000000000).getInt());
        if (GT_TileEntity_Matterfabricator.sMatterFabricationRate <= 0) {
            GT_TileEntity_Matterfabricator.sMatterFabricationRate = 166666666;
        }
        GT_Mod.instance.mTincellCount = Math.min(64, Math.max(1, GT_Mod.instance.mTincellCount));
        if (GT_Mod.instance.mMassfabricator && GT_TileEntity_Matterfabricator.sMatterFabricationRate > 150000) {
            GT_TileEntity_Matterfabricator.sMatterFabricationRate = 150000;
        }
        mConfigFileStandard.save();
        mConfigFileRecipes.save();
        mConfigFileIDs.save();
    }

    public String getStackConfigName(ItemStack aStack) {
        if (aStack == null) {
            return null;
        }
        String rName = GT_OreDictUnificator.getAssociation(aStack);
        if (rName != null && !rName.equals("")) {
            return rName;
        }
        try {
            rName = aStack.getItemName();
            if (rName != null && !rName.equals("")) {
                return rName;
            }
        }
        catch (Throwable throwable) {
            // empty catch block
        }
        return aStack.itemID + "." + aStack.getItemDamage();
    }

    public boolean addAdvConfig(String aCategory, ItemStack aStack, boolean aDefault) {
        return this.addAdvConfig(aCategory, this.getStackConfigName(aStack), aDefault);
    }

    public boolean addAdvConfig(String aCategory, String aName, boolean aDefault) {
        if (aName == null) {
            return aDefault;
        }
        mConfigFileAdvRecipes.load();
        Property tProperty = mConfigFileAdvRecipes.get(aCategory, aName, aDefault);
        boolean rResult = tProperty.getBoolean(aDefault);
        if (!tProperty.wasRead()) {
            mConfigFileAdvRecipes.save();
        }
        return rResult;
    }

    public int addAdvConfig(String aCategory, ItemStack aStack, int aDefault) {
        return this.addAdvConfig(aCategory, this.getStackConfigName(aStack), aDefault);
    }

    public int addAdvConfig(String aCategory, String aName, int aDefault) {
        if (aName == null) {
            return aDefault;
        }
        mConfigFileAdvRecipes.load();
        Property tProperty = mConfigFileAdvRecipes.get(aCategory, aName, aDefault);
        int rResult = tProperty.getInt(aDefault);
        if (!tProperty.wasRead()) {
            mConfigFileAdvRecipes.save();
        }
        return rResult;
    }
}

