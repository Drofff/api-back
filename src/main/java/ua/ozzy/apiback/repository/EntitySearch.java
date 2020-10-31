package ua.ozzy.apiback.repository;

import java.util.List;

public interface EntitySearch<E, S> {

    List<E> findBy(S searchCriteria);

}
