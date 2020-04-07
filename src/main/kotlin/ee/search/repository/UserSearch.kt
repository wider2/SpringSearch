package ee.search.repository

import ee.search.model.User
import org.hibernate.search.jpa.FullTextQuery
import org.springframework.stereotype.Repository
import javax.transaction.Transactional
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext

@Repository
@Transactional
class UserSearch(@PersistenceContext val entityManager: EntityManager) {

    fun searchUsers(text: String): List<User> {

        val fullTextEntityManager = org.hibernate.search.jpa.Search.getFullTextEntityManager(entityManager)
        fullTextEntityManager.flushToIndexes()
        fullTextEntityManager.flush()

        val queryBuilder = fullTextEntityManager.searchFactory
                .buildQueryBuilder().forEntity(User::class.java).get()

        /*
        Fuzzy queries are working like keyword queries, except that we can define a limit of “fuzziness”, above which Lucene shall accept the two terms as matching.

By withEditDistanceUpTo(), we can define how much a term may deviate from the other. It can be set to 0, 1, and 2, whereby the default value is 2 (note: this limitation is coming from the Lucene's implementation).

By withPrefixLength(), we can define the length of the prefix which shall be ignored by the fuzziness:
         */
        val query = queryBuilder
                .keyword()
                .fuzzy()
                .withEditDistanceUpTo(2)
                .withPrefixLength(0)
                //.onFields("name")
                .onField("name")
                .matching(text)
                .createQuery()

        // wrap the Lucene query into a Hibernate query:
        val jpaQuery: FullTextQuery = fullTextEntityManager.createFullTextQuery(query, User::class.java)

        return jpaQuery.resultList.map { result -> result as User }.toList()
    }
}