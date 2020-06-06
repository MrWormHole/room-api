package com.goldenhandsoftware.learningspring.data.repository;

import com.goldenhandsoftware.learningspring.data.entity.Room;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepository extends CrudRepository<Room, Long> {
}
