package com.vivek.projects.lovable_clone.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;

// Java CamelCase notation i.e passwordHash -> DB snake case i.e password_hash using ORM (Hibernate)

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "users")
public class User {
     @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
     Long id;
     String email;
     String passwordHash;
     String name;
     String avatarUrl;

     @CreationTimestamp
     Instant createdAt;

     @UpdateTimestamp
     Instant updatedAt;

     Instant deletedAt; //Soft delete
}
