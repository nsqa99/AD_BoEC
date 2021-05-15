package com.example.demo.config;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.example.demo.common.Erole;
import com.example.demo.entity.FullName;
import com.example.demo.entity.Role;
import com.example.demo.entity.User;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.UserRepository;

@Component
public class InsertData implements ApplicationListener<ContextRefreshedEvent>, InitializingBean {

	private static boolean eventFired = false;
	private static final Logger logger = LoggerFactory.getLogger(InsertData.class);

	@Autowired
	private UserRepository repos;

	@Autowired
	private RoleRepository roleRepos;

	@Autowired
	private PasswordEncoder encoder;

	@Override
	public void afterPropertiesSet() throws Exception {
		if (eventFired) {
			return;
		}

		logger.info("Application started.");

		eventFired = true;

		try {
			createRoles();
			createAdminUser();
			createStaffSaleUser();
			createStaffBusinessUser();
			createStaffStockUser();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void createRoles() {
		List<Role> roleNames = new ArrayList<>();
		roleNames.add(new Role(Erole.ROLE_ADMIN));
		roleNames.add(new Role(Erole.ROLE_STAFF_BUSINESS));
		roleNames.add(new Role(Erole.ROLE_STAFF_SALE));
		roleNames.add(new Role(Erole.ROLE_STAFF_STOCK));
		roleNames.add(new Role(Erole.ROLE_USER));
		
		for (Role roleName : roleNames) {
			if (roleRepos.existsByName(roleName.getName())) {
				return;
			}
			roleName.setName(roleName.getName());
			try {
				roleRepos.save(roleName);
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}

	private void createAdminUser() {
		if (repos.existsByUsername("admin")) {
			return;
		} else if (repos.existsByEmail("admin@gmail.com")) {
			return;
		} else {
			FullName fullName = new FullName("User", "Admin");
			User admin = new User("0999999999", "admin@gmail.com", "admin", encoder.encode("admin"), fullName, null);

			Set<Role> roles = new HashSet<Role>();
			Role role = roleRepos.findByName(Erole.ROLE_ADMIN)
					.orElseThrow(() -> new RuntimeException("Error: Role is not found"));
			roles.add(role);
			admin.setRoles(roles);
			try {
				repos.save(admin);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	private void createStaffSaleUser() {
		if (repos.existsByUsername("staffsale")) {
			return;
		} else if (repos.existsByEmail("staffsale@gmail.com")) {
			return;
		} else {
			FullName fullName = new FullName("User", "Staff Sale");
			User admin = new User("0999999999", "staffsale@gmail.com", "staffsale", encoder.encode("staffsale"), fullName, null);

			Set<Role> roles = new HashSet<Role>();
			Role role = roleRepos.findByName(Erole.ROLE_STAFF_SALE)
					.orElseThrow(() -> new RuntimeException("Error: Role is not found"));
			roles.add(role);
			admin.setRoles(roles);
			try {
				repos.save(admin);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	private void createStaffBusinessUser() {
		if (repos.existsByUsername("staffbusiness")) {
			return;
		} else if (repos.existsByEmail("staffbusiness@gmail.com")) {
			return;
		} else {
			FullName fullName = new FullName("User", "Staff Business");
			User admin = new User("0999999999", "staffbusiness@gmail.com", "staffbusiness", encoder.encode("staffbusiness"), fullName, null);

			Set<Role> roles = new HashSet<Role>();
			Role role = roleRepos.findByName(Erole.ROLE_STAFF_BUSINESS)
					.orElseThrow(() -> new RuntimeException("Error: Role is not found"));
			roles.add(role);
			admin.setRoles(roles);
			try {
				repos.save(admin);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	private void createStaffStockUser() {
		if (repos.existsByUsername("staffstock")) {
			return;
		} else if (repos.existsByEmail("staffstock@gmail.com")) {
			return;
		} else {
			FullName fullName = new FullName("User", "Staff Stock");
			User admin = new User("0999999999", "staffstock@gmail.com", "staffstock", encoder.encode("staffstock"), fullName, null);

			Set<Role> roles = new HashSet<Role>();
			Role role = roleRepos.findByName(Erole.ROLE_STAFF_STOCK)
					.orElseThrow(() -> new RuntimeException("Error: Role is not found"));
			roles.add(role);
			admin.setRoles(roles);
			try {
				repos.save(admin);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		// TODO Auto-generated method stub

	}

}
