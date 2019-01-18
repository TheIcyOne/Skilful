package com.headfishindustries.skilful.util;

import com.headfishindustries.skilful.api.AbstractSkill;
import com.headfishindustries.skilful.api.PlayerManager;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.text.TextComponentString;

public class MessageUtils {

	public static void tellXP(EntityPlayer player, AbstractSkill s) {
		player.sendMessage(
				new TextComponentString(
						String.format(Minecraft.getMinecraft().getLanguageManager().getCurrentLanguage().getJavaLocale(),"Your current experience in %s is %d", s.getName(), PlayerManager.getXP(player, s))
					)
			);
	}
	
	public static void tellLevel(EntityPlayer player, AbstractSkill s) {
		player.sendMessage(
				new TextComponentString(
						String.format(Minecraft.getMinecraft().getLanguageManager().getCurrentLanguage().getJavaLocale(),"Your %s level is %d", s.getName(), PlayerManager.getLevel(player, s))
					)
			);
	}

}
