package com.ruinedmango.morethancopper.screen.copperendium;

import com.ruinedmango.morethancopper.MoreThanCopper;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class CopperendiumScreen extends AbstractContainerScreen<CopperendiumMenu> {
    private static final ResourceLocation GUI_TEXTURE = ResourceLocation.fromNamespaceAndPath(MoreThanCopper.MODID,
	    "textures/gui/copperendium/copperendium_gui.png");

    public CopperendiumScreen(CopperendiumMenu menu, Inventory playerInventory, Component title) {
	super(menu, playerInventory, title);
    }

    @Override
    protected void renderBg(GuiGraphics guiGraphics, float pPartialTick, int pMouseX, int pMouseY) {
	int x = leftPos;
	int y = topPos;

	guiGraphics.blit(RenderType::guiTextured, GUI_TEXTURE, x, y, 0.0f, 0.0f, imageWidth, imageHeight, imageWidth,
		imageHeight);
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
	super.render(guiGraphics, mouseX, mouseY, partialTick);
	this.renderTooltip(guiGraphics, mouseX, mouseY);
    }
}
