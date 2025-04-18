package com.ruinedmango.morethancopper.items;

import java.util.function.Consumer;

import com.ruinedmango.morethancopper.registries.DataComponentRegistry;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.capabilities.Capabilities;

public class FluxBattery extends Item {

    public FluxBattery(Properties properties) {
	super(properties);
    }

    @Override
    public void appendHoverText(ItemStack stack, Item.TooltipContext context, TooltipDisplay tooltipDisplay,
	    Consumer<Component> tooltipComponents, TooltipFlag tooltipFlag) {
	tooltipComponents.accept(Component
		.literal(stack.get(DataComponentRegistry.OF) + "/"
			+ stack.getCapability(Capabilities.EnergyStorage.ITEM).getMaxEnergyStored())
		.withStyle(ChatFormatting.GRAY));
    }

    @Override
    public InteractionResult use(Level level, Player player, InteractionHand hand) {
	return InteractionResult.SUCCESS;
    }
}
