package com.ruinedmango.morethancopper;

import org.slf4j.Logger;

import com.mojang.logging.LogUtils;
import com.ruinedmango.morethancopper.registries.BlockEntityRegistry;
import com.ruinedmango.morethancopper.registries.BlockRegistry;
import com.ruinedmango.morethancopper.registries.CreativeModeTabRegistry;
import com.ruinedmango.morethancopper.registries.ItemRegistry;
import com.ruinedmango.morethancopper.registries.MenuRegistry;
import com.ruinedmango.morethancopper.registries.MenuScreenRegistry;

import net.minecraft.client.Minecraft;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.event.server.ServerStartingEvent;

// The value here should match an entry in the META-INF/neoforge.mods.toml file
@Mod(MoreThanCopper.MODID)
public class MoreThanCopper {
    // Define mod id in a common place for everything to reference
    public static final String MODID = "morethancopper";
    // Directly reference a slf4j logger
    private static final Logger LOGGER = LogUtils.getLogger();

    public MoreThanCopper(IEventBus modEventBus, ModContainer modContainer) {
	modEventBus.addListener(this::commonSetup);

	BlockRegistry.register(modEventBus);
	ItemRegistry.register(modEventBus);
	CreativeModeTabRegistry.register(modEventBus);
	MenuRegistry.register(modEventBus);
	BlockEntityRegistry.register(modEventBus);

	NeoForge.EVENT_BUS.register(this);

	modEventBus.addListener(this::addCreative);
	modEventBus.addListener(MenuScreenRegistry::registerScreens);

	modContainer.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {

	if (Config.logDirtBlock)
	    LOGGER.info("DIRT BLOCK >> {}", BuiltInRegistries.BLOCK.getKey(Blocks.DIRT));

	LOGGER.info(Config.magicNumberIntroduction + Config.magicNumber);

	Config.items.forEach((item) -> LOGGER.info("ITEM >> {}", item.toString()));
    }

    // Add the example block item to the building blocks tab
    private void addCreative(BuildCreativeModeTabContentsEvent event) {
	// if (event.getTabKey() == CreativeModeTabs.BUILDING_BLOCKS)
	// event.accept(ItemRegistry.EXAMPLE_BLOCK_ITEM);
    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
	// Do something when the server starts
	LOGGER.info("HELLO from server starting");
    }

    // You can use EventBusSubscriber to automatically register all static methods
    // in the class annotated with @SubscribeEvent
    @EventBusSubscriber(modid = MODID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {
	@SubscribeEvent
	public static void onClientSetup(FMLClientSetupEvent event) {
	    // Some client setup code
	    LOGGER.info("HELLO FROM CLIENT SETUP");
	    LOGGER.info("MINECRAFT NAME >> {}", Minecraft.getInstance().getUser().getName());
	}
    }
}
