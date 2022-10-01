package pljava.spring_jpa.todo.spring_jpa_todo.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import pljava.spring_jpa.todo.spring_jpa_todo.models.Task;
import java.util.List;

@Repository
public interface TaskRepository extends CrudRepository<Task, Integer> {
    List<Task> findAllByStatus (boolean status);
}
