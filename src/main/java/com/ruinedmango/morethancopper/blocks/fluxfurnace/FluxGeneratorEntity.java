package com.ruinedmango.morethancopper.blocks.fluxfurnace;

import javax.annotation.Nonnull;

import com.ruinedmango.morethancopper.energy.OxidizedFluxStorage;
import com.ruinedmango.morethancopper.registries.BlockEntityRegistry;
import com.ruinedmango.morethancopper.screen.fluxgenerator.FluxGeneratorMenu;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.common.util.Lazy;
import net.neoforged.neoforge.energy.IEnergyStorage;
import net.neoforged.neoforge.items.IItemHandler;
import net.neoforged.neoforge.items.ItemStackHandler;

public class FluxGeneratorEntity extends BlockEntity implements MenuProvider {
    public static final String ITEMS_TAG = "Inventory";
    public static final String ENERGY_TAG = "Energy";

    public static final int GENERATE = 50;
    public static final int MAXTRANSFER = 1000;
    public static final int CAPACITY = 100000;

    public static int SLOT_COUNT = 1;
    public static int SLOT = 0;

    private final ItemStackHandler items = createItemHandler();
    private final Lazy<IItemHandler> itemHandler = Lazy.of(() -> items);

    private final OxidizedFluxStorage energy = createEnergyStorage();
    private final Lazy<IEnergyStorage> energyHandler = Lazy.of(() -> new OxidizedFluxStorage(100000, 1000));

    public int burnTime;
    public int burnTimeTotal;

    private final ContainerData data = new ContainerData() {
	@Override
	public int get(int index) {
	    switch (index) {
	    case 0:
		return burnTime;
	    case 1:
		return burnTimeTotal;
	    case 2:
		return energy.getEnergyStored();
	    default:
		return 0;
	    }
	}

	@Override
	public void set(int index, int value) {
	    switch (index) {
	    case 0:
		burnTime = value;
		break;
	    case 1:
		burnTimeTotal = value;
		break;
	    case 2:
		energy.receiveEnergy(value, false);
		break;
	    }
	}

	@Override
	public int getCount() {
	    return 3;
	}
    };

    // Provide a getter for the container data.
    public ContainerData getContainerData() {
	return data;
    }

    public FluxGeneratorEntity(BlockPos pos, BlockState state) {
	super(BlockEntityRegistry.FLUX_GENERATOR_ENTITY.get(), pos, state);
    }

    public void tickServer() {
	generateEnergy();
	distributeEnergy();
    }

    // Check if we have a burnable item in the inventory and if so generate energy
    private void generateEnergy() {
	if (energy.getEnergyStored() < energy.getMaxEnergyStored()) {
	    if (burnTime <= 0) {
		ItemStack fuel = items.getStackInSlot(SLOT);
		if (fuel.isEmpty()) {
		    // No fuelz
		    return;
		}
		setBurnTime(fuel.getBurnTime(RecipeType.SMELTING, this.level.fuelValues()));
		if (burnTime <= 0) {
		    // Not a fuel
		    return;
		}
		burnTimeTotal = burnTime;
		items.extractItem(SLOT, 1, false);
	    } else {
		setBurnTime(burnTime - 1);
		energy.receiveEnergy(GENERATE, false);
	    }
	    setChanged();
	}
    }

    private void setBurnTime(int bt) {
	if (bt == burnTime) {
	    return;
	}
	burnTime = bt;
	/*
	 * if (getBlockState().getValue(BlockStateProperties.POWERED) != burnTime > 0) {
	 * level.setBlockAndUpdate(getBlockPos(),
	 * getBlockState().setValue(BlockStateProperties.POWERED, burnTime > 0)); }
	 */
	setChanged();
    }

    private void distributeEnergy() {
	// Check all sides of the block and send energy if that block supports the
	// energy capability
	for (Direction direction : Direction.values()) {
	    if (energy.getEnergyStored() <= 0) {
		return;
	    }
	    IEnergyStorage energy = level.getCapability(Capabilities.EnergyStorage.BLOCK,
		    getBlockPos().relative(direction), null);
	    if (energy != null) {
		if (energy.canReceive()) {
		    int received = energy.receiveEnergy(Math.min(this.energy.getEnergyStored(), MAXTRANSFER), false);
		    this.energy.extractEnergy(received, false);
		    setChanged();
		}
	    }
	}
    }

    public ItemStackHandler getItems() {
	return items;
    }

    public int getStoredPower() {
	return energy.getEnergyStored();
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
	return new FluxGeneratorMenu(containerId, playerInventory, this);
    }

    @Override
    public Component getDisplayName() {
	return Component.translatable("block.morethancopper.flux_generator");
    }

}
