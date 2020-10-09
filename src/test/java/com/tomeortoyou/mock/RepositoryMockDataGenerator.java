package com.tomeortoyou.mock;

import com.tomeortoyou.entities.Conversation;
import com.tomeortoyou.entities.Message;
import com.tomeortoyou.entities.User;
import com.tomeortoyou.repositories.IConversationRepository;
import com.tomeortoyou.repositories.IUserRepository;
import org.mockito.Mockito;

import java.util.List;
import java.util.Optional;

//TODO Create a generic file that reads from a json or a way to make it so it generates random values
public class RepositoryMockDataGenerator {
    private IUserRepository userRepository;
    private IConversationRepository conversationRepository;

    public RepositoryMockDataGenerator(IUserRepository userRepository, IConversationRepository conversationRepository) {
        this.userRepository = userRepository;
        this.conversationRepository = conversationRepository;
    }

    public void generate() {
        User alex = new User();
        alex.setId(MongoIds.get("Alex"));
        alex.setUsername("Alex");

        User mike = new User();
        mike.setId(MongoIds.get("Mike"));
        mike.setUsername("Mike");

        User bob = new User();
        bob.setUsername("Bob");

        Message message1 = new Message(
                mike.getId(),
                "Hello, Mike how are you doing?");
        Message message2 = new Message(
                alex.getId(),
                "Hey Alex, I'm doing great, thanks");
        Conversation conversation = new Conversation();
        conversation.setId(MongoIds.get("Conversation1"));
        conversation.getUsers().add(alex.getId());
        conversation.getUsers().add(mike.getId());
        conversation.getMessages().add(message1);
        conversation.getMessages().add(message2);

        alex.getConversations().add(conversation.getId());

        //Bean Mock
        userRepositoryMock(alex, mike);
        conversationRepositoryMock(alex, conversation);
    }


    private void conversationRepositoryMock(User alex, Conversation conversation) {
        Mockito.when(conversationRepository.findById(MongoIds.get("Conversation1")))
                .thenReturn(Optional.of(conversation));
        Mockito.when(conversationRepository.findAllById(alex.getConversations()))
                .thenReturn(List.of(conversation));
        Mockito.when(conversationRepository.findAll())
                .thenReturn(List.of(conversation));
    }

    private void userRepositoryMock(User alex, User mike) {
        Mockito.when(userRepository.findByUsername(alex.getUsername()))
                .thenReturn(alex);
        Mockito.when(userRepository.findById(MongoIds.get("Alex")))
                .thenReturn(Optional.of(alex));
        Mockito.when(userRepository.findById(MongoIds.get("Mike")))
                .thenReturn(Optional.of(mike));
        Mockito.when(userRepository.findAll())
                .thenReturn(List.of(alex, mike));
    }
}
