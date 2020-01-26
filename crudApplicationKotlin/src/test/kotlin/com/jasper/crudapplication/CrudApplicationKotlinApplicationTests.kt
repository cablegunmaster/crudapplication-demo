package com.jasper.crudApplicationKotlin

import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class CrudApplicationKotlinApplicationTests {

	@Test
	fun contextLoads() {
	}

//	@Test
//	fun givenPerson_whenSaved_thenFound() {
//		doInHibernate(({ this.sessionFactory() }), { session ->
//			val personToSave = Person(0, "John")
//			session.persist(personToSave)
//			val personFound = session.find(User::class.java, personToSave.id)
//			session.refresh(personFound)
//
//			assertTrue(personToSave == personFound)
//		})
//	}
//
//	@Test
//	fun givenPersonWithFullData_whenSaved_thenFound() {
//		doInHibernate(({ this.sessionFactory() }), { session ->
//			val personToSave = Person(
//					0,
//					"John",
//					"jhon@test.com",
//					Arrays.asList(PhoneNumber(0, "202-555-0171"), PhoneNumber(0, "202-555-0102")))
//			session.persist(personToSave)
//			val personFound = session.find(Person::class.java, personToSave.id)
//			session.refresh(personFound)
//
//			assertTrue(personToSave == personFound)
//		})
//	}

}
