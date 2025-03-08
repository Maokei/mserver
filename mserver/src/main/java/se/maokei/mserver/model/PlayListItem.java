package se.maokei.mserver.model;

import java.util.UUID;

public record PlayListItem(
        UUID mediaId,
        String foreignId,
        String title
) {}