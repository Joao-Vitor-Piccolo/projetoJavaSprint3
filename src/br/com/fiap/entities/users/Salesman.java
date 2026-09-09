package br.com.fiap.entities.users;

import br.com.fiap.entities.users.BaseUser;

/**
 * Salesman
 */
public class Salesman extends BaseUser {
    public Salesman(int id, String name, String cpf, String phone, String created_at) {
        super(id, name, cpf, phone, created_at);
    }
}
