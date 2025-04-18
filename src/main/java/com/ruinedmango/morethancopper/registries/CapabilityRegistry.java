package com.ruinedmango.morethancopper.registries;

import com.ruinedmango.morethancopper.energy.ComponentOxidizedFluxStorage;

import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent;

public class CapabilityRegistry {
    public static void register(final RegisterCapabilitiesEvent event) {
	event.registerItem(Capabilities.EnergyStorage.ITEM, (itemStack,
		context) -> new ComponentOxidizedFluxStorage(itemStack, DataComponentRegistry.OF.get(), 20000, 10),
		ItemRegistry.FLUX_BATTERY.get());
	event.registerBlockEntity(Capabilities.EnergyStorage.BLOCK, BlockEntityRegistry.FLUX_CHARGER_ENTITY.get(),
		(o, direction) -> o.getEnergyHandler());
	event.registerBlockEntity(Capabilities.EnergyStorage.BLOCK,
		BlockEntityRegistry.EXPOSED_FLUX_CHARGER_ENTITY.get(), (o, direction) -> o.getEnergyHandler());
	event.registerBlockEntity(Capabilities.EnergyStorage.BLOCK,
		BlockEntityRegistry.WEATHERED_FLUX_CHARGER_ENTITY.get(), (o, direction) -> o.getEnergyHandler());
	event.registerBlockEntity(Capabilities.EnergyStorage.BLOCK,
		BlockEntityRegistry.OXIDIZED_FLUX_CHARGER_ENTITY.get(), (o, direction) -> o.getEnergyHandler());
	event.registerBlockEntity(Capabilities.EnergyStorage.BLOCK, BlockEntityRegistry.FLUX_GENERATOR_ENTITY.get(),
		(o, direction) -> o.getEnergyHandler());
	event.registerBlockEntity(Capabilities.EnergyStorage.BLOCK,
		BlockEntityRegistry.EXPOSED_FLUX_GENERATOR_ENTITY.get(), (o, direction) -> o.getEnergyHandler());
	event.registerBlockEntity(Capabilities.EnergyStorage.BLOCK,
		BlockEntityRegistry.WEATHERED_FLUX_GENERATOR_ENTITY.get(), (o, direction) -> o.getEnergyHandler());
	event.registerBlockEntity(Capabilities.EnergyStorage.BLOCK,
		BlockEntityRegistry.OXIDIZED_FLUX_GENERATOR_ENTITY.get(), (o, direction) -> o.getEnergyHandler());
    }
}
