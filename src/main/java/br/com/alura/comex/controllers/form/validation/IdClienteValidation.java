package br.com.alura.comex.controllers.form.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.alura.comex.repository.ClienteRepository;

public class IdClienteValidation implements ConstraintValidator<ValidaIdCliente, Long> {

    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    public boolean isValid(Long value, ConstraintValidatorContext context) {
        return clienteRepository.findById(value).isPresent();
    }
}
