package comptoirs.exceptions;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Duplicate Found")
public class DuplicateException extends Exception {
        private static final long serialVersionUID = 1L;

        public DuplicateException(String msg) {
                super(msg);
        }
}