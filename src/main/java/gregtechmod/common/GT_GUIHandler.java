/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.network.IGuiHandler
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.world.World
 */
package gregtechmod.common;

import cpw.mods.fml.common.network.IGuiHandler;
import gregtechmod.api.BaseMetaTileEntity;
import gregtechmod.common.containers.GT_ContainerMetaID_Machine;
import gregtechmod.common.containers.GT_Container_AESU;
import gregtechmod.common.containers.GT_Container_AdvancedPump;
import gregtechmod.common.containers.GT_Container_AdvancedTranslocator;
import gregtechmod.common.containers.GT_Container_BasicMachine;
import gregtechmod.common.containers.GT_Container_BasicTank;
import gregtechmod.common.containers.GT_Container_BlastFurnace;
import gregtechmod.common.containers.GT_Container_Centrifuge;
import gregtechmod.common.containers.GT_Container_ChargeOMat;
import gregtechmod.common.containers.GT_Container_ChemicalReactor;
import gregtechmod.common.containers.GT_Container_ComputerCube;
import gregtechmod.common.containers.GT_Container_CropHarvestor;
import gregtechmod.common.containers.GT_Container_DistillationTower;
import gregtechmod.common.containers.GT_Container_ElectricAutoWorkbench;
import gregtechmod.common.containers.GT_Container_ElectricBufferAdvanced;
import gregtechmod.common.containers.GT_Container_ElectricBufferLarge;
import gregtechmod.common.containers.GT_Container_ElectricBufferSmall;
import gregtechmod.common.containers.GT_Container_ElectricInventoryManager;
import gregtechmod.common.containers.GT_Container_ElectricItemClearer;
import gregtechmod.common.containers.GT_Container_ElectricRegulatorAdvanced;
import gregtechmod.common.containers.GT_Container_ElectricSorter;
import gregtechmod.common.containers.GT_Container_ElectricTypeSorter;
import gregtechmod.common.containers.GT_Container_Electrolyzer;
import gregtechmod.common.containers.GT_Container_FusionComputer;
import gregtechmod.common.containers.GT_Container_Grinder;
import gregtechmod.common.containers.GT_Container_IDSU;
import gregtechmod.common.containers.GT_Container_ImplosionCompressor;
import gregtechmod.common.containers.GT_Container_Item_Destructopack;
import gregtechmod.common.containers.GT_Container_LESU;
import gregtechmod.common.containers.GT_Container_MagicEnergyAbsorber;
import gregtechmod.common.containers.GT_Container_Matterfabricator;
import gregtechmod.common.containers.GT_Container_RockBreaker;
import gregtechmod.common.containers.GT_Container_Safe;
import gregtechmod.common.containers.GT_Container_Sawmill;
import gregtechmod.common.containers.GT_Container_Scrapboxinator;
import gregtechmod.common.containers.GT_Container_Sonictron;
import gregtechmod.common.containers.GT_Container_Translocator;
import gregtechmod.common.containers.GT_Container_UUMAssembler;
import gregtechmod.common.containers.GT_Container_VacuumFreezer;
import gregtechmod.common.gui.GT_GUIContainerMetaID_Machine;
import gregtechmod.common.gui.GT_GUIContainer_AESU;
import gregtechmod.common.gui.GT_GUIContainer_AdvancedPump;
import gregtechmod.common.gui.GT_GUIContainer_AdvancedTranslocator;
import gregtechmod.common.gui.GT_GUIContainer_BasicMachine;
import gregtechmod.common.gui.GT_GUIContainer_BasicMachine_AlloySmelter;
import gregtechmod.common.gui.GT_GUIContainer_BasicMachine_Assembler;
import gregtechmod.common.gui.GT_GUIContainer_BasicMachine_Bender;
import gregtechmod.common.gui.GT_GUIContainer_BasicMachine_Canner;
import gregtechmod.common.gui.GT_GUIContainer_BasicMachine_Compressor;
import gregtechmod.common.gui.GT_GUIContainer_BasicMachine_E_Furnace;
import gregtechmod.common.gui.GT_GUIContainer_BasicMachine_Extractor;
import gregtechmod.common.gui.GT_GUIContainer_BasicMachine_Macerator;
import gregtechmod.common.gui.GT_GUIContainer_BasicMachine_Wiremill;
import gregtechmod.common.gui.GT_GUIContainer_BlastFurnace;
import gregtechmod.common.gui.GT_GUIContainer_Centrifuge;
import gregtechmod.common.gui.GT_GUIContainer_ChargeOMat;
import gregtechmod.common.gui.GT_GUIContainer_ChemicalReactor;
import gregtechmod.common.gui.GT_GUIContainer_ComputerCube;
import gregtechmod.common.gui.GT_GUIContainer_CropHarvestor;
import gregtechmod.common.gui.GT_GUIContainer_Destructopack;
import gregtechmod.common.gui.GT_GUIContainer_DieselGenerator;
import gregtechmod.common.gui.GT_GUIContainer_DistillationTower;
import gregtechmod.common.gui.GT_GUIContainer_ElectricAutoWorkbench;
import gregtechmod.common.gui.GT_GUIContainer_ElectricBufferAdvanced;
import gregtechmod.common.gui.GT_GUIContainer_ElectricBufferLarge;
import gregtechmod.common.gui.GT_GUIContainer_ElectricBufferSmall;
import gregtechmod.common.gui.GT_GUIContainer_ElectricInventoryManager;
import gregtechmod.common.gui.GT_GUIContainer_ElectricItemClearer;
import gregtechmod.common.gui.GT_GUIContainer_ElectricRegulatorAdvanced;
import gregtechmod.common.gui.GT_GUIContainer_ElectricSorter;
import gregtechmod.common.gui.GT_GUIContainer_ElectricTypeSorter;
import gregtechmod.common.gui.GT_GUIContainer_Electrolyzer;
import gregtechmod.common.gui.GT_GUIContainer_FusionComputer;
import gregtechmod.common.gui.GT_GUIContainer_FusionExtractor;
import gregtechmod.common.gui.GT_GUIContainer_FusionInjector;
import gregtechmod.common.gui.GT_GUIContainer_GasTurbine;
import gregtechmod.common.gui.GT_GUIContainer_Grinder;
import gregtechmod.common.gui.GT_GUIContainer_IDSU;
import gregtechmod.common.gui.GT_GUIContainer_ImplosionCompressor;
import gregtechmod.common.gui.GT_GUIContainer_LESU;
import gregtechmod.common.gui.GT_GUIContainer_MagicEnergyAbsorber;
import gregtechmod.common.gui.GT_GUIContainer_MagicEnergyConverter;
import gregtechmod.common.gui.GT_GUIContainer_Matterfabricator;
import gregtechmod.common.gui.GT_GUIContainer_PlasmaGenerator;
import gregtechmod.common.gui.GT_GUIContainer_Quantumtank;
import gregtechmod.common.gui.GT_GUIContainer_RockBreaker;
import gregtechmod.common.gui.GT_GUIContainer_Safe;
import gregtechmod.common.gui.GT_GUIContainer_Sawmill;
import gregtechmod.common.gui.GT_GUIContainer_Scrapboxinator;
import gregtechmod.common.gui.GT_GUIContainer_SemifluidGenerator;
import gregtechmod.common.gui.GT_GUIContainer_Sonictron;
import gregtechmod.common.gui.GT_GUIContainer_ThermalGenerator;
import gregtechmod.common.gui.GT_GUIContainer_Translocator;
import gregtechmod.common.gui.GT_GUIContainer_UUMAssembler;
import gregtechmod.common.gui.GT_GUIContainer_VacuumFreezer;
import gregtechmod.common.tileentities.GT_TileEntityMetaID_Machine;
import gregtechmod.common.tileentities.GT_TileEntity_AESU;
import gregtechmod.common.tileentities.GT_TileEntity_ChargeOMat;
import gregtechmod.common.tileentities.GT_TileEntity_ComputerCube;
import gregtechmod.common.tileentities.GT_TileEntity_IDSU;
import gregtechmod.common.tileentities.GT_TileEntity_LESU;
import gregtechmod.common.tileentities.GT_TileEntity_Matterfabricator;
import gregtechmod.common.tileentities.GT_TileEntity_Sonictron;
import gregtechmod.common.tileentities.GT_TileEntity_UUMAssembler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class GT_GUIHandler
implements IGuiHandler {
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        TileEntity tTileEntity = world.getBlockTileEntity(x, y, z);
        if (ID == 0) {
            return new GT_ContainerMetaID_Machine(player.inventory, (GT_TileEntityMetaID_Machine)tTileEntity, 0);
        }
        if (ID == 4) {
            return new GT_Container_ComputerCube(player.inventory, (GT_TileEntity_ComputerCube)tTileEntity, 0);
        }
        if (ID == 5) {
            return new GT_Container_UUMAssembler(player.inventory, (GT_TileEntity_UUMAssembler)tTileEntity, 0);
        }
        if (ID == 6) {
            return new GT_Container_Sonictron(player.inventory, (GT_TileEntity_Sonictron)tTileEntity, 0);
        }
        if (ID == 7) {
            return new GT_Container_LESU(player.inventory, (GT_TileEntity_LESU)tTileEntity, 0);
        }
        if (ID == 8) {
            return new GT_Container_IDSU(player.inventory, (GT_TileEntity_IDSU)tTileEntity, 0);
        }
        if (ID == 9) {
            return new GT_Container_AESU(player.inventory, (GT_TileEntity_AESU)tTileEntity, 0);
        }
        if (ID == 10) {
            return new GT_Container_ChargeOMat(player.inventory, (GT_TileEntity_ChargeOMat)tTileEntity, 0);
        }
        if (ID == 14) {
            return new GT_Container_Matterfabricator(player.inventory, (GT_TileEntity_Matterfabricator)tTileEntity, 0);
        }
        if (ID == 32) {
            return new GT_Container_ComputerCube(player.inventory, (GT_TileEntity_ComputerCube)tTileEntity, 1);
        }
        if (ID == 34) {
            return new GT_Container_ComputerCube(player.inventory, (GT_TileEntity_ComputerCube)tTileEntity, 2);
        }
        if (ID == 35) {
            return new GT_Container_ComputerCube(player.inventory, (GT_TileEntity_ComputerCube)tTileEntity, 3);
        }
        if (ID == 36) {
            return new GT_Container_ComputerCube(player.inventory, (GT_TileEntity_ComputerCube)tTileEntity, 4);
        }
        if (ID == 37) {
            return new GT_Container_ComputerCube(player.inventory, (GT_TileEntity_ComputerCube)tTileEntity, 5);
        }
        if (ID == 38) {
            return new GT_Container_ComputerCube(player.inventory, (GT_TileEntity_ComputerCube)tTileEntity, 6);
        }
        if (ID == 33) {
            return new GT_Container_Item_Destructopack(player.inventory, player.getCurrentEquippedItem());
        }
        if (ID == 100) {
            return new GT_Container_ElectricAutoWorkbench(player.inventory, (BaseMetaTileEntity)tTileEntity, 0);
        }
        if (ID == 101) {
            return new GT_Container_Translocator(player.inventory, (BaseMetaTileEntity)tTileEntity, 0);
        }
        if (ID == 102) {
            return new GT_Container_ElectricBufferSmall(player.inventory, (BaseMetaTileEntity)tTileEntity, 0);
        }
        if (ID == 103) {
            return new GT_Container_ElectricBufferLarge(player.inventory, (BaseMetaTileEntity)tTileEntity, 0);
        }
        if (ID == 104) {
            return new GT_Container_AdvancedTranslocator(player.inventory, (BaseMetaTileEntity)tTileEntity, 0);
        }
        if (ID == 105) {
            return new GT_Container_ElectricBufferAdvanced(player.inventory, (BaseMetaTileEntity)tTileEntity, 0);
        }
        if (ID == 106) {
            return new GT_Container_RockBreaker(player.inventory, (BaseMetaTileEntity)tTileEntity, 0);
        }
        if (ID == 107) {
            return new GT_Container_ElectricSorter(player.inventory, (BaseMetaTileEntity)tTileEntity, 0);
        }
        if (ID == 108) {
            return new GT_Container_ElectricItemClearer(player.inventory, (BaseMetaTileEntity)tTileEntity, 0);
        }
        if (ID == 109) {
            return new GT_Container_Electrolyzer(player.inventory, (BaseMetaTileEntity)tTileEntity, 0);
        }
        if (ID == 110) {
            return new GT_Container_CropHarvestor(player.inventory, (BaseMetaTileEntity)tTileEntity, 0);
        }
        if (ID == 111) {
            return new GT_Container_Scrapboxinator(player.inventory, (BaseMetaTileEntity)tTileEntity, 0);
        }
        if (ID == 112) {
            return new GT_Container_Grinder(player.inventory, (BaseMetaTileEntity)tTileEntity, 0);
        }
        if (ID == 113) {
            return new GT_Container_BlastFurnace(player.inventory, (BaseMetaTileEntity)tTileEntity, 0);
        }
        if (ID == 114) {
            return new GT_Container_BasicTank(player.inventory, (BaseMetaTileEntity)tTileEntity, 0);
        }
        if (ID == 115) {
            return new GT_Container_ImplosionCompressor(player.inventory, (BaseMetaTileEntity)tTileEntity, 0);
        }
        if (ID == 116) {
            return new GT_Container_Sawmill(player.inventory, (BaseMetaTileEntity)tTileEntity, 0);
        }
        if (ID == 117) {
            return new GT_Container_BasicTank(player.inventory, (BaseMetaTileEntity)tTileEntity, 0);
        }
        if (ID == 118) {
            return new GT_Container_BasicTank(player.inventory, (BaseMetaTileEntity)tTileEntity, 0);
        }
        if (ID == 119) {
            return new GT_Container_BasicTank(player.inventory, (BaseMetaTileEntity)tTileEntity, 0);
        }
        if (ID == 120) {
            return new GT_Container_BasicTank(player.inventory, (BaseMetaTileEntity)tTileEntity, 0);
        }
        if (ID == 121) {
            return new GT_Container_BasicTank(player.inventory, (BaseMetaTileEntity)tTileEntity, 0);
        }
        if (ID == 122) {
            return new GT_Container_VacuumFreezer(player.inventory, (BaseMetaTileEntity)tTileEntity, 0);
        }
        if (ID == 123) {
            return new GT_Container_ElectricRegulatorAdvanced(player.inventory, (BaseMetaTileEntity)tTileEntity, 0);
        }
        if (ID == 124) {
            return new GT_Container_ChemicalReactor(player.inventory, (BaseMetaTileEntity)tTileEntity, 0);
        }
        if (ID == 125) {
            return new GT_Container_BasicTank(player.inventory, (BaseMetaTileEntity)tTileEntity, 0);
        }
        if (ID == 126) {
            return new GT_Container_MagicEnergyAbsorber(player.inventory, (BaseMetaTileEntity)tTileEntity, 0);
        }
        if (ID == 127) {
            return new GT_Container_DistillationTower(player.inventory, (BaseMetaTileEntity)tTileEntity, 0);
        }
        if (ID == 128) {
            return new GT_Container_Safe(player.inventory, (BaseMetaTileEntity)tTileEntity, 0);
        }
        if (ID == 129) {
            return new GT_Container_ElectricInventoryManager(player.inventory, (BaseMetaTileEntity)tTileEntity, 0);
        }
        if (ID == 130) {
            return new GT_Container_AdvancedPump(player.inventory, (BaseMetaTileEntity)tTileEntity, 0);
        }
        if (ID == 131) {
            return new GT_Container_BasicMachine(player.inventory, (BaseMetaTileEntity)tTileEntity, 0);
        }
        if (ID == 132) {
            return new GT_Container_BasicMachine(player.inventory, (BaseMetaTileEntity)tTileEntity, 0);
        }
        if (ID == 133) {
            return new GT_Container_BasicMachine(player.inventory, (BaseMetaTileEntity)tTileEntity, 0);
        }
        if (ID == 134) {
            return new GT_Container_BasicMachine(player.inventory, (BaseMetaTileEntity)tTileEntity, 0);
        }
        if (ID == 135) {
            return new GT_Container_BasicMachine(player.inventory, (BaseMetaTileEntity)tTileEntity, 0);
        }
        if (ID == 136) {
            return new GT_Container_BasicMachine(player.inventory, (BaseMetaTileEntity)tTileEntity, 0);
        }
        if (ID == 137) {
            return new GT_Container_BasicMachine(player.inventory, (BaseMetaTileEntity)tTileEntity, 0);
        }
        if (ID == 138) {
            return new GT_Container_BasicMachine(player.inventory, (BaseMetaTileEntity)tTileEntity, 0);
        }
        if (ID == 139) {
            return new GT_Container_ElectricTypeSorter(player.inventory, (BaseMetaTileEntity)tTileEntity, 0);
        }
        if (ID == 140) {
            return new GT_Container_BasicMachine(player.inventory, (BaseMetaTileEntity)tTileEntity, 0);
        }
        if (ID == 141) {
            return new GT_Container_BasicMachine(player.inventory, (BaseMetaTileEntity)tTileEntity, 0);
        }
        if (ID == 142) {
            return new GT_Container_BasicMachine(player.inventory, (BaseMetaTileEntity)tTileEntity, 0);
        }
        if (ID == 143) {
            return new GT_Container_FusionComputer(player.inventory, (BaseMetaTileEntity)tTileEntity, 0);
        }
        if (ID == 144) {
            return new GT_Container_BasicTank(player.inventory, (BaseMetaTileEntity)tTileEntity, 0);
        }
        if (ID == 145) {
            return new GT_Container_BasicTank(player.inventory, (BaseMetaTileEntity)tTileEntity, 0);
        }
        if (ID == 146) {
            return new GT_Container_Centrifuge(player.inventory, (BaseMetaTileEntity)tTileEntity, 0);
        }
        return null;
    }

    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        TileEntity tTileEntity = world.getBlockTileEntity(x, y, z);
        if (ID == 0) {
            return new GT_GUIContainerMetaID_Machine(player.inventory, (GT_TileEntityMetaID_Machine)tTileEntity, 0);
        }
        if (ID == 4) {
            return new GT_GUIContainer_ComputerCube(player.inventory, (GT_TileEntity_ComputerCube)tTileEntity, 0);
        }
        if (ID == 5) {
            return new GT_GUIContainer_UUMAssembler(player.inventory, (GT_TileEntity_UUMAssembler)tTileEntity, 0);
        }
        if (ID == 6) {
            return new GT_GUIContainer_Sonictron(player.inventory, (GT_TileEntity_Sonictron)tTileEntity, 0);
        }
        if (ID == 7) {
            return new GT_GUIContainer_LESU(player.inventory, (GT_TileEntity_LESU)tTileEntity, 0);
        }
        if (ID == 8) {
            return new GT_GUIContainer_IDSU(player.inventory, (GT_TileEntity_IDSU)tTileEntity, 0);
        }
        if (ID == 9) {
            return new GT_GUIContainer_AESU(player.inventory, (GT_TileEntity_AESU)tTileEntity, 0);
        }
        if (ID == 10) {
            return new GT_GUIContainer_ChargeOMat(player.inventory, (GT_TileEntity_ChargeOMat)tTileEntity, 0);
        }
        if (ID == 14) {
            return new GT_GUIContainer_Matterfabricator(player.inventory, (GT_TileEntity_Matterfabricator)tTileEntity, 0);
        }
        if (ID == 32) {
            return new GT_GUIContainer_ComputerCube(player.inventory, (GT_TileEntity_ComputerCube)tTileEntity, 1);
        }
        if (ID == 34) {
            return new GT_GUIContainer_ComputerCube(player.inventory, (GT_TileEntity_ComputerCube)tTileEntity, 2);
        }
        if (ID == 35) {
            return new GT_GUIContainer_ComputerCube(player.inventory, (GT_TileEntity_ComputerCube)tTileEntity, 3);
        }
        if (ID == 36) {
            return new GT_GUIContainer_ComputerCube(player.inventory, (GT_TileEntity_ComputerCube)tTileEntity, 4);
        }
        if (ID == 37) {
            return new GT_GUIContainer_ComputerCube(player.inventory, (GT_TileEntity_ComputerCube)tTileEntity, 5);
        }
        if (ID == 38) {
            return new GT_GUIContainer_ComputerCube(player.inventory, (GT_TileEntity_ComputerCube)tTileEntity, 6);
        }
        if (ID == 33) {
            return new GT_GUIContainer_Destructopack(player.inventory, player.getCurrentEquippedItem());
        }
        if (ID == 100) {
            return new GT_GUIContainer_ElectricAutoWorkbench(player.inventory, (BaseMetaTileEntity)tTileEntity, 0);
        }
        if (ID == 101) {
            return new GT_GUIContainer_Translocator(player.inventory, (BaseMetaTileEntity)tTileEntity, 0);
        }
        if (ID == 102) {
            return new GT_GUIContainer_ElectricBufferSmall(player.inventory, (BaseMetaTileEntity)tTileEntity, 0);
        }
        if (ID == 103) {
            return new GT_GUIContainer_ElectricBufferLarge(player.inventory, (BaseMetaTileEntity)tTileEntity, 0);
        }
        if (ID == 104) {
            return new GT_GUIContainer_AdvancedTranslocator(player.inventory, (BaseMetaTileEntity)tTileEntity, 0);
        }
        if (ID == 105) {
            return new GT_GUIContainer_ElectricBufferAdvanced(player.inventory, (BaseMetaTileEntity)tTileEntity, 0);
        }
        if (ID == 106) {
            return new GT_GUIContainer_RockBreaker(player.inventory, (BaseMetaTileEntity)tTileEntity, 0);
        }
        if (ID == 107) {
            return new GT_GUIContainer_ElectricSorter(player.inventory, (BaseMetaTileEntity)tTileEntity, 0);
        }
        if (ID == 108) {
            return new GT_GUIContainer_ElectricItemClearer(player.inventory, (BaseMetaTileEntity)tTileEntity, 0);
        }
        if (ID == 109) {
            return new GT_GUIContainer_Electrolyzer(player.inventory, (BaseMetaTileEntity)tTileEntity, 0);
        }
        if (ID == 110) {
            return new GT_GUIContainer_CropHarvestor(player.inventory, (BaseMetaTileEntity)tTileEntity, 0);
        }
        if (ID == 111) {
            return new GT_GUIContainer_Scrapboxinator(player.inventory, (BaseMetaTileEntity)tTileEntity, 0);
        }
        if (ID == 112) {
            return new GT_GUIContainer_Grinder(player.inventory, (BaseMetaTileEntity)tTileEntity, 0);
        }
        if (ID == 113) {
            return new GT_GUIContainer_BlastFurnace(player.inventory, (BaseMetaTileEntity)tTileEntity, 0);
        }
        if (ID == 114) {
            return new GT_GUIContainer_Quantumtank(player.inventory, (BaseMetaTileEntity)tTileEntity, 0);
        }
        if (ID == 115) {
            return new GT_GUIContainer_ImplosionCompressor(player.inventory, (BaseMetaTileEntity)tTileEntity, 0);
        }
        if (ID == 116) {
            return new GT_GUIContainer_Sawmill(player.inventory, (BaseMetaTileEntity)tTileEntity, 0);
        }
        if (ID == 117) {
            return new GT_GUIContainer_DieselGenerator(player.inventory, (BaseMetaTileEntity)tTileEntity, 0);
        }
        if (ID == 118) {
            return new GT_GUIContainer_GasTurbine(player.inventory, (BaseMetaTileEntity)tTileEntity, 0);
        }
        if (ID == 119) {
            return new GT_GUIContainer_ThermalGenerator(player.inventory, (BaseMetaTileEntity)tTileEntity, 0);
        }
        if (ID == 120) {
            return new GT_GUIContainer_SemifluidGenerator(player.inventory, (BaseMetaTileEntity)tTileEntity, 0);
        }
        if (ID == 121) {
            return new GT_GUIContainer_PlasmaGenerator(player.inventory, (BaseMetaTileEntity)tTileEntity, 0);
        }
        if (ID == 122) {
            return new GT_GUIContainer_VacuumFreezer(player.inventory, (BaseMetaTileEntity)tTileEntity, 0);
        }
        if (ID == 123) {
            return new GT_GUIContainer_ElectricRegulatorAdvanced(player.inventory, (BaseMetaTileEntity)tTileEntity, 0);
        }
        if (ID == 124) {
            return new GT_GUIContainer_ChemicalReactor(player.inventory, (BaseMetaTileEntity)tTileEntity, 0);
        }
        if (ID == 125) {
            return new GT_GUIContainer_MagicEnergyConverter(player.inventory, (BaseMetaTileEntity)tTileEntity, 0);
        }
        if (ID == 126) {
            return new GT_GUIContainer_MagicEnergyAbsorber(player.inventory, (BaseMetaTileEntity)tTileEntity, 0);
        }
        if (ID == 127) {
            return new GT_GUIContainer_DistillationTower(player.inventory, (BaseMetaTileEntity)tTileEntity, 0);
        }
        if (ID == 128) {
            return new GT_GUIContainer_Safe(player.inventory, (BaseMetaTileEntity)tTileEntity, 0);
        }
        if (ID == 129) {
            return new GT_GUIContainer_ElectricInventoryManager(player.inventory, (BaseMetaTileEntity)tTileEntity, 0);
        }
        if (ID == 130) {
            return new GT_GUIContainer_AdvancedPump(player.inventory, (BaseMetaTileEntity)tTileEntity, 0);
        }
        if (ID == 131) {
            return new GT_GUIContainer_BasicMachine_Macerator(player.inventory, (BaseMetaTileEntity)tTileEntity, 0, "Automatic Macerator", "/gregtechmod/textures/gui/Macerator.png");
        }
        if (ID == 132) {
            return new GT_GUIContainer_BasicMachine_Extractor(player.inventory, (BaseMetaTileEntity)tTileEntity, 0, "Automatic Extractor", "/gregtechmod/textures/gui/Extractor.png");
        }
        if (ID == 133) {
            return new GT_GUIContainer_BasicMachine_Compressor(player.inventory, (BaseMetaTileEntity)tTileEntity, 0, "Automatic Compressor", "/gregtechmod/textures/gui/Compressor.png");
        }
        if (ID == 134) {
            return new GT_GUIContainer_BasicMachine(player.inventory, (BaseMetaTileEntity)tTileEntity, 0, "Automatic Recycler", "/gregtechmod/textures/gui/Recycler.png");
        }
        if (ID == 135) {
            return new GT_GUIContainer_BasicMachine_E_Furnace(player.inventory, (BaseMetaTileEntity)tTileEntity, 0, "Automatic E-Furnace", "/gregtechmod/textures/gui/E_Furnace.png");
        }
        if (ID == 136) {
            return new GT_GUIContainer_BasicMachine_Wiremill(player.inventory, (BaseMetaTileEntity)tTileEntity, 0, "Automatic Wiremill", "/gregtechmod/textures/gui/Wiremill.png");
        }
        if (ID == 137) {
            return new GT_GUIContainer_BasicMachine_AlloySmelter(player.inventory, (BaseMetaTileEntity)tTileEntity, 0, "Alloy Smelter", "/gregtechmod/textures/gui/E_Furnace.png");
        }
        if (ID == 138) {
            return new GT_GUIContainer_BasicMachine_Canner(player.inventory, (BaseMetaTileEntity)tTileEntity, 0, "Automatic Canning Machine", "/gregtechmod/textures/gui/Canner.png");
        }
        if (ID == 139) {
            return new GT_GUIContainer_ElectricTypeSorter(player.inventory, (BaseMetaTileEntity)tTileEntity, 0);
        }
        if (ID == 140) {
            return new GT_GUIContainer_BasicMachine_Bender(player.inventory, (BaseMetaTileEntity)tTileEntity, 0, "Plate Bending Machine", "/gregtechmod/textures/gui/Bender.png");
        }
        if (ID == 141) {
            return new GT_GUIContainer_BasicMachine_Assembler(player.inventory, (BaseMetaTileEntity)tTileEntity, 0, "Assembling Machine", "/gregtechmod/textures/gui/Assembler.png");
        }
        if (ID == 142) {
            return new GT_GUIContainer_BasicMachine(player.inventory, (BaseMetaTileEntity)tTileEntity, 0, "Printing Factory", "/gregtechmod/textures/gui/Printer.png");
        }
        if (ID == 143) {
            return new GT_GUIContainer_FusionComputer(player.inventory, (BaseMetaTileEntity)tTileEntity, 0);
        }
        if (ID == 144) {
            return new GT_GUIContainer_FusionInjector(player.inventory, (BaseMetaTileEntity)tTileEntity, 0);
        }
        if (ID == 145) {
            return new GT_GUIContainer_FusionExtractor(player.inventory, (BaseMetaTileEntity)tTileEntity, 0);
        }
        if (ID == 146) {
            return new GT_GUIContainer_Centrifuge(player.inventory, (BaseMetaTileEntity)tTileEntity, 0);
        }
        return null;
    }
}

