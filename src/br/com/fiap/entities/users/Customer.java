package br.com.fiap.entities.users;

import br.com.fiap.entities.users.BaseUser;

/**
 * Customer
 */
class Customer extends BaseUser {
    private String company;

    public Customer(int id, String name, String cpf, String company, String phone, String created_at) {
        super(id, name, cpf, phone, created_at);
        this.company = company;
    }

    public String getCompany() {
        return company;
    }
}
