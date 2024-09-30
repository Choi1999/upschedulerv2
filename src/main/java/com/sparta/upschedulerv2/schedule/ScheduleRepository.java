package com.sparta.upschedulerv2.schedule;

import aj.org.objectweb.asm.commons.Remapper;
import com.sparta.upschedulerv2.schedule.dto.ScheduleResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import java.awt.print.Pageable;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
}