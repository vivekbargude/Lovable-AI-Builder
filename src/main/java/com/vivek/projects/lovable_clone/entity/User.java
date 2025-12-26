package com.vivek.projects.lovable_clone.entity;

import jakarta.persistence.Entity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.time.Instant;

// Java CamelCase notation i.e passwordHash -> DB snake case i.e password_hash using ORM (Hibernate)

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User {
     Long id;
     String email;
     String passwordHash;
     String name;
     String avatarUrl;
     Instant createdAt;
     Instant updatedAt;
     Instant deletedAt; //Soft delete
}
