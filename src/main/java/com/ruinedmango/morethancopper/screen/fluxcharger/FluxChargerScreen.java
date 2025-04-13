package com.ruinedmango.morethancopper.screen.fluxcharger;

import com.ruinedmango.morethancopper.MoreThanCopper;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.player.Inventory;

public class FluxChargerScreen extends AbstractContainerScreen<FluxChargerMenu> {
    private static final ResourceLocation GUI_TEXTURE = ResourceLocation.fromNamespaceAndPath(MoreThanCopper.MODID,
	    "textures/gui/copperendium/copperendium_gui.png");

    public FluxChargerScreen(FluxChargerMenu menu, Inventory playerInventory, Component title) {
	super(menu, playerInventory, title);
    }

    @Override
    protected void renderBg(GuiGraphics guiGraphics, float partialTick, int mouseX, int mouseY) {
	int x = leftPos;
	int y = topPos;

	guiGraphics.blit(RenderType::guiTextured, GUI_TEXTURE, x, y, 0.0f, 0.0f, imageWidth, imageHeight, imageWidth,
		imageHeight);

	if (this.menu.data.get(0) > 0) {
	    int l = Mth.ceil(this.menu.getEnergyProgress() * 69.0F) + 1;
	    int w = 15;
	    guiGraphics.fillGradient(x + 155, y + 76 - l, x + 155 + w, y + 76, 0xff00ffff, 0xff0000ff);
	}

    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
	super.render(guiGraphics, mouseX, mouseY, partialTick);
	this.renderTooltip(guiGraphics, mouseX, mouseY);
	if (mouseX >= leftPos + 155 && mouseX < leftPos + 155 + 15 && mouseY <= topPos + 76
		&& mouseY > topPos + 76 - 70) {
	    guiGraphics.renderTooltip(this.font, Component.literal(menu.blockEntity.energys + " OF"), mouseX, mouseY);

	}
    }
}
