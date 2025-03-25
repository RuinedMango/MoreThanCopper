package com.ruinedmango.morethancopper.registries;

import com.ruinedmango.morethancopper.screen.copperendium.CopperendiumScreen;

import net.neoforged.neoforge.client.event.RegisterMenuScreensEvent;

public class MenuScreenRegistry {
    public static void registerScreens(final RegisterMenuScreensEvent event) {
	event.register(MenuRegistry.COPPERENDIUM_MENU.get(), CopperendiumScreen::new);
    }
}
