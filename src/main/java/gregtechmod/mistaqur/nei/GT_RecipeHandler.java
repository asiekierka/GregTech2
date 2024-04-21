/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  codechicken.nei.PositionedStack
 *  codechicken.nei.api.API
 *  codechicken.nei.forge.GuiContainerManager
 *  codechicken.nei.recipe.ICraftingHandler
 *  codechicken.nei.recipe.IUsageHandler
 *  codechicken.nei.recipe.TemplateRecipeHandler
 *  codechicken.nei.recipe.TemplateRecipeHandler$CachedRecipe
 *  net.minecraft.item.ItemStack
 *  org.lwjgl.opengl.GL11
 */
package gregtechmod.mistaqur.nei;

import codechicken.nei.PositionedStack;
import codechicken.nei.api.API;
import codechicken.nei.forge.GuiContainerManager;
import codechicken.nei.recipe.ICraftingHandler;
import codechicken.nei.recipe.IUsageHandler;
import codechicken.nei.recipe.TemplateRecipeHandler;
import gregtechmod.api.GT_Recipe;
import java.util.ArrayList;
import net.minecraft.item.ItemStack;
import org.lwjgl.opengl.GL11;

public abstract class GT_RecipeHandler
extends TemplateRecipeHandler {
    public GT_RecipeHandler() {
        API.registerRecipeHandler((ICraftingHandler)this);
        API.registerUsageHandler((IUsageHandler)this);
    }

    public abstract String getRecipeName();

    public abstract String getRecipeId();

    public abstract String getGuiTexture();

    public abstract String getOverlayIdentifier();

    public abstract ArrayList<GT_Recipe> getRecipeList();

    public abstract CachedGT_Recipe getRecipe(GT_Recipe var1);

    public void drawBackground(GuiContainerManager gui, int recipe) {
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        gui.bindTextureByName(this.getGuiTexture());
        gui.drawTexturedModalRect(0, 0, 4, 4, 168, 79);
    }

    public int recipiesPerPage() {
        return 1;
    }

    public void loadTransferRects() {
    }

    public void loadCraftingRecipes(String outputId, Object ... results) {
        if (outputId.equals(this.getRecipeId())) {
            for (GT_Recipe irecipe : this.getRecipeList()) {
                this.arecipes.add(this.getRecipe(irecipe));
            }
        } else {
            super.loadCraftingRecipes(outputId, results);
        }
    }

    public void loadCraftingRecipes(ItemStack result) {
        for (GT_Recipe irecipe : this.getRecipeList()) {
            CachedGT_Recipe recipe = this.getRecipe(irecipe);
            if (!recipe.contains(recipe.products, result)) continue;
            this.arecipes.add(recipe);
        }
    }

    public void loadUsageRecipes(ItemStack ingredient) {
        for (GT_Recipe irecipe : this.getRecipeList()) {
            CachedGT_Recipe recipe = this.getRecipe(irecipe);
            if (!recipe.contains(recipe.resources, ingredient)) continue;
            this.arecipes.add(recipe);
        }
    }

    public String toNumber(int aNumber) {
        String tString = "";
        boolean temp = true;
        boolean negative = false;
        if (aNumber < 0) {
            aNumber *= -1;
            negative = true;
        }
        for (int i = 1000000000; i > 0; i /= 10) {
            int tDigit = aNumber / i % 10;
            if (temp && tDigit != 0) {
                temp = false;
            }
            if (temp) continue;
            tString = tString + tDigit;
            if (i == 1) continue;
            for (int j = i; j > 0; j /= 1000) {
                if (j != 1) continue;
                tString = tString + ",";
            }
        }
        if (tString.equals("")) {
            tString = "0";
        }
        return negative ? "-" + tString : tString;
    }

    public abstract class CachedGT_Recipe
    extends TemplateRecipeHandler.CachedRecipe {
        public ArrayList products;
        public ArrayList resources;

        public ArrayList getIngredients() {
            return this.resources;
        }

        public PositionedStack getResult() {
            return null;
        }

        public ArrayList getOtherStacks() {
            return this.products;
        }
    }
}

