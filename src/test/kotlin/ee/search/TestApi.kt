package ee.search

import ee.search.model.User
import org.hamcrest.Matchers.equalTo
import org.hamcrest.Matchers.not
import org.json.JSONObject
import org.junit.Assert.assertThat
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.springframework.http.HttpStatus
import org.springframework.web.client.RestTemplate
import java.net.URI

class TestApi {
    val url = URI("http://localhost:8081/users")
    val urlSearch = URI("http://localhost:8081/users/search?query=lex")
    val urlId = URI("http://localhost:8081/users?id=1")

    lateinit var restTemplate: RestTemplate

    @Before
    fun setUp() {
        restTemplate = RestTemplate()
    }

    @Test
    fun testPostItem() {
        val user = User(1, "Alex", "Xela", "+372 xxxxxxxx", "test@test.ee")

        val response = restTemplate.postForEntity(url, user, String::class.java)
        assertThat(response.statusCode, equalTo(HttpStatus.OK))
        assertNotNull(response)

        val result = restTemplate.getForEntity(url, String::class.java)
        assertNotNull(result)
        val entity = JSONObject.wrap(result.toString())
        assertNotNull(entity)

    }

    @Test
    fun testGetItem() {
        val result = restTemplate.getForEntity(url, String::class.java)
        assertThat(result.statusCode, not(HttpStatus.BAD_REQUEST))
        assertNotNull(result)
    }

    @Test
    fun testGetItemById() {
        val result = restTemplate.getForEntity(urlId, String::class.java)
        assertThat(result.statusCode, equalTo(HttpStatus.OK))
        assertNotNull(result)
    }

    @Test
    fun testSearch() {
        val result = restTemplate.getForEntity(urlSearch, String::class.java)
        assertNotNull(result)
    }

    @Test
    fun testDeleteItemById() {
        val result = restTemplate.delete(urlId)
        assertNotNull(result)
    }
}