package kono.ceu.mop.common;

import static kono.ceu.mop.common.blocks.MOPMetaBlocks.MOP_BLOCK_PRIMITIVE_CASING;

import java.util.Objects;
import java.util.function.Function;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.registries.IForgeRegistry;

import gregtech.api.block.VariantItemBlock;
import gregtech.api.recipes.RecipeMaps;
import gregtech.api.recipes.ingredients.GTRecipeInput;
import gregtech.loaders.recipe.RecyclingRecipes;

import kono.ceu.mop.api.MOPValues;
import kono.ceu.mop.api.recipes.MOPRecipeMaps;
import kono.ceu.mop.common.blocks.MOPMetaBlocks;
import kono.ceu.mop.common.metatileentities.MOPMetaTileEntities;
import kono.ceu.mop.recipes.PBFRecipe;

@Mod.EventBusSubscriber(modid = MOPValues.MODID)
public class CommonProxy {

    public void preInit(FMLPreInitializationEvent event) {
        MOPMetaBlocks.init();
        MOPMetaTileEntities.init();
    }

    public void init(FMLInitializationEvent event) {}

    public void postInit(FMLPostInitializationEvent event) {}

    @SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Block> event) {
        IForgeRegistry<Block> registry = event.getRegistry();
        registry.register(MOP_BLOCK_PRIMITIVE_CASING);
    }

    public static void registerItems(RegistryEvent.Register<Item> event) {
        IForgeRegistry<Item> registry = event.getRegistry();

        registry.register(createItemBlock(MOP_BLOCK_PRIMITIVE_CASING, VariantItemBlock::new));
    }

    private static <T extends Block> ItemBlock createItemBlock(T block, Function<T, ItemBlock> producer) {
        ItemBlock itemBlock = producer.apply(block);
        itemBlock.setRegistryName(Objects.requireNonNull(block.getRegistryName()));
        return itemBlock;
    }

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public static void registerRecipesHighest(RegistryEvent.Register<IRecipe> event) {}

    @SubscribeEvent(priority = EventPriority.HIGH)
    public static void registerRecipesHigh(RegistryEvent.Register<IRecipe> event) {
        RecipeMaps.PRIMITIVE_BLAST_FURNACE_RECIPES.onRecipeBuild(builder -> {
            MOPRecipeMaps.BRONZE_PLATED_BLAST_FURNACE_RECIPES.recipeBuilder()
                    .inputs(builder.getInputs().toArray(new GTRecipeInput[0]))
                    .outputs(builder.getOutputs())
                    .duration(builder.getDuration())
                    .buildAndRegister();
        });
    }

    @SubscribeEvent
    public static void registerRecipesNormal(RegistryEvent.Register<IRecipe> event) {
        PBFRecipe.init();
    }

    @SubscribeEvent(priority = EventPriority.LOW)
    public static void registerRecipesLow(RegistryEvent.Register<IRecipe> event) {}

    @SubscribeEvent(priority = EventPriority.LOWEST)
    public static void registerRecipesLowest(RegistryEvent.Register<IRecipe> event) {
        RecyclingRecipes.init();
    }
}
