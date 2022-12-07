package Class;

import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;


import java.sql.*;

public class passwordHash {

    public passwordHash() {
    }

    /*
        Hashes a password put in parameters and sends it to the database
         */
    public void hash(String password, String username) {
        // salt 32 bytes
        // Hash length 64 bytes
        Argon2 argon2 = Argon2Factory.create(
                Argon2Factory.Argon2Types.ARGON2id,
                16,
                32);
        String hash = argon2.hash(3, // Number of iterations
                64 * 1024, // 64mb
                1, // how many parallel threads to use
                password);

        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost/ap-clefs", "root", "")){
            ResultSet results;
            String sql = "INSERT INTO `comptes`(`nomUtilisateur`, `motDePasse`) VALUES (?,?)";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, username);
            stmt.setString(2, password);
            results = stmt.executeQuery();
        } catch (SQLException e){
            e.printStackTrace();
        }

        System.out.println("Hash + salt of the password: " + hash);
        System.out.println("Password verification success: " + argon2.verify(hash, password));
    }



}
