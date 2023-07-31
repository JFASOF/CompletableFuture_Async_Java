import java.util.UUID;

public class IdGenerator {
    public static void main(String... args) {
        for (int i = 0; i < 5; i++) {
            System.out.println(generateUniqueId());
        }
       
    }
    public static int generateUniqueId(){
         UUID uuid = UUID.randomUUID();
        String str = ""+uuid;
        int uid = str.hashCode();
        String filterStr = ""+uid;
        str =filterStr.replaceAll("-", "");
        return Integer.valueOf(str);
    }
}
