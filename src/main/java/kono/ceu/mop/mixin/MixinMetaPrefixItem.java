package kono.ceu.mop.mixin;

import gregtech.api.items.materialitem.MetaPrefixItem;
import gregtech.api.unification.OreDictUnifier;
import gregtech.api.unification.material.Material;
import gregtech.api.unification.material.properties.OreProperty;
import gregtech.api.unification.material.properties.PropertyKey;
import gregtech.api.unification.material.registry.MaterialRegistry;
import gregtech.api.unification.ore.OrePrefix;
import net.minecraft.block.BlockCauldron;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Random;

import static gregtech.api.items.materialitem.MetaPrefixItem.purifyMap;


@Mixin(MetaPrefixItem.class)
public abstract class MixinMetaPrefixItem {

    private final MaterialRegistry registry;
    private final OrePrefix prefix;

    protected MixinMetaPrefixItem(@NotNull MaterialRegistry registry, @NotNull OrePrefix prefix) {
        super();
        this.registry = registry;
        this.prefix = prefix;
    }

    @Nullable
    private Material getMaterial(@NotNull ItemStack stack) {
        return registry.getObjectById(stack.getMetadata());
    }

    @Inject(method = "onEntityItemUpdate", at = @At(value = "HEAD", remap = false), remap = false, cancellable = true)
    public void onEntityItemUpdateMixin(EntityItem itemEntity, CallbackInfoReturnable<Boolean> cir) {
        if (itemEntity.getEntityWorld().isRemote)
            cir.setReturnValue(false);

        Material material = getMaterial(itemEntity.getItem());
        if (!purifyMap.containsKey(this.prefix))
            cir.setReturnValue(false);

        BlockPos blockPos = new BlockPos(itemEntity);
        IBlockState blockState = itemEntity.getEntityWorld().getBlockState(blockPos);

        if (!(blockState.getBlock() instanceof BlockCauldron))
            cir.setReturnValue(false);

        int waterLevel = blockState.getValue(BlockCauldron.LEVEL);
        if (waterLevel == 0)
            cir.setReturnValue(false);

        //int rum = new Random().nextInt(10);
        itemEntity.getEntityWorld().setBlockState(blockPos,
                blockState.withProperty(BlockCauldron.LEVEL, waterLevel - 1));
        ItemStack replacementStack = OreDictUnifier.get(purifyMap.get(prefix), material,
                //itemEntity.getItem().getCount()
                10);
        //if (rum == 9) {
            Material byproductMaterial = material.getProperty(PropertyKey.ORE).getOreByProduct(
                   prefix == OrePrefix.dustPure ? 1 : 0, material);
            ItemStack byproductStack = OreDictUnifier.get(OrePrefix.dust, byproductMaterial,
                    itemEntity.getItem().getCount());
            itemEntity.entityDropItem(byproductStack, 1.0f);
        //}
        itemEntity.setItem(replacementStack);
        cir.setReturnValue(false);
        cir.cancel();
    }
}
