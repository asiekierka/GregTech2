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
import gregtechmod.common.gui.GT_GUIContainer_BasicMachine_AlloySmelter;
import gregtechmod.common.gui.GT_GUIContainer_BasicMachine_Compressor;
import gregtechmod.common.gui.GT_GUIContainer_BasicMachine_E_Furnace;
import gregtechmod.common.gui.GT_GUIContainer_BasicMachine_Extractor;
import gregtechmod.common.gui.GT_GUIContainer_BasicMachine_Macerator;
import gregtechmod.common.gui.GT_GUIContainer_Scrapboxinator;
import gregtechmod.mistaqur.nei.GT_RecipeHandler;
import java.awt.Rectangle;
import java.util.ArrayList;

public class AlloySmelterRecipeHandler
extends GT_RecipeHandler {
    @Override
    public void loadTransferRects() {
        try {
            this.transferRects.add(new TemplateRecipeHandler.RecipeTransferRect(new Rectangle(66, 20, 36, 18), this.getRecipeId(), new Object[0]));
            ArrayList<Class> guis = new ArrayList<Class>();
            ArrayList<TemplateRecipeHandler.RecipeTransferRect> transferRects2 = new ArrayList<TemplateRecipeHandler.RecipeTransferRect>();
            guis.add(GT_GUIContainer_BasicMachine_AlloySmelter.class);
            transferRects2.add(new TemplateRecipeHandler.RecipeTransferRect(new Rectangle(65, 13, 36, 18), this.getRecipeId(), new Object[0]));
            TemplateRecipeHandler.RecipeTransferRectHandler.registerRectsToGuis(guis, transferRects2);
            guis = new ArrayList();
            transferRects2 = new ArrayList();
            guis.add(GT_GUIContainer_BasicMachine_E_Furnace.class);
            transferRects2.add(new TemplateRecipeHandler.RecipeTransferRect(new Rectangle(65, 13, 36, 18), "smelting", new Object[0]));
            TemplateRecipeHandler.RecipeTransferRectHandler.registerRectsToGuis(guis, transferRects2);
            guis = new ArrayList();
            transferRects2 = new ArrayList();
            guis.add(GT_GUIContainer_BasicMachine_Macerator.class);
            transferRects2.add(new TemplateRecipeHandler.RecipeTransferRect(new Rectangle(65, 13, 36, 18), "ic2.macerator", new Object[0]));
            TemplateRecipeHandler.RecipeTransferRectHandler.registerRectsToGuis(guis, transferRects2);
            guis = new ArrayList();
            transferRects2 = new ArrayList();
            guis.add(GT_GUIContainer_BasicMachine_Extractor.class);
            transferRects2.add(new TemplateRecipeHandler.RecipeTransferRect(new Rectangle(65, 13, 36, 18), "ic2.extractor", new Object[0]));
            TemplateRecipeHandler.RecipeTransferRectHandler.registerRectsToGuis(guis, transferRects2);
            guis = new ArrayList();
            transferRects2 = new ArrayList();
            guis.add(GT_GUIContainer_BasicMachine_Compressor.class);
            transferRects2.add(new TemplateRecipeHandler.RecipeTransferRect(new Rectangle(65, 13, 36, 18), "ic2.compressor", new Object[0]));
            TemplateRecipeHandler.RecipeTransferRectHandler.registerRectsToGuis(guis, transferRects2);
            guis = new ArrayList();
            transferRects2 = new ArrayList();
            guis.add(GT_GUIContainer_Scrapboxinator.class);
            transferRects2.add(new TemplateRecipeHandler.RecipeTransferRect(new Rectangle(56, 51, 18, 18), "ic2.scrapbox", new Object[0]));
            TemplateRecipeHandler.RecipeTransferRectHandler.registerRectsToGuis(guis, transferRects2);
        }
        catch (Throwable throwable) {
            // empty catch block
        }
    }

    @Override
    public String getRecipeName() {
        return "Alloy Smelter";
    }

    @Override
    public String getRecipeId() {
        return "gregtech.Alloy";
    }

    @Override
    public String getGuiTexture() {
        return "/gregtechmod/textures/gui/NEIAlloySmelter.png";
    }

    @Override
    public String getOverlayIdentifier() {
        return "gregtech.Alloy";
    }

    @Override
    public ArrayList getRecipeList() {
        return GT_Recipe.sAlloySmelterRecipes;
    }

    @Override
    public GT_RecipeHandler.CachedGT_Recipe getRecipe(GT_Recipe irecipe) {
        return new CachedAlloySmelterRecipe(irecipe);
    }

    public void drawExtras(GuiContainerManager gui, int recipe) {
        Integer time = ((CachedAlloySmelterRecipe)((Object)this.arecipes.get((int)recipe))).mDuration;
        gui.drawText(30, 80, "EU: " + this.toNumber(time * ((CachedAlloySmelterRecipe)((Object)this.arecipes.get((int)recipe))).mEUt), -16777216, false);
        gui.drawText(30, 90, "Time: " + this.toNumber(time / 20) + " secs", -16777216, false);
        gui.drawText(30, 100, "MaxEnergy: " + this.toNumber(((CachedAlloySmelterRecipe)((Object)this.arecipes.get((int)recipe))).mEUt) + " EU/t", -16777216, false);
    }

    public class CachedAlloySmelterRecipe
    extends GT_RecipeHandler.CachedGT_Recipe {
        public int mDuration;
        public int mEUt;

        public CachedAlloySmelterRecipe(GT_Recipe aRecipe) {
            int xoffset = 4;
            int yoffset = 4;
            this.resources = new ArrayList();
            if (aRecipe.mInput1 != null) {
                this.resources.add(new PositionedStack((Object)aRecipe.mInput1, 35 - xoffset, 25 - yoffset));
            }
            if (aRecipe.mInput2 != null) {
                this.resources.add(new PositionedStack((Object)aRecipe.mInput2, 53 - xoffset, 25 - yoffset));
            }
            this.products = new ArrayList();
            if (aRecipe.mOutput1 != null) {
                this.products.add(new PositionedStack((Object)aRecipe.mOutput1, 107 - xoffset, 25 - yoffset));
            }
            this.mDuration = aRecipe.mDuration;
            this.mEUt = aRecipe.mEUt;
        }
    }
}

