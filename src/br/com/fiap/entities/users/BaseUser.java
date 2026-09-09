package br.com.fiap.entities.users;

/**
 * BaseUser
 */
public class BaseUser {
    private int id;
    private String name;
    private String cpf;
    private String phone;
    private String created_at; // TODO: change to Date type

    public BaseUser(int id, String name, String cpf, String phone, String created_at) {
        this.id = id;
        this.name = name;
        this.cpf = cpf;
        this.phone = phone;
        this.created_at = created_at;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCpf() {
        return cpf;
    }

    public String getCreatedAt() {
        return created_at;
    }

    public String getPhone() {
        return phone;
    }
}
