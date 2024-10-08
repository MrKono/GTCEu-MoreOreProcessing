package kono.ceu.mop.recipes.handler;

import static gregtech.api.unification.ore.OrePrefix.*;

import gregtech.api.recipes.RecipeMaps;
import gregtech.api.unification.OreDictUnifier;
import gregtech.api.unification.material.Material;
import gregtech.api.unification.material.Materials;
import gregtech.api.unification.material.properties.PropertyKey;

public class MOPAlloyRecipe {

    public static void init() {
        Material[] copper = { Materials.Copper, Materials.AnnealedCopper };
        Material[] iron = { Materials.Iron, Materials.WroughtIron };
        for (Material mat : copper) {
            registerAlloyBlockRecipe(mat, 3, Materials.Tin, 1, Materials.Bronze, 4);
            registerAlloyBlockRecipe(mat, 3, Materials.Zinc, 1, Materials.Brass, 4);
            registerAlloyBlockRecipe(mat, 1, Materials.Nickel, 1, Materials.Cupronickel, 2);
            registerAlloyBlockRecipe(mat, 1, Materials.Redstone, 4, Materials.RedAlloy, 1);
        }
        for (Material mat : iron) {
            registerAlloyBlockRecipe(mat, 2, Materials.Nickel, 1, Materials.Invar, 3);
            registerAlloyBlockRecipe(mat, 1, Materials.Tin, 1, Materials.TinAlloy, 2);
        }
        registerAlloyBlockRecipe(Materials.Lead, 4, Materials.Antimony, 1, Materials.BatteryAlloy, 5);
        registerAlloyBlockRecipe(Materials.Gold, 1, Materials.Silver, 1, Materials.Electrum, 2);
        registerAlloyBlockRecipe(Materials.Magnesium, 2, Materials.Aluminium, 1, Materials.Magnalium, 2);
        registerAlloyBlockRecipe(Materials.Silver, 1, Materials.Electrotine, 4, Materials.BlueAlloy, 1);
    }

    private static void registerAlloyBlockRecipe(Material input1, int amount1, Material input2, int amount2,
                                                 Material output, int amount3) {
        if (!OreDictUnifier.get(block, input1).isEmpty() && !OreDictUnifier.get(block, input2).isEmpty()) {
            if (OreDictUnifier.get(block, output).isEmpty()) {
                RecipeMaps.ALLOY_SMELTER_RECIPES.recipeBuilder()
                        .input(block, input1, amount1)
                        .input(block, input2, amount2)
                        .output(ingot, output, amount3 * 9)
                        .duration(amount3 * 450).EUt(16).buildAndRegister();
            } else {
                RecipeMaps.ALLOY_SMELTER_RECIPES.recipeBuilder()
                        .input(block, input1, amount1)
                        .input(block, input2, amount2)
                        .output(block, output, amount3)
                        .duration(amount3 * 450).EUt(16).buildAndRegister();
            }
        }
        if (!OreDictUnifier.get(block, input1).isEmpty()) {
            if (input2.hasProperty(PropertyKey.INGOT)) {
                RecipeMaps.ALLOY_SMELTER_RECIPES.recipeBuilder()
                        .input(block, input1, amount1)
                        .input(ingot, input2, amount2 * 9)
                        .output(ingot, output, amount3 * 9)
                        .duration(amount3 * 450).EUt(16).buildAndRegister();
            }
            RecipeMaps.ALLOY_SMELTER_RECIPES.recipeBuilder()
                    .input(block, input1, amount1)
                    .input(dust, input2, amount2 * 9)
                    .output(ingot, output, amount3 * 9)
                    .duration(amount3 * 450).EUt(16).buildAndRegister();
        }
        if (!OreDictUnifier.get(block, input2).isEmpty()) {
            if (input1.hasProperty(PropertyKey.INGOT)) {
                RecipeMaps.ALLOY_SMELTER_RECIPES.recipeBuilder()
                        .input(ingot, input1, amount1 * 9)
                        .input(block, input2, amount2)
                        .output(ingot, output, amount3 * 9)
                        .duration(amount3 * 450).EUt(16).buildAndRegister();
            }
            RecipeMaps.ALLOY_SMELTER_RECIPES.recipeBuilder()
                    .input(dust, input1, amount1 * 9)
                    .input(block, input2, amount2)
                    .output(ingot, output, amount3 * 9)
                    .duration(amount3 * 450).EUt(16).buildAndRegister();
        }
    }
}
