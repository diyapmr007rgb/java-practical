import java.lang.annotation.*;
import java.lang.reflect.Field;
import java.util.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@interface NotBlank {
}

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@interface MaxLength {
    int value();
}

class SignupForm {

    @NotBlank
    @MaxLength(20)
    String username;

    @NotBlank
    @MaxLength(30)
    String email;

    SignupForm(String username, String email) {
        this.username = username;
        this.email = email;
    }
}

class Validator {

    static List<String> validate(Object obj) {

        List<String> errors = new ArrayList<>();

        Field[] fields = obj.getClass().getDeclaredFields();

        for (Field field : fields) {

            field.setAccessible(true);

            try {
                Object value = field.get(obj);

                // Check @NotBlank
                if (field.isAnnotationPresent(NotBlank.class)) {

                    if (value == null || value.toString().trim().isEmpty()) {
                        errors.add(field.getName() + " cannot be blank");
                    }
                }

                // Check @MaxLength
                if (field.isAnnotationPresent(MaxLength.class)) {

                    MaxLength annotation =
                            field.getAnnotation(MaxLength.class);

                    int max = annotation.value();

                    if (value != null && value.toString().length() > max) {
                        errors.add(field.getName()
                                + " must be at most " + max + " characters");
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return errors;
    }
}

public class Main {

    public static void main(String[] args) {

        SignupForm form =
                new SignupForm("", "abcdefghijklmnopqrstuvwxyz12345");

        List<String> errors = Validator.validate(form);

        for (String error : errors) {
            System.out.println(error);
        }
    }
}