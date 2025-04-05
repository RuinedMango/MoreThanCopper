package com.ruinedmango.morethancopper.registries;

import com.ruinedmango.morethancopper.MoreThanCopper;
import com.ruinedmango.morethancopper.items.Copperendium;

import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ItemRegistry {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(MoreThanCopper.MODID);

    public static final DeferredItem<Item> COPPERENDIUM = ITEMS.register("copperendium",
	    () -> new Copperendium(new Item.Properties().rarity(Rarity.RARE).setId(ResourceKey
		    .create(ITEMS.getRegistryKey(), ResourceLocation.parse(MoreThanCopper.MODID + ":copperendium")))));

    public static final DeferredItem<BlockItem> OXIDIZER_CORE_ITEM = ITEMS.registerSimpleBlockItem("oxidizer_core",
	    BlockRegistry.OXIDIZER_CORE);
    public static final DeferredItem<BlockItem> EXPOSED_OXIDIZER_CORE_ITEM = ITEMS
	    .registerSimpleBlockItem("exposed_oxidizer_core", BlockRegistry.EXPOSED_OXIDIZER_CORE);
    public static final DeferredItem<BlockItem> WEATHERED_OXIDIZER_CORE_ITEM = ITEMS
	    .registerSimpleBlockItem("weathered_oxidizer_core", BlockRegistry.WEATHERED_OXIDIZER_CORE);
    public static final DeferredItem<BlockItem> OXIDIZED_OXIDIZER_CORE_ITEM = ITEMS
	    .registerSimpleBlockItem("oxidized_oxidizer_core", BlockRegistry.OXIDIZED_OXIDIZER_CORE);

    public static final DeferredItem<BlockItem> FLUX_GENERATOR_ITEM = ITEMS.registerSimpleBlockItem("flux_generator",
	    BlockRegistry.FLUX_GENERATOR);

    public static void register(IEventBus eventBus) {
	ITEMS.register(eventBus);
    }
}
