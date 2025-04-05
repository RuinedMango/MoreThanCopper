package com.ruinedmango.morethancopper.screen.fluxgenerator;

import net.minecraft.network.chat.Component;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.block.entity.BlockEntity;

public class FluxGeneratorMenuProvider implements MenuProvider {
    public BlockEntity entity;

    public FluxGeneratorMenuProvider(BlockEntity entity) {
	this.entity = entity;
    }

    @Override
    public AbstractContainerMenu createMenu(int containerId, Inventory playerInventory, Player player) {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public Component getDisplayName() {
	// TODO Auto-generated method stub
	return null;
    }

}
