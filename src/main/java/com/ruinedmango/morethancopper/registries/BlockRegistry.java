package com.ruinedmango.morethancopper.registries;

import com.ruinedmango.morethancopper.MoreThanCopper;
import com.ruinedmango.morethancopper.blocks.OxidizedCore;

import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

public class BlockRegistry {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(MoreThanCopper.MODID);

    public static final DeferredBlock<Block> OXIDIZED_CORE = BLOCKS
	    .register("oxidized_core",
		    () -> new OxidizedCore(BlockBehaviour.Properties.of().mapColor(MapColor.CLAY)
			    .setId(ResourceKey.create(BLOCKS.getRegistryKey(),
				    ResourceLocation.parse(MoreThanCopper.MODID + ":oxidized_core")))));

    public static final DeferredBlock<Block> EXAMPLE_BLOCK = BLOCKS.registerSimpleBlock("example_block",
	    BlockBehaviour.Properties.of().mapColor(MapColor.STONE));

    public static void register(IEventBus eventBus) {
	BLOCKS.register(eventBus);
    }
}
