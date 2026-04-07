package com.springboot.taskmgmtsystem.dto;

import java.util.List;

public record TaskPageResDto(
        List<TaskResDto> data,
        long total_records,
        int total_pages
) {
}
