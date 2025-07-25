package kg.attractor.demo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EducationInfo {
    private int id;
    private int resume_id;
    private String institution;
    private String program;
    private LocalDateTime start_date;
    private LocalDateTime end_date;
    private String degree;
}
