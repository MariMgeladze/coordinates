package softlab.homework.coordinates.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
@AllArgsConstructor
@Table(name = "vehicle_locations")
public class ExcelInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private  double point1;

    private  double point2;

    private  String number;


    public ExcelInfo() {

    }
}
