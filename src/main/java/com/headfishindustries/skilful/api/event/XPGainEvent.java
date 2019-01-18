package com.headfishindustries.skilful.api.event;

import com.headfishindustries.skilful.api.AbstractSkill;
import com.headfishindustries.skilful.api.SkilfulAPI;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.eventhandler.Event;

public class XPGainEvent extends Event{
	
	private EntityPlayer player;
	private AbstractSkill skill;
	private double amount;
	
	
	public XPGainEvent (EntityPlayer p, AbstractSkill affectedSkill, double xp) {
		this.player = p;
		this.skill = affectedSkill;
		this.amount = xp;
	}

	public XPGainEvent(EntityPlayer p, ResourceLocation skillName, double xp) {
		this(p, SkilfulAPI.SKILL_REGISTRY.getValue(skillName), xp);
	}

	public EntityPlayer getPlayer() {
		return player;
	}

	public AbstractSkill getSkill() {
		return skill;
	}

	public double getXPAmount() {
		return amount;
	}
	
	public void setXPAmount(int xp) {
		this.amount = xp;
	}

}
