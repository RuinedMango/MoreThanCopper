package com.ruinedmango.morethancopper.blocks;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.ChangeOverTimeBlock;
import net.minecraft.world.level.block.WeatheringCopper;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;

public class OxidizerCore extends Block implements WeatheringCopper {
    public static final MapCodec<OxidizerCore> CODEC = RecordCodecBuilder
	    .mapCodec(instance -> instance
		    .group(WeatheringCopper.WeatherState.CODEC.fieldOf("weathering_state")
			    .forGetter(ChangeOverTimeBlock::getAge), propertiesCodec())
		    .apply(instance, OxidizerCore::new));
    private final WeatheringCopper.WeatherState weatherState;

    @Override
    public MapCodec<OxidizerCore> codec() {
	return CODEC;
    }

    public OxidizerCore(WeatheringCopper.WeatherState weatherState, BlockBehaviour.Properties properties) {
	super(properties);
	this.weatherState = weatherState;
    }

    @Override
    protected void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource randomSource) {
	this.changeOverTime(state, level, pos, randomSource);
    }

    @Override
    protected boolean isRandomlyTicking(BlockState state) {
	return WeatheringCopper.getNext(state.getBlock()).isPresent();
    }

    @Override
    public WeatherState getAge() {
	return weatherState;
    }

}
