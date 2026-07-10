package se.maokei.mserver.model;

import org.springframework.data.relational.core.mapping.Column;

import java.util.UUID;

public record PlayListItem(
        @Column("media_id")
        UUID mediaId,
        Integer position
) {}