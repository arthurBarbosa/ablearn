package com.abcode.learn.services;

import com.abcode.learn.dto.DeliverRevisionDTO;
import com.abcode.learn.entities.Deliver;
import com.abcode.learn.observers.DeliverRevisionObserver;
import com.abcode.learn.repositories.DeliverRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedHashSet;
import java.util.Set;

@Service
public class DeliverService {

    @Autowired
    private DeliverRepository deliverRepository;

    private final Set<DeliverRevisionObserver> deliverRevisionObservers = new LinkedHashSet<>();

    @Transactional
    public void saveRevision(Long id, DeliverRevisionDTO dto) {
        Deliver deliver = deliverRepository.getOne(id);
        deliver.setStatus(dto.getStatus());
        deliver.setFeedback(dto.getFeedback());
        deliver.setCorrectCount(dto.getCorrectCount());
        deliverRepository.save(deliver);
        for(DeliverRevisionObserver observer : deliverRevisionObservers){
            observer.onSaveRevision(deliver);;
        }
    }

    public void subscribeDeliverRevisionObserver(DeliverRevisionObserver observer) {
        deliverRevisionObservers.add(observer);
    }
}
