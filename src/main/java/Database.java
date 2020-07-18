import org.apache.commons.dbcp.BasicDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Database {

    private BasicDataSource dataSource;

    public Database(String databaseName) {
//        dataSource = new BasicDataSource();
//        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
//        dataSource.setUsername("root");
//        dataSource.setPassword("");
//        dataSource.setUrl("jdbc:mysql://localhost.net:3306/" + databaseName + "?useUnicode=yes&characterEncoding=UTF-8");
//        dataSource.setValidationQuery("SELECT 1");
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUsername("b394f371a160e8");
        dataSource.setPassword("6b2c6686");
        dataSource.setUrl("jdbc:mysql://eu-cdbr-west-03.cleardb.net:3306/" + databaseName + "?useUnicode=yes&characterEncoding=UTF-8");
        dataSource.setValidationQuery("SELECT 1");
    }

    public void registerRecipient(Recipient recipient) {

        String query = "INSERT INTO `gavejai`(`password`, `email`, `company_name`, " +
                "`ac_person`, `phone`, `street`, `house_nr`, `city`, `longitude`, `latitude`) " +
                "VALUES (?,?,?,?,?,?,?,?,?,?)";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, recipient.getPassword());
            statement.setString(2, recipient.getEmail());
            statement.setString(3, recipient.getCompany_name());
            statement.setString(4, recipient.getAc_person());
            statement.setString(5, recipient.getPhone());
            statement.setString(6, recipient.getStreet());
            statement.setInt(7, recipient.getHouse_nr());
            statement.setString(8, recipient.getCity());
            statement.setDouble(9, recipient.getLongitude());
            statement.setDouble(10, recipient.getLatitude());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Recipient getRecipient(String email, String password) {

        String query = "SELECT * FROM gavejai WHERE email = ? AND password = ?";
        Recipient recipient = new Recipient();

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, email);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                recipient.setId(resultSet.getInt("id"));
                System.out.println(resultSet.getInt("id"));
                recipient.setEmail(resultSet.getString("email"));
                recipient.setCompany_name(resultSet.getString("company_name"));
                recipient.setAc_person(resultSet.getString("ac_person"));
                recipient.setPhone(resultSet.getString("phone"));
                recipient.setStreet(resultSet.getString("street"));
                recipient.setHouse_nr(resultSet.getInt("house_nr"));
                recipient.setCity(resultSet.getString("city"));
                recipient.setLongitude(resultSet.getDouble("longitude"));
                recipient.setLatitude(resultSet.getDouble("latitude"));
                recipient.setCaps(resultSet.getInt("caps"));
                recipient.setGloves(resultSet.getInt("gloves"));
                recipient.setGoggles(resultSet.getInt("goggles"));
                recipient.setMasks(resultSet.getInt("masks"));
                recipient.setShoe_covers(resultSet.getInt("shoe_covers"));
                recipient.setSuits(resultSet.getInt("suits"));
                recipient.setPriority(resultSet.getInt("priority"));
            }
            return recipient;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void deleteRecipient(int id) {
        String query = "DELETE FROM gavejai WHERE id=?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //Sveiku reiksmiu atnaujinimas
    public void updateRecipient(int id, String field, int value) {
        String query = "UPDATE gavejai SET " + field + " =? WHERE id=?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, value);
            statement.setInt(2, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //Skaiciu su kableliu reiksmiu atnaujinimas
    public void updateRecipient(int id, String field, double value) {
        String query = "UPDATE gavejai SET " + field + " =? WHERE id=?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setDouble(1, value);
            statement.setInt(2, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //Tekstiniu reiksmiu atnaujinimas
    public void updateRecipient(int id, String field, String value) {
        String query = "UPDATE gavejai SET " + field + " =? WHERE id=?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, value);
            statement.setInt(2, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //Gauti betkokia reiksme su tekstu
    public String getString(String field, String email) {

        if (field.equals("password")) {
            return null;

        } else {
            String query = "SELECT " + field + " FROM gavejai WHERE email = '" + email + "'";
            try (Connection connection = dataSource.getConnection();
                 PreparedStatement statement = connection.prepareStatement(query);
                 ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getString(field);
                }
                return null;
            } catch (SQLException e) {
                e.printStackTrace();
                return null;
            }
        }
    }
    //Gauti betkokia reiksme su tekstu
    public int getInt(String field, String email) {

        if (field.equals("password")) {
            return 0;

        } else {
            String query = "SELECT " + field + " FROM gavejai WHERE email = '" + email + "'";
            try (Connection connection = dataSource.getConnection();
                 PreparedStatement statement = connection.prepareStatement(query);
                 ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt(field);
                }
                return 0;
            } catch (SQLException e) {
                e.printStackTrace();
                return 0;
            }
        }
    }
    //Gauti betkokia reiksme su tekstu
    public double getDouble(String field, String email) {

        if (field.equals("password")) {
            return 0;

        } else {
            String query = "SELECT " + field + " FROM gavejai WHERE email = '" + email + "'";
            try (Connection connection = dataSource.getConnection();
                 PreparedStatement statement = connection.prepareStatement(query);
                 ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    double value = resultSet.getDouble(field);
                    return value;
                }
                return 0;
            } catch (SQLException e) {
                e.printStackTrace();
                return 0;
            }
        }
    }

    //Zemelapio tasku sarasas
    public ArrayList<Recipient> getNodes() {

        String query = "SELECT id, company_name, latitude, longitude, priority FROM gavejai";
        ArrayList<Recipient> nodeList = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Recipient node = new Recipient();
                node.setId(resultSet.getInt("id"));
                node.setCompany_name(resultSet.getString("company_name"));
                node.setLatitude(resultSet.getDouble("latitude"));
                node.setLongitude(resultSet.getDouble("longitude"));
                node.setPriority(resultSet.getInt("priority"));
                nodeList.add(node);
            }
            return nodeList;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    //Informacija statistikos langui
    public Statistic getStatistic() {
        String query = "SELECT SUM(caps), SUM(gloves), SUM(goggles), SUM(masks), SUM(shoe_covers), SUM(suits)  FROM gavejai";
        Statistic list = new Statistic();

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                list.setCaps(resultSet.getInt("SUM(caps)"));
                list.setGloves(resultSet.getInt("SUM(gloves)"));
                list.setGoggles(resultSet.getInt("SUM(goggles)"));
                list.setMasks(resultSet.getInt("SUM(masks)"));
                list.setShoe_covers(resultSet.getInt("SUM(shoe_covers)"));
                list.setSuits(resultSet.getInt("SUM(suits)"));
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    //Istaigu sarasas su adresu
    public ArrayList<Company> getCompanies() {

        String query = "SELECT id, email, company_name, ac_person, phone, street, house_nr, " +
                "city, caps, gloves, goggles, masks, shoe_covers, suits, priority  FROM gavejai;";
        ArrayList<Company> companiesList = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Company company = new Company();
                company.setId(resultSet.getInt("id"));
                company.setEmail(resultSet.getString("email"));
                company.setCompany_name(resultSet.getString("company_name"));
                company.setAc_person(resultSet.getString("ac_person"));
                company.setPhone(resultSet.getString("phone"));
                company.setStreet(resultSet.getString("street"));
                company.setHouse_nr(resultSet.getInt("house_nr"));
                company.setCity(resultSet.getString("city"));
                company.setCaps(resultSet.getInt("caps"));
                company.setGloves(resultSet.getInt("gloves"));
                company.setGoggles(resultSet.getInt("goggles"));
                company.setMasks(resultSet.getInt("masks"));
                company.setShoe_covers(resultSet.getInt("shoe_covers"));
                company.setSuits(resultSet.getInt("suits"));
                company.setPriority(resultSet.getInt("priority"));
                companiesList.add(company);
            }
            return companiesList;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
