package ua.ozzy.apiback.repository;

import ua.ozzy.apiback.util.ReflectionUtil;

import javax.persistence.EntityManager;
import javax.persistence.criteria.*;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static java.util.Arrays.stream;
import static java.util.stream.Collectors.toMap;

/**
 * Generates and executes hibernate criteria query to search for an entity based
 * on fields of a search template class and values of its instance. Please use null
 * values to ignore search template fields in a particular search call
 *
 * @param <E> class of the DB entity to search for
 * @param <S> class defining possible search fields
 */
public abstract class SimpleEntitySearch<E, S> implements EntitySearch<E, S> {

    private final EntityManager entityManager;

    public SimpleEntitySearch(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<E> findBy(S searchCriteria) {
        Map<String, Object> fieldsAndValues = asFieldValueMap(searchCriteria);
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        Class<E> entityClass = getEntityClass();
        CriteriaQuery<E> query = cb.createQuery(entityClass);
        Root<E> entity = query.from(entityClass);
        Predicate searchPredicate = entityHasAllFieldValuesPredicate(entity, fieldsAndValues);
        query.select(entity).where(searchPredicate);
        return entityManager.createQuery(query).getResultList();
    }

    private Map<String, Object> asFieldValueMap(S sc) {
        Field[] fields = sc.getClass().getDeclaredFields();
        return stream(fields)
                .map(f -> {
                    Object value = ReflectionUtil.getValueOfField(sc, f);
                    return new Object() {
                        final String fieldName = f.getName();
                        final Object fieldValue = value;
                    };
                })
                .filter(obj -> obj.fieldValue != null)
                .collect(toMap(obj -> obj.fieldName, obj -> obj.fieldValue));
    }

    private Predicate entityHasAllFieldValuesPredicate(Root<?> entity, Map<String, Object> fieldsAndValues) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        List<Predicate> predicates = new ArrayList<>();
        for (var fieldAndValue : fieldsAndValues.entrySet()) {
            Path<?> field = entity.get(fieldAndValue.getKey());
            Predicate hasFieldValuePredicate = cb.equal(field, fieldAndValue.getValue());
            predicates.add(hasFieldValuePredicate);
        }
        return allAreTrue(predicates);
    }

    private Predicate allAreTrue(List<Predicate> predicates) {
        if (predicates.size() == 1) {
            return predicates.get(0);
        }
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        Predicate[] predicatesArray = predicates.toArray(new Predicate[] {});
        return cb.and(predicatesArray);
    }

    /**
     * Returns a class of the searched entity generic parameter assuming
     * that the actual type is passed to the abstract class directly
     *
     * @return a class object representing a type of the searched entity
     */
    @SuppressWarnings("unchecked")
    private Class<E> getEntityClass() {
        ParameterizedType superclass = (ParameterizedType) getClass().getGenericSuperclass();
        return (Class<E>) superclass.getActualTypeArguments()[0];
    }

}