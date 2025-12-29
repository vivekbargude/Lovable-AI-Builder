package com.vivek.projects.lovable_clone.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "projects",
        indexes = {
        @Index(name = "idx_projects_updated_at_desc", columnList = "updated_at DESC, deleted_at"),
        @Index(name = "idx_project_deleted_at", columnList = "deleted_at")
        }
        //Trying to use the field in index at first which can eliminate most of the rows
        //select * from projects where user_id = 1 AND status = COMPLETED
        //here the status field can fetch many records but the user_id can fetch the necessary records hence using user_id first in the index

        //If index is created on A, B, C, D
        //then querying based on (A), (A,B), (A,B,C) can use the same index.

)
public class Project {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(nullable = false)
    String name;

    //many projects can belong to one user.
//    @ManyToOne
//    @JoinColumn(name = "owner_id",nullable = false)
//    User owner;

    Boolean isPublic = false;

    @CreationTimestamp
    Instant createdAt;

    @UpdateTimestamp
    Instant updatedAt;
    Instant deletedAt; //Soft delete
}
