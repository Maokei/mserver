package se.maokei.mserver.model;

public enum AccessLevel {
    PUBLIC,
    PRIVATE, //Only owner
    RESTRICTED //Owner plus allowed users and groups
}
