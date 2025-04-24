package com.example.talkhiss.dto;

import java.io.Serializable;

public record FileItemDTO(
    String name,
    String id,
    String thumbnailLink
) implements Serializable {
    // Records automatically handle serialization
}