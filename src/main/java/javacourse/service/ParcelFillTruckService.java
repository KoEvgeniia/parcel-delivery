package javacourse.service;

import javacourse.domain.Parcel;
import javacourse.domain.Truck;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
public class ParcelFillTruckService {
    public List<Truck> fillTruck(List<Parcel> parcels) {
        return parcels.stream().map(parcel -> Truck.builder()
                .Level1(String.format("%-6s", parcel.Level1))
                .Level2(String.format("%-6s", parcel.Level2))
                .Level3(String.format("%-6s", parcel.Level3))
                .Level4(String.format("%-6s", ""))
                .Level5(String.format("%-6s", ""))
                .Level6(String.format("%-6s", ""))
                .build()).toList();
    }

    public void showTrucks(List<Truck> trucks) {
        trucks.forEach(truck -> System.out.println(truck.toString()));
    }
}
