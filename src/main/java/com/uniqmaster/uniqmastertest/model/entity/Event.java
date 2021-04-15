package com.uniqmaster.uniqmastertest.model.entity;

import java.sql.Timestamp;
import java.time.OffsetDateTime;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Event extends BaseEntity{
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  private Timestamp start;
  private Timestamp end;
  private String topic;
  @Enumerated(EnumType.STRING)
  private EventType eventType ;
  @ManyToMany
  private Set<Audience> audiences = new HashSet<>();
  @ManyToMany
  private Set<Organizer> organizers = new HashSet<>();
}
