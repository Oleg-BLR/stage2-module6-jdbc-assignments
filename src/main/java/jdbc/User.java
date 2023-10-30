package jdbc;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/*
@Getter and @Setter: These annotations are from the Lombok library and generate getter and setter methods for the class fields at compile time. This reduces boilerplate code and makes the class more concise.

@AllArgsConstructor and @NoArgsConstructor: These annotations are also from the Lombok library and generate constructors for the class.
@AllArgsConstructor generates a constructor that takes all fields as arguments, while @NoArgsConstructor generates a constructor with no arguments. This reduces boilerplate code and makes the class more concise.

@Builder: This annotation is also from the Lombok library and generates a builder pattern for the class. This allows for more concise and readable code when creating instances of the class with many fields. The builder pattern also allows for optional fields and validation of field values.
*/
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {
    private Long id;
    private String firstName;
    private String lastName;
    private int age;
}
