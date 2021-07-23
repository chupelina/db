package com.company;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;


public class Homework {
    private static final String CONNECTION_STRING =
            "jdbc:mysql://localhost:3306/";
    private static final String tableName = "minions_db";
    private Connection connection;

    public void setConnection(String user, String password) throws SQLException, ClassNotFoundException {
        Properties properties = new Properties();
        properties.setProperty("user", user);
        properties.setProperty("password", password);


        connection = DriverManager
                .getConnection(CONNECTION_STRING + tableName, properties);


    }

    public String getNameById(int entityId, String name) throws SQLException {
        String query = String.format("Select name from %s where id= ?", name);
        PreparedStatement statement = connection
                .prepareStatement(query);
        statement.setInt(1, entityId);
        ResultSet resultSet = statement.executeQuery();
        return resultSet.next() ? resultSet.getString("name") :
                null;

//        while (resultSet.next()) {
//            System.out.printf("%s%n",resultSet.getString("name"));
//        }
    }

    public int getIdByName(String name, String columnName) throws SQLException {
        String query = String.format("select id, name from %s\n" +
                        "where %s.name = '%s';"
                , columnName, columnName, name);
        PreparedStatement statement = connection
                .prepareStatement(query);
        ResultSet resultSet = statement.executeQuery();
        return resultSet.next() ? resultSet.getInt("id") :
                -1;

//        while (resultSet.next()) {
//            System.out.printf("%s%n",resultSet.getString("name"));
//        }
    }

    //2
    public void getVillainsNames() throws SQLException {
        String query = "select v.name, count(mv.minion_id) as count\n" +
                "from villains as v join minions_villains mv on v.id = mv.villain_id\n" +
                "group by  v.id\n" +
                "having  count>15\n" +
                "order by count desc;";
        PreparedStatement statement = connection.prepareStatement(query);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            System.out.printf("%s %s%n", resultSet.getString("name"),
                    resultSet.getString(2));
        }

    }

    //3
    public void getVillainMinions(String scanner) throws SQLException {
        int id = Integer.parseInt(scanner);
        String villainName = getNameById(id, "villains");
        if (villainName == null) {
            System.out.printf("No villain with ID %d exists in the database.%n", id);
            return;
        }
        System.out.println("Villain: " + villainName);

        String query = String.format("select m.name, m.age from villains join minions_villains mv on villains.id = mv.villain_id\n" +
                "join minions m on m.id = mv.minion_id\n" +
                "where villain_id=?;");
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, id);
        ResultSet resultSet = statement.executeQuery();
        int counter = 1;
        while (resultSet.next()) {
            System.out.println(counter + ". " + resultSet.getString("name")
                    + " " + resultSet.getString("age"));
            counter++;
        }
    }

    //4
    public void addMinion(String scan, String scan2) throws SQLException {
        String[] minion = scan.split(": ");
        String[] villain = scan2.split(": ");
        int villainID = getIdByName(villain[1], "villains");
        int townId = getIdByName(minion[1].split(" ")[2], "towns");
        int minionId = getIdByName(minion[1].split(" ")[0], "minions");
        if (villain[0].equals("Villain")) {
            if (villainID == -1) {
                villainID = addingVillain(villain[1]);
                System.out.printf("Villain %s was added to the database.%n", villain[1]);

            }
        }
        if (minion[0].equals("Minion")) {
            String[] current = minion[1].split(" ");
            if (townId == -1) {
                townId = addingTown(current[2]);
                System.out.printf("Town %s was added to the database.%n", current[2]);
            }
            if (minionId == -1) {
                minionId = addingMinion(current[0], Integer.parseInt(current[1]), townId);
            }

        }
        String query = "insert into minions_villains (minion_id, villain_id) values (?, ?);";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, minionId);
        statement.setInt(2, villainID);
        statement.executeUpdate();
        System.out.printf("Successfully added %s to be minion of %s.%n",
                getNameById(minionId, "minions"),
                getNameById(villainID, "villains")
        );

    }

    //4.1
    private int addingMinion(String s, int parseInt, int townId) throws SQLException {
        String query = "insert into minions ( name, age, town_id) values ( ? , ?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, s);
        preparedStatement.setInt(2, parseInt);
        preparedStatement.setInt(3, townId);
        preparedStatement.executeUpdate();
        return getIdByName(s, "minions");
    }

    //4.2
    private int addingTown(String s) throws SQLException {
        String query = "insert into towns ( name, country) values ( ?, null);";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, s);
        preparedStatement.executeUpdate();
        return getIdByName(s, "towns");
    }

    //4.3
    private int addingVillain(String s) throws SQLException {
        String query = "insert into villains ( name, evilness_factor) values ( ?, 'evil');";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, s);
        preparedStatement.executeUpdate();
        return getIdByName(s, "villain");
    }

    //5
    public void changesTowns(String country) throws SQLException {
        String toDo = String.format("update towns set name=upper(towns.name)\n" +
                "where towns.country = '%s';", country);
        PreparedStatement ps = connection.prepareStatement(toDo);
        ps.executeUpdate();
        ps = connection.prepareStatement(String.format("select towns.name from towns\n" +
                "where towns.country='%s';", country));
        ResultSet rs = ps.executeQuery();
        List<String> towns = new ArrayList<>();
        while (rs.next()) {
            towns.add(rs.getString("name"));
        }
        if (towns.isEmpty()) {
            System.out.println("No town names were affected.");
            return;
        }
        System.out.println(towns.size() + " town names were affected.");
        System.out.println(towns.toString());

    }

    //6
    public void removeVillain(String scan) throws SQLException {
        int villainId = Integer.parseInt(scan);
        String name = getNameById(villainId, "villains");
        if (name == null) {
            System.out.println("No such villain was found");
            return;
        }
        String second = String.format("delete from minions_villains as mv\n" +
                "where mv.villain_id=%d;", villainId);
        PreparedStatement pps = connection.prepareStatement(second);
        int n = pps.executeUpdate();
        String third = String.format("delete  from  villains where id = %d", villainId);
        PreparedStatement ppst = connection.prepareStatement(third);
        ppst.executeUpdate();

        System.out.println(name + " was deleted");
        System.out.println(n + " minions released");

    }

    //7
    public void printAllMinions() throws SQLException {
        String query = "select name from minions;";
        PreparedStatement ps = connection.prepareStatement(query);
        ResultSet rs = ps.executeQuery();
        List<String> minions = new ArrayList<>();
        while (rs.next()) {
            minions.add(rs.getString("name"));
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < minions.size() / 2; i++) {
            sb.append(minions.get(i));
            sb.append(System.lineSeparator());
            sb.append(minions.get(minions.size() - i - 1));
            sb.append(System.lineSeparator());
        }
        System.out.println(sb.toString().trim());
    }

    //8
    public void increaseAge(String scan) throws SQLException {
        List<Integer> minionsID = Arrays.stream(scan.split(" "))
                .map(Integer::parseInt).collect(Collectors.toList());
        for (int i = 0; i < minionsID.size(); i++) {
            String query = String.format("update minions set name=lower(name)\n" +
                    "where id = %d;", minionsID.get(i));
            PreparedStatement ps = connection.prepareStatement(query);
            ps.executeUpdate();
            query = String.format("update minions set age = age+1 \n" +
                    "where id = %d;", minionsID.get(i));
            ps = connection.prepareStatement(query);
            ps.executeUpdate();
        }
        StringBuilder sb = new StringBuilder();
        String printingQuery = String.format("select name, age from minions;");
        PreparedStatement ps = connection.prepareStatement(printingQuery);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            sb.append(rs.getString("name") + " " + rs.getString("age"));
            sb.append(System.lineSeparator());
        }
        System.out.println(sb.toString().trim());
    }

    //9
    public void procedure(String scan) throws SQLException {
        int id = Integer.parseInt(scan);
        String query = "call usp_get_older(?);";
        CallableStatement cs = connection.prepareCall(query);
        cs.setInt(1, id);
        cs.executeUpdate();

        query = "select * from minions\n" +
                "where id =? ;";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            System.out.println(rs.getString("name") + " " + rs.getString("age"));
        }

    }
}
