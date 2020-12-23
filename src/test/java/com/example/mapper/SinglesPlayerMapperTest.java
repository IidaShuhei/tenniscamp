package com.example.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class SinglesPlayerMapperTest {

	@Autowired
	SinglesPlayerMapper mapper;
	
	@Test
	public void シングルス選手が10人以下() {
		int count = mapper.findAll().size();
		assertThat(count).isLessThan(10);
	}
	
}
