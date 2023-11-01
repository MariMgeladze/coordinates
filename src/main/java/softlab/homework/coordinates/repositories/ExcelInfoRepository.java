package softlab.homework.coordinates.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import softlab.homework.coordinates.entities.ExcelInfo;

public interface ExcelInfoRepository extends JpaRepository <ExcelInfo, Integer> {
}
