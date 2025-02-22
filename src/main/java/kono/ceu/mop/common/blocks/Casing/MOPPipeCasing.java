package kono.ceu.mop.common.blocks.Casing;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

import org.jetbrains.annotations.NotNull;

import gregtech.api.block.VariantBlock;
import gregtech.api.items.toolitem.ToolClasses;

public class MOPPipeCasing extends VariantBlock<MOPPipeCasing.CasingType> {

    public MOPPipeCasing() {
        super(Material.IRON);
        setTranslationKey("pipe_casing");
        setHardness(5.0f);
        setResistance(10.0f);
        setSoundType(SoundType.METAL);
        setHarvestLevel(ToolClasses.WRENCH, 3);
        setDefaultState(getState(MOPPipeCasing.CasingType.IRIDIUM));
        setRegistryName("mop_pipe_casing");
    }

    @Override
    public boolean canCreatureSpawn(@NotNull IBlockState state, @NotNull IBlockAccess world,
                                    @NotNull BlockPos pos, @NotNull EntityLiving.SpawnPlacementType type) {
        return false;
    }

    @Override
    public IBlockState getState(MOPPipeCasing.CasingType variant) {
        return super.getState(variant);
    }

    public enum CasingType implements IStringSerializable {

        IRIDIUM("iridium_pipe"),
        AMERICIUM("americium_pipe");

        private final String name;

        CasingType(String name) {
            this.name = name;
        }

        @NotNull
        @Override
        public String getName() {
            return this.name;
        }
    }
}
