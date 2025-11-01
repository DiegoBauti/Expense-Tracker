import java.time.LocalDate;
import java.util.Objects;

public class Expense {

    private int id;
    private String description;
    private LocalDate date;
    private double amount;
    public static final double MAX_SPENT=500;

    public Expense() {
    }

    public Expense(int id, String description, double amount) {
        this.id=id;
        this.amount = amount;
        this.description = description;
        this.date=LocalDate.now();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String[] objectArray(){
        String[] expense= {String.valueOf(id),String.valueOf(date),description,String.valueOf(amount)};
        return expense;
    }

    @Override
    public String toString() {
        return String.format("%s\t%s\t%s\t\t$ %s",id,date,description,amount);
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Expense expense)) return false;
        return getId() == expense.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }
}
