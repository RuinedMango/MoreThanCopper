package com.ruinedmango.morethancopper.registries;

import java.util.function.Supplier;

import com.ruinedmango.morethancopper.MoreThanCopper;
import com.ruinedmango.morethancopper.blocks.fluxcharger.FluxChargerEntity;
import com.ruinedmango.morethancopper.blocks.fluxgenerator.FluxGeneratorEntity;

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
    public static final Supplier<BlockEntityType<FluxGeneratorEntity>> EXPOSED_FLUX_GENERATOR_ENTITY = BLOCK_ENTITIES
	    .register("exposed_flux_generator",
		    () -> new BlockEntityType<>(FluxGeneratorEntity::new, BlockRegistry.EXPOSED_FLUX_GENERATOR.get()));
    public static final Supplier<BlockEntityType<FluxGeneratorEntity>> WEATHERED_FLUX_GENERATOR_ENTITY = BLOCK_ENTITIES
	    .register("weathered_flux_generator", () -> new BlockEntityType<>(FluxGeneratorEntity::new,
		    BlockRegistry.WEATHERED_FLUX_GENERATOR.get()));
    public static final Supplier<BlockEntityType<FluxGeneratorEntity>> OXIDIZED_FLUX_GENERATOR_ENTITY = BLOCK_ENTITIES
	    .register("oxidized_flux_generator",
		    () -> new BlockEntityType<>(FluxGeneratorEntity::new, BlockRegistry.OXIDIZED_FLUX_GENERATOR.get()));

    public static final Supplier<BlockEntityType<FluxChargerEntity>> FLUX_CHARGER_ENTITY = BLOCK_ENTITIES.register(
	    "flux_charger", () -> new BlockEntityType<>(FluxChargerEntity::new, BlockRegistry.FLUX_CHARGER.get()));
    public static final Supplier<BlockEntityType<FluxChargerEntity>> EXPOSED_FLUX_CHARGER_ENTITY = BLOCK_ENTITIES
	    .register("exposed_flux_charger",
		    () -> new BlockEntityType<>(FluxChargerEntity::new, BlockRegistry.EXPOSED_FLUX_CHARGER.get()));
    public static final Supplier<BlockEntityType<FluxChargerEntity>> WEATHERED_FLUX_CHARGER_ENTITY = BLOCK_ENTITIES
	    .register("weathered_flux_charger",
		    () -> new BlockEntityType<>(FluxChargerEntity::new, BlockRegistry.WEATHERED_FLUX_CHARGER.get()));
    public static final Supplier<BlockEntityType<FluxChargerEntity>> OXIDIZED_FLUX_CHARGER_ENTITY = BLOCK_ENTITIES
	    .register("oxidized_flux_charger",
		    () -> new BlockEntityType<>(FluxChargerEntity::new, BlockRegistry.OXIDIZED_FLUX_CHARGER.get()));

    public static void register(IEventBus eventBus) {
	BLOCK_ENTITIES.register(eventBus);
    }
}
