package com.ruinedmango.morethancopper.items;

import java.util.List;

import com.ruinedmango.morethancopper.screen.copperendium.CopperendiumMenuProvider;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

public class Copperendium extends Item {

    public Copperendium(Properties properties) {
	super(properties);
    }

    @Override
    public void appendHoverText(ItemStack stack, Item.TooltipContext context, List<Component> tooltipComponents,
	    TooltipFlag tooltipFlag) {
	tooltipComponents.add(Component.translatable("copperendium.description").withStyle(ChatFormatting.GRAY));
    }

    @Override
    public InteractionResult use(Level level, Player player, InteractionHand hand) {
	ItemStack itemstack = player.getItemInHand(hand);
	player.openMenu(new SimpleMenuProvider(new CopperendiumMenuProvider(), Component.literal("Copperendium")));
	player.awardStat(Stats.ITEM_USED.get(this));
	return InteractionResult.SUCCESS;
    }
}
