package com.vivek.projects.lovable_clone.dto.subscription;

public record PlanResponse(
        Long id,
        String name,
        String stripePriceId,
        Integer maxProjects,
        Integer maxTokensPerDay,
        Integer maxPreviews,
        Boolean unlimitedAi, //Unlimited access to LLM, ignore maxTokens per day
        Boolean active
) {
}
