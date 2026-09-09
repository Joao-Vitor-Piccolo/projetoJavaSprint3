package br.com.fiap.entities.sale;

/**
 * BeforeSale
 */
public class BeforeSale {
    private String id_sale;
    private String fk_salesman;
    private String fk_customer;
    private boolean is_started;
    private boolean is_finished;
    private String created_at;

    public BeforeSale(String id_sale, String fk_salesman,
            String fk_customer, boolean is_started,
            boolean is_finished,
            String created_at) {
        this.id_sale = id_sale;
        this.fk_salesman = fk_salesman;
        this.fk_customer = fk_customer;
        this.is_started = is_started;
        this.is_finished = is_finished;
        this.created_at = created_at;
    }

    public String getId_sale() {
        return id_sale;
    }

    public String getFk_salesman() {
        return fk_salesman;
    }

}
