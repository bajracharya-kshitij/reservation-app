package com.kshitij.reservation.repository;

import com.kshitij.reservation.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, Long> {
}
