package com.bpd.challenge.taxi24.repositories;

import java.util.List;

import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.bpd.challenge.taxi24.models.Driver;
import com.bpd.challenge.taxi24.types.DriverStatusType;

@Repository
public interface DriverRepository extends MongoRepository<Driver, String> {

    Driver findOneByName(String name);

    List<Driver> findByLocationNear(Point p, Distance d);

    List<Driver> findByStatusAndLocationNear(DriverStatusType status, Point p, Distance d);

    List<Driver> findFirst3ByStatusAndLocationNear(DriverStatusType status, Point p, Distance d);

    List<Driver> findByStatus(DriverStatusType status);

}
