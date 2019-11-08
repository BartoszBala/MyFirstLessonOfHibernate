import PasswordKeep.PasswordUtils;

public class MainTest {

    public static void main(String[] args) {
        System.out.println(PasswordUtils.decryptPassword(PasswordUtils.encryptPassword("tomektomek1")));
        System.out.println(PasswordUtils.decryptPassword(PasswordUtils.encryptPassword("123mićhął")));

        System.out.println(PasswordUtils.encryptPassword("masseratt1isThe_Best"));




    }
}
