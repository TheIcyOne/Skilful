package com.headfishindustries.skilful.api.capability;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;

public class SkillDataProvider implements ICapabilitySerializable<NBTTagCompound>{
	
	@CapabilityInject(ISkillData.class)
	public static final Capability<ISkillData> SKILL_CAP = null;
	
	private ISkillData instance = SKILL_CAP.getDefaultInstance();

	@Override
	public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
		return capability == SKILL_CAP;
	}

	@Override
	public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
		return capability == SKILL_CAP ? SKILL_CAP.<T> cast(this.instance) : null;
	}

	@Override
	public NBTTagCompound serializeNBT() {
		return (NBTTagCompound) SKILL_CAP.getStorage().writeNBT(SKILL_CAP, this.instance, null);
	}

	@Override
	public void deserializeNBT(NBTTagCompound nbt) {
		SKILL_CAP.getStorage().readNBT(SKILL_CAP, this.instance, null, nbt);
		
	}


}
