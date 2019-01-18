package com.headfishindustries.skilful.handlers;

import com.headfishindustries.skilful.Skilful;
import com.headfishindustries.skilful.SkilfulSkills;
import com.headfishindustries.skilful.util.MessageUtils;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerLoggedInEvent;

@Mod.EventBusSubscriber(modid=Skilful.MODID)
public class ConnectionHandler {
	
	@SubscribeEvent
	public static void onPlayerLogin(PlayerLoggedInEvent e) {
		MessageUtils.tellLevel(e.player, SkilfulSkills.MINING);
	}
}
