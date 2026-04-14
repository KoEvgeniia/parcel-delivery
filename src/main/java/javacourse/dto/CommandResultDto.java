package javacourse.dto;

import lombok.Builder;
import lombok.Data;

/**
 * Command result info
 */
@Data
@Builder
public class CommandResultDto {
    String message;
    String debug;
}
