package com.headfishindustries.skilful;

import com.headfishindustries.skilful.api.AbstractSkill;

public class SkilfulSkills {

	public static final AbstractSkill MINING = new SimpleSkill("Mining", false);
	public static final AbstractSkill MELEE = new SimpleSkill("Melee", false);
	public static final AbstractSkill DEFENCE = new SimpleSkill("Defence", false);
	public static final AbstractSkill AGILITY = new SimpleSkill("Agility", false);
	public static final AbstractSkill EXCAVATION = new SimpleSkill("Excavation", false);
	
	public static class SimpleSkill extends AbstractSkill{
		private String name;
		private boolean hasBuff;
		
		public SimpleSkill(String name, boolean hasBuff) {
			this.name = name;
			this.hasBuff = hasBuff;
			this.setRegistryName(Skilful.MODID, this.name);
		}

		@Override
		public String getName() {
			return this.name;
		}

		@Override
		public boolean hasBuff() {
			return this.hasBuff;
		}
		
	}

}
