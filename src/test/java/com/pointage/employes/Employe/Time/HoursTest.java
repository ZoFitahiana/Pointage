package com.pointage.employes.Employe.Time;

import com.pointage.employes.model.Category;
import com.pointage.employes.model.Employe;
import com.pointage.employes.model.Pointage;
import com.pointage.employes.model.enums.CategoryName;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import static com.pointage.employes.operation.time.Hours.calculateTotalHours;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class HoursTest {
    Category category = new Category(CategoryName.caretaker);
    Employe rabe = new Employe("Rabe", "Rabe", "RA002",new Date(),new Date(),new Date(), 100_000.00,category);
    Employe rakoto = new Employe("Rakoto", "Rakoto", "RA001",new Date(),new Date(),new Date(), 100_000.00,category);


    @Test
    public  void get_total_hours_RAKOTO(){
        List<Pointage> pointagesRakoto = new ArrayList<Pointage>();
         for (int i = 0; i < 42; i++) {
            pointagesRakoto.add(new Pointage(new Date(),10,rakoto,false));
        }
        int totalHoursWork = calculateTotalHours(pointagesRakoto);
        assertEquals(420,totalHoursWork);
    }
    @Test
    public  void get_total_hours_Rabe(){
        List<Pointage> pointagesRabe= new ArrayList<Pointage>();
         for (int i = 0; i < 42; i++) {
             pointagesRabe.add(new Pointage(new Date(),14,rabe,true));
        }
        int totalHoursWork = calculateTotalHours(pointagesRabe);
        assertEquals(588,totalHoursWork);
    }


}
