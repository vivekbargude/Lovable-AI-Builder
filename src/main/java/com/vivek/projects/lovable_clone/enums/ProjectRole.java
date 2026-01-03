package com.vivek.projects.lovable_clone.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Set;

import static com.vivek.projects.lovable_clone.enums.ProjectPermission.*;

@RequiredArgsConstructor
@Getter
public enum ProjectRole {
    VIEWER(Set.of(VIEW,VIEW_MEMBERS)),
    EDITOR(Set.of(EDIT,VIEW,DELETE,VIEW_MEMBERS)),
    OWNER(Set.of(EDIT,VIEW,MANAGE_MEMBERS,DELETE,VIEW_MEMBERS));

    private final Set<ProjectPermission> permissions;
}
