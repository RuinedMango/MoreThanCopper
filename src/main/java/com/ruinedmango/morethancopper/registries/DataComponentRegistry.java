package com.ruinedmango.morethancopper.registries;

import java.util.function.Supplier;

import com.ruinedmango.morethancopper.MoreThanCopper;

import malte0811.dualcodecs.DualCodecs;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.Registries;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

public class DataComponentRegistry {
    public static final DeferredRegister.DataComponents DATA_COMPONENT_TYPES = DeferredRegister
	    .createDataComponents(Registries.DATA_COMPONENT_TYPE, MoreThanCopper.MODID);

    public static final Supplier<DataComponentType<Integer>> OF = DATA_COMPONENT_TYPES.registerComponentType("energy",
	    builder -> builder.persistent(DualCodecs.INT.codec()).networkSynchronized(DualCodecs.INT.streamCodec()));

    public static void register(IEventBus event) {
	DATA_COMPONENT_TYPES.register(event);
    }
}
