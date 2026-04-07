package com.springboot.taskmgmtsystem.service;

import com.springboot.taskmgmtsystem.dto.TaskResDto;
import com.springboot.taskmgmtsystem.enums.TaskPriority;
import com.springboot.taskmgmtsystem.enums.TaskStatus;
import com.springboot.taskmgmtsystem.exceptions.ResourceNotFoundException;
import com.springboot.taskmgmtsystem.mapper.TaskMapper;
import com.springboot.taskmgmtsystem.model.Customer;
import com.springboot.taskmgmtsystem.model.Task;
import com.springboot.taskmgmtsystem.repository.TaskRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class TaskServiceTest {
    @InjectMocks
    private TaskService taskService;
    @Mock
    private TaskRepo taskRepo;

    @Test
    public void getAllTasksTest(){
        // check null
        Assertions.assertNotNull(taskService);

        // data
        Task task1 = new Task();
        task1.setTitle("Running");
        task1.setDueDate(LocalDate.parse("2026-03-29"));
        task1.setTaskPriority(TaskPriority.HIGH);
        task1.setTaskStatus(TaskStatus.PENDING);
        Customer customer1 = new Customer();
        customer1.setName("harry");
        customer1.setContact("2038493");
        task1.setCustomer(customer1);

        Task task2 = new Task();
        task2.setTitle("Eating");
        task2.setDueDate(LocalDate.parse("2026-04-11"));
        task2.setTaskPriority(TaskPriority.MEDIUM);
        task2.setTaskStatus(TaskStatus.IN_PROGRESS);
        Customer customer2 = new Customer();
        customer2.setName("harry");
        customer2.setContact("2038493");
        task2.setCustomer(customer2);
        List<Task> list = List.of(task1, task2);

        Pageable pageable = PageRequest.of(0, 2);
        Page<Task> taskPage = new PageImpl<>(list);

        // mock
        Mockito.when(taskRepo.findAll(pageable)).thenReturn(taskPage);

        // assert
        Assertions.assertEquals(2, taskService.getAllTasks(0, 2).data().size());
    }

    @Test
    public void getByIdTestWhenExists(){
        Task task1 = new Task();
        task1.setTitle("Running");
        task1.setDueDate(LocalDate.parse("2026-03-29"));
        task1.setTaskPriority(TaskPriority.HIGH);
        task1.setTaskStatus(TaskStatus.PENDING);
        Customer customer1 = new Customer();
        customer1.setName("harry");
        customer1.setContact("2038493");
        task1.setCustomer(customer1);
        TaskResDto dto = TaskMapper.toDto(task1);

        Task task2 = new Task();
        task2.setTitle("Eating");
        task2.setDueDate(LocalDate.parse("2026-04-11"));
        task2.setTaskPriority(TaskPriority.MEDIUM);
        task2.setTaskStatus(TaskStatus.IN_PROGRESS);
        Customer customer2 = new Customer();
        customer2.setName("harry");
        customer2.setContact("2038493");
        task2.setCustomer(customer2);
        TaskResDto dto2 = TaskMapper.toDto(task2);

        Mockito.when(taskRepo.findById(3L)).thenReturn(Optional.of(task1));

        Assertions.assertEquals(dto, taskService.getTaskById(3L));
        Assertions.assertNotEquals(dto2, taskService.getTaskById(3L));

        Mockito.verify(taskRepo, Mockito.times(2)).findById(3L);
    }

    @Test
    public void getByIdTestWhenNotExists(){
        Mockito.when(taskRepo.findById(3L)).thenReturn(Optional.empty());

        Exception e = Assertions.assertThrows(ResourceNotFoundException.class, () -> taskService.getTaskById(3L));
        Assertions.assertEquals("Invalid task id..", e.getMessage());
        Assertions.assertNotEquals("Invalid customer id..", e.getMessage());
    }
}
