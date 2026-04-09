package javacourse.service;

import javacourse.domain.Parcel;
import javacourse.exception.ParcelAlreadyExistsException;
import javacourse.exception.ParcelNotFoundException;
import javacourse.repository.ParcelRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;

/**
 * Handles parcels in the repository
 */
@RequiredArgsConstructor
public class ParcelHandler {
    private final ParcelRepository parcelRepository;

    /**
     * Finds all parcels in the repository
     * @return list of parcels
     */
    public List<Parcel> findAll() {
        return parcelRepository.findAll();
    }

    /**
     * Finds a parcel in the repository
     * @param name name of parcel
     * @return parcel
     */
    public Parcel findByName(String name) {
        return parcelRepository.findByName(name)
                .orElseThrow(() -> new ParcelNotFoundException(name));
    }

    /**
     * Creates a parcel in the repository
     * @param parcel parcel
     * @return parcel
     */
    public Parcel create(Parcel parcel) {
        if (parcelRepository.findByName(parcel.getName()).isPresent())
            throw new ParcelAlreadyExistsException(parcel.getName());
        return parcelRepository.create(parcel);
    }

    /**
     * Deletes a parcel in the repository
     * @param name name of parcel
     */
    public void deleteByName(String name) {
        if (parcelRepository.findByName(name).isPresent()) {
            parcelRepository.deleteByName(name);
        } else {
            throw new ParcelNotFoundException(name);
        }
    }
}
