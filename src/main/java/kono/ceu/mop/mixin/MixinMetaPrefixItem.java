package kono.ceu.mop.mixin;

import static gregtech.api.items.materialitem.MetaPrefixItem.purifyMap;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockCauldron;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import gregtech.api.items.materialitem.MetaPrefixItem;
import gregtech.api.unification.OreDictUnifier;
import gregtech.api.unification.material.Material;
import gregtech.api.unification.material.properties.PropertyKey;
import gregtech.api.unification.material.registry.MaterialRegistry;
import gregtech.api.unification.ore.OrePrefix;

@Mixin(MetaPrefixItem.class)
public abstract class MixinMetaPrefixItem {

    private final MaterialRegistry registry;
    private final OrePrefix prefix;

    public MixinMetaPrefixItem(@NotNull MaterialRegistry registry, @NotNull OrePrefix prefix) {
        super();
        this.registry = registry;
        this.prefix = prefix;
    }

    @Nullable
    public Material getMaterial(@NotNull ItemStack stack) {
        return registry.getObjectById(stack.getMetadata());
    }

    @Inject(method = "onEntityItemUpdate", at = @At(value = "HEAD", remap = false), remap = false, cancellable = true)
    public boolean onEntityItemUpdateMixin(EntityItem itemEntity, CallbackInfoReturnable<Boolean> cir) {
        cir.cancel();
        return false;
    }

    @NotNull
    public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, @NotNull EnumHand hand) {
        // Get held item by ItemStack
        ItemStack stack = player.getHeldItem(hand);
        Material material = getMaterial(stack);
        int num = new Random().nextInt(100);
        if (!world.isRemote && purifyMap.containsKey(this.prefix)) {
            RayTraceResult ray = this.rayTrace(world, player, false);
            if (ray.typeOfHit == RayTraceResult.Type.BLOCK) {
                BlockPos pos = ray.getBlockPos();
                IBlockState state = world.getBlockState(pos);
                Block block = state.getBlock();
                if (block instanceof BlockCauldron) {
                    int waterLevel = state.getValue(BlockCauldron.LEVEL);
                    if (waterLevel != 0) {
                        ItemStack replacementStack = OreDictUnifier.get(purifyMap.get(prefix), material, 1);
                        Material byproductMaterial = material.getProperty(PropertyKey.ORE).getOreByProduct(
                                prefix == OrePrefix.dustPure ? 1 : 0, material);
                        ItemStack byproductStack = OreDictUnifier.get(OrePrefix.dust, byproductMaterial, 1);
                        ((BlockCauldron) block).setWaterLevel(world, pos, state, waterLevel - 1);
                        stack.shrink(1);
                        player.dropItem(replacementStack, false);
                        if (num < 3) {
                            player.dropItem(byproductStack, false);
                        }
                    }
                }
            }
            return new ActionResult<>(EnumActionResult.SUCCESS, stack);
        }
        return new ActionResult<>(EnumActionResult.FAIL, stack);
    }

    protected RayTraceResult rayTrace(World world, EntityPlayer player, boolean useLiquids) {
        float f = player.rotationPitch;
        float f1 = player.rotationYaw;
        double d0 = player.posX;
        double d1 = player.posY + (double) player.getEyeHeight();
        double d2 = player.posZ;
        Vec3d vec3d = new Vec3d(d0, d1, d2);
        float f2 = MathHelper.cos(-f1 * 0.017453292F - (float) Math.PI);
        float f3 = MathHelper.sin(-f1 * 0.017453292F - (float) Math.PI);
        float f4 = -MathHelper.cos(-f * 0.017453292F);
        float f5 = MathHelper.sin(-f * 0.017453292F);
        float f6 = f3 * f4;
        float f7 = f2 * f4;
        double d3 = player.getEntityAttribute(EntityPlayer.REACH_DISTANCE).getAttributeValue();
        Vec3d vec3d1 = vec3d.add((double) f6 * d3, (double) f5 * d3, (double) f7 * d3);
        return world.rayTraceBlocks(vec3d, vec3d1, useLiquids, !useLiquids, false);
    }
}
