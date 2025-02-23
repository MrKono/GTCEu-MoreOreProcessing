package kono.ceu.mop.recipes.handler;

import gregtech.api.unification.OreDictUnifier;
import gregtech.api.unification.material.Materials;
import gregtech.api.unification.stack.ItemMaterialInfo;
import gregtech.api.unification.stack.MaterialStack;
import gregtech.common.ConfigHolder;
import kono.ceu.mop.common.blocks.Casing.MOPBlockPrimitiveCasing;
import kono.ceu.mop.common.blocks.Casing.MOPGearBoxCasing;
import kono.ceu.mop.common.blocks.Casing.MOPMetalCasing;
import kono.ceu.mop.common.blocks.Casing.MOPPipeCasing;
import kono.ceu.mop.common.blocks.MOPMetaBlocks;

import static gregtech.api.GTValues.M;

public class MOPMaterialInfoLoader {

    public static int casingAmount = ConfigHolder.recipes.casingsPerCraft;

    public static void init() {
        // Bronze Plated Firebricks (4 dusts + 2 blocks + 6 doublePlates
        OreDictUnifier.registerOre(
                MOPMetaBlocks.MOP_BLOCK_PRIMITIVE_CASING.getItemVariant(MOPBlockPrimitiveCasing.CasingType.BRONZE_PLATED_FIREBRICKS),
                new ItemMaterialInfo(
                        new MaterialStack(Materials.Fireclay, M * 4),
                        new MaterialStack(Materials.Bronze, (M * 9) * 2 + (M * 2) * 6)
                ));
        // Iridium Plated Factory Casing (casing/config + 1 ingots)
        OreDictUnifier.registerOre(
                MOPMetaBlocks.MOP_METAL_CASING.getItemVariant(MOPMetalCasing.CasingType.IRIDIUM),
                new ItemMaterialInfo(
                        new MaterialStack(Materials.StainlessSteel, (M * 8) / casingAmount),
                        new MaterialStack(Materials.Iridium, M)
                ));
        // Americium Plated Factory Casing (casing/config + Ir * 1 + Am * 1)
        OreDictUnifier.registerOre(
                MOPMetaBlocks.MOP_METAL_CASING.getItemVariant(MOPMetalCasing.CasingType.AMERICIUM),
                new ItemMaterialInfo(
                        new MaterialStack(Materials.StainlessSteel, (M * 8) / casingAmount),
                        new MaterialStack(Materials.Iridium, M),
                        new MaterialStack(Materials.Americium, M)
                ));
        // Gear Box ((2 plates + 4 gears + frame) / config)
        OreDictUnifier.registerOre(
                MOPMetaBlocks.MOP_GEAR_BOX_CASING.getItemVariant(MOPGearBoxCasing.CasingType.IRIDIUM),
                new ItemMaterialInfo(
                        new MaterialStack(Materials.Iridium, ((M * 4) + (M * 4) * 2 + (M * 2)) / casingAmount )
                )
        );
        OreDictUnifier.registerOre(
                MOPMetaBlocks.MOP_GEAR_BOX_CASING.getItemVariant(MOPGearBoxCasing.CasingType.AMERICIUM),
                new ItemMaterialInfo(
                        new MaterialStack(Materials.Americium, ((M * 4) + (M * 4) * 2 + (M * 2)) / casingAmount )
                )
        );
        // Pipe Casing ((4 plates + 4 pipeNormals + frame) / config)
        OreDictUnifier.registerOre(
                MOPMetaBlocks.MOP_PIPE_CASING.getItemVariant(MOPPipeCasing.CasingType.IRIDIUM),
                new ItemMaterialInfo(
                        new MaterialStack(Materials.Iridium, ((M * 4) + (M * 3) * 4 + (M * 2)) / casingAmount)
                ));
        OreDictUnifier.registerOre(
                MOPMetaBlocks.MOP_PIPE_CASING.getItemVariant(MOPPipeCasing.CasingType.AMERICIUM),
                new ItemMaterialInfo(
                        new MaterialStack(Materials.Americium, ((M * 4) + (M * 3) * 4 + (M * 2)) / casingAmount)
                ));
    }
}
