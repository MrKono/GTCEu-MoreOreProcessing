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

public class MOPMetalCasing extends VariantBlock<MOPMetalCasing.CasingType> {

    public MOPMetalCasing() {
        super(Material.IRON);
        setTranslationKey("metal_casing");
        setHardness(5.0f);
        setResistance(10.0f);
        setSoundType(SoundType.METAL);
        setHarvestLevel(ToolClasses.WRENCH, 3);
        setDefaultState(getState(CasingType.IRIDIUM));
        setRegistryName("mop_metal_casing");
    }

    @Override
    public boolean canCreatureSpawn(@NotNull IBlockState state, @NotNull IBlockAccess world,
                                    @NotNull BlockPos pos, @NotNull EntityLiving.SpawnPlacementType type) {
        return false;
    }

    @Override
    public IBlockState getState(MOPMetalCasing.CasingType variant) {
        return super.getState(variant);
    }

    public enum CasingType implements IStringSerializable {

        IRIDIUM("iridium_plated"),
        AMERICIUM("americium_plated");

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
