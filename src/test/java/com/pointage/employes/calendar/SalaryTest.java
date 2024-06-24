import com.pointage.employes.model.Category;
import com.pointage.employes.model.Employe;
import com.pointage.employes.model.Pointage;
import com.pointage.employes.model.calendar.Calendars;
import com.pointage.employes.model.enums.CategoryName;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static com.pointage.employes.operation.Salary.getDayWorkerSalary;
import static com.pointage.employes.operation.Salary.getNightWorkerSalary;

public class SalaryTest {

    private List<Pointage> pointages;
    private Calendars calendars;

    @Before
    public void setUp() {
        // Initialisation des données de pointage
        pointages = new ArrayList<>();
        // Ajout des pointages pour Rakoto (travail de jour)
        pointages.add(new Pointage(LocalDate.of(2024, 6, 1), 8, null));
        pointages.add(new Pointage(LocalDate.of(2024, 6, 2), 8, null));
        // Ajout des pointages pour Rabe (travail de nuit)
        pointages.add(new Pointage(LocalDate.of(2024, 6, 1), 8, null, true));
        pointages.add(new Pointage(LocalDate.of(2024, 6, 2), 8, null, true));

        // Initialisation des jours fériés
        List<LocalDate> holidaysList = new ArrayList<>();
        holidaysList.add(LocalDate.of(2024, 6, 10)); // Exemple de jour férié le 10 juin 2024

        // Création de l'objet Calendars avec les jours fériés
        calendars = new Calendars(null, holidaysList);
    }

    @Test
    public void testGetDayAndNightWorkerSalary() {
        // Création des employés Rakoto et Rabe avec leurs catégories
        Employe rakoto = new Employe("Rakoto", "R", "001",
                LocalDate.of(1980, 1, 1), LocalDate.of(2000, 1, 1),
                LocalDate.of(2025, 12, 31), 100000.0,
                new Category(CategoryName.caretaker, 8 * 30, 100000.0, 0.0));

        Employe rabe = new Employe("Rabe", "R", "002",
                LocalDate.of(1985, 1, 1), LocalDate.of(2005, 1, 1),
                LocalDate.of(2025, 12, 31), 120000.0,
                new Category(CategoryName.caretaker, 0, 120000.0, 0.0));

        // Calcul du salaire de jour pour Rakoto
        double rakotoDaySalary = getDayWorkerSalary(calendars, rakoto, pointages);
        System.out.println("Salaire de jour pour Rakoto : " + rakotoDaySalary);

        // Calcul du salaire de nuit pour Rabe
        double rabeNightSalary = getNightWorkerSalary(calendars, rabe, pointages);
        System.out.println("Salaire de nuit pour Rabe : " + rabeNightSalary);
    }
}