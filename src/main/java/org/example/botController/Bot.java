package org.example.botController;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.requests.GatewayIntent;
import org.example.chatController.ChatController;
import org.example.commands.CommandManager;
import org.example.commands.Sum;
import org.example.events.Listeners;
import org.example.events.MessageEvent;
import org.example.propertiesUtil.PropertiesUtil;

public class Bot {
    public static void main(String[] args){
        final String BOT_TOKEN = PropertiesUtil.getString("discord.bot.token");

        ChatController chatController = new ChatController();

        JDA jda = JDABuilder.createDefault(BOT_TOKEN)
                .enableIntents(GatewayIntent.MESSAGE_CONTENT)
                .build();

        jda.addEventListener(new MessageEvent(chatController));
        jda.addEventListener(new Listeners());

        CommandManager manager = new CommandManager();
        manager.add(new Sum());
        //Add more commands here

        jda.addEventListener(manager);
    }
}


