/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  codechicken.nei.PositionedStack
 *  codechicken.nei.forge.GuiContainerManager
 *  codechicken.nei.recipe.TemplateRecipeHandler$RecipeTransferRect
 *  codechicken.nei.recipe.TemplateRecipeHandler$RecipeTransferRectHandler
 */
package gregtechmod.mistaqur.nei;

import codechicken.nei.PositionedStack;
import codechicken.nei.forge.GuiContainerManager;
import codechicken.nei.recipe.TemplateRecipeHandler;
import gregtechmod.api.GT_Recipe;
import gregtechmod.common.gui.GT_GUIContainer_FusionComputer;
import gregtechmod.mistaqur.nei.GT_RecipeHandler;
import java.awt.Rectangle;
import java.util.ArrayList;

public class FusionRecipeHandler
extends GT_RecipeHandler {
    @Override
    public void loadTransferRects() {
        try {
            this.transferRects.add(new TemplateRecipeHandler.RecipeTransferRect(new Rectangle(43, 30, 50, 18), this.getRecipeId(), new Object[0]));
            ArrayList<Class<GT_GUIContainer_FusionComputer>> guis = new ArrayList<Class<GT_GUIContainer_FusionComputer>>();
            ArrayList<TemplateRecipeHandler.RecipeTransferRect> transferRects2 = new ArrayList<TemplateRecipeHandler.RecipeTransferRect>();
            guis.add(GT_GUIContainer_FusionComputer.class);
            transferRects2.add(new TemplateRecipeHandler.RecipeTransferRect(new Rectangle(149, -7, 18, 18), this.getRecipeId(), new Object[0]));
            TemplateRecipeHandler.RecipeTransferRectHandler.registerRectsToGuis(guis, transferRects2);
        }
        catch (Throwable throwable) {
            // empty catch block
        }
    }

    @Override
    public String getRecipeName() {
        return "Fusionreactor";
    }

    @Override
    public String getRecipeId() {
        return "gregtech.fusion";
    }

    @Override
    public String getGuiTexture() {
        return "/gregtechmod/textures/gui/NEIFusionreactor.png";
    }

    @Override
    public String getOverlayIdentifier() {
        return "gregtech.fusion";
    }

    @Override
    public ArrayList getRecipeList() {
        return GT_Recipe.sFusionRecipes;
    }

    @Override
    public GT_RecipeHandler.CachedGT_Recipe getRecipe(GT_Recipe irecipe) {
        return new CachedFusionRecipe(irecipe);
    }

    public void drawExtras(GuiContainerManager gui, int recipe) {
        CachedFusionRecipe t = (CachedFusionRecipe)((Object)this.arecipes.get(recipe));
        gui.drawText(30, 80, "Start: " + this.toNumber(t.mStartEU) + "EU", -16777216, false);
        gui.drawText(30, 90, "EU/t: " + this.toNumber(t.mEUt), -16777216, false);
        gui.drawText(30, 100, this.toNumber(t.mDuration) + " Ticks", -16777216, false);
        if (t.mEUt < 0) {
            gui.drawText(30, 110, "IN: " + this.toNumber(-t.mEUt * t.mDuration) + "EU", -16777216, false);
        } else {
            gui.drawText(30, 110, "OUT: " + this.toNumber(t.mEUt * t.mDuration) + "EU", -16777216, false);
        }
    }

    public class CachedFusionRecipe
    extends GT_RecipeHandler.CachedGT_Recipe {
        public int mDuration;
        public int mEUt;
        public int mStartEU;

        public CachedFusionRecipe(GT_Recipe irecipe) {
            int xoffset = 4;
            int yoffset = 4;
            this.resources = new ArrayList();
            if (irecipe.mInput1 != null) {
                this.resources.add(new PositionedStack((Object)irecipe.mInput1, 48 - xoffset, 17 - yoffset));
            }
            if (irecipe.mInput2 != null) {
                this.resources.add(new PositionedStack((Object)irecipe.mInput2, 48 - xoffset, 53 - yoffset));
            }
            this.products = new ArrayList();
            if (irecipe.mOutput1 != null) {
                this.products.add(new PositionedStack((Object)irecipe.mOutput1, 108 - xoffset, 35 - yoffset));
            }
            this.mDuration = irecipe.mDuration;
            this.mEUt = irecipe.mEUt;
            this.mStartEU = irecipe.mStartEU;
        }
    }
}

