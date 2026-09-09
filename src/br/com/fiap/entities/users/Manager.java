package br.com.fiap.entities.users;

import br.com.fiap.entities.users.BaseUser;

/**
 * Manager
 */
public class Manager extends BaseUser {
    private String phone;

    public Manager(int id, String name, String cpf, String phone, String created_at) {
        super(id, name, cpf, created_at);
        this.phone = phone;
    }
}
