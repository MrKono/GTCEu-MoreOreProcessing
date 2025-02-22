package kono.ceu.mop.common.metatileentities.multi.electric;

import java.util.List;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity;
import gregtech.api.metatileentity.multiblock.IMultiblockAbilityPart;
import gregtech.api.metatileentity.multiblock.IMultiblockPart;
import gregtech.api.metatileentity.multiblock.MultiblockAbility;
import gregtech.api.pattern.BlockPattern;
import gregtech.api.pattern.FactoryBlockPattern;
import gregtech.client.renderer.ICubeRenderer;
import gregtech.client.utils.TooltipHelper;
import gregtech.common.blocks.BlockGlassCasing;
import gregtech.common.blocks.MetaBlocks;

import gregicality.multiblocks.api.metatileentity.GCYMMultiblockAbility;
import gregicality.multiblocks.api.metatileentity.GCYMRecipeMapMultiblockController;

import kono.ceu.mop.api.recipes.MOPRecipeMaps;
import kono.ceu.mop.client.MOPTextures;
import kono.ceu.mop.common.blocks.Casing.MOPGearBoxCasing;
import kono.ceu.mop.common.blocks.Casing.MOPMetalCasing;
import kono.ceu.mop.common.blocks.Casing.MOPPipeCasing;
import kono.ceu.mop.common.blocks.MOPMetaBlocks;

public class MetaTileEntityIndustrialOreFactory extends GCYMRecipeMapMultiblockController {

    public MetaTileEntityIndustrialOreFactory(ResourceLocation metaTileEntityId) {
        super(metaTileEntityId, MOPRecipeMaps.ORE_FACTORY_RECIPES);
    }

    @Override
    public MetaTileEntity createMetaTileEntity(IGregTechTileEntity iGregTechTileEntity) {
        return new MetaTileEntityIndustrialOreFactory(metaTileEntityId);
    }

    @Override
    protected @NotNull BlockPattern createStructurePattern() {
        return FactoryBlockPattern.start()
                .aisle("AAAAAAA#####", "AGGGGGA#####", "AGGGGGA#####", "AGGGGGA#####", "AGGGGGA#####", "AGGGGGA#####",
                        "AAAAAAA#####", "############", "############", "############", "############", "############",
                        "############")
                .aisle("AAAAAAABBBBB", "GP$$$PGACCCA", "GP$$$PGACCCA", "GP$$$PGACCCA", "GP$$$PGACCCA", "GP$$$PGACCCA",
                        "AAAAAAAACCCA", "########DDD#", "########DDD#", "########DDD#", "########DDD#", "########DDD#",
                        "############")
                .aisle("AAAAAAABBBBB", "G$P$P$GC$$$C", "G$P$P$GC$$$C", "G$P$P$GC$$$C", "G$P$P$GC$$$C", "G$P$P$GC$$$C",
                        "AAAAAAAC$$$C", "#######D$$$D", "#######D$$$D", "#######D$$$D", "#######D$$$D", "#######D$$$D",
                        "########EEE#")
                .aisle("AAAAAAABBBBB", "G$$H$$GC$V$C", "G$$H$$GC$H$C", "G$$H$$GC$H$C", "G$$H$$GC$H$C", "G$$H$$GC$H$C",
                        "AAAAAAAC$H$C", "#######D$H$D", "#######D$H$D", "#######D$H$D", "#######D$H$D", "#######D$H$D",
                        "########EME#")
                .aisle("AAAAAAABBBBB", "G$P$P$GC$$$C", "G$P$P$GC$$$C", "G$P$P$GC$$$C", "G$P$P$GC$$$C", "G$P$P$GC$$$C",
                        "AAAAAAAC$$$C", "#######D$$$D", "#######D$$$D", "#######D$$$D", "#######D$$$D", "#######D$$$D",
                        "########EEE#")
                .aisle("AAAAAAABBBBB", "GP$$$PGACSCA", "GP$$$PGACCCA", "GP$$$PGACCCA", "GP$$$PGACCCA", "GP$$$PGACCCA",
                        "AAAAAAAACCCA", "########DDD#", "########DDD#", "########DDD#", "########DDD#", "########DDD#",
                        "############")
                .aisle("AAAAAAA#####", "AGGGGGA#####", "AGGGGGA#####", "AGGGGGA#####", "AGGGGGA#####", "AGGGGGA#####",
                        "AAAAAAA#####", "############", "############", "############", "############", "############",
                        "############")
                .where('A', states(getCasingState()))
                .where('B', states(getCasingState())
                        .or(abilities(MultiblockAbility.INPUT_ENERGY).setMinGlobalLimited(1).setMaxGlobalLimited(3))
                        .or(abilities(MultiblockAbility.IMPORT_FLUIDS).setMaxGlobalLimited(5, 1)))
                .where('C', states(getCasingState2())
                        .or(autoAbilities(true, false))
                        .or(abilities(MultiblockAbility.IMPORT_ITEMS).setMaxGlobalLimited(20, 1))
                        .or(abilities(GCYMMultiblockAbility.PARALLEL_HATCH).setMaxGlobalLimited(1)))
                .where('D', states(getCasingState2())
                        .or(abilities(MultiblockAbility.EXPORT_ITEMS).setMaxGlobalLimited(10, 1)))
                .where('E', states(getCasingState2()))
                .where('G', states(MetaBlocks.TRANSPARENT_CASING.getState(BlockGlassCasing.CasingType.LAMINATED_GLASS)))
                .where('H', states(MOPMetaBlocks.MOP_GEAR_BOX_CASING.getState(MOPGearBoxCasing.CasingType.AMERICIUM)))
                .where('M', abilities(MultiblockAbility.MUFFLER_HATCH))
                .where('P', states(MOPMetaBlocks.MOP_PIPE_CASING.getState(MOPPipeCasing.CasingType.AMERICIUM)))
                .where('S', selfPredicate())
                .where('V',
                        tieredCasing().or(states(
                                MOPMetaBlocks.MOP_GEAR_BOX_CASING.getState(MOPGearBoxCasing.CasingType.AMERICIUM))))
                .where('#', any())
                .where('$', air())
                .build();
    }

    @Override
    public ICubeRenderer getBaseTexture(IMultiblockPart iMultiblockPart) {
        if (iMultiblockPart instanceof IMultiblockAbilityPart) {
            MultiblockAbility<?> ability = ((IMultiblockAbilityPart<?>) iMultiblockPart).getAbility();
            if (ability == MultiblockAbility.INPUT_ENERGY || ability == MultiblockAbility.IMPORT_FLUIDS) {
                return MOPTextures.AMERICIUM_PLATED;
            }
        }
        return MOPTextures.IRIDIUM_PLATED;
    }

    protected IBlockState getCasingState() {
        return MOPMetaBlocks.MOP_METAL_CASING.getState(MOPMetalCasing.CasingType.AMERICIUM);
    }

    protected IBlockState getCasingState2() {
        return MOPMetaBlocks.MOP_METAL_CASING.getState(MOPMetalCasing.CasingType.IRIDIUM);
    }

    @Override
    public boolean canBeDistinct() {
        return true;
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World player, List<String> tooltip, boolean advanced) {
        tooltip.add(I18n.format("mop.machine.ore_factory.tooltip1"));
        tooltip.add(I18n.format("mop.machine.ore_factory.tooltip2"));
        if (TooltipHelper.isCtrlDown()) {
            tooltip.add("");
            tooltip.add(I18n.format("mop.machine.ore_factory.tooltip.process1"));
            tooltip.add(I18n.format("mop.machine.ore_factory.tooltip.process2"));
            tooltip.add(I18n.format("mop.machine.ore_factory.tooltip.process3"));
            tooltip.add(I18n.format("mop.machine.ore_factory.tooltip.process4"));
            tooltip.add(I18n.format("mop.machine.ore_factory.tooltip.process5"));
            tooltip.add(I18n.format("mop.machine.ore_factory.tooltip.process6"));
            tooltip.add(I18n.format("mop.machine.ore_factory.tooltip.process7"));
            tooltip.add(I18n.format("mop.machine.ore_factory.tooltip.process8"));
            tooltip.add(I18n.format("mop.machine.ore_factory.tooltip.process9"));
            tooltip.add("");
        } else {
            tooltip.add(I18n.format("mop.machine.ore_factory.tooltip.hold_ctrl"));
        }
    }
}
