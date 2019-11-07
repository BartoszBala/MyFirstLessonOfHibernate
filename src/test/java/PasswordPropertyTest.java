import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;

class PasswordPropertyTest {

@Test
    void isPasswordLengthCorrect() {
    //given
    String password01 = "RTS";
    String password02 ="rts";

    String password11 = "RTSWXYZ";
    String password12 ="RTSWXYZ";

    String password21 = "RTSWXYZ aa";
    String password22 ="RTSWXYZ aa";

    String password31 = "RTSWXYZaa";
    String password32 ="RTSWXYZaa";

    String password41 = "1RTSWXYZaa";
    String password42 ="1RTSWXYZaa";

    //when

    boolean isPasswordCorrect0 = PasswordProperty.isPasswordLengthCorrect(password01,password02);
    boolean isPasswordCorrect1 = PasswordProperty.isPasswordLengthCorrect(password11,password12);
    boolean isPasswordCorrect2 = PasswordProperty.isPasswordLengthCorrect(password21,password22);
    boolean isPasswordCorrect3 = PasswordProperty.isPasswordLengthCorrect(password31,password32);
    boolean isPasswordCorrect4 = PasswordProperty.isPasswordLengthCorrect(password41,password42);

    //then

    assertAll(

            ()-> Assertions.assertFalse(isPasswordCorrect0),
            ()-> Assertions.assertFalse(isPasswordCorrect1),
            ()-> Assertions.assertFalse(isPasswordCorrect2),
            ()-> Assertions.assertFalse(isPasswordCorrect3),
            ()-> Assertions.assertTrue(isPasswordCorrect4)
            );

    }
}