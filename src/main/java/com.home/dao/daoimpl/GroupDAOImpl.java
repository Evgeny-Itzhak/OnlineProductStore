package com.home.dao.daoimpl;


import com.home.dao.GroupDAO;
import com.home.model.Group;
import com.home.util.DbUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class GroupDAOImpl implements GroupDAO {

    private Connection connection;

    public GroupDAOImpl() {
        connection = DbUtil.getConnection();
    }

    public List<Group> getGroups() {
        List<Group> groups = new ArrayList<Group>();
        String query = "SELECT GROUP_NAME,  t_group.GROUP_ID, count(*)-1 QUANTITY " +
                        "FROM t_group LEFT JOIN t_product ON t_group.GROUP_ID = t_product.GROUP_ID " +
                         "GROUP BY GROUP_NAME";
        try(Statement statement = connection.createStatement() ;
            ResultSet resultSet = statement.executeQuery(query)){
            while (resultSet.next()) {
                Group group = new Group();
                group.setGroupId(resultSet.getInt("GROUP_ID"));
                group.setGroupName(resultSet.getString("GROUP_NAME"));
                group.setQuantityOfProducts(resultSet.getInt("QUANTITY"));
                groups.add(group);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return groups;
    }
}
