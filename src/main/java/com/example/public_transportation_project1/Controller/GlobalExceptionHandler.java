//העמוד הזה מטפל בשגיאות בצורה יפה...

package com.example.public_transportation_project1.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    // טיפול ב-404 - משאב לא נמצא
    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<Map<String, String>> handleNotFound(NoHandlerFoundException ex) {
        Map<String, String> response = new HashMap<>();
        response.put("status", "404");
        response.put("error", "הדף לא נמצא");
        response.put("message", "המשאב שחיפשת לא קיים במערכת");
        response.put("timestamp", java.time.LocalDateTime.now().toString());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    // טיפול בשגיאות ולידציה של Spring
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidation(MethodArgumentNotValidException ex) {
        Map<String, String> response = new HashMap<>();
        response.put("status", "400");
        response.put("error", "שגיאת ולידציה");
        response.put("message", "המידע שהזנת לא תקין");
        response.put("details", ex.getBindingResult().getFieldError() != null ? 
                     ex.getBindingResult().getFieldError().getDefaultMessage() : "שדה חובה חסר");
        response.put("timestamp", java.time.LocalDateTime.now().toString());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    // טיפול בשגיאות לוגיקה (כמו נהג תפוס)
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map<String, String>> handleValidation(IllegalArgumentException ex) {
        Map<String, String> response = new HashMap<>();
        response.put("status", "400");
        response.put("error", "שגיאת לוגיקה");
        response.put("message", ex.getMessage());
        response.put("timestamp", java.time.LocalDateTime.now().toString());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    // טיפול בשגיאות גישה אסורה
    @ExceptionHandler(org.springframework.security.access.AccessDeniedException.class)
    public ResponseEntity<Map<String, String>> handleAccessDenied(org.springframework.security.access.AccessDeniedException ex) {
        Map<String, String> response = new HashMap<>();
        response.put("status", "403");
        response.put("error", "גישה אסורה");
        response.put("message", "אין לך הרשאות מתאימות לבצע את הפעולה הזו");
        response.put("timestamp", java.time.LocalDateTime.now().toString());
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
    }

    // טיפול בשגיאות SQL/מסד נתונים
    @ExceptionHandler(org.springframework.dao.DataAccessException.class)
    public ResponseEntity<Map<String, String>> handleDatabase(org.springframework.dao.DataAccessException ex) {
        Map<String, String> response = new HashMap<>();
        response.put("status", "500");
        response.put("error", "שגיאת מסד נתונים");
        response.put("message", "אירעה שגיאה בעת גישה למסד הנתונים");
        response.put("timestamp", java.time.LocalDateTime.now().toString());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }

    // תופס שגיאות כלליות (כדי שלא יראה "מכוער")
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, String>> handleAll(Exception ex) {
        Map<String, String> response = new HashMap<>();
        response.put("status", "500");
        response.put("error", "שגיאת שרת");
        response.put("message", "אירעה שגיאה כללית בשרת");
        response.put("details", ex.getMessage());
        response.put("timestamp", java.time.LocalDateTime.now().toString());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }
}