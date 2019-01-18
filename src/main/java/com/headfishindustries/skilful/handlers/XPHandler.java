package com.headfishindustries.skilful.handlers;

import com.headfishindustries.skilful.Skilful;
import com.headfishindustries.skilful.SkilfulSkills;
import com.headfishindustries.skilful.api.PlayerManager;
import com.headfishindustries.skilful.api.event.LevelUpEvent;
import com.headfishindustries.skilful.api.event.XPGainEvent;
import com.headfishindustries.skilful.net.MessageSkillXP;
import com.headfishindustries.skilful.util.MessageUtils;

import net.minecraft.entity.item.EntityFireworkRocket;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.world.World;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber(modid=Skilful.MODID)
public class XPHandler {
	
	public static ItemStack levelUpRocket;
	
	public static void makeRocket() {
		levelUpRocket = new ItemStack(Items.FIREWORKS, 111);
		NBTTagCompound t = levelUpRocket.hasTagCompound() ? levelUpRocket.getTagCompound() : new NBTTagCompound();
		NBTTagCompound fireworks = new NBTTagCompound();
		fireworks.setByte("Flight", (byte) 10);
		
		NBTTagList explosions = new NBTTagList();		
		NBTTagCompound explosion = new NBTTagCompound();
		explosion.setBoolean("Flicker", true);
		explosion.setBoolean("Trail", true);
		explosion.setByte("Type", (byte) 1);
		explosion.setIntArray("Colors", new int[]{255, 15, 15});	
		explosions.set(0, explosion);
		
		fireworks.setTag("Explosions", explosions);
		t.setTag("Fireworks", fireworks);
		
		levelUpRocket.setTagCompound(t);
	}
		

	@SubscribeEvent
	public static void onLevelUp(LevelUpEvent e) {
		Skilful.WRAPPER.sendToAll(new MessageSkillXP(e.getPlayer(), e.getSkill(), e.getLevel()));
		World w = e.getPlayer().world;
		EntityFireworkRocket f = new EntityFireworkRocket(w, levelUpRocket, e.getPlayer());
		w.spawnEntity(f);
		MessageUtils.tellLevel(e.getPlayer(), e.getSkill());
	}
	
	@SubscribeEvent
	public static void onXPGained(XPGainEvent e) {
		Skilful.WRAPPER.sendTo(new MessageSkillXP(e), (EntityPlayerMP) e.getPlayer());
	}
	
	@SubscribeEvent
	public static void onBlockBroken(BlockEvent.BreakEvent e) {		
		String s = e.getState().getBlock().getHarvestTool(e.getState());
		switch(s) {
		case "pickaxe":
			PlayerManager.addXP(e.getPlayer(), SkilfulSkills.MINING, e.getState().getBlockHardness(e.getWorld(), e.getPos()) * 5);
			break;
		default:
			break;
		}
	}

}
