package com.ruinedmango.morethancopper.energy;

import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.nbt.Tag;
import net.neoforged.neoforge.common.util.INBTSerializable;
import net.neoforged.neoforge.energy.IEnergyStorage;

public class OxidizedFluxStorage implements IEnergyStorage, INBTSerializable<Tag> {
    protected int energy;
    protected int capacity;
    protected int maxRecieve;
    protected int maxExtract;

    @Override
    public int receiveEnergy(int toReceive, boolean simulate) {
	// TODO Auto-generated method stub
	return 0;
    }

    @Override
    public int extractEnergy(int toExtract, boolean simulate) {
	// TODO Auto-generated method stub
	return 0;
    }

    @Override
    public int getEnergyStored() {
	// TODO Auto-generated method stub
	return 0;
    }

    @Override
    public int getMaxEnergyStored() {
	// TODO Auto-generated method stub
	return 0;
    }

    @Override
    public boolean canExtract() {
	// TODO Auto-generated method stub
	return false;
    }

    @Override
    public boolean canReceive() {
	// TODO Auto-generated method stub
	return false;
    }

    @Override
    public Tag serializeNBT(Provider provider) {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public void deserializeNBT(Provider provider, Tag nbt) {
	// TODO Auto-generated method stub

    }

}
