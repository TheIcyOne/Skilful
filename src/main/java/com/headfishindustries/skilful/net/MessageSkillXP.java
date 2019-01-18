package com.headfishindustries.skilful.net;

import java.nio.charset.StandardCharsets;
import java.util.UUID;

import org.apache.commons.io.input.CharSequenceReader;

import com.headfishindustries.skilful.api.AbstractSkill;
import com.headfishindustries.skilful.api.PlayerManager;
import com.headfishindustries.skilful.api.SkilfulAPI;
import com.headfishindustries.skilful.api.event.LevelUpEvent;
import com.headfishindustries.skilful.api.event.XPGainEvent;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class MessageSkillXP implements IMessage{
	
	private AbstractSkill skill;
	private double xp;
	private EntityPlayer player;

	public MessageSkillXP() {
		
	}
	
	public MessageSkillXP(EntityPlayer p, AbstractSkill s, double x) {
		this.skill = s;
		this.xp = x;
		this.player = p;
	}
	
	public MessageSkillXP(LevelUpEvent e) {
		this(e.getPlayer(), e.getSkill(), PlayerManager.getXP(e.getPlayer(), e.getSkill()));
	}
	
	public MessageSkillXP(XPGainEvent e) {
		this(e.getPlayer(), e.getSkill(), e.getXPAmount() + PlayerManager.getXP(e.getPlayer(), e.getSkill()));
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		long msb = buf.readLong();
		long lsb = buf.readLong();
		this.player = Minecraft.getMinecraft().world.getPlayerEntityByUUID(new UUID(msb, lsb));
		
		int l = buf.readInt();
		this.skill = SkilfulAPI.SKILL_REGISTRY.getValue(new ResourceLocation((String) buf.readCharSequence(l, StandardCharsets.UTF_8)));
		
		this.xp = buf.readDouble();
	}

	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeLong(this.player.getUniqueID().getMostSignificantBits());
		buf.writeLong(this.player.getUniqueID().getLeastSignificantBits());
		
		buf.writeInt(skill.getRegistryName().toString().length());
		buf.writeCharSequence(skill.getRegistryName().toString(), StandardCharsets.UTF_8);
		buf.writeDouble(xp);
	}
	
	public static class SkillMessageHandler implements IMessageHandler<MessageSkillXP, IMessage>{

		@Override
		public IMessage onMessage(MessageSkillXP message, MessageContext ctx) {
			PlayerManager.setXP(message.player, message.skill, message.xp);
			return null;
		}
		
	}
	

}
