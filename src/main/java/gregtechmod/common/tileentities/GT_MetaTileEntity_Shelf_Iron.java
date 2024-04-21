/*
 * Decompiled with CFR 0.152.
 */
package gregtechmod.common.tileentities;

import gregtechmod.api.BaseMetaTileEntity;
import gregtechmod.api.MetaTileEntity;
import gregtechmod.common.tileentities.GT_MetaTileEntity_Shelf;

public class GT_MetaTileEntity_Shelf_Iron
extends GT_MetaTileEntity_Shelf {
    public GT_MetaTileEntity_Shelf_Iron(int aID, String mName, String mNameRegional) {
        super(aID, mName, mNameRegional);
    }

    public GT_MetaTileEntity_Shelf_Iron() {
    }

    @Override
    public MetaTileEntity newMetaEntity(BaseMetaTileEntity aTileEntity) {
        return new GT_MetaTileEntity_Shelf_Iron();
    }

    @Override
    public int getTextureIndex(int aSide, int aFacing, boolean aActive, boolean aRedstone) {
        if (aSide == aFacing) {
            return 216 + this.mType;
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

