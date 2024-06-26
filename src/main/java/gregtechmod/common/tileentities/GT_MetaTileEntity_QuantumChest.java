/*
 * Decompiled with CFR 0.152.
 */
package gregtechmod.common.tileentities;

import gregtechmod.GT_Mod;
import gregtechmod.api.BaseMetaTileEntity;
import gregtechmod.api.MetaTileEntity;
import gregtechmod.common.tileentities.GT_MetaTileEntity_Barrel;

public class GT_MetaTileEntity_QuantumChest
extends GT_MetaTileEntity_Barrel {
    public GT_MetaTileEntity_QuantumChest(int aID, String mName, String mNameRegional) {
        super(aID, mName, mNameRegional);
    }

    public GT_MetaTileEntity_QuantumChest() {
    }

    @Override
    public MetaTileEntity newMetaEntity(BaseMetaTileEntity aTileEntity) {
        return new GT_MetaTileEntity_QuantumChest();
    }

    @Override
    public int getMaxItemCount() {
        return GT_Mod.instance.mQuantumItemCount - 192;
    }

    @Override
    public int getTextureIndex(int aSide, int aFacing, boolean aActive, boolean aRedstone) {
        if (aSide == aFacing) {
            return 55;
        }
        if (aSide == 0) {
            return 3;
        }
        return 0;
    }

    @Override
    protected String getDescription() {
        return "2 Milliards, uhhm I mean Billions, Items to store here!";
    }
}

