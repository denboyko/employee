package com.demo.test.model;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Page<T> {
    private List<T> data;
    private int total;
    private int page;
    private int perPage;

    public long getOffset() {
        return (long) page * (long) perPage;
    }
}
