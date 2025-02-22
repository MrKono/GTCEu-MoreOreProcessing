package kono.ceu.mop.common.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import gregtech.common.blocks.MetaBlocks;

import kono.ceu.mop.common.blocks.Casing.MOPBlockPrimitiveCasing;
import kono.ceu.mop.common.blocks.Casing.MOPGearBoxCasing;
import kono.ceu.mop.common.blocks.Casing.MOPMetalCasing;
import kono.ceu.mop.common.blocks.Casing.MOPPipeCasing;

public class MOPMetaBlocks {

    private MOPMetaBlocks() {}

    public static final MOPBlockPrimitiveCasing MOP_BLOCK_PRIMITIVE_CASING = new MOPBlockPrimitiveCasing();
    public static final MOPMetalCasing MOP_METAL_CASING = new MOPMetalCasing();
    public static final MOPPipeCasing MOP_PIPE_CASING = new MOPPipeCasing();
    public static final MOPGearBoxCasing MOP_GEAR_BOX_CASING = new MOPGearBoxCasing();

    @SideOnly(Side.CLIENT)
    public static void registerItemModels() {
        registerItemModel(MOP_BLOCK_PRIMITIVE_CASING);
        registerItemModel(MOP_METAL_CASING);
        registerItemModel(MOP_PIPE_CASING);
        registerItemModel(MOP_GEAR_BOX_CASING);
    }

    @SideOnly(Side.CLIENT)
    private static void registerItemModel(Block block) {
        for (IBlockState state : block.getBlockState().getValidStates()) {
            // noinspection ConstantConditions
            ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(block),
                    block.getMetaFromState(state),
                    new ModelResourceLocation(block.getRegistryName(),
                            MetaBlocks.statePropertiesToString(state.getProperties())));
        }
    }
}
