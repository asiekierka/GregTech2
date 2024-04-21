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
import gregtechmod.common.gui.GT_GUIContainer_Centrifuge;
import gregtechmod.mistaqur.nei.GT_RecipeHandler;
import java.awt.Rectangle;
import java.util.ArrayList;

public class CentrifugeRecipeHandler
extends GT_RecipeHandler {
    @Override
    public void loadTransferRects() {
        try {
            this.transferRects.add(new TemplateRecipeHandler.RecipeTransferRect(new Rectangle(75, 18, 18, 12), this.getRecipeId(), new Object[0]));
            this.transferRects.add(new TemplateRecipeHandler.RecipeTransferRect(new Rectangle(63, 30, 12, 18), this.getRecipeId(), new Object[0]));
            this.transferRects.add(new TemplateRecipeHandler.RecipeTransferRect(new Rectangle(93, 30, 12, 18), this.getRecipeId(), new Object[0]));
            this.transferRects.add(new TemplateRecipeHandler.RecipeTransferRect(new Rectangle(75, 48, 18, 12), this.getRecipeId(), new Object[0]));
            ArrayList<Class<GT_GUIContainer_Centrifuge>> guis = new ArrayList<Class<GT_GUIContainer_Centrifuge>>();
            ArrayList<TemplateRecipeHandler.RecipeTransferRect> transferRects2 = new ArrayList<TemplateRecipeHandler.RecipeTransferRect>();
            guis.add(GT_GUIContainer_Centrifuge.class);
            transferRects2.add(new TemplateRecipeHandler.RecipeTransferRect(new Rectangle(74, 11, 18, 12), this.getRecipeId(), new Object[0]));
            transferRects2.add(new TemplateRecipeHandler.RecipeTransferRect(new Rectangle(62, 23, 12, 18), this.getRecipeId(), new Object[0]));
            transferRects2.add(new TemplateRecipeHandler.RecipeTransferRect(new Rectangle(92, 23, 12, 18), this.getRecipeId(), new Object[0]));
            transferRects2.add(new TemplateRecipeHandler.RecipeTransferRect(new Rectangle(74, 41, 18, 12), this.getRecipeId(), new Object[0]));
            TemplateRecipeHandler.RecipeTransferRectHandler.registerRectsToGuis(guis, transferRects2);
        }
        catch (Throwable throwable) {
            // empty catch block
        }
    }

    @Override
    public String getRecipeName() {
        return "Industrial Centrifuge";
    }

    @Override
    public String getRecipeId() {
        return "gregtech.centrifuge";
    }

    @Override
    public String getGuiTexture() {
        return "/gregtechmod/textures/gui/NEICentrifuge.png";
    }

    @Override
    public String getOverlayIdentifier() {
        return "gregtech.centrifuge";
    }

    @Override
    public ArrayList getRecipeList() {
        return GT_Recipe.sCentrifugeRecipes;
    }

    @Override
    public GT_RecipeHandler.CachedGT_Recipe getRecipe(GT_Recipe irecipe) {
        return new CachedCentrifugeRecipe(irecipe);
    }

    public void drawExtras(GuiContainerManager gui, int recipe) {
        Integer time = ((CachedCentrifugeRecipe)((Object)this.arecipes.get((int)recipe))).mDuration;
        gui.drawText(30, 80, "EU: " + this.toNumber(time * 5), -16777216, false);
        gui.drawText(30, 90, "Time: " + this.toNumber(time / 20) + " secs", -16777216, false);
    }

    public class CachedCentrifugeRecipe
    extends GT_RecipeHandler.CachedGT_Recipe {
        public int mDuration;

        public CachedCentrifugeRecipe(GT_Recipe aRecipe) {
            int xoffset = 4;
            int yoffset = 4;
            this.resources = new ArrayList();
            if (aRecipe.mInput1 != null) {
                this.resources.add(new PositionedStack((Object)aRecipe.mInput1, 80 - xoffset, 35 - yoffset));
            }
            if (aRecipe.mInput2 != null) {
                this.resources.add(new PositionedStack((Object)aRecipe.mInput2, 50 - xoffset, 5 - yoffset));
            }
            this.products = new ArrayList();
            if (aRecipe.mOutput1 != null) {
                this.products.add(new PositionedStack((Object)aRecipe.mOutput1, 80 - xoffset, 5 - yoffset));
            }
            if (aRecipe.mOutput2 != null) {
                this.products.add(new PositionedStack((Object)aRecipe.mOutput2, 110 - xoffset, 35 - yoffset));
            }
            if (aRecipe.mOutput3 != null) {
                this.products.add(new PositionedStack((Object)aRecipe.mOutput3, 80 - xoffset, 65 - yoffset));
            }
            if (aRecipe.mOutput4 != null) {
                this.products.add(new PositionedStack((Object)aRecipe.mOutput4, 50 - xoffset, 35 - yoffset));
            }
            this.mDuration = aRecipe.mDuration;
        }
    }
}

