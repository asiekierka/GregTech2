/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.InventoryPlayer
 */
package gregtechmod.common.gui;

import gregtechmod.api.BaseMetaTileEntity;
import gregtechmod.common.containers.GT_ContainerMetaTile_Machine;
import gregtechmod.common.gui.GT_GUIContainer;
import net.minecraft.entity.player.InventoryPlayer;

public class GT_GUIContainerMetaTile_Machine
extends GT_GUIContainer {
    public BaseMetaTileEntity mTileEntity;
    public GT_ContainerMetaTile_Machine mContainer;
    public int mID;

    public GT_GUIContainerMetaTile_Machine(GT_ContainerMetaTile_Machine aContainer, BaseMetaTileEntity aTileEntity, int aID) {
        super(aContainer);
        this.mID = aID;
        this.mTileEntity = aTileEntity;
        this.mContainer = aContainer;
    }

    public GT_GUIContainerMetaTile_Machine(InventoryPlayer aInventoryPlayer, BaseMetaTileEntity aTileEntity, int aID) {
        this(new GT_ContainerMetaTile_Machine(aInventoryPlayer, aTileEntity, aID), aTileEntity, aID);
    }
}

