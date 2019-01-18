package com.headfishindustries.skilful.api;

import net.minecraftforge.registries.IForgeRegistryEntry;

public abstract class AbstractSkill extends IForgeRegistryEntry.Impl<AbstractSkill>{
	
	public abstract String getName();
	public abstract boolean hasBuff();
	
	
}
