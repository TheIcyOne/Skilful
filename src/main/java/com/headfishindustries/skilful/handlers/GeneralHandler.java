package com.headfishindustries.skilful.handlers;

import com.headfishindustries.skilful.Skilful;
import com.headfishindustries.skilful.api.AbstractBuff;
import com.headfishindustries.skilful.api.AbstractSkill;
import com.headfishindustries.skilful.api.capability.SkillDataProvider;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.registries.IForgeRegistryEntry;
import net.minecraftforge.registries.RegistryBuilder;

@Mod.EventBusSubscriber(modid = Skilful.MODID)
public class GeneralHandler {
	
	private static final ResourceLocation[] PLAYER_CAPS = {new ResourceLocation(Skilful.MODID, "skills")};
	
	@SubscribeEvent
	public static void registryCreation(RegistryEvent.NewRegistry e) {
		new RegistryBuilder<AbstractSkill>().setName(new ResourceLocation(Skilful.MODID, "skills")).setType(AbstractSkill.class).create();
		new RegistryBuilder<AbstractBuff>().setName(new ResourceLocation(Skilful.MODID, "buffs")).setType(AbstractBuff.class).create();
	}
	
	@SubscribeEvent
	public static void attachCapability(AttachCapabilitiesEvent<Entity> event) {
		if (!(event.getObject() instanceof EntityPlayer)) return;
//		System.out.println(event.getObject().toString());
		for (ResourceLocation res : PLAYER_CAPS) {
			event.addCapability(res, new SkillDataProvider());
		}
	}
}
