public class Main {
    public static void main(String[] args) {
        ExpenseRepository repository=new ExpenseRepository();
        ExpenseService service=new ExpenseService(repository);
        ExpenseCLI cli=new ExpenseCLI(service);
//        args=new String[]{"add","--description","dadasdasd","--amount","50"};
        args=new String[]{"summary"};

        cli.run(args);
    }
}