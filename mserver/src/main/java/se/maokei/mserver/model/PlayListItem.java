package se.maokei.mserver.model;

public record PlayListItem(
        String mediaId,
        String foreignId,
        String title
) {}