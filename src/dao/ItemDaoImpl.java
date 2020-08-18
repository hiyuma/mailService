package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import domain.Item;

public class ItemDaoImpl implements ItemDao {

  private DataSource ds;

  public ItemDaoImpl(DataSource ds) {
    this.ds = ds;
  }

  @Override
  public List<Item> findAll() throws Exception {
    List<Item> itemList = new ArrayList<>();
    try (Connection con = ds.getConnection()) {
      String sql = "SELECT *,locations.name AS location_name FROM items JOIN locations ON items.location_id = locations.id ORDER BY items.id";
      PreparedStatement stmt = con.prepareStatement(sql);
      ResultSet rs = stmt.executeQuery();
      while (rs.next()) {
        itemList.add(mapToItem(rs));
      }
    } catch (Exception e) {
      throw e;
    }
    return itemList;
  }

  @Override
  public Item findById(Integer id) throws Exception {
    Item item = null;
    try (Connection con = ds.getConnection()) {
      String sql = "SELECT *,locations.name AS location_name FROM items JOIN locations ON items.location_id = locations.id WHERE items.id = ?";
      PreparedStatement stmt = con.prepareStatement(sql);
      stmt.setInt(1, id);
      ResultSet rs = stmt.executeQuery();
      if (rs.next()) {
        item = mapToItem(rs);
      }
    } catch (Exception e) {
      throw e;
    }
    return item;
  }

  @Override
  public void insert(Item item) throws Exception {
    try (Connection con = ds.getConnection()) {
      String sql = "INSERT INTO items (name, amount, location_id, note, registered) VALUES (?, ?, ?, ?, NOW())";
      PreparedStatement stmt = con.prepareStatement(sql);
      stmt.setString(1, item.getName());
      stmt.setObject(2, item.getAmount(), Types.INTEGER);
      stmt.setObject(3, item.getLocationId(), Types.INTEGER);
      stmt.setString(4, item.getNote());
      stmt.executeUpdate();
    } catch (Exception e) {
      throw e;
    }
  }

  @Override
  public void update(Item item) throws Exception {
    try (Connection con = ds.getConnection()) {
      String sql = "UPDATE items SET name=?, amount=?, location_id=?, note=? WHERE id=?";
      PreparedStatement stmt = con.prepareStatement(sql);
      stmt.setString(1, item.getName());
      stmt.setObject(2, item.getAmount(), Types.INTEGER);
      stmt.setObject(3, item.getLocationId(), Types.INTEGER);
      stmt.setString(4, item.getNote());
      stmt.setInt(5, item.getId());
      stmt.executeUpdate();
    } catch (Exception e) {
      throw e;
    }
  }

  @Override
  public void delete(Item item) throws Exception {
    int id = item.getId();
    try (Connection con = ds.getConnection()) {
      String sql = "DELETE FROM items WHERE id=?";
      PreparedStatement stmt = con.prepareStatement(sql);
      stmt.setInt(1, id);
      stmt.executeUpdate();
    } catch (Exception e) {
      throw e;
    }
  }

  protected Item mapToItem(ResultSet rs) throws Exception {
    Item item = new Item();
    item.setId((Integer) rs.getObject("id"));
    item.setName(rs.getString("name"));
    item.setAmount((Integer) rs.getObject("amount"));
    item.setLocationId((Integer) rs.getObject("location_id"));
    item.setNote(rs.getString("note"));
    item.setRegistered(rs.getTimestamp("registered"));
    item.setUpdated(rs.getTimestamp("updated"));
    item.setLocationName(rs.getString("location_name"));
    return item;
  }

}
