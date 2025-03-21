package fr.passpar2.api.response;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public class ApiResponse<T> {
    private T response;
    private HttpStatus status;
    private LocalDateTime timestamp;

    public ApiResponse(T response, HttpStatus status) {
        this.response = response;
        this.status = status;
        this.timestamp = LocalDateTime.now();
    }

    public T getResponse() { return response; }
    public void setResponse(T response) { this.response = response; }

    public HttpStatus getStatus() { return status; }
    public void setStatus(HttpStatus status) { this.status = status; }

    public LocalDateTime getTimestamp() { return timestamp; }
    public void setTimestamp(LocalDateTime timestamp) { this.timestamp = timestamp; }
}
