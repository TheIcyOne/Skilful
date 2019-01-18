package com.headfishindustries.skilful.api;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.IForgeRegistryEntry;

public abstract class AbstractBuff extends IForgeRegistryEntry.Impl<AbstractBuff>{

	public abstract String getName();
	
	public abstract AbstractSkill getSkill();
	
	/** Used for passive buffs from skills given for free i.e. mining speed boost from mining or attack damage buff from attack return true, bonus drops or random crits return false**/
	public abstract boolean isPassive();

}
