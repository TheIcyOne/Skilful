package com.headfishindustries.skilful.api.capability;

import com.headfishindustries.skilful.api.AbstractBuff;
import com.headfishindustries.skilful.api.AbstractSkill;
import net.minecraft.nbt.NBTTagCompound;

public interface ISkillData {
	
	public Double getXP(AbstractSkill s);
	
	public Integer getLevel(AbstractSkill s);
	
	/** Should return true if xp gain caused level up. **/
	public boolean addXP(AbstractSkill s, double xp);
	
	/** Set level to 1 xp below level up.**/
	public default void reduceLevel(AbstractSkill s) {
		reduceLevel(s, 1);
	}
	
	public void reduceLevel(AbstractSkill s, int levelsLost);
		
	public Boolean hasBuff(AbstractBuff b);
	
	public void addBuff(AbstractBuff b);
	
	public NBTTagCompound serializeNBT();
	public void deserializeNBT(NBTTagCompound nbt);

	public void setXP(AbstractSkill s, double xp);

}
