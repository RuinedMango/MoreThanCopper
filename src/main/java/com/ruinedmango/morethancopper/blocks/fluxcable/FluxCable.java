package com.ruinedmango.morethancopper.blocks.fluxcable;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class FluxCable extends Block implements SimpleWaterloggedBlock, EntityBlock {

    public FluxCable(Properties p_49795_) {
	super(p_49795_);
	// TODO Auto-generated constructor stub
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
	// TODO Auto-generated method stub
	return null;
    }

}
