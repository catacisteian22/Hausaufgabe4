package main.repository;

import main.exceptions.RepositoryExceptions.KursRepoExceptions;
import main.model.Kurs;

import java.util.List;


/**
 * @author sncam
 */
public class KursInMemoryRepo implements CrudRepository<Kurs>{

    private List<Kurs> kurse;

    public KursInMemoryRepo(List<Kurs> courses) {
        this.kurse = kurse;
    }

    /**
     * @param id -die ID der Entity, die zurückgegeben sein muss(muss nicht NULL sein)
     * @return die Entity mit der spezifizierten ID oder NULL (ob keine Entity mit dieser ID existiert)
     * @throws KursRepoExceptions, ob die ID NULL ist oder die Liste der Kurse leer ist
     */
    @Override
    public Kurs findOne(Long id) {
        System.out.println(id);
        System.out.println(kurse);
        if (kurse.isEmpty())
        {
            throw new KursRepoExceptions("Error! Liste der Kurse ist leer!");
        }
        if(id==null)
        {
            throw new KursRepoExceptions("Error! ID darf nicht NULL sein");
        }
        else
        {
            for (Kurs c : kurse) {
                if (id == c.getKursId()) {
                    return c;
                }
            }
        }
        return null;
    }

    /**
     * @return alle Entities
     */
    @Override
    public Iterable<Kurs> findAll() {
        return kurse;
    }

    /**
     * @param entity Entity muss nicht NULL sein
     * @return NULL, ob die gegebene Entity gespeichert war, andernfalls gibt die Entity zurück(ID existiert schon)
     */
    @Override
    public Kurs save(Kurs entity) {
        for(Kurs c: kurse)
        {
            if(c.equals(entity))
            {
                return c;
            }
        }
        kurse.add(entity);
        return null;

    }

    /**
     * @param entity Entity muss nicht NULL sein
     * @return die entfernte Entity oder NULL, ob keine Entity mit der gegebene ID existiert
     * @throws KursRepoExceptions, ob die Liste der Kurse leer ist
     */
    @Override
    public Kurs delete(Kurs entity) {

        if(kurse.isEmpty())
        {
            throw new KursRepoExceptions("Error! Liste der Kurse ist leer!");
        }
        else
        {
            for (Kurs c : kurse)
            {
                if (c.equals(entity))
                {
                    kurse.remove(entity);
                    return entity;
                }
            }
        }
        return null;
    }

    /**
     * @param entity Entity muss nicht NULL sein
     * @return NULL, ob die Entity aktualisiert ist, andernfalls gibt die Entity zurück
     */
    @Override
    public Kurs update(Kurs entity) {

        for(Kurs kurs: kurse) {
            if (kurs.getKursId() == entity.getKursId()) {
                kurs.setName(entity.getName());
                kurs.setProfessor(entity.getProfessor());
                kurs.setMaxEnrolled(entity.getMaxEnrolled());
                kurs.setCredits(entity.getCredits());
                return null;
            }
        }
        return entity;
    }
}
