package com.sa6.projeto.thymeleaf.springboot.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import com.sa6.projeto.thymeleaf.springboot.modelo.Cronograma;

public interface CronogramaRepositorio extends JpaRepository<Cronograma, Long> {

}
