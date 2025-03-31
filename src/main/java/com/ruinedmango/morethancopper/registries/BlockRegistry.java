package com.ruinedmango.morethancopper.registries;

import com.ruinedmango.morethancopper.MoreThanCopper;
import com.ruinedmango.morethancopper.blocks.OxidizerCore;

import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.WeatheringCopper;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;
import net.minecraft.world.level.material.MapColor;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

public class BlockRegistry {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(MoreThanCopper.MODID);

    public static final DeferredBlock<Block> OXIDIZER_CORE = BLOCKS.register("oxidizer_core",
	    () -> new OxidizerCore(WeatheringCopper.WeatherState.UNAFFECTED,
		    Properties.ofFullCopy(Blocks.COPPER_BLOCK).setId(ResourceKey.create(BLOCKS.getRegistryKey(),
			    ResourceLocation.parse(MoreThanCopper.MODID + ":oxidizer_core")))));
    public static final DeferredBlock<Block> EXPOSED_OXIDIZER_CORE = BLOCKS.register("exposed_oxidizer_core",
	    () -> new OxidizerCore(WeatheringCopper.WeatherState.EXPOSED,
		    Properties.ofFullCopy(Blocks.EXPOSED_COPPER).setId(ResourceKey.create(BLOCKS.getRegistryKey(),
			    ResourceLocation.parse(MoreThanCopper.MODID + ":exposed_oxidizer_core")))));
    public static final DeferredBlock<Block> WEATHERED_OXIDIZER_CORE = BLOCKS.register("weathered_oxidizer_core",
	    () -> new OxidizerCore(WeatheringCopper.WeatherState.WEATHERED,
		    Properties.ofFullCopy(Blocks.WEATHERED_COPPER).setId(ResourceKey.create(BLOCKS.getRegistryKey(),
			    ResourceLocation.parse(MoreThanCopper.MODID + ":weathered_oxidizer_core")))));
    public static final DeferredBlock<Block> OXIDIZED_OXIDIZER_CORE = BLOCKS.register("oxidized_oxidizer_core",
	    () -> new OxidizerCore(WeatheringCopper.WeatherState.OXIDIZED,
		    Properties.ofFullCopy(Blocks.OXIDIZED_COPPER).setId(ResourceKey.create(BLOCKS.getRegistryKey(),
			    ResourceLocation.parse(MoreThanCopper.MODID + ":oxidized_oxidizer_core")))));

    public static final DeferredBlock<Block> EXAMPLE_BLOCK = BLOCKS.registerSimpleBlock("example_block",
	    BlockBehaviour.Properties.of().mapColor(MapColor.STONE));

    public static void register(IEventBus eventBus) {
	BLOCKS.register(eventBus);
    }
}
