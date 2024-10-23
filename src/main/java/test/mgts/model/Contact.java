package test.mgts.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Contact implements Serializable {
    private static final long serialVersionUID = 1L;
    private String firstName;
    private String secondName;
    private String passNumber;

    //Конструктор для копирования объекта.
    public Contact(Contact c) {
        this.firstName = c.getFirstName();
        this.secondName = c.getSecondName();
        this.passNumber = c.getPassNumber();
    }

    @Override
    public String toString() {
        return "Имя '" + firstName + '\'' +
                ", Фамилия '" + secondName + '\'' +
                ", Номер пропуска '" + passNumber + '\'';
    }
}
