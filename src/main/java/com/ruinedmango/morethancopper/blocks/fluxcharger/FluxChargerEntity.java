package com.ruinedmango.morethancopper.blocks.fluxcharger;

import javax.annotation.Nonnull;

import com.ruinedmango.morethancopper.energy.OxidizedFluxStorage;
import com.ruinedmango.morethancopper.registries.BlockEntityRegistry;
import com.ruinedmango.morethancopper.registries.BlockRegistry;
import com.ruinedmango.morethancopper.screen.fluxcharger.FluxChargerMenu;

import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.common.util.Lazy;
import net.neoforged.neoforge.energy.IEnergyStorage;
import net.neoforged.neoforge.items.IItemHandler;
import net.neoforged.neoforge.items.ItemStackHandler;

public class FluxChargerEntity extends BlockEntity implements MenuProvider {
    public static final String ITEMS_TAG = "Inventory";
    public static final String ENERGY_TAG = "Energy";

    public static final int MAXTRANSFER = 100;
    public static final int CAPACITY = 10000;

    public static int SLOT_COUNT = 1;
    public static int SLOT = 0;

    private final ItemStackHandler items = createItemHandler();
    private final Lazy<IItemHandler> itemHandler = Lazy.of(() -> items);

    private final OxidizedFluxStorage energy = createEnergyStorage();
    private final Lazy<OxidizedFluxStorage> energyHandler = Lazy.of(() -> energy);

    public int energys;

    private final ContainerData data = new ContainerData() {
	@Override
	public int get(int index) {
	    switch (index) {
	    case 0:
		return energys;
	    default:
		return 10;
	    }
	}

	@Override
	public void set(int index, int value) {
	    switch (index) {
	    case 0:
		energys = value;
		break;
	    }
	}

	@Override
	public int getCount() {
	    return 1;
	}
    };

    public ContainerData getContainerData() {
	return data;
    }

    public FluxChargerEntity(BlockPos pos, BlockState blockState) {
	super(determineEntityType(blockState), pos, blockState);
	// TODO Auto-generated constructor stub
    }

    private static BlockEntityType<? extends FluxChargerEntity> determineEntityType(BlockState state) {

	if (state.getBlock() == BlockRegistry.FLUX_CHARGER.get()) {
	    return BlockEntityRegistry.FLUX_CHARGER_ENTITY.get();
	} else if (state.getBlock() == BlockRegistry.EXPOSED_FLUX_CHARGER.get()) {
	    return BlockEntityRegistry.EXPOSED_FLUX_CHARGER_ENTITY.get();
	} else if (state.getBlock() == BlockRegistry.WEATHERED_FLUX_CHARGER.get()) {
	    return BlockEntityRegistry.WEATHERED_FLUX_CHARGER_ENTITY.get();
	} else if (state.getBlock() == BlockRegistry.OXIDIZED_FLUX_CHARGER.get()) {
	    return BlockEntityRegistry.OXIDIZED_FLUX_CHARGER_ENTITY.get();
	} else {
	    // Fallback if none match. Adjust as needed.
	    return BlockEntityRegistry.FLUX_CHARGER_ENTITY.get();
	}
    }

    public void tickServer() {
	energys = energy.getEnergyStored();
	ItemStack item = items.getStackInSlot(SLOT);
	if (energy.getEnergyStored() > 0) {
	    if (item.getCapability(Capabilities.EnergyStorage.ITEM) != null
		    && item.getCapability(Capabilities.EnergyStorage.ITEM).canReceive()) {
		energy.extractEnergy(
			item.getCapability(Capabilities.EnergyStorage.ITEM).receiveEnergy(MAXTRANSFER, false), false);
	    }
	}
	setChanged();
	/*
	 * generateEnergy(); distributeEnergy();
	 */
    }

    @Override
    protected void saveAdditional(CompoundTag tag, HolderLookup.Provider provider) {
	super.saveAdditional(tag, provider);
	tag.put(ITEMS_TAG, items.serializeNBT(provider));
	tag.put(ENERGY_TAG, energy.serializeNBT(provider));
    }

    @Override
    public void loadAdditional(CompoundTag tag, HolderLookup.Provider provider) {
	super.loadAdditional(tag, provider);
	if (tag.contains(ITEMS_TAG)) {
	    items.deserializeNBT(provider, tag.getCompoundOrEmpty(ITEMS_TAG));
	}
	if (tag.contains(ENERGY_TAG)) {
	    energy.deserializeNBT(provider, tag.get(ENERGY_TAG));
	}
    }

    @Nonnull
    private ItemStackHandler createItemHandler() {
	return new ItemStackHandler(SLOT_COUNT) {
	    @Override
	    protected void onContentsChanged(int slot) {
		setChanged();
	    }
	};
    }

    @Nonnull
    private OxidizedFluxStorage createEnergyStorage() {
	return new OxidizedFluxStorage(CAPACITY, MAXTRANSFER, MAXTRANSFER);
    }

    public IItemHandler getItemHandler() {
	return itemHandler.get();
    }

    public IEnergyStorage getEnergyHandler() {
	return energyHandler.get();
    }

    @Override
    public AbstractContainerMenu createMenu(int containerId, Inventory playerInventory, Player player) {
	return new FluxChargerMenu(containerId, playerInventory, this);
    }

    @Override
    public Component getDisplayName() {
	return Component.translatable("block.morethancopper.flux_generator");
    }
}
