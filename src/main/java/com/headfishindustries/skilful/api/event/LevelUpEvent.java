package com.headfishindustries.skilful.api.event;

import com.headfishindustries.skilful.api.AbstractSkill;
import com.headfishindustries.skilful.api.PlayerManager;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.eventhandler.Event;

public class LevelUpEvent extends Event{
	
	EntityPlayer player;
	AbstractSkill skill;
	Integer level;
	
	public LevelUpEvent(EntityPlayer p, AbstractSkill s, Integer newLevel) {
		this.player = p;
		this.skill = s;
		this.level = newLevel;
	}

	public LevelUpEvent(EntityPlayer p, AbstractSkill s) {
		this(p, s, PlayerManager.getLevel(p, s));
	}
	
	public EntityPlayer getPlayer() {
		return this.player;
	}
	
	public AbstractSkill getSkill() {
		return this.skill;
	}
	
	public Integer getLevel() {
		return this.level;
	}
}
