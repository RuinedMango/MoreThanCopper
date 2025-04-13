package com.ruinedmango.morethancopper.registries;

import com.ruinedmango.morethancopper.MoreThanCopper;
import com.ruinedmango.morethancopper.screen.copperendium.CopperendiumMenu;
import com.ruinedmango.morethancopper.screen.fluxcharger.FluxChargerMenu;
import com.ruinedmango.morethancopper.screen.fluxgenerator.FluxGeneratorMenu;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.common.extensions.IMenuTypeExtension;
import net.neoforged.neoforge.network.IContainerFactory;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class MenuRegistry {
    public static final DeferredRegister<MenuType<?>> MENUS = DeferredRegister.create(Registries.MENU,
	    MoreThanCopper.MODID);

    public static final DeferredHolder<MenuType<?>, MenuType<CopperendiumMenu>> COPPERENDIUM_MENU = registerMenuType(
	    "copperendium_menu", CopperendiumMenu::new);
    public static final DeferredHolder<MenuType<?>, MenuType<FluxGeneratorMenu>> FLUX_GENERATOR_MENU = registerMenuType(
	    "flux_generator_menu", FluxGeneratorMenu::new);
    public static final DeferredHolder<MenuType<?>, MenuType<FluxChargerMenu>> FLUX_CHARGER_MENU = registerMenuType(
	    "flux_charger_menu", FluxChargerMenu::new);

    private static <T extends AbstractContainerMenu> DeferredHolder<MenuType<?>, MenuType<T>> registerMenuType(
	    String name, IContainerFactory<T> factory) {
	return MENUS.register(name, () -> IMenuTypeExtension.create(factory));
    }

    public static void register(IEventBus eventBus) {
	MENUS.register(eventBus);
    }
}
