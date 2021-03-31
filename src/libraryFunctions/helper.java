package libraryFunctions;

//These functions are ones that can be used anywhere in the project. They are essentially utility funcitons.
import java.security.MessageDigest;

public class helper {

    public static String hashPassword(String password) {
        //This should hash the password (Use a message digest = https://www.tutorialspoint.com/java_cryptography/java_cryptography_message_digest.htm)

        try {
            //Creating the MessageDigest object  
            MessageDigest md = MessageDigest.getInstance("SHA-256");

            //Passing data to the created MessageDigest Object
            md.update(password.getBytes());

            //Compute the message digest
            byte[] digest = md.digest();

            //Converting the byte array in to HexString format
            StringBuffer hexString = new StringBuffer();

            for (int i = 0; i < digest.length; i++) {
                hexString.append(Integer.toHexString(0xFF & digest[i]));
            }
            return password;
        } catch (Exception e) {
            System.out.println("Error " + e);
        }
        
        return null;
    }

    public static boolean CompareHashed(String databasePassword, String userEnteredPassword) {
        return hashPassword(userEnteredPassword).equals(databasePassword);
    }

}
