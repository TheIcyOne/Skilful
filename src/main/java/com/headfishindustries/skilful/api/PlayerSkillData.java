package com.headfishindustries.skilful.api;

import java.util.HashMap;

import com.headfishindustries.skilful.Skilful;
import com.headfishindustries.skilful.api.capability.ISkillData;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.util.INBTSerializable;

public class PlayerSkillData implements INBTSerializable<NBTTagCompound>, ISkillData{
	
	static final Integer MAX_LEVEL = 99;
	static final Integer COEFF_A = 50;
	static final Integer COEFF_B = 10;
	static final Integer COEFF_C = 2;
	
	static final Double[] XP_TO_LEVEL = populateLevels();
	static boolean levels_populated = false;

	static final String BASE_TAG = Skilful.MODID;
	static final String SKILLS_TAG = "skills";
	static final String BUFF_TAG = "buffs";
	
	HashMap<AbstractSkill, Double> xpMap = new HashMap<AbstractSkill, Double>();
	HashMap<AbstractBuff, Boolean> buffMap = new HashMap<AbstractBuff, Boolean>();

	public PlayerSkillData() {
	}
	
	public Double getXP(AbstractSkill s) {
		return xpMap.containsKey(s) ? xpMap.get(s) : 0;
	}
	
	public Integer getLevel(AbstractSkill s) {
		return getLevelForXP(getXP(s));
	}
	
	public boolean addXP(AbstractSkill s, double xp) {
		int level = getLevel(s);
		xpMap.put(s, getXP(s) + xp);
		if (getLevel(s) > level) {
			return true;
		}
		return false;
	}

	private static final Double[] populateLevels() {
		Double[] vals = new Double[MAX_LEVEL];
		for (int i = 0; i < MAX_LEVEL; i++) {
			vals[i] = Math.floor(COEFF_A * i + Math.pow(i, 2) * COEFF_B + Math.pow(i, 3) * COEFF_C);
		}
		return vals;
	}
	
	static final Integer getLevelForXP(Double xp) {
		for (int i = 0; i < MAX_LEVEL; i++) {
			if (xp <= XP_TO_LEVEL[i]) return i;
		}
		//Well this is awkward.
		return 0;
	}

	@Override
	public NBTTagCompound serializeNBT() {
		
		NBTTagCompound xpCmp = new NBTTagCompound();
		
		for(AbstractSkill s : xpMap.keySet()) {
			xpCmp.setDouble(s.getRegistryName().toString(), xpMap.get(s));
		}
		
		NBTTagCompound buffCmp = new NBTTagCompound();
		
		for(AbstractBuff s : buffMap.keySet()) {
			buffCmp.setBoolean(s.getRegistryName().toString(), buffMap.get(s));
		}
		
		NBTTagCompound cmp = new NBTTagCompound();
		
		cmp.setTag(SKILLS_TAG, xpCmp);
		cmp.setTag(BUFF_TAG, buffCmp);
		
		return cmp;
	}

	@Override
	public void deserializeNBT(NBTTagCompound nbt) {
		if (nbt == null) nbt = new NBTTagCompound();
		NBTTagCompound cmp = (NBTTagCompound) nbt;
		NBTTagCompound xpCmp = cmp.hasKey(SKILLS_TAG) ? cmp.getCompoundTag(SKILLS_TAG) : new NBTTagCompound();
		for (ResourceLocation skill : SkilfulAPI.SKILL_REGISTRY.getKeys()) {
			xpMap.put(SkilfulAPI.SKILL_REGISTRY.getValue(skill), xpCmp.getDouble(skill.toString()));
		}
		NBTTagCompound buffCmp = cmp.hasKey(BUFF_TAG) ? cmp.getCompoundTag(BUFF_TAG) : new NBTTagCompound();
		for (ResourceLocation buff : SkilfulAPI.BUFF_REGISTRY.getKeys()) {
			buffMap.put(SkilfulAPI.BUFF_REGISTRY.getValue(buff), buffCmp.getBoolean(buff.toString()));
		}
		
	}

	@Override
	public Boolean hasBuff(AbstractBuff b) {
		return this.buffMap.get(b);
	}

	@Override
	public void addBuff(AbstractBuff b) {
		this.buffMap.put(b, true);
		
	}

	@Override
	public void reduceLevel(AbstractSkill s, int levelsLost) {
		this.xpMap.put(s, XP_TO_LEVEL[getLevelForXP(this.xpMap.get(s)) + 1 - levelsLost] - 1.0);
		
	}

	@Override
	public void setXP(AbstractSkill s, double xp) {
		this.xpMap.put(s, xp);
		
	}
}
