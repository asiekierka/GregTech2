/*
 * Decompiled with CFR 0.152.
 */
package gregtechmod.common.tileentities;

import gregtechmod.api.BaseMetaTileEntity;
import gregtechmod.api.MetaTileEntity;
import gregtechmod.common.tileentities.GT_MetaTileEntity_Shelf;

public class GT_MetaTileEntity_Shelf_FileCabinet
extends GT_MetaTileEntity_Shelf {
    public GT_MetaTileEntity_Shelf_FileCabinet(int aID, String mName, String mNameRegional) {
        super(aID, mName, mNameRegional);
    }

    public GT_MetaTileEntity_Shelf_FileCabinet() {
    }

    @Override
    public MetaTileEntity newMetaEntity(BaseMetaTileEntity aTileEntity) {
        return new GT_MetaTileEntity_Shelf_FileCabinet();
    }

    @Override
    public int getTextureIndex(int aSide, int aFacing, boolean aActive, boolean aRedstone) {
        if (aSide == aFacing) {
            return 223;
        }
        if (aSide == 0) {
            return 32;
        }
        if (aSide == 1) {
            return 29;
        }
        return 40;
    }
}

