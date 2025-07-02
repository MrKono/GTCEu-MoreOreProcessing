package kono.ceu.mop.recipes.handler;

import gregtech.api.unification.material.Materials;
import kono.ceu.mop.api.recipes.MOPRecipeMaps;

import static gregtech.api.GTValues.*;
import static gregtech.api.unification.ore.OrePrefix.*;

public class MOPChemicalFactoryHandler {


    public static void init() {
        // Platiline
        MOPRecipeMaps.CHEMICAL_FACTORY_RECIPE.recipeBuilder()
                .input(dust, Materials.PlatinumGroupSludge, 1260)
                .input(dust, Materials.Carbon, 140)
                .fluidInputs(Materials.AquaRegia.getFluid(252000))
                .fluidInputs(Materials.HydrochloricAcid.getFluid(246000))
                .fluidInputs(Materials.SulfuricAcid.getFluid(105000))
                .fluidInputs(Materials.Hydrogen.getFluid(330000))
                .output(dust, Materials.Platinum, 210)
                .output(dust, Materials.Palladium, 126)
                .output(dust, Materials.Rhodium, 70)
                .output(dust, Materials.Ruthenium, 70)
                .output(dust, Materials.Iridium, 30)
                .output(dust, Materials.Osmium, 30)
                .output(dust, Materials.SiliconDioxide, 180)
                .output(dust, Materials.Gold, 270)
                .output(dust, Materials.Carbon, 140)
                .fluidOutputs(Materials.HydrochloricAcid.getFluid(414000))
                .fluidOutputs(Materials.SulfuricAcid.getFluid(105000))
                .fluidOutputs(Materials.NitricAcid.getFluid(96000))
                .fluidOutputs(Materials.Ammonia.getFluid(30000))
                .fluidOutputs(Materials.Water.getFluid(45000))
                .fluidOutputs(Materials.Hydrogen.getFluid(132000))
                .fluidOutputs(Materials.Oxygen.getFluid(1000))
                .EUt(VA[ZPM]).duration(600 * 20).buildAndRegister();
    }
}
