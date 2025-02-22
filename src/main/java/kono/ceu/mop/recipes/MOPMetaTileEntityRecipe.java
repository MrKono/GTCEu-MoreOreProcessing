package kono.ceu.mop.recipes;

import static gregtech.api.GTValues.*;
import static gregtech.api.unification.ore.OrePrefix.*;

import gregtech.api.recipes.ModHandler;
import gregtech.api.recipes.RecipeMaps;
import gregtech.api.unification.material.MarkerMaterials;
import gregtech.api.unification.material.Materials;
import gregtech.api.unification.stack.UnificationEntry;
import gregtech.common.blocks.BlockMetalCasing;
import gregtech.common.blocks.MetaBlocks;
import gregtech.common.items.MetaItems;
import gregtech.common.metatileentities.MetaTileEntities;

import kono.ceu.mop.common.metatileentities.MOPMetaTileEntities;

public class MOPMetaTileEntityRecipe {

    public static void init() {
        registerMulti();
    }

    public static void registerMulti() {
        // Bronze Plated Blast Furnace
        ModHandler.addShapedRecipe(true, "bronze_plated_blast_furnace",
                MOPMetaTileEntities.BRONZE_PLATED_BLASE_FURNACE.getStackForm(),
                "hRS", "PBR", "dRS",
                'R', new UnificationEntry(stick, Materials.Bronze),
                'S', new UnificationEntry(screw, Materials.Bronze),
                'P', new UnificationEntry(plate, Materials.Bronze),
                'B', MetaBlocks.METAL_CASING.getItemVariant(BlockMetalCasing.MetalCasingType.BRONZE_BRICKS));

        // Bronze Reinforced Blast Furnace
        ModHandler.addShapedRecipe(true, "bronze_reinforced_from_pbf",
                MOPMetaTileEntities.BRONZE_REINFORCED_BLAST_FURNACE.getStackForm(),
                "PwP", "PFP", "PhP",
                'P', new UnificationEntry(plateDouble, Materials.Bronze),
                'F', MetaTileEntities.PRIMITIVE_BLAST_FURNACE.getStackForm());

        ModHandler.addShapedRecipe(true, "bronze_reinforced_from_bpf",
                MOPMetaTileEntities.BRONZE_REINFORCED_BLAST_FURNACE.getStackForm(),
                "PwP", "PFP", "PhP",
                'P', new UnificationEntry(plate, Materials.Bronze),
                'F', MOPMetaTileEntities.BRONZE_PLATED_BLASE_FURNACE.getStackForm());

        // Ore Processing Factory
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                .input(MetaTileEntities.MACERATOR[IV])
                .input(MetaTileEntities.ORE_WASHER[IV])
                .input(MetaTileEntities.CENTRIFUGE[IV])
                .input(MetaTileEntities.SIFTER[IV])
                .input(MetaTileEntities.CHEMICAL_BATH[IV])
                .input(MetaTileEntities.THERMAL_CENTRIFUGE[IV])
                .input(circuit, MarkerMaterials.Tier.LuV, 4)
                .fluidInputs(Materials.SolderingAlloy.getFluid(144 * 8))
                .output(MOPMetaTileEntities.ORE_FACTORY)
                .duration(10 * 20).EUt(VA[IV]).buildAndRegister();

        // Industrial Ore Processing Factory
        RecipeMaps.ASSEMBLY_LINE_RECIPES.recipeBuilder()
                .input(MOPMetaTileEntities.ORE_FACTORY)
                .input(gearSmall, Materials.TungstenCarbide, 8)
                .input(gear, Materials.Tritanium, 4)
                .input(MetaItems.ELECTRIC_MOTOR_UV, 2)
                .input(circuit, MarkerMaterials.Tier.UHV, 1)
                .input(circuit, MarkerMaterials.Tier.UV, 4)
                .fluidInputs(Materials.SolderingAlloy.getFluid(144 * 16))
                .fluidInputs(Materials.Lubricant.getFluid(4000))
                .output(MOPMetaTileEntities.INDUSTRIAL_ORE_FACTORY)
                .stationResearch(b -> b.researchStack(MOPMetaTileEntities.ORE_FACTORY.getStackForm())
                        .CWUt(144).EUt(VA[ZPM]))
                .duration(60 * 20).EUt(VA[UHV]).buildAndRegister();
    }
}
