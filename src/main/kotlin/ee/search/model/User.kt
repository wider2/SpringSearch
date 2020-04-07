package ee.search.model

import org.hibernate.search.annotations.Field
import org.hibernate.search.annotations.Indexed
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "users")
@Indexed
public class User(
        @Id
        val id: Long,
        @Field
        @Column
        val name: String,
        @Field
        val surname: String,
        @Field
        val phoneNumber: String,
        @Field
        val email: String)

