package com.headfishindustries.skilful.api;

import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.registries.ForgeRegistry;

public class SkilfulAPI {
	
	public static final ForgeRegistry<AbstractSkill> SKILL_REGISTRY = (ForgeRegistry<AbstractSkill>) GameRegistry.findRegistry(AbstractSkill.class);
	public static final ForgeRegistry<AbstractBuff> BUFF_REGISTRY = (ForgeRegistry<AbstractBuff>) GameRegistry.findRegistry(AbstractBuff.class);
	


}
