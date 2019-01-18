package com.headfishindustries.skilful.api;

import com.headfishindustries.skilful.api.capability.SkillDataProvider;
import com.headfishindustries.skilful.api.event.LevelUpEvent;
import com.headfishindustries.skilful.api.event.XPGainEvent;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.common.MinecraftForge;

public class PlayerManager {
	
	public static Integer getLevel(EntityPlayer p, AbstractSkill skill) {
		return p.getCapability(SkillDataProvider.SKILL_CAP, null).getLevel(skill);
	}
	
	/** This method should be used for all xp additions or subtractions, to allow for modifiers such as xp boosting.**/
	public static void addXP(EntityPlayer p, AbstractSkill s, double xp) {
		XPGainEvent xpGain = new XPGainEvent(p, s, xp);
		MinecraftForge.EVENT_BUS.post(xpGain);
		if (!xpGain.isCanceled()) {
			if (p.getCapability(SkillDataProvider.SKILL_CAP, null).addXP(s, xp)) {
				LevelUpEvent lvlUp = new LevelUpEvent(p, s, getLevel(p, s));
				MinecraftForge.EVENT_BUS.post(lvlUp);
				if(lvlUp.isCanceled()) {
					p.getCapability(SkillDataProvider.SKILL_CAP, null).reduceLevel(s);
				}
			}
		}
	}
	
	/** Avoid using this, addXP is preferable as it supports adding events.**/
	public static void setXP(EntityPlayer p, AbstractSkill s, double xp) {
		p.getCapability(SkillDataProvider.SKILL_CAP, null).setXP(s, xp);
	}
	
	public static double getXP(EntityPlayer p, AbstractSkill s){
		return p.getCapability(SkillDataProvider.SKILL_CAP, null).getXP(s);
	}

}
