package com.uniqmaster.uniqmastertest.repository;

import com.uniqmaster.uniqmastertest.model.entity.Event;
import java.sql.Timestamp;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
  List<Event> findByStartBetween(Timestamp first, Timestamp second);

  @Query("select e from Event e join e.audiences a where a.id=:audienceId and e.end<:timestamp order by e.start desc")
  List<Event> findByEndBeforeAndAudiencesIdOrderByStartDescLimit(
      @Param("timestamp") Timestamp timestamp,
      @Param("audienceId")
          Long audienceId,
      Pageable pageable);

  @Query("select e from Event e join e.audiences a where a.id=:audienceId and e.start<:end and e.start>:start order by e.start desc")
  List<Event> findByAudienceIdStartBetween(@Param("audienceId") Long audienceId,
                                           @Param("start") Timestamp start,
                                           @Param("end") Timestamp end);
}
