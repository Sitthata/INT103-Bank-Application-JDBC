import javax.swing.plaf.basic.BasicInternalFrameTitlePane;
import java.sql.*;

public class Bank {
    String name;

    public Bank(String name) {
        this.name = name;
    }

    public Bank() {
    }

    public String getName() {
        return name;
    }

    public void listAccount() {
        String sql = "SELECT * FROM bank_application.banking ORDER BY account_number ASC LIMIT 10";

        try (Connection connection = BankConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            if (connection == null) {
                System.out.println("Can't Connect to Database");
                return;
            }

            ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
            StringBuilder columnNames = new StringBuilder();

            // Print Column Names
            for (int i = 1; i <= resultSetMetaData.getColumnCount(); i++) {
                columnNames.append(resultSetMetaData.getColumnName(i)).append(" ");
            }
            System.out.println(columnNames.toString());

            while (resultSet.next()) {
                StringBuilder rowValues = new StringBuilder();
                for (int i = 1; i <= 3; i++) {
                    rowValues.append(resultSet.getString(i)).append(" ");
                }
                System.out.println(rowValues);
            }
            System.out.println("---------------------------");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void openAccount(Account account) {
        String sql = "INSERT INTO bank_application.banking (account_number, name, balance) VALUES (?, ?, ?)";

        try (Connection connection = BankConnection.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, account.getNumber());
            preparedStatement.setString(2, account.getName());
            preparedStatement.setDouble(3, account.getBalance());
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void closeAccount(long accountNumber) {
        String sql = "DELETE FROM bank_application.banking WHERE account_number = ?";

        try (Connection connection = BankConnection.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, accountNumber);
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void depositMoney(long number, double amount) {
        String sql = "UPDATE bank_application.banking SET balance = ? WHERE account_number = ?";
        Account account = getAccount(number);
        account.deposit(amount);

        try (Connection connection = BankConnection.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setDouble(1, account.getBalance());
            preparedStatement.setLong(2, number);
            System.out.println("Balance: " + account.getBalance());
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void withdrawMoney(long number, double amount) {
        String sql = "UPDATE bank_application.banking SET balance = ? WHERE account_number = ?";
        Account account = getAccount(number);
        account.withdraw(amount);

        try(Connection connection = BankConnection.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setDouble(1, account.getBalance());
            preparedStatement.setLong(2, number);
            System.out.println("Balance: " + account.getBalance());
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Account getAccount(long number) {
        String sql = "SELECT * FROM bank_application.banking WHERE account_number = ?";

        try(Connection connection = BankConnection.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, number);
            ResultSet resultSet = preparedStatement.executeQuery();

            // Return new Account object
            while(resultSet.next()) {
                return new Account(resultSet.getLong(1), resultSet.getString(2), resultSet.getDouble(3));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean isAccountExist(long number) {
        String sql = "SELECT * FROM bank_application.banking WHERE account_number = ?";
        try(Connection connection = BankConnection.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, number);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

}



