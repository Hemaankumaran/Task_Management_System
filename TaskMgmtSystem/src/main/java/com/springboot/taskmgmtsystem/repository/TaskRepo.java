package com.springboot.taskmgmtsystem.repository;

import com.springboot.taskmgmtsystem.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepo extends JpaRepository<Task, Long> {
}
