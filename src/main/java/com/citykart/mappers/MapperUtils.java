package com.citykart.mappers;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Small null-safe helpers for mapping.
 */
public final class MapperUtils {
    private MapperUtils() {}

    public static <T> List<T> nullSafe(List<T> list) {
        return list == null ? new ArrayList<>() : list;
    }
    public static <S, T> List<T> mapList(List<S> source, Function<S, T> fn) {
        if (source == null) return new ArrayList<>();
        return source.stream()
                .filter(Objects::nonNull)
                .map(fn)
                .collect(Collectors.toList());
    }
}
