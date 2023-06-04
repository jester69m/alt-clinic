package com.mis.altclinic.consumers;

import java.util.List;
import java.util.Optional;

public interface ConsumerService {

    Optional<Consumer> getConsumerById(Long id);

    List<Consumer> findAll();

    Optional<Consumer> getConsumerByEmail(String email);

    Consumer save(Consumer consumer);

    boolean saveAll(List<Consumer> consumers);

    void deleteConsumer(Long id);

    void updateConsumer(Long id,
                        String first_name,
                        String last_name,
                        String email,
                        String password,
                        String patronymic,
                        String phone_number,
                        String address,
                        Integer age,
                        String blood_type,
                        Boolean enabled);
}
