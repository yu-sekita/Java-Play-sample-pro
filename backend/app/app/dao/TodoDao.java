package dao;

import java.util.List;

import com.google.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import play.db.jpa.JPAApi;

import domain.TodoDO;


public class TodoDao {
    private static final String ENTITY_MANAGER_NAME = "default";

    @Inject
    protected JPAApi jpaApi;

    public TodoDO create(TodoDO todoDO) {
        jpaApi.withTransaction(entityManager -> { entityManager.persist(todoDO); });
        return todoDO;
    }

    public TodoDO find(Integer id) {
        return jpaApi.em(ENTITY_MANAGER_NAME).find(TodoDO.class, id);
    }

    public List<TodoDO> findAll(int limit) {
        System.out.println("start findAll");
        System.out.println("initialize EntityManager");
        EntityManager entityManager = jpaApi.em(ENTITY_MANAGER_NAME);
        System.out.println(" EntityManager initialized");

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<TodoDO> criteriaQuery = criteriaBuilder.createQuery(TodoDO.class);
        Root<TodoDO> root = criteriaQuery.from(TodoDO.class);
        criteriaQuery.select(root);
        return entityManager.createQuery(criteriaQuery).setMaxResults(limit).getResultList();
    }

    public void delete(int id) {
        jpaApi.withTransaction(entityManager -> {
            TodoDO todoDO = entityManager.find(TodoDO.class, id);
            if (todoDO != null) {
                entityManager.remove(todoDO);
            }
        });
    }

    public TodoDO update(TodoDO todoDO) {
        jpaApi.withTransaction(entityManager -> {entityManager.merge(todoDO);});
        return todoDO;
    }
}