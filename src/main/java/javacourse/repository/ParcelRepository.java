package javacourse.repository;

import javacourse.domain.Parcel;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Repository for parcels
 */
public class ParcelRepository {

    private final List<Parcel> parcels = new ArrayList<>(List.of(
            Parcel.builder()
                    .name("Parcel type 9")
                    .form(new Character[][]{{'9', '9', '9'}, {'9', '9', '9'}, {'9', '9', '9'}})
                    .width(3)
                    .height(3)
                    .build(),
            Parcel.builder()
                    .name("Parcel type 8")
                    .form(new Character[][]{{'8', '8', '8', '8'}, {'8', '8', '8', '8'}})
                    .width(4)
                    .height(2)
                    .build(),
            Parcel.builder()
                    .name("Parcel type 7")
                    .form(new Character[][]{{'7', '7', '7', '7'}, {'7', '7', '7'}})
                    .width(4)
                    .height(2)
                    .build(),
            Parcel.builder()
                    .name("Parcel type 6")
                    .form(new Character[][]{{'6', '6', '6'}, {'6', '6', '6'}})
                    .width(3)
                    .height(2)
                    .build(),
            Parcel.builder()
                    .name("Parcel type 5")
                    .form(new Character[][]{{'5', '5', '5', '5', '5'}})
                    .width(5)
                    .height(1)
                    .build(),
            Parcel.builder()
                    .name("Parcel type 4")
                    .form(new Character[][]{{'4', '4', '4', '4'}})
                    .width(4)
                    .height(1)
                    .build(),
            Parcel.builder()
                    .name("Parcel type 3")
                    .form(new Character[][]{{'3', '3', '3'}})
                    .width(3)
                    .height(1)
                    .build(),
            Parcel.builder()
                    .name("Parcel type 2")
                    .form(new Character[][]{{'2', '2'}})
                    .width(2)
                    .height(1)
                    .build(),
            Parcel.builder()
                    .name("Parcel type 1")
                    .form(new Character[][]{{'1'}})
                    .width(1)
                    .height(1)
                    .build()
    ));

    /**
     * Finds all parcels in the repository
     * @return list of parcels
     */
    public List<Parcel> findAll() {
        return parcels;
    }

    /**
     * Finds a parcel in the repository
     * @param name name of parcel
     * @return parcel
     */
    public Optional<Parcel> findByName(String name) {
        return parcels.stream()
                .filter(parcel -> parcel.getName().equals(name))
                .findFirst();
    }

    /**
     * Creates a parcel in the repository
     * @param parcel parcel
     * @return parcel
     */
    public Parcel create(Parcel parcel) {
        parcels.add(parcel);
        return parcel;
    }

    /**
     * Deletes a parcel in the repository
     * @param name name of parcel
     */
    public void deleteByName(String name) {
        parcels.removeIf(parcel -> parcel.getName().equals(name));
    }
}
