package com.bpd.challenge.taxi24.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Metrics;
import org.springframework.data.geo.Point;
import org.springframework.stereotype.Service;

import com.bpd.challenge.taxi24.models.Driver;
import com.bpd.challenge.taxi24.repositories.DriverRepository;
import com.bpd.challenge.taxi24.types.DriverStatusType;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class DriverService {

    @Autowired
    private DriverRepository repository;

    public Page<Driver> findAll(Pageable page) {
        return repository.findAll(page);
    }

    public List<Driver> findOnlineAround(double lon, double lat, double distance) {
        log.info("Looking for drivers around ({},{}) withing {} kilometers", lon, lat, distance);
        Point p = new Point(Double.valueOf(lon), Double.valueOf(lat));
        Distance d = new Distance(distance, Metrics.KILOMETERS);
        return repository.findByStatusAndLocationNear(DriverStatusType.ONLINE, p, d);
    }

    public List<Driver> findFirst3OnlineLocationNear(double lon, double lat) {
        log.info("Looking for 3 drivers around available ({},{})", lon, lat);
        Point p = new Point(Double.valueOf(lon), Double.valueOf(lat));
        Distance d = new Distance(1000, Metrics.KILOMETERS);
        return repository.findFirst3ByStatusAndLocationNear(DriverStatusType.ONLINE, p, d);
    }

    public List<Driver> findByStatus(DriverStatusType status) {
        return repository.findByStatus(status);
    }

    public Optional<Driver> findById(String id) {
        return repository.findById(id);
    }

}
