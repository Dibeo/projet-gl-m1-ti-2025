package git.projetgl.api.handlers;

import git.projetgl.database.model.Message;
import git.projetgl.service.MessageService;
import io.javalin.http.Context;
import org.hibernate.SessionFactory;

import java.util.List;
import java.util.Map;

public class MessageHandler {

    private final MessageService messageService;

    public MessageHandler(SessionFactory sessionFactory) {
        this.messageService = new MessageService(sessionFactory);
    }

    public void getAllMessages(Context ctx) {
        List<Message> messages = messageService.getAllMessages();
        ctx.json(messages);
    }

    public void getMessageById(Context ctx) {
        Long id = Long.parseLong(ctx.pathParam("id"));
        Message message = messageService.getMessageById(id);
        if (message != null) {
            ctx.json(message);
        } else {
            ctx.status(404).json(Map.of("error", "Message not found"));
        }
    }

    public void sendMessage(Context ctx) {
        Message message = ctx.bodyAsClass(Message.class);
        Message created = messageService.sendMessage(message);
        ctx.status(201).json(created);
    }

    public void getConversationWithUser(Context ctx) {
        Long userId = Long.parseLong(ctx.pathParam("userId"));
        List<Message> conversation = messageService.getConversationWithUser(userId);
        ctx.json(conversation);
    }
}
