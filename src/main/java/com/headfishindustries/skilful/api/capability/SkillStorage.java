package com.headfishindustries.skilful.api.capability;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.Capability.IStorage;

public class SkillStorage implements IStorage<ISkillData>{

	@Override
	public NBTBase writeNBT(Capability<ISkillData> capability, ISkillData instance, EnumFacing side) {
		
		return instance.serializeNBT();
	}

	@Override
	public void readNBT(Capability<ISkillData> capability, ISkillData instance, EnumFacing side, NBTBase nbt) {
		if (nbt instanceof NBTTagCompound)
			instance.deserializeNBT((NBTTagCompound) nbt);
		
	}

}
