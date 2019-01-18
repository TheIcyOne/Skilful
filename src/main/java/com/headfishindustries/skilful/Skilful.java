package com.headfishindustries.skilful;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.headfishindustries.skilful.api.PlayerSkillData;
import com.headfishindustries.skilful.api.SkilfulAPI;
import com.headfishindustries.skilful.api.capability.ISkillData;
import com.headfishindustries.skilful.api.capability.SkillStorage;
import com.headfishindustries.skilful.handlers.XPHandler;
import com.headfishindustries.skilful.net.MessageSkillXP;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

@Mod(modid=Skilful.MODID, name=Skilful.NAME, version=Skilful.VERSION, acceptedMinecraftVersions="[1.12, 1.13]")
public class Skilful {

	public static final String MODID = "skilful";
	public static final String NAME = "Skilful";
	public static final String VERSION = "%gradle.version%";
	
	public static final Logger LOGGER = LogManager.getLogger(MODID);	
	public static final SimpleNetworkWrapper WRAPPER =  NetworkRegistry.INSTANCE.newSimpleChannel(MODID);
	
	@EventHandler
	public static void preInit(FMLPreInitializationEvent e) {
		CapabilityManager.INSTANCE.register(ISkillData.class, new SkillStorage(), PlayerSkillData::new);
	}
	
	@EventHandler
	public static void init(FMLInitializationEvent e) {
		SkilfulAPI.SKILL_REGISTRY.register(SkilfulSkills.MINING);
		
		int net = 0;
		
		WRAPPER.registerMessage(MessageSkillXP.SkillMessageHandler.class, MessageSkillXP.class, net++, Side.CLIENT);
		
		XPHandler.makeRocket();
		
	}

}
