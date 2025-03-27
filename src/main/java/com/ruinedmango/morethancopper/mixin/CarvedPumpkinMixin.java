package com.ruinedmango.morethancopper.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.include.com.google.common.base.Predicate;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.monster.EnderMan;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CarvedPumpkinBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.pattern.BlockInWorld;
import net.minecraft.world.level.block.state.pattern.BlockPattern;
import net.minecraft.world.level.block.state.pattern.BlockPatternBuilder;
import net.minecraft.world.level.block.state.predicate.BlockStatePredicate;

@Mixin(CarvedPumpkinBlock.class)
public class CarvedPumpkinMixin {
    private BlockPattern endermanGolemBase;
    private BlockPattern endermanGolemFull;

    private static final Predicate<BlockState> PUMPKINS_PREDICATE = (p_51396_) -> {
	return p_51396_ != null && (p_51396_.is(Blocks.CARVED_PUMPKIN) || p_51396_.is(Blocks.JACK_O_LANTERN));
    };

    @Inject(method = "canSpawnGolem", at = @At("RETURN"), cancellable = true)
    public void canSpawnGolem(LevelReader levelReader, BlockPos pos, CallbackInfoReturnable<Boolean> ci) {
	boolean canSpawn = ci.getReturnValue() || getOrCreateEndermanGolemBase().find(levelReader, pos) != null;
	ci.setReturnValue(canSpawn);
    }

    @Inject(method = "trySpawnGolem", at = @At("TAIL"))
    private void trySpawnGolem(Level level, BlockPos pos, CallbackInfo ci) {
	BlockPattern.BlockPatternMatch patternMatch = this.getOrCreateEndermanGolemFull().find(level, pos);
	if (patternMatch != null) {
	    // Clear it
	    for (int i = 0; i < patternMatch.getWidth(); i++) {
		for (int j = 0; j < patternMatch.getHeight(); j++) {
		    BlockInWorld blockinworld = patternMatch.getBlock(i, j, 0);
		    level.setBlock(blockinworld.getPos(), Blocks.AIR.defaultBlockState(), 2);
		    level.levelEvent(2001, blockinworld.getPos(), Block.getId(blockinworld.getState()));
		}
	    }

	    // Spawn it
	    EnderMan strawGolem = EntityType.ENDERMAN.create(level, EntitySpawnReason.TRIGGERED);
	    strawGolem.snapTo((double) pos.getX() + 0.5, (double) pos.getY() + 0.05, (double) pos.getZ() + 0.5, 0.0F,
		    0.0F);
	    level.addFreshEntity(strawGolem);

	    // Block update
	    for (int l = 0; l < this.getOrCreateEndermanGolemFull().getHeight(); ++l) {
		BlockInWorld blockinworld3 = patternMatch.getBlock(0, l, 0);
		level.updateNeighborsAt(blockinworld3.getPos(), Blocks.AIR);
	    }
	}
    }

    private BlockPattern getOrCreateEndermanGolemBase() {
	if (endermanGolemBase == null) {
	    endermanGolemBase = BlockPatternBuilder.start().aisle("~ ~", "###", "~#~")
		    .where('~', block -> block.getState().isAir())
		    .where('#', BlockInWorld.hasState(BlockStatePredicate.forBlock(Blocks.OBSIDIAN))).build();
	}
	return endermanGolemBase;
    }

    private BlockPattern getOrCreateEndermanGolemFull() {
	if (endermanGolemFull == null) {
	    endermanGolemFull = BlockPatternBuilder.start().aisle("~^~", "###", "~#~")
		    .where('^', BlockInWorld.hasState(PUMPKINS_PREDICATE)).where('~', block -> block.getState().isAir())
		    .where('#', BlockInWorld.hasState(BlockStatePredicate.forBlock(Blocks.OBSIDIAN))).build();
	}
	return endermanGolemFull;
    }
}
