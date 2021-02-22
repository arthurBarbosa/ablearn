package com.abcode.learn.services;

import com.abcode.learn.dto.NotificationDTO;
import com.abcode.learn.entities.Notification;
import com.abcode.learn.entities.User;
import com.abcode.learn.repositories.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    @Autowired
    private NotificationRepository notificationRepository;

    @Autowired
    private AuthService authService;

    public Page<NotificationDTO> notificationForCurrentUser(boolean unreadOnly, Pageable pageable) {
        User user = authService.authenticated();
        Page<Notification> page = notificationRepository.find(user, unreadOnly, pageable);
        return page.map(x -> new NotificationDTO(x));
    }
}
