/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  codechicken.nei.api.IConfigureNEI
 */
package gregtechmod.mistaqur.nei;

import codechicken.nei.api.IConfigureNEI;
import gregtechmod.mistaqur.nei.AlloySmelterRecipeHandler;
import gregtechmod.mistaqur.nei.AssemblerRecipeHandler;
import gregtechmod.mistaqur.nei.BenderRecipeHandler;
import gregtechmod.mistaqur.nei.BlastRecipeHandler;
import gregtechmod.mistaqur.nei.CannerRecipeHandler;
import gregtechmod.mistaqur.nei.CentrifugeRecipeHandler;
import gregtechmod.mistaqur.nei.ChemicalRecipeHandler;
import gregtechmod.mistaqur.nei.DenseLiquidFuelsHandler;
import gregtechmod.mistaqur.nei.DieselFuelsHandler;
import gregtechmod.mistaqur.nei.DistillationRecipeHandler;
import gregtechmod.mistaqur.nei.ElectrolyzerRecipeHandler;
import gregtechmod.mistaqur.nei.FusionRecipeHandler;
import gregtechmod.mistaqur.nei.GrinderRecipeHandler;
import gregtechmod.mistaqur.nei.HotFuelsHandler;
import gregtechmod.mistaqur.nei.ImplosionRecipeHandler;
import gregtechmod.mistaqur.nei.MagicFuelsHandler;
import gregtechmod.mistaqur.nei.PlasmaFuelsHandler;
import gregtechmod.mistaqur.nei.SawmillRecipeHandler;
import gregtechmod.mistaqur.nei.TurbineFuelsHandler;
import gregtechmod.mistaqur.nei.VacuumFreezerRecipeHandler;
import gregtechmod.mistaqur.nei.WiremillRecipeHandler;

public class NEI_GregTech_Config
implements IConfigureNEI {
    public void loadConfig() {
        new CentrifugeRecipeHandler();
        new ElectrolyzerRecipeHandler();
        new ChemicalRecipeHandler();
        new VacuumFreezerRecipeHandler();
        new GrinderRecipeHandler();
        new BlastRecipeHandler();
        new SawmillRecipeHandler();
        new ImplosionRecipeHandler();
        new FusionRecipeHandler();
        new DistillationRecipeHandler();
        new WiremillRecipeHandler();
        new AlloySmelterRecipeHandler();
        new CannerRecipeHandler();
        new BenderRecipeHandler();
        new AssemblerRecipeHandler();
        new DieselFuelsHandler();
        new TurbineFuelsHandler();
        new HotFuelsHandler();
        new DenseLiquidFuelsHandler();
        new PlasmaFuelsHandler();
        new MagicFuelsHandler();
    }

    public String getName() {
        return "GregTech NEI Plugin";
    }

    public String getVersion() {
        return "(2.90h)";
    }
}

