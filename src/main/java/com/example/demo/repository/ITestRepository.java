package com.example.demo.repository;
import org.springframework.data.repository.CrudRepository;

import com.example.demo.entity.Test;

public interface ITestRepository extends CrudRepository<Test, Long> {

}
