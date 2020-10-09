/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachinespringmvc.dao;

import com.sg.vendingmachinespringmvc.model.Item;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author mirandabeamer
 */
public class VendingMachineDaoDbImpl implements VendingMachineDao {
    
    
    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    
    private static final String SQL_INSERT_ITEM
            ="insert into items(item_name, inventory, cost) "
            + "values(?, ?, ?)";

    private static final String SQL_SELECT_ITEM
            = "select * from items where menu_selection = ?";

    private static final String SQL_UPDATE_ITEM
            = "update items set inventory = ? where menu_selection = ?";

    private static final String SQL_SELECT_ALL_ITEMS
            = "select * from items";

    @Override
    public Item getItem(int menuSelection) {
        try {
            return jdbcTemplate.queryForObject(SQL_SELECT_ITEM, new ItemMapper(),
                    menuSelection);
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<Item> getAllItems() {
        return jdbcTemplate.query(SQL_SELECT_ALL_ITEMS, new ItemMapper());
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public Item updateInventory(Item item) {
        int inventory = item.getInventory() - 1;
        int menuSelection = item.getMenuSelection();
        jdbcTemplate.update(SQL_UPDATE_ITEM,
                inventory, menuSelection);
        try {
            item =  jdbcTemplate.queryForObject(SQL_SELECT_ITEM, new ItemMapper(),
                    menuSelection);
            return item;
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }

    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void loadItems() {

//
//        Item item2 = new Item();
//        item2.setItemName("Twix");
//        item2.setCost(cost);
//        item2.setInventory(10); 
//        jdbcTemplate.update(SQL_INSERT_ITEM, item2.getItemName(), item2.getInventory(), item2.getCost());
//        menuSelection = jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Integer.class);
//        item2.setMenuSelection(menuSelection);
//        //itemMap.put(menuSelection, item2);
//        
//        Item item3 = new Item();
//        item3.setItemName("Skittles");
//        item3.setCost(cost);
//        item3.setInventory(10); 
//        jdbcTemplate.update(SQL_INSERT_ITEM, item3.getItemName(), item3.getInventory(), item3.getCost());
//        menuSelection = jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Integer.class);
//        item3.setMenuSelection(menuSelection);
//        //itemMap.put(menuSelection, item3);
//        
//        Item item4 = new Item();
//        item4.setItemName("Cheetos");
//        cost = new BigDecimal(1.50).setScale(2);
//        item4.setCost(cost);
//        item4.setInventory(10); 
//        jdbcTemplate.update(SQL_INSERT_ITEM, item4.getItemName(), item4.getInventory(), item4.getCost());
//        menuSelection = jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Integer.class);
//        item4.setMenuSelection(menuSelection);
//        //itemMap.put(menuSelection, item4);
//        
//        Item item5 = new Item();
//        item5.setItemName("Doritos");
//        item5.setCost(cost);
//        item5.setInventory(10); 
//        jdbcTemplate.update(SQL_INSERT_ITEM, item5.getItemName(), item5.getInventory(), item5.getCost());
//        menuSelection = jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Integer.class);
//        item5.setMenuSelection(menuSelection);
//        //itemMap.put(menuSelection, item5);
//        
//        Item item6 = new Item();
//        item6.setItemName("Pretzels");
//        item6.setCost(cost);
//        item6.setInventory(10); 
//        jdbcTemplate.update(SQL_INSERT_ITEM, item6.getItemName(), item6.getInventory(), item6.getCost());
//        menuSelection = jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Integer.class);
//        item6.setMenuSelection(menuSelection);
//        //itemMap.put(menuSelection, item6);
//        
//        Item item7 = new Item();
//        item7.setItemName("Coke");
//        cost = new BigDecimal(1.75).setScale(2);
//        item7.setCost(cost);
//        item7.setInventory(10); 
//        jdbcTemplate.update(SQL_INSERT_ITEM, item7.getItemName(), item7.getInventory(), item7.getCost());
//        menuSelection = jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Integer.class);
//        item7.setMenuSelection(menuSelection);
//        //itemMap.put(menuSelection, item7);
//        
//        Item item8 = new Item();
//        item8.setItemName("Sprite");
//        item8.setCost(cost);
//        item8.setInventory(10); 
//        jdbcTemplate.update(SQL_INSERT_ITEM, item8.getItemName(), item8.getInventory(), item8.getCost());
//        menuSelection = jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Integer.class);
//        item8.setMenuSelection(menuSelection);
//        //itemMap.put(menuSelection, item8);
//        
//        Item item9 = new Item();
//        item9.setItemName("Powerade");
//        cost = new BigDecimal(2.00).setScale(2);
//        item9.setCost(cost);
//        item9.setInventory(10); 
//        jdbcTemplate.update(SQL_INSERT_ITEM, item9.getItemName(), item9.getInventory(), item9.getCost());
//        menuSelection = jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Integer.class);
//        item9.setMenuSelection(menuSelection);
//        //itemMap.put(menuSelection, item9);
    }

    private static final class ItemMapper implements RowMapper<Item> {

        @Override
        public Item mapRow(ResultSet rs, int i) throws SQLException {
            Item item = new Item();
            item.setMenuSelection(rs.getInt("menu_selection"));
            item.setItemName(rs.getString("item_name"));
            item.setCost(rs.getBigDecimal("cost"));
            item.setInventory(rs.getInt("inventory"));
            return item;
        }

    }

}
