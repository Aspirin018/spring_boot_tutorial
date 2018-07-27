package com.example.space.api.domain.secondary;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author liyu
 * @date 18-7-26
 */
public interface MessageRepository extends JpaRepository<Message, Long> {
}
