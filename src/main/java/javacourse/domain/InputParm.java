package javacourse.domain;

import lombok.Builder;
import lombok.Data;

import java.nio.file.Path;

/**
 * An object with incoming parameters
 */
@Data
@Builder
public class InputParm {
    Path filePath;
    Long truckCount;
    LoaderType loaderType;
    String parcelsText;
    LoadParcelOutType outType;
    String fileName;
    boolean withCount;
}
