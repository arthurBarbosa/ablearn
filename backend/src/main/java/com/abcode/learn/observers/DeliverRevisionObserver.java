package com.abcode.learn.observers;

import com.abcode.learn.entities.Deliver;

public interface DeliverRevisionObserver {

    void onSaveRevision(Deliver deliver);
}
