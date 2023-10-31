package com.phuonghoang.michael.phproductservice.domain.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {
    private Long code;

    @NotBlank
    private String name;

    private String description;

    private Set<String> categories;

    @Getter
    public enum Type {
        CODE("code"),
        NAME("name"),
        DESCRIPTION("description"),
        CATEGORIES("categories");
        private final String value;
        private static final Map<String, Type> BY_VALUE = new HashMap<>();

        Type(String value) {
            this.value = value;
        }

        static {
            for (Type type : values()) {
                BY_VALUE.put(type.value, type);
            }
        }

        public static Type toEnum(String value) {
            return BY_VALUE.get(value);
        }
    }
}
