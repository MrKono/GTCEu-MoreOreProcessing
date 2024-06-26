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

public class MOPBlockPrimitiveCasing extends VariantBlock<MOPBlockPrimitiveCasing.CasingType> {

    public MOPBlockPrimitiveCasing() {
        super(Material.IRON);
        setTranslationKey("primitive_casing");
        setHardness(5.0f);
        setResistance(10.0f);
        setSoundType(SoundType.METAL);
        setHarvestLevel(ToolClasses.WRENCH, 1);
        setDefaultState(getState(CasingType.BRONZE_PLATED_FIREBRICKS));
        setRegistryName("mop_primitive_casing");
    }

    @Override
    public boolean canCreatureSpawn(@NotNull IBlockState state, @NotNull IBlockAccess world,
                                    @NotNull BlockPos pos, @NotNull EntityLiving.SpawnPlacementType type) {
        return false;
    }

    @Override
    public IBlockState getState(CasingType variant) {
        return super.getState(variant);
    }

    public enum CasingType implements IStringSerializable {

        BRONZE_PLATED_FIREBRICKS("bronze_plated_firebricks");

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
