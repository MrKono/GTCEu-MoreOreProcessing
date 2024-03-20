package kono.ceu.mop.recipes;

import static gregtech.api.recipes.RecipeMaps.PRIMITIVE_BLAST_FURNACE_RECIPES;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.ore.OrePrefix.*;
import static gregtech.loaders.OreDictionaryLoader.OREDICT_FUEL_COKE;

import org.jetbrains.annotations.Nullable;

import gregtech.api.unification.material.Material;
import gregtech.api.unification.ore.OrePrefix;

public class PBFRecipe {

    public static void init() {
        // Iron
        registerPBFRecipe(Magnetite, Iron);
        registerPBFRecipe(Pyrite, Iron);
        registerPBFRecipe(BrownLimonite, Iron);
        registerPBFRecipe(YellowLimonite, Iron);
        registerPBFRecipe(BandedIron, Iron);
        // Copper
        registerPBFRecipe(Malachite, Copper);
        // Nickel
        registerPBFRecipe(Garnierite, Nickel);
        registerPBFRecipe(Pentlandite, Nickel);
        // Cobalt
        registerPBFRecipe(Cobaltite, Cobalt);
        // Zinc
        registerPBFRecipe(Sphalerite, Zinc);
        // Tin
        registerPBFRecipe(Cassiterite, Tin);
        registerPBFRecipe(CassiteriteSand, Tin);
        // Antimony
        registerPBFRecipe(Stibnite, Antimony);

        // Copper & Antimony
        registerPBFRecipe(Tetrahedrite, Copper, nugget, Antimony, 9);
        // Lead & Silver
        registerPBFRecipe(Galena, Lead, nugget, Silver, 9);

        // Chalcopyrite
        registerPBFRecipe(Chalcopyrite, dust, Glass, 2, Copper, dustTiny, Ferrosilite, 21);
        registerPBFRecipe(Chalcopyrite, dust, SiliconDioxide, 2, Copper, dust, Ferrosilite, 3);
        registerPBFRecipe(Chalcopyrite, dust, QuartzSand, 2, Copper, dustSmall, Ferrosilite, 6);
    }

    public static void registerPBFRecipe(Material input, Material output) {
        registerPBFRecipe(input, null, null, 0, output, null, null, 0);
    }

    public static void registerPBFRecipe(Material input, Material outputMain, OrePrefix prefix, Material outputSub,
                                         int count) {
        registerPBFRecipe(input, null, null, 0, outputMain, prefix, outputSub, count);
    }

    public static void registerPBFRecipe(Material input, OrePrefix prefix, Material sub, int count, Material output) {
        registerPBFRecipe(input, prefix, sub, count, output, null, null, 0);
    }

    public static void registerPBFRecipe(Material input, @Nullable OrePrefix inputSubOrePrefix,
                                         @Nullable Material inputSub, int inputSubCount,
                                         Material outputMain, @Nullable OrePrefix outputSubOrePrefix,
                                         @Nullable Material outputSub, int outputSubCount) {
        if (inputSubOrePrefix == null) {
            if (outputSubOrePrefix == null) {
                PRIMITIVE_BLAST_FURNACE_RECIPES.recipeBuilder()
                        .input(dust, input, 3).input(gem, Coal, 2)
                        .output(ingot, outputMain, 2).output(dustTiny, DarkAsh, 2)
                        .duration(2400).buildAndRegister();
                PRIMITIVE_BLAST_FURNACE_RECIPES.recipeBuilder()
                        .input(dust, input, 3).input(dust, Coal, 2)
                        .output(ingot, outputMain, 2).output(dustTiny, DarkAsh, 2)
                        .duration(2400).buildAndRegister();
                PRIMITIVE_BLAST_FURNACE_RECIPES.recipeBuilder()
                        .input(dust, input, 3).input(gem, Charcoal, 2)
                        .output(ingot, outputMain, 2).output(dustTiny, DarkAsh, 2)
                        .duration(2400).buildAndRegister();
                PRIMITIVE_BLAST_FURNACE_RECIPES.recipeBuilder()
                        .input(dust, input, 3).input(dust, Charcoal, 2)
                        .output(ingot, outputMain, 2).output(dustTiny, DarkAsh, 2)
                        .duration(2400).buildAndRegister();
                PRIMITIVE_BLAST_FURNACE_RECIPES.recipeBuilder()
                        .input(dust, input, 3).input(OREDICT_FUEL_COKE)
                        .output(ingot, outputMain, 2).output(dustTiny, DarkAsh)
                        .duration(2000).buildAndRegister();
                PRIMITIVE_BLAST_FURNACE_RECIPES.recipeBuilder()
                        .input(dust, input, 3).input(dust, Coke)
                        .output(ingot, outputMain, 2).output(dustTiny, DarkAsh)
                        .duration(2000).buildAndRegister();
            } else {
                PRIMITIVE_BLAST_FURNACE_RECIPES.recipeBuilder()
                        .input(dust, input, 3).input(gem, Coal, 2)
                        .output(ingot, outputMain, 2).output(outputSubOrePrefix, outputSub, outputSubCount)
                        .output(dustTiny, DarkAsh, 2)
                        .duration(2400).buildAndRegister();
                PRIMITIVE_BLAST_FURNACE_RECIPES.recipeBuilder()
                        .input(dust, input, 3).input(dust, Coal, 2)
                        .output(ingot, outputMain, 2).output(outputSubOrePrefix, outputSub, outputSubCount)
                        .output(dustTiny, DarkAsh, 2)
                        .duration(2400).buildAndRegister();
                PRIMITIVE_BLAST_FURNACE_RECIPES.recipeBuilder()
                        .input(dust, input, 3).input(gem, Charcoal, 2)
                        .output(ingot, outputMain, 2).output(outputSubOrePrefix, outputSub, outputSubCount)
                        .output(dustTiny, DarkAsh, 2)
                        .duration(2400).buildAndRegister();
                PRIMITIVE_BLAST_FURNACE_RECIPES.recipeBuilder()
                        .input(dust, input, 3).input(dust, Charcoal, 2)
                        .output(ingot, outputMain, 2).output(outputSubOrePrefix, outputSub, outputSubCount)
                        .output(dustTiny, DarkAsh, 2)
                        .duration(2400).buildAndRegister();
                PRIMITIVE_BLAST_FURNACE_RECIPES.recipeBuilder()
                        .input(dust, input, 3).input(OREDICT_FUEL_COKE)
                        .output(ingot, outputMain, 2).output(outputSubOrePrefix, outputSub, outputSubCount)
                        .output(dustTiny, DarkAsh)
                        .duration(2000).buildAndRegister();
                PRIMITIVE_BLAST_FURNACE_RECIPES.recipeBuilder()
                        .input(dust, input, 3).input(dust, Coke)
                        .output(ingot, outputMain, 2).output(outputSubOrePrefix, outputSub, outputSubCount)
                        .output(dustTiny, DarkAsh)
                        .duration(2000).buildAndRegister();
            }
        } else {
            if (outputSubOrePrefix == null) {
                PRIMITIVE_BLAST_FURNACE_RECIPES.recipeBuilder()
                        .input(dust, input, 3).input(inputSubOrePrefix, inputSub, inputSubCount).input(gem, Coal, 2)
                        .output(ingot, outputMain, 2).output(dustTiny, DarkAsh, 2)
                        .duration(2400).buildAndRegister();
                PRIMITIVE_BLAST_FURNACE_RECIPES.recipeBuilder()
                        .input(dust, input, 3).input(inputSubOrePrefix, inputSub, inputSubCount).input(dust, Coal, 2)
                        .output(ingot, outputMain, 2).output(dustTiny, DarkAsh, 2)
                        .duration(2400).buildAndRegister();
                PRIMITIVE_BLAST_FURNACE_RECIPES.recipeBuilder()
                        .input(dust, input, 3).input(inputSubOrePrefix, inputSub, inputSubCount).input(gem, Charcoal, 2)
                        .output(ingot, outputMain, 2).output(dustTiny, DarkAsh, 2)
                        .duration(2400).buildAndRegister();
                PRIMITIVE_BLAST_FURNACE_RECIPES.recipeBuilder()
                        .input(dust, input, 3).input(inputSubOrePrefix, inputSub, inputSubCount)
                        .input(dust, Charcoal, 2)
                        .output(ingot, outputMain, 2).output(dustTiny, DarkAsh, 2)
                        .duration(2400).buildAndRegister();
                PRIMITIVE_BLAST_FURNACE_RECIPES.recipeBuilder()
                        .input(dust, input, 3).input(inputSubOrePrefix, inputSub, inputSubCount)
                        .input(OREDICT_FUEL_COKE)
                        .output(ingot, outputMain, 2).output(dustTiny, DarkAsh)
                        .duration(2000).buildAndRegister();
                PRIMITIVE_BLAST_FURNACE_RECIPES.recipeBuilder()
                        .input(dust, input, 3).input(inputSubOrePrefix, inputSub, inputSubCount).input(dust, Coke)
                        .output(ingot, outputMain, 2).output(dustTiny, DarkAsh)
                        .duration(2000).buildAndRegister();
            } else {
                PRIMITIVE_BLAST_FURNACE_RECIPES.recipeBuilder()
                        .input(dust, input, 3).input(inputSubOrePrefix, inputSub, inputSubCount).input(gem, Coal, 2)
                        .output(ingot, outputMain, 2).output(outputSubOrePrefix, outputSub, outputSubCount)
                        .output(dustTiny, DarkAsh, 2)
                        .duration(2400).buildAndRegister();
                PRIMITIVE_BLAST_FURNACE_RECIPES.recipeBuilder()
                        .input(dust, input, 3).input(inputSubOrePrefix, inputSub, inputSubCount).input(dust, Coal, 2)
                        .output(ingot, outputMain, 2).output(outputSubOrePrefix, outputSub, outputSubCount)
                        .output(dustTiny, DarkAsh, 2)
                        .duration(2400).buildAndRegister();
                PRIMITIVE_BLAST_FURNACE_RECIPES.recipeBuilder()
                        .input(dust, input, 3).input(inputSubOrePrefix, inputSub, inputSubCount).input(gem, Charcoal, 2)
                        .output(ingot, outputMain, 2).output(outputSubOrePrefix, outputSub, outputSubCount)
                        .output(dustTiny, DarkAsh, 2)
                        .duration(2400).buildAndRegister();
                PRIMITIVE_BLAST_FURNACE_RECIPES.recipeBuilder()
                        .input(dust, input, 3).input(inputSubOrePrefix, inputSub, inputSubCount)
                        .input(dust, Charcoal, 2)
                        .output(ingot, outputMain, 2).output(outputSubOrePrefix, outputSub, outputSubCount)
                        .output(dustTiny, DarkAsh, 2)
                        .duration(2400).buildAndRegister();
                PRIMITIVE_BLAST_FURNACE_RECIPES.recipeBuilder()
                        .input(dust, input, 3).input(inputSubOrePrefix, inputSub, inputSubCount)
                        .input(OREDICT_FUEL_COKE)
                        .output(ingot, outputMain, 2).output(outputSubOrePrefix, outputSub, outputSubCount)
                        .output(dustTiny, DarkAsh)
                        .duration(2000).buildAndRegister();
                PRIMITIVE_BLAST_FURNACE_RECIPES.recipeBuilder()
                        .input(dust, input, 3).input(inputSubOrePrefix, inputSub, inputSubCount).input(dust, Coke)
                        .output(ingot, outputMain, 2).output(outputSubOrePrefix, outputSub, outputSubCount)
                        .output(dustTiny, DarkAsh)
                        .duration(2000).buildAndRegister();
            }
        }
    }
}
