package main.repository;

public interface IFileRepository<E> extends CrudRepository<E>{
    String getFileName();

}
