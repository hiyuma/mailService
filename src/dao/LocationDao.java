package dao;

import java.util.List;

import domain.Location;

public interface LocationDao {

  List<Location> findAll() throws Exception;

  Location findById(Integer id) throws Exception;

  void insert(Location location) throws Exception;

  void update(Location location) throws Exception;

  void delete(Location location) throws Exception;

}
