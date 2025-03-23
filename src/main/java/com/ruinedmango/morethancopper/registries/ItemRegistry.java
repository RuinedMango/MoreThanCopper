package com.ruinedmango.morethancopper.registries;

import com.ruinedmango.morethancopper.MoreThanCopper;
import com.ruinedmango.morethancopper.items.Copperendium;

import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ItemRegistry {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(MoreThanCopper.MODID);

    public static final DeferredItem<Item> EXAMPLE_ITEM = ITEMS.register("copperendium",
	    () -> new Copperendium(new Item.Properties().setId(ResourceKey.create(ITEMS.getRegistryKey(),
		    ResourceLocation.parse(MoreThanCopper.MODID + ":copperendium")))));

    public static final DeferredItem<BlockItem> EXAMPLE_BLOCK_ITEM = ITEMS.registerSimpleBlockItem("example_block",
	    BlockRegistry.EXAMPLE_BLOCK);

    public static void register(IEventBus eventBus) {
	ITEMS.register(eventBus);
    }
}
