package com.ruinedmango.morethancopper.registries;

import com.ruinedmango.morethancopper.screen.copperendium.CopperendiumScreen;
import com.ruinedmango.morethancopper.screen.fluxcharger.FluxChargerScreen;
import com.ruinedmango.morethancopper.screen.fluxgenerator.FluxGeneratorScreen;

import net.neoforged.neoforge.client.event.RegisterMenuScreensEvent;

public class MenuScreenRegistry {
    public static void register(final RegisterMenuScreensEvent event) {
	event.register(MenuRegistry.COPPERENDIUM_MENU.get(), CopperendiumScreen::new);
	event.register(MenuRegistry.FLUX_GENERATOR_MENU.get(), FluxGeneratorScreen::new);
	event.register(MenuRegistry.FLUX_CHARGER_MENU.get(), FluxChargerScreen::new);
    }
}
