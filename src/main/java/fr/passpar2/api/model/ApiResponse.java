package fr.passpar2.api.model;

import org.springframework.http.HttpStatus;

public class ApiResponse<T> {

        private T response;
        private HttpStatus status;

        public ApiResponse(T response, HttpStatus status) {
            this.response = response;
            this.status = status;
        }

        public T getResponse() {
            return response;
        }

        public void setResponse(T response) {
            this.response = response;
        }

        public HttpStatus getStatus() {
            return status;
        }

        public void setStatus(HttpStatus status) {
            this.status = status;
        }

}
