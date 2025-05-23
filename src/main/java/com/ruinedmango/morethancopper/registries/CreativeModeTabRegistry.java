package com.ruinedmango.morethancopper.registries;

import com.ruinedmango.morethancopper.MoreThanCopper;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class CreativeModeTabRegistry {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister
	    .create(Registries.CREATIVE_MODE_TAB, MoreThanCopper.MODID);

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> EXAMPLE_TAB = CREATIVE_MODE_TABS.register(
	    "morethancopper_tab",
	    () -> CreativeModeTab.builder().title(Component.translatable("itemGroup.morethancopper"))
		    .withTabsBefore(CreativeModeTabs.COMBAT)
		    .icon(() -> ItemRegistry.COPPERENDIUM.get().getDefaultInstance())
		    .displayItems((parameters, output) -> {
			output.accept(ItemRegistry.COPPERENDIUM.get());
			output.accept(ItemRegistry.FLUX_BATTERY.get());
			output.accept(ItemRegistry.OXIDIZER_CORE_ITEM.get());
			output.accept(ItemRegistry.EXPOSED_OXIDIZER_CORE_ITEM.get());
			output.accept(ItemRegistry.WEATHERED_OXIDIZER_CORE_ITEM.get());
			output.accept(ItemRegistry.OXIDIZED_OXIDIZER_CORE_ITEM.get());
			output.accept(ItemRegistry.FLUX_GENERATOR_ITEM.get());
			output.accept(ItemRegistry.EXPOSED_FLUX_GENERATOR_ITEM.get());
			output.accept(ItemRegistry.WEATHERED_FLUX_GENERATOR_ITEM.get());
			output.accept(ItemRegistry.OXIDIZED_FLUX_GENERATOR_ITEM.get());
			output.accept(ItemRegistry.FLUX_CHARGER_ITEM.get());
			output.accept(ItemRegistry.EXPOSED_FLUX_CHARGER_ITEM.get());
			output.accept(ItemRegistry.WEATHERED_FLUX_CHARGER_ITEM.get());
			output.accept(ItemRegistry.OXIDIZED_FLUX_CHARGER_ITEM.get());
		    }).build());

    public static void register(IEventBus eventBus) {
	CREATIVE_MODE_TABS.register(eventBus);
    }
}
