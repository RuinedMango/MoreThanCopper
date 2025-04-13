package com.ruinedmango.morethancopper.energy;

import net.minecraft.core.component.DataComponentType;
import net.minecraft.util.Mth;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.common.MutableDataComponentHolder;
import net.neoforged.neoforge.energy.ComponentEnergyStorage;
import net.neoforged.neoforge.energy.IEnergyStorage;

public class ComponentOxidizedFluxStorage implements IEnergyStorage {
    protected final MutableDataComponentHolder parent;
    protected final DataComponentType<Integer> energyComponent;
    protected final int capacity;
    protected final int maxReceive;
    protected final int maxExtract;

    /**
     * Creates a new ComponentEnergyStorage with a data component as the backing
     * store for the energy value.
     * 
     * @param parent          The parent component holder, such as an
     *                        {@link ItemStack}
     * @param energyComponent The data component referencing the stored energy of
     *                        the item stack
     * @param capacity        The max capacity of the energy being stored
     * @param maxReceive      The max per-transfer power input rate
     * @param maxExtract      The max per-transfer power output rate
     */
    public ComponentOxidizedFluxStorage(MutableDataComponentHolder parent, DataComponentType<Integer> energyComponent,
	    int capacity, int maxReceive, int maxExtract) {
	this.parent = parent;
	this.energyComponent = energyComponent;
	this.capacity = capacity;
	this.maxReceive = maxReceive;
	this.maxExtract = maxExtract;
    }

    /**
     * Creates a new ItemEnergyStorage with a unified receive / extract rate.
     * 
     * @see ComponentEnergyStorage#ItemEnergyStorage(ItemStack, DataComponentType,
     *      int, int, int)
     */
    public ComponentOxidizedFluxStorage(MutableDataComponentHolder parent, DataComponentType<Integer> energyComponent,
	    int capacity, int maxTransfer) {
	this(parent, energyComponent, capacity, maxTransfer, maxTransfer);
    }

    /**
     * Creates a new ItemEnergyStorage with a transfer rate equivalent to the
     * capacity.
     * 
     * @see ComponentEnergyStorage#ItemEnergyStorage(ItemStack, DataComponentType,
     *      int, int, int)
     */
    public ComponentOxidizedFluxStorage(MutableDataComponentHolder parent, DataComponentType<Integer> energyComponent,
	    int capacity) {
	this(parent, energyComponent, capacity, capacity);
    }

    @Override
    public int receiveEnergy(int toReceive, boolean simulate) {
	if (!canReceive() || toReceive <= 0) {
	    return 0;
	}

	int energy = this.getEnergyStored();
	int energyReceived = Mth.clamp(this.capacity - energy, 0, Math.min(this.maxReceive, toReceive));
	if (!simulate && energyReceived > 0) {
	    this.setEnergy(energy + energyReceived);
	}
	return energyReceived;
    }

    @Override
    public int extractEnergy(int toExtract, boolean simulate) {
	if (!canExtract() || toExtract <= 0) {
	    return 0;
	}

	int energy = this.getEnergyStored();
	int energyExtracted = Math.min(energy, Math.min(this.maxExtract, toExtract));
	if (!simulate && energyExtracted > 0) {
	    this.setEnergy(energy - energyExtracted);
	}
	return energyExtracted;
    }

    @Override
    public int getEnergyStored() {
	int rawEnergy = this.parent.getOrDefault(this.energyComponent, 0);
	return Mth.clamp(rawEnergy, 0, this.capacity);
    }

    @Override
    public int getMaxEnergyStored() {
	return this.capacity;
    }

    @Override
    public boolean canExtract() {
	return this.maxExtract > 0;
    }

    @Override
    public boolean canReceive() {
	return this.maxReceive > 0;
    }

    /**
     * Writes a new energy value to the data component. Clamps to [0, capacity]
     * 
     * @param energy The new energy value
     */
    protected void setEnergy(int energy) {
	int realEnergy = Mth.clamp(energy, 0, this.capacity);
	this.parent.set(this.energyComponent, realEnergy);
    }
}
