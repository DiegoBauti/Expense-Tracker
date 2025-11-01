import java.time.Month;
import java.util.List;

public class ExpenseService {
    private final List<Expense> expenseList;
    private final ExpenseRepository repository;

    public ExpenseService(ExpenseRepository repository){
        this.repository=repository;
        expenseList=repository.loadCsv();
    }

    public void addExpense(String description, double amount){
        Expense expense=new Expense(repository.generateId(),description,amount);
        if (Expense.MAX_SPENT<expense.getAmount()) throw new IllegalArgumentException("The allocated expenditure exceeds the permitted amount:"+Expense.MAX_SPENT);
        expenseList.add(expense);
        System.out.printf("Expense added successfully (ID: %d)",expense.getId());
    }

    public void listExpenses(){
        StringBuilder sb=new StringBuilder("ID\tDate\t\tDescription\t\tAmount\n");
        for (Expense expense : expenseList) {
            sb.append(expense.toString()).append("\n");
        }
        System.out.println(sb);
    }

    public void deleteExpense(int id){
        Expense expense=null;
        for (Expense exp : expenseList) {
            if(exp.getId()==id){
                expense=exp;
            }
        }
        if(expense!=null){
            expenseList.remove(expense);
            System.out.println("Expense deleted successfully");
        }else {
            System.out.printf("No expense found with ID %d",id);
        }
    }

    public void summary(){
        double total=0;
        for (Expense expense : expenseList) {
            total+=expense.getAmount();
        }
        System.out.printf("Total expenses: $ %.2f",total);
    }

    public void updateExpense(int id, double amount){
        for (Expense exp : expenseList) {
            if(exp.getId()==id){
                exp.setAmount(amount);
            }
        }
    }

    public void summaryMonth(int month){
        double total=0;
        for (Expense expense : expenseList) {
            if(expense.getDate().getMonthValue()==month){
                total+=expense.getAmount();
            }
        }
        System.out.printf("Total expenses for %s : $ 2.f% "+Month.of(month).toString().toLowerCase(),total);
    }

    public void writeCsv(){
        repository.saveExpensesToCsv(expenseList);
    }

}
