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
	    .mapCodec(p_368452_ -> p_368452_
		    .group(WeatheringCopper.WeatherState.CODEC.fieldOf("weathering_state")
			    .forGetter(ChangeOverTimeBlock::getAge), propertiesCodec())
		    .apply(p_368452_, OxidizerCore::new));
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
    protected void randomTick(BlockState p_222665_, ServerLevel p_222666_, BlockPos p_222667_, RandomSource p_222668_) {
	this.changeOverTime(p_222665_, p_222666_, p_222667_, p_222668_);
    }

    @Override
    protected boolean isRandomlyTicking(BlockState p_154935_) {
	return WeatheringCopper.getNext(p_154935_.getBlock()).isPresent();
    }

    @Override
    public WeatherState getAge() {
	return weatherState;
    }

}
