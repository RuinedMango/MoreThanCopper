package com.ruinedmango.morethancopper.blocks.fluxgenerator;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.ChangeOverTimeBlock;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.WeatheringCopper;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.phys.BlockHitResult;

public class FluxGenerator extends Block implements WeatheringCopper, EntityBlock {
    public static final MapCodec<FluxGenerator> CODEC = RecordCodecBuilder
	    .mapCodec(p_368452_ -> p_368452_
		    .group(WeatheringCopper.WeatherState.CODEC.fieldOf("weathering_state")
			    .forGetter(ChangeOverTimeBlock::getAge), propertiesCodec())
		    .apply(p_368452_, FluxGenerator::new));
    private final WeatheringCopper.WeatherState weatherState;

    @Override
    public MapCodec<FluxGenerator> codec() {
	return CODEC;
    }

    public FluxGenerator(WeatheringCopper.WeatherState weatherState, BlockBehaviour.Properties properties) {
	super(properties);
	this.weatherState = weatherState;
    }

    @Override
    public WeatherState getAge() {
	return this.weatherState;
    }

    @Override
    protected void randomTick(BlockState p_222665_, ServerLevel p_222666_, BlockPos p_222667_, RandomSource p_222668_) {
	this.changeOverTime(p_222665_, p_222666_, p_222667_, p_222668_);
    }

    @Override
    public void changeOverTime(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
	float f = 0.05688889F;
	if (random.nextFloat() < f) {
	    CompoundTag fullData = new CompoundTag();
	    BlockEntity be = level.getBlockEntity(pos);
	    if (be instanceof FluxGeneratorEntity fe) {
		fe.saveAdditional(fullData, level.registryAccess());
	    }
	    this.getNextState(state, level, pos, random)
		    .ifPresent(p_153039_ -> level.setBlockAndUpdate(pos, p_153039_));
	    be = level.getBlockEntity(pos);
	    if (be instanceof FluxGeneratorEntity fe) {
		fe.loadAdditional(fullData, level.registryAccess());
	    }
	}
    }

    @Override
    protected boolean isRandomlyTicking(BlockState p_154935_) {
	return true;
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
	return new FluxGeneratorEntity(pos, state);
    }

    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state,
	    BlockEntityType<T> type) {
	if (level.isClientSide) {
	    return null;
	} else {
	    return (lvl, pos, st, be) -> {
		if (be instanceof FluxGeneratorEntity generator) {
		    generator.tickServer();
		}
	    };
	}
    }

    @Override
    public InteractionResult useItemOn(ItemStack pStack, BlockState state, Level level, BlockPos pos, Player player,
	    InteractionHand pHand, BlockHitResult trace) {
	if (!level.isClientSide) {
	    BlockEntity be = level.getBlockEntity(pos);
	    if (be instanceof FluxGeneratorEntity fluxGeneratorEntity) {
		((ServerPlayer) player).openMenu(new SimpleMenuProvider(fluxGeneratorEntity,
			Component.translatable("block.morethancopper.flux_generator")), pos);
	    } else {
		throw new IllegalStateException("Our named container provider is missing!");
	    }
	}
	return InteractionResult.SUCCESS;
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
	return this.defaultBlockState()
		.setValue(BlockStateProperties.FACING, context.getHorizontalDirection().getOpposite())
		.setValue(BlockStateProperties.LIT, false);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
	super.createBlockStateDefinition(builder);
	builder.add(BlockStateProperties.LIT, BlockStateProperties.FACING);
    }

}
