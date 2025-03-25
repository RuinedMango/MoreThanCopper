package com.ruinedmango.morethancopper.screen.copperendium;

import net.minecraft.network.chat.Component;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;

public class CopperendiumMenuProvider implements MenuProvider {

    @Override
    public AbstractContainerMenu createMenu(int containerId, Inventory playerInventory, Player player) {
	return new CopperendiumMenu(containerId, playerInventory);
    }

    @Override
    public Component getDisplayName() {
	return Component.literal("Copperendium");
    }

}
