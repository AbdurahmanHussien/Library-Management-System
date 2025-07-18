package com.springboot.librarysystem.dataInitializer;
import com.springboot.librarysystem.constants.RoleNames;
import com.springboot.librarysystem.entity.auth.Role;
import com.springboot.librarysystem.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class RoleInitializer implements CommandLineRunner {

    private final RoleRepository roleRepository;



    @Override
    public void run(String... args) {
        if (!roleRepository.existsRoleByRole(RoleNames.ADMINISTRATOR)) {
            roleRepository.save(new Role(RoleNames.ADMINISTRATOR));
        }

        if (!roleRepository.existsRoleByRole(RoleNames.LIBRARIAN)) {
            roleRepository.save(new Role(RoleNames.LIBRARIAN));
        }
        if (!roleRepository.existsRoleByRole(RoleNames.BORROWERS)) {
            roleRepository.save(new Role(RoleNames.BORROWERS));
        }
        if (!roleRepository.existsRoleByRole(RoleNames.STAFF)) {
            roleRepository.save(new Role(RoleNames.STAFF));
        }
    }
}
