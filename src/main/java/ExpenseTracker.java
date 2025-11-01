public class ExpenseTracker {
    public static void main(String[] args) {
        ExpenseRepository repository=new ExpenseRepository();
        ExpenseService service=new ExpenseService(repository);
        ExpenseCLI cli=new ExpenseCLI(service);

        cli.run(args);
    }
}