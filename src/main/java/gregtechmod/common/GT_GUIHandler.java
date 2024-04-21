package gregtechmod.common;

import gregtechmod.api.BaseMetaTileEntity;
import gregtechmod.common.containers.GT_ContainerMetaID_Machine;
import gregtechmod.common.containers.GT_Container_AESU;
import gregtechmod.common.containers.GT_Container_AdvancedTranslocator;
import gregtechmod.common.containers.GT_Container_BlastFurnace;
import gregtechmod.common.containers.GT_Container_Centrifuge;
import gregtechmod.common.containers.GT_Container_ChargeOMat;
import gregtechmod.common.containers.GT_Container_ComputerCube;
import gregtechmod.common.containers.GT_Container_CropHarvestor;
import gregtechmod.common.containers.GT_Container_DieselGenerator;
import gregtechmod.common.containers.GT_Container_ElectricAutoWorkbench;
import gregtechmod.common.containers.GT_Container_ElectricBufferAdvanced;
import gregtechmod.common.containers.GT_Container_ElectricBufferLarge;
import gregtechmod.common.containers.GT_Container_ElectricBufferSmall;
import gregtechmod.common.containers.GT_Container_ElectricItemClearer;
import gregtechmod.common.containers.GT_Container_ElectricSorter;
import gregtechmod.common.containers.GT_Container_Electrolyzer;
import gregtechmod.common.containers.GT_Container_Fusionreactor;
import gregtechmod.common.containers.GT_Container_GasTurbine;
import gregtechmod.common.containers.GT_Container_Grinder;
import gregtechmod.common.containers.GT_Container_IDSU;
import gregtechmod.common.containers.GT_Container_ImplosionCompressor;
import gregtechmod.common.containers.GT_Container_Item_Destructopack;
import gregtechmod.common.containers.GT_Container_LESU;
import gregtechmod.common.containers.GT_Container_Matterfabricator;
import gregtechmod.common.containers.GT_Container_Quantumchest;
import gregtechmod.common.containers.GT_Container_Quantumtank;
import gregtechmod.common.containers.GT_Container_RockBreaker;
import gregtechmod.common.containers.GT_Container_Sawmill;
import gregtechmod.common.containers.GT_Container_Scrapboxinator;
import gregtechmod.common.containers.GT_Container_SemifluidGenerator;
import gregtechmod.common.containers.GT_Container_Sonictron;
import gregtechmod.common.containers.GT_Container_ThermalGenerator;
import gregtechmod.common.containers.GT_Container_Translocator;
import gregtechmod.common.containers.GT_Container_UUMAssembler;
import gregtechmod.common.gui.GT_GUIContainerMetaID_Machine;
import gregtechmod.common.gui.GT_GUIContainer_AESU;
import gregtechmod.common.gui.GT_GUIContainer_AdvancedTranslocator;
import gregtechmod.common.gui.GT_GUIContainer_BlastFurnace;
import gregtechmod.common.gui.GT_GUIContainer_Centrifuge;
import gregtechmod.common.gui.GT_GUIContainer_ChargeOMat;
import gregtechmod.common.gui.GT_GUIContainer_ComputerCube;
import gregtechmod.common.gui.GT_GUIContainer_CropHarvestor;
import gregtechmod.common.gui.GT_GUIContainer_Destructopack;
import gregtechmod.common.gui.GT_GUIContainer_DieselGenerator;
import gregtechmod.common.gui.GT_GUIContainer_ElectricAutoWorkbench;
import gregtechmod.common.gui.GT_GUIContainer_ElectricBufferAdvanced;
import gregtechmod.common.gui.GT_GUIContainer_ElectricBufferLarge;
import gregtechmod.common.gui.GT_GUIContainer_ElectricBufferSmall;
import gregtechmod.common.gui.GT_GUIContainer_ElectricItemClearer;
import gregtechmod.common.gui.GT_GUIContainer_ElectricSorter;
import gregtechmod.common.gui.GT_GUIContainer_Electrolyzer;
import gregtechmod.common.gui.GT_GUIContainer_Fusionreactor;
import gregtechmod.common.gui.GT_GUIContainer_GasTurbine;
import gregtechmod.common.gui.GT_GUIContainer_Grinder;
import gregtechmod.common.gui.GT_GUIContainer_IDSU;
import gregtechmod.common.gui.GT_GUIContainer_ImplosionCompressor;
import gregtechmod.common.gui.GT_GUIContainer_LESU;
import gregtechmod.common.gui.GT_GUIContainer_Matterfabricator;
import gregtechmod.common.gui.GT_GUIContainer_Quantumchest;
import gregtechmod.common.gui.GT_GUIContainer_Quantumtank;
import gregtechmod.common.gui.GT_GUIContainer_RockBreaker;
import gregtechmod.common.gui.GT_GUIContainer_Sawmill;
import gregtechmod.common.gui.GT_GUIContainer_Scrapboxinator;
import gregtechmod.common.gui.GT_GUIContainer_SemifluidGenerator;
import gregtechmod.common.gui.GT_GUIContainer_Sonictron;
import gregtechmod.common.gui.GT_GUIContainer_ThermalGenerator;
import gregtechmod.common.gui.GT_GUIContainer_Translocator;
import gregtechmod.common.gui.GT_GUIContainer_UUMAssembler;
import gregtechmod.common.tileentities.GT_TileEntityMetaID_Machine;
import gregtechmod.common.tileentities.GT_TileEntity_AESU;
import gregtechmod.common.tileentities.GT_TileEntity_Centrifuge;
import gregtechmod.common.tileentities.GT_TileEntity_ChargeOMat;
import gregtechmod.common.tileentities.GT_TileEntity_ComputerCube;
import gregtechmod.common.tileentities.GT_TileEntity_Fusionreactor;
import gregtechmod.common.tileentities.GT_TileEntity_IDSU;
import gregtechmod.common.tileentities.GT_TileEntity_LESU;
import gregtechmod.common.tileentities.GT_TileEntity_Matterfabricator;
import gregtechmod.common.tileentities.GT_TileEntity_Quantumchest;
import gregtechmod.common.tileentities.GT_TileEntity_Sonictron;
import gregtechmod.common.tileentities.GT_TileEntity_UUMAssembler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.IGuiHandler;

public class GT_GUIHandler implements IGuiHandler {
	
	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        TileEntity tTileEntity = world.getBlockTileEntity(x, y, z);
        if (ID ==   0) return new GT_ContainerMetaID_Machine			(player.inventory, (GT_TileEntityMetaID_Machine)tTileEntity, 0);
        if (ID ==   1) return new GT_Container_Fusionreactor			(player.inventory, (GT_TileEntity_Fusionreactor)tTileEntity, 0);
        if (ID ==   3) return new GT_Container_Quantumchest				(player.inventory, (GT_TileEntity_Quantumchest)		tTileEntity, 0);
        if (ID ==   4) return new GT_Container_ComputerCube				(player.inventory, (GT_TileEntity_ComputerCube)		tTileEntity, 0);
        if (ID ==   5) return new GT_Container_UUMAssembler				(player.inventory, (GT_TileEntity_UUMAssembler)		tTileEntity, 0);
        if (ID ==   6) return new GT_Container_Sonictron				(player.inventory, (GT_TileEntity_Sonictron)	tTileEntity, 0);
        if (ID ==   7) return new GT_Container_LESU						(player.inventory, (GT_TileEntity_LESU)				tTileEntity, 0);
        if (ID ==   8) return new GT_Container_IDSU						(player.inventory, (GT_TileEntity_IDSU)				tTileEntity, 0);
        if (ID ==   9) return new GT_Container_AESU						(player.inventory, (GT_TileEntity_AESU)				tTileEntity, 0);
        if (ID ==  10) return new GT_Container_ChargeOMat				(player.inventory, (GT_TileEntity_ChargeOMat)		tTileEntity, 0);
        if (ID ==  11) return new GT_Container_Centrifuge				(player.inventory, (GT_TileEntity_Centrifuge)		tTileEntity, 0);
        if (ID ==  14) return new GT_Container_Matterfabricator			(player.inventory, (GT_TileEntity_Matterfabricator)	tTileEntity, 0);

        if (ID ==  32) return new GT_Container_ComputerCube				(player.inventory, (GT_TileEntity_ComputerCube)		tTileEntity, 1);
        if (ID ==  34) return new GT_Container_ComputerCube				(player.inventory, (GT_TileEntity_ComputerCube)		tTileEntity, 2);
        if (ID ==  35) return new GT_Container_ComputerCube				(player.inventory, (GT_TileEntity_ComputerCube)		tTileEntity, 3);
        if (ID ==  36) return new GT_Container_ComputerCube				(player.inventory, (GT_TileEntity_ComputerCube)		tTileEntity, 4);
        if (ID ==  37) return new GT_Container_ComputerCube				(player.inventory, (GT_TileEntity_ComputerCube)		tTileEntity, 5);
        if (ID ==  38) return new GT_Container_ComputerCube				(player.inventory, (GT_TileEntity_ComputerCube)		tTileEntity, 6);

        if (ID ==  33) return new GT_Container_Item_Destructopack		(player.inventory, player.getCurrentEquippedItem());
        
        if (ID == 100) return new GT_Container_ElectricAutoWorkbench	(player.inventory, (BaseMetaTileEntity)				tTileEntity, 0);
        if (ID == 101) return new GT_Container_Translocator				(player.inventory, (BaseMetaTileEntity)				tTileEntity, 0);
        if (ID == 102) return new GT_Container_ElectricBufferSmall		(player.inventory, (BaseMetaTileEntity)				tTileEntity, 0);
        if (ID == 103) return new GT_Container_ElectricBufferLarge		(player.inventory, (BaseMetaTileEntity)				tTileEntity, 0);
        if (ID == 104) return new GT_Container_AdvancedTranslocator		(player.inventory, (BaseMetaTileEntity)				tTileEntity, 0);
        if (ID == 105) return new GT_Container_ElectricBufferAdvanced	(player.inventory, (BaseMetaTileEntity)				tTileEntity, 0);
        if (ID == 106) return new GT_Container_RockBreaker				(player.inventory, (BaseMetaTileEntity)				tTileEntity, 0);
        if (ID == 107) return new GT_Container_ElectricSorter			(player.inventory, (BaseMetaTileEntity)				tTileEntity, 0);
        if (ID == 108) return new GT_Container_ElectricItemClearer		(player.inventory, (BaseMetaTileEntity)				tTileEntity, 0);
        if (ID == 109) return new GT_Container_Electrolyzer				(player.inventory, (BaseMetaTileEntity)				tTileEntity, 0);
        if (ID == 110) return new GT_Container_CropHarvestor			(player.inventory, (BaseMetaTileEntity)				tTileEntity, 0);
        if (ID == 111) return new GT_Container_Scrapboxinator			(player.inventory, (BaseMetaTileEntity)				tTileEntity, 0);
        if (ID == 112) return new GT_Container_Grinder					(player.inventory, (BaseMetaTileEntity)				tTileEntity, 0);
        if (ID == 113) return new GT_Container_BlastFurnace				(player.inventory, (BaseMetaTileEntity)				tTileEntity, 0);
        if (ID == 114) return new GT_Container_Quantumtank				(player.inventory, (BaseMetaTileEntity)				tTileEntity, 0);
        if (ID == 115) return new GT_Container_ImplosionCompressor		(player.inventory, (BaseMetaTileEntity)				tTileEntity, 0);
        if (ID == 116) return new GT_Container_Sawmill					(player.inventory, (BaseMetaTileEntity)				tTileEntity, 0);
        if (ID == 117) return new GT_Container_DieselGenerator			(player.inventory, (BaseMetaTileEntity)				tTileEntity, 0);
        if (ID == 118) return new GT_Container_GasTurbine				(player.inventory, (BaseMetaTileEntity)				tTileEntity, 0);
        if (ID == 119) return new GT_Container_ThermalGenerator			(player.inventory, (BaseMetaTileEntity)				tTileEntity, 0);
        if (ID == 120) return new GT_Container_SemifluidGenerator		(player.inventory, (BaseMetaTileEntity)				tTileEntity, 0);
        return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        TileEntity tTileEntity = world.getBlockTileEntity(x, y, z);
        if (ID ==   0) return new GT_GUIContainerMetaID_Machine			(player.inventory, (GT_TileEntityMetaID_Machine)	tTileEntity, 0);
        if (ID ==   1) return new GT_GUIContainer_Fusionreactor			(player.inventory, (GT_TileEntity_Fusionreactor)	tTileEntity, 0);
        if (ID ==   3) return new GT_GUIContainer_Quantumchest 			(player.inventory, (GT_TileEntity_Quantumchest) 	tTileEntity, 0);
        if (ID ==   4) return new GT_GUIContainer_ComputerCube			(player.inventory, (GT_TileEntity_ComputerCube)		tTileEntity, 0);
        if (ID ==   5) return new GT_GUIContainer_UUMAssembler 			(player.inventory, (GT_TileEntity_UUMAssembler) 	tTileEntity, 0);
        if (ID ==   6) return new GT_GUIContainer_Sonictron				(player.inventory, (GT_TileEntity_Sonictron)		tTileEntity, 0);
        if (ID ==   7) return new GT_GUIContainer_LESU					(player.inventory, (GT_TileEntity_LESU)				tTileEntity, 0);
        if (ID ==   8) return new GT_GUIContainer_IDSU					(player.inventory, (GT_TileEntity_IDSU)				tTileEntity, 0);
        if (ID ==   9) return new GT_GUIContainer_AESU					(player.inventory, (GT_TileEntity_AESU)				tTileEntity, 0);
        if (ID ==  10) return new GT_GUIContainer_ChargeOMat			(player.inventory, (GT_TileEntity_ChargeOMat)	tTileEntity, 0);
        if (ID ==  11) return new GT_GUIContainer_Centrifuge			(player.inventory, (GT_TileEntity_Centrifuge)	tTileEntity, 0);
        if (ID ==  14) return new GT_GUIContainer_Matterfabricator		(player.inventory, (GT_TileEntity_Matterfabricator)	tTileEntity, 0);

        if (ID ==  32) return new GT_GUIContainer_ComputerCube			(player.inventory, (GT_TileEntity_ComputerCube)		tTileEntity, 1);
        if (ID ==  34) return new GT_GUIContainer_ComputerCube			(player.inventory, (GT_TileEntity_ComputerCube)		tTileEntity, 2);
        if (ID ==  35) return new GT_GUIContainer_ComputerCube			(player.inventory, (GT_TileEntity_ComputerCube)		tTileEntity, 3);
        if (ID ==  36) return new GT_GUIContainer_ComputerCube			(player.inventory, (GT_TileEntity_ComputerCube)		tTileEntity, 4);
        if (ID ==  37) return new GT_GUIContainer_ComputerCube			(player.inventory, (GT_TileEntity_ComputerCube)		tTileEntity, 5);
        if (ID ==  38) return new GT_GUIContainer_ComputerCube			(player.inventory, (GT_TileEntity_ComputerCube)		tTileEntity, 6);

        if (ID ==  33) return new GT_GUIContainer_Destructopack			(player.inventory, player.getCurrentEquippedItem());
        
        if (ID == 100) return new GT_GUIContainer_ElectricAutoWorkbench	(player.inventory, (BaseMetaTileEntity)tTileEntity, 0);
        if (ID == 101) return new GT_GUIContainer_Translocator			(player.inventory, (BaseMetaTileEntity)tTileEntity, 0);
        if (ID == 102) return new GT_GUIContainer_ElectricBufferSmall	(player.inventory, (BaseMetaTileEntity)tTileEntity, 0);
        if (ID == 103) return new GT_GUIContainer_ElectricBufferLarge	(player.inventory, (BaseMetaTileEntity)tTileEntity, 0);
        if (ID == 104) return new GT_GUIContainer_AdvancedTranslocator	(player.inventory, (BaseMetaTileEntity)tTileEntity, 0);
        if (ID == 105) return new GT_GUIContainer_ElectricBufferAdvanced(player.inventory, (BaseMetaTileEntity)tTileEntity, 0);
        if (ID == 106) return new GT_GUIContainer_RockBreaker			(player.inventory, (BaseMetaTileEntity)tTileEntity, 0);
        if (ID == 107) return new GT_GUIContainer_ElectricSorter		(player.inventory, (BaseMetaTileEntity)tTileEntity, 0);
        if (ID == 108) return new GT_GUIContainer_ElectricItemClearer	(player.inventory, (BaseMetaTileEntity)tTileEntity, 0);
        if (ID == 109) return new GT_GUIContainer_Electrolyzer			(player.inventory, (BaseMetaTileEntity)tTileEntity, 0);
        if (ID == 110) return new GT_GUIContainer_CropHarvestor			(player.inventory, (BaseMetaTileEntity)tTileEntity, 0);
        if (ID == 111) return new GT_GUIContainer_Scrapboxinator		(player.inventory, (BaseMetaTileEntity)tTileEntity, 0);
        if (ID == 112) return new GT_GUIContainer_Grinder				(player.inventory, (BaseMetaTileEntity)tTileEntity, 0);
        if (ID == 113) return new GT_GUIContainer_BlastFurnace			(player.inventory, (BaseMetaTileEntity)tTileEntity, 0);
        if (ID == 114) return new GT_GUIContainer_Quantumtank			(player.inventory, (BaseMetaTileEntity)tTileEntity, 0);
        if (ID == 115) return new GT_GUIContainer_ImplosionCompressor	(player.inventory, (BaseMetaTileEntity)tTileEntity, 0);
        if (ID == 116) return new GT_GUIContainer_Sawmill				(player.inventory, (BaseMetaTileEntity)tTileEntity, 0);
        if (ID == 117) return new GT_GUIContainer_DieselGenerator		(player.inventory, (BaseMetaTileEntity)tTileEntity, 0);
        if (ID == 118) return new GT_GUIContainer_GasTurbine			(player.inventory, (BaseMetaTileEntity)tTileEntity, 0);
        if (ID == 119) return new GT_GUIContainer_ThermalGenerator		(player.inventory, (BaseMetaTileEntity)tTileEntity, 0);
        if (ID == 120) return new GT_GUIContainer_SemifluidGenerator	(player.inventory, (BaseMetaTileEntity)tTileEntity, 0);
        return null;
	}
}
