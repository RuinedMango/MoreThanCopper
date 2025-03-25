package com.ruinedmango.morethancopper.screen.copperendium;

import com.mojang.blaze3d.systems.RenderSystem;
import com.ruinedmango.morethancopper.MoreThanCopper;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.CoreShaders;
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
	RenderSystem.setShader(CoreShaders.POSITION_TEX);
	RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
	RenderSystem.setShaderTexture(0, GUI_TEXTURE);

	int x = (width - imageWidth) / 2;
	int y = (height - imageHeight) / 2;

	guiGraphics.blit(RenderType::guiTextured, GUI_TEXTURE, x, y, 0.0f, 0.0f, (int) (imageWidth * 1.6),
		(int) (imageHeight * 1.6), (int) (imageWidth * 1.6), (int) (imageHeight * 1.6));
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
	super.render(guiGraphics, mouseX, mouseY, partialTick);
	this.renderTooltip(guiGraphics, mouseX, mouseY);
    }
}
