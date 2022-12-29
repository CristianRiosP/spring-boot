package com.fundamentos.springboot.fundamentos;

import com.fundamentos.springboot.fundamentos.bean.MyBEan;
import com.fundamentos.springboot.fundamentos.bean.MyBeanWhithDependency;
import com.fundamentos.springboot.fundamentos.bean.MyBeanWhithProperties;
import com.fundamentos.springboot.fundamentos.component.ComponentDependency;
import com.fundamentos.springboot.fundamentos.entity.User;
import com.fundamentos.springboot.fundamentos.repository.UserRepository;
import com.fundamentos.springboot.fundamentos.service.UserService;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Sort;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class FundamentosApplication implements CommandLineRunner {
 	private final Log LOGGER = LogFactory.getLog(FundamentosApplication.class);
	private final ComponentDependency componentDependency;
	private final MyBEan myBEan;
	private final MyBeanWhithDependency myBeanWhithDependency;
	private final MyBeanWhithProperties myBeanWhithProperties;
	private final UserRepository userRepository;
	private final UserService userService;

	public FundamentosApplication(@Qualifier("componentTwo") ComponentDependency componentDependency, MyBEan myBEan, MyBeanWhithDependency myBeanWhithDependency,
								  MyBeanWhithProperties myBeanWhithProperties,UserRepository userRepository,
								  UserService userService){
		this.componentDependency=componentDependency;
		this.myBEan =myBEan;
		this.myBeanWhithDependency=myBeanWhithDependency;
		this.myBeanWhithProperties= myBeanWhithProperties;
		this.userRepository = userRepository;
		this.userService=userService;

	}

	public static void main(String[] args) {
		SpringApplication.run(FundamentosApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		//ejemplosAnteriores();
		saveUserInDatabase();
		//getInformationJpqsqlFromUserByEmail();
		//getInformationJpqlFromUser();
		saveWithErrorTransactional();
	}
	private void saveWithErrorTransactional(){
		User test1= new User("testTransactional1","testTransactional1@algo.com",LocalDate.now());
		User test2= new User("testTransactional2","testTransactional2@algo.com",LocalDate.now());
		User test3= new User("testTransactional3","testTransactional1@algo.com",LocalDate.now());
		User test4= new User("testTransactional4","testTransactional4@algo.com",LocalDate.now());
		List<User> users = Arrays.asList(test1,test2,test3,test4);
		try{
			userService.saveTransactional(users);
		}catch (Exception e){
			LOGGER.error("Se ha generado un error en el transactional: "+e);
		}
		userService.getListUsers()
				.forEach(user -> LOGGER.info("Usuario getAllListTrasactional"+user));
	}
	private  void getInformationJpqsqlFromUserByEmail(){
		String email = "cristian@gmail.com";
		LOGGER.info("Usuario con el metodo findByUserEmail"+userRepository.findByUserEmail(email)
				.orElseThrow(()-> new RuntimeException("no se encontro el usuario")));

	}
	private void getInformationJpqlFromUser(){
		LOGGER.info("El usuario User whith method findByUserEmail"+userRepository.findByUserEmail("julie@gmail.com")
				.orElseThrow(()->new RuntimeException("No se encontro el usuario")));

		// se realiza una consulta con jpql ordenando decendente y usando un like

		userRepository.findAndSort("pe", Sort.by("id").descending())
				.forEach(user -> LOGGER.info("User with method short "+ user));

		userRepository.findByName("cristian").forEach(user -> LOGGER.info("Usuario con el query method"+ user));
		//USO DE QUERY METHOD

		/*LOGGER.info("Usuario co query method"+userRepository.findByEmailAndName("daniela@gmail.com","daniela")
				.orElseThrow(()-> new RuntimeException("Usuaro no encontrado")));

		userRepository.findByNameLike("%j%").forEach(user -> LOGGER.info("Usuario query method like"+user));

		userRepository.findBynameOrEmail("fernando",null).forEach(user -> LOGGER.info("Usuario query method findBynameOrEmail"+user));
		*/
		userRepository.findByBirthDateBetween(LocalDate.of(2021,1,1),LocalDate.of(2021,3,30))
				.forEach(user -> LOGGER.info("Usuarios con fecha findByBirthaDateBetween"+user));

		//userRepository.findByNameLikeOrderByIdDesc("%cris%").forEach(user -> LOGGER.info("Usando findByNameLikeOrderByIdDesc" +user));
		//userRepository.findByNameContainingOrderByIdAsc("cris").forEach(user -> LOGGER.info("Usando findByNameLikeOrderByIdDesc" +user));
		//userRepository.findDistinctByName("jhon").forEach(user -> LOGGER.info("Usando findByNameDistinctOrderByIdAsc" +user));
	LOGGER.info("El Usuario buscado con getAllByBirthDateAndEmail"+userRepository
			.getAllByBirthDateAndEmail(LocalDate.of(2021,3,12),"jhon@gmail.com")
			.orElseThrow(()->new RuntimeException("NO se encontro el usuario")));
	}
	private void saveUserInDatabase(){
		// Se ingresan datos en la BD tabla user para pruebas
		User user1= new User("jhon","jhon@gmail.com", LocalDate.of(2021,3,12));
		User user2= new User("julieth","julie@gmail.com", LocalDate.of(2021,5,20));
		User user3= new User("fernando","fernado@gmail.com", LocalDate.of(2021,7,12));
		User user4= new User("daniela","daniela@gmail.com", LocalDate.of(2021,11,12));
		User user5= new User("pepe","pepe@gmail.com", LocalDate.of(2021,12,12));
		User user6= new User("juan","juan@gmail.com", LocalDate.of(2021,1,13));
		User user7= new User("luis","luis@gmail.com", LocalDate.of(2021,2,14));
		User user8= new User("cristian","cristian@gmail.com", LocalDate.of(2021,5,1));
		User user9= new User("fredy","fredy@gmail.com", LocalDate.of(2021,5,16));
		User user10= new User("cristian","pepino@gmail.com", LocalDate.of(2021,3,17));

		List<User> listUser = Arrays.asList(user1,user2,user3,user4,user5,user6,user7,user8,user9,user10);


		listUser.forEach(userRepository::save);
	}

	private void ejemplosAnteriores(){
		componentDependency.saludar();
		myBEan.print();
		myBeanWhithDependency.printWithDependency();
		System.out.println(myBeanWhithProperties.function());
	}
}
