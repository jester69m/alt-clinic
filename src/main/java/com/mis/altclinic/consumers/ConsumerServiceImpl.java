package com.mis.altclinic.consumers;

import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ConsumerServiceImpl implements UserDetailsService , ConsumerService{

    private final ConsumerRepository consumerRepository;
    private final PasswordEncoder encoder;
    private final static String USER_NOT_FOUND =
            "user with email %s not found";

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return consumerRepository.findByEmail(email)
                .orElseThrow(() ->
                        new UsernameNotFoundException(
                                String.format(USER_NOT_FOUND,email)));
    }

    @Override
    public Optional<Consumer> getConsumerById(Long id) {
        return consumerRepository.findById(id);
    }

    @Override
    public List<Consumer> findAll() {
        return consumerRepository.findAll();
    }

    @Override
    public Optional<Consumer> getConsumerByEmail(String email) {
        return consumerRepository.findByEmail(email);
    }

    @Override
    public Consumer save(Consumer consumer) {
        consumer.setPassword(encoder.encode(consumer.getPassword()));
        return consumerRepository.save(consumer);
    }

    @Override
    public void saveAll(List<Consumer> consumers) {
        for(Consumer consumer : consumers)
            consumer.setPassword(encoder.encode(consumer.getPassword()));
        consumerRepository.saveAll(consumers);
    }

    @Override
    public void deleteConsumer(Long id) {
        consumerRepository.deleteById(id);
    }

    @Override
    public void deleteAll(){
        consumerRepository.deleteAll();
    }

    @Override
    public void updateConsumer(Long id, String first_name, String last_name, String email, String password, String patronymic, String phone_number, String address, Integer age, String blood_type, Boolean enabled) {
        Optional<Consumer> consumerOptional = consumerRepository.findById(id);
        if(consumerOptional.isEmpty()){
            throw new IllegalStateException("Consumer with id " + id + " does not exist");
        }
        Consumer consumer = consumerOptional.get();
        if(first_name != null && first_name.length() > 0 && !first_name.equals(consumer.getFirst_name())){
            consumer.setFirst_name(first_name);
        }
        if(last_name != null && last_name.length() > 0 && !last_name.equals(consumer.getLast_name())){
            consumer.setLast_name(last_name);
        }
        if(email != null && email.length() > 0 && !email.equals(consumer.getEmail())){
            consumer.setEmail(email);
        }
        if(password != null && password.length() > 0 && !password.equals(consumer.getPassword())){
            consumer.setPassword(password);
        }
        if(patronymic != null && patronymic.length() > 0 && !patronymic.equals(consumer.getPatronymic())){
            consumer.setPatronymic(patronymic);
        }
        if(phone_number != null && phone_number.length() > 0 && !phone_number.equals(consumer.getPhone_number())){
            consumer.setPhone_number(phone_number);
        }
        if(address != null && address.length() > 0 && !address.equals(consumer.getAddress())){
            consumer.setAddress(address);
        }
        if(age != null && age > 0 && !age.equals(consumer.getAge())){
            consumer.setAge(age);
        }
        if(blood_type != null && blood_type.length() > 0 && !blood_type.equals(consumer.getBlood_type())){
            consumer.setBlood_type(blood_type);
        }
        if(enabled != null && !enabled.equals(consumer.getEnabled())){
            consumer.setEnabled(enabled);
        }
        consumerRepository.save(consumer);
    }
}
