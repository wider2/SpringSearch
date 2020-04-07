package ee.search.exception

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND) //to return 404 status
class RecordNotFoundException(message: String) : Exception(message)
