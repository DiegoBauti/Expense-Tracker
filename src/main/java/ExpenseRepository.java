import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ExpenseRepository {

    Path path= Path.of("expense.csv");
    private int nextId = 0;

    public ExpenseRepository(){
        if (!Files.exists(path)){
            try {
                Files.createFile(path);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public List<Expense> loadCsv(){
        List<Expense> list=new ArrayList<>();
        try(BufferedReader reader=Files.newBufferedReader(path);
            CSVReader openReader=new CSVReader(reader)) {
            List<String[]> records=openReader.readAll();
            for (String[] record : records) {
                if (record.length>=4){
                    boolean allEmpty=record[0].trim().isEmpty() && record[1].trim().isEmpty()
                            && record[2].trim().isEmpty() && record[3].trim().isEmpty();

                    if(!allEmpty){
                        Expense expense=new Expense();
                        expense.setId(Integer.parseInt(record[0]));
                        expense.setDate(LocalDate.parse(record[1]));
                        expense.setDescription(record[2]);
                        expense.setAmount(Double.parseDouble(record[3]));
                        list.add(expense);
                    }
                }

                for (Expense expense : list) {
                    if (nextId<expense.getId()){
                        nextId=expense.getId();
                    }
                }

            }
        } catch (Exception e) {
            e.printStackTrace();;
        }
        return list;
    }

    public void saveExpensesToCsv(List<Expense> expenseList){
        try(BufferedWriter writer=Files.newBufferedWriter(path);
            CSVWriter csvWriter=new CSVWriter(writer)) {

            for (Expense expense : expenseList) {
                csvWriter.writeNext(expense.objectArray());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int generateId() {
        return ++nextId;
    }
}
