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
import gregtechmod.common.gui.GT_GUIContainer_DistillationTower;
import gregtechmod.mistaqur.nei.GT_RecipeHandler;
import java.awt.Rectangle;
import java.util.ArrayList;

public class DistillationRecipeHandler
extends GT_RecipeHandler {
    @Override
    public void loadTransferRects() {
        try {
            this.transferRects.add(new TemplateRecipeHandler.RecipeTransferRect(new Rectangle(76, 0, 16, 72), this.getRecipeId(), new Object[0]));
            ArrayList<Class<GT_GUIContainer_DistillationTower>> guis = new ArrayList<Class<GT_GUIContainer_DistillationTower>>();
            ArrayList<TemplateRecipeHandler.RecipeTransferRect> transferRects2 = new ArrayList<TemplateRecipeHandler.RecipeTransferRect>();
            guis.add(GT_GUIContainer_DistillationTower.class);
            transferRects2.add(new TemplateRecipeHandler.RecipeTransferRect(new Rectangle(75, -7, 16, 72), this.getRecipeId(), new Object[0]));
            TemplateRecipeHandler.RecipeTransferRectHandler.registerRectsToGuis(guis, transferRects2);
        }
        catch (Throwable throwable) {
            // empty catch block
        }
    }

    @Override
    public String getRecipeName() {
        return "Distillation Tower";
    }

    @Override
    public String getRecipeId() {
        return "gregtech.Distillation";
    }

    @Override
    public String getGuiTexture() {
        return "/gregtechmod/textures/gui/NEIDistillationTower.png";
    }

    @Override
    public String getOverlayIdentifier() {
        return "gregtech.Distillation";
    }

    @Override
    public ArrayList getRecipeList() {
        return GT_Recipe.sDistillationRecipes;
    }

    @Override
    public GT_RecipeHandler.CachedGT_Recipe getRecipe(GT_Recipe irecipe) {
        return new CachedDistillationRecipe(irecipe);
    }

    public void drawExtras(GuiContainerManager gui, int recipe) {
        Integer time = ((CachedDistillationRecipe)((Object)this.arecipes.get((int)recipe))).mDuration;
        gui.drawText(30, 80, "EU: " + this.toNumber(time * ((CachedDistillationRecipe)((Object)this.arecipes.get((int)recipe))).mEUt), -16777216, false);
        gui.drawText(30, 90, "Time: " + this.toNumber(time / 20) + " secs", -16777216, false);
        gui.drawText(30, 100, "MaxEnergy: " + this.toNumber(((CachedDistillationRecipe)((Object)this.arecipes.get((int)recipe))).mEUt) + " EU/t", -16777216, false);
    }

    public class CachedDistillationRecipe
    extends GT_RecipeHandler.CachedGT_Recipe {
        public int mDuration;
        public int mEUt;

        public CachedDistillationRecipe(GT_Recipe aRecipe) {
            int xoffset = 4;
            int yoffset = 4;
            this.resources = new ArrayList();
            if (aRecipe.mInput1 != null) {
                this.resources.add(new PositionedStack((Object)aRecipe.mInput1, 62 - xoffset, 41 - yoffset));
            }
            if (aRecipe.mInput2 != null) {
                this.resources.add(new PositionedStack((Object)aRecipe.mInput2, 62 - xoffset, 59 - yoffset));
            }
            this.products = new ArrayList();
            if (aRecipe.mOutput1 != null) {
                this.products.add(new PositionedStack((Object)aRecipe.mOutput1, 98 - xoffset, 5 - yoffset));
            }
            if (aRecipe.mOutput2 != null) {
                this.products.add(new PositionedStack((Object)aRecipe.mOutput2, 98 - xoffset, 23 - yoffset));
            }
            if (aRecipe.mOutput3 != null) {
                this.products.add(new PositionedStack((Object)aRecipe.mOutput3, 98 - xoffset, 41 - yoffset));
            }
            if (aRecipe.mOutput4 != null) {
                this.products.add(new PositionedStack((Object)aRecipe.mOutput4, 98 - xoffset, 59 - yoffset));
            }
            this.mDuration = aRecipe.mDuration;
            this.mEUt = aRecipe.mEUt;
        }
    }
}

