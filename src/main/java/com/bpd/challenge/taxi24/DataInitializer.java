package com.bpd.challenge.taxi24;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.stereotype.Component;

import com.bpd.challenge.taxi24.models.Driver;
import com.bpd.challenge.taxi24.models.Passenger;
import com.bpd.challenge.taxi24.models.Vehicle;
import com.bpd.challenge.taxi24.repositories.DriverRepository;
import com.bpd.challenge.taxi24.repositories.PassengerRepository;
import com.bpd.challenge.taxi24.types.DriverStatusType;
import com.bpd.challenge.taxi24.types.PassengerStatusType;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class DataInitializer {

        @Autowired
        DriverRepository driverRepository;

        @Autowired
        PassengerRepository passengerRepository;

        public void initData() {
                try {

                        Driver d = driverRepository.findOneByName("Homero");
                        if (d == null) {
                                log.info("Creating Drivers...");
                                d = Driver.builder().name("Homero").status(DriverStatusType.ONLINE)
                                                .location(new GeoJsonPoint(new Point(Double.valueOf(-34.6105533051675),
                                                                Double.valueOf(-58.378213650055905))))
                                                .vehicle(Vehicle.builder().name("My car").registration("ABC123")
                                                                .isEnabled(true).brand("Ford").model("Fiesta")
                                                                .year("2020").build())
                                                .build();
                                driverRepository.save(d);

                                d = Driver.builder().name("March").status(DriverStatusType.ONLINE)
                                                .location(new GeoJsonPoint(
                                                                new Point(Double.valueOf(-34.608974917317944),
                                                                                Double.valueOf(-58.3733254246421))))
                                                .vehicle(Vehicle.builder().name("My car 2").registration("DBS234")
                                                                .isEnabled(true).brand("Ford").model("Ka").year("2019")
                                                                .build())
                                                .build();
                                driverRepository.save(d);

                                d = Driver.builder().name("Bart").status(DriverStatusType.ON_TRIP)
                                                .location(new GeoJsonPoint(new Point(Double.valueOf(-34.57748146308352),
                                                                Double.valueOf(-58.48172553256751))))
                                                .vehicle(Vehicle.builder().name("My car 3").registration("FGE344")
                                                                .isEnabled(true).brand("Peugeot").model("208")
                                                                .year("2022").build())
                                                .build();
                                driverRepository.save(d);

                                d = Driver.builder().name("Lisa").status(DriverStatusType.ONLINE)
                                                .location(new GeoJsonPoint(new Point(Double.valueOf(-34.61497378997174),
                                                                Double.valueOf(-58.37610591652356))))
                                                .vehicle(Vehicle.builder().name("My car 4").registration("VBG456")
                                                                .isEnabled(true).brand("FIAT").model("Uno").year("2010")
                                                                .build())
                                                .build();
                                driverRepository.save(d);

                                d = Driver.builder().name("Magui").status(DriverStatusType.READY)
                                                .location(new GeoJsonPoint(new Point(Double.valueOf(-34.61087516312523),
                                                                Double.valueOf(-58.3834677374779))))
                                                .vehicle(Vehicle.builder().name("My car 5").registration("GHJ789")
                                                                .isEnabled(true).brand("CITROEN").model("C4")
                                                                .year("2015").build())
                                                .build();
                                driverRepository.save(d);

                                log.info("Creating Passenger...");
                                Passenger p = Passenger.builder().name("Barnie").status(PassengerStatusType.READY)
                                                .build();
                                passengerRepository.save(p);
                                p = Passenger.builder().name("Moe").status(PassengerStatusType.READY).build();
                                passengerRepository.save(p);
                                p = Passenger.builder().name("Skinner").status(PassengerStatusType.READY)
                                                .build();
                                passengerRepository.save(p);

                        } else {
                                log.info("Database already has initial data");
                        }

                } catch (final Exception e) {
                        log.error("Exception while inserting mock data.", e);
                        throw e;
                }
        }

}
