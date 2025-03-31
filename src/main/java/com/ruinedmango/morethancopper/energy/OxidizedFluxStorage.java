package com.ruinedmango.morethancopper.energy;

import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.IntTag;
import net.minecraft.nbt.Tag;
import net.minecraft.util.Mth;
import net.neoforged.neoforge.common.util.INBTSerializable;
import net.neoforged.neoforge.energy.IEnergyStorage;

public class OxidizedFluxStorage implements IEnergyStorage, INBTSerializable<Tag> {
    protected int energy;
    protected int capacity;
    protected int maxReceive;
    protected int maxExtract;

    public OxidizedFluxStorage(int capacity) {
	this(capacity, capacity, capacity, 0);
    }

    public OxidizedFluxStorage(int capacity, int maxTransfer) {
	this(capacity, maxTransfer, maxTransfer, 0);
    }

    public OxidizedFluxStorage(int capacity, int maxReceive, int maxExtract) {
	this(capacity, maxReceive, maxExtract, 0);
    }

    public OxidizedFluxStorage(int capacity, int maxReceive, int maxExtract, int energy) {
	this.capacity = capacity;
	this.maxReceive = maxReceive;
	this.maxExtract = maxExtract;
	this.energy = Math.max(0, Math.min(capacity, energy));
    }

    @Override
    public int receiveEnergy(int toReceive, boolean simulate) {
	if (!canReceive() || toReceive <= 0) {
	    return 0;
	}

	int energyReceived = Mth.clamp(this.capacity - this.energy, 0, Math.min(this.maxReceive, toReceive));
	if (!simulate)
	    this.energy += energyReceived;
	return energyReceived;
    }

    @Override
    public int extractEnergy(int toExtract, boolean simulate) {
	if (!canExtract() || toExtract <= 0) {
	    return 0;
	}

	int energyExtracted = Math.min(this.energy, Math.min(this.maxExtract, toExtract));
	if (!simulate)
	    this.energy -= energyExtracted;
	return energyExtracted;
    }

    @Override
    public int getEnergyStored() {
	return this.energy;
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

    @Override
    public Tag serializeNBT(HolderLookup.Provider provider) {
	return IntTag.valueOf(this.getEnergyStored());
    }

    @Override
    public void deserializeNBT(HolderLookup.Provider provider, Tag nbt) {
	if (!(nbt instanceof IntTag(int value)))
	    throw new IllegalArgumentException(
		    "Can not deserialize to an instance that isn't the default implementation");
	this.energy = value;
    }

}
