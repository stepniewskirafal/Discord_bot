package org.example.events;

import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.example.chatController.ChatController;

public class MessageEvent extends ListenerAdapter {

    private final ChatController chatController;

    public MessageEvent(ChatController chatController) {
        this.chatController = chatController;
    }

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        String messageReceived = event.getMessage().getContentRaw();
        String authorName = event.getAuthor().getGlobalName();
        User user = event.getAuthor();
        if (!user.isBot() && !messageReceived.startsWith("$")) {
            if (messageReceived.equalsIgnoreCase("hello")
            ||  messageReceived.equalsIgnoreCase("hi")){
                event.getChannel().sendMessage("Hi  " + authorName).queue();
            }else{
                String chatGptAnswer = chatController.chatGPT(messageReceived);
                event.getChannel().sendMessage(chatGptAnswer).queue();
                System.out.println(chatGptAnswer);
            }
        }



    }
}
