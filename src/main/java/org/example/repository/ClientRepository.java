package org.example.repository;

import lombok.extern.log4j.Log4j2;
import org.example.entity.Client;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Log4j2
public class ClientRepository {

    private final JdbcTemplate jdbc;
    private final RowMapper<Client> mapper;

    private Client one(String sql, Object... args) {
        var res = jdbc.query(sql, mapper, args);
        return res.size() == 0 ? null : res.get(0);
    }

    public ClientRepository(JdbcTemplate jdbc, PaymentTypeRepository paymentTypeRepository, ProductRepository productRepository) {
        this.jdbc = jdbc;
        this.mapper = (rs, i) -> new Client()
            .setPassportNumber(rs.getString("passport_number"))
            .setName(rs.getString("name"))
            .setPhone(rs.getString("phone"))
            .setAddress(rs.getString("address"))
            .setDelivery(rs.getInt("delivery") == 1)
            .setCredit(rs.getInt("payment_is_credit") == 1)
            .setPaymentType(paymentTypeRepository.get(rs.getInt("payment_type_id")))
            .setProduct(productRepository.get(rs.getInt("product_code")));
    }

    public List<Client> all() {
        return jdbc.query("call client_all()", mapper);
    }

    public List<Client> searchByBrandId(int brandId) {
        return jdbc.query(
            "call client_search_brand(?)",
            mapper,
            brandId
        );
    }

    public List<Client> searchByPaymentType(int paymentTypeId) {
        return jdbc.query(
            "call client_search_payment_type(?)",
            mapper,
            paymentTypeId
        );
    }

    public Client get(String passportNumber) {
        var res = one("call client_get(?)", passportNumber);
        if (res == null) throw new NotFoundException("Client with passport number " + passportNumber + " not found");
        return res;
    }

    public Client add(Client client) {
        log.info("client add " + client);
        return one("call client_add(?,?,?,?,?,?,?,?)",
                   client.getPassportNumber(),
                   client.getName(),
                   client.getPhone(),
                   client.getAddress(),
                   client.isDelivery() ? 1 : 0,
                   client.isCredit() ? 1 : 0,
                   client.getPaymentType().getId(),
                   client.getProduct().getCode());
    }

    public Client update(String passportNumber, Client client) {
        log.info("client update " + passportNumber + "; " + client);
        return one("call client_update(?,?,?,?,?,?,?,?,?)",
                   passportNumber,
                   client.getPassportNumber(),
                   client.getName(),
                   client.getPhone(),
                   client.getAddress(),
                   client.isDelivery() ? 1 : 0,
                   client.isCredit() ? 1 : 0,
                   client.getPaymentType().getId(),
                   client.getProduct().getCode());
    }

    public void remove(String passportNumber) {
        log.info("client remove " + passportNumber);
        jdbc.update("call client_delete(?)", passportNumber);
    }

}
