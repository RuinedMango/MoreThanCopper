package com.ruinedmango.morethancopper.blocks.fluxfurnace;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.WeatheringCopper;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

public class FluxGenerator extends Block implements WeatheringCopper, EntityBlock {

    public FluxGenerator(Properties properties) {
	super(properties);
    }

    @Override
    public WeatherState getAge() {
	return null;
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
	return this.defaultBlockState();
	// .setValue(BlockStateProperties.FACING,
	// context.getNearestLookingDirection().getOpposite())
	// .setValue(BlockStateProperties.POWERED, false);
    }

}
