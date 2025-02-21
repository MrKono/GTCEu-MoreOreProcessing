package kono.ceu.mop.common.metatileentities.multi.primitive;

import java.util.List;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.*;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import org.apache.commons.lang3.ArrayUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import gregtech.api.GTValues;
import gregtech.api.gui.ModularUI;
import gregtech.api.gui.widgets.LabelWidget;
import gregtech.api.gui.widgets.ProgressWidget;
import gregtech.api.gui.widgets.RecipeProgressWidget;
import gregtech.api.gui.widgets.SlotWidget;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity;
import gregtech.api.metatileentity.multiblock.IMultiblockPart;
import gregtech.api.metatileentity.multiblock.RecipeMapPrimitiveMultiblockController;
import gregtech.api.pattern.BlockPattern;
import gregtech.api.pattern.FactoryBlockPattern;
import gregtech.api.pattern.TraceabilityPredicate;
import gregtech.api.util.GTUtility;
import gregtech.client.particle.VanillaParticleEffects;
import gregtech.client.renderer.CubeRendererState;
import gregtech.client.renderer.ICubeRenderer;
import gregtech.client.renderer.cclop.ColourOperation;
import gregtech.client.renderer.cclop.LightMapOperation;
import gregtech.client.renderer.texture.Textures;
import gregtech.client.utils.BloomEffectUtil;
import gregtech.common.ConfigHolder;

import kono.ceu.mop.api.recipes.MOPRecipeMaps;
import kono.ceu.mop.client.MOPTextures;
import kono.ceu.mop.common.blocks.Casing.MOPBlockPrimitiveCasing;
import kono.ceu.mop.common.blocks.MOPMetaBlocks;

import codechicken.lib.render.CCRenderState;
import codechicken.lib.render.pipeline.IVertexOperation;
import codechicken.lib.texture.TextureUtils;
import codechicken.lib.vec.Cuboid6;
import codechicken.lib.vec.Matrix4;

public class MetaTileEntityBronzeReinforcedBlastFurnace extends RecipeMapPrimitiveMultiblockController {

    private static final TraceabilityPredicate SNOW_PREDICATE = new TraceabilityPredicate(
            bws -> GTUtility.isBlockSnow(bws.getBlockState()));

    public MetaTileEntityBronzeReinforcedBlastFurnace(ResourceLocation metaTileEntityId) {
        super(metaTileEntityId, MOPRecipeMaps.BRONZE_REINFORCED_BLAST_FURNACE_RECIPES);
    }

    @Override
    public MetaTileEntity createMetaTileEntity(IGregTechTileEntity tileEntity) {
        return new MetaTileEntityBronzeReinforcedBlastFurnace(metaTileEntityId);
    }

    @NotNull
    @Override
    protected BlockPattern createStructurePattern() {
        return FactoryBlockPattern.start()
                .aisle("XXX", "XXX", "XXX", "XXX")
                .aisle("XXX", "X&X", "X#X", "X#X")
                .aisle("XXX", "XYX", "XXX", "XXX")
                .where('X',
                        states(MOPMetaBlocks.MOP_BLOCK_PRIMITIVE_CASING
                                .getState(MOPBlockPrimitiveCasing.CasingType.BRONZE_PLATED_FIREBRICKS)))
                .where('#', air())
                .where('&', air().or(SNOW_PREDICATE)) // this won't stay in the structure, and will be broken while
                                                      // running
                .where('Y', selfPredicate())
                .build();
    }

    @SideOnly(Side.CLIENT)
    @Override
    public ICubeRenderer getBaseTexture(IMultiblockPart sourcePart) {
        return MOPTextures.BRONZE_FIREBRICKS;
    }

    @Override
    protected ModularUI.Builder createUITemplate(EntityPlayer entityPlayer) {
        return ModularUI.builder(MOPTextures.PRIMITIVE_BACKGROUND_BRONZE, 176, 166)
                .shouldColor(false)
                .widget(new LabelWidget(5, 5, getMetaFullName()))
                .widget(new SlotWidget(importItems, 0, 52, 20, true, true)
                        .setBackgroundTexture(MOPTextures.PRIMITIVE_SLOT_BRONZE,
                                MOPTextures.PRIMITIVE_INGOT_OVERLAY_BRONZE))
                .widget(new SlotWidget(importItems, 1, 52, 38, true, true)
                        .setBackgroundTexture(MOPTextures.PRIMITIVE_SLOT_BRONZE,
                                MOPTextures.PRIMITIVE_DUST_OVERLAY_BRONZE))
                .widget(new SlotWidget(importItems, 2, 52, 56, true, true)
                        .setBackgroundTexture(MOPTextures.PRIMITIVE_SLOT_BRONZE,
                                MOPTextures.PRIMITIVE_FURNACE_OVERLAY_BRONZE))
                .widget(new RecipeProgressWidget(recipeMapWorkable::getProgressPercent, 77, 39, 20, 15,
                        MOPTextures.BRONZE_PRIMITIVE_BLAST_FURNACE_PROGRESS_BAR, ProgressWidget.MoveType.HORIZONTAL,
                        MOPRecipeMaps.BRONZE_REINFORCED_BLAST_FURNACE_RECIPES))
                .widget(new SlotWidget(exportItems, 0, 104, 29, true, false)
                        .setBackgroundTexture(MOPTextures.PRIMITIVE_SLOT_BRONZE,
                                MOPTextures.PRIMITIVE_INGOT_OVERLAY_BRONZE))
                .widget(new SlotWidget(exportItems, 1, 122, 29, true, false)
                        .setBackgroundTexture(MOPTextures.PRIMITIVE_SLOT_BRONZE,
                                MOPTextures.PRIMITIVE_INGOT_OVERLAY_BRONZE))
                .widget(new SlotWidget(exportItems, 2, 140, 29, true, false)
                        .setBackgroundTexture(MOPTextures.PRIMITIVE_SLOT_BRONZE,
                                MOPTextures.PRIMITIVE_INGOT_OVERLAY_BRONZE))
                .widget(new SlotWidget(exportItems, 3, 104, 47, true, false)
                        .setBackgroundTexture(MOPTextures.PRIMITIVE_SLOT_BRONZE,
                                MOPTextures.PRIMITIVE_DUST_OVERLAY_BRONZE))
                .widget(new SlotWidget(exportItems, 4, 122, 47, true, false)
                        .setBackgroundTexture(MOPTextures.PRIMITIVE_SLOT_BRONZE,
                                MOPTextures.PRIMITIVE_DUST_OVERLAY_BRONZE))
                .widget(new SlotWidget(exportItems, 5, 140, 47, true, false)
                        .setBackgroundTexture(MOPTextures.PRIMITIVE_SLOT_BRONZE,
                                MOPTextures.PRIMITIVE_DUST_OVERLAY_BRONZE))
                .bindPlayerInventory(entityPlayer.inventory, MOPTextures.PRIMITIVE_SLOT_BRONZE, 0);
    }

    @Override
    public void renderMetaTileEntity(CCRenderState renderState, Matrix4 translation, IVertexOperation[] pipeline) {
        super.renderMetaTileEntity(renderState, translation, pipeline);
        getFrontOverlay().renderOrientedState(renderState, translation, pipeline, getFrontFacing(),
                recipeMapWorkable.isActive(), recipeMapWorkable.isWorkingEnabled());
        if (recipeMapWorkable.isActive() && isStructureFormed()) {
            EnumFacing back = getFrontFacing().getOpposite();
            Matrix4 offset = translation.copy().translate(back.getXOffset(), -0.3, back.getZOffset());
            CubeRendererState op = Textures.RENDER_STATE.get();
            Textures.RENDER_STATE.set(new CubeRendererState(op.layer, CubeRendererState.PASS_MASK, op.world));
            Textures.renderFace(renderState, offset,
                    ArrayUtils.addAll(pipeline, new LightMapOperation(240, 240), new ColourOperation(0xFFFFFFFF)),
                    EnumFacing.UP, Cuboid6.full, TextureUtils.getBlockTexture("lava_still"),
                    BloomEffectUtil.getEffectiveBloomLayer());
            Textures.RENDER_STATE.set(op);
        }
    }

    @SideOnly(Side.CLIENT)
    @NotNull
    @Override
    protected ICubeRenderer getFrontOverlay() {
        return Textures.PRIMITIVE_BLAST_FURNACE_OVERLAY;
    }

    @Override
    public boolean hasMaintenanceMechanics() {
        return false;
    }

    @Override
    public void update() {
        super.update();

        if (this.isActive()) {
            if (getWorld().isRemote) {
                VanillaParticleEffects.PBF_SMOKE.runEffect(this);
            } else {
                damageEntitiesAndBreakSnow();
            }
        }
    }

    private void damageEntitiesAndBreakSnow() {
        BlockPos middlePos = this.getPos();
        middlePos = middlePos.offset(getFrontFacing().getOpposite());
        this.getWorld().getEntitiesWithinAABB(EntityLivingBase.class, new AxisAlignedBB(middlePos))
                .forEach(entity -> entity.attackEntityFrom(DamageSource.LAVA, 3.0f));

        if (getOffsetTimer() % 10 == 0) {
            IBlockState state = getWorld().getBlockState(middlePos);
            GTUtility.tryBreakSnow(getWorld(), middlePos, state, true);
        }
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void randomDisplayTick() {
        if (this.isActive()) {
            VanillaParticleEffects.defaultFrontEffect(this, 0.3F, EnumParticleTypes.SMOKE_LARGE,
                    EnumParticleTypes.FLAME);
            if (ConfigHolder.machines.machineSounds && GTValues.RNG.nextDouble() < 0.1) {
                BlockPos pos = getPos();
                getWorld().playSound(pos.getX() + 0.5F, pos.getY() + 0.5F, pos.getZ() + 0.5F,
                        SoundEvents.BLOCK_FURNACE_FIRE_CRACKLE, SoundCategory.BLOCKS, 1.0F, 1.0F, false);
            }
        }
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World world, @NotNull List<String> tooltip,
                               boolean advanced) {
        super.addInformation(stack, world, tooltip, advanced);
    }
}
