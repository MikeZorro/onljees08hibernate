package pl.coderslab;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Repository
@Transactional
public class PersonDetailsDAO {

    @PersistenceContext
    private EntityManager entityManager;

    public void savePersonDetails(PersonDetails personDetails) {
        entityManager.persist(personDetails);
    }

    public PersonDetails findById(long id) {
        return entityManager.find(PersonDetails.class, id);
    }

    public void update(PersonDetails personDetails ) {
        entityManager.merge(personDetails);
    }

    public void delete(PersonDetails personDetails ) {
        entityManager.remove(entityManager.contains(personDetails) ?
                personDetails : entityManager.merge(personDetails));
    }
}
