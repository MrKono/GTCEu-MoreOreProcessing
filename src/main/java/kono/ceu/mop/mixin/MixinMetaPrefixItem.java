package kono.ceu.mop.mixin;

import static gregtech.api.items.materialitem.MetaPrefixItem.purifyMap;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.minecraft.block.Block;
import net.minecraft.block.BlockCauldron;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import gregtech.api.damagesources.DamageSources;
import gregtech.api.items.materialitem.MetaPrefixItem;
import gregtech.api.unification.OreDictUnifier;
import gregtech.api.unification.material.Material;
import gregtech.api.unification.material.registry.MaterialRegistry;
import gregtech.api.unification.ore.OrePrefix;
import gregtech.core.sound.GTSoundEvents;

import kono.ceu.mop.MOPConfig;

@Mixin(value = MetaPrefixItem.class, remap = false)
public abstract class MixinMetaPrefixItem {

    private final boolean easyCooling = MOPConfig.difficulty.easyCooling;
    private final boolean easyCleaning = MOPConfig.difficulty.easyCleaning;
    private final boolean hardCleaning = MOPConfig.difficulty.hardCleaning;

    private final MaterialRegistry registry;
    private final OrePrefix prefix;

    private static final Map<OrePrefix, OrePrefix> hotMap = new HashMap<>();

    static {
        hotMap.put(OrePrefix.ingotHot, OrePrefix.nugget);
    }

    public MixinMetaPrefixItem(@NotNull MaterialRegistry registry, @NotNull OrePrefix prefix) {
        super();
        this.registry = registry;
        this.prefix = prefix;
    }

    @Nullable
    public Material getMaterial(@NotNull ItemStack stack) {
        return registry.getObjectById(stack.getMetadata());
    }

    @Inject(method = "onEntityItemUpdate",
            at = @At(value = "INVOKE",
                     target = "Lgregtech/api/items/materialitem/MetaPrefixItem;getMaterial(Lnet/minecraft/item/ItemStack;)Lgregtech/api/unification/material/Material;"),
            cancellable = true)
    public void onEntityItemUpdateMixin(EntityItem itemEntity, CallbackInfoReturnable<Boolean> cir) {
        int count = itemEntity.getItem().getCount();
        ItemStack stack = itemEntity.getItem();
        World worlds = itemEntity.getEntityWorld();
        Material mat = getMaterial(itemEntity.getItem());
        // Cooling with water
        if (easyCooling) {
            if (hotMap.containsKey(this.prefix)) {
                boolean checkWater = true;
                BlockPos pos = itemEntity.getPosition();
                AxisAlignedBB boundingBox = new AxisAlignedBB(
                        itemEntity.posX - 2, itemEntity.posY - 2, itemEntity.posZ - 2,
                        itemEntity.posX + 2, itemEntity.posY + 2, itemEntity.posZ + 2);
                List<EntityPlayer> players1 = itemEntity.world.getEntitiesWithinAABB(EntityPlayer.class, boundingBox);
                float heatDamage = prefix.heatDamageFunction.apply(mat.getBlastTemperature());
                for (int left = -1; left <= 1; left++) {
                    for (int up = -1; up <= 1; up++) {
                        BlockPos checkPos = pos.add(left, 0, up);
                        IBlockState state = itemEntity.world.getBlockState(checkPos);
                        Block block = state.getBlock();
                        if (block != Blocks.WATER) {
                            checkWater = false;
                        }
                    }
                    if (!checkWater) {
                        break;
                    }
                }
                if (checkWater) {
                    ItemStack newStack = stack.copy();
                    NBTTagCompound data = itemEntity.getEntityData();
                    if (!data.hasKey("cooling")) {
                        itemEntity.getEntityData().setInteger("cooling", 0);
                    }
                    int cooling = data.getInteger("cooling");
                    if (cooling < 200) {
                        if (cooling % 40 == 0) {
                            itemEntity.playSound(SoundEvents.BLOCK_FIRE_EXTINGUISH, 1.0F, 1.0F);
                            for (EntityPlayer player : players1) {
                                player.attackEntityFrom(DamageSources.getHeatDamage().setDamageBypassesArmor(),
                                        heatDamage);
                            }
                            data.setInteger("cooling", cooling + 1);
                        } else if (cooling % 10 == 0) {
                            itemEntity.playSound(SoundEvents.BLOCK_FIRE_EXTINGUISH, 1.0F, 1.0F);
                            data.setInteger("cooling", cooling + 1);
                        } else {
                            data.setInteger("cooling", cooling + 1);
                        }
                    } else {
                        itemEntity.getEntityData().removeTag("cooling");
                        itemEntity.world.setBlockState(pos, Blocks.AIR.getDefaultState());
                        itemEntity.playSound(SoundEvents.BLOCK_FIRE_EXTINGUISH, 1.0F, -2.0F);
                        itemEntity.playSound(SoundEvents.ENTITY_ITEM_BREAK, 1.0F, 1.0F);
                        ItemStack nuggetStack = OreDictUnifier.get(hotMap.get(prefix), mat, 9);
                        EntityItem nuggetEntity = new EntityItem(worlds, pos.getX(), pos.getY() + 0.25, pos.getZ(),
                                nuggetStack);
                        if (count > 1) {
                            newStack.setCount(count - 1);
                            EntityItem overStack = new EntityItem(worlds, pos.getX(), pos.getY(), pos.getZ(),
                                    newStack);
                            itemEntity.world.spawnEntity(overStack);
                        }
                        List<EntityPlayer> players2 = itemEntity.world.getEntitiesWithinAABB(EntityPlayer.class,
                                boundingBox.expand(2.0, 2.0, 2.0));
                        for (EntityPlayer player : players2) {
                            player.attackEntityFrom(DamageSources.getHeatDamage().setDamageBypassesArmor(),
                                    heatDamage + 0.5F);
                        }
                        itemEntity.world.spawnEntity(nuggetEntity);
                        itemEntity.setDead();
                    }
                    cir.setReturnValue(false);
                }
            }
        }
        // Washing with water
        if (easyCleaning) {
            if (purifyMap.containsKey(this.prefix)) {
                BlockPos pos = itemEntity.getPosition();
                IBlockState state = itemEntity.world.getBlockState(pos);
                Block block = state.getBlock();
                if (block == Blocks.WATER) {
                    ItemStack replacementStack = OreDictUnifier.get(purifyMap.get(prefix),
                            mat, 1);
                    if (count > 1) {
                        ItemStack newStack = itemEntity.getItem().copy();
                        newStack.setCount(count - 1);
                        EntityItem overStack = new EntityItem(worlds, itemEntity.posX, itemEntity.posY,
                                itemEntity.posZ, newStack);
                        itemEntity.world.spawnEntity(overStack);
                        overStack.setPickupDelay(10);
                    }
                    itemEntity.playSound(GTSoundEvents.BATH, 0.5F, 1.0F);
                    itemEntity.world.setBlockState(pos, Blocks.AIR.getDefaultState());
                    itemEntity.setItem(replacementStack);
                    cir.setReturnValue(false);
                }
            }
        }
        // Cleaning onw by one
        if (hardCleaning) {
            if (purifyMap.containsKey(this.prefix)) {
                BlockPos blockPos = new BlockPos(itemEntity);
                IBlockState blockState = itemEntity.getEntityWorld().getBlockState(blockPos);
                if ((blockState.getBlock() instanceof BlockCauldron)) {
                    int waterLevel = blockState.getValue(BlockCauldron.LEVEL);
                    if (waterLevel != 0) {
                        itemEntity.getEntityWorld().setBlockState(blockPos,
                                blockState.withProperty(BlockCauldron.LEVEL, waterLevel - 1));
                        ItemStack replacementStack = OreDictUnifier.get(purifyMap.get(prefix),
                                getMaterial(itemEntity.getItem()), 1);
                        EntityItem cleanStack = new EntityItem(worlds, itemEntity.posX, itemEntity.posY + 0.1,
                                itemEntity.posZ, replacementStack);
                        itemEntity.world.spawnEntity(cleanStack);
                        if (count > 1) {
                            ItemStack newStack = itemEntity.getItem().copy();
                            newStack.setCount(count - 1);
                            itemEntity.setItem(newStack);
                        } else {
                            itemEntity.setDead();
                        }
                    }
                    cir.setReturnValue(false);
                }
            }
            cir.cancel();
        }
    }
}
