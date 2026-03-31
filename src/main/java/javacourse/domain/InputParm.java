package javacourse.domain;

import lombok.Builder;
import lombok.Data;

import java.nio.file.Path;

@Data
@Builder
public class InputParm {
    Path filePath;
    Long truckCount;
    LoaderType loaderType;
}
