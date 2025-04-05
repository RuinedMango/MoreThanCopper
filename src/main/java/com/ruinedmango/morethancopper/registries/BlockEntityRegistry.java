package com.ruinedmango.morethancopper.registries;

import java.util.function.Supplier;

import com.ruinedmango.morethancopper.MoreThanCopper;
import com.ruinedmango.morethancopper.blocks.fluxfurnace.FluxGeneratorEntity;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

public class BlockEntityRegistry {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister
	    .create(BuiltInRegistries.BLOCK_ENTITY_TYPE, MoreThanCopper.MODID);

    public static final Supplier<BlockEntityType<FluxGeneratorEntity>> FLUX_GENERATOR_ENTITY = BLOCK_ENTITIES.register(
	    "flux_generator",
	    () -> new BlockEntityType<>(FluxGeneratorEntity::new, BlockRegistry.FLUX_GENERATOR.get()));

    public static void register(IEventBus eventBus) {
	BLOCK_ENTITIES.register(eventBus);
    }
}
