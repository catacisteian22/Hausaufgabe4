package main.controller;
import main.model.Teacher;
import main.exceptions.ControllerExceptions.ControllerExceptions;
import main.repository.CrudRepository;

/**
 * @author sncam
 */
public class TeacherController {
    private CrudRepository<Teacher> repository;

    public TeacherController(CrudRepository<Teacher> teacherRepo) {
        this.repository = teacherRepo;
    }

    public Teacher findById(long teacherId){
        try{
            return this.repository.findOne(teacherId);
        }
        catch (Exception e){
            throw new ControllerExceptions("Error");
        }
    }

    public Teacher updateTeacher(Teacher teacher) {
        return this.repository.update(teacher);
    }
}
