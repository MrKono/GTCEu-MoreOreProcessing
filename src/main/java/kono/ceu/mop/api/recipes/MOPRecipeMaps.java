package kono.ceu.mop.api.recipes;

import gregtech.api.recipes.RecipeMap;
import gregtech.api.recipes.builders.PrimitiveRecipeBuilder;
import gregtech.core.sound.GTSoundEvents;

import crafttweaker.annotations.ZenRegister;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenProperty;

@ZenClass("mods.mop.recipe.MOPRecipeMaps")
@ZenRegister
public class MOPRecipeMaps {

    @ZenProperty
    public static final RecipeMap<PrimitiveRecipeBuilder> BRONZE_REINFORCED_BLAST_FURNACE_RECIPES = new RecipeMap<>(
            "bronze_reinforced_blast_furnace", 3, 6, 0, 0, new PrimitiveRecipeBuilder(), false)
                    .setSound(GTSoundEvents.FIRE);
}
