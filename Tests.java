import java.lang.Integer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
public class Tests
{
    @Test
    public void testDecodeEmptyString() {
        Throwable exception = Assertions.assertThrows(NumberFormatException.class, () -> {
            Integer.decode("");
        });
        Assertions.assertEquals("Zero length string",exception.getMessage());
    }

    @Test
    public void testDecodeInvalidFormat() {
        Assertions.assertThrows(NumberFormatException.class, () -> {
            Integer.decode("0G");
        });
        Assertions.assertThrows(NumberFormatException.class, () -> {
            Integer.decode("##");
        });
        Assertions.assertThrows(NumberFormatException.class, () -> {
            Integer.decode("two");
        });
        Assertions.assertThrows(NumberFormatException.class, () -> {
            Integer.decode("09");
        });
        Assertions.assertThrows(NumberFormatException.class, () -> {
            Integer.decode("А");
        });
        Assertions.assertThrows(NumberFormatException.class, () -> {
            Integer.decode("0xG");
        });
    }

    @Test
    public void testDecode_Sign_Character_In_Wrong_Position() {
        Assertions.assertThrows(NumberFormatException.class, () -> {
            Integer.decode("0X1+");
        });
        Assertions.assertThrows(NumberFormatException.class, () -> {
            Integer.decode("0X1-");
        });
        Assertions.assertThrows(NumberFormatException.class, () -> {
            Integer.decode("01+");
        });
        Assertions.assertThrows(NumberFormatException.class, () -> {
            Integer.decode("01-");
        });
        Assertions.assertThrows(NumberFormatException.class, () -> {
            Integer.decode("1+");
        });
        Assertions.assertThrows(NumberFormatException.class, () -> {
            Integer.decode("1-");
        });
    }

    @Test
    public void testDecodeDecimal() {
        Assertions.assertEquals(10,Integer.decode("10"));
        Assertions.assertEquals(-10,Integer.decode("-10"));
        Assertions.assertEquals(10,Integer.decode("+10"));
    }

    @Test
    public void testDecodeHex() {
        Assertions.assertEquals(16,Integer.decode("0x10"));
        Assertions.assertEquals(-16,Integer.decode("-0x10"));
        Assertions.assertEquals(16,Integer.decode("+0x10"));
        Assertions.assertEquals(16,Integer.decode("#10"));
        Assertions.assertEquals(15,Integer.decode("0xf"));
        Assertions.assertEquals(-15,Integer.decode("-0xf"));
        Assertions.assertEquals(15,Integer.decode("+0xf"));
        Assertions.assertEquals(15,Integer.decode("#f"));
    }
    @Test
    public void testDecodeOctal()
    {
        Assertions.assertEquals(8,Integer.decode("+010"));
        Assertions.assertEquals(-8,Integer.decode("-010"));
    }

    @Test
    public void testDecode_number_is_too_high() {
        Assertions.assertThrows(NumberFormatException.class, () -> {
            Integer.decode("0x80000000");
        });
        Assertions.assertThrows(NumberFormatException.class, () -> {
            Integer.decode("2147483648");
        });
        Assertions.assertThrows(NumberFormatException.class, () -> {
            Integer.decode("020000000000");
        });
    }

    @Test
    public void testDecode_number_is_too_small() {
        Assertions.assertThrows(NumberFormatException.class, () -> {
            Integer.decode("-0x80000001");
        });
        Assertions.assertThrows(NumberFormatException.class, () -> {
            Integer.decode("-2147483649");
        });
        Assertions.assertThrows(NumberFormatException.class, () -> {
            Integer.decode("-020000000001");
        });
    }
}
