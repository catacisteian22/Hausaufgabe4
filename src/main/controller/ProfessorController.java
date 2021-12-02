package main.controller;
import main.model.Professor;
import main.exceptions.ControllerExceptions.ControllerExceptions;
import main.repository.CrudRepository;

public class ProfessorController {
    private CrudRepository<Professor> repository;

    public ProfessorController(CrudRepository<Professor> professorRepo) {
        this.repository = professorRepo;
    }

    public Professor findById(long professorId){
        try{
            return this.repository.findOne(professorId);
        }
        catch (Exception e){
            throw new ControllerExceptions("Error");
        }
    }

    public Professor updateProfessor(Professor professor) {
        return this.repository.update(professor);
    }
}
