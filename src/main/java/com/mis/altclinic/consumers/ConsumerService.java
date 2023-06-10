package com.mis.altclinic.consumers;

import java.util.List;
import java.util.Optional;

public interface ConsumerService {

    Optional<Consumer> getConsumerById(Long id);

    List<Consumer> findAll();

    Consumer save(Consumer consumer);

    void saveAll(List<Consumer> consumers);

    void delete(Long id);

    void deleteAll();

    void update(Long id,
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
