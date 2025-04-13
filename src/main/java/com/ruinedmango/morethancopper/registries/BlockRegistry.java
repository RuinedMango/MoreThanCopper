package com.ruinedmango.morethancopper.registries;

import com.ruinedmango.morethancopper.MoreThanCopper;
import com.ruinedmango.morethancopper.blocks.OxidizerCore;
import com.ruinedmango.morethancopper.blocks.fluxcharger.FluxCharger;
import com.ruinedmango.morethancopper.blocks.fluxgenerator.FluxGenerator;

import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.WeatheringCopper;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

public class BlockRegistry {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(MoreThanCopper.MODID);

    public static final DeferredBlock<Block> EXAMPLE_BLOCK = BLOCKS.registerSimpleBlock("example_block");
    public static final DeferredBlock<Block> OXIDIZER_CORE = BLOCKS.register("oxidizer_core",
	    () -> new OxidizerCore(WeatheringCopper.WeatherState.UNAFFECTED,
		    Properties.ofFullCopy(Blocks.COPPER_BLOCK).randomTicks()
			    .setId(ResourceKey.create(BLOCKS.getRegistryKey(),
				    ResourceLocation.parse(MoreThanCopper.MODID + ":oxidizer_core")))));
    public static final DeferredBlock<Block> EXPOSED_OXIDIZER_CORE = BLOCKS.register("exposed_oxidizer_core",
	    () -> new OxidizerCore(WeatheringCopper.WeatherState.EXPOSED,
		    Properties.ofFullCopy(Blocks.EXPOSED_COPPER).randomTicks()
			    .setId(ResourceKey.create(BLOCKS.getRegistryKey(),
				    ResourceLocation.parse(MoreThanCopper.MODID + ":exposed_oxidizer_core")))));
    public static final DeferredBlock<Block> WEATHERED_OXIDIZER_CORE = BLOCKS
	    .register("weathered_oxidizer_core",
		    () -> new OxidizerCore(WeatheringCopper.WeatherState.WEATHERED,
			    Properties.ofFullCopy(Blocks.WEATHERED_COPPER).randomTicks().setId(ResourceKey.create(
				    BLOCKS.getRegistryKey(),
				    ResourceLocation.parse(MoreThanCopper.MODID + ":weathered_oxidizer_core")))));
    public static final DeferredBlock<Block> OXIDIZED_OXIDIZER_CORE = BLOCKS
	    .register("oxidized_oxidizer_core",
		    () -> new OxidizerCore(WeatheringCopper.WeatherState.OXIDIZED,
			    Properties.ofFullCopy(Blocks.OXIDIZED_COPPER).randomTicks().setId(ResourceKey.create(
				    BLOCKS.getRegistryKey(),
				    ResourceLocation.parse(MoreThanCopper.MODID + ":oxidized_oxidizer_core")))));

    public static final DeferredBlock<Block> FLUX_GENERATOR = BLOCKS.register("flux_generator",
	    () -> new FluxGenerator(WeatheringCopper.WeatherState.UNAFFECTED,
		    Properties.ofFullCopy(Blocks.COBBLESTONE).randomTicks()
			    .setId(ResourceKey.create(BLOCKS.getRegistryKey(),
				    ResourceLocation.parse(MoreThanCopper.MODID + ":flux_generator")))));
    public static final DeferredBlock<Block> EXPOSED_FLUX_GENERATOR = BLOCKS
	    .register("exposed_flux_generator",
		    () -> new FluxGenerator(WeatheringCopper.WeatherState.EXPOSED,
			    Properties.ofFullCopy(Blocks.COBBLESTONE).randomTicks().setId(ResourceKey.create(
				    BLOCKS.getRegistryKey(),
				    ResourceLocation.parse(MoreThanCopper.MODID + ":exposed_flux_generator")))));
    public static final DeferredBlock<Block> WEATHERED_FLUX_GENERATOR = BLOCKS
	    .register("weathered_flux_generator",
		    () -> new FluxGenerator(WeatheringCopper.WeatherState.WEATHERED,
			    Properties.ofFullCopy(Blocks.COBBLESTONE).randomTicks().setId(ResourceKey.create(
				    BLOCKS.getRegistryKey(),
				    ResourceLocation.parse(MoreThanCopper.MODID + ":weathered_flux_generator")))));
    public static final DeferredBlock<Block> OXIDIZED_FLUX_GENERATOR = BLOCKS
	    .register("oxidized_flux_generator",
		    () -> new FluxGenerator(WeatheringCopper.WeatherState.OXIDIZED,
			    Properties.ofFullCopy(Blocks.COBBLESTONE).randomTicks().setId(ResourceKey.create(
				    BLOCKS.getRegistryKey(),
				    ResourceLocation.parse(MoreThanCopper.MODID + ":oxidized_flux_generator")))));

    public static final DeferredBlock<Block> FLUX_CHARGER = BLOCKS
	    .register("flux_charger",
		    () -> new FluxCharger(WeatheringCopper.WeatherState.UNAFFECTED,
			    Properties.ofFullCopy(Blocks.COBBLESTONE).randomTicks()
				    .setId(ResourceKey.create(BLOCKS.getRegistryKey(),
					    ResourceLocation.parse(MoreThanCopper.MODID + ":flux_charger")))));
    public static final DeferredBlock<Block> EXPOSED_FLUX_CHARGER = BLOCKS.register("exposed_flux_charger",
	    () -> new FluxCharger(WeatheringCopper.WeatherState.EXPOSED,
		    Properties.ofFullCopy(Blocks.COBBLESTONE).randomTicks()
			    .setId(ResourceKey.create(BLOCKS.getRegistryKey(),
				    ResourceLocation.parse(MoreThanCopper.MODID + ":exposed_flux_charger")))));
    public static final DeferredBlock<Block> WEATHERED_FLUX_CHARGER = BLOCKS
	    .register("weathered_flux_charger",
		    () -> new FluxCharger(WeatheringCopper.WeatherState.WEATHERED,
			    Properties.ofFullCopy(Blocks.COBBLESTONE).randomTicks().setId(ResourceKey.create(
				    BLOCKS.getRegistryKey(),
				    ResourceLocation.parse(MoreThanCopper.MODID + ":weathered_flux_charger")))));
    public static final DeferredBlock<Block> OXIDIZED_FLUX_CHARGER = BLOCKS.register("oxidized_flux_charger",
	    () -> new FluxCharger(WeatheringCopper.WeatherState.OXIDIZED,
		    Properties.ofFullCopy(Blocks.COBBLESTONE).randomTicks()
			    .setId(ResourceKey.create(BLOCKS.getRegistryKey(),
				    ResourceLocation.parse(MoreThanCopper.MODID + ":oxidized_flux_charger")))));

    public static void register(IEventBus eventBus) {
	BLOCKS.register(eventBus);
    }
}
