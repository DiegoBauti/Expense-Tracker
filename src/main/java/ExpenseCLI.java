public class ExpenseCLI {

    private final ExpenseService service;

    public ExpenseCLI(ExpenseService service){
        this.service=service;
    }

    public void run(String[] args) {
        if (args.length>0) {
            switch (args[0]) {
                case "add"-> {
                    if (args.length < 5) {
                        System.out.println("Usage: add --description <text> --amount <number>");
                        break;
                    }
                    if (args[1].equals("--description") && args[3].equals("--amount")) {
                        try {
                            double num = Double.parseDouble(args[4]);
                            if (num > 0) {
                                service.addExpense(args[2], num);
                            }
                        } catch (Exception e) {
                            throw new IllegalArgumentException("The amount placed is not a number");
                        }
                    }
                }
                case "list"-> service.listExpenses();
                case "summary"-> {
                    if (args.length==1){
                        service.summary();
                    }else if (args.length == 3 && args[1].equals("--month") ) {
                        try {
                            int month = Integer.parseInt(args[2]);
                            if (month > 0 && month <= 12) {
                                service.summaryMonth(month);
                            }
                        } catch (Exception ex) {
                            throw new IllegalArgumentException("The month entered is not a number");
                        }
                    }else{
                        System.out.println("Usage: summary [--month <1-12>]");
                    }

                }

                case "delete"->{
                    if (args.length < 3) {
                        System.out.println("Usage: delete --id <number>");
                        break;
                    }
                    if (args[1].equals("--id") && !args[2].isEmpty()) {
                        try {
                            int num = Integer.parseInt(args[2]);
                            service.deleteExpense(num);
                        } catch (Exception e) {
                            throw new IllegalArgumentException("The ID entered is not a number");
                        }
                    }
                }

                case "update"-> {
                    if (args.length < 5) {
                        System.out.println("Usage: update --id <number> --amount <number>");
                        break;
                    }
                    if (args[1].equals("--id") && (args[3].equals("--amount"))) {
                        try {
                            int num = Integer.parseInt(args[2]);
                            double amount = Double.parseDouble(args[4]);
                            if (num >= 0) {
                                service.updateExpense(num, amount);
                            }
                        } catch (Exception e) {
                            throw new IllegalArgumentException("One of the arguments is not a number");
                        }
                    }
                }
                default -> System.out.println("Invalid command. Try: add, list, summary, delete, update");

            }
            if (!args[0].equals("list") && !args[0].equals("summary")){
                service.writeCsv();
            }
        }
    }
}
