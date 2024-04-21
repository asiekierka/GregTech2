/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.InventoryPlayer
 *  net.minecraft.util.StatCollector
 *  org.lwjgl.opengl.GL11
 */
package gregtechmod.common.gui;

import gregtechmod.api.GT_Recipe;
import gregtechmod.common.GT_ComputercubeDescription;
import gregtechmod.common.containers.GT_Container_ComputerCube;
import gregtechmod.common.gui.GT_GUIContainerMetaID_Machine;
import gregtechmod.common.tileentities.GT_TileEntityMetaID_Machine;
import gregtechmod.common.tileentities.GT_TileEntity_ComputerCube;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.StatCollector;
import org.lwjgl.opengl.GL11;

public class GT_GUIContainer_ComputerCube
extends GT_GUIContainerMetaID_Machine {
    public GT_GUIContainer_ComputerCube(InventoryPlayer aInventoryPlayer, GT_TileEntity_ComputerCube aTileEntity, int aID) {
        super(new GT_Container_ComputerCube(aInventoryPlayer, aTileEntity, aID), (GT_TileEntityMetaID_Machine)aTileEntity, aID);
        if (aID == 5) {
            this.xSize += 50;
        }
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int par1, int par2) {
        GT_Container_ComputerCube tContainer = (GT_Container_ComputerCube)this.mContainer;
        GT_TileEntity_ComputerCube tTileEntity = (GT_TileEntity_ComputerCube)this.mTileEntity;
        if (tContainer != null) {
            switch (tContainer.mID) {
                case 0: {
                    this.fontRenderer.drawString("G.L.A.D.-OS", 64, 61, 0xFAFAFF);
                    this.fontRenderer.drawString(StatCollector.translateToLocal((String)"container.inventory"), 8, this.ySize - 96 + 2, 0x404040);
                    break;
                }
                case 1: {
                    this.fontRenderer.drawString("Reactorstats:", 7, 108, 0xFAFAFF);
                    this.fontRenderer.drawString(this.toNumber(tContainer.mEU) + "EU at " + tContainer.mEUOut + "EU/t", 7, 120, 0xFAFAFF);
                    this.fontRenderer.drawString("HEM: " + (float)tContainer.mHEM / 10000.0f, 7, 128, 0xFAFAFF);
                    this.fontRenderer.drawString(this.toNumber(tContainer.mHeat) + "/" + this.toNumber(tContainer.mMaxHeat) + "Heat", 7, 136, 0xFAFAFF);
                    this.fontRenderer.drawString("Explosionpower: " + (float)tContainer.mExplosionStrength / 100.0f, 7, 144, 0xFAFAFF);
                    this.fontRenderer.drawString("Runtime: " + (tContainer.mEUOut > 0 ? (float)(tContainer.mEU / tContainer.mEUOut) / 20.0f : 0.0f) + "secs", 7, 152, 0xFAFAFF);
                    break;
                }
                case 2: {
                    this.fontRenderer.drawString("Scanner", 51, 7, 0xFAFAFF);
                    if (tContainer.mProgress == 0) {
                        this.fontRenderer.drawString("Can be used to", 51, 24, 0xFAFAFF);
                        this.fontRenderer.drawString("scan Seedbags", 51, 32, 0xFAFAFF);
                    } else {
                        this.fontRenderer.drawString("Progress:", 51, 24, 0xFAFAFF);
                        this.fontRenderer.drawString(tContainer.mProgress + "%", 51, 32, 0xFAFAFF);
                    }
                    this.fontRenderer.drawString(StatCollector.translateToLocal((String)"container.inventory"), 8, this.ySize - 96 + 2, 0x404040);
                    break;
                }
                case 3: {
                    this.fontRenderer.drawString("Centrifuge", 7, 7, 0xFAFAFF);
                    this.fontRenderer.drawString("Recipe: " + (tContainer.mMaxHeat + 1) + "/" + GT_Recipe.sCentrifugeRecipes.size(), 7, 23, 0xFAFAFF);
                    this.fontRenderer.drawString("EU: " + this.toNumber(tContainer.mEU), 7, 31, 0xFAFAFF);
                    break;
                }
                case 4: {
                    this.fontRenderer.drawString("Fusionreactor", 7, 7, 0xFAFAFF);
                    this.fontRenderer.drawString("Recipe: " + (tContainer.mMaxHeat + 1) + "/" + GT_Recipe.sFusionRecipes.size(), 7, 23, 0xFAFAFF);
                    this.fontRenderer.drawString("Start: " + this.toNumber(tContainer.mEU) + "EU", 7, 31, 0xFAFAFF);
                    this.fontRenderer.drawString("EU/t: " + this.toNumber(tContainer.mEUOut), 7, 39, 0xFAFAFF);
                    this.fontRenderer.drawString(this.toNumber(tContainer.mHeat) + " Ticks", 7, 47, 0xFAFAFF);
                    if (tContainer.mEUOut < 0) {
                        this.fontRenderer.drawString("IN: " + this.toNumber(-tContainer.mEUOut * tContainer.mHeat) + "EU", 7, 55, 0xFAFAFF);
                        break;
                    }
                    this.fontRenderer.drawString("OUT: " + this.toNumber(tContainer.mEUOut * tContainer.mHeat) + "EU", 7, 55, 0xFAFAFF);
                    break;
                }
                case 5: {
                    if (tContainer.mMaxHeat < 0 || tContainer.mMaxHeat >= GT_ComputercubeDescription.sDescriptions.size()) break;
                    for (int i = 0; i < ((GT_ComputercubeDescription)GT_ComputercubeDescription.sDescriptions.get((int)tContainer.mMaxHeat)).mDescription.length; ++i) {
                        if (i == 0) {
                            this.fontRenderer.drawString(((GT_ComputercubeDescription)GT_ComputercubeDescription.sDescriptions.get((int)tContainer.mMaxHeat)).mDescription[i], 7, 7, 0xFAFAFF);
                            continue;
                        }
                        this.fontRenderer.drawString(((GT_ComputercubeDescription)GT_ComputercubeDescription.sDescriptions.get((int)tContainer.mMaxHeat)).mDescription[i], 7, 7 + 8 * i, 0xFAFAFF);
                    }
                    break;
                }
                case 6: {
                    this.fontRenderer.drawString("Electrolyzer", 7, 7, 0xFAFAFF);
                    this.fontRenderer.drawString("Recipe: " + (tContainer.mMaxHeat + 1) + "/" + GT_Recipe.sElectrolyzerRecipes.size(), 7, 23, 0xFAFAFF);
                    this.fontRenderer.drawString("EU: " + this.toNumber(tContainer.mEU), 7, 31, 0xFAFAFF);
                }
            }
        }
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3) {
        GT_Container_ComputerCube tContainer = (GT_Container_ComputerCube)this.mContainer;
        if (tContainer != null) {
            int texture = this.mc.renderEngine.getTexture("/gregtechmod/textures/gui/ComputerCube" + ((GT_Container_ComputerCube)this.mContainer).mID + ".png");
            GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
            this.mc.renderEngine.bindTexture(texture);
            int x = (this.width - this.xSize) / 2;
            int y = (this.height - this.ySize) / 2;
            this.drawTexturedModalRect(x, y, 0, 0, this.xSize, this.ySize);
            switch (tContainer.mID) {
                case 0: {
                    break;
                }
                case 1: {
                    break;
                }
                case 2: {
                    break;
                }
                case 3: {
                    break;
                }
                case 4: {
                    break;
                }
                case 5: {
                    if (tContainer.mExplosionStrength != 0) {
                        this.drawTexturedModalRect(x + 152, y + 6, 0, 166, 50, 50);
                    }
                    if (tContainer.mMaxHeat != 0) break;
                    this.drawTexturedModalRect(x + 145, y + 56, 50, 166, 57, 57);
                }
            }
        }
    }
}

