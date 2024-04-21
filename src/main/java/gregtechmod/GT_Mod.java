/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  codechicken.nei.api.API
 *  cpw.mods.fml.common.Mod
 *  cpw.mods.fml.common.Mod$Init
 *  cpw.mods.fml.common.Mod$Instance
 *  cpw.mods.fml.common.Mod$PostInit
 *  cpw.mods.fml.common.Mod$PreInit
 *  cpw.mods.fml.common.Mod$ServerStarting
 *  cpw.mods.fml.common.Mod$ServerStopping
 *  cpw.mods.fml.common.SidedProxy
 *  cpw.mods.fml.common.event.FMLInitializationEvent
 *  cpw.mods.fml.common.event.FMLPostInitializationEvent
 *  cpw.mods.fml.common.event.FMLPreInitializationEvent
 *  cpw.mods.fml.common.event.FMLServerStartingEvent
 *  cpw.mods.fml.common.event.FMLServerStoppingEvent
 *  cpw.mods.fml.common.network.NetworkMod
 *  cpw.mods.fml.common.registry.GameRegistry
 *  cpw.mods.fml.common.registry.LanguageRegistry
 *  cpw.mods.fml.relauncher.Side
 *  ic2.core.item.ItemScrapbox
 *  net.minecraft.block.Block
 *  net.minecraft.creativetab.CreativeTabs
 *  net.minecraft.enchantment.Enchantment
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemBlock
 *  net.minecraft.item.ItemFood
 *  net.minecraft.item.ItemStack
 *  net.minecraft.item.crafting.IRecipe
 *  net.minecraft.nbt.CompressedStreamTools
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.nbt.NBTTagList
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.world.ChunkPosition
 *  net.minecraft.world.World
 *  net.minecraft.world.biome.BiomeGenBase
 *  net.minecraft.world.storage.SaveHandler
 *  net.minecraftforge.common.Configuration
 *  net.minecraftforge.common.DungeonHooks
 *  net.minecraftforge.common.ForgeHooks
 *  net.minecraftforge.liquids.LiquidContainerData
 *  net.minecraftforge.liquids.LiquidContainerRegistry
 *  net.minecraftforge.liquids.LiquidDictionary
 *  net.minecraftforge.liquids.LiquidStack
 *  net.minecraftforge.oredict.OreDictionary
 *  net.minecraftforge.oredict.ShapedOreRecipe
 *  net.minecraftforge.oredict.ShapelessOreRecipe
 *  railcraft.common.api.core.items.ItemRegistry
 *  thermalexpansion.api.core.ItemRegistry
 */
package gregtechmod;

import codechicken.nei.api.API;
import cpw.mods.fml.common.Mod;
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
import gregtechmod.api.BaseMetaTileEntity;
import gregtechmod.api.GT_Recipe;
import gregtechmod.api.GregTech_API;
import gregtechmod.api.IMachineBlockUpdateable;
import gregtechmod.common.GT_ComputercubeDescription;
import gregtechmod.common.GT_Config;
import gregtechmod.common.GT_ConnectionHandler;
import gregtechmod.common.GT_CreativeTab;
import gregtechmod.common.GT_DummyWorld;
import gregtechmod.common.GT_ItsNotMyFaultException;
import gregtechmod.common.GT_LanguageManager;
import gregtechmod.common.GT_Log;
import gregtechmod.common.GT_ModHandler;
import gregtechmod.common.GT_OreDictHandler;
import gregtechmod.common.GT_OreDictUnificator;
import gregtechmod.common.GT_PacketHandler;
import gregtechmod.common.GT_Proxy;
import gregtechmod.common.GT_Utility;
import gregtechmod.common.blocks.GT_BlockMetaID_Block;
import gregtechmod.common.blocks.GT_BlockMetaID_Block2;
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
import gregtechmod.common.items.GT_MetaBlock2_Item;
import gregtechmod.common.items.GT_MetaBlock_Item;
import gregtechmod.common.items.GT_MetaItem_Abstract;
import gregtechmod.common.items.GT_MetaItem_Cell;
import gregtechmod.common.items.GT_MetaItem_Component;
import gregtechmod.common.items.GT_MetaItem_Dust;
import gregtechmod.common.items.GT_MetaItem_Gas;
import gregtechmod.common.items.GT_MetaItem_Liquid;
import gregtechmod.common.items.GT_MetaItem_Material;
import gregtechmod.common.items.GT_MetaItem_Nugget;
import gregtechmod.common.items.GT_MetaItem_Plasma;
import gregtechmod.common.items.GT_MetaItem_SmallDust;
import gregtechmod.common.items.GT_MetaMachine_Item;
import gregtechmod.common.items.GT_MetaOre_Item;
import gregtechmod.common.items.GT_Mortar_Item;
import gregtechmod.common.items.GT_NeutronReflector_Item;
import gregtechmod.common.items.GT_RadioactiveCell_Item;
import gregtechmod.common.items.GT_Rockcutter_Item;
import gregtechmod.common.items.GT_Scanner_Item;
import gregtechmod.common.items.GT_SensorCard_Item;
import gregtechmod.common.items.GT_SensorKit_Item;
import gregtechmod.common.items.GT_Sonictron_Item;
import gregtechmod.common.items.GT_Teslastaff_Item;
import gregtechmod.common.tileentities.GT_MetaTileEntity_AdvancedPump;
import gregtechmod.common.tileentities.GT_MetaTileEntity_AdvancedTranslocator;
import gregtechmod.common.tileentities.GT_MetaTileEntity_AlloySmelter;
import gregtechmod.common.tileentities.GT_MetaTileEntity_Assembler;
import gregtechmod.common.tileentities.GT_MetaTileEntity_Barrel;
import gregtechmod.common.tileentities.GT_MetaTileEntity_Bender;
import gregtechmod.common.tileentities.GT_MetaTileEntity_BlastFurnace;
import gregtechmod.common.tileentities.GT_MetaTileEntity_Canner;
import gregtechmod.common.tileentities.GT_MetaTileEntity_Centrifuge;
import gregtechmod.common.tileentities.GT_MetaTileEntity_ChemicalReactor;
import gregtechmod.common.tileentities.GT_MetaTileEntity_Compressor;
import gregtechmod.common.tileentities.GT_MetaTileEntity_CropHarvestor;
import gregtechmod.common.tileentities.GT_MetaTileEntity_DieselGenerator;
import gregtechmod.common.tileentities.GT_MetaTileEntity_DistillationTower;
import gregtechmod.common.tileentities.GT_MetaTileEntity_DragonEggEnergySiphon;
import gregtechmod.common.tileentities.GT_MetaTileEntity_E_Furnace;
import gregtechmod.common.tileentities.GT_MetaTileEntity_ElectricAutoWorkbench;
import gregtechmod.common.tileentities.GT_MetaTileEntity_ElectricBufferAdvanced;
import gregtechmod.common.tileentities.GT_MetaTileEntity_ElectricBufferLarge;
import gregtechmod.common.tileentities.GT_MetaTileEntity_ElectricBufferSmall;
import gregtechmod.common.tileentities.GT_MetaTileEntity_ElectricInventoryManager;
import gregtechmod.common.tileentities.GT_MetaTileEntity_ElectricItemClearer;
import gregtechmod.common.tileentities.GT_MetaTileEntity_ElectricRegulatorAdvanced;
import gregtechmod.common.tileentities.GT_MetaTileEntity_ElectricSorter;
import gregtechmod.common.tileentities.GT_MetaTileEntity_ElectricTypeSorter;
import gregtechmod.common.tileentities.GT_MetaTileEntity_Electrolyzer;
import gregtechmod.common.tileentities.GT_MetaTileEntity_Extractor;
import gregtechmod.common.tileentities.GT_MetaTileEntity_FusionComputer;
import gregtechmod.common.tileentities.GT_MetaTileEntity_FusionEnergyInjector;
import gregtechmod.common.tileentities.GT_MetaTileEntity_FusionExtractor;
import gregtechmod.common.tileentities.GT_MetaTileEntity_FusionInjector;
import gregtechmod.common.tileentities.GT_MetaTileEntity_GasTurbine;
import gregtechmod.common.tileentities.GT_MetaTileEntity_Grinder;
import gregtechmod.common.tileentities.GT_MetaTileEntity_ImplosionCompressor;
import gregtechmod.common.tileentities.GT_MetaTileEntity_Macerator;
import gregtechmod.common.tileentities.GT_MetaTileEntity_MagicEnergyAbsorber;
import gregtechmod.common.tileentities.GT_MetaTileEntity_MagicEnergyConverter;
import gregtechmod.common.tileentities.GT_MetaTileEntity_PlasmaGenerator;
import gregtechmod.common.tileentities.GT_MetaTileEntity_Printer;
import gregtechmod.common.tileentities.GT_MetaTileEntity_QuantumChest;
import gregtechmod.common.tileentities.GT_MetaTileEntity_Quantumtank;
import gregtechmod.common.tileentities.GT_MetaTileEntity_Recycler;
import gregtechmod.common.tileentities.GT_MetaTileEntity_RockBreaker;
import gregtechmod.common.tileentities.GT_MetaTileEntity_Safe;
import gregtechmod.common.tileentities.GT_MetaTileEntity_Sawmill;
import gregtechmod.common.tileentities.GT_MetaTileEntity_Scrapboxinator;
import gregtechmod.common.tileentities.GT_MetaTileEntity_SemifluidGenerator;
import gregtechmod.common.tileentities.GT_MetaTileEntity_Shelf;
import gregtechmod.common.tileentities.GT_MetaTileEntity_Shelf_Compartment;
import gregtechmod.common.tileentities.GT_MetaTileEntity_Shelf_Desk;
import gregtechmod.common.tileentities.GT_MetaTileEntity_Shelf_FileCabinet;
import gregtechmod.common.tileentities.GT_MetaTileEntity_Shelf_Iron;
import gregtechmod.common.tileentities.GT_MetaTileEntity_ThermalGenerator;
import gregtechmod.common.tileentities.GT_MetaTileEntity_Translocator;
import gregtechmod.common.tileentities.GT_MetaTileEntity_VacuumFreezer;
import gregtechmod.common.tileentities.GT_MetaTileEntity_Wiremill;
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
import ic2.core.item.ItemScrapbox;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.nbt.CompressedStreamTools;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.ChunkPosition;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.storage.SaveHandler;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.DungeonHooks;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.liquids.LiquidContainerData;
import net.minecraftforge.liquids.LiquidContainerRegistry;
import net.minecraftforge.liquids.LiquidDictionary;
import net.minecraftforge.liquids.LiquidStack;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;
import railcraft.common.api.core.items.ItemRegistry;

@Mod(modid="GregTech_Addon", name="GregTech-Addon", version="MC146_V2.90", useMetadata=false, dependencies="required-after:IC2; after:factorization; after:Railcraft; after:ThermalExpansion; after:ThermalExpansion|Transport; after:ThermalExpansion|Energy; after:ThermalExpansion|Factory; after:XyCraft; after:MetallurgyCore; after:MetallurgyBase; after:MetallurgyEnder; after:MetallurgyFantasy; after:MetallurgyNether; after:MetallurgyPrecious; after:MetallurgyUtility; after:BuildCraft|Silicon; after:BuildCraft|Core; after:BuildCraft|Transport; after:BuildCraft|Factory; after:BuildCraft|Energy; after:BuildCraft|Builders; after:LiquidUU; after:TwilightForest; after:Forestry; after:RedPowerCore; after:RedPowerBase; after:RedPowerMachine; after:RedPowerCompat; after:RedPowerWiring; after:RedPowerLogic; after:RedPowerLighting; after:RedPowerWorld; after:RedPowerControl; after:Tubestuff; after:ICBM; after:Mekanism; after:MekanismGenerators; after:MekanismTools;")
@NetworkMod(clientSideRequired=true, serverSideRequired=false, channels={"gregtech"}, packetHandler=GT_PacketHandler.class, connectionHandler=GT_ConnectionHandler.class)
public class GT_Mod {
    @Mod.Instance
    public static GT_Mod instance;
    @SidedProxy(clientSide="gregtechmod.common.GT_Client", serverSide="gregtechmod.common.GT_Proxy")
    public static GT_Proxy gregtechproxy;
    public static Random Randomizer;
    public static final CreativeTabs tabGregTech;
    public static final GT_OreDictHandler sOreDict;
    public static GT_Config mConfig;
    public static File mLogFile;
    public boolean mAlloySmelter;
    public boolean mWiremill;
    public boolean mAutoMachines;
    public boolean mDigitalChest;
    public boolean mAdvancedPump;
    public boolean mAdvSafe;
    public boolean mEnchantmentTable;
    public boolean mMachineFlammable;
    public boolean mMachineNonWrenchExplosions;
    public boolean mMachineRainExplosions;
    public boolean mMachineThunderExplosions;
    public boolean mMachineFireExplosions;
    public boolean mMachineWireFire;
    public boolean mTungstenBlastFurnace;
    public boolean mTitaniumBlastFurnace;
    public boolean mAluminiumBlastFurnace;
    public boolean mChromeBlastFurnace;
    public boolean mSteelBlastFurnace;
    public boolean mExpensiveNanosaber;
    public boolean mBlockBreakerNerf;
    public boolean mDistillationTower;
    public boolean mMagicAbsorber;
    public boolean mDragonEggSiphon;
    public boolean mForestryBronzeNerf;
    public boolean mQuantumTank;
    public boolean mSawmill;
    public boolean mHardIridiumPlate;
    public boolean mConstantEnergy;
    public boolean mDiamondDrillTitanium;
    public boolean mNoMobRecycling;
    public boolean mNoStoneRecycling;
    public boolean mScrapboxinator;
    public boolean mCropHarvestor;
    public boolean mItemClearer;
    public boolean mCloakingDevice;
    public boolean mRockBreaker;
    public boolean mAutomationStuff;
    public boolean mLithiumbattery;
    public boolean mLithiumpack;
    public boolean mEAutoCrafter;
    public boolean mSteelETools;
    public boolean mMassfabricator;
    public boolean mRollingmachineAlloy;
    public boolean mAnimations;
    public boolean mMatterfabricator;
    public boolean mBetterQuarryRecipe;
    public boolean mBetterWatermillRecipe;
    public boolean mBetterMaceratorRecipe;
    public boolean mPlayerdetector;
    public boolean mFusionreactor;
    public boolean mLightningrod;
    public boolean mQuantumchest;
    public boolean mIridiumblock;
    public boolean mOnline = true;
    public boolean mPreLoaded = false;
    public boolean mLoaded = false;
    public boolean mPostLoaded = false;
    public boolean mAlreadyPlayed = false;
    public boolean mShowCapes = true;
    public boolean mDetectIDConflicts;
    public boolean mDebugMode;
    public boolean mJackHammer;
    public boolean mBeryliumReflector;
    public boolean mGrinder;
    public boolean mBetterWindRecipe;
    public boolean mQSuithelmet;
    public boolean mQSuitplate;
    public boolean mQSuitpants;
    public boolean mQSuitshoes;
    public boolean mLightHelmet;
    public boolean mBetterSolarRecipe;
    public boolean mBetterNukeRecipe;
    public boolean mSuperconductor;
    public boolean mLapotronpack;
    public boolean mTeslaStaff;
    public boolean mRockcutter;
    public boolean mNeutronReflector;
    public boolean mDungeonCredits;
    public boolean mChargeOMat;
    public boolean mAESU;
    public boolean mDoNotInit;
    public boolean mIDSU;
    public boolean mSeedscanner;
    public boolean mReactorplanner;
    public boolean mHeliumCoolant;
    public boolean mNaKCoolant;
    public boolean mLESU;
    public boolean mHeliumLaser;
    public boolean mDestructopack;
    public boolean mDungeonDisc;
    public boolean mDungeonLoot;
    public boolean mSonictron;
    public boolean mLapotronSapphire;
    public boolean mEnergyRuby;
    public boolean mDebug;
    public int mUpgradeCount = 4;
    public int mTincellCount = 8;
    public int mBarrelItemCount = 32768;
    public int mQuantumItemCount = 2000000000;
    public int mBlockStackSize = 64;
    public int mOreStackSize = 64;
    public int mWoodStackSize = 64;
    public int mPlankStackSize = 64;
    public final Block[] mBlocks = new Block[5];
    public final Item[] mItems = new Item[256];
    public final int[] mItemIDs = new int[256];
    public final int[] mBlockIDs = new int[]{4058, 4059, 4060, 4061, 4057};
    public static World mUniverse;
    public static final ArrayList sPremiumNames;
    public static final ArrayList sAdminNames;
    public static final ArrayList mBrainTechCapeList;
    public static final ArrayList mGregTechCapeList;
    public static final ArrayList mRubyList;
    public static final ArrayList mSapphireList;
    public static final ArrayList mBauxiteList;
    public static Configuration sLangFile;
    public static final ArrayList mSoundNames;
    public static final ArrayList mSoundItems;
    public static final ArrayList mSoundCounts;
    public static final HashMap mMachineIDs;

    public GT_Mod() {
        mGregTechCapeList.add("SpwnX");
        mGregTechCapeList.add("Kadah");
        mGregTechCapeList.add("bsaa");
        mGregTechCapeList.add("Stute");
        mGregTechCapeList.add("Hotchi");
        mGregTechCapeList.add("jagoly");
        mGregTechCapeList.add("nallar");
        mGregTechCapeList.add("meep310");
        mGregTechCapeList.add("Seldron");
        mGregTechCapeList.add("Neonbeta");
        mGregTechCapeList.add("yinscape");
        mGregTechCapeList.add("voooon24");
        mGregTechCapeList.add("Quintine");
        mGregTechCapeList.add("peach774");
        mGregTechCapeList.add("djshiny99");
        mGregTechCapeList.add("megatronp");
        mGregTechCapeList.add("DZCreeper");
        mGregTechCapeList.add("Kane_Hart");
        mGregTechCapeList.add("Truculent");
        mGregTechCapeList.add("vidplace7");
        mGregTechCapeList.add("simon6689");
        mGregTechCapeList.add("UnknownXLV");
        mGregTechCapeList.add("cublikefoot");
        mGregTechCapeList.add("chainman564");
        mGregTechCapeList.add("michaelbrady");
        mGregTechCapeList.add("GarretSidzaka");
        mGregTechCapeList.add("The_Hypersonic");
        mGregTechCapeList.add("CrafterOfMines57");
        mGregTechCapeList.add("adamros");
        mGregTechCapeList.add("alexbegt");
        mBrainTechCapeList.add("Friedi4321");
    }

    @Mod.PreInit
    public void preload(FMLPreInitializationEvent aEvent) {
        if (GregTech_API.isGregTechLoaded()) {
            throw new GT_ItsNotMyFaultException("Why did you install my Addon twice? Remove the second gregtechmod.zip out of your mods-Folder, you need only one of them. And another Question: Why the Hack did Forge not detect that before me?");
        }
        GregTech_API.setGregTechLoaded();
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
            this.mAlreadyPlayed = true;
            try {
                GT_Log.out = new PrintStream(mLogFile);
            }
            catch (FileNotFoundException e) {
                GT_Log.out = System.out;
            }
        } else {
            GT_Log.out = System.out;
        }
        boolean bl = this.mDoNotInit = tFile.getAbsolutePath().contains(".technic") || tFile.getAbsolutePath().contains("tekkit") || tFile.getAbsolutePath().contains(".Technic") || tFile.getAbsolutePath().contains("Tekkit");
        if (this.mDoNotInit) {
            GT_Log.out.println("GT_Mod: Detected Technic Launcher.");
            GT_Log.out.println("GT_Mod: Errored.");
            System.err.println("*");
            System.err.println("*");
            System.err.println("*");
            System.err.println("Hello, Gregorius Techneticies here,");
            System.err.println("I see you most likely use Tekkit, but this Mod won't load, until Kakermix asks me PERSONALLY for the inclusion of my Mod. So bug HIM for it.");
            System.err.println("PS. I could have exploded your Worlds, but i didn't for Publicityreasons.");
            System.err.println("PPS. This Addon is Part of the FTB-Pack. It's even easier to use than Tekkit");
            System.err.println("*");
            System.err.println("*");
            System.err.println("*");
            tConfig1.get("Switching this won't help", "Tekkitsupport", false);
            tConfig1.save();
            return;
        }
        GT_Log.out.println("GT_Mod: Creating Config Object.");
        mConfig = new GT_Config(tConfig1, tConfig2, tConfig3, tConfig4);
        GT_Log.out.println("GT_Mod: Generating Lang-File");
        sLangFile = new Configuration(new File(new File(aEvent.getModConfigurationDirectory(), "GregTech"), "GregTech.lang"));
        sLangFile.load();
        GT_Log.out.println("GT_Mod: Removing all Scrapbox Drops.");
        while (ItemScrapbox.dropList.size() > 0) {
            ItemScrapbox.dropList.remove(0);
        }
        GT_Log.out.println("GT_Mod: Adding Scrap with a Chance of 200.00F to the Scrapbox Drops.");
        GT_ModHandler.addScrapboxDrop(200.0f, GT_ModHandler.getIC2Item("scrap", 1));
        this.mPreLoaded = true;
        GT_Log.out.println("GT_Mod: Preload-Phase finished!");
    }

    @Mod.Init
    public void load(FMLInitializationEvent aEvent) {
        if (this.mDoNotInit) {
            return;
        }
        GT_Log.out.println("GT_Mod: Beginning Load-Phase.");
        Object tStack = null;
        Object tStack2 = null;
        Object tStack3 = null;
        GT_Log.out.println("GT_Mod: Adding Blocks.");
        this.mBlocks[0] = new GT_BlockMetaID_Block(this.mBlockIDs[0]);
        GameRegistry.registerBlock((Block)this.mBlocks[0], GT_MetaBlock_Item.class, (String)GT_LanguageManager.mNameList0[0], (String)"GregTech_Addon");
        this.mBlocks[1] = new GT_BlockMetaID_Machine(this.mBlockIDs[1]);
        GameRegistry.registerBlock((Block)this.mBlocks[1], GT_MetaMachine_Item.class, (String)GT_LanguageManager.mNameList1[0], (String)"GregTech_Addon");
        this.mBlocks[2] = new GT_BlockMetaID_Ore(this.mBlockIDs[2]);
        GameRegistry.registerBlock((Block)this.mBlocks[2], GT_MetaOre_Item.class, (String)GT_LanguageManager.mNameList2[0], (String)"GregTech_Addon");
        this.mBlocks[4] = new GT_BlockMetaID_Block2(this.mBlockIDs[4]);
        GameRegistry.registerBlock((Block)this.mBlocks[4], GT_MetaBlock2_Item.class, (String)GT_LanguageManager.mNameList3[0], (String)"GregTech_Addon");
        this.mBlocks[3] = new GT_Block_LightSource(this.mBlockIDs[3]);
        GameRegistry.registerBlock((Block)this.mBlocks[3], ItemBlock.class, (String)"GT_TransparentTileEntity", (String)"GregTech_Addon");
        LanguageRegistry.addName((Object)this.mBlocks[0], (String)GT_LanguageManager.mRegionalNameList0[0]);
        LanguageRegistry.addName((Object)this.mBlocks[1], (String)GT_LanguageManager.mRegionalNameList1[0]);
        LanguageRegistry.addName((Object)this.mBlocks[2], (String)GT_LanguageManager.mRegionalNameList2[0]);
        LanguageRegistry.addName((Object)this.mBlocks[4], (String)GT_LanguageManager.mRegionalNameList3[0]);
        for (int i = 0; i < 16; ++i) {
            GT_LanguageManager.addStringLocalization("tile.BlockMetaID_Block." + GT_LanguageManager.mNameList0[i] + ".name", GT_LanguageManager.mRegionalNameList0[i]);
            GT_LanguageManager.addStringLocalization("tile.BlockMetaID_Machine." + GT_LanguageManager.mNameList1[i] + ".name", GT_LanguageManager.mRegionalNameList1[i]);
            GT_LanguageManager.addStringLocalization("tile.BlockMetaID_Ore." + GT_LanguageManager.mNameList2[i] + ".name", GT_LanguageManager.mRegionalNameList2[i]);
            GT_LanguageManager.addStringLocalization("tile.BlockMetaID_Block2." + GT_LanguageManager.mNameList3[i] + ".name", GT_LanguageManager.mRegionalNameList3[i]);
        }
        GT_Mod.registerMachineBlock(this.mBlocks[0].blockID, 58435);
        GT_Log.out.println("GT_Mod: Register OreDict Entries of Non-GT-Items.");
        GT_OreDictUnificator.registerOre("molecule_2o", GT_ModHandler.getIC2Item("airCell", 1));
        GT_OreDictUnificator.registerOre("chunkLazurite", new ItemStack(Block.blockLapis, 1));
        GT_OreDictUnificator.registerOre("gemDiamond", GT_ModHandler.getIC2Item("industrialDiamond", 1));
        GT_OreDictUnificator.registerOre("craftingIndustrialDiamond", GT_ModHandler.getIC2Item("industrialDiamond", 1));
        GT_OreDictUnificator.registerOre("itemDiamond", GT_ModHandler.getIC2Item("industrialDiamond", 1));
        GT_OreDictUnificator.registerOre("stoneEnd", new ItemStack(Block.whiteStone, 1));
        GT_OreDictUnificator.registerOre("stoneNetherrack", new ItemStack(Block.netherrack, 1));
        GT_OreDictUnificator.registerOre("stoneNetherBrick", new ItemStack(Block.netherBrick, 1));
        GT_OreDictUnificator.registerOre("itemIridium", GT_ModHandler.getIC2Item("iridiumOre", 1));
        GT_OreDictUnificator.registerOre("plateIridiumAlloy", GT_ModHandler.getIC2Item("iridiumPlate", 1));
        GT_OreDictUnificator.registerOre("plateCopperDense", GT_ModHandler.getIC2Item("denseCopperPlate", 1));
        GT_OreDictUnificator.registerOre("itemRecord", new ItemStack(Item.record13, 1));
        GT_OreDictUnificator.registerOre("itemRecord", new ItemStack(Item.recordCat, 1));
        GT_OreDictUnificator.registerOre("itemRecord", new ItemStack(Item.recordBlocks, 1));
        GT_OreDictUnificator.registerOre("itemRecord", new ItemStack(Item.recordStrad, 1));
        GT_OreDictUnificator.registerOre("itemRecord", new ItemStack(Item.recordStal, 1));
        GT_OreDictUnificator.registerOre("itemRecord", new ItemStack(Item.recordFar, 1));
        GT_OreDictUnificator.registerOre("itemRecord", new ItemStack(Item.recordMall, 1));
        GT_OreDictUnificator.registerOre("itemRecord", new ItemStack(Item.recordMellohi, 1));
        GT_OreDictUnificator.registerOre("itemRecord", new ItemStack(Item.recordWard, 1));
        GT_OreDictUnificator.registerOre("itemRecord", new ItemStack(Item.recordChirp, 1));
        GT_OreDictUnificator.registerOre("itemRecord", new ItemStack(Item.record11, 1));
        if (Block.enderChest != null) {
            GT_OreDictUnificator.registerOre("craftingEnderChest", new ItemStack(Block.enderChest, 1));
        }
        GT_OreDictUnificator.registerOre("glassReinforced", GT_ModHandler.getIC2Item("reinforcedGlass", 1));
        GT_OreDictUnificator.registerOre("glassReinforced", GT_ModHandler.getTEItem("hardenedGlass", 1));
        GT_Log.out.println("GT_Mod: Register Unification Entries");
        GT_OreDictUnificator.add("oreCoal", new ItemStack(Block.oreCoal, 1));
        GT_OreDictUnificator.add("oreIron", new ItemStack(Block.oreIron, 1));
        GT_OreDictUnificator.add("oreLapis", new ItemStack(Block.oreLapis, 1));
        GT_OreDictUnificator.add("oreRedstone", new ItemStack(Block.oreRedstone, 1));
        GT_OreDictUnificator.add("oreRedstone", new ItemStack(Block.oreRedstoneGlowing, 1));
        GT_OreDictUnificator.add("oreGold", new ItemStack(Block.oreGold, 1));
        GT_OreDictUnificator.add("oreDiamond", new ItemStack(Block.oreDiamond, 1));
        GT_OreDictUnificator.add("oreEmerald", new ItemStack(Block.oreEmerald, 1));
        GT_OreDictUnificator.add("oreGalena", new ItemStack(this.mBlocks[2], 1, 1));
        GT_OreDictUnificator.add("oreIridium", new ItemStack(this.mBlocks[2], 1, 2));
        GT_OreDictUnificator.add("oreRuby", new ItemStack(this.mBlocks[2], 1, 3));
        GT_OreDictUnificator.add("oreSapphire", new ItemStack(this.mBlocks[2], 1, 4));
        GT_OreDictUnificator.add("oreBauxite", new ItemStack(this.mBlocks[2], 1, 5));
        GT_OreDictUnificator.add("oreNetherPyrite", new ItemStack(this.mBlocks[2], 1, 6));
        GT_OreDictUnificator.add("oreNetherCinnabar", new ItemStack(this.mBlocks[2], 1, 7));
        GT_OreDictUnificator.add("oreNetherSphalerite", new ItemStack(this.mBlocks[2], 1, 8));
        GT_OreDictUnificator.add("oreEndTungstate", new ItemStack(this.mBlocks[2], 1, 9));
        GT_OreDictUnificator.add("oreEndCooperite", new ItemStack(this.mBlocks[2], 1, 10));
        GT_OreDictUnificator.add("oreEndOlivine", new ItemStack(this.mBlocks[2], 1, 11));
        GT_OreDictUnificator.add("oreEndSodalite", new ItemStack(this.mBlocks[2], 1, 12));
        GT_OreDictUnificator.add("craftingSaltpeterToGunpowder", GT_ModHandler.getTEItem("crystalNiter", 1));
        GT_OreDictUnificator.add("craftingSulfurToGunpowder", GT_ModHandler.getTEItem("crystalSulfur", 1));
        GT_OreDictUnificator.add("itemRubber", GT_ModHandler.getIC2Item("rubber", 1));
        GT_OreDictUnificator.add("gemDiamond", new ItemStack(Item.diamond, 1));
        GT_OreDictUnificator.add("gemEmerald", new ItemStack(Item.emerald, 1));
        GT_OreDictUnificator.add("nuggetGold", new ItemStack(Item.goldNugget, 1));
        GT_OreDictUnificator.add("ingotGold", new ItemStack(Item.ingotGold, 1));
        GT_OreDictUnificator.add("ingotIron", new ItemStack(Item.ingotIron, 1));
        GT_OreDictUnificator.add("ingotTin", GT_ModHandler.getIC2Item("tinIngot", 1));
        GT_OreDictUnificator.add("ingotCopper", GT_ModHandler.getIC2Item("copperIngot", 1));
        GT_OreDictUnificator.add("ingotBronze", GT_ModHandler.getIC2Item("bronzeIngot", 1));
        GT_OreDictUnificator.add("ingotRefinedIron", GT_ModHandler.getIC2Item("refinedIronIngot", 1));
        GT_OreDictUnificator.add("ingotUranium", GT_ModHandler.getIC2Item("uraniumIngot", 1));
        GT_OreDictUnificator.add("dustLapis", new ItemStack(Item.dyePowder, 1, 4));
        GT_OreDictUnificator.add("dustRedstone", new ItemStack(Item.redstone, 1));
        GT_OreDictUnificator.add("dustGunpowder", new ItemStack(Item.gunpowder, 1));
        GT_OreDictUnificator.add("dustGlowstone", new ItemStack(Item.lightStoneDust, 1));
        GT_OreDictUnificator.add("blockIron", new ItemStack(Block.blockSteel, 1, 0));
        GT_OreDictUnificator.add("blockGold", new ItemStack(Block.blockGold, 1, 0));
        GT_OreDictUnificator.add("blockDiamond", new ItemStack(Block.blockDiamond, 1, 0));
        GT_OreDictUnificator.add("blockEmerald", new ItemStack(Block.blockEmerald, 1, 0));
        GT_OreDictUnificator.add("blockLapis", new ItemStack(Block.blockLapis, 1, 0));
        GT_OreDictUnificator.add("blockCopper", GT_ModHandler.getIC2Item("copperBlock", 1));
        GT_OreDictUnificator.add("blockTin", GT_ModHandler.getIC2Item("tinBlock", 1));
        GT_OreDictUnificator.add("blockBronze", GT_ModHandler.getIC2Item("bronzeBlock", 1));
        GT_OreDictUnificator.add("blockUranium", GT_ModHandler.getIC2Item("uraniumBlock", 1));
        GT_OreDictUnificator.add("blockSilver", new ItemStack(this.mBlocks[0], 1, 3));
        GT_OreDictUnificator.add("blockRuby", new ItemStack(this.mBlocks[0], 1, 4));
        GT_OreDictUnificator.add("blockSapphire", new ItemStack(this.mBlocks[0], 1, 5));
        GT_OreDictUnificator.add("blockAluminum", new ItemStack(this.mBlocks[0], 1, 7));
        GT_OreDictUnificator.add("blockAluminium", new ItemStack(this.mBlocks[0], 1, 7));
        GT_OreDictUnificator.add("blockTitanium", new ItemStack(this.mBlocks[0], 1, 8));
        GT_OreDictUnificator.add("blockChrome", new ItemStack(this.mBlocks[0], 1, 9));
        GT_OreDictUnificator.add("blockSteel", new ItemStack(this.mBlocks[0], 1, 11));
        GT_OreDictUnificator.add("blockBrass", new ItemStack(this.mBlocks[0], 1, 12));
        GT_OreDictUnificator.add("blockLead", new ItemStack(this.mBlocks[4], 1, 0));
        GT_OreDictUnificator.add("blockElectrum", new ItemStack(this.mBlocks[4], 1, 1));
        GT_OreDictUnificator.add("blockZinc", new ItemStack(this.mBlocks[4], 1, 2));
        GT_OreDictUnificator.add("blockOlivine", new ItemStack(this.mBlocks[4], 1, 3));
        GT_OreDictUnificator.add("blockGreenSapphire", new ItemStack(this.mBlocks[4], 1, 4));
        GT_OreDictUnificator.add("blockPlatinum", new ItemStack(this.mBlocks[4], 1, 5));
        GT_OreDictUnificator.add("blockTungsten", new ItemStack(this.mBlocks[4], 1, 6));
        GT_OreDictUnificator.add("blockNickel", new ItemStack(this.mBlocks[4], 1, 7));
        GT_OreDictUnificator.add("blockInvar", new ItemStack(this.mBlocks[4], 1, 10));
        GT_OreDictUnificator.add("blockOsmium", new ItemStack(this.mBlocks[4], 1, 11));
        GT_OreDictUnificator.add("blockIridium", new ItemStack(this.mBlocks[4], 1, 12));
        GT_Log.out.println("GT_Mod: Register other Mods Unification Targets.");
        if (GT_Config.mUnificatorFR) {
            GT_OreDictUnificator.override("ingotCopper", GT_ModHandler.getFRItem("ingotCopper", 1));
            GT_OreDictUnificator.override("ingotTin", GT_ModHandler.getFRItem("ingotTin", 1));
            GT_OreDictUnificator.override("ingotBronze", GT_ModHandler.getFRItem("ingotBronze", 1));
            GT_OreDictUnificator.override("dustAsh", GT_ModHandler.getFRItem("ash", 1));
            GT_OreDictUnificator.override("dustWood", GT_ModHandler.getFRItem("woodPulp", 1));
            GT_OreDictUnificator.override("pulpWood", GT_ModHandler.getFRItem("woodPulp", 1));
        }
        if (GT_Config.mUnificatorRC) {
            GT_OreDictUnificator.override("nuggetIron", GT_ModHandler.getRCItem("nugget.iron", 1));
            GT_OreDictUnificator.override("nuggetSteel", GT_ModHandler.getRCItem("nugget.steel", 1));
            GT_OreDictUnificator.override("ingotSteel", GT_ModHandler.getRCItem("part.ingot.steel", 1));
            GT_OreDictUnificator.override("dustCharcoal", GT_ModHandler.getRCItem("dust.charcoal", 1));
            GT_OreDictUnificator.override("dustObsidian", GT_ModHandler.getRCItem("dust.obsidian", 1));
            GT_OreDictUnificator.override("dustSaltpeter", GT_ModHandler.getRCItem("dust.saltpeter", 1));
            GT_OreDictUnificator.override("dustSulfur", GT_ModHandler.getRCItem("dust.sulfur", 1));
        }
        if (GT_Config.mUnificatorTE) {
            GT_OreDictUnificator.override("dustWood", GT_ModHandler.getTEItem("sawdust", 1));
            GT_OreDictUnificator.override("pulpWood", GT_ModHandler.getTEItem("sawdust", 1));
            GT_OreDictUnificator.override("dustGold", GT_ModHandler.getTEItem("dustGold", 1));
            GT_OreDictUnificator.override("dustBrass", GT_ModHandler.getTEItem("dustBrass", 1));
            GT_OreDictUnificator.override("dustBronze", GT_ModHandler.getTEItem("dustBronze", 1));
            GT_OreDictUnificator.override("dustCopper", GT_ModHandler.getTEItem("dustCopper", 1));
            GT_OreDictUnificator.override("dustElectrum", GT_ModHandler.getTEItem("dustElectrum", 1));
            GT_OreDictUnificator.override("dustInvar", GT_ModHandler.getTEItem("dustInvar", 1));
            GT_OreDictUnificator.override("dustIron", GT_ModHandler.getTEItem("dustIron", 1));
            GT_OreDictUnificator.override("dustLead", GT_ModHandler.getTEItem("dustLead", 1));
            GT_OreDictUnificator.override("dustNickel", GT_ModHandler.getTEItem("dustNickel", 1));
            GT_OreDictUnificator.override("dustObsidian", GT_ModHandler.getTEItem("dustObsidian", 1));
            GT_OreDictUnificator.override("dustPlatinum", GT_ModHandler.getTEItem("dustPlatinum", 1));
            GT_OreDictUnificator.override("dustSilver", GT_ModHandler.getTEItem("dustSilver", 1));
            GT_OreDictUnificator.override("dustTin", GT_ModHandler.getTEItem("dustTin", 1));
            GT_OreDictUnificator.override("ingotCopper", GT_ModHandler.getTEItem("ingotCopper", 1));
            GT_OreDictUnificator.override("ingotElectrum", GT_ModHandler.getTEItem("ingotElectrum", 1));
            GT_OreDictUnificator.override("ingotInvar", GT_ModHandler.getTEItem("ingotInvar", 1));
            GT_OreDictUnificator.override("ingotLead", GT_ModHandler.getTEItem("ingotLead", 1));
            GT_OreDictUnificator.override("ingotNickel", GT_ModHandler.getTEItem("ingotNickel", 1));
            GT_OreDictUnificator.override("ingotPlatinum", GT_ModHandler.getTEItem("ingotPlatinum", 1));
            GT_OreDictUnificator.override("ingotSilver", GT_ModHandler.getTEItem("ingotSilver", 1));
            GT_OreDictUnificator.override("ingotTin", GT_ModHandler.getTEItem("ingotTin", 1));
        }
        if (GT_Config.mUnificatorRP) {
            GT_OreDictUnificator.override("gemRuby", GT_ModHandler.mRuby);
            GT_OreDictUnificator.override("gemSapphire", GT_ModHandler.mSapphire);
            GT_OreDictUnificator.override("gemGreenSapphire", GT_ModHandler.mGreenSapphire);
            GT_OreDictUnificator.override("ingotSilver", GT_ModHandler.mSilver);
            GT_OreDictUnificator.override("ingotCopper", GT_ModHandler.mCopper);
            GT_OreDictUnificator.override("ingotTin", GT_ModHandler.mTin);
            GT_OreDictUnificator.override("ingotBrass", GT_ModHandler.mBrass);
            GT_OreDictUnificator.override("nuggetIron", GT_ModHandler.mIronNugget);
            GT_OreDictUnificator.override("nuggetSilver", GT_ModHandler.mSilverNugget);
            GT_OreDictUnificator.override("nuggetTin", GT_ModHandler.mTinNugget);
            GT_OreDictUnificator.override("nuggetCopper", GT_ModHandler.mCopperNugget);
        }
        if (GT_Config.mUnificatorTC) {
            GT_OreDictUnificator.override("nuggetIron", GT_ModHandler.mNuggetIron);
            GT_OreDictUnificator.override("nuggetSilver", GT_ModHandler.mNuggetSilver);
            GT_OreDictUnificator.override("nuggetTin", GT_ModHandler.mNuggetTin);
            GT_OreDictUnificator.override("nuggetCopper", GT_ModHandler.mNuggetCopper);
            GT_OreDictUnificator.override("nuggetLead", GT_ModHandler.mNuggetLead);
        }
        GT_OreDictUnificator.override("plateTin", GT_ModHandler.getRCItem("part.plate.tin", 1));
        GT_OreDictUnificator.override("plateIron", GT_ModHandler.getRCItem("part.plate.iron", 1));
        GT_OreDictUnificator.override("plateSteel", GT_ModHandler.getRCItem("part.plate.steel", 1));
        GT_Log.out.println("GT_Mod: Register TileEntities.");
        GameRegistry.registerTileEntity(GT_TileEntityMetaID_Machine.class, (String)GT_LanguageManager.mNameList1[0]);
        GameRegistry.registerTileEntity(GT_TileEntity_Fusionreactor.class, (String)GT_LanguageManager.mNameList1[1]);
        GameRegistry.registerTileEntity(GT_TileEntity_Lightningrod.class, (String)GT_LanguageManager.mNameList1[2]);
        GameRegistry.registerTileEntity(GT_TileEntity_Quantumchest.class, (String)GT_LanguageManager.mNameList1[3]);
        GameRegistry.registerTileEntity(GT_TileEntity_ComputerCube.class, (String)GT_LanguageManager.mNameList1[4]);
        GameRegistry.registerTileEntity(GT_TileEntity_UUMAssembler.class, (String)GT_LanguageManager.mNameList1[5]);
        GameRegistry.registerTileEntity(GT_TileEntity_Sonictron.class, (String)GT_LanguageManager.mNameList1[6]);
        GameRegistry.registerTileEntity(GT_TileEntity_LESU.class, (String)GT_LanguageManager.mNameList1[7]);
        GameRegistry.registerTileEntity(GT_TileEntity_IDSU.class, (String)GT_LanguageManager.mNameList1[8]);
        GameRegistry.registerTileEntity(GT_TileEntity_AESU.class, (String)GT_LanguageManager.mNameList1[9]);
        GameRegistry.registerTileEntity(GT_TileEntity_ChargeOMat.class, (String)GT_LanguageManager.mNameList1[10]);
        GameRegistry.registerTileEntity(GT_TileEntity_Centrifuge.class, (String)GT_LanguageManager.mNameList1[11]);
        GameRegistry.registerTileEntity(GT_TileEntity_Superconductor.class, (String)GT_LanguageManager.mNameList1[12]);
        GameRegistry.registerTileEntity(GT_TileEntity_PlayerDetector.class, (String)GT_LanguageManager.mNameList1[13]);
        GameRegistry.registerTileEntity(GT_TileEntity_Matterfabricator.class, (String)GT_LanguageManager.mNameList1[14]);
        GameRegistry.registerTileEntity(GT_TileEntity_Supercondensator.class, (String)GT_LanguageManager.mNameList1[15]);
        GameRegistry.registerTileEntity(GT_TileEntity_LightSource.class, (String)"GT_LightSource");
        GameRegistry.registerTileEntity(GT_Mod.constructBaseMetaTileEntity().getClass(), (String)"MetatileEntity");
        GT_Log.out.println("GT_Mod: Register MetaTileEntities.");
        new GT_MetaTileEntity_ElectricAutoWorkbench(16, "GT_E_Craftingtable", "Electric Craftingtable");
        new GT_MetaTileEntity_Translocator(17, "GT_Translocator", "Electric Translocator");
        new GT_MetaTileEntity_ElectricBufferSmall(18, "GT_E_Buffer_Small", "Small Electric Buffer");
        new GT_MetaTileEntity_ElectricBufferLarge(19, "GT_E_Buffer_Large", "Large Electric Buffer");
        new GT_MetaTileEntity_AdvancedTranslocator(20, "GT_Adv_Translocator", "Advanced Translocator");
        new GT_MetaTileEntity_ElectricBufferAdvanced(21, "GT_Adv_Buffer", "Advanced Buffer");
        new GT_MetaTileEntity_RockBreaker(22, "GT_RockBreaker", "Electric Rock Breaker");
        new GT_MetaTileEntity_ElectricSorter(23, "GT_E_Sorter", "Electric Sorter");
        new GT_MetaTileEntity_ElectricItemClearer(24, "GT_E_ItemClearer", "Electric Item Clearer");
        new GT_MetaTileEntity_Electrolyzer(25, "GT_Electrolyzer", "Industrial Electrolyzer");
        new GT_MetaTileEntity_CropHarvestor(26, "GT_Harvestor", "Crop Harvestor");
        new GT_MetaTileEntity_Scrapboxinator(27, "GT_Scrapboxinator", "Scrapboxinator");
        new GT_MetaTileEntity_Grinder(28, "GT_Grinder", "Industrial Grinder");
        new GT_MetaTileEntity_BlastFurnace(29, "GT_BlastFurnace", "Industrial Blast Furnace");
        new GT_MetaTileEntity_Quantumtank(30, "GT_QuantumTank", "Quantum Tank");
        new GT_MetaTileEntity_ImplosionCompressor(31, "GT_ImplosionCompressor", "Implosion Compressor");
        new GT_MetaTileEntity_Sawmill(32, "GT_Sawmill", "Industrial Sawmill");
        new GT_MetaTileEntity_DieselGenerator(33, "GT_DieselGenerator", "Diesel Generator");
        new GT_MetaTileEntity_GasTurbine(34, "GT_GasTurbine", "Gas Turbine");
        new GT_MetaTileEntity_ThermalGenerator(35, "GT_ThermalGenerator", "Thermal Generator");
        new GT_MetaTileEntity_SemifluidGenerator(36, "GT_SemifluidGenerator", "Semifluid Generator");
        new GT_MetaTileEntity_PlasmaGenerator(37, "GT_PlasmaGenerator", "Plasma Generator");
        new GT_MetaTileEntity_VacuumFreezer(38, "GT_VacuumFreezer", "Vacuum Freezer");
        new GT_MetaTileEntity_ElectricRegulatorAdvanced(39, "GT_RegulatorAdvanced", "Advanced Regulator");
        new GT_MetaTileEntity_DragonEggEnergySiphon(40, "GT_DragonEggEnergySiphon", "Dragon Egg Energy Siphon");
        new GT_MetaTileEntity_ChemicalReactor(41, "GT_ChemicalReactor", "Chemical Reactor");
        new GT_MetaTileEntity_MagicEnergyConverter(42, "GT_MagicConverter", "Magic Energy Converter");
        new GT_MetaTileEntity_MagicEnergyAbsorber(43, "GT_MagicAbsorber", "Magic Energy Absorber");
        new GT_MetaTileEntity_DistillationTower(44, "GT_DistillationTower", "Distillation Tower");
        new GT_MetaTileEntity_Safe(45, "GT_Safe", "Advanced Safe");
        new GT_MetaTileEntity_ElectricInventoryManager(46, "GT_InventoryManager", "Inventory Manager");
        new GT_MetaTileEntity_AdvancedPump(47, "GT_Pump", "Advanced Pump");
        new GT_MetaTileEntity_Barrel(48, "GT_Barrel", "Digital Chest");
        new GT_MetaTileEntity_QuantumChest(49, "GT_QuantumChest", "Quantum Chest");
        new GT_MetaTileEntity_Macerator(50, "GT_Macerator", "Automatic Macerator");
        new GT_MetaTileEntity_Extractor(51, "GT_Extractor", "Automatic Extractor");
        new GT_MetaTileEntity_Compressor(52, "GT_Compressor", "Automatic Compressor");
        new GT_MetaTileEntity_Recycler(53, "GT_Recycler", "Automatic Recycler");
        new GT_MetaTileEntity_E_Furnace(54, "GT_E_Furnace", "Automatic E-Furnace");
        new GT_MetaTileEntity_Wiremill(55, "GT_Wiremill", "Automatic Wiremill");
        new GT_MetaTileEntity_AlloySmelter(56, "GT_AlloySmelter", "Alloy Smelter");
        new GT_MetaTileEntity_Canner(57, "GT_Canner", "Automatic Canning Machine");
        new GT_MetaTileEntity_ElectricTypeSorter(58, "GT_E_T_Sorter", "Electric Type Sorter");
        new GT_MetaTileEntity_Bender(59, "GT_Bender", "Plate Bending Machine");
        new GT_MetaTileEntity_Assembler(60, "GT_Assembler", "Assembling Machine");
        new GT_MetaTileEntity_Printer(61, "GT_Printer", "Printing Factory");
        new GT_MetaTileEntity_Centrifuge(62, "GT_Centrifuge", "Industrial Centrifuge");
        new GT_MetaTileEntity_Shelf(70, "GT_Shelf", "Wood encased Shelf");
        new GT_MetaTileEntity_Shelf_Iron(71, "GT_Shelf_Iron", "Metal encased Shelf");
        new GT_MetaTileEntity_Shelf_FileCabinet(72, "GT_Shelf_FileCabinet", "File Cabinet");
        new GT_MetaTileEntity_Shelf_Desk(73, "GT_Shelf_Desk", "Metal encased Desk");
        new GT_MetaTileEntity_Shelf_Compartment(74, "GT_Shelf_Compartment", "Compartment");
        new GT_MetaTileEntity_FusionComputer(80, "GT_Fusion_Computer", "Fusion Control Computer");
        new GT_MetaTileEntity_FusionEnergyInjector(81, "GT_Fusion_Energy", "Fusion Energy Injector");
        new GT_MetaTileEntity_FusionInjector(82, "GT_Fusion_Injector", "Fusion Material Injector");
        new GT_MetaTileEntity_FusionExtractor(83, "GT_Fusion_Extractor", "Fusion Material Extractor");
        GT_Log.out.println("GT_Mod: Register Armor Textures.");
        int tArmorID1 = gregtechproxy.addArmor("lapotronpack");
        int tArmorID2 = gregtechproxy.addArmor("lithiumbatpack");
        int tArmorID3 = gregtechproxy.addArmor("claokingdevice");
        GT_Log.out.println("GT_Mod: Register Meta-ID Items.");
        this.mItems[0] = new GT_MetaItem_Material(this.mItemIDs[0]);
        this.mItems[1] = new GT_MetaItem_Dust(this.mItemIDs[1]);
        this.mItems[2] = new GT_MetaItem_Cell(this.mItemIDs[2]);
        this.mItems[3] = new GT_MetaItem_Component(this.mItemIDs[3]);
        this.mItems[4] = new GT_MetaItem_SmallDust(this.mItemIDs[4]);
        this.mItems[5] = new GT_MetaItem_Nugget(this.mItemIDs[5]);
        this.mItems[13] = new GT_MetaItem_Liquid(this.mItemIDs[13]);
        this.mItems[14] = new GT_MetaItem_Gas(this.mItemIDs[14]);
        this.mItems[15] = new GT_MetaItem_Plasma(this.mItemIDs[15]);
        GT_Log.out.println("GT_Mod: Adding All Sub-Items with their OreDict and LiquidDict Entries.");
        GT_MetaItem_Material.addItem(0, "Copper Credit", "", "0.125 Credits", false);
        GT_MetaItem_Material.addItem(1, "Silver Credit", "", "8 Credits", false);
        GT_MetaItem_Material.addItem(2, "Gold Credit", "", "64 Credits", false);
        GT_MetaItem_Material.addItem(3, "Diamond Credit", "", "512 Credits", false);
        GT_MetaItem_Material.addItem(4, "Iridium Alloy Ingot", "", "", false);
        GT_MetaItem_Material.addItem(5, "Hot Tungstensteel Ingot", "", "", false);
        GT_MetaItem_Material.addItem(6, "Tungstensteel Ingot", "ingotTungstenSteel", "Vacuum Hardened", false);
        GT_MetaItem_Material.addItem(13, "Magnalium Plate", "plateMagnalium", "MgAl2", false);
        GT_MetaItem_Material.addItem(14, "Flour", "dustWheat", "", false);
        GT_MetaItem_Material.addItem(15, "Wood Plate", "plankWood", "", false);
        GT_MetaItem_Material.addItem(16, "Iridium Ingot", "ingotIridium", "Ir", false);
        GT_MetaItem_Material.addItem(17, "Silver Ingot", "ingotSilver", "Ag", false);
        GT_MetaItem_Material.addItem(18, "Aluminium Ingot", "ingotAluminium", "Al", false);
        GT_MetaItem_Material.addItem(19, "Titanium Ingot", "ingotTitanium", "Ti", false);
        GT_MetaItem_Material.addItem(20, "Chrome Ingot", "ingotChrome", "Cr", false);
        GT_MetaItem_Material.addItem(21, "Electrum Ingot", "ingotElectrum", "AgAu", false);
        GT_MetaItem_Material.addItem(22, "Tungsten Ingot", "ingotTungsten", "W", false);
        GT_MetaItem_Material.addItem(23, "Lead Ingot", "ingotLead", "Pb", false);
        GT_MetaItem_Material.addItem(24, "Zinc Ingot", "ingotZinc", "Zn", false);
        GT_MetaItem_Material.addItem(25, "Brass Ingot", "ingotBrass", "ZnCu3", false);
        GT_MetaItem_Material.addItem(26, "Steel Ingot", "ingotSteel", "Fe", false);
        GT_MetaItem_Material.addItem(27, "Platinum Ingot", "ingotPlatinum", "Pt", false);
        GT_MetaItem_Material.addItem(28, "Nickel Ingot", "ingotNickel", "Ni", false);
        GT_MetaItem_Material.addItem(29, "Invar Ingot", "ingotInvar", "Fe2Ni", false);
        GT_MetaItem_Material.addItem(30, "Osmium Ingot", "ingotOsmium", "Os", false);
        GT_MetaItem_Material.addItem(32, "Ruby", "gemRuby", "Al2O6Cr", false);
        GT_MetaItem_Material.addItem(33, "Sapphire", "gemSapphire", "Al2O6", false);
        GT_MetaItem_Material.addItem(34, "Green Sapphire", "gemGreenSapphire", "Al2O6", false);
        GT_MetaItem_Material.addItem(35, "Chunk of Lazurite", "chunkLazurite", "(Al6Si6Ca8Na8)8", false);
        GT_MetaItem_Material.addItem(36, "Silicon Plate", "plateSilicon", "Si2", false);
        GT_MetaItem_Material.addItem(37, "Olivine", "gemOlivine", "Mg2Fe2SiO4", false);
        GT_MetaItem_Material.addItem(38, "Thorium Ingot", "ingotThorium", "Th", true);
        GT_MetaItem_Material.addItem(39, "Plutonium Ingot", "ingotPlutonium", "Pu", true);
        GT_MetaItem_Material.addItem(54, "Red Garnet", "gemGarnetRed", "(Al2Mg3Si3O12)3(Al2Fe3Si3O12)5(Al2Mn3Si3O12)8", false);
        GT_MetaItem_Material.addItem(55, "Yellow Garnet", "gemGarnetYellow", "(Ca3Fe2Si3O12)5(Ca3Al2Si3O12)8(Ca3Cr2Si3O12)3", false);
        GT_MetaItem_Material.addItem(64, "Iron Plate", "plateIron", "Fe", false);
        GT_MetaItem_Material.addItem(65, "Gold Plate", "plateGold", "Au", false);
        GT_MetaItem_Material.addItem(66, "Refined Iron Plate", "plateRefinedIron", "Fe", false);
        GT_MetaItem_Material.addItem(67, "Tin Plate", "plateTin", "Sn", false);
        GT_MetaItem_Material.addItem(68, "Copper Plate", "plateCopper", "Cu", false);
        GT_MetaItem_Material.addItem(69, "Silver Plate", "plateSilver", "Ag", false);
        GT_MetaItem_Material.addItem(70, "Bronze Plate", "plateBronze", "SnCu3", false);
        GT_MetaItem_Material.addItem(71, "Electrum Plate", "plateElectrum", "AgAu", false);
        GT_MetaItem_Material.addItem(72, "Nickel Plate", "plateNickel", "Ni", false);
        GT_MetaItem_Material.addItem(73, "Invar Plate", "plateInvar", "Fe2Ni", false);
        GT_MetaItem_Material.addItem(74, "Lead Plate", "plateLead", "Pb", false);
        GT_MetaItem_Material.addItem(75, "Aluminium Plate", "plateAluminium", "Al", false);
        GT_MetaItem_Material.addItem(76, "Chrome Plate", "plateChrome", "Cr", false);
        GT_MetaItem_Material.addItem(77, "Titanium Plate", "plateTitanium", "Ti", false);
        GT_MetaItem_Material.addItem(78, "Steel Plate", "plateSteel", "Fe", false);
        GT_MetaItem_Material.addItem(79, "Platinum Plate", "platePlatinum", "Pt", false);
        GT_MetaItem_Material.addItem(80, "Tungsten Plate", "plateTungsten", "W", false);
        GT_MetaItem_Material.addItem(81, "Brass Plate", "plateBrass", "ZnCu3", false);
        GT_MetaItem_Material.addItem(82, "Zinc Plate", "plateZinc", "Zn", false);
        GT_MetaItem_Material.addItem(83, "Tungstensteel Plate", "plateTungstenSteel", "Vacuum Hardened", false);
        GT_MetaItem_Material.addItem(84, "Osmium Plate", "plateOsmium", "Os", false);
        GT_MetaItem_Nugget.addItem(16, "Iridium Nugget", "Iridium", "Ir", false);
        GT_MetaItem_Nugget.addItem(17, "Silver Nugget", "Silver", "Ag", false);
        GT_MetaItem_Nugget.addItem(18, "Aluminium Nugget", "Aluminium", "Al", false);
        GT_MetaItem_Nugget.addItem(19, "Titanium Nugget", "Titanium", "Ti", false);
        GT_MetaItem_Nugget.addItem(20, "Chrome Nugget", "Chrome", "Cr", false);
        GT_MetaItem_Nugget.addItem(21, "Electrum Nugget", "Electrum", "AgAu", false);
        GT_MetaItem_Nugget.addItem(22, "Tungsten Nugget", "Tungsten", "W", false);
        GT_MetaItem_Nugget.addItem(23, "Lead Nugget", "Lead", "Pb", false);
        GT_MetaItem_Nugget.addItem(24, "Zinc Nugget", "Zinc", "Zn", false);
        GT_MetaItem_Nugget.addItem(25, "Brass Nugget", "Brass", "ZnCu3", false);
        GT_MetaItem_Nugget.addItem(26, "Steel Nugget", "Steel", "Fe", false);
        GT_MetaItem_Nugget.addItem(27, "Platinum Nugget", "Platinum", "Pt", false);
        GT_MetaItem_Nugget.addItem(28, "Nickel Nugget", "Nickel", "Ni", false);
        GT_MetaItem_Nugget.addItem(29, "Invar Nugget", "Invar", "Fe2Ni", false);
        GT_MetaItem_Nugget.addItem(30, "Osmium Nugget", "Osmium", "Os", false);
        GT_MetaItem_Nugget.addItem(241, "Iron Nugget", "Iron", "Fe", false);
        GT_MetaItem_Nugget.addItem(243, "Copper Nugget", "Copper", "Cu", false);
        GT_MetaItem_Nugget.addItem(244, "Tin Nugget", "Tin", "Sn", false);
        GT_MetaItem_Nugget.addItem(245, "Bronze Nugget", "Bronze", "SnCu3", false);
        GT_MetaItem_Dust.addItem(0, "Ender Pearl Dust", "EnderPearl", "BeK4N5Cl6", false);
        GT_MetaItem_Dust.addItem(1, "Ender Eye Dust", "EnderEye", "BeK4N5Cl6C4S2", false);
        GT_MetaItem_Dust.addItem(2, "Lazurite Dust", "Lazurite", "Al6Si6Ca8Na8", false);
        GT_MetaItem_Dust.addItem(3, "Pyrite Dust", "Pyrite", "FeS2", false);
        GT_MetaItem_Dust.addItem(4, "Calcite Dust", "Calcite", "CaCO3", false);
        GT_MetaItem_Dust.addItem(5, "Sodalite Dust", "Sodalite", "Al3Si3Na4Cl", false);
        GT_MetaItem_Dust.addItem(6, "Netherrack Dust", "Netherrack", "", false);
        GT_MetaItem_Dust.addItem(7, "Flint Dust", "Flint", "SiO2", false);
        GT_MetaItem_Dust.addItem(8, "Sulfur Dust", "Sulfur", "S", false);
        GT_MetaItem_Dust.addItem(9, "Saltpeter Dust", "Saltpeter", "KNO3", false);
        GT_MetaItem_Dust.addItem(10, "Endstone Dust", "Endstone", "", false);
        GT_MetaItem_Dust.addItem(11, "Cinnabar Dust", "Cinnabar", "HgS", false);
        GT_MetaItem_Dust.addItem(12, "Manganese Dust", "Manganese", "Mn", false);
        GT_MetaItem_Dust.addItem(13, "Magnesium Dust", "Magnesium", "Mg", false);
        GT_MetaItem_Dust.addItem(14, "Sphalerite Dust", "Sphalerite", "ZnS", false);
        GT_MetaItem_Dust.addItem(15, "Wood Pulp", "Wood", "", false);
        GT_OreDictUnificator.add("pulpWood", GT_MetaItem_Dust.instance.getUnunifiedStack(15, 1));
        GT_OreDictUnificator.add("pulpWood", GT_ModHandler.getTEItem("sawdust", 1));
        GT_MetaItem_Dust.addItem(16, "Uranium Dust", "Uranium", "U", true);
        GT_MetaItem_Dust.addItem(17, "Bauxite Dust", "Bauxite", "TiAl16H10O12", false);
        GT_MetaItem_Dust.addItem(18, "Aluminium Dust", "Aluminium", "Al", false);
        GT_MetaItem_Dust.addItem(19, "Titanium Dust", "Titanium", "Ti", false);
        GT_MetaItem_Dust.addItem(20, "Chrome Dust", "Chrome", "Cr", false);
        GT_MetaItem_Dust.addItem(21, "Electrum Dust", "Electrum", "AgAu", false);
        GT_MetaItem_Dust.addItem(22, "Tungsten Dust", "Tungsten", "W", false);
        GT_MetaItem_Dust.addItem(23, "Lead Dust", "Lead", "Pb", false);
        GT_MetaItem_Dust.addItem(24, "Zinc Dust", "Zinc", "Zn", false);
        GT_MetaItem_Dust.addItem(25, "Brass Dust", "Brass", "ZnCu3", false);
        GT_MetaItem_Dust.addItem(26, "Steel Dust", "Steel", "Fe", false);
        GT_MetaItem_Dust.addItem(27, "Platinum Dust", "Platinum", "Pt", false);
        GT_MetaItem_Dust.addItem(28, "Nickel Dust", "Nickel", "Ni", false);
        GT_MetaItem_Dust.addItem(29, "Invar Dust", "Invar", "Fe2Ni", false);
        GT_MetaItem_Dust.addItem(30, "Osmium Dust", "Osmium", "Os", false);
        GT_MetaItem_Dust.addItem(32, "Ruby Dust", "Ruby", "Al2O6Cr", false);
        GT_MetaItem_Dust.addItem(33, "Sapphire Dust", "Sapphire", "Al2O6", false);
        GT_MetaItem_Dust.addItem(34, "Green Sapphire Dust", "GreenSapphire", "Al2O6", false);
        GT_MetaItem_Dust.addItem(35, "Emerald Dust", "Emerald", "Be3Al2Si6O18", false);
        GT_MetaItem_Dust.addItem(36, "Diamond Dust", "Diamond", "C128", false);
        GT_MetaItem_Dust.addItem(37, "Olivine Dust", "Olivine", "Mg2Fe2SiO4", false);
        GT_MetaItem_Dust.addItem(44, "Galena Dust", "Galena", "Pb3Ag3S2", false);
        GT_MetaItem_Dust.addItem(45, "Phosphorus Dust", "Phosphorus", "Ca3(PO4)2", false);
        GT_MetaItem_Dust.addItem(46, "Obsidian Dust", "Obsidian", "MgFeSi2O8", false);
        GT_MetaItem_Dust.addItem(47, "Charcoal Dust", "Charcoal", "C", false);
        GT_MetaItem_Dust.addItem(54, "Red Garnet Dust", "GarnetRed", "(Al2Mg3Si3O12)3(Al2Fe3Si3O12)5(Al2Mn3Si3O12)8", false);
        GT_MetaItem_Dust.addItem(55, "Yellow Garnet Dust", "GarnetYellow", "(Ca3Fe2Si3O12)5(Ca3Al2Si3O12)8(Ca3Cr2Si3O12)3", false);
        GT_MetaItem_Dust.addItem(56, "Pyrope Dust", "Pyrope", "Al2Mg3Si3O12", false);
        GT_MetaItem_Dust.addItem(57, "Almandine Dust", "Almandine", "Al2Fe3Si3O12", false);
        GT_MetaItem_Dust.addItem(58, "Spessartine Dust", "Spessartine", "Al2Mn3Si3O12", false);
        GT_MetaItem_Dust.addItem(59, "Andradite Dust", "Andradite", "Ca3Fe2Si3O12", false);
        GT_MetaItem_Dust.addItem(60, "Grossular Dust", "Grossular", "Ca3Al2Si3O12", false);
        GT_MetaItem_Dust.addItem(61, "Uvarovite Dust", "Uvarovite", "Ca3Cr2Si3O12", false);
        GT_MetaItem_Dust.addItem(62, "Ashes", "Ash", "C", false);
        GT_MetaItem_Dust.addItem(63, "Dark Ashes", "DarkAsh", "C", false);
        GT_MetaItem_Dust.addItem(64, "Redrock Dust", "RedRock", "(Na2LiAl2Si2)((CaCO3)2SiO2)3", false);
        GT_MetaItem_Dust.addItem(65, "Marble Dust", "Marble", "Mg(CaCO3)7", false);
        GT_MetaItem_Dust.addItem(66, "Basalt Dust", "Basalt", "(Mg2Fe2SiO4)(CaCO3)3(SiO2)8C4", false);
        GT_MetaItem_Dust.addItem(80, "Thorium Dust", "Thorium", "Th", true);
        GT_MetaItem_Dust.addItem(81, "Plutonium Dust", "Plutonium", "Pu", true);
        GT_MetaItem_Dust.addItem(240, "Coal Dust", "Coal", "C2", false);
        GT_MetaItem_Dust.addItem(241, "Iron Dust", "Iron", "Fe", false);
        GT_MetaItem_Dust.addItem(242, "Gold Dust", "Gold", "Au", false);
        GT_MetaItem_Dust.addItem(243, "Copper Dust", "Copper", "Cu", false);
        GT_MetaItem_Dust.addItem(244, "Tin Dust", "Tin", "Sn", false);
        GT_MetaItem_Dust.addItem(245, "Bronze Dust", "Bronze", "SnCu3", false);
        GT_MetaItem_Dust.addItem(246, "Silver Dust", "Silver", "Ag", false);
        GT_MetaItem_Dust.addItem(247, "Clay Dust", "Clay", "Na2LiAl2Si2", false);
        GT_MetaItem_SmallDust.addItem(248, "Gunpowder", "Gunpowder", "", false);
        GT_MetaItem_SmallDust.addItem(249, "Redstone Dust", "Redstone", "", false);
        GT_MetaItem_SmallDust.addItem(250, "Glowstone Dust", "Glowstone", "", false);
        GT_MetaItem_Cell.addItem(0, "Hydrogen Cell", "molecule_1h", "H");
        GT_MetaItem_Cell.addItem(1, "Deuterium Cell", "molecule_1d", "H-2");
        GT_MetaItem_Cell.addItem(2, "Tritium Cell", "molecule_1t", "H-3");
        GT_MetaItem_Cell.addItem(3, "Helium Cell", "molecule_1he", "He");
        GT_MetaItem_Cell.addItem(4, "Wolframium Cell", "molecule_1w", "W");
        GT_MetaItem_Cell.addItem(5, "Lithium Cell", "molecule_1li", "Li");
        GT_MetaItem_Cell.addItem(6, "Helium-3 Cell", "molecule_1he3", "He-3");
        GT_MetaItem_Cell.addItem(7, "Silicon Cell", "molecule_1si", "Si");
        GT_MetaItem_Cell.addItem(8, "Carbon Cell", "molecule_1c", "C");
        GT_MetaItem_Cell.addItem(9, "Methane Cell", "molecule_1me", "CH4");
        GT_MetaItem_Cell.addItem(10, "Berylium Cell", "molecule_1be", "Be");
        GT_MetaItem_Cell.addItem(11, "Calcium Cell", "molecule_1ca", "Ca");
        GT_MetaItem_Cell.addItem(12, "Sodium Cell", "molecule_1na", "Na");
        GT_MetaItem_Cell.addItem(13, "Chlorite Cell", "molecule_1cl", "Cl");
        GT_MetaItem_Cell.addItem(14, "Potassium Cell", "molecule_1k", "K");
        GT_MetaItem_Cell.addItem(15, "Nitrogen Cell", "molecule_1n", "N");
        GT_MetaItem_Cell.addItem(16, "Mercury Cell", "molecule_1hg", "Hg");
        GT_MetaItem_Cell.addItem(17, "Oil Cell", "", "");
        GT_MetaItem_Cell.addItem(18, "Diesel Cell", "", "");
        GT_MetaItem_Cell.addItem(19, "Bio Diesel Cell", "", "");
        GT_MetaItem_Cell.addItem(20, "Biomass Cell", "", "");
        GT_MetaItem_Cell.addItem(22, "Nitro-Diesel Cell", "", "");
        GT_MetaItem_Cell.addItem(23, "Ice Cell", "", "H2O");
        GT_MetaItem_Cell.addItem(24, "Seed Oil Cell", "", "");
        GT_MetaItem_Cell.addItem(32, "Sodium Persulfate Cell", "molecule_2na_2s_8o", "Na2S2O8");
        GT_MetaItem_Cell.addItem(33, "Calcium Carbonate Cell", "molecule_1ca_1c_3o", "CaCO3");
        GT_MetaItem_Cell.addItem(34, "Glyceryl Cell", "molecule_3c_5h_3n_9o", "C3H5N3O9");
        GT_MetaItem_Cell.addItem(35, "Nitro-Coalfuel Cell", "", "");
        GT_MetaItem_Cell.addItem(36, "Sulfur Cell", "molecule_1s", "S");
        GT_MetaItem_Cell.addItem(37, "Sodium Sulfide Cell", "molecule_1na_1s", "NaS");
        GT_MetaItem_Cell.addItem(38, "Nitrogen Dioxide Cell", "molecule_1n_2o", "NO2");
        GT_MetaItem_Cell.addItem(39, "Nitrocarbon Cell", "molecule_1n_1c", "NC");
        GT_MetaItem_Cell.addItem(40, "Sulfuric Acid Cell", "molecule_2h_1s_4o", "H2SO4");
        GT_MetaItem_Cell.addItem(131, "Helium Plasma Cell", "plasma_1he", "He");
        GT_MetaItem_Plasma.addItem(3, "Helium", "Helium", "He", GT_MetaItem_Cell.instance.getUnunifiedStack(131, 1), GT_ModHandler.getEmptyCell(1));
        GT_MetaItem_Gas.addItem(0, "Hydrogen", "Hydrogen", "H", GT_MetaItem_Cell.instance.getUnunifiedStack(0, 1), GT_ModHandler.getEmptyCell(1));
        GT_MetaItem_Gas.addItem(1, "Deuterium", "Deuterium", "H-2", GT_MetaItem_Cell.instance.getUnunifiedStack(1, 1), GT_ModHandler.getEmptyCell(1));
        GT_MetaItem_Gas.addItem(2, "Tritium", "Tritium", "H-3", GT_MetaItem_Cell.instance.getUnunifiedStack(2, 1), GT_ModHandler.getEmptyCell(1));
        GT_MetaItem_Gas.addItem(3, "Helium", "Helium", "He", GT_MetaItem_Cell.instance.getUnunifiedStack(3, 1), GT_ModHandler.getEmptyCell(1));
        GT_MetaItem_Gas.addItem(6, "Helium-3", "Helium-3", "He-3", GT_MetaItem_Cell.instance.getUnunifiedStack(6, 1), GT_ModHandler.getEmptyCell(1));
        GT_MetaItem_Gas.addItem(9, "Methane", "Methane", "CH4", GT_MetaItem_Cell.instance.getUnunifiedStack(9, 1), GT_ModHandler.getEmptyCell(1));
        GT_MetaItem_Gas.addItem(15, "Nitrogen", "Nitrogen", "N", GT_MetaItem_Cell.instance.getUnunifiedStack(15, 1), GT_ModHandler.getEmptyCell(1));
        GT_MetaItem_Gas.addItem(38, "Nitrogen Dioxide", "NitrogenDioxide", "NO2", GT_MetaItem_Cell.instance.getUnunifiedStack(38, 1), GT_ModHandler.getEmptyCell(1));
        GT_MetaItem_Liquid.addItem(4, "Wolframium", "Wolframium", "W", GT_MetaItem_Cell.instance.getUnunifiedStack(4, 1), GT_ModHandler.getEmptyCell(1), 34, 32, 40);
        GT_MetaItem_Liquid.addItem(5, "Lithium", "Lithium", "Li", GT_MetaItem_Cell.instance.getUnunifiedStack(5, 1), GT_ModHandler.getEmptyCell(1), 145, 191, 255);
        GT_MetaItem_Liquid.addItem(7, "Silicon", "Silicon", "Si", GT_MetaItem_Cell.instance.getUnunifiedStack(7, 1), GT_ModHandler.getEmptyCell(1), 63, 36, 85);
        GT_MetaItem_Liquid.addItem(10, "Berylium", "Berylium", "Be", GT_MetaItem_Cell.instance.getUnunifiedStack(10, 1), GT_ModHandler.getEmptyCell(1), 0, 46, 0);
        GT_MetaItem_Liquid.addItem(11, "Calcium", "Calcium", "Ca", GT_MetaItem_Cell.instance.getUnunifiedStack(11, 1), GT_ModHandler.getEmptyCell(1), 234, 79, 97);
        GT_MetaItem_Liquid.addItem(12, "Sodium", "Sodium", "Na", GT_MetaItem_Cell.instance.getUnunifiedStack(12, 1), GT_ModHandler.getEmptyCell(1), 43, 72, 164);
        GT_MetaItem_Liquid.addItem(13, "Chlorite", "Chlorite", "Cl", GT_MetaItem_Cell.instance.getUnunifiedStack(13, 1), GT_ModHandler.getEmptyCell(1), 25, 100, 95);
        GT_MetaItem_Liquid.addItem(14, "Potassium", "Potassium", "K", GT_MetaItem_Cell.instance.getUnunifiedStack(14, 1), GT_ModHandler.getEmptyCell(1), 164, 181, 181);
        GT_MetaItem_Liquid.addItem(16, "Mercury", "Mercury", "Hg", GT_MetaItem_Cell.instance.getUnunifiedStack(16, 1), GT_ModHandler.getEmptyCell(1), 170, 170, 170);
        GT_MetaItem_Liquid.addItem(22, "Nitro Diesel", "NitroFuel", "", GT_MetaItem_Cell.instance.getUnunifiedStack(22, 1), GT_ModHandler.getEmptyCell(1), 191, 255, 100);
        GT_MetaItem_Liquid.addItem(32, "Sodium Persulfate", "SodiumPersulfate", "Na2S2O8", GT_MetaItem_Cell.instance.getUnunifiedStack(32, 1), GT_ModHandler.getEmptyCell(1), 0, 103, 67);
        GT_MetaItem_Liquid.addItem(33, "Calcium Carbonate", "CalciumCarbonate", "CaCO3", GT_MetaItem_Cell.instance.getUnunifiedStack(33, 1), GT_ModHandler.getEmptyCell(1), 91, 16, 18);
        GT_MetaItem_Liquid.addItem(34, "Glyceryl", "Glyceryl", "C3H5N3O9", GT_MetaItem_Cell.instance.getUnunifiedStack(34, 1), GT_ModHandler.getEmptyCell(1), 52, 157, 157);
        GT_MetaItem_Liquid.addItem(35, "Nitro Coalfuel", "NitroCoalFuel", "", GT_MetaItem_Cell.instance.getUnunifiedStack(35, 1), GT_ModHandler.getEmptyCell(1), 0, 43, 43);
        GT_MetaItem_Component.addItem(0, "Energy Flow Circuit", "craftingCircuitTier07", "Manages large amounts of Energy");
        GT_MetaItem_Component.addItem(1, "Data Control Circuit", "eliteCircuit", "Basic Computer Processor");
        GT_MetaItem_Component.addItem(2, "Superconductor", "craftingSuperconductor", "Conducts Energy losslessly");
        GT_MetaItem_Component.addItem(3, "Data Storage Circuit", "craftingCircuitTier05", "Stores Data");
        GT_MetaItem_Component.addItem(4, "Computer Monitor", "craftingMonitorTier02", "Displays things");
        GT_MetaItem_Component.addItem(5, "Conveyor Module", "craftingConveyor", "Moves Items around");
        GT_MetaItem_Component.addItem(16, "BrainTech Aerospace Advanced Reinforced Duct Tape FAL-84", "craftingDuctTape", "If this doesn't fix it, then it's broken");
        GT_MetaItem_Component.addItem(17, "Diamond Sawblade", "craftingDiamondBlade", "Caution! This is very sharp.");
        GT_MetaItem_Component.addItem(18, "Diamond Grinder", "craftingGrinder", "Fancy Grinding Head");
        GT_MetaItem_Component.addItem(19, "Kanthal Heating Coil", "craftingHeatingCoilTier01", "Standard Heating Coil");
        GT_MetaItem_Component.addItem(20, "Nichrome Heating Coil", "craftingHeatingCoilTier02", "Advanced Heating Coil");
        GT_MetaItem_Component.addItem(21, "Cupronickel Heating Coil", "craftingHeatingCoilTier00", "Cheap and simple Heating Coil");
        GT_MetaItem_Component.addItem(22, "Machine Parts", "craftingMachineParts", "Random Machine Parts");
        GT_MetaItem_Component.addItem(23, "Wolframium Grinder", "craftingGrinder", "Regular Grinding Head");
        GT_MetaItem_Component.addItem(24, "Advanced Circuit Parts", "craftingCircuitPartsTier04", "Part of advanced Circuitry");
        GT_MetaItem_Component.addItem(25, "Pneumatic Generator Upgrade", "craftingPneumaticGenerator", "Lets Machines accept MJ");
        GT_MetaItem_Component.addItem(26, "Lithium Battery Upgrade", "craftingLiBatteryUpgrade", "Adds 100000 EU to the Energy Capacity");
        GT_MetaItem_Component.addItem(27, "HV-Transformer Upgrade", "craftingHVTUpgrade", "Higher Tier of Transformer Upgrade");
        GT_MetaItem_Component.addItem(28, "RS-Energy-Cell Upgrade", "craftingEnergyCellUpgrade", "Adds 100000 MJ to the Energy Capacity");
        GT_MetaItem_Component.addItem(32, "Aluminium Machine Hull", "craftingRawMachineTier01", "Machine Block");
        GT_MetaItem_Component.addItem(33, "Bronze Machine Hull", "craftingRawMachineTier01", "Machine Block");
        GT_MetaItem_Component.addItem(34, "Brass Machine Hull", "craftingRawMachineTier00", "Cheap Machine Block");
        GT_MetaItem_Component.addItem(35, "Steel Machine Hull", "craftingRawMachineTier02", "Advanced Machine Block");
        GT_MetaItem_Component.addItem(36, "Titanium Machine Hull", "craftingRawMachineTier03", "Very Advanced Machine Block");
        GT_OreDictUnificator.registerOre("craftingRawMachineTier02", GT_MetaItem_Component.instance.getUnunifiedStack(36, 1));
        GT_MetaItem_Component.addItem(48, "Basic Circuit Board", "craftingCircuitBoardTier02", "Just a simple Circuit Plate");
        GT_MetaItem_Component.addItem(49, "Advanced Circuit Board", "craftingCircuitBoardTier04", "Standard Circuit Plate");
        GT_MetaItem_Component.addItem(50, "Elite Circuit Board", "craftingCircuitBoardTier06", "Highly advanced Circuit Plate");
        GT_Log.out.println("GT_Mod: Trying to add Nuclear Control Sensor Kit/Card...");
        try {
            Class.forName("shedar.mods.ic2.nuclearcontrol.api.IRemoteSensor");
            this.mItems[16] = new GT_SensorCard_Item(this.mItemIDs[16]);
            this.mItems[17] = new GT_SensorKit_Item(this.mItemIDs[17]);
            GT_Log.out.println("GT_Mod: ...succeeded.");
        }
        catch (Throwable e) {
            GT_Log.out.println("GT_Mod: ...failed.");
        }
        GT_Log.out.println("GT_Mod: Register Regular Items.");
        this.mItems[18] = new GT_EnergyArmor_Item(this.mItemIDs[18], 1000000000, Integer.MAX_VALUE, 1, 10, -1, 100.0, true, 1, tArmorID1);
        this.mItems[30] = new GT_Mortar_Item(this.mItemIDs[30], 64, GT_OreDictUnificator.get("dustIron", 1));
        this.mItems[31] = new GT_Generic_Item(this.mItemIDs[31]);
        this.mItems[32] = new GT_Sonictron_Item(this.mItemIDs[32]);
        this.mItems[33] = new GT_Destructopack_Item(this.mItemIDs[33]);
        this.mItems[34] = new GT_CoolantCell_Item(this.mItemIDs[34], 60000, 1);
        this.mItems[35] = new GT_CoolantCell_Item(this.mItemIDs[35], 180000, 3);
        this.mItems[36] = new GT_CoolantCell_Item(this.mItemIDs[36], 360000, 6);
        this.mItems[37] = new GT_EnergyStore_Item(this.mItemIDs[37], 10000000, 8192, 4, 37, 37);
        this.mItems[38] = new GT_EnergyArmor_Item(this.mItemIDs[38], 10000000, 8192, 4, 0, 512, 0.0, false, 1, tArmorID3);
        this.mItems[39] = new GT_Jackhammer_Item(this.mItemIDs[39], 10000, 1, 7.5f, 50);
        this.mItems[40] = new GT_NeutronReflector_Item(this.mItemIDs[40], 0);
        this.mItems[41] = new GT_Jackhammer_Item(this.mItemIDs[41], 10000, 1, 15.0f, 100);
        this.mItems[42] = new GT_Jackhammer_Item(this.mItemIDs[42], 100000, 2, 45.0f, 250);
        this.mItems[43] = new GT_Dataorb_Item(this.mItemIDs[43]);
        this.mItems[44] = new GT_EnergyArmor_Item(this.mItemIDs[44], 10000, 32, 1, 0, 48, 0.0, false, 0, tArmorID1);
        this.mItems[45] = new GT_EnergyArmor_Item(this.mItemIDs[45], 10000000, 8192, 4, 0, 0, 0.0, true, 1, tArmorID1);
        this.mItems[46] = new GT_Rockcutter_Item(this.mItemIDs[46]);
        this.mItems[47] = new GT_Teslastaff_Item(this.mItemIDs[47]);
        this.mItems[48] = new GT_RadioactiveCell_Item(this.mItemIDs[48], 25000, 1, -5, null);
        this.mItems[49] = new GT_RadioactiveCell_Item(this.mItemIDs[49], 25000, 2, -5, null);
        this.mItems[50] = new GT_RadioactiveCell_Item(this.mItemIDs[50], 25000, 4, -5, null);
        this.mItems[51] = new GT_RadioactiveCell_Item(this.mItemIDs[51], 20000, 1, 2, GT_ModHandler.getIC2Item("nearDepletedUraniumCell", 4));
        this.mItems[52] = new GT_RadioactiveCell_Item(this.mItemIDs[52], 20000, 2, 2, GT_ModHandler.getIC2Item("nearDepletedUraniumCell", 4));
        this.mItems[53] = new GT_RadioactiveCell_Item(this.mItemIDs[53], 20000, 4, 2, GT_ModHandler.getIC2Item("nearDepletedUraniumCell", 4));
        this.mItems[55] = new GT_Debug_Item(this.mItemIDs[55]);
        this.mItems[56] = new GT_EnergyStore_Item(this.mItemIDs[56], 100000, 128, 1, 56, 57).setMaxStackSize(16).setMaxDamage(0);
        this.mItems[57] = new GT_EnergyStore_Item(this.mItemIDs[57], 100000, 128, 1, 56, 57);
        this.mItems[58] = new GT_EnergyArmor_Item(this.mItemIDs[58], 600000, 128, 1, 0, 0, 0.0, true, 1, tArmorID2);
        this.mItems[60] = new GT_CoolantCell_Item(this.mItemIDs[60], 60000, 1);
        this.mItems[61] = new GT_CoolantCell_Item(this.mItemIDs[61], 180000, 3);
        this.mItems[62] = new GT_CoolantCell_Item(this.mItemIDs[62], 360000, 6);
        this.mItems[63] = new GT_Scanner_Item(this.mItemIDs[63]);
        GT_Log.out.println("GT_Mod: Hiding certain Items from NEI.");
        try {
            Class.forName("codechicken.nei.api.API");
            API.hideItem((int)this.mItems[4].itemID);
            API.hideItem((int)this.mItems[13].itemID);
            API.hideItem((int)this.mItems[14].itemID);
            API.hideItem((int)this.mItems[15].itemID);
            API.hideItem((int)this.mItems[16].itemID);
            API.hideItem((int)GT_ModHandler.getIC2Item((String)"clayDust", (int)1).itemID);
            API.hideItem((int)GT_ModHandler.getIC2Item((String)"coalDust", (int)1).itemID);
            API.hideItem((int)GT_ModHandler.getIC2Item((String)"ironDust", (int)1).itemID);
            API.hideItem((int)GT_ModHandler.getIC2Item((String)"goldDust", (int)1).itemID);
            API.hideItem((int)GT_ModHandler.getIC2Item((String)"tinDust", (int)1).itemID);
            API.hideItem((int)GT_ModHandler.getIC2Item((String)"copperDust", (int)1).itemID);
            API.hideItem((int)GT_ModHandler.getIC2Item((String)"bronzeDust", (int)1).itemID);
            API.hideItem((int)GT_ModHandler.getIC2Item((String)"silverDust", (int)1).itemID);
        }
        catch (Throwable e) {
            // empty catch block
        }
        GT_Log.out.println("GT_Mod: Register regular Item Names.");
        for (int i = 16; i < this.mItems.length; ++i) {
            if (this.mItems[i] == null) continue;
            this.mItems[i].setIconIndex(i);
            this.mItems[i].setItemName(GT_LanguageManager.mNameListItem[i]);
            GameRegistry.registerItem((Item)this.mItems[i], (String)((GT_LanguageManager.mNameListItem[i].startsWith("GT_") ? "" : "GT_") + GT_LanguageManager.mNameListItem[i]), (String)"GregTech_Addon");
            GT_LanguageManager.addStringLocalization(this.mItems[i].getItemName() + ".name", GT_LanguageManager.mRegionalNameListItem[i]);
        }
        GT_Log.out.println("GT_Mod: Registering GT/IC2-Circuitry and similar to the OreDict.");
        GT_OreDictUnificator.registerOre("craftingLiBattery", new ItemStack(this.mItems[56], 1, -1));
        GT_OreDictUnificator.registerOre("craftingLiBattery", new ItemStack(this.mItems[57], 1, -1));
        GT_OreDictUnificator.registerOre("advancedBattery", new ItemStack(this.mItems[56], 1, -1));
        GT_OreDictUnificator.registerOre("advancedBattery", new ItemStack(this.mItems[57], 1, -1));
        GT_OreDictUnificator.registerOre("craftingCircuitTier08", new ItemStack(this.mItems[43], 1));
        GT_OreDictUnificator.registerOre("battery", new ItemStack(GT_ModHandler.getIC2Item("reBattery", 1).getItem(), 1, -1));
        GT_OreDictUnificator.registerOre("battery", new ItemStack(GT_ModHandler.getIC2Item("chargedReBattery", 1).getItem(), 1, -1));
        GT_OreDictUnificator.registerOre("crafting60kEUPack", new ItemStack(GT_ModHandler.getIC2Item("batPack", 1).getItem(), 1, -1));
        GT_OreDictUnificator.registerOre("crafting100kEUStore", new ItemStack(this.mItems[56], 1, -1));
        GT_OreDictUnificator.registerOre("crafting100kEUStore", new ItemStack(this.mItems[57], 1, -1));
        GT_OreDictUnificator.registerOre("crafting300kEUPack", new ItemStack(GT_ModHandler.getIC2Item("lapPack", 1).getItem(), 1, -1));
        GT_OreDictUnificator.registerOre("crafting600kEUPack", new ItemStack(this.mItems[58], 1, -1));
        GT_OreDictUnificator.registerOre("crafting100kEUStore", new ItemStack(GT_ModHandler.getIC2Item("energyCrystal", 1).getItem(), 1, -1));
        GT_OreDictUnificator.registerOre("crafting1kkEUStore", new ItemStack(GT_ModHandler.getIC2Item("lapotronCrystal", 1).getItem(), 1, -1));
        GT_OreDictUnificator.registerOre("crafting10kkEUStore", new ItemStack(this.mItems[37], 1, -1));
        GT_OreDictUnificator.registerOre("crafting10kkEUPack", new ItemStack(this.mItems[45], 1, -1));
        GT_OreDictUnificator.registerOre("crafting10kCoolantStore", new ItemStack(GT_ModHandler.getIC2Item("reactorCoolantSimple", 1).getItem(), 1, -1));
        GT_OreDictUnificator.registerOre("crafting30kCoolantStore", new ItemStack(GT_ModHandler.getIC2Item("reactorCoolantTriple", 1).getItem(), 1, -1));
        GT_OreDictUnificator.registerOre("crafting60kCoolantStore", new ItemStack(GT_ModHandler.getIC2Item("reactorCoolantSix", 1).getItem(), 1, -1));
        GT_OreDictUnificator.registerOre("crafting60kCoolantStore", new ItemStack(this.mItems[60], 1, -1));
        GT_OreDictUnificator.registerOre("crafting60kCoolantStore", new ItemStack(this.mItems[34], 1, -1));
        GT_OreDictUnificator.registerOre("crafting180kCoolantStore", new ItemStack(this.mItems[61], 1, -1));
        GT_OreDictUnificator.registerOre("crafting180kCoolantStore", new ItemStack(this.mItems[35], 1, -1));
        GT_OreDictUnificator.registerOre("crafting360kCoolantStore", new ItemStack(this.mItems[62], 1, -1));
        GT_OreDictUnificator.registerOre("crafting360kCoolantStore", new ItemStack(this.mItems[36], 1, -1));
        GT_OreDictUnificator.registerOre("copperWire", GT_ModHandler.getIC2Item("insulatedCopperCableItem", 1));
        GT_OreDictUnificator.registerOre("copperWire", GT_ModHandler.getIC2Item("insulatedCopperCableItem", 1));
        GT_OreDictUnificator.registerOre("copperWire", GT_ModHandler.getIC2Item("insulatedCopperCableItem", 1));
        GT_OreDictUnificator.registerOre("craftingRawMachineTier01", GT_ModHandler.getTEItem("machineFrame", 1));
        GT_OreDictUnificator.registerOre("craftingRawMachineTier01", GT_ModHandler.getIC2Item("machine", 1));
        GT_OreDictUnificator.registerOre("craftingRawMachineTier02", GT_ModHandler.getIC2Item("advancedMachine", 1));
        GT_OreDictUnificator.registerOre("craftingRawMachineTier04", new ItemStack(this.mBlocks[0], 1, 10));
        GT_OreDictUnificator.registerOre("craftingCircuitTier00", new ItemStack(Block.torchRedstoneIdle, 1, -1));
        GT_OreDictUnificator.registerOre("craftingCircuitTier00", new ItemStack(Block.torchRedstoneActive, 1, -1));
        GT_OreDictUnificator.registerOre("craftingCircuitTier00", new ItemStack(Block.lever, 1, -1));
        GT_OreDictUnificator.registerOre("basicCircuit", GT_ModHandler.getIC2Item("electronicCircuit", 1));
        GT_OreDictUnificator.registerOre("advancedCircuit", GT_ModHandler.getIC2Item("advancedCircuit", 1));
        GT_OreDictUnificator.registerOre("craftingCircuitTier10", new ItemStack(this.mBlocks[1], 1, 4));
        GT_OreDictUnificator.registerOre("craftingWorkBench", new ItemStack(Block.workbench, 1));
        GT_OreDictUnificator.registerOre("craftingWorkBench", new ItemStack(this.mBlocks[1], 1, 16));
        GT_OreDictUnificator.registerOre("craftingPump", GT_ModHandler.getIC2Item("pump", 1));
        GT_OreDictUnificator.registerOre("craftingTeleporter", GT_ModHandler.getIC2Item("teleporter", 1));
        GT_OreDictUnificator.registerOre("paperVanilla", new ItemStack(Item.paper, 1));
        GT_OreDictUnificator.registerOre("paperMap", new ItemStack((Item)Item.emptyMap, 1));
        GT_OreDictUnificator.registerOre("paperMap", new ItemStack((Item)Item.map, 1));
        GT_OreDictUnificator.registerOre("bookVanilla", new ItemStack(Item.book, 1));
        GT_OreDictUnificator.registerOre("bookWritable", new ItemStack(Item.writableBook, 1));
        GT_OreDictUnificator.registerOre("bookWritten", new ItemStack(Item.writtenBook, 1));
        GT_OreDictUnificator.registerOre("bookEnchanted", new ItemStack((Item)Item.enchantedBook, 1));
        GT_Log.out.println("GT_Mod: Register colors to the OreDict.");
        GT_OreDictUnificator.registerOre("dyeCyan", GT_MetaItem_Dust.instance.getUnunifiedStack(2, 1));
        GT_OreDictUnificator.registerOre("dyeBlue", GT_MetaItem_Dust.instance.getUnunifiedStack(5, 1));
        this.mLoaded = true;
        GT_Log.out.println("GT_Mod: Load-Phase finished!");
    }

    /*
     * Opcode count of 27859 triggered aggressive code reduction.  Override with --aggressivesizethreshold.
     */
    @Mod.PostInit
    public void postload(FMLPostInitializationEvent aEvent) {
        int tTinCanNuggetCount;
        ItemStack tStack3;
        int i;
        if (this.mDoNotInit) {
            return;
        }
        GT_Log.out.println("GT_Mod: Beginning PostLoad-Phase.");
        GT_Log.out.println("GT_Mod: Initializing Proxy.");
        gregtechproxy.initialize();
        Item tNewItem = null;
        boolean temp = false;
        GT_Log.out.println("GT_Mod: Checking if Items got Overloaded.");
        for (i = 0; i < this.mItems.length; ++i) {
            if (this.mItems[i] == null || Item.itemsList[this.mItems[i].itemID] == this.mItems[i]) continue;
            GT_Log.out.println("GT_Mod: Another Mods ItemID is conflicting.");
            GT_Log.out.println("GT_Mod: Errored.");
            throw new GT_ItsNotMyFaultException("One of the GregTech-Items got overloaded. Check the ID-Config of the Mod you just installed for Conflicts mentioned in the Log with 'CONFLICT @'. That is an Item-ID-Conflict! Don't bother ANY Modauthor with that, we most likely won't help you at all!");
        }
        GT_Log.out.println("GT_Mod: Scanning for certain kinds of compatible Machineblocks.");
        ItemStack tStack2 = GT_OreDictUnificator.get("ingotBronze", 1);
        ItemStack tStack = GT_ModHandler.getRecipeOutput(new ItemStack[]{tStack2, tStack2, tStack2, tStack2, null, tStack2, tStack2, tStack2, tStack2});
        if (null != tStack) {
            GT_OreDictUnificator.registerOre("craftingRawMachineTier01", tStack);
            GT_ModHandler.addPulverisationRecipe(tStack, GT_OreDictUnificator.get("dustBronze", 8), null, 0, false);
            GT_ModHandler.addSmeltingRecipe(tStack, GT_OreDictUnificator.get("ingotBronze", 8));
        }
        if (null != (tStack = GT_ModHandler.getRecipeOutput(new ItemStack[]{tStack2 = GT_OreDictUnificator.get("ingotRefinedIron", 1), tStack2, tStack2, tStack2, null, tStack2, tStack2, tStack2, tStack2}))) {
            GT_OreDictUnificator.registerOre("craftingRawMachineTier01", tStack);
            GT_ModHandler.addPulverisationRecipe(tStack, GT_OreDictUnificator.get("dustRefinedIron", GT_OreDictUnificator.get("dustIron", 8), 8), null, 0, false);
            GT_ModHandler.addSmeltingRecipe(tStack, GT_OreDictUnificator.get("ingotRefinedIron", 8));
        }
        if (null != (tStack = GT_ModHandler.getRecipeOutput(new ItemStack[]{tStack2 = GT_OreDictUnificator.get("plateBronze", 1), tStack2, tStack2, tStack2, null, tStack2, tStack2, tStack2, tStack2}))) {
            GT_OreDictUnificator.registerOre("craftingRawMachineTier01", tStack);
            GT_ModHandler.addPulverisationRecipe(tStack, GT_OreDictUnificator.get("dustBronze", 8), null, 0, false);
            GT_ModHandler.addSmeltingRecipe(tStack, GT_OreDictUnificator.get("ingotBronze", 8));
        }
        if (null != (tStack = GT_ModHandler.getRecipeOutput(new ItemStack[]{tStack2 = GT_OreDictUnificator.get("plateRefinedIron", 1), tStack2, tStack2, tStack2, null, tStack2, tStack2, tStack2, tStack2}))) {
            GT_OreDictUnificator.registerOre("craftingRawMachineTier01", tStack);
            GT_ModHandler.addPulverisationRecipe(tStack, GT_OreDictUnificator.get("dustRefinedIron", GT_OreDictUnificator.get("dustIron", 8), 8), null, 0, false);
            GT_ModHandler.addSmeltingRecipe(tStack, GT_OreDictUnificator.get("ingotRefinedIron", 8));
        }
        if (null != (tStack = GT_ModHandler.getRecipeOutput(new ItemStack[]{tStack2 = GT_OreDictUnificator.get("ingotIron", 1), tStack3 = new ItemStack(Block.glass, 1, 0), tStack2, tStack3, GT_OreDictUnificator.get("ingotGold", 1), tStack3, tStack2, tStack3, tStack2}))) {
            GT_OreDictUnificator.registerOre("craftingRawMachineTier01", tStack);
            GT_ModHandler.addPulverisationRecipe(tStack, GT_OreDictUnificator.get("dustIron", 4), GT_OreDictUnificator.get("dustGold", 1), 0, false);
        }
        GT_Log.out.println("GT_Mod: Adding Food Recipes to the Automatic Canning Machine. (also during the following Item Iteration)");
        GT_Mod.addCannerRecipe(new ItemStack(Item.rottenFlesh, 1, -1), GT_ModHandler.getIC2Item("tinCan", 2), GT_ModHandler.getIC2Item("filledTinCan", 2, 1), null, 200, 1);
        GT_Mod.addCannerRecipe(new ItemStack(Item.spiderEye, 1, -1), GT_ModHandler.getIC2Item("tinCan", 1), GT_ModHandler.getIC2Item("filledTinCan", 1, 1), null, 100, 1);
        GT_Mod.addCannerRecipe(new ItemStack(Item.poisonousPotato, 1, -1), GT_ModHandler.getIC2Item("tinCan", 1), GT_ModHandler.getIC2Item("filledTinCan", 1, 1), null, 100, 1);
        GT_Mod.addCannerRecipe(new ItemStack(Item.cake, 1, -1), GT_ModHandler.getIC2Item("tinCan", 6), GT_ModHandler.getIC2Item("filledTinCan", 6, 0), null, 600, 1);
        GT_Mod.addCannerRecipe(new ItemStack(Block.cake, 1, -1), GT_ModHandler.getIC2Item("tinCan", 6), GT_ModHandler.getIC2Item("filledTinCan", 6, 0), null, 600, 1);
        GT_Mod.addCannerRecipe(new ItemStack(Item.bowlSoup, 1, -1), GT_ModHandler.getIC2Item("tinCan", 3), GT_ModHandler.getIC2Item("filledTinCan", 3, 0), new ItemStack(Item.bowlEmpty, 1), 300, 1);
        GT_Log.out.println("GT_Mod: Scanning ItemList.");
        for (i = 0; i < Item.itemsList.length; ++i) {
            int tFoodValue;
            String tName;
            Item tItem = Item.itemsList[i];
            if (tItem == null || (tName = tItem.getItemName()) == null) continue;
            if (tItem instanceof ItemFood && tItem.itemID != GT_ModHandler.getIC2Item((String)"filledTinCan", (int)1, (int)0).itemID && (tFoodValue = (int)Math.ceil((double)((ItemFood)tItem).getHealAmount() / 2.0)) > 0) {
                GT_Mod.addCannerRecipe(new ItemStack(tItem, 1, -1), GT_ModHandler.getIC2Item("tinCan", tFoodValue), GT_ModHandler.getIC2Item("filledTinCan", tFoodValue, 0), tItem.hasContainerItem() ? new ItemStack(tItem.getContainerItem(), 1, 0) : null, tFoodValue * 100, 1);
            }
            if (tName.equals("item.myst.page")) {
                GT_OreDictUnificator.registerOre("paperMystcraft", new ItemStack(tItem, 1, -1));
            }
            if (tName.equals("item.myst.agebook")) {
                GT_OreDictUnificator.registerOre("bookMystcraftAge", new ItemStack(tItem, 1, -1));
            }
            if (tName.equals("item.myst.linkbook")) {
                GT_OreDictUnificator.registerOre("bookMystcraftLink", new ItemStack(tItem, 1, -1));
            }
            if (tName.equals("item.myst.notebook")) {
                GT_OreDictUnificator.registerOre("bookNotes", new ItemStack(tItem, 1, -1));
            }
            if (tName.equals("item.itemManuelBook")) {
                GT_OreDictUnificator.registerOre("bookWritten", new ItemStack(tItem, 1, 0));
            }
            if (tName.equals("item.blueprintItem")) {
                GT_OreDictUnificator.registerOre("paperBlueprint", new ItemStack(tItem, 1, -1));
            }
            if (tName.equals("item.ccprintout")) {
                GT_OreDictUnificator.registerOre("paperWritten", new ItemStack(tItem, 1, 0));
                GT_OreDictUnificator.registerOre("paperWritten", new ItemStack(tItem, 1, 1));
                GT_OreDictUnificator.registerOre("bookWritten", new ItemStack(tItem, 1, 2));
            }
            if (tName.equals("item.blueprintItem")) {
                GT_OreDictUnificator.registerOre("paperBlueprint", new ItemStack(tItem, 1, -1));
            }
            if (tName.equals("item.wirelessmap")) {
                GT_OreDictUnificator.registerOre("paperMap", new ItemStack(tItem, 1, -1));
            }
            if (tName.equals("item.ItemResearchNotes")) {
                GT_OreDictUnificator.registerOre("paperResearch", new ItemStack(tItem, 1, -1));
            }
            if (tName.equals("item.ItemThaumonomicon")) {
                GT_OreDictUnificator.registerOre("bookThaumonomicon", new ItemStack(tItem, 1, -1));
            }
            if (tName.equals("item.ItemEssence")) {
                GT_ModHandler.mTCFluxEssence = new ItemStack(tItem, 1, -1);
            }
            if (tName.equals("item.ItemWispEssence")) {
                GT_ModHandler.mTCWispEssence = new ItemStack(tItem, 1, -1);
            }
            if (tName.equals("item.ItemResource")) {
                GT_ModHandler.mTCResource = new ItemStack(tItem, 1, -1);
                GT_OreDictUnificator.registerOre("itemQuicksilver", new ItemStack(tItem, 1, 3));
                GT_OreDictUnificator.registerOre("paperResearchFragment", new ItemStack(tItem, 1, 9));
            }
            if (tName.equals("item.ItemShard")) {
                GT_ModHandler.mTCCrystal = new ItemStack(tItem, 1, -1);
            }
            if (tName.equals("item.ItemEnderDust")) {
                GT_OreDictUnificator.registerOre("dustEnderPearl", new ItemStack(tItem, 1, 0));
            }
            if (tName.equals("tile.extrabiomes.redrock") && tItem.itemID < 4096) {
                GT_OreDictUnificator.registerOre("stoneRedRock", new ItemStack(tItem, 1, 0));
                GT_OreDictUnificator.registerOre("stoneRedRock", new ItemStack(tItem, 1, 1));
                GT_OreDictUnificator.registerOre("stoneRedRock", new ItemStack(tItem, 1, 2));
            }
            if (tName.equals("tile.rpstone") && tItem.itemID < 4096) {
                GT_OreDictUnificator.registerOre("stoneMarble", new ItemStack(tItem, 1, 0));
                GT_OreDictUnificator.registerOre("stoneBasalt", new ItemStack(tItem, 1, 1));
                GT_OreDictUnificator.registerOre("stoneMarble", new ItemStack(tItem, 1, 2));
                GT_OreDictUnificator.registerOre("stoneBasalt", new ItemStack(tItem, 1, 3));
                GT_OreDictUnificator.registerOre("stoneBasalt", new ItemStack(tItem, 1, 4));
                GT_OreDictUnificator.registerOre("stoneBasalt", new ItemStack(tItem, 1, 5));
                GT_OreDictUnificator.registerOre("stoneBasalt", new ItemStack(tItem, 1, 6));
            }
            if (tName.equals("tile.enderchest")) {
                GT_OreDictUnificator.registerOre("craftingEnderChest", new ItemStack(tItem, 1, -1));
            }
            if (tName.equals("tile.autoWorkbenchBlock")) {
                GT_OreDictUnificator.registerOre("craftingWorkBench", new ItemStack(tItem, 1, 0));
            }
            if (tName.equals("tile.pumpBlock")) {
                GT_OreDictUnificator.registerOre("craftingPump", new ItemStack(tItem, 1, 0));
            }
            if (tName.equals("tile.tankBlock")) {
                GT_OreDictUnificator.registerOre("craftingTank", new ItemStack(tItem, 1, 0));
            }
            if (tName.equals("item.drawplateDiamond")) {
                GT_ModHandler.mDiamondDrawplate = new ItemStack(tItem, 1);
            }
            if (tName.equals("item.woodenGearItem")) {
                GT_ModHandler.mBCWoodGear = new ItemStack(tItem, 1);
            }
            if (tName.equals("item.stoneGearItem")) {
                GT_ModHandler.mBCStoneGear = new ItemStack(tItem, 1);
            }
            if (tName.equals("item.ironGearItem")) {
                GT_ModHandler.mBCIronGear = new ItemStack(tItem, 1);
            }
            if (tName.equals("item.goldGearItem")) {
                GT_ModHandler.mBCGoldGear = new ItemStack(tItem, 1);
            }
            if (tName.equals("item.diamondGearItem")) {
                GT_ModHandler.mBCDiamondGear = new ItemStack(tItem, 1);
            }
            if (tName.equals("item.canLava")) {
                GT_Mod.addCentrifugeRecipe(new ItemStack(tItem, 16), 0, GT_OreDictUnificator.get("ingotElectrum", 1), GT_OreDictUnificator.get("ingotCopper", 4), GT_OreDictUnificator.get("dustSmallTungsten", 1), GT_OreDictUnificator.get("ingotTin", 6), 15000);
            }
            if (tName.equals("item.refractoryLava")) {
                GT_Mod.addCentrifugeRecipe(new ItemStack(tItem, 16), 0, GT_OreDictUnificator.get("ingotElectrum", 1), GT_OreDictUnificator.get("ingotCopper", 4), GT_OreDictUnificator.get("dustSmallTungsten", 1), GT_OreDictUnificator.get("ingotTin", 2), 10000);
            }
            if (tNewItem == null) continue;
            if (aEvent.getSide() == Side.CLIENT) {
                tNewItem.setCreativeTab(tItem.getCreativeTab());
                tNewItem.setTextureFile(tItem.getTextureFile());
                tNewItem.setIconIndex(tItem.getIconFromDamage(0));
            }
            tNewItem.setItemName(tName.replaceFirst("item.", ""));
            tNewItem.setMaxStackSize(tItem.getItemStackLimit());
            tNewItem = null;
        }
        GT_Log.out.println("GT_Mod: Getting Storage Blocks of Redpower for the OreDictUnification.");
        if (GT_ModHandler.mNikolite != null) {
            GT_OreDictUnificator.set("dustNikolite", GT_ModHandler.mNikolite, GT_Config.mUnificatorRP);
            tStack2 = GT_ModHandler.mRuby;
            tStack3 = GT_MetaItem_Material.instance.getUnunifiedStack(32, 1);
            if (tStack2 != null) {
                GT_ModHandler.removeRecipe(new ItemStack[]{tStack2, tStack3, tStack3, tStack3, tStack3, tStack3, tStack3, tStack3, tStack3});
                tStack = GT_ModHandler.getRecipeOutput(new ItemStack[]{tStack2, tStack2, tStack2, tStack2, tStack2, tStack2, tStack2, tStack2, tStack2});
                if (null != tStack) {
                    GT_OreDictUnificator.set("blockRuby", tStack, GT_Config.mUnificatorRP);
                }
            }
            tStack2 = GT_ModHandler.mSapphire;
            tStack3 = GT_MetaItem_Material.instance.getUnunifiedStack(33, 1);
            if (tStack2 != null) {
                GT_ModHandler.removeRecipe(new ItemStack[]{tStack2, tStack3, tStack3, tStack3, tStack3, tStack3, tStack3, tStack3, tStack3});
                tStack = GT_ModHandler.getRecipeOutput(new ItemStack[]{tStack2, tStack2, tStack2, tStack2, tStack2, tStack2, tStack2, tStack2, tStack2});
                if (null != tStack) {
                    GT_OreDictUnificator.set("blockSapphire", tStack, GT_Config.mUnificatorRP);
                }
            }
            tStack2 = GT_ModHandler.mGreenSapphire;
            tStack3 = GT_MetaItem_Material.instance.getUnunifiedStack(34, 1);
            if (tStack2 != null) {
                GT_ModHandler.removeRecipe(new ItemStack[]{tStack2, tStack3, tStack3, tStack3, tStack3, tStack3, tStack3, tStack3, tStack3});
                tStack = GT_ModHandler.getRecipeOutput(new ItemStack[]{tStack2, tStack2, tStack2, tStack2, tStack2, tStack2, tStack2, tStack2, tStack2});
                if (null != tStack) {
                    GT_OreDictUnificator.set("blockGreenSapphire", tStack, GT_Config.mUnificatorRP);
                }
            }
            tStack2 = GT_ModHandler.mSilver;
            tStack3 = GT_MetaItem_Material.instance.getUnunifiedStack(17, 1);
            if (tStack2 != null) {
                GT_ModHandler.removeRecipe(new ItemStack[]{tStack2, tStack3, tStack3, tStack3, tStack3, tStack3, tStack3, tStack3, tStack3});
                tStack = GT_ModHandler.getRecipeOutput(new ItemStack[]{tStack2, tStack2, tStack2, tStack2, tStack2, tStack2, tStack2, tStack2, tStack2});
                if (null != tStack) {
                    GT_OreDictUnificator.set("blockSilver", tStack, GT_Config.mUnificatorRP);
                }
            }
            tStack2 = GT_ModHandler.mCopper;
            tStack3 = GT_ModHandler.getIC2Item("copperIngot", 1);
            if (tStack2 != null) {
                GT_ModHandler.removeRecipe(new ItemStack[]{tStack2, tStack3, tStack3, tStack3, tStack3, tStack3, tStack3, tStack3, tStack3});
                tStack = GT_ModHandler.getRecipeOutput(new ItemStack[]{tStack2, tStack2, tStack2, tStack2, tStack2, tStack2, tStack2, tStack2, tStack2});
                if (null != tStack) {
                    GT_OreDictUnificator.set("blockCopper", tStack, GT_Config.mUnificatorRP);
                }
            }
            tStack2 = GT_ModHandler.mTin;
            tStack3 = GT_ModHandler.getIC2Item("tinIngot", 1);
            if (tStack2 != null) {
                GT_ModHandler.removeRecipe(new ItemStack[]{tStack2, tStack3, tStack3, tStack3, tStack3, tStack3, tStack3, tStack3, tStack3});
                tStack = GT_ModHandler.getRecipeOutput(new ItemStack[]{tStack2, tStack2, tStack2, tStack2, tStack2, tStack2, tStack2, tStack2, tStack2});
                if (null != tStack) {
                    GT_OreDictUnificator.set("blockTin", tStack, GT_Config.mUnificatorRP);
                }
            }
            tStack2 = GT_ModHandler.mBrass;
            tStack3 = GT_MetaItem_Material.instance.getUnunifiedStack(25, 1);
            if (tStack2 != null) {
                GT_ModHandler.removeRecipe(new ItemStack[]{tStack2, tStack3, tStack3, tStack3, tStack3, tStack3, tStack3, tStack3, tStack3});
                tStack = GT_ModHandler.getRecipeOutput(new ItemStack[]{tStack2, tStack2, tStack2, tStack2, tStack2, tStack2, tStack2, tStack2, tStack2});
                if (null != tStack) {
                    GT_OreDictUnificator.set("blockBrass", tStack, GT_Config.mUnificatorRP);
                }
            }
        }
        GT_Log.out.println("GT_Mod: Grabbing Liquids of other Mods to register Liquid Cells, and adding Liquid Transposer Recipes for them");
        LiquidStack tLiquid = LiquidDictionary.getLiquid((String)"Oil", (int)1000);
        if (null != tLiquid) {
            tStack = GT_MetaItem_Cell.instance.getUnunifiedStack(17, 1);
            LiquidContainerRegistry.registerLiquid((LiquidContainerData)new LiquidContainerData(tLiquid, tStack, GT_ModHandler.getEmptyCell(1)));
            GT_ModHandler.addLiquidTransposerRecipe(GT_ModHandler.getEmptyCell(1), tLiquid, tStack, 160);
        }
        if (null != (tLiquid = LiquidDictionary.getLiquid((String)"Fuel", (int)1000))) {
            tStack = GT_MetaItem_Cell.instance.getUnunifiedStack(18, 1);
            LiquidContainerRegistry.registerLiquid((LiquidContainerData)new LiquidContainerData(tLiquid, tStack, GT_ModHandler.getEmptyCell(1)));
            GT_ModHandler.addLiquidTransposerRecipe(GT_ModHandler.getEmptyCell(1), tLiquid, tStack, 160);
        }
        if (null != (tLiquid = LiquidDictionary.getLiquid((String)"biofuel", (int)1000))) {
            tStack = GT_MetaItem_Cell.instance.getUnunifiedStack(19, 1);
            LiquidContainerRegistry.registerLiquid((LiquidContainerData)new LiquidContainerData(tLiquid, tStack, GT_ModHandler.getEmptyCell(1)));
            GT_ModHandler.addLiquidTransposerRecipe(GT_ModHandler.getEmptyCell(1), tLiquid, tStack, 160);
        }
        if (null != (tLiquid = LiquidDictionary.getLiquid((String)"biomass", (int)1000))) {
            tStack = GT_MetaItem_Cell.instance.getUnunifiedStack(20, 1);
            LiquidContainerRegistry.registerLiquid((LiquidContainerData)new LiquidContainerData(tLiquid, tStack, GT_ModHandler.getEmptyCell(1)));
            GT_ModHandler.addLiquidTransposerRecipe(GT_ModHandler.getEmptyCell(1), tLiquid, tStack, 160);
        }
        if (null != (tLiquid = LiquidDictionary.getLiquid((String)"ice", (int)1000))) {
            tStack = GT_MetaItem_Cell.instance.getUnunifiedStack(23, 1);
            LiquidContainerRegistry.registerLiquid((LiquidContainerData)new LiquidContainerData(tLiquid, tStack, GT_ModHandler.getEmptyCell(1)));
            GT_ModHandler.addLiquidTransposerRecipe(GT_ModHandler.getEmptyCell(1), tLiquid, tStack, 160);
        }
        if (null != (tLiquid = LiquidDictionary.getLiquid((String)"seedoil", (int)1000))) {
            tStack = GT_MetaItem_Cell.instance.getUnunifiedStack(24, 1);
            LiquidContainerRegistry.registerLiquid((LiquidContainerData)new LiquidContainerData(tLiquid, tStack, GT_ModHandler.getEmptyCell(1)));
            GT_ModHandler.addLiquidTransposerRecipe(GT_ModHandler.getEmptyCell(1), tLiquid, tStack, 160);
        }
        GT_Log.out.println("GT_Mod: Initializing various Fuels.");
        GT_Mod.addFuel(GT_ModHandler.getIC2Item("biofuelCell", 1), GT_ModHandler.getEmptyCell(1), 6, 0);
        GT_Mod.addFuel(GT_ModHandler.getIC2Item("coalfuelCell", 1), GT_ModHandler.getEmptyCell(1), 16, 0);
        GT_Mod.addFuel(GT_MetaItem_Cell.instance.getStack(19, 1), GT_ModHandler.getEmptyCell(1), 32, 0);
        GT_Mod.addFuel(GT_MetaItem_Cell.instance.getStack(35, 1), GT_ModHandler.getEmptyCell(1), 48, 0);
        GT_Mod.addFuel(GT_MetaItem_Cell.instance.getStack(18, 1), GT_ModHandler.getEmptyCell(1), 384, 0);
        GT_Mod.addFuel(GT_MetaItem_Cell.instance.getStack(22, 1), GT_ModHandler.getEmptyCell(1), 400, 0);
        GT_Mod.addFuel(GT_MetaItem_Cell.instance.getStack(0, 1), GT_ModHandler.getIC2Item("waterCell", 1), 15, 1);
        GT_Mod.addFuel(GT_MetaItem_Cell.instance.getStack(9, 1), GT_ModHandler.getIC2Item("waterCell", 1), 45, 1);
        GT_Mod.addFuel(GT_ModHandler.getIC2Item("lavaCell", 1), GT_ModHandler.getEmptyCell(1), 30, 2);
        GT_Mod.addFuel(GT_ModHandler.getIC2Item("reactorHeatpack", 1), GT_ModHandler.getEmptyCell(1), 30, 2);
        GT_Mod.addFuel(GT_MetaItem_Cell.instance.getStack(24, 1), GT_ModHandler.getEmptyCell(1), 2, 3);
        GT_Mod.addFuel(GT_ModHandler.getRCItem("liquid.creosote.cell", 1), GT_ModHandler.getEmptyCell(1), 3, 3);
        GT_Mod.addFuel(GT_MetaItem_Cell.instance.getStack(20, 1), GT_ModHandler.getEmptyCell(1), 8, 3);
        GT_Mod.addFuel(GT_MetaItem_Cell.instance.getStack(12, 1), GT_ModHandler.getEmptyCell(1), 30, 3);
        GT_Mod.addFuel(GT_MetaItem_Cell.instance.getStack(5, 1), GT_ModHandler.getEmptyCell(1), 60, 3);
        GT_Mod.addFuel(GT_MetaItem_Cell.instance.getStack(17, 1), GT_ModHandler.getEmptyCell(1), 64, 3);
        GT_Mod.addFuel(GT_MetaItem_Cell.instance.getStack(16, 1), GT_ModHandler.getEmptyCell(1), 8, 5);
        GT_Mod.addFuel(new ItemStack(Item.eyeOfEnder, 1), null, 20, 5);
        GT_Mod.addFuel(new ItemStack(Item.netherStar, 1), null, 100000, 5);
        GT_Mod.addFuel(new ItemStack(Block.beacon, 1), null, 100000, 5);
        if (GT_ModHandler.mTCResource != null) {
            GT_Mod.addFuel(new ItemStack(GT_ModHandler.mTCResource.getItem(), 1, 3), null, 8, 5);
            GT_Mod.addFuel(new ItemStack(GT_ModHandler.mTCResource.getItem(), 1, 4), null, 2, 5);
            GT_Mod.addFuel(new ItemStack(GT_ModHandler.mTCResource.getItem(), 1, 6), null, 3, 5);
        }
        if (GT_ModHandler.mTCCrystal != null) {
            GT_Mod.addFuel(new ItemStack(GT_ModHandler.mTCCrystal.getItem(), 1, 0), new ItemStack(GT_ModHandler.mTCCrystal.getItem(), 1, 5), 160, 5);
            GT_Mod.addFuel(new ItemStack(GT_ModHandler.mTCCrystal.getItem(), 1, 1), new ItemStack(GT_ModHandler.mTCCrystal.getItem(), 1, 5), 320, 5);
            GT_Mod.addFuel(new ItemStack(GT_ModHandler.mTCCrystal.getItem(), 1, 2), new ItemStack(GT_ModHandler.mTCCrystal.getItem(), 1, 5), 160, 5);
            GT_Mod.addFuel(new ItemStack(GT_ModHandler.mTCCrystal.getItem(), 1, 3), new ItemStack(GT_ModHandler.mTCCrystal.getItem(), 1, 5), 160, 5);
            GT_Mod.addFuel(new ItemStack(GT_ModHandler.mTCCrystal.getItem(), 1, 4), new ItemStack(GT_ModHandler.mTCCrystal.getItem(), 1, 5), 240, 5);
        }
        if (GT_ModHandler.mTCFluxEssence != null) {
            GT_Mod.addFuel(new ItemStack(GT_ModHandler.mTCFluxEssence.getItem(), 1, 7), new ItemStack(GT_ModHandler.mTCFluxEssence.getItem(), 1, 0), 80, 5);
            GT_Mod.addFuel(new ItemStack(GT_ModHandler.mTCFluxEssence.getItem(), 1, 10), new ItemStack(GT_ModHandler.mTCFluxEssence.getItem(), 1, 0), 160, 5);
            GT_Mod.addFuel(new ItemStack(GT_ModHandler.mTCFluxEssence.getItem(), 1, 41), new ItemStack(GT_ModHandler.mTCFluxEssence.getItem(), 1, 0), 120, 5);
        }
        GT_ModHandler.addBoilerFuel(new LiquidStack(GT_MetaItem_Gas.instance.getStack((int)0, (int)1).itemID, 1000, GT_MetaItem_Gas.instance.getStack(0, 1).getItemDamage()), 2000);
        GT_ModHandler.addBoilerFuel(new LiquidStack(GT_MetaItem_Gas.instance.getStack((int)9, (int)1).itemID, 1000, GT_MetaItem_Gas.instance.getStack(9, 1).getItemDamage()), 3000);
        GT_ModHandler.addBoilerFuel(new LiquidStack(GT_MetaItem_Liquid.instance.getStack((int)35, (int)1).itemID, 1000, GT_MetaItem_Liquid.instance.getStack(35, 1).getItemDamage()), 18000);
        GT_ModHandler.addBoilerFuel(new LiquidStack(GT_MetaItem_Liquid.instance.getStack((int)5, (int)1).itemID, 1000, GT_MetaItem_Liquid.instance.getStack(5, 1).getItemDamage()), 24000);
        GT_Log.out.println("GT_Mod: Changing maximum Stacksizes if configured.");
        GT_ModHandler.getIC2Item("overclockerUpgrade", 1).getItem().setMaxStackSize(this.mUpgradeCount);
        Item.cake.setMaxStackSize(64);
        Item.itemsList[Block.woodSingleSlab.blockID].setMaxStackSize(GT_Mod.instance.mPlankStackSize);
        Item.itemsList[Block.woodDoubleSlab.blockID].setMaxStackSize(GT_Mod.instance.mPlankStackSize);
        Item.itemsList[Block.stairCompactPlanks.blockID].setMaxStackSize(GT_Mod.instance.mPlankStackSize);
        Item.itemsList[Block.stairsWoodBirch.blockID].setMaxStackSize(GT_Mod.instance.mPlankStackSize);
        Item.itemsList[Block.stairsWoodJungle.blockID].setMaxStackSize(GT_Mod.instance.mPlankStackSize);
        Item.itemsList[Block.stairsWoodSpruce.blockID].setMaxStackSize(GT_Mod.instance.mPlankStackSize);
        Item.itemsList[Block.leaves.blockID].setMaxStackSize(GT_Mod.instance.mWoodStackSize);
        Item.itemsList[Block.stoneSingleSlab.blockID].setMaxStackSize(GT_Mod.instance.mBlockStackSize);
        Item.itemsList[Block.stoneDoubleSlab.blockID].setMaxStackSize(GT_Mod.instance.mBlockStackSize);
        Item.itemsList[Block.stairsBrick.blockID].setMaxStackSize(GT_Mod.instance.mBlockStackSize);
        Item.itemsList[Block.stairsNetherBrick.blockID].setMaxStackSize(GT_Mod.instance.mBlockStackSize);
        Item.itemsList[Block.stairsSandStone.blockID].setMaxStackSize(GT_Mod.instance.mBlockStackSize);
        Item.itemsList[Block.stairsStoneBrickSmooth.blockID].setMaxStackSize(GT_Mod.instance.mBlockStackSize);
        Item.itemsList[Block.stairCompactCobblestone.blockID].setMaxStackSize(GT_Mod.instance.mBlockStackSize);
        Item.itemsList[Block.ice.blockID].setMaxStackSize(GT_Mod.instance.mBlockStackSize);
        Item.itemsList[Block.slowSand.blockID].setMaxStackSize(GT_Mod.instance.mBlockStackSize);
        Item.itemsList[Block.glowStone.blockID].setMaxStackSize(GT_Mod.instance.mBlockStackSize);
        Item.itemsList[Block.snow.blockID].setMaxStackSize(GT_Mod.instance.mBlockStackSize);
        Item.itemsList[Block.blockSnow.blockID].setMaxStackSize(GT_Mod.instance.mBlockStackSize);
        Item.itemsList[Block.blockSteel.blockID].setMaxStackSize(GT_Mod.instance.mBlockStackSize);
        Item.itemsList[Block.blockGold.blockID].setMaxStackSize(GT_Mod.instance.mBlockStackSize);
        Item.itemsList[Block.blockEmerald.blockID].setMaxStackSize(GT_Mod.instance.mBlockStackSize);
        Item.itemsList[Block.blockLapis.blockID].setMaxStackSize(GT_Mod.instance.mBlockStackSize);
        Item.itemsList[Block.blockDiamond.blockID].setMaxStackSize(GT_Mod.instance.mBlockStackSize);
        Item.itemsList[Block.blockClay.blockID].setMaxStackSize(GT_Mod.instance.mBlockStackSize);
        Item.itemsList[Block.redstoneLampIdle.blockID].setMaxStackSize(GT_Mod.instance.mBlockStackSize);
        Item.itemsList[Block.redstoneLampActive.blockID].setMaxStackSize(GT_Mod.instance.mBlockStackSize);
        Item.itemsList[Block.dirt.blockID].setMaxStackSize(GT_Mod.instance.mBlockStackSize);
        Item.itemsList[Block.grass.blockID].setMaxStackSize(GT_Mod.instance.mBlockStackSize);
        Item.itemsList[Block.mycelium.blockID].setMaxStackSize(GT_Mod.instance.mBlockStackSize);
        Item.itemsList[Block.gravel.blockID].setMaxStackSize(GT_Mod.instance.mBlockStackSize);
        Item.itemsList[Block.sand.blockID].setMaxStackSize(GT_Mod.instance.mBlockStackSize);
        Item.itemsList[Block.sandStone.blockID].setMaxStackSize(GT_Mod.instance.mBlockStackSize);
        Item.itemsList[Block.stone.blockID].setMaxStackSize(GT_Mod.instance.mBlockStackSize);
        Item.itemsList[Block.stoneBrick.blockID].setMaxStackSize(GT_Mod.instance.mBlockStackSize);
        Item.itemsList[Block.brick.blockID].setMaxStackSize(GT_Mod.instance.mBlockStackSize);
        Item.itemsList[Block.cobblestone.blockID].setMaxStackSize(GT_Mod.instance.mBlockStackSize);
        Item.itemsList[Block.cobblestoneMossy.blockID].setMaxStackSize(GT_Mod.instance.mBlockStackSize);
        Item.itemsList[Block.netherrack.blockID].setMaxStackSize(GT_Mod.instance.mBlockStackSize);
        Item.itemsList[Block.netherBrick.blockID].setMaxStackSize(GT_Mod.instance.mBlockStackSize);
        Item.itemsList[Block.whiteStone.blockID].setMaxStackSize(GT_Mod.instance.mBlockStackSize);
        Item.itemsList[Block.cloth.blockID].setMaxStackSize(GT_Mod.instance.mBlockStackSize);
        Item.itemsList[Block.melon.blockID].setMaxStackSize(GT_Mod.instance.mBlockStackSize);
        Item.itemsList[Block.pumpkin.blockID].setMaxStackSize(GT_Mod.instance.mBlockStackSize);
        Item.itemsList[Block.pumpkinLantern.blockID].setMaxStackSize(GT_Mod.instance.mBlockStackSize);
        Item.itemsList[Block.dispenser.blockID].setMaxStackSize(GT_Mod.instance.mBlockStackSize);
        Item.itemsList[Block.obsidian.blockID].setMaxStackSize(GT_Mod.instance.mBlockStackSize);
        Item.itemsList[Block.pistonBase.blockID].setMaxStackSize(GT_Mod.instance.mBlockStackSize);
        Item.itemsList[Block.pistonStickyBase.blockID].setMaxStackSize(GT_Mod.instance.mBlockStackSize);
        Item.itemsList[Block.workbench.blockID].setMaxStackSize(GT_Mod.instance.mBlockStackSize);
        Item.itemsList[Block.glass.blockID].setMaxStackSize(GT_Mod.instance.mBlockStackSize);
        Item.itemsList[Block.jukebox.blockID].setMaxStackSize(GT_Mod.instance.mBlockStackSize);
        Item.itemsList[Block.anvil.blockID].setMaxStackSize(GT_Mod.instance.mBlockStackSize);
        Item.itemsList[Block.jukebox.blockID].setMaxStackSize(GT_Mod.instance.mBlockStackSize);
        Item.itemsList[Block.chest.blockID].setMaxStackSize(GT_Mod.instance.mBlockStackSize);
        Item.itemsList[Block.music.blockID].setMaxStackSize(GT_Mod.instance.mBlockStackSize);
        Item.itemsList[Block.mobSpawner.blockID].setMaxStackSize(GT_Mod.instance.mBlockStackSize);
        Item.itemsList[Block.bookShelf.blockID].setMaxStackSize(GT_Mod.instance.mBlockStackSize);
        Item.itemsList[Block.stoneOvenIdle.blockID].setMaxStackSize(GT_Mod.instance.mBlockStackSize);
        Item.itemsList[Block.stoneOvenActive.blockID].setMaxStackSize(GT_Mod.instance.mBlockStackSize);
        GT_Log.out.println("GT_Mod: Adding Dungeon Loot.");
        if (this.mDungeonLoot) {
            DungeonHooks.addDungeonLoot((ItemStack)GT_OreDictUnificator.get("ingotSilver", 1), (int)100, (int)1, (int)4);
            DungeonHooks.addDungeonLoot((ItemStack)GT_OreDictUnificator.get("gemEmerald", 1), (int)20, (int)1, (int)4);
            DungeonHooks.addDungeonLoot((ItemStack)GT_OreDictUnificator.get("gemRuby", 1), (int)20, (int)1, (int)4);
            DungeonHooks.addDungeonLoot((ItemStack)GT_OreDictUnificator.get("gemSapphire", 1), (int)20, (int)1, (int)4);
            DungeonHooks.addDungeonLoot((ItemStack)GT_OreDictUnificator.get("gemGreenSapphire", 1), (int)20, (int)1, (int)4);
        }
        if (this.mDungeonCredits) {
            DungeonHooks.addDungeonLoot((ItemStack)GT_MetaItem_Material.instance.getStack(0, 1), (int)10, (int)4, (int)24);
            DungeonHooks.addDungeonLoot((ItemStack)GT_ModHandler.getIC2Item("coin", 1), (int)5, (int)4, (int)12);
            DungeonHooks.addDungeonLoot((ItemStack)GT_MetaItem_Material.instance.getStack(1, 1), (int)2, (int)1, (int)8);
            DungeonHooks.addDungeonLoot((ItemStack)GT_MetaItem_Material.instance.getStack(2, 1), (int)1, (int)1, (int)4);
            DungeonHooks.addDungeonLoot((ItemStack)GT_MetaItem_Material.instance.getStack(3, 1), (int)1, (int)1, (int)1);
        }
        if (this.mDungeonDisc) {
            DungeonHooks.addDungeonLoot((ItemStack)new ItemStack(Item.recordChirp), (int)5);
            DungeonHooks.addDungeonLoot((ItemStack)new ItemStack(Item.record11), (int)5);
            DungeonHooks.addDungeonLoot((ItemStack)new ItemStack(Item.recordBlocks), (int)5);
            DungeonHooks.addDungeonLoot((ItemStack)new ItemStack(Item.recordStrad), (int)5);
            DungeonHooks.addDungeonLoot((ItemStack)new ItemStack(Item.recordStal), (int)5);
            DungeonHooks.addDungeonLoot((ItemStack)new ItemStack(Item.recordFar), (int)5);
            DungeonHooks.addDungeonLoot((ItemStack)new ItemStack(Item.recordMall), (int)5);
            DungeonHooks.addDungeonLoot((ItemStack)new ItemStack(Item.recordMellohi), (int)5);
            DungeonHooks.addDungeonLoot((ItemStack)new ItemStack(Item.recordWard), (int)5);
            DungeonHooks.addDungeonLoot((ItemStack)new ItemStack(Item.recordWait), (int)5);
        }
        GT_Log.out.println("GT_Mod: Adding non-OreDict Machine Recipes.");
        GT_ModHandler.addCompressionRecipe(GT_ModHandler.getIC2Item("coalChunk", 1), GT_ModHandler.getIC2Item("industrialDiamond", 1));
        GT_ModHandler.addCompressionRecipe(new ItemStack(Block.sand, 4, 0), new ItemStack(Block.sandStone, 1, 0));
        GT_ModHandler.addCompressionRecipe(new ItemStack(Item.wheat, 8, 0), GT_ModHandler.getIC2Item("compressedPlantBall", 1));
        GT_ModHandler.addCompressionRecipe(new ItemStack(Item.reed, 8, 0), GT_ModHandler.getIC2Item("compressedPlantBall", 1));
        GT_ModHandler.addCompressionRecipe(new ItemStack(Block.cactus, 8, 0), GT_ModHandler.getIC2Item("compressedPlantBall", 1));
        GT_ModHandler.addCompressionRecipe(new ItemStack(Item.clay, 4, 0), new ItemStack(Block.blockClay, 1));
        GT_ModHandler.addCompressionRecipe(GT_ModHandler.getIC2Item("iridiumOre", 1), GT_MetaItem_Material.instance.getStack(16, 1));
        GT_ModHandler.addPulverisationRecipe(new ItemStack(Block.blockClay, 1), GT_OreDictUnificator.get("dustClay", 2), null, 0, false);
        GT_ModHandler.addPulverisationRecipe(new ItemStack(Block.blockSnow, 1), new ItemStack(Item.snowball, 4), null, 0, false);
        GT_ModHandler.addPulverisationRecipe(new ItemStack(Item.stick, 1), GT_MetaItem_SmallDust.instance.getStack(15, 2), null, 0, false);
        GT_ModHandler.addPulverisationRecipe(new ItemStack(Item.coal, 1, 1), GT_MetaItem_Dust.instance.getStack(47, 1), null, 0, false);
        GT_ModHandler.addPulverisationRecipe(new ItemStack(Block.melon, 1, 0), new ItemStack(Item.melon, 8, 0), new ItemStack(Item.melonSeeds, 1), 80, false);
        GT_ModHandler.addPulverisationRecipe(new ItemStack(Block.pumpkin, 1, 0), new ItemStack(Item.pumpkinSeeds, 4, 0), null, 0, false);
        GT_ModHandler.addPulverisationRecipe(new ItemStack(Item.melon, 1, 0), new ItemStack(Item.melonSeeds, 1, 0), null, 0, false);
        GT_ModHandler.addPulverisationRecipe(new ItemStack(Item.wheat, 1, 0), GT_OreDictUnificator.get("dustWheat", 1), null, 0, false);
        GT_ModHandler.addPulverisationRecipe(GT_ModHandler.getIC2Item("plantBall", 1), new ItemStack(Block.dirt, 1, 0), null, 0, false);
        GT_ModHandler.addPulverisationRecipe(GT_ModHandler.getIC2Item("denseCopperPlate", 1), GT_OreDictUnificator.get("dustCopper", 8), null, 0, false);
        GT_ModHandler.addPulverisationRecipe(GT_ModHandler.getIC2Item("crop", 1), GT_OreDictUnificator.get("dustWood", 1), null, 0, false);
        GT_ModHandler.addPulverisationRecipe(new ItemStack(Item.enderPearl, 1), GT_MetaItem_Dust.instance.getStack(0, 1), null, 0, true);
        GT_ModHandler.addPulverisationRecipe(new ItemStack(Item.eyeOfEnder, 1), GT_MetaItem_Dust.instance.getStack(1, 2), null, 0, true);
        GT_ModHandler.addPulverisationRecipe(new ItemStack(Item.flint, 1), GT_MetaItem_SmallDust.instance.getStack(7, 2), GT_MetaItem_SmallDust.instance.getStack(7, 1), 10, true);
        GT_ModHandler.addPulverisationRecipe(new ItemStack(Item.coal, 1), GT_OreDictUnificator.get("dustCoal", 1), null, 0, false);
        GT_ModHandler.addSmeltingRecipe(GT_ModHandler.getIC2Item("denseCopperPlate", 1), GT_OreDictUnificator.get("ingotCopper", 8));
        GT_ModHandler.addSmeltingRecipe(GT_ModHandler.getIC2Item("hydratedCoalDust", 1), GT_OreDictUnificator.get("dustCoal", 1));
        GT_ModHandler.addLiquidTransposerEmptyRecipe(GT_ModHandler.getIC2Item("hydratedCoalDust", 1), new LiquidStack(Block.waterStill, 100), GT_OreDictUnificator.get("dustCoal", 1), 125);
        GT_ModHandler.addExtractionRecipe(GT_ModHandler.getIC2Item("airCell", 1), GT_ModHandler.getEmptyCell(1));
        GT_ModHandler.addExtractionRecipe(GT_ModHandler.getIC2Item("filledTinCan", 1, 0), GT_ModHandler.getIC2Item("tinCan", 1));
        GT_ModHandler.addExtractionRecipe(GT_ModHandler.getIC2Item("filledTinCan", 1, 1), GT_ModHandler.getIC2Item("tinCan", 1));
        GT_Mod.addDistillationRecipe(GT_MetaItem_Cell.instance.getStack(17, 16), 17, GT_MetaItem_Cell.instance.getStack(18, 16), GT_MetaItem_Cell.instance.getStack(40, 16), GT_MetaItem_Cell.instance.getStack(34, 1), null, 16000, 128);
        GT_Mod.addDistillationRecipe(GT_MetaItem_Cell.instance.getStack(20, 16), 0, GT_MetaItem_Cell.instance.getStack(19, 8), null, null, GT_ModHandler.getEmptyCell(8), 400, 32);
        GT_Mod.addElectrolyzerRecipe(new ItemStack(Block.waterStill, 6), 5, GT_MetaItem_Cell.instance.getStack(0, 4), GT_ModHandler.getIC2Item("airCell", 1), null, null, 775, 120);
        GT_Mod.addElectrolyzerRecipe(GT_ModHandler.getIC2Item("electrolyzedWaterCell", 6), 0, GT_MetaItem_Cell.instance.getStack(0, 4), GT_ModHandler.getIC2Item("airCell", 1), null, GT_ModHandler.getEmptyCell(1), 100, 30);
        GT_Mod.addElectrolyzerRecipe(GT_ModHandler.getIC2Item("waterCell", 1), 0, GT_ModHandler.getIC2Item("electrolyzedWaterCell", 1), null, null, null, 128, 128);
        GT_Mod.addElectrolyzerRecipe(new ItemStack(Item.bucketWater, 1), 1, GT_ModHandler.getIC2Item("electrolyzedWaterCell", 1), new ItemStack(Item.bucketEmpty, 1), null, null, 128, 128);
        GT_Mod.addElectrolyzerRecipe(new ItemStack(Item.dyePowder, 3, 15), 1, GT_MetaItem_Cell.instance.getStack(11, 1), null, null, null, 24, 106);
        GT_Mod.addVacuumFreezerRecipe(GT_ModHandler.getIC2Item("reactorCoolantSimple", 1, -1), GT_ModHandler.getIC2Item("reactorCoolantSimple", 1), 100);
        GT_Mod.addVacuumFreezerRecipe(GT_ModHandler.getIC2Item("reactorCoolantTriple", 1, -1), GT_ModHandler.getIC2Item("reactorCoolantTriple", 1), 300);
        GT_Mod.addVacuumFreezerRecipe(GT_ModHandler.getIC2Item("reactorCoolantSix", 1, -1), GT_ModHandler.getIC2Item("reactorCoolantSix", 1), 600);
        GT_Mod.addVacuumFreezerRecipe(new ItemStack(this.mItems[34], 1, -1), new ItemStack(this.mItems[34], 1), 700);
        GT_Mod.addVacuumFreezerRecipe(new ItemStack(this.mItems[35], 1, -1), new ItemStack(this.mItems[35], 1), 2000);
        GT_Mod.addVacuumFreezerRecipe(new ItemStack(this.mItems[36], 1, -1), new ItemStack(this.mItems[36], 1), 3900);
        GT_Mod.addVacuumFreezerRecipe(new ItemStack(this.mItems[60], 1, -1), new ItemStack(this.mItems[60], 1), 500);
        GT_Mod.addVacuumFreezerRecipe(new ItemStack(this.mItems[61], 1, -1), new ItemStack(this.mItems[61], 1), 1500);
        GT_Mod.addVacuumFreezerRecipe(new ItemStack(this.mItems[62], 1, -1), new ItemStack(this.mItems[62], 1), 3000);
        GT_Mod.addVacuumFreezerRecipe(GT_MetaItem_Material.instance.getStack(5, 1), GT_OreDictUnificator.get("ingotTungstenSteel", 1), 450);
        GT_Mod.addVacuumFreezerRecipe(GT_ModHandler.getIC2Item("waterCell", 1), GT_MetaItem_Cell.instance.getStack(23, 1), 50);
        GT_Mod.addCentrifugeRecipe(GT_MetaItem_Cell.instance.getStack(23, 1), 0, new ItemStack(Block.ice, 1), null, null, GT_ModHandler.getEmptyCell(1), 40);
        GT_Mod.addCentrifugeRecipe(new ItemStack(Item.magmaCream, 1), 0, new ItemStack(Item.blazePowder, 1), new ItemStack(Item.slimeBall, 1), null, null, 500);
        GT_Mod.addImplosionRecipe(GT_MetaItem_Material.instance.getStack(4, 1), 8, GT_ModHandler.getIC2Item("iridiumPlate", 1), GT_OreDictUnificator.get("dustDarkAsh", 4));
        GT_Mod.addGrinderRecipe(new ItemStack(Block.netherrack, 16), -1, new ItemStack(Item.goldNugget, 1), GT_MetaItem_Dust.instance.getStack(6, 16), null, GT_ModHandler.getEmptyCell(1));
        GT_Mod.addGrinderRecipe(new ItemStack(Block.netherrack, 8), GT_MetaItem_Cell.instance.getStack(16, 1), new ItemStack(Item.goldNugget, 1), GT_MetaItem_Dust.instance.getStack(6, 8), null, GT_ModHandler.getEmptyCell(1));
        GT_Mod.addCannerRecipe(GT_ModHandler.getIC2Item("compressedPlantBall", 1), GT_ModHandler.getEmptyCell(1), GT_ModHandler.getIC2Item("bioCell", 1), null, 100, 1);
        GT_Mod.addCannerRecipe(GT_ModHandler.getIC2Item("hydratedCoalClump", 1), GT_ModHandler.getEmptyCell(1), GT_ModHandler.getIC2Item("hydratedCoalCell", 1), null, 100, 1);
        GT_Mod.addCannerRecipe(new ItemStack(Item.bucketLava), GT_ModHandler.getEmptyCell(1), GT_ModHandler.getIC2Item("lavaCell", 1), new ItemStack(Item.bucketEmpty, 1), 100, 1);
        GT_Mod.addCannerRecipe(new ItemStack(Item.bucketWater), GT_ModHandler.getEmptyCell(1), GT_ModHandler.getIC2Item("waterCell", 1), new ItemStack(Item.bucketEmpty, 1), 100, 1);
        GT_Mod.addCannerRecipe(GT_ModHandler.getIC2Item("biofuelCell", 6), GT_ModHandler.getIC2Item("fuelCan", 1), GT_ModHandler.getFuelCan(5208), GT_ModHandler.getEmptyCell(6), 600, 1);
        GT_Mod.addCannerRecipe(GT_ModHandler.getIC2Item("coalfuelCell", 6), GT_ModHandler.getIC2Item("fuelCan", 1), GT_ModHandler.getFuelCan(15288), GT_ModHandler.getEmptyCell(6), 600, 1);
        GT_Mod.addAssemblerRecipe(GT_MetaItem_Component.instance.getStack(49, 1), GT_MetaItem_Component.instance.getStack(24, 2), GT_ModHandler.getIC2Item("advancedCircuit", 1), 1600, 2);
        GT_Mod.addAssemblerRecipe(GT_MetaItem_Component.instance.getStack(50, 1), GT_MetaItem_Component.instance.getStack(3, 1), GT_MetaItem_Component.instance.getStack(1, 1), 3200, 4);
        GT_Mod.addAssemblerRecipe(GT_MetaItem_Component.instance.getStack(50, 1), GT_ModHandler.getIC2Item("lapotronCrystal", 1, -1), GT_MetaItem_Component.instance.getStack(0, 1), 3200, 4);
        GT_Mod.addAssemblerRecipe(GT_MetaItem_Component.instance.getStack(1, 1), GT_MetaItem_Component.instance.getStack(3, 8), new ItemStack(this.mItems[43], 4, 0), 12800, 16);
        GT_Mod.addAssemblerRecipe(new ItemStack(this.mBlocks[1], 1, 6), GT_MetaItem_Component.instance.getStack(3, 4), new ItemStack(this.mItems[32], 1, 0), 6400, 8);
        GT_Mod.addAssemblerRecipe(GT_ModHandler.getIC2Item("waterMill", 2), null, GT_ModHandler.getIC2Item("generator", 1), 6400, 8);
        GT_Mod.addAssemblerRecipe(GT_ModHandler.getIC2Item("generator", 1), GT_ModHandler.getIC2Item("carbonPlate", 4), GT_ModHandler.getIC2Item("windMill", 1), 6400, 8);
        GT_Mod.addAssemblerRecipe(new ItemStack((Block)Block.stoneSingleSlab, 3, 0), GT_ModHandler.getRCItem("part.rebar", 1), GT_ModHandler.getRCItem("part.tie.stone", 1), 128, 8);
        GT_Mod.addAssemblerRecipe(new ItemStack((Block)Block.stoneSingleSlab, 3, 7), GT_ModHandler.getRCItem("part.rebar", 1), GT_ModHandler.getRCItem("part.tie.stone", 1), 128, 8);
        GT_Mod.addAssemblerRecipe(new ItemStack(this.mItems[58], 1, -1), null, new ItemStack(this.mItems[56], 6, 0), 3200, 4);
        GT_Mod.addAssemblerRecipe(GT_ModHandler.getIC2Item("batPack", 1, -1), null, GT_ModHandler.getIC2Item("reBattery", 6), 1600, 2);
        GT_Mod.addAssemblerRecipe(GT_ModHandler.mBCIronGear, GT_ModHandler.getIC2Item("generator", 1), GT_MetaItem_Component.instance.getStack(25, 1), 3200, 4);
        GT_Mod.addAssemblerRecipe(GT_ModHandler.getRCItem("part.gear.iron", 1), GT_ModHandler.getIC2Item("generator", 1), GT_MetaItem_Component.instance.getStack(25, 1), 3200, 4);
        GT_Mod.addAssemblerRecipe(GT_ModHandler.getRCItem("part.gear.steel", 1), GT_ModHandler.getIC2Item("generator", 1), GT_MetaItem_Component.instance.getStack(25, 1), 3200, 4);
        GT_Mod.addAssemblerRecipe(GT_ModHandler.getTEItem("gearInvar", 1), GT_ModHandler.getIC2Item("generator", 1), GT_MetaItem_Component.instance.getStack(25, 1), 3200, 4);
        GT_Mod.addAssemblerRecipe(GT_ModHandler.getIC2Item("hvTransformer", 1), GT_ModHandler.getIC2Item("transformerUpgrade", 1), GT_MetaItem_Component.instance.getStack(27, 1), 3200, 4);
        GT_Mod.addAssemblerRecipe(GT_ModHandler.getTEItem("energyFrameFull", 1), GT_ModHandler.getIC2Item("energyStorageUpgrade", 1), GT_MetaItem_Component.instance.getStack(28, 4), 6400, 8);
        GT_Mod.addAssemblerRecipe(GT_ModHandler.getRCItem("part.tie.wood", 4), null, GT_ModHandler.getRCItem("part.railbed.wood", 1), 800, 1);
        GT_Mod.addAssemblerRecipe(GT_ModHandler.getRCItem("part.tie.stone", 4), null, GT_ModHandler.getRCItem("part.railbed.stone", 1), 800, 1);
        tStack2 = GT_ModHandler.getRCItem("part.railbed.wood", 1);
        tStack = GT_ModHandler.getRCItem("part.rail.standard", 6);
        GT_Mod.addAssemblerRecipe(tStack2, tStack, GT_ModHandler.getRecipeOutput(new ItemStack[]{tStack, null, tStack, tStack, tStack2, tStack, tStack, null, tStack}), 1600, 1);
        tStack2 = GT_ModHandler.getRCItem("part.railbed.wood", 1);
        tStack = GT_ModHandler.getRCItem("part.rail.advanced", 6);
        GT_Mod.addAssemblerRecipe(tStack2, tStack, GT_ModHandler.getRecipeOutput(new ItemStack[]{tStack, null, tStack, tStack, tStack2, tStack, tStack, null, tStack}), 1600, 1);
        tStack2 = GT_ModHandler.getRCItem("part.railbed.wood", 1);
        tStack = GT_ModHandler.getRCItem("part.rail.reinforced", 6);
        GT_Mod.addAssemblerRecipe(tStack2, tStack, GT_ModHandler.getRecipeOutput(new ItemStack[]{tStack, null, tStack, tStack, tStack2, tStack, tStack, null, tStack}), 1600, 1);
        tStack2 = GT_ModHandler.getRCItem("part.railbed.wood", 1);
        tStack = GT_ModHandler.getRCItem("part.rail.speed", 6);
        GT_Mod.addAssemblerRecipe(tStack2, tStack, GT_ModHandler.getRecipeOutput(new ItemStack[]{tStack, null, tStack, tStack, tStack2, tStack, tStack, null, tStack}), 1600, 1);
        tStack2 = GT_ModHandler.getRCItem("part.railbed.wood", 1);
        tStack = GT_ModHandler.getRCItem("part.rail.wood", 6);
        GT_Mod.addAssemblerRecipe(tStack2, tStack, GT_ModHandler.getRecipeOutput(new ItemStack[]{tStack, null, tStack, tStack, tStack2, tStack, tStack, null, tStack}), 1600, 1);
        tStack2 = GT_ModHandler.getRCItem("part.railbed.stone", 1);
        tStack = GT_ModHandler.getRCItem("part.rail.standard", 6);
        GT_Mod.addAssemblerRecipe(tStack2, tStack, GT_ModHandler.getRecipeOutput(new ItemStack[]{tStack, null, tStack, tStack, tStack2, tStack, tStack, null, tStack}), 1600, 1);
        tStack2 = GT_ModHandler.getRCItem("part.railbed.stone", 1);
        tStack = GT_ModHandler.getRCItem("part.rail.advanced", 6);
        GT_Mod.addAssemblerRecipe(tStack2, tStack, GT_ModHandler.getRecipeOutput(new ItemStack[]{tStack, null, tStack, tStack, tStack2, tStack, tStack, null, tStack}), 1600, 1);
        tStack2 = GT_ModHandler.getRCItem("part.railbed.stone", 1);
        tStack = GT_ModHandler.getRCItem("part.rail.reinforced", 6);
        GT_Mod.addAssemblerRecipe(tStack2, tStack, GT_ModHandler.getRecipeOutput(new ItemStack[]{tStack, null, tStack, tStack, tStack2, tStack, tStack, null, tStack}), 1600, 1);
        tStack2 = GT_ModHandler.getRCItem("part.railbed.stone", 1);
        tStack = GT_ModHandler.getRCItem("part.rail.speed", 6);
        GT_Mod.addAssemblerRecipe(tStack2, tStack, GT_ModHandler.getRecipeOutput(new ItemStack[]{tStack, null, tStack, tStack, tStack2, tStack, tStack, null, tStack}), 1600, 1);
        tStack2 = GT_ModHandler.getRCItem("part.railbed.stone", 1);
        tStack = GT_ModHandler.getRCItem("part.rail.wood", 6);
        GT_Mod.addAssemblerRecipe(tStack2, tStack, GT_ModHandler.getRecipeOutput(new ItemStack[]{tStack, null, tStack, tStack, tStack2, tStack, tStack, null, tStack}), 1600, 1);
        GT_ModHandler.removeRecipe(new ItemStack[]{GT_ModHandler.getIC2Item("uraniumIngot", 1), GT_ModHandler.getEmptyCell(1)});
        GT_ModHandler.removeRecipe(new ItemStack[]{new ItemStack(Item.bucketLava), GT_ModHandler.getEmptyCell(1)});
        GT_ModHandler.removeRecipe(new ItemStack[]{new ItemStack(Item.bucketWater), GT_ModHandler.getEmptyCell(1)});
        GT_ModHandler.removeRecipe(new ItemStack[]{GT_ModHandler.getIC2Item("compressedPlantBall", 1), GT_ModHandler.getEmptyCell(1)});
        GT_ModHandler.removeRecipe(new ItemStack[]{GT_ModHandler.getIC2Item("hydratedCoalClump", 1), GT_ModHandler.getEmptyCell(1)});
        GT_Mod.addCentrifugeRecipe(GT_ModHandler.getIC2Item("nearDepletedUraniumCell", 1), 0, GT_OreDictUnificator.get("dustThorium", 1), null, null, GT_ModHandler.getEmptyCell(1), 500);
        GT_Mod.addCentrifugeRecipe(GT_ModHandler.getIC2Item("reEnrichedUraniumCell", 8), 0, GT_ModHandler.getIC2Item("nearDepletedUraniumCell", 3), GT_OreDictUnificator.get("dustPlutonium", 1), GT_OreDictUnificator.get("dustThorium", 4), GT_ModHandler.getEmptyCell(5), 20000);
        GT_Mod.addCentrifugeRecipe(new ItemStack(Block.dirt, 16), 0, GT_ModHandler.getIC2Item("compressedPlantBall", 1), GT_ModHandler.getIC2Item("plantBall", 1), new ItemStack(Item.clay, 1), new ItemStack(Block.sand, 8), 2500);
        GT_Mod.addCentrifugeRecipe(new ItemStack((Block)Block.grass, 16), 0, GT_ModHandler.getIC2Item("compressedPlantBall", 2), GT_ModHandler.getIC2Item("plantBall", 2), new ItemStack(Item.clay, 1), new ItemStack(Block.sand, 8), 2500);
        GT_Mod.addCentrifugeRecipe(new ItemStack((Block)Block.mycelium, 8), 0, new ItemStack((Block)Block.mushroomBrown, 2), new ItemStack((Block)Block.mushroomRed, 2), new ItemStack(Item.clay, 1), new ItemStack(Block.sand, 4), 1650);
        GT_Mod.addCentrifugeRecipe(new ItemStack(Item.appleGold, 1, 1), 2, GT_MetaItem_Cell.instance.getStack(9, 2), new ItemStack(Item.ingotGold, 64), null, null, 10000);
        GT_Mod.addCentrifugeRecipe(new ItemStack(Item.appleGold, 1, 0), 1, GT_MetaItem_Cell.instance.getStack(9, 1), new ItemStack(Item.goldNugget, 6), null, null, 10000);
        GT_Mod.addCentrifugeRecipe(new ItemStack(Item.goldenCarrot, 1, 0), 1, GT_MetaItem_Cell.instance.getStack(9, 1), new ItemStack(Item.goldNugget, 6), null, null, 10000);
        GT_Mod.addCentrifugeRecipe(new ItemStack(Item.speckledMelon, 8, 0), 1, GT_MetaItem_Cell.instance.getStack(9, 1), new ItemStack(Item.goldNugget, 6), null, null, 10000);
        GT_Mod.addCentrifugeRecipe(new ItemStack(Item.appleRed, 32, 0), 1, GT_MetaItem_Cell.instance.getStack(9, 1), null, null, null, 5000);
        GT_Mod.addCentrifugeRecipe(new ItemStack(Item.bowlSoup, 16, 0), 1, GT_MetaItem_Cell.instance.getStack(9, 1), null, null, null, 5000);
        GT_Mod.addCentrifugeRecipe(new ItemStack(Item.bread, 64, 0), 1, GT_MetaItem_Cell.instance.getStack(9, 1), null, null, null, 5000);
        GT_Mod.addCentrifugeRecipe(new ItemStack(Item.porkRaw, 12, 0), 1, GT_MetaItem_Cell.instance.getStack(9, 1), null, null, null, 5000);
        GT_Mod.addCentrifugeRecipe(new ItemStack(Item.porkCooked, 16, 0), 1, GT_MetaItem_Cell.instance.getStack(9, 1), null, null, null, 5000);
        GT_Mod.addCentrifugeRecipe(new ItemStack(Item.beefRaw, 12, 0), 1, GT_MetaItem_Cell.instance.getStack(9, 1), null, null, null, 5000);
        GT_Mod.addCentrifugeRecipe(new ItemStack(Item.beefCooked, 16, 0), 1, GT_MetaItem_Cell.instance.getStack(9, 1), null, null, null, 5000);
        GT_Mod.addCentrifugeRecipe(new ItemStack(Item.fishRaw, 12, 0), 1, GT_MetaItem_Cell.instance.getStack(9, 1), null, null, null, 5000);
        GT_Mod.addCentrifugeRecipe(new ItemStack(Item.fishCooked, 16, 0), 1, GT_MetaItem_Cell.instance.getStack(9, 1), null, null, null, 5000);
        GT_Mod.addCentrifugeRecipe(new ItemStack(Item.chickenRaw, 12, 0), 1, GT_MetaItem_Cell.instance.getStack(9, 1), null, null, null, 5000);
        GT_Mod.addCentrifugeRecipe(new ItemStack(Item.chickenCooked, 16, 0), 1, GT_MetaItem_Cell.instance.getStack(9, 1), null, null, null, 5000);
        GT_Mod.addCentrifugeRecipe(new ItemStack(Item.melon, 64, 0), 1, GT_MetaItem_Cell.instance.getStack(9, 1), null, null, null, 5000);
        GT_Mod.addCentrifugeRecipe(new ItemStack(Block.pumpkin, 16, 0), 1, GT_MetaItem_Cell.instance.getStack(9, 1), null, null, null, 5000);
        GT_Mod.addCentrifugeRecipe(new ItemStack(Item.rottenFlesh, 16, 0), 1, GT_MetaItem_Cell.instance.getStack(9, 1), null, null, null, 5000);
        GT_Mod.addCentrifugeRecipe(new ItemStack(Item.spiderEye, 32, 0), 1, GT_MetaItem_Cell.instance.getStack(9, 1), null, null, null, 5000);
        GT_Mod.addCentrifugeRecipe(new ItemStack(Item.carrot, 16, 0), 1, GT_MetaItem_Cell.instance.getStack(9, 1), null, null, null, 5000);
        GT_Mod.addCentrifugeRecipe(new ItemStack(Item.potato, 16, 0), 1, GT_MetaItem_Cell.instance.getStack(9, 1), null, null, null, 5000);
        GT_Mod.addCentrifugeRecipe(new ItemStack(Item.poisonousPotato, 12, 0), 1, GT_MetaItem_Cell.instance.getStack(9, 1), null, null, null, 5000);
        GT_Mod.addCentrifugeRecipe(new ItemStack(Item.bakedPotato, 24, 0), 1, GT_MetaItem_Cell.instance.getStack(9, 1), null, null, null, 5000);
        GT_Mod.addCentrifugeRecipe(new ItemStack(Item.cookie, 64, 0), 1, GT_MetaItem_Cell.instance.getStack(9, 1), null, null, null, 5000);
        GT_Mod.addCentrifugeRecipe(new ItemStack(Item.cake, 8, 0), 1, GT_MetaItem_Cell.instance.getStack(9, 1), null, null, null, 5000);
        GT_Mod.addCentrifugeRecipe(new ItemStack(Block.mushroomCapBrown, 12, -1), 1, GT_MetaItem_Cell.instance.getStack(9, 1), null, null, null, 5000);
        GT_Mod.addCentrifugeRecipe(new ItemStack(Block.mushroomCapRed, 12, -1), 1, GT_MetaItem_Cell.instance.getStack(9, 1), null, null, null, 5000);
        GT_Mod.addCentrifugeRecipe(new ItemStack((Block)Block.mushroomBrown, 32), 1, GT_MetaItem_Cell.instance.getStack(9, 1), null, null, null, 5000);
        GT_Mod.addCentrifugeRecipe(new ItemStack((Block)Block.mushroomRed, 32), 1, GT_MetaItem_Cell.instance.getStack(9, 1), null, null, null, 5000);
        GT_Mod.addCentrifugeRecipe(new ItemStack(Item.netherStalkSeeds, 32), 1, GT_MetaItem_Cell.instance.getStack(9, 1), null, null, null, 5000);
        GT_Mod.addCentrifugeRecipe(GT_ModHandler.getIC2Item("terraWart", 16), 1, GT_MetaItem_Cell.instance.getStack(9, 1), null, null, null, 5000);
        GT_Mod.addElectrolyzerRecipe(new ItemStack(Item.sugar, 32), 7, GT_MetaItem_Cell.instance.getStack(8, 2), GT_ModHandler.getIC2Item("waterCell", 5), null, null, 210, 32);
        GT_Mod.addElectrolyzerRecipe(new ItemStack(Item.blazePowder, 4), 0, GT_OreDictUnificator.get("dustDarkAsh", 1), GT_OreDictUnificator.get("dustSulfur", 1), null, null, 300, 25);
        GT_Mod.addElectrolyzerRecipe(new ItemStack(Block.sand, 16), 2, GT_MetaItem_Cell.instance.getStack(7, 1), GT_ModHandler.getIC2Item("airCell", 1), null, null, 1000, 25);
        GT_Mod.addCentrifugeRecipe(GT_ModHandler.getIC2Item("resin", 4), 0, GT_OreDictUnificator.get("itemRubber", 14), GT_ModHandler.getIC2Item("compressedPlantBall", 1), GT_ModHandler.getIC2Item("plantBall", 1), null, 1300);
        GT_Mod.addCentrifugeRecipe(new ItemStack(Block.slowSand, 16), 1, GT_MetaItem_Cell.instance.getStack(17, 1), GT_MetaItem_Dust.instance.getStack(9, 4), GT_OreDictUnificator.get("dustCoal", 1), new ItemStack(Block.sand, 10), 2500);
        GT_Mod.addCentrifugeRecipe(GT_ModHandler.getIC2Item("lavaCell", 16), 0, GT_OreDictUnificator.get("ingotElectrum", 1), GT_OreDictUnificator.get("ingotCopper", 4), GT_OreDictUnificator.get("dustSmallTungsten", 1), GT_OreDictUnificator.get("ingotTin", Math.min(64, 2 + 64 / this.mTincellCount)), 15000);
        GT_Mod.addCentrifugeRecipe(new ItemStack(Block.lavaStill, 16), 0, GT_OreDictUnificator.get("ingotElectrum", 1), GT_OreDictUnificator.get("ingotCopper", 4), GT_OreDictUnificator.get("dustSmallTungsten", 1), GT_OreDictUnificator.get("ingotTin", 2), 10000);
        if (!mConfig.addAdvConfig("StorageBlockCrafting", "blockGlowstone", false)) {
            tStack = new ItemStack(Item.lightStoneDust, 1);
            GT_ModHandler.removeRecipe(new ItemStack[]{tStack, tStack, null, tStack, tStack, null, null, null, null});
        }
        GT_ModHandler.addCompressionRecipe(new ItemStack(Item.lightStoneDust, 4, 0), new ItemStack(Block.glowStone, 1));
        GT_ModHandler.addPulverisationRecipe(GT_ModHandler.getIC2Item("machine", 1), GT_OreDictUnificator.get("dustRefinedIron", GT_OreDictUnificator.get("dustIron", 8), 8), null, 0, false);
        GT_ModHandler.addSmeltingRecipe(GT_ModHandler.getIC2Item("machine", 1), GT_OreDictUnificator.get("ingotRefinedIron", 8));
        GT_ModHandler.addPulverisationRecipe(new ItemStack(Block.obsidian, 1), GT_MetaItem_Dust.instance.getStack(46, 1), null, 0, false);
        GT_ModHandler.addExtractionRecipe(new ItemStack(Item.slimeBall, 1), GT_OreDictUnificator.get("itemRubber", 2));
        GT_ModHandler.addSmeltingRecipe(new ItemStack(Item.slimeBall, 1), GT_ModHandler.getIC2Item("resin", 1));
        GT_ModHandler.addExtractionRecipe(GT_ModHandler.getIC2Item("resin", 1), GT_OreDictUnificator.get("itemRubber", 3));
        GT_Log.out.println("GT_Mod: Adding Stuff to the Recycler Blacklist.");
        if (this.mNoMobRecycling) {
            GT_ModHandler.addToRecyclerBlackList(new ItemStack(Item.arrow, 1, 0));
            GT_ModHandler.addToRecyclerBlackList(new ItemStack(Item.bone, 1, 0));
            GT_ModHandler.addToRecyclerBlackList(new ItemStack(Item.dyePowder, 1, 15));
            GT_ModHandler.addToRecyclerBlackList(new ItemStack(Item.rottenFlesh, 1, 0));
            GT_ModHandler.addToRecyclerBlackList(new ItemStack(Item.silk, 1, 0));
            GT_ModHandler.addToRecyclerBlackList(new ItemStack(Item.egg, 1, 0));
        }
        if (this.mNoStoneRecycling) {
            GT_ModHandler.addToRecyclerBlackList(new ItemStack(Block.sand, 1, 0));
            GT_ModHandler.addToRecyclerBlackList(new ItemStack(Block.sandStone, 1, 0));
            GT_ModHandler.addToRecyclerBlackList(new ItemStack(Block.glass, 1, 0));
            GT_ModHandler.addToRecyclerBlackList(new ItemStack(Item.glassBottle, 1, 0));
            GT_ModHandler.addToRecyclerBlackList(new ItemStack((Item)Item.potion, 1, 0));
            GT_ModHandler.addToRecyclerBlackList(GT_ModHandler.getSmeltingOutput(new ItemStack(Block.stone, 1, 0)));
            GT_ModHandler.addToRecyclerBlackList(GT_ModHandler.getRecipeOutput(new ItemStack[]{new ItemStack(Block.stone, 1, 0), null, new ItemStack(Block.stone, 1, 0), null, new ItemStack(Block.stone, 1, 0)}));
            GT_ModHandler.addToRecyclerBlackList(GT_ModHandler.getRecipeOutput(new ItemStack[]{new ItemStack(Block.stone, 1, 0), new ItemStack(Block.glass, 1, 0), new ItemStack(Block.stone, 1, 0)}));
            GT_ModHandler.addToRecyclerBlackList(GT_ModHandler.getRecipeOutput(new ItemStack[]{new ItemStack(Block.cobblestone, 1, 0), new ItemStack(Block.glass, 1, 0), new ItemStack(Block.cobblestone, 1, 0)}));
            GT_ModHandler.addToRecyclerBlackList(GT_ModHandler.getRecipeOutput(new ItemStack[]{new ItemStack(Block.sandStone, 1, 0), new ItemStack(Block.glass, 1, 0), new ItemStack(Block.sandStone, 1, 0)}));
            GT_ModHandler.addToRecyclerBlackList(GT_ModHandler.getRecipeOutput(new ItemStack[]{new ItemStack(Block.sand, 1, 0), new ItemStack(Block.glass, 1, 0), new ItemStack(Block.sand, 1, 0)}));
            GT_ModHandler.addToRecyclerBlackList(GT_ModHandler.getRecipeOutput(new ItemStack[]{new ItemStack(Block.sandStone, 1, 0), new ItemStack(Block.sandStone, 1, 0), new ItemStack(Block.sandStone, 1, 0), new ItemStack(Block.sandStone, 1, 0), new ItemStack(Block.sandStone, 1, 0), new ItemStack(Block.sandStone, 1, 0)}));
            GT_ModHandler.addToRecyclerBlackList(GT_ModHandler.getRecipeOutput(new ItemStack[]{new ItemStack(Block.glass, 1, 0)}));
            GT_ModHandler.addToRecyclerBlackList(GT_ModHandler.getRecipeOutput(new ItemStack[]{new ItemStack(Block.glass, 1, 0), new ItemStack(Block.glass, 1, 0)}));
            GT_ModHandler.addToRecyclerBlackList(GT_ModHandler.getRecipeOutput(new ItemStack[]{new ItemStack(Block.glass, 1, 0), null, null, new ItemStack(Block.glass, 1, 0)}));
            GT_ModHandler.addToRecyclerBlackList(new ItemStack(Block.thinGlass, 1, 0));
            GT_ModHandler.addToRecyclerBlackList(new ItemStack(Block.cobblestone, 1, 0));
            GT_ModHandler.addToRecyclerBlackList(new ItemStack(Block.cobblestoneWall, 1, 0));
            GT_ModHandler.addToRecyclerBlackList(new ItemStack(Block.stairsSandStone, 1, 0));
            GT_ModHandler.addToRecyclerBlackList(new ItemStack(Block.stairCompactCobblestone, 1, 0));
            GT_ModHandler.addToRecyclerBlackList(new ItemStack(Block.stairsStoneBrickSmooth, 1, 0));
            GT_ModHandler.addToRecyclerBlackList(new ItemStack(Block.stoneOvenActive, 1, 0));
            GT_ModHandler.addToRecyclerBlackList(new ItemStack(Block.stoneOvenIdle, 1, 0));
            GT_ModHandler.addToRecyclerBlackList(new ItemStack((Block)Block.stoneSingleSlab, 1, 0));
            GT_ModHandler.addToRecyclerBlackList(new ItemStack((Block)Block.stoneDoubleSlab, 1, 0));
            GT_ModHandler.addToRecyclerBlackList(new ItemStack((Block)Block.stoneSingleSlab, 1, 1));
            GT_ModHandler.addToRecyclerBlackList(new ItemStack((Block)Block.stoneDoubleSlab, 1, 1));
            GT_ModHandler.addToRecyclerBlackList(new ItemStack((Block)Block.stoneSingleSlab, 1, 3));
            GT_ModHandler.addToRecyclerBlackList(new ItemStack((Block)Block.stoneDoubleSlab, 1, 3));
            GT_ModHandler.addToRecyclerBlackList(new ItemStack((Block)Block.stoneSingleSlab, 1, 5));
            GT_ModHandler.addToRecyclerBlackList(new ItemStack((Block)Block.stoneDoubleSlab, 1, 5));
            GT_ModHandler.addToRecyclerBlackList(new ItemStack((Block)Block.stoneSingleSlab, 1, 7));
            GT_ModHandler.addToRecyclerBlackList(new ItemStack((Block)Block.stoneDoubleSlab, 1, 7));
            GT_ModHandler.addToRecyclerBlackList(new ItemStack(Block.pressurePlateStone, 1, 0));
            GT_ModHandler.addToRecyclerBlackList(new ItemStack(Block.stoneButton, 1, 0));
            GT_ModHandler.addToRecyclerBlackList(new ItemStack(Block.stoneBrick, 1, 0));
            GT_ModHandler.addToRecyclerBlackList(new ItemStack(Block.stone, 1, 0));
            GT_ModHandler.addToRecyclerBlackList(new ItemStack(Block.lever, 1, 0));
        }
        GT_ModHandler.addToRecyclerBlackList(new ItemStack(Item.snowball, 1));
        GT_ModHandler.addToRecyclerBlackList(new ItemStack(Block.ice, 1));
        GT_ModHandler.addToRecyclerBlackList(new ItemStack(Block.snow, 1));
        GT_ModHandler.addToRecyclerBlackList(new ItemStack(Block.blockSnow, 1));
        GT_Log.out.println("GT_Mod: (re-)adding Scrapbox Drops.");
        GT_ModHandler.addScrapboxDrop(9.5f, new ItemStack(Item.hoeWood));
        GT_ModHandler.addScrapboxDrop(2.0f, new ItemStack(Item.axeWood));
        GT_ModHandler.addScrapboxDrop(2.0f, new ItemStack(Item.swordWood));
        GT_ModHandler.addScrapboxDrop(2.0f, new ItemStack(Item.shovelWood));
        GT_ModHandler.addScrapboxDrop(2.0f, new ItemStack(Item.pickaxeWood));
        GT_ModHandler.addScrapboxDrop(2.0f, new ItemStack(Item.sign));
        GT_ModHandler.addScrapboxDrop(9.5f, new ItemStack(Item.stick));
        GT_ModHandler.addScrapboxDrop(5.0f, new ItemStack(Block.dirt));
        GT_ModHandler.addScrapboxDrop(3.0f, new ItemStack((Block)Block.grass));
        GT_ModHandler.addScrapboxDrop(3.0f, new ItemStack(Block.gravel));
        GT_ModHandler.addScrapboxDrop(0.5f, new ItemStack(Block.pumpkin));
        GT_ModHandler.addScrapboxDrop(1.0f, new ItemStack(Block.slowSand));
        GT_ModHandler.addScrapboxDrop(2.0f, new ItemStack(Block.netherrack));
        GT_ModHandler.addScrapboxDrop(1.0f, new ItemStack(Item.bone));
        GT_ModHandler.addScrapboxDrop(9.0f, new ItemStack(Item.rottenFlesh));
        GT_ModHandler.addScrapboxDrop(0.4f, new ItemStack(Item.porkCooked));
        GT_ModHandler.addScrapboxDrop(0.4f, new ItemStack(Item.beefCooked));
        GT_ModHandler.addScrapboxDrop(0.4f, new ItemStack(Item.chickenCooked));
        GT_ModHandler.addScrapboxDrop(0.5f, new ItemStack(Item.appleRed));
        GT_ModHandler.addScrapboxDrop(0.5f, new ItemStack(Item.bread));
        GT_ModHandler.addScrapboxDrop(0.1f, new ItemStack(Item.cake));
        GT_ModHandler.addScrapboxDrop(1.0f, GT_ModHandler.getIC2Item("filledTinCan", 1, 0));
        GT_ModHandler.addScrapboxDrop(2.0f, GT_ModHandler.getIC2Item("filledTinCan", 1, 1));
        GT_ModHandler.addScrapboxDrop(0.2f, GT_MetaItem_Cell.instance.getStack(7, 1));
        GT_ModHandler.addScrapboxDrop(1.0f, GT_ModHandler.getIC2Item("waterCell", 1));
        GT_ModHandler.addScrapboxDrop(2.0f, GT_ModHandler.getEmptyCell(1));
        GT_ModHandler.addScrapboxDrop(5.0f, new ItemStack(Item.paper));
        GT_ModHandler.addScrapboxDrop(1.0f, new ItemStack(Item.leather));
        GT_ModHandler.addScrapboxDrop(1.0f, new ItemStack(Item.feather));
        GT_ModHandler.addScrapboxDrop(0.7f, GT_ModHandler.getIC2Item("plantBall", 1));
        GT_ModHandler.addScrapboxDrop(3.8f, GT_OreDictUnificator.get("dustWood", 1));
        GT_ModHandler.addScrapboxDrop(0.6f, new ItemStack(Item.slimeBall));
        GT_ModHandler.addScrapboxDrop(0.8f, GT_OreDictUnificator.get("itemRubber", 1));
        GT_ModHandler.addScrapboxDrop(2.7f, GT_ModHandler.getIC2Item("suBattery", 1));
        GT_ModHandler.addScrapboxDrop(0.8f, GT_MetaItem_Component.instance.getStack(22, 1));
        GT_ModHandler.addScrapboxDrop(1.2f, GT_MetaItem_Component.instance.getStack(24, 1));
        GT_ModHandler.addScrapboxDrop(1.8f, GT_MetaItem_Component.instance.getStack(48, 1));
        GT_ModHandler.addScrapboxDrop(0.4f, GT_MetaItem_Component.instance.getStack(49, 1));
        GT_ModHandler.addScrapboxDrop(0.2f, GT_MetaItem_Component.instance.getStack(50, 1));
        GT_ModHandler.addScrapboxDrop(2.0f, GT_ModHandler.getIC2Item("insulatedCopperCableItem", 1));
        GT_ModHandler.addScrapboxDrop(0.4f, GT_ModHandler.getIC2Item("doubleInsulatedGoldCableItem", 1));
        GT_ModHandler.addScrapboxDrop(0.9f, new ItemStack(Item.redstone));
        GT_ModHandler.addScrapboxDrop(0.8f, new ItemStack(Item.lightStoneDust));
        GT_ModHandler.addScrapboxDrop(0.8f, GT_OreDictUnificator.get("dustCoal", 1));
        GT_ModHandler.addScrapboxDrop(2.5f, GT_OreDictUnificator.get("dustCharcoal", 1));
        GT_ModHandler.addScrapboxDrop(1.0f, GT_OreDictUnificator.get("dustIron", 1));
        GT_ModHandler.addScrapboxDrop(1.0f, GT_OreDictUnificator.get("dustGold", 1));
        GT_ModHandler.addScrapboxDrop(0.5f, GT_OreDictUnificator.get("dustSilver", 1));
        GT_ModHandler.addScrapboxDrop(0.5f, GT_OreDictUnificator.get("dustElectrum", 1));
        GT_ModHandler.addScrapboxDrop(1.2f, GT_OreDictUnificator.get("dustTin", 1));
        GT_ModHandler.addScrapboxDrop(1.2f, GT_OreDictUnificator.get("dustCopper", 1));
        GT_ModHandler.addScrapboxDrop(0.5f, GT_OreDictUnificator.get("dustBauxite", 1));
        GT_ModHandler.addScrapboxDrop(0.5f, GT_OreDictUnificator.get("dustAluminium", 1));
        GT_ModHandler.addScrapboxDrop(0.5f, GT_OreDictUnificator.get("dustLead", 1));
        GT_ModHandler.addScrapboxDrop(0.5f, GT_OreDictUnificator.get("dustNickel", 1));
        GT_ModHandler.addScrapboxDrop(0.5f, GT_OreDictUnificator.get("dustZinc", 1));
        GT_ModHandler.addScrapboxDrop(0.5f, GT_OreDictUnificator.get("dustBrass", 1));
        GT_ModHandler.addScrapboxDrop(0.5f, GT_OreDictUnificator.get("dustSteel", 1));
        GT_ModHandler.addScrapboxDrop(1.5f, GT_OreDictUnificator.get("dustObsidian", 1));
        GT_ModHandler.addScrapboxDrop(1.5f, GT_OreDictUnificator.get("dustSulfur", 1));
        GT_ModHandler.addScrapboxDrop(2.0f, GT_OreDictUnificator.get("dustSaltpeter", 1));
        GT_ModHandler.addScrapboxDrop(2.0f, GT_MetaItem_Dust.instance.getStack(2, 1));
        GT_ModHandler.addScrapboxDrop(2.0f, GT_MetaItem_Dust.instance.getStack(3, 1));
        GT_ModHandler.addScrapboxDrop(2.0f, GT_MetaItem_Dust.instance.getStack(4, 1));
        GT_ModHandler.addScrapboxDrop(2.0f, GT_MetaItem_Dust.instance.getStack(5, 1));
        GT_ModHandler.addScrapboxDrop(4.0f, GT_MetaItem_Dust.instance.getStack(6, 1));
        GT_ModHandler.addScrapboxDrop(4.0f, GT_MetaItem_Dust.instance.getStack(7, 1));
        GT_ModHandler.addScrapboxDrop(0.03f, GT_MetaItem_Dust.instance.getStack(27, 1));
        GT_ModHandler.addScrapboxDrop(0.03f, GT_MetaItem_Dust.instance.getStack(22, 1));
        GT_ModHandler.addScrapboxDrop(0.03f, GT_MetaItem_Dust.instance.getStack(20, 1));
        GT_ModHandler.addScrapboxDrop(0.03f, GT_MetaItem_Dust.instance.getStack(19, 1));
        GT_ModHandler.addScrapboxDrop(0.03f, GT_MetaItem_Dust.instance.getStack(13, 1));
        GT_ModHandler.addScrapboxDrop(0.03f, GT_MetaItem_Dust.instance.getStack(10, 1));
        GT_ModHandler.addScrapboxDrop(0.5f, GT_MetaItem_Material.instance.getStack(54, 1));
        GT_ModHandler.addScrapboxDrop(0.5f, GT_MetaItem_Material.instance.getStack(55, 1));
        GT_ModHandler.addScrapboxDrop(0.05f, GT_OreDictUnificator.get("gemOlivine", 1));
        GT_ModHandler.addScrapboxDrop(0.05f, GT_OreDictUnificator.get("gemRuby", 1));
        GT_ModHandler.addScrapboxDrop(0.05f, GT_OreDictUnificator.get("gemSapphire", 1));
        GT_ModHandler.addScrapboxDrop(0.05f, GT_OreDictUnificator.get("gemGreenSapphire", 1));
        GT_ModHandler.addScrapboxDrop(0.05f, GT_OreDictUnificator.get("gemEmerald", 1));
        GT_ModHandler.addScrapboxDrop(0.05f, GT_OreDictUnificator.get("gemDiamond", 1));
        GT_Log.out.println("GT_Mod: Adding Slab Recipes.");
        GameRegistry.addRecipe((IRecipe)new ShapedOreRecipe(new ItemStack((Block)Block.stoneDoubleSlab, 1, 7), new Object[]{"BB", Character.valueOf('B'), new ItemStack((Block)Block.stoneSingleSlab, 1, 0)}));
        GameRegistry.addRecipe((IRecipe)new ShapedOreRecipe(new ItemStack((Block)Block.stoneDoubleSlab, 1, 0), new Object[]{"BB", Character.valueOf('B'), new ItemStack((Block)Block.stoneSingleSlab, 1, 7)}));
        GameRegistry.addRecipe((IRecipe)new ShapedOreRecipe(new ItemStack((Block)Block.stoneDoubleSlab, 1, 0), new Object[]{"B", "B", Character.valueOf('B'), new ItemStack((Block)Block.stoneSingleSlab, 1, 0)}));
        GameRegistry.addRecipe((IRecipe)new ShapedOreRecipe(new ItemStack(Block.cobblestone, 1, 0), new Object[]{"B", "B", Character.valueOf('B'), new ItemStack((Block)Block.stoneSingleSlab, 1, 3)}));
        GameRegistry.addRecipe((IRecipe)new ShapedOreRecipe(new ItemStack(Block.brick, 1, 0), new Object[]{"B", "B", Character.valueOf('B'), new ItemStack((Block)Block.stoneSingleSlab, 1, 4)}));
        GameRegistry.addRecipe((IRecipe)new ShapedOreRecipe(new ItemStack(Block.stoneBrick, 1, 0), new Object[]{"B", "B", Character.valueOf('B'), new ItemStack((Block)Block.stoneSingleSlab, 1, 5)}));
        GameRegistry.addRecipe((IRecipe)new ShapedOreRecipe(new ItemStack(Block.netherBrick, 1, 0), new Object[]{"B", "B", Character.valueOf('B'), new ItemStack((Block)Block.stoneSingleSlab, 1, 6)}));
        GameRegistry.addRecipe((IRecipe)new ShapedOreRecipe(new ItemStack((Block)Block.stoneDoubleSlab, 1, 7), new Object[]{"B", "B", Character.valueOf('B'), new ItemStack((Block)Block.stoneSingleSlab, 1, 7)}));
        GameRegistry.addRecipe((IRecipe)new ShapedOreRecipe(new ItemStack(Block.planks, 1, 0), new Object[]{"B", "B", Character.valueOf('B'), new ItemStack((Block)Block.woodSingleSlab, 1, 0)}));
        GameRegistry.addRecipe((IRecipe)new ShapedOreRecipe(new ItemStack(Block.planks, 1, 1), new Object[]{"B", "B", Character.valueOf('B'), new ItemStack((Block)Block.woodSingleSlab, 1, 1)}));
        GameRegistry.addRecipe((IRecipe)new ShapedOreRecipe(new ItemStack(Block.planks, 1, 2), new Object[]{"B", "B", Character.valueOf('B'), new ItemStack((Block)Block.woodSingleSlab, 1, 2)}));
        GameRegistry.addRecipe((IRecipe)new ShapedOreRecipe(new ItemStack(Block.planks, 1, 3), new Object[]{"B", "B", Character.valueOf('B'), new ItemStack((Block)Block.woodSingleSlab, 1, 3)}));
        GameRegistry.addRecipe((IRecipe)new ShapelessOreRecipe(new ItemStack(Item.stick, 2, 0), new Object[]{new ItemStack((Block)Block.deadBush, 1, -1)}));
        GameRegistry.addRecipe((IRecipe)new ShapelessOreRecipe(new ItemStack(Item.stick, 2, 0), new Object[]{new ItemStack((Block)Block.tallGrass, 1, 0)}));
        GT_Log.out.println("GT_Mod: Adding Wool and Color releated Recipes.");
        for (i = 0; i < 16; ++i) {
            GT_ModHandler.addExtractionRecipe(new ItemStack(Block.cloth, 1, i), new ItemStack(Block.cloth, 1, 0));
            GT_ModHandler.addPulverisationRecipe(new ItemStack(Block.cloth, 1, i), new ItemStack(Item.silk, 2), new ItemStack(Item.silk, 1), 50, false);
        }
        GT_ModHandler.addShapelessCraftingRecipe(new ItemStack(Block.cloth, 1, 0), new ItemStack(Block.cloth, 1, -1), "dyeWhite");
        GT_ModHandler.addShapelessCraftingRecipe(new ItemStack(Block.cloth, 1, 1), new ItemStack(Block.cloth, 1, 0), "dyeOrange");
        GT_ModHandler.addShapelessCraftingRecipe(new ItemStack(Block.cloth, 1, 2), new ItemStack(Block.cloth, 1, 0), "dyeMagenta");
        GT_ModHandler.addShapelessCraftingRecipe(new ItemStack(Block.cloth, 1, 3), new ItemStack(Block.cloth, 1, 0), "dyeLightBlue");
        GT_ModHandler.addShapelessCraftingRecipe(new ItemStack(Block.cloth, 1, 4), new ItemStack(Block.cloth, 1, 0), "dyeYellow");
        GT_ModHandler.addShapelessCraftingRecipe(new ItemStack(Block.cloth, 1, 5), new ItemStack(Block.cloth, 1, 0), "dyeLime");
        GT_ModHandler.addShapelessCraftingRecipe(new ItemStack(Block.cloth, 1, 6), new ItemStack(Block.cloth, 1, 0), "dyePink");
        GT_ModHandler.addShapelessCraftingRecipe(new ItemStack(Block.cloth, 1, 7), new ItemStack(Block.cloth, 1, 0), "dyeGray");
        GT_ModHandler.addShapelessCraftingRecipe(new ItemStack(Block.cloth, 1, 8), new ItemStack(Block.cloth, 1, 0), "dyeLightGray");
        GT_ModHandler.addShapelessCraftingRecipe(new ItemStack(Block.cloth, 1, 9), new ItemStack(Block.cloth, 1, 0), "dyeCyan");
        GT_ModHandler.addShapelessCraftingRecipe(new ItemStack(Block.cloth, 1, 10), new ItemStack(Block.cloth, 1, 0), "dyePurple");
        GT_ModHandler.addShapelessCraftingRecipe(new ItemStack(Block.cloth, 1, 11), new ItemStack(Block.cloth, 1, 0), "dyeBlue");
        GT_ModHandler.addShapelessCraftingRecipe(new ItemStack(Block.cloth, 1, 12), new ItemStack(Block.cloth, 1, 0), "dyeBrown");
        GT_ModHandler.addShapelessCraftingRecipe(new ItemStack(Block.cloth, 1, 13), new ItemStack(Block.cloth, 1, 0), "dyeGreen");
        GT_ModHandler.addShapelessCraftingRecipe(new ItemStack(Block.cloth, 1, 14), new ItemStack(Block.cloth, 1, 0), "dyeRed");
        GT_ModHandler.addShapelessCraftingRecipe(new ItemStack(Block.cloth, 1, 15), new ItemStack(Block.cloth, 1, 0), "dyeBlack");
        GT_ModHandler.addShapelessCraftingRecipe(GT_ModHandler.getIC2Item("painter", 1), GT_ModHandler.getIC2Item("blackPainter", 1));
        GT_ModHandler.addShapelessCraftingRecipe(GT_ModHandler.getIC2Item("painter", 1), GT_ModHandler.getIC2Item("redPainter", 1));
        GT_ModHandler.addShapelessCraftingRecipe(GT_ModHandler.getIC2Item("painter", 1), GT_ModHandler.getIC2Item("greenPainter", 1));
        GT_ModHandler.addShapelessCraftingRecipe(GT_ModHandler.getIC2Item("painter", 1), GT_ModHandler.getIC2Item("brownPainter", 1));
        GT_ModHandler.addShapelessCraftingRecipe(GT_ModHandler.getIC2Item("painter", 1), GT_ModHandler.getIC2Item("bluePainter", 1));
        GT_ModHandler.addShapelessCraftingRecipe(GT_ModHandler.getIC2Item("painter", 1), GT_ModHandler.getIC2Item("purplePainter", 1));
        GT_ModHandler.addShapelessCraftingRecipe(GT_ModHandler.getIC2Item("painter", 1), GT_ModHandler.getIC2Item("cyanPainter", 1));
        GT_ModHandler.addShapelessCraftingRecipe(GT_ModHandler.getIC2Item("painter", 1), GT_ModHandler.getIC2Item("lightGreyPainter", 1));
        GT_ModHandler.addShapelessCraftingRecipe(GT_ModHandler.getIC2Item("painter", 1), GT_ModHandler.getIC2Item("darkGreyPainter", 1));
        GT_ModHandler.addShapelessCraftingRecipe(GT_ModHandler.getIC2Item("painter", 1), GT_ModHandler.getIC2Item("pinkPainter", 1));
        GT_ModHandler.addShapelessCraftingRecipe(GT_ModHandler.getIC2Item("painter", 1), GT_ModHandler.getIC2Item("limePainter", 1));
        GT_ModHandler.addShapelessCraftingRecipe(GT_ModHandler.getIC2Item("painter", 1), GT_ModHandler.getIC2Item("yellowPainter", 1));
        GT_ModHandler.addShapelessCraftingRecipe(GT_ModHandler.getIC2Item("painter", 1), GT_ModHandler.getIC2Item("cloudPainter", 1));
        GT_ModHandler.addShapelessCraftingRecipe(GT_ModHandler.getIC2Item("painter", 1), GT_ModHandler.getIC2Item("magentaPainter", 1));
        GT_ModHandler.addShapelessCraftingRecipe(GT_ModHandler.getIC2Item("painter", 1), GT_ModHandler.getIC2Item("orangePainter", 1));
        GT_ModHandler.addShapelessCraftingRecipe(GT_ModHandler.getIC2Item("painter", 1), GT_ModHandler.getIC2Item("whitePainter", 1));
        GT_Log.out.println("GT_Mod: Adding 'The holy Planks of Sengir'.");
        tStack = GT_MetaItem_Material.instance.getStack(15, 1);
        tStack.setItemName("The holy Planks of Sengir");
        tStack.addEnchantment(Enchantment.smite, 10);
        GT_ModHandler.addCraftingRecipe(tStack, "XXX", "XDX", "XXX", Character.valueOf('X'), new ItemStack(Item.netherStar, 1, -1), Character.valueOf('D'), new ItemStack(Block.dragonEgg, 1, -1));
        if (null != GT_ModHandler.removeRecipe(new ItemStack[]{GT_OreDictUnificator.get("ingotCopper", 1), GT_OreDictUnificator.get("ingotCopper", 1), null, GT_OreDictUnificator.get("ingotCopper", 1), GT_OreDictUnificator.get("ingotTin", 1)})) {
            if (this.mForestryBronzeNerf) {
                GT_Log.out.println("GT_Mod: Nerfing Forestrys invalid Bronze Recipe (even though it's still logical, seeing the amount of Ingots, but IC added Bronze first, so...).");
            }
            GT_ModHandler.addShapelessCraftingRecipe(GT_OreDictUnificator.get("ingotBronze", this.mForestryBronzeNerf ? 1 : 4), "ingotCopper", "ingotCopper", "ingotCopper", "ingotTin");
        }
        if (!this.mEnchantmentTable) {
            GT_Log.out.println("GT_Mod: Removing the Recipe of the Enchantment Table, to have more Fun at enchanting with the Anvil and Books from Dungeons.");
            GT_ModHandler.removeRecipe(new ItemStack[]{null, new ItemStack(Item.book, 1), null, new ItemStack(Item.diamond), new ItemStack(Block.obsidian, 1), new ItemStack(Item.diamond), new ItemStack(Block.obsidian, 1), new ItemStack(Block.obsidian, 1), new ItemStack(Block.obsidian, 1)});
            GT_Log.out.println("GT_Mod: De-Enchanting using the Magic Energy Absorber is now 100 times more lucrative.");
        }
        GT_Log.out.println("GT_Mod: Adding Mixed Metal Ingot Recipes.");
        GT_ModHandler.removeRecipe(new ItemStack[]{GT_OreDictUnificator.get("ingotRefinedIron", 1), GT_OreDictUnificator.get("ingotRefinedIron", 1), GT_OreDictUnificator.get("ingotRefinedIron", 1), GT_OreDictUnificator.get("ingotBronze", 1), GT_OreDictUnificator.get("ingotBronze", 1), GT_OreDictUnificator.get("ingotBronze", 1), GT_OreDictUnificator.get("ingotTin", 1), GT_OreDictUnificator.get("ingotTin", 1), GT_OreDictUnificator.get("ingotTin", 1)});
        GT_ModHandler.addCraftingRecipe(GT_ModHandler.getIC2Item("mixedMetalIngot", 1), "X", "Y", "Z", Character.valueOf('X'), "plateRefinedIron", Character.valueOf('Y'), "plateBronze", Character.valueOf('Z'), "plateTin");
        GT_ModHandler.addCraftingRecipe(GT_ModHandler.getIC2Item("mixedMetalIngot", 1), "X", "Y", "Z", Character.valueOf('X'), "plateRefinedIron", Character.valueOf('Y'), "plateBronze", Character.valueOf('Z'), "plateZinc");
        GT_ModHandler.addCraftingRecipe(GT_ModHandler.getIC2Item("mixedMetalIngot", 1), "X", "Y", "Z", Character.valueOf('X'), "plateRefinedIron", Character.valueOf('Y'), "plateBronze", Character.valueOf('Z'), "plateAluminium");
        GT_ModHandler.addCraftingRecipe(GT_ModHandler.getIC2Item("mixedMetalIngot", 1), "X", "Y", "Z", Character.valueOf('X'), "plateRefinedIron", Character.valueOf('Y'), "plateBrass", Character.valueOf('Z'), "plateTin");
        GT_ModHandler.addCraftingRecipe(GT_ModHandler.getIC2Item("mixedMetalIngot", 1), "X", "Y", "Z", Character.valueOf('X'), "plateRefinedIron", Character.valueOf('Y'), "plateBrass", Character.valueOf('Z'), "plateZinc");
        GT_ModHandler.addCraftingRecipe(GT_ModHandler.getIC2Item("mixedMetalIngot", 1), "X", "Y", "Z", Character.valueOf('X'), "plateRefinedIron", Character.valueOf('Y'), "plateBrass", Character.valueOf('Z'), "plateAluminium");
        GT_ModHandler.addCraftingRecipe(GT_ModHandler.getIC2Item("mixedMetalIngot", 1), "X", "Y", "Z", Character.valueOf('X'), "plateNickel", Character.valueOf('Y'), "plateBronze", Character.valueOf('Z'), "plateTin");
        GT_ModHandler.addCraftingRecipe(GT_ModHandler.getIC2Item("mixedMetalIngot", 1), "X", "Y", "Z", Character.valueOf('X'), "plateNickel", Character.valueOf('Y'), "plateBronze", Character.valueOf('Z'), "plateZinc");
        GT_ModHandler.addCraftingRecipe(GT_ModHandler.getIC2Item("mixedMetalIngot", 1), "X", "Y", "Z", Character.valueOf('X'), "plateNickel", Character.valueOf('Y'), "plateBronze", Character.valueOf('Z'), "plateAluminium");
        GT_ModHandler.addCraftingRecipe(GT_ModHandler.getIC2Item("mixedMetalIngot", 1), "X", "Y", "Z", Character.valueOf('X'), "plateNickel", Character.valueOf('Y'), "plateBrass", Character.valueOf('Z'), "plateTin");
        GT_ModHandler.addCraftingRecipe(GT_ModHandler.getIC2Item("mixedMetalIngot", 1), "X", "Y", "Z", Character.valueOf('X'), "plateNickel", Character.valueOf('Y'), "plateBrass", Character.valueOf('Z'), "plateZinc");
        GT_ModHandler.addCraftingRecipe(GT_ModHandler.getIC2Item("mixedMetalIngot", 1), "X", "Y", "Z", Character.valueOf('X'), "plateNickel", Character.valueOf('Y'), "plateBrass", Character.valueOf('Z'), "plateAluminium");
        GT_ModHandler.addCraftingRecipe(GT_ModHandler.getIC2Item("mixedMetalIngot", 2), "X", "Y", "Z", Character.valueOf('X'), "plateInvar", Character.valueOf('Y'), "plateBronze", Character.valueOf('Z'), "plateTin");
        GT_ModHandler.addCraftingRecipe(GT_ModHandler.getIC2Item("mixedMetalIngot", 2), "X", "Y", "Z", Character.valueOf('X'), "plateInvar", Character.valueOf('Y'), "plateBronze", Character.valueOf('Z'), "plateZinc");
        GT_ModHandler.addCraftingRecipe(GT_ModHandler.getIC2Item("mixedMetalIngot", 3), "X", "Y", "Z", Character.valueOf('X'), "plateInvar", Character.valueOf('Y'), "plateBronze", Character.valueOf('Z'), "plateAluminium");
        GT_ModHandler.addCraftingRecipe(GT_ModHandler.getIC2Item("mixedMetalIngot", 2), "X", "Y", "Z", Character.valueOf('X'), "plateInvar", Character.valueOf('Y'), "plateBrass", Character.valueOf('Z'), "plateTin");
        GT_ModHandler.addCraftingRecipe(GT_ModHandler.getIC2Item("mixedMetalIngot", 2), "X", "Y", "Z", Character.valueOf('X'), "plateInvar", Character.valueOf('Y'), "plateBrass", Character.valueOf('Z'), "plateZinc");
        GT_ModHandler.addCraftingRecipe(GT_ModHandler.getIC2Item("mixedMetalIngot", 3), "X", "Y", "Z", Character.valueOf('X'), "plateInvar", Character.valueOf('Y'), "plateBrass", Character.valueOf('Z'), "plateAluminium");
        GT_ModHandler.addCraftingRecipe(GT_ModHandler.getIC2Item("mixedMetalIngot", 2), "X", "Y", "Z", Character.valueOf('X'), "plateSteel", Character.valueOf('Y'), "plateBronze", Character.valueOf('Z'), "plateTin");
        GT_ModHandler.addCraftingRecipe(GT_ModHandler.getIC2Item("mixedMetalIngot", 2), "X", "Y", "Z", Character.valueOf('X'), "plateSteel", Character.valueOf('Y'), "plateBronze", Character.valueOf('Z'), "plateZinc");
        GT_ModHandler.addCraftingRecipe(GT_ModHandler.getIC2Item("mixedMetalIngot", 3), "X", "Y", "Z", Character.valueOf('X'), "plateSteel", Character.valueOf('Y'), "plateBronze", Character.valueOf('Z'), "plateAluminium");
        GT_ModHandler.addCraftingRecipe(GT_ModHandler.getIC2Item("mixedMetalIngot", 2), "X", "Y", "Z", Character.valueOf('X'), "plateSteel", Character.valueOf('Y'), "plateBrass", Character.valueOf('Z'), "plateTin");
        GT_ModHandler.addCraftingRecipe(GT_ModHandler.getIC2Item("mixedMetalIngot", 2), "X", "Y", "Z", Character.valueOf('X'), "plateSteel", Character.valueOf('Y'), "plateBrass", Character.valueOf('Z'), "plateZinc");
        GT_ModHandler.addCraftingRecipe(GT_ModHandler.getIC2Item("mixedMetalIngot", 3), "X", "Y", "Z", Character.valueOf('X'), "plateSteel", Character.valueOf('Y'), "plateBrass", Character.valueOf('Z'), "plateAluminium");
        GT_ModHandler.addCraftingRecipe(GT_ModHandler.getIC2Item("mixedMetalIngot", 3), "X", "Y", "Z", Character.valueOf('X'), "plateTitanium", Character.valueOf('Y'), "plateBronze", Character.valueOf('Z'), "plateTin");
        GT_ModHandler.addCraftingRecipe(GT_ModHandler.getIC2Item("mixedMetalIngot", 3), "X", "Y", "Z", Character.valueOf('X'), "plateTitanium", Character.valueOf('Y'), "plateBronze", Character.valueOf('Z'), "plateZinc");
        GT_ModHandler.addCraftingRecipe(GT_ModHandler.getIC2Item("mixedMetalIngot", 4), "X", "Y", "Z", Character.valueOf('X'), "plateTitanium", Character.valueOf('Y'), "plateBronze", Character.valueOf('Z'), "plateAluminium");
        GT_ModHandler.addCraftingRecipe(GT_ModHandler.getIC2Item("mixedMetalIngot", 3), "X", "Y", "Z", Character.valueOf('X'), "plateTitanium", Character.valueOf('Y'), "plateBrass", Character.valueOf('Z'), "plateTin");
        GT_ModHandler.addCraftingRecipe(GT_ModHandler.getIC2Item("mixedMetalIngot", 3), "X", "Y", "Z", Character.valueOf('X'), "plateTitanium", Character.valueOf('Y'), "plateBrass", Character.valueOf('Z'), "plateZinc");
        GT_ModHandler.addCraftingRecipe(GT_ModHandler.getIC2Item("mixedMetalIngot", 4), "X", "Y", "Z", Character.valueOf('X'), "plateTitanium", Character.valueOf('Y'), "plateBrass", Character.valueOf('Z'), "plateAluminium");
        GT_ModHandler.addCraftingRecipe(GT_ModHandler.getIC2Item("mixedMetalIngot", 3), "X", "Y", "Z", Character.valueOf('X'), "plateTungsten", Character.valueOf('Y'), "plateBronze", Character.valueOf('Z'), "plateTin");
        GT_ModHandler.addCraftingRecipe(GT_ModHandler.getIC2Item("mixedMetalIngot", 3), "X", "Y", "Z", Character.valueOf('X'), "plateTungsten", Character.valueOf('Y'), "plateBronze", Character.valueOf('Z'), "plateZinc");
        GT_ModHandler.addCraftingRecipe(GT_ModHandler.getIC2Item("mixedMetalIngot", 4), "X", "Y", "Z", Character.valueOf('X'), "plateTungsten", Character.valueOf('Y'), "plateBronze", Character.valueOf('Z'), "plateAluminium");
        GT_ModHandler.addCraftingRecipe(GT_ModHandler.getIC2Item("mixedMetalIngot", 3), "X", "Y", "Z", Character.valueOf('X'), "plateTungsten", Character.valueOf('Y'), "plateBrass", Character.valueOf('Z'), "plateTin");
        GT_ModHandler.addCraftingRecipe(GT_ModHandler.getIC2Item("mixedMetalIngot", 3), "X", "Y", "Z", Character.valueOf('X'), "plateTungsten", Character.valueOf('Y'), "plateBrass", Character.valueOf('Z'), "plateZinc");
        GT_ModHandler.addCraftingRecipe(GT_ModHandler.getIC2Item("mixedMetalIngot", 4), "X", "Y", "Z", Character.valueOf('X'), "plateTungsten", Character.valueOf('Y'), "plateBrass", Character.valueOf('Z'), "plateAluminium");
        GT_ModHandler.addCraftingRecipe(GT_ModHandler.getIC2Item("mixedMetalIngot", 5), "X", "Y", "Z", Character.valueOf('X'), "plateTungstenSteel", Character.valueOf('Y'), "plateBronze", Character.valueOf('Z'), "plateTin");
        GT_ModHandler.addCraftingRecipe(GT_ModHandler.getIC2Item("mixedMetalIngot", 5), "X", "Y", "Z", Character.valueOf('X'), "plateTungstenSteel", Character.valueOf('Y'), "plateBronze", Character.valueOf('Z'), "plateZinc");
        GT_ModHandler.addCraftingRecipe(GT_ModHandler.getIC2Item("mixedMetalIngot", 6), "X", "Y", "Z", Character.valueOf('X'), "plateTungstenSteel", Character.valueOf('Y'), "plateBronze", Character.valueOf('Z'), "plateAluminium");
        GT_ModHandler.addCraftingRecipe(GT_ModHandler.getIC2Item("mixedMetalIngot", 5), "X", "Y", "Z", Character.valueOf('X'), "plateTungstenSteel", Character.valueOf('Y'), "plateBrass", Character.valueOf('Z'), "plateTin");
        GT_ModHandler.addCraftingRecipe(GT_ModHandler.getIC2Item("mixedMetalIngot", 5), "X", "Y", "Z", Character.valueOf('X'), "plateTungstenSteel", Character.valueOf('Y'), "plateBrass", Character.valueOf('Z'), "plateZinc");
        GT_ModHandler.addCraftingRecipe(GT_ModHandler.getIC2Item("mixedMetalIngot", 6), "X", "Y", "Z", Character.valueOf('X'), "plateTungstenSteel", Character.valueOf('Y'), "plateBrass", Character.valueOf('Z'), "plateAluminium");
        GT_Log.out.println("GT_Mod: Adding Rolling Machine Recipes.");
        GT_ModHandler.addRollingMachineRecipe(GT_MetaItem_Material.instance.getStack(13, 3), new Object[]{"AAA", "MMM", "AAA", Character.valueOf('A'), "plateAluminium", Character.valueOf('M'), "dustMagnesium"});
        GT_ModHandler.addRollingMachineRecipe(GT_MetaItem_Material.instance.getStack(13, 3), new Object[]{"AAA", "MMM", "AAA", Character.valueOf('A'), "plateAluminium", Character.valueOf('M'), "ingotMagnesium"});
        GT_ModHandler.addRollingMachineRecipe(GT_MetaItem_Material.instance.getStack(13, 3), new Object[]{"AAA", "MMM", "AAA", Character.valueOf('A'), "plateAluminium", Character.valueOf('M'), "plateMagnesium"});
        GT_ModHandler.addRollingMachineRecipe(GT_MetaItem_Component.instance.getStack(19, 3), new Object[]{"AAA", "BCC", "BBC", Character.valueOf('A'), "ingotRefinedIron", Character.valueOf('B'), "ingotChrome", Character.valueOf('C'), "ingotAluminium"});
        GT_ModHandler.addRollingMachineRecipe(GT_MetaItem_Component.instance.getStack(20, 1), new Object[]{" B ", "BAB", " B ", Character.valueOf('A'), "ingotChrome", Character.valueOf('B'), "ingotNickel"});
        GT_ModHandler.addRollingMachineRecipe(GT_MetaItem_Component.instance.getStack(21, 3), new Object[]{"BAB", "A A", "BAB", Character.valueOf('A'), "ingotCopper", Character.valueOf('B'), "ingotNickel"});
        GT_ModHandler.addRollingMachineRecipe(GT_ModHandler.getRCItem("part.rail.standard", 4), new Object[]{"X X", "X X", "X X", Character.valueOf('X'), "ingotAluminium"});
        GT_ModHandler.addRollingMachineRecipe(GT_ModHandler.getRCItem("part.rail.standard", 32), new Object[]{"X X", "X X", "X X", Character.valueOf('X'), "ingotTitanium"});
        GT_ModHandler.addRollingMachineRecipe(GT_ModHandler.getRCItem("part.rail.standard", 32), new Object[]{"X X", "X X", "X X", Character.valueOf('X'), "ingotTungsten"});
        GT_ModHandler.addRollingMachineRecipe(GT_ModHandler.getRCItem("part.rail.reinforced", 32), new Object[]{"X X", "X X", "X X", Character.valueOf('X'), "ingotTungstenSteel"});
        GT_ModHandler.addRollingMachineRecipe(GT_ModHandler.getRCItem("part.rebar", 2), new Object[]{"  X", " X ", "X  ", Character.valueOf('X'), "ingotAluminium"});
        GT_ModHandler.addRollingMachineRecipe(GT_ModHandler.getRCItem("part.rebar", 16), new Object[]{"  X", " X ", "X  ", Character.valueOf('X'), "ingotTitanium"});
        GT_ModHandler.addRollingMachineRecipe(GT_ModHandler.getRCItem("part.rebar", 16), new Object[]{"  X", " X ", "X  ", Character.valueOf('X'), "ingotTungsten"});
        GT_ModHandler.addRollingMachineRecipe(GT_ModHandler.getRCItem("part.rebar", 48), new Object[]{"  X", " X ", "X  ", Character.valueOf('X'), "ingotTungstenSteel"});
        GT_ModHandler.addRollingMachineRecipe(GT_ModHandler.getRCItem("post.metal.light.blue", 8), new Object[]{"XXX", " X ", "XXX", Character.valueOf('X'), "ingotAluminium"});
        GT_ModHandler.addRollingMachineRecipe(GT_ModHandler.getRCItem("post.metal.purple", 64), new Object[]{"XXX", " X ", "XXX", Character.valueOf('X'), "ingotTitanium"});
        GT_ModHandler.addRollingMachineRecipe(GT_ModHandler.getRCItem("post.metal.black", 64), new Object[]{"XXX", " X ", "XXX", Character.valueOf('X'), "ingotTungsten"});
        GT_ModHandler.addRollingMachineRecipe(GT_ModHandler.getRCItem("post.metal.light.blue", 8), new Object[]{"X X", "XXX", "X X", Character.valueOf('X'), "ingotAluminium"});
        GT_ModHandler.addRollingMachineRecipe(GT_ModHandler.getRCItem("post.metal.purple", 64), new Object[]{"X X", "XXX", "X X", Character.valueOf('X'), "ingotTitanium"});
        GT_ModHandler.addRollingMachineRecipe(GT_ModHandler.getRCItem("post.metal.black", 64), new Object[]{"X X", "XXX", "X X", Character.valueOf('X'), "ingotTungsten"});
        GT_Log.out.println("GT_Mod: Beginning to add regular Crafting Recipes.");
        GT_ModHandler.addCraftingRecipe(GT_ModHandler.getIC2Item("doubleInsulatedGoldCableItem", 4), "RRR", "RGR", "RRR", Character.valueOf('G'), "ingotGold", Character.valueOf('R'), "itemRubber");
        GT_ModHandler.addCraftingRecipe(GT_ModHandler.getIC2Item("industrialTnt", 5), "FFF", "TTT", "FFF", Character.valueOf('T'), new ItemStack(Block.tnt, 1), Character.valueOf('F'), "dustFlint");
        GT_ModHandler.addCraftingRecipe(GT_ModHandler.getEmptyCell(this.mTincellCount), " T ", "T T", " T ", Character.valueOf('T'), "ingotTin");
        GT_ModHandler.addCraftingRecipe(GT_ModHandler.getIC2Item("scaffold", 4), "WWW", " S ", "S S", Character.valueOf('W'), "plankWood", Character.valueOf('S'), "stickWood");
        GT_ModHandler.addCraftingRecipe(new ItemStack(Block.pistonBase, 1), "WWW", "CBC", "CRC", Character.valueOf('W'), "plankWood", Character.valueOf('C'), new ItemStack(Block.cobblestone, 1), Character.valueOf('R'), "dustRedstone", Character.valueOf('B'), "ingotIron");
        GT_ModHandler.addCraftingRecipe(new ItemStack(Block.pistonBase, 1), "WWW", "CBC", "CRC", Character.valueOf('W'), "plankWood", Character.valueOf('C'), new ItemStack(Block.cobblestone, 1), Character.valueOf('R'), "dustRedstone", Character.valueOf('B'), "ingotBronze");
        GT_ModHandler.addCraftingRecipe(new ItemStack(Block.pistonBase, 1), "WWW", "CBC", "CRC", Character.valueOf('W'), "plankWood", Character.valueOf('C'), new ItemStack(Block.cobblestone, 1), Character.valueOf('R'), "dustRedstone", Character.valueOf('B'), "ingotAluminium");
        GT_ModHandler.addCraftingRecipe(new ItemStack(Block.pistonBase, 1), "WWW", "CBC", "CRC", Character.valueOf('W'), "plankWood", Character.valueOf('C'), new ItemStack(Block.cobblestone, 1), Character.valueOf('R'), "dustRedstone", Character.valueOf('B'), "ingotRefinedIron");
        GT_ModHandler.addCraftingRecipe(new ItemStack(Block.pistonBase, 1), "WWW", "CBC", "CRC", Character.valueOf('W'), "plankWood", Character.valueOf('C'), new ItemStack(Block.cobblestone, 1), Character.valueOf('R'), "dustRedstone", Character.valueOf('B'), "ingotSteel");
        GT_ModHandler.addCraftingRecipe(new ItemStack(Block.pistonBase, 1), "WWW", "CBC", "CRC", Character.valueOf('W'), "plankWood", Character.valueOf('C'), new ItemStack(Block.cobblestone, 1), Character.valueOf('R'), "dustRedstone", Character.valueOf('B'), "ingotTitanium");
        GT_ModHandler.addCraftingRecipe(new ItemStack(this.mItems[37], 1), "LLL", "LIL", "LLL", Character.valueOf('L'), "crafting1kkEUStore", Character.valueOf('I'), "plateIridiumAlloy");
        GT_ModHandler.addCraftingRecipe(GT_MetaItem_Component.instance.getStack(0, 4), "AWA", "LIL", "AWA", Character.valueOf('L'), "crafting1kkEUStore", Character.valueOf('I'), "plateIridiumAlloy", Character.valueOf('A'), "craftingCircuitTier04", Character.valueOf('W'), "plateTungsten");
        GT_ModHandler.addCraftingRecipe(GT_MetaItem_Component.instance.getStack(1, 4), "AEA", "EIE", "AEA", Character.valueOf('E'), "craftingCircuitTier05", Character.valueOf('I'), "plateIridiumAlloy", Character.valueOf('A'), "craftingCircuitTier04");
        GT_ModHandler.addCraftingRecipe(GT_MetaItem_Component.instance.getStack(2, 4), "CCC", "WIW", "LLL", Character.valueOf('L'), "craftingCircuitTier07", Character.valueOf('W'), "plateTungsten", Character.valueOf('I'), "plateIridiumAlloy", Character.valueOf('C'), new ItemStack(this.mItems[34], 1));
        GT_ModHandler.addCraftingRecipe(GT_MetaItem_Component.instance.getStack(2, 4), "CCC", "WIW", "LLL", Character.valueOf('L'), "craftingCircuitTier07", Character.valueOf('W'), "plateTungsten", Character.valueOf('I'), "plateIridiumAlloy", Character.valueOf('C'), new ItemStack(this.mItems[60], 1));
        GT_ModHandler.addCraftingRecipe(GT_MetaItem_Component.instance.getStack(2, 4), "CCC", "WIW", "LLL", Character.valueOf('L'), "craftingCircuitTier07", Character.valueOf('W'), "plateTungsten", Character.valueOf('I'), "plateIridiumAlloy", Character.valueOf('C'), GT_ModHandler.getIC2Item("reactorCoolantSix", 1));
        GT_ModHandler.addShapelessCraftingRecipe(new ItemStack(this.mItems[43], 1), new ItemStack(this.mItems[43], 1));
        GT_ModHandler.addCraftingRecipe(GT_MetaItem_Component.instance.getStack(4, 1), "AGA", "RPB", "ALA", Character.valueOf('A'), "plateAluminium", Character.valueOf('L'), "dustGlowstone", Character.valueOf('R'), "dyeRed", Character.valueOf('G'), "dyeLime", Character.valueOf('B'), "dyeBlue", Character.valueOf('P'), new ItemStack(Block.thinGlass, 1));
        GT_ModHandler.addCraftingRecipe(GT_MetaItem_Component.instance.getStack(5, 1), "GGG", "AAA", "CBC", Character.valueOf('A'), "plateAluminium", Character.valueOf('C'), "craftingCircuitTier02", Character.valueOf('B'), "crafting10kEUStore", Character.valueOf('G'), new ItemStack(Block.glass, 1));
        GT_ModHandler.addCraftingRecipe(GT_MetaItem_Component.instance.getStack(5, 1), "GGG", "RRR", "CBC", Character.valueOf('R'), "plateRefinedIron", Character.valueOf('C'), "craftingCircuitTier02", Character.valueOf('B'), "crafting10kEUStore", Character.valueOf('G'), new ItemStack(Block.glass, 1));
        GT_ModHandler.addCraftingRecipe(GT_MetaItem_Component.instance.getStack(5, 1), "GGG", "AAA", "CBC", Character.valueOf('A'), "ingotAluminium", Character.valueOf('C'), "craftingCircuitTier02", Character.valueOf('B'), "crafting10kEUStore", Character.valueOf('G'), new ItemStack(Block.glass, 1));
        GT_ModHandler.addCraftingRecipe(GT_MetaItem_Component.instance.getStack(5, 1), "GGG", "RRR", "CBC", Character.valueOf('R'), "ingotRefinedIron", Character.valueOf('C'), "craftingCircuitTier02", Character.valueOf('B'), "crafting10kEUStore", Character.valueOf('G'), new ItemStack(Block.glass, 1));
        GT_ModHandler.addCraftingRecipe(GT_MetaItem_Component.instance.getStack(17, 4), "DSD", "S S", "DSD", Character.valueOf('D'), "dustDiamond", Character.valueOf('S'), "plateSteel");
        GT_ModHandler.addCraftingRecipe(GT_MetaItem_Component.instance.getStack(18, 2), "DSD", "SGS", "DSD", Character.valueOf('G'), "craftingIndustrialDiamond", Character.valueOf('D'), "dustDiamond", Character.valueOf('S'), "plateSteel");
        GT_ModHandler.addCraftingRecipe(GT_MetaItem_Component.instance.getStack(23, 2), "TST", "SBS", "TST", Character.valueOf('B'), "blockSteel", Character.valueOf('T'), "plateTungsten", Character.valueOf('S'), "plateSteel");
        GT_ModHandler.addCraftingRecipe(new ItemStack(this.mItems[49], 1), "CPC", Character.valueOf('C'), new ItemStack(this.mItems[48], 1), Character.valueOf('P'), "plateCopper");
        GT_ModHandler.addCraftingRecipe(new ItemStack(this.mItems[50], 1), " C ", "PPP", " C ", Character.valueOf('C'), new ItemStack(this.mItems[49], 1), Character.valueOf('P'), "plateCopper");
        GT_ModHandler.addCraftingRecipe(new ItemStack(this.mItems[52], 1), "CPC", Character.valueOf('C'), new ItemStack(this.mItems[51], 1), Character.valueOf('P'), "plateCopperDense");
        GT_ModHandler.addCraftingRecipe(new ItemStack(this.mItems[53], 1), " C ", "PPP", " C ", Character.valueOf('C'), new ItemStack(this.mItems[52], 1), Character.valueOf('P'), "plateCopperDense");
        GT_ModHandler.addCraftingRecipe(GT_ModHandler.getIC2Item("reactorVent", 1), "AIA", "I I", "AIA", Character.valueOf('I'), new ItemStack(Block.fenceIron, 1), Character.valueOf('A'), "plateAluminium");
        GT_ModHandler.addShapelessCraftingRecipe(GT_ModHandler.getIC2Item("reactorPlatingExplosive", 1), GT_ModHandler.getIC2Item("reactorPlating", 1), "plateLead");
        GT_ModHandler.addCraftingRecipe(new ItemStack(this.mBlocks[0], 1, 10), "CTC", "TMT", "CTC", Character.valueOf('C'), "plateChrome", Character.valueOf('T'), "plateTitanium", Character.valueOf('M'), "craftingRawMachineTier02");
        GT_ModHandler.addCraftingRecipe(new ItemStack(this.mBlocks[0], 4, 13), "AAA", "CMC", "AAA", Character.valueOf('C'), "craftingCircuitTier02", Character.valueOf('A'), "plateRefinedIron", Character.valueOf('M'), "craftingRawMachineTier01");
        GT_ModHandler.addCraftingRecipe(new ItemStack(this.mBlocks[0], 4, 13), "AAA", "CMC", "AAA", Character.valueOf('C'), "craftingCircuitTier02", Character.valueOf('A'), "plateAluminium", Character.valueOf('M'), "craftingRawMachineTier01");
        GT_ModHandler.addCraftingRecipe(new ItemStack(this.mBlocks[0], 4, 14), "SSS", "CMC", "SSS", Character.valueOf('C'), "craftingCircuitTier04", Character.valueOf('S'), "plateSteel", Character.valueOf('M'), "craftingRawMachineTier02");
        GT_ModHandler.addCraftingRecipe(new ItemStack(this.mBlocks[0], 4, 15), "TTT", "CMC", "TTT", Character.valueOf('C'), "craftingCircuitTier06", Character.valueOf('T'), "plateChrome", Character.valueOf('M'), "craftingRawMachineTier04");
        GT_ModHandler.addCraftingRecipe(GT_ModHandler.getIC2Item("generator", 1), "B", "M", "F", Character.valueOf('B'), "crafting10kEUStore", Character.valueOf('M'), "craftingRawMachineTier01", Character.valueOf('F'), new ItemStack(Block.stoneOvenIdle, 1, -1));
        GT_ModHandler.addCraftingRecipe(GT_ModHandler.getIC2Item("generator", 1), " B ", "RRR", " F ", Character.valueOf('B'), "crafting10kEUStore", Character.valueOf('R'), "ingotRefinedIron", Character.valueOf('F'), GT_ModHandler.getIC2Item("ironFurnace", 1));
        GT_ModHandler.addCraftingRecipe(GT_ModHandler.getIC2Item("generator", 1), " B ", "RRR", " F ", Character.valueOf('B'), "crafting10kEUStore", Character.valueOf('R'), "ingotAluminium", Character.valueOf('F'), GT_ModHandler.getIC2Item("ironFurnace", 1));
        GT_ModHandler.addCraftingRecipe(GT_ModHandler.getIC2Item("generator", 1), " B ", "RRR", " F ", Character.valueOf('B'), "crafting10kEUStore", Character.valueOf('R'), "ingotInvar", Character.valueOf('F'), GT_ModHandler.getIC2Item("ironFurnace", 1));
        GT_ModHandler.addCraftingRecipe(GT_ModHandler.getIC2Item("generator", 1), " B ", "RRR", " F ", Character.valueOf('B'), "crafting10kEUStore", Character.valueOf('R'), "plateRefinedIron", Character.valueOf('F'), GT_ModHandler.getIC2Item("ironFurnace", 1));
        GT_ModHandler.addCraftingRecipe(GT_ModHandler.getIC2Item("generator", 1), " B ", "RRR", " F ", Character.valueOf('B'), "crafting10kEUStore", Character.valueOf('R'), "plateAluminium", Character.valueOf('F'), GT_ModHandler.getIC2Item("ironFurnace", 1));
        GT_ModHandler.addCraftingRecipe(GT_ModHandler.getIC2Item("generator", 1), " B ", "RRR", " F ", Character.valueOf('B'), "crafting10kEUStore", Character.valueOf('R'), "plateInvar", Character.valueOf('F'), GT_ModHandler.getIC2Item("ironFurnace", 1));
        if (this.mFusionreactor) {
            GT_ModHandler.addCraftingRecipe(new ItemStack(this.mBlocks[1], 1, 80), "CCC", "PHP", "CCC", Character.valueOf('C'), "craftingCircuitTier07", Character.valueOf('P'), "craftingCircuitTier10", Character.valueOf('H'), new ItemStack(this.mBlocks[0], 1, 1));
            GT_ModHandler.addCraftingRecipe(new ItemStack(this.mBlocks[1], 1, 81), "SCS", "CHC", "SCS", Character.valueOf('C'), "craftingCircuitTier07", Character.valueOf('H'), new ItemStack(this.mBlocks[1], 1, 15), Character.valueOf('S'), "craftingSuperconductor");
            GT_ModHandler.addCraftingRecipe(new ItemStack(this.mBlocks[1], 1, 82), "PWP", "CMC", "PCP", Character.valueOf('C'), "craftingCircuitTier07", Character.valueOf('M'), "craftingRawMachineTier04", Character.valueOf('W'), new ItemStack(Block.chest, 1), Character.valueOf('P'), GT_ModHandler.getIC2Item("pump", 1));
            GT_ModHandler.addCraftingRecipe(new ItemStack(this.mBlocks[1], 1, 83), "PCP", "CMC", "PWP", Character.valueOf('C'), "craftingCircuitTier07", Character.valueOf('M'), "craftingRawMachineTier04", Character.valueOf('W'), new ItemStack(Block.chest, 1), Character.valueOf('P'), GT_ModHandler.getIC2Item("pump", 1));
            GT_ModHandler.addCraftingRecipe(new ItemStack(this.mBlocks[0], 1, 1), "ESE", "CMC", "EIE", Character.valueOf('I'), this.mNeutronReflector ? new ItemStack(this.mItems[40], 1) : "plateIridiumAlloy", Character.valueOf('S'), "craftingSuperconductor", Character.valueOf('E'), "craftingCircuitTier07", Character.valueOf('C'), "craftingHeatingCoilTier02", Character.valueOf('M'), "craftingRawMachineTier04");
        }
        if (this.mLightningrod) {
            GT_ModHandler.addCraftingRecipe(new ItemStack(this.mBlocks[1], 1, 2), "IAI", "AMA", "IAI", Character.valueOf('I'), "craftingCircuitTier07", Character.valueOf('A'), "craftingRawMachineTier04", Character.valueOf('M'), new ItemStack(this.mBlocks[1], 1, 15));
        }
        GT_ModHandler.addCraftingRecipe(new ItemStack(this.mBlocks[1], 1, 4), "CGD", "GAG", "DGC", Character.valueOf('D'), "craftingCircuitTier07", Character.valueOf('G'), "craftingMonitorTier02", Character.valueOf('C'), "craftingCircuitTier08", Character.valueOf('A'), "craftingRawMachineTier02");
        if (this.mLESU) {
            GT_ModHandler.addCraftingRecipe(new ItemStack(this.mBlocks[0], 1, 6), "LLL", "LML", "LLL", Character.valueOf('M'), "craftingCircuitTier02", Character.valueOf('L'), "chunkLazurite");
            GT_ModHandler.addCraftingRecipe(new ItemStack(this.mBlocks[1], 1, 7), " L ", "ACA", " M ", Character.valueOf('A'), "craftingCircuitTier04", Character.valueOf('C'), new ItemStack(this.mBlocks[0], 1, 6), Character.valueOf('L'), GT_ModHandler.getIC2Item("lvTransformer", 1), Character.valueOf('M'), GT_ModHandler.getIC2Item("mvTransformer", 1));
        }
        if (this.mIDSU) {
            GT_ModHandler.addCraftingRecipe(new ItemStack(this.mBlocks[1], 1, 8), "IMI", "MCM", "IMI", Character.valueOf('I'), "plateIridiumAlloy", Character.valueOf('C'), "craftingEnderChest", Character.valueOf('M'), this.mAESU ? new ItemStack(this.mBlocks[1], 1, 9) : "crafting10kkEUStore");
        }
        if (this.mAESU) {
            GT_ModHandler.addCraftingRecipe(new ItemStack(this.mBlocks[1], 1, 9), "MMM", "MCM", "MMM", Character.valueOf('C'), "craftingCircuitTier10", Character.valueOf('M'), "crafting10kkEUStore");
        }
        if (this.mChargeOMat) {
            GT_ModHandler.addCraftingRecipe(new ItemStack(this.mBlocks[1], 1, 10), "BCB", "TMT", "BAB", Character.valueOf('C'), "craftingCircuitTier10", Character.valueOf('M'), "crafting10kkEUStore", Character.valueOf('A'), "craftingRawMachineTier02", Character.valueOf('T'), new ItemStack(Block.chest, 1), Character.valueOf('B'), "craftingCircuitTier07");
        }
        if (this.mDigitalChest) {
            GT_ModHandler.addCraftingRecipe(new ItemStack(this.mBlocks[1], 1, 48), "AAA", "ADA", "ASA", Character.valueOf('D'), "craftingCircuitTier08", Character.valueOf('S'), "craftingMonitorTier02", Character.valueOf('A'), "plateRefinedIron");
            GT_ModHandler.addCraftingRecipe(new ItemStack(this.mBlocks[1], 1, 48), "AAA", "ADA", "ASA", Character.valueOf('D'), "craftingCircuitTier08", Character.valueOf('S'), "craftingMonitorTier02", Character.valueOf('A'), "plateAluminium");
        }
        if (this.mQuantumchest) {
            GT_ModHandler.addShapelessCraftingRecipe(new ItemStack(this.mBlocks[1], 1, 49), new ItemStack(this.mBlocks[1], 1, 3));
            GT_ModHandler.addCraftingRecipe(new ItemStack(this.mBlocks[1], 1, 49), "ASA", "BTB", "ALA", Character.valueOf('A'), "craftingCircuitTier08", Character.valueOf('S'), "craftingMonitorTier02", Character.valueOf('L'), this.mDigitalChest ? new ItemStack(this.mBlocks[1], 1, 48) : "craftingCircuitTier07", Character.valueOf('T'), "craftingTeleporter", Character.valueOf('B'), "craftingRawMachineTier04");
            if (this.mQuantumTank) {
                GT_ModHandler.addCraftingRecipe(new ItemStack(this.mBlocks[1], 1, 30), "APA", "PQP", "APA", Character.valueOf('A'), "craftingCircuitTier07", Character.valueOf('P'), "platePlatinum", Character.valueOf('L'), "craftingCircuitTier07", Character.valueOf('Q'), new ItemStack(this.mBlocks[1], 1, 49));
            }
        } else if (this.mQuantumTank) {
            GT_ModHandler.addCraftingRecipe(new ItemStack(this.mBlocks[1], 1, 30), "ASA", "BTB", "ALA", Character.valueOf('A'), "craftingCircuitTier08", Character.valueOf('S'), "craftingMonitorTier02", Character.valueOf('L'), "craftingCircuitTier07", Character.valueOf('T'), "craftingTeleporter", Character.valueOf('B'), "craftingRawMachineTier04");
        }
        GT_ModHandler.addCraftingRecipe(new ItemStack(this.mBlocks[1], 1, 62), "RCR", "AEA", "RCR", Character.valueOf('C'), "craftingCircuitTier04", Character.valueOf('A'), "craftingRawMachineTier02", Character.valueOf('E'), GT_ModHandler.getIC2Item("extractor", 1), Character.valueOf('R'), "plateRefinedIron");
        GT_ModHandler.addCraftingRecipe(new ItemStack(this.mBlocks[1], 1, 62), "RCR", "AEA", "RCR", Character.valueOf('C'), "craftingCircuitTier04", Character.valueOf('A'), "craftingRawMachineTier02", Character.valueOf('E'), GT_ModHandler.getIC2Item("extractor", 1), Character.valueOf('R'), "plateAluminium");
        GT_ModHandler.addCraftingRecipe(new ItemStack(this.mBlocks[1], 1, 25), "RDR", "CEC", "RMR", Character.valueOf('E'), GT_ModHandler.getIC2Item("electrolyzer", 1), Character.valueOf('M'), GT_ModHandler.getIC2Item("magnetizer", 1), Character.valueOf('D'), GT_ModHandler.getIC2Item("extractor", 1), Character.valueOf('R'), "plateRefinedIron", Character.valueOf('C'), "craftingCircuitTier04");
        GT_ModHandler.addCraftingRecipe(new ItemStack(this.mBlocks[1], 1, 25), "RDR", "CEC", "RMR", Character.valueOf('E'), GT_ModHandler.getIC2Item("electrolyzer", 1), Character.valueOf('M'), GT_ModHandler.getIC2Item("magnetizer", 1), Character.valueOf('D'), GT_ModHandler.getIC2Item("extractor", 1), Character.valueOf('R'), "plateAluminium", Character.valueOf('C'), "craftingCircuitTier04");
        GT_ModHandler.addCraftingRecipe(new ItemStack(this.mBlocks[1], 1, 38), "RPR", "CGC", "RPR", Character.valueOf('C'), "craftingCircuitTier04", Character.valueOf('P'), GT_ModHandler.getIC2Item("pump", 1), Character.valueOf('G'), "glassReinforced", Character.valueOf('R'), "plateRefinedIron");
        GT_ModHandler.addCraftingRecipe(new ItemStack(this.mBlocks[1], 1, 38), "RPR", "CGC", "RPR", Character.valueOf('C'), "craftingCircuitTier04", Character.valueOf('P'), GT_ModHandler.getIC2Item("pump", 1), Character.valueOf('G'), "glassReinforced", Character.valueOf('R'), "plateAluminium");
        GT_ModHandler.addCraftingRecipe(new ItemStack(this.mBlocks[1], 1, 41), "RMR", "CEC", "RDR", Character.valueOf('M'), GT_ModHandler.getIC2Item("magnetizer", 1), Character.valueOf('D'), GT_ModHandler.getIC2Item("extractor", 1), Character.valueOf('E'), GT_ModHandler.getIC2Item("compressor", 1), Character.valueOf('R'), "plateInvar", Character.valueOf('C'), "craftingCircuitTier04");
        GT_ModHandler.addCraftingRecipe(new ItemStack(this.mBlocks[1], 1, 41), "RMR", "CEC", "RDR", Character.valueOf('M'), GT_ModHandler.getIC2Item("magnetizer", 1), Character.valueOf('D'), GT_ModHandler.getIC2Item("extractor", 1), Character.valueOf('E'), GT_ModHandler.getIC2Item("compressor", 1), Character.valueOf('R'), "plateAluminium", Character.valueOf('C'), "craftingCircuitTier04");
        if (this.mSuperconductor) {
            GT_ModHandler.addCraftingRecipe(new ItemStack(this.mBlocks[1], 4, 12), "ALA", "CCC", "ALA", Character.valueOf('C'), "craftingSuperconductor", Character.valueOf('A'), "craftingRawMachineTier02", Character.valueOf('L'), "craftingCircuitTier07");
        }
        GT_ModHandler.addCraftingRecipe(new ItemStack(this.mBlocks[1], 1, 15), "LEL", "CAC", "LEL", Character.valueOf('C'), "craftingSuperconductor", Character.valueOf('A'), "craftingRawMachineTier04", Character.valueOf('L'), "craftingCircuitTier07", Character.valueOf('E'), "crafting10kkEUStore");
        if (this.mPlayerdetector) {
            GT_ModHandler.addCraftingRecipe(new ItemStack(this.mBlocks[1], 1, 13), " E ", "ACA", " E ", Character.valueOf('C'), "craftingCircuitTier10", Character.valueOf('A'), "craftingCircuitTier04", Character.valueOf('E'), "craftingCircuitTier05");
        }
        if (this.mMatterfabricator) {
            GT_ModHandler.addCraftingRecipe(new ItemStack(this.mBlocks[1], 1, 14), "ETE", "ALA", "ETE", Character.valueOf('L'), "crafting10kkEUStore", Character.valueOf('A'), "craftingRawMachineTier04", Character.valueOf('E'), "craftingCircuitTier07", Character.valueOf('T'), "craftingTeleporter");
        }
        if (this.mSonictron) {
            GT_ModHandler.addCraftingRecipe(new ItemStack(this.mBlocks[1], 1, 6), "CRC", "NAN", "CJC", Character.valueOf('C'), "craftingCircuitTier02", Character.valueOf('N'), new ItemStack(Block.music, 1), Character.valueOf('A'), "craftingRawMachineTier02", Character.valueOf('J'), new ItemStack(Block.jukebox, 1), Character.valueOf('R'), "itemRecord");
        }
        if (this.mEAutoCrafter) {
            GT_ModHandler.addCraftingRecipe(new ItemStack(this.mBlocks[1], 1, 16), "GBG", "CTC", "GAG", Character.valueOf('B'), "crafting10kEUStore", Character.valueOf('A'), "craftingRawMachineTier02", Character.valueOf('C'), "craftingCircuitTier04", Character.valueOf('T'), "craftingWorkBench", Character.valueOf('G'), "plateElectrum");
        }
        if (this.mAdvancedPump) {
            GT_ModHandler.addCraftingRecipe(new ItemStack(this.mBlocks[1], 1, 47), "CPC", "PMP", "CPC", Character.valueOf('C'), "craftingCircuitTier04", Character.valueOf('M'), "craftingRawMachineTier02", Character.valueOf('P'), "craftingPump");
        }
        if (this.mWiremill) {
            tStack = GT_ModHandler.getRecipeOutput(new ItemStack[]{null, new ItemStack(Item.diamond, 1), null, new ItemStack(Item.ingotIron, 1), null, new ItemStack(Item.ingotIron, 1), null, new ItemStack(Item.ingotIron, 1), null});
            GT_ModHandler.addCraftingRecipe(new ItemStack(this.mBlocks[1], 1, 55), "BDB", "CMC", "BQB", Character.valueOf('M'), "craftingRawMachineTier01", Character.valueOf('C'), "craftingCircuitTier02", Character.valueOf('B'), "plateBrass", Character.valueOf('D'), tStack == null ? "gemDiamond" : tStack, Character.valueOf('Q'), "craftingConveyor");
        }
        if (this.mAlloySmelter) {
            GT_ModHandler.addCraftingRecipe(new ItemStack(this.mBlocks[1], 1, 56), "IHI", "CEC", "IQI", Character.valueOf('H'), "craftingHeatingCoilTier00", Character.valueOf('C'), "craftingCircuitTier02", Character.valueOf('E'), GT_ModHandler.getIC2Item("electroFurnace", 1), Character.valueOf('I'), "plateInvar", Character.valueOf('Q'), "craftingConveyor");
        }
        if (this.mAutoMachines) {
            GT_ModHandler.addShapelessCraftingRecipe(new ItemStack(this.mBlocks[1], 1, 50), "craftingConveyor", GT_ModHandler.getIC2Item("macerator", 1));
            GT_ModHandler.addShapelessCraftingRecipe(new ItemStack(this.mBlocks[1], 1, 51), "craftingConveyor", GT_ModHandler.getIC2Item("extractor", 1));
            GT_ModHandler.addShapelessCraftingRecipe(new ItemStack(this.mBlocks[1], 1, 52), "craftingConveyor", GT_ModHandler.getIC2Item("compressor", 1));
            GT_ModHandler.addShapelessCraftingRecipe(new ItemStack(this.mBlocks[1], 1, 53), "craftingConveyor", GT_ModHandler.getIC2Item("recycler", 1));
            GT_ModHandler.addShapelessCraftingRecipe(new ItemStack(this.mBlocks[1], 1, 54), "craftingConveyor", GT_ModHandler.getIC2Item("electroFurnace", 1));
        }
        GT_ModHandler.addShapelessCraftingRecipe(new ItemStack(this.mBlocks[1], 1, 57), "craftingConveyor", GT_ModHandler.getIC2Item("canner", 1));
        GT_ModHandler.addCraftingRecipe(new ItemStack(this.mBlocks[1], 1, 59), "PCP", "RQR", "PCP", Character.valueOf('C'), "craftingCircuitTier02", Character.valueOf('P'), new ItemStack(Block.pistonBase, 1), Character.valueOf('R'), GT_ModHandler.getIC2Item("compressor", 1), Character.valueOf('Q'), "craftingConveyor");
        GT_ModHandler.addCraftingRecipe(new ItemStack(this.mBlocks[1], 1, 60), "CPC", "RQR", "CRC", Character.valueOf('C'), "craftingCircuitTier02", Character.valueOf('P'), new ItemStack(Block.pistonBase, 1), Character.valueOf('R'), "plateRefinedIron", Character.valueOf('Q'), "craftingConveyor");
        GT_ModHandler.addCraftingRecipe(new ItemStack(this.mBlocks[1], 1, 60), "CPC", "RQR", "CRC", Character.valueOf('C'), "craftingCircuitTier02", Character.valueOf('P'), new ItemStack(Block.pistonBase, 1), Character.valueOf('R'), "plateAluminium", Character.valueOf('Q'), "craftingConveyor");
        GT_ModHandler.addCraftingRecipe(new ItemStack(this.mBlocks[1], 1, 61), "RPR", "CQC", "RRR", Character.valueOf('C'), "craftingCircuitTier02", Character.valueOf('P'), new ItemStack(Block.pistonBase, 1), Character.valueOf('R'), "plateRefinedIron", Character.valueOf('Q'), "craftingConveyor");
        GT_ModHandler.addCraftingRecipe(new ItemStack(this.mBlocks[1], 1, 61), "RPR", "CQC", "RRR", Character.valueOf('C'), "craftingCircuitTier02", Character.valueOf('P'), new ItemStack(Block.pistonBase, 1), Character.valueOf('R'), "plateAluminium", Character.valueOf('Q'), "craftingConveyor");
        if (this.mAutomationStuff) {
            GT_ModHandler.addCraftingRecipe(new ItemStack(this.mBlocks[1], 1, 17), "GBG", "CPC", "GAG", Character.valueOf('B'), "crafting10kEUStore", Character.valueOf('A'), "craftingRawMachineTier00", Character.valueOf('C'), "craftingCircuitTier02", Character.valueOf('G'), "plateElectrum", Character.valueOf('P'), "craftingConveyor");
            GT_ModHandler.addCraftingRecipe(new ItemStack(this.mBlocks[1], 1, 20), "GBG", "CPC", "GAG", Character.valueOf('B'), "crafting10kEUStore", Character.valueOf('A'), "craftingRawMachineTier02", Character.valueOf('C'), "craftingCircuitTier04", Character.valueOf('G'), "plateElectrum", Character.valueOf('P'), "craftingConveyor");
            GT_ModHandler.addCraftingRecipe(new ItemStack(this.mBlocks[1], 1, 23), "GBG", "CHC", "GAG", Character.valueOf('B'), "crafting10kEUStore", Character.valueOf('A'), "craftingMonitorTier02", Character.valueOf('C'), "craftingCircuitTier02", Character.valueOf('G'), "plateElectrum", Character.valueOf('H'), new ItemStack(this.mBlocks[1], 1, 17));
            GT_ModHandler.addCraftingRecipe(new ItemStack(this.mBlocks[1], 1, 58), "GBG", "CHC", "GAG", Character.valueOf('B'), "crafting10kEUStore", Character.valueOf('A'), "craftingMonitorTier02", Character.valueOf('C'), "craftingCircuitTier04", Character.valueOf('G'), "plateElectrum", Character.valueOf('H'), new ItemStack(this.mBlocks[1], 1, 17));
            GT_ModHandler.addCraftingRecipe(new ItemStack(this.mBlocks[1], 1, 21), "GBG", "CHC", "GAG", Character.valueOf('B'), "crafting10kEUStore", Character.valueOf('A'), "craftingMonitorTier02", Character.valueOf('C'), "craftingCircuitTier04", Character.valueOf('G'), "plateElectrum", Character.valueOf('H'), new ItemStack(this.mBlocks[1], 1, 18));
            GT_ModHandler.addCraftingRecipe(new ItemStack(this.mBlocks[1], 1, 39), "CHC", "HAH", "CHC", Character.valueOf('A'), "craftingMonitorTier02", Character.valueOf('C'), "craftingCircuitTier04", Character.valueOf('H'), new ItemStack(this.mBlocks[1], 1, 21));
            GT_ModHandler.addCraftingRecipe(new ItemStack(this.mBlocks[1], 1, 19), "GBG", "CBC", "GBG", Character.valueOf('C'), "craftingCircuitTier04", Character.valueOf('G'), "plateElectrum", Character.valueOf('B'), "craftingConveyor");
            GT_ModHandler.addCraftingRecipe(new ItemStack(this.mBlocks[1], 1, 46), "CRC", "SMS", "CEC", Character.valueOf('M'), "craftingCircuitTier10", Character.valueOf('C'), "craftingCircuitTier05", Character.valueOf('R'), new ItemStack(this.mBlocks[1], 1, 39), Character.valueOf('S'), new ItemStack(this.mBlocks[1], 1, 23), Character.valueOf('E'), "crafting100kEUStore");
            GT_ModHandler.addCraftingRecipe(new ItemStack(this.mBlocks[1], 1, 19), "TTT", "TCT", "TTT", Character.valueOf('T'), new ItemStack(this.mBlocks[1], 1, 18), Character.valueOf('C'), "craftingCircuitTier04");
            GT_ModHandler.addShapelessCraftingRecipe(new ItemStack(this.mBlocks[1], 8, 18), new ItemStack(this.mBlocks[1], 1, 19));
        }
        if (this.mItemClearer) {
            GT_ModHandler.addCraftingRecipe(new ItemStack(this.mBlocks[1], 1, 24), "GBG", "PMH", "GCG", Character.valueOf('B'), "crafting10kEUStore", Character.valueOf('M'), "craftingRawMachineTier02", Character.valueOf('C'), "craftingCircuitTier02", Character.valueOf('G'), "plateElectrum", Character.valueOf('P'), new ItemStack(Block.pistonStickyBase, 1), Character.valueOf('H'), new ItemStack(this.mBlocks[1], 1, 18));
        }
        if (this.mRockBreaker) {
            GT_ModHandler.addCraftingRecipe(new ItemStack(this.mBlocks[1], 1, 22), "ODO", "CMC", "OAO", Character.valueOf('D'), "itemDiamond", Character.valueOf('A'), "craftingRawMachineTier02", Character.valueOf('C'), "craftingCircuitTier02", Character.valueOf('M'), new ItemStack(GT_ModHandler.getIC2Item("miningDrill", 1).getItem(), 1, -1), Character.valueOf('O'), "plateInvar");
            GT_ModHandler.addCraftingRecipe(new ItemStack(this.mBlocks[1], 1, 22), "ODO", "CMC", "OAO", Character.valueOf('D'), "itemDiamond", Character.valueOf('A'), "craftingRawMachineTier02", Character.valueOf('C'), "craftingCircuitTier02", Character.valueOf('M'), new ItemStack(GT_ModHandler.getIC2Item("miningDrill", 1).getItem(), 1, -1), Character.valueOf('O'), "plateAluminium");
        }
        if (this.mCropHarvestor) {
            GT_ModHandler.addCraftingRecipe(new ItemStack(this.mBlocks[1], 1, 26), "GHG", "DPM", "GCG", Character.valueOf('D'), "craftingDiamondBlade", Character.valueOf('M'), "craftingRawMachineTier02", Character.valueOf('C'), "craftingCircuitTier02", Character.valueOf('G'), "plateElectrum", Character.valueOf('P'), new ItemStack(Block.pistonStickyBase, 1), Character.valueOf('H'), this.mItemClearer ? new ItemStack(this.mBlocks[1], 1, 24) : new ItemStack(this.mBlocks[1], 1, 18));
        }
        if (this.mScrapboxinator) {
            GT_ModHandler.addCraftingRecipe(new ItemStack(this.mBlocks[1], 1, 27), "GHG", "DPM", "GCG", Character.valueOf('P'), new ItemStack(Block.pressurePlatePlanks, 1), Character.valueOf('M'), "craftingRawMachineTier02", Character.valueOf('C'), "craftingCircuitTier02", Character.valueOf('G'), "plateElectrum", Character.valueOf('D'), new ItemStack(Block.dispenser, 1), Character.valueOf('H'), this.mItemClearer ? new ItemStack(this.mBlocks[1], 1, 24) : new ItemStack(this.mBlocks[1], 1, 18));
        }
        if (this.mAdvSafe) {
            GT_ModHandler.addCraftingRecipe(new ItemStack(this.mBlocks[1], 1, 45), "C", "S", "B", Character.valueOf('C'), "craftingCircuitTier04", Character.valueOf('S'), GT_ModHandler.getIC2Item("personalSafe", 1), Character.valueOf('B'), new ItemStack(this.mBlocks[1], 1, 18));
        }
        if (this.mGrinder) {
            GT_ModHandler.addCraftingRecipe(new ItemStack(this.mBlocks[1], 1, 28), "EAP", "GGG", "ABA", Character.valueOf('B'), "craftingRawMachineTier02", Character.valueOf('A'), "craftingCircuitTier04", Character.valueOf('G'), "craftingGrinder", Character.valueOf('E'), new ItemStack(this.mBlocks[1], 1, 25), Character.valueOf('P'), GT_ModHandler.getIC2Item("pump", 1));
        }
        if (this.mDistillationTower) {
            GT_ModHandler.addCraftingRecipe(new ItemStack(this.mBlocks[1], 1, 44), "CAC", "PBP", "EAE", Character.valueOf('B'), "craftingRawMachineTier04", Character.valueOf('A'), "craftingCircuitTier07", Character.valueOf('C'), new ItemStack(this.mBlocks[1], 1, 11), Character.valueOf('E'), new ItemStack(this.mBlocks[1], 1, 25), Character.valueOf('P'), GT_ModHandler.getIC2Item("pump", 1));
        }
        if (this.mSawmill) {
            GT_ModHandler.addCraftingRecipe(new ItemStack(this.mBlocks[1], 1, 32), "PAP", "DDD", "ABA", Character.valueOf('B'), "craftingRawMachineTier02", Character.valueOf('A'), "craftingCircuitTier04", Character.valueOf('D'), "craftingDiamondBlade", Character.valueOf('P'), GT_ModHandler.getIC2Item("pump", 1));
        }
        GT_ModHandler.addCraftingRecipe(new ItemStack(this.mBlocks[1], 1, 33), "XXX", "X X", "CGC", Character.valueOf('X'), "plateRefinedIron", Character.valueOf('C'), "craftingCircuitTier02", Character.valueOf('G'), GT_ModHandler.getIC2Item("generator", 1));
        GT_ModHandler.addCraftingRecipe(new ItemStack(this.mBlocks[1], 1, 33), "XXX", "X X", "CGC", Character.valueOf('X'), "plateAluminium", Character.valueOf('C'), "craftingCircuitTier02", Character.valueOf('G'), GT_ModHandler.getIC2Item("generator", 1));
        GT_ModHandler.addCraftingRecipe(new ItemStack(this.mBlocks[1], 1, 34), "XCX", "WPW", "XCX", Character.valueOf('X'), "plateInvar", Character.valueOf('C'), "craftingCircuitTier04", Character.valueOf('W'), GT_ModHandler.getIC2Item("windMill", 1), Character.valueOf('P'), "glassReinforced");
        GT_ModHandler.addCraftingRecipe(new ItemStack(this.mBlocks[1], 1, 34), "XCX", "WPW", "XCX", Character.valueOf('X'), "plateAluminium", Character.valueOf('C'), "craftingCircuitTier04", Character.valueOf('W'), GT_ModHandler.getIC2Item("windMill", 1), Character.valueOf('P'), "glassReinforced");
        GT_ModHandler.addCraftingRecipe(new ItemStack(this.mBlocks[1], 1, 35), "XXX", "XPX", "CGC", Character.valueOf('X'), "plateInvar", Character.valueOf('C'), "craftingCircuitTier02", Character.valueOf('G'), GT_ModHandler.getIC2Item("geothermalGenerator", 1), Character.valueOf('P'), "glassReinforced");
        GT_ModHandler.addCraftingRecipe(new ItemStack(this.mBlocks[1], 1, 35), "XXX", "XPX", "CGC", Character.valueOf('X'), "plateAluminium", Character.valueOf('C'), "craftingCircuitTier02", Character.valueOf('G'), GT_ModHandler.getIC2Item("geothermalGenerator", 1), Character.valueOf('P'), "glassReinforced");
        GT_ModHandler.addCraftingRecipe(new ItemStack(this.mBlocks[1], 1, 36), "XXX", "XPX", "CGC", Character.valueOf('X'), "plateRefinedIron", Character.valueOf('C'), "craftingCircuitTier02", Character.valueOf('G'), GT_ModHandler.getIC2Item("generator", 1), Character.valueOf('P'), "glassReinforced");
        GT_ModHandler.addCraftingRecipe(new ItemStack(this.mBlocks[1], 1, 36), "XXX", "XPX", "CGC", Character.valueOf('X'), "plateAluminium", Character.valueOf('C'), "craftingCircuitTier02", Character.valueOf('G'), GT_ModHandler.getIC2Item("generator", 1), Character.valueOf('P'), "glassReinforced");
        GT_ModHandler.addCraftingRecipe(new ItemStack(this.mBlocks[1], 1, 37), "XXX", "XPX", "CGC", Character.valueOf('X'), "plateTungstenSteel", Character.valueOf('C'), "craftingCircuitTier07", Character.valueOf('G'), GT_ModHandler.getIC2Item("generator", 1), Character.valueOf('P'), GT_ModHandler.getIC2Item("hvTransformer", 1));
        if (this.mDragonEggSiphon) {
            GT_ModHandler.addCraftingRecipe(new ItemStack(this.mBlocks[1], 1, 40), "CTC", "ISI", "CLC", Character.valueOf('C'), "craftingCircuitTier07", Character.valueOf('L'), "crafting10kkEUStore", Character.valueOf('S'), new ItemStack(this.mBlocks[1], 1, 15), Character.valueOf('T'), "craftingTeleporter", Character.valueOf('I'), "plateIridiumAlloy");
        }
        GT_ModHandler.addCraftingRecipe(new ItemStack(this.mBlocks[1], 1, 42), "CTC", "PSP", "CLC", Character.valueOf('C'), "craftingCircuitTier04", Character.valueOf('L'), "crafting1kkEUStore", Character.valueOf('S'), new ItemStack(Block.beacon, 1, -1), Character.valueOf('T'), "craftingTeleporter", Character.valueOf('P'), GT_ModHandler.mTCResource == null ? "platePlatinum" : new ItemStack(GT_ModHandler.mTCResource.getItem(), 1, 2));
        if (this.mMagicAbsorber) {
            GT_ModHandler.addCraftingRecipe(new ItemStack(this.mBlocks[1], 1, 43), "CSC", "IBI", "CMC", Character.valueOf('C'), "craftingCircuitTier07", Character.valueOf('S'), "craftingSuperconductor", Character.valueOf('B'), new ItemStack(Block.beacon, 1, -1), Character.valueOf('M'), new ItemStack(this.mBlocks[1], 1, 42), Character.valueOf('I'), "plateIridiumAlloy");
        }
        GT_ModHandler.addCraftingRecipe(new ItemStack(this.mBlocks[1], 1, 29), "ACA", "CMC", "FCF", Character.valueOf('M'), "craftingRawMachineTier02", Character.valueOf('A'), "craftingCircuitTier02", Character.valueOf('C'), "craftingHeatingCoilTier00", Character.valueOf('F'), GT_ModHandler.getIC2Item("inductionFurnace", 1));
        GT_ModHandler.addCraftingRecipe(new ItemStack(this.mBlocks[1], 1, 31), "ABA", "DCD", "ABA", Character.valueOf('B'), "craftingRawMachineTier02", Character.valueOf('D'), "craftingCircuitTier02", Character.valueOf('C'), GT_ModHandler.getIC2Item("compressor", 1), Character.valueOf('A'), GT_ModHandler.getIC2Item("advancedAlloy", 1));
        GT_ModHandler.addCraftingRecipe(new ItemStack(this.mBlocks[1], 4, 70), "WWW", "A A", "WWW", Character.valueOf('W'), "plankWood", Character.valueOf('A'), "plateAluminium");
        GT_ModHandler.addCraftingRecipe(new ItemStack(this.mBlocks[1], 4, 71), "III", "A A", "III", Character.valueOf('I'), "plateRefinedIron", Character.valueOf('A'), "plateAluminium");
        GT_ModHandler.addShapelessCraftingRecipe(new ItemStack(this.mBlocks[1], 1, 71), new ItemStack(this.mBlocks[1], 1, 74));
        GT_ModHandler.addShapelessCraftingRecipe(new ItemStack(this.mBlocks[1], 1, 72), new ItemStack(this.mBlocks[1], 1, 71));
        GT_ModHandler.addShapelessCraftingRecipe(new ItemStack(this.mBlocks[1], 1, 73), new ItemStack(this.mBlocks[1], 1, 72));
        GT_ModHandler.addShapelessCraftingRecipe(new ItemStack(this.mBlocks[1], 1, 74), new ItemStack(this.mBlocks[1], 1, 73));
        if (this.mIridiumblock) {
            GT_ModHandler.addShapelessCraftingRecipe(new ItemStack(this.mBlocks[0], 1, 2), GT_ModHandler.getIC2Item("reinforcedStone", 1), "ingotIridium");
            GT_ModHandler.addShapelessCraftingRecipe(new ItemStack(this.mBlocks[0], 1, 2), GT_ModHandler.getIC2Item("reinforcedStone", 1), "plateIridium");
            GT_ModHandler.addShapelessCraftingRecipe(new ItemStack(this.mBlocks[4], 1, 8), GT_ModHandler.getIC2Item("reinforcedStone", 1), "plateTungstenSteel");
            GT_ModHandler.addShapelessCraftingRecipe(new ItemStack(this.mBlocks[4], 1, 9), new ItemStack(this.mBlocks[0], 1, 2), "plateTungstenSteel");
            GT_ModHandler.addShapelessCraftingRecipe(new ItemStack(this.mBlocks[4], 1, 9), new ItemStack(this.mBlocks[4], 1, 8), "plateIridium");
            GT_ModHandler.addShapelessCraftingRecipe(new ItemStack(this.mBlocks[4], 1, 9), new ItemStack(this.mBlocks[4], 1, 8), "ingotIridium");
        }
        if (this.mItems[16] != null && this.mItems[17] != null) {
            GT_Mod.addAssemblerRecipe(GT_ModHandler.getIC2Item("electronicCircuit", 1), GT_ModHandler.getIC2Item("frequencyTransmitter", 1), new ItemStack(this.mItems[17], 1, 0), 1600, 2);
            GT_Mod.addAssemblerRecipe(new ItemStack(this.mItems[16], 1, 0), null, GT_ModHandler.getIC2Item("electronicCircuit", 2), 1600, 2);
        }
        if (this.mDestructopack) {
            GT_ModHandler.addCraftingRecipe(new ItemStack(this.mItems[33], 1, 0), "ARA", "RLR", "ARA", Character.valueOf('A'), "craftingCircuitTier04", Character.valueOf('R'), "plateRefinedIron", Character.valueOf('L'), new ItemStack(Item.bucketLava, 1));
            GT_ModHandler.addCraftingRecipe(new ItemStack(this.mItems[33], 1, 0), "ARA", "RLR", "ARA", Character.valueOf('A'), "craftingCircuitTier04", Character.valueOf('R'), "plateAluminium", Character.valueOf('L'), new ItemStack(Item.bucketLava, 1));
        }
        if (this.mHeliumCoolant) {
            GT_ModHandler.addCraftingRecipe(new ItemStack(this.mItems[34], 1), " T ", "THT", " T ", Character.valueOf('H'), "molecule_1he", Character.valueOf('T'), "ingotTin");
            GT_ModHandler.addCraftingRecipe(new ItemStack(this.mItems[35], 1), "TTT", "HHH", "TTT", Character.valueOf('H'), new ItemStack(this.mItems[34], 1), Character.valueOf('T'), "ingotTin");
            GT_ModHandler.addCraftingRecipe(new ItemStack(this.mItems[36], 1), "THT", "TCT", "THT", Character.valueOf('H'), new ItemStack(this.mItems[35], 1), Character.valueOf('T'), "ingotTin", Character.valueOf('C'), "plateCopperDense");
            GT_ModHandler.addCraftingRecipe(GT_ModHandler.getIC2Item("overclockerUpgrade", 2), " H ", "WCW", "   ", Character.valueOf('H'), new ItemStack(this.mItems[34], 1), Character.valueOf('W'), "copperWire", Character.valueOf('C'), "craftingCircuitTier02");
        }
        if (this.mNaKCoolant) {
            GT_ModHandler.addCraftingRecipe(new ItemStack(this.mItems[60], 1), "TNT", "KCK", "TNT", Character.valueOf('N'), "molecule_1na", Character.valueOf('K'), "molecule_1k", Character.valueOf('T'), "ingotTin", Character.valueOf('C'), GT_ModHandler.getIC2Item("reactorCoolantSimple", 1));
            GT_ModHandler.addCraftingRecipe(new ItemStack(this.mItems[60], 1), "TKT", "NCN", "TKT", Character.valueOf('N'), "molecule_1na", Character.valueOf('K'), "molecule_1k", Character.valueOf('T'), "ingotTin", Character.valueOf('C'), GT_ModHandler.getIC2Item("reactorCoolantSimple", 1));
            GT_ModHandler.addCraftingRecipe(new ItemStack(this.mItems[61], 1), "TTT", "HHH", "TTT", Character.valueOf('H'), new ItemStack(this.mItems[60], 1), Character.valueOf('T'), "ingotTin");
            GT_ModHandler.addCraftingRecipe(new ItemStack(this.mItems[62], 1), "THT", "TCT", "THT", Character.valueOf('H'), new ItemStack(this.mItems[61], 1), Character.valueOf('T'), "ingotTin", Character.valueOf('C'), "plateCopperDense");
            GT_ModHandler.addCraftingRecipe(GT_ModHandler.getIC2Item("overclockerUpgrade", 2), " H ", "WCW", "   ", Character.valueOf('H'), new ItemStack(this.mItems[60], 1), Character.valueOf('W'), "copperWire", Character.valueOf('C'), "craftingCircuitTier02");
        }
        if (this.mNeutronReflector) {
            GT_ModHandler.addCraftingRecipe(new ItemStack(this.mItems[40], 1), "NNN", "NIN", "NNN", Character.valueOf('I'), "plateIridiumAlloy", Character.valueOf('N'), GT_ModHandler.getIC2Item("reactorReflectorThick", 1));
        }
        if (this.mLapotronpack) {
            GT_ModHandler.addCraftingRecipe(new ItemStack(this.mItems[45], 1), "CLC", "SPS", "CIC", Character.valueOf('L'), "crafting10kkEUStore", Character.valueOf('C'), "craftingCircuitTier07", Character.valueOf('I'), "plateIridiumAlloy", Character.valueOf('P'), "crafting300kEUPack", Character.valueOf('S'), "craftingSuperconductor");
        }
        if (this.mLithiumbattery) {
            GT_ModHandler.addCraftingRecipe(new ItemStack(this.mItems[56], 1), " C ", "ALA", "ALA", Character.valueOf('C'), GT_ModHandler.getIC2Item("doubleInsulatedGoldCableItem", 1), Character.valueOf('L'), "molecule_1li", Character.valueOf('A'), "plateAluminium");
        }
        if (this.mLithiumpack) {
            GT_ModHandler.addCraftingRecipe(new ItemStack(this.mItems[58], 1), "LCL", "LAL", "L L", Character.valueOf('L'), "craftingLiBattery", Character.valueOf('C'), "craftingCircuitTier04", Character.valueOf('A'), "plateAluminium");
        }
        if (this.mLightHelmet) {
            GT_ModHandler.addShapelessCraftingRecipe(new ItemStack(this.mItems[44], 1), GT_ModHandler.getIC2Item("solarHelmet", 1), GT_ModHandler.getIC2Item("luminator", 1));
        }
        if (this.mRockcutter) {
            GT_ModHandler.addCraftingRecipe(new ItemStack(this.mItems[46], 1), "DR ", "DR ", "DCB", Character.valueOf('B'), "crafting10kEUStore", Character.valueOf('C'), "craftingCircuitTier02", Character.valueOf('R'), "plateTitanium", Character.valueOf('D'), "dustDiamond");
        }
        if (this.mJackHammer) {
            GT_ModHandler.addCraftingRecipe(new ItemStack(this.mItems[39], 1), "RBR", " C ", " R ", Character.valueOf('B'), "crafting10kEUStore", Character.valueOf('C'), "craftingCircuitTier02", Character.valueOf('R'), "ingotRefinedIron");
            GT_ModHandler.addCraftingRecipe(new ItemStack(this.mItems[41], 1), "RBR", " C ", " R ", Character.valueOf('B'), "crafting10kEUStore", Character.valueOf('C'), "craftingCircuitTier04", Character.valueOf('R'), "ingotSteel");
            GT_ModHandler.addCraftingRecipe(new ItemStack(this.mItems[42], 1), "RBR", " C ", " D ", Character.valueOf('B'), "crafting100kEUStore", Character.valueOf('C'), "craftingCircuitTier04", Character.valueOf('R'), "ingotTitanium", Character.valueOf('D'), "dustDiamond");
        }
        if (this.mTeslaStaff) {
            GT_ModHandler.addCraftingRecipe(new ItemStack(this.mItems[47], 1), "LS ", "SI ", "  I", Character.valueOf('L'), "crafting10kkEUStore", Character.valueOf('S'), "craftingSuperconductor", Character.valueOf('I'), "plateIridiumAlloy");
        }
        if (this.mCloakingDevice) {
            GT_ModHandler.addCraftingRecipe(new ItemStack(this.mItems[38], 1), "CIC", "ILI", "CIC", Character.valueOf('L'), "crafting10kkEUStore", Character.valueOf('I'), "plateIridiumAlloy", Character.valueOf('C'), "ingotChrome");
        }
        GT_ModHandler.addShapelessCraftingRecipe(new ItemStack(this.mItems[31], 1), new ItemStack(Item.bowlEmpty, 1), new ItemStack(Item.flint, 1));
        GT_ModHandler.addShapelessCraftingRecipe(GT_ModHandler.getIC2Item("hydratedCoalDust", 1), new ItemStack(this.mItems[31], 1, -1), new ItemStack(Item.coal, 1));
        GT_ModHandler.addShapelessCraftingRecipe(GT_OreDictUnificator.get("dustGold", 1), new ItemStack(this.mItems[31], 1, -1), "ingotGold");
        GT_ModHandler.addShapelessCraftingRecipe(GT_OreDictUnificator.get("dustClay", 1), new ItemStack(this.mItems[31], 1, -1), new ItemStack(Block.blockClay, 1));
        GT_ModHandler.addShapelessCraftingRecipe(GT_OreDictUnificator.get("dustCopper", 1), new ItemStack(this.mItems[31], 1, -1), "ingotCopper");
        GT_ModHandler.addShapelessCraftingRecipe(GT_OreDictUnificator.get("dustTin", 1), new ItemStack(this.mItems[31], 1, -1), "ingotTin");
        GT_ModHandler.addShapelessCraftingRecipe(GT_OreDictUnificator.get("dustSilver", 1), new ItemStack(this.mItems[31], 1, -1), "ingotSilver");
        GT_ModHandler.addShapelessCraftingRecipe(GT_OreDictUnificator.get("dustElectrum", 1), new ItemStack(this.mItems[31], 1, -1), "ingotElectrum");
        GT_ModHandler.addShapelessCraftingRecipe(GT_OreDictUnificator.get("dustWheat", 1), new ItemStack(this.mItems[31], 1, -1), new ItemStack(Item.wheat, 1));
        GT_ModHandler.addCraftingRecipe(new ItemStack(this.mItems[30], 1), " R ", "SRS", "SSS", Character.valueOf('S'), new ItemStack(Block.stoneBrick, 1), Character.valueOf('R'), "ingotRefinedIron");
        GT_ModHandler.addShapelessCraftingRecipe(GT_ModHandler.getIC2Item("hydratedCoalDust", 1), new ItemStack(this.mItems[30], 1, -1), new ItemStack(Item.coal, 1));
        GT_ModHandler.addShapelessCraftingRecipe(GT_OreDictUnificator.get("dustGold", 1), new ItemStack(this.mItems[30], 1, -1), "ingotGold");
        GT_ModHandler.addShapelessCraftingRecipe(GT_OreDictUnificator.get("dustClay", 1), new ItemStack(this.mItems[30], 1, -1), new ItemStack(Block.blockClay, 1));
        GT_ModHandler.addShapelessCraftingRecipe(GT_OreDictUnificator.get("dustCopper", 1), new ItemStack(this.mItems[30], 1, -1), "ingotCopper");
        GT_ModHandler.addShapelessCraftingRecipe(GT_OreDictUnificator.get("dustTin", 1), new ItemStack(this.mItems[30], 1, -1), "ingotTin");
        GT_ModHandler.addShapelessCraftingRecipe(GT_OreDictUnificator.get("dustBronze", 1), new ItemStack(this.mItems[30], 1, -1), "ingotBronze");
        GT_ModHandler.addShapelessCraftingRecipe(GT_OreDictUnificator.get("dustSilver", 1), new ItemStack(this.mItems[30], 1, -1), "ingotSilver");
        GT_ModHandler.addShapelessCraftingRecipe(GT_OreDictUnificator.get("dustElectrum", 1), new ItemStack(this.mItems[30], 1, -1), "ingotElectrum");
        GT_ModHandler.addShapelessCraftingRecipe(GT_OreDictUnificator.get("dustBrass", 1), new ItemStack(this.mItems[30], 1, -1), "ingotBrass");
        GT_ModHandler.addShapelessCraftingRecipe(GT_OreDictUnificator.get("dustIron", 1), new ItemStack(this.mItems[30], 1, -1), "ingotIron");
        GT_ModHandler.addShapelessCraftingRecipe(GT_OreDictUnificator.get("dustIron", 1), new ItemStack(this.mItems[30], 1, -1), "ingotRefinedIron");
        GT_ModHandler.addShapelessCraftingRecipe(new ItemStack(Item.flint, 1), new ItemStack(this.mItems[30], 1, -1), new ItemStack(Block.gravel, 1));
        GT_ModHandler.addShapelessCraftingRecipe(GT_OreDictUnificator.get("dustWheat", 1), new ItemStack(this.mItems[30], 1, -1), new ItemStack(Item.wheat, 1));
        if (!this.mSteelBlastFurnace) {
            GT_ModHandler.addShapelessCraftingRecipe(GT_OreDictUnificator.get("dustSteel", 1), "dustIron", "dustCoal", "dustCoal");
            GT_ModHandler.addShapelessCraftingRecipe(GT_OreDictUnificator.get("dustSteel", 1), "dustRefinedIron", "dustCoal", "dustCoal");
        }
        GT_ModHandler.addShapelessCraftingRecipe(GT_OreDictUnificator.get("dustElectrum", 2), "dustSilver", "dustGold");
        GT_ModHandler.addShapelessCraftingRecipe(GT_OreDictUnificator.get("dustElectrum", 1), "dustSmallSilver", "dustSmallSilver", "dustSmallGold", "dustSmallGold");
        GT_ModHandler.addShapelessCraftingRecipe(GT_OreDictUnificator.get("dustBrass", 4), "dustCopper", "dustCopper", "dustCopper", "dustZinc");
        GT_ModHandler.addShapelessCraftingRecipe(GT_OreDictUnificator.get("dustBrass", 1), "dustSmallCopper", "dustSmallCopper", "dustSmallCopper", "dustSmallZinc");
        GT_ModHandler.addShapelessCraftingRecipe(GT_OreDictUnificator.get("dustBronze", 2), "dustCopper", "dustCopper", "dustCopper", "dustTin");
        GT_ModHandler.addShapelessCraftingRecipe(GT_OreDictUnificator.get("dustInvar", 3), "dustIron", "dustIron", "dustNickel");
        GT_ModHandler.addShapelessCraftingRecipe(GT_OreDictUnificator.get("dustInvar", 3), "dustRefinedIron", "dustRefinedIron", "dustNickel");
        GT_ModHandler.addShapelessCraftingRecipe(GT_MetaItem_SmallDust.instance.getStack(29, 3), "dustSmallIron", "dustSmallIron", "dustSmallNickel");
        GT_ModHandler.addShapelessCraftingRecipe(GT_MetaItem_SmallDust.instance.getStack(29, 3), "dustSmallRefinedIron", "dustSmallRefinedIron", "dustSmallNickel");
        GT_ModHandler.addShapelessCraftingRecipe(GT_MetaItem_SmallDust.instance.getStack(245, 2), "dustSmallCopper", "dustSmallCopper", "dustSmallCopper", "dustSmallTin");
        GT_ModHandler.addShapelessCraftingRecipe(new ItemStack(Item.gunpowder, 3), "dustCoal", "craftingSulfurToGunpowder", "craftingSaltpeterToGunpowder", "craftingSaltpeterToGunpowder");
        GT_ModHandler.addShapelessCraftingRecipe(new ItemStack(Item.gunpowder, 2), "dustCharcoal", "craftingSulfurToGunpowder", "craftingSaltpeterToGunpowder", "craftingSaltpeterToGunpowder");
        GT_ModHandler.addShapelessCraftingRecipe(GT_ModHandler.getIC2Item("fertilizer", 4), GT_ModHandler.getIC2Item("fertilizer", 1), "dustPhosphorus");
        GT_ModHandler.addShapelessCraftingRecipe(GT_ModHandler.getIC2Item("fertilizer", 2), GT_ModHandler.getIC2Item("fertilizer", 1), new ItemStack(Item.dyePowder, 1, 15));
        GT_ModHandler.addShapelessCraftingRecipe(GT_ModHandler.getIC2Item("fertilizer", 3), GT_ModHandler.getIC2Item("fertilizer", 1), "dustSulfur", "molecule_1ca");
        GT_ModHandler.addShapelessCraftingRecipe(GT_ModHandler.getIC2Item("fertilizer", 3), GT_ModHandler.getIC2Item("fertilizer", 1), "molecule_1s", "molecule_1ca");
        GT_ModHandler.addShapelessCraftingRecipe(GT_ModHandler.getIC2Item("fertilizer", 2), GT_ModHandler.getIC2Item("fertilizer", 1), "dustAsh", "dustAsh", "dustAsh");
        GT_ModHandler.addShapelessCraftingRecipe(GT_ModHandler.getIC2Item("fertilizer", 2), GT_ModHandler.getIC2Item("fertilizer", 1), "dustDarkAsh");
        GT_ModHandler.addShapelessCraftingRecipe(GT_MetaItem_Dust.instance.getStack(9, 10), "molecule_1k", "molecule_1k", "molecule_1n", "molecule_1n", "molecule_2o", "molecule_2o", "molecule_2o");
        GT_ModHandler.addShapelessCraftingRecipe(GT_MetaItem_Dust.instance.getStack(9, 5), "molecule_1k", "molecule_1n", "molecule_1o", "molecule_1o", "molecule_1o");
        GT_ModHandler.addShapelessCraftingRecipe(GT_ModHandler.getIC2Item("carbonFiber", 1), "molecule_1c", "molecule_1c", "molecule_1c", "molecule_1c", "molecule_1c", "molecule_1c", "molecule_1c", "molecule_1c", "molecule_1c");
        GT_ModHandler.addCraftingRecipe(GT_ModHandler.getIC2Item("suBattery", 32), "C", "Q", "R", Character.valueOf('Q'), "molecule_1hg", Character.valueOf('R'), "dustRedstone", Character.valueOf('C'), "copperWire");
        GT_ModHandler.addCraftingRecipe(GT_ModHandler.getIC2Item("suBattery", 32), "C", "R", "Q", Character.valueOf('Q'), "molecule_1hg", Character.valueOf('R'), "dustRedstone", Character.valueOf('C'), "copperWire");
        GT_ModHandler.addCraftingRecipe(GT_ModHandler.getIC2Item("suBattery", 32), "C", "S", "R", Character.valueOf('S'), "molecule_2h_1s_4o", Character.valueOf('R'), "dustLead", Character.valueOf('C'), "copperWire");
        GT_ModHandler.addCraftingRecipe(GT_ModHandler.getIC2Item("suBattery", 32), "C", "R", "S", Character.valueOf('S'), "molecule_2h_1s_4o", Character.valueOf('R'), "dustLead", Character.valueOf('C'), "copperWire");
        GT_ModHandler.addCraftingRecipe(GT_ModHandler.getIC2Item("reBattery", 2), " C ", "TLT", "TST", Character.valueOf('S'), "molecule_2h_1s_4o", Character.valueOf('L'), "dustLead", Character.valueOf('C'), "copperWire", Character.valueOf('T'), "plateTin");
        GT_ModHandler.addCraftingRecipe(GT_ModHandler.getIC2Item("reBattery", 2), " C ", "TST", "TLT", Character.valueOf('S'), "molecule_2h_1s_4o", Character.valueOf('L'), "dustLead", Character.valueOf('C'), "copperWire", Character.valueOf('T'), "plateTin");
        GT_ModHandler.addCraftingRecipe(GT_ModHandler.getIC2Item("glassFiberCableItem", 4), "GGG", "XDX", "GGG", Character.valueOf('G'), new ItemStack(Block.glass, 1, -1), Character.valueOf('X'), "dustRedstone", Character.valueOf('D'), "itemDiamond");
        GT_ModHandler.addCraftingRecipe(GT_ModHandler.getIC2Item("glassFiberCableItem", 6), "GGG", "XDX", "GGG", Character.valueOf('G'), new ItemStack(Block.glass, 1, -1), Character.valueOf('X'), "ingotSilver", Character.valueOf('D'), "itemDiamond");
        GT_ModHandler.addCraftingRecipe(GT_ModHandler.getIC2Item("glassFiberCableItem", 8), "GGG", "XDX", "GGG", Character.valueOf('G'), new ItemStack(Block.glass, 1, -1), Character.valueOf('X'), "ingotElectrum", Character.valueOf('D'), "itemDiamond");
        GT_ModHandler.addCraftingRecipe(GT_ModHandler.getIC2Item("electronicCircuit", 1), "CCC", "SRS", "CCC", Character.valueOf('C'), "copperWire", Character.valueOf('R'), "ingotRefinedIron", Character.valueOf('S'), "dustRedstone");
        GT_ModHandler.addCraftingRecipe(GT_ModHandler.getIC2Item("electronicCircuit", 1), "CSC", "CRC", "CSC", Character.valueOf('C'), "copperWire", Character.valueOf('R'), "ingotRefinedIron", Character.valueOf('S'), "dustRedstone");
        GT_ModHandler.addCraftingRecipe(GT_ModHandler.getIC2Item("advancedCircuit", 1), "SGS", "LCL", "SGS", Character.valueOf('C'), GT_ModHandler.getIC2Item("electronicCircuit", 1), Character.valueOf('S'), "dustRedstone", Character.valueOf('G'), "dustGlowstone", Character.valueOf('L'), "itemLazurite");
        GT_ModHandler.addCraftingRecipe(GT_ModHandler.getIC2Item("advancedCircuit", 1), "SLS", "GCG", "SLS", Character.valueOf('C'), GT_ModHandler.getIC2Item("electronicCircuit", 1), Character.valueOf('S'), "dustRedstone", Character.valueOf('G'), "dustGlowstone", Character.valueOf('L'), "itemLazurite");
        if (this.mEnergyRuby) {
            GT_ModHandler.addCraftingRecipe(GT_ModHandler.getIC2Item("energyCrystal", 1), "DDD", "DRD", "DDD", Character.valueOf('D'), "dustRedstone", Character.valueOf('R'), "gemRuby");
        }
        GT_ModHandler.addCraftingRecipe(GT_ModHandler.getIC2Item("lapotronCrystal", 1), "LCL", "LSL", "LCL", Character.valueOf('C'), "craftingCircuitTier02", Character.valueOf('S'), new ItemStack(GT_ModHandler.getIC2Item("energyCrystal", 1).getItem(), 1, -1), Character.valueOf('L'), "itemLazurite");
        GT_ModHandler.addCraftingRecipe(GT_ModHandler.getIC2Item("lapPack", 1), "LCL", "LBL", "L L", Character.valueOf('C'), "craftingCircuitTier04", Character.valueOf('B'), "crafting60kEUPack", Character.valueOf('L'), "chunkLazurite");
        if (this.mLapotronSapphire) {
            GT_ModHandler.addCraftingRecipe(GT_ModHandler.getIC2Item("lapotronCrystal", 1), "LCL", "LSL", "LCL", Character.valueOf('C'), "craftingCircuitTier02", Character.valueOf('S'), "gemSapphire", Character.valueOf('L'), "itemLazurite");
        }
        if (this.mHeliumLaser && GT_ModHandler.removeRecipe(GT_ModHandler.getIC2Item("miningLaser", 1))) {
            GT_ModHandler.addCraftingRecipe(GT_ModHandler.getIC2Item("miningLaser", 1), "RHE", "TTC", " AA", Character.valueOf('C'), "craftingCircuitTier04", Character.valueOf('H'), this.mHeliumCoolant ? new ItemStack(this.mItems[36], 1, 0) : (this.mNaKCoolant ? new ItemStack(this.mItems[62], 1, 0) : GT_ModHandler.getIC2Item("reactorCoolantSix", 1)), Character.valueOf('R'), "gemRuby", Character.valueOf('T'), "plateTitanium", Character.valueOf('E'), "crafting100kEUStore", Character.valueOf('A'), GT_ModHandler.getIC2Item("advancedAlloy", 1));
        }
        GT_ModHandler.addCraftingRecipe(GT_ModHandler.getIC2Item("luminator", 16), "RTR", "GHG", "GGG", Character.valueOf('H'), "molecule_1he", Character.valueOf('T'), "ingotTin", Character.valueOf('R'), "ingotRefinedIron", Character.valueOf('G'), new ItemStack(Block.glass, 1));
        GT_ModHandler.addCraftingRecipe(GT_ModHandler.getIC2Item("luminator", 16), "RTR", "GHG", "GGG", Character.valueOf('H'), "molecule_1hg", Character.valueOf('T'), "ingotTin", Character.valueOf('R'), "ingotRefinedIron", Character.valueOf('G'), new ItemStack(Block.glass, 1));
        GT_ModHandler.addCraftingRecipe(GT_ModHandler.getIC2Item("mfeUnit", 1), "GCG", "CMC", "GCG", Character.valueOf('C'), "crafting100kEUStore", Character.valueOf('G'), GT_ModHandler.getIC2Item("doubleInsulatedGoldCableItem", 1), Character.valueOf('M'), "craftingRawMachineTier01");
        GT_ModHandler.addShapelessCraftingRecipe(GT_ModHandler.getEmptyCell(1), GT_ModHandler.getIC2Item("airCell", 1));
        GT_ModHandler.addShapelessCraftingRecipe(GT_MetaItem_Material.instance.getStack(0, 8), GT_ModHandler.getIC2Item("coin", 1));
        GT_ModHandler.addShapelessCraftingRecipe(GT_ModHandler.getIC2Item("coin", 8), GT_MetaItem_Material.instance.getStack(1, 1));
        GT_ModHandler.addShapelessCraftingRecipe(GT_MetaItem_Material.instance.getStack(1, 8), GT_MetaItem_Material.instance.getStack(2, 1));
        GT_ModHandler.addShapelessCraftingRecipe(GT_MetaItem_Material.instance.getStack(2, 8), GT_MetaItem_Material.instance.getStack(3, 1));
        GT_ModHandler.addShapelessCraftingRecipe(GT_ModHandler.getIC2Item("coin", 1), GT_MetaItem_Material.instance.getStack(0, 1), GT_MetaItem_Material.instance.getStack(0, 1), GT_MetaItem_Material.instance.getStack(0, 1), GT_MetaItem_Material.instance.getStack(0, 1), GT_MetaItem_Material.instance.getStack(0, 1), GT_MetaItem_Material.instance.getStack(0, 1), GT_MetaItem_Material.instance.getStack(0, 1), GT_MetaItem_Material.instance.getStack(0, 1));
        GT_ModHandler.addShapelessCraftingRecipe(GT_MetaItem_Material.instance.getStack(1, 1), GT_ModHandler.getIC2Item("coin", 1), GT_ModHandler.getIC2Item("coin", 1), GT_ModHandler.getIC2Item("coin", 1), GT_ModHandler.getIC2Item("coin", 1), GT_ModHandler.getIC2Item("coin", 1), GT_ModHandler.getIC2Item("coin", 1), GT_ModHandler.getIC2Item("coin", 1), GT_ModHandler.getIC2Item("coin", 1));
        GT_ModHandler.addShapelessCraftingRecipe(GT_MetaItem_Material.instance.getStack(2, 1), GT_MetaItem_Material.instance.getStack(1, 1), GT_MetaItem_Material.instance.getStack(1, 1), GT_MetaItem_Material.instance.getStack(1, 1), GT_MetaItem_Material.instance.getStack(1, 1), GT_MetaItem_Material.instance.getStack(1, 1), GT_MetaItem_Material.instance.getStack(1, 1), GT_MetaItem_Material.instance.getStack(1, 1), GT_MetaItem_Material.instance.getStack(1, 1));
        GT_ModHandler.addShapelessCraftingRecipe(GT_MetaItem_Material.instance.getStack(3, 1), GT_MetaItem_Material.instance.getStack(2, 1), GT_MetaItem_Material.instance.getStack(2, 1), GT_MetaItem_Material.instance.getStack(2, 1), GT_MetaItem_Material.instance.getStack(2, 1), GT_MetaItem_Material.instance.getStack(2, 1), GT_MetaItem_Material.instance.getStack(2, 1), GT_MetaItem_Material.instance.getStack(2, 1), GT_MetaItem_Material.instance.getStack(2, 1));
        ItemStack tSulfur = GT_OreDictUnificator.get("dustSulfur", 1);
        ItemStack tRefIron = GT_OreDictUnificator.get("ingotRefinedIron", 1);
        ItemStack tFlint = new ItemStack(Item.flint, 1);
        GT_OreDictUnificator.get("ingotTin", 1);
        ItemStack tIrOre = GT_ModHandler.getIC2Item("iridiumOre", 1);
        ItemStack tReflector = GT_ModHandler.getIC2Item("reactorReflector", 1);
        ItemStack tDiamond = new ItemStack(Item.diamond, 1);
        ItemStack tGlowstoneDust = new ItemStack(Item.lightStoneDust, 1);
        ItemStack tMachine = GT_ModHandler.getIC2Item("machine", 1);
        ItemStack tAlloy = GT_ModHandler.getIC2Item("advancedAlloy", 1);
        ItemStack tAdvCircuit = GT_ModHandler.getIC2Item("advancedCircuit", 1);
        ItemStack tIrPlate = GT_ModHandler.getIC2Item("iridiumPlate", 1);
        GT_ModHandler.getIC2Item("lapotronCrystal", 1);
        ItemStack tMat = GT_ModHandler.getIC2Item("matter", 1);
        ItemStack tCoaldust = GT_OreDictUnificator.get("dustCoal", 1);
        ItemStack tGlassPane = new ItemStack(Block.thinGlass, 1);
        ItemStack tGlass = new ItemStack(Block.glass, 1);
        ItemStack tCircuit = GT_ModHandler.getIC2Item("electronicCircuit", 1);
        ItemStack tGenerator = GT_ModHandler.getIC2Item("generator", 1);
        GT_ModHandler.removeRecipe(new ItemStack[]{tSulfur, tSulfur, tSulfur, tSulfur, new ItemStack(Item.coal, 1, 0), tSulfur, tSulfur, tSulfur, tSulfur});
        GT_ModHandler.removeRecipe(new ItemStack[]{tSulfur, tSulfur, tSulfur, tSulfur, new ItemStack(Item.coal, 1, 1), tSulfur, tSulfur, tSulfur, tSulfur});
        GT_ModHandler.removeRecipe(new ItemStack[]{tIrOre, tAlloy, tIrOre, tAlloy, GT_ModHandler.getIC2Item("industrialDiamond", 1), tAlloy, tIrOre, tAlloy, tIrOre});
        tStack = new ItemStack(Item.seeds, 1);
        GT_ModHandler.removeRecipe(new ItemStack[]{tStack, tStack, tStack, tStack, null, tStack, tStack, tStack, tStack});
        GT_Log.out.println("GT_Mod: Applying harder Recipes for Electric Tools and several Blocks.");
        if (null != GT_ModHandler.removeRecipe(new ItemStack[]{tIrOre, tAlloy, tIrOre, tAlloy, tDiamond, tAlloy, tIrOre, tAlloy, tIrOre})) {
            GT_ModHandler.addRollingMachineRecipe(this.mHardIridiumPlate ? GT_MetaItem_Material.instance.getStack(4, 1) : tIrPlate.copy().splitStack(1), new Object[]{"IAI", "ADA", "IAI", Character.valueOf('D'), "dustDiamond", Character.valueOf('A'), tAlloy, Character.valueOf('I'), "ingotIridium"});
            GT_ModHandler.addRollingMachineRecipe(this.mHardIridiumPlate ? GT_MetaItem_Material.instance.getStack(4, 1) : tIrPlate.copy().splitStack(1), new Object[]{"IAI", "ADA", "IAI", Character.valueOf('D'), "dustDiamond", Character.valueOf('A'), tAlloy, Character.valueOf('I'), "ingotIridium"});
        }
        if (this.mBlockBreakerNerf && null != (tStack = GT_ModHandler.removeRecipe(new ItemStack[]{new ItemStack(Block.cobblestone, 1), new ItemStack(Item.pickaxeSteel, 1), new ItemStack(Block.cobblestone, 1), new ItemStack(Block.cobblestone, 1), new ItemStack(Block.pistonBase, 1), new ItemStack(Block.cobblestone, 1), new ItemStack(Block.cobblestone, 1), new ItemStack(Item.redstone, 1), new ItemStack(Block.cobblestone, 1)}))) {
            GT_ModHandler.addCraftingRecipe(tStack, "RGR", "RPR", "RCR", Character.valueOf('G'), "craftingGrinder", Character.valueOf('C'), "craftingCircuitTier04", Character.valueOf('R'), "plateRefinedIron", Character.valueOf('P'), new ItemStack(Block.pistonBase, 1));
            GT_ModHandler.addCraftingRecipe(tStack, "RGR", "RPR", "RCR", Character.valueOf('G'), "craftingGrinder", Character.valueOf('C'), "craftingCircuitTier04", Character.valueOf('R'), "plateAluminium", Character.valueOf('P'), new ItemStack(Block.pistonBase, 1));
        }
        if (this.mBeryliumReflector && null != GT_ModHandler.removeRecipe(new ItemStack[]{null, tReflector, null, tReflector, GT_ModHandler.getIC2Item("denseCopperPlate", 1), tReflector, null, tReflector, null})) {
            GT_ModHandler.addCraftingRecipe(GT_ModHandler.getIC2Item("reactorReflectorThick", 1), " N ", "NBN", " N ", Character.valueOf('B'), "molecule_1be", Character.valueOf('N'), tReflector);
        }
        if (this.mBetterMaceratorRecipe && null != GT_ModHandler.removeRecipe(new ItemStack[]{tFlint, tFlint, tFlint, new ItemStack(Block.cobblestone, 1), tMachine, new ItemStack(Block.cobblestone, 1), null, tCircuit, null})) {
            GT_ModHandler.addCraftingRecipe(GT_ModHandler.getIC2Item("macerator", 1), "FDF", "DMD", "FAF", Character.valueOf('F'), tFlint, Character.valueOf('A'), "craftingCircuitTier04", Character.valueOf('M'), "craftingRawMachineTier01", Character.valueOf('D'), "itemDiamond");
            GT_ModHandler.addCraftingRecipe(GT_ModHandler.getIC2Item("macerator", 1), "FDF", "AMA", "FAF", Character.valueOf('F'), tFlint, Character.valueOf('A'), "craftingCircuitTier02", Character.valueOf('M'), "craftingRawMachineTier01", Character.valueOf('D'), "craftingGrinder");
        }
        if (this.mBetterWindRecipe) {
            GT_ModHandler.removeRecipe(new ItemStack[]{new ItemStack(Item.ingotIron, 1), null, new ItemStack(Item.ingotIron, 1), null, tGenerator, null, new ItemStack(Item.ingotIron, 1), null, new ItemStack(Item.ingotIron, 1)});
        }
        if (this.mBetterWatermillRecipe) {
            GT_ModHandler.removeRecipe(new ItemStack[]{new ItemStack(Item.stick, 1), new ItemStack(Block.planks, 1), new ItemStack(Item.stick, 1), new ItemStack(Block.planks, 1), tGenerator, new ItemStack(Block.planks, 1), new ItemStack(Item.stick, 1), new ItemStack(Block.planks, 1), new ItemStack(Item.stick, 1)});
        }
        if (this.mBetterSolarRecipe && null != GT_ModHandler.removeRecipe(new ItemStack[]{tCoaldust, tGlass, tCoaldust, tGlass, tCoaldust, tGlass, tCircuit, tGenerator, tCircuit})) {
            GT_ModHandler.addCraftingRecipe(GT_ModHandler.getIC2Item("solarPanel", 1), "GGG", "SPS", "CHC", Character.valueOf('C'), "craftingCircuitTier02", Character.valueOf('G'), tGlassPane, Character.valueOf('H'), tGenerator, Character.valueOf('P'), GT_ModHandler.getIC2Item("carbonPlate", 1), Character.valueOf('S'), "plateSilicon");
        }
        if (this.mBetterNukeRecipe && null != GT_ModHandler.removeRecipe(new ItemStack[]{new ItemStack(Item.gunpowder, 1), GT_OreDictUnificator.get("ingotUranium", 1), new ItemStack(Item.gunpowder, 1), GT_OreDictUnificator.get("ingotUranium", 1), new ItemStack(Item.gunpowder, 1), GT_OreDictUnificator.get("ingotUranium", 1), new ItemStack(Item.gunpowder, 1), GT_OreDictUnificator.get("ingotUranium", 1), new ItemStack(Item.gunpowder, 1)})) {
            GT_ModHandler.addCraftingRecipe(GT_ModHandler.getIC2Item("nuke", 1), "CRC", "NPN", "CRC", Character.valueOf('C'), "craftingCircuitTier04", Character.valueOf('R'), GT_ModHandler.getIC2Item("reEnrichedUraniumCell", 1), Character.valueOf('P'), "ingotPlutonium", Character.valueOf('N'), GT_ModHandler.getIC2Item("reactorReflectorThick", 1));
        }
        if (this.mExpensiveNanosaber && null != GT_ModHandler.removeRecipe(new ItemStack[]{tGlowstoneDust, tAlloy, null, tGlowstoneDust, tAlloy, null, GT_ModHandler.getIC2Item("carbonPlate", 1), GT_ModHandler.getIC2Item("energyCrystal", 1), GT_ModHandler.getIC2Item("carbonPlate", 1)})) {
            GT_ModHandler.addCraftingRecipe(GT_ModHandler.getIC2Item("nanoSaber", 1), "PI ", "PI ", "CLC", Character.valueOf('L'), "crafting1kkEUStore", Character.valueOf('I'), "plateIridiumAlloy", Character.valueOf('P'), "platePlatinum", Character.valueOf('C'), "craftingCircuitTier07");
        }
        if (this.mBetterQuarryRecipe && GT_ModHandler.mBCDiamondGear != null) {
            GT_ModHandler.addCraftingRecipe(GT_ModHandler.removeRecipe(new ItemStack[]{GT_ModHandler.mBCIronGear, new ItemStack(Item.redstone, 1), GT_ModHandler.mBCIronGear, GT_ModHandler.mBCGoldGear, GT_ModHandler.mBCIronGear, GT_ModHandler.mBCGoldGear, GT_ModHandler.mBCDiamondGear, new ItemStack(Item.pickaxeDiamond, 1), GT_ModHandler.mBCDiamondGear}), "ICI", "GIG", "DPD", Character.valueOf('C'), "craftingCircuitTier04", Character.valueOf('D'), GT_ModHandler.mBCDiamondGear, Character.valueOf('G'), GT_ModHandler.mBCGoldGear, Character.valueOf('I'), GT_ModHandler.mBCIronGear, Character.valueOf('P'), new ItemStack(GT_ModHandler.getIC2Item("diamondDrill", 1).getItem(), 1, -1));
        }
        if (null != GT_ModHandler.removeRecipe(new ItemStack[]{null, tDiamond, null, tDiamond, GT_ModHandler.getIC2Item("miningDrill", 1), tDiamond, null, null, null})) {
            if (this.mDiamondDrillTitanium) {
                GT_ModHandler.addCraftingRecipe(GT_ModHandler.getIC2Item("diamondDrill", 1), " D ", "DMD", "TAT", Character.valueOf('M'), GT_ModHandler.getIC2Item("miningDrill", 1), Character.valueOf('D'), "itemDiamond", Character.valueOf('T'), "plateTitanium", Character.valueOf('A'), tAdvCircuit);
            } else {
                GT_ModHandler.addCraftingRecipe(GT_ModHandler.getIC2Item("diamondDrill", 1), " D ", "DMD", "   ", Character.valueOf('M'), GT_ModHandler.getIC2Item("miningDrill", 1), Character.valueOf('D'), "itemDiamond");
            }
        }
        if (null != GT_ModHandler.removeRecipe(new ItemStack[]{null, tRefIron, null, tRefIron, tCircuit, tRefIron, tRefIron, GT_ModHandler.getIC2Item("reBattery", 1), tRefIron}) && null != GT_ModHandler.removeRecipe(new ItemStack[]{null, tRefIron, null, tRefIron, tCircuit, tRefIron, tRefIron, GT_ModHandler.getIC2Item("chargedReBattery", 1), tRefIron})) {
            GT_ModHandler.addCraftingRecipe(GT_ModHandler.getIC2Item("miningDrill", 1), " S ", "SCS", "SBS", Character.valueOf('C'), "craftingCircuitTier02", Character.valueOf('B'), "crafting10kEUStore", Character.valueOf('S'), this.mSteelETools ? "plateSteel" : "plateRefinedIron");
        }
        if (null != GT_ModHandler.removeRecipe(new ItemStack[]{null, tRefIron, tRefIron, tRefIron, tCircuit, tRefIron, GT_ModHandler.getIC2Item("reBattery", 1), tRefIron, null}) && null != GT_ModHandler.removeRecipe(new ItemStack[]{null, tRefIron, tRefIron, tRefIron, tCircuit, tRefIron, GT_ModHandler.getIC2Item("chargedReBattery", 1), tRefIron, null})) {
            GT_ModHandler.addCraftingRecipe(GT_ModHandler.getIC2Item("chainsaw", 1), " SS", "SCS", "BS ", Character.valueOf('C'), "craftingCircuitTier02", Character.valueOf('B'), "crafting10kEUStore", Character.valueOf('S'), this.mSteelETools ? "plateSteel" : "plateRefinedIron");
        }
        if (null != GT_ModHandler.removeRecipe(new ItemStack[]{tRefIron, tRefIron, null, null, tCircuit, null, null, GT_ModHandler.getIC2Item("reBattery", 1), null}) && null != GT_ModHandler.removeRecipe(new ItemStack[]{tRefIron, tRefIron, null, null, tCircuit, null, null, GT_ModHandler.getIC2Item("chargedReBattery", 1), null})) {
            GT_ModHandler.addCraftingRecipe(GT_ModHandler.getIC2Item("electricHoe", 1), "SS ", " C ", " B ", Character.valueOf('C'), "craftingCircuitTier02", Character.valueOf('B'), "crafting10kEUStore", Character.valueOf('S'), this.mSteelETools ? "plateSteel" : "plateRefinedIron");
        }
        if (null != GT_ModHandler.removeRecipe(new ItemStack[]{null, null, GT_ModHandler.getIC2Item("treetap", 1), null, tCircuit, null, GT_ModHandler.getIC2Item("reBattery", 1), null, null}) && null != GT_ModHandler.removeRecipe(new ItemStack[]{null, null, GT_ModHandler.getIC2Item("treetap", 1), null, tCircuit, null, GT_ModHandler.getIC2Item("chargedReBattery", 1), null, null})) {
            GT_ModHandler.addCraftingRecipe(GT_ModHandler.getIC2Item("electricTreetap", 1), " B ", "SCS", "S  ", Character.valueOf('C'), "craftingCircuitTier02", Character.valueOf('B'), "crafting10kEUStore", Character.valueOf('S'), this.mSteelETools ? "plateSteel" : "plateRefinedIron");
        }
        if (null != GT_ModHandler.removeRecipe(new ItemStack[]{null, null, GT_ModHandler.getIC2Item("wrench", 1), null, tCircuit, null, GT_ModHandler.getIC2Item("reBattery", 1), null, null}) && null != GT_ModHandler.removeRecipe(new ItemStack[]{null, null, GT_ModHandler.getIC2Item("wrench", 1), null, tCircuit, null, GT_ModHandler.getIC2Item("chargedReBattery", 1), null, null})) {
            GT_ModHandler.addCraftingRecipe(GT_ModHandler.getIC2Item("electricWrench", 1), "S S", "SCS", " B ", Character.valueOf('C'), "craftingCircuitTier02", Character.valueOf('B'), "crafting10kEUStore", Character.valueOf('S'), this.mSteelETools ? "plateSteel" : "plateRefinedIron");
        }
        GT_Log.out.println("GT_Mod: Removing Q-Armor Recipes if configured.");
        if (!this.mQSuithelmet) {
            GT_ModHandler.removeRecipe(GT_ModHandler.getIC2Item("quantumHelmet", 1));
        }
        if (!this.mQSuitplate) {
            GT_ModHandler.removeRecipe(GT_ModHandler.getIC2Item("quantumBodyarmor", 1));
        }
        if (!this.mQSuitpants) {
            GT_ModHandler.removeRecipe(GT_ModHandler.getIC2Item("quantumLeggings", 1));
        }
        if (!this.mQSuitshoes) {
            GT_ModHandler.removeRecipe(GT_ModHandler.getIC2Item("quantumBoots", 1));
        }
        GT_Log.out.println("GT_Mod: Removing Mass Fabricator Recipe if configured.");
        if (!this.mMassfabricator) {
            GT_ModHandler.removeRecipe(GT_ModHandler.getIC2Item("massFabricator", 1));
        }
        GT_Log.out.println("GT_Mod: Adding/Removing/Overloading UUM Recipes.");
        GT_ModHandler.addUUMRecipe(GT_OreDictUnificator.get("gemDiamond", 1), "UUU", "UUU", "UUU", true, Character.valueOf('U'), GT_ModHandler.getIC2Item("matter", 1));
        GT_ModHandler.addUUMRecipe(GT_OreDictUnificator.get("gemEmerald", 2), "UUU", "UUU", " U ", true, Character.valueOf('U'), GT_ModHandler.getIC2Item("matter", 1));
        GT_ModHandler.addUUMRecipe(GT_OreDictUnificator.get("gemRuby", 2), " UU", "UUU", "UU ", true, Character.valueOf('U'), GT_ModHandler.getIC2Item("matter", 1));
        GT_ModHandler.addUUMRecipe(GT_OreDictUnificator.get("gemSapphire", 2), "UU ", "UUU", " UU", true, Character.valueOf('U'), GT_ModHandler.getIC2Item("matter", 1));
        GT_ModHandler.addUUMRecipe(GT_OreDictUnificator.get("gemGreenSapphire", 2), " UU", "UUU", " UU", true, Character.valueOf('U'), GT_ModHandler.getIC2Item("matter", 1));
        GT_ModHandler.addUUMRecipe(GT_OreDictUnificator.get("gemOlivine", 2), "UU ", "UUU", "UU ", true, Character.valueOf('U'), GT_ModHandler.getIC2Item("matter", 1));
        GT_ModHandler.addUUMRecipe(GT_OreDictUnificator.get("dustIron", 4), "U U", " U ", "U U", true, Character.valueOf('U'), GT_ModHandler.getIC2Item("matter", 1));
        GT_ModHandler.addUUMRecipe(GT_OreDictUnificator.get("dustGold", 2), " U ", "UUU", " U ", true, Character.valueOf('U'), GT_ModHandler.getIC2Item("matter", 1));
        GT_ModHandler.addUUMRecipe(GT_OreDictUnificator.get("dustTin", 10), "   ", "U U", "  U", true, Character.valueOf('U'), GT_ModHandler.getIC2Item("matter", 1));
        GT_ModHandler.addUUMRecipe(GT_OreDictUnificator.get("dustCopper", 10), "  U", "U U", "   ", true, Character.valueOf('U'), GT_ModHandler.getIC2Item("matter", 1));
        GT_ModHandler.addUUMRecipe(GT_OreDictUnificator.get("dustZinc", 10), "   ", "U U", "U  ", true, Character.valueOf('U'), GT_ModHandler.getIC2Item("matter", 1));
        GT_ModHandler.addUUMRecipe(GT_OreDictUnificator.get("dustNickel", 10), "U  ", "U U", "   ", true, Character.valueOf('U'), GT_ModHandler.getIC2Item("matter", 1));
        GT_ModHandler.addUUMRecipe(GT_OreDictUnificator.get("dustLead", 14), "UUU", "UUU", "U  ", true, Character.valueOf('U'), GT_ModHandler.getIC2Item("matter", 1));
        GT_ModHandler.addUUMRecipe(GT_OreDictUnificator.get("dustSilver", 14), " U ", "UUU", "UUU", true, Character.valueOf('U'), GT_ModHandler.getIC2Item("matter", 1));
        GT_ModHandler.addUUMRecipe(GT_OreDictUnificator.get("dustPlatinum", 1), "  U", "UUU", "UUU", true, Character.valueOf('U'), GT_ModHandler.getIC2Item("matter", 1));
        GT_ModHandler.addUUMRecipe(GT_OreDictUnificator.get("dustTungsten", 6), "U  ", "UUU", "UUU", true, Character.valueOf('U'), GT_ModHandler.getIC2Item("matter", 1));
        GT_ModHandler.addUUMRecipe(GT_OreDictUnificator.get("dustNikolite", 12), "UUU", " U ", "   ", true, Character.valueOf('U'), GT_ModHandler.getIC2Item("matter", 1));
        GT_ModHandler.addUUMRecipe(GT_OreDictUnificator.get("dustRedstone", 24), "   ", " U ", "UUU", true, Character.valueOf('U'), GT_ModHandler.getIC2Item("matter", 1));
        GT_ModHandler.addUUMRecipe(GT_OreDictUnificator.get("dustGlowstone", 32), " U ", "U U", "UUU", true, Character.valueOf('U'), GT_ModHandler.getIC2Item("matter", 1));
        GT_ModHandler.addUUMRecipe(GT_OreDictUnificator.get("dustSmallOsmium", 1), "U U", "UUU", "U U", true, Character.valueOf('U'), GT_ModHandler.getIC2Item("matter", 1));
        GT_ModHandler.addUUMRecipe(GT_OreDictUnificator.get("dustTitanium", 2), "UUU", " U ", " U ", true, Character.valueOf('U'), GT_ModHandler.getIC2Item("matter", 1));
        GT_ModHandler.addUUMRecipe(GT_OreDictUnificator.get("dustAluminium", 16), " U ", " U ", "UUU", true, Character.valueOf('U'), GT_ModHandler.getIC2Item("matter", 1));
        GT_ModHandler.addUUMRecipe(new ItemStack(Item.coal, 8), "  U", "U  ", "  U", true, Character.valueOf('U'), GT_ModHandler.getIC2Item("matter", 1));
        GT_ModHandler.addUUMRecipe(new ItemStack(Item.enderPearl, 1), "UUU", "U U", "UUU", true, Character.valueOf('U'), GT_ModHandler.getIC2Item("matter", 1));
        GT_ModHandler.addUUMRecipe(new ItemStack(Item.blazeRod, 4), "U U", "UU ", "U U", true, Character.valueOf('U'), GT_ModHandler.getIC2Item("matter", 1));
        GT_ModHandler.addUUMRecipe(new ItemStack(Block.waterlily, 32), "U U", "UUU", " U ", true, Character.valueOf('U'), GT_ModHandler.getIC2Item("matter", 1));
        GT_ModHandler.addUUMRecipe(new ItemStack(Item.leather, 32), "U U", " U ", "UUU", true, Character.valueOf('U'), GT_ModHandler.getIC2Item("matter", 1));
        GT_ModHandler.addUUMRecipe(new ItemStack(Item.silk, 32), "U U", "   ", " U ", true, Character.valueOf('U'), GT_ModHandler.getIC2Item("matter", 1));
        GT_ModHandler.addUUMRecipe(new ItemStack(Block.obsidian, 12), "U U", "U U", "   ", true, Character.valueOf('U'), GT_ModHandler.getIC2Item("matter", 1));
        GT_ModHandler.addUUMRecipe(new ItemStack(Block.netherrack, 16), "  U", " U ", "U  ", true, Character.valueOf('U'), GT_ModHandler.getIC2Item("matter", 1));
        GT_ModHandler.addUUMRecipe(new ItemStack(Block.waterStill, 1), "   ", " U ", " U ", true, Character.valueOf('U'), GT_ModHandler.getIC2Item("matter", 1));
        GT_ModHandler.addUUMRecipe(new ItemStack(Block.lavaStill, 1), " U ", " U ", " U ", true, Character.valueOf('U'), GT_ModHandler.getIC2Item("matter", 1));
        GT_ModHandler.addUUMRecipe(new ItemStack(Block.stone, 16), "   ", " U ", "   ", true, Character.valueOf('U'), GT_ModHandler.getIC2Item("matter", 1));
        GT_ModHandler.addUUMRecipe(new ItemStack(Block.wood, 8, 0), " U ", "   ", "   ", true, Character.valueOf('U'), GT_ModHandler.getIC2Item("matter", 1));
        GT_ModHandler.addUUMRecipe(new ItemStack(Block.wood, 8, 1), "U  ", "   ", "   ", true, Character.valueOf('U'), GT_ModHandler.getIC2Item("matter", 1));
        GT_ModHandler.addUUMRecipe(new ItemStack(Block.wood, 8, 2), "  U", "   ", "   ", true, Character.valueOf('U'), GT_ModHandler.getIC2Item("matter", 1));
        GT_ModHandler.addUUMRecipe(new ItemStack(Block.wood, 8, 3), "   ", "U  ", "   ", true, Character.valueOf('U'), GT_ModHandler.getIC2Item("matter", 1));
        GT_ModHandler.addUUMRecipe(new ItemStack(Block.glass, 32), " U ", "U U", " U ", true, Character.valueOf('U'), GT_ModHandler.getIC2Item("matter", 1));
        GT_ModHandler.addUUMRecipe(new ItemStack((Block)Block.grass, 16), "   ", "U  ", "U  ", true, Character.valueOf('U'), GT_ModHandler.getIC2Item("matter", 1));
        GT_ModHandler.addUUMRecipe(new ItemStack(Block.sandStone, 16), "   ", "  U", " U ", true, Character.valueOf('U'), GT_ModHandler.getIC2Item("matter", 1));
        GT_ModHandler.addUUMRecipe(new ItemStack(Block.cobblestoneMossy, 16), "   ", " U ", "U U", true, Character.valueOf('U'), GT_ModHandler.getIC2Item("matter", 1));
        GT_ModHandler.addUUMRecipe(new ItemStack(Block.blockSnow, 16), "U U", "   ", "   ", true, Character.valueOf('U'), GT_ModHandler.getIC2Item("matter", 1));
        GT_ModHandler.addUUMRecipe(new ItemStack(Block.cactus, 48), " U ", "UUU", "U U", true, Character.valueOf('U'), GT_ModHandler.getIC2Item("matter", 1));
        GT_ModHandler.addUUMRecipe(new ItemStack(Block.vine, 24), "U  ", "U  ", "U  ", true, Character.valueOf('U'), GT_ModHandler.getIC2Item("matter", 1));
        GT_ModHandler.addUUMRecipe(new ItemStack(Item.dyePowder, 9, 4), " U ", " U ", " UU", true, Character.valueOf('U'), GT_ModHandler.getIC2Item("matter", 1));
        GT_ModHandler.addUUMRecipe(new ItemStack(Item.feather, 32), " U ", " U ", "U U", true, Character.valueOf('U'), GT_ModHandler.getIC2Item("matter", 1));
        GT_ModHandler.addUUMRecipe(new ItemStack(Item.gunpowder, 15), "UUU", "U  ", "UUU", true, Character.valueOf('U'), GT_ModHandler.getIC2Item("matter", 1));
        GT_ModHandler.addUUMRecipe(new ItemStack(Item.clay, 48), "UU ", "U  ", "UU ", true, Character.valueOf('U'), GT_ModHandler.getIC2Item("matter", 1));
        GT_ModHandler.addUUMRecipe(new ItemStack(Item.dyePowder, 32, 3), "UU ", "  U", "UU ", true, Character.valueOf('U'), GT_ModHandler.getIC2Item("matter", 1));
        GT_ModHandler.addUUMRecipe(new ItemStack(Item.dyePowder, 48, 0), " UU", " UU", " U ", true, Character.valueOf('U'), GT_ModHandler.getIC2Item("matter", 1));
        GT_ModHandler.addUUMRecipe(new ItemStack(Item.reed, 48), "U U", "U U", "U U", true, Character.valueOf('U'), GT_ModHandler.getIC2Item("matter", 1));
        GT_ModHandler.addUUMRecipe(new ItemStack(Item.flint, 32), " U ", "UU ", "UU ", true, Character.valueOf('U'), GT_ModHandler.getIC2Item("matter", 1));
        GT_ModHandler.addUUMRecipe(new ItemStack(Item.bone, 32), "U  ", "UU ", "U  ", true, Character.valueOf('U'), GT_ModHandler.getIC2Item("matter", 1));
        GT_ModHandler.addUUMRecipe(new ItemStack(Block.stoneBrick, 48, 3), "UU ", "UU ", "U  ", true, Character.valueOf('U'), GT_ModHandler.getIC2Item("matter", 1));
        GT_ModHandler.addUUMRecipe(new ItemStack((Block)Block.mycelium, 24), "   ", "U U", "UUU", true, Character.valueOf('U'), GT_ModHandler.getIC2Item("matter", 1));
        GT_ModHandler.addUUMRecipe(GT_ModHandler.getIC2Item("resin", 21), "U U", "   ", "U U", true, Character.valueOf('U'), GT_ModHandler.getIC2Item("matter", 1));
        GT_ModHandler.addUUMRecipe(GT_ModHandler.getIC2Item("iridiumOre", 1), "UUU", " U ", "UUU", true, Character.valueOf('U'), GT_ModHandler.getIC2Item("matter", 1));
        GT_ModHandler.removeRecipe(new ItemStack[]{null, null, null, null, null, null, tMat, tMat, tMat});
        if (this.mMatterfabricator && !this.mMassfabricator && GT_TileEntity_Matterfabricator.sMatterFabricationRate >= 10000000) {
            GT_ModHandler.addUUMRecipe(GT_OreDictUnificator.get("dustPlutonium", 1), "U", "R", true, Character.valueOf('R'), "dustUranium", Character.valueOf('U'), GT_ModHandler.getIC2Item("matter", 1));
        } else {
            GT_ModHandler.addUUMRecipe(GT_OreDictUnificator.get("dustPlutonium", 1), "UUU", "URU", "UUU", true, Character.valueOf('R'), "dustUranium", Character.valueOf('U'), GT_ModHandler.getIC2Item("matter", 1));
        }
        GT_Log.out.println("GT_Mod: Adding all the Reverse Recipes for the Furnace/Macerator/Sawmill.");
        GT_Utility.applyUsagesForMaterials(GT_ModHandler.mRuby == null ? GT_OreDictUnificator.get("gemRuby", 1) : GT_ModHandler.mRuby, GT_OreDictUnificator.get("dustRuby", 1), false, true);
        GT_Utility.applyUsagesForMaterials(GT_ModHandler.mSapphire == null ? GT_OreDictUnificator.get("gemSapphire", 1) : GT_ModHandler.mSapphire, GT_OreDictUnificator.get("dustSapphire", 1), false, true);
        GT_Utility.applyUsagesForMaterials(GT_ModHandler.mGreenSapphire == null ? GT_OreDictUnificator.get("gemGreenSapphire", 1) : GT_ModHandler.mGreenSapphire, GT_OreDictUnificator.get("dustGreenSapphire", 1), false, true);
        GT_Utility.applyUsagesForMaterials(GT_ModHandler.mBrass == null ? GT_OreDictUnificator.get("ingotBrass", 1) : GT_ModHandler.mBrass, GT_OreDictUnificator.get("dustBrass", 1), true, true);
        GT_Utility.applyUsagesForMaterials(GT_ModHandler.mSilver == null ? GT_OreDictUnificator.get("ingotSilver", 1) : GT_ModHandler.mSilver, GT_OreDictUnificator.get("dustSilver", 1), true, true);
        GT_Utility.applyUsagesForMaterials(GT_OreDictUnificator.get("gemOlivine", 1), GT_MetaItem_Dust.instance.getStack(37, 1), false, true);
        GT_Utility.applyUsagesForMaterials(GT_OreDictUnificator.get("ingotAluminium", 1), GT_MetaItem_Dust.instance.getStack(18, 1), true, true);
        GT_Utility.applyUsagesForMaterials(GT_OreDictUnificator.get("ingotTitanium", 1), GT_MetaItem_Dust.instance.getStack(19, 1), true, true);
        GT_Utility.applyUsagesForMaterials(GT_OreDictUnificator.get("ingotChrome", 1), GT_MetaItem_Dust.instance.getStack(20, 1), true, true);
        GT_Utility.applyUsagesForMaterials(GT_OreDictUnificator.get("ingotSteel", 1), GT_MetaItem_Dust.instance.getStack(26, 1), true, true);
        GT_Utility.applyUsagesForMaterials(GT_MetaItem_Material.instance.getStack(21, 1), GT_MetaItem_Dust.instance.getStack(21, 1), true, true);
        GT_Utility.applyUsagesForMaterials(GT_MetaItem_Material.instance.getStack(22, 1), GT_MetaItem_Dust.instance.getStack(22, 1), true, true);
        GT_Utility.applyUsagesForMaterials(GT_MetaItem_Material.instance.getStack(23, 1), GT_MetaItem_Dust.instance.getStack(23, 1), true, true);
        GT_Utility.applyUsagesForMaterials(GT_MetaItem_Material.instance.getStack(24, 1), GT_MetaItem_Dust.instance.getStack(24, 1), true, true);
        GT_Utility.applyUsagesForMaterials(GT_MetaItem_Material.instance.getStack(27, 1), GT_MetaItem_Dust.instance.getStack(27, 1), true, true);
        GT_Utility.applyUsagesForMaterials(GT_MetaItem_Material.instance.getStack(28, 1), GT_MetaItem_Dust.instance.getStack(28, 1), true, true);
        GT_Utility.applyUsagesForMaterials(GT_MetaItem_Material.instance.getStack(29, 1), GT_MetaItem_Dust.instance.getStack(29, 1), true, true);
        GT_Utility.applyUsagesForMaterials(GT_MetaItem_Material.instance.getStack(31, 1), GT_MetaItem_Dust.instance.getStack(31, 1), true, true);
        GT_Utility.applyUsagesForMaterials(GT_MetaItem_Material.instance.getStack(16, 1), GT_ModHandler.getIC2Item("iridiumOre", 1), true, true);
        GT_Utility.applyUsagesForMaterials(GT_ModHandler.getIC2Item("iridiumOre", 1), GT_ModHandler.getIC2Item("iridiumOre", 1), false, true);
        GT_Utility.applyUsagesForMaterials(GT_OreDictUnificator.get("ingotOsmium", 1), GT_OreDictUnificator.get("dustOsmium", 1), false, true);
        GT_Utility.applyUsagesForMaterials(GT_OreDictUnificator.get("ingotRefinedIron", 1), GT_OreDictUnificator.get("dustIron", 1), true, true);
        GT_Utility.applyUsagesForMaterials(GT_OreDictUnificator.get("ingotBronze", 1), GT_OreDictUnificator.get("dustBronze", 1), true, true);
        GT_Utility.applyUsagesForMaterials(GT_OreDictUnificator.get("ingotCopper", 1), GT_OreDictUnificator.get("dustCopper", 1), true, true);
        GT_Utility.applyUsagesForMaterials(GT_OreDictUnificator.get("ingotTin", 1), GT_OreDictUnificator.get("dustTin", 1), true, true);
        GT_Utility.applyUsagesForMaterials(new ItemStack(Item.ingotIron, 1), GT_OreDictUnificator.get("dustIron", 1), true, true);
        GT_Utility.applyUsagesForMaterials(new ItemStack(Item.ingotGold, 1), GT_OreDictUnificator.get("dustGold", 1), true, true);
        GT_Utility.applyUsagesForMaterials(new ItemStack(Item.diamond, 1), GT_OreDictUnificator.get("dustDiamond", 1), false, true);
        GT_Utility.applyUsagesForMaterials(new ItemStack(Block.planks, 1), GT_MetaItem_SmallDust.instance.getStack(15, 4), false, true);
        if (GT_ModHandler.mTCResource != null) {
            GT_Utility.applyUsagesForMaterials(new ItemStack(GT_ModHandler.mTCResource.getItem(), 1, 2), new ItemStack(GT_ModHandler.mTCResource.getItem(), 1, 2), true, false);
        }
        GT_ModHandler.addPulverisationRecipe(new ItemStack(Item.helmetChain, 1), GT_MetaItem_SmallDust.instance.getStack(26, 5), null, 0, true);
        GT_ModHandler.addPulverisationRecipe(new ItemStack(Item.plateChain, 1), GT_MetaItem_SmallDust.instance.getStack(26, 8), null, 0, true);
        GT_ModHandler.addPulverisationRecipe(new ItemStack(Item.legsChain, 1), GT_MetaItem_SmallDust.instance.getStack(26, 7), null, 0, true);
        GT_ModHandler.addPulverisationRecipe(new ItemStack(Item.bootsChain, 1), GT_MetaItem_SmallDust.instance.getStack(26, 4), null, 0, true);
        GT_ModHandler.addPulverisationRecipe(new ItemStack(Item.pocketSundial, 1), GT_OreDictUnificator.get("dustGold", 4), GT_OreDictUnificator.get("dustRedstone", 1), 95, false);
        GT_ModHandler.addPulverisationRecipe(new ItemStack(Item.compass, 1), GT_OreDictUnificator.get("dustIron", 4), GT_OreDictUnificator.get("dustRedstone", 1), 95, false);
        GT_ModHandler.addPulverisationRecipe(new ItemStack((Item)Item.shears, 1), GT_OreDictUnificator.get("dustIron", 2), null, 95, false);
        GT_ModHandler.addPulverisationRecipe(new ItemStack(Item.minecartEmpty, 1), GT_OreDictUnificator.get("dustIron", 5), null, 0, true);
        GT_ModHandler.addPulverisationRecipe(new ItemStack(Item.bucketEmpty, 1), GT_OreDictUnificator.get("dustIron", 3), null, 0, true);
        GT_ModHandler.addPulverisationRecipe(new ItemStack(Item.doorSteel, 1), GT_OreDictUnificator.get("dustIron", 6), null, 0, true);
        GT_ModHandler.addPulverisationRecipe(new ItemStack(Block.fenceIron, 2), GT_MetaItem_SmallDust.instance.getStack(241, 3), null, 0, true);
        GT_ModHandler.addPulverisationRecipe(GT_ModHandler.getIC2Item("wrench", 1), GT_OreDictUnificator.get("dustBronze", 6), null, 0, true);
        GT_ModHandler.addPulverisationRecipe(GT_ModHandler.getIC2Item("ironFence", 2), GT_MetaItem_SmallDust.instance.getStack(241, 3), null, 0, true);
        GT_ModHandler.addPulverisationRecipe(GT_ModHandler.getIC2Item("fuelCan", 1), GT_OreDictUnificator.get("dustTin", 7), null, 0, true);
        GT_ModHandler.addPulverisationRecipe(GT_ModHandler.getIC2Item("tinCan", 1), GT_MetaItem_SmallDust.instance.getStack(244, 5), null, 0, true);
        GT_ModHandler.addSmeltingRecipe(new ItemStack(Item.pocketSundial, 1), GT_OreDictUnificator.get("ingotGold", 4));
        GT_ModHandler.addSmeltingRecipe(new ItemStack(Item.compass, 1), GT_OreDictUnificator.get("ingotIron", 4));
        GT_ModHandler.addSmeltingRecipe(new ItemStack((Item)Item.shears, 1), GT_OreDictUnificator.get("ingotIron", 2));
        GT_ModHandler.addSmeltingRecipe(new ItemStack(Item.minecartEmpty, 1), GT_OreDictUnificator.get("ingotIron", 5));
        GT_ModHandler.addSmeltingRecipe(new ItemStack(Item.bucketEmpty, 1), GT_OreDictUnificator.get("ingotIron", 3));
        GT_ModHandler.addSmeltingRecipe(new ItemStack(Item.doorSteel, 1), GT_OreDictUnificator.get("ingotIron", 6));
        GT_ModHandler.addSmeltingRecipe(new ItemStack(Block.anvil, 1, 0), GT_OreDictUnificator.get("ingotIron", 31));
        GT_ModHandler.addSmeltingRecipe(new ItemStack(Block.anvil, 1, 1), GT_OreDictUnificator.get("ingotIron", 20));
        GT_ModHandler.addSmeltingRecipe(new ItemStack(Block.anvil, 1, 2), GT_OreDictUnificator.get("ingotIron", 10));
        GT_ModHandler.addSmeltingRecipe(GT_ModHandler.getIC2Item("wrench", 1), GT_OreDictUnificator.get("ingotBronze", 6));
        GT_ModHandler.addSmeltingRecipe(GT_ModHandler.getIC2Item("fuelCan", 1), GT_OreDictUnificator.get("ingotTin", 7));
        GT_ModHandler.addSmeltingRecipe(GT_ModHandler.getIC2Item("tinCan", 1), GT_OreDictUnificator.get("ingotTin", 1));
        GT_ModHandler.addPulverisationRecipe(new ItemStack(Block.enchantmentTable, 1), GT_OreDictUnificator.get("dustDiamond", 2), GT_OreDictUnificator.get("dustObsidian", 4), 95, false);
        GT_ModHandler.addPulverisationRecipe(new ItemStack(Block.stoneButton, 1), new ItemStack(Block.sand, 1), null, 0, false);
        GT_ModHandler.addPulverisationRecipe(new ItemStack(Item.sign, 1, 0), GT_OreDictUnificator.get("dustWood", 2), null, 0, false);
        GT_ModHandler.addPulverisationRecipe(new ItemStack(Item.doorWood, 1, 0), GT_OreDictUnificator.get("dustWood", 6), null, 0, false);
        GT_ModHandler.addPulverisationRecipe(new ItemStack(Block.chest, 1, 0), GT_OreDictUnificator.get("dustWood", 8), null, 0, false);
        GT_ModHandler.addPulverisationRecipe(new ItemStack(Block.woodenButton, 1, 0), GT_OreDictUnificator.get("dustWood", 1), null, 0, false);
        GT_ModHandler.addPulverisationRecipe(new ItemStack(Block.pressurePlateStone, 1), new ItemStack(Block.sand, 2), null, 0, false);
        GT_ModHandler.addPulverisationRecipe(new ItemStack(Block.pressurePlatePlanks, 1), GT_OreDictUnificator.get("dustWood", 2), null, 0, false);
        GT_ModHandler.addPulverisationRecipe(new ItemStack(Block.torchRedstoneActive, 1), GT_OreDictUnificator.get("dustSmallWood", 2), new ItemStack(Item.redstone, 1), 95, false);
        GT_ModHandler.addPulverisationRecipe(new ItemStack(Block.torchRedstoneIdle, 1), GT_OreDictUnificator.get("dustSmallWood", 2), new ItemStack(Item.redstone, 1), 95, false);
        GT_ModHandler.addPulverisationRecipe(new ItemStack(Block.lever, 1), new ItemStack(Block.sand, 1), GT_OreDictUnificator.get("dustSmallWood", 2), 95, false);
        GT_ModHandler.addPulverisationRecipe(new ItemStack(Block.ladder, 1, 0), GT_OreDictUnificator.get("dustWood", 1), null, 0, false);
        tStack = GT_ModHandler.getRecipeOutput(new ItemStack[]{null, GT_OreDictUnificator.get("ingotTin", 1), null, GT_OreDictUnificator.get("ingotTin", 1), null, GT_OreDictUnificator.get("ingotTin", 1), null, null, null});
        if (tStack != null && (tTinCanNuggetCount = 27 / tStack.stackSize) > 0) {
            tStack.stackSize = 1;
            if (tTinCanNuggetCount % 9 == 0) {
                GT_ModHandler.addSmeltingRecipe(tStack, GT_OreDictUnificator.get("ingotTin", tTinCanNuggetCount / 9));
            } else {
                GT_ModHandler.addSmeltingRecipe(tStack, GT_OreDictUnificator.get("nuggetTin", tTinCanNuggetCount));
            }
        }
        if (this.mTincellCount % 4 == 0) {
            GT_ModHandler.addPulverisationRecipe(GT_ModHandler.getEmptyCell(1), GT_MetaItem_SmallDust.instance.getStack(244, 16 / this.mTincellCount), null, 0, true);
        } else {
            GT_ModHandler.addPulverisationRecipe(GT_ModHandler.getEmptyCell(4), GT_MetaItem_SmallDust.instance.getStack(244, 64 / this.mTincellCount), null, 0, true);
        }
        GT_Log.out.println("GT_Mod: Adding JackHammer minable Blocks.");
        GT_Mod.addJackHammerMinableBlock(Block.cobblestone);
        GT_Mod.addJackHammerMinableBlock(Block.stone);
        GT_Mod.addJackHammerMinableBlock(Block.stoneBrick);
        GT_Mod.addJackHammerMinableBlock(Block.sandStone);
        GT_Mod.addJackHammerMinableBlock(Block.cobblestoneMossy);
        GT_Mod.addJackHammerMinableBlock(Block.netherrack);
        GT_Mod.addJackHammerMinableBlock(Block.glowStone);
        GT_Mod.addJackHammerMinableBlock(Block.netherBrick);
        GT_Mod.addJackHammerMinableBlock(Block.whiteStone);
        GT_Log.out.println("GT_Mod: Adding Glowstone and Soulsand to the miners Valuable List.");
        GT_ModHandler.addValuableOre(Block.glowStone.blockID, 0, 1);
        GT_ModHandler.addValuableOre(Block.slowSand.blockID, 0, 1);
        GT_Log.out.println("GT_Mod: Activating OreDictionary Handler");
        sOreDict.activateHandler();
        GT_Log.out.println("GT_Mod: Iterating through the Seed-List of ForgeHooks, with a brilliant and 100% Invocation-free Method, to add Recipes for gaining Seed Oil from Seeds.");
        temp = false;
        try {
            GT_DummyWorld tWorld = new GT_DummyWorld();
            tLiquid = LiquidDictionary.getLiquid((String)"seedoil", (int)15);
            while (tWorld.mRandom.mIterationStep > 0) {
                ItemStack tSeed = ForgeHooks.getGrassSeed((World)tWorld);
                if (tSeed == null) continue;
                if (tLiquid != null && GT_ModHandler.addSqueezerRecipe(tSeed, tLiquid, 15)) {
                    temp = true;
                    continue;
                }
                tSeed = tSeed.copy();
                tSeed.stackSize = 64;
                GT_Mod.addCentrifugeRecipe(tSeed, 1, GT_MetaItem_Cell.instance.getStack(24, 1), null, null, null, 200);
            }
            GT_Log.out.println("GT_Mod: Iterating through the Grass-Flower-List of ForgeHooks, with a brilliant and 100% Invocation-free Method, to add Extractor Recipes for gaining more Dye from Flowers and also Compression Recipes for Plantballs.");
            tWorld.mRandom.mIterationStep = Integer.MAX_VALUE;
            while (tWorld.mRandom.mIterationStep > 0) {
                try {
                    ForgeHooks.plantGrass((World)tWorld, (int)0, (int)0, (int)0);
                    if (tWorld.mLastSetBlock == null) continue;
                    ItemStack tColor = GT_ModHandler.getRecipeOutput(new ItemStack[]{tWorld.mLastSetBlock, null, null, null, null, null, null, null, null});
                    if (GT_OreDictUnificator.isItemStackDye(tColor)) {
                        tColor = tColor.copy();
                        ++tColor.stackSize;
                        GT_ModHandler.addExtractionRecipe(tWorld.mLastSetBlock, tColor);
                    }
                    GT_ModHandler.addCompressionRecipe(tWorld.mLastSetBlock.copy().splitStack(8), GT_ModHandler.getIC2Item("compressedPlantBall", 1));
                }
                catch (Throwable e) {
                    GT_Log.out.println("Minor Bug: Wasn't able to simulate the planting of a Flower with Bonemeal, to add Extractor Recipe for Dye:\n");
                    e.printStackTrace(GT_Log.out);
                }
            }
        }
        catch (Throwable e) {
            GT_Log.out.println("GT_Mod: failed to iterate somehow, maybe it's your Forge Version causing it. But it's not that important\n");
            e.printStackTrace(GT_Log.out);
        }
        if (temp) {
            GT_Log.out.println("GT_Mod: Forestry was properly loaded, so the Seed Recipes got added to the Squeezer.");
        } else {
            GT_Log.out.println("GT_Mod: Forestry was NOT loaded, so the Recipes got added to the Industrial Centrifuge.");
            GT_Mod.addCentrifugeRecipe(new ItemStack(Item.melonSeeds, 64, 0), 1, GT_MetaItem_Cell.instance.getStack(24, 1), null, null, null, 200);
            GT_Mod.addCentrifugeRecipe(new ItemStack(Item.pumpkinSeeds, 64, 0), 1, GT_MetaItem_Cell.instance.getStack(24, 1), null, null, null, 200);
            GT_Mod.addCentrifugeRecipe(new ItemStack(Item.seeds, 64, 0), 1, GT_MetaItem_Cell.instance.getStack(24, 1), null, null, null, 200);
        }
        GT_ModHandler.addExtractionRecipe(new ItemStack((Block)Block.plantRed, 1, 0), new ItemStack(Item.dyePowder, 3, 1));
        GT_ModHandler.addExtractionRecipe(new ItemStack((Block)Block.plantYellow, 1, 0), new ItemStack(Item.dyePowder, 3, 11));
        GT_Log.out.println("GT_Mod: Adding Default Description Set of the Computer Cube");
        GT_ComputercubeDescription.addStandardDescriptions();
        GT_Log.out.println("GT_Mod: Saving Lang File now.");
        sLangFile.save();
        this.mPostLoaded = true;
        GT_Log.out.println("GT_Mod: PostLoad-Phase finished!");
    }

    @Mod.ServerStarting
    public void start(FMLServerStartingEvent aEvent) {
        if (this.mDoNotInit) {
            return;
        }
        gregtechproxy.serverStart();
        mUniverse = null;
        sLangFile.save();
        GT_TileEntity_IDSU.sEnergyList = null;
        GT_MetaTileEntity_MagicEnergyAbsorber.sUsedDragonCrystalList = null;
    }

    @Mod.ServerStopping
    public void stop(FMLServerStoppingEvent aEvent) {
        block15: {
            if (this.mDoNotInit) {
                return;
            }
            this.writeIDSUData();
            mUniverse = null;
            sLangFile.save();
            GT_TileEntity_IDSU.sEnergyList = null;
            try {
                int i;
                if (!this.mDebug && GT_Log.out == System.out) break block15;
                if (this.mDebug) {
                    System.out.println("BEGIN GregTech-Debug");
                }
                GT_Log.out.println("*");
                GT_Log.out.println("Printing List of all registered Objects inside the OreDictionary:");
                GT_Log.out.println("*");
                GT_Log.out.println("*");
                GT_Log.out.println("*");
                String[] tOres = OreDictionary.getOreNames();
                for (i = 0; i < tOres.length; ++i) {
                    GT_Log.out.println(tOres[i]);
                }
                GT_Log.out.println("*");
                GT_Log.out.println("*");
                GT_Log.out.println("*");
                GT_Log.out.println("Outputting all the Names inside the Itemslist, this List can become very long");
                GT_Log.out.println("*");
                GT_Log.out.println("*");
                GT_Log.out.println("*");
                for (i = 0; i < Item.itemsList.length; ++i) {
                    if (Item.itemsList[i] == null) continue;
                    GT_Log.out.println(Item.itemsList[i].getItemName());
                    if (!Item.itemsList[i].getHasSubtypes()) continue;
                    String tName = "";
                    for (int j = 0; j < 16; ++j) {
                        try {
                            tName = Item.itemsList[i].getItemNameIS(new ItemStack(Item.itemsList[i], 1, j));
                            if (tName == null || tName.equals("")) continue;
                            GT_Log.out.println(j + ": " + Item.itemsList[i].getItemName());
                            continue;
                        }
                        catch (Throwable e) {
                            // empty catch block
                        }
                    }
                }
                if (this.mDebug) {
                    System.out.println("*");
                    System.out.println("*");
                    System.out.println("*");
                    System.out.println("Outputting all the Names registered by Railcraft");
                    System.out.println("*");
                    System.out.println("*");
                    System.out.println("*");
                    try {
                        ItemRegistry.printItemTags();
                    }
                    catch (Throwable e) {
                        // empty catch block
                    }
                    System.out.println("*");
                    System.out.println("*");
                    System.out.println("*");
                    System.out.println("Outputting all the Names registered by Thermal Expansion");
                    System.out.println("*");
                    System.out.println("*");
                    System.out.println("*");
                    try {
                        thermalexpansion.api.core.ItemRegistry.printItemNames();
                    }
                    catch (Throwable e) {
                        // empty catch block
                    }
                }
                GT_Log.out.println("*");
                GT_Log.out.println("*");
                GT_Log.out.println("*");
                GT_Log.out.println("Outputting all the Names inside the Biomeslist");
                GT_Log.out.println("*");
                GT_Log.out.println("*");
                GT_Log.out.println("*");
                for (int i2 = 0; i2 < BiomeGenBase.biomeList.length; ++i2) {
                    if (BiomeGenBase.biomeList[i2] == null) continue;
                    GT_Log.out.println(BiomeGenBase.biomeList[i2].biomeID + " = " + BiomeGenBase.biomeList[i2].biomeName);
                }
                GT_Log.out.println("*");
                GT_Log.out.println("*");
                GT_Log.out.println("*");
                GT_Log.out.println("END GregTech-Debug");
                GT_Log.out.println("*");
                GT_Log.out.println("*");
                GT_Log.out.println("*");
            }
            catch (Throwable throwable) {
                // empty catch block
            }
        }
    }

    public static void addFusionReactorRecipe(ItemStack aInput1, ItemStack aInput2, ItemStack aOutput1, int aFusionDurationInTicks, int aFusionEnergyPerTick, int aEnergyNeededForStartingFusion) {
        if (aInput1 == null || aOutput1 == null) {
            return;
        }
        if ((aFusionDurationInTicks = mConfig.addAdvConfig("fusionreactor", aOutput1, aFusionDurationInTicks)) <= 0) {
            return;
        }
        new GT_Recipe(aInput1, aInput2, aOutput1, aFusionDurationInTicks, aFusionEnergyPerTick, aEnergyNeededForStartingFusion);
    }

    public static void addCentrifugeRecipe(ItemStack aInput1, int aInput2, ItemStack aOutput1, ItemStack aOutput2, ItemStack aOutput3, ItemStack aOutput4, int aDuration) {
        if (aInput1 == null || aOutput1 == null) {
            return;
        }
        if ((aDuration = mConfig.addAdvConfig("centrifuge", aInput1, aDuration)) <= 0) {
            return;
        }
        new GT_Recipe(aInput1, (ItemStack)(aInput2 > 0 ? GT_ModHandler.getEmptyCell(aInput2) : (aInput2 < 0 ? GT_ModHandler.getIC2Item("fuelCan", -aInput2) : null)), aOutput1, aOutput2, aOutput3, aOutput4, aDuration);
    }

    public static void addElectrolyzerRecipe(ItemStack aInput1, int aInput2, ItemStack aOutput1, ItemStack aOutput2, ItemStack aOutput3, ItemStack aOutput4, int aDuration, int aEUt) {
        if (aInput1 == null || aOutput1 == null) {
            return;
        }
        if ((aDuration = mConfig.addAdvConfig("electrolyzer", aInput1, aDuration)) <= 0) {
            return;
        }
        new GT_Recipe(aInput1, (ItemStack)(aInput2 > 0 ? GT_ModHandler.getEmptyCell(aInput2) : (aInput2 < 0 ? GT_ModHandler.getIC2Item("fuelCan", -aInput2) : null)), aOutput1, aOutput2, aOutput3, aOutput4, aDuration, aEUt);
    }

    public static void addChemicalRecipe(ItemStack aInput1, ItemStack aInput2, ItemStack aOutput1, int aDuration) {
        if (aInput1 == null || aOutput1 == null) {
            return;
        }
        if ((aDuration = mConfig.addAdvConfig("chemicalreactor", aOutput1, aDuration)) <= 0) {
            return;
        }
        new GT_Recipe(aInput1, aInput2, aOutput1, aDuration);
    }

    public static void addBlastRecipe(ItemStack aInput1, ItemStack aInput2, ItemStack aOutput1, ItemStack aOutput2, int aDuration, int aEUt, int aLevel) {
        if (aInput1 == null || aOutput1 == null) {
            return;
        }
        if ((aDuration = mConfig.addAdvConfig("blastfurnace", aInput1, aDuration)) <= 0) {
            return;
        }
        new GT_Recipe(aInput1, aInput2, aOutput1, aOutput2, aDuration, aEUt, aLevel);
    }

    public static void addCannerRecipe(ItemStack aInput1, ItemStack aInput2, ItemStack aOutput1, ItemStack aOutput2, int aDuration, int aEUt) {
        if (aInput1 == null || aOutput1 == null) {
            return;
        }
        if ((aDuration = mConfig.addAdvConfig("canning", aInput1, aDuration)) <= 0) {
            return;
        }
        new GT_Recipe(aInput1, aEUt, aInput2, aDuration, aOutput1, aOutput2);
    }

    public static void addAlloySmelterRecipe(ItemStack aInput1, ItemStack aInput2, ItemStack aOutput1, int aDuration, int aEUt) {
        if (aInput1 == null || aOutput1 == null) {
            return;
        }
        aDuration = mConfig.addAdvConfig("alloysmelting", aInput2 == null ? aInput1 : aOutput1, aDuration);
        if (aDuration <= 0) {
            return;
        }
        new GT_Recipe(aInput1, aInput2, aEUt, aDuration, aOutput1);
    }

    public static void addPlateCuttingRecipe(ItemStack aInput1, ItemStack aOutput1, int aDuration, int aEUt) {
        if (aInput1 == null || aOutput1 == null) {
            return;
        }
        if ((aDuration = mConfig.addAdvConfig("cutting", aOutput1, aDuration)) <= 0) {
            return;
        }
    }

    public static void addAssemblerRecipe(ItemStack aInput1, ItemStack aInput2, ItemStack aOutput1, int aDuration, int aEUt) {
        if (aInput1 == null || aOutput1 == null) {
            return;
        }
        if ((aDuration = mConfig.addAdvConfig("assembling", aOutput1, aDuration)) <= 0) {
            return;
        }
        new GT_Recipe(aInput1, aEUt, aInput2, aDuration, aOutput1);
    }

    public static void addWiremillRecipe(ItemStack aInput1, ItemStack aOutput1, int aDuration, int aEUt) {
        if (aInput1 == null || aOutput1 == null) {
            return;
        }
        if ((aDuration = mConfig.addAdvConfig("wiremill", aInput1, aDuration)) <= 0) {
            return;
        }
        new GT_Recipe(aInput1, aEUt, aDuration, aOutput1);
    }

    public static void addBenderRecipe(ItemStack aInput1, ItemStack aOutput1, int aDuration, int aEUt) {
        if (aInput1 == null || aOutput1 == null) {
            return;
        }
        if ((aDuration = mConfig.addAdvConfig("bender", aInput1, aDuration)) <= 0) {
            return;
        }
        new GT_Recipe(aEUt, aDuration, aInput1, aOutput1);
    }

    public static void addImplosionRecipe(ItemStack aInput1, int aInput2, ItemStack aOutput1, ItemStack aOutput2) {
        if (aInput1 == null || aOutput1 == null) {
            return;
        }
        if ((aInput2 = mConfig.addAdvConfig("implosion", aInput1, aInput2)) <= 0) {
            return;
        }
        new GT_Recipe(aInput1, aInput2, aOutput1, aOutput2);
    }

    public static void addGrinderRecipe(ItemStack aInput1, int aInput2, ItemStack aOutput1, ItemStack aOutput2, ItemStack aOutput3, ItemStack aOutput4) {
        if (aInput1 == null || aOutput1 == null) {
            return;
        }
        if (!mConfig.addAdvConfig("grinder", aInput1, true)) {
            return;
        }
        new GT_Recipe(aInput1, (ItemStack)(aInput2 > 0 ? GT_ModHandler.getEmptyCell(aInput2) : (aInput2 < 0 ? GT_ModHandler.getIC2Item("waterCell", -aInput2) : null)), aOutput1, aOutput2, aOutput3, aOutput4);
        if (aInput2 == -1 && aOutput4 != null && aOutput4.isItemEqual(GT_ModHandler.getEmptyCell(1)) && aOutput4.stackSize == 1) {
            new GT_Recipe(aInput1, new ItemStack(Item.bucketWater, 1), aOutput1, aOutput2, aOutput3, new ItemStack(Item.bucketEmpty, 1));
        }
    }

    public static void addGrinderRecipe(ItemStack aInput1, ItemStack aInput2, ItemStack aOutput1, ItemStack aOutput2, ItemStack aOutput3, ItemStack aOutput4) {
        if (aInput1 == null || aOutput1 == null) {
            return;
        }
        if (!mConfig.addAdvConfig("grinder", aInput1, true)) {
            return;
        }
        new GT_Recipe(aInput1, aInput2, aOutput1, aOutput2, aOutput3, aOutput4);
    }

    public static void addDistillationRecipe(ItemStack aInput1, int aInput2, ItemStack aOutput1, ItemStack aOutput2, ItemStack aOutput3, ItemStack aOutput4, int aDuration, int aEUt) {
        if (aInput1 == null || aOutput1 == null) {
            return;
        }
        if ((aDuration = mConfig.addAdvConfig("distillation", aInput1, aDuration)) <= 0) {
            return;
        }
        new GT_Recipe(aInput1, aInput2, aOutput1, aOutput2, aOutput3, aOutput4, aDuration, aEUt);
    }

    public static void addVacuumFreezerRecipe(ItemStack aInput1, ItemStack aOutput1, int aDuration) {
        if (aInput1 == null || aOutput1 == null) {
            return;
        }
        if ((aDuration = mConfig.addAdvConfig("vacuumfreezer", aInput1, aDuration)) <= 0) {
            return;
        }
        new GT_Recipe(aInput1, aOutput1, aDuration);
    }

    public static void addSawmillRecipe(ItemStack aInput1, int aInput2, ItemStack aOutput1, ItemStack aOutput2, ItemStack aOutput3) {
        if (aInput1 == null || aOutput1 == null) {
            return;
        }
        if (!mConfig.addAdvConfig("sawmill", aInput1, true)) {
            return;
        }
        new GT_Recipe(aInput1, (ItemStack)(aInput2 > 0 ? GT_ModHandler.getEmptyCell(aInput2) : (aInput2 < 0 ? GT_ModHandler.getIC2Item("waterCell", -aInput2) : null)), aOutput1, aOutput2, aOutput3);
        if (aInput2 == -1 && aOutput3 != null && aOutput3.isItemEqual(GT_ModHandler.getEmptyCell(1)) && aOutput3.stackSize == 1) {
            new GT_Recipe(aInput1, new ItemStack(Item.bucketWater, 1), aOutput1, aOutput2, new ItemStack(Item.bucketEmpty, 1));
        }
    }

    public static void addSawmillRecipe(ItemStack aInput1, ItemStack aInput2, ItemStack aOutput1, ItemStack aOutput2, ItemStack aOutput3) {
        if (aInput1 == null || aOutput1 == null) {
            return;
        }
        if (!mConfig.addAdvConfig("sawmill", aInput1, true)) {
            return;
        }
        new GT_Recipe(aInput1, aInput2, aOutput1, aOutput2, aOutput3);
    }

    public static void addFuel(ItemStack aInput1, ItemStack aOutput1, int aEU, int aType) {
        if (aInput1 == null || aOutput1 == null) {
            return;
        }
        new GT_Recipe(aInput1, aOutput1, mConfig.addAdvConfig("fuel_" + aType, aInput1, aEU), aType);
    }

    public static void addJackHammerMinableBlock(Block aBlock) {
        if (aBlock != null && !GT_Jackhammer_Item.mineableBlocks.contains(aBlock)) {
            GT_Jackhammer_Item.mineableBlocks.add(aBlock);
        }
    }

    public static void addSonictronSound(ItemStack aItemStack, String aSoundName) {
        if (aItemStack == null || aSoundName == null || aSoundName.equals("")) {
            return;
        }
        mSoundItems.add(aItemStack);
        mSoundNames.add(aSoundName);
        if (aSoundName.startsWith("note.")) {
            mSoundCounts.add(25);
        } else {
            mSoundCounts.add(1);
        }
    }

    public static void addComputercubeDescriptionSet(ItemStack[] aItemStack, String[] aText) {
        new GT_ComputercubeDescription(aText, aItemStack);
    }

    public static void causeMachineUpdate(World aWorld, int aX, int aY, int aZ) {
        GT_Mod.stepToUpdateMachine(aWorld, aX, aY, aZ, new ArrayList());
    }

    public static void stepToUpdateMachine(World aWorld, int aX, int aY, int aZ, ArrayList aList) {
        aList.add(new ChunkPosition(aX, aY, aZ));
        TileEntity tTileEntity = aWorld.getBlockTileEntity(aX, aY, aZ);
        if (tTileEntity != null && tTileEntity instanceof IMachineBlockUpdateable) {
            ((IMachineBlockUpdateable)tTileEntity).onMachineBlockUpdate();
        }
        if (aList.size() < 5 || tTileEntity != null && tTileEntity instanceof IMachineBlockUpdateable || GT_Mod.isMachineBlock(aWorld.getBlockId(aX, aY, aZ), aWorld.getBlockMetadata(aX, aY, aZ))) {
            if (!aList.contains(new ChunkPosition(aX + 1, aY, aZ))) {
                GT_Mod.stepToUpdateMachine(aWorld, aX + 1, aY, aZ, aList);
            }
            if (!aList.contains(new ChunkPosition(aX - 1, aY, aZ))) {
                GT_Mod.stepToUpdateMachine(aWorld, aX - 1, aY, aZ, aList);
            }
            if (!aList.contains(new ChunkPosition(aX, aY + 1, aZ))) {
                GT_Mod.stepToUpdateMachine(aWorld, aX, aY + 1, aZ, aList);
            }
            if (!aList.contains(new ChunkPosition(aX, aY - 1, aZ))) {
                GT_Mod.stepToUpdateMachine(aWorld, aX, aY - 1, aZ, aList);
            }
            if (!aList.contains(new ChunkPosition(aX, aY, aZ + 1))) {
                GT_Mod.stepToUpdateMachine(aWorld, aX, aY, aZ + 1, aList);
            }
            if (!aList.contains(new ChunkPosition(aX, aY, aZ - 1))) {
                GT_Mod.stepToUpdateMachine(aWorld, aX, aY, aZ - 1, aList);
            }
        }
    }

    public static void registerMachineBlock(int aID, int aMeta) {
        mMachineIDs.put(aID, aMeta);
    }

    public static boolean isMachineBlock(int aID, int aMeta) {
        return mMachineIDs.containsKey(aID) && ((Integer)mMachineIDs.get(aID) & 1 << aMeta) != 0;
    }

    public static BaseMetaTileEntity constructBaseMetaTileEntity() {
        try {
            return (BaseMetaTileEntity)Class.forName("gregtechmod.api.BaseMetaTileEntityMJ").newInstance();
        }
        catch (Throwable throwable) {
            return new BaseMetaTileEntity();
        }
    }

    public static ItemStack getUnificatedOreDictStack(ItemStack aOreStack) {
        if (!GT_Mod.instance.mLoaded) {
            System.err.println(aOreStack.itemID + "." + aOreStack.getItemDamage() + " - OreDict Unification Entries are not registered now, please call it in the postload phase.");
        }
        return GT_OreDictUnificator.get(aOreStack);
    }

    public static ItemStack getGregTechBlock(int aIndex, int aAmount, int aMeta) {
        if (instance != null) {
            if (aIndex < GT_Mod.instance.mBlocks.length && aIndex >= 0) {
                if (GT_Mod.instance.mBlocks[aIndex] != null) {
                    return new ItemStack(GT_Mod.instance.mBlocks[aIndex], aAmount, aMeta);
                }
                System.err.println("GT_API: This Blockindex wasn't initialized, you either called it before the Init-Phase (Only Post-Init or later will work), or this Item doesnt exist now");
            } else {
                System.err.println("GT_API: The Index " + aIndex + " is not part of my Itemrange");
            }
        } else {
            System.err.println("GT_API: Why did you hack the API? Was that check if my Mod is loaded too much?");
        }
        return null;
    }

    public static ItemStack getGregTechItem(int aIndex, int aAmount, int aDamage) {
        if (instance != null) {
            if (aIndex < GT_Mod.instance.mItems.length && aIndex >= 0) {
                if (GT_Mod.instance.mItems[aIndex] != null) {
                    Field tField;
                    if (GT_Mod.instance.mItems[aIndex] instanceof GT_MetaItem_Abstract && (tField = GT_Utility.getPublicField(GT_Mod.instance.mItems[aIndex], "instance")) != null) {
                        try {
                            ItemStack rStack = null;
                            rStack = ((GT_MetaItem_Abstract)((Object)tField.get(GT_Mod.instance.mItems[aIndex]))).getStack(aDamage, aAmount);
                            if (rStack != null) {
                                return rStack;
                            }
                        }
                        catch (Throwable e) {
                            // empty catch block
                        }
                    }
                    return new ItemStack(GT_Mod.instance.mItems[aIndex], aAmount, aDamage);
                }
                System.err.println("GT_API: This Itemindex wasn't initialized, you either called it before the Init-Phase (Only Post-Init or later will work), or this Item doesnt exist now");
            } else {
                System.err.println("GT_API: The Index " + aIndex + " is not part of my Itemrange");
            }
        } else {
            System.err.println("GT_API: Why did you hack the API? Was that check if my Mod is loaded too much?");
        }
        return null;
    }

    public static int getCapsuleCellContainerCount(ItemStack aStack) {
        return GT_ModHandler.getCapsuleCellContainerCount(aStack);
    }

    public File getSaveDirectory() {
        if (mUniverse == null) {
            return null;
        }
        SaveHandler tSaveHandler = (SaveHandler)mUniverse.getSaveHandler();
        File rFile = null;
        Field[] tFields = SaveHandler.class.getDeclaredFields();
        for (int i = 0; i < tFields.length; ++i) {
            if (tFields[i].getType() != File.class) continue;
            tFields[i].setAccessible(true);
            try {
                File tFile = (File)tFields[i].get(tSaveHandler);
                if (rFile != null && rFile.getParentFile() != tFile) continue;
                rFile = tFile;
                continue;
            }
            catch (Exception e) {
                // empty catch block
            }
        }
        return rFile;
    }

    public void writeIDSUData() {
        if (mUniverse != null && GT_TileEntity_IDSU.sEnergyList != null && !GT_Mod.mUniverse.isRemote) {
            try {
                File tDirectory = this.getSaveDirectory();
                if (tDirectory != null) {
                    NBTTagCompound tNBT = new NBTTagCompound();
                    NBTTagList tList = new NBTTagList();
                    for (Map.Entry<Integer, Integer> tEntry : GT_TileEntity_IDSU.sEnergyList.entrySet()) {
                        NBTTagCompound tTag = new NBTTagCompound();
                        tTag.setInteger("Hash", tEntry.getKey());
                        tTag.setInteger("EU", tEntry.getValue());
                        tList.appendTag((NBTBase)tTag);
                    }
                    tNBT.setTag("Energy", (NBTBase)tList);
                    try {
                        CompressedStreamTools.writeCompressed((NBTTagCompound)tNBT, (OutputStream)new FileOutputStream(new File(tDirectory, "GT_IDSU_Energyvalues.dat")));
                    }
                    catch (StackOverflowError e) {
                        GT_Log.out.println("Ignored Stackoverflow at writing!");
                    }
                }
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void readIDSUData() {
        block6: {
            if (mUniverse != null && GT_TileEntity_IDSU.sEnergyList == null && !GT_Mod.mUniverse.isRemote) {
                GT_TileEntity_IDSU.sEnergyList = new HashMap();
                try {
                    File tDirectory = this.getSaveDirectory();
                    if (tDirectory == null) break block6;
                    try {
                        NBTTagCompound tNBT = CompressedStreamTools.readCompressed((InputStream)new FileInputStream(new File(tDirectory, "GT_IDSU_Energyvalues.dat")));
                        NBTTagList tList = tNBT.getTagList("Energy");
                        for (int i = 0; i < tList.tagCount(); ++i) {
                            NBTTagCompound tTag = (NBTTagCompound)tList.tagAt(i);
                            GT_TileEntity_IDSU.sEnergyList.put(tTag.getInteger("Hash"), tTag.getInteger("EU"));
                        }
                    }
                    catch (StackOverflowError e) {
                        GT_Log.out.println("Ignored Stackoverflow at reading!");
                    }
                }
                catch (Exception e) {
                    if (e instanceof FileNotFoundException) break block6;
                    e.printStackTrace();
                }
            }
        }
    }

    static {
        Randomizer = new Random(42L);
        tabGregTech = new GT_CreativeTab();
        sOreDict = new GT_OreDictHandler();
        mUniverse = null;
        sPremiumNames = new ArrayList();
        sAdminNames = new ArrayList();
        mBrainTechCapeList = new ArrayList();
        mGregTechCapeList = new ArrayList();
        mRubyList = new ArrayList();
        mSapphireList = new ArrayList();
        mBauxiteList = new ArrayList();
        mSoundNames = new ArrayList();
        mSoundItems = new ArrayList();
        mSoundCounts = new ArrayList();
        mMachineIDs = new HashMap();
    }
}

