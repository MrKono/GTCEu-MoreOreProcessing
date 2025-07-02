package kono.ceu.mop.common.metatileentities.multi.electric;

import gregicality.multiblocks.api.metatileentity.GCYMRecipeMapMultiblockController;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity;
import gregtech.api.metatileentity.multiblock.IMultiblockPart;
import gregtech.api.metatileentity.multiblock.MultiblockAbility;
import gregtech.api.pattern.BlockPattern;
import gregtech.api.pattern.FactoryBlockPattern;
import gregtech.client.renderer.ICubeRenderer;
import gregtech.client.renderer.texture.Textures;
import gregtech.common.blocks.BlockBoilerCasing;
import gregtech.common.blocks.BlockMetalCasing;
import gregtech.common.blocks.MetaBlocks;
import kono.ceu.mop.api.recipes.MOPRecipeMaps;
import kono.ceu.mop.common.blocks.Casing.MOPGlassCasing;
import kono.ceu.mop.common.blocks.MOPMetaBlocks;
import net.minecraft.util.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public class MetaTileEntityChemicalFactory extends GCYMRecipeMapMultiblockController {

    public MetaTileEntityChemicalFactory(ResourceLocation metaTileEntityId) {
        super(metaTileEntityId, MOPRecipeMaps.CHEMICAL_FACTORY_RECIPE);
    }

    @Override
    public MetaTileEntity createMetaTileEntity(IGregTechTileEntity tileEntity) {
        return new MetaTileEntityChemicalFactory(metaTileEntityId);
    }

    @Override
    protected @NotNull BlockPattern createStructurePattern() {
        return FactoryBlockPattern.start()
                .aisle("CCCCCCCCC", "AGGGGGGGA", "AGGGGGGGA", "AGGGGGGGA", "AGGGGGGGA", "AGGGGGGGA", "AGGGGGGGA", "AGGGGGGGA", "AGGGGGGGA", "AGGGGGGGA", "AGGGGGGGA", "CCCCCCCCC")
                .aisle("CCCCCCCCC", "G#####E#G", "G####E##G", "G###E###G", "G##E####G", "G#E#####G", "G#####D#G", "G####D##G", "G###D###G", "G##D####G", "G#D#####G", "CCCCCCCCC")
                .aisle("CCCCCCCCC", "GF######G", "G#######G", "G#######G", "G#######G", "G######DG", "GE######G", "G#######G", "G#######G", "G#######G", "G######BG", "CCCCCCCCC")
                .aisle("CCCCCCCCC", "G##PPP##G", "GF#PPP##G", "G##PPP##G", "G##PPP#DG", "G##PPP##G", "G##PPP##G", "GE#PPP##G", "G##PPP##G", "G##PPP#BG", "G##PPP##G", "CCCCCCCCC")
                .aisle("CCCCVCCCC", "G##P#P##G", "G##P#P##G", "GF#P#P#DG", "G##P#P##G", "G##P#P##G", "G##P#P##G", "G##P#P##G", "GE#P#P#BG", "G##P#P##G", "G##P#P##G", "CCCCMCCCC")
                .aisle("CCCCCCCCC", "G##PPP##G", "G##PPP#DG", "G##PPP##G", "GF#PPP##G", "G##PPP##G", "G##PPP##G", "G##PPP#BG", "G##PPP##G", "GE#PPP##G", "G##PPP##G", "CCCCCCCCC")
                .aisle("CCCCCCCCC", "G######DG", "G#######G", "G#######G", "G#######G", "GF######G", "G######BG", "G#######G", "G#######G", "G#######G", "GE######G", "CCCCCCCCC")
                .aisle("CCCCCCCCC", "G#B#####G", "G##B####G", "G###B###G", "G####B##G", "G#####B#G", "G#F#####G", "G##F####G", "G###F###G", "G####F##G", "G#####F#G", "CCCCCCCCC")
                .aisle("CCCCSCCCC", "AGGGGGGGA", "AGGGGGGGA", "AGGGGGGGA", "AGGGGGGGA", "AGGGGGGGA", "AGGGGGGGA", "AGGGGGGGA", "AGGGGGGGA", "AGGGGGGGA", "AGGGGGGGA", "CCCCCCCCC")
                .where('A', states(MetaBlocks.METAL_CASING.getState(BlockMetalCasing.MetalCasingType.PTFE_INERT_CASING)))
                .where('B', states(MetaBlocks.METAL_CASING.getState(BlockMetalCasing.MetalCasingType.TITANIUM_STABLE)))
                .where('C', states(MetaBlocks.METAL_CASING.getState(BlockMetalCasing.MetalCasingType.PTFE_INERT_CASING)).setMinGlobalLimited(60)
                        .or(autoAbilities(true, true, true, true, true, true,false)))
                .where('D', states(MetaBlocks.METAL_CASING.getState(BlockMetalCasing.MetalCasingType.STAINLESS_CLEAN)))
                .where('E', states(MetaBlocks.METAL_CASING.getState(BlockMetalCasing.MetalCasingType.ALUMINIUM_FROSTPROOF)))
                .where('F', states(MetaBlocks.METAL_CASING.getState(BlockMetalCasing.MetalCasingType.INVAR_HEATPROOF)))
                .where('G', states(MOPMetaBlocks.MOP_GLASS_CASING.getState(MOPGlassCasing.CasingType.BOROSILICATE_GLASS)))
                .where('M', abilities(MultiblockAbility.MUFFLER_HATCH))
                .where('P', states(MetaBlocks.BOILER_CASING.getState(BlockBoilerCasing.BoilerCasingType.POLYTETRAFLUOROETHYLENE_PIPE)))
                .where('S', selfPredicate())
                .where('V', states(MetaBlocks.METAL_CASING.getState(BlockMetalCasing.MetalCasingType.PTFE_INERT_CASING))
                        .or(tieredCasing()))
                .where('#', air())
                .build();
    }

    @Override
    public ICubeRenderer getBaseTexture(IMultiblockPart iMultiblockPart) {
        return Textures.INERT_PTFE_CASING;
    }
}
