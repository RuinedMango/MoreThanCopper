package com.ruinedmango.morethancopper.screen.fluxgenerator;

import com.ruinedmango.morethancopper.MoreThanCopper;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.player.Inventory;

public class FluxGeneratorScreen extends AbstractContainerScreen<FluxGeneratorMenu> {
    private static final ResourceLocation GUI_TEXTURE = ResourceLocation.fromNamespaceAndPath(MoreThanCopper.MODID,
	    "textures/gui/copperendium/copperendium_gui.png");
    private static final ResourceLocation LIT_PROGRESS_SPRITE = ResourceLocation
	    .withDefaultNamespace("container/furnace/lit_progress");

    public FluxGeneratorScreen(FluxGeneratorMenu menu, Inventory playerInventory, Component title) {
	super(menu, playerInventory, title);
    }

    @Override
    protected void renderBg(GuiGraphics guiGraphics, float partialTick, int mouseX, int mouseY) {
	int x = leftPos;
	int y = topPos;

	guiGraphics.blit(RenderType::guiTextured, GUI_TEXTURE, x, y, 0.0f, 0.0f, imageWidth, imageHeight, imageWidth,
		imageHeight);

	if (this.menu.data.get(0) > 0) {
	    int k = 14;
	    int l = Mth.ceil(this.menu.getLitProgress() * 13.0F) + 1;
	    guiGraphics.blitSprite(RenderType::guiTextured, LIT_PROGRESS_SPRITE, 14, 14, 0, 14 - l, x + 56,
		    y + 36 + 14 - l, 14, l);
	}
	if (this.menu.data.get(2) > 0) {
	    int k = 14;
	    int l = Mth.ceil(this.menu.getEnergyProgress() * 13.0F) + 1;
	    guiGraphics.blitSprite(RenderType::guiTextured, LIT_PROGRESS_SPRITE, 14, 14, 0, 14 - l, x + 40,
		    y + 36 + 14 - l, 14, l);
	}
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
	super.render(guiGraphics, mouseX, mouseY, partialTick);
	this.renderTooltip(guiGraphics, mouseX, mouseY);
    }
}
