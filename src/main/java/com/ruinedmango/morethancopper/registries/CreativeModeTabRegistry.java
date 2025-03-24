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
	    "example_tab",
	    () -> CreativeModeTab.builder().title(Component.translatable("itemGroup.morethancopper"))
		    .withTabsBefore(CreativeModeTabs.COMBAT)
		    .icon(() -> ItemRegistry.COPPERENDIUM.get().getDefaultInstance())
		    .displayItems((parameters, output) -> {
			output.accept(ItemRegistry.COPPERENDIUM.get());
		    }).build());

    public static void register(IEventBus eventBus) {
	CREATIVE_MODE_TABS.register(eventBus);
    }
}
