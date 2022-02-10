package baseAPI;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

public class SQLConnector {

    public static Connection connect = null;
    public static Statement statement = null;
    public static PreparedStatement ps = null;
    public static ResultSet resultSet = null;

    public static Properties loadProperties() throws IOException {
        Properties prop = new Properties();
        String path = System.getProperty("user.dir") + "/src/main/resources/secret.properties";
        InputStream ism = new FileInputStream(path);
        prop.load(ism);
        ism.close();
        return prop;
    }

    public static Connection connectToSqlDatabase() throws IOException, SQLException, ClassNotFoundException {
        Properties prop = loadProperties();
        String driverClass = prop.getProperty("MYSQLJDBC.driver");
        String url = prop.getProperty("MYSQLJDBC.url");
        String userName = prop.getProperty("MYSQLJDBC.userName");
        String password = prop.getProperty("MYSQLJDBC.password");
        Class.forName(driverClass);
        try {
            connect = DriverManager.getConnection(url, userName, password);
            System.out.println("Database is connected");

        } catch (Exception e) {
            System.out.println("COULD NOT CONNECT TO DB");
            e.printStackTrace();
        }
        return connect;
    }

    private void close() {
        try {
            if (resultSet != null) {
                resultSet.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (connect != null) {
                connect.close();
            }
        } catch (Exception e) {

        }
    }

    public List<String> getResultSetData(ResultSet resultSet2, String columnName) throws SQLException {
        List<String> dataList = new ArrayList<String>();
        while (resultSet.next()) {
            String itemName = resultSet.getString(columnName);
            dataList.add(itemName);
        }
        return dataList;
    }

    public void insertDataFromListToSqlTable(List<String> ArrayData, String tableName, String columnName) {
        try {
            connectToSqlDatabase();
            ps = connect.prepareStatement("DROP TABLE IF EXISTS `" + tableName + "`;");
            ps.executeUpdate();

            ps = connect.prepareStatement(
                    "CREATE TABLE `" + tableName + "` (`ID` int(11) NOT NULL AUTO_INCREMENT,`"+columnName+"` varchar(255) DEFAULT NULL,  PRIMARY KEY (`ID`) );");
            ps.executeUpdate();

            for (String n : ArrayData) {
                ps = connect.prepareStatement("INSERT INTO " + tableName + " ( " + columnName + " ) VALUES(?)");
                ps.setString(1, n);
                ps.executeUpdate();
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void insertDataFrom2ListsToSqlTable(List<String> ArrayData,List<String> ArrayData2, String tableName, String columnName,String columnName2) {
        try {
            connectToSqlDatabase();
            ps = connect.prepareStatement("DROP TABLE IF EXISTS `" + tableName + "`;");
            ps.executeUpdate();

            ps = connect.prepareStatement(
                    "CREATE TABLE `" + tableName + "` (`ID` int(11) NOT NULL AUTO_INCREMENT,`"+columnName+"` varchar(255) DEFAULT NULL,`"+columnName2+"` varchar(255) DEFAULT NULL,  PRIMARY KEY (`ID`) );");
            ps.executeUpdate();

            for (int n=0;n<ArrayData.size();n++) {
                ps = connect.prepareStatement("INSERT INTO " + tableName + " ( " + columnName + "," + columnName2 + " ) VALUES(?,?)");
                ps.setString(1, ArrayData.get(n));
                ps.setString(2,ArrayData2.get(n));
                ps.executeUpdate();
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void insertDataFromArrayToSqlTable(int[] ArrayData, String tableName, String columnName) {
        try {
            connectToSqlDatabase();
            ps = connect.prepareStatement("DROP TABLE IF EXISTS `" + tableName + "`;");
            ps.executeUpdate();

            ps = connect.prepareStatement(
                    "CREATE TABLE `" + tableName + "` (`ID` int(11) NOT NULL AUTO_INCREMENT,`SortingNumbers` bigint(20) DEFAULT NULL,  PRIMARY KEY (`ID`) );");
            ps.executeUpdate();

            for (int n = 0; n < ArrayData.length; n++) {
                ps = connect.prepareStatement("INSERT INTO " + tableName + " ( " + columnName + " ) VALUES(?)");
                ps.setInt(1, ArrayData[n]);
                ps.executeUpdate();
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }



    public void insertDataFromStringToSqlTable(String ArrayData, String tableName, String columnName) {
        try {
            connectToSqlDatabase();
            ps = connect.prepareStatement("INSERT INTO " + tableName + " ( " + columnName + " ) VALUES(?)");
            ps.setString(1, ArrayData);
            ps.executeUpdate();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public List<String> directDatabaseQueryExecute(String passQuery, String dataColumn) throws Exception {
        List<String> data = new ArrayList<String>();

        try {
            connectToSqlDatabase();
            statement = connect.createStatement();
            resultSet = statement.executeQuery(passQuery);
            data = getResultSetData(resultSet, dataColumn);
        } catch (ClassNotFoundException e) {
            throw e;
        } finally {
            close();
        }
        return data;
    }

    public void insertDataFromHashMapToSqlTable(HashMap<Object, Object> hashMap, String tableName) {
        try {
            connectToSqlDatabase();
            ps = connect.prepareStatement("DROP TABLE IF EXISTS " + tableName + ";");
            ps.executeUpdate();

            ps = connect.prepareStatement("CREATE TABLE " + tableName + " (`keys` VARCHAR(45) NOT NULL AUTO_INCREMENT, `values` VARCHAR(45) NULL);");
            ps.executeUpdate();

            for (Object obj : hashMap.keySet()) {

                ps = connect.prepareStatement("INSERT INTO " + tableName + " (`keys`) VALUES(?)");
                ps.setObject(1, obj);
                ps.executeUpdate();

                ps = connect.prepareStatement("INSERT INTO " + tableName + " (`values`) VALUES(?)");
                ps.setObject(1, hashMap.get(obj));
                ps.executeUpdate();
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    public void insertProfileToSqlTable(String tableName, String columnName1, String columnName2) {
        try {
            connectToSqlDatabase();
            ps = connect.prepareStatement("INSERT INTO " + tableName + " ( " + columnName1 + "," + columnName2 + " ) VALUES(?,?)");
            ps.setString(1, "Ankita Sing");
            ps.setInt(2, 3590);
            ps.executeUpdate();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
