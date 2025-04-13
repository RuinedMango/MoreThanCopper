package com.ruinedmango.morethancopper.registries;

import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent;

public class CapabilityRegistry {
    public static void register(final RegisterCapabilitiesEvent event) {
	event.registerBlockEntity(Capabilities.EnergyStorage.BLOCK, BlockEntityRegistry.FLUX_CHARGER_ENTITY.get(),
		(o, direction) -> o.getEnergyHandler());
	event.registerBlockEntity(Capabilities.EnergyStorage.BLOCK,
		BlockEntityRegistry.EXPOSED_FLUX_CHARGER_ENTITY.get(), (o, direction) -> o.getEnergyHandler());
	event.registerBlockEntity(Capabilities.EnergyStorage.BLOCK,
		BlockEntityRegistry.WEATHERED_FLUX_CHARGER_ENTITY.get(), (o, direction) -> o.getEnergyHandler());
	event.registerBlockEntity(Capabilities.EnergyStorage.BLOCK,
		BlockEntityRegistry.OXIDIZED_FLUX_CHARGER_ENTITY.get(), (o, direction) -> o.getEnergyHandler());
    }
}
