package com.tomeortoyou.repositories;

import com.tomeortoyou.entities.Conversation;
import com.tomeortoyou.entities.User;
import org.springframework.data.mongodb.core.mapping.MongoId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ConversationRepository extends MongoRepository<Conversation, String> {
}
